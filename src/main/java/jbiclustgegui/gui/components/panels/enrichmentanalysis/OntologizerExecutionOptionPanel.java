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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.common.StandardAnnotationFile;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerAnnotationType;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerCalculationMethod;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerMTCMethod;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerPropertiesContainer;
import jbiclustgegui.gui.components.tables.common.InformationTable;
import pt.ornrocha.fileutils.MTUFileUtils;
import pt.ornrocha.propertyutils.PropertiesUtilities;
import pt.ornrocha.swingutils.tables.models.GenericTableModel;
import pt.ornrocha.swingutils.textfield.CopyPasteJTextField;
import pt.ornrocha.systemutils.OSystemUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class OntologizerExecutionOptionPanel.
 */
public class OntologizerExecutionOptionPanel extends AbstractGeneEnrichmentAnalyserSettingsPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl ontology. */
	private JLabel lblOntology;
	
	/** The lbl ontology file. */
	private JLabel lblOntologyFile;
	
	/** The panel. */
	private JPanel panel;
	
	/** The chckbx use web file. */
	private JCheckBox chckbxUseWebFile;
	
	/** The chckbx provide A file. */
	private JCheckBox chckbxProvideAFile;
	
	/** The text field. */
	private CopyPasteJTextField textField;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The btn open. */
	private JButton btnOpen;
	
	/** The text field 1. */
	private CopyPasteJTextField textField_1;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The panel 4. */
	private JPanel panel_4;
	
	/** The chckbx use annotation of. */
	private JCheckBox chckbxUseAnnotationOf;
	
	/** The combo boxorganism. */
	private JComboBox comboBoxorganism;
	
	/** The chckbx provide annotation. */
	private JCheckBox chckbxProvideAnnotation;
	
	/** The btn openannotation. */
	private JButton btnOpenannotation;
	
	/** The text field 2. */
	private CopyPasteJTextField textField_2;
	
	/** The lbl calculation method. */
	private JLabel lblCalculationMethod;
	
	/** The combo box calculationmethod. */
	private JComboBox comboBox_calculationmethod;
	
	/** The lbl correction method. */
	private JLabel lblCorrectionMethod;
	
	/** The combo box mtc. */
	private JComboBox comboBox_mtc;
	
	/** The lbl resampling. */
	private JLabel lblResampling;
	
	/** The spinnerresampling. */
	private JSpinner spinnerresampling;
	
	/** The lbl size tolerance. */
	private JLabel lblSizeTolerance;
	
	/** The spinnersizetol. */
	private JSpinner spinnersizetol;
	
	/** The lbl gene ontology aspect. */
	private JLabel lblGeneOntologyAspect;
	
	/** The combo boxgoaspect. */
	private JComboBox comboBoxgoaspect;
	
	/** The chckbx update ontology file. */
	private JCheckBox chckbxUpdateOntologyFile;
	
	/** The panel ignoretermids. */
	private JPanel panel_ignoretermids;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The panel 5. */
	private JPanel panel_5;
	
	/** The panel 6. */
	private JPanel panel_6;
	
	/** The text field 3. */
	private CopyPasteJTextField textField_3;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The btn clear table. */
	private JButton btnClearTable;
	
	/** The btn add. */
	private JButton btnAdd;
	
	/** The btn new buttonreset. */
	private JButton btnNewButtonreset;
	
	/** The tabletermids. */
	private InformationTable tabletermids;
	
	/** The usewebfile. */
	private static String usewebfile="usewebfile";
	
	/** The provideontology. */
	private static String provideontology="provideontology";
	
	/** The openontology. */
	private static String openontology="openontology";
	
	/** The useorganism. */
	private static String useorganism="useorganism";
	
	/** The provideannotation. */
	private static String provideannotation="provideannotation";
	
	/** The openannotationfile. */
	private static String openannotationfile="openannotationfile";
	
	/** The reset. */
	private static String reset="reset";
	
	/** The addid. */
	private static String addid="addid";
	
	/** The removeid. */
	private static String removeid="removeid";
	
	/** The removeall. */
	private static String removeall="removeall";
	
	/** The btn new buttonimportconfs. */
	private JButton btnNewButtonimportconfs;
	
	/** The btn new buttonexportconfs. */
	private JButton btnNewButtonexportconfs;
	
	/**
	 * Instantiates a new ontologizer execution option panel.
	 */
	public OntologizerExecutionOptionPanel() {
		super();
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		setBorder(new TitledBorder(null, "Settings of Ontologizer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblOntologyFile = new JLabel("Ontology File:");
		GridBagConstraints gbc_lblOntologyFile = new GridBagConstraints();
		gbc_lblOntologyFile.anchor = GridBagConstraints.EAST;
		gbc_lblOntologyFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblOntologyFile.gridx = 0;
		gbc_lblOntologyFile.gridy = 0;
		add(this.lblOntologyFile, gbc_lblOntologyFile);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{0.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.chckbxUseWebFile = new JCheckBox("Use Web URL file");
		GridBagConstraints gbc_chckbxUseWebFile = new GridBagConstraints();
		gbc_chckbxUseWebFile.anchor = GridBagConstraints.EAST;
		gbc_chckbxUseWebFile.insets = new Insets(0, 10, 5, 5);
		gbc_chckbxUseWebFile.gridx = 0;
		gbc_chckbxUseWebFile.gridy = 0;
		this.panel.add(this.chckbxUseWebFile, gbc_chckbxUseWebFile);
		chckbxUseWebFile.setActionCommand(usewebfile);
		chckbxUseWebFile.addActionListener(this);
		
		this.textField = new CopyPasteJTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		this.panel.add(this.textField, gbc_textField);
		this.textField.setColumns(10);
		
		this.chckbxProvideAFile = new JCheckBox("Provide Ontology");
		GridBagConstraints gbc_chckbxProvideAFile = new GridBagConstraints();
		gbc_chckbxProvideAFile.anchor = GridBagConstraints.WEST;
		gbc_chckbxProvideAFile.insets = new Insets(0, 10, 0, 5);
		gbc_chckbxProvideAFile.gridx = 0;
		gbc_chckbxProvideAFile.gridy = 1;
		this.panel.add(this.chckbxProvideAFile, gbc_chckbxProvideAFile);
		chckbxProvideAFile.setActionCommand(provideontology);
		chckbxProvideAFile.addActionListener(this);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		this.panel.add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1};
		gbl_panel_1.rowHeights = new int[]{1};
		gbl_panel_1.columnWeights = new double[]{0.0,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.btnOpen = new JButton("Open");
		btnOpen.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/import.png")));
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpen.gridx = 0;
		gbc_btnOpen.gridy = 0;
		this.panel_1.add(this.btnOpen, gbc_btnOpen);
		btnOpen.setActionCommand(openontology);
		btnOpen.addActionListener(this);
		
		this.textField_1 = new CopyPasteJTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		this.panel_1.add(this.textField_1, gbc_textField_1);
		this.textField_1.setColumns(10);
		
		this.lblOntology = new JLabel("Annotation File:");
		GridBagConstraints gbc_lblOntology = new GridBagConstraints();
		gbc_lblOntology.anchor = GridBagConstraints.EAST;
		gbc_lblOntology.insets = new Insets(0, 0, 5, 5);
		gbc_lblOntology.gridx = 0;
		gbc_lblOntology.gridy = 1;
		add(this.lblOntology, gbc_lblOntology);
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 7;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1,1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		this.panel_2.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1,1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{0.0,1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.chckbxUseAnnotationOf = new JCheckBox("Use annotation of Organism ");
		GridBagConstraints gbc_chckbxUseAnnotationOf = new GridBagConstraints();
		gbc_chckbxUseAnnotationOf.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseAnnotationOf.insets = new Insets(0, 10, 0, 5);
		gbc_chckbxUseAnnotationOf.gridx = 0;
		gbc_chckbxUseAnnotationOf.gridy = 0;
		this.panel_3.add(this.chckbxUseAnnotationOf, gbc_chckbxUseAnnotationOf);
		chckbxUseAnnotationOf.setActionCommand(useorganism);
		chckbxUseAnnotationOf.addActionListener(this);
		
		this.comboBoxorganism = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		this.panel_3.add(this.comboBoxorganism, gbc_comboBox);
		
		this.panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		this.panel_2.add(this.panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1,1,1};
		gbl_panel_4.rowHeights = new int[]{1};
		gbl_panel_4.columnWeights = new double[]{0.0,0.1,1.0};
		gbl_panel_4.rowWeights = new double[]{1.0};
		this.panel_4.setLayout(gbl_panel_4);
		
		this.chckbxProvideAnnotation = new JCheckBox("Provide Annotation");
		GridBagConstraints gbc_chckbxProvideAnnotation = new GridBagConstraints();
		gbc_chckbxProvideAnnotation.anchor = GridBagConstraints.WEST;
		gbc_chckbxProvideAnnotation.insets = new Insets(0, 10, 0, 5);
		gbc_chckbxProvideAnnotation.gridx = 0;
		gbc_chckbxProvideAnnotation.gridy = 0;
		this.panel_4.add(this.chckbxProvideAnnotation, gbc_chckbxProvideAnnotation);
		chckbxProvideAnnotation.addActionListener(this);
		chckbxProvideAnnotation.setActionCommand(provideannotation);
		
		this.btnOpenannotation = new JButton("Open");
		btnOpenannotation.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/import.png")));
		GridBagConstraints gbc_btnOpenannotation = new GridBagConstraints();
		gbc_btnOpenannotation.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenannotation.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenannotation.gridx = 1;
		gbc_btnOpenannotation.gridy = 0;
		this.panel_4.add(this.btnOpenannotation, gbc_btnOpenannotation);
		btnOpenannotation.setActionCommand(openannotationfile);
		btnOpenannotation.addActionListener(this);
		
		this.textField_2 = new CopyPasteJTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 0;
		this.panel_4.add(this.textField_2, gbc_textField_2);
		this.textField_2.setColumns(10);
		
		this.lblGeneOntologyAspect = new JLabel("Gene Ontology Aspect:");
		GridBagConstraints gbc_lblGeneOntologyAspect = new GridBagConstraints();
		gbc_lblGeneOntologyAspect.anchor = GridBagConstraints.EAST;
		gbc_lblGeneOntologyAspect.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneOntologyAspect.gridx = 0;
		gbc_lblGeneOntologyAspect.gridy = 2;
		add(this.lblGeneOntologyAspect, gbc_lblGeneOntologyAspect);
		
		this.comboBoxgoaspect = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 2;
		add(this.comboBoxgoaspect, gbc_comboBox_2);
		
		this.chckbxUpdateOntologyFile = new JCheckBox("Update Ontology file");
		GridBagConstraints gbc_chckbxUpdateOntologyFile = new GridBagConstraints();
		gbc_chckbxUpdateOntologyFile.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUpdateOntologyFile.gridx = 4;
		gbc_chckbxUpdateOntologyFile.gridy = 2;
		add(this.chckbxUpdateOntologyFile, gbc_chckbxUpdateOntologyFile);
		
		this.panel_ignoretermids = new JPanel();
		this.panel_ignoretermids.setBorder(new TitledBorder(null, "Ignore GO term ids", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_ignoretermids = new GridBagConstraints();
		gbc_panel_ignoretermids.gridheight = 5;
		gbc_panel_ignoretermids.gridwidth = 3;
		gbc_panel_ignoretermids.insets = new Insets(0, 0, 5, 0);
		gbc_panel_ignoretermids.fill = GridBagConstraints.BOTH;
		gbc_panel_ignoretermids.gridx = 5;
		gbc_panel_ignoretermids.gridy = 2;
		add(this.panel_ignoretermids, gbc_panel_ignoretermids);
		GridBagLayout gbl_panel_ignoretermids = new GridBagLayout();
		gbl_panel_ignoretermids.columnWidths = new int[]{1};
		gbl_panel_ignoretermids.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gbl_panel_ignoretermids.columnWeights = new double[]{1.0};
		gbl_panel_ignoretermids.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel_ignoretermids.setLayout(gbl_panel_ignoretermids);
		
		this.panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		this.panel_ignoretermids.add(this.panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{1,1};
		gbl_panel_5.rowHeights = new int[]{1};
		gbl_panel_5.columnWeights = new double[]{1.0,0.2};
		gbl_panel_5.rowWeights = new double[]{1.0};
		this.panel_5.setLayout(gbl_panel_5);
		
		this.textField_3 = new CopyPasteJTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 0;
		this.panel_5.add(this.textField_3, gbc_textField_3);
		this.textField_3.setColumns(10);
		
		this.btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/add.png")));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		this.panel_5.add(this.btnAdd, gbc_btnAdd);
		btnAdd.setActionCommand(addid);
		btnAdd.addActionListener(this);
		
		tabletermids=new InformationTable(new String[] {"Term ID"});
		this.scrollPane = new JScrollPane(tabletermids);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.panel_ignoretermids.add(this.scrollPane, gbc_scrollPane);
		
		this.panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 7;
		this.panel_ignoretermids.add(this.panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{1,1};
		gbl_panel_6.rowHeights = new int[]{1};
		gbl_panel_6.columnWeights = new double[]{1.0,1.0};
		gbl_panel_6.rowWeights = new double[]{1.0};
		this.panel_6.setLayout(gbl_panel_6);
		
		this.btnNewButton = new JButton("Remove Selected");
		btnNewButton.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/delete.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		this.panel_6.add(this.btnNewButton, gbc_btnNewButton);
		btnNewButton.setActionCommand(removeid);
		btnNewButton.addActionListener(this);
		
		this.btnClearTable = new JButton("Clear table");
		GridBagConstraints gbc_btnClearTable = new GridBagConstraints();
		gbc_btnClearTable.fill = GridBagConstraints.BOTH;
		gbc_btnClearTable.gridx = 1;
		gbc_btnClearTable.gridy = 0;
		this.panel_6.add(this.btnClearTable, gbc_btnClearTable);
		btnClearTable.setActionCommand(removeall);
		btnClearTable.addActionListener(this);
		
		this.lblCalculationMethod = new JLabel("Calculation Method:");
		GridBagConstraints gbc_lblCalculationMethod = new GridBagConstraints();
		gbc_lblCalculationMethod.anchor = GridBagConstraints.EAST;
		gbc_lblCalculationMethod.insets = new Insets(0, 10, 5, 5);
		gbc_lblCalculationMethod.gridx = 0;
		gbc_lblCalculationMethod.gridy = 3;
		add(this.lblCalculationMethod, gbc_lblCalculationMethod);
		
		this.comboBox_calculationmethod = new JComboBox();
		GridBagConstraints gbc_comboBox_calculationmethod = new GridBagConstraints();
		gbc_comboBox_calculationmethod.gridwidth = 3;
		gbc_comboBox_calculationmethod.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_calculationmethod.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_calculationmethod.gridx = 1;
		gbc_comboBox_calculationmethod.gridy = 3;
		add(this.comboBox_calculationmethod, gbc_comboBox_calculationmethod);
		
		this.lblCorrectionMethod = new JLabel("Multiple testing correction Method:");
		GridBagConstraints gbc_lblCorrectionMethod = new GridBagConstraints();
		gbc_lblCorrectionMethod.anchor = GridBagConstraints.EAST;
		gbc_lblCorrectionMethod.insets = new Insets(0, 10, 5, 5);
		gbc_lblCorrectionMethod.gridx = 0;
		gbc_lblCorrectionMethod.gridy = 4;
		add(this.lblCorrectionMethod, gbc_lblCorrectionMethod);
		
		this.comboBox_mtc = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 3;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 4;
		add(this.comboBox_mtc, gbc_comboBox_1);
		
		this.lblResampling = new JLabel("Resampling steps:");
		GridBagConstraints gbc_lblResampling = new GridBagConstraints();
		gbc_lblResampling.anchor = GridBagConstraints.EAST;
		gbc_lblResampling.insets = new Insets(0, 10, 5, 5);
		gbc_lblResampling.gridx = 0;
		gbc_lblResampling.gridy = 5;
		add(this.lblResampling, gbc_lblResampling);
		
		this.spinnerresampling = new JSpinner();
		this.spinnerresampling.setToolTipText("0  = use default number of resampling steps or a value between [100-100000]");
		// interval 100 - 100000
		this.spinnerresampling.setModel(new SpinnerNumberModel(0, 0, 100000, 1));
		GridBagConstraints gbc_spinnerresampling = new GridBagConstraints();
		gbc_spinnerresampling.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerresampling.gridwidth = 2;
		gbc_spinnerresampling.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerresampling.gridx = 1;
		gbc_spinnerresampling.gridy = 5;
		add(this.spinnerresampling, gbc_spinnerresampling);
		
		this.lblSizeTolerance = new JLabel("Size tolerance:");
		GridBagConstraints gbc_lblSizeTolerance = new GridBagConstraints();
		gbc_lblSizeTolerance.anchor = GridBagConstraints.EAST;
		gbc_lblSizeTolerance.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeTolerance.gridx = 0;
		gbc_lblSizeTolerance.gridy = 6;
		add(this.lblSizeTolerance, gbc_lblSizeTolerance);
		
		this.spinnersizetol = new JSpinner();
		this.spinnersizetol.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		// interval 1-100
		this.spinnersizetol.setToolTipText("0  = use default number of size tolerance or a value between [1-100]");
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridwidth = 2;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 6;
		add(this.spinnersizetol, gbc_spinner);
		
		this.btnNewButtonreset = new JButton("Reset to default values");
		btnNewButtonreset.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/delete.png")));
		GridBagConstraints gbc_btnNewButtonreset = new GridBagConstraints();
		gbc_btnNewButtonreset.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonreset.gridwidth = 4;
		gbc_btnNewButtonreset.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonreset.gridx = 0;
		gbc_btnNewButtonreset.gridy = 7;
		add(this.btnNewButtonreset, gbc_btnNewButtonreset);
		btnNewButtonreset.setActionCommand(reset);
		btnNewButtonreset.addActionListener(this);
		
		this.btnNewButtonimportconfs = new JButton("Import Settings");
		this.btnNewButtonimportconfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					importSettings();
				} catch (IOException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		btnNewButtonimportconfs.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/import.png")));
		GridBagConstraints gbc_btnNewButtonimportconfs = new GridBagConstraints();
		gbc_btnNewButtonimportconfs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonimportconfs.gridwidth = 2;
		gbc_btnNewButtonimportconfs.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonimportconfs.gridx = 4;
		gbc_btnNewButtonimportconfs.gridy = 7;
		add(this.btnNewButtonimportconfs, gbc_btnNewButtonimportconfs);
		
		this.btnNewButtonexportconfs = new JButton("Export Settings");
		this.btnNewButtonexportconfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					exportSettings();
				} catch (IOException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		btnNewButtonexportconfs.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/export.png")));
		GridBagConstraints gbc_btnNewButtonexportconfs = new GridBagConstraints();
		gbc_btnNewButtonexportconfs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonexportconfs.gridwidth = 3;
		gbc_btnNewButtonexportconfs.gridx = 6;
		gbc_btnNewButtonexportconfs.gridy = 7;
		add(this.btnNewButtonexportconfs, gbc_btnNewButtonexportconfs);
	}

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		
		//StandardAnnotationFile
		for (StandardAnnotationFile stdfile : StandardAnnotationFile.values()) {
			comboBoxorganism.addItem(stdfile);
		}
		
		
		for (OntologizerAnnotationType goasp : OntologizerAnnotationType.values()) {
			comboBoxgoaspect.addItem(goasp);
		}
		
		for (OntologizerCalculationMethod calcmethod : OntologizerCalculationMethod.values()) {
			comboBox_calculationmethod.addItem(calcmethod);
		}
		
		for (OntologizerMTCMethod mtc : OntologizerMTCMethod.values()) {
			comboBox_mtc.addItem(mtc);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		chckbxUseWebFile.setSelected(true);
		textField.setText("http://purl.obolibrary.org/obo/go.obo");
		textField.setEnabled(true);
		chckbxProvideAFile.setSelected(false);
		btnOpen.setEnabled(false);
		textField_1.setText("");
		textField_1.setEnabled(false);
		chckbxUseAnnotationOf.setSelected(true);
		comboBoxorganism.setSelectedItem(StandardAnnotationFile.Escherichiacoli);
		comboBoxorganism.setEnabled(true);
		chckbxProvideAnnotation.setSelected(false);
		btnOpenannotation.setEnabled(false);
		textField_2.setText("");
		textField_2.setEnabled(false);
		comboBoxgoaspect.setSelectedItem(OntologizerAnnotationType.ALL);
		chckbxUpdateOntologyFile.setSelected(true);
		comboBox_calculationmethod.setSelectedItem(OntologizerCalculationMethod.ParentChildUnion);
		comboBox_mtc.setSelectedItem(OntologizerMTCMethod.None);
		spinnerresampling.setValue(0);
		spinnersizetol.setValue(0);
		textField_3.setText("");
		tabletermids.clearTable();
	}

	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(reset))
			resetToDefaultValues();
		else if(cmd.equals(usewebfile))
			UseWebFileCMD();
		else if(cmd.equals(provideontology))
			provideOntologyCMD();
		else if(cmd.equals(openontology) && chckbxProvideAFile.isSelected()) {
			loadOntologyFileCMD();
		}
		else if(cmd.equals(useorganism)) {
			UseOrganismCMD();
		}
		else if(cmd.equals(provideannotation)) {
			provideAnnotationCMD();
		}
		else if(cmd.equals(openannotationfile) && btnOpenannotation.isEnabled())
			loadAnnotationFileCMD();
		else if(cmd.equals(addid)) {
			if(!textField_3.getText().isEmpty()) {
				String data=textField_3.getText();
				boolean found=false;
				for (int i = 0; i < tabletermids.getRowCount(); i++) {
					String in=(String) tabletermids.getValueAt(i, 0);
					if(data.equals(in)) {
						found=true;
						break;
					}
					
				}
				if(!found)
					tabletermids.appendData(new Object[]{textField_3.getText()});
			}
		}
		else if(cmd.equals(removeid)) {
			if(tabletermids.getRowCount()>0) {
				int row=tabletermids.getSelectedRow();
				GenericTableModel model=(GenericTableModel) tabletermids.getModel();
				model.removeRowAtPos(row);
			}
		}
		else if(cmd.equals(removeall)) {
			if(tabletermids.getRowCount()>0)
				tabletermids.clearTable();
		}
		
	}
	
	/**
	 * Use web file CMD.
	 */
	private void UseWebFileCMD() {
		if(chckbxUseWebFile.isSelected()) {
			chckbxProvideAFile.setSelected(false);
			btnOpen.setEnabled(false);
			textField_1.setText("");
			textField_1.setEnabled(false);
			textField.setText("http://purl.obolibrary.org/obo/go.obo");
			textField.setEnabled(true);
		}
		else {
			chckbxProvideAFile.setSelected(true);
			provideOntologyCMD();
		}	
	}
	
	/**
	 * Provide ontology CMD.
	 */
	private void provideOntologyCMD() {
		if(chckbxProvideAFile.isSelected()) {
			textField.setText("");
			textField.setEnabled(false);
			chckbxUseWebFile.setSelected(false);
			btnOpen.setEnabled(true);
			textField_1.setEnabled(true);
		}
		else {
			chckbxUseWebFile.setSelected(true);
			UseWebFileCMD();
		}
	}
	
	
	/**
	 * Use organism CMD.
	 */
	private void UseOrganismCMD() {
		if(chckbxUseAnnotationOf.isSelected()) {
			chckbxProvideAnnotation.setSelected(false);
			btnOpenannotation.setEnabled(false);
			textField_2.setText("");
			textField_2.setEnabled(false);
			comboBoxorganism.setSelectedItem(StandardAnnotationFile.Escherichiacoli);
			comboBoxorganism.setEnabled(true);
		}
		else {
			chckbxProvideAnnotation.setSelected(true);
			provideAnnotationCMD();
		}
	}
	
	
	
	/**
	 * Provide annotation CMD.
	 */
	private void provideAnnotationCMD() {
		if(chckbxProvideAnnotation.isSelected()) {
			chckbxUseAnnotationOf.setSelected(false);
			comboBoxorganism.setEnabled(false);
			btnOpenannotation.setEnabled(true);
			textField_2.setEnabled(true);
		}
		else {
			chckbxUseAnnotationOf.setSelected(true);
			UseOrganismCMD();
		}
	}
	
	/**
	 * Load ontology file CMD.
	 */
	private void loadOntologyFileCMD() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null)
        	 textField_1.setText(selected.getAbsolutePath());
        }
	}
	
	/**
	 * Load annotation file CMD.
	 */
	private void loadAnnotationFileCMD() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null)
        		textField_2.setText(selected.getAbsolutePath());
        }
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#validSettings()
	 */
	public Pair<Boolean, String> validSettings(){
		if(chckbxUseWebFile.isSelected() && textField.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set an url of the Ontology file in the \"Use Web URL file\" field");
		else if(chckbxProvideAFile.isSelected() && textField_1.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set a path to the Ontology file in the \"Provide Ontology\" field");
		else if(chckbxProvideAnnotation.isSelected() && textField_2.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set a path to the Ontology file in the \"Provide Annotation\" field");
		else if((int)spinnerresampling.getValue()>0 && ((int)spinnerresampling.getValue()<100 || (int)spinnerresampling.getValue()>100000))
			return new Pair<Boolean, String>(false, "Please the resampling steps value must be 0 or a value in the interval [100-100000]");
		else if((int)spinnersizetol.getValue()>100)
			return new Pair<Boolean, String>(false, "Please the size tolerance value must be 0 or a value in the interval [1-100]");
		else
		  return new Pair<Boolean, String>(true, null);
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#getExecutionSettings()
	 */
	@Override
	public Properties getExecutionSettings() {
		OntologizerPropertiesContainer props=new OntologizerPropertiesContainer();
		
		if(chckbxUseWebFile.isSelected())
			props.setOntologyURLFile(textField.getText());
		else
			props.setOntologyFilePath(textField_1.getText());
		
		if(chckbxUseAnnotationOf.isSelected()) {
			StandardAnnotationFile annotorg=(StandardAnnotationFile) comboBoxorganism.getSelectedItem();
			props.setAnnotationOrganismURLFile(annotorg.getDownloadURL());
		}
		else
			props.setAnnotationFilePath(textField_2.getText());
		
		
		props.setAnnotationGOAspectType((OntologizerAnnotationType) comboBoxgoaspect.getSelectedItem());
		props.setCalculationMethod((OntologizerCalculationMethod) comboBox_calculationmethod.getSelectedItem());
		props.setMultipleTestCorrectionMethod((OntologizerMTCMethod) comboBox_mtc.getSelectedItem());
		props.setResamplingSteps((int) spinnerresampling.getValue());
		props.setSizeTolerance((int) spinnersizetol.getValue());
		props.forceUpdateOfOntologyFileConcerningToGOAspect(chckbxUpdateOntologyFile.isSelected());
		
		return props;
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#loadProperties(java.util.Properties)
	 */
	@Override
	public void loadProperties(Properties props) {
		
		if(props!=null){
			String ontologyfilepath=PropertiesUtilities.getStringPropertyValue(props,OntologizerPropertiesContainer.ONTOLOGYFILE, null, null);
			if(ontologyfilepath!=null && !ontologyfilepath.isEmpty()){
			
				chckbxProvideAFile.setSelected(true);
				provideOntologyCMD();
				textField_1.setText(OSystemUtils.validatePath(ontologyfilepath));
			}
			else{
				ontologyfilepath=PropertiesUtilities.getStringPropertyValue(props,OntologizerPropertiesContainer.URLONTOLOGYFILE, null, null);
				if(ontologyfilepath!=null && !ontologyfilepath.isEmpty()) {
	
						chckbxUseWebFile.setSelected(true);
						UseWebFileCMD();
						textField.setText(ontologyfilepath);
				}
				
			}
			
			String annotationfilepath=PropertiesUtilities.getStringPropertyValue(props,OntologizerPropertiesContainer.ANNOTATIONFILE, null, null);
			if(annotationfilepath!=null && !annotationfilepath.isEmpty()){
				chckbxProvideAnnotation.setSelected(true);
				provideAnnotationCMD();
				textField_2.setText(annotationfilepath);
			}
			else{
				annotationfilepath=PropertiesUtilities.getStringPropertyValue(props,OntologizerPropertiesContainer.ORGANISMANNOTATIONFILE, null, null);
				if(annotationfilepath!=null && !annotationfilepath.isEmpty()) {
					chckbxUseAnnotationOf.setSelected(true);
					UseOrganismCMD();
					for (StandardAnnotationFile orgfile : StandardAnnotationFile.values()) {
						String url=orgfile.getDownloadURL();
						if(url.equals(annotationfilepath)) {
							comboBoxorganism.setSelectedItem(orgfile);
							break;
						}
						
					}
				}
			}
			
			comboBox_calculationmethod.setSelectedItem(OntologizerCalculationMethod.getCalculationmethodFromString(PropertiesUtilities.getStringPropertyValue(props, OntologizerPropertiesContainer.CALCULATIONMETHOD, null, null)));
			comboBox_mtc.setSelectedItem(OntologizerMTCMethod.getMTCMethodFromString(PropertiesUtilities.getStringPropertyValue(props, OntologizerPropertiesContainer.MCTMETHOD, null, null)));
			
			
		    int resamplingsteps=PropertiesUtilities.getIntegerPropertyValueValidLimits(props, OntologizerPropertiesContainer.RESAMPLINGSTEPS, -1, 100, 100000, true, this.getClass());
		    if(resamplingsteps==-1)
		    	spinnerresampling.setValue(0);
		    else
		    	spinnerresampling.setValue(resamplingsteps);
		    	
		    int sizetolerance=PropertiesUtilities.getIntegerPropertyValueValidLimits(props, OntologizerPropertiesContainer.SIZETOLERANCE, -1, 1, 100, true, this.getClass());
		    if(sizetolerance==-1)
		    	spinnersizetol.setValue(0);
		    else
		    	spinnersizetol.setValue(sizetolerance);
		    
		    String annottype=PropertiesUtilities.getStringPropertyValue(props, OntologizerPropertiesContainer.ANNOTATIONTYPE, "all", getClass());
		    comboBoxgoaspect.setSelectedItem(OntologizerAnnotationType.getAnnotationTypeFromString(annottype));
		    chckbxUpdateOntologyFile.setSelected(PropertiesUtilities.getBooleanPropertyValue(props, OntologizerPropertiesContainer.ANNOTATIONUPDATE, false, getClass()));
		    
		    
		   
		    
		    String ignoreterms=PropertiesUtilities.getStringPropertyValue(props, OntologizerPropertiesContainer.IGNOREGOTERMIDS, null, getClass());
		    if(ignoreterms!=null && !ignoreterms.isEmpty()){
		    	String[] elems=ignoreterms.split(";");
		    	if(elems.length>0) {
		    		ArrayList<String>gotermidstoignore=new ArrayList<>(Arrays.asList(elems));
		    		List<Object[]> listobj=new ArrayList<>(gotermidstoignore.size());
		    		for (int i = 0; i < gotermidstoignore.size(); i++) {
		    			Object[] val=new Object[] {gotermidstoignore.get(i)};
		    			listobj.add(val);
					}
		    		tabletermids.setData(listobj);
		    	}
		    }
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
	 * @see jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel#resetToDefaultInBeginning()
	 */
	@Override
	protected boolean resetToDefaultInBeginning() {
		return true;
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
        		OntologizerPropertiesContainer props=(OntologizerPropertiesContainer) getExecutionSettings();
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
