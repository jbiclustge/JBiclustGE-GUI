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
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.r.components.FabiaCenteringMethod;
import jbiclustge.methods.algorithms.r.components.FabiaNormalizationMethod;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaMethod;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaSMethod;
import jbiclustge.methods.algorithms.wrappers.UnibicMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class FabiaSSettingsPanel.
 */
public class FabiaSSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The usetfsearch. */
	private static String USETFSEARCH="usetfsearch";
	
	/** The lbl number of biclusters. */
	private JLabel lblNumberOfBiclusters;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	/** The lbl alpha. */
	private JLabel lblAlpha;
	
	/** The spinneralpha. */
	private JSpinner spinneralpha;
	
	/** The lbl number of iterations. */
	private JLabel lblNumberOfIterations;
	
	/** The integer text fieldnumberiter. */
	private IntegerTextField integerTextFieldnumberiter;
	
	/** The lbl new label 1. */
	private JLabel lblNewLabel_1;
	
	/** The spinnersparfactors. */
	private JSpinner spinnersparfactors;
	
	/** The lbl nonnegative factors. */
	private JLabel lblNonnegativeFactors;
	
	/** The check boxnnfactors. */
	private JCheckBox checkBoxnnfactors;
	
	/** The lbl random initialization of. */
	private JLabel lblRandomInitializationOf;
	
	/** The double text fieldrandominitload. */
	private DoubleTextField doubleTextFieldrandominitload;
	
	/** The lbl data centering. */
	private JLabel lblDataCentering;
	
	/** The combo boxdatacenter. */
	private JComboBox comboBoxdatacenter;
	
	/** The lbl data normalization. */
	private JLabel lblDataNormalization;
	
	/** The combo boxdatanorm. */
	private JComboBox comboBoxdatanorm;
	
	/** The lbl min variational parameter. */
	private JLabel lblMinVariationalParameter;
	
	/** The double text fieldminvarparam. */
	private DoubleTextField doubleTextFieldminvarparam;
	
	/** The lblmax biclusters at. */
	private JLabel lblmaxBiclustersAt;
	
	/** The integer text fieldmaxbicinrow. */
	private IntegerTextField integerTextFieldmaxbicinrow;
	
	/** The lbl new label 2. */
	private JLabel lblNewLabel_2;
	
	/** The integer text fieldmaxrowbic. */
	private IntegerTextField integerTextFieldmaxrowbic;
	
	/** The lbl cycle at which. */
	private JLabel lblCycleAtWhich;
	
	/** The integer text fieldcyclenlil. */
	private IntegerTextField integerTextFieldcyclenlil;
	
	/** The lbl threshold for sample. */
	private JLabel lblThresholdForSample;
	
	/** The spinnerthreshsamplebelbic. */
	private JSpinner spinnerthreshsamplebelbic;
	
	/** The lbl threshold for loading. */
	private JLabel lblThresholdForLoading;
	
	/** The double text fieldthreshloadbelbic. */
	private DoubleTextField doubleTextFieldthreshloadbelbic;
	
	/** The panel extra. */
	private JPanel panelExtra;
	
	/**
	 * Create the panel.
	 */
	public FabiaSSettingsPanel() {
       super();
	}
	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNumberOfBiclusters = new JLabel("Number of biclusters:");
		this.lblNumberOfBiclusters.setToolTipText("Number of bicluster to be found");
		GridBagConstraints gbc_lblNumberOfBiclusters = new GridBagConstraints();
		gbc_lblNumberOfBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfBiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfBiclusters.gridx = 0;
		gbc_lblNumberOfBiclusters.gridy = 0;
		add(this.lblNumberOfBiclusters, gbc_lblNumberOfBiclusters);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		this.integerTextFieldnumberbics.setText("13");
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 0;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
		
		this.lblAlpha = new JLabel("alpha:");
		this.lblAlpha.setToolTipText("Sparseness loadings");
		GridBagConstraints gbc_lblAlpha = new GridBagConstraints();
		gbc_lblAlpha.anchor = GridBagConstraints.EAST;
		gbc_lblAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlpha.gridx = 0;
		gbc_lblAlpha.gridy = 1;
		add(this.lblAlpha, gbc_lblAlpha);
		
		this.spinneralpha = new JSpinner();
		this.spinneralpha.setModel(new SpinnerNumberModel(0.01, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinneralpha = new GridBagConstraints();
		gbc_spinneralpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneralpha.insets = new Insets(0, 0, 5, 0);
		gbc_spinneralpha.gridx = 1;
		gbc_spinneralpha.gridy = 1;
		add(this.spinneralpha, gbc_spinneralpha);
		
		this.lblNumberOfIterations = new JLabel("Number of iterations:");
		GridBagConstraints gbc_lblNumberOfIterations = new GridBagConstraints();
		gbc_lblNumberOfIterations.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfIterations.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfIterations.gridx = 0;
		gbc_lblNumberOfIterations.gridy = 2;
		add(this.lblNumberOfIterations, gbc_lblNumberOfIterations);
		
		this.integerTextFieldnumberiter = new IntegerTextField();
		this.integerTextFieldnumberiter.setText("500");
		GridBagConstraints gbc_integerTextFieldnumberiter = new GridBagConstraints();
		gbc_integerTextFieldnumberiter.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberiter.gridx = 1;
		gbc_integerTextFieldnumberiter.gridy = 2;
		add(this.integerTextFieldnumberiter, gbc_integerTextFieldnumberiter);
		
		this.lblNewLabel_1 = new JLabel("Sparseness factors:");
		this.lblNewLabel_1.setToolTipText("Sparseness factors (0.5 - 2.0)");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		add(this.lblNewLabel_1, gbc_lblNewLabel_1);
		
		this.spinnersparfactors = new JSpinner();
		this.spinnersparfactors.setModel(new SpinnerNumberModel(0.5, 0.5, 2.0, 0.01));
		GridBagConstraints gbc_spinnersparfactors = new GridBagConstraints();
		gbc_spinnersparfactors.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnersparfactors.insets = new Insets(0, 0, 5, 0);
		gbc_spinnersparfactors.gridx = 1;
		gbc_spinnersparfactors.gridy = 3;
		add(this.spinnersparfactors, gbc_spinnersparfactors);
		
		this.lblNonnegativeFactors = new JLabel("Non-negative factors:");
		this.lblNonnegativeFactors.setToolTipText("Non-negative factors and loadings if non_negative > 0");
		GridBagConstraints gbc_lblNonnegativeFactors = new GridBagConstraints();
		gbc_lblNonnegativeFactors.anchor = GridBagConstraints.EAST;
		gbc_lblNonnegativeFactors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNonnegativeFactors.gridx = 0;
		gbc_lblNonnegativeFactors.gridy = 4;
		add(this.lblNonnegativeFactors, gbc_lblNonnegativeFactors);
		
		this.checkBoxnnfactors = new JCheckBox("");
		GridBagConstraints gbc_checkBoxnnfactors = new GridBagConstraints();
		gbc_checkBoxnnfactors.anchor = GridBagConstraints.WEST;
		gbc_checkBoxnnfactors.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxnnfactors.gridx = 1;
		gbc_checkBoxnnfactors.gridy = 4;
		add(this.checkBoxnnfactors, gbc_checkBoxnnfactors);
		
		this.lblRandomInitializationOf = new JLabel("Random initialization of loadings:");
		this.lblRandomInitializationOf.setToolTipText("Random initialization of loadings in [-random,random] if >0 or <=0 SVD");
		GridBagConstraints gbc_lblRandomInitializationOf = new GridBagConstraints();
		gbc_lblRandomInitializationOf.anchor = GridBagConstraints.EAST;
		gbc_lblRandomInitializationOf.insets = new Insets(0, 20, 5, 5);
		gbc_lblRandomInitializationOf.gridx = 0;
		gbc_lblRandomInitializationOf.gridy = 5;
		add(this.lblRandomInitializationOf, gbc_lblRandomInitializationOf);
		
		this.doubleTextFieldrandominitload = new DoubleTextField();
		this.doubleTextFieldrandominitload.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldrandominitload = new GridBagConstraints();
		gbc_doubleTextFieldrandominitload.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldrandominitload.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldrandominitload.gridx = 1;
		gbc_doubleTextFieldrandominitload.gridy = 5;
		add(this.doubleTextFieldrandominitload, gbc_doubleTextFieldrandominitload);
		
		this.lblDataCentering = new JLabel("Data centering:");
		this.lblDataCentering.setToolTipText("Data centering: 1 (mean), 2 (median), 3 (mode), 0 (none)");
		GridBagConstraints gbc_lblDataCentering = new GridBagConstraints();
		gbc_lblDataCentering.anchor = GridBagConstraints.EAST;
		gbc_lblDataCentering.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataCentering.gridx = 0;
		gbc_lblDataCentering.gridy = 6;
		add(this.lblDataCentering, gbc_lblDataCentering);
		
		this.comboBoxdatacenter = new JComboBox();
		GridBagConstraints gbc_comboBoxdatacenter = new GridBagConstraints();
		gbc_comboBoxdatacenter.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxdatacenter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxdatacenter.gridx = 1;
		gbc_comboBoxdatacenter.gridy = 6;
		add(this.comboBoxdatacenter, gbc_comboBoxdatacenter);
		
		this.lblDataNormalization = new JLabel("Data normalization:");
		this.lblDataNormalization.setToolTipText("Data normalization: 1 (0.75-0.25 quantile), 2 (var=1), 0 (none)");
		GridBagConstraints gbc_lblDataNormalization = new GridBagConstraints();
		gbc_lblDataNormalization.anchor = GridBagConstraints.EAST;
		gbc_lblDataNormalization.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataNormalization.gridx = 0;
		gbc_lblDataNormalization.gridy = 7;
		add(this.lblDataNormalization, gbc_lblDataNormalization);
		
		this.comboBoxdatanorm = new JComboBox();
		GridBagConstraints gbc_comboBoxdatanorm = new GridBagConstraints();
		gbc_comboBoxdatanorm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxdatanorm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxdatanorm.gridx = 1;
		gbc_comboBoxdatanorm.gridy = 7;
		add(this.comboBoxdatanorm, gbc_comboBoxdatanorm);
		
		this.lblMinVariationalParameter = new JLabel("Min. variational parameter:");
		this.lblMinVariationalParameter.setToolTipText("Minimal value of the variational parameter");
		GridBagConstraints gbc_lblMinVariationalParameter = new GridBagConstraints();
		gbc_lblMinVariationalParameter.anchor = GridBagConstraints.EAST;
		gbc_lblMinVariationalParameter.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinVariationalParameter.gridx = 0;
		gbc_lblMinVariationalParameter.gridy = 8;
		add(this.lblMinVariationalParameter, gbc_lblMinVariationalParameter);
		
		this.doubleTextFieldminvarparam = new DoubleTextField();
		this.doubleTextFieldminvarparam.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldminvarparam = new GridBagConstraints();
		gbc_doubleTextFieldminvarparam.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldminvarparam.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldminvarparam.gridx = 1;
		gbc_doubleTextFieldminvarparam.gridy = 8;
		add(this.doubleTextFieldminvarparam, gbc_doubleTextFieldminvarparam);
		
		this.lblmaxBiclustersAt = new JLabel("<html>Max. biclusters at which a<br>row element can participate:</html>");
		this.lblmaxBiclustersAt.setToolTipText("Maximal number of biclusters at which a row element can participate");
		GridBagConstraints gbc_lblmaxBiclustersAt = new GridBagConstraints();
		gbc_lblmaxBiclustersAt.anchor = GridBagConstraints.EAST;
		gbc_lblmaxBiclustersAt.insets = new Insets(0, 0, 5, 5);
		gbc_lblmaxBiclustersAt.gridx = 0;
		gbc_lblmaxBiclustersAt.gridy = 9;
		add(this.lblmaxBiclustersAt, gbc_lblmaxBiclustersAt);
		
		this.integerTextFieldmaxbicinrow = new IntegerTextField();
		this.integerTextFieldmaxbicinrow.setText("0");
		GridBagConstraints gbc_integerTextFieldmaxbicinrow = new GridBagConstraints();
		gbc_integerTextFieldmaxbicinrow.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmaxbicinrow.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxbicinrow.gridx = 1;
		gbc_integerTextFieldmaxbicinrow.gridy = 9;
		add(this.integerTextFieldmaxbicinrow, gbc_integerTextFieldmaxbicinrow);
		
		this.lblNewLabel_2 = new JLabel("<html>Maximal number of <br>row elements per bicluster:</html>");
		this.lblNewLabel_2.setToolTipText("Maximal number of row elements per bicluster");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 10;
		add(this.lblNewLabel_2, gbc_lblNewLabel_2);
		
		this.integerTextFieldmaxrowbic = new IntegerTextField();
		this.integerTextFieldmaxrowbic.setText("0");
		GridBagConstraints gbc_integerTextFieldmaxrowbic = new GridBagConstraints();
		gbc_integerTextFieldmaxrowbic.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmaxrowbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxrowbic.gridx = 1;
		gbc_integerTextFieldmaxrowbic.gridy = 10;
		add(this.integerTextFieldmaxrowbic, gbc_integerTextFieldmaxrowbic);
		
		this.lblCycleAtWhich = new JLabel("<html>Cycle at which the<br>nL or lL maximum starts:</html>");
		GridBagConstraints gbc_lblCycleAtWhich = new GridBagConstraints();
		gbc_lblCycleAtWhich.anchor = GridBagConstraints.EAST;
		gbc_lblCycleAtWhich.insets = new Insets(0, 0, 5, 5);
		gbc_lblCycleAtWhich.gridx = 0;
		gbc_lblCycleAtWhich.gridy = 11;
		add(this.lblCycleAtWhich, gbc_lblCycleAtWhich);
		
		this.integerTextFieldcyclenlil = new IntegerTextField();
		this.integerTextFieldcyclenlil.setText("0");
		GridBagConstraints gbc_integerTextFieldcyclenlil = new GridBagConstraints();
		gbc_integerTextFieldcyclenlil.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldcyclenlil.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldcyclenlil.gridx = 1;
		gbc_integerTextFieldcyclenlil.gridy = 11;
		add(this.integerTextFieldcyclenlil, gbc_integerTextFieldcyclenlil);
		
		this.lblThresholdForSample = new JLabel("<html>Threshold for sample<br>belonging to bicluster:</html>");
		GridBagConstraints gbc_lblThresholdForSample = new GridBagConstraints();
		gbc_lblThresholdForSample.anchor = GridBagConstraints.EAST;
		gbc_lblThresholdForSample.insets = new Insets(0, 0, 5, 5);
		gbc_lblThresholdForSample.gridx = 0;
		gbc_lblThresholdForSample.gridy = 12;
		add(this.lblThresholdForSample, gbc_lblThresholdForSample);
		
		this.spinnerthreshsamplebelbic = new JSpinner();
		this.spinnerthreshsamplebelbic.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerthreshsamplebelbic = new GridBagConstraints();
		gbc_spinnerthreshsamplebelbic.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerthreshsamplebelbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerthreshsamplebelbic.gridx = 1;
		gbc_spinnerthreshsamplebelbic.gridy = 12;
		add(this.spinnerthreshsamplebelbic, gbc_spinnerthreshsamplebelbic);
		
		this.lblThresholdForLoading = new JLabel("<html>Threshold for loading<br>belonging to bicluster:</html>");
		this.lblThresholdForLoading.setToolTipText("Threshold for loading belonging to bicluster (if not given it is estimated)");
		GridBagConstraints gbc_lblThresholdForLoading = new GridBagConstraints();
		gbc_lblThresholdForLoading.anchor = GridBagConstraints.EAST;
		gbc_lblThresholdForLoading.insets = new Insets(0, 0, 5, 5);
		gbc_lblThresholdForLoading.gridx = 0;
		gbc_lblThresholdForLoading.gridy = 13;
		add(this.lblThresholdForLoading, gbc_lblThresholdForLoading);
		
		this.panelExtra = getFabiaVariantPanel();
		
		this.doubleTextFieldthreshloadbelbic = new DoubleTextField();
		GridBagConstraints gbc_doubleTextFieldthreshloadbelbic = new GridBagConstraints();
		gbc_doubleTextFieldthreshloadbelbic.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldthreshloadbelbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldthreshloadbelbic.gridx = 1;
		gbc_doubleTextFieldthreshloadbelbic.gridy = 13;
		add(this.doubleTextFieldthreshloadbelbic, gbc_doubleTextFieldthreshloadbelbic);
		if(panelExtra!=null) {
			GridBagConstraints gbc_panelExtra = new GridBagConstraints();
			gbc_panelExtra.gridheight = 2;
			gbc_panelExtra.gridwidth = 2;
			gbc_panelExtra.insets = new Insets(0, 0, 5, 5);
			gbc_panelExtra.fill = GridBagConstraints.BOTH;
			gbc_panelExtra.gridx = 0;
			gbc_panelExtra.gridy = 16;
			add(this.panelExtra, gbc_panelExtra);
		}
		
		
	}

	
	/**
	 * Gets the fabia variant panel.
	 *
	 * @return the fabia variant panel
	 */
	protected JPanel getFabiaVariantPanel() {
		return null;
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		
		integerTextFieldnumberbics.setText("13");
		spinneralpha.setValue(0.01);
		integerTextFieldnumberiter.setText("500");
		spinnersparfactors.setValue(0.5);
		checkBoxnnfactors.setSelected(false);
		doubleTextFieldrandominitload.setText("1.0");
		comboBoxdatacenter.setSelectedIndex(2);
		comboBoxdatanorm.setSelectedIndex(1);
		doubleTextFieldminvarparam.setText("1.0");
		integerTextFieldmaxbicinrow.setText("0");
		integerTextFieldmaxrowbic.setText("0");
		integerTextFieldcyclenlil.setText("0");
		spinnerthreshsamplebelbic.setValue(0.5);
		doubleTextFieldthreshloadbelbic.setText("");
		
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
		
		for (FabiaCenteringMethod element : FabiaCenteringMethod.values()) {
			comboBoxdatacenter.addItem(element.getName());
		}
		
		for (FabiaNormalizationMethod element : FabiaNormalizationMethod.values()) {
			comboBoxdatanorm.addItem(element.getName());
		}
		
	}
	

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#setCurrentProject(jbiclustgegui.datatypes.project.Project)
	 */
	@Override
	public void setCurrentProject(Project proj) {
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {
		
		String[] propkeys=new String[]{
				RFabiaSMethod.FABIA_NCLUSTERS,
				RFabiaSMethod.FABIA_ALPHA,
				RFabiaSMethod.FABIA_CYC,
				RFabiaSMethod.FABIA_SPZ,
				RFabiaSMethod.FABIA_NNEG,
				RFabiaSMethod.FABIA_RANDOM,
				RFabiaSMethod.FABIA_CENTER,
				RFabiaSMethod.FABIA_NORM,
				RFabiaSMethod.FABIA_LAP,
				RFabiaSMethod.FABIA_NL,
				RFabiaSMethod.FABIA_LL,
				RFabiaSMethod.FABIA_BL,
				RFabiaSMethod.FABIA_THRZ,
				RFabiaSMethod.FABIA_THRL
		};
		
		String thrl="";
		if(!doubleTextFieldthreshloadbelbic.getText().isEmpty())
			thrl=doubleTextFieldthreshloadbelbic.getText();
		
		String[] values=new String[]{integerTextFieldnumberbics.getText(),
				String.valueOf(spinneralpha.getValue()),
				integerTextFieldnumberiter.getText(),
				String.valueOf(spinnersparfactors.getValue()),
				String.valueOf(checkBoxnnfactors.isSelected()),
				doubleTextFieldrandominitload.getText(),
				String.valueOf(comboBoxdatacenter.getSelectedIndex()),
				String.valueOf(comboBoxdatanorm.getSelectedIndex()),
				doubleTextFieldminvarparam.getText(),
				integerTextFieldmaxbicinrow.getText(),
				integerTextFieldmaxrowbic.getText(),
				integerTextFieldcyclenlil.getText(),
				String.valueOf(spinnerthreshsamplebelbic.getValue()),
				thrl};
		
		
		String[] comments=new String[] {
				"Number of bicluster to be found",
				"Sparseness loadings",
				"Number of iterations",
				"Sparseness factors (0.5 - 2.0)",
				"Non-negative factors and loadings if non_negative > 0",
				"Random initialization of loadings in [-random,random] if >0 or <=0 SVD",
				"Data centering: 1 (mean), 2 (median), 3 (mode), 0 (none)",
				"Data normalization: 1 (0.75-0.25 quantile), 2 (var=1), 0 (none)",
				"Minimal value of the variational parameter",
				"Maximal number of biclusters at which a row element can participate",
				"Maximal number of row elements per bicluster",
				"Cycle at which the nL or lL maximum starts",
				"Threshold for sample belonging to bicluster",
				"Threshold for loading belonging to bicluster (if not given it is estimated)"
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: fabia manual, url: http://www.bioconductor.org/packages/devel/bioc/vignettes/fabia/inst/doc/fabia.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		
		if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of biclusters must be higher than 0");
		else if(integerTextFieldnumberiter.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberiter.getText())<1)
			return new Pair<Boolean, String>(false, "Value for the number of iterations must be higher than 0");
		else if(doubleTextFieldrandominitload.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Random initialization of loadings must have a value");
		else if(doubleTextFieldrandominitload.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Scale must have a value");
		else if(doubleTextFieldminvarparam.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Min. variational parameter must have a value");
		else if(integerTextFieldmaxbicinrow.getText().isEmpty())
			return new Pair<Boolean, String>(false, "\"Max. biclusters at which a row element can participate\" must have a value");
		else if(integerTextFieldmaxrowbic.getText().isEmpty())
			return new Pair<Boolean, String>(false, "\"Maximal number of row elements per bicluster\" must have a value");
		else if(integerTextFieldcyclenlil.getText().isEmpty())
			return new Pair<Boolean, String>(false, "\"Cycle at which the nL or lL maximum starts\" must have a value");
		else
			  return new Pair<Boolean, String>(true, null);
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(600,700);
	}


	

}
