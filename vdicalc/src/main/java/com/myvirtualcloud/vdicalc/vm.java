package com.myvirtualcloud.vdicalc;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author andreleibovici
 * @version $Revision: 1.0 $
 */
public class vm {
	/**
	 * Field snapshotcount.
	 */
	private int snapshotcount;
	/**
	 * Field count.
	 */
	private int count; // number of vms
	/**
	 * Field concurrentcount.
	 */
	private int concurrentcount; // number of concurrent vms
	/**
	 * Field memorysize.
	 */
	private int memorysize; // memory size
	/**
	 * Field memoryoverhead.
	 */
	private int memoryoverhead; // memory overhead defined by cpu and memory
	/**
	 * Field memorytotal.
	 */
	private int memorytotal; // memory + total memory overhead
	/**
	 * Field memoryreservation.
	 */
	private int memoryreservation; // memory reservation
	/**
	 * Field fullclonesize.
	 */
	private int fullclonesize; // full clones size
	/**
	 * Field deltasize.
	 */
	private int deltasize; // linked clone delta size
	/**
	 * Field persistentdisksize.
	 */
	private int persistentdisksize; // persistent disk size
	/**
	 * Field disposabledisksize.
	 */
	private int disposabledisksize; // disposable disk size
	/**
	 * Field logsize.
	 */
	private int logsize; // log size
	/**
	 * Field swapsize.
	 */
	private int swapsize; // swap size
	/**
	 * Field videoswapsize.
	 */
	private int videoswapsize; // video swap size
	/**
	 * Field storagesize.
	 */
	private int storagesize; // storage used by vm
	/**
	 * Field storagesizetotal.
	 */
	private double storagesizetotal = 0.0; // total storage used by vm
	/**
	 * Field thinsize.
	 */
	private int thinsize; // used for replica sizing
	/**
	 * Field parentcount.
	 */
	private int parentcount; // number of parent vms
	/**
	 * Field parentsize.
	 */
	private int parentsize; // size of parent vm
	/**
	 * Field refreshrate.
	 */
	private int refreshrate; // linked clone refresh rate
	/**
	 * Field displaycount.
	 */
	private int displaycount; // number of displays
	/**
	 * Field displayresolution.
	 */
	private String displayresolution = ""; // display resolution
	/**
	 * Field vram.
	 */
	private int vram = 0; // video ram size
	/**
	 * Field vcpucount.
	 */
	private int vcpucount; // number of vcpu
	/**
	 * Field vcpumhz.
	 */
	private int vcpumhz; // average vcpu mhz
	/**
	 * Field vcpuoverhead.
	 */
	private int vcpuoverhead; // overhead to be added to total vcpu
	/**
	 * Field powerstate.
	 */
	private String powerstate = ""; // powerstate when not in use
	/**
	 * Field replicareadsteadystateIOPS.
	 */
	private int replicareadsteadystateIOPS; // number of iops on replica during
											// steady state
	/**
	 * Field replicaconcurrentbootcount.
	 */
	private int replicaconcurrentbootcount; // number of concurrent boot
											// operations
	/**
	 * Field bootIOPS.
	 */
	private int bootIOPS; // number of iops during boot per vm
	/**
	 * Field deltasteadystateIOPS.
	 */
	private int deltasteadystateIOPS; // number of iops on clone during steady
										// state
	/**
	 * Field deltareadIOpercent.
	 */
	private int deltareadIOpercent;
	/**
	 * Field persistentsteadystateIOPS.
	 */
	private int persistentsteadystateIOPS;
	/**
	 * Field persistentreadIOpercent.
	 */
	private int persistentreadIOpercent;
	/**
	 * Field idenditysize.
	 */
	private int idenditysize; // (Xen) A unique identity disk used to provide
								// each VM with a unique identity
	/**
	 * Field identitysnapshotsize.
	 */
	private int identitysnapshotsize; // (Xen) A unique identity disk used to
										// provide each VM with a unique
										// identity

	/**
	 * Calculate size of delta size
	 * 
	 * @param vmfullclonesize
	 * @param vmrefreshrate
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int calculateDeltaSize(int vmfullclonesize, int vmrefreshrate) {

		final int i = (vmfullclonesize * vmrefreshrate) / 100;

		return i;
	}

	/**
	 * Define total storage utilized by a single virtual desktop. The calculation is
	 * done by summing up multiple pre-calculated values. This method will return an
	 * integer with any point or value checks.
	 * 
	 * @param vmfullclonesize      full clone disk size
	 * @param vmdeltasize          linked clones delta disk size
	 * @param vmpersistentdisksize persistent disk size
	 * @param vmdisposabledisksize disposable disk size
	 * @param vmlogsize            log size
	 * @param vmswapsize           vswap size
	 * @param vmvideoswapsize      video vswap size
	 * @since 1.0
	 *
	 * @return int
	 */
	public int calculateStorageSize(int vmfullclonesize, int vmdeltasize, int vmpersistentdisksize,
			int vmdisposabledisksize, int vmlogsize, int vmswapsize, int vmvideoswapsize) {

		final int i = vmfullclonesize + vmdeltasize + vmpersistentdisksize + vmdisposabledisksize + vmlogsize
				+ vmswapsize + vmvideoswapsize;

		return i;
	}

	/**
	 * 
	 * @return the bootIOPS
	 */
	public Integer getBootIOPS() {
		return bootIOPS;
	}

	/**
	 * 
	 * @return the concurrentcount
	 */
	public Integer getConcurrentcount() {
		return concurrentcount;
	}

	/**
	 * 
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 
	 * @return the deltareadIOpercent
	 */
	public Integer getDeltareadIOpercent() {
		return deltareadIOpercent;
	}

	/**
	 * 
	 * @return the deltasize
	 */
	public Integer getDeltasize() {
		return deltasize;
	}

	/**
	 * 
	 * @return the deltasteadystateIOPS
	 */
	public Integer getDeltasteadystateIOPS() {
		return deltasteadystateIOPS;
	}

	/**
	 * 
	 * @return the displaycount
	 */
	public Integer getDisplaycount() {
		return displaycount;
	}

	/**
	 * 
	 * @return the displayresolution
	 */
	public String getDisplayresolution() {
		return displayresolution;
	}

	/**
	 * 
	 * @return the disposabledisksize
	 */
	public Integer getDisposabledisksize() {
		return disposabledisksize;
	}

	/**
	 * 
	 * @return the fullclonesize
	 */
	public Integer getFullclonesize() {
		return fullclonesize;
	}

	/**
	 * 
	 * @return the idenditysize
	 */
	public Integer getIdenditysize() {
		return idenditysize;
	}

	/**
	 * 
	 * @return the identitysnapshotsize
	 */
	public Integer getIdentitysnapshotsize() {
		return identitysnapshotsize;
	}

	/**
	 * 
	 * @return the logsize
	 */
	public Integer getLogsize() {
		return logsize;
	}

	/**
	 * Retrieve the default log size for virtual machines The value is recommended
	 * by VMware
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getLogSize() {
		return 100;
	}

	/**
	 * 
	 * @return the memoryoverhead
	 */
	public Integer getMemoryoverhead() {
		return memoryoverhead;
	}

	/**
	 * Calculate virtual machine memory overhead
	 * 
	 * @param vmvcpu
	 * @param vmmemory
	 * @param vmvram
	 * @param vmdisplaycount
	 * @param vmdisplayresolution
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getMemoryOverhead(int vmvcpu, int vmmemory, int vmvram, int vmdisplaycount, String vmdisplayresolution) {

		final int vmmemorytmp = (int) Math.round(vmmemory / 1024.0);
		double vmvcpuvmemoryoverhead = 0;
		double vmmvideooverhead = 0;
		int itmp = 0;

		// cpu
		switch (vmmemorytmp) {
		case 1:
			switch (vmvcpu) {
			case 1:
				vmvcpuvmemoryoverhead = 71.2;
				break;
			case 2:
				vmvcpuvmemoryoverhead = 107.62;
				break;
			default:
				vmvcpuvmemoryoverhead = 71.2;
				break;
			}
			break;
		case 2:
			switch (vmvcpu) {
			case 1:
				vmvcpuvmemoryoverhead = 90.23;
				break;
			case 2:
				vmvcpuvmemoryoverhead = 137.75;
				break;
			default:
				vmvcpuvmemoryoverhead = 90.23;
				break;
			}
			break;
		case 4:
			switch (vmvcpu) {
			case 1:
				vmvcpuvmemoryoverhead = 128.5;
				break;
			case 2:
				vmvcpuvmemoryoverhead = 197.99;
				break;
			default:
				vmvcpuvmemoryoverhead = 128.5;
				break;
			}
			break;
		default:
			switch (vmvcpu) {
			case 1:
				vmvcpuvmemoryoverhead = 71.2;
				break;
			case 2:
				vmvcpuvmemoryoverhead = 107.62;
				break;
			default:
				vmvcpuvmemoryoverhead = 71.2;
				break;
			}
			break;
		}

		// display
		if (0 == vmvram) {
			switch (vmdisplaycount) {
			case 1:
				if (vmdisplayresolution.equals("1280x800")) {
					vmmvideooverhead = 4.00;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					vmmvideooverhead = 8.00;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					vmmvideooverhead = 16.00;
				}
				break;
			case 2:
				if (vmdisplayresolution.equals("1280x800")) {
					vmmvideooverhead = 12.50;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					vmmvideooverhead = 25.40;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					vmmvideooverhead = 60.00;
				}
				break;
			case 3:
				if (vmdisplayresolution.equals("1280x800")) {
					vmmvideooverhead = 18.75;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					vmmvideooverhead = 38.00;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					vmmvideooverhead = 84.80;
				}
				break;
			case 4:
				if (vmdisplayresolution.equals("1280x800")) {
					vmmvideooverhead = 25.00;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					vmmvideooverhead = 50.60;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					vmmvideooverhead = 109.60;
				}
				break;
			default:
				if (vmdisplayresolution.equals("1280x800")) {
					vmmvideooverhead = 6.73;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					vmmvideooverhead = 8.79;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					vmmvideooverhead = 31.25;
				}
				break;
			}
		}

		itmp = (int) Math.round(vmvcpuvmemoryoverhead + vmvram + vmmvideooverhead);

		return itmp;
	}

	/**
	 * 
	 * @return the memoryreservation
	 */
	public Integer getMemoryreservation() {
		return memoryreservation;
	}

	/**
	 * 
	 * @return the memorysize
	 */
	public Integer getMemorysize() {
		return memorysize;
	}

	/**
	 * 
	 * @return the memorytotal
	 */
	public Integer getMemorytotal() {
		return memorytotal;
	}

	/**
	 * Calculate total virtual desktop memory
	 * 
	 * @param vmmemory
	 * @param vmvmemoryoverhead
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getMemoryTotal(int vmmemory, int vmvmemoryoverhead) {
		return vmmemory + vmvmemoryoverhead;
	}

	/**
	 * 
	 * @return the parentcount
	 */
	public Integer getParentcount() {
		return parentcount;
	}

	/**
	 * getParentsize
	 * 
	 * 
	 * 
	 * @return int
	 */
	public int getParentsize() {
		return parentsize;
	}

	/**
	 * 
	 * @return the persistentdisksize
	 */
	public Integer getPersistentdisksize() {
		return persistentdisksize;
	}

	/**
	 * 
	 * @return the persistentreadIOpercent
	 */
	public Integer getPersistentreadIOpercent() {
		return persistentreadIOpercent;
	}

	/**
	 * 
	 * @return the persistentsteadystateIOPS
	 */
	public Integer getPersistentsteadystateIOPS() {
		return persistentsteadystateIOPS;
	}

	/**
	 * 
	 * @return the powerstate
	 */
	public String getPowerstate() {
		return powerstate;
	}

	/**
	 * 
	 * @return the refreshrate
	 */
	public Integer getRefreshrate() {
		return refreshrate;
	}

	/**
	 * 
	 * @return the replicaconcurrentbootcount
	 */
	public Integer getReplicaconcurrentbootcount() {
		return replicaconcurrentbootcount;
	}

	/**
	 * 
	 * @return the replicareadsteadystateIOPS
	 */
	public Integer getReplicareadsteadystateIOPS() {
		return replicareadsteadystateIOPS;
	}

	/**
	 * 
	 * @return the snapshotcount
	 */
	public int getSnapshotcount() {
		return snapshotcount;
	}

	/**
	 * 
	 * @return the storagesize
	 */
	public Integer getStoragesize() {
		return storagesize;
	}

	/**
	 * 
	 * @return the storagesizetotal
	 */
	public Double getStoragesizetotal() {
		return storagesizetotal;
	}

	/**
	 * Calculate total storage for all virtual machines
	 * 
	 * @param vmstoragesize
	 * @param vmcounttotal
	 * 
	 * @since 1.0
	 * 
	 * @return Double
	 */
	public Double getStorageSizeTotal(int vmstoragesize, int vmcounttotal) {

		double i = (vmstoragesize * vmcounttotal) / 1048576.0;
		i = (double) Math.round(i * 100) / 100; // round to 2 decimal

		return i;
	}

	/**
	 * 
	 * @return the swapsize
	 */
	public Integer getSwapsize() {
		return swapsize;
	}

	/**
	 * Calculate virtual desktop vswap size based on memory reservation.
	 * 
	 * @param vmmemoryreservation the percent value of memory reservation to be
	 *                            applied
	 * @param vmmemory            virtual desktop base memory
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getSwapSize(int vmmemoryreservation, int vmmemory) {

		final double i = (1.0 - (vmmemoryreservation / 100.0)) * vmmemory;

		return (int) i;
	}

	/**
	 * 
	 * @return the thinsize
	 */
	public Integer getThinsize() {
		return thinsize;
	}

	/**
	 * 
	 * @return the vcpucount
	 */
	public Integer getVcpucount() {
		return vcpucount;
	}

	/**
	 * 
	 * @return the vcpumhz
	 */
	public Integer getVcpumhz() {
		return vcpumhz;
	}

	/**
	 * 
	 * @return the vcpuoverhead
	 */
	public Integer getVcpuoverhead() {
		return vcpuoverhead;
	}

	/**
	 * 
	 * @return the videoswapsize
	 */
	public Integer getVideoswapsize() {
		return videoswapsize;
	}

	/**
	 * Calculate the video vswap size for a virtual desktop. This method will
	 * execute calculations based on number of displays, or if 3D option is enabled
	 * it will calculate based on the amount of vRAM assigned. vSwap values are
	 * pre-assigned based on tests with vSphere 5.1.
	 * 
	 * @param vmdisplaynumber     number of displays
	 * @param vmdisplayresolution heightxWidth resolution
	 * @param vmvram              amount of vRam assigned
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getVideoSwapSize(int vmdisplaynumber, String vmdisplayresolution, int vmvram) {

		int i = 0;

		if (0 == vmvram) {
			// calculation based on displays
			switch (vmdisplaynumber) {
			case 1:
				if (vmdisplayresolution.equals("1280x800")) {
					i = 107;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					i = 111;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					i = 203;
				}
				break;
			case 2:
				if (vmdisplayresolution.equals("1280x800")) {
					i = 163;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					i = 190;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					i = 203;
				}
				break;
			case 3:
				if (vmdisplayresolution.equals("1280x800")) {
					i = 207;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					i = 248;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					i = 461;
				}
				break;
			case 4:
				if (vmdisplayresolution.equals("1280x800")) {
					i = 252;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					i = 306;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					i = 589;
				}
				break;
			default:
				if (vmdisplayresolution.equals("1280x800")) {
					i = 107;
				} else if (vmdisplayresolution.equals("1920x1200")) {
					i = 111;
				} else if (vmdisplayresolution.equals("2560x1600")) {
					i = 203;
				}
				break;
			}
		} else if (0 != vmvram) {
			// calculation based on vRAM(3d)
			switch (vmvram) {
			case 64:
				i = 1076;
				break;
			case 128:
				i = 1468;
				break;
			case 256:
				i = 1468;
				break;
			case 512:
				i = 1916;
				break;
			default:
				i = 1076;
				break;
			}
		}

		return i;
	}

	/**
	 * 
	 * @return the vram
	 */
	public Integer getVram() {
		return vram;
	}

	/**
	 * Retrieve the default identity disk size for Xen Desktop A unique identity
	 * disk used to provide each VM with a unique identity The value is recommended
	 * by Citrix
	 * 
	 * 
	 * @since 2.0
	 * 
	 * @return int
	 */
	public int getXenIdentitySize() {
		return 16;
	}

	/**
	 * Retrieve the default identity disk size for Xen Desktop A unique identity
	 * disk used to provide each VM with a unique identity The value is recommended
	 * by Citrix.
	 * 
	 * 
	 * @since 2.0
	 * 
	 * @return int
	 */
	public int getXenIdentitySnapshotSize() {
		return 24;
	}

	/**
	 * Calculate virtual machine storage size for xendesktop desktops
	 * 
	 * @param vmfullclonesize
	 * @param vmdeltasize
	 * @param vmidentitysize
	 * @param vmidentitysnapshotsize
	 * @param vmlogsize
	 * @param vmswapsize
	 * @param vmvideoswapsize
	 * 
	 * @since 2.0
	 * 
	 * @return int
	 */
	public int getXenStorageSize(int vmfullclonesize, int vmdeltasize, int vmidentitysize, int vmidentitysnapshotsize,
			int vmlogsize, int vmswapsize, int vmvideoswapsize) {

		final int i = vmfullclonesize + vmdeltasize + vmidentitysize + vmidentitysnapshotsize + vmlogsize + vmswapsize
				+ vmvideoswapsize;

		return i;
	}

	/**
	 * @param bootIOPS the bootIOPS to set
	 */
	public void setBootIOPS(Integer bootIOPS) {
		this.bootIOPS = bootIOPS;
	}

	/**
	 * @param concurrentcount the concurrentcount to set
	 */
	public void setConcurrentcount(Integer concurrentcount) {
		this.concurrentcount = concurrentcount;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @param deltareadIOpercent the deltareadIOpercent to set
	 */
	public void setDeltareadIOpercent(Integer deltareadIOpercent) {
		this.deltareadIOpercent = deltareadIOpercent;
	}

	/**
	 * @param deltasize the deltasize to set
	 */
	public void setDeltasize(Integer deltasize) {
		this.deltasize = deltasize;
	}

	/**
	 * @param deltasteadystateIOPS the deltasteadystateIOPS to set
	 */
	public void setDeltasteadystateIOPS(Integer deltasteadystateIOPS) {
		this.deltasteadystateIOPS = deltasteadystateIOPS;
	}

	/**
	 * @param displaycount the displaycount to set
	 */
	public void setDisplaycount(Integer displaycount) {
		this.displaycount = displaycount;
	}

	/**
	 * @param displayresolution the displayresolution to set
	 */
	public void setDisplayresolution(String displayresolution) {
		this.displayresolution = displayresolution;
	}

	/**
	 * @param disposabledisksize the disposabledisksize to set
	 */
	public void setDisposabledisksize(Integer disposabledisksize) {
		this.disposabledisksize = disposabledisksize;
	}

	/**
	 * @param fullclonesize the fullclonesize to set
	 */
	public void setFullclonesize(Integer fullclonesize) {
		this.fullclonesize = fullclonesize;
	}

	/**
	 * @param idenditysize the idenditysize to set
	 */
	public void setIdenditysize(Integer idenditysize) {
		this.idenditysize = idenditysize;
	}

	/**
	 * @param identitysnapshotsize the identitysnapshotsize to set
	 */
	public void setIdentitysnapshotsize(Integer identitysnapshotsize) {
		this.identitysnapshotsize = identitysnapshotsize;
	}

	/**
	 * @param logsize the logsize to set
	 */
	public void setLogsize(Integer logsize) {
		this.logsize = logsize;
	}

	/**
	 * @param memoryoverhead the memoryoverhead to set
	 */
	public void setMemoryoverhead(Integer memoryoverhead) {
		this.memoryoverhead = memoryoverhead;
	}

	/**
	 * @param memoryreservation the memoryreservation to set
	 */
	public void setMemoryreservation(Integer memoryreservation) {
		this.memoryreservation = memoryreservation;
	}

	/**
	 * @param memorysize the memorysize to set
	 */
	public void setMemorysize(Integer memorysize) {
		this.memorysize = memorysize;
	}

	/**
	 * @param memorytotal the memorytotal to set
	 */
	public void setMemorytotal(Integer memorytotal) {
		this.memorytotal = memorytotal;
	}

	/**
	 * @param parentcount the parentcount to set
	 */
	public void setParentcount(Integer parentcount) {
		this.parentcount = parentcount;
	}

	/**
	 * setParentsize
	 * 
	 * @param parentsize
	 */
	public void setParentsize(int parentsize) {
		this.parentsize = parentsize;
	}

	/**
	 * @param persistentdisksize the persistentdisksize to set
	 */
	public void setPersistentdisksize(Integer persistentdisksize) {
		this.persistentdisksize = persistentdisksize;
	}

	/**
	 * @param persistentreadIOpercent the persistentreadIOpercent to set
	 */
	public void setPersistentreadIOpercent(Integer persistentreadIOpercent) {
		this.persistentreadIOpercent = persistentreadIOpercent;
	}

	/**
	 * @param persistentsteadystateIOPS the persistentsteadystateIOPS to set
	 */
	public void setPersistentsteadystateIOPS(Integer persistentsteadystateIOPS) {
		this.persistentsteadystateIOPS = persistentsteadystateIOPS;
	}

	/**
	 * @param powerstate the powerstate to set
	 */
	public void setPowerstate(String powerstate) {
		this.powerstate = powerstate;
	}

	/**
	 * @param refreshrate the refreshrate to set
	 */
	public void setRefreshrate(Integer refreshrate) {
		this.refreshrate = refreshrate;
	}

	/**
	 * @param replicaconcurrentbootcount the replicaconcurrentbootcount to set
	 */
	public void setReplicaconcurrentbootcount(Integer replicaconcurrentbootcount) {
		this.replicaconcurrentbootcount = replicaconcurrentbootcount;
	}

	/**
	 * @param replicareadsteadystateIOPS the replicareadsteadystateIOPS to set
	 */
	public void setReplicareadsteadystateIOPS(Integer replicareadsteadystateIOPS) {
		this.replicareadsteadystateIOPS = replicareadsteadystateIOPS;
	}

	/**
	 * @param snapshotcount the snapshotcount to set
	 */
	public void setSnapshotcount(int snapshotcount) {
		this.snapshotcount = snapshotcount;
	}

	/**
	 * @param storagesize the storagesize to set
	 */
	public void setStoragesize(Integer storagesize) {
		this.storagesize = storagesize;
	}

	/**
	 * @param storagesizetotal the storagesizetotal to set
	 */
	public void setStoragesizetotal(Double storagesizetotal) {
		this.storagesizetotal = storagesizetotal;
	}

	/**
	 * @param swapsize the swapsize to set
	 */
	public void setSwapsize(Integer swapsize) {
		this.swapsize = swapsize;
	}

	/**
	 * @param thinsize the thinsize to set
	 */
	public void setThinsize(Integer thinsize) {
		this.thinsize = thinsize;
	}

	/**
	 * @param vcpucount the vcpucount to set
	 */
	public void setVcpucount(Integer vcpucount) {
		this.vcpucount = vcpucount;
	}

	/**
	 * @param vcpumhz the vcpumhz to set
	 */
	public void setVcpumhz(Integer vcpumhz) {
		this.vcpumhz = vcpumhz;
	}

	/**
	 * @param vcpuoverhead the vcpuoverhead to set
	 */
	public void setVcpuoverhead(Integer vcpuoverhead) {
		this.vcpuoverhead = vcpuoverhead;
	}

	/**
	 * @param videoswapsize the videoswapsize to set
	 */
	public void setVideoswapsize(Integer videoswapsize) {
		this.videoswapsize = videoswapsize;
	}

	/**
	 * @param vram the vram to set
	 */
	public void setVram(Integer vram) {
		this.vram = vram;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder(32);
		builder.append("Vm []");
		return builder.toString();
	}

}
