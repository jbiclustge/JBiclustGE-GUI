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
import jbiclustge.methods.algorithms.java.bicat.opsm.OPSMMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class OPSMSettingsPanel.
 */
public class OPSMSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl number best models. */
	private JLabel lblNumberBestModels;
	
	/** The integer text fieldnbestmodels. */
	private IntegerTextField integerTextFieldnbestmodels;

	/**
	 * Create the panel.
	 */
	public OPSMSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberBestModels = new JLabel("Number best models:");
		this.lblNumberBestModels.setToolTipText("Recover the n best partial models for next iteration");
		GridBagConstraints gbc_lblNumberBestModels = new GridBagConstraints();
		gbc_lblNumberBestModels.anchor = GridBagConstraints.EAST;
		gbc_lblNumberBestModels.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberBestModels.gridx = 0;
		gbc_lblNumberBestModels.gridy = 0;
		add(this.lblNumberBestModels, gbc_lblNumberBestModels);
		
		this.integerTextFieldnbestmodels = new IntegerTextField();
		this.integerTextFieldnbestmodels.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnbestmodels = new GridBagConstraints();
		gbc_integerTextFieldnbestmodels.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnbestmodels.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnbestmodels.gridx = 1;
		gbc_integerTextFieldnbestmodels.gridy = 0;
		add(this.integerTextFieldnbestmodels, gbc_integerTextFieldnbestmodels);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnbestmodels.setText("10");
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

		String[] propkeys=new String[]{OPSMMethod.NUMBERBESTMODELS};
		String[] values=new String[]{"10"};
		String[] comments=new String[] {"Pick the n best partial models for next iteration"};
		return AlgorithmProperties.setupProperties(propkeys, values, comments);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnbestmodels.getText().isEmpty() || Integer.parseInt(integerTextFieldnbestmodels.getText())<1)
			return new Pair<Boolean, String>(false, "The minimum value for the n best partial models must be higher than 0");
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
		return new Dimension(450,150);
	}

}
