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
package jbiclustgegui.gui.components.panels.biclusters;

import javax.swing.JPanel;

import jbiclustge.results.biclusters.containers.BiclusterList;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusterMethodSettingsInfoPanel.
 */
public class BiclusterMethodSettingsInfoPanel extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list. */
	private JList list;
	
	
	/**
	 * Instantiates a new bicluster method settings info panel.
	 */
	public BiclusterMethodSettingsInfoPanel() {
		initGUI();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0,0.05};
		setLayout(gridBagLayout);
		
		this.list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		add(this.list, gbc_list);
	}
	
	/**
	 * Adds the bicluster list.
	 *
	 * @param results the results
	 */
	public void addBiclusterList(BiclusterList results) {
		
		LinkedHashMap<String, String> settings =results.getMethodRunningParameters();
		DefaultListModel<String> model = new DefaultListModel<>();
		if(settings!=null) {
			for (String key : settings.keySet()) {
				String showlabelText=key.replace("_", " ");
				String in=showlabelText+" = "+settings.get(key);
				model.addElement(in);
			}
			
		}
		else {
			model.addElement("Information is not available");
		}
		list.setModel(model);
	}
	

}
