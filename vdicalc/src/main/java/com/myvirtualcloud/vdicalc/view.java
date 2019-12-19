package com.myvirtualcloud.vdicalc;

/*
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
public class view {
    /**
     * Field vcvmlimit.
     */
    private static int Vcvmlimit; // vcenter limit set by user
    /**
     * Field externalsessioncount.
     */
    private static int Externalsessioncount; // n external connections
    /**
     * Field vccount.
     */
    private static int Vccount; // n of vcenter servers
    /**
     * Field cscount.
     */
    private static int Cscount; // n of connection servers
    /**
     * Field sscount.
     */
    private static int Sscount; // n of security servers

    /**
     * Calculate number of connection servers
     * 
     * @param vmcount
     * 
     * @since 2.0
     * 
     * @return int
     */
    public static int calculateConnectionServerCount(int vmcount) {
        Float f = (vmcount / (float) 2000.00);
        f = (float) Math.ceil(f); // round up to next Integer
        if (f >= 5) {
            f++;
            f++; // add 2 additional brokers for redundancy if more than 5
        } else {
            f++; // add 1 additional broker for redundancy if total less than 5
        }
        final int i = f.intValue(); // convert to Integer
        return i;
    }

    /**
     * Calculate number of security servers
     * 
     * @param externalsessioncount
     * 
     * @since 2.0
     * 
     * @return int
     */
    public static int calculateSecurityServerCount(int externalsessioncount) {
        Float f = (externalsessioncount / (float) 2000.00);
        f = (float) Math.ceil(f); // round up to next Integer
        if (f > 0.0) {
            f++; // add 1 additional security server for redundancy
        }
        final int i = f.intValue(); // convert to Integer
        return i;
    }

    /**
     * Calculate number of vCenter servers
     * 
     * @param vmconcurrentcount
     * @param vcvmlimit
     * @since 1.0
     * 
     * 
     * @return int
     */
    public static int calculatevCenterServerCount(int vmconcurrentcount, int vcvmlimit) {
        Float f = ((float) vmconcurrentcount / (float) vcvmlimit);
        f = (float) Math.ceil(f); // round up to next Integer
        final int i = f.intValue(); // convert to Integer
        return i;
    }

    /**
     * Calculate memory requirements for deployment
     * 
     * @param connectionServerCount
     * @param securityServerCount
     * @param vCenterServerCount
     * 
     * 
     * @return int
     */
    public static int calculateViewMemory(int connectionServerCount, int securityServerCount, int vCenterServerCount) {

        final int cs = Integer.parseInt(vdicalc.DEFAULTS.getString("view.connectionserver.memory.default")) //$NON-NLS-1$
                * connectionServerCount;
        final int ss = Integer.parseInt(vdicalc.DEFAULTS.getString("view.securityserver.memory.default")) //$NON-NLS-1$
                * securityServerCount;
        final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("view.vcenter.memory.default")) //$NON-NLS-1$
                * securityServerCount;
        final int co = Integer.parseInt(vdicalc.DEFAULTS.getString("view.composer.memory.default")); //$NON-NLS-1$

        return cs + ss + vc + co;

    }

    /**
     * Calculate storage requirements for deployment
     * 
     * @param connectionServerCount
     * @param securityServerCount
     * @param vCenterServerCount
     * 
     * 
     * @return double
     */
    public static double calculateViewStorage(int connectionServerCount, int securityServerCount,
            int vCenterServerCount) {

        final int cs = Integer.parseInt(vdicalc.DEFAULTS.getString("view.connectionserver.storage.default")) //$NON-NLS-1$
                * connectionServerCount;
        final int ss = Integer.parseInt(vdicalc.DEFAULTS.getString("view.securityserver.storage.default")) //$NON-NLS-1$
                * securityServerCount;
        final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("view.vcenter.storage.default")) //$NON-NLS-1$
                * vCenterServerCount;
        final int co = Integer.parseInt(vdicalc.DEFAULTS.getString("view.composer.storage.default")); //$NON-NLS-1$

        final double d = (cs + ss + vc + co) / 1048576.0; // Convert to Terabytes

        return d;

    }

    /**
     * Calculate vCPU requirements for deployment
     * 
     * @param connectionServerCount
     * @param securityServerCount
     * @param vCenterServerCount
     * 
     * 
     * @return int
     */
    public static int calculateViewvCPU(int connectionServerCount, int securityServerCount, int vCenterServerCount) {

        final int cs = Integer.parseInt(vdicalc.DEFAULTS.getString("view.connectionserver.vcpu.default")) //$NON-NLS-1$
                * connectionServerCount;
        final int ss = Integer.parseInt(vdicalc.DEFAULTS.getString("view.securityserver.vcpu.default")) //$NON-NLS-1$
                * securityServerCount;
        final int vc = Integer.parseInt(vdicalc.DEFAULTS.getString("view.vcenter.vcpu.default")) //$NON-NLS-1$
                * securityServerCount;
        final int co = Integer.parseInt(vdicalc.DEFAULTS.getString("view.composer.vcpu.default")); //$NON-NLS-1$

        return cs + ss + vc + co;

    }

    /**
     * 
     * @return the cscount
     */
    public static int getCscount() {
        return Cscount;
    }

    /**
     * 
     * @return the externalsessioncount
     */
    public static int getExternalsessioncount() {
        return Externalsessioncount;
    }

    /**
     * 
     * @return the sscount
     */
    public static int getSscount() {
        return Sscount;
    }

    /**
     * 
     * @return the vccount
     */
    public static int getVccount() {
        return Vccount;
    }

    /**
     * 
     * @return the vcvmlimit
     */
    public static int getVcvmlimit() {
        return Vcvmlimit;
    }

    /**
     * @param cscount the cscount to set
     */
    public static void setCscount(Integer cscount) {
        view.Cscount = cscount;
    }

    /**
     * @param externalsessioncount the externalsessioncount to set
     */
    public static void setExternalsessioncount(Integer externalsessioncount) {
        view.Externalsessioncount = externalsessioncount;
    }

    /**
     * @param sscount the sscount to set
     */
    public static void setSscount(Integer sscount) {
        view.Sscount = sscount;
    }

    /**
     * @param vccount the vccount to set
     */
    public static void setVccount(Integer vccount) {
        view.Vccount = vccount;
    }

    /**
     * @param vcvmlimit the vcvmlimit to set
     */
    public static void setVcvmlimit(Integer vcvmlimit) {
        view.Vcvmlimit = vcvmlimit;
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
        return "View []";
    }

}
