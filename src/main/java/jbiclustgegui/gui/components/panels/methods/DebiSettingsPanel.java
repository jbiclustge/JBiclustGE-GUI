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
import jbiclustge.methods.algorithms.r.components.BCSpectralNormalizationMethod;
import jbiclustge.methods.algorithms.wrappers.components.RegulationPattern;
import jbiclustge.methods.algorithms.wrappers.debi.DebiMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

import javax.swing.JComboBox;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class DebiSettingsPanel.
 */
public class DebiSettingsPanel extends AbstractMethodSettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text fieldminconds. */
	private IntegerTextField integerTextFieldminconds;
	
	/** The lbl overlap treshold. */
	private JLabel lblOverlapTreshold;
	
	/** The lbl max overlap. */
	private JLabel lblMaxOverlap;
	
	/** The spinneroverlap. */
	private JSpinner spinneroverlap;
	
	/** The lbl binarization level. */
	private JLabel lblBinarizationLevel;
	
	/** The double text fieldbinlevel. */
	private DoubleTextField doubleTextFieldbinlevel;
	
	/** The lbl regularization pattern. */
	private JLabel lblRegularizationPattern;
	
	/** The combo boxregpattern. */
	private JComboBox comboBoxregpattern;
	
	/** The check boxusebimaxbin. */
	private JCheckBox checkBoxusebimaxbin;

	/**
	 * Create the panel.
	 */
	public DebiSettingsPanel() {
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
		
		this.lblMaxOverlap = new JLabel("Max. overlap:");
		this.lblMaxOverlap.setToolTipText("<html>Maximum overlap level of the biclusters default=0.5 <br>(overlap=1: no overlap is allowed, overlap=0: all the biclusters are taken, no filtering applied)</html>");
		GridBagConstraints gbc_lblMaxOverlap = new GridBagConstraints();
		gbc_lblMaxOverlap.anchor = GridBagConstraints.EAST;
		gbc_lblMaxOverlap.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxOverlap.gridx = 0;
		gbc_lblMaxOverlap.gridy = 0;
		add(this.lblMaxOverlap, gbc_lblMaxOverlap);
		
		this.spinneroverlap = new JSpinner();
		this.spinneroverlap.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinneroverlap = new GridBagConstraints();
		gbc_spinneroverlap.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinneroverlap.insets = new Insets(0, 0, 5, 0);
		gbc_spinneroverlap.gridx = 1;
		gbc_spinneroverlap.gridy = 0;
		add(this.spinneroverlap, gbc_spinneroverlap);
		
		this.lblBinarizationLevel = new JLabel("Binarization level:");
		this.lblBinarizationLevel.setToolTipText("Binarization level (for binary data not needed otherwise it must be defined)");
		GridBagConstraints gbc_lblBinarizationLevel = new GridBagConstraints();
		gbc_lblBinarizationLevel.anchor = GridBagConstraints.EAST;
		gbc_lblBinarizationLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblBinarizationLevel.gridx = 0;
		gbc_lblBinarizationLevel.gridy = 1;
		add(this.lblBinarizationLevel, gbc_lblBinarizationLevel);
		
		this.doubleTextFieldbinlevel = new DoubleTextField();
		GridBagConstraints gbc_doubleTextFieldbinlevel = new GridBagConstraints();
		gbc_doubleTextFieldbinlevel.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFieldbinlevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldbinlevel.gridx = 1;
		gbc_doubleTextFieldbinlevel.gridy = 1;
		add(this.doubleTextFieldbinlevel, gbc_doubleTextFieldbinlevel);
		
		this.lblRegularizationPattern = new JLabel("Regulation pattern:");
		this.lblRegularizationPattern.setToolTipText("Pattern of regulation: up, down or both (up, down, both) default=both");
		GridBagConstraints gbc_lblRegularizationPattern = new GridBagConstraints();
		gbc_lblRegularizationPattern.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegularizationPattern.anchor = GridBagConstraints.EAST;
		gbc_lblRegularizationPattern.gridx = 0;
		gbc_lblRegularizationPattern.gridy = 2;
		add(this.lblRegularizationPattern, gbc_lblRegularizationPattern);
		
		this.comboBoxregpattern = new JComboBox();
		GridBagConstraints gbc_comboBoxregpattern = new GridBagConstraints();
		gbc_comboBoxregpattern.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxregpattern.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxregpattern.gridx = 1;
		gbc_comboBoxregpattern.gridy = 2;
		add(this.comboBoxregpattern, gbc_comboBoxregpattern);
		
		this.label_1 = new JLabel("Min. Conditions:");
		this.label_1.setToolTipText("<html>Minimum number of conditions in biclusters,<br> if 0, it is automatically calculated, but the minimum value will be allways 5,  default=0</html>");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 3;
		add(this.label_1, gbc_label_1);
		
		this.integerTextFieldminconds = new IntegerTextField();
		this.integerTextFieldminconds.setColumns(10);
		GridBagConstraints gbc_integerTextFieldminconds = new GridBagConstraints();
		gbc_integerTextFieldminconds.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldminconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldminconds.gridx = 1;
		gbc_integerTextFieldminconds.gridy = 3;
		add(this.integerTextFieldminconds, gbc_integerTextFieldminconds);
		
		this.lblOverlapTreshold = new JLabel("Use BiMax binarization method:");
		this.lblOverlapTreshold.setToolTipText("Use same binarization method of bimax");
		GridBagConstraints gbc_lblOverlapTreshold = new GridBagConstraints();
		gbc_lblOverlapTreshold.anchor = GridBagConstraints.EAST;
		gbc_lblOverlapTreshold.insets = new Insets(0, 10, 0, 5);
		gbc_lblOverlapTreshold.gridx = 0;
		gbc_lblOverlapTreshold.gridy = 4;
		add(this.lblOverlapTreshold, gbc_lblOverlapTreshold);
		
		this.checkBoxusebimaxbin = new JCheckBox("");
		GridBagConstraints gbc_checkBoxusebimaxbin = new GridBagConstraints();
		gbc_checkBoxusebimaxbin.anchor = GridBagConstraints.WEST;
		gbc_checkBoxusebimaxbin.gridx = 1;
		gbc_checkBoxusebimaxbin.gridy = 4;
		add(this.checkBoxusebimaxbin, gbc_checkBoxusebimaxbin);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		spinneroverlap.setValue(0.5);
		doubleTextFieldbinlevel.setText("1.0");
		comboBoxregpattern.setSelectedItem(RegulationPattern.BOTH.getName());
		integerTextFieldminconds.setText("0");
		checkBoxusebimaxbin.setSelected(false);
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
		
		for (RegulationPattern element : RegulationPattern.values()) {
			comboBoxregpattern.addItem(element.getName());
		}
		
	}

	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {

		String[] propkeys=new String[]{
				DebiMethod.MAXOVERLAP,
				DebiMethod.BINARIZATIONLEVEL,
				DebiMethod.REGULATIONPATTERN,
				DebiMethod.MINCONDSBIC,
				DebiMethod.BINARIZEWITHBIMAXMETHOD
		};
		
		
		String[] values=new String[]{String.valueOf(spinneroverlap.getValue()),
				doubleTextFieldbinlevel.getText(),
				(String) comboBoxregpattern.getSelectedItem(),
				integerTextFieldminconds.getText(),
				String.valueOf(checkBoxusebimaxbin.isSelected())};
		
		String[] comments=new String[] {
				"Maximum overlap level of the biclusters default=0.5 (overlap=1: no overlap is allowed, overlap=0: all the biclusters are taken, no filtering applied)",
				"binarization level (for binary data not needed otherwise it must be defined)",
				"Pattern of regulation up, down or both (up, down, both) default=both",
				"Minimum number of conditions in biclusters, if 0, it is automatically calculated, but the minimum value will be allways 5,  default=0",
				"Use same binarization method of bimax"
			
		};
		
		String source="source: https://owww.molgen.mpg.de/~serin/debi/main.html";
		return AlgorithmProperties.setupProperties(propkeys, values, comments,source);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(doubleTextFieldbinlevel.getText().isEmpty() || Double.parseDouble(doubleTextFieldbinlevel.getText())<=0)
		return new Pair<Boolean, String>(false, "The value for the binarization level must be higher than 0");
		else if(integerTextFieldminconds.getText().isEmpty() || (Integer.parseInt(integerTextFieldminconds.getText())!=0 && Integer.parseInt(integerTextFieldminconds.getText())<5))
			return new Pair<Boolean, String>(false, "The value for the minimum number of conditions must be 0 or higher than 4");
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
		return new Dimension(450,380);
	}

}
