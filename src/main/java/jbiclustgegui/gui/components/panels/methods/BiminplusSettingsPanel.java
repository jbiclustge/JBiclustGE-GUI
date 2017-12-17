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

import jbiclustge.methods.algorithms.wrappers.BiMinePlusMethod;
import jbiclustge.methods.algorithms.wrappers.BicFinderMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class BiminplusSettingsPanel.
 */
public class BiminplusSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl acsi. */
	private JLabel lblAcsi;
	
	/** The lbl asr. */
	private JLabel lblAsr;
	
	/** The lbl java maximum size. */
	private JLabel lblJavaMaximumSize;
	
	/** The spinnerjava. */
	private JSpinner spinnerjava;
	
	/** The spinnerasr. */
	private JSpinner spinnerasr;
	
	/** The integer text fieldminconds. */
	private IntegerTextField integerTextFieldminconds;

	/**
	 * Create the panel.
	 */
	public BiminplusSettingsPanel() {
       super();
       setToolTipText("Threshold of minimum number of conditions >2");
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblAcsi = new JLabel("Min. conditions threshold:");
		this.lblAcsi.setToolTipText("ACSI is the threshold of the average correspondence similarity index [0..1]");
		GridBagConstraints gbc_lblAcsi = new GridBagConstraints();
		gbc_lblAcsi.anchor = GridBagConstraints.EAST;
		gbc_lblAcsi.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcsi.gridx = 0;
		gbc_lblAcsi.gridy = 0;
		add(this.lblAcsi, gbc_lblAcsi);
		
		this.integerTextFieldminconds = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldminconds = new GridBagConstraints();
		gbc_integerTextFieldminconds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminconds.gridx = 1;
		gbc_integerTextFieldminconds.gridy = 0;
		add(this.integerTextFieldminconds, gbc_integerTextFieldminconds);
		
		this.lblAsr = new JLabel("ASR:");
		this.lblAsr.setToolTipText("ASR is the threshold of the average spearmans rho [-1..1]");
		GridBagConstraints gbc_lblAsr = new GridBagConstraints();
		gbc_lblAsr.anchor = GridBagConstraints.EAST;
		gbc_lblAsr.insets = new Insets(0, 0, 5, 5);
		gbc_lblAsr.gridx = 0;
		gbc_lblAsr.gridy = 1;
		add(this.lblAsr, gbc_lblAsr);
		
		this.spinnerasr = new JSpinner();
		this.spinnerasr.setModel(new SpinnerNumberModel(0.6, -1.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerasr = new GridBagConstraints();
		gbc_spinnerasr.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerasr.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerasr.gridx = 1;
		gbc_spinnerasr.gridy = 1;
		add(this.spinnerasr, gbc_spinnerasr);
		
		this.lblJavaMaximumSize = new JLabel("Java maximum size memory:");
		this.lblJavaMaximumSize.setToolTipText("Java maximum size memory allocation pool");
		GridBagConstraints gbc_lblJavaMaximumSize = new GridBagConstraints();
		gbc_lblJavaMaximumSize.anchor = GridBagConstraints.EAST;
		gbc_lblJavaMaximumSize.insets = new Insets(0, 20, 0, 5);
		gbc_lblJavaMaximumSize.gridx = 0;
		gbc_lblJavaMaximumSize.gridy = 2;
		add(this.lblJavaMaximumSize, gbc_lblJavaMaximumSize);
		
		this.spinnerjava = new JSpinner();
		this.spinnerjava.setModel(new SpinnerNumberModel(1024, 1024, 30720, 1024));
		GridBagConstraints gbc_spinnerjava = new GridBagConstraints();
		gbc_spinnerjava.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerjava.gridx = 1;
		gbc_spinnerjava.gridy = 2;
		add(this.spinnerjava, gbc_spinnerjava);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldminconds.setText("2");
		spinnerasr.setValue(0.6);
		spinnerjava.setValue(1024);
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

		String[] propkeys=new String[]{BiMinePlusMethod.MINCONDITIONS,BiMinePlusMethod.ASR,BiMinePlusMethod.JAVAXMX};
		
		String[] propdefaultvals=new String[]{integerTextFieldminconds.getText(),
				String.valueOf(spinnerasr.getValue()),
				String.valueOf(spinnerjava.getValue())};
		
		String[] propcomments=new String[]{
				"Threshold of minimum number of conditions >=2",
				"Threshold of the average spearmans rho [-1..1]",
				"Java maximum size memory allocation pool"
			};
		return AlgorithmProperties.setupProperties(propkeys, propdefaultvals,propcomments);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldminconds.getText().isEmpty() || Integer.parseInt(integerTextFieldminconds.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the threshold of minimum number of conditions must be higher than 1");
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
		return new Dimension(450,250);
	}

}
