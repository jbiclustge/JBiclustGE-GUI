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

import org.javatuples.Pair;

import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class TableBiclustersVariableSize.
 */
public class TableBiclustersVariableSize extends JTable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/**
	 * Instantiates a new table biclusters variable size.
	 */
	public TableBiclustersVariableSize() {
		this.tablemodel=new GenericTableModel(new String[] {"Bicluster", "Number Rows", "Number Columns"}, new int[] {1,2} );
		setModel(tablemodel);
	}
	
	

	
	/**
	 * Sets the number of biclusters.
	 *
	 * @param nbics the nbics
	 * @param rowdim the rowdim
	 * @param coldim the coldim
	 */
	public void setNumberOfBiclusters(int nbics, Integer rowdim, Integer coldim) {
	
		
			if(tablemodel.getRowCount()>0) {
				int existrowsnumber=tablemodel.getRowCount();
				
				if(rowdim==null)
					rowdim=10;
				if(coldim==null)
					coldim=10;
				
				if(nbics>existrowsnumber) {
					for (int i = existrowsnumber; i < nbics; i++) {
						tablemodel.addRow(new Object[] {(i+1), rowdim, coldim});
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
					tablemodel.addRow(new Object[] {(i+1), rowdim, coldim});
				}
			}
	}
	
	
	/**
	 * Sets the uniform values to all biclusters.
	 *
	 * @param rowdim the rowdim
	 * @param coldim the coldim
	 */
	public void setUniformValuesToAllBiclusters(int rowdim, int coldim) {
		
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			tablemodel.setValueAt(rowdim, i, 1);
			tablemodel.setValueAt(coldim, i, 2);
		}
	}

    /**
     * Gets the shape of biclusters.
     *
     * @return the shape of biclusters
     */
    public ArrayList<Pair<Integer, Integer>> getShapeOfBiclusters(){
    	
    	ArrayList<Pair<Integer, Integer>> res=new ArrayList<>();
    	
    	for (int i = 0; i < tablemodel.getRowCount(); i++) {
			int rows=(int) tablemodel.getValueAt(i, 1);
			int cols=(int) tablemodel.getValueAt(i, 2);
			res.add(new Pair<Integer, Integer>(rows, cols));
		}
    	return res;
    }
	
	
}
