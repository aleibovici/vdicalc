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
public class cluster {
	/**
	 * Field count.
	 */
	private static int Count; // number of clusters
	/**
	 * Field size.
	 */
	private static int Size; // cluster size
	/**
	 * Field hamultiplier.
	 */
	private static double Hamultiplier = 0.0D; // ha multiplier

	/**
	 * Calculate cluster size
	 * 
	 * @param totalhosts
	 * @param vcclustersize
	 * @return int
	 */
	public static int calcCount(int totalhosts, int vcclustersize) {
		final Float f = ((float) totalhosts / (float) vcclustersize);
		final int i = (int) Math.ceil(f); // round up and convert to integer
		return i;
	}

	/**
	 * Calculate HA multiplier
	 *
	 * @param vcenterhastate
	 * @return double
	 */
	public static double calcHaMultiplier(Boolean vcenterhastate) {
		double d = 0;
		if (vcenterhastate) {
			d = 1.125;
		} else {
			d = 1;
		}
		return d;
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
	 * @return the hamultiplier
	 */
	public static double getHamultiplier() {
		return Hamultiplier;
	}

	/**
	 * 
	 * @return the size
	 */
	public static int getSize() {
		return Size;
	}

	/**
	 * @param count the count to set
	 */
	public static void setCount(int count) {
		cluster.Count = count;
	}

	/**
	 * @param hamultiplier the hamultiplier to set
	 */
	public static void setHamultiplier(double hamultiplier) {
		cluster.Hamultiplier = hamultiplier;
	}

	/**
	 * @param size the size to set
	 */
	public static void setSize(int size) {
		cluster.Size = size;
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
		return "Cluster []"; //$NON-NLS-1$
	}

}
