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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

/**
 * @author andreleibovici
 * @version $Revision: 7.1 $
 */
// @SuppressWarnings({ "serial" })
@SuppressWarnings("unchecked")
public class vdicalc extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Field comboHeight.
	 */
	static String comboHeight; // Define height of combo
	/**
	 * Field textfieldHeight.
	 */
	static String TextfieldHeight; // Define height for textfield
	/**
	 * Field buttonHeight.
	 */
	static String ButtonHeight; // Define height for buttons
	/**
	 * Field MESSAGES.
	 */
	static final ResourceBundle MESSAGES = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());
	/**
	 * Field DEFAULTS.
	 */
	static final ResourceBundle DEFAULTS = ResourceBundle.getBundle("DefaultValuesBundle");
	/**
	 * Field SETTINGS.
	 */
	static final ResourceBundle SETTINGS = ResourceBundle.getBundle("DefaultSettings");
	/**
	 * Field FC.
	 */
	static final JFileChooser FC = new JFileChooser(); // file
														// loader
	/**
	 * Field componentMap.
	 */
	private static HashMap<String, Component> componentMap;

	/*
	 * Temporary Variables
	 */
	/**
	 * Field tmphostcount.
	 */
	static int tmphostcount;
	/**
	 * Field tmpcoremhz.
	 */
	static int tmpcoremhz;
	/**
	 * Field tmphostmemoryTPS.
	 */
	static int tmphostmemoryTPS;
	/**
	 * Field tmphostmemoryNoTPS.
	 */
	static int tmphostmemoryNoTPS;
	/**
	 * Field tmppoolvmconcurrent.
	 */
	static int tmppoolvmconcurrent;
	/**
	 * Field tmpcloneusablecapacity.
	 */
	static double tmpcloneusablecapacity;
	/**
	 * Field tmpusablecapacity.
	 */
	static double tmpusablecapacity;
	/**
	 * Field tmpdatastoresize.
	 */
	static int tmpdatastoresize;
	/**
	 * Field tmpreplicareadfrontendIOPS.
	 */
	static int tmpreplicareadfrontendIOPS;
	/**
	 * Field tmpclonereadfrontendIOPS.
	 */
	static int tmpclonereadfrontendIOPS;
	/**
	 * Field tmpclonereadbackendIOPS.
	 */
	static int tmpclonereadbackendIOPS;
	/**
	 * Field tmpclonewritefrontendIOPS.
	 */
	static int tmpclonewritefrontendIOPS;
	/**
	 * Field tmpclonewritebackendIOPS.
	 */
	static int tmpclonewritebackendIOPS;
	/**
	 * Field tmppersistentreadfrontendIOPS.
	 */
	static int tmppersistentreadfrontendIOPS;
	/**
	 * Field tmppersistentreadbackendIOPS.
	 */
	static int tmppersistentreadbackendIOPS;
	/**
	 * Field tmppersistentwritefrontendIOPS.
	 */
	static int tmppersistentwritefrontendIOPS;
	/**
	 * Field tmppersistentwritebackendIOPS.
	 */
	static int tmppersistentwritebackendIOPS;
	/**
	 * Field tmppersistentusablecapacity.
	 */
	static double tmppersistentusablecapacity = 0D;
	/**
	 * Field tmppersistentdatastorecount.
	 */
	static int tmppersistentdatastorecount;
	/**
	 * Field tmppersistentdatastoresize.
	 */
	static int tmppersistentdatastoresize;
	/**
	 * Field tmpreplicausablecapacity.
	 */
	static double tmpreplicausablecapacity;
	/**
	 * Field tmphostlocalswapsize.
	 */
	static int tmphostlocalswapsize;
	/**
	 * Field tmpsuspendusablecapacity.
	 */
	static double tmpsuspendusablecapacity = 0D;
	/**
	 * Field tmpswappowerstatecapacityreduction.
	 */
	static double tmpswappowerstatecapacityreduction = 0D;
	/**
	 * Field tmpparentusablecapacity.
	 */
	static double tmpparentusablecapacity = 0D;
	/**
	 * Field tmppooltypefull.
	 */
	static boolean tmppooltypefull = false;

	/*
	 * Objects
	 */
	/**
	 * Field storageobj.
	 */
	static storage storageobj = new storage();

	/**
	 * Field hostobj.
	 */
	static host hostobj = new host();
	/**
	 * Field functionsobj.
	 */
	static functions functionsobj = new functions();
	/**
	 * Field validationsobj.
	 */
	static validations validationsobj = new validations();
	/**
	 * Field tabbedpanel_vm.
	 */
	static JTabbedPane tabbedpanel_vm = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Field tabbedpanel_results.
	 */
	static JTabbedPane tabbedpanel_results = new JTabbedPane(JTabbedPane.TOP);
	/**
	 * Field externalconcurrentsessionstxt.
	 */
	static JTextField externalconcurrentsessionstxt = new JTextField(
			DEFAULTS.getString("viewexternalconcurrentsessionstxt.default"), 4);
	/**
	 * Field vchacombovalues.
	 */
	static String[] vchacombovalues = { "Yes", "No" };
	/**
	 * Field vchacombo.
	 */
	static JComboBox<String> vchacombo = new JComboBox<>(vchacombovalues);
	/**
	 * Field vcclustersizecombovalues.
	 */
	static String[] vcclustersizecombovalues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
			"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
			"32" };
	/**
	 * Field vcclustersizecombo.
	 */
	static JComboBox<String> vcclustersizecombo = new JComboBox<>(vcclustersizecombovalues);

	/**
	 * Field backendserverinfracombovalues.
	 */
	static String[] backendserverinfracombovalues = { "No", "Yes" };
	/**
	 * Field backendserverinfracombo.
	 */
	static JComboBox<String> backendserverinfracombo = new JComboBox<>(backendserverinfracombovalues);
	/**
	 * Field vcvmlimittxt.
	 */
	static JTextField vcvmlimittxt = new JTextField(DEFAULTS.getString("vcvmlimittxt.default"), 4);
	/**
	 * Field hostsocketscombovalues.
	 */
	static String[] hostsocketscombovalues = { "1", "2", "4", "6", "8", "10" };
	/**
	 * Field hostsocketscombo.
	 */
	static JComboBox<String> hostsocketscombo = new JComboBox<>(hostsocketscombovalues);
	/**
	 * Field coressocketcombovalues.
	 */
	static String[] coressocketcombovalues = { "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "24", "32", "64" };
	/**
	 * Field coresocketscombo.
	 */
	static JComboBox<String> coresocketscombo = new JComboBox<>(coressocketcombovalues);
	/**
	 * Field hostsharedmemorycombovalues.
	 */
	static String[] hostsharedmemorycombovalues = { "0", "10", "20", "30", "40", "50" };
	/**
	 * Field hostsharedmemorycombo.
	 */
	static JComboBox<String> hostsharedmemorycombo = new JComboBox<>(hostsharedmemorycombovalues);
	/**
	 * Field hostmemoryoverheadtxt.
	 */
	static JTextField hostmemoryoverheadtxt = new JTextField(DEFAULTS.getString("hostmemoryoverheadtxt.default"), 3);
	/**
	 * Field hostusedmemoryvalues.
	 */
	static String[] hostusedmemoryvalues = { "0", "70", "80", "90", "100" };
	/**
	 * Field hostusedmemorycombo.
	 */
	static JComboBox<String> hostusedmemorycombo = new JComboBox<>(hostusedmemoryvalues);
	/**
	 * Field hostcbrcvalues.
	 */
	static String[] hostcbrcvalues = { "Off", "512", "1024", "2048" };
	/**
	 * Field hostcbrccombo.
	 */
	static JComboBox<String> hostcbrccombo = new JComboBox<>(hostcbrcvalues);

	/**
	 * Field hostinframodelcombovalues.
	 */
	static String[] hostinframodelcombovalues = { "Traditional", "Datrium", "Nutanix" };
	/**
	 * Field hostinframodelcombo.
	 */
	static JComboBox<String> hostinframodelcombo = new JComboBox<>(hostinframodelcombovalues);
	/**
	 * Field hostlocalvmswapcombovalues.
	 */
	static String[] hostlocalvmswapcombovalues = { "On", "Off" };
	/**
	 * Field hostlocalvmswapcombo.
	 */
	static JComboBox<String> hostlocalvmswapcombo = new JComboBox<>(hostlocalvmswapcombovalues);
	/**
	 * Field desktopbrokertypecombovalues.
	 */
	static String[] desktopbrokertypecombovalues = { "View", "Xen", "Workspot" };
	/**
	 * Field desktopbrokertypecombo.
	 */
	static JComboBox<String> desktopbrokertypecombo = new JComboBox<>(desktopbrokertypecombovalues);
	/**
	 * Field storagedatastorevmtxt.
	 */
	static JTextField storagedatastorevmtxt = new JTextField(DEFAULTS.getString("storagedatastorevmtxt.default"), 2);
	/**
	 * Field persistentdisksperdatastoretxt.
	 */
	static JTextField persistentdisksperdatastoretxt = new JTextField(
			DEFAULTS.getString("persistentdisksperdatastoretxt.default"), 3);
	/**
	 * Field storageoverheadcombovalues.
	 */
	static String[] storageoverheadcombovalues = { "0", "10", "15", "20", "30", "40" };
	/**
	 * Field storageoverheadcombo.
	 */
	static JComboBox<String> storageoverheadcombo = new JComboBox<>(storageoverheadcombovalues);
	/**
	 * Field storagetieringcombovalues.
	 */
	static String[] storagetieringcombovalues = { "On", "Off" };
	/**
	 * Field storagetieringcombo.
	 */
	static JComboBox<String> storagetieringcombo = new JComboBox<>(storagetieringcombovalues);
	/**
	 * Field storageblockdeduplicationratiocombovalues.
	 */
	static String[] storageblockdeduplicationratiocombovalues = { "0", "10", "20", "30", "40", "50", "60", "70", "80",
			"90" };
	/**
	 * Field storageblockdeduplicationratiocombo.
	 */
	static JComboBox<String> storageblockdeduplicationratiocombo = new JComboBox<>(storageblockdeduplicationratiocombovalues);
	/**
	 * Field storagecachereadratiocombovalues.
	 */
	static String[] storagecachereadratiocombovalues = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20",
			"25", "30", "40", "50", "60", "70", "80", "90", "100" };
	/**
	 * Field storagecachereadratiocombo.
	 */
	static JComboBox<String> storagecachereadratiocombo = new JComboBox<>(storagecachereadratiocombovalues);
	/**
	 * Field storagecachewriteratiocombovalues.
	 */
	static String[] storagecachewriteratiocombovalues = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20",
			"25", "30", "40", "50", "60", "70", "80", "90" };
	/**
	 * Field storagecachewriteratiocombo.
	 */
	static JComboBox<String> storagecachewriteratiocombo = new JComboBox<>(storagecachewriteratiocombovalues);
	/**
	 * Field vmreplicaconcurrentbootcounttxt.
	 */
	static JTextField vmreplicaconcurrentbootcounttxt = new JTextField(
			DEFAULTS.getString("vmreplicaconcurrentbootcounttxt.default"), 3);
	/**
	 * Field storagedeltaraidtypecombovalues.
	 */
	static String[] storagedeltaraidtypecombovalues = { "0", "5", "6", "10", "DP" };
	/**
	 * Field storagedeltaraidtypecombo.
	 */
	static JComboBox<String> storagedeltaraidtypecombo = new JComboBox<>(storagedeltaraidtypecombovalues);
	/**
	 * Field storagepersistentraidtypecombovalues.
	 */
	static String[] storagepersistentraidtypecombovalues = { "0", "5", "6", "10", "DP" };
	/**
	 * Field storagepersistentraidtypecombo.
	 */
	static JComboBox<String> storagepersistentraidtypecombo = new JComboBox<>(storagepersistentraidtypecombovalues);

	/**
	 * Method main.
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {

		/*
		 * Check for expired application and exit Expiry settings located in
		 * DefaultSettings.properties
		 */
		if (functionsobj.isApplicationExpired()) {
			JOptionPane.showMessageDialog(null, "Application Expired", null, 0);
			System.exit(0);
		}

		/**
		 * Load VDI Calculator Guide Page upon execution
		 */
		if (SETTINGS.getString("openmanual.setting").equalsIgnoreCase("on")) {
			functionsobj.openURI(MESSAGES.getString("guide.uri"));
		}

		/*
		 * Utilize Google Analytics to track when VDI calculator is launched The loader
		 * include the version of the VDI calculator being loaded Updated to iterate
		 * trhought multiple Google Analytic IDs
		 */
		if (SETTINGS.getString("googleanalyticstracker.setting").equalsIgnoreCase("on")) {
			String GoogleAnalyticsTrackers = functionsobj.getGoogleAnalyticsTrackers();
			List<String> GoogleAnalyticsTrackersIDs = Arrays.asList(GoogleAnalyticsTrackers.split(","));	
			for (int i = 0; i < GoogleAnalyticsTrackersIDs.size(); i++) {
				functionsobj.jGoogleAnalyticsTracker("AppLoad", SETTINGS.getString("googleanalyticstracker.version"),
						GoogleAnalyticsTrackersIDs.get(i).toUpperCase());
			}
		}

		/*
		 * Determine System OS and set properties for UI Property: Swing's Look & Feel
		 */
		try {
			if (functions.isMac()) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else if (functions.isWindows()) {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} else if (functions.isLinux()) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		vdicalc sal = new vdicalc(args);
	}

	/**
	 * updateCalculation
	 * 
	 * @param updateResultsUI
	 */
	public static void updateCalculation(boolean updateResultsUI) {

		/*
		 * Reset Temporary variables and acumulators
		 */
		tmphostcount = 0;
		tmpcoremhz = 0;
		tmphostmemoryTPS = 0;
		tmphostmemoryNoTPS = 0;
		tmppoolvmconcurrent = 0;
		tmpcloneusablecapacity = 0D;
		tmpusablecapacity = 0;
		tmpdatastoresize = 0;
		tmpreplicareadfrontendIOPS = 0;
		tmpclonereadfrontendIOPS = 0;
		tmpclonereadbackendIOPS = 0;
		tmpclonewritefrontendIOPS = 0;
		tmpclonewritebackendIOPS = 0;
		tmppersistentreadfrontendIOPS = 0;
		tmppersistentreadbackendIOPS = 0;
		tmppersistentwritefrontendIOPS = 0;
		tmppersistentwritebackendIOPS = 0;
		tmppersistentusablecapacity = 0D;
		tmppersistentdatastorecount = 0;
		tmppersistentdatastoresize = 0;
		tmpreplicausablecapacity = 0D;
		tmphostlocalswapsize = 0;
		tmpsuspendusablecapacity = 0D;
		tmpswappowerstatecapacityreduction = 0D;
		tmpparentusablecapacity = 0D;

		storageobj.setOverhead(Integer.parseInt(storageoverheadcombo.getSelectedItem().toString()));
		storageobj.setDatastorevmcount(Integer.parseInt(storagedatastorevmtxt.getText().toString()));

		cluster.setSize(Integer.parseInt(vcclustersizecombo.getSelectedItem().toString()));

		if (vchacombo.getSelectedItem().toString().equalsIgnoreCase("yes")) {
			cluster.setHamultiplier(cluster.calcHaMultiplier(true));
		} else {
			cluster.setHamultiplier(cluster.calcHaMultiplier(false));
		}

		hostobj.setSocketcount(Integer.parseInt(hostsocketscombo.getSelectedItem().toString()));
		hostobj.setSocketcores(Integer.parseInt(coresocketscombo.getSelectedItem().toString()));
		host.setCorescount(hostobj.getSocketCoresCount(hostobj.getSocketcount(), hostobj.getSocketcores()));

		/*
		 * Deduct the amount of CPU cores used for the storage virtualization layer
		 * (HCI)
		 */
		if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("nutanix")) {
			host.setCorescount(host.getCorescount()
					- Integer.parseInt(DEFAULTS.getString("vcpureservation.nutanix.default").toString()));
		}

		hostobj.setSharedmemory(Integer.parseInt(hostsharedmemorycombo.getSelectedItem().toString()));
		hostobj.setUsedmemory(Integer.parseInt(hostusedmemorycombo.getSelectedItem().toString()));
		hostobj.setMemoryoverhead(Integer.parseInt(hostmemoryoverheadtxt.getText().toString()));

		/*
		 * Content Based Read Cache
		 */
		if (!hostcbrccombo.getSelectedItem().toString().equals("Off")) {
			hostobj.setCbrcmemory(Integer.parseInt(hostcbrccombo.getSelectedItem().toString()));
		} else {
			hostobj.setCbrcmemory(0);
		}

		storageobj.setBlockdeduplicationratio(
				Integer.parseInt(storageblockdeduplicationratiocombo.getSelectedItem().toString()));
		storageobj.persistentdatastorevmcount = Integer.parseInt(persistentdisksperdatastoretxt.getText().toString());
		storageobj.cloneraidtype = storagedeltaraidtypecombo.getSelectedItem().toString();
		storageobj.persistentraidtype = storagepersistentraidtypecombo.getSelectedItem().toString();
		storageobj.setCachereadratio(Integer.parseInt(storagecachereadratiocombo.getSelectedItem().toString()));
		storageobj.setCachewriteratio(Integer.parseInt(storagecachewriteratiocombo.getSelectedItem().toString()));

		vm vm1obj = new vm();
		vm vm2obj = new vm();
		vm vm3obj = new vm();
		vm vm4obj = new vm();
		vm vm5obj = new vm();

		pool pool1obj = new pool();
		pool pool2obj = new pool();
		pool pool3obj = new pool();
		pool pool4obj = new pool();
		pool pool5obj = new pool();

		storageobj.setReplicausablecapacity(0D);
		storageobj.datastorecount = 0;

		componentMap = new HashMap<String, Component>();

		int totalTabs = tabbedpanel_vm.getTabCount() - 1;
		for (int i = 1; i < totalTabs; i++) {
			componentMap.clear();
			JPanel c = (JPanel) tabbedpanel_vm.getComponentAt(i);
			Component[] components = c.getComponents();
			for (int n = 0; n < components.length; ++n) {
				if (components[n].getName() != null) {
					componentMap.put(components[n].getName(), components[n]);
				}
			}

			vm vmobj = null;
			pool poolobj = null;
			switch (i) {
			case 1:
				vmobj = vm1obj;
				poolobj = pool1obj;
				break;
			case 2:
				vmobj = vm2obj;
				poolobj = pool2obj;
				break;
			case 3:
				vmobj = vm3obj;
				poolobj = pool3obj;
				break;
			case 4:
				vmobj = vm4obj;
				poolobj = pool4obj;
				break;
			case 5:
				vmobj = vm5obj;
				poolobj = pool5obj;
				break;
			}

			/*
			 * Map CustomPanel objects to componentMap
			 */
			JComboBox<String> aa = (JComboBox<String>) componentMap.get("vmvcpucombo");
			vmobj.setVcpucount(Integer.parseInt(aa.getSelectedItem().toString()));
			JTextField ab = (JTextField) componentMap.get("vmvcpumhztxt");
			vmobj.setVcpumhz(Integer.parseInt(ab.getText()));
			JComboBox<String> ac = (JComboBox<String>) componentMap.get("vmvcpumhzoverheadcombo");
			vmobj.setVcpuoverhead(Integer.parseInt(ac.getSelectedItem().toString()));
			JComboBox<String> ad = (JComboBox<String>) componentMap.get("vmrefreshratecombo");
			vmobj.setRefreshrate(Integer.parseInt(ad.getSelectedItem().toString()));
			JComboBox<String> ae = (JComboBox<String>) componentMap.get("vmmemoryreservationcombo");
			vmobj.setMemoryreservation(Integer.parseInt(ae.getSelectedItem().toString()));
			JComboBox<String> af = (JComboBox<String>) componentMap.get("vmdisplaynumbercombo");
			vmobj.setDisplaycount(Integer.parseInt(af.getSelectedItem().toString()));
			JComboBox<String> ag = (JComboBox<String>) componentMap.get("vmdisplayresolutioncombo");
			vmobj.setDisplayresolution(ag.getSelectedItem().toString());
			JTextField ah = (JTextField) componentMap.get("vmmemorysizetxt");
			vmobj.setMemorysize(Integer.parseInt(ah.getText()));
			JComboBox<String> ai = (JComboBox<String>) componentMap.get("vmpowerstatecombo");
			vmobj.setPowerstate(ai.getSelectedItem().toString());
			JTextField aj = (JTextField) componentMap.get("vmparentcounttxt");
			vmobj.setParentcount(Integer.parseInt(aj.getText()));
			JTextField ak = (JTextField) componentMap.get("vmpersistentdisksizetxt");
			vmobj.setPersistentdisksize(Integer.parseInt(ak.getText()));
			JTextField al = (JTextField) componentMap.get("vmthinsizeparentvmtxt");
			vmobj.setThinsize(Integer.parseInt(al.getText()));
			JTextField am = (JTextField) componentMap.get("vmfullsizeparentvmtxt");
			vmobj.setFullclonesize(Integer.parseInt(am.getText()));
			JTextField an = (JTextField) componentMap.get("vmconcurrenttxt");
			vmobj.setConcurrentcount(Integer.parseInt(an.getText()));
			JTextField ao = (JTextField) componentMap.get("vmdisposabledisksizetxt");
			JComboBox<String> ap = (JComboBox<String>) componentMap.get("vmvramcombo");
			if (ap.getSelectedItem().toString().equalsIgnoreCase("off")) {
				vmobj.setVram(0);
			} else {
				vmobj.setVram(Integer.parseInt(ap.getSelectedItem().toString()));
			}
			JComboBox<String> ar = (JComboBox<String>) componentMap.get("poolsnapshotcountcombo");
			vmobj.setSnapshotcount(Integer.parseInt(ar.getSelectedItem().toString()));
			JTextField as = (JTextField) componentMap.get("vmcounttxt");
			vmobj.setCount(Integer.parseInt(as.getText()));
			JTextField av = (JTextField) componentMap.get("vmreplicasteadystateiotxt");
			vmobj.setReplicareadsteadystateIOPS(Integer.parseInt(av.getText()));
			JTextField ay = (JTextField) componentMap.get("vmbootIOtxt");
			vmobj.setBootIOPS(Integer.parseInt(ay.getText()));
			JTextField az = (JTextField) componentMap.get("vmdeltaIOtxt");
			vmobj.setDeltasteadystateIOPS(Integer.parseInt(az.getText()));
			JTextField ba = (JTextField) componentMap.get("vmdeltareadIOtxt");
			vmobj.setDeltareadIOpercent(Integer.parseInt(ba.getText()));
			JTextField bb = (JTextField) componentMap.get("vmpersistentIOtxt");
			vmobj.setPersistentsteadystateIOPS(Integer.parseInt(bb.getText()));
			JTextField bc = (JTextField) componentMap.get("vmpersistentreadIOtxt");
			vmobj.setPersistentreadIOpercent(Integer.parseInt(bc.getText()));
			JTextField at = (JTextField) componentMap.get("desktoppoolcounttxt");
			poolobj.setCount(Integer.parseInt(at.getText()));
			JTextField au = (JTextField) componentMap.get("vmconcurrenttxt");
			JComboBox<Object> bd = (JComboBox<Object>) componentMap.get("vmcorecombo");

			/*
			 * Assign custompanels component values to object variables
			 */
			poolobj.setVmcount(Integer.parseInt(as.getText()));
			poolobj.setVmconcurrentcount(Integer.parseInt(au.getText()));
			poolobj.setVmspercpucore(Integer.parseInt(bd.getSelectedItem().toString()));
			vmobj.setMemoryoverhead(vmobj.getMemoryOverhead(vmobj.getVcpucount(), vmobj.getMemorysize(),
					vmobj.getVram(), vmobj.getDisplaycount(), vmobj.getDisplayresolution()));
			vmobj.setMemorytotal(vmobj.getMemoryTotal(vmobj.getMemoryoverhead(), vmobj.getMemorysize()));
			vmobj.setLogsize(vmobj.getLogSize());
			vmobj.setSwapsize(vmobj.getSwapSize(vmobj.getMemoryreservation(), vmobj.getMemorysize()));
			vmobj.setVideoswapsize(
					vmobj.getVideoSwapSize(vmobj.getDisplaycount(), vmobj.getDisplayresolution(), vmobj.getVram()));
			vmobj.setIdenditysize(vmobj.getXenIdentitySize());
			vmobj.setIdentitysnapshotsize(vmobj.getXenIdentitySnapshotSize());
			vmobj.setParentsize(Integer.parseInt(am.getText()));
			host.setVmcount(hostobj.getVmCount(poolobj.getVmconcurrentcount(), host.getCorescount(),
					poolobj.getVmspercpucore()));
			storageobj.datastorecount += storageobj.getDatastoreCount(vmobj.getCount(),
					storageobj.getDatastorevmcount());

			/*
			 * Full Clone and Linked Clone Tree
			 */
			JComboBox<String> aq = (JComboBox<String>) componentMap.get("desktoppooltypecombo");

			/*
			 * Set desktop type to "full" for Workspot and disable combo
			 */
			if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {
				aq.setSelectedIndex(1);
				aq.setEnabled(false);
			} else {
				aq.setEnabled(true);
			}

			if (aq.getSelectedItem().toString().equalsIgnoreCase("full")
					|| aq.getSelectedItem().toString().equalsIgnoreCase("assigned")) {

				vmobj.setDeltasize(0);
				vmobj.setPersistentdisksize(0);
				vmobj.setDisposabledisksize(0);
				storageobj.setPersistentusablecapacity(0.0);
				storageobj.setReplicausablecapacity(0D);
				storageobj.persistentdatastorecount = 0;

				/*
				 * Zero out Persistent disk
				 */
				tmppersistentreadfrontendIOPS = 0;
				tmppersistentreadbackendIOPS = 0;
				tmppersistentwritefrontendIOPS = 0;
				tmppersistentwritebackendIOPS = 0;

				/*
				 * Define pool type for post use with Storage Virtualization overheads
				 */
				tmppooltypefull = true;

			} else if (aq.getSelectedItem().toString().equalsIgnoreCase("linked")
					|| aq.getSelectedItem().toString().equalsIgnoreCase("pooled")) {

				vmobj.setDeltasize(vmobj.calculateDeltaSize(vmobj.getFullclonesize(), vmobj.getRefreshrate()));
				vmobj.setFullclonesize(0);
				vmobj.setPersistentdisksize(Integer.parseInt(ak.getText()));
				vmobj.setDisposabledisksize(Integer.parseInt(ao.getText()));
				tmpreplicausablecapacity += storageobj.calculateReplicaUsableCapacity(vmobj.getThinsize(),
						vmobj.getSnapshotcount(), poolobj.getCount(), storageobj.datastorecount,
						storageobj.getOverhead(), storagetieringcombo.getSelectedItem().toString());
				tmppersistentusablecapacity += storageobj.getPersistentUsableCapacity(vmobj.getPersistentdisksize(),
						vmobj.getCount(), storageobj.getOverhead(), storageobj.getBlockdeduplicationratio());

				/*
				 * Persistent Disk
				 */
				if (vmobj.getPersistentdisksize() > 0) {
					tmppersistentreadfrontendIOPS += storageobj.getPersistentReadFrontendIOPS(
							vmobj.getConcurrentcount(), vmobj.getPersistentsteadystateIOPS(),
							vmobj.getPersistentreadIOpercent());
					tmppersistentreadbackendIOPS += storageobj
							.getPersistentReadBackendIOPS(tmppersistentreadfrontendIOPS, 0);
					tmppersistentwritefrontendIOPS += storageobj.getPersistentWriteFrontendIOPS(
							vmobj.getConcurrentcount(), vmobj.getPersistentsteadystateIOPS(),
							vmobj.getPersistentreadIOpercent());
					tmppersistentwritebackendIOPS = storageobj.getPersistentWriteBackendIOPS(
							tmppersistentwritefrontendIOPS, storageobj.persistentraidtype, 0);
					tmppersistentdatastorecount += storageobj.getPersistentDatastoreCount(vmobj.getCount(),
							storageobj.persistentdatastorevmcount);
				}
			}

			/*
			 * View and XenDesktop decision tree
			 */
			if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {
				vmobj.setStoragesize(vmobj.calculateStorageSize(vmobj.getFullclonesize(), vmobj.getDeltasize(),
						vmobj.getPersistentdisksize(), vmobj.getDisposabledisksize(), vmobj.getLogsize(),
						vmobj.getSwapsize(), vmobj.getVideoswapsize()));
			} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {
				vmobj.setStoragesize(vmobj.getXenStorageSize(vmobj.getFullclonesize(), vmobj.getDeltasize(),
						vmobj.getIdenditysize(), vmobj.getIdentitysnapshotsize(), vmobj.getLogsize(),
						vmobj.getSwapsize(), vmobj.getVideoswapsize()));
			} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {
				vmobj.setStoragesize(vmobj.calculateStorageSize(vmobj.getFullclonesize(), vmobj.getDeltasize(),
						vmobj.getPersistentdisksize(), vmobj.getDisposabledisksize(), vmobj.getLogsize(),
						vmobj.getSwapsize(), vmobj.getVideoswapsize()));
			}

			vmobj.setStoragesizetotal(vmobj.getStorageSizeTotal(vmobj.getStoragesize(), poolobj.getVmcount()));
			storageobj.disposableusablecapacity = storageobj.getDisposableUsableCapacity(poolobj.getVmcount(),
					poolobj.getVmconcurrentcount(), vmobj.getDisposabledisksize());

			/*
			 * VM powerstate action
			 */
			if (ai.getSelectedItem().toString().equalsIgnoreCase("On")) {

				tmpswappowerstatecapacityreduction += 0D;
				tmpsuspendusablecapacity += 0D;

			} else if (ai.getSelectedItem().toString().equalsIgnoreCase("Off")) {

				tmpswappowerstatecapacityreduction += storageobj.calculateSwapPowerstateCapacityReduction(
						poolobj.getVmcount(), poolobj.getVmconcurrentcount(), vmobj.getSwapsize());
				tmpsuspendusablecapacity += 0D;

			} else if (ai.getSelectedItem().toString().equalsIgnoreCase("Suspend")) {

				tmpswappowerstatecapacityreduction += 0D;
				tmpsuspendusablecapacity += storageobj.calculateSuspendUsableCapacity(poolobj.getVmcount(),
						vmobj.getMemorysize());
			}

			/*
			 * Non-Cumulative Temporal Variables
			 */
			int tmphostcounta = hostobj.calculateCount(vmobj.getPowerstate(), poolobj.getVmconcurrentcount(),
					host.getVmcount(), cluster.getHamultiplier());
			int tmphostlocalswapsizea = hostobj.calculateLocalSwapSize(vmobj.getSwapsize(), vmobj.getVideoswapsize(),
					host.getVmcount());

			/*
			 * Local host swap
			 */
			if (hostlocalvmswapcombo.getSelectedItem().toString().equalsIgnoreCase("on")) {
				tmphostlocalswapsize += hostobj.calculateLocalSwapSize(vmobj.getSwapsize(), vmobj.getVideoswapsize(),
						host.getVmcount());
				storageobj.setLocalswapsizetotal(
						storageobj.calculateLocalSwapTotalCapacity(tmphostcounta, tmphostlocalswapsizea));
			} else {
				host.setLocalswapsize(0);
				storageobj.setLocalswapsizetotal(0D);
			}

			/*
			 * Accumulator Variables
			 */
			tmphostcount += hostobj.calculateCount(vmobj.getPowerstate(), poolobj.getVmconcurrentcount(),
					host.getVmcount(), cluster.getHamultiplier());
			tmpcoremhz += host.getCoreMhz(vmobj.getVcpucount(), vmobj.getVcpumhz(), host.getVmcount(),
					host.getCorescount(), vmobj.getVcpuoverhead());
			tmphostmemoryNoTPS += (hostobj.getTotalMemoryNoTPS(hostobj.getMemoryoverhead(), host.getVmcount(),
					vmobj.getMemorytotal(), hostobj.getCbrcmemory()) / (tabbedpanel_vm.getTabCount() - 2));
			tmphostmemoryTPS += (hostobj.getTotalMemoryTPS(hostobj.getMemoryoverhead(), host.getVmcount(),
					vmobj.getMemorytotal(), hostobj.getCbrcmemory(), hostobj.getSharedmemory(), hostobj.getUsedmemory())
					/ (tabbedpanel_vm.getTabCount() - 2));
			tmppoolvmconcurrent += poolobj.getVmconcurrentcount();
			tmpreplicareadfrontendIOPS += storageobj.getReplicaReadFrontendIOPS(poolobj.getVmconcurrentcount(),
					vmobj.getReplicareadsteadystateIOPS(), Integer.parseInt(vmreplicaconcurrentbootcounttxt.getText()),
					vmobj.getBootIOPS(), hostobj.getCbrcmemory(), host.getCbrcreductor());
			tmpclonereadfrontendIOPS += storageobj.getCloneReadFrontendIOPS(poolobj.getVmconcurrentcount(),
					vmobj.getDeltasteadystateIOPS(), vmobj.getDeltareadIOpercent());
			tmpclonereadbackendIOPS = storageobj.getCloneReadBackendIOPS(tmpclonereadfrontendIOPS,
					storageobj.getCachereadratio());
			tmpclonewritefrontendIOPS += storageobj.getCloneWriteFrontendIOPS(poolobj.getVmconcurrentcount(),
					vmobj.getDeltasteadystateIOPS(), vmobj.getDeltareadIOpercent());
			tmpclonewritebackendIOPS = storageobj.getCloneWriteBackendIOPS(tmpclonewritefrontendIOPS,
					storageobj.cloneraidtype, storageobj.getCachewriteratio());
			tmpcloneusablecapacity += storageobj.calculateCloneUsableCapacity(vmobj.getFullclonesize(),
					vmobj.getDeltasize(), vmobj.getDisposabledisksize(), vmobj.getLogsize(), vmobj.getSwapsize(),
					vmobj.getVideoswapsize(), poolobj.getVmcount(), storageobj.getOverhead(),
					storageobj.getBlockdeduplicationratio(), aq.getSelectedItem().toString());
			tmpparentusablecapacity += storageobj.calculateParentUsableCapacity(vmobj.getParentsize(),
					vmobj.getParentcount(), storageobj.getOverhead());

		}

		host.setCount(tmphostcount);
		host.setCoremhz(tmpcoremhz / (tabbedpanel_vm.getTabCount() - 2));

		/*
		 * Nutanix Infrastructure
		 */
		if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("nutanix")) {

			/*
			 * Define different overheads based on existence of full clones Add RAM
			 * dedicated to storage virtualization layer (NUTANIX or VSAN)
			 */
			if (tmppooltypefull) {
				host.setMemoryNoTPS(tmphostmemoryNoTPS
						+ (Integer.parseInt(DEFAULTS.getString("hostmemoryreservation.nutanix_full.default").toString())
								/ 1024));
				host.setMemoryTPS(tmphostmemoryTPS
						+ (Integer.parseInt(DEFAULTS.getString("hostmemoryreservation.nutanix_full.default").toString())
								/ 1024));
				tmppooltypefull = false;
			} else {
				host.setMemoryNoTPS(tmphostmemoryNoTPS + (Integer.parseInt(
						DEFAULTS.getString("hostmemoryreservation.nutanix_linked.default").toString()) / 1024));
				host.setMemoryTPS(tmphostmemoryTPS + (Integer.parseInt(
						DEFAULTS.getString("hostmemoryreservation.nutanix_linked.default").toString()) / 1024));
			}

			/*
			 * For NUTANIX single datastore is required.
			 */
			storageobj.datastorecount = 1;

			/*
			 * DATRIUM Infrastructure
			 */
		} else if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("datrium")) {

			/*
			 * CPU sizing guideline
			 */
			host.setCoremhz((int) (host.getCoremhz()
					* (Double.parseDouble((DEFAULTS.getString("vcpureservation.datrium.default").toString())))));

			/*
			 * Single datastore is required.
			 */
			storageobj.datastorecount = 1;

		} else if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("traditional")) {
			/*
			 * Memory sizing guideline
			 */
			host.setMemoryNoTPS(tmphostmemoryNoTPS);
			host.setMemoryTPS(tmphostmemoryTPS);
		}

		host.setLocalswapsize(tmphostlocalswapsize / (tabbedpanel_vm.getTabCount() - 2));

		storageobj.setReplicausablecapacity(Math.round(tmpreplicausablecapacity * 100.0) / 100.0); // Round
																									// double
																									// number
		storageobj.cloneusablecapacity = tmpcloneusablecapacity;
		storageobj.datastoresize = storageobj.getDatastoreSize(storageobj.datastorecount,
				storageobj.cloneusablecapacity, storageobj.getReplicausablecapacity());

		/*
		 * Memory sizing guideline based on local host flash capacity
		 */
		if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("datrium")) {

			double a = Math.ceil(tmphostmemoryNoTPS
					+ (Double.parseDouble(DEFAULTS.getString("hostmemoryreservation.datrium.default").toString())
							/ 1024));
			double ExtraMemoryNoTPS = Math
					.ceil(((((double) storageobj.datastoresize / 1000.0) / host.getCount()) / 2.0) * 2.5);
			host.setMemoryNoTPS((int) (a + ExtraMemoryNoTPS));

			double b = Math.ceil(tmphostmemoryTPS
					+ (Double.parseDouble(DEFAULTS.getString("hostmemoryreservation.datrium.default").toString())
							/ 1024));
			double ExtraMemoryTPS = Math
					.ceil(((((double) storageobj.datastoresize / 1000.0) / host.getCount()) / 2.0) * 2.5);
			host.setMemoryTPS((int) (b + ExtraMemoryTPS));
		}

		storageobj.replicareadfrontendIOPS = tmpreplicareadfrontendIOPS;
		storageobj.clonereadfrontendIOPS = tmpclonereadfrontendIOPS;
		storageobj.clonereadbackendIOPS = tmpclonereadbackendIOPS;
		storageobj.clonewritefrontendIOPS = tmpclonewritefrontendIOPS;
		storageobj.clonewritebackendIOPS = tmpclonewritebackendIOPS;
		storageobj.persistentreadfrontendIOPS = tmppersistentreadfrontendIOPS;
		storageobj.persistentreadbackendIOPS = tmppersistentreadbackendIOPS;
		storageobj.persistentwritefrontendIOPS = tmppersistentwritefrontendIOPS;
		storageobj.persistentwritebackendIOPS = tmppersistentwritebackendIOPS;
		storageobj.persistentusablecapacity = tmppersistentusablecapacity;
		storageobj.setSwappowerstatecapacityreduction(tmpswappowerstatecapacityreduction);
		storageobj.setSuspendusablecapacity(tmpsuspendusablecapacity);
		storageobj.persistentdatastoresize = storageobj.getPersistentDatastoreSize(tmppersistentdatastorecount,
				tmppersistentusablecapacity);
		storageobj.persistentdatastorecount = tmppersistentdatastorecount;
		storageobj.setParentusablecapacity(tmpparentusablecapacity);
		storageobj.setUsablecapacity(storageobj.calculateUsableCapacity(storageobj.getParentusablecapacity(),
				storageobj.getReplicausablecapacity(), tmpcloneusablecapacity, tmppersistentusablecapacity,
				storageobj.getSuspendusablecapacity(), storageobj.getSwappowerstatecapacityreduction()));
		cluster.setCount(cluster.calcCount(host.getCount(), cluster.getSize()));

		/*
		 * Calculate VDI broker requirements
		 */
		view.setVcvmlimit(Integer.parseInt(vcvmlimittxt.getText()));
		view.setVccount(view.calculatevCenterServerCount(tmppoolvmconcurrent, view.getVcvmlimit()));
		if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {
			view.setCscount(view.calculateConnectionServerCount(tmppoolvmconcurrent));
			view.setExternalsessioncount(Integer.parseInt(externalconcurrentsessionstxt.getText()));
			view.setSscount(view.calculateSecurityServerCount(view.getExternalsessioncount()));
			backendserverinfracombo.setEnabled(true);
		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {
			xen.setDdccount(xen.calculateDeliveryControllerCount(tmppoolvmconcurrent));
			backendserverinfracombo.setEnabled(true);
		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {
			view.setCscount(0);
			view.setExternalsessioncount(0);
			view.setSscount(0);
			xen.setDdccount(0);
			backendserverinfracombo.setSelectedIndex(0);
			backendserverinfracombo.setEnabled(false);
		}

		/*
		 * Backend server infrastructure calculations
		 */
		if (backendserverinfracombo.getSelectedIndex() == 1) {
			double d;
			int i;
			if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {
				/*
				 * Storage calculations
				 */
				d = storageobj.getUsablecapacity()
						+ view.calculateViewStorage(view.getCscount(), view.getSscount(), view.getVccount());
				d = (double) Math.round(d * 100) / 100; // Round to 1 decimals
				storageobj.setUsablecapacity(d);

				/*
				 * Memory calculations TPS is not applied to memory calculations for backend
				 * infrastructure.
				 */
				i = (int) Math.round(
						(view.calculateViewMemory(view.getCscount(), view.getSscount(), view.getVccount()) / 1024)
								/ (double) host.getCount());
				host.setMemoryNoTPS(host.getMemoryNoTPS() + i);
				host.setMemoryTPS(host.getMemoryTPS() + i);

				/*
				 * vCPU calculations
				 */
				if (view.calculateViewvCPU(view.getCscount(), view.getSscount(), view.getVccount()) < host
						.getCorescount()) {
					host.setCount(host.getCount() + 1);
				} else {
					host.setCount((host.getCount()
							+ view.calculateViewvCPU(view.getCscount(), view.getSscount(), view.getVccount())
									/ host.getCorescount()));
				}

			} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {

				/*
				 * Storage calculations
				 */
				d = storageobj.getUsablecapacity() + xen.calculateXenStorage(xen.getDdccount(), view.getVccount());
				d = (double) Math.round(d * 100) / 100; // Round to 1 decimals
				storageobj.setUsablecapacity(d);

				/*
				 * Memory calculations TPS is not applied to memory calculations for backend
				 * infrastructure.
				 */
				i = (int) Math.round((xen.calculateXenMemory(xen.getDdccount(), view.getVccount()) / 1024)
						/ (double) host.getCount());
				host.setMemoryNoTPS(host.getMemoryNoTPS() + i);
				host.setMemoryTPS(host.getMemoryTPS() + i);

				/*
				 * vCPU calculations
				 */
				if (xen.calculateXenvCPU(xen.getDdccount(), view.getVccount()) < host.getCorescount()) {
					host.setCount(host.getCount() + 1);
				} else {
					host.setCount((host.getCount()
							+ xen.calculateXenvCPU(xen.getDdccount(), view.getVccount()) / host.getCorescount()));
				}
			}

		}

		/* Update results UI if boolen true */
		if (updateResultsUI == true) {
			updateFrame();
		}
		;

	}

	/**
	 * Method updateFrame.
	 */
	public static void updateFrame() {

		/*
		 * Create Hash Map will components from all tabs in tabbedpanel_results
		 */
		componentMap = new HashMap<String, Component>();
		int totalTabs = tabbedpanel_results.getTabCount();
		for (int i = 0; i < totalTabs; i++) {
			JPanel c = (JPanel) tabbedpanel_results.getComponentAt(i);
			Component[] components = c.getComponents();
			for (int n = 0; n < components.length; ++n) {
				if (components[n].getClass() == JLabel.class & components[n].getName() != null) {
					componentMap.put(components[n].getName(), components[n]);
				}
			}
		}

		/*
		 * Update host results
		 */
		JLabel aa = (JLabel) componentMap.get("numberofhostslabelvalue");
		JLabel ab = (JLabel) componentMap.get("coresperhostlabelvalue");
		JLabel ac = (JLabel) componentMap.get("hostcoremhzlabelvalue");
		JLabel ad = (JLabel) componentMap.get("hostvmcountlabelvalue");
		JLabel ag = (JLabel) componentMap.get("hostmemorytotallabelvalue");
		JLabel ah = (JLabel) componentMap.get("hostmemorytotalovercommitlabelvalue");
		JLabel ai = (JLabel) componentMap.get("hostlocalusablecapacitylabelvalue");
		JLabel aj = (JLabel) componentMap.get("vcclusterslabelvalue");

		aa.setText(String.valueOf(host.getCount()));
		ab.setText(String.valueOf(host.getCorescount()));
		ac.setText(String.valueOf(host.getCoremhz()));
		ad.setText(String.valueOf(host.getVmcount()));
		ag.setText(String.valueOf(host.getMemoryNoTPS()));
		ah.setText(String.valueOf(host.getMemoryTPS()));
		ai.setText(String.valueOf(host.getLocalswapsize()));
		aj.setText(String.valueOf(cluster.getCount()));

		/*
		 * Update Storage Results
		 */
		if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {
			JLabel ca = (JLabel) componentMap.get("storageparentusablecapacitylabelvalue");
			JLabel cb = (JLabel) componentMap.get("storagereplicausablecapacitylabelvalue");
			JLabel cc = (JLabel) componentMap.get("storagepersistentusablecapacitylabelvalue");
			JLabel cd = (JLabel) componentMap.get("storagecloneusablecapacitylabelvalue");
			JLabel ce = (JLabel) componentMap.get("storageusablecapacitylabelvalue");
			JLabel cf = (JLabel) componentMap.get("storagefrontendIOPSlabelvalue");
			JLabel cg = (JLabel) componentMap.get("storagebackendIOPSlabelvalue");
			JLabel ch = (JLabel) componentMap.get("storagereplicafrontendIOPSlabelvalue");
			JLabel ci = (JLabel) componentMap.get("storagereplicafrontendIOPSlabelvalue");
			JLabel cj = (JLabel) componentMap.get("storagereplicabackendIOPSlabelvalue");
			JLabel ck = (JLabel) componentMap.get("storageclonefrontendIOPSlabelvalue");
			JLabel cl = (JLabel) componentMap.get("storageclonebackendIOPSlabelvalue");
			JLabel cm = (JLabel) componentMap.get("storagepersistentfrontendIOPSlabelvalue");
			JLabel cn = (JLabel) componentMap.get("storagepersistentbackendIOPSlabelvalue");
			JLabel co = (JLabel) componentMap.get("storagepersistentdatastorecountlabelvalue");
			JLabel cp = (JLabel) componentMap.get("storagepersistentdatastoresizelabelvalue");
			JLabel cq = (JLabel) componentMap.get("storagedatastorecountlabelvalue");
			JLabel cr = (JLabel) componentMap.get("storagedatastoresizelabelvalue");
			ca.setText(String.valueOf(storageobj.getParentusablecapacity()));
			cb.setText(String.valueOf(storageobj.getReplicausablecapacity()));
			cc.setText(storageobj.getPersistentusablecapacity().toString());
			cd.setText(storageobj.cloneusablecapacity.toString());
			ce.setText(String.valueOf(storageobj.getUsablecapacity()));
			cf.setText(String.valueOf(storageobj.getFrontendIOPS()));
			cg.setText(String.valueOf(storageobj.getBackendIOPS()));
			ch.setText(storageobj.replicareadfrontendIOPS.toString());
			ci.setText(storageobj.replicareadfrontendIOPS.toString());
			cj.setText(storageobj.getReplicaBackEndIOPS().toString());
			ck.setText(storageobj.getCloneFrontendIOPS().toString());
			cl.setText(storageobj.getCloneBackendIOPS().toString());
			cm.setText(storageobj.getPersistentFrondendIOPS().toString());
			cn.setText(storageobj.getPersistentBackendIOPS().toString());
			co.setText(String.valueOf(storageobj.persistentdatastorecount));
			cp.setText(String.valueOf(storageobj.persistentdatastoresize));
			cq.setText(String.valueOf(storageobj.datastorecount));
			cr.setText(String.valueOf(storageobj.datastoresize));

		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {
			JLabel ea = (JLabel) componentMap.get("storageparentusablecapacitylabelvalue_xen");
			JLabel eb = (JLabel) componentMap.get("storagereplicausablecapacitylabelvalue_xen");
			JLabel ec = (JLabel) componentMap.get("storagecloneusablecapacitylabelvalue_xen");
			JLabel ed = (JLabel) componentMap.get("storageclonefrontendIOPSlabelvalue_xen");
			JLabel ee = (JLabel) componentMap.get("storageclonebackendIOPSlabelvalue_xen");
			JLabel ef = (JLabel) componentMap.get("storagedatastorecountlabelvalue_xen");
			JLabel eg = (JLabel) componentMap.get("storagedatastoresizelabelvalue_xen");
			JLabel eh = (JLabel) componentMap.get("storageusablecapacitylabelvalue_xen");
			JLabel ei = (JLabel) componentMap.get("storagefrontendIOPSlabelvalue_xen");
			JLabel ej = (JLabel) componentMap.get("storagebackendIOPSlabelvalue_xen");
			JLabel ek = (JLabel) componentMap.get("storagepersistentusablecapacitylabelvalue_xen");
			JLabel el = (JLabel) componentMap.get("storagepersistentfrontendIOPSlabelvalue_xen");
			JLabel em = (JLabel) componentMap.get("storagepersistentbackendIOPSlabelvalue_xen");
			JLabel en = (JLabel) componentMap.get("storagepersistentdatastorecountlabelvalue_xen");
			JLabel eo = (JLabel) componentMap.get("storagepersistentdatastoresizelabelvalue_xen");
			ea.setText(String.valueOf(storageobj.getParentusablecapacity()));
			eb.setText(String.valueOf(storageobj.getReplicausablecapacity()));
			ec.setText(storageobj.cloneusablecapacity.toString());
			ed.setText(String.valueOf(storageobj.getCloneFrontendIOPS()));
			ee.setText(storageobj.getCloneBackendIOPS().toString());
			ef.setText(String.valueOf(storageobj.datastorecount));
			eg.setText(String.valueOf(storageobj.datastoresize));
			eh.setText(String.valueOf(storageobj.getUsablecapacity()));
			ei.setText(String.valueOf(storageobj.getFrontendIOPS()));
			ej.setText(String.valueOf(storageobj.getBackendIOPS()));
			ek.setText(storageobj.getPersistentusablecapacity().toString());
			el.setText(storageobj.getPersistentFrondendIOPS().toString());
			em.setText(storageobj.getPersistentBackendIOPS().toString());
			en.setText(String.valueOf(storageobj.persistentdatastorecount));
			eo.setText(String.valueOf(storageobj.persistentdatastoresize));

		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {
			JLabel ca = (JLabel) componentMap.get("storageparentusablecapacitylabelvalue");
			JLabel cb = (JLabel) componentMap.get("storagereplicausablecapacitylabelvalue");
			JLabel cc = (JLabel) componentMap.get("storagepersistentusablecapacitylabelvalue");
			JLabel cd = (JLabel) componentMap.get("storagecloneusablecapacitylabelvalue");
			JLabel ce = (JLabel) componentMap.get("storageusablecapacitylabelvalue");
			JLabel cf = (JLabel) componentMap.get("storagefrontendIOPSlabelvalue");
			JLabel cg = (JLabel) componentMap.get("storagebackendIOPSlabelvalue");
			JLabel ch = (JLabel) componentMap.get("storagereplicafrontendIOPSlabelvalue");
			JLabel ci = (JLabel) componentMap.get("storagereplicafrontendIOPSlabelvalue");
			JLabel cj = (JLabel) componentMap.get("storagereplicabackendIOPSlabelvalue");
			JLabel ck = (JLabel) componentMap.get("storageclonefrontendIOPSlabelvalue");
			JLabel cl = (JLabel) componentMap.get("storageclonebackendIOPSlabelvalue");
			JLabel cm = (JLabel) componentMap.get("storagepersistentfrontendIOPSlabelvalue");
			JLabel cn = (JLabel) componentMap.get("storagepersistentbackendIOPSlabelvalue");
			JLabel co = (JLabel) componentMap.get("storagepersistentdatastorecountlabelvalue");
			JLabel cp = (JLabel) componentMap.get("storagepersistentdatastoresizelabelvalue");
			JLabel cq = (JLabel) componentMap.get("storagedatastorecountlabelvalue");
			JLabel cr = (JLabel) componentMap.get("storagedatastoresizelabelvalue");
			ca.setText(String.valueOf(storageobj.getParentusablecapacity()));
			cb.setText(String.valueOf(storageobj.getReplicausablecapacity()));
			cc.setText(storageobj.getPersistentusablecapacity().toString());
			cd.setText(storageobj.cloneusablecapacity.toString());
			ce.setText(String.valueOf(storageobj.getUsablecapacity()));
			cf.setText(String.valueOf(storageobj.getFrontendIOPS()));
			cg.setText(String.valueOf(storageobj.getBackendIOPS()));
			ch.setText(storageobj.replicareadfrontendIOPS.toString());
			ci.setText(storageobj.replicareadfrontendIOPS.toString());
			cj.setText(storageobj.getReplicaBackEndIOPS().toString());
			ck.setText(storageobj.getCloneFrontendIOPS().toString());
			cl.setText(storageobj.getCloneBackendIOPS().toString());
			cm.setText(storageobj.getPersistentFrondendIOPS().toString());
			cn.setText(storageobj.getPersistentBackendIOPS().toString());
			co.setText(String.valueOf(storageobj.persistentdatastorecount));
			cp.setText(String.valueOf(storageobj.persistentdatastoresize));
			cq.setText(String.valueOf(storageobj.datastorecount));
			cr.setText(String.valueOf(storageobj.datastoresize));
		}

		/*
		 * Update Storage Detail Results
		 */
		JLabel da = (JLabel) componentMap.get("storagedisposableusablecapacitylabelvalue");
		JLabel db = (JLabel) componentMap.get("storageswapusablecapacitylabelvalue");
		JLabel dc = (JLabel) componentMap.get("storageswappowerstatecapacityreductionlabelvalue");
		JLabel dd = (JLabel) componentMap.get("storagesuspendusablecapacitylabelvalue");

		da.setText(String.valueOf(storageobj.disposableusablecapacity));
		db.setText(String.valueOf(storageobj.getLocalswapsizetotal()));
		dc.setText(String.valueOf(storageobj.getSwappowerstatecapacityreduction()));
		dd.setText(String.valueOf(storageobj.getSuspendusablecapacity()));

		/*
		 * Update Infrastructure Results
		 */
		if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {

			JLabel fa = (JLabel) componentMap.get("vccountlabelvalue");
			JLabel fb = (JLabel) componentMap.get("viewconnectionservercountlabelvalue");
			JLabel fc = (JLabel) componentMap.get("viewsecurityservercountlabelvalue");
			fa.setText(String.valueOf(view.getVccount()));
			fb.setText(String.valueOf(view.getCscount()));
			fc.setText(String.valueOf(view.getSscount()));

		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {

			JLabel ga = (JLabel) componentMap.get("xenvccountlabelvalue");
			JLabel gb = (JLabel) componentMap.get("xendeliverycontrollerlabelvalue");
			ga.setText(String.valueOf(view.getVccount()));
			gb.setText(String.valueOf(xen.getDdccount()));

		} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {

			JLabel fa = (JLabel) componentMap.get("vccountlabelvalue");
			JLabel fb = (JLabel) componentMap.get("viewconnectionservercountlabelvalue");
			JLabel fc = (JLabel) componentMap.get("viewsecurityservercountlabelvalue");
			fa.setText(String.valueOf(view.getVccount()));
			fb.setText(String.valueOf(view.getCscount()));
			fc.setText(String.valueOf(view.getSscount()));

		}

		/*
		 * Validations
		 */
		if (Integer.parseInt(ac.getText()) >= Integer.parseInt(DEFAULTS.getString("hostvcpumhztotal.maximum"))) {
			JOptionPane.showMessageDialog(null, MESSAGES.getString("warningcpuutilizationintel.validation"), "Warning",
					JOptionPane.WARNING_MESSAGE);

		}
	}

	/*
	 * Define MAC/Windows/Linux variables for MigLayout
	 */
	{

		if (functions.isMac()) {
			comboHeight = "h 24!";
			TextfieldHeight = "h 20!";
			ButtonHeight = "h 35!";
		} else if (functions.isWindows()) {
			comboHeight = "h 22!";
			TextfieldHeight = "h 21!";
			ButtonHeight = "h 31!";
		} else if (functions.isLinux()) {
			comboHeight = "h 24!";
			TextfieldHeight = "h 20!";
			ButtonHeight = "h 35!";
		}
	}
	/*
	 * Panels
	 */
	/**
	 * Field panel_general.
	 */
	JPanel panel_general = new JPanel();
	/**
	 * Field panel_host.
	 */
	JPanel panel_host = new JPanel();
	/**
	 * Field panel_storage.
	 */
	JPanel panel_storage = new JPanel();
	/**
	 * Field panel_buttons.
	 */
	JPanel panel_buttons = new JPanel();
	/*
	 * General Panel
	 */
	/**
	 * Field panel_general_desc.
	 */
	JLabel panel_general_desc = new JLabel("General", JLabel.LEFT);
	/**
	 * Field externalconcurrentsessionstxtlabel.
	 */
	JLabel externalconcurrentsessionstxtlabel = new JLabel(MESSAGES.getString("externalconcurrentsessions.view"),
			JLabel.RIGHT);
	/**
	 * Field vchacombolabel.
	 */
	JLabel vchacombolabel = new JLabel(MESSAGES.getString("vchacombo.panel"), JLabel.LEFT);
	/**
	 * Field vcclustersizecombolabel.
	 */
	JLabel vcclustersizecombolabel = new JLabel(MESSAGES.getString("vcclustersizecombo.panel"), JLabel.RIGHT);
	/**
	 * Field backendserverinfracombolabel.
	 */
	JLabel backendserverinfracombolabel = new JLabel(MESSAGES.getString("backendserverinfracombo.panel"), JLabel.LEFT);
	/**
	 * Field vcvmlimittxtlabel.
	 */
	JLabel vcvmlimittxtlabel = new JLabel(MESSAGES.getString("vcvmlimittxt.panel"), JLabel.LEFT);
	/*
	 * host Panel
	 */
	/**
	 * Field panel_host_desc.
	 */
	JLabel panel_host_desc = new JLabel("Host", JLabel.LEFT);
	/**
	 * Field hostsocketslabel.
	 */
	JLabel hostsocketslabel = new JLabel(MESSAGES.getString("hostsockets.panel"), JLabel.RIGHT);
	/**
	 * Field coressocketslabel.
	 */
	JLabel coressocketslabel = new JLabel(MESSAGES.getString("coressockets.panel"), JLabel.RIGHT);
	/**
	 * Field hostvmcorelabel.
	 */
	// JLabel hostvmcorelabel = new
	// JLabel(MESSAGES.getString("hostvmcore.panel"),
	// JLabel.RIGHT);
	/**
	 * Field hostvmcorecombovalues.
	 */
	// String[] hostvmcorecombovalues = { "1", "2", "3", "4", "5", "6", "7",
	// "8",
	// "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
	// "20" };
	/**
	 * Field hostvmcorecombo.
	 */
	// JComboBox<String> hostvmcorecombo = new JComboBox<>(hostvmcorecombovalues);
	/**
	 * Field hostsharedmemorylabel.
	 */
	JLabel hostsharedmemorylabel = new JLabel(MESSAGES.getString("hostsharedmemory.panel"), JLabel.RIGHT);
	/**
	 * Field hostmemoryoverheadlabel.
	 */
	JLabel hostmemoryoverheadlabel = new JLabel(MESSAGES.getString("hostmemoryoverhead.panel"), JLabel.RIGHT);
	/**
	 * Field hostusedmemorylabel.
	 */
	JLabel hostusedmemorylabel = new JLabel(MESSAGES.getString("hostusedmemory.panel"), JLabel.RIGHT);
	/**
	 * Field hostcbrclabel.
	 */
	JLabel hostcbrclabel = new JLabel(MESSAGES.getString("hostcbrc.panel"), JLabel.RIGHT);
	/**
	 * Field hostinframodellabel.
	 */
	JLabel hostinframodellabel = new JLabel(MESSAGES.getString("hostinframodel.panel"), JLabel.RIGHT);
	/**
	 * Field hostlocalvmswapcombolabel.
	 */
	JLabel hostlocalvmswapcombolabel = new JLabel(MESSAGES.getString("hostlocalvmswapcombo.panel"), JLabel.RIGHT);
	/*
	 * Storage Panel
	 */
	/**
	 * Field panel_storage_desc.
	 */
	JLabel panel_storage_desc = new JLabel("Storage", JLabel.LEFT);
	/**
	 * Field desktopbrokertypecombolabel.
	 */
	JLabel desktopbrokertypecombolabel = new JLabel(vdicalc.MESSAGES.getString("desktopbrokertypecombo.panel"));
	/**
	 * Field vmthinsizeparentvmtxtlabel.
	 */
	JLabel vmthinsizeparentvmtxtlabel = new JLabel(MESSAGES.getString("vmthinsizeparentvm.view"), JLabel.LEFT);
	/**
	 * Field vmthinsizeparentvmtxt.
	 */
	JTextField vmthinsizeparentvmtxt = new JTextField(DEFAULTS.getString("vmthinsizeparentvmtxt.default"), 4);
	/**
	 * Field storagedatastorevmtxtlabel.
	 */
	JLabel storagedatastorevmtxtlabel = new JLabel(vdicalc.MESSAGES.getString("storagedatastorevmtxt.panel"),
			JLabel.LEFT);
	/**
	 * Field persistentdisksperdatastorelabel.
	 */
	JLabel persistentdisksperdatastorelabel = new JLabel(MESSAGES.getString("persistentdisksperdatastore.view"),
			JLabel.LEFT);
	/**
	 * Field storageoverheadcombolabel.
	 */
	JLabel storageoverheadcombolabel = new JLabel(MESSAGES.getString("storageoverheadcombo.panel"), JLabel.LEFT);
	/**
	 * Field storagetieringcombolabel.
	 */
	JLabel storagetieringcombolabel = new JLabel(MESSAGES.getString("storagetieringcombo.panel"), JLabel.LEFT);
	/**
	 * Field storageblockdeduplicationratiocombolabel.
	 */
	JLabel storageblockdeduplicationratiocombolabel = new JLabel(
			MESSAGES.getString("storageblockdeduplicationratiocombo.panel"), JLabel.LEFT);
	/**
	 * Field storagecachereadratiocombolabel.
	 */
	JLabel storagecachereadratiocombolabel = new JLabel(MESSAGES.getString("storagecachereadratiocombo.panel"),
			JLabel.LEFT);
	/**
	 * Field storagecachewriteratiocombolabel.
	 */
	JLabel storagecachewriteratiocombolabel = new JLabel(MESSAGES.getString("storagecachewriteratiocombo.panel"),
			JLabel.LEFT);

	/**
	 * Field vmreplicaconcurrentbootcounttxtlabel.
	 */
	JLabel vmreplicaconcurrentbootcounttxtlabel = new JLabel(
			MESSAGES.getString("vmreplicaconcurrentbootcounttxt.panel"), JLabel.LEFT);
	/**
	 * Field storagedeltaraidtypecombolabel.
	 */
	JLabel storagedeltaraidtypecombolabel = new JLabel(MESSAGES.getString("storagedeltaraidtypecombo.panel"),
			JLabel.LEFT);
	/**
	 * Field storagepersistentraidtypecombolabel.
	 */
	JLabel storagepersistentraidtypecombolabel = new JLabel(MESSAGES.getString("storagepersistentraidtypecombo.panel"),
			JLabel.LEFT);
	/*
	 * Buttons
	 */
	/**
	 * Field update.
	 */
	JButton update = new JButton("Update");
	/**
	 * Field save.
	 */
	JButton save = new JButton("Save");
	/**
	 * Field load.
	 */
	JButton load = new JButton("Load");

	/**
	 * Field print.
	 */
	JButton print = new JButton("Print");

	/**
	 * Field about.
	 */
	JButton about = new JButton("About");

	/**
	 * Field guide.
	 */
	JButton guide = new JButton("Guide");

	/**
	 * @param args
	 * @return
	 */
	public vdicalc(String[] args) {
		super("VDI Calculator by Andre Leibovici");
		if (functions.isMac()) {
			this.setSize(892, 820);
		} else if (functions.isWindows()) {
			this.setSize(892, 820);
		} else if (functions.isLinux()) {
			this.setSize(892, 780);
		}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.toFront();
		this.requestFocus();
		this.repaint();

		MigLayout layout = new MigLayout("gap 0.1! 0.1!, ins 0", "", "");
		setLayout(layout);
		Border raisedbevel;
		raisedbevel = BorderFactory.createRaisedBevelBorder();

		/*
		 * Build UI panels
		 */
		add(tabbedpanel_vm, "cell 0 1, wmin 893");
		tabbedpanel_vm.setName("tabbedpanel_vm");
		add(panel_host, "cell 0 2, wmin 893");
		panel_host.setName("panel_host");
		add(panel_storage, "cell 0 3, wmin 893");
		panel_storage.setName("panel_storage");
		add(panel_general, "cell 0 4, wmin 893");
		panel_general.setName("panel_general");
		add(tabbedpanel_results, "cell 0 7, wmin 893");
		tabbedpanel_results.setName("tabbedpanel_results");
		add(panel_buttons, "cell 0 8");
		panel_buttons.setName("panel_buttons");

		/*
		 * Build Result Panels
		 */
		new custompanels();
		final JPanel panel_results_host = custompanels.createHostResultPanel();
		final JPanel panel_results_storage_view = custompanels.createStorageViewResultPanel();
		final JPanel panel_results_storage_detail = custompanels.createStorageDetailResultPanel();
		final JPanel panel_results_infra_view = custompanels.createViewInfraResultPanel();
		final JPanel panel_results_storage_xen = custompanels.createStorageXenResultPanel();
		final JPanel panel_results_infra_xen = custompanels.createXenInfraResultPanel();

		tabbedpanel_results.add(panel_results_host, "host");
		tabbedpanel_results.add(panel_results_storage_view, "storage");
		tabbedpanel_results.add(panel_results_storage_detail, "storage detail");
		tabbedpanel_results.add(panel_results_infra_view, "view");

		/*
		 * Tabbed Virtual Machine
		 */
		JLabel tabbedpanel_vm_minus = new JLabel("", SwingConstants.LEFT);
		tabbedpanel_vm_minus.setName("-");
		JLabel tabbedpanel_vm_plus = new JLabel("", SwingConstants.LEFT);
		tabbedpanel_vm_plus.setName("+");
		tabbedpanel_vm.addTab("-", tabbedpanel_vm_minus);
		String tabName = "VM1";
		JPanel newTab = new custompanels().createVMPanel(tabName);
		tabbedpanel_vm.addTab(MESSAGES.getString("tabbedpaneldesktoptype.panel") + " 1", newTab);
		tabbedpanel_vm.addTab("+", tabbedpanel_vm_plus);
		tabbedpanel_vm.setSelectedIndex(1);
		tabbedpanel_vm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabbedpanel_vm.getSelectedComponent() instanceof JLabel) {
					if (tabbedpanel_vm.getSelectedComponent().getName().equalsIgnoreCase("+")) {
						if (tabbedpanel_vm.getTabCount() < 7) {
							int count = tabbedpanel_vm.getTabCount();
							String tabName = "VM" + (count - 1);
							JPanel newTab = new custompanels().createVMPanel(tabName);
							tabbedpanel_vm.add(newTab,
									MESSAGES.getString("tabbedpaneldesktoptype.panel") + " " + (count - 1), count - 1);
							tabbedpanel_vm.setSelectedComponent(newTab);

							/*
							 * Change JTabPanel format to broker selection
							 */
							if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")) {
								functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "view");
							} else if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")) {
								functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "xen");
							} else if (desktopbrokertypecombo.getSelectedItem().toString()
									.equalsIgnoreCase("workspot")) {
								functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "workspot");
							}

						} else {
							tabbedpanel_vm.setSelectedIndex(tabbedpanel_vm.getTabCount() - 2);
						}
					} else if (tabbedpanel_vm.getSelectedComponent().getName().equalsIgnoreCase("-")) {
						if (tabbedpanel_vm.getTabCount() > 3) {
							int count = tabbedpanel_vm.getTabCount();
							tabbedpanel_vm.removeTabAt(count - 2);
							tabbedpanel_vm.setSelectedIndex(tabbedpanel_vm.getTabCount() - 2);
						} else {
							tabbedpanel_vm.setSelectedIndex(1);
						}
					}
				}
			}
		});

		/*
		 * Host
		 */
		MigLayout layout3 = new MigLayout("ins 3, gap 10px -4px", "", "");
		panel_host.setLayout(layout3);
		panel_host.setBorder(raisedbevel);
		panel_host.setBackground(Color.LIGHT_GRAY);

		/*
		 * Host Column 0
		 */
		panel_host.add(panel_host_desc, "pos 0 0");
		if (functions.isLinux()) {
			panel_host_desc.setFont(new Font("Serif", Font.PLAIN, 10));
		} else {
			panel_host_desc.setFont(new Font("Serif", Font.PLAIN, 12));
		}
		panel_host_desc.setForeground(Color.DARK_GRAY);
		panel_host.add(hostsocketslabel, "pos 18 10");
		panel_host.add(hostsocketscombo, "pos 200 7, w 70!, " + comboHeight);
		hostsocketscombo.setSelectedIndex(1);
		hostsocketscombo.setToolTipText(MESSAGES.getString("hostsockets.tooltip"));
		hostsocketscombo.setName("hostsocketscombo");
		panel_host.add(coressocketslabel, "pos 18 33");
		panel_host.add(coresocketscombo, "pos 200 31, w 70!,  " + comboHeight);
		coresocketscombo.setSelectedIndex(5);
		coresocketscombo.setToolTipText(MESSAGES.getString("coresockets.tooltip"));
		coresocketscombo.setName("coresocketscombo");

		/*
		 * Host Column 1
		 */
		panel_host.add(hostsharedmemorylabel, "pos 297 10");
		panel_host.add(hostsharedmemorycombo, "pos 476 7, w 70!, " + comboHeight);
		hostsharedmemorycombo.setSelectedIndex(0);
		hostsharedmemorycombo.setToolTipText(MESSAGES.getString("hostsharedmemory.tooltip"));
		hostsharedmemorycombo.setName("hostsharedmemorycombo");
		hostsharedmemorycombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();
				if (state == ItemEvent.SELECTED) {
					if (Integer.parseInt(event.getItem().toString()) > 0) {
						JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("warningtps.validation"), "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panel_host.add(hostusedmemorylabel, "pos 297 33");
		if (functions.isMac() || functions.isLinux()) {
			panel_host.add(hostusedmemorycombo, "pos 476 31, w 74!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_host.add(hostusedmemorycombo, "pos 476 31, w 70!, " + comboHeight);
		}
		hostusedmemorycombo.setSelectedIndex(4);
		hostusedmemorycombo.setToolTipText(MESSAGES.getString("hostusedmemory.tooltip"));
		hostusedmemorycombo.setName("hostusedmemorycombo");
		panel_host.add(hostmemoryoverheadlabel, "pos 297 57");
		panel_host.add(hostmemoryoverheadtxt, "pos 476 57, w 70!, " + TextfieldHeight);
		hostmemoryoverheadtxt.setToolTipText(MESSAGES.getString("hostmemoryoverhead.tooltip"));
		hostmemoryoverheadtxt.setName("hostmemoryoverheadtxt");
		hostmemoryoverheadtxt.addFocusListener(this);
		hostmemoryoverheadtxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) { // $codepro.audit.disable
													// emptyMethod

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

			}

		});

		/*
		 * Host Column 2
		 */
		panel_host.add(hostcbrclabel, "pos 580 10");
		if (functions.isMac() || functions.isLinux()) {
			panel_host.add(hostcbrccombo, "pos 760 7, w 82!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_host.add(hostcbrccombo, "pos 760 7, w 70!, " + comboHeight);
		}
		hostcbrccombo.setSelectedIndex(3);
		hostcbrccombo.setToolTipText(MESSAGES.getString("hostcbrc.tooltip"));
		hostcbrccombo.setName("hostcbrccombo");
		panel_host.add(hostinframodellabel, "pos 580 33");
		if (functions.isMac() || functions.isLinux()) {
			panel_host.add(hostinframodelcombo, "pos 760 31, w 118!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_host.add(hostinframodelcombo, "pos 760 31, w 90!, " + comboHeight);
		}
		hostinframodelcombo.setSelectedIndex(0);
		hostinframodelcombo.setToolTipText(MESSAGES.getString("hostinframodel.tooltip"));
		hostinframodelcombo.setName("hostinframodelcombo");
		hostinframodelcombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();
				if (state == ItemEvent.SELECTED) {
					if (event.getItem().toString().equalsIgnoreCase("traditional")) {
						storagedatastorevmtxt.setText(DEFAULTS.getString("storagedatastorevmtxt.maximum"));
						storagedatastorevmtxt.setEnabled(true);
						persistentdisksperdatastoretxt
								.setText(DEFAULTS.getString("persistentdisksperdatastoretxt.default"));
						persistentdisksperdatastoretxt.setEnabled(true);
						storagetieringcombo.setEnabled(true);
						storagetieringcombo.setSelectedIndex(0);
						storagepersistentraidtypecombo.setSelectedIndex(1);
						storagepersistentraidtypecombo.setEnabled(true);
						hostlocalvmswapcombo.setSelectedIndex(1);
						hostlocalvmswapcombo.setEnabled(true);
						storageblockdeduplicationratiocombo.setSelectedIndex(0);
						storageblockdeduplicationratiocombo.setEnabled(true);
						storagecachereadratiocombo.setSelectedIndex(0);
						storagecachereadratiocombo.setEnabled(true);
						storagecachewriteratiocombo.setSelectedIndex(0);
						storagecachewriteratiocombo.setEnabled(true);
						storagedeltaraidtypecombo.setSelectedIndex(1);
						storagedeltaraidtypecombo.setEnabled(true);
						desktopbrokertypecombo.setEnabled(true);
						hostcbrccombo.setSelectedIndex(3);
						hostcbrccombo.setEnabled(true);
						vcclustersizecombo.setSelectedIndex(15);
						vcclustersizecombo.setEnabled(true);
						hostsocketscombo.setEnabled(true);
						coresocketscombo.setEnabled(true);
						updateCalculation(true);

					} else if (event.getItem().toString().equalsIgnoreCase("nutanix")) {

						if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")
								|| desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")
								|| desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {

							storagedatastorevmtxt.setText(DEFAULTS.getString("storagedatastorevmtxt.nutanix.maximum"));
							storagedatastorevmtxt.setEnabled(false);
							persistentdisksperdatastoretxt
									.setText(DEFAULTS.getString("persistentdisksperdatastoretxt.nutanix.maximum"));
							persistentdisksperdatastoretxt.setEnabled(false);
							storagetieringcombo.setEnabled(false);
							storagetieringcombo.setSelectedIndex(1);
							storagepersistentraidtypecombo.setSelectedIndex(0);
							storagepersistentraidtypecombo.setEnabled(false);
							hostlocalvmswapcombo.setSelectedIndex(1);
							hostlocalvmswapcombo.setEnabled(false);
							storageblockdeduplicationratiocombo.setSelectedIndex(8);
							storageblockdeduplicationratiocombo.setEnabled(false);
							storagecachereadratiocombo.setSelectedIndex(20);
							storagecachereadratiocombo.setEnabled(false);
							storagecachewriteratiocombo.setSelectedIndex(0);
							storagecachewriteratiocombo.setEnabled(false);
							storagedeltaraidtypecombo.setSelectedIndex(0);
							storagedeltaraidtypecombo.setEnabled(false);
							hostcbrccombo.setSelectedIndex(0);
							hostcbrccombo.setEnabled(false);
							vcclustersizecombo.setSelectedIndex(15);
							vcclustersizecombo.setEnabled(true);
							updateCalculation(true);
						} else {
							JOptionPane.showMessageDialog(rootPane,
									MESSAGES.getString("warningconvergedinfraxen.message"), "Converged Infrastructure",
									JOptionPane.WARNING_MESSAGE, null);
							hostinframodelcombo.setSelectedIndex(0);
						}
					} else if (event.getItem().toString().equalsIgnoreCase("datrium")) {

						if (desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("view")
								|| desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("xen")
								|| desktopbrokertypecombo.getSelectedItem().toString().equalsIgnoreCase("workspot")) {

							storagedatastorevmtxt.setText(DEFAULTS.getString("storagedatastorevmtxt.datrium.maximum"));
							storagedatastorevmtxt.setEnabled(false);
							persistentdisksperdatastoretxt
									.setText(DEFAULTS.getString("persistentdisksperdatastoretxt.datrium.maximum"));
							persistentdisksperdatastoretxt.setEnabled(false);
							storagetieringcombo.setEnabled(false);
							storagetieringcombo.setSelectedIndex(1);
							storagepersistentraidtypecombo.setSelectedIndex(0);
							storagepersistentraidtypecombo.setEnabled(false);
							hostlocalvmswapcombo.setSelectedIndex(1);
							hostlocalvmswapcombo.setEnabled(false);
							storageblockdeduplicationratiocombo.setSelectedIndex(8);
							storageblockdeduplicationratiocombo.setEnabled(false);
							storagecachereadratiocombo.setSelectedIndex(20);
							storagecachereadratiocombo.setEnabled(false);
							storagecachewriteratiocombo.setSelectedIndex(0);
							storagecachewriteratiocombo.setEnabled(false);
							storagedeltaraidtypecombo.setSelectedIndex(0);
							storagedeltaraidtypecombo.setEnabled(false);
							storageoverheadcombo.setSelectedIndex(0);
							hostcbrccombo.setSelectedIndex(0);
							hostcbrccombo.setEnabled(false);
							vcclustersizecombo.setSelectedIndex(31);
							vcclustersizecombo.setEnabled(true);
							updateCalculation(true);
						} else {
							JOptionPane.showMessageDialog(rootPane,
									MESSAGES.getString("warningconvergedinfraxen.message"), "Converged Infrastructure",
									JOptionPane.WARNING_MESSAGE, null);
							hostinframodelcombo.setSelectedIndex(0);
						}
					}
				}
			}
		});

		/*
		 * Storage
		 */
		MigLayout layout4 = new MigLayout("ins 3, gap 10px -4px", "", "");
		panel_storage.setLayout(layout4);
		panel_storage.setBorder(raisedbevel);
		panel_storage.setBackground(Color.LIGHT_GRAY);
		panel_storage.setSize(500, 1100);

		/*
		 * Storage Column 0
		 */
		panel_storage.add(panel_storage_desc, "pos 0 0");
		if (functions.isLinux()) {
			panel_storage_desc.setFont(new Font("Serif", Font.PLAIN, 10));
		} else {
			panel_storage_desc.setFont(new Font("Serif", Font.PLAIN, 12));
		}
		panel_storage_desc.setForeground(Color.DARK_GRAY);
		panel_storage.add(desktopbrokertypecombolabel, "pos 18 10");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(desktopbrokertypecombo, "pos 200 7, w 79!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(desktopbrokertypecombo, "pos 200 7, w 70!, " + comboHeight);
		}
		desktopbrokertypecombo.setToolTipText(MESSAGES.getString("desktopbrokertype.tooltip"));
		desktopbrokertypecombo.setName("desktopbrokertypecombo");
		desktopbrokertypecombo.setFocusable(false);
		desktopbrokertypecombo.setForeground(Color.BLUE);
		desktopbrokertypecombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				int state = event.getStateChange();
				if (state == ItemEvent.SELECTED)
					if (event.getItem().toString().equalsIgnoreCase("view")) {

						/*
						 * Change JTabPanel format to broker selection
						 */
						int intSelectedTabbedPannel = tabbedpanel_results.getSelectedIndex();
						functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "view");

						/*
						 * Enable features that only work in traditional architecture
						 */
						if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("traditional")) {
							hostcbrccombo.setEnabled(true);
							hostcbrccombo.setSelectedIndex(3);
							storagepersistentraidtypecombo.setSelectedIndex(0);
							storagepersistentraidtypecombo.setEnabled(true);
							storagetieringcombo.setEnabled(true);
							storagetieringcombo.setSelectedIndex(0);
						}

						externalconcurrentsessionstxt
								.setText(DEFAULTS.getString("viewexternalconcurrentsessionstxt.default"));
						externalconcurrentsessionstxt.setEnabled(true);
						persistentdisksperdatastoretxt
								.setText(DEFAULTS.getString("persistentdisksperdatastoretxt.default"));
						persistentdisksperdatastoretxt.setEnabled(true);
						persistentdisksperdatastorelabel
								.setText(MESSAGES.getString("persistentdisksperdatastore.view"));
						storagepersistentraidtypecombolabel
								.setText(MESSAGES.getString("storagepersistentraidtype.view"));
						storagedeltaraidtypecombolabel.setText(MESSAGES.getString("storagedeltaraidtype.view"));

						tabbedpanel_results.remove(panel_results_infra_xen);
						tabbedpanel_results.insertTab("view", null, panel_results_infra_view, null, 3);
						tabbedpanel_results.remove(panel_results_storage_xen);
						tabbedpanel_results.insertTab("storage", null, panel_results_storage_view, null, 1);
						tabbedpanel_results.repaint();
						tabbedpanel_results.setSelectedIndex(intSelectedTabbedPannel);

						JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("warningbrokerselection.message"),
								"Horizon View", JOptionPane.WARNING_MESSAGE, null);

						updateCalculation(true);

					} else if (event.getItem().toString().equalsIgnoreCase("xen")) {

						if (hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("traditional")
								|| hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("nutanix")
								|| hostinframodelcombo.getSelectedItem().toString().equalsIgnoreCase("datrium")) {

							/*
							 * Change JTabPanel format to broker selection
							 */
							int intSelectedTabbedPannel = tabbedpanel_results.getSelectedIndex();
							functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "xen");

							hostcbrccombo.setSelectedIndex(0);
							hostcbrccombo.setEnabled(false);
							storagetieringcombo.setSelectedIndex(1);
							storagetieringcombo.setEnabled(false);
							externalconcurrentsessionstxt
									.setText(DEFAULTS.getString("viewexternalconcurrentsessionstxt.minimum"));
							externalconcurrentsessionstxt.setEnabled(false);
							persistentdisksperdatastorelabel
									.setText(MESSAGES.getString("persistentdisksperdatastore.xen"));
							storagepersistentraidtypecombolabel
									.setText(MESSAGES.getString("storagepersistentraidtype.xen"));
							storagedeltaraidtypecombolabel.setText(MESSAGES.getString("storagedeltaraidtype.xen"));
							tabbedpanel_results.remove(panel_results_infra_view);
							tabbedpanel_results.insertTab("xen", null, panel_results_infra_xen, null, 3);
							tabbedpanel_results.remove(panel_results_storage_view);
							tabbedpanel_results.insertTab("storage", null, panel_results_storage_xen, null, 1);
							tabbedpanel_results.repaint();
							tabbedpanel_results.setSelectedIndex(intSelectedTabbedPannel);

							JOptionPane.showMessageDialog(rootPane,
									MESSAGES.getString("warningbrokerselection.message"), "XenDesktop",
									JOptionPane.WARNING_MESSAGE, null);

							updateCalculation(true);

						}

					} else if (event.getItem().toString().equalsIgnoreCase("workspot")) {

						/*
						 * Change JTabPanel format to broker selection
						 */
						int intSelectedTabbedPannel = tabbedpanel_results.getSelectedIndex();
						functionsobj.createVMPanel_Convert_To(tabbedpanel_vm, "workspot");

						externalconcurrentsessionstxt
								.setText(DEFAULTS.getString("viewexternalconcurrentsessionstxt.default"));
						externalconcurrentsessionstxt.setEnabled(true);
						persistentdisksperdatastorelabel
								.setText(MESSAGES.getString("persistentdisksperdatastore.view"));
						storagepersistentraidtypecombolabel
								.setText(MESSAGES.getString("storagepersistentraidtype.view"));
						storagedeltaraidtypecombolabel.setText(MESSAGES.getString("storagedeltaraidtype.view"));

						tabbedpanel_results.remove(panel_results_infra_xen);
						tabbedpanel_results.insertTab("view", null, panel_results_infra_view, null, 3);
						tabbedpanel_results.remove(panel_results_storage_xen);
						tabbedpanel_results.insertTab("storage", null, panel_results_storage_view, null, 1);
						tabbedpanel_results.repaint();
						tabbedpanel_results.setSelectedIndex(intSelectedTabbedPannel);

						JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("warningbrokerselection.message"),
								"Horizon View", JOptionPane.WARNING_MESSAGE, null);

						updateCalculation(true);

					}

			}
		});

		panel_storage.add(persistentdisksperdatastorelabel, "pos 18 34");
		panel_storage.add(persistentdisksperdatastoretxt, "pos 200 33, w 70!, " + TextfieldHeight);
		persistentdisksperdatastoretxt.setToolTipText(MESSAGES.getString("persistentdisksperdatastore.tooltip"));
		persistentdisksperdatastoretxt.setName("persistentdisksperdatastoretxt");
		persistentdisksperdatastoretxt.addFocusListener(this);
		persistentdisksperdatastoretxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
			}

		});

		panel_storage.add(storagedatastorevmtxtlabel, "pos 18 58");
		panel_storage.add(storagedatastorevmtxt, "pos 200 57, w 70!, " + TextfieldHeight);
		storagedatastorevmtxt.setToolTipText(MESSAGES.getString("storagedatastorevm.tooltip"));
		storagedatastorevmtxt.setName("storagedatastorevmtxt");
		storagedatastorevmtxt.addFocusListener(this);
		storagedatastorevmtxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
			}

		});

		panel_storage.add(vmreplicaconcurrentbootcounttxtlabel, "pos 18 84");
		panel_storage.add(vmreplicaconcurrentbootcounttxt, "pos 200 81, w 70!, " + TextfieldHeight);
		vmreplicaconcurrentbootcounttxt.setToolTipText(MESSAGES.getString("vmreplicaconcurrentbootcount.tooltip"));
		vmreplicaconcurrentbootcounttxt.setName("vmreplicaconcurrentbootcounttxt");
		vmreplicaconcurrentbootcounttxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

				if (Integer.parseInt(tf.getText()) > Integer.parseInt(DEFAULTS.getString(tf.getName() + ".maximum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}

			}

		});

		/*
		 * Storage Column 1
		 */
		panel_storage.add(storageblockdeduplicationratiocombolabel, "pos 297 10");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(storageblockdeduplicationratiocombo, "pos 476 7, w 70!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(storageblockdeduplicationratiocombo, "pos 476 7, w 70!, " + comboHeight);
		}
		storageblockdeduplicationratiocombo
				.setToolTipText(MESSAGES.getString("storageblockdeduplicationratio.tooltip"));
		storageblockdeduplicationratiocombo.setName("storageblockdeduplicationratiocombo");
		panel_storage.add(storagecachereadratiocombolabel, "pos 297 34");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(storagecachereadratiocombo, "pos 476 32, w 70!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(storagecachereadratiocombo, "pos 476 32, w 70!, " + comboHeight);
		}
		storagecachereadratiocombo.setName("storagecachereadratiocombo");
		storagecachereadratiocombo.setToolTipText(MESSAGES.getString("storagecachereadratio.tooltip"));
		panel_storage.add(storagecachewriteratiocombolabel, "pos 297 58");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(storagecachewriteratiocombo, "pos 476 57, w 70!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(storagecachewriteratiocombo, "pos 476 57, w 70!, " + comboHeight);
		}
		storagecachewriteratiocombo.setName("storagecachewriteratiocombo");
		storagecachewriteratiocombo.setToolTipText(MESSAGES.getString("storagecachewriteratio.tooltip"));

		panel_storage.add(storagedeltaraidtypecombolabel, "pos 297 84");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(storagedeltaraidtypecombo, "pos 476 82, w 70!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(storagedeltaraidtypecombo, "pos 476 82, w 70!, " + comboHeight);
		}
		storagedeltaraidtypecombo.setToolTipText(MESSAGES.getString("storagedeltaraidtype.tooltip"));
		storagedeltaraidtypecombo.setName("storagedeltaraidtypecombo");
		storagedeltaraidtypecombo.setSelectedIndex(1);

		/*
		 * Storage Column 2
		 */
		panel_storage.add(storageoverheadcombolabel, "pos 580 10");
		panel_storage.add(storageoverheadcombo, "pos 760 7, w 70!, " + comboHeight);
		storageoverheadcombo.setSelectedIndex(1);
		storageoverheadcombo.setToolTipText(MESSAGES.getString("storageoverhead.tooltip"));
		storageoverheadcombo.setName("storageoverheadcombo");
		panel_storage.add(storagetieringcombolabel, "pos 580 34");
		panel_storage.add(storagetieringcombo, "pos 760 32, w 70!, " + comboHeight);
		storagetieringcombo.setToolTipText(MESSAGES.getString("storagetiering.tooltip"));
		storagetieringcombo.setName("storagetieringcombo");
		panel_storage.add(hostlocalvmswapcombolabel, "pos 580 58");
		panel_storage.add(hostlocalvmswapcombo, "pos 760 57, w 70!, " + comboHeight);
		hostlocalvmswapcombo.setSelectedIndex(1);
		hostlocalvmswapcombo.setToolTipText(MESSAGES.getString("hostlocalvmswap.tooltip"));
		hostlocalvmswapcombo.setName("hostlocalvmswapcombo");
		panel_storage.add(storagepersistentraidtypecombolabel, "pos 580 84");
		if (functions.isMac() || functions.isLinux()) {
			panel_storage.add(storagepersistentraidtypecombo, "pos 760 82, w 70!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_storage.add(storagepersistentraidtypecombo, "pos 760 82, w 70!, " + comboHeight);
		}
		storagepersistentraidtypecombo.setToolTipText(MESSAGES.getString("storagepersistentraidtype.tooltip"));
		storagepersistentraidtypecombo.setName("storagepersistentraidtypecombo");
		storagepersistentraidtypecombo.setSelectedIndex(1);

		/*
		 * General
		 */
		MigLayout layout1 = new MigLayout("ins 3, gap 10px -4px", "", "");
		panel_general.setLayout(layout1);
		panel_general.setBorder(raisedbevel);
		panel_general.setBackground(Color.LIGHT_GRAY);

		/*
		 * General Column 0
		 */
		panel_general.add(panel_general_desc, "pos 0 0");
		if (functions.isLinux()) {
			panel_general_desc.setFont(new Font("Serif", Font.PLAIN, 10));
		} else {
			panel_general_desc.setFont(new Font("Serif", Font.PLAIN, 12));
		}
		panel_general_desc.setForeground(Color.DARK_GRAY);
		panel_general.add(externalconcurrentsessionstxtlabel, "pos 18 10");
		panel_general.add(externalconcurrentsessionstxt, "pos 200 7, w 61!, " + TextfieldHeight);
		externalconcurrentsessionstxt.setName("viewexternalconcurrentsessionstxt");
		externalconcurrentsessionstxt.setToolTipText(MESSAGES.getString("externalconcurrentsessions.tooltip"));
		externalconcurrentsessionstxt.addFocusListener(this);
		externalconcurrentsessionstxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				}

			}

		});

		/*
		 * General Column 1
		 */
		panel_general.add(vchacombolabel, "pos 297 10");
		if (functions.isMac() || functions.isLinux()) {
			panel_general.add(vchacombo, "pos 476 7, w 72!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_general.add(vchacombo, "pos 476 7, w 70!, " + comboHeight);
		}
		vchacombo.setSelectedIndex(0);
		vchacombo.setToolTipText(MESSAGES.getString("vcha.tooltip"));
		vchacombo.setName("vchacombo");
		panel_general.add(backendserverinfracombolabel, "pos 297 33");
		if (functions.isMac() || functions.isLinux()) {
			panel_general.add(backendserverinfracombo, "pos 476 31, w 72!, " + comboHeight);
		} else if (functions.isWindows()) {
			panel_general.add(backendserverinfracombo, "pos 476 31, w 70!, " + comboHeight);
		}
		backendserverinfracombo.setSelectedIndex(0);
		backendserverinfracombo.setToolTipText(MESSAGES.getString("backendserverinfracombo.tooltip"));
		backendserverinfracombo.setName("backendserverinfracombo");

		/*
		 * General Column 2
		 */
		panel_general.add(vcclustersizecombolabel, "pos 580 10");
		panel_general.add(vcclustersizecombo, "pos 760 7, w 70!, " + comboHeight);
		vcclustersizecombo.setSelectedIndex(7);
		vcclustersizecombo.setToolTipText(MESSAGES.getString("vcclustersize.tooltip"));
		vcclustersizecombo.setName("vcclustersizecombo");
		panel_general.add(vcvmlimittxtlabel, "pos 580 33");
		panel_general.add(vcvmlimittxt, "pos 760 33, w 70!, " + TextfieldHeight);
		vcvmlimittxt.setToolTipText(MESSAGES.getString("vcvmlimit.tooltip"));
		vcvmlimittxt.setName("vcvmlimittxt");
		vcvmlimittxt.addFocusListener(this);
		vcvmlimittxt.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

				JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer.parseInt(DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) > Integer.parseInt(DEFAULTS.getString(tf.getName() + ".maximum"))) {
					JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}

			}

		});

		/*
		 * Buttons
		 */
		MigLayout layout_buttons = new MigLayout("gap 5! 0.5!, ins 2", "", "");
		panel_buttons.setLayout(layout_buttons);
		panel_buttons.setBorder(null);
		if (functions.isMac() || functions.isLinux()) {
			panel_buttons.add(update, "pos 10 10, w 130!, " + ButtonHeight);
			panel_buttons.add(save, "pos 155 10, w 130!, " + ButtonHeight);
			panel_buttons.add(load, "pos 303 10, w 130!, " + ButtonHeight);
			panel_buttons.add(print, "pos 453 10, w 130!, " + ButtonHeight);
			panel_buttons.add(about, "pos 603 10, w 130!, " + ButtonHeight);
			panel_buttons.add(guide, "pos 753 10, w 130!, " + ButtonHeight);
		} else if (functions.isWindows()) {
			panel_buttons.add(update, "pos 10 10, w 130!, " + ButtonHeight);
			panel_buttons.add(save, "pos 155 10, w 130!, " + ButtonHeight);
			panel_buttons.add(load, "pos 303 10, w 130!, " + ButtonHeight);
			panel_buttons.add(print, "pos 453 10, w 130!, " + ButtonHeight);
			panel_buttons.add(about, "pos 603 10, w 130!, " + ButtonHeight);
			panel_buttons.add(guide, "pos 753 10, w 130!, " + ButtonHeight);
		}
		update.setFocusable(false);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Update Write IOps percentage when Update button is actioned
				 */
				componentMap = new HashMap<String, Component>();
				int totalTabs = tabbedpanel_vm.getTabCount() - 1;
				for (int i = 1; i < totalTabs; i++) {
					componentMap.clear();
					JPanel c = (JPanel) tabbedpanel_vm.getComponentAt(i);
					Component[] components = c.getComponents();
					for (int n = 0; n < components.length; ++n) {
						if (components[n].getName() != null) {
							componentMap.put(components[n].getName(), components[n]);
						}
					}
					JTextField aa = (JTextField) componentMap.get("vmdeltareadIOtxt");
					int i1 = 100 - Integer.parseInt(aa.getText());
					JLabel ab = (JLabel) componentMap.get("vmdeltawriteIOtxtlabel");
					ab.setText(String.valueOf(i1));
					JTextField ba = (JTextField) componentMap.get("vmpersistentreadIOtxt");
					int i2 = 100 - Integer.parseInt(ba.getText());
					JLabel bb = (JLabel) componentMap.get("vmpersistentwriteIOlabel");
					bb.setText(String.valueOf(i2));
				}

				updateCalculation(true);
			}

		});

		save.setFocusable(false);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int returnVal = FC.showSaveDialog(FC);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = FC.getSelectedFile();
					BufferedWriter bw;
					try {
						bw = new BufferedWriter(new FileWriter(file));
						int totalTabs = tabbedpanel_vm.getTabCount() - 1;
						for (int i = 1; i < totalTabs; i++) {
							JPanel c = (JPanel) tabbedpanel_vm.getComponentAt(i);
							Component[] components = c.getComponents();
							for (int n = 0; n < components.length; ++n) {
								if (components[n].getName() != null) {
									functionsobj.fileWrite(components[n], components[n].getParent().getName(), bw);
								}
							}
						}
						for (Component c : panel_general.getComponents()) {
							functionsobj.fileWrite(c, c.getParent().getName(), bw);
						}
						for (Component c : panel_host.getComponents()) {
							functionsobj.fileWrite(c, c.getParent().getName(), bw);
						}
						for (Component c : panel_storage.getComponents()) {
							functionsobj.fileWrite(c, c.getParent().getName(), bw);
						}
						bw.flush();
						bw.close();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		load.setFocusable(false);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = FC.showOpenDialog(FC);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = FC.getSelectedFile();
					BufferedReader br;
					FileReader reader = null;
					if (file.canRead() && file.exists()) {
						try {
							reader = new FileReader(file);
							br = new BufferedReader(reader);
							String sCurrentLine;
							while ((sCurrentLine = br.readLine()) != null) {
								int totalTabs = tabbedpanel_vm.getTabCount() - 1;
								for (int i = 1; i < totalTabs; i++) {
									JPanel c = (JPanel) tabbedpanel_vm.getComponentAt(i);
									Component[] components = c.getComponents();
									for (int n = 0; n < components.length; ++n) {
										functionsobj.fileRead(components[n], components[n].getParent().getName(),
												sCurrentLine);
									}
								}
								for (Component c : panel_general.getComponents()) {
									functionsobj.fileRead(c, c.getParent().getName(), sCurrentLine);
								}
								for (Component c : panel_host.getComponents()) {
									functionsobj.fileRead(c, c.getParent().getName(), sCurrentLine);
								}
								for (Component c : panel_storage.getComponents()) {
									functionsobj.fileRead(c, c.getParent().getName(), sCurrentLine);
								}
							}
							updateCalculation(true);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}

		});

		print.setFocusable(false);
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				updateCalculation(true);
				functionsobj.printAction(desktopbrokertypecombo.getSelectedItem().toString());

			}

		});

		about.setFocusable(false);
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("about.info"),
						MESSAGES.getString("about.title"), 1);

			}

		});

		guide.setFocusable(false);
		guide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				functionsobj.openURI(MESSAGES.getString("guide.uri"));

			}

		});

		/*
		 * Snippet to execute background tasks Drop code in public void run()
		 */
		if (SETTINGS.getString("backgroundexecution.setting").toString().equalsIgnoreCase("on")) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {

					/*
					 * while (!Thread.currentThread().isInterrupted()) { drop code here }
					 */
				}
			});
			t.start();
		}

		/*
		 * Display Splash Screeen when Opening App
		 */
		if (SETTINGS.getString("splash.setting").equalsIgnoreCase("on")) {
			JOptionPane.showMessageDialog(this, MESSAGES.getString("about.info"), MESSAGES.getString("about.title"), 1);
		}

		/*
		 * Check Software Version against oficial software repository
		 */
		if (SETTINGS.getString("versioncheck.setting").equalsIgnoreCase("on")) {
			try {
				if (!(functionsobj.versionCheck(SETTINGS.getString("googleanalyticstracker.version")))) {
					JOptionPane.showMessageDialog(this, MESSAGES.getString("about.update"),
							MESSAGES.getString("about.title"), 1);
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method actionPerformed.
	 * 
	 * @param e ActionEvent @see
	 *          java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * Method focusGained.
	 * 
	 * @param arg0 FocusEvent @see
	 *             java.awt.event.FocusListener#focusGained(FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {

	}

	/**
	 * User input validations
	 * 
	 * @param event FocusEvent
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent event) {

		/*
		 * Generic validation to all JTextFields
		 */
		JTextField tf = (JTextField) event.getSource();
		if (!(validationsobj.equalNumeric(tf.getText()))) {
			JOptionPane.showMessageDialog(rootPane, MESSAGES.getString("onlynumbersaccepted.validation"), null,
					JOptionPane.ERROR_MESSAGE, null);
			tf.setText(DEFAULTS.getString(tf.getName() + ".default"));
			tf.requestFocus();
		}

	}

}
