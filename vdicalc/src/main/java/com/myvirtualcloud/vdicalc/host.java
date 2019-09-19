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
 * 
 * @author andreleibovici
 *
 * @version $Revision: 1.0 $
 */
public class host {
	/**
	 * Field socketcount.
	 */
	private static int Socketcount;
	/**
	 * Field socketcores.
	 */
	private static int Socketcores;
	/**
	 * Field corescount.
	 */
	private static int Corescount;
	/**
	 * Field vmcore.
	 */
	private static int Vmcore;
	/**
	 * Field sharedmemory.
	 */
	private static int Sharedmemory;
	/**
	 * Field usedmemory.
	 */
	private static int Usedmemory;
	/**
	 * Field memoryoverhead.
	 */
	private static int Memoryoverhead;
	/**
	 * Field cbrcmemory.
	 */
	private static int Cbrcmemory;
	/**
	 * Field vmcount.
	 */
	private static int Vmcount;
	/**
	 * Field count.
	 */
	private static int Count;
	/**
	 * Field coremhz.
	 */
	private static int Coremhz;
	/**
	 * Field memoryTPS.
	 */
	private static int MemoryTPS;
	/**
	 * Field memoryNoTPS.
	 */
	private static int MemoryNoTPS;
	/**
	 * Field localswapsize.
	 */
	private static int Localswapsize = 0;
	/**
	 * Field CBRCREDUCTOR. (value is 0.65)
	 */
	private static final double CBRCREDUCTOR = 0.65; // default CBRC reduction
	/**
	 * Field thirdpartycpumhz.
	 */
	private static int Thirdpartycpumhz;
	/**
	 * Field thirdpartymemory.
	 */
	private static int Thirdpartymemory;
	/**
	 * Field thirdpartylocalstorage.
	 */
	private static int Thirdpartylocalstorage;

	/**
	 * 
	 * @return the cbrcreductor
	 */
	public static double getCbrcreductor() {
		return CBRCREDUCTOR;
	}

	/**
	 * 
	 * @return the coremhz
	 */
	public static int getCoremhz() {
		return Coremhz;
	}

	/**
	 * Calculate CPU clock rate per core
	 * 
	 * @param vmvcpu
	 * @param vmvcpumhz
	 * @param vmsperhost
	 * @param hostcores
	 * @param vmvcpumhzoverhead
	 * @since 1.0
	 * @return int
	 */
	public static int getCoreMhz(int vmvcpu, int vmvcpumhz, int vmsperhost, int hostcores, int vmvcpumhzoverhead) {
		Float f = (float) 0;

		Integer i = (((vmvcpu * vmvcpumhz) * vmsperhost) / hostcores);
		i += (host.getThirdpartycpumhz() / hostcores); // Calculate 3rd party CPU MHz accelerator
		f = (float) ((vmvcpumhzoverhead / 100.0) + 1); // Calculate CPU overhead
		i = (int) (i * f); // Apply CPU overhead

		return i;
	}

	/**
	 * 
	 * @return the corescount
	 */
	public static int getCorescount() {
		return Corescount;
	}

	/**
	 * 
	 * @return the count
	 */
	public static int getCount() {
		return Count;
	}

	/**
	 * 
	 * @return the localswapsize
	 */
	public static int getLocalswapsize() {
		return Localswapsize;
	}

	/**
	 * 
	 * @return the memoryNoTPS
	 */
	public static int getMemoryNoTPS() {
		return MemoryNoTPS;
	}

	/**
	 * 
	 * @return the memoryTPS
	 */
	public static int getMemoryTPS() {
		return MemoryTPS;
	}

	/**
	 * 
	 * @return the thirdpartycpumhz
	 */
	public static int getThirdpartycpumhz() {
		return Thirdpartycpumhz;
	}

	/**
	 * 
	 * @return the thirdpartylocalstorage
	 */
	public static int getThirdpartylocalstorage() {
		return Thirdpartylocalstorage;
	}

	/**
	 * 
	 * @return the thirdpartymemory
	 */
	public static int getThirdpartymemory() {
		return Thirdpartymemory;
	}

	/**
	 * 
	 * @return the vmcount
	 */
	public static int getVmcount() {
		return Vmcount;
	}

	/**
	 * @param coremhz the coremhz to set
	 */
	public static void setCoremhz(int coremhz) {
		host.Coremhz = coremhz;
	}

	/**
	 * @param corescount the corescount to set
	 */
	public static void setCorescount(int corescount) {
		host.Corescount = corescount;
	}

	/**
	 * @param count the count to set
	 */
	public static void setCount(int count) {
		host.Count = count;
	}

	/**
	 * @param localswapsize the localswapsize to set
	 */
	public static void setLocalswapsize(int localswapsize) {
		host.Localswapsize = localswapsize;
	}

	/**
	 * @param memoryNoTPS the memoryNoTPS to set
	 */
	public static void setMemoryNoTPS(int memoryNoTPS) {
		host.MemoryNoTPS = memoryNoTPS;
	}

	/**
	 * @param memoryTPS the memoryTPS to set
	 */
	public static void setMemoryTPS(int memoryTPS) {
		host.MemoryTPS = memoryTPS;
	}

	/**
	 * @param thirdpartycpumhz the thirdpartycpumhz to set
	 */
	public static void setThirdpartycpumhz(Integer thirdpartycpumhz) {
		host.Thirdpartycpumhz = thirdpartycpumhz;
	}

	/**
	 * @param thirdpartylocalstorage the thirdpartylocalstorage to set
	 */
	public static void setThirdpartylocalstorage(Integer thirdpartylocalstorage) {
		host.Thirdpartylocalstorage = thirdpartylocalstorage;
	}

	/**
	 * @param thirdpartymemory the thirdpartymemory to set
	 */
	public static void setThirdpartymemory(Integer thirdpartymemory) {
		host.Thirdpartymemory = thirdpartymemory;
	}

	/**
	 * @param vmcount the vmcount to set
	 */
	public static void setVmcount(Integer vmcount) {
		host.Vmcount = vmcount;
	}

	/**
	 * Calculate host number
	 * 
	 * @param desktopstate
	 * @param concurrentvms
	 * @param vmsperhost
	 * @param vcenterhamultiplier
	 * @since 1.0
	 * @return int
	 */
	public int calculateCount(String desktopstate, int concurrentvms, int vmsperhost, double vcenterhamultiplier) {

		int i = 0;
		Float f = null;

		if (desktopstate.equalsIgnoreCase("on")) {
			f = ((float) concurrentvms / (float) vmsperhost);
		} else if (desktopstate.equalsIgnoreCase("off")) {
			f = ((float) concurrentvms / (float) vmsperhost);
		} else if (desktopstate.equalsIgnoreCase("suspend")) {
			f = ((float) concurrentvms / (float) vmsperhost);
		} else {
			f = ((float) concurrentvms / (float) vmsperhost);
		}

		f = (float) Math.ceil(f * 2) / 2; // round up
		f = (float) Math.round(f); // round up to nearest integer (fix for calculation with results lower than 1.5)
		f = (float) (f * vcenterhamultiplier); // add cluster ha overhead
		f = (float) Math.ceil(f * 2) / 2; // round up

		final Float ftemp = (float) 0.5;
		if (ftemp == (f % 1)) {
			f += ftemp; // add 0.5 if modulus is 0.5 is applicable for
						// cases where host number not exact
		}

		i = f.intValue(); // convert to Integer

		return i;
	}

	/**
	 * Calculate amount of VM swap per host when option is selected.
	 * 
	 * @param vmswapsize    virtual machine vswap size after reservations
	 * @param videoswapsize video swap size
	 * @param hostvmcount   number of virtual machines per host
	 * 
	 * @since 1.0
	 * @return int
	 */
	public int calculateLocalSwapSize(int vmswapsize, int videoswapsize, int hostvmcount) {

		int i = ((vmswapsize + videoswapsize) * hostvmcount) / 1024;
		i += (host.getThirdpartylocalstorage() / 1024);

		return i;
	}

	/**
	 * 
	 * @return the cbrcmemory
	 */
	public int getCbrcmemory() {
		return Cbrcmemory;
	}

	/**
	 * 
	 * @return the memoryoverhead
	 */
	public int getMemoryoverhead() {
		return Memoryoverhead;
	}

	/**
	 * 
	 * @return the sharedmemory
	 */
	public int getSharedmemory() {
		return Sharedmemory;
	}

	/**
	 * 
	 * @return the socketcores
	 */
	public int getSocketcores() {
		return Socketcores;
	}

	/**
	 * 
	 * @param hostsockets
	 * @param hostsocketscores
	 * 
	 * @since 1.0
	 * @return int
	 */
	public int getSocketCoresCount(int hostsockets, int hostsocketscores) {
		return hostsockets * hostsocketscores;
	}

	/**
	 * 
	 * @return the socketcount
	 */
	public int getSocketcount() {
		return Socketcount;
	}

	/**
	 * Return Total host memory without TPS
	 * 
	 * @param hostmemoryoverhead
	 * @param hostvmcount
	 * @param vmmemorytotal
	 * @param hostcbrcmemory
	 * 
	 * @return int
	 */
	public int getTotalMemoryNoTPS(int hostmemoryoverhead, int hostvmcount, int vmmemorytotal, int hostcbrcmemory) {
		final int i = (hostvmcount * vmmemorytotal);
		int temp = (hostmemoryoverhead + i + hostcbrcmemory) / 1024;
		temp += (host.getThirdpartymemory() / 1024);
		return temp;
	}

	/**
	 * Return Total host memory with TPS
	 * 
	 * @param hostmemoryoverhead
	 * @param hostvmcount
	 * @param vmmemorytotal
	 * @param hostcbrcmemory
	 * @param hostsharedmemory
	 * @param hostusedmemory
	 * @return int
	 */
	public int getTotalMemoryTPS(int hostmemoryoverhead, int hostvmcount, int vmmemorytotal, int hostcbrcmemory,
			int hostsharedmemory, int hostusedmemory) {
		final int i = (hostvmcount * vmmemorytotal);
		final int hostcpuallocated = (100 - hostusedmemory) + (100 - hostsharedmemory);
		final Double vmmemorytotalovercommit = (hostcpuallocated / 100.0) * i;
		int b = ((int) (hostmemoryoverhead + vmmemorytotalovercommit + hostcbrcmemory)) / 1024;
		b += (host.getThirdpartymemory() / 1024);
		return b;
	}

	/**
	 * 
	 * @return the usedmemory
	 */
	public int getUsedmemory() {
		return Usedmemory;
	}

	/**
	 * 
	 * @return the vmcore
	 */
	public int getVmcore() {
		return Vmcore;
	}

	/**
	 * 
	 * Calculate total VMs per host
	 * 
	 * @param concurrentvms
	 * @param hostcores
	 * @param vmscore
	 * 
	 * @since 1.0
	 * @return int
	 */
	public int getVmCount(int concurrentvms, int hostcores, int vmscore) {

		int i = 0;
		if (concurrentvms < (hostcores * vmscore)) {
			i = concurrentvms;
		} else {
			i = (vmscore * hostcores);
		}

		return i;
	}

	/**
	 * @param cbrcmemory the cbrcmemory to set
	 */
	public void setCbrcmemory(Integer cbrcmemory) {
		host.Cbrcmemory = cbrcmemory;
	}

	/**
	 * @param memoryoverhead the memoryoverhead to set
	 */
	public void setMemoryoverhead(Integer memoryoverhead) {
		host.Memoryoverhead = memoryoverhead;
	}

	/**
	 * @param sharedmemory the sharedmemory to set
	 */
	public void setSharedmemory(Integer sharedmemory) {
		host.Sharedmemory = sharedmemory;
	}

	/**
	 * @param socketcores the socketcores to set
	 */
	public void setSocketcores(Integer socketcores) {
		host.Socketcores = socketcores;
	}

	/**
	 * @param socketcount the socketcount to set
	 */
	public void setSocketcount(Integer socketcount) {
		host.Socketcount = socketcount;
	}

	/**
	 * @param usedmemory the usedmemory to set
	 */
	public void setUsedmemory(Integer usedmemory) {
		host.Usedmemory = usedmemory;
	}

	/**
	 * @param vmcore the vmcore to set
	 */
	public void setVmcore(Integer vmcore) {
		host.Vmcore = vmcore;
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
		return "Host []";
	}

}
