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
package jbiclustgegui.gui.components.panels.enrichmentanalysis;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import pt.ornrocha.propertyutils.EnhancedProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEASettingsInfoPanel.
 */
public class GSEASettingsInfoPanel extends JPanel{
	
	/** The lbl results analysed. */
	private JLabel lblResultsAnalysed;
	
	/** The text fieldresanalysed. */
	private JTextField textFieldresanalysed;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The text fieldengine. */
	private JTextField textFieldengine;
	
	/** The list. */
	private JList list;
	
	
	/**
	 * Instantiates a new GSEA settings info panel.
	 */
	public GSEASettingsInfoPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblResultsAnalysed = new JLabel("Results Analysed:");
		GridBagConstraints gbc_lblResultsAnalysed = new GridBagConstraints();
		gbc_lblResultsAnalysed.anchor = GridBagConstraints.EAST;
		gbc_lblResultsAnalysed.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultsAnalysed.gridx = 1;
		gbc_lblResultsAnalysed.gridy = 0;
		add(this.lblResultsAnalysed, gbc_lblResultsAnalysed);
		
		this.textFieldresanalysed = new JTextField();
		GridBagConstraints gbc_textFieldresanalysed = new GridBagConstraints();
		gbc_textFieldresanalysed.gridwidth = 2;
		gbc_textFieldresanalysed.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldresanalysed.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldresanalysed.gridx = 2;
		gbc_textFieldresanalysed.gridy = 0;
		add(this.textFieldresanalysed, gbc_textFieldresanalysed);
		this.textFieldresanalysed.setColumns(10);
		
		this.lblNewLabel = new JLabel("Gene Enrichment Analyser Engine:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		add(this.lblNewLabel, gbc_lblNewLabel);
		
		this.textFieldengine = new JTextField();
		GridBagConstraints gbc_textFieldengine = new GridBagConstraints();
		gbc_textFieldengine.gridwidth = 2;
		gbc_textFieldengine.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldengine.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldengine.gridx = 5;
		gbc_textFieldengine.gridy = 0;
		add(this.textFieldengine, gbc_textFieldengine);
		this.textFieldengine.setColumns(10);
		
		this.list = new JList();
		this.list.setBorder(new TitledBorder(null, "Settings:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 6;
		gbc_list.gridwidth = 6;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		add(this.list, gbc_list);
	}


	/**
	 * Adds the enrichment results box.
	 *
	 * @param results the results
	 */
	public void addEnrichmentResultsBox(EnrichmentAnalysisResultBox results) {
		
		textFieldresanalysed.setText((results.getAssociatedResultDatatype()!=null)?results.getAssociatedResultDatatype():"Unknown");
		textFieldengine.setText(results.getSettingsUsed().getAnalyserType().toString());
		
		
		
		EnhancedProperties configs=(EnhancedProperties) results.getSettingsUsed().getConfig();
		
		ArrayList<String> keys=configs.getOrderedInputOrderKeys();
		
		DefaultListModel<String> model = new DefaultListModel<>();
		
		
		for (String key : keys) {
			String showlabelText=key.replace("_", " ");
			String in=showlabelText+" = "+configs.getProperty(key);
			model.addElement(in);
		}
		list.setModel(model);
	}
	
	
}
