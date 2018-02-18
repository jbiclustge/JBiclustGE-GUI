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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaPMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class FabiaPSettingsPanel.
 */
public class FabiaPSettingsPanel extends FabiaSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The lbl final sparseness loadings. */
	private JLabel lblFinalSparsenessLoadings;
	
	/** The double text fieldfinalsparload. */
	private DoubleTextField doubleTextFieldfinalsparload;
	
	/** The lbl final sparseness factors. */
	private JLabel lblFinalSparsenessFactors;
	
	/** The double text fieldfinalsparcfactors. */
	private DoubleTextField doubleTextFieldfinalsparcfactors;
	
	
	/**
	 * Create the panel.
	 */
	public FabiaPSettingsPanel() {
       super();
       
	}
	
    /* (non-Javadoc)
     * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#getFabiaVariantPanel()
     */
    @Override
	protected JPanel getFabiaVariantPanel() {
    	
    	JPanel extrapanel=new JPanel();
    	extrapanel.setBorder(new TitledBorder(null, "FabiaP specific settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0};
		extrapanel.setLayout(gridBagLayout);
		
		this.lblFinalSparsenessLoadings = new JLabel("Final sparseness loadings:");
		GridBagConstraints gbc_lblFinalSparsenessLoadings = new GridBagConstraints();
		gbc_lblFinalSparsenessLoadings.anchor = GridBagConstraints.EAST;
		gbc_lblFinalSparsenessLoadings.insets = new Insets(0, 20, 5, 5);
		gbc_lblFinalSparsenessLoadings.gridx = 0;
		gbc_lblFinalSparsenessLoadings.gridy = 0;
		extrapanel.add(this.lblFinalSparsenessLoadings, gbc_lblFinalSparsenessLoadings);
		
		this.doubleTextFieldfinalsparload = new DoubleTextField();
		doubleTextFieldfinalsparload.setToolTipText("Final sparseness loadings");
		GridBagConstraints gbc_doubleTextFieldfinalsparload = new GridBagConstraints();
		gbc_doubleTextFieldfinalsparload.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldfinalsparload.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldfinalsparload.gridx = 1;
		gbc_doubleTextFieldfinalsparload.gridy = 0;
		extrapanel.add(this.doubleTextFieldfinalsparload, gbc_doubleTextFieldfinalsparload);
		
		this.lblFinalSparsenessFactors = new JLabel("Final sparseness factors:");
		GridBagConstraints gbc_lblFinalSparsenessFactors = new GridBagConstraints();
		gbc_lblFinalSparsenessFactors.anchor = GridBagConstraints.EAST;
		gbc_lblFinalSparsenessFactors.insets = new Insets(0, 0, 0, 5);
		gbc_lblFinalSparsenessFactors.gridx = 0;
		gbc_lblFinalSparsenessFactors.gridy = 1;
		extrapanel.add(this.lblFinalSparsenessFactors, gbc_lblFinalSparsenessFactors);
		
		this.doubleTextFieldfinalsparcfactors = new DoubleTextField();
		doubleTextFieldfinalsparcfactors.setToolTipText("Final sparseness factors");
		GridBagConstraints gbc_doubleTextFieldfinalsparcfactors = new GridBagConstraints();
		gbc_doubleTextFieldfinalsparcfactors.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldfinalsparcfactors.gridx = 1;
		gbc_doubleTextFieldfinalsparcfactors.gridy = 1;
		extrapanel.add(this.doubleTextFieldfinalsparcfactors, gbc_doubleTextFieldfinalsparcfactors);

		return extrapanel;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		super.resetToDefaultValues();
		doubleTextFieldfinalsparload.setText("0.6");
		doubleTextFieldfinalsparcfactors.setText("0.6");
		
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#needInitComponents()
	 */
	@Override
	protected boolean needInitComponents() {
		return true;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		super.initComponents();
	}
	

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#setCurrentProject(jbiclustgegui.datatypes.project.Project)
	 */
	@Override
	public void setCurrentProject(Project proj) {
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {
		AlgorithmProperties props=(AlgorithmProperties) super.getMethodSettings();
		props.addPropertyKey(RFabiaPMethod.FABIA_SL, doubleTextFieldfinalsparload.getText(),"final sparseness loadings");
		props.addPropertyKey(RFabiaPMethod.FABIA_SZ, doubleTextFieldfinalsparcfactors.getText(),"final sparseness factors");
		return props;
		
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		Pair<Boolean, String> isvalidsettings=super.validSettings();
		if(!isvalidsettings.getValue0())
			return isvalidsettings;
		else if(doubleTextFieldfinalsparload.getText().isEmpty())
			return new Pair<Boolean, String>(false, "\"Final sparseness loadings\" must have a value");
		else if(doubleTextFieldfinalsparcfactors.getText().isEmpty())
			return new Pair<Boolean, String>(false, "\"Final sparseness factors\" must have a value");
		else
			  return new Pair<Boolean, String>(true, null);
		
	}



	

}
