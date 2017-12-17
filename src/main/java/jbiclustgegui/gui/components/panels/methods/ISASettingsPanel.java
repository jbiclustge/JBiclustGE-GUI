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

import jbiclustge.datatools.expressiondata.transformdata.binarization.BinarizationDecision;
import jbiclustge.methods.algorithms.r.biclic.RBiclicMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RSpectralMethod;
import jbiclustge.methods.algorithms.r.components.BCSpectralNormalizationMethod;
import jbiclustge.methods.algorithms.r.isapackage.RIsaMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JComboBox;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ISASettingsPanel.
 */
public class ISASettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl row thresholds. */
	private JLabel lblRowThresholds;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl min genes. */
	private JLabel lblMinGenes;
	
	/** The lbl min genes 1. */
	private JLabel lblMinGenes_1;
	
	/** The lbl number of seeds. */
	private JLabel lblNumberOfSeeds;
	
	/** The integer text fieldnumberseeds. */
	private IntegerTextField integerTextFieldnumberseeds;
	
	/** The combo boxrowdirection. */
	private JComboBox comboBoxrowdirection;
	
	/** The combo boxcolumndirection. */
	private JComboBox comboBoxcolumndirection;
	
	/** The text fieldrowtrhesholds. */
	private JTextField textFieldrowtrhesholds;
	
	/** The text fieldcolumntresholds. */
	private JTextField textFieldcolumntresholds;

	/**
	 * Create the panel.
	 */
	public ISASettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberOfSeeds = new JLabel("Number of seeds:");
		this.lblNumberOfSeeds.setToolTipText("Integer scalar, the number of seeds to use");
		GridBagConstraints gbc_lblNumberOfSeeds = new GridBagConstraints();
		gbc_lblNumberOfSeeds.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfSeeds.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfSeeds.gridx = 0;
		gbc_lblNumberOfSeeds.gridy = 0;
		add(this.lblNumberOfSeeds, gbc_lblNumberOfSeeds);
		
		this.integerTextFieldnumberseeds = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldnumberseeds = new GridBagConstraints();
		gbc_integerTextFieldnumberseeds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberseeds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberseeds.gridx = 1;
		gbc_integerTextFieldnumberseeds.gridy = 0;
		add(this.integerTextFieldnumberseeds, gbc_integerTextFieldnumberseeds);
		
		this.lblMinGenes = new JLabel("Row direction scheme:");
		this.lblMinGenes.setToolTipText("It specifies whether we are interested in rows that are higher (up) than average, lower than average(down), or both (updown)");
		GridBagConstraints gbc_lblMinGenes = new GridBagConstraints();
		gbc_lblMinGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes.gridx = 0;
		gbc_lblMinGenes.gridy = 1;
		add(this.lblMinGenes, gbc_lblMinGenes);
		
		this.comboBoxrowdirection = new JComboBox();
		GridBagConstraints gbc_comboBoxrowdirection = new GridBagConstraints();
		gbc_comboBoxrowdirection.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxrowdirection.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxrowdirection.gridx = 1;
		gbc_comboBoxrowdirection.gridy = 1;
		add(this.comboBoxrowdirection, gbc_comboBoxrowdirection);
		
		this.lblMinGenes_1 = new JLabel("Column direction scheme:");
		this.lblMinGenes_1.setToolTipText("It specifies whether we are interested in columns that are higher (up) than average, lower than average(down), or both (updown)");
		GridBagConstraints gbc_lblMinGenes_1 = new GridBagConstraints();
		gbc_lblMinGenes_1.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes_1.insets = new Insets(0, 10, 5, 5);
		gbc_lblMinGenes_1.gridx = 0;
		gbc_lblMinGenes_1.gridy = 2;
		add(this.lblMinGenes_1, gbc_lblMinGenes_1);
		
		this.comboBoxcolumndirection = new JComboBox();
		GridBagConstraints gbc_comboBoxcolumndirection = new GridBagConstraints();
		gbc_comboBoxcolumndirection.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxcolumndirection.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxcolumndirection.gridx = 1;
		gbc_comboBoxcolumndirection.gridy = 2;
		add(this.comboBoxcolumndirection, gbc_comboBoxcolumndirection);
		
		this.lblRowThresholds = new JLabel("Row thresholds:");
		this.lblRowThresholds.setToolTipText("<html>Numeric vector, can be defined as following: <br>(1) numbers separated by semi-colon -> 1;1.5;3;4.5;6, <br> (2) defining a range (1:6) with interval 3 as follows -> 1:6:3</html>");
		GridBagConstraints gbc_lblRowThresholds = new GridBagConstraints();
		gbc_lblRowThresholds.anchor = GridBagConstraints.EAST;
		gbc_lblRowThresholds.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowThresholds.gridx = 0;
		gbc_lblRowThresholds.gridy = 3;
		add(this.lblRowThresholds, gbc_lblRowThresholds);
		
		this.textFieldrowtrhesholds = new JTextField();
		GridBagConstraints gbc_textFieldrowtrhesholds = new GridBagConstraints();
		gbc_textFieldrowtrhesholds.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldrowtrhesholds.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldrowtrhesholds.gridx = 1;
		gbc_textFieldrowtrhesholds.gridy = 3;
		add(this.textFieldrowtrhesholds, gbc_textFieldrowtrhesholds);
		this.textFieldrowtrhesholds.setColumns(10);
		
		this.lblOverlapTreshold = new JLabel("Column thresholds:");
		this.lblOverlapTreshold.setToolTipText("<html>Numeric vector, can be defined as following: <br>(1) numbers separated by semi-colon -> 1;1.5;3;4.5;6, <br> (2) defining a range (1:6) with interval 3 as follows -> 1:6:3</html>");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 0, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 4;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.textFieldcolumntresholds = new JTextField();
		GridBagConstraints gbc_textFieldcolumntresholds = new GridBagConstraints();
		gbc_textFieldcolumntresholds.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldcolumntresholds.gridx = 1;
		gbc_textFieldcolumntresholds.gridy = 4;
		add(this.textFieldcolumntresholds, gbc_textFieldcolumntresholds);
		this.textFieldcolumntresholds.setColumns(10);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFieldnumberseeds.setText("100");
		comboBoxrowdirection.setSelectedItem(BinarizationDecision.UPREGULATED);
		comboBoxcolumndirection.setSelectedItem(BinarizationDecision.UPREGULATED);
		textFieldrowtrhesholds.setText("");
		textFieldcolumntresholds.setText("");
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
		
		for (BinarizationDecision element : BinarizationDecision.values()) {
			comboBoxrowdirection.addItem(element);
			comboBoxcolumndirection.addItem(element);
		}
		
	}

	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				RIsaMethod.ISA_NUMBERSEEDS,
				RIsaMethod.ISA_ROWDIRECTION,
				RIsaMethod.ISA_COLUMNDIRECTION,
				RIsaMethod.ISA_ROWTHRESHOLDS,
				RIsaMethod.ISA_COLUMNTHRESHOLDS
		};
		
		String rowdir=comboBoxrowdirection.getSelectedItem().toString();
		String coldir=comboBoxcolumndirection.getSelectedItem().toString();
		
		String rowtresh="";
		if(!textFieldrowtrhesholds.getText().isEmpty())
			rowtresh=textFieldrowtrhesholds.getText();
		String coltresh="";
		if(!textFieldcolumntresholds.getText().isEmpty())
			coltresh=textFieldcolumntresholds.getText();
		
		String[] values=new String[]{integerTextFieldnumberseeds.getText(),
				rowdir,
				coldir,
				rowtresh,
				coltresh};
		
		
		String[] comments=new String[] {
				"Integer scalar, the number of seeds to use",
				"It specifies whether we are interested in rows that are higher (up) than average, lower than average(down), or both (updown)",
				"It specifies whether we are interested in columns that are higher (up) than average, lower than average(down), or both (updown)",
				"Numeric vector, can be defined as following: (1) numbers separated by semi-colon -> 1;1.5;3;4.5;6, (2) defining a range (1:6) with interval 3 as follows -> 1:6:3",
				"Numeric vector, can be defined as following: (1) numbers separated by semi-colon -> 1;1.5;3;4.5;6, (2) defining a range (1:6) with interval 3 as follows -> 1:6:3"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: isa2 manual, url: https://cran.r-project.org/web/packages/isa2/isa2.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberseeds.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberseeds.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of seeds must be higher than 0");
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
		return new Dimension(450,350);
	}

}
