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

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.wrappers.components.COALESCEMotifMatch;
import jbiclustge.utils.props.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import pt.ornrocha.swingutils.textfield.IntegerTextField;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class CoalesceSettingsPanel.
 */
public class CoalesceSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The lbl conditions pvalue. */
	private JLabel lblConditionsPvalue;
	
	/** The lbl motif pvalue. */
	private JLabel lblMotifPvalue;
	
	/** The lbl condition zscore. */
	private JLabel lblConditionZscore;
	
	/** The lbl motif zscore. */
	private JLabel lblMotifZscore;
	
	/** The spinnergeneprob. */
	private JSpinner spinnergeneprob;
	
	/** The double text fieldcondpvalue. */
	private DoubleTextField doubleTextFieldcondpvalue;
	
	/** The double text fieldmotifpvalue. */
	private DoubleTextField doubleTextFieldmotifpvalue;
	
	/** The double text fieldcondzscore. */
	private DoubleTextField doubleTextFieldcondzscore;
	
	/** The double text fieldmotifzscore. */
	private DoubleTextField doubleTextFieldmotifzscore;
	
	/** The lbl correlation pvalue. */
	private JLabel lblCorrelationPvalue;
	
	/** The lbl N pairs in. */
	private JLabel lblNPairsIn;
	
	/** The lbl resolution of bases. */
	private JLabel lblResolutionOfBases;
	
	/** The lbl min gene count. */
	private JLabel lblMinGeneCount;
	
	/** The lbl min motif count. */
	private JLabel lblMinMotifCount;
	
	/** The lbl sequences to use. */
	private JLabel lblSequencesToUse;
	
	/** The lbl max motif cluster. */
	private JLabel lblMaxMotifCluster;
	
	/** The double text fieldcorrpvalue. */
	private DoubleTextField doubleTextFieldcorrpvalue;
	
	/** The integer text fieldnpaircorrelation. */
	private IntegerTextField integerTextFieldnpaircorrelation;
	
	/** The text fieldseqstouse. */
	private JTextField textFieldseqstouse;
	
	/** The integer text fieldbaseresolution. */
	private IntegerTextField integerTextFieldbaseresolution;
	
	/** The integer text fieldmingenecount. */
	private IntegerTextField integerTextFieldmingenecount;
	
	/** The integer text fieldmaxmotifmergecount. */
	private IntegerTextField integerTextFieldmaxmotifmergecount;
	
	/** The integer text fieldmaxmotifclustercount. */
	private IntegerTextField integerTextFieldmaxmotifclustercount;
	
	/** The panel 4. */
	private JPanel panel_4;
	
	/** The panel 7. */
	private JPanel panel_7;
	
	/** The panel 8. */
	private JPanel panel_8;
	
	/** The label 6. */
	private JLabel label_6;
	
	/** The buttonopenfastafile. */
	private JButton buttonopenfastafile;
	
	/** The text fieldshowfastafile. */
	private JTextField textFieldshowfastafile;
	
	/** The label 7. */
	private JLabel label_7;
	
	/** The label 8. */
	private JLabel label_8;
	
	/** The label 9. */
	private JLabel label_9;
	
	/** The integer text fieldkmerlenght. */
	private IntegerTextField integerTextFieldkmerlenght;
	
	/** The double text fieldmotifmergepvalue. */
	private DoubleTextField doubleTextFieldmotifmergepvalue;
	
	/** The double text fieldmotifmergecutoff. */
	private DoubleTextField doubleTextFieldmotifmergecutoff;
	
	/** The label 10. */
	private JLabel label_10;
	
	/** The double text fieldpenaltygap. */
	private DoubleTextField doubleTextFieldpenaltygap;
	
	/** The label 11. */
	private JLabel label_11;
	
	/** The double text fieldpenaltymismatch. */
	private DoubleTextField doubleTextFieldpenaltymismatch;
	
	/** The lbl known motifs file. */
	private JLabel lblKnownMotifsFile;
	
	/** The lbl known motif cutoff. */
	private JLabel lblKnownMotifCutoff;
	
	/** The lbl known motif matching. */
	private JLabel lblKnownMotifMatching;
	
	/** The lbl simirarity cutoff. */
	private JLabel lblSimirarityCutoff;
	
	/** The lbl overlap fraction. */
	private JLabel lblOverlapFraction;
	
	/** The lbl cocluster cutoff. */
	private JLabel lblCoclusterCutoff;
	
	/** The lbl convert rcs and. */
	private JLabel lblConvertRcsAnd;
	
	/** The lbl uninformative motif cutoff. */
	private JLabel lblUninformativeMotifCutoff;
	
	/** The lbl max motifs to. */
	private JLabel lblMaxMotifsTo;
	
	/** The panel 5. */
	private JPanel panel_5;
	
	/** The btn openopenmotifsfile. */
	private JButton btnOpenopenmotifsfile;
	
	/** The text fieldshowopenmotifsfile. */
	private JTextField textFieldshowopenmotifsfile;
	
	/** The double text fieldknownmotifcutoff. */
	private DoubleTextField doubleTextFieldknownmotifcutoff;
	
	/** The panel 6. */
	private JPanel panel_6;
	
	/** The combo boxknownmotifmatch. */
	private JComboBox comboBoxknownmotifmatch;
	
	/** The spinnersimiraritycutoff. */
	private JSpinner spinnersimiraritycutoff;
	
	/** The spinneroverlapfraction. */
	private JSpinner spinneroverlapfraction;
	
	/** The spinnercoclustercutoff. */
	private JSpinner spinnercoclustercutoff;
	
	/** The check boxconvertrecs. */
	private JCheckBox checkBoxconvertrecs;
	
	/** The double text fielduniformativemotif. */
	private DoubleTextField doubleTextFielduniformativemotif;
	
	/** The integer text fieldmaxmotifmerge. */
	private IntegerTextField integerTextFieldmaxmotifmerge;
	
	/** The lbl detectnormalize automatically. */
	private JLabel lblDetectnormalizeAutomatically;
	
	/** The check boxautonormalization. */
	private JCheckBox checkBoxautonormalization;
	
	
	
	/**
	 * Instantiates a new coalesce settings panel.
	 */
	public CoalesceSettingsPanel() {
		super();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{0.0,0.0,0.0};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Algorithm Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{0.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.lblNewLabel = new JLabel("Gene probability:");
		this.lblNewLabel.setToolTipText("Probability threshhold for gene inclusion");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		this.panel.add(this.lblNewLabel, gbc_lblNewLabel);
		
		this.spinnergeneprob = new JSpinner();
		spinnergeneprob.setModel(new SpinnerNumberModel(0.95, 0.0, 1.0, 0.01));
		spinnergeneprob.setToolTipText("Probability threshhold for gene inclusion");
		GridBagConstraints gbc_spinnergeneprob = new GridBagConstraints();
		gbc_spinnergeneprob.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnergeneprob.insets = new Insets(0, 0, 5, 0);
		gbc_spinnergeneprob.gridx = 1;
		gbc_spinnergeneprob.gridy = 0;
		this.panel.add(this.spinnergeneprob, gbc_spinnergeneprob);
		
		this.lblConditionsPvalue = new JLabel("Conditions p-value:");
		this.lblConditionsPvalue.setToolTipText("P-value threshold for condition inclusion");
		GridBagConstraints gbc_lblConditionsPvalue = new GridBagConstraints();
		gbc_lblConditionsPvalue.anchor = GridBagConstraints.EAST;
		gbc_lblConditionsPvalue.insets = new Insets(0, 20, 5, 5);
		gbc_lblConditionsPvalue.gridx = 0;
		gbc_lblConditionsPvalue.gridy = 1;
		this.panel.add(this.lblConditionsPvalue, gbc_lblConditionsPvalue);
		
		this.doubleTextFieldcondpvalue = new DoubleTextField();
		doubleTextFieldcondpvalue.setToolTipText("P-value threshold for condition inclusion");
		doubleTextFieldcondpvalue.setToolTipText("Conditions p-value");
		GridBagConstraints gbc_doubleTextFieldcondpvalue = new GridBagConstraints();
		gbc_doubleTextFieldcondpvalue.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldcondpvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldcondpvalue.gridx = 1;
		gbc_doubleTextFieldcondpvalue.gridy = 1;
		this.panel.add(this.doubleTextFieldcondpvalue, gbc_doubleTextFieldcondpvalue);
		
		this.lblMotifPvalue = new JLabel("Motif p-value:");
		this.lblMotifPvalue.setToolTipText("P-value threshold for motif inclusion");
		GridBagConstraints gbc_lblMotifPvalue = new GridBagConstraints();
		gbc_lblMotifPvalue.anchor = GridBagConstraints.EAST;
		gbc_lblMotifPvalue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotifPvalue.gridx = 0;
		gbc_lblMotifPvalue.gridy = 2;
		this.panel.add(this.lblMotifPvalue, gbc_lblMotifPvalue);
		
		this.doubleTextFieldmotifpvalue = new DoubleTextField();
		doubleTextFieldmotifpvalue.setToolTipText("P-value threshold for motif inclusion");
		doubleTextFieldmotifpvalue.setToolTipText("Motif p-value");
		GridBagConstraints gbc_doubleTextFieldmotifpvalue = new GridBagConstraints();
		gbc_doubleTextFieldmotifpvalue.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldmotifpvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldmotifpvalue.gridx = 1;
		gbc_doubleTextFieldmotifpvalue.gridy = 2;
		this.panel.add(this.doubleTextFieldmotifpvalue, gbc_doubleTextFieldmotifpvalue);
		
		this.lblConditionZscore = new JLabel("Condition z-score:");
		this.lblConditionZscore.setToolTipText("Z-score threshold for condition inclusion");
		GridBagConstraints gbc_lblConditionZscore = new GridBagConstraints();
		gbc_lblConditionZscore.anchor = GridBagConstraints.EAST;
		gbc_lblConditionZscore.insets = new Insets(0, 20, 5, 5);
		gbc_lblConditionZscore.gridx = 0;
		gbc_lblConditionZscore.gridy = 3;
		this.panel.add(this.lblConditionZscore, gbc_lblConditionZscore);
		
		this.doubleTextFieldcondzscore = new DoubleTextField();
		doubleTextFieldcondzscore.setToolTipText("Z-score threshold for condition inclusion");
		doubleTextFieldcondzscore.setToolTipText("Condition z-score");
		GridBagConstraints gbc_doubleTextFieldcondzscore = new GridBagConstraints();
		gbc_doubleTextFieldcondzscore.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldcondzscore.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldcondzscore.gridx = 1;
		gbc_doubleTextFieldcondzscore.gridy = 3;
		this.panel.add(this.doubleTextFieldcondzscore, gbc_doubleTextFieldcondzscore);
		
		this.lblMotifZscore = new JLabel("Motif z-score:");
		this.lblMotifZscore.setToolTipText("Z-score threshhold for motif inclusion");
		GridBagConstraints gbc_lblMotifZscore = new GridBagConstraints();
		gbc_lblMotifZscore.anchor = GridBagConstraints.EAST;
		gbc_lblMotifZscore.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotifZscore.gridx = 0;
		gbc_lblMotifZscore.gridy = 4;
		this.panel.add(this.lblMotifZscore, gbc_lblMotifZscore);
		
		this.doubleTextFieldmotifzscore = new DoubleTextField();
		doubleTextFieldmotifzscore.setToolTipText("Z-score threshhold for motif inclusion");
		doubleTextFieldmotifzscore.setToolTipText("Motif z-score");
		GridBagConstraints gbc_doubleTextFieldmotifzscore = new GridBagConstraints();
		gbc_doubleTextFieldmotifzscore.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldmotifzscore.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldmotifzscore.gridx = 1;
		gbc_doubleTextFieldmotifzscore.gridy = 4;
		this.panel.add(this.doubleTextFieldmotifzscore, gbc_doubleTextFieldmotifzscore);
		
		this.lblDetectnormalizeAutomatically = new JLabel("Detect/normalize automatically:");
		this.lblDetectnormalizeAutomatically.setToolTipText("Automatically detect/normalize single channel data");
		GridBagConstraints gbc_lblDetectnormalizeAutomatically = new GridBagConstraints();
		gbc_lblDetectnormalizeAutomatically.anchor = GridBagConstraints.EAST;
		gbc_lblDetectnormalizeAutomatically.insets = new Insets(0, 20, 0, 5);
		gbc_lblDetectnormalizeAutomatically.gridx = 0;
		gbc_lblDetectnormalizeAutomatically.gridy = 5;
		this.panel.add(this.lblDetectnormalizeAutomatically, gbc_lblDetectnormalizeAutomatically);
		
		this.checkBoxautonormalization = new JCheckBox("");
		checkBoxautonormalization.setToolTipText("Automatically detect/normalize single channel data");
		GridBagConstraints gbc_checkBoxautonormalization = new GridBagConstraints();
		gbc_checkBoxautonormalization.anchor = GridBagConstraints.WEST;
		gbc_checkBoxautonormalization.gridx = 1;
		gbc_checkBoxautonormalization.gridy = 5;
		this.panel.add(this.checkBoxautonormalization, gbc_checkBoxautonormalization);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Performance Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{0.0,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.lblCorrelationPvalue = new JLabel("Correlation p-value:");
		this.lblCorrelationPvalue.setToolTipText("P-value threshhold for significant correlation");
		GridBagConstraints gbc_lblCorrelationPvalue = new GridBagConstraints();
		gbc_lblCorrelationPvalue.anchor = GridBagConstraints.EAST;
		gbc_lblCorrelationPvalue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorrelationPvalue.gridx = 0;
		gbc_lblCorrelationPvalue.gridy = 0;
		this.panel_1.add(this.lblCorrelationPvalue, gbc_lblCorrelationPvalue);
		
		this.doubleTextFieldcorrpvalue = new DoubleTextField();
		doubleTextFieldcorrpvalue.setToolTipText("P-value threshhold for significant correlation");
		doubleTextFieldcorrpvalue.setToolTipText("Correlation p-value");
		GridBagConstraints gbc_doubleTextFieldcorrpvalue = new GridBagConstraints();
		gbc_doubleTextFieldcorrpvalue.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldcorrpvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldcorrpvalue.gridx = 1;
		gbc_doubleTextFieldcorrpvalue.gridy = 0;
		this.panel_1.add(this.doubleTextFieldcorrpvalue, gbc_doubleTextFieldcorrpvalue);
		
		this.lblNPairsIn = new JLabel("Nº pairs in correlation:");
		this.lblNPairsIn.setToolTipText("Maximum number of pairs to sample for significant correlation");
		GridBagConstraints gbc_lblNPairsIn = new GridBagConstraints();
		gbc_lblNPairsIn.anchor = GridBagConstraints.EAST;
		gbc_lblNPairsIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblNPairsIn.gridx = 0;
		gbc_lblNPairsIn.gridy = 1;
		this.panel_1.add(this.lblNPairsIn, gbc_lblNPairsIn);
		
		this.integerTextFieldnpaircorrelation = new IntegerTextField();
		integerTextFieldnpaircorrelation.setToolTipText("Maximum number of pairs to sample for significant correlation");
		integerTextFieldnpaircorrelation.setToolTipText("Nº pairs in correlation");
		GridBagConstraints gbc_integerTextFieldnpaircorrelation = new GridBagConstraints();
		gbc_integerTextFieldnpaircorrelation.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnpaircorrelation.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnpaircorrelation.gridx = 1;
		gbc_integerTextFieldnpaircorrelation.gridy = 1;
		this.panel_1.add(this.integerTextFieldnpaircorrelation, gbc_integerTextFieldnpaircorrelation);
		
		this.lblSequencesToUse = new JLabel("Sequences to use:");
		this.lblSequencesToUse.setToolTipText("Sequence types to use (comma separated *,*))");
		GridBagConstraints gbc_lblSequencesToUse = new GridBagConstraints();
		gbc_lblSequencesToUse.anchor = GridBagConstraints.EAST;
		gbc_lblSequencesToUse.insets = new Insets(0, 0, 5, 5);
		gbc_lblSequencesToUse.gridx = 0;
		gbc_lblSequencesToUse.gridy = 2;
		this.panel_1.add(this.lblSequencesToUse, gbc_lblSequencesToUse);
		
		this.textFieldseqstouse = new JTextField();
		textFieldseqstouse.setToolTipText("Sequence types to use (comma separated *,*))");
		textFieldseqstouse.setToolTipText("Sequences to use");
		GridBagConstraints gbc_textFieldseqstouse = new GridBagConstraints();
		gbc_textFieldseqstouse.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldseqstouse.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldseqstouse.gridx = 1;
		gbc_textFieldseqstouse.gridy = 2;
		this.panel_1.add(this.textFieldseqstouse, gbc_textFieldseqstouse);
		this.textFieldseqstouse.setColumns(10);
		
		this.lblResolutionOfBases = new JLabel("Resolution of bases:");
		this.lblResolutionOfBases.setToolTipText("Resolution of bases per motif match");
		GridBagConstraints gbc_lblResolutionOfBases = new GridBagConstraints();
		gbc_lblResolutionOfBases.anchor = GridBagConstraints.EAST;
		gbc_lblResolutionOfBases.insets = new Insets(0, 0, 5, 5);
		gbc_lblResolutionOfBases.gridx = 0;
		gbc_lblResolutionOfBases.gridy = 3;
		this.panel_1.add(this.lblResolutionOfBases, gbc_lblResolutionOfBases);
		
		this.integerTextFieldbaseresolution = new IntegerTextField();
		integerTextFieldbaseresolution.setToolTipText("Resolution of bases per motif match");
		integerTextFieldbaseresolution.setToolTipText("Resolution of bases");
		GridBagConstraints gbc_integerTextFieldbaseresolution = new GridBagConstraints();
		gbc_integerTextFieldbaseresolution.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldbaseresolution.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldbaseresolution.gridx = 1;
		gbc_integerTextFieldbaseresolution.gridy = 3;
		this.panel_1.add(this.integerTextFieldbaseresolution, gbc_integerTextFieldbaseresolution);
		
		this.lblMinGeneCount = new JLabel("Min. gene count:");
		this.lblMinGeneCount.setToolTipText("Minimum gene count for clusters of interest");
		GridBagConstraints gbc_lblMinGeneCount = new GridBagConstraints();
		gbc_lblMinGeneCount.anchor = GridBagConstraints.EAST;
		gbc_lblMinGeneCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinGeneCount.gridx = 0;
		gbc_lblMinGeneCount.gridy = 4;
		this.panel_1.add(this.lblMinGeneCount, gbc_lblMinGeneCount);
		
		this.integerTextFieldmingenecount = new IntegerTextField();
		integerTextFieldmingenecount.setToolTipText("Minimum gene count for clusters of interest");
		integerTextFieldmingenecount.setToolTipText("Min. gene count");
		GridBagConstraints gbc_integerTextFieldmingenecount = new GridBagConstraints();
		gbc_integerTextFieldmingenecount.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmingenecount.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenecount.gridx = 1;
		gbc_integerTextFieldmingenecount.gridy = 4;
		this.panel_1.add(this.integerTextFieldmingenecount, gbc_integerTextFieldmingenecount);
		
		this.lblMinMotifCount = new JLabel("Max. motif merge count:");
		this.lblMinMotifCount.setToolTipText("Maximum motif count for realtime merging");
		GridBagConstraints gbc_lblMinMotifCount = new GridBagConstraints();
		gbc_lblMinMotifCount.anchor = GridBagConstraints.EAST;
		gbc_lblMinMotifCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinMotifCount.gridx = 0;
		gbc_lblMinMotifCount.gridy = 5;
		this.panel_1.add(this.lblMinMotifCount, gbc_lblMinMotifCount);
		
		this.integerTextFieldmaxmotifmergecount = new IntegerTextField();
		integerTextFieldmaxmotifmergecount.setToolTipText("Maximum motif count for realtime merging");
		GridBagConstraints gbc_integerTextFieldmaxmotifmergecount = new GridBagConstraints();
		gbc_integerTextFieldmaxmotifmergecount.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldmaxmotifmergecount.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxmotifmergecount.gridx = 1;
		gbc_integerTextFieldmaxmotifmergecount.gridy = 5;
		this.panel_1.add(this.integerTextFieldmaxmotifmergecount, gbc_integerTextFieldmaxmotifmergecount);
		
		this.lblMaxMotifCluster = new JLabel("Max. motif cluster count:");
		this.lblMaxMotifCluster.setToolTipText("Maximum motif count to consider a cluster saturated");
		GridBagConstraints gbc_lblMaxMotifCluster = new GridBagConstraints();
		gbc_lblMaxMotifCluster.anchor = GridBagConstraints.EAST;
		gbc_lblMaxMotifCluster.insets = new Insets(0, 20, 0, 5);
		gbc_lblMaxMotifCluster.gridx = 0;
		gbc_lblMaxMotifCluster.gridy = 6;
		this.panel_1.add(this.lblMaxMotifCluster, gbc_lblMaxMotifCluster);
		
		this.integerTextFieldmaxmotifclustercount = new IntegerTextField();
		integerTextFieldmaxmotifclustercount.setToolTipText("Maximum motif count to consider a cluster saturated");
		integerTextFieldmaxmotifclustercount.setToolTipText("Max. motif cluster count");
		GridBagConstraints gbc_integerTextFieldmaxmotifclustercount = new GridBagConstraints();
		gbc_integerTextFieldmaxmotifclustercount.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxmotifclustercount.gridx = 1;
		gbc_integerTextFieldmaxmotifclustercount.gridy = 6;
		this.panel_1.add(this.integerTextFieldmaxmotifclustercount, gbc_integerTextFieldmaxmotifclustercount);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, "Sequence Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1,1};
		gbl_panel_2.rowHeights = new int[]{1,1,1,1,1,1};
		gbl_panel_2.columnWeights = new double[]{1.0,1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		this.panel_2.add(this.panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1,1,1};
		gbl_panel_4.rowHeights = new int[]{1};
		gbl_panel_4.columnWeights = new double[]{0.2,0.2,1.0};
		gbl_panel_4.rowWeights = new double[]{1.0};
		this.panel_4.setLayout(gbl_panel_4);
		
		this.label_6 = new JLabel("Fasta file:");
		this.label_6.setToolTipText("Input FASTA file");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 0;
		this.panel_4.add(this.label_6, gbc_label_6);
		
		this.buttonopenfastafile = new JButton("Open");
		GridBagConstraints gbc_buttonopenfastafile = new GridBagConstraints();
		gbc_buttonopenfastafile.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonopenfastafile.insets = new Insets(0, 0, 0, 5);
		gbc_buttonopenfastafile.gridx = 1;
		gbc_buttonopenfastafile.gridy = 0;
		this.panel_4.add(this.buttonopenfastafile, gbc_buttonopenfastafile);
		
		this.textFieldshowfastafile = new JTextField();
		this.textFieldshowfastafile.setColumns(10);
		GridBagConstraints gbc_textFieldshowfastafile = new GridBagConstraints();
		gbc_textFieldshowfastafile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldshowfastafile.gridx = 2;
		gbc_textFieldshowfastafile.gridy = 0;
		this.panel_4.add(this.textFieldshowfastafile, gbc_textFieldshowfastafile);
		
		this.panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.gridheight = 5;
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		this.panel_2.add(this.panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{1,1};
		gbl_panel_7.rowHeights = new int[]{1,1,1};
		gbl_panel_7.columnWeights = new double[]{0.0,1.0};
		gbl_panel_7.rowWeights = new double[]{1.0,1.0,1.0};
		this.panel_7.setLayout(gbl_panel_7);
		
		this.label_7 = new JLabel("kmer lenght:");
		this.label_7.setToolTipText("Sequence kmer length");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 0;
		this.panel_7.add(this.label_7, gbc_label_7);
		
		this.integerTextFieldkmerlenght = new IntegerTextField();
		integerTextFieldkmerlenght.setToolTipText("Sequence kmer length");
		integerTextFieldkmerlenght.setToolTipText("kmer lenght");
		GridBagConstraints gbc_integerTextFieldkmerlenght = new GridBagConstraints();
		gbc_integerTextFieldkmerlenght.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldkmerlenght.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldkmerlenght.gridx = 1;
		gbc_integerTextFieldkmerlenght.gridy = 0;
		this.panel_7.add(this.integerTextFieldkmerlenght, gbc_integerTextFieldkmerlenght);
		
		this.label_8 = new JLabel("Motif merging p-value:");
		this.label_8.setToolTipText("P-value threshhold for motif merging");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.EAST;
		gbc_label_8.insets = new Insets(0, 20, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 1;
		this.panel_7.add(this.label_8, gbc_label_8);
		
		this.doubleTextFieldmotifmergepvalue = new DoubleTextField();
		doubleTextFieldmotifmergepvalue.setToolTipText("P-value threshhold for motif merging");
		GridBagConstraints gbc_doubleTextFieldmotifmergepvalue = new GridBagConstraints();
		gbc_doubleTextFieldmotifmergepvalue.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldmotifmergepvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldmotifmergepvalue.gridx = 1;
		gbc_doubleTextFieldmotifmergepvalue.gridy = 1;
		this.panel_7.add(this.doubleTextFieldmotifmergepvalue, gbc_doubleTextFieldmotifmergepvalue);
		
		this.label_9 = new JLabel("Motif merging cutoff:");
		this.label_9.setToolTipText("Edit distance cutoff for motif merging");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.EAST;
		gbc_label_9.insets = new Insets(0, 0, 0, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 2;
		this.panel_7.add(this.label_9, gbc_label_9);
		
		this.doubleTextFieldmotifmergecutoff = new DoubleTextField();
		doubleTextFieldmotifmergecutoff.setToolTipText("Edit distance cutoff for motif merging");
		GridBagConstraints gbc_doubleTextFieldmotifmergecutoff = new GridBagConstraints();
		gbc_doubleTextFieldmotifmergecutoff.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldmotifmergecutoff.gridx = 1;
		gbc_doubleTextFieldmotifmergecutoff.gridy = 2;
		this.panel_7.add(this.doubleTextFieldmotifmergecutoff, gbc_doubleTextFieldmotifmergecutoff);
		
		this.panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.gridheight = 5;
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 1;
		gbc_panel_8.gridy = 1;
		this.panel_2.add(this.panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{1,1};
		gbl_panel_8.rowHeights = new int[]{1,1,1};
		gbl_panel_8.columnWeights = new double[]{0.0,1.0};
		gbl_panel_8.rowWeights = new double[]{1.0,1.0,1.0};
		this.panel_8.setLayout(gbl_panel_8);
		
		this.label_10 = new JLabel("Penalty gap:");
		this.label_10.setToolTipText("Edit distance penalty for gaps");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 0;
		this.panel_8.add(this.label_10, gbc_label_10);
		
		this.doubleTextFieldpenaltygap = new DoubleTextField();
		this.doubleTextFieldpenaltygap.setToolTipText("Edit distance penalty for gaps");
		GridBagConstraints gbc_doubleTextFieldpenaltygap = new GridBagConstraints();
		gbc_doubleTextFieldpenaltygap.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldpenaltygap.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldpenaltygap.gridx = 1;
		gbc_doubleTextFieldpenaltygap.gridy = 0;
		this.panel_8.add(this.doubleTextFieldpenaltygap, gbc_doubleTextFieldpenaltygap);
		
		this.label_11 = new JLabel("Penalty mismatch:");
		this.label_11.setToolTipText("Edit distance penalty for mismatches");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 20, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 1;
		this.panel_8.add(this.label_11, gbc_label_11);
		
		this.doubleTextFieldpenaltymismatch = new DoubleTextField();
		doubleTextFieldpenaltymismatch.setToolTipText("Penalty mismatch");
		GridBagConstraints gbc_doubleTextFieldpenaltymismatch = new GridBagConstraints();
		gbc_doubleTextFieldpenaltymismatch.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldpenaltymismatch.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldpenaltymismatch.gridx = 1;
		gbc_doubleTextFieldpenaltymismatch.gridy = 1;
		this.panel_8.add(this.doubleTextFieldpenaltymismatch, gbc_doubleTextFieldpenaltymismatch);
		
		this.panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridwidth = 2;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		add(this.panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{1,1,1};
		gbl_panel_6.rowHeights = new int[]{1};
		gbl_panel_6.columnWeights = new double[]{1.0,0.5,1.0};
		gbl_panel_6.rowWeights = new double[]{0.0};
		this.panel_6.setLayout(gbl_panel_6);
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		this.panel_6.add(this.panel_3, gbc_panel_3);
		this.panel_3.setBorder(new TitledBorder(null, "Postprocessng Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1,1};
		gbl_panel_3.rowHeights = new int[]{1,1,1,1,1,1,1,1,1};
		gbl_panel_3.columnWeights = new double[]{0.0,1.0};
		gbl_panel_3.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.lblKnownMotifsFile = new JLabel("Known motifs file:");
		this.lblKnownMotifsFile.setToolTipText("File containing known motifs");
		GridBagConstraints gbc_lblKnownMotifsFile = new GridBagConstraints();
		gbc_lblKnownMotifsFile.anchor = GridBagConstraints.EAST;
		gbc_lblKnownMotifsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnownMotifsFile.gridx = 0;
		gbc_lblKnownMotifsFile.gridy = 0;
		this.panel_3.add(this.lblKnownMotifsFile, gbc_lblKnownMotifsFile);
		
		this.panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 0;
		this.panel_3.add(this.panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{1,1};
		gbl_panel_5.rowHeights = new int[]{1};
		gbl_panel_5.columnWeights = new double[]{0.2,1.0};
		gbl_panel_5.rowWeights = new double[]{1.0};
		this.panel_5.setLayout(gbl_panel_5);
		
		this.btnOpenopenmotifsfile = new JButton("Open");
		GridBagConstraints gbc_btnOpenopenmotifsfile = new GridBagConstraints();
		gbc_btnOpenopenmotifsfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenopenmotifsfile.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenopenmotifsfile.gridx = 0;
		gbc_btnOpenopenmotifsfile.gridy = 0;
		this.panel_5.add(this.btnOpenopenmotifsfile, gbc_btnOpenopenmotifsfile);
		
		this.textFieldshowopenmotifsfile = new JTextField();
		textFieldshowopenmotifsfile.setToolTipText("File containing known motifs");
		GridBagConstraints gbc_textFieldshowopenmotifsfile = new GridBagConstraints();
		gbc_textFieldshowopenmotifsfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldshowopenmotifsfile.gridx = 1;
		gbc_textFieldshowopenmotifsfile.gridy = 0;
		this.panel_5.add(this.textFieldshowopenmotifsfile, gbc_textFieldshowopenmotifsfile);
		this.textFieldshowopenmotifsfile.setColumns(10);
		
		this.lblKnownMotifCutoff = new JLabel("Known motif cutoff:");
		this.lblKnownMotifCutoff.setToolTipText("Score cutoff for known motif labeling");
		GridBagConstraints gbc_lblKnownMotifCutoff = new GridBagConstraints();
		gbc_lblKnownMotifCutoff.anchor = GridBagConstraints.EAST;
		gbc_lblKnownMotifCutoff.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnownMotifCutoff.gridx = 0;
		gbc_lblKnownMotifCutoff.gridy = 1;
		this.panel_3.add(this.lblKnownMotifCutoff, gbc_lblKnownMotifCutoff);
		
		this.doubleTextFieldknownmotifcutoff = new DoubleTextField();
		doubleTextFieldknownmotifcutoff.setToolTipText("Score cutoff for known motif labeling");
		doubleTextFieldknownmotifcutoff.setToolTipText("Known motif cutoff");
		GridBagConstraints gbc_doubleTextFieldknownmotifcutoff = new GridBagConstraints();
		gbc_doubleTextFieldknownmotifcutoff.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldknownmotifcutoff.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldknownmotifcutoff.gridx = 1;
		gbc_doubleTextFieldknownmotifcutoff.gridy = 1;
		this.panel_3.add(this.doubleTextFieldknownmotifcutoff, gbc_doubleTextFieldknownmotifcutoff);
		
		this.lblKnownMotifMatching = new JLabel("Known motif matching:");
		this.lblKnownMotifMatching.setToolTipText("Type of known motif matching");
		GridBagConstraints gbc_lblKnownMotifMatching = new GridBagConstraints();
		gbc_lblKnownMotifMatching.anchor = GridBagConstraints.EAST;
		gbc_lblKnownMotifMatching.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnownMotifMatching.gridx = 0;
		gbc_lblKnownMotifMatching.gridy = 2;
		this.panel_3.add(this.lblKnownMotifMatching, gbc_lblKnownMotifMatching);
		
		this.comboBoxknownmotifmatch = new JComboBox();
		comboBoxknownmotifmatch.setToolTipText("Type of known motif matching");
		GridBagConstraints gbc_comboBoxknownmotifmatch = new GridBagConstraints();
		gbc_comboBoxknownmotifmatch.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxknownmotifmatch.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxknownmotifmatch.gridx = 1;
		gbc_comboBoxknownmotifmatch.gridy = 2;
		this.panel_3.add(this.comboBoxknownmotifmatch, gbc_comboBoxknownmotifmatch);
		
		this.lblSimirarityCutoff = new JLabel("Simirarity cutoff:");
		this.lblSimirarityCutoff.setToolTipText("Similarity cutoff for cluster merging");
		GridBagConstraints gbc_lblSimirarityCutoff = new GridBagConstraints();
		gbc_lblSimirarityCutoff.anchor = GridBagConstraints.EAST;
		gbc_lblSimirarityCutoff.insets = new Insets(0, 0, 5, 5);
		gbc_lblSimirarityCutoff.gridx = 0;
		gbc_lblSimirarityCutoff.gridy = 3;
		this.panel_3.add(this.lblSimirarityCutoff, gbc_lblSimirarityCutoff);
		
		this.spinnersimiraritycutoff = new JSpinner();
		spinnersimiraritycutoff.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01)); 
		spinnersimiraritycutoff.setToolTipText("Similarity cutoff for cluster merging");
		GridBagConstraints gbc_spinnersimiraritycutoff = new GridBagConstraints();
		gbc_spinnersimiraritycutoff.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnersimiraritycutoff.insets = new Insets(0, 0, 5, 0);
		gbc_spinnersimiraritycutoff.gridx = 1;
		gbc_spinnersimiraritycutoff.gridy = 3;
		this.panel_3.add(this.spinnersimiraritycutoff, gbc_spinnersimiraritycutoff);
		
		this.lblOverlapFraction = new JLabel("Overlap fraction:");
		this.lblOverlapFraction.setToolTipText("Overlap fraction for postprocessing gene/condition inclusion");
		GridBagConstraints gbc_lblOverlapFraction = new GridBagConstraints();
		gbc_lblOverlapFraction.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapFraction.insets = new Insets(0, 0, 5, 5);
		gbc_lblOverlapFraction.gridx = 0;
		gbc_lblOverlapFraction.gridy = 4;
		this.panel_3.add(this.lblOverlapFraction, gbc_lblOverlapFraction);
		
		this.spinneroverlapfraction = new JSpinner();
		spinneroverlapfraction.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.01)); 
		spinneroverlapfraction.setToolTipText("Overlap fraction for postprocessing gene/condition inclusion");
		GridBagConstraints gbc_spinneroverlapfraction = new GridBagConstraints();
		gbc_spinneroverlapfraction.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneroverlapfraction.insets = new Insets(0, 0, 5, 0);
		gbc_spinneroverlapfraction.gridx = 1;
		gbc_spinneroverlapfraction.gridy = 4;
		this.panel_3.add(this.spinneroverlapfraction, gbc_spinneroverlapfraction);
		
		this.lblCoclusterCutoff = new JLabel("CoCluster cutoff:");
		this.lblCoclusterCutoff.setToolTipText("Cocluster stdev cutoff for cluster trimming");
		GridBagConstraints gbc_lblCoclusterCutoff = new GridBagConstraints();
		gbc_lblCoclusterCutoff.anchor = GridBagConstraints.EAST;
		gbc_lblCoclusterCutoff.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoclusterCutoff.gridx = 0;
		gbc_lblCoclusterCutoff.gridy = 5;
		this.panel_3.add(this.lblCoclusterCutoff, gbc_lblCoclusterCutoff);
		
		this.spinnercoclustercutoff = new JSpinner();
		spinnercoclustercutoff.setToolTipText("Cocluster stdev cutoff for cluster trimming");
		GridBagConstraints gbc_spinnercoclustercutoff = new GridBagConstraints();
		gbc_spinnercoclustercutoff.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnercoclustercutoff.insets = new Insets(0, 0, 5, 0);
		gbc_spinnercoclustercutoff.gridx = 1;
		gbc_spinnercoclustercutoff.gridy = 5;
		this.panel_3.add(this.spinnercoclustercutoff, gbc_spinnercoclustercutoff);
		
		this.lblConvertRcsAnd = new JLabel("Convert RCs and RC-like:");
		this.lblConvertRcsAnd.setToolTipText("Convert RCs and RC-like PSTs to single strand");
		GridBagConstraints gbc_lblConvertRcsAnd = new GridBagConstraints();
		gbc_lblConvertRcsAnd.anchor = GridBagConstraints.EAST;
		gbc_lblConvertRcsAnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblConvertRcsAnd.gridx = 0;
		gbc_lblConvertRcsAnd.gridy = 6;
		this.panel_3.add(this.lblConvertRcsAnd, gbc_lblConvertRcsAnd);
		
		this.checkBoxconvertrecs = new JCheckBox("");
		checkBoxconvertrecs.setToolTipText("Convert RCs and RC-like PSTs to single strand");
		GridBagConstraints gbc_checkBoxconvertrecs = new GridBagConstraints();
		gbc_checkBoxconvertrecs.anchor = GridBagConstraints.WEST;
		gbc_checkBoxconvertrecs.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxconvertrecs.gridx = 1;
		gbc_checkBoxconvertrecs.gridy = 6;
		this.panel_3.add(this.checkBoxconvertrecs, gbc_checkBoxconvertrecs);
		
		this.lblUninformativeMotifCutoff = new JLabel("Uninformative motif cutoff:");
		this.lblUninformativeMotifCutoff.setToolTipText("Uninformative motif threshold (bits)");
		GridBagConstraints gbc_lblUninformativeMotifCutoff = new GridBagConstraints();
		gbc_lblUninformativeMotifCutoff.anchor = GridBagConstraints.EAST;
		gbc_lblUninformativeMotifCutoff.insets = new Insets(0, 20, 5, 5);
		gbc_lblUninformativeMotifCutoff.gridx = 0;
		gbc_lblUninformativeMotifCutoff.gridy = 7;
		this.panel_3.add(this.lblUninformativeMotifCutoff, gbc_lblUninformativeMotifCutoff);
		
		this.doubleTextFielduniformativemotif = new DoubleTextField();
		doubleTextFielduniformativemotif.setToolTipText("Uninformative motif threshold (bits)");
		doubleTextFielduniformativemotif.setToolTipText("Uninformative motif cutoff");
		GridBagConstraints gbc_doubleTextFielduniformativemotif = new GridBagConstraints();
		gbc_doubleTextFielduniformativemotif.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFielduniformativemotif.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielduniformativemotif.gridx = 1;
		gbc_doubleTextFielduniformativemotif.gridy = 7;
		this.panel_3.add(this.doubleTextFielduniformativemotif, gbc_doubleTextFielduniformativemotif);
		
		this.lblMaxMotifsTo = new JLabel("Max. motifs to merge:");
		this.lblMaxMotifsTo.setToolTipText("Maximum motifs to merge exactly");
		GridBagConstraints gbc_lblMaxMotifsTo = new GridBagConstraints();
		gbc_lblMaxMotifsTo.anchor = GridBagConstraints.EAST;
		gbc_lblMaxMotifsTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxMotifsTo.gridx = 0;
		gbc_lblMaxMotifsTo.gridy = 8;
		this.panel_3.add(this.lblMaxMotifsTo, gbc_lblMaxMotifsTo);
		
		this.integerTextFieldmaxmotifmerge = new IntegerTextField();
		integerTextFieldmaxmotifmerge.setToolTipText("Maximum motifs to merge exactly");
		integerTextFieldmaxmotifmerge.setToolTipText("Max. motifs to merge");
		GridBagConstraints gbc_integerTextFieldmaxmotifmerge = new GridBagConstraints();
		gbc_integerTextFieldmaxmotifmerge.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxmotifmerge.gridx = 1;
		gbc_integerTextFieldmaxmotifmerge.gridy = 8;
		this.panel_3.add(this.integerTextFieldmaxmotifmerge, gbc_integerTextFieldmaxmotifmerge);
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
		
		for (COALESCEMotifMatch element : COALESCEMotifMatch.values()) {
			comboBoxknownmotifmatch.addItem(element.toString());
		}
		
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		spinnergeneprob.setValue(0.95);
		doubleTextFieldcondpvalue.setText("0.05");
		doubleTextFieldmotifpvalue.setText("0.05");
		doubleTextFieldcondzscore.setText("0.05");
		doubleTextFieldmotifzscore.setText("0.05");
		checkBoxautonormalization.setSelected(false);
		textFieldshowfastafile.setText("");
		integerTextFieldkmerlenght.setText("7");
		doubleTextFieldmotifmergepvalue.setText("0.05");
		doubleTextFieldmotifmergecutoff.setText("2.5");
		doubleTextFieldpenaltygap.setText("1.0");
		doubleTextFieldpenaltymismatch.setText("2.1");
		doubleTextFieldcorrpvalue.setText("0.05");
		integerTextFieldnpaircorrelation.setText("100000");
		textFieldseqstouse.setText("");
		integerTextFieldbaseresolution.setText("2500");
		integerTextFieldmingenecount.setText("5");
		integerTextFieldmaxmotifmergecount.setText("100");
		integerTextFieldmaxmotifclustercount.setText("1000");
		textFieldshowopenmotifsfile.setText("");
		doubleTextFieldknownmotifcutoff.setText("0.05");
		comboBoxknownmotifmatch.setSelectedIndex(0);
		spinnersimiraritycutoff.setValue(1.0);
		spinneroverlapfraction.setValue(0.5);
		spinnercoclustercutoff.setValue(1.0);
		checkBoxconvertrecs.setSelected(true);
		doubleTextFielduniformativemotif.setText("0.3");
		integerTextFieldmaxmotifmerge.setText("2500");
		
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {
		String[] propkeys=new String[]{"fasta_file",
				"condition_groupings_dataset",
				"prob_gene",
				"pvalue_cond",
				"pvalue_motif",
				"zscore_cond",
				"zscore_motif",
				"sequence_kmer_length",
				"pvalue_merge",
				"cutoff_merge",
				"penalty_gap",
				"penalty_mismatch",
				"pvalue_correl",
				"number_correl",
				"sequences_types_to_use",
				"resolution_of_bases_per_motif",
				"minimum_gene_count",
				"maximum_motif_count_merging",
				"maximum_motif_count_cluster",
				"known_motifs_file",
				"known_cutoff",
				"type_of_known_motif_matching",
				"cutoff_postprocess",
				"fraction_postprocess",
				"cutoff_trim",
				"Convert_RCs_and_RC-like_PSTs_to_single_strand",
				"uninformative_motif_threshold",
				"maximum_motifs_to_merge",
				"normalize"};
		
		String fastafilepath="";
		if(!textFieldshowfastafile.getText().isEmpty())
			fastafilepath=textFieldshowfastafile.getText();
		
		String sequences="";
		if(!textFieldseqstouse.getText().isEmpty())
			sequences=textFieldseqstouse.getText();
		
		String motiffilepath="";
		if(!textFieldshowopenmotifsfile.getText().isEmpty())
			motiffilepath=textFieldshowopenmotifsfile.getText();
		
		String[] defaultvalues=new String[]{fastafilepath,
				"",
				String.valueOf(spinnergeneprob.getValue()),
				doubleTextFieldcondpvalue.getText(),
				doubleTextFieldmotifpvalue.getText(),
				doubleTextFieldcondzscore.getText(),
				doubleTextFieldmotifzscore.getText(),
				integerTextFieldkmerlenght.getText(),
				doubleTextFieldmotifmergepvalue.getText(),
				doubleTextFieldmotifmergecutoff.getText(),
				doubleTextFieldpenaltygap.getText(),
				doubleTextFieldpenaltymismatch.getText(),
				doubleTextFieldcorrpvalue.getText(),
				integerTextFieldnpaircorrelation.getText(),
				sequences,
				integerTextFieldbaseresolution.getText(),
				integerTextFieldmingenecount.getText(),
				integerTextFieldmaxmotifmergecount.getText(),
				integerTextFieldmaxmotifclustercount.getText(),
				motiffilepath,
				doubleTextFieldknownmotifcutoff.getText(),
				(String) comboBoxknownmotifmatch.getSelectedItem(),
				String.valueOf(spinnersimiraritycutoff.getValue()),
				String.valueOf(spinneroverlapfraction.getValue()),
				String.valueOf(spinnercoclustercutoff.getValue()),
				String.valueOf(checkBoxconvertrecs.isSelected()),
				doubleTextFielduniformativemotif.getText(),
				integerTextFieldmaxmotifmerge.getText(),
				String.valueOf(checkBoxautonormalization.isSelected())
		};
		
		String[] comments=new String[] {
			"Input FASTA file",
			"Condition groupings into dataset blocks",
			"Probability threshhold for gene inclusion",
			"P-value threshold for condition inclusion",
			"P-value threshold for motif inclusion",
			"Z-score threshold for condition inclusion",
			"Z-score threshhold for motif inclusion",
			"Sequence kmer length",
			"P-value threshhold for motif merging",
			"Edit distance cutoff for motif merging", 
			"Edit distance penalty for gaps",
			"Edit distance penalty for mismatches",  
			"P-value threshhold for significant correlation",  
			"Maximum number of pairs to sample for significant correlation",
			"Sequence types to use (comma separated)",
			"Resolution of bases per motif match",
			"Minimum gene count for clusters of interest", 
			"Maximum motif count for realtime merging",  
			"Maximum motif count to consider a cluster", 
			"File containing known motifs",
			"Score cutoff for known motif labeling",  
			"Type of known motif matching  (possible values: pvalue, rmse, js)", 
			"Similarity cutoff for cluster merging",  
			"Overlap fraction for postprocessing gene/condition inclusion",
			"Cocluster stdev cutoff for cluster trimming",
			"Convert RCs and RC-like PSTs to single strand",
			"Uninformative motif threshold (bits)",
			"Maximum motifs to merge exactly",  
			"Automatically detect/normalize single channel data (false or true)"
         };
		
		
		String[] propsubkeys=new String[]{"-f","-d","-p","-c","-m","-C","-M","-k","-g","-G","-y","-Y","-n","-N","-q","-b","-z","-E","-Z","-K","-F","-S","-J","-L","-T","-R","-u","-x","-e" };
		
		return AlgorithmProperties.setupProperties(propkeys, defaultvalues,comments,propsubkeys);
	}







	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(doubleTextFieldcondpvalue.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldcondpvalue.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldmotifpvalue.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldmotifpvalue.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldcondzscore.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldcondzscore.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldmotifzscore.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldmotifzscore.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldkmerlenght.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldkmerlenght.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldmotifmergepvalue.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldmotifmergepvalue.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldmotifmergecutoff.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldmotifmergecutoff.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldpenaltygap.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldpenaltygap.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldpenaltymismatch.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldpenaltymismatch.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldcorrpvalue.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldcorrpvalue.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldnpaircorrelation.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldnpaircorrelation.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldbaseresolution.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldbaseresolution.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldmingenecount.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldmingenecount.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldmaxmotifmergecount.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldmaxmotifmergecount.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldmaxmotifclustercount.getText().isEmpty() && !textFieldshowfastafile.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldmaxmotifclustercount.getToolTipText()+" parameter must have a value");
		else if(doubleTextFieldknownmotifcutoff.getText().isEmpty() && !textFieldshowopenmotifsfile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFieldknownmotifcutoff.getToolTipText()+" parameter must have a value");
		else if(doubleTextFielduniformativemotif.getText().isEmpty()  && !textFieldshowopenmotifsfile.getText().isEmpty())
			return new Pair<Boolean, String>(false, doubleTextFielduniformativemotif.getToolTipText()+" parameter must have a value");
		else if(integerTextFieldmaxmotifmerge.getText().isEmpty()  && !textFieldshowopenmotifsfile.getText().isEmpty())
			return new Pair<Boolean, String>(false, integerTextFieldmaxmotifmerge.getToolTipText()+" parameter must have a value");
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
		return new Dimension(750,700);
	}

}
