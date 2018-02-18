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
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.wrappers.BBCMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.methods.algorithms.wrappers.components.BBCNormalizationMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JComboBox;

// TODO: Auto-generated Javadoc
/**
 * The Class BBCSettingsPanel.
 */
public class BBCSettingsPanel extends AbstractMethodSettingsPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl number biclusters. */
	private JLabel lblNumberBiclusters;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl normalization method. */
	private JLabel lblNormalizationMethod;
	
	/** The lbl normalization alpha value. */
	private JLabel lblNormalizationAlphaValue;
	
	/** The combo boxnormmethod. */
	private JComboBox comboBoxnormmethod;
	
	/** The spinnernormalpha. */
	private JSpinner spinnernormalpha;

	
	/** The normmethod. */
	private static String NORMMETHOD="normmethod";
	
	private static String tip1="<html>Number of biclusters that user hopes to find in the datasets<br>Warning: in general this method do not produces biclusters<br>if the value of the number of bicluster to be found, is higher than 4</html>";
	private static String tip2="<html>Normalization method to be used on the microarray data.<br>\nnone (No normalization will be done by the BBC program)<br>\ncsn (Column-wise standardization)<br>\nrsn (Row-wise standardization)<br>\niqrn (Inter-quartile range normalization, alpha value for the normalization needs to be specified)<br>\nsqrn (The smallest range quartile normalization, alpha value for the normalization needs to be specified)</html>";
	private static String tip3="<html>alpha value for the normalization method, needs to be set if iqrn or sqrn is used, value between (5,100]<br>\nalpha value means the normalization is done based on the alpha% quartile of data in each column</html>";
	
	/**
	 * Create the panel.
	 */
	public BBCSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberBiclusters = new JLabel("Number biclusters:");
		this.lblNumberBiclusters.setToolTipText(tip1);
		GridBagConstraints gbc_lblNumberBiclusters = new GridBagConstraints();
		gbc_lblNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberBiclusters.gridx = 0;
		gbc_lblNumberBiclusters.gridy = 0;
		add(this.lblNumberBiclusters, gbc_lblNumberBiclusters);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		integerTextFieldnumberbics.setToolTipText(tip1);
		this.integerTextFieldnumberbics.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 0;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblNormalizationMethod = new JLabel("Normalization method:");
		this.lblNormalizationMethod.setToolTipText(tip2);
		GridBagConstraints gbc_lblNormalizationMethod = new GridBagConstraints();
		gbc_lblNormalizationMethod.anchor = GridBagConstraints.EAST;
		gbc_lblNormalizationMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblNormalizationMethod.gridx = 0;
		gbc_lblNormalizationMethod.gridy = 1;
		add(this.lblNormalizationMethod, gbc_lblNormalizationMethod);
		
		this.comboBoxnormmethod = new JComboBox();
		comboBoxnormmethod.setToolTipText(tip2);
		GridBagConstraints gbc_comboBoxnormmethod = new GridBagConstraints();
		gbc_comboBoxnormmethod.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxnormmethod.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxnormmethod.gridx = 1;
		gbc_comboBoxnormmethod.gridy = 1;
		add(this.comboBoxnormmethod, gbc_comboBoxnormmethod);
		comboBoxnormmethod.addActionListener(this);
		comboBoxnormmethod.setActionCommand(NORMMETHOD);
		
		this.lblNormalizationAlphaValue = new JLabel("Normalization alpha value:");
		this.lblNormalizationAlphaValue.setToolTipText(tip3);
		GridBagConstraints gbc_lblNormalizationAlphaValue = new GridBagConstraints();
		gbc_lblNormalizationAlphaValue.anchor = GridBagConstraints.EAST;
		gbc_lblNormalizationAlphaValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblNormalizationAlphaValue.gridx = 0;
		gbc_lblNormalizationAlphaValue.gridy = 2;
		add(this.lblNormalizationAlphaValue, gbc_lblNormalizationAlphaValue);
		
		this.spinnernormalpha = new JSpinner();
		spinnernormalpha.setToolTipText(tip3);
		this.spinnernormalpha.setModel(new SpinnerNumberModel(90, 0.49, 100.0, 0.01));
		GridBagConstraints gbc_spinnernormalpha = new GridBagConstraints();
		gbc_spinnernormalpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnernormalpha.insets = new Insets(0, 0, 5, 0);
		gbc_spinnernormalpha.gridx = 1;
		gbc_spinnernormalpha.gridy = 2;
		add(this.spinnernormalpha, gbc_spinnernormalpha);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnumberbics.setText("2");
		comboBoxnormmethod.setSelectedItem(BBCNormalizationMethod.IQRN.getLongName());
		spinnernormalpha.setValue(90.0);
		spinnernormalpha.setEnabled(false);
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
		
		for (BBCNormalizationMethod  method : BBCNormalizationMethod.values()) {
			comboBoxnormmethod.addItem(method.getLongName());
		}
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{BBCMethod.NUMBERBICLUSTERS,BBCMethod.NORMALIZATIONMETHOD,BBCMethod.NORMALIZATIONALPHAVALUE};
		
		String norm=(String) comboBoxnormmethod.getSelectedItem();
		BBCNormalizationMethod bbcnorm=BBCNormalizationMethod.getBBCNormalizationMethodFromString(norm);
		
		
		String[] propdefaultvals=new String[]{integerTextFieldnumberbics.getText(),
				bbcnorm.getName(),
				String.valueOf(spinnernormalpha.getValue())};
		
		String[] propcomments=new String[]{
				"Number of biclusters that user hopes to find in the datasets",
				"Normalization method to be used on the microarray data.\n"
				+ "none (No normalization will be done by the BBC program)\n"
				+ "csn (Column-wise standardization)\n"
				+ "rsn (Row-wise standardization)\n"
				+ "iqrn (Inter-quartile range normalization, alpha value for the normalization needs to be specified)\n"
				+ "sqrn (The smallest range quartile normalization, alpha value for the normalization needs to be specified)\n",
				"alpha value for the normalization method, needs to be set if iqrn or sqrn is used (value between (5,100].\n"
				+ "alpha value means the normalization is done based on the alpha% quartile of data in each column"
			};
		
		
		
		String source="http://www.people.fas.harvard.edu/~junliu/BBC/BBC_manual.pdf";
		return AlgorithmProperties.setupProperties(propkeys, propdefaultvals,propcomments,source);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the number of biclusters must be higher than 1");
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
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(NORMMETHOD)) {
			String norm=(String) comboBoxnormmethod.getSelectedItem();
			BBCNormalizationMethod bbcnorm=BBCNormalizationMethod.getBBCNormalizationMethodFromString(norm);
			if(bbcnorm.equals(BBCNormalizationMethod.IQRN) || bbcnorm.equals(BBCNormalizationMethod.SQRN))
				spinnernormalpha.setEnabled(true);
			else
				spinnernormalpha.setEnabled(false);
		}
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(450,250);
	}

}
