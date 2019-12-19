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
public class validations {

	/**
	 * IsNumeric
	 * 
	 * @param str
	 * @since 1.0
	 * @return boolean
	 **/
	public boolean equalNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			final double d = Double.parseDouble(str); // $codepro.audit.disable variableUsage
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
		return true;
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
		builder.append("Validations []");
		return builder.toString();
	}

}
