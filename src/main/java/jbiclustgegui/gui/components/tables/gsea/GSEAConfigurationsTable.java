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
package jbiclustgegui.gui.components.tables.gsea;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JTable;

import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAConfigurationsTable.
 */
public class GSEAConfigurationsTable extends JTable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/** The configs. */
	private ArrayList<GSEAConfigurationContainer> configs;
	
	/**
	 * Instantiates a new GSEA configurations table.
	 */
	public GSEAConfigurationsTable() {
		tablemodel=new GenericTableModel(new String[] {"Configuration"});
		setModel(tablemodel);
		this.configs=new ArrayList<>();
	}
	
	
	/**
	 * Reset to default.
	 */
	public void resetToDefault() {
		tablemodel.resetTable();
		configs=null;
	}
	
	
	/**
	 * Adds the configuration.
	 *
	 * @param config the config
	 */
	public void addConfiguration(GSEAConfigurationContainer config) {
		if(configs==null)
			configs=new ArrayList<>();
		
		configs.add(config);
		Object[] row=new Object[] {config.getAnalyserType().toString()};
		tablemodel.addRow(row);
	}
	
	/**
	 * Removes the configuration.
	 *
	 * @param index the index
	 */
	public void removeConfiguration(int index) {
		configs.remove(index);
		tablemodel.removeRowAtPos(index);
	}
	
	/**
	 * Gets the selected configuration.
	 *
	 * @return the selected configuration
	 */
	public GSEAConfigurationContainer getSelectedConfiguration() {
		int pos=getSelectedRow();
		if(pos>-1 && pos<getRowCount()) {
			return configs.get(pos);
		}
		return null;
	}
	
	/**
	 * Gets the GSEA configurations.
	 *
	 * @return the GSEA configurations
	 */
	public ArrayList<GSEAConfigurationContainer> getGSEAConfigurations(){
		return configs;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
	 */
	@Override
	public String getToolTipText(MouseEvent e) {
		
    	String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        
        GSEAConfigurationContainer container=configs.get(rowIndex);
        
        StringBuilder str=new StringBuilder();
        Properties props=container.getConfig();
        
        for (String propname  : props.stringPropertyNames()) {
			str.append(propname+" = "+props.getProperty(propname)+"\n");
		}
        
    	
    	return str.toString();
	}

}
