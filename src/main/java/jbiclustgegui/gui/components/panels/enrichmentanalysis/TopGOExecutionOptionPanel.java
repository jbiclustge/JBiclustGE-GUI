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
package jbiclustgegui.gui.components.panels.enrichmentanalysis;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerPropertiesContainer;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGOAlgorithm;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGOAnnotationFunction;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGOMappingType;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGOStatistic;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGoPropertiesContainer;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGopvaluesAdjustMethod;
import jbiclustge.enrichmentanalysistools.topgo.components.TopgoOntology;
import jbiclustgegui.gui.components.selection.Delimiter;
import pt.ornrocha.fileutils.MTUFileUtils;
import pt.ornrocha.propertyutils.PropertiesUtilities;
import pt.ornrocha.swingutils.textfield.CopyPasteJTextField;
import pt.ornrocha.systemutils.OSystemUtils;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class TopGOExecutionOptionPanel.
 */
public class TopGOExecutionOptionPanel extends AbstractGeneEnrichmentAnalyserSettingsPanel implements ActionListener{
	
	/** The text fieldpakagename. */
	private CopyPasteJTextField textFieldpakagename;
	
	/** The text fieldannotationfile. */
	private CopyPasteJTextField textFieldannotationfile;
	
	/** The chckbx annotation database. */
	private JCheckBox chckbxAnnotationDatabase;
	
	/** The chckbx is org type. */
	private JCheckBox chckbxIsOrgType;
	
	/** The combo boxmappingtype. */
	private JComboBox comboBoxmappingtype;
	
	/** The chckbx use file. */
	private JCheckBox chckbxUseFile;
	
	/** The btn openannotationfile. */
	private JButton btnOpenannotationfile;
	
	/** The rdbtn new radio buttongotogenes. */
	private JRadioButton rdbtnNewRadioButtongotogenes;
	
	/** The rdbtn new radio buttongenetogo. */
	private JRadioButton rdbtnNewRadioButtongenetogo;
	
	/** The combo box coldelimiter. */
	private JComboBox comboBox_coldelimiter;
	
	/** The combo boxgotermdelimiter. */
	private JComboBox comboBoxgotermdelimiter;
	
	/** The combo boxontologyaspect. */
	private JComboBox comboBoxontologyaspect;
	
	/** The combo boxalgorithm. */
	private JComboBox comboBoxalgorithm;
	
	/** The combo boxstatistics. */
	private JComboBox comboBoxstatistics;
	
	/** The combo boxmtc. */
	private JComboBox comboBoxmtc;
	
	/** The spinnernodesize. */
	private JSpinner spinnernodesize;
	
	/** The check boxdiscardgenes. */
	private JCheckBox checkBoxdiscardgenes;
	
	/** The btn new buttonresetdefault. */
	private JButton btnNewButtonresetdefault;
	
	
	
	/** The useannotationdatabase. */
	private static String USEANNOTATIONDATABASE="useannotationdatabase";
	
	/** The useannotationfile. */
	private static String USEANNOTATIONFILE="useannotationfile";
	
	/** The openannotationfile. */
	private static String OPENANNOTATIONFILE="openannotationfile";
	
	/** The gotogenes. */
	private static String GOTOGENES="gotogenes";
	
	/** The genestogo. */
	private static String GENESTOGO="genestogo";
	
	/** The reset. */
	private static String RESET="reset";
	
	/** The btn new buttonimportconfs. */
	private JButton btnNewButtonimportconfs;
	
	/** The btn new buttonexportconfs. */
	private JButton btnNewButtonexportconfs;
	
	/**
	 * Instantiates a new top GO execution option panel.
	 */
	public TopGOExecutionOptionPanel() {
		setBorder(new TitledBorder(null, "TopGO Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Use Annotation Database", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 10;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{0.0,0.0,1.0,1.0,0.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0};
		panel.setLayout(gbl_panel);
		
		chckbxAnnotationDatabase = new JCheckBox("Use database package");
		GridBagConstraints gbc_chckbxAnnotationDatabase = new GridBagConstraints();
		gbc_chckbxAnnotationDatabase.gridheight = 2;
		gbc_chckbxAnnotationDatabase.insets = new Insets(0, 10, 0, 5);
		gbc_chckbxAnnotationDatabase.gridx = 0;
		gbc_chckbxAnnotationDatabase.gridy = 0;
		panel.add(chckbxAnnotationDatabase, gbc_chckbxAnnotationDatabase);
		chckbxAnnotationDatabase.addActionListener(this);
		chckbxAnnotationDatabase.setActionCommand(USEANNOTATIONDATABASE);
		
		JLabel lblPackageName = new JLabel("Package name:");
		GridBagConstraints gbc_lblPackageName = new GridBagConstraints();
		gbc_lblPackageName.anchor = GridBagConstraints.EAST;
		gbc_lblPackageName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPackageName.gridx = 1;
		gbc_lblPackageName.gridy = 0;
		panel.add(lblPackageName, gbc_lblPackageName);
		
		textFieldpakagename = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldpakagename = new GridBagConstraints();
		gbc_textFieldpakagename.gridwidth = 2;
		gbc_textFieldpakagename.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldpakagename.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpakagename.gridx = 2;
		gbc_textFieldpakagename.gridy = 0;
		panel.add(textFieldpakagename, gbc_textFieldpakagename);
		textFieldpakagename.setColumns(10);
		
		textFieldpakagename.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	checkTextIsOrgPackage();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		    	String text=textFieldpakagename.getText();
		    	if(text.length()==0)
		    		chckbxIsOrgType.setSelected(false);
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	checkTextIsOrgPackage();
		    }
		});
		
		chckbxIsOrgType = new JCheckBox("<html>Type<br>org.xx.xx</html>");
		GridBagConstraints gbc_chckbxIsOrgType = new GridBagConstraints();
		gbc_chckbxIsOrgType.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxIsOrgType.gridx = 4;
		gbc_chckbxIsOrgType.gridy = 0;
		panel.add(chckbxIsOrgType, gbc_chckbxIsOrgType);
		
		JLabel lblMappingType = new JLabel("Mapping type:");
		GridBagConstraints gbc_lblMappingType = new GridBagConstraints();
		gbc_lblMappingType.anchor = GridBagConstraints.EAST;
		gbc_lblMappingType.insets = new Insets(0, 0, 0, 5);
		gbc_lblMappingType.gridx = 1;
		gbc_lblMappingType.gridy = 1;
		panel.add(lblMappingType, gbc_lblMappingType);
		
		comboBoxmappingtype = new JComboBox();
		GridBagConstraints gbc_comboBoxmappingtype = new GridBagConstraints();
		gbc_comboBoxmappingtype.gridwidth = 2;
		gbc_comboBoxmappingtype.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxmappingtype.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxmappingtype.gridx = 2;
		gbc_comboBoxmappingtype.gridy = 1;
		panel.add(comboBoxmappingtype, gbc_comboBoxmappingtype);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Use a File With Annotation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.gridwidth = 10;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1,1,1,1,1,1};
		gbl_panel_1.rowHeights = new int[]{1,1};
		gbl_panel_1.columnWeights = new double[]{0.0,0.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0};
		panel_1.setLayout(gbl_panel_1);
		
		chckbxUseFile = new JCheckBox("Use file");
		GridBagConstraints gbc_chckbxUseFile = new GridBagConstraints();
		gbc_chckbxUseFile.insets = new Insets(0, 10, 5, 5);
		gbc_chckbxUseFile.gridx = 0;
		gbc_chckbxUseFile.gridy = 0;
		panel_1.add(chckbxUseFile, gbc_chckbxUseFile);
		chckbxUseFile.addActionListener(this);
		chckbxUseFile.setActionCommand(USEANNOTATIONFILE);
		
		btnOpenannotationfile = new JButton("Open");
		GridBagConstraints gbc_btnOpenannotationfile = new GridBagConstraints();
		gbc_btnOpenannotationfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenannotationfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpenannotationfile.gridx = 1;
		gbc_btnOpenannotationfile.gridy = 0;
		panel_1.add(btnOpenannotationfile, gbc_btnOpenannotationfile);
		btnOpenannotationfile.addActionListener(this);
		btnOpenannotationfile.setActionCommand(OPENANNOTATIONFILE);
		
		textFieldannotationfile = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldannotationfile = new GridBagConstraints();
		gbc_textFieldannotationfile.gridwidth = 5;
		gbc_textFieldannotationfile.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldannotationfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldannotationfile.gridx = 2;
		gbc_textFieldannotationfile.gridy = 0;
		panel_1.add(textFieldannotationfile, gbc_textFieldannotationfile);
		textFieldannotationfile.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1,1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{0.0,0.0};
		panel_2.setLayout(gbl_panel_2);
		
		rdbtnNewRadioButtongotogenes = new JRadioButton("GO-to-Genes");
		GridBagConstraints gbc_rdbtnNewRadioButtongotogenes = new GridBagConstraints();
		gbc_rdbtnNewRadioButtongotogenes.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButtongotogenes.gridx = 0;
		gbc_rdbtnNewRadioButtongotogenes.gridy = 0;
		panel_2.add(rdbtnNewRadioButtongotogenes, gbc_rdbtnNewRadioButtongotogenes);
		rdbtnNewRadioButtongotogenes.addActionListener(this);
		rdbtnNewRadioButtongotogenes.setActionCommand(GOTOGENES);
		
		rdbtnNewRadioButtongenetogo = new JRadioButton("Gene-to-GOs");
		GridBagConstraints gbc_rdbtnNewRadioButtongenetogo = new GridBagConstraints();
		gbc_rdbtnNewRadioButtongenetogo.gridx = 0;
		gbc_rdbtnNewRadioButtongenetogo.gridy = 1;
		panel_2.add(rdbtnNewRadioButtongenetogo, gbc_rdbtnNewRadioButtongenetogo);
		rdbtnNewRadioButtongenetogo.addActionListener(this);
		rdbtnNewRadioButtongenetogo.setActionCommand(GENESTOGO);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1,1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0,1.0};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Column delimiter");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		comboBox_coldelimiter = new JComboBox();
		GridBagConstraints gbc_comboBox_coldelimiter = new GridBagConstraints();
		gbc_comboBox_coldelimiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_coldelimiter.gridx = 0;
		gbc_comboBox_coldelimiter.gridy = 1;
		panel_3.add(comboBox_coldelimiter, gbc_comboBox_coldelimiter);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 5;
		gbc_panel_4.gridy = 1;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1, 0};
		gbl_panel_4.rowHeights = new int[]{1, 1, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblGoTermsDelimiter = new JLabel("GO Terms delimiter");
		GridBagConstraints gbc_lblGoTermsDelimiter = new GridBagConstraints();
		gbc_lblGoTermsDelimiter.insets = new Insets(0, 0, 5, 0);
		gbc_lblGoTermsDelimiter.gridx = 0;
		gbc_lblGoTermsDelimiter.gridy = 0;
		panel_4.add(lblGoTermsDelimiter, gbc_lblGoTermsDelimiter);
		
		comboBoxgotermdelimiter = new JComboBox();
		GridBagConstraints gbc_comboBoxgotermdelimiter = new GridBagConstraints();
		gbc_comboBoxgotermdelimiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxgotermdelimiter.gridx = 0;
		gbc_comboBoxgotermdelimiter.gridy = 1;
		panel_4.add(comboBoxgotermdelimiter, gbc_comboBoxgotermdelimiter);
		
		JLabel lblNewLabel = new JLabel("Ontology aspect:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxontologyaspect = new JComboBox();
		GridBagConstraints gbc_comboBoxontologyaspect = new GridBagConstraints();
		gbc_comboBoxontologyaspect.gridwidth = 4;
		gbc_comboBoxontologyaspect.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxontologyaspect.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxontologyaspect.gridx = 1;
		gbc_comboBoxontologyaspect.gridy = 5;
		add(comboBoxontologyaspect, gbc_comboBoxontologyaspect);
		
		JLabel lblAlgorithm = new JLabel("Algorithm:");
		GridBagConstraints gbc_lblAlgorithm = new GridBagConstraints();
		gbc_lblAlgorithm.anchor = GridBagConstraints.EAST;
		gbc_lblAlgorithm.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlgorithm.gridx = 0;
		gbc_lblAlgorithm.gridy = 6;
		add(lblAlgorithm, gbc_lblAlgorithm);
		
		comboBoxalgorithm = new JComboBox();
		GridBagConstraints gbc_comboBoxalgorithm = new GridBagConstraints();
		gbc_comboBoxalgorithm.gridwidth = 4;
		gbc_comboBoxalgorithm.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxalgorithm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxalgorithm.gridx = 1;
		gbc_comboBoxalgorithm.gridy = 6;
		add(comboBoxalgorithm, gbc_comboBoxalgorithm);
		
		JLabel lblStatistics = new JLabel("Statistics:");
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.anchor = GridBagConstraints.EAST;
		gbc_lblStatistics.fill = GridBagConstraints.VERTICAL;
		gbc_lblStatistics.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatistics.gridx = 0;
		gbc_lblStatistics.gridy = 7;
		add(lblStatistics, gbc_lblStatistics);
		
		comboBoxstatistics = new JComboBox();
		GridBagConstraints gbc_comboBoxstatistics = new GridBagConstraints();
		gbc_comboBoxstatistics.gridwidth = 4;
		gbc_comboBoxstatistics.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxstatistics.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxstatistics.gridx = 1;
		gbc_comboBoxstatistics.gridy = 7;
		add(comboBoxstatistics, gbc_comboBoxstatistics);
		
		JLabel lblNewLabel_1 = new JLabel("Multiple testing correction Method:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 8;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxmtc = new JComboBox();
		GridBagConstraints gbc_comboBoxmtc = new GridBagConstraints();
		gbc_comboBoxmtc.gridwidth = 4;
		gbc_comboBoxmtc.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxmtc.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxmtc.gridx = 1;
		gbc_comboBoxmtc.gridy = 8;
		add(comboBoxmtc, gbc_comboBoxmtc);
		
		JLabel lblNodeSize = new JLabel("Node size:");
		GridBagConstraints gbc_lblNodeSize = new GridBagConstraints();
		gbc_lblNodeSize.anchor = GridBagConstraints.EAST;
		gbc_lblNodeSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblNodeSize.gridx = 0;
		gbc_lblNodeSize.gridy = 9;
		add(lblNodeSize, gbc_lblNodeSize);
		
		spinnernodesize = new JSpinner();
		spinnernodesize.setModel(new SpinnerNumberModel(5, 1, 10000, 1));
		GridBagConstraints gbc_spinnernodesize = new GridBagConstraints();
		gbc_spinnernodesize.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnernodesize.gridwidth = 2;
		gbc_spinnernodesize.insets = new Insets(0, 0, 5, 5);
		gbc_spinnernodesize.gridx = 1;
		gbc_spinnernodesize.gridy = 9;
		add(spinnernodesize, gbc_spinnernodesize);
		
		JLabel lblDiscardUnn = new JLabel("Discard unannotated genes:");
		GridBagConstraints gbc_lblDiscardUnn = new GridBagConstraints();
		gbc_lblDiscardUnn.anchor = GridBagConstraints.EAST;
		gbc_lblDiscardUnn.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscardUnn.gridx = 0;
		gbc_lblDiscardUnn.gridy = 10;
		add(lblDiscardUnn, gbc_lblDiscardUnn);
		
		checkBoxdiscardgenes = new JCheckBox("");
		GridBagConstraints gbc_checkBoxdiscardgenes = new GridBagConstraints();
		gbc_checkBoxdiscardgenes.anchor = GridBagConstraints.WEST;
		gbc_checkBoxdiscardgenes.insets = new Insets(0, 0, 5, 5);
		gbc_checkBoxdiscardgenes.gridx = 1;
		gbc_checkBoxdiscardgenes.gridy = 10;
		add(checkBoxdiscardgenes, gbc_checkBoxdiscardgenes);
		
		btnNewButtonresetdefault = new JButton("Reset to default values");
		btnNewButtonresetdefault.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/delete.png")));
		GridBagConstraints gbc_btnNewButtonresetdefault = new GridBagConstraints();
		gbc_btnNewButtonresetdefault.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonresetdefault.gridwidth = 2;
		gbc_btnNewButtonresetdefault.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonresetdefault.gridx = 0;
		gbc_btnNewButtonresetdefault.gridy = 13;
		add(btnNewButtonresetdefault, gbc_btnNewButtonresetdefault);
		btnNewButtonresetdefault.addActionListener(this);
		btnNewButtonresetdefault.setActionCommand(RESET);
		
		btnNewButtonimportconfs = new JButton("Import Settings");
		btnNewButtonimportconfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					importSettings();
				} catch (IOException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		btnNewButtonimportconfs.setIcon(new ImageIcon(TopGOExecutionOptionPanel.class.getResource("/images/i24x24/import.png")));
		GridBagConstraints gbc_btnNewButtonimportconfs = new GridBagConstraints();
		gbc_btnNewButtonimportconfs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonimportconfs.gridwidth = 4;
		gbc_btnNewButtonimportconfs.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonimportconfs.gridx = 2;
		gbc_btnNewButtonimportconfs.gridy = 13;
		add(btnNewButtonimportconfs, gbc_btnNewButtonimportconfs);
		
		btnNewButtonexportconfs = new JButton("Export Settings");
		btnNewButtonexportconfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					exportSettings();
				} catch (IOException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		btnNewButtonexportconfs.setIcon(new ImageIcon(TopGOExecutionOptionPanel.class.getResource("/images/i24x24/export.png")));
		GridBagConstraints gbc_btnNewButtonexportconfs = new GridBagConstraints();
		gbc_btnNewButtonexportconfs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonexportconfs.gridwidth = 4;
		gbc_btnNewButtonexportconfs.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonexportconfs.gridx = 6;
		gbc_btnNewButtonexportconfs.gridy = 13;
		add(btnNewButtonexportconfs, gbc_btnNewButtonexportconfs);
	}
	
	
	/**
	 * Check text is org package.
	 */
	private void checkTextIsOrgPackage() {
		String text=textFieldpakagename.getText();
		if(text!=null && !text.isEmpty()) {
			if(text.startsWith("org.") && !chckbxIsOrgType.isSelected())
				chckbxIsOrgType.setSelected(true);
		}
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#needInitComponents()
	 */
	@Override
	protected boolean needInitComponents() {
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		
		for (TopGOMappingType val : TopGOMappingType.values()) {
			comboBoxmappingtype.addItem(val);
		}
		
		for (Delimiter val : Delimiter.values()) {
			comboBox_coldelimiter.addItem(val);
		}
		
		for (Delimiter val : Delimiter.values()) {
			comboBoxgotermdelimiter.addItem(val);
		}
		
		for (TopgoOntology val : TopgoOntology.values()) {
			comboBoxontologyaspect.addItem(val);
		}
		
		for (TopGOAlgorithm val : TopGOAlgorithm.values()) {
			comboBoxalgorithm.addItem(val);
		}
		
		for (TopGOStatistic val : TopGOStatistic.values()) {
			comboBoxstatistics.addItem(val);
		}
		
		for (TopGopvaluesAdjustMethod val : TopGopvaluesAdjustMethod.values()) {
			comboBoxmtc.addItem(val);
		}
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#resetToDefaultInBeginning()
	 */
	@Override
	protected boolean resetToDefaultInBeginning() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		chckbxAnnotationDatabase.setSelected(true);
		textFieldpakagename.setText("");
		textFieldpakagename.setEnabled(true);
		chckbxIsOrgType.setSelected(false);
		chckbxIsOrgType.setEnabled(true);
		comboBoxmappingtype.setEnabled(true);
		comboBoxmappingtype.setSelectedItem(TopGOMappingType.Symbol);
		chckbxUseFile.setSelected(false);
		btnOpenannotationfile.setEnabled(false);
		textFieldannotationfile.setEnabled(false);
		rdbtnNewRadioButtongotogenes.setSelected(true);
		rdbtnNewRadioButtongotogenes.setEnabled(false);
		rdbtnNewRadioButtongenetogo.setEnabled(false);
		comboBox_coldelimiter.setSelectedItem(Delimiter.TAB);
		comboBox_coldelimiter.setEnabled(false);
		comboBoxgotermdelimiter.setEnabled(false);
		comboBoxgotermdelimiter.setSelectedItem(Delimiter.COMMA);
		comboBoxontologyaspect.setSelectedItem(TopgoOntology.BP);
		comboBoxalgorithm.setSelectedItem(TopGOAlgorithm.classic);
		comboBoxstatistics.setSelectedItem(TopGOStatistic.fisher);
		comboBoxmtc.setSelectedItem(TopGopvaluesAdjustMethod.NONE);
		spinnernodesize.setValue(5);
		checkBoxdiscardgenes.setSelected(true);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(RESET))
			resetToDefaultValues();
		else if(cmd.equals(USEANNOTATIONDATABASE))
			useAnnotationDatabaseCMD();
		else if(cmd.equals(USEANNOTATIONFILE))
			useAnnotationFileCMD();
		else if(cmd.equals(GOTOGENES) && chckbxUseFile.isSelected()) {
			if(rdbtnNewRadioButtongotogenes.isSelected())
				rdbtnNewRadioButtongenetogo.setSelected(false);
			else
				rdbtnNewRadioButtongenetogo.setSelected(true);
		}
		else if(cmd.equals(GENESTOGO) && chckbxUseFile.isSelected()) {
			if(rdbtnNewRadioButtongenetogo.isSelected())
				rdbtnNewRadioButtongotogenes.setSelected(false);
			else
				rdbtnNewRadioButtongotogenes.setSelected(true);
		}
		else if(cmd.equals(OPENANNOTATIONFILE))
			openAnnotationFile();
	}

	
	/**
	 * Use annotation database CMD.
	 */
	private void useAnnotationDatabaseCMD() {
		if(chckbxAnnotationDatabase.isSelected()) {
			textFieldpakagename.setEnabled(true);
			comboBoxmappingtype.setEnabled(true);
			chckbxIsOrgType.setEnabled(true);
			chckbxIsOrgType.setSelected(false);
			chckbxUseFile.setSelected(false);
			btnOpenannotationfile.setEnabled(false);
			textFieldannotationfile.setEnabled(false);
			rdbtnNewRadioButtongotogenes.setSelected(true);
			rdbtnNewRadioButtongotogenes.setEnabled(false);
			rdbtnNewRadioButtongenetogo.setEnabled(false);
			rdbtnNewRadioButtongenetogo.setSelected(false);
			comboBox_coldelimiter.setEnabled(false);
			comboBoxgotermdelimiter.setEnabled(false);
		}
		else {
			chckbxUseFile.setSelected(true);
			useAnnotationFileCMD();
		}
	}
	
	/**
	 * Use annotation file CMD.
	 */
	private void useAnnotationFileCMD() {
		if(chckbxUseFile.isSelected()) {
			chckbxAnnotationDatabase.setSelected(false);
			textFieldpakagename.setText("");
			textFieldpakagename.setEnabled(false);
			comboBoxmappingtype.setEnabled(false);
			chckbxIsOrgType.setEnabled(false);
			btnOpenannotationfile.setEnabled(true);
			textFieldannotationfile.setEnabled(true);
			rdbtnNewRadioButtongotogenes.setSelected(true);
			rdbtnNewRadioButtongotogenes.setEnabled(true);
			rdbtnNewRadioButtongenetogo.setSelected(false);
			rdbtnNewRadioButtongenetogo.setEnabled(true);
			comboBox_coldelimiter.setEnabled(true);
			comboBoxgotermdelimiter.setEnabled(true);
			comboBox_coldelimiter.setSelectedItem(Delimiter.TAB);
			comboBoxgotermdelimiter.setSelectedItem(Delimiter.COMMA);
		}
		else {
			chckbxAnnotationDatabase.setSelected(true);
			useAnnotationDatabaseCMD();
		}
		
	}
	
	/**
	 * Open annotation file.
	 */
	private void openAnnotationFile() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null)
        		textFieldannotationfile.setText(selected.getAbsolutePath());
        }
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#getExecutionSettings()
	 */
	@Override
	public Properties getExecutionSettings() {
	
		TopGoPropertiesContainer props=new TopGoPropertiesContainer();
		if(chckbxAnnotationDatabase.isSelected()) {
			props.setAnnotationDatabase(textFieldpakagename.getText());
			props.setGOTermMappingType((TopGOMappingType) comboBoxmappingtype.getSelectedItem());
			props.setIsAnnotationDatabaseORGType(chckbxIsOrgType.isSelected());
		}
		else {
			props.setAnnotationFile(textFieldannotationfile.getText());
			props.setIsAnnotationGO2GeneFormat(rdbtnNewRadioButtongotogenes.isSelected());
			Delimiter columndelimiter=(Delimiter) comboBox_coldelimiter.getSelectedItem();
			props.setAnnotationColumnDelimiter(columndelimiter.getDelimiterString());
			Delimiter termsdelimiter=(Delimiter) comboBoxgotermdelimiter.getSelectedItem();
			props.setAnnotationGOTermsDelimiter(termsdelimiter.getDelimiterString());
		}
		
		props.setOntology((TopgoOntology) comboBoxontologyaspect.getSelectedItem());
		props.setAlgorithm((TopGOAlgorithm) comboBoxalgorithm.getSelectedItem());
		props.setStatisticMethod((TopGOStatistic) comboBoxstatistics.getSelectedItem());
		props.setMTCMethod((TopGopvaluesAdjustMethod) comboBoxmtc.getSelectedItem());
		props.setNodeSize((int) spinnernodesize.getValue());
		props.setDiscardUnannotatedGenes(checkBoxdiscardgenes.isSelected());
		
		
		return props;
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(chckbxAnnotationDatabase.isSelected() && textFieldpakagename.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set the name of database package in \"Package name\" field");
		else if(chckbxUseFile.isSelected() && textFieldannotationfile.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set a path to the Annotation file in \"Use file (text field)\"");
		else
			  return new Pair<Boolean, String>(true, null);
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#loadProperties(java.util.Properties)
	 */
	@Override
	public void loadProperties(Properties props) {
		if(props!=null){
			String ontology=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ONTOLOGY, null, null);
			comboBoxontologyaspect.setSelectedItem(TopgoOntology.getTopgoOntologyFromString(ontology));
			
			String algorithm =PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ALGORITHM, null, null);
			comboBoxalgorithm.setSelectedItem(TopGOAlgorithm.getTopGOAlgorithmFromString(algorithm));
			
			String statistic =PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.STATISTIC, null, null);
			comboBoxstatistics.setSelectedItem(TopGOStatistic.getTopGOStatisticFromString(statistic));
			
			
			int nodesize=PropertiesUtilities.getIntegerPropertyValue(props, TopGoPropertiesContainer.NODESIZE, 0, null);
			spinnernodesize.setValue(nodesize);
			
			String mtcmethod=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.MCTMETHOD, "none", getClass());
			comboBoxmtc.setSelectedItem(TopGopvaluesAdjustMethod.getMTCMethodFromString(mtcmethod));
			
			
			 boolean discardgenes=PropertiesUtilities.getBooleanPropertyValue(props, TopGoPropertiesContainer.DISCARDUNANNOTATEDGENES, true, getClass());
			 checkBoxdiscardgenes.setSelected(discardgenes);
		
			String annotfile=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONFILE, null, null);
			if(annotfile!=null){
				chckbxUseFile.setSelected(true);
				useAnnotationFileCMD();
				textFieldannotationfile.setText(OSystemUtils.validatePath(annotfile));
				
				
			
				String columdelim=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONFILECOLUMNDELIMITER, null, null);
				if(columdelim!=null) {
					comboBox_coldelimiter.setSelectedItem(Delimiter.getDelimiterFromString(columdelim));
				}
			
				String termdelim=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONFILETERMSDELIMITER, null, null);
				if(termdelim!=null)
					comboBoxgotermdelimiter.setSelectedItem(Delimiter.getDelimiterFromString(termdelim));
			
				boolean isgo2geneformat=PropertiesUtilities.getBooleanPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONFILEGO2GENEFORMAT, false, null);
				if(isgo2geneformat) {
					rdbtnNewRadioButtongotogenes.setSelected(true);
					rdbtnNewRadioButtongenetogo.setSelected(false);
				}
				else {
					rdbtnNewRadioButtongotogenes.setSelected(false);
					rdbtnNewRadioButtongenetogo.setSelected(true);
				}
			}
			
			String anndatabase=PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONDATABASE, null, null);
			if(anndatabase!=null && annotfile==null){
				chckbxAnnotationDatabase.setSelected(true);
				useAnnotationDatabaseCMD();
				textFieldpakagename.setText(anndatabase);
			
				boolean isorgdatabase=PropertiesUtilities.getBooleanPropertyValue(props, TopGoPropertiesContainer.ANNOTATIONDATABASEORGTYPE, false, null);
				chckbxIsOrgType.setSelected(isorgdatabase);
				String mapping =PropertiesUtilities.getStringPropertyValue(props, TopGoPropertiesContainer.MAPPINGTYPE, null, null);
				comboBoxmappingtype.setSelectedItem(TopGOMappingType.getTopGOMappingTypeFromString(mapping));
		

			}
		}
	}
	
	
	/**
	 * Import settings.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void importSettings() throws FileNotFoundException, IOException {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null) {
        		Properties props=PropertiesUtilities.loadFileProperties(selected.getAbsolutePath());
        		loadProperties(props);
        	}
        }
	}
	
	/**
	 * Export settings.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void exportSettings() throws IOException {
		
		
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null) {
        		TopGoPropertiesContainer props=(TopGoPropertiesContainer) getExecutionSettings();
        		String filepath=MTUFileUtils.buildFilePathWithExtension(selected.getAbsolutePath(), "props");
        		props.store(new FileWriter(new File(filepath)), true);
        	}
        }
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#getPreferredDimensionsToPanel()
	 */
	@Override
	public Dimension getPreferredDimensionsToPanel() {
		return new Dimension(1100, 700);
	}

}
