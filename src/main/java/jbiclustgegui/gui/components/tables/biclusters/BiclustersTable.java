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
package jbiclustgegui.gui.components.tables.biclusters;

import java.util.ArrayList;

import javax.swing.JTable;

import jbiclustge.results.biclusters.containers.BiclusterList;
import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclustersTable.
 */
public class BiclustersTable extends JTable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/** The listbics. */
	private BiclusterList listbics=null;
	
	
	/**
	 * Instantiates a new biclusters table.
	 */
	public BiclustersTable() {
		this.tablemodel=new GenericTableModel(new String[]{"Biclusters"}, false);
		setModel(tablemodel);
	}
	
	
	
	/**
	 * Adds the listof biclusters.
	 *
	 * @param listbics the listbics
	 */
	public void addListofBiclusters(BiclusterList listbics) {
		this.listbics=listbics;
		showAllBiclustersIDS();
	}
	
	/**
	 * Show all biclusters IDS.
	 */
	public void showAllBiclustersIDS() {
		tablemodel.resetTable();
		for (int i = 0; i < listbics.size(); i++) {
			String name="Bicluster "+(i+1);
			Object[] tname=new Object[] {name};
			tablemodel.addRow(tname);
		}
	}
	
	
   /**
    * Show only biclusters.
    *
    * @param bicindexes the bicindexes
    */
   public void showOnlyBiclusters(ArrayList<Integer> bicindexes) {
	   
	   if(bicindexes!=null) {
		   tablemodel.resetTable();
		   for (int i = 0; i < bicindexes.size(); i++) {
			   String name="Bicluster "+(bicindexes.get(i)+1);
			   Object[] tname=new Object[] {name};
			   tablemodel.addRow(tname);
		   }
	   }
	   
   }
	
	

}
