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

import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.boxysystems.jgoogleanalytics.FocusPoint;
import com.boxysystems.jgoogleanalytics.JGoogleAnalyticsTracker;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * @author andreleibovici
 * @version $Revision: 1.0 $
 */
public final class functions {
	/**
	 * Field OS.
	 */
	private static final String OS = System.getProperty("os.name").toLowerCase(); // System
																					// OS

	/**
	 * Field SETTINGS.
	 */
	private static final ResourceBundle SETTINGS = ResourceBundle.getBundle("DefaultSettings");

	/**
	 * isLinux
	 * 
	 * @since 6.6
	 * @return boolean
	 */
	public static boolean isLinux() {
		return OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0;
	}

	/**
	 * isMac
	 * 
	 * @since 1.0
	 * @return boolean
	 **/
	public static boolean isMac() {
		return OS.indexOf("mac") >= 0;
	}

	/**
	 * isWindows
	 * 
	 * @since 1.0
	 * @return boolean
	 **/
	public static boolean isWindows() {
		return OS.indexOf("win") >= 0;
	}

	/**
	 * Convert a CreateVMPanel to specific hypervizor model
	 * 
	 * @param jTabbedPane_name
	 * @param name
	 */
	void createVMPanel_Convert_To(JTabbedPane jTabbedPane_name, final String name) {

		JTabbedPane j = new JTabbedPane(SwingConstants.TOP);
		j = jTabbedPane_name;

		if (name.equalsIgnoreCase("view")) {

			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "vmvramcombo", true, null, 0);

			final String[] s = { "Linked", "Full" };
			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "desktoppooltypecombo", true, s, 0);

		} else if (name.equalsIgnoreCase("xen")) {

			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "vmvramcombo", false, null, 0);

			final String[] s = { "Pooled", "Assigned" };
			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "desktoppooltypecombo", true, s, 0);

		} else if (name.equalsIgnoreCase("workspot")) {

			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "vmvramcombo", true, null, 0);

			final String[] s = { "Linked", "Full" };
			vdicalc.functionsobj.jPanelComponentPropertyChange(j, JComboBox.class, "desktoppooltypecombo", true, s, 0);

		}
	}

	/**
	 * Populate components matching strings and component names. The input string
	 * come from a BufferedReader object in the mainframe.
	 * 
	 * @param c
	 * @param sCurrentLine
	 * @since 1.0
	 * @param parent String
	 */
	@SuppressWarnings("unchecked")
	public void fileRead(Component c, final String parent, final String sCurrentLine) {

		final String[] tokens = sCurrentLine.split("[:]");

		if (c instanceof JTextField) {
			if (null != c.getName()) {
				if (tokens[0].trim().contentEquals(parent)) {
					if (tokens[1].trim().contentEquals(c.getName())) {
						((JTextField) c).setText(tokens[2].trim());
					}
				}
			}
		}

		if (c instanceof JComboBox) {
			if (null != c.getName()) {
				if (tokens[0].trim().contentEquals(parent)) {
					if (tokens[1].trim().contentEquals(c.getName())) {
						((JComboBox<String>) c).setSelectedItem(tokens[2].trim());
					}
				}
			}
		}
	}

	/**
	 * Add components to save file executing component type check
	 * 
	 * @param c
	 * @param bw
	 * @param parent String
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void fileWrite(final Component c, final String parent, final BufferedWriter bw) throws IOException {
		if (c instanceof JTextField) {
			if (null != c.getName()) {
				bw.write(parent);
				bw.write(" : ");
				bw.write(c.getName());
				bw.write(" : ");
				bw.write(((JTextField) c).getText());
				bw.newLine();
			}
		}
		if (c instanceof JComboBox) {
			if (null != c.getName()) {
				bw.write(parent);
				bw.write(" : ");
				bw.write(c.getName());
				bw.write(" : ");
				bw.write(((JComboBox<String>) c).getSelectedItem().toString());
				bw.newLine();
			}
		}
	}

	/**
	 * Function to retrieve GoogleAnalyticsTrackerID from a file in
	 * myvirtualcloud.net googleanalyticstracker.txt is hard-coded
	 * 
	 * @return String
	 **/
	public String getGoogleAnalyticsTrackers() {

		String googleAnalyticsTrackerID = null;
		BufferedReader reader = null;
		try {

			final URL url = new URL(SETTINGS.getString("googleanalyticstracker.url"));
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			googleAnalyticsTrackerID = reader.readLine().toLowerCase().trim();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return googleAnalyticsTrackerID;
	}

	/**
	 * Expire application
	 * 
	 * @return boolean
	 */
	public boolean isApplicationExpired() {
		final Calendar expireDate = Calendar.getInstance();
		expireDate.set(Integer.valueOf(SETTINGS.getString("expirydateyy.setting")),
				Integer.valueOf(SETTINGS.getString("expirydatemm.setting")),
				Integer.valueOf(SETTINGS.getString("expirydatedd.setting")));
		if (Calendar.getInstance().after(expireDate)) {
			return true;
		}
		return false;
	}

	/**
	 * Google Analytics Usage Tracking
	 * 
	 * @param eventType
	 * @since 1.0
	 * @param version   String
	 * @param trackerId String
	 */
	public void jGoogleAnalyticsTracker(final String eventType, final String version, final String trackerId) {
		final JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker("vdi_calculator", version, trackerId);
		final FocusPoint focusPoint = new FocusPoint(eventType.toLowerCase());
		tracker.trackAsynchronously(focusPoint);
	}

	/**
	 * Modify component properties in all JPanel tabs
	 * 
	 * @param panel
	 * @param objclass
	 * @param name
	 * @param enabled
	 * @param value
	 * @param index
	 **/
	@SuppressWarnings("unchecked")
	public void jPanelComponentPropertyChange(final JTabbedPane panel, final Object objclass, final String name,
			final boolean enabled, final String[] value, final int index) {
		JPanel jPanelTemp = null;
		JComboBox<Object> jComboTemp = null;
		JTextField jTextTemp = null;
		JLabel jLabelTemp = null;
		Component[] components = null;
		int totalTabs = 0;

		final Map<String, Component> componentMap = new HashMap<String, Component>();
		totalTabs = panel.getTabCount() - 1;

		for (int i = 1; i < totalTabs; i++) {
			componentMap.clear();
			jPanelTemp = (JPanel) panel.getComponentAt(i);
			components = jPanelTemp.getComponents();
			for (int n = 0; n < components.length; ++n) {
				if (null != components[n].getName()) {
					componentMap.put(components[n].getName(), components[n]);
				}
			}

			if (JComboBox.class.equals(objclass)) {
				jComboTemp = (JComboBox<Object>) componentMap.get(name);
				if (enabled) {
					jComboTemp.setSelectedIndex(index);
					jComboTemp.setEnabled(true);
				} else {
					jComboTemp.setSelectedIndex(index);
					jComboTemp.setEnabled(false);
				}
				if (null != value) {
					jComboTemp.setEditable(true);
					jComboTemp.removeAllItems();
					jComboTemp.addItem(value[0]);
					jComboTemp.addItem(value[1]);
					jComboTemp.setEditable(false);
				}

			} else if (JTextField.class.equals(objclass)) {
				jTextTemp = (JTextField) componentMap.get(name);
				if (enabled) {
					jTextTemp.setEnabled(true);
				} else {
					jTextTemp.setEnabled(false);
				}
				jTextTemp.setText(String.valueOf(value));

			} else if (JLabel.class.equals(objclass)) {
				jLabelTemp = (JLabel) componentMap.get(name);
				if (enabled) {
					jLabelTemp.setEnabled(true);
				} else {
					jLabelTemp.setEnabled(false);
				}
				jLabelTemp.setText(String.valueOf(value));
			}
		}
	}

	/**
	 * Open webpage in default web browser
	 * 
	 * @param uri
	 */
	void openURI(final String uri) {

		try {
			java.awt.Desktop.getDesktop().browse(new URI(uri));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Print to PDF
	 * 
	 * @param brokertype
	 * @since 1.0
	 */
	public void printAction(final String brokertype) {

		final Document document = new Document(PageSize.LETTER);
		FileOutputStream fop = null;

		try {
			fop = new FileOutputStream("report_tmp.pdf");
			PdfWriter.getInstance(document, fop);
			document.open();
			document.addTitle("VDI Calculator");
			document.addAuthor("Leibovici. A");
			document.addCreationDate();
			final Font fontheader = FontFactory.getFont("Times-Roman", 12, Font.BOLD | Font.UNDERLINE);
			final Font fontcontent = FontFactory.getFont("Times-Roman", 11, Font.NORMAL);
			final LineSeparator line = new LineSeparator(1, 90, null, Element.ALIGN_CENTER, -2);

			Paragraph paragraph = new Paragraph("VDI Calculator by Andre Leibovici");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			paragraph = new Paragraph("myvirtualcloud.net");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(80);
			table.setSpacingBefore(10);

			document.add(new Paragraph("Hosts", fontheader));

			PdfPCell cell = new PdfPCell(new Phrase("Count", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(host.getCount()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Cores per Host", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(host.getCorescount()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Avg. Host CPU", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(host.getCoremhz()) + " MHz", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Host Memory - No TPS", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(host.getMemoryNoTPS() + " GB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Host Memory - TPS", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(host.getMemoryTPS() + " GB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Local Disk", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(host.getLocalswapsize()) + " GB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Desktops per Host", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(host.getVmcount()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Number of Clusters", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(cluster.getCount()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			document.add(table);

			document.add(Chunk.NEWLINE);
			document.add(line);
			document.add(Chunk.NEWLINE);

			if (brokertype.equalsIgnoreCase("view")) {
				document.add(new Paragraph("View Infrastructure", fontheader));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				document.add(new Paragraph("XenDesktop Infrastructure", fontheader));
			}

			table = new PdfPTable(2);
			table.setWidthPercentage(80);
			table.setSpacingBefore(10);

			cell = new PdfPCell(new Phrase("vCenter Servers", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(view.getVccount()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Connection Servers", fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(view.getCscount()), fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Security Servers", fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(view.getSscount()), fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Delivery Controllers", fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(String.valueOf(xen.getDdccount()), fontcontent));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			}
			document.add(table);

			document.add(Chunk.NEWLINE);
			document.add(line);
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Storage Capacity", fontheader));

			table = new PdfPTable(2);
			table.setWidthPercentage(80);
			table.setSpacingBefore(10);

			cell = new PdfPCell(new Phrase("Total Capacity", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(
					new Phrase(String.valueOf(vdicalc.storageobj.getUsablecapacity()) + " TB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Parent Capacity", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Master Image Capacity", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(
					new Phrase(String.valueOf(vdicalc.storageobj.getParentusablecapacity()) + " TB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Replica Capacity", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Private Clone Capacity", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(
					new Phrase(String.valueOf(vdicalc.storageobj.getReplicausablecapacity()) + " TB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Clone Capacity", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Snapshot Capacity", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(vdicalc.storageobj.cloneusablecapacity.toString() + " TB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Clone Datastores", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Snapshot Datastores", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(vdicalc.storageobj.datastorecount), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Clone Datastore Size", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Snapshot Datastore Size", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(vdicalc.storageobj.datastoresize) + " GB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Persistent Datastores", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("vDisk Datastores", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(vdicalc.storageobj.persistentdatastorecount), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Persistent Datastore Size", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("vDisk Datastores Size", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(
					new Phrase(String.valueOf(vdicalc.storageobj.persistentdatastoresize) + " GB", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			document.add(table);

			document.add(Chunk.NEWLINE);
			document.add(line);
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Storage IOps", fontheader));

			table = new PdfPTable(2);
			table.setWidthPercentage(80);
			table.setSpacingBefore(10);

			cell = new PdfPCell(new Phrase("Total Frontend IOPS", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(vdicalc.storageobj.getFrontendIOPS()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Total Backend IOPS", fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(String.valueOf(vdicalc.storageobj.getBackendIOPS()), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Clone Frontend IOPS", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Snapshot Frontend IOPS", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(storage.ClonefrontendIOPS.toString(), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Clone Backend IOPS", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("Snapshot Backend IOPS", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(storage.ClonebackendIOPS.toString(), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Persistent Frontend IOPS", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("vDisk Frontend IOPS", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(storage.PersistentfrontendIOPS.toString(), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			if (brokertype.equalsIgnoreCase("view")) {
				cell = new PdfPCell(new Phrase("Persistent Backend IOPS", fontcontent));
			} else if (brokertype.equalsIgnoreCase("xen")) {
				cell = new PdfPCell(new Phrase("vDisk Backend IOPS", fontcontent));
			}
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(storage.PersistentbackendIOPS.toString(), fontcontent));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			document.add(table);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		/*
		 * Open PDF
		 */
		try {
			final File myFile = new File("report_tmp.pdf");
			Desktop.getDesktop().open(myFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		builder.append("Functions []");
		return builder.toString();
	}

	/**
	 * Check and compare software version against current version. Returns false if
	 * version is not current Returns true if version is current
	 * 
	 * @param version
	 * @return Boolean
	 * @throws IOException
	 **/
	public Boolean versionCheck(final String version) throws IOException {

		String version_current = new String();
		BufferedReader reader = null;
		System.setProperty("http.keepAlive", "false");
		try {
			final URL url = new URL(SETTINGS.getString("versioncheck.url"));
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			version_current = reader.readLine().toLowerCase().trim();
		} finally {
			reader.close();
		}

		return version.equalsIgnoreCase(version_current) ? true : false;
	}

}
