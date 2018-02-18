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
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.java.bibit.BibitMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class BibitSettingsPanel.
 */
public class BibitSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label. */
	private JLabel label;
	
	/** The integer text fieldmingenes. */
	private IntegerTextField integerTextFieldmingenes;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text fieldminconds. */
	private IntegerTextField integerTextFieldminconds;
	
	/** The label 2. */
	private JLabel label_2;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The integer text fieldpatternsize. */
	private IntegerTextField integerTextFieldpatternsize;
	
	/** The lbl max discretization value. */
	private JLabel lblMaxDiscretizationValue;
	
	/** The spinnerdiscretvalue. */
	private JSpinner spinnerdiscretvalue;
	
	/** The lbl use max value. */
	private JLabel lblUseMaxValue;
	
	/** The check boxmaxvaluedatadiscret. */
	private JCheckBox checkBoxmaxvaluedatadiscret;

	private static String tip1="Minimum number of genes allowed in a valid bicluster";
	private static String tip2="Minimum number of conditions allowed in a valid bicluster";
	private static String tip3="Number of bicluster to find. If value is zero the algorithm will return the maximum number of biclusters that can find (default=0)";
	private static String tip4="Number of bits to use at the encoding phase default=16";
	private static String tip5="Maximum value in the discretized dataset. From this value, BiBit will binarize the dataset generating max_value different ones";
	private static String tip6="If true, the maximum value of expression dataset will be used as the maximum value in the discretized dataset (previous parameter)";
	/**
	 * Create the panel.
	 */
	public BibitSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.label = new JLabel("Min. Genes:");
		this.label.setToolTipText(tip1);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(this.label, gbc_label);
		
		this.integerTextFieldmingenes = new IntegerTextField();
		integerTextFieldmingenes.setToolTipText(tip1);
		this.integerTextFieldmingenes.setColumns(10);
		GridBagConstraints gbc_integerTextFieldmingenes = new GridBagConstraints();
		gbc_integerTextFieldmingenes.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmingenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenes.gridx = 1;
		gbc_integerTextFieldmingenes.gridy = 0;
		add(this.integerTextFieldmingenes, gbc_integerTextFieldmingenes);
		
		this.label_1 = new JLabel("Min. Conditions:");
		this.label_1.setToolTipText(tip2);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		add(this.label_1, gbc_label_1);
		
		this.integerTextFieldminconds = new IntegerTextField();
		integerTextFieldminconds.setToolTipText(tip2);
		this.integerTextFieldminconds.setColumns(10);
		GridBagConstraints gbc_integerTextFieldminconds = new GridBagConstraints();
		gbc_integerTextFieldminconds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminconds.gridx = 1;
		gbc_integerTextFieldminconds.gridy = 1;
		add(this.integerTextFieldminconds, gbc_integerTextFieldminconds);
		
		this.label_2 = new JLabel("Max number biclusters");
		this.label_2.setToolTipText(tip3);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		add(this.label_2, gbc_label_2);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		integerTextFieldnumberbics.setToolTipText(tip3);
		this.integerTextFieldnumberbics.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 2;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblOverlapTreshold = new JLabel("Pattern size:");
		this.lblOverlapTreshold.setToolTipText(tip4);
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 3;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.integerTextFieldpatternsize = new IntegerTextField();
		integerTextFieldpatternsize.setToolTipText(tip4);
		this.integerTextFieldpatternsize.setColumns(10);
		GridBagConstraints gbc_integerTextFieldpatternsize = new GridBagConstraints();
		gbc_integerTextFieldpatternsize.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldpatternsize.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldpatternsize.gridx = 1;
		gbc_integerTextFieldpatternsize.gridy = 3;
		add(this.integerTextFieldpatternsize, gbc_integerTextFieldpatternsize);
		
		this.lblMaxDiscretizationValue = new JLabel("Max. discretization value:");
		this.lblMaxDiscretizationValue.setToolTipText(tip5);
		GridBagConstraints gbc_lblMaxDiscretizationValue = new GridBagConstraints();
		gbc_lblMaxDiscretizationValue.anchor = GridBagConstraints.EAST;
		gbc_lblMaxDiscretizationValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxDiscretizationValue.gridx = 0;
		gbc_lblMaxDiscretizationValue.gridy = 4;
		add(this.lblMaxDiscretizationValue, gbc_lblMaxDiscretizationValue);
		
		this.spinnerdiscretvalue = new JSpinner();
		spinnerdiscretvalue.setToolTipText(tip5);
		this.spinnerdiscretvalue.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		GridBagConstraints gbc_spinnerdiscretvalue = new GridBagConstraints();
		gbc_spinnerdiscretvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerdiscretvalue.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerdiscretvalue.gridx = 1;
		gbc_spinnerdiscretvalue.gridy = 4;
		add(this.spinnerdiscretvalue, gbc_spinnerdiscretvalue);
		
		this.lblUseMaxValue = new JLabel("Use max. value of dataset as reference:");
		this.lblUseMaxValue.setToolTipText(tip6);
		GridBagConstraints gbc_lblUseMaxValue = new GridBagConstraints();
		gbc_lblUseMaxValue.anchor = GridBagConstraints.EAST;
		gbc_lblUseMaxValue.insets = new Insets(0, 0, 0, 5);
		gbc_lblUseMaxValue.gridx = 0;
		gbc_lblUseMaxValue.gridy = 5;
		add(this.lblUseMaxValue, gbc_lblUseMaxValue);
		
		this.checkBoxmaxvaluedatadiscret = new JCheckBox("");
		checkBoxmaxvaluedatadiscret.setToolTipText(tip6);
		GridBagConstraints gbc_checkBoxmaxvaluedatadiscret = new GridBagConstraints();
		gbc_checkBoxmaxvaluedatadiscret.anchor = GridBagConstraints.WEST;
		gbc_checkBoxmaxvaluedatadiscret.gridx = 1;
		gbc_checkBoxmaxvaluedatadiscret.gridy = 5;
		add(this.checkBoxmaxvaluedatadiscret, gbc_checkBoxmaxvaluedatadiscret);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldmingenes.setText("2");
		integerTextFieldminconds.setText("2");
		integerTextFieldnumberbics.setText("100");
		integerTextFieldpatternsize.setText("16");
		spinnerdiscretvalue.setValue(1.0);
		checkBoxmaxvaluedatadiscret.setSelected(false);
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				BibitMethod.MINIMUMGENES,
				BibitMethod.MINIMUMCONDITIONS,
				BibitMethod.MAXBICLUSTERS,
				BibitMethod.PATTERNSIZE,
				BibitMethod.MAXDISCRETIZEDVALUE,
				BibitMethod.MAXVALUEDATASETASREFERENCE
		};
		
		String[] values=new String[]{integerTextFieldmingenes.getText(),
				integerTextFieldminconds.getText(),
				integerTextFieldnumberbics.getText(),
				integerTextFieldpatternsize.getText(),
				String.valueOf(spinnerdiscretvalue.getValue()),
				String.valueOf(checkBoxmaxvaluedatadiscret.isSelected())};
		
		String[] comments=new String[] {
				"Minimum number of genes allowed in a valid bicluster",
				"Minimum number of conditions allowed in a valid bicluster",
				"Number of bicluster to find. If value is zero the algorithm will return the maximum number of biclusters that can find (default=100)",
				"Number of bits to use at the encoding phase default=16",
				"Maximum value in the discretized dataset. From this value, BiBit will binarize the dataset generating max_value different ones",
				"If true, the maximum value of expression dataset will be used as the maximum value in the discretized dataset (previous parameter)"
				
		};

		return AlgorithmProperties.setupProperties(propkeys, values, comments);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldmingenes.getText().isEmpty() || Integer.parseInt(integerTextFieldmingenes.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of genes must be higher than 2");
		else if(integerTextFieldminconds.getText().isEmpty() || Integer.parseInt(integerTextFieldminconds.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of conditions must be higher than 2");
		else if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<0)
			return new Pair<Boolean, String>(false, "Value for maximum number of biclusters must be 0 = (return the maximum number of biclusters) or other positive integer value");
		else if(integerTextFieldpatternsize.getText().isEmpty() || Integer.parseInt(integerTextFieldpatternsize.getText())<1)
			return new Pair<Boolean, String>(false, "Value for pattern size must be higher than 0");
		else
			  return new Pair<Boolean, String>(true, null);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#setCurrentProject(jbiclustgegui.datatypes.project.Project)
	 */
	@Override
	public void setCurrentProject(Project proj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(550,300);
	}

}
