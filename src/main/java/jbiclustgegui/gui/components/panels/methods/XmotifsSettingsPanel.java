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
import jbiclustge.methods.algorithms.r.biclustpackage.RXMOTIFSMethod;
import jbiclustge.methods.algorithms.r.components.BCSpectralNormalizationMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JComboBox;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class XmotifsSettingsPanel.
 */
public class XmotifsSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl correlation threshold. */
	private JLabel lblCorrelationThreshold;
	
	/** The lbl number of columns. */
	private JLabel lblNumberOfColumns;
	
	/** The integer text fieldnumbercolumns. */
	private IntegerTextField integerTextFieldnumbercolumns;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl min genes. */
	private JLabel lblMinGenes;
	
	/** The lbl min genes 1. */
	private JLabel lblMinGenes_1;
	
	/** The integer text fielddisclevels. */
	private IntegerTextField integerTextFielddisclevels;
	
	/** The check boxusequantilediscret. */
	private JCheckBox checkBoxusequantilediscret;
	
	/** The check boxusebimaxdiscret. */
	private JCheckBox checkBoxusebimaxdiscret;
	
	/** The integer text fieldsamplesizerep. */
	private IntegerTextField integerTextFieldsamplesizerep;
	
	/** The lbl alpha. */
	private JLabel lblAlpha;
	
	/** The double text fieldalpha. */
	private DoubleTextField doubleTextFieldalpha;
	
	/** The lbl number of biclusters. */
	private JLabel lblNumberOfBiclusters;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The integer text fieldnumberrep. */
	private IntegerTextField integerTextFieldnumberrep;

	/**
	 * Create the panel.
	 */
	public XmotifsSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblCorrelationThreshold = new JLabel("Discretize levels:");
		this.lblCorrelationThreshold.setToolTipText("Discretize levels (default=10)");
		GridBagConstraints gbc_lblCorrelationThreshold = new GridBagConstraints();
		gbc_lblCorrelationThreshold.anchor = GridBagConstraints.EAST;
		gbc_lblCorrelationThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrelationThreshold.gridx = 0;
		gbc_lblCorrelationThreshold.gridy = 0;
		add(this.lblCorrelationThreshold, gbc_lblCorrelationThreshold);
		
		this.integerTextFielddisclevels = new IntegerTextField();
		GridBagConstraints gbc_integerTextFielddisclevels = new GridBagConstraints();
		gbc_integerTextFielddisclevels.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFielddisclevels.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFielddisclevels.gridx = 1;
		gbc_integerTextFielddisclevels.gridy = 0;
		add(this.integerTextFielddisclevels, gbc_integerTextFielddisclevels);
		
		this.lblMinGenes = new JLabel("Use quantiles in discretization:");
		this.lblMinGenes.setToolTipText("Use quantiles in discretization process, otherwise uses equally spaced levels");
		GridBagConstraints gbc_lblMinGenes = new GridBagConstraints();
		gbc_lblMinGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes.gridx = 0;
		gbc_lblMinGenes.gridy = 1;
		add(this.lblMinGenes, gbc_lblMinGenes);
		
		this.checkBoxusequantilediscret = new JCheckBox("");
		GridBagConstraints gbc_checkBoxusequantilediscret = new GridBagConstraints();
		gbc_checkBoxusequantilediscret.anchor = GridBagConstraints.WEST;
		gbc_checkBoxusequantilediscret.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxusequantilediscret.gridx = 1;
		gbc_checkBoxusequantilediscret.gridy = 1;
		add(this.checkBoxusequantilediscret, gbc_checkBoxusequantilediscret);
		
		this.lblMinGenes_1 = new JLabel("Use BiMax discretization method:");
		this.lblMinGenes_1.setToolTipText("Use bimax discretization method, instead of discretize levels");
		GridBagConstraints gbc_lblMinGenes_1 = new GridBagConstraints();
		gbc_lblMinGenes_1.anchor = GridBagConstraints.EAST;
		gbc_lblMinGenes_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGenes_1.gridx = 0;
		gbc_lblMinGenes_1.gridy = 2;
		add(this.lblMinGenes_1, gbc_lblMinGenes_1);
		
		this.checkBoxusebimaxdiscret = new JCheckBox("");
		GridBagConstraints gbc_checkBoxusebimaxdiscret = new GridBagConstraints();
		gbc_checkBoxusebimaxdiscret.anchor = GridBagConstraints.WEST;
		gbc_checkBoxusebimaxdiscret.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxusebimaxdiscret.gridx = 1;
		gbc_checkBoxusebimaxdiscret.gridy = 2;
		add(this.checkBoxusebimaxdiscret, gbc_checkBoxusebimaxdiscret);
		
		this.lblNumberOfColumns = new JLabel("Number of columns to be chosen:");
		this.lblNumberOfColumns.setToolTipText("Number of columns to be chosen (ns)");
		GridBagConstraints gbc_lblNumberOfColumns = new GridBagConstraints();
		gbc_lblNumberOfColumns.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfColumns.gridx = 0;
		gbc_lblNumberOfColumns.gridy = 3;
		add(this.lblNumberOfColumns, gbc_lblNumberOfColumns);
		
		this.integerTextFieldnumbercolumns = new IntegerTextField();
		this.integerTextFieldnumbercolumns.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumbercolumns = new GridBagConstraints();
		gbc_integerTextFieldnumbercolumns.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumbercolumns.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumbercolumns.gridx = 1;
		gbc_integerTextFieldnumbercolumns.gridy = 3;
		add(this.integerTextFieldnumbercolumns, gbc_integerTextFieldnumbercolumns);
		
		this.lblNewLabel = new JLabel("Number of repetitions:");
		this.lblNewLabel.setToolTipText("Number of repetitions (nd)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(this.lblNewLabel, gbc_lblNewLabel);
		
		this.integerTextFieldnumberrep = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldnumberrep = new GridBagConstraints();
		gbc_integerTextFieldnumberrep.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberrep.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberrep.gridx = 1;
		gbc_integerTextFieldnumberrep.gridy = 4;
		add(this.integerTextFieldnumberrep, gbc_integerTextFieldnumberrep);
		
		this.lblOverlapTreshold = new JLabel("Sample size in repetitions:");
		this.lblOverlapTreshold.setToolTipText("Sample size in repetitions (sd)");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 5;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.integerTextFieldsamplesizerep = new IntegerTextField();
		this.integerTextFieldsamplesizerep.setColumns(10);
		GridBagConstraints gbc_integerTextFieldsamplesizerep = new GridBagConstraints();
		gbc_integerTextFieldsamplesizerep.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldsamplesizerep.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldsamplesizerep.gridx = 1;
		gbc_integerTextFieldsamplesizerep.gridy = 5;
		add(this.integerTextFieldsamplesizerep, gbc_integerTextFieldsamplesizerep);
		
		this.lblAlpha = new JLabel("alpha:");
		this.lblAlpha.setToolTipText("Scaling factor for column result (alpha)");
		GridBagConstraints gbc_lblAlpha = new GridBagConstraints();
		gbc_lblAlpha.anchor = GridBagConstraints.EAST;
		gbc_lblAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlpha.gridx = 0;
		gbc_lblAlpha.gridy = 6;
		add(this.lblAlpha, gbc_lblAlpha);
		
		this.doubleTextFieldalpha = new DoubleTextField();
		GridBagConstraints gbc_doubleTextFieldalpha = new GridBagConstraints();
		gbc_doubleTextFieldalpha.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldalpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldalpha.gridx = 1;
		gbc_doubleTextFieldalpha.gridy = 6;
		add(this.doubleTextFieldalpha, gbc_doubleTextFieldalpha);
		
		this.lblNumberOfBiclusters = new JLabel("Number of biclusters:");
		this.lblNumberOfBiclusters.setToolTipText("Number of biclusters to be found");
		GridBagConstraints gbc_lblNumberOfBiclusters = new GridBagConstraints();
		gbc_lblNumberOfBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfBiclusters.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfBiclusters.gridx = 0;
		gbc_lblNumberOfBiclusters.gridy = 7;
		add(this.lblNumberOfBiclusters, gbc_lblNumberOfBiclusters);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 7;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		integerTextFielddisclevels.setText("10");
		checkBoxusequantilediscret.setSelected(false);
		checkBoxusebimaxdiscret.setSelected(false);
		integerTextFieldnumbercolumns.setText("10");
		integerTextFieldnumberrep.setText("10");
		integerTextFieldsamplesizerep.setText("5");
		doubleTextFieldalpha.setText("0.05");
		integerTextFieldnumberbics.setText("100");
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
		
	}

	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {


		String[] propkeys=new String[]{
				RXMOTIFSMethod.XMOTIFS_DISCRETIZE_LEVEL,
				RXMOTIFSMethod.XMOTIFS_DISCRETIZE_WITH_QUANTILES,
				RXMOTIFSMethod.XMOTIFS_DISCRETIZE_BIMAX_METHOD,
				RXMOTIFSMethod.XMOTIFS_NS,
				RXMOTIFSMethod.XMOTIFS_ND,
				RXMOTIFSMethod.XMOTIFS_SD,
				RXMOTIFSMethod.XMOTIFS_ALPHA,
				RXMOTIFSMethod.XMOTIFS_NCLUSTERS
		};
		String[] values=new String[]{integerTextFielddisclevels.getText(),
				String.valueOf(checkBoxusequantilediscret.isSelected()),
				String.valueOf(checkBoxusebimaxdiscret.isSelected()),
				integerTextFieldnumbercolumns.getText(),
				integerTextFieldnumberrep.getText(),
				integerTextFieldsamplesizerep.getText(),
				doubleTextFieldalpha.getText(),
				integerTextFieldnumberbics.getText()};
		
		String[] comments=new String[] {
				"Discretize levels (default=10)",
				"Use quantiles in discretization process, otherwise uses equally spaced levels",
				"Use bimax discretization method, instead of discretize levels",
				"Number of columns to be chosen (ns)",
				"Number of repetitions (nd)",
				"Sample size in repetitions (sd)",
				"Scaling factor for column result (alpha)",
				"Number of biclusters to be found"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: biclust manual, url: https://cran.r-project.org/web/packages/biclust/biclust.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFielddisclevels.getText().isEmpty() || Integer.parseInt(integerTextFielddisclevels.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the eigen discretize levels must be higher than 0");
		else if(integerTextFieldnumbercolumns.getText().isEmpty() || Integer.parseInt(integerTextFieldnumbercolumns.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of columns to be chosen must be higher than 0");
		else if(integerTextFieldnumberrep.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberrep.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of repetitions must be higher than 0");
		else if(integerTextFieldsamplesizerep.getText().isEmpty() || Integer.parseInt(integerTextFieldsamplesizerep.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the sample size in repetitions must be higher than 0");
		else if(doubleTextFieldalpha.getText().isEmpty() || Double.parseDouble(doubleTextFieldalpha.getText())<0)
			return new Pair<Boolean, String>(false, "The value for alpha must be 0 or higher");
		else if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of biclusters must be higher than 0");
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
		return new Dimension(550,500);
	}

}
