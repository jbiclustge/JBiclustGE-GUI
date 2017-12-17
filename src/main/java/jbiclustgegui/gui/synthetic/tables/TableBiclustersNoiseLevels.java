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
 * The Class TableBiclustersNoiseLevels.
 */
public class TableBiclustersNoiseLevels extends JTable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;

	/**
	 * Instantiates a new table biclusters noise levels.
	 */
	public TableBiclustersNoiseLevels() {
		tablemodel=new GenericTableModel(new String[] {"Bicluster","Noise level"}, new int[]{1});
		setModel(tablemodel);
	}



	/**
	 * Sets the noise of biclusters.
	 *
	 * @param nbics the nbics
	 * @param noiselevel the noiselevel
	 */
	public void setNoiseOfBiclusters(int nbics, Double noiselevel) {

		if(noiselevel==null)
				noiselevel=0.0;
		else {
			if(noiselevel>1)
				noiselevel=1.0;
			else if(noiselevel<0)
				noiselevel=0.0;
		}

		if(tablemodel.getRowCount()>0) {
			int existrowsnumber=tablemodel.getRowCount();

			if(nbics>existrowsnumber) {
				for (int i = existrowsnumber; i < nbics; i++) {
					tablemodel.addRow(new Object[] {(i+1), noiselevel});
				}
			}
			else if(nbics<existrowsnumber) {
				for (int i = existrowsnumber; i > nbics; i--) {
					tablemodel.removeRowAtPos((i-1));
				}
			}

		}
		else {
			for (int i = 0; i < nbics; i++) {
				tablemodel.addRow(new Object[] {(i+1), noiselevel});
			}
		}
	}
	
	/**
	 * Gets the noise of biclusters.
	 *
	 * @return the noise of biclusters
	 */
	public ArrayList<Double> getNoiseOfBiclusters(){
		ArrayList<Double> res=new ArrayList<>();
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			double value=(double) tablemodel.getValueAt(i, 1);
			if(value<0)
				value=0;
			res.add(value);
		}
		return res;
	}
	
	
	
	
	
	/**
	 * Clear table.
	 */
	public void clearTable() {
		tablemodel.resetTable();
	}
}
