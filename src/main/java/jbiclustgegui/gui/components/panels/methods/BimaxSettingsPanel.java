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

import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class BimaxSettingsPanel.
 */
public class BimaxSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label. */
	private JLabel label;
	
	/** The integer text field. */
	private IntegerTextField integerTextField;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text field 1. */
	private IntegerTextField integerTextField_1;
	
	/** The label 2. */
	private JLabel label_2;
	
	/** The integer text field 2. */
	private IntegerTextField integerTextField_2;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The spinner. */
	private JSpinner spinner;

	/**
	 * Create the panel.
	 */
	public BimaxSettingsPanel() {
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
		
		this.label = new JLabel("Min. Genes:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(this.label, gbc_label);
		
		this.integerTextField = new IntegerTextField();
		integerTextField.setToolTipText("Minimum of genes in biclusters, 0 = no limits");
		this.integerTextField.setColumns(10);
		GridBagConstraints gbc_integerTextField = new GridBagConstraints();
		gbc_integerTextField.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextField.gridx = 1;
		gbc_integerTextField.gridy = 0;
		add(this.integerTextField, gbc_integerTextField);
		
		this.label_1 = new JLabel("Min. Conditions:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		add(this.label_1, gbc_label_1);
		
		this.integerTextField_1 = new IntegerTextField();
		integerTextField_1.setToolTipText("Minimum of conditions in biclusters, 0 = no limits");
		this.integerTextField_1.setColumns(10);
		GridBagConstraints gbc_integerTextField_1 = new GridBagConstraints();
		gbc_integerTextField_1.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextField_1.gridx = 1;
		gbc_integerTextField_1.gridy = 1;
		add(this.integerTextField_1, gbc_integerTextField_1);
		
		this.label_2 = new JLabel("Max number biclusters");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		add(this.label_2, gbc_label_2);
		
		this.integerTextField_2 = new IntegerTextField();
		integerTextField_2.setToolTipText("Maximum number of biclusters");
		this.integerTextField_2.setColumns(10);
		GridBagConstraints gbc_integerTextField_2 = new GridBagConstraints();
		gbc_integerTextField_2.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextField_2.gridx = 1;
		gbc_integerTextField_2.gridy = 2;
		add(this.integerTextField_2, gbc_integerTextField_2);
		
		this.lblOverlapTreshold = new JLabel("Overlap Treshold:");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 3;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.spinner = new JSpinner();
		spinner.setToolTipText("<html>Overlap threshold, value between interval [0,1] . If value is equal to 1.0, the algorithm will return the maximum number of biclusters defined by user (default=1.0)<br>Otherwise only return the biclusters which respect overlap threshold</html>");
		this.spinner.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 3;
		add(this.spinner, gbc_spinner);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextField.setText("2");
		integerTextField_1.setText("2");
		integerTextField_2.setText("100");
		spinner.setValue(1.0);
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
				BimaxMethod.MINGENES,
				BimaxMethod.MINCONDITIONS,
				BimaxMethod.MAXNUMBERBICLUSTERS,
				BimaxMethod.OVERLAPTHRESHOLD
		};
		
		
		String[] defaultvalues=new String[]{integerTextField.getText(),integerTextField_1.getText(),integerTextField_2.getText(),String.valueOf(spinner.getValue())};
		
		String[] comments=new String[] {
				"biclusters output need to have at least that many genes, 0= no limits",
				"biclusters output need to have at least that many conditions, 0= no limits",
				"number of bicluster to find, (default=0 find maximum number of biclusters)",
				"overlap threshold, value between [0,1] interval. If value is equal to 1.0, the algorithm will return the maximum number of biclusters defined by user (default=1.0)\n.Otherwise only return the biclusters which respect overlap threshold"
		};
		return AlgorithmProperties.setupProperties(propkeys, defaultvalues, comments);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextField.getText().isEmpty() || Integer.parseInt(integerTextField.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of genes must be higher than 2");
		else if(integerTextField_1.getText().isEmpty() || Integer.parseInt(integerTextField_1.getText())<2)
			return new Pair<Boolean, String>(false, "The value for the minimum of conditions must be higher than 2");
		else if(integerTextField_2.getText().isEmpty() || Integer.parseInt(integerTextField_2.getText())<1)
			return new Pair<Boolean, String>(false, "Value for maximum number of biclusters must be higher than 1");
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
