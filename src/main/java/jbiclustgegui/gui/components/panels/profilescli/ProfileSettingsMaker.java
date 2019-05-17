/************************************************************************** 
 * Copyright 2017
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 *  
 * Created by Orlando Rocha (ornrocha@gmail.com) inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.gui.components.panels.profilescli;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.prefs.Preferences;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.analysis.profilecli.components.ProfileBiclusteringMethod;
import jbiclustgegui.gui.analysis.profilecli.components.ProfileBiclusteringMethodConfiguration;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.gui.components.dialogs.GSEAConfigurationDialog;
import jbiclustgegui.gui.components.dialogs.profilecli.ConfigureMethodToProfileDialog;
import pt.ornrocha.systemutils.OSystemUtils;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.ListSelectionModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileSettingsMaker.
 */
public class ProfileSettingsMaker extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The text fieldnameprofile. */
	private JTextField textFieldnameprofile;
	
	/** The btn new buttonsavepath. */
	private JButton btnNewButtonsavepath;
	
	/** The text fieldsavepath. */
	private JTextField textFieldsavepath;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The listmethodsprofile. */
	private JList<ProfileBiclusteringMethod> listmethodsprofile;
	
	/** The btn new buttonaddmethodconfig. */
	private JButton btnNewButtonaddmethodconfig;
	
	/** The scroll pane 1. */
	private JScrollPane scrollPane_1;
	
	/** The listconfigsmethodsprofile. */
	private JList<ProfileBiclusteringMethodConfiguration> listconfigsmethodsprofile;
	
	/** The listmodelmethodsconfigs. */
	private DefaultListModel<ProfileBiclusteringMethodConfiguration> listmodelmethodsconfigs;
	
	/** The btn new buttonremoveconfig. */
	private JButton btnNewButtonremoveconfig;
	
	/** The btnremoveallconfigurations. */
	private JButton btnremoveallconfigurations;
	
	/** The panel. */
	private JPanel panel;
	
	/** The chckbx perform in runtime. */
	private JCheckBox chckbxPerformInRuntime;
	
	/** The rdbtn new radio buttonontologizer. */
	private JRadioButton rdbtnNewRadioButtonontologizer;
	
	/** The rdbtn new radio buttontopgo. */
	private JRadioButton rdbtnNewRadioButtontopgo;
	
	/** The btn new buttonconfigure. */
	private JButton btnNewButtonconfigure;
	
	/** The lbl new label 1. */
	private JLabel lblNewLabel_1;
	
	/** The text fieldconfiguredengine. */
	private JTextField textFieldconfiguredengine;
	
	/** The gseaconfig. */
	private GSEAConfigurationContainer gseaconfig=null;
	
	/** The proj. */
	private Project proj;

	/** The spinner. */
	private JSpinner spinner;
	
	/** The lbl new label 2. */
	private JLabel lblNewLabel_2;
	
	/** The removedmethods. */
	private LinkedHashMap<String, Integer> removedmethods=new LinkedHashMap<>();
	
	/** The scroll pane 2. */
	private JScrollPane scrollPane_2;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The listpvalues. */
	private JList<Double> listpvalues;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The chckbx adjusted pvalues. */
	private JCheckBox chckbxAdjustedPvalues;
	
	/** The double text fieldpvalues. */
	private DoubleTextField doubleTextFieldpvalues;
	
	/** The btn add pvalue. */
	private JButton btnAddPvalue;
	
	/** The btn remove all. */
	private JButton btnRemoveAll;
	
	/** The listpvaluesmodel. */
	private DefaultListModel<Double> listpvaluesmodel;
	
	
   
	private JPanel panel_typeprocess;
	private JRadioButton rdbtnSingleThread;
	private JRadioButton rdbtnMultiThread;
	
	/** The addmethodconfig. */
	private static String ADDMETHODCONFIG="addmethodconfig";
	
	/** The removeconfig. */
	private static String REMOVECONFIG="removeconfig";
	
	/** The rmoveall. */
	private static String RMOVEALL="removeall";
	
	/** The openchooser. */
	private static String OPENCHOOSER="openchooser";
	
	/** The useontologizer. */
	private static String USEONTOLOGIZER="useontologizer";
	
	/** The usetopgo. */
	private static String USETOPGO="usetopgo";
	
	/** The usegsea. */
	private static String USEGSEA="usegsea";
	
	/** The configgsea. */
	private static String CONFIGGSEA="configgsea";
	
	/** The addpvalue. */
	private static String ADDPVALUE="addpvalue";
	
	/** The removepvalues. */
	private static String REMOVEPVALUES="removepvalues";
	
	
	private static String SINGLETHREAD="singlethread";
	private static String MULTITHREAD="multithread";
	/**
	 * Instantiates a new profile settings maker.
	 */
	public ProfileSettingsMaker() {
		initGUI();
		initComponents();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNewLabel = new JLabel("Name Profile:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(this.lblNewLabel, gbc_lblNewLabel);
		
		this.textFieldnameprofile = new JTextField();
		GridBagConstraints gbc_textFieldnameprofile = new GridBagConstraints();
		gbc_textFieldnameprofile.gridwidth = 2;
		gbc_textFieldnameprofile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldnameprofile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnameprofile.gridx = 1;
		gbc_textFieldnameprofile.gridy = 0;
		add(this.textFieldnameprofile, gbc_textFieldnameprofile);
		this.textFieldnameprofile.setColumns(10);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 20, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(this.scrollPane, gbc_scrollPane);
		
		this.listmethodsprofile = new JList<ProfileBiclusteringMethod>();
		this.listmethodsprofile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scrollPane.setViewportView(this.listmethodsprofile);
		
		this.scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 6;
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 1;
		add(this.scrollPane_1, gbc_scrollPane_1);
		
		this.listconfigsmethodsprofile = new JList<ProfileBiclusteringMethodConfiguration>();
		this.scrollPane_1.setViewportView(this.listconfigsmethodsprofile);
		
		this.btnNewButtonaddmethodconfig = new JButton("<html><center>Add new configuration<br> of a biclustering method<br>to profile</center></html> ");
		this.btnNewButtonaddmethodconfig.setIcon(new ImageIcon(ProfileSettingsMaker.class.getResource("/images/i24x24/forward.png")));
		GridBagConstraints gbc_btnNewButtonaddmethodconfig = new GridBagConstraints();
		gbc_btnNewButtonaddmethodconfig.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonaddmethodconfig.gridwidth = 2;
		gbc_btnNewButtonaddmethodconfig.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonaddmethodconfig.gridx = 2;
		gbc_btnNewButtonaddmethodconfig.gridy = 2;
		add(this.btnNewButtonaddmethodconfig, gbc_btnNewButtonaddmethodconfig);
		btnNewButtonaddmethodconfig.addActionListener(this);
		btnNewButtonaddmethodconfig.setActionCommand(ADDMETHODCONFIG);
		
		this.btnNewButtonremoveconfig = new JButton("<html><center>Remove<br>selected<br>configuration</center></html>");
		GridBagConstraints gbc_btnNewButtonremoveconfig = new GridBagConstraints();
		gbc_btnNewButtonremoveconfig.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonremoveconfig.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonremoveconfig.gridx = 3;
		gbc_btnNewButtonremoveconfig.gridy = 3;
		add(this.btnNewButtonremoveconfig, gbc_btnNewButtonremoveconfig);
		btnNewButtonremoveconfig.setActionCommand(REMOVECONFIG);
		btnNewButtonremoveconfig.addActionListener(this);
		
		this.btnremoveallconfigurations = new JButton("<html><center>Remove<br>all<br>configurations</center></html>");
		GridBagConstraints gbc_btnremoveallconfigurations = new GridBagConstraints();
		gbc_btnremoveallconfigurations.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnremoveallconfigurations.insets = new Insets(0, 0, 5, 5);
		gbc_btnremoveallconfigurations.gridx = 3;
		gbc_btnremoveallconfigurations.gridy = 4;
		add(this.btnremoveallconfigurations, gbc_btnremoveallconfigurations);
		btnremoveallconfigurations.setActionCommand(RMOVEALL);
		btnremoveallconfigurations.addActionListener(this);
		
		panel_typeprocess = new JPanel();
		panel_typeprocess.setBorder(new TitledBorder(null, "Execute each configuration using:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_typeprocess = new GridBagConstraints();
		gbc_panel_typeprocess.gridwidth = 2;
		gbc_panel_typeprocess.insets = new Insets(0, 0, 5, 5);
		gbc_panel_typeprocess.fill = GridBagConstraints.BOTH;
		gbc_panel_typeprocess.gridx = 2;
		gbc_panel_typeprocess.gridy = 6;
		add(panel_typeprocess, gbc_panel_typeprocess);
		GridBagLayout gbl_panel_typeprocess = new GridBagLayout();
		gbl_panel_typeprocess.columnWidths = new int[]{1,1};
		gbl_panel_typeprocess.rowHeights = new int[]{1};
		gbl_panel_typeprocess.columnWeights = new double[]{1.0,1.0};
		gbl_panel_typeprocess.rowWeights = new double[]{1.0};
		panel_typeprocess.setLayout(gbl_panel_typeprocess);
		
		rdbtnSingleThread = new JRadioButton("Single thread");
		GridBagConstraints gbc_rdbtnSingleThread = new GridBagConstraints();
		gbc_rdbtnSingleThread.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnSingleThread.gridx = 0;
		gbc_rdbtnSingleThread.gridy = 0;
		panel_typeprocess.add(rdbtnSingleThread, gbc_rdbtnSingleThread);
		rdbtnSingleThread.setActionCommand(SINGLETHREAD);
		rdbtnSingleThread.addActionListener(this);
		rdbtnSingleThread.setSelected(true);
		
		rdbtnMultiThread = new JRadioButton("Multi thread");
		GridBagConstraints gbc_rdbtnMultiThread = new GridBagConstraints();
		gbc_rdbtnMultiThread.gridx = 1;
		gbc_rdbtnMultiThread.gridy = 0;
		panel_typeprocess.add(rdbtnMultiThread, gbc_rdbtnMultiThread);
		rdbtnMultiThread.setActionCommand(MULTITHREAD);
		rdbtnMultiThread.addActionListener(this);
		rdbtnMultiThread.setSelected(false);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Gene Set Enrichment Analysis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 10;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 7;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.chckbxPerformInRuntime = new JCheckBox("Perform in Runtime");
		GridBagConstraints gbc_chckbxPerformInRuntime = new GridBagConstraints();
		gbc_chckbxPerformInRuntime.gridheight = 2;
		gbc_chckbxPerformInRuntime.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxPerformInRuntime.gridx = 0;
		gbc_chckbxPerformInRuntime.gridy = 0;
		this.panel.add(this.chckbxPerformInRuntime, gbc_chckbxPerformInRuntime);
		chckbxPerformInRuntime.setActionCommand(USEGSEA);
		chckbxPerformInRuntime.addActionListener(this);
		
		this.rdbtnNewRadioButtonontologizer = new JRadioButton("Ontologizer");
		GridBagConstraints gbc_rdbtnNewRadioButtonontologizer = new GridBagConstraints();
		gbc_rdbtnNewRadioButtonontologizer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButtonontologizer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButtonontologizer.gridx = 1;
		gbc_rdbtnNewRadioButtonontologizer.gridy = 0;
		this.panel.add(this.rdbtnNewRadioButtonontologizer, gbc_rdbtnNewRadioButtonontologizer);
		rdbtnNewRadioButtonontologizer.setActionCommand(USEONTOLOGIZER);
		rdbtnNewRadioButtonontologizer.addActionListener(this);
		
		this.lblNewLabel_1 = new JLabel("Configured:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		this.panel.add(this.lblNewLabel_1, gbc_lblNewLabel_1);
		
		this.textFieldconfiguredengine = new JTextField();
		GridBagConstraints gbc_textFieldconfiguredengine = new GridBagConstraints();
		gbc_textFieldconfiguredengine.gridwidth = 3;
		gbc_textFieldconfiguredengine.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldconfiguredengine.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldconfiguredengine.gridx = 3;
		gbc_textFieldconfiguredengine.gridy = 0;
		this.panel.add(this.textFieldconfiguredengine, gbc_textFieldconfiguredengine);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, "p-values", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 6;
		gbc_panel_2.gridy = 0;
		this.panel.add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1,1,1,1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,0.0,0.0,0.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.chckbxAdjustedPvalues = new JCheckBox("adjusted p-values");
		GridBagConstraints gbc_chckbxAdjustedPvalues = new GridBagConstraints();
		gbc_chckbxAdjustedPvalues.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAdjustedPvalues.gridx = 0;
		gbc_chckbxAdjustedPvalues.gridy = 0;
		this.panel_2.add(this.chckbxAdjustedPvalues, gbc_chckbxAdjustedPvalues);
		
		this.doubleTextFieldpvalues = new DoubleTextField();
		GridBagConstraints gbc_doubleTextFieldpvalues = new GridBagConstraints();
		gbc_doubleTextFieldpvalues.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldpvalues.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldpvalues.gridx = 0;
		gbc_doubleTextFieldpvalues.gridy = 1;
		this.panel_2.add(this.doubleTextFieldpvalues, gbc_doubleTextFieldpvalues);
		
		this.btnAddPvalue = new JButton("Add p-value");
		GridBagConstraints gbc_btnAddPvalue = new GridBagConstraints();
		gbc_btnAddPvalue.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddPvalue.gridx = 0;
		gbc_btnAddPvalue.gridy = 2;
		this.panel_2.add(this.btnAddPvalue, gbc_btnAddPvalue);
		btnAddPvalue.setActionCommand(ADDPVALUE);
		btnAddPvalue.addActionListener(this);
		
		this.btnRemoveAll = new JButton("Remove all");
		GridBagConstraints gbc_btnRemoveAll = new GridBagConstraints();
		gbc_btnRemoveAll.gridx = 0;
		gbc_btnRemoveAll.gridy = 3;
		this.panel_2.add(this.btnRemoveAll, gbc_btnRemoveAll);
		btnRemoveAll.setActionCommand(REMOVEPVALUES);
		btnRemoveAll.addActionListener(this);
		
		this.scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 8;
		gbc_scrollPane_2.gridy = 0;
		this.panel.add(this.scrollPane_2, gbc_scrollPane_2);
		
		this.panel_1 = new JPanel();
		this.scrollPane_2.setViewportView(this.panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.listpvalues = new JList<Double>();
		GridBagConstraints gbc_listpvalues = new GridBagConstraints();
		gbc_listpvalues.fill = GridBagConstraints.BOTH;
		gbc_listpvalues.gridx = 0;
		gbc_listpvalues.gridy = 0;
		this.panel_1.add(this.listpvalues, gbc_listpvalues);
		
		this.rdbtnNewRadioButtontopgo = new JRadioButton("TopGO");
		GridBagConstraints gbc_rdbtnNewRadioButtontopgo = new GridBagConstraints();
		gbc_rdbtnNewRadioButtontopgo.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButtontopgo.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButtontopgo.gridx = 1;
		gbc_rdbtnNewRadioButtontopgo.gridy = 1;
		this.panel.add(this.rdbtnNewRadioButtontopgo, gbc_rdbtnNewRadioButtontopgo);
		rdbtnNewRadioButtontopgo.setActionCommand(USETOPGO);
		rdbtnNewRadioButtontopgo.addActionListener(this);
		rdbtnNewRadioButtontopgo.setSelected(false);
		
		this.btnNewButtonconfigure = new JButton("Configure");
		GridBagConstraints gbc_btnNewButtonconfigure = new GridBagConstraints();
		gbc_btnNewButtonconfigure.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonconfigure.gridwidth = 3;
		gbc_btnNewButtonconfigure.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonconfigure.gridx = 3;
		gbc_btnNewButtonconfigure.gridy = 1;
		this.panel.add(this.btnNewButtonconfigure, gbc_btnNewButtonconfigure);
		btnNewButtonconfigure.setActionCommand(CONFIGGSEA);
		btnNewButtonconfigure.addActionListener(this);
		
		this.btnNewButtonsavepath = new JButton("Save profile at:");
		GridBagConstraints gbc_btnNewButtonsavepath = new GridBagConstraints();
		gbc_btnNewButtonsavepath.anchor = GridBagConstraints.EAST;
		gbc_btnNewButtonsavepath.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonsavepath.gridx = 0;
		gbc_btnNewButtonsavepath.gridy = 9;
		add(this.btnNewButtonsavepath, gbc_btnNewButtonsavepath);
		btnNewButtonsavepath.setActionCommand(OPENCHOOSER);
		btnNewButtonsavepath.addActionListener(this);
		
		this.textFieldsavepath = new JTextField();
		GridBagConstraints gbc_textFieldsavepath = new GridBagConstraints();
		gbc_textFieldsavepath.gridwidth = 3;
		gbc_textFieldsavepath.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldsavepath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldsavepath.gridx = 1;
		gbc_textFieldsavepath.gridy = 9;
		add(this.textFieldsavepath, gbc_textFieldsavepath);
		this.textFieldsavepath.setColumns(10);
		
		this.lblNewLabel_2 = new JLabel("Concurrent Processes:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 9;
		add(this.lblNewLabel_2, gbc_lblNewLabel_2);
		
		this.spinner = new JSpinner();
		this.spinner.setModel(new SpinnerNumberModel(1, 1, 200, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridwidth = 2;
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.gridx = 7;
		gbc_spinner.gridy = 9;
		add(this.spinner, gbc_spinner);
	}
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		
		DefaultListModel<ProfileBiclusteringMethod> modelListmethodsprofile = new DefaultListModel<ProfileBiclusteringMethod>();
		
		for (ProfileBiclusteringMethod pmethod : ProfileBiclusteringMethod.values()) {
			modelListmethodsprofile.addElement(pmethod);
		}
		listmethodsprofile.setModel(modelListmethodsprofile);
		
		listmodelmethodsconfigs = new DefaultListModel<ProfileBiclusteringMethodConfiguration>();
		listconfigsmethodsprofile.setModel(listmodelmethodsconfigs);
		
		listpvaluesmodel=new DefaultListModel<Double>();
		listpvalues.setModel(listpvaluesmodel);
		resetToDefault();
		/*chckbxPerformInRuntime.setSelected(false);
		performGSEA();
		rdbtnNewRadioButtonontologizer.setSelected(true);*/
		
	}
	
	
	/**
	 * Sets the current project.
	 *
	 * @param proj the new current project
	 */
	public void setCurrentProject(Project proj) {
		this.proj=proj;
	}
	
	
	/**
	 * Reset to default.
	 */
	public void resetToDefault() {
		listmodelmethodsconfigs.removeAllElements();
		chckbxPerformInRuntime.setSelected(false);
		performGSEA();
		rdbtnNewRadioButtonontologizer.setSelected(true);

	}
	
	
	/**
	 * Adds the method configuration.
	 */
	private void addMethodConfiguration() {

		ProfileBiclusteringMethod method=listmethodsprofile.getSelectedValue();

		if(method!=null) {

			ConfigureMethodToProfileDialog dialog=new ConfigureMethodToProfileDialog();
			try {
				int tag=dialog.showOpenDialog(method,proj, this);
				
					if(tag==ConfigureMethodToProfileDialog.APPROVE_OPTION) {
						int numberconfig=getNumberConfigurationsToMethod(method.getBiclusteringMethod());
						String sufixname="config_"+(numberconfig+1);

						ProfileBiclusteringMethodConfiguration methodconfig=new ProfileBiclusteringMethodConfiguration(method.getBiclusteringMethod(), 
								sufixname, 
								dialog.getMethodConfiguration(), 
								dialog.getNumberTimesToExecute());
						
						listmodelmethodsconfigs.addElement(methodconfig);
					}
		

			} catch (IOException | InterruptedException e) {
				Workbench.getInstance().error(e);
			}

		}
		else
			JOptionPane.showMessageDialog(this, "Please select one method", "warning", JOptionPane.INFORMATION_MESSAGE);

	}
	
	
	/**
	 * Gets the number configurations to method.
	 *
	 * @param method the method
	 * @return the number configurations to method
	 */
	private int getNumberConfigurationsToMethod(BiclusteringMethod method){
		
		if(listmodelmethodsconfigs.getSize()>0) {
			
			int nelems=0;
			
			for (int i = 0; i < listmodelmethodsconfigs.getSize(); i++) {
				ProfileBiclusteringMethodConfiguration config=listmodelmethodsconfigs.elementAt(i);
				if(config.getMethod().equals(method))
					nelems++;
			}
			
			if(removedmethods.containsKey(method.getAlgorithmID()))
				nelems=nelems+removedmethods.get(method.getAlgorithmID());
			return nelems;
		}
		else 
			return 0;
		
		
		
	}
	
	/**
	 * Removeconfiguration.
	 */
	private void removeconfiguration() {
		if(listmodelmethodsconfigs.getSize()>0) {
			int i =listconfigsmethodsprofile.getSelectedIndex();
			ProfileBiclusteringMethodConfiguration config=listconfigsmethodsprofile.getSelectedValue();
			String method=config.getMethod().getAlgorithmID();
			if(removedmethods.containsKey(method)) {
				int v=removedmethods.get(method);
				removedmethods.put(method, v+1);
			}
			else
				removedmethods.put(method, 1);
			
			listmodelmethodsconfigs.removeElementAt(i);
		}
	}
	
	/**
	 * Removes the all configurations.
	 */
	private void removeAllConfigurations() {
		if(listmodelmethodsconfigs.getSize()>0) {
			listmodelmethodsconfigs.removeAllElements();
		}
	}
	
	
	/**
	 * Perform GSEA.
	 */
	private void performGSEA() {
		if(chckbxPerformInRuntime.isSelected()) {
			rdbtnNewRadioButtonontologizer.setEnabled(true);
			rdbtnNewRadioButtontopgo.setEnabled(true);
			textFieldconfiguredengine.setEnabled(true);
			btnNewButtonconfigure.setEnabled(true);
			listpvalues.setEnabled(true);
			btnAddPvalue.setEnabled(true);
			btnRemoveAll.setEnabled(true);
			chckbxAdjustedPvalues.setEnabled(true);
			doubleTextFieldpvalues.setEnabled(true);
			if(listpvaluesmodel.size()==0)
				listpvaluesmodel.addElement(0.05);
			
		}
		else {
			rdbtnNewRadioButtonontologizer.setEnabled(false);
			rdbtnNewRadioButtontopgo.setEnabled(false);
			textFieldconfiguredengine.setEnabled(false);
			btnNewButtonconfigure.setEnabled(false);
			gseaconfig=null;
			textFieldconfiguredengine.setText("");
			listpvalues.setEnabled(false);
			btnAddPvalue.setEnabled(false);
			btnRemoveAll.setEnabled(false);
			chckbxAdjustedPvalues.setEnabled(false);
			chckbxAdjustedPvalues.setSelected(false);
			doubleTextFieldpvalues.setEnabled(false);
			
		}
	}
	
	/**
	 * Use ontologizer.
	 */
	private void useOntologizer() {
		if(rdbtnNewRadioButtonontologizer.isSelected()) {
			rdbtnNewRadioButtontopgo.setSelected(false);
		}
		else {
			rdbtnNewRadioButtontopgo.setSelected(true);
		}
	}
	
	/**
	 * Usetop GO.
	 */
	private void usetopGO() {
		if(rdbtnNewRadioButtontopgo.isSelected()) {
			rdbtnNewRadioButtonontologizer.setSelected(false);
		}
		else {
			rdbtnNewRadioButtonontologizer.setSelected(true);
		}
	}
	
	/**
	 * Configure GSEA.
	 */
	private void configureGSEA() {
		
		if(chckbxPerformInRuntime.isSelected()) {
			GSEAAnalyserType type=null;
			if(rdbtnNewRadioButtonontologizer.isSelected()) {
				type=GSEAAnalyserType.Ontologizer;
			}
			else
				type=GSEAAnalyserType.TopGO;
			
			GSEAConfigurationDialog dialog=new GSEAConfigurationDialog(type);
			
			try {
				int flag=dialog.showOpenDialog(this);
				if(flag==GSEAConfigurationDialog.APPROVE_OPTION) {
					gseaconfig=new GSEAConfigurationContainer(dialog.getGSEASettings(),type);
					textFieldconfiguredengine.setText(type.toString());
				}
				else
					gseaconfig=null;
			} catch (HeadlessException | InterruptedException e) {
				Workbench.getInstance().error(e);
			}
		}
		
	}
	
	/**
	 * Savetodir.
	 */
	private void savetodir() {
		Preferences prefs = Preferences.userRoot().node(getClass().getName());
		JFileChooser chooser = new JFileChooser(prefs.get("LAST_USED_FOLDER",new File(".").getAbsolutePath()));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int tag= chooser.showOpenDialog(this);
		if (tag == JFileChooser.APPROVE_OPTION) {  
			textFieldsavepath.setText(OSystemUtils.validatePath(chooser.getSelectedFile().getAbsolutePath()));
		    prefs.put("LAST_USED_FOLDER", chooser.getSelectedFile().getParent());
		}
		
	}
	
	/**
	 * Checks if is to perform GSEA.
	 *
	 * @return true, if is to perform GSEA
	 */
	public boolean isToPerformGSEA() {
		if(chckbxPerformInRuntime.isSelected() && gseaconfig!=null)
			return true;
		return false;
	}
	
	
	/**
	 * Adds the P value.
	 */
	private void addPValue() {
		if(chckbxPerformInRuntime.isSelected() && !doubleTextFieldpvalues.getText().isEmpty()) {

			double value=Double.parseDouble(doubleTextFieldpvalues.getText());
			if(!listpvaluesmodel.contains(value))	
				listpvaluesmodel.addElement(value);
		}
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(ADDMETHODCONFIG))
		  addMethodConfiguration();
		else if(cmd.equals(REMOVECONFIG))
			removeconfiguration();
		else if(cmd.equals(RMOVEALL))
			removeAllConfigurations();
		else if(cmd.equals(USEGSEA))
			performGSEA();
		else if(cmd.equals(USEONTOLOGIZER))
			useOntologizer();
		else if(cmd.equals(USETOPGO))
			usetopGO();
		else if(cmd.equals(CONFIGGSEA))
			configureGSEA();
		else if(cmd.equals(OPENCHOOSER))
			savetodir();
		else if(cmd.equals(ADDPVALUE))
			addPValue();
		else if(cmd.equals(REMOVEPVALUES)) {
			if(listpvaluesmodel.size()>0)
				listpvaluesmodel.removeAllElements();
		}
		else if(cmd.equals(SINGLETHREAD)) {
			if(rdbtnSingleThread.isSelected())
				rdbtnMultiThread.setSelected(false);
			else
				rdbtnMultiThread.setSelected(true);
		}
		else if(cmd.equals(MULTITHREAD)) {
			if(rdbtnMultiThread.isSelected())
				rdbtnSingleThread.setSelected(false);
			else
				rdbtnSingleThread.setSelected(true);
		}
	}

	
	
	
	
	
	
	/**
	 * Gets the method configurations.
	 *
	 * @return the method configurations
	 */
	public ArrayList<ProfileBiclusteringMethodConfiguration> getMethodConfigurations(){
		ArrayList<ProfileBiclusteringMethodConfiguration> res=new ArrayList<>();
		for (int i = 0; i < listmodelmethodsconfigs.getSize(); i++) {
			res.add(listmodelmethodsconfigs.elementAt(i));
		}
		return res;
	}
	
	/**
	 * Number methods configured.
	 *
	 * @return the int
	 */
	public int numberMethodsConfigured() {
		return listmodelmethodsconfigs.size();
	}

   /**
    * Gets the name profile.
    *
    * @return the name profile
    */
   public String getNameProfile() {
	   return textFieldnameprofile.getText();
   }

   /**
    * Gets the save to folder.
    *
    * @return the save to folder
    */
   public String getSaveToFolder() {
	   return textFieldsavepath.getText();
   }
   
   /**
    * Gets the GSEA configuration.
    *
    * @return the GSEA configuration
    */
   public GSEAConfigurationContainer getGSEAConfiguration() {
	   return gseaconfig;
   }
   
   /**
    * Gets the concurrent processes.
    *
    * @return the concurrent processes
    */
   public int getConcurrentProcesses() {
	   return (int) spinner.getValue();
   }
   
   /**
    * Gets the p values.
    *
    * @return the p values
    */
   public ArrayList<Double> getPValues(){
	   ArrayList<Double> res=new ArrayList<>();
	   if(listpvaluesmodel.size()>0) {
		   for (int i = 0; i < listpvaluesmodel.size(); i++) {
			 res.add(listpvaluesmodel.elementAt(i));
		   }
	   }
	   return res;
   }
   
   /**
    * Use adjusted pvalues.
    *
    * @return true, if successful
    */
   public boolean useAdjustedPvalues() {
	   return chckbxAdjustedPvalues.isSelected();
   }
   
   public boolean isUsingConfigurationMultiThread() {
	   return rdbtnMultiThread.isSelected();
   }

}
