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
package jbiclustgegui.gui.synthetic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.analysis.syntheticdata.distribution.NormalDistributionDataFactory;
import jbiclustge.analysis.syntheticdata.factory.biclusters.BiclusterModelType;
import jbiclustge.analysis.syntheticdata.factory.props.SyntheticDataProperties;
import jbiclustgegui.gui.synthetic.configpanel.ConstantModelSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.ConstantModelWithAjustmentsSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.ConstantUpModelSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.PlaidModelSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.ScaleModelSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.ShiftModelSettingsPanel;
import jbiclustgegui.gui.synthetic.configpanel.ShiftScaleModelSettingsPanel;
import jbiclustgegui.gui.synthetic.containers.ConstantAdditiveColumnAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantAdditiveRowAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantMultiplicativeColumnsAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantMultiplicativeRowAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantUPModelContainer;
import jbiclustgegui.gui.synthetic.containers.IBiclusterModelDataFactory;
import jbiclustgegui.gui.synthetic.containers.PlaidModelContainer;
import jbiclustgegui.gui.synthetic.containers.ScaleModelContainer;
import jbiclustgegui.gui.synthetic.containers.ShiftModelContainer;
import jbiclustgegui.gui.synthetic.containers.ShiftScaleModelContainer;
import jbiclustgegui.gui.synthetic.tables.TableBiclustersNoiseLevels;
import jbiclustgegui.gui.synthetic.tables.TableBiclustersVariableSize;
import jbiclustgegui.gui.synthetic.tables.TableDatasetAsymmetricOverlapProfiles;
import jbiclustgegui.gui.synthetic.tables.TableDatasetNoiseLevels;
import jbiclustgegui.gui.synthetic.tables.TableDatasetSymmetricOverlapLevels;
import jbiclustgegui.gui.synthetic.tables.TableOverlapBetweenBiclusters;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class SyntheticDatasetMakerDialog.
 */
public class SyntheticDatasetMakerDialog extends JDialog implements ActionListener,InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The rec. */
	protected ParamsReceiver rec;
	
	/** The integer text fieldnumberbiclusters. */
	private IntegerTextField integerTextFieldnumberbiclusters;
	
	/** The chckbx biclusters with uniform. */
	private JCheckBox chckbxBiclustersWithUniform;
	
	/** The chckbx biclusters with variable. */
	private JCheckBox chckbxBiclustersWithVariable;
	
	/** The integer text fieldunifbicrows. */
	private IntegerTextField integerTextFieldunifbicrows;
	
	/** The integer text fieldunifbiccolumns. */
	private IntegerTextField integerTextFieldunifbiccolumns;
	
	/** The tablebiclusterssize. */
	private TableBiclustersVariableSize tablebiclusterssize;
	
	/** The integer text fielddatasetnumberrows. */
	private IntegerTextField integerTextFielddatasetnumberrows;
	
	/** The integer text field numbercolumnsdataset. */
	private IntegerTextField integerTextFieldNumbercolumnsdataset;
	
	/** The chckbx new check boxsuffledata. */
	private JCheckBox chckbxNewCheckBoxsuffledata;
	
	/** The double text fieldmeanbackground. */
	private DoubleTextField doubleTextFieldmeanbackground;
	
	/** The double text fieldsdbackground. */
	private DoubleTextField doubleTextFieldsdbackground;
	
	/** The chckbx usesamebacksamplenumber. */
	private JCheckBox chckbxUsesamebacksamplenumber;
	
	/** The tablenoiselevels. */
	private TableDatasetNoiseLevels tablenoiselevels;
	
	/** The chckbx experiments with noise. */
	private JCheckBox chckbxExperimentsWithNoise;
	
	/** The btn add noise level. */
	private JButton btnAddNoiseLevel;
	
	/** The tablesymmetricoverlapprofiles. */
	private TableDatasetSymmetricOverlapLevels tablesymmetricoverlapprofiles;
	
	/** The asymmetricoverlapprofilestable. */
	private TableDatasetAsymmetricOverlapProfiles asymmetricoverlapprofilestable;
	
	/** The tableoverlap. */
	private TableOverlapBetweenBiclusters tableoverlap;
	
	/** The tablebiclusternoiselevel. */
	private TableBiclustersNoiseLevels tablebiclusternoiselevel;
	
	/** The chckbx define noise in. */
	private JCheckBox chckbxDefineNoiseIn;
	
	/** The integer text fieldsamplesperdataset. */
	private IntegerTextField integerTextFieldsamplesperdataset;
	
	
	
	/** The listmodels. */
	private JList<BiclusterModelType> listmodels;
	
	/** The listmodelsmodel. */
	private DefaultListModel<BiclusterModelType> listmodelsmodel;
	
	/** The listfactory. */
	private JList<IBiclusterModelDataFactory> listfactory;
	
	/** The listfactorymodel. */
	private DefaultListModel<IBiclusterModelDataFactory> listfactorymodel;
	
	/** The panel base ofoverlap. */
	private JPanel panelBaseOfoverlap;
	
	/** The chckbx disable overlap. */
	private JCheckBox chckbxDisableOverlap;
	
	/** The chckbx asymmetric overlap. */
	private JCheckBox chckbxAsymmetricOverlap;
	
	/** The chckbx symmetric overlapnew. */
	private JCheckBox chckbxSymmetricOverlapnew;
	
	/** The uniformbiclusterssize. */
	private static String UNIFORMBICLUSTERSSIZE="UNIFORMBICLUSTERSSIZE";
	
	/** The variablebiclusterssize. */
	private static String VARIABLEBICLUSTERSSIZE="VARIABLEBICLUSTERSSIZE";
	
	/** The symmetricoverlap. */
	//private static String USEOVERLAP="USEOVERLAP";
	private static String SYMMETRICOVERLAP="SYMMETRICOVERLAP";
	
	/** The asymmetricoverlap. */
	private static String ASYMMETRICOVERLAP="ASYMMETRICOVERLAP";
	
	/** The disableoverlap. */
	private static String DISABLEOVERLAP="DISABLEOVERLAP";
	
	/** The editasymprofile. */
	private static String EDITASYMPROFILE="EDITASYMPROFILE";
	
	/** The removeasymprofile. */
	private static String REMOVEASYMPROFILE="REMOVEASYMPROFILE";
	
	/** The addmodel. */
	//private static String CUSTOMOVERLAP="CUSTOMOVERLAP";
	private static String ADDMODEL="ADDMODEL";
	
	/** The removemodel. */
	private static String REMOVEMODEL="REMOVEMODEL";
	
	/** The saveindir. */
	private static String SAVEINDIR="SAVEINDIR";
	
	/** The enableexpnoise. */
	private static String ENABLEEXPNOISE="ENABLEEXPNOISE";
	
	/** The addexpnoise. */
	private static String ADDEXPNOISE="ADDEXPNOISE";
	
	/** The definenoisebiclusters. */
	private static String DEFINENOISEBICLUSTERS="DEFINENOISEBICLUSTERS";
	
	/** The close. */
	private static String CLOSE="close";
	
	/** The ok. */
	private static String OK="ok";
	
	
	/** The currentparameters. */
	private SyntheticDataProperties currentparameters;
	
	/** The text fieldsavedir. */
	private JTextField textFieldsavedir;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			SyntheticDatasetMakerDialog dialog = new SyntheticDatasetMakerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new synthetic dataset maker dialog.
	 */
	public SyntheticDatasetMakerDialog() {
		super(Workbench.getInstance().getMainFrame());
		tablebiclusterssize=new TableBiclustersVariableSize();
		tablebiclusterssize.setEnabled(false);
		
		initGUI();
		initComponents();
	}
	

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 1233, 764);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "General Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.gridwidth = 10;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
			gbl_panel.rowHeights = new int[]{0, 1,1,1,1};
			gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0, 1.0,1.0,1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 5;
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1};
				gbl_panel_1.rowHeights = new int[]{1,1,1,1,1,1};
				gbl_panel_1.columnWeights = new double[]{0.0,1.0};
				gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					JLabel lblNumberRowsDataset = new JLabel("Number Rows dataset:");
					GridBagConstraints gbc_lblNumberRowsDataset = new GridBagConstraints();
					gbc_lblNumberRowsDataset.insets = new Insets(0, 0, 5, 5);
					gbc_lblNumberRowsDataset.gridx = 0;
					gbc_lblNumberRowsDataset.gridy = 0;
					panel_1.add(lblNumberRowsDataset, gbc_lblNumberRowsDataset);
				}
				{
					integerTextFielddatasetnumberrows = new IntegerTextField();
					GridBagConstraints gbc_integerTextFielddatasetnumberrows = new GridBagConstraints();
					gbc_integerTextFielddatasetnumberrows.fill = GridBagConstraints.HORIZONTAL;
					gbc_integerTextFielddatasetnumberrows.insets = new Insets(0, 0, 5, 0);
					gbc_integerTextFielddatasetnumberrows.gridx = 1;
					gbc_integerTextFielddatasetnumberrows.gridy = 0;
					panel_1.add(integerTextFielddatasetnumberrows, gbc_integerTextFielddatasetnumberrows);
					integerTextFielddatasetnumberrows.setText("500");
					integerTextFielddatasetnumberrows.setSize(100, 30);
				}
				{
					JLabel lblNewLabel = new JLabel("Number Columns dataset:");
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel.gridx = 0;
					gbc_lblNewLabel.gridy = 1;
					panel_1.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					integerTextFieldNumbercolumnsdataset = new IntegerTextField();
					GridBagConstraints gbc_integerTextFieldNumbercolumnsdataset = new GridBagConstraints();
					gbc_integerTextFieldNumbercolumnsdataset.fill = GridBagConstraints.HORIZONTAL;
					gbc_integerTextFieldNumbercolumnsdataset.insets = new Insets(0, 0, 5, 0);
					gbc_integerTextFieldNumbercolumnsdataset.gridx = 1;
					gbc_integerTextFieldNumbercolumnsdataset.gridy = 1;
					panel_1.add(integerTextFieldNumbercolumnsdataset, gbc_integerTextFieldNumbercolumnsdataset);
					integerTextFieldNumbercolumnsdataset.setText("250");
				}
				{
					JLabel lblNumberSyntheticBiclusters = new JLabel("Number synthetic biclusters:");
					GridBagConstraints gbc_lblNumberSyntheticBiclusters = new GridBagConstraints();
					gbc_lblNumberSyntheticBiclusters.insets = new Insets(0, 10, 5, 5);
					gbc_lblNumberSyntheticBiclusters.gridx = 0;
					gbc_lblNumberSyntheticBiclusters.gridy = 2;
					panel_1.add(lblNumberSyntheticBiclusters, gbc_lblNumberSyntheticBiclusters);
				}
				{
					integerTextFieldnumberbiclusters = new IntegerTextField();
					GridBagConstraints gbc_integerTextFieldnumberbiclusters = new GridBagConstraints();
					gbc_integerTextFieldnumberbiclusters.fill = GridBagConstraints.HORIZONTAL;
					gbc_integerTextFieldnumberbiclusters.insets = new Insets(0, 0, 5, 0);
					gbc_integerTextFieldnumberbiclusters.gridx = 1;
					gbc_integerTextFieldnumberbiclusters.gridy = 2;
					panel_1.add(integerTextFieldnumberbiclusters, gbc_integerTextFieldnumberbiclusters);
					integerTextFieldnumberbiclusters.setText("1");
					//integerTextFieldnumberbiclusters.addPropertyChangeListener("changenumberbics", tablebiclusterssize);
					integerTextFieldnumberbiclusters.getDocument().addDocumentListener(new DocumentListener() {
					    public void changedUpdate(DocumentEvent e) {
					    	
					    }
					    public void removeUpdate(DocumentEvent e) {
					       //whatever you want
					    }
					    
					    public void insertUpdate(DocumentEvent e) {
					    	if(!integerTextFieldnumberbiclusters.getText().isEmpty()) {
					    		
					    		if(chckbxBiclustersWithUniform.isSelected())
					    			tablebiclusterssize.setNumberOfBiclusters(Integer.parseInt(integerTextFieldnumberbiclusters.getText()), Integer.parseInt(integerTextFieldunifbicrows.getText()), Integer.parseInt(integerTextFieldunifbiccolumns.getText()));
					    		else
					    			tablebiclusterssize.setNumberOfBiclusters(Integer.parseInt(integerTextFieldnumberbiclusters.getText()),null,null);
					    		
					    		
					    		if(chckbxAsymmetricOverlap.isSelected() || chckbxSymmetricOverlapnew.isSelected()) {
					    			
					    			chckbxDisableOverlap.setSelected(true);
					    			disableOverlapCMD();
					    		}
					    		
					    		if(chckbxDefineNoiseIn.isSelected())
					    			tablebiclusternoiselevel.setNoiseOfBiclusters(integerTextFieldnumberbiclusters.getIntegerValue(), null);	
					    	}
	
					    }

					});
				}
				{
					chckbxNewCheckBoxsuffledata = new JCheckBox("Shuffle data");
					GridBagConstraints gbc_chckbxNewCheckBoxsuffledata = new GridBagConstraints();
					gbc_chckbxNewCheckBoxsuffledata.gridwidth = 2;
					gbc_chckbxNewCheckBoxsuffledata.insets = new Insets(0, 0, 5, 5);
					gbc_chckbxNewCheckBoxsuffledata.gridx = 0;
					gbc_chckbxNewCheckBoxsuffledata.gridy = 3;
					panel_1.add(chckbxNewCheckBoxsuffledata, gbc_chckbxNewCheckBoxsuffledata);
				}
			}
			{
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
				gbc_tabbedPane.gridheight = 5;
				gbc_tabbedPane.gridwidth = 6;
				gbc_tabbedPane.fill = GridBagConstraints.BOTH;
				gbc_tabbedPane.gridx = 4;
				gbc_tabbedPane.gridy = 0;
				panel.add(tabbedPane, gbc_tabbedPane);
				{
					JPanel panelconfigurebics = new JPanel();
					tabbedPane.addTab("Biclusters Size", null, panelconfigurebics, null);
					GridBagLayout gbl_panelconfigurebics = new GridBagLayout();
					gbl_panelconfigurebics.columnWidths = new int[]{1,1,1,1,1,1};
					gbl_panelconfigurebics.rowHeights = new int[]{1,1,1,1,1,1};
					gbl_panelconfigurebics.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
					gbl_panelconfigurebics.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
					panelconfigurebics.setLayout(gbl_panelconfigurebics);
					{
						chckbxBiclustersWithUniform = new JCheckBox("Biclusters with uniform size:");
						chckbxBiclustersWithUniform.setSelected(true);
						GridBagConstraints gbc_chckbxBiclustersWithUniform = new GridBagConstraints();
						gbc_chckbxBiclustersWithUniform.anchor = GridBagConstraints.EAST;
						gbc_chckbxBiclustersWithUniform.insets = new Insets(0, 0, 5, 5);
						gbc_chckbxBiclustersWithUniform.gridx = 0;
						gbc_chckbxBiclustersWithUniform.gridy = 0;
						panelconfigurebics.add(chckbxBiclustersWithUniform, gbc_chckbxBiclustersWithUniform);
						chckbxBiclustersWithUniform.setActionCommand(UNIFORMBICLUSTERSSIZE);
						chckbxBiclustersWithUniform.addActionListener(this);
					}
					{
						JLabel lblNumberRows = new JLabel("number rows:");
						GridBagConstraints gbc_lblNumberRows = new GridBagConstraints();
						gbc_lblNumberRows.anchor = GridBagConstraints.EAST;
						gbc_lblNumberRows.insets = new Insets(0, 0, 5, 5);
						gbc_lblNumberRows.gridx = 1;
						gbc_lblNumberRows.gridy = 0;
						panelconfigurebics.add(lblNumberRows, gbc_lblNumberRows);
					}
					{
						integerTextFieldunifbicrows = new IntegerTextField();
						integerTextFieldunifbicrows.setSize(50, 30);
						integerTextFieldunifbicrows.setText("20");
						GridBagConstraints gbc_integerTextFieldunifbicrows = new GridBagConstraints();
						gbc_integerTextFieldunifbicrows.insets = new Insets(0, 0, 5, 5);
						gbc_integerTextFieldunifbicrows.fill = GridBagConstraints.HORIZONTAL;
						gbc_integerTextFieldunifbicrows.gridx = 2;
						gbc_integerTextFieldunifbicrows.gridy = 0;
						panelconfigurebics.add(integerTextFieldunifbicrows, gbc_integerTextFieldunifbicrows);
						integerTextFieldunifbicrows.getDocument().addDocumentListener(new DocumentListener() {
						    public void changedUpdate(DocumentEvent e) {
						    	
						    }
						    public void removeUpdate(DocumentEvent e) {
						       //whatever you want
						    }
						    
						    public void insertUpdate(DocumentEvent e) {
						    	tablebiclusterssize.setUniformValuesToAllBiclusters(integerTextFieldunifbicrows.getIntegerValue(), integerTextFieldunifbiccolumns.getIntegerValue());
						    }
						

						});
					}
					{
						JLabel lblNumberColumns = new JLabel("number columns:");
						GridBagConstraints gbc_lblNumberColumns = new GridBagConstraints();
						gbc_lblNumberColumns.anchor = GridBagConstraints.EAST;
						gbc_lblNumberColumns.insets = new Insets(0, 0, 5, 5);
						gbc_lblNumberColumns.gridx = 3;
						gbc_lblNumberColumns.gridy = 0;
						panelconfigurebics.add(lblNumberColumns, gbc_lblNumberColumns);
					}
					{
						integerTextFieldunifbiccolumns = new IntegerTextField();
						integerTextFieldunifbiccolumns.setText("20");
						integerTextFieldunifbiccolumns.setSize(50, 30);
						GridBagConstraints gbc_integerTextFieldunifbiccolumns = new GridBagConstraints();
						gbc_integerTextFieldunifbiccolumns.insets = new Insets(0, 0, 5, 5);
						gbc_integerTextFieldunifbiccolumns.fill = GridBagConstraints.HORIZONTAL;
						gbc_integerTextFieldunifbiccolumns.gridx = 4;
						gbc_integerTextFieldunifbiccolumns.gridy = 0;
						panelconfigurebics.add(integerTextFieldunifbiccolumns, gbc_integerTextFieldunifbiccolumns);
						integerTextFieldunifbiccolumns.getDocument().addDocumentListener(new DocumentListener() {
						    public void changedUpdate(DocumentEvent e) {
						    	
						    }
						    public void removeUpdate(DocumentEvent e) {
						       //whatever you want
						    }
						    
						    public void insertUpdate(DocumentEvent e) {
						    	tablebiclusterssize.setUniformValuesToAllBiclusters(integerTextFieldunifbicrows.getIntegerValue(), integerTextFieldunifbiccolumns.getIntegerValue());
						    }
						

						});
					}
					{
						chckbxBiclustersWithVariable = new JCheckBox("Biclusters with variable size");
						GridBagConstraints gbc_chckbxBiclustersWithVariable = new GridBagConstraints();
						gbc_chckbxBiclustersWithVariable.anchor = GridBagConstraints.EAST;
						gbc_chckbxBiclustersWithVariable.insets = new Insets(0, 0, 5, 5);
						gbc_chckbxBiclustersWithVariable.gridx = 0;
						gbc_chckbxBiclustersWithVariable.gridy = 1;
						panelconfigurebics.add(chckbxBiclustersWithVariable, gbc_chckbxBiclustersWithVariable);
						chckbxBiclustersWithVariable.setActionCommand(VARIABLEBICLUSTERSSIZE);
						chckbxBiclustersWithVariable.addActionListener(this);
					}
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new TitledBorder(null, "Shape of biclusters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						GridBagConstraints gbc_panel_1 = new GridBagConstraints();
						gbc_panel_1.gridheight = 5;
						gbc_panel_1.gridwidth = 5;
						gbc_panel_1.insets = new Insets(0, 0, 5, 0);
						gbc_panel_1.fill = GridBagConstraints.BOTH;
						gbc_panel_1.gridx = 1;
						gbc_panel_1.gridy = 1;
						panelconfigurebics.add(panel_1, gbc_panel_1);
						GridBagLayout gbl_panel_1 = new GridBagLayout();
						gbl_panel_1.columnWidths = new int[]{0, 0};
						gbl_panel_1.rowHeights = new int[]{0, 0};
						gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
						panel_1.setLayout(gbl_panel_1);
						{
							JScrollPane scrollPane = new JScrollPane();
							GridBagConstraints gbc_scrollPane = new GridBagConstraints();
							gbc_scrollPane.fill = GridBagConstraints.BOTH;
							gbc_scrollPane.gridx = 0;
							gbc_scrollPane.gridy = 0;
							panel_1.add(scrollPane, gbc_scrollPane);
							{
								scrollPane.setViewportView(tablebiclusterssize);
							}
						}
					}
				}
				{
					JPanel panelbicsnoise = new JPanel();
					tabbedPane.addTab("Biclusters noise", null, panelbicsnoise, null);
					GridBagLayout gbl_panelbicsnoise = new GridBagLayout();
					gbl_panelbicsnoise.columnWidths = new int[]{1,1,1,1};
					gbl_panelbicsnoise.rowHeights = new int[]{1,1,1,1,1};
					gbl_panelbicsnoise.columnWeights = new double[]{0.0,1.0,1.0,1.0};
					gbl_panelbicsnoise.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
					panelbicsnoise.setLayout(gbl_panelbicsnoise);
					{
						chckbxDefineNoiseIn = new JCheckBox("Define noise in biclusters");
						GridBagConstraints gbc_chckbxDefineNoiseIn = new GridBagConstraints();
						gbc_chckbxDefineNoiseIn.insets = new Insets(0, 0, 5, 5);
						gbc_chckbxDefineNoiseIn.gridx = 0;
						gbc_chckbxDefineNoiseIn.gridy = 0;
						panelbicsnoise.add(chckbxDefineNoiseIn, gbc_chckbxDefineNoiseIn);
						chckbxDefineNoiseIn.setActionCommand( DEFINENOISEBICLUSTERS);
						chckbxDefineNoiseIn.addActionListener(this);
					}
					{
						
						tablebiclusternoiselevel=new TableBiclustersNoiseLevels();
						JScrollPane scrollPanenoisebics = new JScrollPane(tablebiclusternoiselevel);
						GridBagConstraints gbc_scrollPanenoisebics = new GridBagConstraints();
						gbc_scrollPanenoisebics.gridheight = 5;
						gbc_scrollPanenoisebics.gridwidth = 2;
						gbc_scrollPanenoisebics.insets = new Insets(0, 0, 0, 5);
						gbc_scrollPanenoisebics.fill = GridBagConstraints.BOTH;
						gbc_scrollPanenoisebics.gridx = 1;
						gbc_scrollPanenoisebics.gridy = 0;
						panelbicsnoise.add(scrollPanenoisebics, gbc_scrollPanenoisebics);
					}
				}
				{
					JPanel panelnewOverlap = new JPanel();
					tabbedPane.addTab("Biclusters Overlap", null, panelnewOverlap, null);
					GridBagLayout gbl_panelnewOverlap = new GridBagLayout();
					gbl_panelnewOverlap.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
					gbl_panelnewOverlap.rowHeights = new int[]{1,1,1,1};
					gbl_panelnewOverlap.columnWeights = new double[]{0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
					gbl_panelnewOverlap.rowWeights = new double[]{1.0,1.0,1.0,1.0};
					panelnewOverlap.setLayout(gbl_panelnewOverlap);
					{
						chckbxDisableOverlap = new JCheckBox("Disable");
						GridBagConstraints gbc_chckbxDisable = new GridBagConstraints();
						gbc_chckbxDisable.anchor = GridBagConstraints.WEST;
						gbc_chckbxDisable.insets = new Insets(0, 20, 5, 5);
						gbc_chckbxDisable.gridx = 0;
						gbc_chckbxDisable.gridy = 0;
						panelnewOverlap.add(chckbxDisableOverlap, gbc_chckbxDisable);
						chckbxDisableOverlap.setActionCommand(DISABLEOVERLAP);
						chckbxDisableOverlap.addActionListener(this);
						chckbxDisableOverlap.setSelected(true);
					}
					{
						panelBaseOfoverlap = new JPanel();
						GridBagConstraints gbc_panelBaseOfoverlap = new GridBagConstraints();
						gbc_panelBaseOfoverlap.gridheight = 4;
						gbc_panelBaseOfoverlap.gridwidth = 9;
						gbc_panelBaseOfoverlap.insets = new Insets(0, 0, 5, 5);
						gbc_panelBaseOfoverlap.fill = GridBagConstraints.BOTH;
						gbc_panelBaseOfoverlap.gridx = 1;
						gbc_panelBaseOfoverlap.gridy = 0;
						panelnewOverlap.add(panelBaseOfoverlap, gbc_panelBaseOfoverlap);
						GridBagLayout gbl_panelBaseOfoverlap = new GridBagLayout();
						gbl_panelBaseOfoverlap.columnWidths = new int[]{1};
						gbl_panelBaseOfoverlap.rowHeights = new int[]{1};
						gbl_panelBaseOfoverlap.columnWeights = new double[]{1.0};
						gbl_panelBaseOfoverlap.rowWeights = new double[]{1.0};
						panelBaseOfoverlap.setLayout(gbl_panelBaseOfoverlap);
						/*{
							JPanel panelOverlapDisable = new JPanel();
							GridBagConstraints gbc_panelOverlapDisable = new GridBagConstraints();
							gbc_panelOverlapDisable.fill = GridBagConstraints.BOTH;
							gbc_panelOverlapDisable.gridx = 0;
							gbc_panelOverlapDisable.gridy = 0;
							this.panelBaseOfoverlap.add(panelOverlapDisable, gbc_panelOverlapDisable);
						}*/
						/*{
							JPanel panelAsymmetricPanel = new JPanel();
							GridBagConstraints gbc_panelAsymmetricPanel = new GridBagConstraints();
							gbc_panelAsymmetricPanel.fill = GridBagConstraints.BOTH;
							gbc_panelAsymmetricPanel.gridx = 0;
							gbc_panelAsymmetricPanel.gridy = 0;
							this.panelBaseOfoverlap.add(panelAsymmetricPanel, gbc_panelAsymmetricPanel);
							GridBagLayout gbl_panelAsymmetricPanel = new GridBagLayout();
							gbl_panelAsymmetricPanel.columnWidths = new int[]{1,1,1,1,1,1};
							gbl_panelAsymmetricPanel.rowHeights = new int[]{1,1,1,1};
							gbl_panelAsymmetricPanel.columnWeights = new double[]{0.0,1.0,1.0,0.0,0.0,1.0};
							gbl_panelAsymmetricPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
							panelAsymmetricPanel.setLayout(gbl_panelAsymmetricPanel);
							{
								JButton btnNewButton = new JButton("<html><center>create<br>profile</center></html>");
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										if(integerTextFieldnumberbiclusters.getIntegerValue()>1) {
											tableoverlap.clearTable();
											tableoverlap.setNumberOfBiclusters(integerTextFieldnumberbiclusters.getIntegerValue(),null,null);
										}
										else
											Workbench.getInstance().warn("The number of synthetic biclusters must be 2 or higher");
									}
								});
								GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
								gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
								gbc_btnNewButton.gridx = 0;
								gbc_btnNewButton.gridy = 0;
								panelAsymmetricPanel.add(btnNewButton, gbc_btnNewButton);
							}
							{
								
								JScrollPane scrollPane = new JScrollPane(tableoverlap);
								GridBagConstraints gbc_scrollPane = new GridBagConstraints();
								gbc_scrollPane.gridheight = 4;
								gbc_scrollPane.gridwidth = 3;
								gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
								gbc_scrollPane.fill = GridBagConstraints.BOTH;
								gbc_scrollPane.gridx = 1;
								gbc_scrollPane.gridy = 0;
								panelAsymmetricPanel.add(scrollPane, gbc_scrollPane);
							}
							{
								JButton btnAdd = new JButton("<html><center>add<br>profile</center></html>");
								btnAdd.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										asymmetricoverlapprofilestable.addAsymmetricProfile(tableoverlap.getOverlapBetweenBiclusters());
										tableoverlap.clearTable();
									}
								});
								GridBagConstraints gbc_btnAdd = new GridBagConstraints();
								gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
								gbc_btnAdd.gridx = 4;
								gbc_btnAdd.gridy = 0;
								panelAsymmetricPanel.add(btnAdd, gbc_btnAdd);
							}
							{
								asymmetricoverlapprofilestable=new TableDatasetAsymmetricOverlapProfiles();
								JScrollPane scrollPane = new JScrollPane(asymmetricoverlapprofilestable);
								GridBagConstraints gbc_scrollPane = new GridBagConstraints();
								gbc_scrollPane.gridheight = 4;
								gbc_scrollPane.fill = GridBagConstraints.BOTH;
								gbc_scrollPane.gridx = 5;
								gbc_scrollPane.gridy = 0;
								panelAsymmetricPanel.add(scrollPane, gbc_scrollPane);
							}
							{
								JButton btnEditprofile = new JButton("<html><center>Edit<br>profile</center></html>");
								GridBagConstraints gbc_btnEditprofile = new GridBagConstraints();
								gbc_btnEditprofile.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnEditprofile.insets = new Insets(0, 0, 5, 5);
								gbc_btnEditprofile.gridx = 4;
								gbc_btnEditprofile.gridy = 1;
								panelAsymmetricPanel.add(btnEditprofile, gbc_btnEditprofile);
							}
							{
								JButton btnNewButtonremoveprofile = new JButton("Remove");
								GridBagConstraints gbc_btnNewButtonremoveprofile = new GridBagConstraints();
								gbc_btnNewButtonremoveprofile.insets = new Insets(0, 0, 0, 5);
								gbc_btnNewButtonremoveprofile.gridx = 4;
								gbc_btnNewButtonremoveprofile.gridy = 3;
								panelAsymmetricPanel.add(btnNewButtonremoveprofile, gbc_btnNewButtonremoveprofile);
							}
						}*/
						/*{
							JPanel panelSymmetricOverlapPanel = new JPanel();
							GridBagConstraints gbc_panelSymmetricOverlapPanel = new GridBagConstraints();
							gbc_panelSymmetricOverlapPanel.fill = GridBagConstraints.BOTH;
							gbc_panelSymmetricOverlapPanel.gridx = 0;
							gbc_panelSymmetricOverlapPanel.gridy = 0;
							panelBaseOfoverlap.add(panelSymmetricOverlapPanel, gbc_panelSymmetricOverlapPanel);
							GridBagLayout gbl_panelSymmetricOverlapPanel = new GridBagLayout();
							gbl_panelSymmetricOverlapPanel.columnWidths = new int[]{1,1,1,1};
							gbl_panelSymmetricOverlapPanel.rowHeights = new int[]{1,1,1,1};
							gbl_panelSymmetricOverlapPanel.columnWeights = new double[]{1.0,1.0,1.0,1.0};
							gbl_panelSymmetricOverlapPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
							panelSymmetricOverlapPanel.setLayout(gbl_panelSymmetricOverlapPanel);
							{
								JButton btnAddLevelsymoverlap = new JButton("Add Level");
								btnAddLevelsymoverlap.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(chckbxSymmetricOverlapnew.isSelected()) {
											tablesymmetricoverlapprofiles.addLevelCell();
											
										}
										
									}
								});
								GridBagConstraints gbc_btnAddLevelsymoverlap = new GridBagConstraints();
								gbc_btnAddLevelsymoverlap.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnAddLevelsymoverlap.insets = new Insets(0, 0, 5, 5);
								gbc_btnAddLevelsymoverlap.gridx = 0;
								gbc_btnAddLevelsymoverlap.gridy = 0;
								panelSymmetricOverlapPanel.add(btnAddLevelsymoverlap, gbc_btnAddLevelsymoverlap);
							}
							{
								tablesymmetricoverlapprofiles=new TableDatasetSymmetricOverlapLevels();
								JScrollPane scrollPane = new JScrollPane(tablesymmetricoverlapprofiles);
								GridBagConstraints gbc_scrollPane = new GridBagConstraints();
								gbc_scrollPane.gridheight = 4;
								gbc_scrollPane.gridwidth = 2;
								gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
								gbc_scrollPane.fill = GridBagConstraints.BOTH;
								gbc_scrollPane.gridx = 1;
								gbc_scrollPane.gridy = 0;
								panelSymmetricOverlapPanel.add(scrollPane, gbc_scrollPane);
							}
							{
								JButton btnRemoveLevel = new JButton("Remove Level");
								btnRemoveLevel.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										if(tablesymmetricoverlapprofiles.getRowCount()>0 && tablesymmetricoverlapprofiles.getSelectedRow()>-1)
											tablesymmetricoverlapprofiles.removeLevelIndex(tablesymmetricoverlapprofiles.getSelectedRow());
									}
								});
								GridBagConstraints gbc_btnRemoveLevel = new GridBagConstraints();
								gbc_btnRemoveLevel.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnRemoveLevel.insets = new Insets(0, 0, 5, 5);
								gbc_btnRemoveLevel.gridx = 0;
								gbc_btnRemoveLevel.gridy = 1;
								panelSymmetricOverlapPanel.add(btnRemoveLevel, gbc_btnRemoveLevel);
							}
						}*/
					}
					{
						chckbxSymmetricOverlapnew = new JCheckBox("Symmetric Overlap");
						GridBagConstraints gbc_chckbxSymmetricOverlapnew = new GridBagConstraints();
						gbc_chckbxSymmetricOverlapnew.anchor = GridBagConstraints.WEST;
						gbc_chckbxSymmetricOverlapnew.insets = new Insets(0, 20, 5, 5);
						gbc_chckbxSymmetricOverlapnew.gridx = 0;
						gbc_chckbxSymmetricOverlapnew.gridy = 1;
						panelnewOverlap.add(chckbxSymmetricOverlapnew, gbc_chckbxSymmetricOverlapnew);
						chckbxSymmetricOverlapnew.setActionCommand(SYMMETRICOVERLAP);
						chckbxSymmetricOverlapnew.addActionListener(this);
					}
					{
						chckbxAsymmetricOverlap = new JCheckBox("Asymmetric Overlap");
						GridBagConstraints gbc_chckbxAsymmetricOverlap = new GridBagConstraints();
						gbc_chckbxAsymmetricOverlap.anchor = GridBagConstraints.WEST;
						gbc_chckbxAsymmetricOverlap.insets = new Insets(0, 20, 5, 5);
						gbc_chckbxAsymmetricOverlap.gridx = 0;
						gbc_chckbxAsymmetricOverlap.gridy = 2;
						panelnewOverlap.add(chckbxAsymmetricOverlap, gbc_chckbxAsymmetricOverlap);
						chckbxAsymmetricOverlap.setActionCommand(ASYMMETRICOVERLAP);
						chckbxAsymmetricOverlap.addActionListener(this);
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Background Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1};
			gbl_panel.rowHeights = new int[]{1,1,1,1,1};
			gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				chckbxUsesamebacksamplenumber = new JCheckBox("<html>Use the same background<br>in the same sample number</html>");
				GridBagConstraints gbc_chckbxUsesamebacksamplenumber = new GridBagConstraints();
				gbc_chckbxUsesamebacksamplenumber.anchor = GridBagConstraints.WEST;
				gbc_chckbxUsesamebacksamplenumber.gridwidth = 4;
				gbc_chckbxUsesamebacksamplenumber.insets = new Insets(0, 20, 5, 0);
				gbc_chckbxUsesamebacksamplenumber.gridx = 0;
				gbc_chckbxUsesamebacksamplenumber.gridy = 0;
				panel.add(chckbxUsesamebacksamplenumber, gbc_chckbxUsesamebacksamplenumber);
				chckbxUsesamebacksamplenumber.setSelected(true);
			}
			{
				JLabel lblMeanOfDistribution = new JLabel("Mean of distribution:");
				GridBagConstraints gbc_lblMeanOfDistribution = new GridBagConstraints();
				gbc_lblMeanOfDistribution.anchor = GridBagConstraints.EAST;
				gbc_lblMeanOfDistribution.insets = new Insets(0, 0, 5, 5);
				gbc_lblMeanOfDistribution.gridx = 0;
				gbc_lblMeanOfDistribution.gridy = 1;
				panel.add(lblMeanOfDistribution, gbc_lblMeanOfDistribution);
			}
			{
				doubleTextFieldmeanbackground = new DoubleTextField();
				doubleTextFieldmeanbackground.setText("0.0");
				GridBagConstraints gbc_doubleTextFieldmeanbackground = new GridBagConstraints();
				gbc_doubleTextFieldmeanbackground.insets = new Insets(0, 0, 5, 5);
				gbc_doubleTextFieldmeanbackground.fill = GridBagConstraints.HORIZONTAL;
				gbc_doubleTextFieldmeanbackground.gridx = 1;
				gbc_doubleTextFieldmeanbackground.gridy = 1;
				panel.add(doubleTextFieldmeanbackground, gbc_doubleTextFieldmeanbackground);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("<html>Standard deviation<br>of distribution</html>");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				doubleTextFieldsdbackground = new DoubleTextField();
				doubleTextFieldsdbackground.setText("1.0");
				GridBagConstraints gbc_doubleTextFieldsdbackground = new GridBagConstraints();
				gbc_doubleTextFieldsdbackground.insets = new Insets(0, 0, 5, 5);
				gbc_doubleTextFieldsdbackground.fill = GridBagConstraints.HORIZONTAL;
				gbc_doubleTextFieldsdbackground.gridx = 1;
				gbc_doubleTextFieldsdbackground.gridy = 2;
				panel.add(doubleTextFieldsdbackground, gbc_doubleTextFieldsdbackground);
			}
		}
		{
			{
				{
					listmodelsmodel=new DefaultListModel<>();
				}
			}
			{
				{
					listfactorymodel=new DefaultListModel<>();
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Dataset Noise Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1};
			gbl_panel.rowHeights = new int[]{1,1,1,1};
			gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				chckbxExperimentsWithNoise = new JCheckBox("Generate experiments with noise");
				GridBagConstraints gbc_chckbxExperimentsWithNoise = new GridBagConstraints();
				gbc_chckbxExperimentsWithNoise.gridwidth = 3;
				gbc_chckbxExperimentsWithNoise.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxExperimentsWithNoise.gridx = 0;
				gbc_chckbxExperimentsWithNoise.gridy = 0;
				panel.add(chckbxExperimentsWithNoise, gbc_chckbxExperimentsWithNoise);
				chckbxExperimentsWithNoise.setActionCommand(ENABLEEXPNOISE);
				chckbxExperimentsWithNoise.addActionListener(this);
			}
			{
				btnAddNoiseLevel = new JButton("<html><center>Add<br> Noise<br>level</center></html>");
				GridBagConstraints gbc_btnAddNoiseLevel = new GridBagConstraints();
				gbc_btnAddNoiseLevel.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddNoiseLevel.insets = new Insets(0, 0, 5, 5);
				gbc_btnAddNoiseLevel.gridx = 0;
				gbc_btnAddNoiseLevel.gridy = 1;
				panel.add(btnAddNoiseLevel, gbc_btnAddNoiseLevel);
				btnAddNoiseLevel.setActionCommand(ADDEXPNOISE);
				btnAddNoiseLevel.addActionListener(this);
			}
			{
				tablenoiselevels=new TableDatasetNoiseLevels();
				JScrollPane scrollPane = new JScrollPane(tablenoiselevels);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 3;
				gbc_scrollPane.gridwidth = 3;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 1;
				panel.add(scrollPane, gbc_scrollPane);
				
			}
			{
				JButton btnNewButtonremovenoiselevel = new JButton("Remove level");
				btnNewButtonremovenoiselevel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(tablenoiselevels.getRowCount()>0 && chckbxExperimentsWithNoise.isSelected()) {
							tablenoiselevels.removeLevelIndex(tablenoiselevels.getSelectedRow());
						}
						
					}
				});
				GridBagConstraints gbc_btnNewButtonremovenoiselevel = new GridBagConstraints();
				gbc_btnNewButtonremovenoiselevel.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButtonremovenoiselevel.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButtonremovenoiselevel.gridx = 0;
				gbc_btnNewButtonremovenoiselevel.gridy = 2;
				panel.add(btnNewButtonremovenoiselevel, gbc_btnNewButtonremovenoiselevel);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Save Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 4;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 6;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1};
			gbl_panel.rowHeights = new int[]{1,1,1,1,1};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Folder where the datasets will be saved", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1};
				gbl_panel_1.rowHeights = new int[]{1,1};
				gbl_panel_1.columnWeights = new double[]{1.0};
				gbl_panel_1.rowWeights = new double[]{1.0,1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					textFieldsavedir = new JTextField();
					GridBagConstraints gbc_textFieldsavedir = new GridBagConstraints();
					gbc_textFieldsavedir.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldsavedir.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldsavedir.gridx = 0;
					gbc_textFieldsavedir.gridy = 0;
					panel_1.add(textFieldsavedir, gbc_textFieldsavedir);
					textFieldsavedir.setColumns(10);
				}
				{
					JButton btnSaveDatasetsAt = new JButton("Save Datasets at folder");
					GridBagConstraints gbc_btnSaveDatasetsAt = new GridBagConstraints();
					gbc_btnSaveDatasetsAt.anchor = GridBagConstraints.NORTH;
					gbc_btnSaveDatasetsAt.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnSaveDatasetsAt.gridx = 0;
					gbc_btnSaveDatasetsAt.gridy = 1;
					panel_1.add(btnSaveDatasetsAt, gbc_btnSaveDatasetsAt);
					btnSaveDatasetsAt.setActionCommand(SAVEINDIR);
					{
						JPanel panel_2 = new JPanel();
						GridBagConstraints gbc_panel_2 = new GridBagConstraints();
						gbc_panel_2.insets = new Insets(0, 0, 5, 0);
						gbc_panel_2.fill = GridBagConstraints.BOTH;
						gbc_panel_2.gridx = 0;
						gbc_panel_2.gridy = 2;
						panel.add(panel_2, gbc_panel_2);
						GridBagLayout gbl_panel_2 = new GridBagLayout();
						gbl_panel_2.columnWidths = new int[]{1,1};
						gbl_panel_2.rowHeights = new int[]{1};
						gbl_panel_2.columnWeights = new double[]{1.0,1.0};
						gbl_panel_2.rowWeights = new double[]{1.0};
						panel_2.setLayout(gbl_panel_2);
						{
							JLabel lblNumberOfSamples = new JLabel("Number of Samples per dataset:");
							GridBagConstraints gbc_lblNumberOfSamples = new GridBagConstraints();
							gbc_lblNumberOfSamples.anchor = GridBagConstraints.EAST;
							gbc_lblNumberOfSamples.insets = new Insets(0, 0, 0, 5);
							gbc_lblNumberOfSamples.gridx = 0;
							gbc_lblNumberOfSamples.gridy = 0;
							panel_2.add(lblNumberOfSamples, gbc_lblNumberOfSamples);
						}
						{
							integerTextFieldsamplesperdataset = new IntegerTextField();
							integerTextFieldsamplesperdataset.setText("20");
							GridBagConstraints gbc_integerTextFieldsamplesperdataset = new GridBagConstraints();
							gbc_integerTextFieldsamplesperdataset.fill = GridBagConstraints.HORIZONTAL;
							gbc_integerTextFieldsamplesperdataset.gridx = 1;
							gbc_integerTextFieldsamplesperdataset.gridy = 0;
							panel_2.add(integerTextFieldsamplesperdataset, gbc_integerTextFieldsamplesperdataset);
						}
					}
					btnSaveDatasetsAt.addActionListener(this);
				}
			}
		}
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Bicluster synthetic model settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.gridwidth = 10;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		panel.setLayout(gbl_panel);
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		listmodels = new JList();
		scrollPane.setViewportView(listmodels);
		listmodels.setModel(listmodelsmodel);
		JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.gridheight = 6;
		gbc_scrollPane1.gridwidth = 4;
		gbc_scrollPane1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane1.gridx = 3;
		gbc_scrollPane1.gridy = 0;
		panel.add(scrollPane1, gbc_scrollPane1);
		listfactory = new JList();
		scrollPane1.setViewportView(listfactory);
		listfactory.setModel(listfactorymodel);
		{
			JButton btnAddModel = new JButton("Add Model");
			GridBagConstraints gbc_btnAddModel = new GridBagConstraints();
			gbc_btnAddModel.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnAddModel.insets = new Insets(0, 0, 5, 5);
			gbc_btnAddModel.gridx = 2;
			gbc_btnAddModel.gridy = 1;
			panel.add(btnAddModel, gbc_btnAddModel);
			btnAddModel.addActionListener(this);
			btnAddModel.setActionCommand(ADDMODEL);
			
		}
		{
			JButton btnRemoveModel = new JButton("Remove Model");
			GridBagConstraints gbc_btnRemoveModel = new GridBagConstraints();
			gbc_btnRemoveModel.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRemoveModel.insets = new Insets(0, 0, 5, 5);
			gbc_btnRemoveModel.gridx = 2;
			gbc_btnRemoveModel.gridy = 2;
			panel.add(btnRemoveModel, gbc_btnRemoveModel);
			btnRemoveModel.addActionListener(this);
			btnRemoveModel.setActionCommand(REMOVEMODEL);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CLOSE);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		tablebiclusterssize.setNumberOfBiclusters(1,integerTextFieldunifbicrows.getIntegerValue(),integerTextFieldunifbiccolumns.getIntegerValue());
		
		for (BiclusterModelType model : BiclusterModelType.values()) {
			listmodelsmodel.addElement(model);
		}
	}
	
	
	/**
	 * Load symmetri overlap panel.
	 */
	private void loadSymmetriOverlapPanel() {
		
		    panelBaseOfoverlap.removeAll();
			JPanel panelSymmetricOverlapPanel = new JPanel();
			GridBagConstraints gbc_panelSymmetricOverlapPanel = new GridBagConstraints();
			gbc_panelSymmetricOverlapPanel.fill = GridBagConstraints.BOTH;
			gbc_panelSymmetricOverlapPanel.gridx = 0;
			gbc_panelSymmetricOverlapPanel.gridy = 0;
			panelBaseOfoverlap.add(panelSymmetricOverlapPanel, gbc_panelSymmetricOverlapPanel);
			GridBagLayout gbl_panelSymmetricOverlapPanel = new GridBagLayout();
			gbl_panelSymmetricOverlapPanel.columnWidths = new int[]{1,1,1,1};
			gbl_panelSymmetricOverlapPanel.rowHeights = new int[]{1,1,1,1};
			gbl_panelSymmetricOverlapPanel.columnWeights = new double[]{1.0,1.0,1.0,1.0};
			gbl_panelSymmetricOverlapPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
			panelSymmetricOverlapPanel.setLayout(gbl_panelSymmetricOverlapPanel);
			{
				JButton btnAddLevelsymoverlap = new JButton("Add Level");
				btnAddLevelsymoverlap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(chckbxSymmetricOverlapnew.isSelected()) {
							tablesymmetricoverlapprofiles.addLevelCell();
							
						}
						
					}
				});
				GridBagConstraints gbc_btnAddLevelsymoverlap = new GridBagConstraints();
				gbc_btnAddLevelsymoverlap.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAddLevelsymoverlap.insets = new Insets(0, 0, 5, 5);
				gbc_btnAddLevelsymoverlap.gridx = 0;
				gbc_btnAddLevelsymoverlap.gridy = 0;
				panelSymmetricOverlapPanel.add(btnAddLevelsymoverlap, gbc_btnAddLevelsymoverlap);
			}
			{
				tablesymmetricoverlapprofiles=new TableDatasetSymmetricOverlapLevels();
				JScrollPane scrollPane = new JScrollPane(tablesymmetricoverlapprofiles);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 4;
				gbc_scrollPane.gridwidth = 2;
				gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 0;
				panelSymmetricOverlapPanel.add(scrollPane, gbc_scrollPane);
			}
			{
				JButton btnRemoveLevel = new JButton("Remove Level");
				btnRemoveLevel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(tablesymmetricoverlapprofiles.getRowCount()>0 && tablesymmetricoverlapprofiles.getSelectedRow()>-1)
							tablesymmetricoverlapprofiles.removeLevelIndex(tablesymmetricoverlapprofiles.getSelectedRow());
					}
				});
				GridBagConstraints gbc_btnRemoveLevel = new GridBagConstraints();
				gbc_btnRemoveLevel.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnRemoveLevel.insets = new Insets(0, 0, 5, 5);
				gbc_btnRemoveLevel.gridx = 0;
				gbc_btnRemoveLevel.gridy = 1;
				panelSymmetricOverlapPanel.add(btnRemoveLevel, gbc_btnRemoveLevel);
			}
			 panelBaseOfoverlap.updateUI();
			asymmetricoverlapprofilestable=null;
	}
	
	
	/**
	 * Load asymmetric ovelap panel.
	 */
	private void loadAsymmetricOvelapPanel() {
		
			panelBaseOfoverlap.removeAll();
			JPanel panelAsymmetricPanel = new JPanel();
			GridBagConstraints gbc_panelAsymmetricPanel = new GridBagConstraints();
			gbc_panelAsymmetricPanel.fill = GridBagConstraints.BOTH;
			gbc_panelAsymmetricPanel.gridx = 0;
			gbc_panelAsymmetricPanel.gridy = 0;
			this.panelBaseOfoverlap.add(panelAsymmetricPanel, gbc_panelAsymmetricPanel);
			GridBagLayout gbl_panelAsymmetricPanel = new GridBagLayout();
			gbl_panelAsymmetricPanel.columnWidths = new int[]{1,1,1,1,1,1};
			gbl_panelAsymmetricPanel.rowHeights = new int[]{1,1,1,1};
			gbl_panelAsymmetricPanel.columnWeights = new double[]{0.0,1.0,1.0,0.0,0.0,1.0};
			gbl_panelAsymmetricPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
			panelAsymmetricPanel.setLayout(gbl_panelAsymmetricPanel);
			{
				JButton btnNewButton = new JButton("<html><center>create<br>profile</center></html>");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(integerTextFieldnumberbiclusters.getIntegerValue()>1) {
							tableoverlap.clearTable();
							tableoverlap.setNumberOfBiclusters(integerTextFieldnumberbiclusters.getIntegerValue(),null,null);
						}
						else
							Workbench.getInstance().warn("The number of synthetic biclusters must be 2 or higher");
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 0;
				panelAsymmetricPanel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				
				tableoverlap=new TableOverlapBetweenBiclusters();
				JScrollPane scrollPane = new JScrollPane(tableoverlap);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 4;
				gbc_scrollPane.gridwidth = 3;
				gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 0;
				panelAsymmetricPanel.add(scrollPane, gbc_scrollPane);
			}
			{
				JButton btnAdd = new JButton("<html><center>add<br>profile</center></html>");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						asymmetricoverlapprofilestable.addAsymmetricProfile(tableoverlap.getOverlapBetweenBiclusters());
						tableoverlap.clearTable();
					}
				});
				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
				gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
				gbc_btnAdd.gridx = 4;
				gbc_btnAdd.gridy = 0;
				panelAsymmetricPanel.add(btnAdd, gbc_btnAdd);
			}
			{
				asymmetricoverlapprofilestable=new TableDatasetAsymmetricOverlapProfiles();
				JScrollPane scrollPane = new JScrollPane(asymmetricoverlapprofilestable);
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 4;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 5;
				gbc_scrollPane.gridy = 0;
				panelAsymmetricPanel.add(scrollPane, gbc_scrollPane);
			}
			{
				JButton btnEditprofile = new JButton("<html><center>Edit<br>profile</center></html>");
				GridBagConstraints gbc_btnEditprofile = new GridBagConstraints();
				gbc_btnEditprofile.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEditprofile.insets = new Insets(0, 0, 5, 5);
				gbc_btnEditprofile.gridx = 4;
				gbc_btnEditprofile.gridy = 1;
				panelAsymmetricPanel.add(btnEditprofile, gbc_btnEditprofile);
				btnEditprofile.setActionCommand(EDITASYMPROFILE);
				btnEditprofile.addActionListener(this);
			}
			{
				JButton btnNewButtonremoveprofile = new JButton("Remove");
				GridBagConstraints gbc_btnNewButtonremoveprofile = new GridBagConstraints();
				gbc_btnNewButtonremoveprofile.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButtonremoveprofile.gridx = 4;
				gbc_btnNewButtonremoveprofile.gridy = 3;
				panelAsymmetricPanel.add(btnNewButtonremoveprofile, gbc_btnNewButtonremoveprofile);
				btnNewButtonremoveprofile.setActionCommand(REMOVEASYMPROFILE);
				btnNewButtonremoveprofile.addActionListener(this);
			}
			 panelBaseOfoverlap.updateUI();
			tablesymmetricoverlapprofiles=null;
	}
	
	
	/**
	 * Disable overlap.
	 */
	private void disableOverlap() {
		    panelBaseOfoverlap.removeAll();
			JPanel panelOverlapDisable = new JPanel();
			GridBagConstraints gbc_panelOverlapDisable = new GridBagConstraints();
			gbc_panelOverlapDisable.fill = GridBagConstraints.BOTH;
			gbc_panelOverlapDisable.gridx = 0;
			gbc_panelOverlapDisable.gridy = 0;
			this.panelBaseOfoverlap.add(panelOverlapDisable, gbc_panelOverlapDisable);
			panelBaseOfoverlap.updateUI();
			 tablesymmetricoverlapprofiles=null;
			 asymmetricoverlapprofilestable=null;
	}
	

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#finish()
	 */
	@Override
	public void finish() {
		setVisible(false);
		dispose();
		
	}

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(new Dimension(1200, 700));
	    pack();
	    Utilities.centerOnOwner(this);
	    setVisible(true);
		
	}

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#onValidationError(java.lang.Throwable)
	 */
	@Override
	public void onValidationError(Throwable t) {
		Workbench.getInstance().error(t);
		
	}
	
	/**
	 * Use bicluster uniform size.
	 */
	private void useBiclusterUniformSize() {
		
		if(chckbxBiclustersWithUniform.isSelected()) {
			chckbxBiclustersWithVariable.setSelected(false);
			tablebiclusterssize.setEnabled(false);
			integerTextFieldunifbicrows.setEnabled(true);
			integerTextFieldunifbiccolumns.setEnabled(true);
			tablebiclusterssize.setUniformValuesToAllBiclusters(integerTextFieldunifbicrows.getIntegerValue(), integerTextFieldunifbiccolumns.getIntegerValue());
		}
		else {
			chckbxBiclustersWithVariable.setSelected(true);
			tablebiclusterssize.setEnabled(true);
			integerTextFieldunifbicrows.setEnabled(false);
			integerTextFieldunifbiccolumns.setEnabled(false);
		}
	}
	
	
	/**
	 * Use bicluster variable size.
	 */
	private void useBiclusterVariableSize() {
		if(chckbxBiclustersWithVariable.isSelected()) {
			chckbxBiclustersWithUniform.setSelected(false);
			tablebiclusterssize.setEnabled(true);
			integerTextFieldunifbicrows.setEnabled(false);
			integerTextFieldunifbiccolumns.setEnabled(false);
		}
		else {
			chckbxBiclustersWithUniform.setSelected(true);
			tablebiclusterssize.setEnabled(false);
			integerTextFieldunifbicrows.setEnabled(true);
			integerTextFieldunifbiccolumns.setEnabled(true);
			tablebiclusterssize.setUniformValuesToAllBiclusters(integerTextFieldunifbicrows.getIntegerValue(), integerTextFieldunifbiccolumns.getIntegerValue());
		}
	}
	
	
	/**
	 * Disable overlap CMD.
	 */
	private void disableOverlapCMD() {
		
		if(chckbxDisableOverlap.isSelected()) {
			chckbxAsymmetricOverlap.setSelected(false);
			chckbxSymmetricOverlapnew.setSelected(false);
			disableOverlap();
		}
		
	}
	
	
	/**
	 * Use symmetric overlap.
	 */
	private void useSymmetricOverlap() {
		
		if(chckbxSymmetricOverlapnew.isSelected()) {
			chckbxDisableOverlap.setSelected(false);
			chckbxAsymmetricOverlap.setSelected(false);
			loadSymmetriOverlapPanel();
		}
	}
	
	
	/**
	 * Use asymmetric overlap.
	 */
	private void useAsymmetricOverlap() {
		
		if(chckbxAsymmetricOverlap.isSelected()) {
			chckbxDisableOverlap.setSelected(false);
			chckbxSymmetricOverlapnew.setSelected(false);
			loadAsymmetricOvelapPanel();
		}
	}
	
	
	/**
	 * Adds the model.
	 */
	private void addModel() {
		BiclusterModelType model = (BiclusterModelType) listmodels.getSelectedValue();
		IBiclusterModelDataFactory factory= chooseModelSettings(model);
		if(factory!=null) {
			listfactorymodel.addElement(factory);
			listmodelsmodel.removeElement(factory.getTypeBiclusterModel());
		}
	}
	

	/**
	 * Choose model settings.
	 *
	 * @param model the model
	 * @return the i bicluster model data factory
	 */
	private IBiclusterModelDataFactory chooseModelSettings(BiclusterModelType model){
		
		BiclusterModelSettingsDialog dialog=new BiclusterModelSettingsDialog();
		
		if(model.equals(BiclusterModelType.CONSTANTUP)) {
			int tag=dialog.showOpenDialog(new ConstantUpModelSettingsPanel(integerTextFieldnumberbiclusters.getIntegerValue()),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantUPModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		if(model.equals(BiclusterModelType.CONSTANT)) {
			int tag=dialog.showOpenDialog(new ConstantModelSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.CONSTANTADDROWADJ)) {
			int tag=dialog.showOpenDialog(new ConstantModelWithAjustmentsSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantAdditiveRowAdjModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.CONSTANTADDCOLUMNADJ)) {
			int tag=dialog.showOpenDialog(new ConstantModelWithAjustmentsSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantAdditiveColumnAdjModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.CONSTANTMULTROWADJ)) {
			int tag=dialog.showOpenDialog(new ConstantModelWithAjustmentsSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantMultiplicativeRowAdjModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.CONSTANTMULTCOLUMNADJ)) {
			int tag=dialog.showOpenDialog(new ConstantModelWithAjustmentsSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ConstantMultiplicativeColumnsAdjModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.SCALE)) {
			int tag=dialog.showOpenDialog(new ScaleModelSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ScaleModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.SHIFT)) {
			int tag=dialog.showOpenDialog(new ShiftModelSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ShiftModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.SHIFTSCALE)) {
			int tag=dialog.showOpenDialog(new ShiftScaleModelSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new ShiftScaleModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		else if(model.equals(BiclusterModelType.PLAID)) {
			int tag=dialog.showOpenDialog(new PlaidModelSettingsPanel(),model.getName(),this);
			if(tag==BiclusterModelSettingsDialog.APPROVE_OPTION) {
				return new PlaidModelContainer(getCurrentParameters(),dialog.getBiclusterModelSettings());
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * Gets the current parameters.
	 *
	 * @return the current parameters
	 */
	private SyntheticDataProperties getCurrentParameters() {
		if(currentparameters==null) {
		   
			currentparameters=new SyntheticDataProperties()
					.setNumberRowsDataset(integerTextFielddatasetnumberrows.getIntegerValue())
					.setNumberColumnsDataset(integerTextFieldNumbercolumnsdataset.getIntegerValue())
					.setShuffleData(chckbxNewCheckBoxsuffledata.isSelected())
					.setShapeOfBiclusters(tablebiclusterssize.getShapeOfBiclusters());
			
			if(chckbxDefineNoiseIn.isSelected() && tablebiclusternoiselevel.getRowCount()>0)
				currentparameters.setBiclustersNoise(tablebiclusternoiselevel.getNoiseOfBiclusters());
					
			/*
			if(chckbxDefineOverlapBetween.isSelected()) {
				currentparameters.setOverlapBetweenBiclusters(tableoverlap.getOverlapBetweenBiclusters());
			}*/
		}
	    return currentparameters;
	}
	
	
	/**
	 * Removes the model.
	 */
	private void removeModel() {
		IBiclusterModelDataFactory factory=listfactory.getSelectedValue();
		
		listfactorymodel.removeElement(factory);
		listmodelsmodel.addElement(factory.getTypeBiclusterModel());
		
	}
	
	/**
	 * Open save in dir.
	 */
	private void openSaveInDir() {
		Preferences prefs = Preferences.userRoot().node(getClass().getName());
		JFileChooser chooser = new JFileChooser(prefs.get("LAST_USED_FOLDER",new File(".").getAbsolutePath()));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int tag=chooser.showSaveDialog(this);
		if(tag==JFileChooser.APPROVE_OPTION) {
			textFieldsavedir.setText(chooser.getSelectedFile().getAbsolutePath());
			prefs.put("LAST_USED_FOLDER", chooser.getSelectedFile().getParent());
		}
	}
	
	
	/**
	 * Enable experiments with noise.
	 */
	private void enableExperimentsWithNoise() {
		if(chckbxExperimentsWithNoise.isSelected()) {
			btnAddNoiseLevel.setEnabled(true);
			tablenoiselevels.setEnabled(true);
		}
		else {
			btnAddNoiseLevel.setEnabled(false);
			tablenoiselevels.setEnabled(false);
		}
	}
	
	/**
	 * Adds the noise level.
	 */
	private void addNoiseLevel() {
		if(chckbxExperimentsWithNoise.isSelected()) {
			tablenoiselevels.addLevelCell();
		}
	}
	
	/**
	 * Adds the bicluster noise level.
	 */
	private void addBiclusterNoiseLevel() {
		
		if(chckbxDefineNoiseIn.isSelected()) {
			tablebiclusternoiselevel.setNoiseOfBiclusters(integerTextFieldnumberbiclusters.getIntegerValue(), null);	
		}
		else{
			tablebiclusternoiselevel.clearTable();
		}
	}
	
	/**
	 * Edits the asymmetric profile.
	 */
	private void editAsymmetricProfile() {
		
		if(asymmetricoverlapprofilestable.getSelectedRow()>-1) {
			int selectedindex=asymmetricoverlapprofilestable.getSelectedRow();
			ArrayList<Pair<Integer, Integer>> toedit=asymmetricoverlapprofilestable.getSelectedProfile(selectedindex);
			EditAsymmetricProfileDialog profileeditdialog=new EditAsymmetricProfileDialog();
			int tag=profileeditdialog.showOpenDialog(toedit, this);
			if(tag==EditAsymmetricProfileDialog.APPROVE_OPTION) {
				asymmetricoverlapprofilestable.changeProfile(selectedindex,profileeditdialog.getOverlapBetweenBiclusters());
			}
		
		}
		
	}
	
	/**
	 * Removes the asymmetric profile.
	 */
	private void removeAsymmetricProfile() {
		
		if(asymmetricoverlapprofilestable.getSelectedRow()>-1) {
			asymmetricoverlapprofilestable.removeLevelIndex(asymmetricoverlapprofilestable.getSelectedRow());
			Workbench.getInstance().warn("Profile "+asymmetricoverlapprofilestable.getSelectedRow()+" was removed, and list of profile was updated");
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(UNIFORMBICLUSTERSSIZE))
			useBiclusterUniformSize();
		else if(cmd.equals(VARIABLEBICLUSTERSSIZE))
			useBiclusterVariableSize();
		else if(cmd.equals(DISABLEOVERLAP))
			disableOverlapCMD();
		else if(cmd.equals(SYMMETRICOVERLAP))
			useSymmetricOverlap();
		else if(cmd.equals(ASYMMETRICOVERLAP))
			useAsymmetricOverlap();
		else if(cmd.equals(ADDMODEL))
			addModel();
		else if(cmd.equals(REMOVEMODEL))
			removeModel();
		else if(cmd.equals(SAVEINDIR))
			openSaveInDir();
		else if(cmd.equals(ENABLEEXPNOISE))
			enableExperimentsWithNoise();
		else if(cmd.equals(ADDEXPNOISE))
			addNoiseLevel();
		else if(cmd.equals(DEFINENOISEBICLUSTERS))
			addBiclusterNoiseLevel();
		else if(cmd.equals(EDITASYMPROFILE))
			editAsymmetricProfile();
		else if(cmd.equals(REMOVEASYMPROFILE))
			removeAsymmetricProfile();
		else if(cmd.equals(CLOSE))
			finish();
		else if(cmd.equals(OK)) {
			
			boolean valid=true;
			if(textFieldsavedir.getText().isEmpty()) {
				launchWarning("Please define where you want to save the generated datasets");
				valid=false;
			}
			else if(integerTextFielddatasetnumberrows.getIntegerValue()==0) {
				launchWarning("Please set number of rows of dataset");
				valid=false;
			}
			else if(integerTextFieldNumbercolumnsdataset.getIntegerValue()==0) {
				launchWarning("Please set number of columns of dataset");
				valid=false;
			}
			else if(integerTextFieldnumberbiclusters.getIntegerValue()==0) {
				launchWarning("Please set the number of biclusters");
				valid=false;
			}
			else if(integerTextFieldsamplesperdataset.getIntegerValue()==0) {
				launchWarning("Please define the number of samples per dataset");
				valid=false;
			}
			else if(listfactorymodel.size()==0) {
				launchWarning("Please select at least one of the bicluster models");
				valid=false;
			}
			else if(chckbxSymmetricOverlapnew.isSelected()) {
				valid=validateSymmetricOverlap();
			}
			else if(chckbxAsymmetricOverlap.isSelected()) {
				valid=validateASymmetricOverlap();
			}
			if(valid)
				terminate();
		}
	}
	
	/**
	 * Validate symmetric overlap.
	 *
	 * @return true, if successful
	 */
	private boolean validateSymmetricOverlap() {
		
		
		ArrayList<Pair<Integer, Integer>> shapebiclusters=tablebiclusterssize.getShapeOfBiclusters();
		
		ArrayList<Integer> symprofile= tablesymmetricoverlapprofiles.getSymmetricOverlapExperiments();
		
		for (int i = 0; i < symprofile.size(); i++) {
			
			int currentprofile=symprofile.get(i);
			for (int j = 0; j < shapebiclusters.size(); j++) {
				
				int rows=shapebiclusters.get(j).getValue0();
				int cols=shapebiclusters.get(j).getValue1();
				
				if(currentprofile>rows) {
					launchWarning("You have defined a symmetric overlap profile with an overlap over "+currentprofile+" rows, but bicluster "+(j+1)+" only have "+rows+" rows");
					return false;
				}
				else if(currentprofile>cols) {
					launchWarning("You have defined a symmetric overlap profile with an overlap over "+currentprofile+" columns, but bicluster "+(j+1)+" only have "+cols+" columns");
					return false;
				}
			}
		}
		
		return true;
	}
	
	

	/**
	 * Validate A symmetric overlap.
	 *
	 * @return true, if successful
	 */
	private boolean validateASymmetricOverlap() {
		
		
		ArrayList<Pair<Integer, Integer>> shapebiclusters=tablebiclusterssize.getShapeOfBiclusters();
		
		ArrayList<ArrayList<Pair<Integer, Integer>>> asymprofiles=asymmetricoverlapprofilestable.getASymmetricOverlapExperiments();

		
		
		for (int i = 0; i < asymprofiles.size(); i++) {
			
			ArrayList<Pair<Integer, Integer>> profile=asymprofiles.get(i);
			
			for (int j = 0; j < profile.size(); j++) {
				
				Pair<Integer, Integer> overlapbetweenbics=profile.get(j);
				
				Pair<Integer, Integer> bic1shape=shapebiclusters.get(j);
				Pair<Integer, Integer> bic2shape=shapebiclusters.get(j+1);
				
				
				if(overlapbetweenbics.getValue0()>bic1shape.getValue0()) {
					launchWarning("In Profile "+(i+1)+" you have defined an overlap for biclusters "+(j+1)+" and "+(j+2)+" of "+overlapbetweenbics.getValue0()+" rows, but bicluster "+(j+1)+" only have "+bic1shape.getValue0()+" rows");
					return false;
				}
				else if(overlapbetweenbics.getValue0()>bic2shape.getValue0()) {
					launchWarning("In Profile "+(i+1)+" you have defined an overlap for biclusters "+(j+1)+" and "+(j+2)+" of "+overlapbetweenbics.getValue0()+" rows, but bicluster "+(j+2)+" only have "+bic2shape.getValue0()+" rows");
					return false;
				}
				else if(overlapbetweenbics.getValue1()>bic1shape.getValue1()) {
					launchWarning("In Profile "+(i+1)+" you have defined an overlap for biclusters "+(j+1)+" and "+(j+2)+" of "+overlapbetweenbics.getValue1()+" columns, but bicluster "+(j+1)+" only have "+bic1shape.getValue1()+" columns");
					return false;
				}	
				else if(overlapbetweenbics.getValue1()>bic2shape.getValue1()) {
					launchWarning("In Profile "+(i+1)+" you have defined an overlap for biclusters "+(j+1)+" and "+(j+2)+" of "+overlapbetweenbics.getValue1()+" columns, but bicluster "+(j+2)+" only have "+bic2shape.getValue1()+" columns");
					return false;
				}
				
				
			}
		
		}
		
		return true;
	}
	
	
	/**
	 * Gets the list model factories.
	 *
	 * @return the list model factories
	 */
	private ArrayList<IBiclusterModelDataFactory> getListModelFactories(){
		ArrayList<IBiclusterModelDataFactory> res=new ArrayList<>();
		
		for (int i = 0; i < listfactorymodel.size(); i++) {
			res.add(listfactorymodel.getElementAt(i));
		}
		
		return res;
	}
	
	/**
	 * Gets the symmetric overlap.
	 *
	 * @return the symmetric overlap
	 */
	private ArrayList<Integer> getSymmetricOverlap(){
		
		if(chckbxSymmetricOverlapnew.isSelected())
			return tablesymmetricoverlapprofiles.getSymmetricOverlapExperiments();
		return null;
	}
	
	
	/**
	 * Gets the asymmetric overlap.
	 *
	 * @return the asymmetric overlap
	 */
	private ArrayList<ArrayList<Pair<Integer, Integer>>> getAsymmetricOverlap(){
		
		if(chckbxAsymmetricOverlap.isSelected())
			return asymmetricoverlapprofilestable.getASymmetricOverlapExperiments();
		return null;
	}
	
	
	/**
	 * Gets the experimets noise.
	 *
	 * @return the experimets noise
	 */
	private ArrayList<Double> getExperimetsNoise(){
		if(chckbxExperimentsWithNoise.isSelected())
			return tablenoiselevels.getNoiseOfExperiments();
		return null;
	}

	/**
	 * Terminate.
	 */
	protected void terminate(){

		
		NormalDistributionDataFactory backdist=new NormalDistributionDataFactory(doubleTextFieldmeanbackground.getDoubleValue(), doubleTextFieldsdbackground.getDoubleValue());
		
		
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("List Model Factories", ArrayList.class,getListModelFactories(), null));
		listSpecs.add(new ParamSpec("Background dist",NormalDistributionDataFactory.class,backdist, null));
		listSpecs.add(new ParamSpec("Repeat background",Boolean.class,chckbxUsesamebacksamplenumber.isSelected(), null));
		listSpecs.add(new ParamSpec("Data Properties",SyntheticDataProperties.class,currentparameters, null));
		listSpecs.add(new ParamSpec("symmetric overlap",ArrayList.class,getSymmetricOverlap(), null));
		listSpecs.add(new ParamSpec("asymmetric overlap",ArrayList.class,getAsymmetricOverlap(), null));
		listSpecs.add(new ParamSpec("Experiments Noise",ArrayList.class,getExperimetsNoise(), null));
		listSpecs.add(new ParamSpec("Number Samples",Integer.class,integerTextFieldsamplesperdataset.getIntegerValue(), null));
		listSpecs.add(new ParamSpec("Save to folder",String.class,textFieldsavedir.getText(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}
	
	
	/**
	 * Launch warning.
	 *
	 * @param msg the msg
	 */
	protected void launchWarning(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Incorrect Parameters", JOptionPane.WARNING_MESSAGE);
	}

}
