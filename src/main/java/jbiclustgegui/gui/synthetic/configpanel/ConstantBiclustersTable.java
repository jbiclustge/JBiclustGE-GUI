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

import javax.swing.JTable;

import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstantBiclustersTable.
 */
public class ConstantBiclustersTable extends JTable{
	
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/**
	 * Instantiates a new constant biclusters table.
	 */
	public ConstantBiclustersTable() {
		tablemodel=new GenericTableModel(new String[] {"Bicluster", "Constant value"}, new int[] {1});
		setModel(tablemodel);
	}

	
	/**
	 * Append bicluster with constant to table.
	 *
	 * @param constant the constant
	 */
	public void appendBiclusterWithConstantToTable(int constant) {
		
		int bicsintable=tablemodel.getRowCount();
		tablemodel.addRow(new Object[] {(bicsintable+1), constant});
	}
	
	
	/**
	 * Replace bicluster constant value.
	 *
	 * @param rowindex the rowindex
	 * @param constant the constant
	 */
	public void replaceBiclusterConstantValue(int rowindex, int constant) {
		tablemodel.setValueAt(constant,rowindex, 1);
	}
	
	
	/**
	 * Replace all bicluster constant value.
	 *
	 * @param constant the constant
	 */
	public void replaceAllBiclusterConstantValue(int constant) {
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			replaceBiclusterConstantValue(i, constant);
		}
	}
	
	
	/**
	 * Gets the biclusters signal.
	 *
	 * @return the biclusters signal
	 */
	public int[] getBiclustersSignal() {
		int[] res=new int[tablemodel.getRowCount()];
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			res[i]=(int) tablemodel.getValueAt(i, 1);
		}
		return res;
	}
	
}
