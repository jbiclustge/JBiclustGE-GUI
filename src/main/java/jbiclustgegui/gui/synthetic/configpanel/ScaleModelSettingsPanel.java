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
 * The Class ScaleModelSettingsPanel.
 */
public class ScaleModelSettingsPanel extends AbstractModelDataFactorySettingsPanel{
	
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
	
	/** The double text fieldscalemean. */
	private DoubleTextField doubleTextFieldscalemean;
	
	/** The double text fieldscalesd. */
	private DoubleTextField doubleTextFieldscalesd;
	
	/** The double text fieldcolumnmean. */
	private DoubleTextField doubleTextFieldcolumnmean;
	
	/** The double text fieldclumnsd. */
	private DoubleTextField doubleTextFieldclumnsd;
	
	
	/**
	 * Instantiates a new scale model settings panel.
	 */
	public ScaleModelSettingsPanel() {
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
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
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
		gbc_doubleTextFielddatamean.insets = new Insets(0, 0, 5, 0);
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
		gbc_doubleTextFielddatasd.insets = new Insets(0, 0, 5, 0);
		gbc_doubleTextFielddatasd.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFielddatasd.gridx = 1;
		gbc_doubleTextFielddatasd.gridy = 1;
		add(this.doubleTextFielddatasd, gbc_doubleTextFielddatasd);
		
		this.lblScaleMean = new JLabel("Scale mean:");
		GridBagConstraints gbc_lblScaleMean = new GridBagConstraints();
		gbc_lblScaleMean.anchor = GridBagConstraints.EAST;
		gbc_lblScaleMean.insets = new Insets(0, 0, 5, 5);
		gbc_lblScaleMean.gridx = 0;
		gbc_lblScaleMean.gridy = 2;
		add(this.lblScaleMean, gbc_lblScaleMean);
		
		this.doubleTextFieldscalemean = new DoubleTextField();
		this.doubleTextFieldscalemean.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldscalemean = new GridBagConstraints();
		gbc_doubleTextFieldscalemean.gridwidth = 2;
		gbc_doubleTextFieldscalemean.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldscalemean.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldscalemean.gridx = 1;
		gbc_doubleTextFieldscalemean.gridy = 2;
		add(this.doubleTextFieldscalemean, gbc_doubleTextFieldscalemean);
		
		this.lblScaleSd = new JLabel("Scale SD:");
		this.lblScaleSd.setToolTipText("Scale Standard Deviation");
		GridBagConstraints gbc_lblScaleSd = new GridBagConstraints();
		gbc_lblScaleSd.anchor = GridBagConstraints.EAST;
		gbc_lblScaleSd.insets = new Insets(0, 0, 5, 5);
		gbc_lblScaleSd.gridx = 0;
		gbc_lblScaleSd.gridy = 3;
		add(this.lblScaleSd, gbc_lblScaleSd);
		
		this.doubleTextFieldscalesd = new DoubleTextField();
		this.doubleTextFieldscalesd.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldscalesd = new GridBagConstraints();
		gbc_doubleTextFieldscalesd.gridwidth = 2;
		gbc_doubleTextFieldscalesd.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldscalesd.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldscalesd.gridx = 1;
		gbc_doubleTextFieldscalesd.gridy = 3;
		add(this.doubleTextFieldscalesd, gbc_doubleTextFieldscalesd);
		
		this.lblColumnMean = new JLabel("Column mean:");
		GridBagConstraints gbc_lblColumnMean = new GridBagConstraints();
		gbc_lblColumnMean.anchor = GridBagConstraints.EAST;
		gbc_lblColumnMean.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnMean.gridx = 0;
		gbc_lblColumnMean.gridy = 4;
		add(this.lblColumnMean, gbc_lblColumnMean);
		
		this.doubleTextFieldcolumnmean = new DoubleTextField();
		this.doubleTextFieldcolumnmean.setText("0.0");
		GridBagConstraints gbc_doubleTextFieldcolumnmean = new GridBagConstraints();
		gbc_doubleTextFieldcolumnmean.gridwidth = 2;
		gbc_doubleTextFieldcolumnmean.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldcolumnmean.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldcolumnmean.gridx = 1;
		gbc_doubleTextFieldcolumnmean.gridy = 4;
		add(this.doubleTextFieldcolumnmean, gbc_doubleTextFieldcolumnmean);
		
		this.lblColumnSd = new JLabel("Column SD:");
		this.lblColumnSd.setToolTipText("Column Standard Deviation");
		GridBagConstraints gbc_lblColumnSd = new GridBagConstraints();
		gbc_lblColumnSd.anchor = GridBagConstraints.EAST;
		gbc_lblColumnSd.insets = new Insets(0, 0, 0, 5);
		gbc_lblColumnSd.gridx = 0;
		gbc_lblColumnSd.gridy = 5;
		add(this.lblColumnSd, gbc_lblColumnSd);
		
		this.doubleTextFieldclumnsd = new DoubleTextField();
		this.doubleTextFieldclumnsd.setText("1.0");
		GridBagConstraints gbc_doubleTextFieldclumnsd = new GridBagConstraints();
		gbc_doubleTextFieldclumnsd.gridwidth = 2;
		gbc_doubleTextFieldclumnsd.insets = new Insets(0, 0, 0, 5);
		gbc_doubleTextFieldclumnsd.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldclumnsd.gridx = 1;
		gbc_doubleTextFieldclumnsd.gridy = 5;
		add(this.doubleTextFieldclumnsd, gbc_doubleTextFieldclumnsd);
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
		res.put(ScaleModelContainer.BICMEANDIST, doubleTextFielddatamean.getDoubleValue());
		res.put(ScaleModelContainer.BICSDDIST, doubleTextFielddatasd.getDoubleValue());
		res.put(ScaleModelContainer.SCALEMEAN,doubleTextFieldscalemean.getDoubleValue());
		res.put(ScaleModelContainer.SCALESD, doubleTextFieldscalesd.getDoubleValue());
		res.put(ScaleModelContainer.COLUMNMEAN, doubleTextFieldcolumnmean.getDoubleValue());
		res.put(ScaleModelContainer.COLUMNSD, doubleTextFieldclumnsd.getDoubleValue());
		return res;
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.configpanel.AbstractModelDataFactorySettingsPanel#getDimensions()
	 */
	@Override
	public Dimension getDimensions() {
		return new Dimension(450, 400);
	}
	

}
