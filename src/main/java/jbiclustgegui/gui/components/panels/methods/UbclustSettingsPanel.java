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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.wrappers.UBClustMethod;
import jbiclustge.methods.algorithms.wrappers.components.KolmogorovEstimator;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class UbclustSettingsPanel.
 */
public class UbclustSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl correlation threshold. */
	private JLabel lblCorrelationThreshold;
	
	/** The lbl temperature factor. */
	private JLabel lblTemperatureFactor;
	
	/** The integer text fielddiscretlevels. */
	private IntegerTextField integerTextFielddiscretlevels;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl min genes. */
	private JLabel lblMinGenes;
	
	/** The lbl min genes 1. */
	private JLabel lblMinGenes_1;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The combo boxkolmogorovestim. */
	private JComboBox comboBoxkolmogorovestim;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The spinnerjavainitheap. */
	private JSpinner spinnerjavainitheap;
	
	/** The double text fieldinittemp. */
	private DoubleTextField doubleTextFieldinittemp;
	
	/** The double text fieldtempfactor. */
	private DoubleTextField doubleTextFieldtempfactor;

	/**
	 * Create the panel.
	 */
	public UbclustSettingsPanel() {
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
		
		this.lblCorrelationThreshold = new JLabel("Number of biclusters:");
		this.lblCorrelationThreshold.setToolTipText("Number of biclusters to estimate");
		GridBagConstraints gbc_lblCorrelationThreshold = new GridBagConstraints();
		gbc_lblCorrelationThreshold.anchor = GridBagConstraints.EAST;
		gbc_lblCorrelationThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrelationThreshold.gridx = 0;
		gbc_lblCorrelationThreshold.gridy = 0;
		add(this.lblCorrelationThreshold, gbc_lblCorrelationThreshold);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		integerTextFieldnumberbics.setToolTipText("Number of biclusters to estimate");
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 0;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblMinGenes = new JLabel("Discretization levels:");
		this.lblMinGenes.setToolTipText("Discretization levels (default 128)");
		GridBagConstraints gbc_lblMinGenes = new GridBagConstraints();
		gbc_lblMinGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes.gridx = 0;
		gbc_lblMinGenes.gridy = 1;
		add(this.lblMinGenes, gbc_lblMinGenes);
		
		this.integerTextFielddiscretlevels = new IntegerTextField();
		integerTextFielddiscretlevels.setToolTipText("Discretization levels (default 128)");
		this.integerTextFielddiscretlevels.setColumns(10);
		GridBagConstraints gbc_integerTextFielddiscretlevels = new GridBagConstraints();
		gbc_integerTextFielddiscretlevels.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFielddiscretlevels.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFielddiscretlevels.gridx = 1;
		gbc_integerTextFielddiscretlevels.gridy = 1;
		add(this.integerTextFielddiscretlevels, gbc_integerTextFielddiscretlevels);
		
		this.lblMinGenes_1 = new JLabel("Initial temperature:");
		this.lblMinGenes_1.setToolTipText("Initial temperature (default 0.00001)");
		GridBagConstraints gbc_lblMinGenes_1 = new GridBagConstraints();
		gbc_lblMinGenes_1.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes_1.gridx = 0;
		gbc_lblMinGenes_1.gridy = 2;
		add(this.lblMinGenes_1, gbc_lblMinGenes_1);
		
		doubleTextFieldinittemp = new DoubleTextField();
		doubleTextFieldinittemp.setToolTipText("Initial temperature (default 0.00001)");
		GridBagConstraints gbc_doubleTextFieldinittemp = new GridBagConstraints();
		gbc_doubleTextFieldinittemp.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldinittemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldinittemp.gridx = 1;
		gbc_doubleTextFieldinittemp.gridy = 2;
		add(doubleTextFieldinittemp, gbc_doubleTextFieldinittemp);
		
		this.lblTemperatureFactor = new JLabel("Temperature factor:");
		this.lblTemperatureFactor.setToolTipText("Temperature factor (default 0.9)");
		GridBagConstraints gbc_lblTemperatureFactor = new GridBagConstraints();
		gbc_lblTemperatureFactor.anchor = GridBagConstraints.EAST;
		gbc_lblTemperatureFactor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemperatureFactor.gridx = 0;
		gbc_lblTemperatureFactor.gridy = 3;
		add(this.lblTemperatureFactor, gbc_lblTemperatureFactor);
		
		doubleTextFieldtempfactor = new DoubleTextField();
		doubleTextFieldtempfactor.setToolTipText("Temperature factor (default 0.9)");
		GridBagConstraints gbc_doubleTextFieldtempfactor = new GridBagConstraints();
		gbc_doubleTextFieldtempfactor.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldtempfactor.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldtempfactor.gridx = 1;
		gbc_doubleTextFieldtempfactor.gridy = 3;
		add(doubleTextFieldtempfactor, gbc_doubleTextFieldtempfactor);
		
		this.lblOverlapTreshold = new JLabel("Kolmogorov complexity estimator:");
		this.lblOverlapTreshold.setToolTipText("<html>Kolmogorov complexity estimator:\n<ul><li>Uniform Model (default)\n<li>Constant Rows Model\n<li>Additive Model\n<li>Relaxed OPSM</ul></html>");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 4;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		comboBoxkolmogorovestim = new JComboBox();
		comboBoxkolmogorovestim.setToolTipText("<html>Kolmogorov complexity estimator:\n<ul><li>Uniform Model (default)\n<li>Constant Rows Model\n<li>Additive Model\n<li>Relaxed OPSM</ul></html>");
		GridBagConstraints gbc_comboBoxkolmogorovestim = new GridBagConstraints();
		gbc_comboBoxkolmogorovestim.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxkolmogorovestim.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxkolmogorovestim.gridx = 1;
		gbc_comboBoxkolmogorovestim.gridy = 4;
		add(comboBoxkolmogorovestim, gbc_comboBoxkolmogorovestim);
		
		lblNewLabel = new JLabel("Initial Java heap size:");
		lblNewLabel.setToolTipText("Initial Java heap size (MB), default=1024");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		spinnerjavainitheap = new JSpinner();
		spinnerjavainitheap.setModel(new SpinnerNumberModel(1024, 1024, 10240, 1024));
		spinnerjavainitheap.setToolTipText("Initial Java heap size (MB), default=1024");
		GridBagConstraints gbc_spinnerjavainitheap = new GridBagConstraints();
		gbc_spinnerjavainitheap.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerjavainitheap.gridx = 1;
		gbc_spinnerjavainitheap.gridy = 5;
		add(spinnerjavainitheap, gbc_spinnerjavainitheap);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		comboBoxkolmogorovestim.setSelectedItem(KolmogorovEstimator.UNIFORMMODEL);
		integerTextFieldnumberbics.setText("10");
		integerTextFielddiscretlevels.setText("128");
		doubleTextFieldinittemp.setText("0.00001");
		doubleTextFieldtempfactor.setText("0.9");
		spinnerjavainitheap.setValue(1024);
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#needInitComponents()
	 */
	@Override
	protected boolean needInitComponents() {
		return true;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		
		for (KolmogorovEstimator element : KolmogorovEstimator.values()) {
			comboBoxkolmogorovestim.addItem(element);
		}
		
	}

	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				UBClustMethod.NUMBERBICLUSTERSESTIMATE,
				UBClustMethod.DISCRETIZATIONLEVAL,
				UBClustMethod.INITIALTEMPERATURE,
				UBClustMethod.TEMPERATUREFACTOR,
				UBClustMethod.ESTIMATOR,
				UBClustMethod.MINJAVAHEAPSIZE
		};
		
		KolmogorovEstimator estimator=(KolmogorovEstimator) comboBoxkolmogorovestim.getSelectedItem();
		
		
		String[] values=new String[]{integerTextFieldnumberbics.getText(),
				integerTextFielddiscretlevels.getText(),
				doubleTextFieldinittemp.getText(),
				doubleTextFieldtempfactor.getText(),
				estimator.getCodeTag(),
				String.valueOf(spinnerjavainitheap.getValue())};
		
		String[] comments=new String[] {
				"Number of biclusters to estimate",
				"Discretization levels (default 128)",
				"Initial temperature (default 0.00001)",
				"Temperature factor (default 0.9)",
				"Kolmogorov complexity estimator: \n"
				+ "  0 = Uniform Model (default)\n"
				+ "  1 = Constant Rows Model\n"
				+ "  2 = Additive Model\n"
				+ "  3 = Relaxed OPSM",
				"Initial Java heap size (MB), default=1024"
		};
		
		String source="source: http://alumni.cs.ucr.edu/~hli/ubc/";
		return AlgorithmProperties.setupProperties(propkeys, values, comments,source);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of biclusters must be higher than 0");
		else if(integerTextFielddiscretlevels.getText().isEmpty() || Integer.parseInt(integerTextFielddiscretlevels.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the discretization levels must be higher than 0");

		else if(doubleTextFieldinittemp.getText().isEmpty() || Double.parseDouble(doubleTextFieldinittemp.getText())<=0)
			return new Pair<Boolean, String>(false, "The value for the initial temperature must be higher than 0");
		else if(doubleTextFieldtempfactor.getText().isEmpty() || Double.parseDouble(doubleTextFieldtempfactor.getText())<=0)
			return new Pair<Boolean, String>(false, "The value for the temperature factor must be higher than 0");
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
		return new Dimension(450,380);
	}

}
