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

import jbiclustge.methods.algorithms.r.bicarepackage.RBicAREMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class BicareFlocSettingsPanel.
 */
public class BicareFlocSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl number biclusters. */
	private JLabel lblNumberBiclusters;
	
	/** The integer text fieldnbics. */
	private IntegerTextField integerTextFieldnbics;
	
	/** The lbl initial gene probability. */
	private JLabel lblInitialGeneProbability;
	
	/** The lbl initial condition probability. */
	private JLabel lblInitialConditionProbability;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl max discretization value. */
	private JLabel lblMaxDiscretizationValue;
	
	/** The lbl use max value. */
	private JLabel lblUseMaxValue;
	
	/** The lbl number iterations. */
	private JLabel lblNumberIterations;
	
	/** The spinnergeneprob. */
	private JSpinner spinnergeneprob;
	
	/** The spinnercondprob. */
	private JSpinner spinnercondprob;
	
	/** The double text fieldresthreshold. */
	private DoubleTextField doubleTextFieldresthreshold;
	
	/** The integer text fieldmingenesperbic. */
	private IntegerTextField integerTextFieldmingenesperbic;
	
	/** The integer text fieldmincondperbic. */
	private IntegerTextField integerTextFieldmincondperbic;
	
	/** The integer text fieldniter. */
	private IntegerTextField integerTextFieldniter;

	/**
	 * Create the panel.
	 */
	public BicareFlocSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberBiclusters = new JLabel("Number biclusters:");
		this.lblNumberBiclusters.setToolTipText("The number of biclusters searched");
		GridBagConstraints gbc_lblNumberBiclusters = new GridBagConstraints();
		gbc_lblNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberBiclusters.gridx = 0;
		gbc_lblNumberBiclusters.gridy = 0;
		add(this.lblNumberBiclusters, gbc_lblNumberBiclusters);
		
		this.integerTextFieldnbics = new IntegerTextField();
		this.integerTextFieldnbics.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnbics = new GridBagConstraints();
		gbc_integerTextFieldnbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnbics.gridx = 1;
		gbc_integerTextFieldnbics.gridy = 0;
		add(this.integerTextFieldnbics, gbc_integerTextFieldnbics);
		
		this.lblInitialGeneProbability = new JLabel("Initial gene probability: ");
		this.lblInitialGeneProbability.setToolTipText("Genes initial probability of membership to the biclusters");
		GridBagConstraints gbc_lblInitialGeneProbability = new GridBagConstraints();
		gbc_lblInitialGeneProbability.anchor = GridBagConstraints.EAST;
		gbc_lblInitialGeneProbability.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitialGeneProbability.gridx = 0;
		gbc_lblInitialGeneProbability.gridy = 1;
		add(this.lblInitialGeneProbability, gbc_lblInitialGeneProbability);
		
		spinnergeneprob = new JSpinner();
		this.spinnergeneprob.setModel(new SpinnerNumberModel(0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnergeneprob = new GridBagConstraints();
		gbc_spinnergeneprob.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnergeneprob.insets = new Insets(0, 0, 5, 0);
		gbc_spinnergeneprob.gridx = 1;
		gbc_spinnergeneprob.gridy = 1;
		add(spinnergeneprob, gbc_spinnergeneprob);
		
		this.lblInitialConditionProbability = new JLabel("Initial condition probability:");
		this.lblInitialConditionProbability.setToolTipText("Samples initial probability of membership to the biclusters");
		GridBagConstraints gbc_lblInitialConditionProbability = new GridBagConstraints();
		gbc_lblInitialConditionProbability.anchor = GridBagConstraints.EAST;
		gbc_lblInitialConditionProbability.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitialConditionProbability.gridx = 0;
		gbc_lblInitialConditionProbability.gridy = 2;
		add(this.lblInitialConditionProbability, gbc_lblInitialConditionProbability);
		
		spinnercondprob = new JSpinner();
		this.spinnercondprob.setModel(new SpinnerNumberModel(0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnercondprob = new GridBagConstraints();
		gbc_spinnercondprob.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnercondprob.insets = new Insets(0, 0, 5, 0);
		gbc_spinnercondprob.gridx = 1;
		gbc_spinnercondprob.gridy = 2;
		add(spinnercondprob, gbc_spinnercondprob);
		
		this.lblOverlapTreshold = new JLabel("Residue threshold:");
		this.lblOverlapTreshold.setToolTipText("The residue threshold, if 0 is calculated from data (default=0)");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 3;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		doubleTextFieldresthreshold = new DoubleTextField();
		doubleTextFieldresthreshold.setText("0");
		GridBagConstraints gbc_doubleTextFieldresthreshold = new GridBagConstraints();
		gbc_doubleTextFieldresthreshold.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldresthreshold.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldresthreshold.gridx = 1;
		gbc_doubleTextFieldresthreshold.gridy = 3;
		add(doubleTextFieldresthreshold, gbc_doubleTextFieldresthreshold);
		
		this.lblMaxDiscretizationValue = new JLabel("Min. genes per bicluster:");
		this.lblMaxDiscretizationValue.setToolTipText("Maximum value in the discretized dataset. From this value, BiBit will binarize the dataset generating max_value different ones");
		GridBagConstraints gbc_lblMaxDiscretizationValue = new GridBagConstraints();
		gbc_lblMaxDiscretizationValue.anchor = GridBagConstraints.EAST;
		gbc_lblMaxDiscretizationValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxDiscretizationValue.gridx = 0;
		gbc_lblMaxDiscretizationValue.gridy = 4;
		add(this.lblMaxDiscretizationValue, gbc_lblMaxDiscretizationValue);
		
		integerTextFieldmingenesperbic = new IntegerTextField();
		this.integerTextFieldmingenesperbic.setToolTipText("Minmum number of genes per bicluster");
		GridBagConstraints gbc_integerTextFieldmingenesperbic = new GridBagConstraints();
		gbc_integerTextFieldmingenesperbic.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmingenesperbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenesperbic.gridx = 1;
		gbc_integerTextFieldmingenesperbic.gridy = 4;
		add(integerTextFieldmingenesperbic, gbc_integerTextFieldmingenesperbic);
		
		this.lblUseMaxValue = new JLabel("Min. conditions per bicluster:");
		this.lblUseMaxValue.setToolTipText("Minimum number of conditions per bicluster");
		GridBagConstraints gbc_lblUseMaxValue = new GridBagConstraints();
		gbc_lblUseMaxValue.anchor = GridBagConstraints.EAST;
		gbc_lblUseMaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblUseMaxValue.gridx = 0;
		gbc_lblUseMaxValue.gridy = 5;
		add(this.lblUseMaxValue, gbc_lblUseMaxValue);
		
		integerTextFieldmincondperbic = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldmincondperbic = new GridBagConstraints();
		gbc_integerTextFieldmincondperbic.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmincondperbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmincondperbic.gridx = 1;
		gbc_integerTextFieldmincondperbic.gridy = 5;
		add(integerTextFieldmincondperbic, gbc_integerTextFieldmincondperbic);
		
		lblNumberIterations = new JLabel("Number iterations:");
		this.lblNumberIterations.setToolTipText("Number of iterations");
		GridBagConstraints gbc_lblNumberIterations = new GridBagConstraints();
		gbc_lblNumberIterations.anchor = GridBagConstraints.EAST;
		gbc_lblNumberIterations.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberIterations.gridx = 0;
		gbc_lblNumberIterations.gridy = 6;
		add(lblNumberIterations, gbc_lblNumberIterations);
		
		integerTextFieldniter = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldniter = new GridBagConstraints();
		gbc_integerTextFieldniter.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldniter.gridx = 1;
		gbc_integerTextFieldniter.gridy = 6;
		add(integerTextFieldniter, gbc_integerTextFieldniter);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnbics.setText("2");
		spinnergeneprob.setValue(0.5);
		spinnercondprob.setValue(0.5);
		doubleTextFieldresthreshold.setText("0");
		integerTextFieldmingenesperbic.setText("8");
		integerTextFieldmincondperbic.setText("6");
		integerTextFieldniter.setText("500");
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
				RBicAREMethod.BICARE_NCLUSTERS,
				RBicAREMethod.BICARE_INITGENESPROB,
				RBicAREMethod.BICARE_INITCONDSPROB,
				RBicAREMethod.BICARE_RESIDUETHRESHOLD,
				RBicAREMethod.BICARE_MINGENESBIC,
				RBicAREMethod.BICARE_MINCONDSBIC,
				RBicAREMethod.BICARE_NITER
	
		};
		String[] values=new String[]{integerTextFieldnbics.getText(),
				String.valueOf(spinnergeneprob.getValue()),
				String.valueOf(spinnercondprob.getValue()),
				doubleTextFieldresthreshold.getText(),
				integerTextFieldmingenesperbic.getText(),
				integerTextFieldmincondperbic.getText(),
				integerTextFieldniter.getText()};
		
		String[] comments=new String[] {
				"The number of biclusters searched",
				"Genes initial probability of membership to the biclusters",
				"Samples initial probability of membership to the biclusters",
				"The residue threshold, if 0 is calculated from data (default=0)",
				"Minimal number of gene per bicluster",
				"Minimal number of conditions per bicluster",
				"Number of iterations"
				
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: BicARE manual, url: https://www.bioconductor.org/packages/release/bioc/vignettes/BicARE/inst/doc/BicARE.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnbics.getText())<1)
			return new Pair<Boolean, String>(false, "The number of biclusters to be search must be higher than 0");
		else if(doubleTextFieldresthreshold.getText().isEmpty() || Double.parseDouble(doubleTextFieldresthreshold.getText())<0)
			return new Pair<Boolean, String>(false, "The value for the residue threshold must be 0 or higher");
		else if(integerTextFieldmingenesperbic.getText().isEmpty() || Integer.parseInt(integerTextFieldmingenesperbic.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the minimal number of genes per bicluster must be higher than 0");
		else if(integerTextFieldmincondperbic.getText().isEmpty() || Integer.parseInt(integerTextFieldmincondperbic.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the minimal number of conditions per bicluster must be higher than 0");
		else if(integerTextFieldniter.getText().isEmpty() || Integer.parseInt(integerTextFieldniter.getText())<1)
			return new Pair<Boolean, String>(false, "The Number of iterations must be higher than 0");
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
