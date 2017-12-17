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
package jbiclustgegui.gui.synthetic.tables;

import java.util.ArrayList;

import javax.swing.JTable;

import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class TableDatasetSymmetricOverlapLevels.
 */
public class TableDatasetSymmetricOverlapLevels extends JTable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	
	/**
	 * Instantiates a new table dataset symmetric overlap levels.
	 */
	public TableDatasetSymmetricOverlapLevels() {
		tablemodel=new GenericTableModel(new String[] {"Symmetric Overlap Level"}, new int[] {0});
		setModel(tablemodel);
	}
	
	
	/**
	 * Adds the level cell.
	 */
	public void addLevelCell() {
		if(tablemodel.getRowCount()==0) {
			tablemodel.addRow(new Object[] {5});
		}
		else {
			int lastval=(int) tablemodel.getValueAt(tablemodel.getRowCount()-1, 0);
			int nextval=lastval+10;
			tablemodel.addRow(new Object[] {nextval});
		}
	}
	
	
	/**
	 * Clear table.
	 */
	public void clearTable() {
		tablemodel.resetTable();
	}
	
	/**
	 * Removes the level index.
	 *
	 * @param row the row
	 */
	public void removeLevelIndex(int row) {
		tablemodel.removeRowAtPos(row);
	}
	
	/**
	 * Gets the symmetric overlap experiments.
	 *
	 * @return the symmetric overlap experiments
	 */
	public ArrayList<Integer> getSymmetricOverlapExperiments(){
		ArrayList<Integer> res=new ArrayList<>();
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			Integer value=(int) tablemodel.getValueAt(i,0);
			res.add(value);
		}
		if(res.size()>0)
			return res;
		return null;
	}
	
}
