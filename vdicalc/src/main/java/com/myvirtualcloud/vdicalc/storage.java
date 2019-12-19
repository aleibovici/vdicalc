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
public class storage {
	/**
	 * Field usablecapacity.
	 */
	private static double Usablecapacity = 0.0;
	/**
	 * Field parentusablecapacity.
	 */
	private static double Parentusablecapacity = 0D;
	/**
	 * Field replicausablecapacity.
	 */
	private static double Replicausablecapacity = 0D;
	/**
	 * Field replicareadbackendIOPS.
	 */
	protected static Integer ReplicareadbackendIOPS;
	/**
	 * Field clonefrontendIOPS.
	 */
	protected static Integer ClonefrontendIOPS;
	/**
	 * Field clonebackendIOPS.
	 */
	public static Integer ClonebackendIOPS;
	/**
	 * Field persistentfrontendIOPS.
	 */
	protected static Integer PersistentfrontendIOPS;
	/**
	 * Field persistentbackendIOPS.
	 */
	protected static Integer PersistentbackendIOPS;
	/**
	 * Field persistentdatastorefrontendIOPS.
	 */
	protected static Integer PersistentdatastorefrontendIOPS = 0;
	/**
	 * Field persistentdatastorebackendIOPS.
	 */
	protected static Integer PersistentdatastorebackendIOPS = 0;
	/**
	 * Field frontendIOPS.
	 */
	// static Integer FrontendIOPS;
	/**
	 * Field backendIOPS.
	 */
	protected Integer backendIOPS;
	/**
	 * Field replicareadfrontendIOPS.
	 */
	protected Integer replicareadfrontendIOPS;
	/**
	 * Field cloneusablecapacity.
	 */
	protected Double cloneusablecapacity = 0D;
	/**
	 * Field clonedatastorecount.
	 */
	private int clonedatastorecount;
	/**
	 * Field clonedatastoresize.
	 */
	private int clonedatastoresize;
	/**
	 * Field clonedatastorefrontendIOPS.
	 */
	private int clonedatastorefrontendIOPS;
	/**
	 * Field clonedatastorebackendIOPS.
	 */
	private int clonedatastorebackendIOPS;
	/**
	 * Field clonereadfrontendIOPS.
	 */
	protected Integer clonereadfrontendIOPS;
	/**
	 * Field clonewritefrontendIOPS.
	 */
	protected Integer clonewritefrontendIOPS;
	/**
	 * Field clonereadbackendIOPS.
	 */
	protected Integer clonereadbackendIOPS;
	/**
	 * Field clonewritebackendIOPS.
	 */
	protected Integer clonewritebackendIOPS;
	/**
	 * Field cloneraidtype.
	 */
	protected String cloneraidtype = "";
	/**
	 * Field persistentusablecapacity.
	 */
	protected double persistentusablecapacity = 0D;
	// Number of persistent disks per datastore
	/**
	 * Field persistentdatastorevmcount.
	 */
	protected Integer persistentdatastorevmcount;
	// Number of datastores for persistent disks
	/**
	 * Field persistentdatastorecount.
	 */
	protected int persistentdatastorecount = 0;
	// Size of datastores for persistent disks
	/**
	 * Field persistentdatastoresize.
	 */
	protected int persistentdatastoresize = 0;
	/**
	 * Field persistentreadfrontendIOPS.
	 */
	protected Integer persistentreadfrontendIOPS;
	/**
	 * Field persistentreadbackendIOPS.
	 */
	protected Integer persistentreadbackendIOPS;
	/**
	 * Field persistentwritefrontendIOPS.
	 */
	protected Integer persistentwritefrontendIOPS;
	/**
	 * Field persistentwritebackendIOPS.
	 */
	protected Integer persistentwritebackendIOPS;
	/**
	 * Field persistentraidtype.
	 */
	protected String persistentraidtype = "";
	// Total disposable disk capacity
	/**
	 * Field disposableusablecapacity.
	 */
	protected Double disposableusablecapacity = 0D;
	// Total host local swap
	/**
	 * Field localswapsizetotal.
	 */
	private double localswapsizetotal = 0D;
	// Total swap reduction due to concurrency and power settings
	/**
	 * Field swappowerstatecapacityreduction.
	 */
	private double swappowerstatecapacityreduction = 0D;
	// Capacity used for suspend mode
	/**
	 * Field suspendusablecapacity.
	 */
	private double suspendusablecapacity = 0D;
	// Capacity overhead
	/**
	 * Field overhead.
	 */
	private int overhead;
	// De-duplication ratio
	/**
	 * Field blockdeduplicationratio.
	 */
	private int blockdeduplicationratio;
	// Read caching ratio
	/**
	 * Field cachereadratio.
	 */
	private int cachereadratio;
	// Write caching ratio
	/**
	 * Field cachewriteratio.
	 */
	private int cachewriteratio;
	// Number of datastores
	/**
	 * Field datastorecount.
	 */
	protected int datastorecount;
	// Size of each datastore for full or linked clones
	/**
	 * Field datastoresize.
	 */
	protected int datastoresize;
	// Number of VMs per datastore
	/**
	 * Field datastorevmcount.
	 */
	private Integer datastorevmcount;

	/**
	 * Calculate clone size subset for all VM in the solution. Includes all
	 * components of VM, but replica.
	 * 
	 * @param vmfullclonesize
	 * @param vmdeltasize
	 * @param vmdisposabledisksize
	 * @param vmlogsize
	 * @param vmswapsize
	 * @param vmvideoswapsize
	 * @param vmcount
	 * @param storageoverhead
	 * @param blockdeduplicationratio
	 * @param clonetype
	 * @since 1.0
	 * @return double
	 */
	public double calculateCloneUsableCapacity(int vmfullclonesize, int vmdeltasize, int vmdisposabledisksize,
			int vmlogsize, int vmswapsize, int vmvideoswapsize, int vmcount, int storageoverhead,
			int blockdeduplicationratio, String clonetype) {

		Double d = (double) ((vmfullclonesize + vmdeltasize + vmdisposabledisksize + vmlogsize + vmswapsize
				+ vmvideoswapsize) * vmcount) / 1048576;
		d += disposableusablecapacity; // deduct disposable usable
										// capacityfor powered off VMs
		d += localswapsizetotal; // deduct local VM vswap
		d *= (1.0 + (storageoverhead / 100.0)); // overhead

		/*
		 * Apply de-duplication only for clone type full (view) or assigned (xen)
		 */
		if (clonetype.equalsIgnoreCase("full") || clonetype.equalsIgnoreCase("assigned")) {
			d *= (1.0 - (blockdeduplicationratio / 100.0));
		}

		d = (double) Math.round(d * 100) / 100; // round to 2 decimal

		return d;
	}

	/**
	 * Calculate total amount of virtual machine vswap saved in shared storage when
	 * local vswap option is enabled. This option enabled virtual machine vswap
	 * placement on host local storage. Return value in terabytes.
	 * 
	 * @param hostcount
	 * @param hostlocalswapsize
	 * @since 1.0
	 * 
	 * 
	 * @return double * @see hostobj.localswapsize
	 **/
	public double calculateLocalSwapTotalCapacity(int hostcount, int hostlocalswapsize) {
		double d = (double) (hostcount * hostlocalswapsize) / 1024;
		d *= -1;
		d = (double) Math.round(d * 100) / 100; // round to 2 decimal
		return d;
	}

	/**
	 * 
	 * @param vmfullsizeparent
	 * @param vmparentcount
	 * @param storageoverhead
	 * 
	 * 
	 * @return double
	 */
	public double calculateParentUsableCapacity(int vmfullsizeparent, int vmparentcount, int storageoverhead) {
		double d = (vmfullsizeparent * vmparentcount) / 1048576.0;
		d *= (1.0 + (storageoverhead / 100.0));
		d = (double) Math.round(d * 100) / 100; // round to 2 decimal
		return d;
	}

	/**
	 * Calculate amount of storage capacity to be used by replicas
	 * 
	 * @param vmthinsizeparentvm thin size of the parent VM
	 * @param poolsnapshotcount  number of snapshots per pool
	 * @param desktoppoolcount   number of desktop pools
	 * @param datastorecount     datastore number
	 * @param storageoverhead    storage overhead in percent
	 * @param storagetiering     Dedicated replica datastore. "On" or "Off" as
	 *                           String value.
	 * 
	 * @since 1.0
	 * 
	 * 
	 * @return double * @see hostobj.localswapsize
	 */
	public double calculateReplicaUsableCapacity(int vmthinsizeparentvm, int poolsnapshotcount, int desktoppoolcount,
			int datastorecount, int storageoverhead, String storagetiering) {

		boolean b = false;
		if (storagetiering.equalsIgnoreCase("On")) {
			b = true;
		} else {
			b = false;
		}
		double d = 0;
		if (b) {
			d = (vmthinsizeparentvm * desktoppoolcount * poolsnapshotcount) / 1048576.0;
		} else {
			d = (vmthinsizeparentvm * poolsnapshotcount * desktoppoolcount * datastorecount) / 1048576.0;
		}

		d *= (1.0 + (storageoverhead / 100.0));
		d = (double) Math.round(d * 100) / 100;

		return d;
	}

	/**
	 * 
	 * @param vmcount
	 * @param vmmemory
	 * 
	 * @since 1.0
	 * 
	 * @return double
	 */
	public double calculateSuspendUsableCapacity(int vmcount, int vmmemory) {
		double d = vmcount * vmmemory / 1048576.0;
		// round to 2 decimal
		d = (double) Math.round(d * 100) / 100;
		return d;
	}

	/**
	 * 
	 * @param vmcount
	 * @param vmconcurrentcount
	 * @param vmswapsize
	 * 
	 * 
	 * 
	 * @return double
	 */
	public double calculateSwapPowerstateCapacityReduction(int vmcount, int vmconcurrentcount, int vmswapsize) {
		double d = (vmcount - vmconcurrentcount) * vmswapsize / 1048576.0;
		d = (double) Math.round(d * 100) / 100; // round to 2 decimal
		d *= -1;
		return d;
	}

	/**
	 * Sum up total shared storage capacity required for solution All input values
	 * must be in Terabytes. Return value in Terabytes.
	 * 
	 * @param storageparentusablecapacity
	 * @param storagereplicausablecapacity
	 * @param storagecloneusablecapacity
	 * @param storagepersistentusablecapacity
	 * 
	 * @since 1.0
	 * 
	 * @param storagesuspendusablecapacity    double
	 * @param swappowerstatecapacityreduction double
	 * 
	 * @return double * @see localswapsizetotal
	 */
	public double calculateUsableCapacity(double storageparentusablecapacity, double storagereplicausablecapacity,
			double storagecloneusablecapacity, double storagepersistentusablecapacity,
			double storagesuspendusablecapacity, double swappowerstatecapacityreduction) {

		double d = (storageparentusablecapacity + storagereplicausablecapacity + storagecloneusablecapacity
				+ storagepersistentusablecapacity + storagesuspendusablecapacity + swappowerstatecapacityreduction);

		// Round to 1 decimals
		d = (double) Math.round(d * 100) / 100;

		return d;

	}

	/**
	 * 
	 * 
	 * @return int
	 */
	public int getBackendIOPS() {

		return replicareadfrontendIOPS + clonereadbackendIOPS + clonewritebackendIOPS + persistentreadbackendIOPS
				+ persistentwritebackendIOPS;
	}

	/**
	 * 
	 * @return the blockdeduplicationratio
	 */
	public Integer getBlockdeduplicationratio() {
		return blockdeduplicationratio;
	}

	/**
	 * 
	 * @return the cachereadratio
	 */
	public Integer getCachereadratio() {
		return cachereadratio;
	}

	/**
	 * 
	 * @return the cachewriteratio
	 */
	public Integer getCachewriteratio() {
		return cachewriteratio;
	}

	/**
	 * 
	 * @return Integer
	 */
	public Integer getCloneBackendIOPS() {

		storage.ClonebackendIOPS = clonereadbackendIOPS + clonewritebackendIOPS;

		return storage.ClonebackendIOPS;
	}

	/**
	 * 
	 * @return the clonedatastorebackendIOPS
	 */
	public Integer getClonedatastorebackendIOPS() {
		return clonedatastorebackendIOPS;
	}

	/**
	 * 
	 * @return the clonedatastorecount
	 */
	public Integer getClonedatastorecount() {
		return clonedatastorecount;
	}

	/**
	 * 
	 * @return the clonedatastorefrontendIOPS
	 */
	public Integer getClonedatastorefrontendIOPS() {
		return clonedatastorefrontendIOPS;
	}

	/**
	 * 
	 * @return the clonedatastoresize
	 */
	public Integer getClonedatastoresize() {
		return clonedatastoresize;
	}

	/**
	 * Calculate clone storage frontend IOPs summing up read and write IOPS.
	 * 
	 * @since 1.0
	 * 
	 * 
	 * 
	 * @return Integer * @see clonereadfrontendIOPS * @see clonewritefrontendIOPS
	 **/
	public Integer getCloneFrontendIOPS() {

		storage.ClonefrontendIOPS = clonereadfrontendIOPS + clonewritefrontendIOPS;

		return storage.ClonefrontendIOPS;
	}

	/**
	 * 
	 * @param storageclonereadfrontendIOPS
	 * @param readcachepercent
	 * 
	 * 
	 * @return int
	 */
	public int getCloneReadBackendIOPS(int storageclonereadfrontendIOPS, int readcachepercent) {
		final double d = storageclonereadfrontendIOPS * (1 - (readcachepercent / 100.0));
		return (int) d;
	}

	/**
	 * 
	 * @param vmconcurrentcount
	 * @param vmdeltasteadystateIOPS
	 * @param vmdeltareadIOPSpercentage
	 * 
	 * 
	 * @return int
	 */
	public int getCloneReadFrontendIOPS(int vmconcurrentcount, int vmdeltasteadystateIOPS,
			int vmdeltareadIOPSpercentage) {
		double d = vmconcurrentcount * vmdeltasteadystateIOPS;
		d *= (vmdeltareadIOPSpercentage / 100.0); // read IOPS to %
		return (int) d;
	}

	/**
	 * 
	 * @param storageclonewritefrontendIOPS
	 * @param storageraidtype
	 * @param storagewritecachepercent
	 * 
	 * 
	 * @return int
	 */
	public int getCloneWriteBackendIOPS(int storageclonewritefrontendIOPS, String storageraidtype,
			int storagewritecachepercent) {
		double d = storageclonewritefrontendIOPS;
		// calculate raid penalty for writes
		if (storageraidtype.equals("0")) {
			d *= 1;
		} else if (storageraidtype.equals("5")) {
			d *= 4;
		} else if (storageraidtype.equals("6")) {
			d *= 6;
		} else if (storageraidtype.equals("10")) {
			d *= 2;
		} else if (storageraidtype.equals("DP")) {
			d *= 2;
		}
		d *= (1 - (storagewritecachepercent / 100.0)); // apply write cache
		return (int) d;
	}

	/**
	 * 
	 * @param vmconcurrentcount
	 * @param vmdeltasteadystateIOPS
	 * @param vmdeltareadIOPSpercentage
	 * 
	 * 
	 * @return int
	 */
	public int getCloneWriteFrontendIOPS(int vmconcurrentcount, int vmdeltasteadystateIOPS,
			int vmdeltareadIOPSpercentage) {
		double d = vmconcurrentcount * vmdeltasteadystateIOPS;
		d *= (1 - (vmdeltareadIOPSpercentage / 100.0)); // write IOPS to %
		return (int) d;
	}

	/**
	 * Calculate datastore number
	 * 
	 * @param vmcounttotal
	 * @param storagedatastorevm
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getDatastoreCount(int vmcounttotal, int storagedatastorevm) {
		float f = ((float) vmcounttotal / (float) storagedatastorevm);

		// round up to next Integer
		f = (float) Math.ceil(f);

		return (int) f;
	}

	/**
	 * Calculate datastore size
	 * 
	 * @param datastorecount
	 * @param cloneusablecapacity
	 * @param replicausablecapacity
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getDatastoreSize(Integer datastorecount, Double cloneusablecapacity, Double replicausablecapacity) {
		double d = ((((cloneusablecapacity + replicausablecapacity) * 1048576.0) / datastorecount));
		d = Math.ceil(d); // round up to next Integer
		final int i = (int) d / 1024; // convert to Integer and convert to GB
		return i;
	}

	/**
	 * 
	 * @return the datastorevmcount
	 */
	public Integer getDatastorevmcount() {
		return datastorevmcount;
	}

	/**
	 * 
	 * @param vmcount
	 * @param vmconcurrentcount
	 * @param vmdisposabledisksize
	 * 
	 * 
	 * @return double
	 */
	public double getDisposableUsableCapacity(int vmcount, int vmconcurrentcount, int vmdisposabledisksize) {
		double d = (vmcount - vmconcurrentcount) * vmdisposabledisksize / 1048576.0;
		d = (double) Math.round(d * 100) / 100; // round to 2 decimal
		d *= -1;
		return d;
	}

	/**
	 * 
	 * 
	 * @return int
	 */
	public int getFrontendIOPS() {

		return replicareadfrontendIOPS + clonereadfrontendIOPS + clonewritefrontendIOPS + persistentreadfrontendIOPS
				+ persistentwritefrontendIOPS;
	}

	/**
	 * 
	 * @return the localswapsizetotal
	 */
	public double getLocalswapsizetotal() {
		return localswapsizetotal;
	}

	/**
	 * 
	 * @return the overhead
	 */
	public Integer getOverhead() {
		return overhead;
	}

	/**
	 * Method getParentusablecapacity.
	 * 
	 * @return double
	 */
	public double getParentusablecapacity() {
		return Parentusablecapacity;
	}

	/**
	 * 
	 * 
	 * 
	 * @return Integer
	 */
	public Integer getPersistentBackendIOPS() {

		storage.PersistentbackendIOPS = persistentreadbackendIOPS + persistentwritebackendIOPS;

		return storage.PersistentbackendIOPS;
	}

	/**
	 * Calculate required number of datastores for persistent disks
	 * 
	 * @param vmcounttotal
	 * @param storagepersistentdatastorevm
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getPersistentDatastoreCount(int vmcounttotal, int storagepersistentdatastorevm) {
		float f = ((float) vmcounttotal / (float) storagepersistentdatastorevm);
		f = (float) Math.ceil(f); // round up to next Integer
		return (int) f;
	}

	/**
	 * Calculate persistent disk datastore size
	 * 
	 * @param persistentDatastoreCount
	 * @param persistentusablecapacity
	 * 
	 * @since 1.0
	 * 
	 * @return int
	 */
	public int getPersistentDatastoreSize(Integer persistentDatastoreCount, Double persistentusablecapacity) {
		double d = (((persistentusablecapacity * 1048576.0) / persistentDatastoreCount));
		d = Math.ceil(d); // round up to next Integer
		return (int) d;
	}

	/**
	 * 
	 * 
	 * 
	 * @return Integer
	 */
	public Integer getPersistentFrondendIOPS() {

		storage.PersistentfrontendIOPS = persistentreadfrontendIOPS + persistentwritefrontendIOPS;

		return storage.PersistentfrontendIOPS;
	}

	/**
	 * 
	 * @param storagepersistentreadfrontendIOPS
	 * @param readcachepercent
	 * 
	 * 
	 * @return int
	 */
	public int getPersistentReadBackendIOPS(int storagepersistentreadfrontendIOPS, int readcachepercent) {
		final double d = storagepersistentreadfrontendIOPS * (1 - (readcachepercent / 100.0));
		return (int) d;
	}

	/**
	 * Method GetPersistentReadFrontendIOPS.
	 * 
	 * @param vmconcurrentcount              int
	 * @param vmpersistentsteadystateIOPS    int
	 * @param vmpersistentreadIOPSpercentage int
	 * 
	 * @return int
	 */
	public int getPersistentReadFrontendIOPS(int vmconcurrentcount, int vmpersistentsteadystateIOPS,
			int vmpersistentreadIOPSpercentage) {
		double d = vmconcurrentcount * vmpersistentsteadystateIOPS;
		d *= (vmpersistentreadIOPSpercentage / 100.0); // read IOPS to %
		return (int) d;
	}

	/**
	 * 
	 * @return the persistentusablecapacity
	 */
	public Double getPersistentusablecapacity() {
		return persistentusablecapacity;
	}

	/**
	 * 
	 * @param vmpersistentdisksize
	 * @param vmcount
	 * @param storageoverhead
	 * @param blockdeduplicationratio
	 * 
	 * @since 1.0
	 * 
	 * @return double
	 */
	public double getPersistentUsableCapacity(int vmpersistentdisksize, int vmcount, int storageoverhead,
			int blockdeduplicationratio) {

		double d = (vmpersistentdisksize * vmcount) / 1048576.0;
		d *= (1.0 + (storageoverhead / 100.0));

		// apply storage deduplication ratio
		d *= (1.0 - (blockdeduplicationratio / 100.0));

		// round up to next Integer
		d = Math.round(d * 100.0) / 100.0;

		return d;
	}

	/**
	 * 
	 * @param persistentwritefrontendIOPS
	 * @param storageraidtype
	 * @param storagewritecachepercent
	 * 
	 * 
	 * @return int
	 */
	public int getPersistentWriteBackendIOPS(int persistentwritefrontendIOPS, String storageraidtype,
			int storagewritecachepercent) {
		double d = persistentwritefrontendIOPS;

		// calculate raid penalty for writes
		if (storageraidtype.equals("0")) {
			d *= 1;
		} else if (storageraidtype.equals("5")) {
			d *= 4;
		} else if (storageraidtype.equals("6")) {
			d *= 6;
		} else if (storageraidtype.equals("10")) {
			d *= 2;
		} else if (storageraidtype.equals("DP")) {
			d *= 2;
		}

		// apply write cache
		d *= (1 - (storagewritecachepercent / 100.0));
		return (int) d;
	}

	/**
	 * 
	 * @param vmconcurrentcount
	 * @param vmpersistentsteadystateIOPS
	 * @param vmpersistentreadIOPSpercentage
	 * 
	 * 
	 * @return int
	 */
	public int getPersistentWriteFrontendIOPS(int vmconcurrentcount, int vmpersistentsteadystateIOPS,
			int vmpersistentreadIOPSpercentage) {
		double d = vmconcurrentcount * vmpersistentsteadystateIOPS;
		d *= (1 - (vmpersistentreadIOPSpercentage / 100.0)); // write IOPS to
																// %
		return (int) d;
	}

	/**
	 * 
	 * 
	 * @return Integer
	 */
	public Integer getReplicaBackEndIOPS() {
		return replicareadfrontendIOPS;
	}

	/**
	 * 
	 * @param vmconcurrentcount
	 * @param vmreplicasteadystateIOPS
	 * @param vmconcurrentboocount
	 * @param vmbootIOPS
	 * @param hostcbrcmemory
	 * @param hostcbrcreductior
	 * 
	 * 
	 * @return int
	 */
	public int getReplicaReadFrontendIOPS(int vmconcurrentcount, int vmreplicasteadystateIOPS, int vmconcurrentboocount,
			int vmbootIOPS, int hostcbrcmemory, double hostcbrcreductior) {
		final double d0 = vmconcurrentboocount * vmbootIOPS;
		final double d1 = (vmconcurrentcount - vmconcurrentboocount) * vmreplicasteadystateIOPS;
		double d = 0;
		if (d0 > d1) {
			d = d0;
		} else {
			d = d1;
		}
		if (0 != hostcbrcmemory) {
			d *= (1 - hostcbrcreductior);
		}
		return (int) d;
	}

	/**
	 * Method getReplicausablecapacity.
	 * 
	 * @return double
	 */
	public double getReplicausablecapacity() {
		return Replicausablecapacity;
	}

	/**
	 * 
	 * @return the suspendusablecapacity
	 */
	public double getSuspendusablecapacity() {
		return suspendusablecapacity;
	}

	/**
	 * 
	 * @return the swappowerstatecapacityreduction
	 */
	public double getSwappowerstatecapacityreduction() {
		return swappowerstatecapacityreduction;
	}

	/**
	 * 
	 * @return the usablecapacity
	 */
	public double getUsablecapacity() {
		return Usablecapacity;
	}

	/**
	 * @param blockdeduplicationratio the blockdeduplicationratio to set
	 */
	public void setBlockdeduplicationratio(Integer blockdeduplicationratio) {
		this.blockdeduplicationratio = blockdeduplicationratio;
	}

	/**
	 * @param cachereadratio the cachereadratio to set
	 */
	public void setCachereadratio(Integer cachereadratio) {
		this.cachereadratio = cachereadratio;
	}

	/**
	 * @param cachewriteratio the cachewriteratio to set
	 */
	public void setCachewriteratio(Integer cachewriteratio) {
		this.cachewriteratio = cachewriteratio;
	}

	/**
	 * @param clonedatastorebackendIOPS the clonedatastorebackendIOPS to set
	 */
	public void setClonedatastorebackendIOPS(Integer clonedatastorebackendIOPS) {
		this.clonedatastorebackendIOPS = clonedatastorebackendIOPS;
	}

	/**
	 * @param clonedatastorecount the clonedatastorecount to set
	 */
	public void setClonedatastorecount(Integer clonedatastorecount) {
		this.clonedatastorecount = clonedatastorecount;
	}

	/**
	 * @param clonedatastorefrontendIOPS the clonedatastorefrontendIOPS to set
	 */
	public void setClonedatastorefrontendIOPS(Integer clonedatastorefrontendIOPS) {
		this.clonedatastorefrontendIOPS = clonedatastorefrontendIOPS;
	}

	/**
	 * @param clonedatastoresize the clonedatastoresize to set
	 */
	public void setClonedatastoresize(Integer clonedatastoresize) {
		this.clonedatastoresize = clonedatastoresize;
	}

	/**
	 * @param datastorevmcount the datastorevmcount to set
	 */
	public void setDatastorevmcount(Integer datastorevmcount) {
		this.datastorevmcount = datastorevmcount;
	}

	/**
	 * @param localswapsizetotal the localswapsizetotal to set
	 */
	public void setLocalswapsizetotal(double localswapsizetotal) {
		this.localswapsizetotal = localswapsizetotal;
	}

	/**
	 * @param overhead the overhead to set
	 */
	public void setOverhead(Integer overhead) {
		this.overhead = overhead;
	}

	/**
	 * Method setParentusablecapacity.
	 * 
	 * @param parentusablecapacity double
	 */
	public void setParentusablecapacity(double parentusablecapacity) {
		storage.Parentusablecapacity = parentusablecapacity;
	}

	/**
	 * @param persistentusablecapacity the persistentusablecapacity to set
	 */
	public void setPersistentusablecapacity(Double persistentusablecapacity) {
		this.persistentusablecapacity = persistentusablecapacity;
	}

	/**
	 * Method setReplicausablecapacity.
	 * 
	 * @param replicausablecapacity double
	 */
	protected void setReplicausablecapacity(double replicausablecapacity) {
		storage.Replicausablecapacity = replicausablecapacity;
	}

	/**
	 * @param suspendusablecapacity the suspendusablecapacity to set
	 */
	public void setSuspendusablecapacity(double suspendusablecapacity) {
		this.suspendusablecapacity = suspendusablecapacity;
	}

	/**
	 * @param swappowerstatecapacityreduction the swappowerstatecapacityreduction to
	 *                                        set
	 */
	public void setSwappowerstatecapacityreduction(double swappowerstatecapacityreduction) {
		this.swappowerstatecapacityreduction = swappowerstatecapacityreduction;
	}

	/**
	 * @param usablecapacity the usablecapacity to set
	 */
	public void setUsablecapacity(double usablecapacity) {
		storage.Usablecapacity = usablecapacity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return String.format(
				"Storage [getFrontendIOPS()=%s, getBackendIOPS()=%s, getReplicaBackEndIOPS()=%s, getCloneFrontendIOPS()=%s, getCloneBackendIOPS()=%s, getPersistentFrondendIOPS()=%s, getPersistentBackendIOPS()=%s, getClonedatastorecount()=%s, getClonedatastoresize()=%s, getClonedatastorefrontendIOPS()=%s, getClonedatastorebackendIOPS()=%s, getPersistentusablecapacity()=%s, getOverhead()=%s, getBlockdeduplicationratio()=%s, getDatastorevmcount()=%s, getCachereadratio()=%s, getCachewriteratio()=%s, getLocalswapsizetotal()=%s, getUsablecapacity()=%s, getSuspendusablecapacity()=%s, getSwappowerstatecapacityreduction()=%s, getParentusablecapacity()=%s, getReplicausablecapacity()=%s]",
				getFrontendIOPS(), getBackendIOPS(), getReplicaBackEndIOPS(), getCloneFrontendIOPS(),
				getCloneBackendIOPS(), getPersistentFrondendIOPS(), getPersistentBackendIOPS(),
				getClonedatastorecount(), getClonedatastoresize(), getClonedatastorefrontendIOPS(),
				getClonedatastorebackendIOPS(), getPersistentusablecapacity(), getOverhead(),
				getBlockdeduplicationratio(), getDatastorevmcount(), getCachereadratio(), getCachewriteratio(),
				getLocalswapsizetotal(), getUsablecapacity(), getSuspendusablecapacity(),
				getSwappowerstatecapacityreduction(), getParentusablecapacity(), getReplicausablecapacity());
	}

}
