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
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclicSettingsPanel.
 */
public class BiclicSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl correlation threshold. */
	private JLabel lblCorrelationThreshold;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text fieldmingenes. */
	private IntegerTextField integerTextFieldmingenes;
	
	/** The integer text fieldminconds. */
	private IntegerTextField integerTextFieldminconds;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The spinneroverlapthresh. */
	private JSpinner spinneroverlapthresh;
	
	/** The spinnercorrthreshold. */
	private JSpinner spinnercorrthreshold;
	
	/** The lbl min genes. */
	private JLabel lblMinGenes;

	
	
	/**
	 * Create the panel.
	 */
	public BiclicSettingsPanel() {
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
		
		this.lblCorrelationThreshold = new JLabel("Correlation threshold:");
		this.lblCorrelationThreshold.setToolTipText("The correlation threshold to find correlated expression");
		GridBagConstraints gbc_lblCorrelationThreshold = new GridBagConstraints();
		gbc_lblCorrelationThreshold.anchor = GridBagConstraints.EAST;
		gbc_lblCorrelationThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrelationThreshold.gridx = 0;
		gbc_lblCorrelationThreshold.gridy = 0;
		add(this.lblCorrelationThreshold, gbc_lblCorrelationThreshold);
		
		this.spinnercorrthreshold = new JSpinner();
		spinnercorrthreshold.setToolTipText("The correlation threshold to find correlated expression");
		this.spinnercorrthreshold.setModel(new SpinnerNumberModel(0.9, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnercorrthreshold = new GridBagConstraints();
		gbc_spinnercorrthreshold.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnercorrthreshold.insets = new Insets(0, 0, 5, 0);
		gbc_spinnercorrthreshold.gridx = 1;
		gbc_spinnercorrthreshold.gridy = 0;
		add(this.spinnercorrthreshold, gbc_spinnercorrthreshold);
		
		this.lblMinGenes = new JLabel("Min. genes:");
		this.lblMinGenes.setToolTipText("The minimum number of rows for final bicluster matrix");
		GridBagConstraints gbc_lblMinGenes = new GridBagConstraints();
		gbc_lblMinGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes.gridx = 0;
		gbc_lblMinGenes.gridy = 1;
		add(this.lblMinGenes, gbc_lblMinGenes);
		
		this.integerTextFieldmingenes = new IntegerTextField();
		integerTextFieldmingenes.setToolTipText("The minimum number of rows for final bicluster matrix");
		this.integerTextFieldmingenes.setColumns(10);
		GridBagConstraints gbc_integerTextFieldmingenes = new GridBagConstraints();
		gbc_integerTextFieldmingenes.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmingenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenes.gridx = 1;
		gbc_integerTextFieldmingenes.gridy = 1;
		add(this.integerTextFieldmingenes, gbc_integerTextFieldmingenes);
		
		this.label_1 = new JLabel("Min. Conditions:");
		this.label_1.setToolTipText("The minimum number of condition for final bicluster matrix. This should be larger than 2");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		add(this.label_1, gbc_label_1);
		
		this.integerTextFieldminconds = new IntegerTextField();
		integerTextFieldminconds.setToolTipText("The minimum number of condition for final bicluster matrix. This should be larger than 2");
		this.integerTextFieldminconds.setColumns(10);
		GridBagConstraints gbc_integerTextFieldminconds = new GridBagConstraints();
		gbc_integerTextFieldminconds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminconds.gridx = 1;
		gbc_integerTextFieldminconds.gridy = 2;
		add(this.integerTextFieldminconds, gbc_integerTextFieldminconds);
		
		this.lblOverlapTreshold = new JLabel("Overlap Treshold:");
		this.lblOverlapTreshold.setToolTipText("The threshold to filter out overlapped biclusters, 1 means filtering 100% overlapped biclusters");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 3;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.spinneroverlapthresh = new JSpinner();
		spinneroverlapthresh.setToolTipText("The threshold to filter out overlapped biclusters, 1 means filtering 100% overlapped biclusters");
		this.spinneroverlapthresh.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinneroverlapthresh = new GridBagConstraints();
		gbc_spinneroverlapthresh.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneroverlapthresh.gridx = 1;
		gbc_spinneroverlapthresh.gridy = 3;
		add(this.spinneroverlapthresh, gbc_spinneroverlapthresh);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		spinnercorrthreshold.setValue(0.9);
		integerTextFieldmingenes.setText("5");
		integerTextFieldminconds.setText("5");
		spinneroverlapthresh.setValue(1.0);
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
				RBiclicMethod.CORRELATIONTHRESHOLD,
				RBiclicMethod.MINROWS,
				RBiclicMethod.MINCOLUMNS,
				RBiclicMethod.OVERLAPTHRESHOLD
		};
		
		
		String[] values=new String[]{String.valueOf(spinnercorrthreshold.getValue()),
				integerTextFieldmingenes.getText(),
				integerTextFieldminconds.getText(),
				String.valueOf(spinneroverlapthresh.getValue())};
		
		String[] comments=new String[] {
				"The correlation threshold to find correlated expression",
				"The minimum number of rows for final bicluster matrix",
				"The minimum number of condition for final bicluster matrix. This should be larger than 2",
				"The threshold to filter out overlapped biclusters, 1 means filtering 100% overlapped biclusters"
		};
		
		String source="source: http://bisyn.kaist.ac.kr/software/biclic.htm";
		return AlgorithmProperties.setupProperties(propkeys, values, comments,source);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldmingenes.getText().isEmpty() || Integer.parseInt(integerTextFieldmingenes.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the minimum of genes must be higher than 0");
		else if(integerTextFieldminconds.getText().isEmpty() || Integer.parseInt(integerTextFieldminconds.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of conditions must be higher than 2");
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
		return new Dimension(450,250);
	}

}
