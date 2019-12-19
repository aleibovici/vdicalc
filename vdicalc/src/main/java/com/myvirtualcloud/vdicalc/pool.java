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
public class pool {

	/**
	 * Field count.
	 */
	@Deprecated
	private int count; // number of pools

	/**
	 * Field vmcount.
	 */
	private int vmcount; // number of vms

	/**
	 * Field vmconcurrentcount.
	 */

	private int vmconcurrentcount; // number of concurrent vms

	/**
	 * Field vmspercpucore.
	 */
	private int vmspercpucore; // number of vms per CPU core

	/**
	 * Method getCount.
	 * 
	 * @return int
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @return the vmconcurrentcount
	 */
	public int getVmconcurrentcount() {
		return vmconcurrentcount;
	}

	/**
	 * @return the vmcount
	 */
	public int getVmcount() {
		return vmcount;
	}

	/**
	 * 
	 * @return the vmspercpucore
	 */
	public int getVmspercpucore() {
		return vmspercpucore;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @param vmconcurrentcount the vmconcurrentcount to set
	 */
	public void setVmconcurrentcount(int vmconcurrentcount) {
		this.vmconcurrentcount = vmconcurrentcount;
	}

	/**
	 * @param vmcount the vmcount to set
	 */
	public void setVmcount(int vmcount) {
		this.vmcount = vmcount;
	}

	/**
	 * @param vmspercpucore the vmspercpucore to set
	 */
	public void setVmspercpucore(int vmspercpucore) {
		this.vmspercpucore = vmspercpucore;
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
		final StringBuilder builder = new StringBuilder(32);
		builder.append("Pool []");
		return builder.toString();
	}

}
