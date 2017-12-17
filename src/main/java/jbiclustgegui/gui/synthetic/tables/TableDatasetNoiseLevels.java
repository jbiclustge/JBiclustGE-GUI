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
 * The Class TableDatasetNoiseLevels.
 */
public class TableDatasetNoiseLevels extends JTable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	
	/**
	 * Instantiates a new table dataset noise levels.
	 */
	public TableDatasetNoiseLevels() {
		tablemodel=new GenericTableModel(new String[] {"Noise Level"}, new int[] {0});
		setModel(tablemodel);
	}
	
	
	/**
	 * Adds the level cell.
	 */
	public void addLevelCell() {
		if(tablemodel.getRowCount()==0) {
			tablemodel.addRow(new Object[] {0.25});
		}
		else {
			double lastval=(double) tablemodel.getValueAt(tablemodel.getRowCount()-1, 0);
			double nextval=lastval+0.25;
			if(nextval>1)
				nextval=1.0;
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
	 * Gets the noise of experiments.
	 *
	 * @return the noise of experiments
	 */
	public ArrayList<Double> getNoiseOfExperiments(){
		ArrayList<Double> res=new ArrayList<>();
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			double value=(double) tablemodel.getValueAt(i,0);
			if(value<0)
				value=0;
			res.add(value);
		}
		if(res.size()>0)
			return res;
		return null;
	}
	
}
