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
package jbiclustgegui.gui.components.panels.methods;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.wrappers.CPBMethod;
import jbiclustge.methods.algorithms.wrappers.UnibicMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class CPBSettingsPanel.
 */
public class CPBSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl min rows in. */
	private JLabel lblMinRowsIn;
	
	/** The integer text fieldminrowseed. */
	private IntegerTextField integerTextFieldminrowseed;
	
	/** The lbl max rows in. */
	private JLabel lblMaxRowsIn;
	
	/** The integer text fieldmaxrowseed. */
	private IntegerTextField integerTextFieldmaxrowseed;
	
	/** The lbl required row in. */
	private JLabel lblRequiredRowIn;
	
	/** The lbl required column in. */
	private JLabel lblRequiredColumnIn;
	
	/** The check boxgridinit. */
	private JCheckBox checkBoxgridinit;
	
	/** The lbl max overlap ratio. */
	private JLabel lblMaxOverlapRatio;
	
	/** The spinneroverlap. */
	private JSpinner spinneroverlap;

	/** The usetfsearch. */
	private static String USETFSEARCH="usetfsearch";
	
	/** The lbl number seed biclusters. */
	private JLabel lblNumberSeedBiclusters;
	
	/** The integer text fieldnumberseed. */
	private IntegerTextField integerTextFieldnumberseed;
	
	/** The spinnerpcctreshold. */
	private JSpinner spinnerpcctreshold;
	
	/** The lbl pcc threshold. */
	private JLabel lblPccThreshold;
	
	/** The combo boxrequiredrowseed. */
	private JComboBox comboBoxrequiredrowseed;
	
	/** The lbl grid initialization. */
	private JLabel lblGridInitialization;
	
	/** The lbl row to column. */
	private JLabel lblRowToColumn;
	
	/** The spinnerrowcolratio. */
	private JSpinner spinnerrowcolratio;
	
	/** The combo boxreqcolseed. */
	private JComboBox comboBoxreqcolseed;
	
	/**
	 * Create the panel.
	 */
	public CPBSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberSeedBiclusters = new JLabel("Number seed biclusters:");
		GridBagConstraints gbc_lblNumberSeedBiclusters = new GridBagConstraints();
		gbc_lblNumberSeedBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberSeedBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberSeedBiclusters.gridx = 0;
		gbc_lblNumberSeedBiclusters.gridy = 0;
		add(this.lblNumberSeedBiclusters, gbc_lblNumberSeedBiclusters);
		
		this.integerTextFieldnumberseed = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldnumberseed = new GridBagConstraints();
		gbc_integerTextFieldnumberseed.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberseed.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberseed.gridx = 1;
		gbc_integerTextFieldnumberseed.gridy = 0;
		add(this.integerTextFieldnumberseed, gbc_integerTextFieldnumberseed);
		
		this.lblPccThreshold = new JLabel("PCC threshold:");
		GridBagConstraints gbc_lblPccThreshold = new GridBagConstraints();
		gbc_lblPccThreshold.anchor = GridBagConstraints.EAST;
		gbc_lblPccThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblPccThreshold.gridx = 0;
		gbc_lblPccThreshold.gridy = 1;
		add(this.lblPccThreshold, gbc_lblPccThreshold);
		
		this.spinnerpcctreshold = new JSpinner();
		this.spinnerpcctreshold.setModel(new SpinnerNumberModel(0.95, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerpcctreshold = new GridBagConstraints();
		gbc_spinnerpcctreshold.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerpcctreshold.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerpcctreshold.gridx = 1;
		gbc_spinnerpcctreshold.gridy = 1;
		add(this.spinnerpcctreshold, gbc_spinnerpcctreshold);
		
		lblMinRowsIn = new JLabel("Min. rows in seed:");
		GridBagConstraints gbc_lblMinRowsIn = new GridBagConstraints();
		gbc_lblMinRowsIn.anchor = GridBagConstraints.EAST;
		gbc_lblMinRowsIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinRowsIn.gridx = 0;
		gbc_lblMinRowsIn.gridy = 2;
		add(lblMinRowsIn, gbc_lblMinRowsIn);
		
		integerTextFieldminrowseed = new IntegerTextField();
		integerTextFieldminrowseed.setText("0");
		integerTextFieldminrowseed.setColumns(10);
		GridBagConstraints gbc_integerTextFieldminrowseed = new GridBagConstraints();
		gbc_integerTextFieldminrowseed.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminrowseed.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminrowseed.gridx = 1;
		gbc_integerTextFieldminrowseed.gridy = 2;
		add(integerTextFieldminrowseed, gbc_integerTextFieldminrowseed);
		
		lblMaxRowsIn = new JLabel("Max. rows in seed:");
		GridBagConstraints gbc_lblMaxRowsIn = new GridBagConstraints();
		gbc_lblMaxRowsIn.anchor = GridBagConstraints.EAST;
		gbc_lblMaxRowsIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxRowsIn.gridx = 0;
		gbc_lblMaxRowsIn.gridy = 3;
		add(lblMaxRowsIn, gbc_lblMaxRowsIn);
		
		integerTextFieldmaxrowseed = new IntegerTextField();
		integerTextFieldmaxrowseed.setText("0");
		integerTextFieldmaxrowseed.setColumns(10);
		GridBagConstraints gbc_integerTextFieldmaxrowseed = new GridBagConstraints();
		gbc_integerTextFieldmaxrowseed.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmaxrowseed.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxrowseed.gridx = 1;
		gbc_integerTextFieldmaxrowseed.gridy = 3;
		add(integerTextFieldmaxrowseed, gbc_integerTextFieldmaxrowseed);
		
		lblRequiredRowIn = new JLabel("Required row in  seed:");
		GridBagConstraints gbc_lblRequiredRowIn = new GridBagConstraints();
		gbc_lblRequiredRowIn.anchor = GridBagConstraints.EAST;
		gbc_lblRequiredRowIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequiredRowIn.gridx = 0;
		gbc_lblRequiredRowIn.gridy = 4;
		add(lblRequiredRowIn, gbc_lblRequiredRowIn);
		
		this.comboBoxrequiredrowseed = new JComboBox();
		GridBagConstraints gbc_comboBoxrequiredrowseed = new GridBagConstraints();
		gbc_comboBoxrequiredrowseed.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxrequiredrowseed.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxrequiredrowseed.gridx = 1;
		gbc_comboBoxrequiredrowseed.gridy = 4;
		add(this.comboBoxrequiredrowseed, gbc_comboBoxrequiredrowseed);
		
		lblRequiredColumnIn = new JLabel("Required column in seed:");
		GridBagConstraints gbc_lblRequiredColumnIn = new GridBagConstraints();
		gbc_lblRequiredColumnIn.anchor = GridBagConstraints.EAST;
		gbc_lblRequiredColumnIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequiredColumnIn.gridx = 0;
		gbc_lblRequiredColumnIn.gridy = 5;
		add(lblRequiredColumnIn, gbc_lblRequiredColumnIn);
		
		this.comboBoxreqcolseed = new JComboBox();
		GridBagConstraints gbc_comboBoxreqcolseed = new GridBagConstraints();
		gbc_comboBoxreqcolseed.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxreqcolseed.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxreqcolseed.gridx = 1;
		gbc_comboBoxreqcolseed.gridy = 5;
		add(this.comboBoxreqcolseed, gbc_comboBoxreqcolseed);
		
		lblMaxOverlapRatio = new JLabel("Max. overlap ratio:");
		GridBagConstraints gbc_lblMaxOverlapRatio = new GridBagConstraints();
		gbc_lblMaxOverlapRatio.anchor = GridBagConstraints.EAST;
		gbc_lblMaxOverlapRatio.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxOverlapRatio.gridx = 0;
		gbc_lblMaxOverlapRatio.gridy = 6;
		add(lblMaxOverlapRatio, gbc_lblMaxOverlapRatio);
		
		spinneroverlap = new JSpinner();
		spinneroverlap.setToolTipText("range: (0.5-1.0]");
		spinneroverlap.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinneroverlap = new GridBagConstraints();
		gbc_spinneroverlap.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneroverlap.insets = new Insets(0, 0, 5, 0);
		gbc_spinneroverlap.gridx = 1;
		gbc_spinneroverlap.gridy = 6;
		add(spinneroverlap, gbc_spinneroverlap);
		
		this.lblGridInitialization = new JLabel("Grid initialization:");
		GridBagConstraints gbc_lblGridInitialization = new GridBagConstraints();
		gbc_lblGridInitialization.anchor = GridBagConstraints.EAST;
		gbc_lblGridInitialization.insets = new Insets(0, 0, 5, 5);
		gbc_lblGridInitialization.gridx = 0;
		gbc_lblGridInitialization.gridy = 7;
		add(this.lblGridInitialization, gbc_lblGridInitialization);
		
		checkBoxgridinit = new JCheckBox("");
		checkBoxgridinit.setSelected(false);
		GridBagConstraints gbc_checkBoxgridinit = new GridBagConstraints();
		gbc_checkBoxgridinit.anchor = GridBagConstraints.WEST;
		gbc_checkBoxgridinit.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxgridinit.gridx = 1;
		gbc_checkBoxgridinit.gridy = 7;
		add(checkBoxgridinit, gbc_checkBoxgridinit);
		
		this.lblRowToColumn = new JLabel("Row to column ratio:");
		GridBagConstraints gbc_lblRowToColumn = new GridBagConstraints();
		gbc_lblRowToColumn.anchor = GridBagConstraints.EAST;
		gbc_lblRowToColumn.insets = new Insets(0, 0, 0, 5);
		gbc_lblRowToColumn.gridx = 0;
		gbc_lblRowToColumn.gridy = 8;
		add(this.lblRowToColumn, gbc_lblRowToColumn);
		
		this.spinnerrowcolratio = new JSpinner();
		GridBagConstraints gbc_spinnerrowcolratio = new GridBagConstraints();
		gbc_spinnerrowcolratio.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerrowcolratio.gridx = 1;
		gbc_spinnerrowcolratio.gridy = 8;
		add(this.spinnerrowcolratio, gbc_spinnerrowcolratio);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		
		integerTextFieldnumberseed.setText("100");
		spinnerpcctreshold.setValue(0.9);
		integerTextFieldminrowseed.setText("2");
		integerTextFieldmaxrowseed.setText("-1");
		
		if(comboBoxrequiredrowseed.getModel().getSize()>0)
			comboBoxrequiredrowseed.setSelectedIndex(0);
		if(comboBoxreqcolseed.getModel().getSize()>0)
			comboBoxreqcolseed.setSelectedIndex(0);

		spinneroverlap.setValue(0.75);
		checkBoxgridinit.setSelected(false);
		spinnerrowcolratio.setValue(1.0);
		
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#needInitComponents()
	 */
	@Override
	protected boolean needInitComponents() {
		return false;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		
		
		
	}
	

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#setCurrentProject(jbiclustgegui.datatypes.project.Project)
	 */
	@Override
	public void setCurrentProject(Project proj) {
		comboBoxrequiredrowseed.removeAllItems();
		comboBoxreqcolseed.removeAllItems();
		
		ArrayList<String> genes=proj.getExpressionDataset().getExpressionset().getGeneNamesList();
		ArrayList<String> conditions=proj.getExpressionDataset().getExpressionset().getConditionsList();
		
		integerTextFieldmaxrowseed.setText(String.valueOf(genes.size()));
		
		comboBoxrequiredrowseed.addItem("indifferent");
		comboBoxreqcolseed.addItem("indifferent");
		
		
		for (int i = 0; i < genes.size(); i++) {
			comboBoxrequiredrowseed.addItem(genes.get(i));
		}
		
		for (int i = 0; i < conditions.size(); i++) {
			comboBoxreqcolseed.addItem(conditions.get(i));
		}
		
		comboBoxrequiredrowseed.setSelectedIndex(0);
		comboBoxreqcolseed.setSelectedIndex(0);
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {
		
		
		String[] propkeys=new String[]{
				CPBMethod.NUMBERBICLUSTERS,
				CPBMethod.PCCTHRESHOLD,
				CPBMethod.MINROWSINSEED,
				CPBMethod.MAXROWSINSEED,
				CPBMethod.REQUIREDROWSINSEED,
				CPBMethod.REQUIREDCOLUMNSINSEED,
				CPBMethod.MAXOVERLAPRATIO,
				CPBMethod.RANDOMINIT,
				CPBMethod.ROWTOCOLUMNRATION
		};
		
		int reqrow=-1;
		int reqcol=-1;
		
		int reqrowind=comboBoxrequiredrowseed.getSelectedIndex();
		int reqcolin=comboBoxreqcolseed.getSelectedIndex();
		
		if(reqrowind>0)
			reqrow=reqrowind-1;
		if(reqcolin>0)
			reqcol=reqcolin-1;
		
		String[]values=new String[]{integerTextFieldnumberseed.getText(),
				String.valueOf(spinnerpcctreshold.getValue()),
				integerTextFieldminrowseed.getText(),
				integerTextFieldmaxrowseed.getText(),
				String.valueOf(reqrow),
				String.valueOf(reqcol),
				String.valueOf(spinneroverlap.getValue()),
				String.valueOf(checkBoxgridinit.isSelected()),
				String.valueOf(spinnerrowcolratio.getValue())};
		
		String[] comments=new String[] {
				"Number of biclusters that cpb will be seeded",
				"PCC threshold for each row",
				"Min rows in the seed",
				"Max rows in the seed",
				"Required row in the seed. -1 for random choice(reference row)",
				"Required columns in the seed. -1 for random choice",
				"Max overlap ratio. Between [0-1]",
				"Use grid initialization (false) or use random initialization (true)",
				"Row to column ratio"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberseed.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberseed.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of seed biclusters must be higher than 0");
		else if(integerTextFieldminrowseed.getText().isEmpty() || Integer.parseInt(integerTextFieldminrowseed.getText())<1)
			return new Pair<Boolean, String>(false, "Value for the min. rows in the seed must be higher than 0");
		else if(integerTextFieldmaxrowseed.getText().isEmpty() || Integer.parseInt(integerTextFieldmaxrowseed.getText())<Integer.parseInt(integerTextFieldminrowseed.getText()))
			return new Pair<Boolean, String>(false, "Value for the max. rows in the seed must be higher than min. rows in the seed");
		else
			  return new Pair<Boolean, String>(true, null);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(450,500);
	}



	

}
