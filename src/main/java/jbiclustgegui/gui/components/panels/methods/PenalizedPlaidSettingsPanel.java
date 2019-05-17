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

import jbiclustge.methods.algorithms.java.bibit.BibitMethod;
import jbiclustge.methods.algorithms.java.penalizedplaid.PPlaidModelData;
import jbiclustge.methods.algorithms.java.penalizedplaid.PenalizedPlaidMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.props.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class PenalizedPlaidSettingsPanel.
 */
public class PenalizedPlaidSettingsPanel extends AbstractMethodSettingsPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl number biclusters. */
	private JLabel lblNumberBiclusters;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl model scheme. */
	private JLabel lblModelScheme;
	
	/** The lbl max number biclusters. */
	private JLabel lblMaxNumberBiclusters;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The combo boxmodelscheme. */
	private JComboBox comboBoxmodelscheme;
	
	/** The spinnermcmc. */
	private JSpinner spinnermcmc;
	
	/** The spinnerburnin. */
	private JSpinner spinnerburnin;
	
	/** The lbl lambda value. */
	private JLabel lblLambdaValue;
	
	/** The double text fieldlambda. */
	private DoubleTextField doubleTextFieldlambda;

	
	/** The changemodel. */
	private static String CHANGEMODEL="changemodel";
	/**
	 * Create the panel.
	 */
	public PenalizedPlaidSettingsPanel() {
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
		
		this.lblNumberBiclusters = new JLabel("Number biclusters:");
		this.lblNumberBiclusters.setToolTipText("Is the number of biclusters to estimate");
		GridBagConstraints gbc_lblNumberBiclusters = new GridBagConstraints();
		gbc_lblNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberBiclusters.gridx = 0;
		gbc_lblNumberBiclusters.gridy = 0;
		add(this.lblNumberBiclusters, gbc_lblNumberBiclusters);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		integerTextFieldnumberbics.setToolTipText("Is the number of biclusters to estimate");
		this.integerTextFieldnumberbics.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 0;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblModelScheme = new JLabel("Model scheme:");
		this.lblModelScheme.setToolTipText("<html>Is the model used for the data. We have basically 4 methods:\n<ul><li>GPE, penalized plaid model with the sampling of the penalty parameter, lambda. The model is fitted with a Gibbs sampling\n<li>GPF, the penalized model with a fix value of the penalty parameter lambda and fitted with the  Gibbs sampling procedure.<br>When  lambda=0, the plaid model does not assume any constraint on the amount of overlapping genes and conditions between biclusters.<br> When lambda tends to infinity (lambda>=10^3) is recommended to speed up the algorithm<br>Authors assume that biclusters do not overlap as with the Cheng and Church model\n<li> MPE (the penalized model fitted with a Metropolis-Hastings procedure)\n<li>MPF, the penalized model with a fix value of lambda and fitted with a Metropolis-Hastings procedure</ul></html>");
		GridBagConstraints gbc_lblModelScheme = new GridBagConstraints();
		gbc_lblModelScheme.anchor = GridBagConstraints.EAST;
		gbc_lblModelScheme.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelScheme.gridx = 0;
		gbc_lblModelScheme.gridy = 1;
		add(this.lblModelScheme, gbc_lblModelScheme);
		
		this.comboBoxmodelscheme = new JComboBox();
		comboBoxmodelscheme.setToolTipText("<html>Is the model used for the data. We have basically 4 methods:\n<ul><li>GPE, penalized plaid model with the sampling of the penalty parameter, lambda. The model is fitted with a Gibbs sampling\n<li>GPF, the penalized model with a fix value of the penalty parameter lambda and fitted with the  Gibbs sampling procedure.<br>When  lambda=0, the plaid model does not assume any constraint on the amount of overlapping genes and conditions between biclusters.<br> When lambda tends to infinity (lambda>=10^3) is recommended to speed up the algorithm<br>Authors assume that biclusters do not overlap as with the Cheng and Church model\n<li> MPE (the penalized model fitted with a Metropolis-Hastings procedure)\n<li>MPF, the penalized model with a fix value of lambda and fitted with a Metropolis-Hastings procedure</ul></html>");
		GridBagConstraints gbc_comboBoxmodelscheme = new GridBagConstraints();
		gbc_comboBoxmodelscheme.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxmodelscheme.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxmodelscheme.gridx = 1;
		gbc_comboBoxmodelscheme.gridy = 1;
		add(this.comboBoxmodelscheme, gbc_comboBoxmodelscheme);
		comboBoxmodelscheme.addActionListener(this);
		comboBoxmodelscheme.setActionCommand(CHANGEMODEL);
		
		this.lblLambdaValue = new JLabel("lambda value:");
		this.lblLambdaValue.setToolTipText("If the models are GPF or MPF, is necessary to specify a fix value of lambda");
		GridBagConstraints gbc_lblLambdaValue = new GridBagConstraints();
		gbc_lblLambdaValue.anchor = GridBagConstraints.EAST;
		gbc_lblLambdaValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblLambdaValue.gridx = 0;
		gbc_lblLambdaValue.gridy = 2;
		add(this.lblLambdaValue, gbc_lblLambdaValue);
		
		this.doubleTextFieldlambda = new DoubleTextField();
		doubleTextFieldlambda.setToolTipText("If the models are GPF or MPF, is necessary to specify a fix value of lambda");
		GridBagConstraints gbc_doubleTextFieldlambda = new GridBagConstraints();
		gbc_doubleTextFieldlambda.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldlambda.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldlambda.gridx = 1;
		gbc_doubleTextFieldlambda.gridy = 2;
		add(this.doubleTextFieldlambda, gbc_doubleTextFieldlambda);
		
		this.lblMaxNumberBiclusters = new JLabel("Number MCMC samples:");
		this.lblMaxNumberBiclusters.setToolTipText("Is the number of MCMC samples after the burn in. Authors recommend more than 1000 samples");
		GridBagConstraints gbc_lblMaxNumberBiclusters = new GridBagConstraints();
		gbc_lblMaxNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblMaxNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxNumberBiclusters.gridx = 0;
		gbc_lblMaxNumberBiclusters.gridy = 3;
		add(this.lblMaxNumberBiclusters, gbc_lblMaxNumberBiclusters);
		
		this.spinnermcmc = new JSpinner();
		spinnermcmc.setToolTipText("Is the number of MCMC samples after the burn in. Authors recommend more than 1000 samples");
		this.spinnermcmc.setModel(new SpinnerNumberModel(1000, 1, 100000, 10));
		GridBagConstraints gbc_spinnermcmc = new GridBagConstraints();
		gbc_spinnermcmc.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermcmc.insets = new Insets(0, 0, 5, 0);
		gbc_spinnermcmc.gridx = 1;
		gbc_spinnermcmc.gridy = 3;
		add(this.spinnermcmc, gbc_spinnermcmc);
		
		this.lblOverlapTreshold = new JLabel("Number of burn-in samples:");
		this.lblOverlapTreshold.setToolTipText("Is the number of burn-in samples. Authors recommend a number more than 1000");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 4;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.spinnerburnin = new JSpinner();
		spinnerburnin.setToolTipText("Is the number of burn-in samples. Authors recommend a number more than 1000");
		this.spinnerburnin.setModel(new SpinnerNumberModel(1000, 1, 100000, 10));
		GridBagConstraints gbc_spinnerburnin = new GridBagConstraints();
		gbc_spinnerburnin.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerburnin.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerburnin.gridx = 1;
		gbc_spinnerburnin.gridy = 4;
		add(this.spinnerburnin, gbc_spinnerburnin);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnumberbics.setText("5");
		comboBoxmodelscheme.setSelectedItem(PPlaidModelData.GPE);
		doubleTextFieldlambda.setText("0");
		doubleTextFieldlambda.setEnabled(false);
		spinnermcmc.setValue(1000);
		spinnerburnin.setValue(1000);
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
		
		for (PPlaidModelData model : PPlaidModelData.values()) {
			comboBoxmodelscheme.addItem(model);
		}
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				PenalizedPlaidMethod.NUMBERBICLUSTERSESTIMATE,
				PenalizedPlaidMethod.MODELTODATA,
				PenalizedPlaidMethod.MCMCSAMPLES,
				PenalizedPlaidMethod.BURNINSAMPLES,
				PenalizedPlaidMethod.PPLAMBDA
		};
		
		String[] values=new String[]{integerTextFieldnumberbics.getText(),
				comboBoxmodelscheme.getSelectedItem().toString(),
				String.valueOf(spinnermcmc.getValue()),
				String.valueOf(spinnerburnin.getValue()),
				doubleTextFieldlambda.getText()};
		
		String[] comments=new String[] {
				"Is the number of biclusters to estimate",
				"Is the model used for the data. We have basically 4 methods: \n"
				+ "1) GPE (penalized plaid model with the sampling of the penalty parameter,lambda. The model is fitted with a Gibbs sampling); \n"
				+ "2) GPF (the penalized model with a fix value of the penalty parameter lambda and fitted with the  Gibbs sampling procedure. When  lambda=0, the plaid modeldoes not assume "
				+ "any constraint on the amount of overlapping genes and conditions between biclusters. When lambda tends to infinity (lambda>=10^3 is recommended to speed up the algorithm) "
				+ "Authors assume that biclusters do not overlap as with the Cheng and Church model); \n"
				+ "3) MPE (the penalized model fitted with a Metropolis-Hastings procedure); \n"
				+ "4) MPF (the penalized model with a fix value of lambda and fitted with a Metropolis-Hastings procedure)",
				
				
				"Is the number of MCMC samples after the burn in. Authors recommend more than 1000 samples",
				"Is the number of burn-in samples. Authors recommend a number more than 1000",
				"If the models are GPF or MPF, is necessary to specify a fix value of lambda"
				
		};
		
		String source="source:  http://www.dms.umontreal.ca/~murua/software/README.txt";
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments, source);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		
		
		
		if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the minimum of genes must be higher than 1");
		else if(doubleTextFieldlambda.getText().isEmpty() && (comboBoxmodelscheme.getSelectedItem().equals(PPlaidModelData.GPF) || comboBoxmodelscheme.getSelectedItem().equals(PPlaidModelData.MPF)))
			return new Pair<Boolean, String>(false, "Please set a value to lambda");
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
		
		if(cmd.equals(CHANGEMODEL)) {
			PPlaidModelData model=(PPlaidModelData) comboBoxmodelscheme.getSelectedItem();
			if(model.equals(PPlaidModelData.GPF) || model.equals(PPlaidModelData.MPF))
				doubleTextFieldlambda.setEnabled(true);
			else 
				doubleTextFieldlambda.setEnabled(false);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(450,300);
	}

}
