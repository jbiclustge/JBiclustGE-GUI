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
package jbiclustgegui.gui.components.tables.common;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclustersTable.
 */
public class RpackagesTable extends JTable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	LinkedHashMap<String, ArrayList<String>> packagesmap=null;
	
	
	public RpackagesTable() {
		this.tablemodel=new GenericTableModel(new String[]{"Main package","Dependencies"}, false);
		setModel(tablemodel);
	}
	
	

	public void addPackagesMap(LinkedHashMap<String, ArrayList<String>> packagesmap) {
		this.packagesmap=packagesmap;
		for (String packname : packagesmap.keySet()) {
			
			Object[] row=new Object[] {packname, packagesmap.get(packname)};
			tablemodel.addRow(row);
		}
	}
	

	@Override
	public String getToolTipText(MouseEvent e) {
		
    	String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        
       Object val= tablemodel.getValueAt(rowIndex, colIndex);
       if(!(val instanceof String))
    	   tip=String.valueOf(val);
       else
    	   tip=(String) val;
    	
    	return tip;
	}
	
	@Override
	public TableModel getModel() {
		return tablemodel;
	}
	

}
