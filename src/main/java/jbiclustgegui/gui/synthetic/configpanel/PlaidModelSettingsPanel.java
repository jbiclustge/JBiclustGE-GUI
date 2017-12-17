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

import jbiclustgegui.gui.synthetic.containers.ConstantAdditiveRowAdjModelContainer;
import jbiclustgegui.gui.synthetic.containers.ConstantUPModelContainer;
import jbiclustgegui.gui.synthetic.containers.PlaidModelContainer;
import jbiclustgegui.gui.synthetic.containers.ScaleModelContainer;
import jbiclustgegui.gui.synthetic.containers.ShiftModelContainer;
import pt.ornrocha.swingutils.textfield.IntegerTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import pt.ornrocha.swingutils.textfield.DoubleTextField;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaidModelSettingsPanel.
 */
public class PlaidModelSettingsPanel extends AbstractModelDataFactorySettingsPanel{
	
	/** The lbl mean of data. */
	private JLabel lblMeanOfData;
	
	/** The double text fielddatamean. */
	private DoubleTextField doubleTextFielddatamean;
	
	/** The lbl standard deviation of. */
	private JLabel lblStandardDeviationOf;
	
	/** The double text fielddatasd. */
	private DoubleTextField doubleTextFielddatasd;
	
	/** The lbl scale mean. */
	private JLabel lblScaleMean;
	
	/** The lbl scale sd. */
	private JLabel lblScaleSd;
	
	/** The lbl column mean. */
	private JLabel lblColumnMean;
	
	/** The lbl column sd. */
	private JLabel lblColumnSd;
	
	/** The double text fieldbiclustermeaneffect. */
	private DoubleTextField doubleTextFieldbiclustermeaneffect;
	
	/** The double text fieldbiclustersdeffect. */
	private DoubleTextField doubleTextFieldbiclustersdeffect;
	
	/** The double text fieldcolumnmeaneffect. */
	private DoubleTextField doubleTextFieldcolumnmeaneffect;
	
	/** The double text fieldclumnsdeffect. */
	private DoubleTextField doubleTextFieldclumnsdeffect;
	
	/** The lbl rows mean effect. */
	private JLabel lblRowsMeanEffect;
	
	/** The double text fieldrowmeaneffect. */
	private DoubleTextField doubleTextFieldrowmeaneffect;
	
	/** The lbl rows sd effect. */
	private JLabel lblRowsSdEffect;
	
	/** The double text fieldrowsdeffect. */
	private DoubleTextField doubleTextFieldrowsdeffect;
	
	
	/**
	 * Instantiates a new plaid model settings panel.
	 */
	public PlaidModelSettingsPanel() {
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
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblMeanOfData = new JLabel("Mean of the data distribution: ");
		GridBagConstraints gbc_lblMeanOfData = new GridBagConstraints();
		gbc_lblMeanOfData.anchor = GridBagConstraints.EAST;
		gbc_lblMeanOfData.insets = new Insets(0, 20, 5, 5);
		gbc_lblMeanOfData.gridx = 0;
		gbc_lblMeanOfData.gridy = 0;
		add(this.lblMeanOfData, gbc_lblMeanOfData);
		
		this.doubleTextFielddatamean = new DoubleTextField();
		this.doubleTextFielddatamean.setText("0.0");
		GridBagConstraints gbc_doubleTextFielddatamean = new GridBagConstraints();
		gbc_doubleTextFielddatamean.gridwidth = 2;
		gbc_doubleTextFielddatamean.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFielddatamean.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielddatamean.gridx = 1;
		gbc_doubleTextFielddatamean.gridy = 0;
		add(this.doubleTextFielddatamean, gbc_doubleTextFielddatamean);
		
		this.lblStandardDeviationOf = new JLabel("Standard Deviation of data distribution:");
		GridBagConstraints gbc_lblStandardDeviationOf = new GridBagConstraints();
		gbc_lblStandardDeviationOf.anchor = GridBagConstraints.EAST;
		gbc_lblStandardDeviationOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblStandardDeviationOf.gridx = 0;
		gbc_lblStandardDeviationOf.gridy = 1;
		add(this.lblStandardDeviationOf, gbc_lblStandardDeviationOf);
		
		this.doubleTextFielddatasd = new DoubleTextField();
		this.doubleTextFielddatasd.setText("1.0");
		GridBagConstraints gbc_doubleTextFielddatasd = new GridBagConstraints();
		gbc_doubleTextFielddatasd.gridwidth = 2;
		gbc_doubleTextFielddatasd.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFielddatasd.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielddatasd.gridx = 1;
		gbc_doubleTextFielddatasd.gridy = 1;
		add(this.doubleTextFielddatasd, gbc_doubleTextFielddatasd);
		
		this.lblScaleMean = new JLabel("Bicluster mean effect:");
		GridBagConstraints gbc_lblScaleMean = new GridBagConstraints();
		gbc_lblScaleMean.anchor = GridBagConstraints.EAST;
		gbc_lblScaleMean.insets = new Insets(0, 0, 5, 5);
		gbc_lblScaleMean.gridx = 0;
		gbc_lblScaleMean.gridy = 2;
		add(this.lblScaleMean, gbc_lblScaleMean);
		
		this.doubleTextFieldbiclustermeaneffect = new DoubleTextField();
		this.doubleTextFieldbiclustermeaneffect.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldbiclustermeaneffect = new GridBagConstraints();
		gbc_doubleTextFieldbiclustermeaneffect.gridwidth = 2;
		gbc_doubleTextFieldbiclustermeaneffect.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldbiclustermeaneffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldbiclustermeaneffect.gridx = 1;
		gbc_doubleTextFieldbiclustermeaneffect.gridy = 2;
		add(this.doubleTextFieldbiclustermeaneffect, gbc_doubleTextFieldbiclustermeaneffect);
		
		this.lblScaleSd = new JLabel("Bicluster SD effect:");
		this.lblScaleSd.setToolTipText("Bicluster Standard Deviation effect");
		GridBagConstraints gbc_lblScaleSd = new GridBagConstraints();
		gbc_lblScaleSd.anchor = GridBagConstraints.EAST;
		gbc_lblScaleSd.insets = new Insets(0, 0, 5, 5);
		gbc_lblScaleSd.gridx = 0;
		gbc_lblScaleSd.gridy = 3;
		add(this.lblScaleSd, gbc_lblScaleSd);
		
		this.doubleTextFieldbiclustersdeffect = new DoubleTextField();
		this.doubleTextFieldbiclustersdeffect.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldbiclustersdeffect = new GridBagConstraints();
		gbc_doubleTextFieldbiclustersdeffect.gridwidth = 2;
		gbc_doubleTextFieldbiclustersdeffect.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldbiclustersdeffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldbiclustersdeffect.gridx = 1;
		gbc_doubleTextFieldbiclustersdeffect.gridy = 3;
		add(this.doubleTextFieldbiclustersdeffect, gbc_doubleTextFieldbiclustersdeffect);
		
		this.lblRowsMeanEffect = new JLabel("Rows mean effect:");
		GridBagConstraints gbc_lblRowsMeanEffect = new GridBagConstraints();
		gbc_lblRowsMeanEffect.anchor = GridBagConstraints.EAST;
		gbc_lblRowsMeanEffect.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowsMeanEffect.gridx = 0;
		gbc_lblRowsMeanEffect.gridy = 4;
		add(this.lblRowsMeanEffect, gbc_lblRowsMeanEffect);
		
		this.doubleTextFieldrowmeaneffect = new DoubleTextField();
		this.doubleTextFieldrowmeaneffect.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldrowmeaneffect = new GridBagConstraints();
		gbc_doubleTextFieldrowmeaneffect.gridwidth = 2;
		gbc_doubleTextFieldrowmeaneffect.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldrowmeaneffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldrowmeaneffect.gridx = 1;
		gbc_doubleTextFieldrowmeaneffect.gridy = 4;
		add(this.doubleTextFieldrowmeaneffect, gbc_doubleTextFieldrowmeaneffect);
		
		this.lblRowsSdEffect = new JLabel("Rows SD effect:");
		this.lblRowsSdEffect.setToolTipText("Rows Standard Deviation effect");
		GridBagConstraints gbc_lblRowsSdEffect = new GridBagConstraints();
		gbc_lblRowsSdEffect.anchor = GridBagConstraints.EAST;
		gbc_lblRowsSdEffect.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowsSdEffect.gridx = 0;
		gbc_lblRowsSdEffect.gridy = 5;
		add(this.lblRowsSdEffect, gbc_lblRowsSdEffect);
		
		this.doubleTextFieldrowsdeffect = new DoubleTextField();
		this.doubleTextFieldrowsdeffect.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldrowsdeffect = new GridBagConstraints();
		gbc_doubleTextFieldrowsdeffect.gridwidth = 2;
		gbc_doubleTextFieldrowsdeffect.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldrowsdeffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldrowsdeffect.gridx = 1;
		gbc_doubleTextFieldrowsdeffect.gridy = 5;
		add(this.doubleTextFieldrowsdeffect, gbc_doubleTextFieldrowsdeffect);
		
		this.lblColumnMean = new JLabel("Columns mean effect:");
		GridBagConstraints gbc_lblColumnMean = new GridBagConstraints();
		gbc_lblColumnMean.anchor = GridBagConstraints.EAST;
		gbc_lblColumnMean.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnMean.gridx = 0;
		gbc_lblColumnMean.gridy = 6;
		add(this.lblColumnMean, gbc_lblColumnMean);
		
		this.doubleTextFieldcolumnmeaneffect = new DoubleTextField();
		this.doubleTextFieldcolumnmeaneffect.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldcolumnmeaneffect = new GridBagConstraints();
		gbc_doubleTextFieldcolumnmeaneffect.gridwidth = 2;
		gbc_doubleTextFieldcolumnmeaneffect.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldcolumnmeaneffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldcolumnmeaneffect.gridx = 1;
		gbc_doubleTextFieldcolumnmeaneffect.gridy = 6;
		add(this.doubleTextFieldcolumnmeaneffect, gbc_doubleTextFieldcolumnmeaneffect);
		
		this.lblColumnSd = new JLabel("Columns SD effect:");
		this.lblColumnSd.setToolTipText("Columns Standard Deviation effect");
		GridBagConstraints gbc_lblColumnSd = new GridBagConstraints();
		gbc_lblColumnSd.anchor = GridBagConstraints.EAST;
		gbc_lblColumnSd.insets = new Insets(0, 0, 0, 5);
		gbc_lblColumnSd.gridx = 0;
		gbc_lblColumnSd.gridy = 7;
		add(this.lblColumnSd, gbc_lblColumnSd);
		
		this.doubleTextFieldclumnsdeffect = new DoubleTextField();
		this.doubleTextFieldclumnsdeffect.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldclumnsdeffect = new GridBagConstraints();
		gbc_doubleTextFieldclumnsdeffect.gridwidth = 2;
		gbc_doubleTextFieldclumnsdeffect.insets = new Insets(0, 0, 0, 5);
		gbc_doubleTextFieldclumnsdeffect.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldclumnsdeffect.gridx = 1;
		gbc_doubleTextFieldclumnsdeffect.gridy = 7;
		add(this.doubleTextFieldclumnsdeffect, gbc_doubleTextFieldclumnsdeffect);
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
		res.put(PlaidModelContainer.BICMEANDIST, doubleTextFielddatamean.getDoubleValue());
		res.put(PlaidModelContainer.BICSDDIST, doubleTextFielddatasd.getDoubleValue());
		res.put(PlaidModelContainer.BICMEANEFFECT,doubleTextFieldbiclustermeaneffect.getDoubleValue());
		res.put(PlaidModelContainer.BICSDEFFECT, doubleTextFieldbiclustersdeffect.getDoubleValue());
		res.put(PlaidModelContainer.ROWMEANEFFECT, doubleTextFieldrowmeaneffect.getDoubleValue());
		res.put(PlaidModelContainer.ROWSDEFFECT, doubleTextFieldrowsdeffect.getDoubleValue());
		res.put(PlaidModelContainer.COLUMNMEANEFFECT, doubleTextFieldcolumnmeaneffect.getDoubleValue());
		res.put(PlaidModelContainer.COLUMNSDEFFECT, doubleTextFieldclumnsdeffect.getDoubleValue());
		return res;
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getDimensions()
	 */
	@Override
	public Dimension getDimensions() {
		return new Dimension(450, 500);
	}
	

}
