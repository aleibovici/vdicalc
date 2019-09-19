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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

/**
 * @author andreleibovici
 * @version $Revision: 1.0 $
 */
@SuppressWarnings({ "serial" })
public class custompanels extends JPanel implements FocusListener, ActionListener {

	/**
	 * @author andreleibovici
	 */
	private static enum State {
		ON, OFF, SUSPEND;
	}

	/**
	 * Field raisedbevel.
	 */
	private static Border Raisedbevel = null;

	/**
	 * Field grayline.
	 */
	private static Border Grayline = null;

	/**
	 * 
	 * @return JPanel
	 */
	public static JPanel createHostResultPanel() {
		final JPanel j = new JPanel();
		final JLabel numberofhostslabelvalue = new JLabel("0");
		final JLabel coresperhostlabelvalue = new JLabel("0");
		final JLabel hostvmcountlabelvalue = new JLabel("0");
		final JLabel hostcoremhzlabelvalue = new JLabel("0");
		final JLabel hostmemorytotallabelvalue = new JLabel("0");
		final JLabel hostmemorytotalovercommitlabelvalue = new JLabel("0");
		final JLabel hostlocalusablecapacitylabelvalue = new JLabel("0");
		final JLabel vcclusterslabelvalue = new JLabel("0");

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel numberofhostslabel = new JLabel(vdicalc.MESSAGES.getString("numberofhosts.result"),
				SwingConstants.LEFT);
		final JLabel coresperhostlabel = new JLabel(vdicalc.MESSAGES.getString("coresperhost.result"),
				SwingConstants.LEFT);
		final JLabel hostvmcountlabel = new JLabel(vdicalc.MESSAGES.getString("hostvmcount.result"),
				SwingConstants.LEFT);
		final JLabel hostcoremhzlabel = new JLabel(vdicalc.MESSAGES.getString("hostcoremhz.result"),
				SwingConstants.LEFT);
		final JLabel hostmemorytotallabel = new JLabel(vdicalc.MESSAGES.getString("hostmemorytotal.result"),
				SwingConstants.LEFT);
		final JLabel hostmemorytotalovercommitlabel = new JLabel(
				vdicalc.MESSAGES.getString("hostmemorytotalovercommit.result"), SwingConstants.LEFT);
		final JLabel hostlocalusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("hostlocalusablecapacity.result"), SwingConstants.LEFT);
		final JLabel vcclusterslabel = new JLabel(vdicalc.MESSAGES.getString("vcclusters.result"), SwingConstants.LEFT);

		numberofhostslabelvalue.setName("numberofhostslabelvalue");
		coresperhostlabelvalue.setName("coresperhostlabelvalue");
		hostvmcountlabelvalue.setName("hostvmcountlabelvalue");
		hostcoremhzlabelvalue.setName("hostcoremhzlabelvalue");
		hostmemorytotallabelvalue.setName("hostmemorytotallabelvalue");
		hostmemorytotalovercommitlabelvalue.setName("hostmemorytotalovercommitlabelvalue");
		hostlocalusablecapacitylabelvalue.setName("hostlocalusablecapacitylabelvalue");
		vcclusterslabelvalue.setName("vcclusterslabelvalue");

		numberofhostslabelvalue.setBorder(Grayline);
		coresperhostlabelvalue.setBorder(Grayline);
		hostvmcountlabelvalue.setBorder(Grayline);
		hostcoremhzlabelvalue.setBorder(Grayline);
		hostmemorytotallabelvalue.setBorder(Grayline);
		hostmemorytotalovercommitlabelvalue.setBorder(Grayline);
		hostlocalusablecapacitylabelvalue.setBorder(Grayline);
		vcclusterslabelvalue.setBorder(Grayline);

		j.add(numberofhostslabel, "cell 0 0");
		j.add(numberofhostslabelvalue, "w 50!");
		numberofhostslabelvalue.setBorder(Grayline);
		j.add(coresperhostlabel, "cell 0 1");
		j.add(coresperhostlabelvalue, "w 50!");
		coresperhostlabelvalue.setBorder(Grayline);
		j.add(hostcoremhzlabel, "cell 0 2");
		j.add(hostcoremhzlabelvalue, "w 50!");
		hostcoremhzlabelvalue.setBorder(Grayline);
		j.add(hostmemorytotallabel, "cell 2 0");
		j.add(hostmemorytotallabelvalue, "w 50!");
		hostmemorytotallabelvalue.setBorder(Grayline);
		j.add(hostmemorytotalovercommitlabel, "cell 2 1");
		j.add(hostmemorytotalovercommitlabelvalue, "w 50!");
		hostmemorytotalovercommitlabelvalue.setBorder(Grayline);
		j.add(hostlocalusablecapacitylabel, "cell 4 0");
		j.add(hostlocalusablecapacitylabelvalue, "w 50!");
		hostlocalusablecapacitylabelvalue.setBorder(Grayline);
		j.add(hostvmcountlabel, "cell 6 0");
		j.add(hostvmcountlabelvalue, "w 50!");
		hostvmcountlabelvalue.setBorder(Grayline);
		j.add(vcclusterslabel, "cell 8 0");
		j.add(vcclusterslabelvalue, "w 50!");
		vcclusterslabelvalue.setBorder(Grayline);

		return j;
	}

	/**
	 * 
	 * @return JPanel
	 */
	public static JPanel createStorageDetailResultPanel() {
		final JPanel j = new JPanel();
		final JLabel storagedisposableusablecapacitylabelvalue = new JLabel("0");
		final JLabel storageswapusablecapacitylabelvalue = new JLabel("0");
		JLabel storageswappowerstatecapacityreductionlabelvalue = new JLabel("0");
		final JLabel storagesuspendusablecapacitylabelvalue = new JLabel("0");

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel storagedisposableusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storagedisposableusablecapacity.result"), SwingConstants.LEFT);
		final JLabel storageswapusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storageswapusablecapacity.result"), SwingConstants.LEFT);
		final JLabel storageswappowerstatecapacityreductionlabel = new JLabel(
				vdicalc.MESSAGES.getString("storageswappowerstatecapacityreduction.result"), SwingConstants.LEFT);
		final JLabel storagesuspendusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storagesuspendusablecapacity.result"), SwingConstants.LEFT);

		storagedisposableusablecapacitylabelvalue.setBorder(Grayline);
		storageswapusablecapacitylabelvalue.setBorder(Grayline);
		storageswappowerstatecapacityreductionlabelvalue.setBorder(Grayline);
		storagesuspendusablecapacitylabelvalue.setBorder(Grayline);

		storagedisposableusablecapacitylabelvalue.setForeground(Color.RED);
		storageswapusablecapacitylabelvalue.setForeground(Color.RED);
		storageswappowerstatecapacityreductionlabelvalue.setForeground(Color.RED);

		storagedisposableusablecapacitylabel.setName("storagedisposableusablecapacitylabel");
		storageswapusablecapacitylabel.setName("storageswapusablecapacitylabel");
		storageswappowerstatecapacityreductionlabel.setName("storageswappowerstatecapacityreductionlabel");
		storagesuspendusablecapacitylabel.setName("storagesuspendusablecapacitylabel");
		storagedisposableusablecapacitylabelvalue.setName("storagedisposableusablecapacitylabelvalue");
		storageswapusablecapacitylabelvalue.setName("storageswapusablecapacitylabelvalue");
		storageswappowerstatecapacityreductionlabelvalue.setName("storageswappowerstatecapacityreductionlabelvalue");
		storagesuspendusablecapacitylabelvalue.setName("storagesuspendusablecapacitylabelvalue");

		j.add(storagedisposableusablecapacitylabel, "cell 0 0");
		j.add(storagedisposableusablecapacitylabelvalue, "w 60!");
		j.add(storageswapusablecapacitylabel, "cell 0 1");
		j.add(storageswapusablecapacitylabelvalue, "w 60!");
		j.add(storageswappowerstatecapacityreductionlabel, "cell 0 2");
		j.add(storageswappowerstatecapacityreductionlabelvalue, "w 60!");
		j.add(storagesuspendusablecapacitylabel, "cell 0 3");
		j.add(storagesuspendusablecapacitylabelvalue, "w 60!");

		return j;
	}

	/**
	 * 
	 * 
	 * 
	 * @return JPanel
	 */
	public static JPanel createStorageViewResultPanel() {

		final JPanel j = new JPanel();

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel storageparentusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storageparentusablecapacitylabel.view"), SwingConstants.LEFT);
		final JLabel storagereplicausablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storagereplicausablecapacity.view"), SwingConstants.LEFT);
		final JLabel storagepersistentusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentusablecapacity.result"), SwingConstants.LEFT);
		final JLabel storagecloneusablecapacitylabel = new JLabel(
				vdicalc.MESSAGES.getString("storagecloneusablecapacity.view"), SwingConstants.LEFT);
		final JLabel storageusablecapacitylabel = new JLabel(vdicalc.MESSAGES.getString("storageusablecapacity.result"),
				SwingConstants.LEFT);
		final JLabel storagefrontendIOPSlabel = new JLabel(vdicalc.MESSAGES.getString("storagefrontendIOPS.result"),
				SwingConstants.LEFT);
		final JLabel storagebackendIOPSlabel = new JLabel(vdicalc.MESSAGES.getString("storagebackendIOPS.result"),
				SwingConstants.LEFT);
		final JLabel storagereplicafrontendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storagereplicafrontendIOPS.result"), SwingConstants.LEFT);
		final JLabel storagereplicabackendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storagereplicabackendIOPS.result"), SwingConstants.LEFT);
		final JLabel storageclonefrontendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storageclonefrontendIOPS.view"), SwingConstants.LEFT);
		final JLabel storageclonebackendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storageclonebackendIOPS.view"), SwingConstants.LEFT);
		final JLabel storagepersistentfrontendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentfrontendIOPS.result"), SwingConstants.LEFT);
		final JLabel storagepersistentbackendIOPSlabel = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentbackendIOPS.result"), SwingConstants.LEFT);
		final JLabel storagepersistentdatastorecountlabel = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentdatastorecount.result"), SwingConstants.LEFT);
		final JLabel storagepersistentdatastoresizelabel = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentdatastoresize.result"), SwingConstants.LEFT);
		final JLabel storagedatastorecountlabel = new JLabel(vdicalc.MESSAGES.getString("storagedatastorecount.result"),
				SwingConstants.LEFT);
		final JLabel storagedatastoresizelabel = new JLabel(vdicalc.MESSAGES.getString("storagedatastoresize.result"),
				SwingConstants.LEFT);

		final JLabel storageparentusablecapacitylabelvalue = new JLabel("0");
		final JLabel storagereplicausablecapacitylabelvalue = new JLabel("0");
		final JLabel storagepersistentusablecapacitylabelvalue = new JLabel("0");
		final JLabel storagecloneusablecapacitylabelvalue = new JLabel("0");
		final JLabel storageusablecapacitylabelvalue = new JLabel("0");
		final JLabel storagefrontendIOPSlabelvalue = new JLabel("0");
		final JLabel storagebackendIOPSlabelvalue = new JLabel("0");
		final JLabel storagereplicafrontendIOPSlabelvalue = new JLabel("0");
		final JLabel storagereplicabackendIOPSlabelvalue = new JLabel("0");
		final JLabel storageclonefrontendIOPSlabelvalue = new JLabel("0");
		final JLabel storageclonebackendIOPSlabelvalue = new JLabel("0");
		final JLabel storagepersistentfrontendIOPSlabelvalue = new JLabel("0");
		final JLabel storagepersistentbackendIOPSlabelvalue = new JLabel("0");
		final JLabel storagepersistentdatastorecountlabelvalue = new JLabel("0");
		final JLabel storagepersistentdatastoresizelabelvalue = new JLabel("0");
		final JLabel storagedatastorecountlabelvalue = new JLabel("0");
		final JLabel storagedatastoresizelabelvalue = new JLabel("0");

		storageparentusablecapacitylabelvalue.setBorder(Grayline);
		storagereplicausablecapacitylabelvalue.setBorder(Grayline);
		storagepersistentusablecapacitylabelvalue.setBorder(Grayline);
		storagecloneusablecapacitylabelvalue.setBorder(Grayline);
		storageusablecapacitylabelvalue.setBorder(Grayline);
		storagefrontendIOPSlabelvalue.setBorder(Grayline);
		storagebackendIOPSlabelvalue.setBorder(Grayline);
		storagereplicafrontendIOPSlabelvalue.setBorder(Grayline);
		storagereplicabackendIOPSlabelvalue.setBorder(Grayline);
		storageclonefrontendIOPSlabelvalue.setBorder(Grayline);
		storageclonebackendIOPSlabelvalue.setBorder(Grayline);
		storagepersistentfrontendIOPSlabelvalue.setBorder(Grayline);
		storagepersistentbackendIOPSlabelvalue.setBorder(Grayline);
		storagepersistentdatastorecountlabelvalue.setBorder(Grayline);
		storagepersistentdatastoresizelabelvalue.setBorder(Grayline);
		storagedatastorecountlabelvalue.setBorder(Grayline);
		storagedatastoresizelabelvalue.setBorder(Grayline);

		storageparentusablecapacitylabelvalue.setName("storageparentusablecapacitylabelvalue");
		storagereplicausablecapacitylabelvalue.setName("storagereplicausablecapacitylabelvalue");
		storagepersistentusablecapacitylabelvalue.setName("storagepersistentusablecapacitylabelvalue");
		storagecloneusablecapacitylabelvalue.setName("storagecloneusablecapacitylabelvalue");
		storageusablecapacitylabelvalue.setName("storageusablecapacitylabelvalue");
		storagefrontendIOPSlabelvalue.setName("storagefrontendIOPSlabelvalue");
		storagebackendIOPSlabelvalue.setName("storagebackendIOPSlabelvalue");
		storagereplicafrontendIOPSlabelvalue.setName("storagereplicafrontendIOPSlabelvalue");
		storagereplicabackendIOPSlabelvalue.setName("storagereplicabackendIOPSlabelvalue");
		storageclonefrontendIOPSlabelvalue.setName("storageclonefrontendIOPSlabelvalue");
		storageclonebackendIOPSlabelvalue.setName("storageclonebackendIOPSlabelvalue");
		storagepersistentfrontendIOPSlabelvalue.setName("storagepersistentfrontendIOPSlabelvalue");
		storagepersistentbackendIOPSlabelvalue.setName("storagepersistentbackendIOPSlabelvalue");
		storagepersistentdatastorecountlabelvalue.setName("storagepersistentdatastorecountlabelvalue");
		storagepersistentdatastoresizelabelvalue.setName("storagepersistentdatastoresizelabelvalue");
		storagedatastorecountlabelvalue.setName("storagedatastorecountlabelvalue");
		storagedatastoresizelabelvalue.setName("storagedatastoresizelabelvalue");

		j.add(storageparentusablecapacitylabel, "cell 2 0");
		j.add(storageparentusablecapacitylabelvalue, "w 60!");
		j.add(storagereplicausablecapacitylabel, "cell 2 1");
		j.add(storagereplicausablecapacitylabelvalue, "w 60!");
		j.add(storagereplicafrontendIOPSlabel, "cell 2 2");
		j.add(storagereplicafrontendIOPSlabelvalue, "w 60!");
		j.add(storagereplicabackendIOPSlabel, "cell 2 3");
		j.add(storagereplicabackendIOPSlabelvalue, "w 60!");
		j.add(storagecloneusablecapacitylabel, "cell 3 0");
		j.add(storagecloneusablecapacitylabelvalue, "w 60!");
		j.add(storageclonefrontendIOPSlabel, "cell 3 1");
		j.add(storageclonefrontendIOPSlabelvalue, "w 60!");
		j.add(storageclonebackendIOPSlabel, "cell 3 2");
		j.add(storageclonebackendIOPSlabelvalue, "w 60!");
		j.add(storagepersistentusablecapacitylabel, "cell 5 0");
		j.add(storagepersistentusablecapacitylabelvalue, "w 60!");
		j.add(storagepersistentfrontendIOPSlabel, "cell 5 1");
		j.add(storagepersistentfrontendIOPSlabelvalue, "w 60!");
		j.add(storagepersistentbackendIOPSlabel, "cell 5 2");
		j.add(storagepersistentbackendIOPSlabelvalue, "w 60!");
		j.add(storagepersistentdatastorecountlabel, "cell 5 3");
		j.add(storagepersistentdatastorecountlabelvalue, "w 60!");
		j.add(storagepersistentdatastoresizelabel, "cell 5 4");
		j.add(storagepersistentdatastoresizelabelvalue, "w 60!");
		j.add(storageusablecapacitylabel, "cell 7 0");
		j.add(storageusablecapacitylabelvalue, "w 60!");
		j.add(storagefrontendIOPSlabel, "cell 7 1");
		j.add(storagefrontendIOPSlabelvalue, "w 60!");
		j.add(storagebackendIOPSlabel, "cell 7 2");
		j.add(storagebackendIOPSlabelvalue, "w 60!");
		j.add(storagedatastorecountlabel, "cell 7 3");
		j.add(storagedatastorecountlabelvalue, "w 60!");
		j.add(storagedatastoresizelabel, "cell 7 4");
		j.add(storagedatastoresizelabelvalue, "w 60!");

		return j;

	}

	/**
	 * 
	 * 
	 * 
	 * @return JPanel
	 */
	public static JPanel createStorageXenResultPanel() {

		final JPanel j = new JPanel();

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel storageparentusablecapacitylabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storageparentusablecapacitylabel.xen"), SwingConstants.LEFT);
		final JLabel storagereplicausablecapacitylabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagereplicausablecapacity.xen"), SwingConstants.LEFT);
		final JLabel storagecloneusablecapacitylabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagecloneusablecapacity.xen"), SwingConstants.LEFT);
		final JLabel storageclonefrontendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storageclonefrontendIOPS.xen"), SwingConstants.LEFT);
		final JLabel storageclonebackendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storageclonebackendIOPS.xen"), SwingConstants.LEFT);

		final JLabel storagedatastorecountlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagedatastorecount_xen.result"), SwingConstants.LEFT);
		final JLabel storagedatastoresizelabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagedatastoresize_xen.result"), SwingConstants.LEFT);
		final JLabel storageusablecapacitylabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storageusablecapacity_xen.result"), SwingConstants.LEFT);
		final JLabel storagefrontendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagefrontendIOPS_xen.result"), SwingConstants.LEFT);
		final JLabel storagebackendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagebackendIOPS_xen.result"), SwingConstants.LEFT);
		final JLabel storagepersistentusablecapacitylabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentusablecapacity_xen.result"), SwingConstants.LEFT);
		final JLabel storagepersistentfrontendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentfrontendIOPS_xen.result"), SwingConstants.LEFT);
		final JLabel storagepersistentbackendIOPSlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentbackendIOPS_xen.result"), SwingConstants.LEFT);
		final JLabel storagepersistentdatastorecountlabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentdatastorecount_xen.result"), SwingConstants.LEFT);
		final JLabel storagepersistentdatastoresizelabel_xen = new JLabel(
				vdicalc.MESSAGES.getString("storagepersistentdatastoresize_xen.result"), SwingConstants.LEFT);

		final JLabel storageparentusablecapacitylabelvalue_xen = new JLabel("0");
		final JLabel storagereplicausablecapacitylabelvalue_xen = new JLabel("0");
		final JLabel storagecloneusablecapacitylabelvalue_xen = new JLabel("0");
		final JLabel storageclonefrontendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storageclonebackendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storagedatastorecountlabelvalue_xen = new JLabel("0");
		final JLabel storagedatastoresizelabelvalue_xen = new JLabel("0");
		final JLabel storageusablecapacitylabelvalue_xen = new JLabel("0");
		final JLabel storagefrontendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storagebackendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storagepersistentusablecapacitylabelvalue_xen = new JLabel("0");
		final JLabel storagepersistentfrontendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storagepersistentbackendIOPSlabelvalue_xen = new JLabel("0");
		final JLabel storagepersistentdatastorecountlabelvalue_xen = new JLabel("0");
		final JLabel storagepersistentdatastoresizelabelvalue_xen = new JLabel("0");

		storageparentusablecapacitylabelvalue_xen.setBorder(Grayline);
		storagereplicausablecapacitylabelvalue_xen.setBorder(Grayline);
		storagecloneusablecapacitylabelvalue_xen.setBorder(Grayline);
		storageclonefrontendIOPSlabelvalue_xen.setBorder(Grayline);
		storageclonebackendIOPSlabelvalue_xen.setBorder(Grayline);
		storagedatastorecountlabelvalue_xen.setBorder(Grayline);
		storagedatastoresizelabelvalue_xen.setBorder(Grayline);
		storageusablecapacitylabelvalue_xen.setBorder(Grayline);
		storagefrontendIOPSlabelvalue_xen.setBorder(Grayline);
		storagebackendIOPSlabelvalue_xen.setBorder(Grayline);
		storagepersistentusablecapacitylabelvalue_xen.setBorder(Grayline);
		storagepersistentfrontendIOPSlabelvalue_xen.setBorder(Grayline);
		storagepersistentbackendIOPSlabelvalue_xen.setBorder(Grayline);
		storagepersistentdatastorecountlabelvalue_xen.setBorder(Grayline);
		storagepersistentdatastoresizelabelvalue_xen.setBorder(Grayline);

		storageparentusablecapacitylabelvalue_xen.setName("storageparentusablecapacitylabelvalue_xen");
		storagereplicausablecapacitylabelvalue_xen.setName("storagereplicausablecapacitylabelvalue_xen");
		storagecloneusablecapacitylabelvalue_xen.setName("storagecloneusablecapacitylabelvalue_xen");
		storageclonefrontendIOPSlabelvalue_xen.setName("storageclonefrontendIOPSlabelvalue_xen");
		storageclonebackendIOPSlabelvalue_xen.setName("storageclonebackendIOPSlabelvalue_xen");
		storagedatastorecountlabelvalue_xen.setName("storagedatastorecountlabelvalue_xen");
		storagedatastoresizelabelvalue_xen.setName("storagedatastoresizelabelvalue_xen");
		storageusablecapacitylabelvalue_xen.setName("storageusablecapacitylabelvalue_xen");
		storagefrontendIOPSlabelvalue_xen.setName("storagefrontendIOPSlabelvalue_xen");
		storagebackendIOPSlabelvalue_xen.setName("storagebackendIOPSlabelvalue_xen");
		storagepersistentusablecapacitylabelvalue_xen.setName("storagepersistentusablecapacitylabelvalue_xen");
		storagepersistentfrontendIOPSlabelvalue_xen.setName("storagepersistentfrontendIOPSlabelvalue_xen");
		storagepersistentbackendIOPSlabelvalue_xen.setName("storagepersistentbackendIOPSlabelvalue_xen");
		storagepersistentdatastorecountlabelvalue_xen.setName("storagepersistentdatastorecountlabelvalue_xen");
		storagepersistentdatastoresizelabelvalue_xen.setName("storagepersistentdatastoresizelabelvalue_xen");

		j.add(storageparentusablecapacitylabel_xen, "cell 2 0");
		j.add(storageparentusablecapacitylabelvalue_xen, "w 60!");
		j.add(storagereplicausablecapacitylabel_xen, "cell 2 1");
		j.add(storagereplicausablecapacitylabelvalue_xen, "w 60!");
		j.add(storagecloneusablecapacitylabel_xen, "cell 4 0");
		j.add(storagecloneusablecapacitylabelvalue_xen, "w 60!");
		j.add(storageclonefrontendIOPSlabel_xen, "cell 4 1");
		j.add(storageclonefrontendIOPSlabelvalue_xen, "w 60!");
		j.add(storageclonebackendIOPSlabel_xen, "cell 4 2");
		j.add(storageclonebackendIOPSlabelvalue_xen, "w 60!");
		j.add(storagepersistentusablecapacitylabel_xen, "cell 6 0");
		j.add(storagepersistentusablecapacitylabelvalue_xen, "w 60!");
		j.add(storagepersistentfrontendIOPSlabel_xen, "cell 6 1");
		j.add(storagepersistentfrontendIOPSlabelvalue_xen, "w 60!");
		j.add(storagepersistentbackendIOPSlabel_xen, "cell 6 2");
		j.add(storagepersistentbackendIOPSlabelvalue_xen, "w 60!");
		j.add(storagepersistentdatastorecountlabel_xen, "cell 6 3");
		j.add(storagepersistentdatastorecountlabelvalue_xen, "w 60!");
		j.add(storagepersistentdatastoresizelabel_xen, "cell 6 4");
		j.add(storagepersistentdatastoresizelabelvalue_xen, "w 60!");
		j.add(storageusablecapacitylabel_xen, "cell 8 0");
		j.add(storageusablecapacitylabelvalue_xen, "w 60!");
		j.add(storagefrontendIOPSlabel_xen, "cell 8 1");
		j.add(storagefrontendIOPSlabelvalue_xen, "w 60!");
		j.add(storagebackendIOPSlabel_xen, "cell 8 2");
		j.add(storagebackendIOPSlabelvalue_xen, "w 60!");
		j.add(storagedatastorecountlabel_xen, "cell 8 3");
		j.add(storagedatastorecountlabelvalue_xen, "w 60!");
		j.add(storagedatastoresizelabel_xen, "cell 8 4");
		j.add(storagedatastoresizelabelvalue_xen, "w 60!");

		return j;

	}

	/**
	 * createViewInfraResultPanel
	 * 
	 * @return null
	 */
	public static JPanel createViewInfraResultPanel() {

		final JPanel j = new JPanel();

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel vccountlabel = new JLabel(vdicalc.MESSAGES.getString("vccountlabel.result"), SwingConstants.LEFT);
		final JLabel viewconnectionservercountlabel = new JLabel(
				vdicalc.MESSAGES.getString("viewconnectionservercount.result"), SwingConstants.LEFT);
		final JLabel viewsecurityservercountlabel = new JLabel(
				vdicalc.MESSAGES.getString("viewsecurityservercount.result"), SwingConstants.LEFT);

		final JLabel vccountlabelvalue = new JLabel("0");
		final JLabel viewconnectionservercountlabelvalue = new JLabel("0");
		final JLabel viewsecurityservercountlabelvalue = new JLabel("0");

		vccountlabelvalue.setBorder(Grayline);
		viewconnectionservercountlabelvalue.setBorder(Grayline);
		viewsecurityservercountlabelvalue.setBorder(Grayline);

		vccountlabelvalue.setName("vccountlabelvalue");
		viewconnectionservercountlabelvalue.setName("viewconnectionservercountlabelvalue");
		viewsecurityservercountlabelvalue.setName("viewsecurityservercountlabelvalue");

		j.add(vccountlabel);
		j.add(vccountlabelvalue, "w 50!");
		j.add(viewconnectionservercountlabel);
		j.add(viewconnectionservercountlabelvalue, "w 50!");
		j.add(viewsecurityservercountlabel);
		j.add(viewsecurityservercountlabelvalue, "w 50!");

		return j;
	}

	/**
	 * 
	 * 
	 * 
	 * @return JPanel
	 */
	public static JPanel createVMResultPanel() {

		final JPanel j = new JPanel();

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel vmfullclonesizelabel = new JLabel("Full Clone (MB)", SwingConstants.LEFT);
		final JLabel vmdeltasizelabel = new JLabel(vdicalc.MESSAGES.getString("vmdeltasize.view"), SwingConstants.LEFT);
		final JLabel vmpersistentdisksizelabel = new JLabel("Persistent Disk (MB)", SwingConstants.LEFT);
		final JLabel vmdisposabledisksizelabel = new JLabel("Disposable Disk (MB)", SwingConstants.LEFT);
		final JLabel vmlogsizelabel = new JLabel("Log (MB)", SwingConstants.LEFT);
		final JLabel vmswapsizelabel = new JLabel("Memory vSwap (MB)", SwingConstants.LEFT);
		final JLabel vmvideoswapsizelabel = new JLabel("Video vSwap (MB)", SwingConstants.LEFT);
		final JLabel vmstoragesizelabel = new JLabel("VM Storage (MB)", SwingConstants.LEFT);
		final JLabel vmmemoryoverheadlabel = new JLabel("Memory Overhead (MB)", SwingConstants.LEFT);
		final JLabel vmstoragesizetotallabel = new JLabel("Total VM Storage (TB)", SwingConstants.LEFT);
		final JLabel vmmemorylabel = new JLabel("Total VM Memory (MB)", SwingConstants.LEFT);

		final JLabel vmfullclonesizelabelvalue = new JLabel("0final ");
		final JLabel vmdeltasizelabelvalue = new JLabel("0");
		final JLabel vmpersistentdisksizelabelvalue = new JLabel("0final ");
		final JLabel vmdisposabledisksizelabelvalue = new JLabel("0final ");
		final JLabel vmlogsizelabelvalue = new JLabel("0");
		final JLabel vmswapsizelabelvalue = new JLabel("0");
		final JLabel vmvideoswapsizelabelvalue = new JLabel("0");
		final JLabel vmstoragesizelabelvalue = new JLabel("0");
		final JLabel vmmemoryoverheadlabelvalue = new JLabel("0final ");
		final JLabel vmstoragesizetotallabelvalue = new JLabel("0final ");
		final JLabel vmmemorylabelvalue = new JLabel("0");

		vmfullclonesizelabelvalue.setBorder(Grayline);
		vmdeltasizelabelvalue.setBorder(Grayline);
		vmpersistentdisksizelabelvalue.setBorder(Grayline);
		vmdisposabledisksizelabelvalue.setBorder(Grayline);
		vmlogsizelabelvalue.setBorder(Grayline);
		vmswapsizelabelvalue.setBorder(Grayline);
		vmvideoswapsizelabelvalue.setBorder(Grayline);
		vmstoragesizelabelvalue.setBorder(Grayline);
		vmmemoryoverheadlabelvalue.setBorder(Grayline);
		vmstoragesizetotallabelvalue.setBorder(Grayline);
		vmmemorylabelvalue.setBorder(Grayline);

		vmfullclonesizelabelvalue.setName("vmfullclonesizelabelvalue");
		vmdeltasizelabelvalue.setName("vmdeltasizelabelvalue");
		vmpersistentdisksizelabelvalue.setName("vmpersistentdisksizelabelvalue");
		vmdisposabledisksizelabelvalue.setName("vmdisposabledisksizelabelvalue");
		vmlogsizelabelvalue.setName("vmlogsizelabelvalue");
		vmswapsizelabelvalue.setName("vmswapsizelabelvalue");
		vmvideoswapsizelabelvalue.setName("vmvideoswapsizelabelvalue");
		vmstoragesizelabelvalue.setName("vmstoragesizelabelvalue");
		vmmemoryoverheadlabelvalue.setName("vmmemoryoverheadlabelvalue");
		vmstoragesizetotallabelvalue.setName("vmstoragesizetotallabelvalue");
		vmmemorylabelvalue.setName("vmmemorylabelvalue");

		j.add(vmfullclonesizelabel, "cell 0 0");
		j.add(vmfullclonesizelabelvalue, "w 50!");
		j.add(vmdeltasizelabel, "cell 0 1");
		j.add(vmdeltasizelabelvalue, "w 50!");
		j.add(vmpersistentdisksizelabel, "cell 0 2");
		j.add(vmpersistentdisksizelabelvalue, "w 50!");
		j.add(vmdisposabledisksizelabel, "cell 0 3");
		j.add(vmdisposabledisksizelabelvalue, "w 50!");
		j.add(vmlogsizelabel, "cell 0 4");
		j.add(vmlogsizelabelvalue, "w 50!");
		j.add(vmswapsizelabel, "cell 2 0");
		j.add(vmswapsizelabelvalue, "w 50!");
		j.add(vmvideoswapsizelabel, "cell 2 1");
		j.add(vmvideoswapsizelabelvalue, "w 50!");
		j.add(vmstoragesizelabel, "cell 2 2");
		j.add(vmstoragesizelabelvalue, "w 50!");
		j.add(vmmemoryoverheadlabel, "cell 2 3");
		j.add(vmmemoryoverheadlabelvalue, "w 50!");
		j.add(vmstoragesizetotallabel, "cell 4 0");
		j.add(vmstoragesizetotallabelvalue, "w 50!");
		j.add(vmmemorylabel, "cell 4 1");
		j.add(vmmemorylabelvalue, "w 50!");

		return j;
	}

	/**
	 * 
	 * 
	 * 
	 * @return JPanel
	 */
	public static JPanel createXenInfraResultPanel() {

		final JPanel j = new JPanel();

		final MigLayout layout = new MigLayout("gap 6! 0.5!, ins 5", "", "");
		j.setLayout(layout);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JLabel xenvccountlabel = new JLabel(vdicalc.MESSAGES.getString("xenvccount.result"), SwingConstants.LEFT);
		final JLabel xendeliverycontrollerlabel = new JLabel(vdicalc.MESSAGES.getString("xendeliverycontroller.result"),
				SwingConstants.LEFT);

		final JLabel xenvccountlabelvalue = new JLabel("0");
		final JLabel xendeliverycontrollerlabelvalue = new JLabel("0");

		xenvccountlabelvalue.setBorder(Grayline);
		xendeliverycontrollerlabelvalue.setBorder(Grayline);

		xenvccountlabelvalue.setName("xenvccountlabelvalue");
		xendeliverycontrollerlabelvalue.setName("xendeliverycontrollerlabelvalue");

		j.add(xenvccountlabel);
		j.add(xenvccountlabelvalue, "w 50!");
		j.add(xendeliverycontrollerlabel);
		j.add(xendeliverycontrollerlabelvalue, "w 50!");

		return j;
	}

	/**
	 * Constructor for CustomPanels.
	 */
	protected custompanels() {
		Grayline = BorderFactory.createLineBorder(Color.GRAY);
		Raisedbevel = BorderFactory.createRaisedBevelBorder();
	}

	/**
	 * Method actionPerformed.
	 * 
	 * @param e ActionEvent
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * Create panel object for multiple VM configuration
	 * 
	 * @param name String
	 * @return JPanel
	 **/
	@SuppressWarnings({})
	public JPanel createVMPanel(String name) {
		final vdicalc mainframe = null;

		final JPanel j = new JPanel();
		j.setName(name);

		final MigLayout layout2 = new MigLayout("ins 3, gap 10px -4px", "", "");
		j.setLayout(layout2);
		j.setBorder(Raisedbevel);
		j.setBackground(Color.LIGHT_GRAY);

		final JPanel panel_userprofile = new JPanel(); // Add JPanel for User Profile
		final JLabel vmvcpucombolabel = new JLabel(vdicalc.MESSAGES.getString("vmvcpu.view"), SwingConstants.LEFT);
		final String[] vmvcpucombovalues = { "1", "2", "4", "8" };
		final JComboBox<String> vmvcpucombo = new JComboBox<>(vmvcpucombovalues);
		final JLabel vmvcpumhzlabel = new JLabel(vdicalc.MESSAGES.getString("vmvcpumhz.view"), SwingConstants.LEFT);
		final JTextField vmvcpumhztxt = new JTextField(vdicalc.DEFAULTS.getString("vmvcpumhztxt.default"), 2);
		final JLabel vmvcpumhztotalsize = new JLabel("0000", SwingConstants.LEFT); // Label for total vCPU MHZ in use
																					// per VM
		final JLabel vmvcpumhzoverheadlabel = new JLabel(vdicalc.MESSAGES.getString("vmvcpumhzoverhead.view"),
				SwingConstants.LEFT);
		final String[] vmvcpumhzoverheadcombovalues = { "0", "10", "15", "20", "25", "30" };
		final JComboBox<String> vmvcpumhzoverheadcombo = new JComboBox<>(vmvcpumhzoverheadcombovalues);
		final JLabel vmmemorysizelabel = new JLabel(vdicalc.MESSAGES.getString("vmmemorysize.view"),
				SwingConstants.RIGHT);
		final JTextField vmmemorysizetxt = new JTextField(vdicalc.DEFAULTS.getString("vmmemorysizetxt.default"), 3);
		final JLabel vmmemoryreservationlabel = new JLabel(vdicalc.MESSAGES.getString("vmmemoryreservation.view"),
				SwingConstants.RIGHT);
		final String[] vmmemoryreservationvalues = { "0", "10", "15", "20", "30", "40", "50", "60", "70", "80", "90",
				"100" };
		final JComboBox<String> vmmemoryreservationcombo = new JComboBox<>(vmmemoryreservationvalues);
		final JLabel vmvramlabel = new JLabel(vdicalc.MESSAGES.getString("vmdisplay3d.view"));
		final String[] vmvramcombovalues = { State.OFF.toString(), "64", "128", "256", "512" };
		final JComboBox<String> vmvramcombo = new JComboBox<>(vmvramcombovalues);
		final JLabel vmdisplaynumbercombolabel = new JLabel(vdicalc.MESSAGES.getString("vmdisplaynumber.view"));
		final String[] vmdisplaynumbercombovalues = { "1", "2", "3", "4" };
		final JComboBox<String> vmdisplaynumbercombo = new JComboBox<>(vmdisplaynumbercombovalues);
		final JLabel vmdisplayresolutioncombolabel = new JLabel(vdicalc.MESSAGES.getString("vmdisplayresolution.view"));
		final String[] vmdisplayresolutioncombovalues = { "1280x800", "1920x1200", "2560x1600" };
		final JComboBox<String> vmdisplayresolutioncombo = new JComboBox<>(vmdisplayresolutioncombovalues);
		final JLabel desktoppooltypecombolabel = new JLabel(vdicalc.MESSAGES.getString("desktoppooltype.view"));
		final String[] desktoppooltypecombovalues = { "Linked", "Full" };
		final JComboBox<String> desktoppooltypecombo = new JComboBox<>(desktoppooltypecombovalues);
		final JLabel vmdisposabledisksizetxtlabel = new JLabel(
				vdicalc.MESSAGES.getString("vmdisposabledisksizetxt.view"), SwingConstants.LEFT);
		final JTextField vmdisposabledisksizetxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmdisposabledisksizetxt.default"), 4);
		final JLabel vmpowerstatecombolabel = new JLabel(vdicalc.MESSAGES.getString("vmpowerstate.view"),
				SwingConstants.LEFT);
		final String[] vmpowerstatecombovalues = { State.ON.toString(), State.OFF.toString(),
				State.SUSPEND.toString() };
		final JComboBox<String> vmpowerstatecombo = new JComboBox<>(vmpowerstatecombovalues);
		final JLabel vmrefreshratecombolabel = new JLabel(vdicalc.MESSAGES.getString("vmrefreshrate.view"),
				SwingConstants.LEFT);
		final String[] vmrefreshratecombovalues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "25", "30",
				"40", "50", "60", "70", "80", "90", "100" };
		final JComboBox<String> vmrefreshratecombo = new JComboBox<>(vmrefreshratecombovalues);
		final JLabel vmrefreshratesize = new JLabel("0000", SwingConstants.LEFT); // Label for total delta disk size per
																					// VM
		final JLabel poolsnapshotcountcombolabel = new JLabel(vdicalc.MESSAGES.getString("poolsnapshotcount.view"),
				SwingConstants.LEFT);
		final String[] poolsnapshotcountcombovalues = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		final JComboBox<String> poolsnapshotcountcombo = new JComboBox<>(poolsnapshotcountcombovalues);
		final JLabel vmparentcounttxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmparentcount.view"),
				SwingConstants.LEFT);
		final JTextField vmparentcounttxt = new JTextField(vdicalc.DEFAULTS.getString("vmparentcounttxt.default"), 3);
		final JLabel vmthinsizeparentvmtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmthinsizeparentvm.view"),
				SwingConstants.LEFT);
		final JTextField vmthinsizeparentvmtxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmthinsizeparentvmtxt.default"), 4);
		final JLabel vmpersistentdisksizetxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmpersistentdisksize.view"),
				SwingConstants.LEFT);
		final JTextField vmpersistentdisksizetxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmpersistentdisksizetxt.default"), 4);
		final JLabel vmfullsizeparentvmtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmfullsizeparentvm.view"),
				SwingConstants.LEFT);
		final JTextField vmfullsizeparentvmtxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmfullsizeparentvmtxt.default"), 4);
		vmfullsizeparentvmtxt.setName("vmfullsizeparentvmtxt");
		final JLabel vmcounttxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmcount.view"), SwingConstants.RIGHT);
		final JTextField vmcounttxt = new JTextField(vdicalc.DEFAULTS.getString("vmcounttxt.default"), 4);
		final JLabel vmconcurrenttxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmconcurrent.view"),
				SwingConstants.RIGHT);
		final JTextField vmconcurrenttxt = new JTextField(vdicalc.DEFAULTS.getString("vmconcurrenttxt.default"), 4);
		final JLabel desktoppoolcounttxtlabel = new JLabel(vdicalc.MESSAGES.getString("desktoppoolcount.view"),
				SwingConstants.RIGHT);
		final JTextField desktoppoolcounttxt = new JTextField(vdicalc.DEFAULTS.getString("desktoppoolcounttxt.default"),
				3);
		final JLabel vmreplicasteadystateiotxtlabel = new JLabel(
				vdicalc.MESSAGES.getString("vmreplicasteadystateio.view"), SwingConstants.LEFT);
		final JTextField vmreplicasteadystateiotxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmreplicasteadystateiotxt.default"), 3);
		final JLabel vmbootIOtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmbootIO.view"), SwingConstants.LEFT);
		final JTextField vmbootIOtxt = new JTextField(vdicalc.DEFAULTS.getString("vmbootIOtxt.default"), 3);
		final JLabel vmcorelabel = new JLabel(vdicalc.MESSAGES.getString("vmperphysicalcore"), SwingConstants.RIGHT); // Label
																														// for
																														// VMs
																														// per
																														// physical
																														// core
		String[] vmcorecombovalues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20"};
		final JComboBox<String> vmcorecombo = new JComboBox<>(vmcorecombovalues);
		final JLabel vmdeltaIOtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmdeltaIOlinked.view"),
				SwingConstants.LEFT);
		final JTextField vmdeltaIOtxt = new JTextField(vdicalc.DEFAULTS.getString("vmdeltaIOtxt.default"), 3);
		final JLabel vmdeltareadIOtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmdeltareadIO.view"),
				SwingConstants.LEFT);
		final JTextField vmdeltareadIOtxt = new JTextField(vdicalc.DEFAULTS.getString("vmdeltareadIOtxt.default"), 3);
		final JLabel vmdeltawriteIOtxtlabellabel = new JLabel(vdicalc.MESSAGES.getString("vmdeltawriteIO.view"),
				SwingConstants.LEFT);
		final JLabel vmdeltawriteIOtxtlabel = new JLabel(vdicalc.DEFAULTS.getString("vmdeltawriteIOtxtlabel.default"),
				SwingConstants.LEFT);
		final JLabel vmpersistentIOtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmpersistentIO.view"),
				SwingConstants.LEFT);
		final JTextField vmpersistentIOtxt = new JTextField(vdicalc.DEFAULTS.getString("vmpersistentIOtxt.default"), 3);
		final JLabel vmpersistentreadIOtxtlabel = new JLabel(vdicalc.MESSAGES.getString("vmpersistentreadIO.view"),
				SwingConstants.LEFT);
		final JTextField vmpersistentreadIOtxt = new JTextField(
				vdicalc.DEFAULTS.getString("vmpersistentreadIOtxt.default"), 3);
		final JLabel vmpersistentwriteIOlabellabel = new JLabel(vdicalc.MESSAGES.getString("vmpersistentwriteIO.view"),
				SwingConstants.LEFT);
		final JLabel vmpersistentwriteIOlabel = new JLabel(
				vdicalc.DEFAULTS.getString("vmpersistentwriteIOlabel.default"), SwingConstants.LEFT);

		/*
		 * Create UserProfile Radio Buttons
		 */
		final MigLayout layoutuserprofile = new MigLayout("ins 4, gap 20px, wmin 864, align center");
		panel_userprofile.setLayout(layoutuserprofile);
		panel_userprofile.setBackground(Color.LIGHT_GRAY);

		JRadioButton taskworkerButton = new JRadioButton("Task");
		taskworkerButton.setSelected(true);
		JRadioButton officeworkerButton = new JRadioButton("Office");
		JRadioButton knowledgeworkerButton = new JRadioButton("Knowledge");
		JRadioButton powerworkerButton = new JRadioButton("Power");

		ButtonGroup group = new ButtonGroup();
		group.add(taskworkerButton);
		group.add(officeworkerButton);
		group.add(knowledgeworkerButton);
		group.add(powerworkerButton);

		taskworkerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmvcpucombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmvcpucombo")));
				vmvcpumhztxt.setText(vdicalc.DEFAULTS.getString("taskworker.vmvcpumhztxt"));
				vmvcpumhzoverheadcombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmvcpumhzoverheadcombo")));
				vmmemorysizetxt.setText(vdicalc.DEFAULTS.getString("taskworker.vmmemorysizetxt"));
				desktoppooltypecombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.desktoppooltypecombo")));
				vmdeltaIOtxt.setText(vdicalc.DEFAULTS.getString("taskworker.vmdeltaIOtxt"));
				vmdisplaynumbercombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmdisplaynumbercombo")));
				vmdisplayresolutioncombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmdisplayresolutioncombo")));
				vmvramcombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmvramcombo")));
				vmcorecombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("taskworker.vmcorecombo")));
			}

		});

		officeworkerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmvcpucombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmvcpucombo")));
				vmvcpumhztxt.setText(vdicalc.DEFAULTS.getString("officeworker.vmvcpumhztxt"));
				vmvcpumhzoverheadcombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmvcpumhzoverheadcombo")));
				vmmemorysizetxt.setText(vdicalc.DEFAULTS.getString("officeworker.vmmemorysizetxt"));
				desktoppooltypecombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.desktoppooltypecombo")));
				vmdeltaIOtxt.setText(vdicalc.DEFAULTS.getString("officeworker.vmdeltaIOtxt"));
				vmdisplaynumbercombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmdisplaynumbercombo")));
				vmdisplayresolutioncombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmdisplayresolutioncombo")));
				vmvramcombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmvramcombo")));
				vmcorecombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("officeworker.vmcorecombo")));
			}

		});

		knowledgeworkerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmvcpucombo
						.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmvcpucombo")));
				vmvcpumhztxt.setText(vdicalc.DEFAULTS.getString("knowledgeworker.vmvcpumhztxt"));
				vmvcpumhzoverheadcombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmvcpumhzoverheadcombo")));
				vmmemorysizetxt.setText(vdicalc.DEFAULTS.getString("knowledgeworker.vmmemorysizetxt"));
				desktoppooltypecombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.desktoppooltypecombo")));
				vmdeltaIOtxt.setText(vdicalc.DEFAULTS.getString("knowledgeworker.vmdeltaIOtxt"));
				vmdisplaynumbercombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmdisplaynumbercombo")));
				vmdisplayresolutioncombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmdisplayresolutioncombo")));
				vmvramcombo
						.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmvramcombo")));
				vmcorecombo
						.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("knowledgeworker.vmcorecombo")));
			}

		});

		powerworkerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmvcpucombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmvcpucombo")));
				vmvcpumhztxt.setText(vdicalc.DEFAULTS.getString("powerworker.vmvcpumhztxt"));
				vmvcpumhzoverheadcombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmvcpumhzoverheadcombo")));
				vmmemorysizetxt.setText(vdicalc.DEFAULTS.getString("powerworker.vmmemorysizetxt"));
				desktoppooltypecombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.desktoppooltypecombo")));
				vmdeltaIOtxt.setText(vdicalc.DEFAULTS.getString("powerworker.vmdeltaIOtxt"));
				vmdisplaynumbercombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmdisplaynumbercombo")));
				vmdisplayresolutioncombo.setSelectedIndex(
						Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmdisplayresolutioncombo")));
				vmvramcombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmvramcombo")));
				vmcorecombo.setSelectedIndex(Integer.parseInt(vdicalc.DEFAULTS.getString("powerworker.vmcorecombo")));
			}

		});

		panel_userprofile.add(taskworkerButton);
		panel_userprofile.add(officeworkerButton);
		panel_userprofile.add(knowledgeworkerButton);
		panel_userprofile.add(powerworkerButton);

		j.add(panel_userprofile, "pos 0 0");

		/*
		 * Assign components to Column 0
		 */
		j.add(vmvcpucombolabel, "pos 5 45");
		j.add(vmvcpucombo, "pos 190 40, w 70!, " + vdicalc.comboHeight);
		vmvcpucombo.setToolTipText(vdicalc.MESSAGES.getString("vmvcpu.tooltip"));
		vmvcpucombo.setName("vmvcpucombo");
		vmvcpucombo.addFocusListener(this);
		vmvcpucombo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
		vmvcpucombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Populate label that dislpays total amount of MHz used per VM
				vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(),
						vmvcpucombo.getSelectedItem().toString(), vmvcpumhzoverheadcombo.getSelectedItem().toString()));

			}

		});

		j.add(vmvcpumhzlabel, "pos 5 68");
		j.add(vmvcpumhztxt, "pos 191 66, w 70!, " + vdicalc.TextfieldHeight);
		vmvcpumhztxt.setToolTipText(vdicalc.MESSAGES.getString("vmvcpumhz.tooltip"));
		vmvcpumhztxt.setName("vmvcpumhztxt");
		vmvcpumhztxt.addFocusListener(this);
		vmvcpumhztxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();
				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) > Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName() + ".maximum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}

				// Populate label that dislpays total amount of MHz used per VM
				vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(),
						vmvcpucombo.getSelectedItem().toString(), vmvcpumhzoverheadcombo.getSelectedItem().toString()));

			}

		});
		vmvcpumhztxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// Populate label that dislpays total amount of MHz used per VM
				vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(),
						vmvcpucombo.getSelectedItem().toString(), vmvcpumhzoverheadcombo.getSelectedItem().toString()));

			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

		});
		vmvcpumhztxt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Populate label that dislpays total amount of MHz used per VM
				vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(),
						vmvcpucombo.getSelectedItem().toString(), vmvcpumhzoverheadcombo.getSelectedItem().toString()));

			}

		});

		/*
		 * Label for total amount of MHz used per VM
		 */
		j.add(vmvcpumhztotalsize, "pos 150 72");
		vmvcpumhztotalsize.setFont(new Font("Serif", Font.PLAIN, 12));
		vmvcpumhztotalsize.setForeground(Color.DARK_GRAY);

		j.add(vmvcpumhzoverheadlabel, "pos 5 93");
		j.add(vmvcpumhzoverheadcombo, "pos 190 89, w 70!, " + vdicalc.comboHeight);
		vmvcpumhzoverheadcombo.setSelectedIndex(0);
		vmvcpumhzoverheadcombo.setToolTipText(vdicalc.MESSAGES.getString("vmvcpumhzoverhead.tooltip"));
		vmvcpumhzoverheadcombo.setName("vmvcpumhzoverheadcombo");
		vmvcpumhzoverheadcombo.addFocusListener(this);
		vmvcpumhzoverheadcombo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
		vmvcpumhzoverheadcombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Populate label that dislpays total amount of MHz used per VM
				vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(),
						vmvcpucombo.getSelectedItem().toString(), vmvcpumhzoverheadcombo.getSelectedItem().toString()));

			}

		});

		j.add(desktoppooltypecombolabel, "pos 5 263");
		if (functions.isMac() || functions.isLinux()) {
			j.add(desktoppooltypecombo, "pos 190 261, wmax 92, " + vdicalc.comboHeight);
		} else if (functions.isWindows()) {
			j.add(desktoppooltypecombo, "pos 190 261, w 70!, " + vdicalc.comboHeight);
		}
		desktoppooltypecombo.setToolTipText(vdicalc.MESSAGES.getString("desktoppooltype.tooltip"));
		desktoppooltypecombo.setName("desktoppooltypecombo");
		desktoppooltypecombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent event) {
				final int state = event.getStateChange();
				if (state == ItemEvent.SELECTED) {
					if (event.getItem().toString().equalsIgnoreCase("linked")) {
						vmpersistentdisksizetxt.setText(vdicalc.DEFAULTS.getString("vmpersistentdisksizetxt.default"));
						vmpersistentdisksizetxt.setEnabled(true);
						vmpersistentIOtxt.setText(vdicalc.DEFAULTS.getString("vmpersistentIOtxt.default"));
						vmpersistentIOtxt.setEnabled(true);
						vmpersistentreadIOtxt.setText(vdicalc.DEFAULTS.getString("vmpersistentreadIOtxt.default"));
						vmpersistentreadIOtxt.setEnabled(true);
						vmdeltareadIOtxt.setText(vdicalc.DEFAULTS.getString("vmdeltareadIOtxt.default"));
						vmdeltareadIOtxt.setEnabled(true);
						vmdeltaIOtxtlabel.setText(vdicalc.MESSAGES.getString("vmdeltaIOlinked.view"));
						vmreplicasteadystateiotxt
								.setText(vdicalc.DEFAULTS.getString("vmreplicasteadystateiotxt.default"));
						vmreplicasteadystateiotxt.setEnabled(true);
						vmdisposabledisksizetxt.setText(vdicalc.DEFAULTS.getString("vmdisposabledisksizetxt.default"));
						vmdisposabledisksizetxt.setEnabled(true);
						vmrefreshratecombo.setEnabled(true);
						vmthinsizeparentvmtxt.setText(vdicalc.DEFAULTS.getString("vmthinsizeparentvmtxt.default"));
						vmthinsizeparentvmtxt.setEnabled(true);
						poolsnapshotcountcombo.setSelectedIndex(1);
						poolsnapshotcountcombo.setEnabled(true);

					} else if (event.getItem().toString().equalsIgnoreCase("full")) {
						vmpersistentdisksizetxt.setText(vdicalc.DEFAULTS.getString("vmpersistentdisksizetxt.default"));
						vmpersistentdisksizetxt.setEnabled(false);
						vmpersistentIOtxt.setText(vdicalc.DEFAULTS.getString("vmpersistentIOtxt.default"));
						vmpersistentIOtxt.setEnabled(false);
						vmpersistentreadIOtxt.setText(vdicalc.DEFAULTS.getString("vmpersistentreadIOtxt.default"));
						vmpersistentreadIOtxt.setEnabled(false);
						vmdeltareadIOtxt.setText(vdicalc.DEFAULTS.getString("vmdeltareadIOtxt.default"));
						vmdeltareadIOtxt.setEnabled(false);
						vmdeltaIOtxtlabel.setText(vdicalc.MESSAGES.getString("vmdeltaIOfull.view"));
						vmreplicasteadystateiotxt
								.setText(vdicalc.DEFAULTS.getString("vmreplicasteadystateiotxt.default"));
						vmreplicasteadystateiotxt.setEnabled(false);
						vmdisposabledisksizetxt.setText(vdicalc.DEFAULTS.getString("vmdisposabledisksizetxt.default"));
						vmdisposabledisksizetxt.setEnabled(false);
						vmrefreshratecombo.setEnabled(false);
						vmthinsizeparentvmtxt.setText(vdicalc.DEFAULTS.getString("vmthinsizeparentvmtxt.default"));
						vmthinsizeparentvmtxt.setEnabled(false);
						poolsnapshotcountcombo.setSelectedIndex(1);
						poolsnapshotcountcombo.setEnabled(false);
					}
				}
			}

		});

		j.add(vmdisposabledisksizetxtlabel, "pos 5 142");
		j.add(vmdisposabledisksizetxt, "pos 190 140, w 70!, " + vdicalc.TextfieldHeight);
		vmdisposabledisksizetxt.setToolTipText(vdicalc.MESSAGES.getString("vmdisposabledisksize.tooltip"));
		vmdisposabledisksizetxt.setName("vmdisposabledisksizetxt");
		vmdisposabledisksizetxt.addFocusListener(this);
		vmdisposabledisksizetxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

			}
		});

		j.add(vmcounttxtlabel, "pos 5 166");
		j.add(vmcounttxt, "pos 190 164, w 70!, " + vdicalc.TextfieldHeight);
		vmcounttxt.setName("vmcounttxt");
		vmcounttxt.setToolTipText(vdicalc.MESSAGES.getString("vmcount.tooltip"));
		vmcounttxt.addFocusListener(this);
		vmcounttxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) > Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".maximum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}

			}
		});

		j.add(vmconcurrenttxtlabel, "pos 5 190");
		j.add(vmconcurrenttxt, "pos 190 188, w 70!, " + vdicalc.TextfieldHeight);
		vmconcurrenttxt.setName("vmconcurrenttxt");
		vmconcurrenttxt.setToolTipText(vdicalc.MESSAGES.getString("vmconcurrent.tooltip"));
		vmconcurrenttxt.addFocusListener(this);
		vmconcurrenttxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) > (Integer.parseInt(vmcounttxt.getText()))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("invalid.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vmcounttxt.getText());
					tf.requestFocus();
				}

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				}
			}
		});

		j.add(vmreplicasteadystateiotxtlabel, "pos 5 215");
		j.add(vmreplicasteadystateiotxt, "pos 190 212, w 70!, " + vdicalc.TextfieldHeight);
		vmreplicasteadystateiotxt.setToolTipText(vdicalc.MESSAGES.getString("vmreplicasteadystateio.tooltip"));
		vmreplicasteadystateiotxt.setName("vmreplicasteadystateiotxt");
		vmreplicasteadystateiotxt.addFocusListener(this);
		vmreplicasteadystateiotxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(getRootPane(), vdicalc.MESSAGES.getString("outofbounds.validation"),
							null, JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
			}
		});
		j.add(vmbootIOtxtlabel, "pos 5 239");
		j.add(vmbootIOtxt, "pos 190 236, w 70!, " + vdicalc.TextfieldHeight);
		vmbootIOtxt.setToolTipText(vdicalc.MESSAGES.getString("vmbootIO.tooltip"));
		vmbootIOtxt.setName("vmbootIOtxt");
		vmbootIOtxt.addFocusListener(this);
		vmbootIOtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

			}
		});

		/*
		 * Label and combo for number of VMs per core
		 */
		j.add(vmcorelabel, "pos 5 118");
		j.add(vmcorecombo, "pos 190 116, w 70!, " + vdicalc.TextfieldHeight);
		vmcorecombo.setName("vmcorecombo");
		vmcorecombo.setSelectedIndex(4);
		vmcorecombo.addActionListener(this);
		vmcorecombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		/*
		 * Assign components to Column 1
		 */
		j.add(vmmemorysizelabel, "pos 285 45");
		j.add(vmmemorysizetxt, "pos 465 42, w 70!, " + vdicalc.TextfieldHeight);
		vmmemorysizetxt.setToolTipText(vdicalc.MESSAGES.getString("vmmemorysize.tooltip"));
		vmmemorysizetxt.setName("vmmemorysizetxt");
		vmmemorysizetxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();
				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) > Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".maximum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}
			}
		});

		j.add(vmmemoryreservationlabel, "pos 285 68");
		if (functions.isMac() || functions.isLinux()) {
			j.add(vmmemoryreservationcombo, "pos 465 65, w 70!, " + vdicalc.comboHeight);
		} else if (functions.isWindows()) {
			j.add(vmmemoryreservationcombo, "pos 465 65, w 70!, " + vdicalc.comboHeight);
		}
		vmmemoryreservationcombo.setToolTipText(vdicalc.MESSAGES.getString("vmmemoryreservation.tooltip"));
		vmmemoryreservationcombo.setName("vmmemoryreservationcombo");
		j.add(vmpowerstatecombolabel, "pos 285 93");
		if (functions.isMac() || functions.isLinux()) {
			j.add(vmpowerstatecombo, "pos 465 90, w 103!, " + vdicalc.comboHeight);
		} else if (functions.isWindows()) {
			j.add(vmpowerstatecombo, "pos 465 90, w 70!, " + vdicalc.comboHeight);
		}
		vmpowerstatecombo.setToolTipText(vdicalc.MESSAGES.getString("vmpowerstate.tooltip"));
		vmpowerstatecombo.setName("vmpowerstatecombo");
		j.add(vmrefreshratecombolabel, "pos 285 118");
		j.add(vmrefreshratecombo, "pos 465 114, w 70!, " + vdicalc.comboHeight);

		vmrefreshratecombo.setSelectedIndex(9);
		vmrefreshratecombo.setToolTipText(vdicalc.MESSAGES.getString("vmrefreshrate.tooltip"));
		vmrefreshratecombo.setName("vmrefreshratecombo");
		// Calculate delta size for displaying in the UI
		vmrefreshratecombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {

				final Integer i = (Integer.parseInt(vmfullsizeparentvmtxt.getText())
						* Integer.parseInt(vmrefreshratecombo.getSelectedItem().toString())) / 100;
				vmrefreshratesize.setText(i.toString());

			}

		});

		j.add(vmrefreshratesize, "pos 425 122");
		vmrefreshratesize.setFont(new Font("Serif", Font.PLAIN, 12));
		vmrefreshratesize.setForeground(Color.DARK_GRAY);
		// Simulate a action event
		vmrefreshratecombo.setSelectedIndex(9);
		final ActionEvent ae = new ActionEvent(vmrefreshratecombo, ActionEvent.ACTION_PERFORMED, "");
		vmrefreshratecombo.dispatchEvent(ae);

		j.add(poolsnapshotcountcombolabel, "pos 285 142");
		j.add(poolsnapshotcountcombo, "pos 465 138, w 70!, " + vdicalc.comboHeight);
		poolsnapshotcountcombo.setSelectedIndex(1);
		poolsnapshotcountcombo.setToolTipText(vdicalc.MESSAGES.getString("poolsnapshotcount.tooltip"));
		poolsnapshotcountcombo.setName("poolsnapshotcountcombo");
		j.add(vmparentcounttxtlabel, "pos 285 166");
		j.add(vmparentcounttxt, "pos 465 163, w 70!, " + vdicalc.TextfieldHeight);
		vmparentcounttxt.addFocusListener(this);
		vmparentcounttxt.setToolTipText(vdicalc.MESSAGES.getString("vmparentcount.tooltip"));
		vmparentcounttxt.setName("vmparentcounttxt");
		vmparentcounttxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) > Integer.parseInt(desktoppoolcounttxt.getText())) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("mustbelowerthannumberdesktoppools.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(desktoppoolcounttxt.getText());
					tf.requestFocus();
				}

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
			}
		});

		j.add(desktoppoolcounttxtlabel, "pos 285 190");
		if (functions.isMac() || functions.isLinux()) {
			j.add(desktoppoolcounttxt, "pos 465 187, w 70!, " + vdicalc.TextfieldHeight);
		} else if (functions.isWindows()) {
			j.add(desktoppoolcounttxt, "pos 465 187, w 70!, " + vdicalc.TextfieldHeight);
		}
		desktoppoolcounttxt.addFocusListener(this);
		desktoppoolcounttxt.setToolTipText(vdicalc.MESSAGES.getString("desktoppoolcount.tooltip"));
		desktoppoolcounttxt.setName("desktoppoolcounttxt");
		desktoppoolcounttxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();
				if (Integer.parseInt(tf.getText()) > Integer.parseInt(vmcounttxt.getText())) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("mustbelowerthannumberdesktops.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe,
							vdicalc.MESSAGES.getString("positivenumbersonly.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				}
				if ((Integer.parseInt(vmcounttxt.getText()) / Integer.parseInt(tf.getText())) > Integer
						.parseInt(vdicalc.DEFAULTS.getString("desktoppoollimittxt.maximum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					final Integer i = (Integer.parseInt(vmcounttxt.getText())
							/ Integer.parseInt(vdicalc.DEFAULTS.getString("desktoppoollimittxt.maximum")) + 1);
					tf.setText(i.toString());
					tf.requestFocus();
				}
			}
		});
		j.add(vmdeltaIOtxtlabel, "pos 285 215");
		j.add(vmdeltaIOtxt, "pos 465 212, w 70!, " + vdicalc.TextfieldHeight);
		vmdeltaIOtxt.addFocusListener(this);
		vmdeltaIOtxt.setToolTipText(vdicalc.MESSAGES.getString("vmdeltaIO.tooltip"));
		vmdeltaIOtxt.setName("vmdeltaIOtxt");
		j.add(vmdeltareadIOtxtlabel, "pos 285 239");
		j.add(vmdeltareadIOtxt, "pos 465 236, w 70!, " + vdicalc.TextfieldHeight);
		vmdeltareadIOtxt.setToolTipText(vdicalc.MESSAGES.getString("vmdeltareadIO.tooltip"));
		vmdeltareadIOtxt.setName("vmdeltareadIOtxt");
		vmdeltareadIOtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < 1 || Integer.parseInt(tf.getText()) > 100) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				} else {
					final int i = 100 - Integer.parseInt(vmdeltareadIOtxt.getText().toString());
					vmdeltawriteIOtxtlabel.setText(String.valueOf(i));
				}
			}
		});
		j.add(vmdeltawriteIOtxtlabellabel, "pos 285 263");
		j.add(vmdeltawriteIOtxtlabel, "pos 467 261");
		vmdeltawriteIOtxtlabel.setName("vmdeltawriteIOtxtlabel");

		/*
		 * Assign components to Column 2
		 */
		j.add(vmdisplaynumbercombolabel, "pos 570 45");
		j.add(vmdisplaynumbercombo, "pos 750 42, w 70!, " + vdicalc.comboHeight);
		vmdisplaynumbercombo.setToolTipText(vdicalc.MESSAGES.getString("vmdisplaynumber.tooltip"));
		vmdisplaynumbercombo.setName("vmdisplaynumbercombo");
		j.add(vmdisplayresolutioncombolabel, "pos 570 68");
		if (functions.isMac() || functions.isLinux()) {
			j.add(vmdisplayresolutioncombo, "pos 750 66, wmax 122, " + vdicalc.comboHeight);
		} else if (functions.isWindows()) {
			j.add(vmdisplayresolutioncombo, "pos 750 66, wmax 90, " + vdicalc.comboHeight);
		}
		vmdisplayresolutioncombo.setSelectedIndex(1);
		vmdisplayresolutioncombo.setToolTipText(vdicalc.MESSAGES.getString("vmdisplayresolution.tooltip"));
		vmdisplayresolutioncombo.setName("vmdisplayresolutioncombo");
		j.add(vmvramlabel, "pos 570 93");
		if (functions.isMac() || functions.isLinux()) {
			j.add(vmvramcombo, "pos 750 90, w 74!, " + vdicalc.comboHeight);
		} else if (functions.isWindows()) {
			j.add(vmvramcombo, "pos 750 90, w 70!, " + vdicalc.comboHeight);
		}
		vmvramcombo.setSelectedIndex(0);
		vmvramcombo.setToolTipText(vdicalc.MESSAGES.getString("vmvram.tooltip"));
		vmvramcombo.setName("vmvramcombo");
		j.add(vmthinsizeparentvmtxtlabel, "pos 570 118");
		j.add(vmthinsizeparentvmtxt, "pos 750 115, w 70!, " + vdicalc.TextfieldHeight);
		vmthinsizeparentvmtxt.setToolTipText(vdicalc.MESSAGES.getString("vmthinsizeparentvm.tooltip"));
		vmthinsizeparentvmtxt.setName("vmthinsizeparentvmtxt");
		vmthinsizeparentvmtxt.addFocusListener(this);
		vmthinsizeparentvmtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) > Integer.parseInt(vmfullsizeparentvmtxt.getText())) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vmfullsizeparentvmtxt.getText());
					tf.requestFocus();
				}
				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

			}
		});

		j.add(vmpersistentdisksizetxtlabel, "pos 570 142");
		j.add(vmpersistentdisksizetxt, "pos 750 139, w 70!, " + vdicalc.TextfieldHeight);
		vmpersistentdisksizetxt.setToolTipText(vdicalc.MESSAGES.getString("vmpersistentdisksize.tooltip"));
		vmpersistentdisksizetxt.setName("vmpersistentdisksizetxt");
		vmpersistentdisksizetxt.addFocusListener(this);
		vmpersistentdisksizetxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

			}
		});

		j.add(vmfullsizeparentvmtxtlabel, "pos 570 166");
		j.add(vmfullsizeparentvmtxt, "pos 750 163, w 70!, " + vdicalc.TextfieldHeight);
		vmfullsizeparentvmtxt.addFocusListener(this);
		vmfullsizeparentvmtxt.setToolTipText(vdicalc.MESSAGES.getString("vmfullsizeparentvm.tooltip"));
		vmfullsizeparentvmtxt.setName("vmfullsizeparentvmtxt");
		vmfullsizeparentvmtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}

				if (Integer.parseInt(tf.getText()) > Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".maximum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".maximum"));
					tf.requestFocus();
				}

			}
		});

		j.add(vmpersistentIOtxtlabel, "pos 570 190");
		j.add(vmpersistentIOtxt, "pos 750 187, w 70!, " + vdicalc.TextfieldHeight);
		vmpersistentIOtxt.setToolTipText(vdicalc.MESSAGES.getString("vmpersistentIO.tooltip"));
		vmpersistentIOtxt.setName("vmpersistentIOtxt");
		vmpersistentIOtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {
				final JTextField tf = (JTextField) e.getSource();
				if (Integer.parseInt(tf.getText()) < Integer
						.parseInt(vdicalc.DEFAULTS.getString(tf.getName().toString() + ".minimum"))) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".minimum"));
					tf.requestFocus();
				}
			}
		});

		j.add(vmpersistentreadIOtxtlabel, "pos 570 215");
		j.add(vmpersistentreadIOtxt, "pos 750 212, w 70!, " + vdicalc.TextfieldHeight);
		vmpersistentreadIOtxt.setToolTipText(vdicalc.MESSAGES.getString("vmpersistentreadIO.tooltip"));
		vmpersistentreadIOtxt.setName("vmpersistentreadIOtxt");
		vmpersistentreadIOtxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				final int i = 100 - Integer.parseInt(vmpersistentreadIOtxt.getText().toString());
				vmpersistentwriteIOlabel.setText(String.valueOf(i));

			}
		});
		vmpersistentreadIOtxt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(final FocusEvent e) {

			}

			@Override
			public void focusLost(final FocusEvent e) {

				final JTextField tf = (JTextField) e.getSource();

				if (Integer.parseInt(tf.getText()) < 1 || Integer.parseInt(tf.getText()) > 100) {
					JOptionPane.showMessageDialog(mainframe, vdicalc.MESSAGES.getString("outofbounds.validation"), null,
							JOptionPane.ERROR_MESSAGE);
					tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
					tf.requestFocus();
				} else {
					final int i = 100 - Integer.parseInt(vmpersistentreadIOtxt.getText().toString());
					vmpersistentwriteIOlabel.setText(String.valueOf(i));
				}

			}
		});

		j.add(vmpersistentwriteIOlabellabel, "pos 570 239");
		j.add(vmpersistentwriteIOlabel, "pos 750 236");
		vmpersistentwriteIOlabel.setName("vmpersistentwriteIOlabel");

		// Populate label that dislpays total amount of MHz used per VM
		vmvcpumhztotalsize.setText(getVmvcpumhztotal(vmvcpumhztxt.getText(), vmvcpucombo.getSelectedItem().toString(),
				vmvcpumhzoverheadcombo.getSelectedItem().toString()));

		return j;
	}

	/**
	 * Method focusGained.
	 * 
	 * @param e FocusEvent
	 * @see java.awt.event.FocusListener#focusGained(FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent e) {

	}

	/**
	 * Method focusLost.
	 * 
	 * @param e FocusEvent
	 * @see java.awt.event.FocusListener#focusLost(FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {
		final JTextField tf = (JTextField) e.getSource();
		if (!(vdicalc.validationsobj.equalNumeric(tf.getText()))) {
			JOptionPane.showMessageDialog(getRootPane(), vdicalc.MESSAGES.getString("onlynumbersaccepted.validation"),
					null, JOptionPane.ERROR_MESSAGE, null);
			tf.setText(vdicalc.DEFAULTS.getString(tf.getName() + ".default"));
			tf.requestFocus();
		}

	}

	/**
	 * getVmvcpumhztotalsize
	 * 
	 * @param vmvcpumhztxt
	 * @param vmvcpucombo
	 * @param vmvcpumhzoverhead
	 * @return String
	 */
	final private String getVmvcpumhztotal(final String vmvcpumhztxt, final String vmvcpucombo,
			final String vmvcpumhzoverhead) {

		double tmpcoremhz = ((Integer.valueOf(vmvcpumhztxt) * Integer.valueOf(vmvcpucombo))
				* ((Integer.valueOf(vmvcpumhzoverhead) / 100f) + 1));

		return String.valueOf((int) tmpcoremhz);
	};

}
