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
package jbiclustgegui.gui.components.panels.biclusters;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.border.TitledBorder;

import org.apache.commons.io.FilenameUtils;
import org.javatuples.Quartet;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.analysis.coherence.CoherenceAnalyser;
import jbiclustge.analysis.overlap.OverlapAnalyser;
import jbiclustge.analysis.similarity.components.AnalysisTypeDimension;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustge.results.biclusters.containers.BiclusterResult;
import jbiclustgegui.datatypes.biclusteringresults.BBCResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.gui.components.tables.common.InformationTable;
import jbiclustgegui.operations.GenericOperation;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.swingutils.jfilechooser.filefilters.ExtensionFileFilter;
import pt.ornrocha.swingutils.textfield.CopyPasteJTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusterAnalysisMethodsPanel.
 */
public class BiclusterAnalysisMethodsPanel extends JPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The lbl bicluster. */
	private JLabel lblBicluster;
	
	/** The combo boxbiclusterscoherence. */
	private JComboBox comboBoxbiclusterscoherence;
	
	/** The lbl dimension. */
	private JLabel lblDimension;
	
	/** The combo boxcoherencedim. */
	private JComboBox comboBoxcoherencedim;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The lbl constant variance. */
	private JLabel lblConstantVariance;
	
	/** The lbl additive variance. */
	private JLabel lblAdditiveVariance;
	
	/** The lbl multiplicative variance. */
	private JLabel lblMultiplicativeVariance;
	
	/** The lbl sign variance. */
	private JLabel lblSignVariance;
	
	/** The text fieldconstvariance. */
	private JTextField textFieldconstvariance;
	
	/** The text fieldaddvariance. */
	private JTextField textFieldaddvariance;
	
	/** The text fieldmultivariance. */
	private JTextField textFieldmultivariance;
	
	/** The text fieldsignvariance. */
	private JTextField textFieldsignvariance;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The lbl bicluster 1. */
	private JLabel lblBicluster_1;
	
	/** The lbl bicluster 2. */
	private JLabel lblBicluster_2;
	
	/** The combo boxoverlap 1. */
	private JComboBox comboBoxoverlap1;
	
	/** The combo boxoverlap 2. */
	private JComboBox comboBoxoverlap2;
	
	/** The label. */
	private JLabel label;
	
	/** The combo boxoverlapdim. */
	private JComboBox comboBoxoverlapdim;
	
	/** The buttoncalculateoverlap. */
	private JButton buttoncalculateoverlap;
	
	/** The lbl overlap. */
	private JLabel lblOverlap;
	
	/** The text fieldoverlapresult. */
	private JTextField textFieldoverlapresult;
	
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The btn new buttonaverageoverlap. */
	private JButton btnNewButtonaverageoverlap;
	
	/** The text fieldaverageoverlap. */
	private JTextField textFieldaverageoverlap;
	
	/** The spinnerthreshold. */
	private JSpinner spinnerthreshold;
	
	/** The lbl treshold. */
	private JLabel lblTreshold;
	
	/** The lbl max number biclusters. */
	private JLabel lblMaxNumberBiclusters;
	
	/** The spinnermaxbics. */
	private JSpinner spinnermaxbics;
	
	/** The btn new buttonfilter. */
	private JButton btnNewButtonfilter;
	
	/** The lbl new labelprocessed. */
	private JLabel lblNewLabelprocessed;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The btn new buttonexportfiltered. */
	private JButton btnNewButtonexportfiltered;
	
	/** The btn new buttonputinclipboard. */
	private JButton btnNewButtonputinclipboard;
	
	/** The results. */
	private BiclusterList results;
	
	/** The filteredresultsbythreshold. */
	private BiclusterList filteredresultsbythreshold;
	
	/** The proj. */
	private Project proj;
	
	/** The panel 4. */
	private JPanel panel_4;
	
	/** The panel 5. */
	private JPanel panel_5;
	
	/** The lbl dimension 1. */
	private JLabel lblDimension_1;
	
	/** The combo boxdimallcoherence. */
	private JComboBox comboBoxdimallcoherence;
	
	/** The btn calculateallcoherence. */
	private JButton btnCalculateallcoherence;
	
	/** The scroll paneallcoherencemeas. */
	private JScrollPane scrollPaneallcoherencemeas;
	
	/** The coherencetable. */
	private InformationTable coherencetable;
	
	/** The panel 6. */
	private JPanel panel_6;
	
	/** The panel 7. */
	private JPanel panel_7;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The combo boxoverlapalldim. */
	private JComboBox comboBoxoverlapalldim;
	
	/** The buttonoverlapallbiclusters. */
	private JButton buttonoverlapallbiclusters;
	
	/** The scroll paneoverlapallbics. */
	private JScrollPane scrollPaneoverlapallbics;
	
	/** The overlapallbicstable. */
	private InformationTable overlapallbicstable;
	
	/** The overlapallrunning. */
	private boolean overlapallrunning=false;
	
	/** The workeroverlap. */
	private SwingWorker<Boolean, Void> workeroverlap;
	
	/**
	 * Instantiates a new bicluster analysis methods panel.
	 */
	public BiclusterAnalysisMethodsPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Bicluster Coherence Measurements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.lblBicluster = new JLabel("Bicluster:");
		GridBagConstraints gbc_lblBicluster = new GridBagConstraints();
		gbc_lblBicluster.anchor = GridBagConstraints.EAST;
		gbc_lblBicluster.insets = new Insets(0, 0, 5, 5);
		gbc_lblBicluster.gridx = 0;
		gbc_lblBicluster.gridy = 0;
		this.panel.add(this.lblBicluster, gbc_lblBicluster);
		
		this.comboBoxbiclusterscoherence = new JComboBox();
		GridBagConstraints gbc_comboBoxbiclusterscoherence = new GridBagConstraints();
		gbc_comboBoxbiclusterscoherence.gridwidth = 3;
		gbc_comboBoxbiclusterscoherence.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxbiclusterscoherence.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxbiclusterscoherence.gridx = 1;
		gbc_comboBoxbiclusterscoherence.gridy = 0;
		this.panel.add(this.comboBoxbiclusterscoherence, gbc_comboBoxbiclusterscoherence);
		
		this.lblDimension = new JLabel("Dimension:");
		GridBagConstraints gbc_lblDimension = new GridBagConstraints();
		gbc_lblDimension.anchor = GridBagConstraints.EAST;
		gbc_lblDimension.insets = new Insets(0, 0, 5, 5);
		gbc_lblDimension.gridx = 0;
		gbc_lblDimension.gridy = 1;
		this.panel.add(this.lblDimension, gbc_lblDimension);
		
		this.comboBoxcoherencedim = new JComboBox(AnalysisTypeDimension.values());
		GridBagConstraints gbc_comboBoxcoherencedim = new GridBagConstraints();
		gbc_comboBoxcoherencedim.gridwidth = 3;
		gbc_comboBoxcoherencedim.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxcoherencedim.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxcoherencedim.gridx = 1;
		gbc_comboBoxcoherencedim.gridy = 1;
		this.panel.add(this.comboBoxcoherencedim, gbc_comboBoxcoherencedim);
		
		this.btnNewButton = new JButton("Calculate");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calculateBiclusterCoherenceMeasures();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		this.panel.add(this.btnNewButton, gbc_btnNewButton);
		
		this.lblConstantVariance = new JLabel("Constant Variance: ");
		GridBagConstraints gbc_lblConstantVariance = new GridBagConstraints();
		gbc_lblConstantVariance.anchor = GridBagConstraints.EAST;
		gbc_lblConstantVariance.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstantVariance.gridx = 0;
		gbc_lblConstantVariance.gridy = 3;
		this.panel.add(this.lblConstantVariance, gbc_lblConstantVariance);
		
		this.textFieldconstvariance = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldconstvariance = new GridBagConstraints();
		gbc_textFieldconstvariance.gridwidth = 2;
		gbc_textFieldconstvariance.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldconstvariance.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldconstvariance.gridx = 1;
		gbc_textFieldconstvariance.gridy = 3;
		this.panel.add(this.textFieldconstvariance, gbc_textFieldconstvariance);
		this.textFieldconstvariance.setColumns(10);
		
		this.lblAdditiveVariance = new JLabel("Additive Variance:");
		GridBagConstraints gbc_lblAdditiveVariance = new GridBagConstraints();
		gbc_lblAdditiveVariance.anchor = GridBagConstraints.EAST;
		gbc_lblAdditiveVariance.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdditiveVariance.gridx = 0;
		gbc_lblAdditiveVariance.gridy = 4;
		this.panel.add(this.lblAdditiveVariance, gbc_lblAdditiveVariance);
		
		this.textFieldaddvariance = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldaddvariance = new GridBagConstraints();
		gbc_textFieldaddvariance.gridwidth = 2;
		gbc_textFieldaddvariance.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldaddvariance.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldaddvariance.gridx = 1;
		gbc_textFieldaddvariance.gridy = 4;
		this.panel.add(this.textFieldaddvariance, gbc_textFieldaddvariance);
		this.textFieldaddvariance.setColumns(10);
		
		this.lblMultiplicativeVariance = new JLabel("Multiplicative Variance:");
		GridBagConstraints gbc_lblMultiplicativeVariance = new GridBagConstraints();
		gbc_lblMultiplicativeVariance.anchor = GridBagConstraints.EAST;
		gbc_lblMultiplicativeVariance.insets = new Insets(0, 0, 5, 5);
		gbc_lblMultiplicativeVariance.gridx = 0;
		gbc_lblMultiplicativeVariance.gridy = 5;
		this.panel.add(this.lblMultiplicativeVariance, gbc_lblMultiplicativeVariance);
		
		this.textFieldmultivariance = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldmultivariance = new GridBagConstraints();
		gbc_textFieldmultivariance.gridwidth = 2;
		gbc_textFieldmultivariance.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldmultivariance.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldmultivariance.gridx = 1;
		gbc_textFieldmultivariance.gridy = 5;
		this.panel.add(this.textFieldmultivariance, gbc_textFieldmultivariance);
		this.textFieldmultivariance.setColumns(10);
		
		this.lblSignVariance = new JLabel("Sign Variance:");
		GridBagConstraints gbc_lblSignVariance = new GridBagConstraints();
		gbc_lblSignVariance.anchor = GridBagConstraints.EAST;
		gbc_lblSignVariance.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignVariance.gridx = 0;
		gbc_lblSignVariance.gridy = 6;
		this.panel.add(this.lblSignVariance, gbc_lblSignVariance);
		
		this.textFieldsignvariance = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldsignvariance = new GridBagConstraints();
		gbc_textFieldsignvariance.gridwidth = 2;
		gbc_textFieldsignvariance.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldsignvariance.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldsignvariance.gridx = 1;
		gbc_textFieldsignvariance.gridy = 6;
		this.panel.add(this.textFieldsignvariance, gbc_textFieldsignvariance);
		this.textFieldsignvariance.setColumns(10);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Overlap Between 2 Biclusters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 0;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,0.0,1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.lblBicluster_1 = new JLabel("Bicluster 1:");
		GridBagConstraints gbc_lblBicluster_1 = new GridBagConstraints();
		gbc_lblBicluster_1.anchor = GridBagConstraints.EAST;
		gbc_lblBicluster_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBicluster_1.gridx = 0;
		gbc_lblBicluster_1.gridy = 0;
		this.panel_1.add(this.lblBicluster_1, gbc_lblBicluster_1);
		
		this.comboBoxoverlap1 = new JComboBox();
		GridBagConstraints gbc_comboBoxoverlap1 = new GridBagConstraints();
		gbc_comboBoxoverlap1.gridwidth = 3;
		gbc_comboBoxoverlap1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxoverlap1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxoverlap1.gridx = 1;
		gbc_comboBoxoverlap1.gridy = 0;
		this.panel_1.add(this.comboBoxoverlap1, gbc_comboBoxoverlap1);
		
		this.lblBicluster_2 = new JLabel("Bicluster 2:");
		GridBagConstraints gbc_lblBicluster_2 = new GridBagConstraints();
		gbc_lblBicluster_2.anchor = GridBagConstraints.EAST;
		gbc_lblBicluster_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblBicluster_2.gridx = 0;
		gbc_lblBicluster_2.gridy = 1;
		this.panel_1.add(this.lblBicluster_2, gbc_lblBicluster_2);
		
		this.comboBoxoverlap2 = new JComboBox();
		GridBagConstraints gbc_comboBoxoverlap2 = new GridBagConstraints();
		gbc_comboBoxoverlap2.gridwidth = 3;
		gbc_comboBoxoverlap2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxoverlap2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxoverlap2.gridx = 1;
		gbc_comboBoxoverlap2.gridy = 1;
		this.panel_1.add(this.comboBoxoverlap2, gbc_comboBoxoverlap2);
		
		this.label = new JLabel("Dimension:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		this.panel_1.add(this.label, gbc_label);
		
		this.comboBoxoverlapdim = new JComboBox(AnalysisTypeDimension.values());
		GridBagConstraints gbc_comboBoxoverlapdim = new GridBagConstraints();
		gbc_comboBoxoverlapdim.gridwidth = 3;
		gbc_comboBoxoverlapdim.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxoverlapdim.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxoverlapdim.gridx = 1;
		gbc_comboBoxoverlapdim.gridy = 2;
		this.panel_1.add(this.comboBoxoverlapdim, gbc_comboBoxoverlapdim);
		
		this.buttoncalculateoverlap = new JButton("Calculate");
		this.buttoncalculateoverlap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calculateOverlapBetweenTwoBiclusters();
			}
		});
		GridBagConstraints gbc_buttoncalculateoverlap = new GridBagConstraints();
		gbc_buttoncalculateoverlap.gridwidth = 3;
		gbc_buttoncalculateoverlap.insets = new Insets(0, 0, 5, 5);
		gbc_buttoncalculateoverlap.gridx = 1;
		gbc_buttoncalculateoverlap.gridy = 3;
		this.panel_1.add(this.buttoncalculateoverlap, gbc_buttoncalculateoverlap);
		
		this.lblOverlap = new JLabel("Overlap:");
		GridBagConstraints gbc_lblOverlap = new GridBagConstraints();
		gbc_lblOverlap.anchor = GridBagConstraints.EAST;
		gbc_lblOverlap.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverlap.gridx = 0;
		gbc_lblOverlap.gridy = 4;
		this.panel_1.add(this.lblOverlap, gbc_lblOverlap);
		
		this.textFieldoverlapresult = new JTextField();
		GridBagConstraints gbc_textFieldoverlapresult = new GridBagConstraints();
		gbc_textFieldoverlapresult.gridwidth = 2;
		gbc_textFieldoverlapresult.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldoverlapresult.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldoverlapresult.gridx = 1;
		gbc_textFieldoverlapresult.gridy = 4;
		this.panel_1.add(this.textFieldoverlapresult, gbc_textFieldoverlapresult);
		this.textFieldoverlapresult.setColumns(10);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, "Filter Biclusters by Overlap Treshold", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 4;
		gbc_panel_2.gridwidth = 5;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 7;
		gbc_panel_2.gridy = 0;
		add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel_2.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel_2.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.btnNewButtonaverageoverlap = new JButton("Average Overlap");
		this.btnNewButtonaverageoverlap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calculatedAverageOverlap();
			}
		});
		GridBagConstraints gbc_btnNewButtonaverageoverlap = new GridBagConstraints();
		gbc_btnNewButtonaverageoverlap.gridwidth = 2;
		gbc_btnNewButtonaverageoverlap.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonaverageoverlap.gridx = 0;
		gbc_btnNewButtonaverageoverlap.gridy = 0;
		this.panel_2.add(this.btnNewButtonaverageoverlap, gbc_btnNewButtonaverageoverlap);
		
		this.textFieldaverageoverlap = new CopyPasteJTextField();
		GridBagConstraints gbc_textFieldaverageoverlap = new GridBagConstraints();
		gbc_textFieldaverageoverlap.gridwidth = 2;
		gbc_textFieldaverageoverlap.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldaverageoverlap.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldaverageoverlap.gridx = 2;
		gbc_textFieldaverageoverlap.gridy = 0;
		this.panel_2.add(this.textFieldaverageoverlap, gbc_textFieldaverageoverlap);
		this.textFieldaverageoverlap.setColumns(10);
		
		this.lblTreshold = new JLabel("Threshold:");
		GridBagConstraints gbc_lblTreshold = new GridBagConstraints();
		gbc_lblTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblTreshold.gridx = 0;
		gbc_lblTreshold.gridy = 1;
		this.panel_2.add(this.lblTreshold, gbc_lblTreshold);
		
		this.spinnerthreshold = new JSpinner();
		this.spinnerthreshold.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerthreshold = new GridBagConstraints();
		gbc_spinnerthreshold.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerthreshold.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerthreshold.gridx = 1;
		gbc_spinnerthreshold.gridy = 1;
		this.panel_2.add(this.spinnerthreshold, gbc_spinnerthreshold);
		
		this.btnNewButtonfilter = new JButton("Filter");
		this.btnNewButtonfilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					filterBiclustersByOverlap();
				} catch (Exception e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		GridBagConstraints gbc_btnNewButtonfilter = new GridBagConstraints();
		gbc_btnNewButtonfilter.anchor = GridBagConstraints.WEST;
		gbc_btnNewButtonfilter.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButtonfilter.gridheight = 2;
		gbc_btnNewButtonfilter.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonfilter.gridx = 2;
		gbc_btnNewButtonfilter.gridy = 1;
		this.panel_2.add(this.btnNewButtonfilter, gbc_btnNewButtonfilter);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new TitledBorder(null, "State:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 1;
		this.panel_2.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.lblNewLabelprocessed = new JLabel("It is unfiltered");
		GridBagConstraints gbc_lblNewLabelprocessed = new GridBagConstraints();
		gbc_lblNewLabelprocessed.gridx = 0;
		gbc_lblNewLabelprocessed.gridy = 0;
		this.panel_3.add(this.lblNewLabelprocessed, gbc_lblNewLabelprocessed);
		this.lblNewLabelprocessed.setForeground(Color.BLUE);
		
		this.lblMaxNumberBiclusters = new JLabel("<html>Max. Number<br>Biclusters:</html>");
		GridBagConstraints gbc_lblMaxNumberBiclusters = new GridBagConstraints();
		gbc_lblMaxNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblMaxNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxNumberBiclusters.gridx = 0;
		gbc_lblMaxNumberBiclusters.gridy = 2;
		this.panel_2.add(this.lblMaxNumberBiclusters, gbc_lblMaxNumberBiclusters);
		
		this.spinnermaxbics = new JSpinner();
		
		GridBagConstraints gbc_spinnermaxbics = new GridBagConstraints();
		gbc_spinnermaxbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermaxbics.insets = new Insets(0, 0, 5, 5);
		gbc_spinnermaxbics.gridx = 1;
		gbc_spinnermaxbics.gridy = 2;
		this.panel_2.add(this.spinnermaxbics, gbc_spinnermaxbics);
		
		this.btnNewButtonexportfiltered = new JButton("Export to file");
		this.btnNewButtonexportfiltered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filteredresultsbythreshold!=null)
					try {
						exportFilteredToFile();
					} catch (IOException e1) {
						Workbench.getInstance().error(e1);
					}
			}
		});
		GridBagConstraints gbc_btnNewButtonexportfiltered = new GridBagConstraints();
		gbc_btnNewButtonexportfiltered.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButtonexportfiltered.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonexportfiltered.gridwidth = 3;
		gbc_btnNewButtonexportfiltered.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonexportfiltered.gridx = 1;
		gbc_btnNewButtonexportfiltered.gridy = 3;
		this.panel_2.add(this.btnNewButtonexportfiltered, gbc_btnNewButtonexportfiltered);
		
		this.btnNewButtonputinclipboard = new JButton("Put in Clipboard");
		this.btnNewButtonputinclipboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filteredresultsbythreshold!=null)
					try {
						putFilteredInClipboard();
					} catch (InvalidElementListException e1) {
						Workbench.getInstance().error(e1);
					}
				
			}
		});
		GridBagConstraints gbc_btnNewButtonputinclipboard = new GridBagConstraints();
		gbc_btnNewButtonputinclipboard.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButtonputinclipboard.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonputinclipboard.gridwidth = 3;
		gbc_btnNewButtonputinclipboard.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonputinclipboard.gridx = 1;
		gbc_btnNewButtonputinclipboard.gridy = 4;
		this.panel_2.add(this.btnNewButtonputinclipboard, gbc_btnNewButtonputinclipboard);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Coherence Measures For All Biclusters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 8;
		gbc_panel_4.gridwidth = 6;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1};
		gbl_panel_4.rowHeights = new int[]{1,1,1,1,1,1,1,1,1};
		gbl_panel_4.columnWeights = new double[]{1.0};
		gbl_panel_4.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		panel_4.setLayout(gbl_panel_4);
		
		panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_4.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{1,1,1};
		gbl_panel_5.rowHeights = new int[]{1};
		gbl_panel_5.columnWeights = new double[]{0.2,1.0,0.2};
		gbl_panel_5.rowWeights = new double[]{1.0};
		panel_5.setLayout(gbl_panel_5);
		
		lblDimension_1 = new JLabel("Dimension:");
		GridBagConstraints gbc_lblDimension_1 = new GridBagConstraints();
		gbc_lblDimension_1.anchor = GridBagConstraints.EAST;
		gbc_lblDimension_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblDimension_1.gridx = 0;
		gbc_lblDimension_1.gridy = 0;
		panel_5.add(lblDimension_1, gbc_lblDimension_1);
		
		comboBoxdimallcoherence = new JComboBox(AnalysisTypeDimension.values());
		GridBagConstraints gbc_comboBoxdimallcoherence = new GridBagConstraints();
		gbc_comboBoxdimallcoherence.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxdimallcoherence.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxdimallcoherence.gridx = 1;
		gbc_comboBoxdimallcoherence.gridy = 0;
		panel_5.add(comboBoxdimallcoherence, gbc_comboBoxdimallcoherence);
		
		btnCalculateallcoherence = new JButton("Calculate");
		GridBagConstraints gbc_btnCalculateallcoherence = new GridBagConstraints();
		gbc_btnCalculateallcoherence.gridx = 2;
		gbc_btnCalculateallcoherence.gridy = 0;
		panel_5.add(btnCalculateallcoherence, gbc_btnCalculateallcoherence);
		btnCalculateallcoherence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateCoherenceMeasuresAllBiclusters();
			}
		});
		
		scrollPaneallcoherencemeas = new JScrollPane();
		GridBagConstraints gbc_scrollPaneallcoherencemeas = new GridBagConstraints();
		gbc_scrollPaneallcoherencemeas.gridheight = 8;
		gbc_scrollPaneallcoherencemeas.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneallcoherencemeas.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneallcoherencemeas.gridx = 0;
		gbc_scrollPaneallcoherencemeas.gridy = 1;
		panel_4.add(scrollPaneallcoherencemeas, gbc_scrollPaneallcoherencemeas);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Overlap between All Biclusters", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridheight = 8;
		gbc_panel_6.gridwidth = 6;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 6;
		gbc_panel_6.gridy = 4;
		add(this.panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{1, 0};
		gbl_panel_6.rowHeights = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		this.panel_6.setLayout(gbl_panel_6);
		
		this.panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		this.panel_6.add(this.panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{1, 1, 1, 0};
		gbl_panel_7.rowHeights = new int[]{1, 0};
		gbl_panel_7.columnWeights = new double[]{0.2, 1.0, 0.2, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_7.setLayout(gbl_panel_7);
		
		this.label_1 = new JLabel("Dimension:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		this.panel_7.add(this.label_1, gbc_label_1);
		
		this.comboBoxoverlapalldim = new JComboBox(AnalysisTypeDimension.values());
		GridBagConstraints gbc_comboBoxoverlapalldim = new GridBagConstraints();
		gbc_comboBoxoverlapalldim.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxoverlapalldim.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxoverlapalldim.gridx = 1;
		gbc_comboBoxoverlapalldim.gridy = 0;
		this.panel_7.add(this.comboBoxoverlapalldim, gbc_comboBoxoverlapalldim);
		
		this.buttonoverlapallbiclusters = new JButton("Calculate");
		this.buttonoverlapallbiclusters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calculateOverlapBetweenBiclusters();
				
			}
		});
		GridBagConstraints gbc_buttonoverlapallbiclusters = new GridBagConstraints();
		gbc_buttonoverlapallbiclusters.gridx = 2;
		gbc_buttonoverlapallbiclusters.gridy = 0;
		this.panel_7.add(this.buttonoverlapallbiclusters, gbc_buttonoverlapallbiclusters);
		
		this.scrollPaneoverlapallbics = new JScrollPane();
		GridBagConstraints gbc_scrollPaneoverlapallbics = new GridBagConstraints();
		gbc_scrollPaneoverlapallbics.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneoverlapallbics.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneoverlapallbics.gridheight = 8;
		gbc_scrollPaneoverlapallbics.gridx = 0;
		gbc_scrollPaneoverlapallbics.gridy = 1;
		this.panel_6.add(this.scrollPaneoverlapallbics, gbc_scrollPaneoverlapallbics);
	}
	
	/**
	 * Adds the bicluster list results.
	 *
	 * @param results the results
	 * @param proj the proj
	 */
	public void addBiclusterListResults(BiclusterList results, Project proj) {
		this.results=results;
		this.proj=proj;
		String[] biclusterstitlearray=getBiclustersTitleList();
		
		DefaultComboBoxModel model1=new DefaultComboBoxModel<>(biclusterstitlearray);
		comboBoxbiclusterscoherence.setModel(model1);
		AutoCompleteDecorator.decorate(comboBoxbiclusterscoherence);
		
		DefaultComboBoxModel model2=new DefaultComboBoxModel<>(biclusterstitlearray);
		comboBoxoverlap1.setModel(model2);
		AutoCompleteDecorator.decorate(comboBoxoverlap1);
		
		DefaultComboBoxModel model3=new DefaultComboBoxModel<>(biclusterstitlearray);
		comboBoxoverlap2.setModel(model3);
		AutoCompleteDecorator.decorate(comboBoxoverlap2);
		
		this.spinnermaxbics.setModel(new SpinnerNumberModel(results.getNumberBiclusters(), 0, results.getNumberBiclusters(), 1));
		btnNewButtonexportfiltered.setEnabled(false);
		btnNewButtonputinclipboard.setEnabled(false);
		
		coherencetable=new InformationTable(new String[] {"Bicluster","Constant","Additive","Multiplicative","Sign"});
		scrollPaneallcoherencemeas.setViewportView(coherencetable);
		
		overlapallbicstable=new InformationTable(new String[] {"Bicluster","Bicluster","Overlap"});
		scrollPaneoverlapallbics.setViewportView(overlapallbicstable);
	}

	/**
	 * Gets the biclusters title list.
	 *
	 * @return the biclusters title list
	 */
	private String[] getBiclustersTitleList(){
		String[] title=new String[results.size()];
		for (int i = 0; i < results.size(); i++) {
			title[i]="Bicluster "+String.valueOf(i+1);
		}
		
		return title;
	}
	
	
	/**
	 * Calculate bicluster coherence measures.
	 */
	private void calculateBiclusterCoherenceMeasures() {
		int index= comboBoxbiclusterscoherence.getSelectedIndex();
		BiclusterResult bic=results.get(index);
		
		AnalysisTypeDimension dim=(AnalysisTypeDimension) comboBoxcoherencedim.getSelectedItem();
		
		Quartet<Double, Double, Double, Double> measures=CoherenceAnalyser.getCoherenceMeasuresForBicluster(bic, dim);
		
		textFieldconstvariance.setText(String.valueOf(measures.getValue0()));
		textFieldaddvariance.setText(String.valueOf(measures.getValue1()));
		textFieldmultivariance.setText(String.valueOf(measures.getValue2()));
		textFieldsignvariance.setText(String.valueOf(measures.getValue3()));
	}
	
   /**
    * Calculate overlap between two biclusters.
    */
   private void calculateOverlapBetweenTwoBiclusters() {
	   
	   int bic1idx=comboBoxoverlap1.getSelectedIndex();
	   int bic2idx=comboBoxoverlap2.getSelectedIndex();
	   
	   BiclusterResult bic1=results.get(bic1idx);
	   BiclusterResult bic2=results.get(bic2idx);
	   
	   
	   AnalysisTypeDimension dim=(AnalysisTypeDimension) comboBoxoverlapdim.getSelectedItem();
	   if(dim.equals(AnalysisTypeDimension.BOTH))
		   textFieldoverlapresult.setText(String.valueOf(OverlapAnalyser.getOverlapBeetwenTwoBiclusters(bic1, bic2)));
	   else if(dim.equals(AnalysisTypeDimension.ROWS))
		   textFieldoverlapresult.setText(String.valueOf(OverlapAnalyser.getOverlapBiclustersRows(bic1, bic2)));
	   else if(dim.equals(AnalysisTypeDimension.COLUMNS))
		   textFieldoverlapresult.setText(String.valueOf(OverlapAnalyser.getOverlapBiclustersColumns(bic1, bic2)));
	   
   }
   
   /**
    * Calculated average overlap.
    */
   private void calculatedAverageOverlap() {
	   double averageoverlap=OverlapAnalyser.AverageOverlap(results);
	   textFieldaverageoverlap.setText(String.valueOf(averageoverlap));
	   textFieldaverageoverlap.setEditable(false);
   }
   
   /**
    * Filter biclusters by overlap.
    *
    * @throws Exception the exception
    */
   private void filterBiclustersByOverlap() throws Exception {
	   
	   double tresh=(double) spinnerthreshold.getValue();
	   int nbics=(int) spinnermaxbics.getValue();
	   
	   filteredresultsbythreshold=OverlapAnalyser.filterBiclusterListWithOverlapThreshold(results, tresh, nbics);
	   
	   if(filteredresultsbythreshold!=null) {
		   StringBuilder str=new StringBuilder();
		   str.append("<html>");
		   str.append("List was filtered<br>");
		   str.append("Original size: "+results.size()+"<br>");
		   str.append("Filtered size: "+filteredresultsbythreshold.size()+"</html>");
		   
		   lblNewLabelprocessed.setText(str.toString());
		   btnNewButtonexportfiltered.setEnabled(true);
			btnNewButtonputinclipboard.setEnabled(true);
	   } 
	   else {
		   btnNewButtonexportfiltered.setEnabled(false);
			btnNewButtonputinclipboard.setEnabled(false);
	   }
		   
   }
   
   /**
    * Export filtered to file.
    *
    * @throws IOException Signals that an I/O exception has occurred.
    */
   private void exportFilteredToFile() throws IOException {
	   
	    JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("JBiclustGE Default Format","bicge"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("JBiclustGE json Format","json"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("biclust R package Format","txt"));
		
		int tag=fileChooser.showSaveDialog(this);
		if(tag==JFileChooser.APPROVE_OPTION) {
			ExtensionFileFilter filter=(ExtensionFileFilter) fileChooser.getFileFilter();
			
			if(fileChooser.getSelectedFile()!=null) {
				String dir=FilenameUtils.getFullPath(fileChooser.getSelectedFile().getAbsolutePath());
				String filename=FilenameUtils.getBaseName(fileChooser.getSelectedFile().getAbsolutePath());
				if(filter.getSingleDescription().equals("JBiclustGE Default Format"))
					filteredresultsbythreshold.writeBiclusterListToJBiclustGEOutputFormat(dir, filename);
				else if(filter.getSingleDescription().equals("JBiclustGE json Format")) {
					 boolean appendexpressiondataset=false;
		        		int flag=JOptionPane.showConfirmDialog(this, "Do you want to include gene expression dataset in JSON file?", "", JOptionPane.YES_NO_OPTION);
		        		if(flag==JOptionPane.YES_OPTION)
		        			appendexpressiondataset=true;
					filteredresultsbythreshold.writeBiclusterListToJSONFormat(fileChooser.getSelectedFile().getAbsolutePath(), appendexpressiondataset);
				}
				else if(filter.getSingleDescription().equals("biclust R package Format")) {
					filteredresultsbythreshold.writeBiclusterListToBiclustRPackageFormat(dir, filename, "txt");
				}
			}
			
		}
	   
	   
   }
   
   /**
    * Put filtered in clipboard.
    *
    * @throws InvalidElementListException the invalid element list exception
    */
   private void putFilteredInClipboard() throws InvalidElementListException {
	   
	   BiclusteringMethod method=BiclusteringMethod.getMethodbyName(filteredresultsbythreshold.getUsedmethod());
	   
	   BiclusteringResultBox resultsbox=GenericOperation.getResultsBox(proj, "tmp", filteredresultsbythreshold, method);
	   
	   IElementList<IBiclusteringMethodResult> elems=proj.getBiclusteringMethodResultElementListByClass(resultsbox.getByClass());
		int nelems=0;
		if(elems!=null)
			nelems=elems.size();
		
		nelems=nelems+1;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm.ss");
		//String dataid=method.getAlgorithmName()+"filtered_"+now.format(formatter)+"_results_"+nelems;
		String dataid=method.getAlgorithmName()+"_filtered_overlapthreshold@"+String.valueOf(spinnerthreshold.getValue())+"_results_"+nelems;
		resultsbox.setName(dataid,false);
		GenericOperation.addBiclusteringResult(proj, resultsbox.getByClass(), resultsbox, method.getAlgorithmName()+" Results");
	   
   }
   
   /**
    * Calculate coherence measures all biclusters.
    */
   private void calculateCoherenceMeasuresAllBiclusters() {
	   
	   AnalysisTypeDimension dim=(AnalysisTypeDimension) comboBoxdimallcoherence.getSelectedItem();
	   LinkedHashMap<Integer, Quartet<Double, Double, Double, Double>> calculated=CoherenceAnalyser.getCoherenceMeasuresForBiclusterList(results, dim);
	   
	   List<Object[]> listobj=new ArrayList<>();
	   for (Integer bicindex : calculated.keySet()) {
		   Quartet<Double, Double, Double, Double> inres=calculated.get(bicindex);
		   
		   Object[] in=new Object[]{(bicindex+1),inres.getValue0(),inres.getValue1(),inres.getValue2(),inres.getValue3()};
		   listobj.add(in);
	   }
	   coherencetable.setData(listobj);
   }
   
   /**
    * Calculate overlap between biclusters.
    */
   private void calculateOverlapBetweenBiclusters() {


	   if(overlapallrunning) {
		   workeroverlap.cancel(true);
		   while (!workeroverlap.isCancelled()) {
			   try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LogMessageCenter.getLogger().toClass(getClass()).addErrorMessage(e);
			}
			
		   }
		   overlapallrunning=false;
		   buttonoverlapallbiclusters.setText("Calculate");
		   overlapallbicstable=new InformationTable(new String[] {"Bicluster","Bicluster","Overlap"});
		   scrollPaneoverlapallbics.setViewportView(overlapallbicstable);
		   updateUI();

	   }
	   else {
		   //overlapallbicstable.clearTable();
		   overlapallbicstable=new InformationTable(new String[] {"Bicluster","Bicluster","Overlap"});
		   scrollPaneoverlapallbics.setViewportView(overlapallbicstable);
		   overlapallrunning=true;
		   workeroverlap = new SwingWorker<Boolean, Void>() { 
			  
			   @Override
			   protected Boolean doInBackground() throws Exception {
				   buttonoverlapallbiclusters.setText("stop");
				   AnalysisTypeDimension dim=(AnalysisTypeDimension) comboBoxoverlapalldim.getSelectedItem();

				   Queue<BiclusterResult> queue=new LinkedList<>();
				   queue.addAll(results);
				   int n=0;
				   while (!queue.isEmpty() && overlapallrunning) {
					   BiclusterResult top= queue.poll();
					   Iterator<BiclusterResult> g=queue.iterator();
					   int z=n+1;
					   while (g.hasNext() && overlapallrunning) {
					
						   BiclusterResult next= g.next();
						   double overlap=0;
						   if(dim.equals(AnalysisTypeDimension.BOTH))
							   overlap=OverlapAnalyser.getOverlapBeetwenTwoBiclusters(top, next);
						   else if(dim.equals(AnalysisTypeDimension.ROWS))
							   overlap=OverlapAnalyser.getOverlapBiclustersRows(top, next);
						   else if(dim.equals(AnalysisTypeDimension.COLUMNS))
							   overlap=OverlapAnalyser.getOverlapBiclustersColumns(top, next);

						   Object[] in=new Object[]{"Bicluster "+String.valueOf(n+1),"Bicluster "+String.valueOf(z+1),overlap};
						   if(overlapallrunning)
							   overlapallbicstable.addRow(in);

						   z++;
					   }

					   n++;
				   }
				   buttonoverlapallbiclusters.setText("Calculate");
				   overlapallrunning=false;
				   return true;
			   }

		   };
		
			workeroverlap.execute();
	   }
   }

}
