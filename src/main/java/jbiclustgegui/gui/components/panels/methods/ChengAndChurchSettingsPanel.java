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

import jbiclustge.methods.algorithms.r.biclustpackage.RBCCCMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ChengAndChurchSettingsPanel.
 */
public class ChengAndChurchSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl number biclusters. */
	private JLabel lblNumberBiclusters;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl delta. */
	private JLabel lblDelta;
	
	/** The lbl alpha. */
	private JLabel lblAlpha;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl use quantiles. */
	private JLabel lblUseQuantiles;
	
	/** The double text fielddelta. */
	private DoubleTextField doubleTextFielddelta;
	
	/** The double text fieldalpha. */
	private DoubleTextField doubleTextFieldalpha;
	
	/** The integer text fieldnumberdiscrtlevel. */
	private IntegerTextField integerTextFieldnumberdiscrtlevel;
	
	/** The check boxusequantiles. */
	private JCheckBox checkBoxusequantiles;

	/**
	 * Create the panel.
	 */
	public ChengAndChurchSettingsPanel() {
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
		
		this.lblNumberBiclusters = new JLabel("Number biclusters:");
		this.lblNumberBiclusters.setToolTipText("Number of bicluster to be found");
		GridBagConstraints gbc_lblNumberBiclusters = new GridBagConstraints();
		gbc_lblNumberBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberBiclusters.gridx = 0;
		gbc_lblNumberBiclusters.gridy = 0;
		add(this.lblNumberBiclusters, gbc_lblNumberBiclusters);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		this.integerTextFieldnumberbics.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 0;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblDelta = new JLabel("delta:");
		this.lblDelta.setToolTipText("Maximum of accepted score (delta)");
		GridBagConstraints gbc_lblDelta = new GridBagConstraints();
		gbc_lblDelta.anchor = GridBagConstraints.EAST;
		gbc_lblDelta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelta.gridx = 0;
		gbc_lblDelta.gridy = 1;
		add(this.lblDelta, gbc_lblDelta);
		
		this.doubleTextFielddelta = new DoubleTextField();
		this.doubleTextFielddelta.setText("1.0");
		GridBagConstraints gbc_doubleTextFielddelta = new GridBagConstraints();
		gbc_doubleTextFielddelta.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFielddelta.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielddelta.gridx = 1;
		gbc_doubleTextFielddelta.gridy = 1;
		add(this.doubleTextFielddelta, gbc_doubleTextFielddelta);
		
		this.lblAlpha = new JLabel("alpha:");
		this.lblAlpha.setToolTipText("Scaling factor (alpha)");
		GridBagConstraints gbc_lblAlpha = new GridBagConstraints();
		gbc_lblAlpha.anchor = GridBagConstraints.EAST;
		gbc_lblAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlpha.gridx = 0;
		gbc_lblAlpha.gridy = 2;
		add(this.lblAlpha, gbc_lblAlpha);
		
		this.doubleTextFieldalpha = new DoubleTextField();
		this.doubleTextFieldalpha.setText("1.5");
		GridBagConstraints gbc_doubleTextFieldalpha = new GridBagConstraints();
		gbc_doubleTextFieldalpha.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldalpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldalpha.gridx = 1;
		gbc_doubleTextFieldalpha.gridy = 2;
		add(this.doubleTextFieldalpha, gbc_doubleTextFieldalpha);
		
		this.lblOverlapTreshold = new JLabel("Number discretization levels:");
		this.lblOverlapTreshold.setToolTipText("Number of discretization levels, no value = default value = 10");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 3;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.integerTextFieldnumberdiscrtlevel = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldnumberdiscrtlevel = new GridBagConstraints();
		gbc_integerTextFieldnumberdiscrtlevel.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberdiscrtlevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberdiscrtlevel.gridx = 1;
		gbc_integerTextFieldnumberdiscrtlevel.gridy = 3;
		add(this.integerTextFieldnumberdiscrtlevel, gbc_integerTextFieldnumberdiscrtlevel);
		
		this.lblUseQuantiles = new JLabel("Use quantiles:");
		this.lblUseQuantiles.setToolTipText("Use the quantiles, else uses equally spaced levels");
		GridBagConstraints gbc_lblUseQuantiles = new GridBagConstraints();
		gbc_lblUseQuantiles.anchor = GridBagConstraints.EAST;
		gbc_lblUseQuantiles.insets = new Insets(0, 0, 0, 5);
		gbc_lblUseQuantiles.gridx = 0;
		gbc_lblUseQuantiles.gridy = 4;
		add(this.lblUseQuantiles, gbc_lblUseQuantiles);
		
		this.checkBoxusequantiles = new JCheckBox("");
		GridBagConstraints gbc_checkBoxusequantiles = new GridBagConstraints();
		gbc_checkBoxusequantiles.anchor = GridBagConstraints.WEST;
		gbc_checkBoxusequantiles.gridx = 1;
		gbc_checkBoxusequantiles.gridy = 4;
		add(this.checkBoxusequantiles, gbc_checkBoxusequantiles);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnumberbics.setText("100");
		doubleTextFielddelta.setText("1.0");
		doubleTextFieldalpha.setText("1.5");
		integerTextFieldnumberdiscrtlevel.setText("10");
		checkBoxusequantiles.setSelected(false);
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
				RBCCCMethod.BCC_NCLUSTERS,
				RBCCCMethod.BCC_DELTA,
				RBCCCMethod.BCC_ALPHA,
				RBCCCMethod.BCC_DISCRETIZE_LEVELS,
				RBCCCMethod.BCC_DISCRETIZE_QUANTILE,
		};
		
		String disclevels="";
		if(!integerTextFieldnumberdiscrtlevel.getText().isEmpty())
			disclevels=integerTextFieldnumberdiscrtlevel.getText();
		
		String[] values=new String[]{integerTextFieldnumberbics.getText(),
				doubleTextFielddelta.getText(),
				doubleTextFieldalpha.getText(),
				disclevels,
				String.valueOf(checkBoxusequantiles.isSelected())};
		
		String[] comments=new String[] {
				"Number of bicluster to be found",
				"Maximum of accepted score (delta)",
				"Scaling factor (alpha)",
				"Number of discretization levels, no value = default value = 10",
				"Use the quantiles, else uses equally spaced levels"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: biclust manual, url: https://cran.r-project.org/web/packages/biclust/biclust.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "Value for the number of biclusters must be higher than 0");
		else if(doubleTextFielddelta.getText().isEmpty() || Double.parseDouble(doubleTextFielddelta.getText())<0)
			return new Pair<Boolean, String>(false, "The value of delta must be higher than 0");
		else if(doubleTextFieldalpha.getText().isEmpty() || Double.parseDouble(doubleTextFieldalpha.getText())<0)
			return new Pair<Boolean, String>(false, "The value of alpha must be higher than 0");
		else if(!integerTextFieldnumberbics.getText().isEmpty() && Integer.parseInt(integerTextFieldnumberdiscrtlevel.getText())<1)
			return new Pair<Boolean, String>(false, "The value of discretization levels must be higher than 0");
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
		return new Dimension(450,280);
	}

}
