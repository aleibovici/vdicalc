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
public class xen {
        /**
         * Field ddccount.
         */
        private static int Ddccount; // n Delivery Controllers

        /**
         * Calculate number of Delivery Controllers
         * 
         * @param vmcount
         * @since 2.0
         * @return int
         */
        public static int calculateDeliveryControllerCount(int vmcount) {
                int itmp = 0;
                Float f = (vmcount / 2000.00F);
                f = (float) Math.ceil(f); // round up to next Integer
                f++; // add 1 additional broker for redundancy
                itmp = f.intValue(); // convert to Integer
                return itmp;
        }

        /**
         * Calculate XenDesktop memory requirements for deployment
         * 
         * @param deliveryControllerCount
         * @param vCenterServerCount
         * 
         * 
         * @return int
         */
        public static int calculateXenMemory(int deliveryControllerCount, int vCenterServerCount) {

                final int dc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.controller.memory.default")) //$NON-NLS-1$
                                * deliveryControllerCount;
                final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.vcenter.memory.default")) //$NON-NLS-1$
                                * vCenterServerCount;

                return dc + vc;

        }

        /**
         * Calculate XenDesktop storage requirements for deployment
         * 
         * @param deliveryControllerCount
         * @param vCenterServerCount
         * 
         * 
         * @return int
         */
        public static int calculateXenStorage(int deliveryControllerCount, int vCenterServerCount) {

                final int dc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.controller.storage.default")) //$NON-NLS-1$
                                * deliveryControllerCount;
                final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.vcenter.storage.default")) //$NON-NLS-1$
                                * vCenterServerCount;

                return dc + vc;

        }

        /**
         * Calculate XenDesktop vCPU requirements for deployment
         * 
         * @param deliveryControllerCount
         * @param vCenterServerCount
         * 
         * 
         * @return int
         */
        public static int calculateXenvCPU(int deliveryControllerCount, int vCenterServerCount) {

                final int dc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.controller.vcpu.default")) //$NON-NLS-1$
                                * deliveryControllerCount;
                final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("xen.vcenter.vcpu.default")) //$NON-NLS-1$
                                * vCenterServerCount;

                return dc + vc;

        }

        /**
         * @return the ddccount
         */
        public static int getDdccount() {
                return Ddccount;
        }

        /**
         * @param ddccount the ddccount to set
         */
        public static void setDdccount(Integer ddccount) {
                xen.Ddccount = ddccount;
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
         **/
        @Override
        public String toString() {
                return "Xen []";
        }

}
