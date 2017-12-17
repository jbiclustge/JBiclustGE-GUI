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
 * The Class TableOverlapBetweenBiclusters.
 */
public class TableOverlapBetweenBiclusters extends JTable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/**
	 * Instantiates a new table overlap between biclusters.
	 */
	public TableOverlapBetweenBiclusters() {
		this.tablemodel=new GenericTableModel(new String[] {"Overlap between Biclusters", "Number Rows", "Number Columns"}, new int[] {1,2} );
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
	
		if(rowdim==null)
			rowdim=10;
		if(coldim==null)
			coldim=15;
		
		if(nbics>1) {
			if(tablemodel.getRowCount()>0) {
				int existrowsnumber=tablemodel.getRowCount();
				
				
				
				if(nbics>existrowsnumber) {
					for (int i = existrowsnumber; i < nbics-1; i++) {
						String name=(i+1)+" and "+(i+2);
						tablemodel.addRow(new Object[] {name, rowdim, coldim});
					}
				}
				else if(nbics<existrowsnumber) {
					for (int i = existrowsnumber; i > nbics-1; i--) {
						tablemodel.removeRowAtPos((i-1));
					}
				}
				
			}
			else {
				for (int i = 0; i < nbics-1; i++) {
					String name=(i+1)+" and "+(i+2);
					tablemodel.addRow(new Object[] {name, rowdim, coldim});
				}
			}
		}
	}
	
	/**
	 * Clear table.
	 */
	public void clearTable() {
		tablemodel.resetTable();
	}
	
	
	/**
	 * Sets the symmetric overlap values to all biclusters.
	 *
	 * @param rowdim the rowdim
	 * @param coldim the coldim
	 */
	public void setSymmetricOverlapValuesToAllBiclusters(int rowdim, int coldim) {
		
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			tablemodel.setValueAt(rowdim, i, 1);
			tablemodel.setValueAt(coldim, i, 2);
		}
	}
	
	
	/**
	 * Sets the overlap between biclusters.
	 *
	 * @param overlap the overlap
	 */
	public void setOverlapBetweenBiclusters(ArrayList<Pair<Integer, Integer>> overlap) {
		clearTable();
		
		for (int i = 0; i < overlap.size(); i++) {
			String name=(i+1)+" and "+(i+2);
			tablemodel.addRow(new Object[] {name, overlap.get(i).getValue0(), overlap.get(i).getValue1()});
		}
	}

    

	/**
	 * Gets the overlap between biclusters.
	 *
	 * @return the overlap between biclusters
	 */
	public ArrayList<Pair<Integer, Integer>> getOverlapBetweenBiclusters(){
    	
    	ArrayList<Pair<Integer, Integer>> res=new ArrayList<>();
    	
    	for (int i = 0; i < tablemodel.getRowCount(); i++) {
    		
    		Object rowval=tablemodel.getValueAt(i, 1);
    		Object colval=tablemodel.getValueAt(i, 2);
    		
    		int rows=0;
    		int cols=0;
    		
    		if(rowval instanceof String)
    			rows=Integer.parseInt((String) rowval);
    		else
    			rows=(int) rowval;
    		if(colval instanceof String)
    			cols=Integer.parseInt((String) colval);
    		else
    			cols=(int) colval;

			res.add(new Pair<Integer, Integer>(rows, cols));
		}
    	return res;
    }
	
	
}
