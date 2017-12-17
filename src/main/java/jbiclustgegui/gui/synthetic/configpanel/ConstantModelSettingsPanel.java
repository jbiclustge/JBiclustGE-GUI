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

import jbiclustge.analysis.syntheticdata.factory.modeltype.ConstantSyntheticDataBuilder;
import jbiclustgegui.gui.synthetic.containers.ConstantAdditiveRowAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantUPModelContainer;
import pt.ornrocha.swingutils.textfield.IntegerTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstantModelSettingsPanel.
 */
public class ConstantModelSettingsPanel extends AbstractModelDataFactorySettingsPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl mean of data. */
	private JLabel lblMeanOfData;
	
	/** The double text field. */
	protected DoubleTextField doubleTextField;
	
	/** The lbl standard deviation of. */
	private JLabel lblStandardDeviationOf;
	
	/** The double text field 1. */
	protected DoubleTextField doubleTextField_1;
	
	/** The lbl constant signal. */
	private JLabel lblConstantSignal;
	
	/** The integer text fieldconstsignal. */
	private IntegerTextField integerTextFieldconstsignal;
	
	
	/**
	 * Instantiates a new constant model settings panel.
	 */
	public ConstantModelSettingsPanel() {
	   setBorder(new TitledBorder(null, "Biclusters data distribution", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//initGUI();
	}

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblMeanOfData = new JLabel("Mean of the data distribution: ");
		GridBagConstraints gbc_lblMeanOfData = new GridBagConstraints();
		gbc_lblMeanOfData.anchor = GridBagConstraints.EAST;
		gbc_lblMeanOfData.gridwidth = 2;
		gbc_lblMeanOfData.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeanOfData.gridx = 0;
		gbc_lblMeanOfData.gridy = 0;
		add(this.lblMeanOfData, gbc_lblMeanOfData);
		
		this.doubleTextField = new DoubleTextField();
		this.doubleTextField.setText("0.0");
		GridBagConstraints gbc_doubleTextField = new GridBagConstraints();
		gbc_doubleTextField.gridwidth = 2;
		gbc_doubleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextField.gridx = 2;
		gbc_doubleTextField.gridy = 0;
		add(this.doubleTextField, gbc_doubleTextField);
		
		this.lblStandardDeviationOf = new JLabel("Standard Deviation of data distribution:");
		GridBagConstraints gbc_lblStandardDeviationOf = new GridBagConstraints();
		gbc_lblStandardDeviationOf.anchor = GridBagConstraints.EAST;
		gbc_lblStandardDeviationOf.gridwidth = 2;
		gbc_lblStandardDeviationOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblStandardDeviationOf.gridx = 0;
		gbc_lblStandardDeviationOf.gridy = 1;
		add(this.lblStandardDeviationOf, gbc_lblStandardDeviationOf);
		
		this.doubleTextField_1 = new DoubleTextField();
		this.doubleTextField_1.setText("1.0");
		GridBagConstraints gbc_doubleTextField_1 = new GridBagConstraints();
		gbc_doubleTextField_1.gridwidth = 2;
		gbc_doubleTextField_1.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextField_1.gridx = 2;
		gbc_doubleTextField_1.gridy = 1;
		add(this.doubleTextField_1, gbc_doubleTextField_1);
		
		this.lblConstantSignal = new JLabel("Constant Signal:");
		GridBagConstraints gbc_lblConstantSignal = new GridBagConstraints();
		gbc_lblConstantSignal.gridwidth = 2;
		gbc_lblConstantSignal.anchor = GridBagConstraints.EAST;
		gbc_lblConstantSignal.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstantSignal.gridx = 0;
		gbc_lblConstantSignal.gridy = 2;
		add(this.lblConstantSignal, gbc_lblConstantSignal);
		
		this.integerTextFieldconstsignal = new IntegerTextField();
		this.integerTextFieldconstsignal.setText("0");
		GridBagConstraints gbc_integerTextFieldconstsignal = new GridBagConstraints();
		gbc_integerTextFieldconstsignal.gridwidth = 2;
		gbc_integerTextFieldconstsignal.insets = new Insets(0, 0, 5, 5);
		gbc_integerTextFieldconstsignal.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldconstsignal.gridx = 2;
		gbc_integerTextFieldconstsignal.gridy = 2;
		add(this.integerTextFieldconstsignal, gbc_integerTextFieldconstsignal);
		/*integerTextFieldconstsignal.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent e) {
		    	
		    }
		    public void removeUpdate(DocumentEvent e) {
		       //whatever you want
		    }
		    
		    public void insertUpdate(DocumentEvent e) {
		    	if(!integerTextFieldconstsignal.getText().isEmpty()) {
		    		int currentvalue=integerTextFieldconstsignal.getIntegerValue();
		    		
		    		int meanvalue=(int)doubleTextField.getDoubleValue();
		    		int 
		    		if()
		    	
		    	}
		    }
		

		});*/
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getMandatoryOptions()
	 */
	@Override
	public Map<String, Object> getMandatoryOptions() {
		
		HashMap<String, Object> res=new HashMap<>();
		res.put(ConstantModelContainer.BICMEANDIST, doubleTextField.getDoubleValue());
		res.put(ConstantModelContainer.BICSDDIST, doubleTextField_1.getDoubleValue());
		res.put(ConstantModelContainer.CONSTANTSIGNAL, integerTextFieldconstsignal.getIntegerValue());
		return res;
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getDimensions()
	 */
	@Override
	public Dimension getDimensions() {
		return new Dimension(450, 200);
	}
	
	
	
	

}
