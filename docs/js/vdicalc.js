/* VDI Calculator - Client-side calculation engine
 * Ported from Java Swing desktop application
 * Licensed under Apache License 2.0
 * Original author: Andre Leibovici
 */

(function () {
  'use strict';

  // ── Constants (from DefaultValuesBundle.properties) ──
  const VIEW_DEFAULTS = {
    cs: { memory: 10240, storage: 40960, vcpu: 4 },
    ss: { memory: 10240, storage: 40960, vcpu: 4 },
    composer: { memory: 8192, storage: 61440, vcpu: 4 },
    vcenter: { memory: 24576, storage: 307200, vcpu: 8 }
  };

  const XEN_DEFAULTS = {
    controller: { memory: 10240, storage: 102400, vcpu: 4 },
    vcenter: { memory: 8192, storage: 61440, vcpu: 4 }
  };

  const CBRC_REDUCTOR = 0.65;

  // ── Workload Profiles ──
  const PROFILES = {
    task: { vcpu: '1', mhz: 500, overhead: '0', memory: 1536, poolType: 'linked', deltaIO: 6, displayNum: '1', displayRes: '1920x1200', vram: '0', vmCore: '5' },
    office: { vcpu: '1', mhz: 500, overhead: '0', memory: 2048, poolType: 'linked', deltaIO: 8, displayNum: '1', displayRes: '1920x1200', vram: '0', vmCore: '5' },
    knowledge: { vcpu: '2', mhz: 315, overhead: '0', memory: 2048, poolType: 'linked', deltaIO: 9, displayNum: '1', displayRes: '1920x1200', vram: '0', vmCore: '4' },
    power: { vcpu: '2', mhz: 625, overhead: '0', memory: 4096, poolType: 'full', deltaIO: 11, displayNum: '2', displayRes: '2560x1600', vram: '256', vmCore: '2' }
  };

  // ── Helper: get element value ──
  function getInt(id) { return parseInt(document.getElementById(id).value, 10) || 0; }
  function getStr(id) { return document.getElementById(id).value; }
  function getBool(id) { return document.getElementById(id).value === 'true'; }
  function setResult(id, val) {
    const el = document.getElementById(id);
    if (el) el.textContent = typeof val === 'number' ? (Number.isInteger(val) ? val.toLocaleString() : val.toFixed(2)) : val;
  }

  // ── VM Calculations (vm.java) ──

  function calcMemoryOverhead(vmVcpu, vmMemory, vmVram, displayCount, displayRes) {
    const memGB = Math.round(vmMemory / 1024.0);
    let cpuOverhead = 0;
    const overheadMap = { 1: { 1: 71.2, 2: 107.62 }, 2: { 1: 90.23, 2: 137.75 }, 4: { 1: 128.5, 2: 197.99 } };
    const entry = overheadMap[memGB] || overheadMap[1];
    cpuOverhead = (entry && entry[vmVcpu]) || entry[1] || 71.2;

    let videoOverhead = 0;
    if (vmVram === 0) {
      const videoMap = {
        1: { '1280x800': 4, '1920x1200': 8, '2560x1600': 16 },
        2: { '1280x800': 12.5, '1920x1200': 25.4, '2560x1600': 60 },
        3: { '1280x800': 18.75, '1920x1200': 38, '2560x1600': 84.8 },
        4: { '1280x800': 25, '1920x1200': 50.6, '2560x1600': 109.6 }
      };
      const dEntry = videoMap[displayCount];
      if (dEntry) videoOverhead = dEntry[displayRes] || 0;
    }

    return Math.round(cpuOverhead + vmVram + videoOverhead);
  }

  function calcVideoSwapSize(displayCount, displayRes, vmVram) {
    if (vmVram !== 0) {
      const vramSwap = { 64: 1076, 128: 1468, 256: 1468, 512: 1916 };
      return vramSwap[vmVram] || 1076;
    }
    const swapMap = {
      1: { '1280x800': 107, '1920x1200': 111, '2560x1600': 203 },
      2: { '1280x800': 163, '1920x1200': 190, '2560x1600': 203 },
      3: { '1280x800': 207, '1920x1200': 248, '2560x1600': 461 },
      4: { '1280x800': 252, '1920x1200': 306, '2560x1600': 589 }
    };
    return (swapMap[displayCount] && swapMap[displayCount][displayRes]) || 111;
  }

  function calcSwapSize(memReservation, memory) {
    return Math.floor((1.0 - memReservation / 100.0) * memory);
  }

  function calcDeltaSize(fullCloneSize, refreshRate) {
    return Math.floor((fullCloneSize * refreshRate) / 100);
  }

  // ── Host Calculations (host.java) ──

  function calcVmCountPerHost(concurrentVms, hostCores, vmsPerCore) {
    const max = vmsPerCore * hostCores;
    return concurrentVms < max ? concurrentVms : max;
  }

  function calcHostCount(concurrentVms, vmsPerHost, haMultiplier) {
    let f = concurrentVms / vmsPerHost;
    f = Math.ceil(f * 2) / 2;
    f = Math.round(f);
    f = f * haMultiplier;
    f = Math.ceil(f * 2) / 2;
    if ((f % 1) === 0.5) f += 0.5;
    return Math.floor(f);
  }

  function calcCoreMhz(vmVcpu, vmVcpuMhz, vmsPerHost, hostCores, vcpuOverhead) {
    let i = ((vmVcpu * vmVcpuMhz) * vmsPerHost) / hostCores;
    const overhead = (vcpuOverhead / 100.0) + 1;
    i = Math.floor(i * overhead);
    return i;
  }

  function calcHostMemoryNoTPS(hostMemOverhead, vmPerHost, vmMemTotal, cbrcMem) {
    const vmMem = vmPerHost * vmMemTotal;
    return Math.floor((hostMemOverhead + vmMem + cbrcMem) / 1024);
  }

  function calcHostMemoryTPS(hostMemOverhead, vmPerHost, vmMemTotal, cbrcMem, sharedMem, usedMem) {
    const vmMem = vmPerHost * vmMemTotal;
    const hostCpuAllocated = (100 - usedMem) + (100 - sharedMem);
    const overcommit = (hostCpuAllocated / 100.0) * vmMem;
    return Math.floor((hostMemOverhead + overcommit + cbrcMem) / 1024);
  }

  function calcLocalSwapSize(vmSwap, videoSwap, vmPerHost) {
    return Math.floor(((vmSwap + videoSwap) * vmPerHost) / 1024);
  }

  // ── Cluster Calculations (cluster.java) ──

  function calcClusterCount(totalHosts, clusterSize) {
    return Math.ceil(totalHosts / clusterSize);
  }

  function calcHaMultiplier(haEnabled) {
    return haEnabled ? 1.125 : 1.0;
  }

  // ── Storage Calculations (storage.java) ──

  function calcCloneUsableCapacity(fullClone, delta, disposable, logSize, swap, videoSwap, vmCount, overhead, dedup, cloneType) {
    let d = ((fullClone + delta + disposable + logSize + swap + videoSwap) * vmCount) / 1048576;
    d *= (1.0 + overhead / 100.0);
    if (cloneType === 'full' || cloneType === 'assigned') {
      d *= (1.0 - dedup / 100.0);
    }
    return Math.round(d * 100) / 100;
  }

  function calcReplicaUsableCapacity(thinSize, snapshots, pools, datastoreCount, overhead, tiering) {
    let d;
    if (tiering === 'On') {
      d = (thinSize * pools * snapshots) / 1048576;
    } else {
      d = (thinSize * snapshots * pools * datastoreCount) / 1048576;
    }
    d *= (1.0 + overhead / 100.0);
    return Math.round(d * 100) / 100;
  }

  function calcParentUsableCapacity(fullSizeParent, parentCount, overhead) {
    let d = (fullSizeParent * parentCount) / 1048576;
    d *= (1.0 + overhead / 100.0);
    return Math.round(d * 100) / 100;
  }

  function calcPersistentUsableCapacity(persistSize, vmCount, overhead, dedup) {
    let d = (persistSize * vmCount) / 1048576;
    d *= (1.0 + overhead / 100.0);
    d *= (1.0 - dedup / 100.0);
    return Math.round(d * 100) / 100;
  }

  function calcSuspendCapacity(vmCount, vmMemory) {
    return Math.round((vmCount * vmMemory / 1048576) * 100) / 100;
  }

  function calcSwapPowerstateReduction(vmCount, concurrent, swap) {
    let d = (vmCount - concurrent) * swap / 1048576;
    d = Math.round(d * 100) / 100;
    return d * -1;
  }

  function calcDisposableCapacity(vmCount, concurrent, disposable) {
    let d = (vmCount - concurrent) * disposable / 1048576;
    d = Math.round(d * 100) / 100;
    return d * -1;
  }

  function calcLocalSwapTotalCapacity(hostCount, localSwap) {
    let d = (hostCount * localSwap) / 1024;
    return Math.round(d * 100) / 100 * -1;
  }

  function calcDatastoreCount(vmCount, perDs) {
    return Math.ceil(vmCount / perDs);
  }

  function calcDatastoreSize(dsCount, cloneCapacity, replicaCapacity) {
    let d = ((cloneCapacity + replicaCapacity) * 1048576) / dsCount;
    d = Math.ceil(d);
    return Math.floor(d / 1024);
  }

  function calcPersistentDsSize(dsCount, persistCapacity) {
    let d = (persistCapacity * 1048576) / dsCount;
    return Math.ceil(d);
  }

  // ── IOPS Calculations (storage.java) ──

  function calcReplicaReadFrontendIOPS(concurrent, steadyState, bootCount, bootIOPS, cbrcMem) {
    const d0 = bootCount * bootIOPS;
    const d1 = (concurrent - bootCount) * steadyState;
    let d = Math.max(d0, d1);
    if (cbrcMem !== 0) d *= (1 - CBRC_REDUCTOR);
    return Math.floor(d);
  }

  function calcCloneReadFrontendIOPS(concurrent, deltaIO, readPct) {
    return Math.floor(concurrent * deltaIO * (readPct / 100));
  }

  function calcCloneWriteFrontendIOPS(concurrent, deltaIO, readPct) {
    return Math.floor(concurrent * deltaIO * (1 - readPct / 100));
  }

  function calcCloneReadBackendIOPS(frontendRead, cacheRead) {
    return Math.floor(frontendRead * (1 - cacheRead / 100));
  }

  function getRaidPenalty(raidType) {
    const map = { '0': 1, '5': 4, '6': 6, '10': 2, 'DP': 2 };
    return map[raidType] || 1;
  }

  function calcCloneWriteBackendIOPS(frontendWrite, raidType, cacheWrite) {
    let d = frontendWrite * getRaidPenalty(raidType);
    d *= (1 - cacheWrite / 100);
    return Math.floor(d);
  }

  function calcPersistentReadFrontendIOPS(concurrent, steadyState, readPct) {
    return Math.floor(concurrent * steadyState * (readPct / 100));
  }

  function calcPersistentWriteFrontendIOPS(concurrent, steadyState, readPct) {
    return Math.floor(concurrent * steadyState * (1 - readPct / 100));
  }

  function calcPersistentReadBackendIOPS(frontendRead, cacheRead) {
    return Math.floor(frontendRead * (1 - cacheRead / 100));
  }

  function calcPersistentWriteBackendIOPS(frontendWrite, raidType, cacheWrite) {
    let d = frontendWrite * getRaidPenalty(raidType);
    d *= (1 - cacheWrite / 100);
    return Math.floor(d);
  }

  // ── Infrastructure Calculations (view.java, xen.java) ──

  function calcConnectionServers(vmCount) {
    let f = Math.ceil(vmCount / 2000);
    f += (f >= 5) ? 2 : 1;
    return f;
  }

  function calcSecurityServers(externalSessions) {
    let f = Math.ceil(externalSessions / 2000);
    if (f > 0) f += 1;
    return f;
  }

  function calcVcenterServers(concurrent, limit) {
    return Math.ceil(concurrent / limit);
  }

  function calcDeliveryControllers(vmCount) {
    return Math.ceil(vmCount / 2000) + 1;
  }

  function calcViewInfra(cs, ss, vc) {
    return {
      memory: (cs * VIEW_DEFAULTS.cs.memory + ss * VIEW_DEFAULTS.ss.memory + vc * VIEW_DEFAULTS.vcenter.memory + VIEW_DEFAULTS.composer.memory) / 1024,
      storage: (cs * VIEW_DEFAULTS.cs.storage + ss * VIEW_DEFAULTS.ss.storage + vc * VIEW_DEFAULTS.vcenter.storage + VIEW_DEFAULTS.composer.storage) / 1048576,
      vcpu: cs * VIEW_DEFAULTS.cs.vcpu + ss * VIEW_DEFAULTS.ss.vcpu + vc * VIEW_DEFAULTS.vcenter.vcpu + VIEW_DEFAULTS.composer.vcpu
    };
  }

  function calcXenInfra(ddc, vc) {
    return {
      memory: (ddc * XEN_DEFAULTS.controller.memory + vc * XEN_DEFAULTS.vcenter.memory) / 1024,
      storage: (ddc * XEN_DEFAULTS.controller.storage + vc * XEN_DEFAULTS.vcenter.storage) / 1048576,
      vcpu: ddc * XEN_DEFAULTS.controller.vcpu + vc * XEN_DEFAULTS.vcenter.vcpu
    };
  }

  // ══════════════════════════════════════════════
  // ── Main Recalculate Function ──
  // ══════════════════════════════════════════════

  function recalculate() {
    // Read all inputs
    const broker = getStr('brokerType');
    const vmCount = getInt('vmCount');
    const concurrent = getInt('vmConcurrent');
    const pools = getInt('desktopPoolCount');
    const externalSessions = getInt('externalSessions');
    const vcVmLimit = getInt('vcVmLimit');

    const vmVcpu = getInt('vmVcpu');
    const vmMhz = getInt('vmVcpuMhz');
    const vmOverhead = getInt('vmVcpuOverhead');
    const vmMemory = getInt('vmMemorySize');
    const vmMemRes = getInt('vmMemoryReservation');
    const powerState = getStr('vmPowerState');
    const displayNum = getInt('vmDisplayNumber');
    const displayRes = getStr('vmDisplayResolution');
    const vmVram = getInt('vmVram');
    const poolType = getStr('desktopPoolType');
    const refreshRate = getInt('vmRefreshRate');

    const hostSockets = getInt('hostSockets');
    const coresPerSocket = getInt('hostCoresPerSocket');
    const vmsPerCore = getInt('hostVmCore');
    const sharedMem = getInt('hostSharedMemory');
    const usedMem = getInt('hostUsedMemory');
    const hostMemOverhead = getInt('hostMemoryOverhead');
    const cbrcMem = getInt('hostCbrc');

    const haEnabled = getBool('vcHa');
    const clusterSize = getInt('vcClusterSize');
    const includeBackend = getBool('backendInfra');
    const localSwapEnabled = getBool('hostLocalSwap');

    const fullSizeParent = getInt('vmFullSizeParent');
    const thinSizeParent = getInt('vmThinSizeParent');
    const persistDiskSize = getInt('vmPersistentDiskSize');
    const disposableDiskSize = getInt('vmDisposableDiskSize');
    const parentCount = getInt('vmParentCount');
    const snapshots = getInt('poolSnapshotCount');
    const dsPerVm = getInt('storageDatastoreVm');
    const persistPerDs = getInt('persistentPerDatastore');
    const storageOH = getInt('storageOverhead');
    const tiering = getStr('storageTiering');
    const dedup = getInt('storageDedup');

    const replicaSteadyIO = getInt('vmReplicaSteadyStateIO');
    const concurrentBoot = getInt('vmConcurrentBootCount');
    const bootIO = getInt('vmBootIO');
    const deltaIO = getInt('vmDeltaIO');
    const deltaReadPct = getInt('vmDeltaReadIO');
    const deltaRaid = getStr('deltaRaidType');
    const persistIO = getInt('vmPersistentIO');
    const persistReadPct = getInt('vmPersistentReadIO');
    const persistRaid = getStr('persistentRaidType');
    const cacheRead = getInt('storageCacheRead');
    const cacheWrite = getInt('storageCacheWrite');

    // ── VM derived values ──
    const logSize = 100;
    const memOverhead = calcMemoryOverhead(vmVcpu, vmMemory, vmVram, displayNum, displayRes);
    const vmMemTotal = vmMemory + memOverhead;
    const swapSize = calcSwapSize(vmMemRes, vmMemory);
    const videoSwap = calcVideoSwapSize(displayNum, displayRes, vmVram);
    const deltaSize = (poolType === 'linked') ? calcDeltaSize(fullSizeParent, refreshRate) : 0;
    const fullCloneForCalc = (poolType === 'full') ? fullSizeParent : 0;

    // ── Host derived values ──
    const hostCores = hostSockets * coresPerSocket;
    const vmsPerHost = calcVmCountPerHost(concurrent, hostCores, vmsPerCore);
    const haMultiplier = calcHaMultiplier(haEnabled);
    const hostCount = calcHostCount(concurrent, vmsPerHost, haMultiplier);
    const coreMhz = calcCoreMhz(vmVcpu, vmMhz, vmsPerHost, hostCores, vmOverhead);
    const hostMemNoTPS = calcHostMemoryNoTPS(hostMemOverhead, vmsPerHost, vmMemTotal, cbrcMem);
    const hostMemTPS = calcHostMemoryTPS(hostMemOverhead, vmsPerHost, vmMemTotal, cbrcMem, sharedMem, usedMem);
    const hostLocalSwap = localSwapEnabled ? calcLocalSwapSize(swapSize, videoSwap, vmsPerHost) : 0;

    // ── Cluster ──
    const clusters = calcClusterCount(hostCount, clusterSize);

    // ── Storage Capacity ──
    const cloneType = (broker === 'xen' && poolType === 'full') ? 'assigned' : poolType;

    const disposableCapacity = calcDisposableCapacity(vmCount, concurrent, disposableDiskSize);
    const localSwapTotal = localSwapEnabled ? calcLocalSwapTotalCapacity(hostCount, hostLocalSwap) : 0;

    const cloneCapacity = calcCloneUsableCapacity(
      fullCloneForCalc, deltaSize, disposableDiskSize, logSize, swapSize, videoSwap,
      vmCount, storageOH, dedup, cloneType
    ) + disposableCapacity + localSwapTotal;

    const replicaCapacity = calcReplicaUsableCapacity(
      thinSizeParent, snapshots, pools, calcDatastoreCount(vmCount, dsPerVm), storageOH, tiering
    );

    const parentCapacity = calcParentUsableCapacity(fullSizeParent, parentCount, storageOH);
    const persistentCapacity = calcPersistentUsableCapacity(persistDiskSize, vmCount, storageOH, dedup);
    const suspendCapacity = (powerState === 'suspend') ? calcSuspendCapacity(vmCount, vmMemory) : 0;
    const swapReduction = (powerState !== 'on') ? calcSwapPowerstateReduction(vmCount, concurrent, swapSize) : 0;

    const totalCapacity = Math.round((parentCapacity + replicaCapacity + cloneCapacity + persistentCapacity + suspendCapacity + swapReduction) * 100) / 100;

    // ── Datastores ──
    const dsCount = calcDatastoreCount(vmCount, dsPerVm);
    const dsSize = calcDatastoreSize(dsCount, cloneCapacity > 0 ? cloneCapacity : 0, replicaCapacity);
    const persistDsCount = persistDiskSize > 0 ? calcDatastoreCount(vmCount, persistPerDs) : 0;
    const persistDsSize = persistDsCount > 0 ? calcPersistentDsSize(persistDsCount, persistentCapacity) : 0;

    // ── IOPS ──
    const replicaFrontendIOPS = calcReplicaReadFrontendIOPS(concurrent, replicaSteadyIO, concurrentBoot, bootIO, cbrcMem);
    const cloneReadFrontend = calcCloneReadFrontendIOPS(concurrent, deltaIO, deltaReadPct);
    const cloneWriteFrontend = calcCloneWriteFrontendIOPS(concurrent, deltaIO, deltaReadPct);
    const cloneFrontendIOPS = cloneReadFrontend + cloneWriteFrontend;
    const cloneReadBackend = calcCloneReadBackendIOPS(cloneReadFrontend, cacheRead);
    const cloneWriteBackend = calcCloneWriteBackendIOPS(cloneWriteFrontend, deltaRaid, cacheWrite);
    const cloneBackendIOPS = cloneReadBackend + cloneWriteBackend;

    const persistReadFrontend = calcPersistentReadFrontendIOPS(concurrent, persistIO, persistReadPct);
    const persistWriteFrontend = calcPersistentWriteFrontendIOPS(concurrent, persistIO, persistReadPct);
    const persistFrontendIOPS = persistReadFrontend + persistWriteFrontend;
    const persistReadBackend = calcPersistentReadBackendIOPS(persistReadFrontend, cacheRead);
    const persistWriteBackend = calcPersistentWriteBackendIOPS(persistWriteFrontend, persistRaid, cacheWrite);
    const persistBackendIOPS = persistReadBackend + persistWriteBackend;

    const totalFrontendIOPS = replicaFrontendIOPS + cloneFrontendIOPS + persistFrontendIOPS;
    const totalBackendIOPS = replicaFrontendIOPS + cloneReadBackend + cloneWriteBackend + persistReadBackend + persistWriteBackend;

    // ── Infrastructure ──
    const vcCount = calcVcenterServers(concurrent, vcVmLimit);
    let csCount = 0, ssCount = 0, ddcCount = 0;
    let infra = { memory: 0, storage: 0, vcpu: 0 };

    if (broker === 'view') {
      csCount = calcConnectionServers(vmCount);
      ssCount = calcSecurityServers(externalSessions);
      if (includeBackend) infra = calcViewInfra(csCount, ssCount, vcCount);
    } else {
      ddcCount = calcDeliveryControllers(vmCount);
      if (includeBackend) infra = calcXenInfra(ddcCount, vcCount);
    }

    // ── Update Results ──
    setResult('res-hostCount', hostCount);
    setResult('res-coresPerHost', hostCores);
    setResult('res-vmsPerHost', vmsPerHost);
    setResult('res-avgHostCpu', coreMhz);
    setResult('res-hostMemNoTPS', hostMemNoTPS);
    setResult('res-hostMemTPS', hostMemTPS);
    setResult('res-localDisk', hostLocalSwap);
    setResult('res-clusters', clusters);

    setResult('res-parentCapacity', parentCapacity);
    setResult('res-replicaCapacity', replicaCapacity);
    setResult('res-cloneCapacity', Math.round(cloneCapacity * 100) / 100);
    setResult('res-persistentCapacity', persistentCapacity);
    setResult('res-disposableCapacity', disposableCapacity);
    setResult('res-localSwapCapacity', localSwapTotal);
    setResult('res-swapReduction', swapReduction);
    setResult('res-suspendCapacity', suspendCapacity);
    setResult('res-totalCapacity', totalCapacity);

    setResult('res-datastoreCount', dsCount);
    setResult('res-datastoreSize', dsSize);
    setResult('res-persistentDsCount', persistDsCount);
    setResult('res-persistentDsSize', persistDsSize);

    setResult('res-replicaFrontendIOPS', replicaFrontendIOPS);
    setResult('res-replicaBackendIOPS', replicaFrontendIOPS);
    setResult('res-cloneFrontendIOPS', cloneFrontendIOPS);
    setResult('res-cloneBackendIOPS', cloneBackendIOPS);
    setResult('res-persistentFrontendIOPS', persistFrontendIOPS);
    setResult('res-persistentBackendIOPS', persistBackendIOPS);
    setResult('res-totalFrontendIOPS', totalFrontendIOPS);
    setResult('res-totalBackendIOPS', totalBackendIOPS);

    setResult('res-vcCount', vcCount);
    setResult('res-csCount', csCount);
    setResult('res-ssCount', ssCount);
    setResult('res-ddcCount', ddcCount);
    setResult('res-infraMemory', Math.round(infra.memory * 100) / 100);
    setResult('res-infraStorage', Math.round(infra.storage * 10000) / 10000);
    setResult('res-infraVcpu', infra.vcpu);

    // Toggle broker-specific rows
    const isView = broker === 'view';
    document.getElementById('row-csCount').style.display = isView ? '' : 'none';
    document.getElementById('row-ssCount').style.display = isView ? '' : 'none';
    document.getElementById('row-ddcCount').style.display = isView ? 'none' : '';
    document.getElementById('externalSessionsRow').style.display = isView ? '' : 'none';
    document.getElementById('refreshRateRow').style.display = (poolType === 'linked') ? '' : 'none';

    // ── Warnings ──
    const warnings = [];
    if (concurrent > vmCount) warnings.push('Concurrent desktops exceeds total desktop count.');
    if (coreMhz > 4200) warnings.push('CPU utilization above recommended host limit (4200 MHz/core).');
    if (vmsPerCore >= 8) warnings.push('High VM consolidation ratio per core.');
    if (hostMemNoTPS > 768) warnings.push('Host memory utilization is very high.');
    if (sharedMem > 0) warnings.push('TPS (Transparent Page Sharing) may be disallowed for security reasons.');

    const warningsEl = document.getElementById('warnings');
    warningsEl.innerHTML = warnings.map(function (w) { return '<div class="warning-item">' + w + '</div>'; }).join('');
  }

  // ══════════════════════════════════════════════
  // ── Tab Navigation ──
  // ══════════════════════════════════════════════

  function setupTabs() {
    document.querySelectorAll('.tab').forEach(function (tab) {
      tab.addEventListener('click', function () {
        document.querySelectorAll('.tab').forEach(function (t) { t.classList.remove('active'); });
        document.querySelectorAll('.tab-content').forEach(function (c) { c.classList.remove('active'); });
        tab.classList.add('active');
        document.getElementById(tab.dataset.tab).classList.add('active');
      });
    });
  }

  // ══════════════════════════════════════════════
  // ── Workload Profile Loader ──
  // ══════════════════════════════════════════════

  function setupProfileLoader() {
    document.getElementById('workloadProfile').addEventListener('change', function () {
      var p = PROFILES[this.value];
      if (!p) return;
      document.getElementById('vmVcpu').value = p.vcpu;
      document.getElementById('vmVcpuMhz').value = p.mhz;
      document.getElementById('vmVcpuOverhead').value = p.overhead;
      document.getElementById('vmMemorySize').value = p.memory;
      document.getElementById('desktopPoolType').value = p.poolType;
      document.getElementById('vmDeltaIO').value = p.deltaIO;
      document.getElementById('vmDisplayNumber').value = p.displayNum;
      document.getElementById('vmDisplayResolution').value = p.displayRes;
      document.getElementById('vmVram').value = p.vram;
      document.getElementById('hostVmCore').value = p.vmCore;
      recalculate();
    });
  }

  // ══════════════════════════════════════════════
  // ── Save / Load Project ──
  // ══════════════════════════════════════════════

  function getProjectState() {
    var state = {};
    document.querySelectorAll('input[type=number], select').forEach(function (el) {
      if (el.id && el.id !== 'lang-select') state[el.id] = el.value;
    });
    return state;
  }

  function loadProjectState(state) {
    Object.keys(state).forEach(function (id) {
      var el = document.getElementById(id);
      if (el) el.value = state[id];
    });
    recalculate();
  }

  function setupSaveLoad() {
    document.getElementById('btn-save').addEventListener('click', function () {
      var data = JSON.stringify(getProjectState(), null, 2);
      var blob = new Blob([data], { type: 'application/json' });
      var a = document.createElement('a');
      a.href = URL.createObjectURL(blob);
      a.download = 'vdicalc-project.json';
      a.click();
      URL.revokeObjectURL(a.href);
    });

    document.getElementById('btn-load').addEventListener('click', function () {
      document.getElementById('file-input').click();
    });

    document.getElementById('file-input').addEventListener('change', function (e) {
      var file = e.target.files[0];
      if (!file) return;
      var reader = new FileReader();
      reader.onload = function (ev) {
        try {
          var state = JSON.parse(ev.target.result);
          loadProjectState(state);
        } catch (err) {
          alert('Invalid project file.');
        }
      };
      reader.readAsText(file);
      e.target.value = '';
    });
  }

  // ══════════════════════════════════════════════
  // ── PDF Export ──
  // ══════════════════════════════════════════════

  function setupPdfExport() {
    document.getElementById('btn-pdf').addEventListener('click', function () {
      var content = '';
      content += 'VDI Calculator Report\n';
      content += '=====================\n';
      content += 'Generated: ' + new Date().toLocaleString() + '\n';
      content += 'Broker: ' + getStr('brokerType').toUpperCase() + '\n\n';

      document.querySelectorAll('.result-group').forEach(function (group) {
        var heading = group.querySelector('h3');
        if (heading) content += '\n' + heading.textContent + '\n' + '─'.repeat(40) + '\n';
        group.querySelectorAll('.result-row').forEach(function (row) {
          if (row.style.display === 'none') return;
          var spans = row.querySelectorAll('span');
          if (spans.length === 2) {
            content += spans[0].textContent.padEnd(35) + spans[1].textContent + '\n';
          }
        });
      });

      var warnings = document.querySelectorAll('.warning-item');
      if (warnings.length > 0) {
        content += '\nWarnings\n' + '─'.repeat(40) + '\n';
        warnings.forEach(function (w) { content += '• ' + w.textContent + '\n'; });
      }

      var blob = new Blob([content], { type: 'text/plain' });
      var a = document.createElement('a');
      a.href = URL.createObjectURL(blob);
      a.download = 'vdicalc-report.txt';
      a.click();
      URL.revokeObjectURL(a.href);
    });
  }

  // ══════════════════════════════════════════════
  // ── About Modal ──
  // ══════════════════════════════════════════════

  function setupAboutModal() {
    var modal = document.getElementById('about-modal');
    document.getElementById('btn-about').addEventListener('click', function () {
      modal.classList.add('active');
    });
    modal.querySelector('.modal-close').addEventListener('click', function () {
      modal.classList.remove('active');
    });
    modal.addEventListener('click', function (e) {
      if (e.target === modal) modal.classList.remove('active');
    });
  }

  // ══════════════════════════════════════════════
  // ── i18n Integration ──
  // ══════════════════════════════════════════════

  function applyI18n(lang) {
    if (!window.I18N || !window.I18N[lang]) return;
    var strings = window.I18N[lang];
    document.querySelectorAll('[data-i18n]').forEach(function (el) {
      var key = el.getAttribute('data-i18n');
      if (strings[key]) el.textContent = strings[key];
    });
  }

  function setupI18n() {
    document.getElementById('lang-select').addEventListener('change', function () {
      applyI18n(this.value);
    });
  }

  // ══════════════════════════════════════════════
  // ── Auto-recalculate on any input change ──
  // ══════════════════════════════════════════════

  function setupAutoCalc() {
    document.querySelectorAll('input, select').forEach(function (el) {
      if (el.id === 'lang-select' || el.id === 'file-input') return;
      el.addEventListener('change', recalculate);
      el.addEventListener('input', recalculate);
    });
  }

  // ══════════════════════════════════════════════
  // ── Initialize ──
  // ══════════════════════════════════════════════

  function init() {
    setupTabs();
    setupProfileLoader();
    setupAutoCalc();
    setupSaveLoad();
    setupPdfExport();
    setupAboutModal();
    setupI18n();
    recalculate();
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }

})();
