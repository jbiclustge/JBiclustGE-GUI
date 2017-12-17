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
package jbiclustgegui.gui.synthetic.configpanel;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jbiclustgegui.gui.synthetic.containers.ConstantUPModelContainer;
import pt.ornrocha.swingutils.textfield.IntegerTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstantUpModelSettingsPanel.
 */
public class ConstantUpModelSettingsPanel extends AbstractModelDataFactorySettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The chckbx use same constant. */
	private JCheckBox chckbxUseSameConstant;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The chckbx use diferent constants. */
	private JCheckBox chckbxUseDiferentConstants;
	
	/** The integer text field. */
	private IntegerTextField integerTextField;
	
	/** The constantbictable. */
	private ConstantBiclustersTable constantbictable;
	
	/** The label. */
	private JLabel label;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The double text fieldbicdatamean. */
	private DoubleTextField doubleTextFieldbicdatamean;
	
	/** The double text fieldbicdatasd. */
	private DoubleTextField doubleTextFieldbicdatasd;
	
	
	/**
	 * Instantiates a new constant up model settings panel.
	 *
	 * @param numberbiclusters the numberbiclusters
	 */
	public ConstantUpModelSettingsPanel(int numberbiclusters) {
	   super(numberbiclusters);
		//initGUI();
	}

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		
		this.chckbxUseSameConstant = new JCheckBox("Use same constant in all biclusters:");
		this.chckbxUseSameConstant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxUseSameConstant.isSelected()) {
					integerTextField.setEnabled(true);
					constantbictable.replaceAllBiclusterConstantValue(integerTextField.getIntegerValue());
					chckbxUseDiferentConstants.setSelected(false);
					constantbictable.setEnabled(false);
				}
				else {
					integerTextField.setEnabled(false);
					chckbxUseDiferentConstants.setSelected(true);
					constantbictable.setEnabled(true);
				}
				
			}
		});
		GridBagConstraints gbc_chckbxUseSameConstant = new GridBagConstraints();
		gbc_chckbxUseSameConstant.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseSameConstant.gridwidth = 3;
		gbc_chckbxUseSameConstant.insets = new Insets(0, 20, 5, 5);
		gbc_chckbxUseSameConstant.gridx = 0;
		gbc_chckbxUseSameConstant.gridy = 0;
		add(this.chckbxUseSameConstant, gbc_chckbxUseSameConstant);
		
		this.integerTextField = new IntegerTextField();
		this.integerTextField.setText("6");
		GridBagConstraints gbc_integerTextField = new GridBagConstraints();
		gbc_integerTextField.gridwidth = 2;
		gbc_integerTextField.insets = new Insets(0, 0, 5, 5);
		gbc_integerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextField.gridx = 3;
		gbc_integerTextField.gridy = 0;
		add(this.integerTextField, gbc_integerTextField);
		integerTextField.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent e) {
		    	
		    }
		    public void removeUpdate(DocumentEvent e) {
		       //whatever you want
		    }
		    
		    public void insertUpdate(DocumentEvent e) {
		    	for (int i = 0; i < numberbics; i++) {
					constantbictable.replaceBiclusterConstantValue(i,integerTextField.getIntegerValue());
				}
		    	
		    }
		

		});
		
		this.chckbxUseDiferentConstants = new JCheckBox("Use diferent constants in each bicluster");
		this.chckbxUseDiferentConstants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxUseDiferentConstants.isSelected()) {
					integerTextField.setEnabled(false);
					chckbxUseSameConstant.setSelected(false);
					constantbictable.setEnabled(true);
				}
				else {
					integerTextField.setEnabled(true);
					chckbxUseSameConstant.setSelected(true);
					constantbictable.setEnabled(false);
					constantbictable.replaceAllBiclusterConstantValue(integerTextField.getIntegerValue());
				}
				
			}
		});
		GridBagConstraints gbc_chckbxUseDiferentConstants = new GridBagConstraints();
		gbc_chckbxUseDiferentConstants.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseDiferentConstants.gridwidth = 5;
		gbc_chckbxUseDiferentConstants.insets = new Insets(0, 20, 5, 5);
		gbc_chckbxUseDiferentConstants.gridx = 0;
		gbc_chckbxUseDiferentConstants.gridy = 1;
		add(this.chckbxUseDiferentConstants, gbc_chckbxUseDiferentConstants);
		
		constantbictable=new ConstantBiclustersTable();
		this.scrollPane = new JScrollPane(constantbictable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 20, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(this.scrollPane, gbc_scrollPane);
		constantbictable.setEnabled(false);
		
		this.label = new JLabel("Mean of the data distribution: ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridwidth = 3;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 7;
		add(this.label, gbc_label);
		
		this.doubleTextFieldbicdatamean = new DoubleTextField();
		this.doubleTextFieldbicdatamean.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldbicdatamean = new GridBagConstraints();
		gbc_doubleTextFieldbicdatamean.gridwidth = 2;
		gbc_doubleTextFieldbicdatamean.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldbicdatamean.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldbicdatamean.gridx = 3;
		gbc_doubleTextFieldbicdatamean.gridy = 7;
		add(this.doubleTextFieldbicdatamean, gbc_doubleTextFieldbicdatamean);
		
		this.label_1 = new JLabel("Standard Deviation of data distribution:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.gridwidth = 3;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 8;
		add(this.label_1, gbc_label_1);
		
		this.doubleTextFieldbicdatasd = new DoubleTextField();
		this.doubleTextFieldbicdatasd.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldbicdatasd = new GridBagConstraints();
		gbc_doubleTextFieldbicdatasd.gridwidth = 2;
		gbc_doubleTextFieldbicdatasd.insets = new Insets(0, 0, 0, 5);
		gbc_doubleTextFieldbicdatasd.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldbicdatasd.gridx = 3;
		gbc_doubleTextFieldbicdatasd.gridy = 8;
		add(this.doubleTextFieldbicdatasd, gbc_doubleTextFieldbicdatasd);
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		chckbxUseSameConstant.setSelected(true);
		
		for (int i = 0; i < numberbics; i++) {
			constantbictable.appendBiclusterWithConstantToTable(integerTextField.getIntegerValue());
		}
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getMandatoryOptions()
	 */
	@Override
	public Map<String, Object> getMandatoryOptions() {
		
		HashMap<String, Object> res=new HashMap<>();
		res.put(ConstantUPModelContainer.CONSTANTUPBICLUSTERSIGNAL, constantbictable.getBiclustersSignal());
		res.put(ConstantUPModelContainer.BICMEANDIST, doubleTextFieldbicdatamean.getDoubleValue());
		res.put(ConstantUPModelContainer.BICSDDIST, doubleTextFieldbicdatasd.getDoubleValue());
		return res;
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getDimensions()
	 */
	@Override
	public Dimension getDimensions() {
		return new Dimension(450, 450);
	}



	

}
