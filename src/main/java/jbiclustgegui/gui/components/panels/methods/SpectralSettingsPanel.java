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

import jbiclustge.methods.algorithms.r.biclic.RBiclicMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RSpectralMethod;
import jbiclustge.methods.algorithms.r.components.BCSpectralNormalizationMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JComboBox;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class SpectralSettingsPanel.
 */
public class SpectralSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl correlation threshold. */
	private JLabel lblCorrelationThreshold;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text fieldeigenvalues. */
	private IntegerTextField integerTextFieldeigenvalues;
	
	/** The integer text fieldminconds. */
	private IntegerTextField integerTextFieldminconds;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl min genes. */
	private JLabel lblMinGenes;
	
	/** The combo boxnormmethod. */
	private JComboBox comboBoxnormmethod;
	
	/** The lbl min genes 1. */
	private JLabel lblMinGenes_1;
	
	/** The integer text fieldmingenes. */
	private IntegerTextField integerTextFieldmingenes;
	
	/** The double text fieldmaxvariation. */
	private DoubleTextField doubleTextFieldmaxvariation;

	/**
	 * Create the panel.
	 */
	public SpectralSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblCorrelationThreshold = new JLabel("Normalization method:");
		this.lblCorrelationThreshold.setToolTipText("Normalization method, three methods are allowed: log (Logarithmic normalization), irrc (Independent Rescalng of Rows and Columns) and bistochastization");
		GridBagConstraints gbc_lblCorrelationThreshold = new GridBagConstraints();
		gbc_lblCorrelationThreshold.anchor = GridBagConstraints.EAST;
		gbc_lblCorrelationThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrelationThreshold.gridx = 0;
		gbc_lblCorrelationThreshold.gridy = 0;
		add(this.lblCorrelationThreshold, gbc_lblCorrelationThreshold);
		
		this.comboBoxnormmethod = new JComboBox();
		GridBagConstraints gbc_comboBoxnormmethod = new GridBagConstraints();
		gbc_comboBoxnormmethod.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxnormmethod.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxnormmethod.gridx = 1;
		gbc_comboBoxnormmethod.gridy = 0;
		add(this.comboBoxnormmethod, gbc_comboBoxnormmethod);
		
		this.lblMinGenes = new JLabel("Number of eigen values:");
		this.lblMinGenes.setToolTipText("Number of eigenValues considered to find biclusters, high number could increase dramatically time performance");
		GridBagConstraints gbc_lblMinGenes = new GridBagConstraints();
		gbc_lblMinGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes.gridx = 0;
		gbc_lblMinGenes.gridy = 1;
		add(this.lblMinGenes, gbc_lblMinGenes);
		
		this.integerTextFieldeigenvalues = new IntegerTextField();
		this.integerTextFieldeigenvalues.setColumns(10);
		GridBagConstraints gbc_integerTextFieldeigenvalues = new GridBagConstraints();
		gbc_integerTextFieldeigenvalues.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldeigenvalues.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldeigenvalues.gridx = 1;
		gbc_integerTextFieldeigenvalues.gridy = 1;
		add(this.integerTextFieldeigenvalues, gbc_integerTextFieldeigenvalues);
		
		this.lblMinGenes_1 = new JLabel("Min. Genes:");
		this.lblMinGenes_1.setToolTipText("Minimum number of rows that biclusters must have");
		GridBagConstraints gbc_lblMinGenes_1 = new GridBagConstraints();
		gbc_lblMinGenes_1.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes_1.gridx = 0;
		gbc_lblMinGenes_1.gridy = 2;
		add(this.lblMinGenes_1, gbc_lblMinGenes_1);
		
		this.integerTextFieldmingenes = new IntegerTextField();
		this.integerTextFieldmingenes.setColumns(10);
		GridBagConstraints gbc_integerTextFieldmingenes = new GridBagConstraints();
		gbc_integerTextFieldmingenes.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmingenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenes.gridx = 1;
		gbc_integerTextFieldmingenes.gridy = 2;
		add(this.integerTextFieldmingenes, gbc_integerTextFieldmingenes);
		
		this.label_1 = new JLabel("Min. Conditions:");
		this.label_1.setToolTipText("Minimum number of columns that biclusters must have");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		add(this.label_1, gbc_label_1);
		
		this.integerTextFieldminconds = new IntegerTextField();
		this.integerTextFieldminconds.setColumns(10);
		GridBagConstraints gbc_integerTextFieldminconds = new GridBagConstraints();
		gbc_integerTextFieldminconds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminconds.gridx = 1;
		gbc_integerTextFieldminconds.gridy = 3;
		add(this.integerTextFieldminconds, gbc_integerTextFieldminconds);
		
		this.lblOverlapTreshold = new JLabel("Maximum within variation:");
		this.lblOverlapTreshold.setToolTipText("Maximum within variation allowed");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 4;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.doubleTextFieldmaxvariation = new DoubleTextField();
		GridBagConstraints gbc_doubleTextFieldmaxvariation = new GridBagConstraints();
		gbc_doubleTextFieldmaxvariation.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldmaxvariation.gridx = 1;
		gbc_doubleTextFieldmaxvariation.gridy = 4;
		add(this.doubleTextFieldmaxvariation, gbc_doubleTextFieldmaxvariation);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		comboBoxnormmethod.setSelectedItem(BCSpectralNormalizationMethod.LOG.getName());
		integerTextFieldeigenvalues.setText("3");
		integerTextFieldmingenes.setText("2");
		integerTextFieldminconds.setText("2");
		doubleTextFieldmaxvariation.setText("1.0");
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
		
		for (BCSpectralNormalizationMethod element : BCSpectralNormalizationMethod.values()) {
			comboBoxnormmethod.addItem(element.getName());
		}
		
	}

	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				RSpectralMethod.Spectral_NORMALIZATION,
				RSpectralMethod.Spectral_NEIGENVALUES,
				RSpectralMethod.Spectral_MINR,
				RSpectralMethod.Spectral_MINC,
				RSpectralMethod.Spectral_WITHINVAR
		};
		
		
		String normmethod=(String) comboBoxnormmethod.getSelectedItem();
		BCSpectralNormalizationMethod method=BCSpectralNormalizationMethod.getBCSpectralNormalizationMethodFromString(normmethod);
		
		String[] values=new String[]{method.toString(),
				integerTextFieldeigenvalues.getText(),
				integerTextFieldmingenes.getText(),
				integerTextFieldminconds.getText(),
				doubleTextFieldmaxvariation.getText()};
		
		String[] comments=new String[] {
				"Normalization method, three methods are allowed: log (Logarithmic normalization), irrc (Independent Rescalng of Rows and Columns) and bistochastization",
				"Number of eigenValues considered to find biclusters, high number could increase dramatically time performance",
				"Minimum number of rows that biclusters must have",
				"Minimum number of columns that biclusters must have",
				"Maximum within variation allowed"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: biclust manual, url: https://cran.r-project.org/web/packages/biclust/biclust.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldeigenvalues.getText().isEmpty() || Integer.parseInt(integerTextFieldeigenvalues.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the eigen values must be higher than 0");
		else if(integerTextFieldmingenes.getText().isEmpty() || Integer.parseInt(integerTextFieldmingenes.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of genes must be higher than 1");
		else if(integerTextFieldminconds.getText().isEmpty() || Integer.parseInt(integerTextFieldminconds.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of conditions must be higher than 1");
		else if(doubleTextFieldmaxvariation.getText().isEmpty() || Double.parseDouble(doubleTextFieldmaxvariation.getText())<0)
			return new Pair<Boolean, String>(false, "The value for the Maximum within variation must be 0 or higher");
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
