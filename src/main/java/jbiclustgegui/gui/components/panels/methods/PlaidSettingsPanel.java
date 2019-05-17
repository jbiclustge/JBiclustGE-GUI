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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.r.biclustpackage.RPlaidMethod;
import jbiclustge.methods.algorithms.wrappers.UnibicMethod;
import jbiclustge.utils.props.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaidSettingsPanel.
 */
public class PlaidSettingsPanel extends AbstractMethodSettingsPanel implements ActionListener{
	
	/**
	 * The Enum PlaidClusterModel.
	 */
	enum PlaidClusterModel{
		
		/** The both. */
		both,
		
		/** The rows. */
		rows,
		
		/** The columns. */
		columns;
		
	}
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl cluster mode. */
	private JLabel lblClusterMode;
	
	/** The lbl fit model. */
	private JLabel lblFitModel;
	
	/** The lbl consider background. */
	private JLabel lblConsiderBackground;
	
	/** The lbl background layer file. */
	private JLabel lblBackgroundLayerFile;
	
	/** The lbl degrees of freedom. */
	private JLabel lblDegreesOfFreedom;
	
	/** The lbl shuffle. */
	private JLabel lblShuffle;
	
	/** The combo boxclustermode. */
	private JComboBox comboBoxclustermode;
	
	/** The text fieldfitmodel. */
	private JTextField textFieldfitmodel;
	
	/** The check boxusebackground. */
	private JCheckBox checkBoxusebackground;
	
	/** The panel. */
	private JPanel panel;
	
	/** The btn openbackgroundfile. */
	private JButton btnOpenbackgroundfile;
	
	/** The text fieldbackgroudurl. */
	private JTextField textFieldbackgroudurl;
	
	/** The double text fielddegreesbackground. */
	private DoubleTextField doubleTextFielddegreesbackground;
	
	/** The integer text fieldshuffle. */
	private IntegerTextField integerTextFieldshuffle;
	
	/** The lbl number startup iterations. */
	private JLabel lblNumberStartupIterations;
	
	/** The integer text fieldstartupiter. */
	private IntegerTextField integerTextFieldstartupiter;
	
	/** The lbl number iterations to. */
	private JLabel lblNumberIterationsTo;
	
	/** The integer text fielditerationseachlayer. */
	private IntegerTextField integerTextFielditerationseachlayer;
	
	/** The lbl additional iterations for. */
	private JLabel lblAdditionalIterationsFor;
	
	/** The integer text fieldadditionaliter. */
	private IntegerTextField integerTextFieldadditionaliter;
	
	/** The lbl treshold to prune. */
	private JLabel lblTresholdToPrune;
	
	/** The spinnerprunegenes. */
	private JSpinner spinnerprunegenes;
	
	/** The lbl treshold to prune 1. */
	private JLabel lblTresholdToPrune_1;
	
	/** The spinnerpruneconds. */
	private JSpinner spinnerpruneconds;
	
	/** The lbl max number of. */
	private JLabel lblMaxNumberOf;
	
	/** The integer text fieldnumberbics. */
	private IntegerTextField integerTextFieldnumberbics;
	
	
	
	/** The openfile. */
	private static String OPENFILE="openfile";
	/**
	 * Create the panel.
	 */
	public PlaidSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		lblClusterMode = new JLabel("Cluster mode:");
		this.lblClusterMode.setToolTipText("cluster rows, columns or both");
		GridBagConstraints gbc_lblClusterMode = new GridBagConstraints();
		gbc_lblClusterMode.anchor = GridBagConstraints.EAST;
		gbc_lblClusterMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblClusterMode.gridx = 0;
		gbc_lblClusterMode.gridy = 1;
		add(lblClusterMode, gbc_lblClusterMode);
		
		this.comboBoxclustermode = new JComboBox();
		comboBoxclustermode.setToolTipText("cluster rows, columns or both");
		GridBagConstraints gbc_comboBoxclustermode = new GridBagConstraints();
		gbc_comboBoxclustermode.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxclustermode.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxclustermode.gridx = 1;
		gbc_comboBoxclustermode.gridy = 1;
		add(this.comboBoxclustermode, gbc_comboBoxclustermode);
		
		lblFitModel = new JLabel("Fit model:");
		this.lblFitModel.setToolTipText("Model (formula) to fit each layer");
		GridBagConstraints gbc_lblFitModel = new GridBagConstraints();
		gbc_lblFitModel.anchor = GridBagConstraints.EAST;
		gbc_lblFitModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFitModel.gridx = 0;
		gbc_lblFitModel.gridy = 2;
		add(lblFitModel, gbc_lblFitModel);
		
		this.textFieldfitmodel = new JTextField();
		textFieldfitmodel.setToolTipText("Model (formula) to fit each layer");
		GridBagConstraints gbc_textFieldfitmodel = new GridBagConstraints();
		gbc_textFieldfitmodel.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldfitmodel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldfitmodel.gridx = 1;
		gbc_textFieldfitmodel.gridy = 2;
		add(this.textFieldfitmodel, gbc_textFieldfitmodel);
		this.textFieldfitmodel.setColumns(10);
		
		lblConsiderBackground = new JLabel("Consider background:");
		this.lblConsiderBackground.setToolTipText("If true the method will consider that a background layer, [true, false]");
		GridBagConstraints gbc_lblConsiderBackground = new GridBagConstraints();
		gbc_lblConsiderBackground.anchor = GridBagConstraints.EAST;
		gbc_lblConsiderBackground.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsiderBackground.gridx = 0;
		gbc_lblConsiderBackground.gridy = 3;
		add(lblConsiderBackground, gbc_lblConsiderBackground);
		
		this.checkBoxusebackground = new JCheckBox("");
		checkBoxusebackground.setToolTipText("If true the method will consider that a background layer, [true, false]");
		GridBagConstraints gbc_checkBoxusebackground = new GridBagConstraints();
		gbc_checkBoxusebackground.anchor = GridBagConstraints.WEST;
		gbc_checkBoxusebackground.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxusebackground.gridx = 1;
		gbc_checkBoxusebackground.gridy = 3;
		add(this.checkBoxusebackground, gbc_checkBoxusebackground);
		
		lblBackgroundLayerFile = new JLabel("Background layer file:");
		this.lblBackgroundLayerFile.setToolTipText("A background layer can be specified in a file containing a numeric matrix with dimension of data matrix");
		GridBagConstraints gbc_lblBackgroundLayerFile = new GridBagConstraints();
		gbc_lblBackgroundLayerFile.anchor = GridBagConstraints.EAST;
		gbc_lblBackgroundLayerFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBackgroundLayerFile.gridx = 0;
		gbc_lblBackgroundLayerFile.gridy = 4;
		add(lblBackgroundLayerFile, gbc_lblBackgroundLayerFile);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{0.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		this.panel.setLayout(gbl_panel);
		
		this.btnOpenbackgroundfile = new JButton("open");
		GridBagConstraints gbc_btnOpenbackgroundfile = new GridBagConstraints();
		gbc_btnOpenbackgroundfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenbackgroundfile.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenbackgroundfile.gridx = 0;
		gbc_btnOpenbackgroundfile.gridy = 0;
		this.panel.add(this.btnOpenbackgroundfile, gbc_btnOpenbackgroundfile);
		btnOpenbackgroundfile.setActionCommand(OPENFILE);
		btnOpenbackgroundfile.addActionListener(this);
		
		this.textFieldbackgroudurl = new JTextField();
		textFieldbackgroudurl.setToolTipText("A background layer can be specified in a file containing a numeric matrix with dimension of data matrix");
		GridBagConstraints gbc_textFieldbackgroudurl = new GridBagConstraints();
		gbc_textFieldbackgroudurl.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldbackgroudurl.gridx = 1;
		gbc_textFieldbackgroudurl.gridy = 0;
		this.panel.add(this.textFieldbackgroudurl, gbc_textFieldbackgroudurl);
		this.textFieldbackgroudurl.setColumns(10);
		
		lblDegreesOfFreedom = new JLabel("Degrees of Freedom of backround layer:");
		this.lblDegreesOfFreedom.setToolTipText("Degrees of Freedom of backround layer if background.layer is specified");
		GridBagConstraints gbc_lblDegreesOfFreedom = new GridBagConstraints();
		gbc_lblDegreesOfFreedom.anchor = GridBagConstraints.EAST;
		gbc_lblDegreesOfFreedom.insets = new Insets(0, 0, 5, 5);
		gbc_lblDegreesOfFreedom.gridx = 0;
		gbc_lblDegreesOfFreedom.gridy = 5;
		add(lblDegreesOfFreedom, gbc_lblDegreesOfFreedom);
		
		this.doubleTextFielddegreesbackground = new DoubleTextField();
		doubleTextFielddegreesbackground.setToolTipText("Degrees of Freedom of backround layer if background.layer is specified");
		this.doubleTextFielddegreesbackground.setText("1.0");
		GridBagConstraints gbc_doubleTextFielddegreesbackground = new GridBagConstraints();
		gbc_doubleTextFielddegreesbackground.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFielddegreesbackground.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielddegreesbackground.gridx = 1;
		gbc_doubleTextFielddegreesbackground.gridy = 5;
		add(this.doubleTextFielddegreesbackground, gbc_doubleTextFielddegreesbackground);
		
		lblShuffle = new JLabel("Shuffle:");
		this.lblShuffle.setToolTipText("Before a layer is added, statistical significance is compared against a x number of layers obtained by random defined by this parameter");
		GridBagConstraints gbc_lblShuffle = new GridBagConstraints();
		gbc_lblShuffle.anchor = GridBagConstraints.EAST;
		gbc_lblShuffle.insets = new Insets(0, 0, 5, 5);
		gbc_lblShuffle.gridx = 0;
		gbc_lblShuffle.gridy = 6;
		add(lblShuffle, gbc_lblShuffle);
		
		this.integerTextFieldshuffle = new IntegerTextField();
		integerTextFieldshuffle.setToolTipText("Before a layer is added, statistical significance is compared against a x number of layers obtained by random defined by this parameter");
		GridBagConstraints gbc_integerTextFieldshuffle = new GridBagConstraints();
		gbc_integerTextFieldshuffle.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldshuffle.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldshuffle.gridx = 1;
		gbc_integerTextFieldshuffle.gridy = 6;
		add(this.integerTextFieldshuffle, gbc_integerTextFieldshuffle);
		
		this.lblNumberStartupIterations = new JLabel("Number startup iterations:");
		this.lblNumberStartupIterations.setToolTipText("Number of iterations to find starting values");
		GridBagConstraints gbc_lblNumberStartupIterations = new GridBagConstraints();
		gbc_lblNumberStartupIterations.anchor = GridBagConstraints.EAST;
		gbc_lblNumberStartupIterations.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberStartupIterations.gridx = 0;
		gbc_lblNumberStartupIterations.gridy = 7;
		add(this.lblNumberStartupIterations, gbc_lblNumberStartupIterations);
		
		this.integerTextFieldstartupiter = new IntegerTextField();
		integerTextFieldstartupiter.setToolTipText("Number of iterations to find starting values");
		GridBagConstraints gbc_integerTextFieldstartupiter = new GridBagConstraints();
		gbc_integerTextFieldstartupiter.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldstartupiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldstartupiter.gridx = 1;
		gbc_integerTextFieldstartupiter.gridy = 7;
		add(this.integerTextFieldstartupiter, gbc_integerTextFieldstartupiter);
		
		this.lblNumberIterationsTo = new JLabel("Number iterations to find each layer:");
		this.lblNumberIterationsTo.setToolTipText("Number of iterations to find each layer");
		GridBagConstraints gbc_lblNumberIterationsTo = new GridBagConstraints();
		gbc_lblNumberIterationsTo.anchor = GridBagConstraints.EAST;
		gbc_lblNumberIterationsTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberIterationsTo.gridx = 0;
		gbc_lblNumberIterationsTo.gridy = 8;
		add(this.lblNumberIterationsTo, gbc_lblNumberIterationsTo);
		
		this.integerTextFielditerationseachlayer = new IntegerTextField();
		integerTextFielditerationseachlayer.setToolTipText("Number of iterations to find each layer");
		GridBagConstraints gbc_integerTextFielditerationseachlayer = new GridBagConstraints();
		gbc_integerTextFielditerationseachlayer.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFielditerationseachlayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFielditerationseachlayer.gridx = 1;
		gbc_integerTextFielditerationseachlayer.gridy = 8;
		add(this.integerTextFielditerationseachlayer, gbc_integerTextFielditerationseachlayer);
		
		this.lblAdditionalIterationsFor = new JLabel("Additional iterations for each layer:");
		this.lblAdditionalIterationsFor.setToolTipText("After a layer is added, additional iterations can be done to refine the fitting of the layer");
		GridBagConstraints gbc_lblAdditionalIterationsFor = new GridBagConstraints();
		gbc_lblAdditionalIterationsFor.anchor = GridBagConstraints.EAST;
		gbc_lblAdditionalIterationsFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdditionalIterationsFor.gridx = 0;
		gbc_lblAdditionalIterationsFor.gridy = 9;
		add(this.lblAdditionalIterationsFor, gbc_lblAdditionalIterationsFor);
		
		this.integerTextFieldadditionaliter = new IntegerTextField();
		integerTextFieldadditionaliter.setToolTipText("After a layer is added, additional iterations can be done to refine the fitting of the layer");
		GridBagConstraints gbc_integerTextFieldadditionaliter = new GridBagConstraints();
		gbc_integerTextFieldadditionaliter.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldadditionaliter.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldadditionaliter.gridx = 1;
		gbc_integerTextFieldadditionaliter.gridy = 9;
		add(this.integerTextFieldadditionaliter, gbc_integerTextFieldadditionaliter);
		
		this.lblTresholdToPrune = new JLabel("Treshold to prune genes:");
		this.lblTresholdToPrune.setToolTipText("Threshold to prune rows in the layers depending on row homogeneity [0-1]");
		GridBagConstraints gbc_lblTresholdToPrune = new GridBagConstraints();
		gbc_lblTresholdToPrune.anchor = GridBagConstraints.EAST;
		gbc_lblTresholdToPrune.insets = new Insets(0, 0, 5, 5);
		gbc_lblTresholdToPrune.gridx = 0;
		gbc_lblTresholdToPrune.gridy = 10;
		add(this.lblTresholdToPrune, gbc_lblTresholdToPrune);
		
		this.spinnerprunegenes = new JSpinner();
		spinnerprunegenes.setToolTipText("Threshold to prune rows in the layers depending on row homogeneity [0-1]");
		this.spinnerprunegenes.setModel(new SpinnerNumberModel(0.7, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerprunegenes = new GridBagConstraints();
		gbc_spinnerprunegenes.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerprunegenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerprunegenes.gridx = 1;
		gbc_spinnerprunegenes.gridy = 10;
		add(this.spinnerprunegenes, gbc_spinnerprunegenes);
		
		this.lblTresholdToPrune_1 = new JLabel("Treshold to prune conditions:");
		this.lblTresholdToPrune_1.setToolTipText("Threshold to prune columns in the layers depending on column homogeneity [0-1]");
		GridBagConstraints gbc_lblTresholdToPrune_1 = new GridBagConstraints();
		gbc_lblTresholdToPrune_1.anchor = GridBagConstraints.EAST;
		gbc_lblTresholdToPrune_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTresholdToPrune_1.gridx = 0;
		gbc_lblTresholdToPrune_1.gridy = 11;
		add(this.lblTresholdToPrune_1, gbc_lblTresholdToPrune_1);
		
		this.spinnerpruneconds = new JSpinner();
		spinnerpruneconds.setToolTipText("Threshold to prune columns in the layers depending on column homogeneity [0-1]");
		this.spinnerpruneconds.setModel(new SpinnerNumberModel(0.7, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerpruneconds = new GridBagConstraints();
		gbc_spinnerpruneconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerpruneconds.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerpruneconds.gridx = 1;
		gbc_spinnerpruneconds.gridy = 11;
		add(this.spinnerpruneconds, gbc_spinnerpruneconds);
		
		this.lblMaxNumberOf = new JLabel("Max. number of layers to be found:");
		this.lblMaxNumberOf.setToolTipText("Maximum number of layer to include in the model (max number biclusters to be found)");
		GridBagConstraints gbc_lblMaxNumberOf = new GridBagConstraints();
		gbc_lblMaxNumberOf.anchor = GridBagConstraints.EAST;
		gbc_lblMaxNumberOf.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxNumberOf.gridx = 0;
		gbc_lblMaxNumberOf.gridy = 12;
		add(this.lblMaxNumberOf, gbc_lblMaxNumberOf);
		
		this.integerTextFieldnumberbics = new IntegerTextField();
		integerTextFieldnumberbics.setToolTipText("Maximum number of layer to include in the model (max number biclusters to be found)");
		GridBagConstraints gbc_integerTextFieldnumberbics = new GridBagConstraints();
		gbc_integerTextFieldnumberbics.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberbics.gridx = 1;
		gbc_integerTextFieldnumberbics.gridy = 12;
		add(this.integerTextFieldnumberbics, gbc_integerTextFieldnumberbics);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		
		comboBoxclustermode.setSelectedItem(PlaidClusterModel.both);
		textFieldfitmodel.setText("y ~ m + a + b");
		checkBoxusebackground.setSelected(true);
		btnOpenbackgroundfile.setEnabled(true);
		textFieldbackgroudurl.setText("");
		doubleTextFielddegreesbackground.setText("1");
		integerTextFieldshuffle.setText("3");
		integerTextFieldstartupiter.setText("5");
		integerTextFielditerationseachlayer.setText("10");
		integerTextFieldadditionaliter.setText("0");
		spinnerprunegenes.setValue(0.7);
		spinnerpruneconds.setValue(0.7);
		integerTextFieldnumberbics.setText("20");
		
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
		
		for (PlaidClusterModel element : PlaidClusterModel.values()) {
			comboBoxclustermode.addItem(element);
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
				RPlaidMethod.PLAID_CTYPE,
				RPlaidMethod.PLAID_FITMODEL,
				RPlaidMethod.PLAID_BACKGROUND,
				RPlaidMethod.PLAID_BACKGROUNDLAYER,
				RPlaidMethod.PLAID_BACKGROUNDDEGREESFREEDOM,
				RPlaidMethod.PLAID_SUFFLE,
				RPlaidMethod.PLAID_ITERSTARTUP,
				RPlaidMethod.PLAID_ITERLAYER,
				RPlaidMethod.PLAID_BACKFIT,
				RPlaidMethod.PLAID_ROWRELEASE,
				RPlaidMethod.PLAID_COLUMNRELEASE,
				RPlaidMethod.PLAID_MAXLAYERS
		};
		
		String clustmodel="b";
		PlaidClusterModel ccmodel=(PlaidClusterModel) comboBoxclustermode.getSelectedItem();
		if(ccmodel.equals(PlaidClusterModel.rows))
			clustmodel="r";
		else if(ccmodel.equals(PlaidClusterModel.columns))
			clustmodel="c";
		
		
		String[] values=new String[]{clustmodel,
				textFieldfitmodel.getText(),
				String.valueOf(checkBoxusebackground.isSelected()),
				textFieldbackgroudurl.getText(),
				doubleTextFielddegreesbackground.getText(),
				integerTextFieldshuffle.getText(),
				integerTextFieldstartupiter.getText(),
				integerTextFielditerationseachlayer.getText(),
				integerTextFieldadditionaliter.getText(),
				String.valueOf(spinnerprunegenes.getValue()),
				String.valueOf(spinnerpruneconds.getValue()),
				integerTextFieldnumberbics.getText()};
		
		
		String[] comments=new String[] {
				"r, c or b, to cluster rows, columns or both",
				"Model (formula) to fit each layer",
				"If true the method will consider that a background layer, [true, false]",
				"A background layer can be specified in a file containing a numeric matrix with dimension of data matrix",
				"Degrees of Freedom of backround layer if background.layer is specified",
				"Before a layer is added, the statistical significance is compared against a x number of layers obtained by random defined by this parameter",
				"Number of iterations to find starting values",
				"Number of iterations to find each layer",
				"After a layer is added, additional iterations can be done to refine the fitting of the layer",
				"Threshold to prune rows in the layers depending on row homogeneity [0-1]",
				"Threshold to prune columns in the layers depending on column homogeneity [0-1]",
				"Maximum number of layer to include in the model (max number biclusters to be found) "
		};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments,"Source: biclust manual, url: https://cran.r-project.org/web/packages/biclust/biclust.pdf");
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(textFieldfitmodel.getText().isEmpty())
			return new Pair<Boolean, String>(false, "Please set a model to fit each layer");
		else if(doubleTextFielddegreesbackground.getText().isEmpty() || Double.parseDouble(doubleTextFielddegreesbackground.getText())<1)
			return new Pair<Boolean, String>(false, "Degrees of Freedom of the backround layer must be higher than 0");
		else if(integerTextFieldshuffle.getText().isEmpty() || Integer.parseInt(integerTextFieldshuffle.getText())<1)
			return new Pair<Boolean, String>(false, "Shuffle value must be higher than 0");
		else if(integerTextFieldstartupiter.getText().isEmpty() || Integer.parseInt(integerTextFieldstartupiter.getText())<1)
			return new Pair<Boolean, String>(false, "Startup iterations must be higher than 0");
		else if(integerTextFielditerationseachlayer.getText().isEmpty() || Integer.parseInt(integerTextFielditerationseachlayer.getText())<1)
			return new Pair<Boolean, String>(false, "Iterations to find each layer must be higher than 0");
		else if(integerTextFieldadditionaliter.getText().isEmpty() || Integer.parseInt(integerTextFieldadditionaliter.getText())<0)
			return new Pair<Boolean, String>(false, "Additional iterations to find each layer must be 0 or higher");
		else if(integerTextFieldnumberbics.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberbics.getText())<1)
			return new Pair<Boolean, String>(false, "Maximum number of layers to include in the model must be higher than 0");
		else
			  return new Pair<Boolean, String>(true, null);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(OPENFILE)) {
			openBackgroundFile();
		}
		
	}
	
	/**
	 * Open background file.
	 */
	private void openBackgroundFile() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null)
        		textFieldbackgroudurl.setText(selected.getAbsolutePath());
        }
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(650,500);
	}

	

}
