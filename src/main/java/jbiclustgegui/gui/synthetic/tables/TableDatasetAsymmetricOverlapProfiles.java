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
 * The Class TableDatasetAsymmetricOverlapProfiles.
 */
public class TableDatasetAsymmetricOverlapProfiles extends JTable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/** The listof profiles. */
	private ArrayList<ArrayList<Pair<Integer, Integer>>> listofProfiles;
	
	
	/**
	 * Instantiates a new table dataset asymmetric overlap profiles.
	 */
	public TableDatasetAsymmetricOverlapProfiles() {
		tablemodel=new GenericTableModel(new String[] {"Profile"}, new int[] {0});
		setModel(tablemodel);
		listofProfiles=new ArrayList<>();
	}
	
	
	/**
	 * Adds the asymmetric profile.
	 *
	 * @param profile the profile
	 */
	public void addAsymmetricProfile(ArrayList<Pair<Integer, Integer>> profile) {
		
		listofProfiles.add(profile);
		String setname="Profile "+(tablemodel.getRowCount()+1);
		tablemodel.addRow(new Object[] {setname});
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
		listofProfiles.remove(row);
		
		int nelems=tablemodel.getRowCount();
		tablemodel.resetTable();
		for (int i = 0; i < nelems; i++) {
			String setname="Profile "+(i+1);
			tablemodel.addRow(new Object[] {setname});
		}
	}
	
	/**
	 * Gets the a symmetric overlap experiments.
	 *
	 * @return the a symmetric overlap experiments
	 */
	public ArrayList<ArrayList<Pair<Integer, Integer>>> getASymmetricOverlapExperiments(){
		if(listofProfiles.size()>0)
			return listofProfiles;
		return null;
	}
	
	/**
	 * Gets the selected profile.
	 *
	 * @param index the index
	 * @return the selected profile
	 */
	public ArrayList<Pair<Integer, Integer>> getSelectedProfile(int index){
		return listofProfiles.get(index);
	}
	
	/**
	 * Change profile.
	 *
	 * @param index the index
	 * @param profile the profile
	 */
	public void changeProfile(int index,ArrayList<Pair<Integer, Integer>> profile){
		listofProfiles.set(index, profile);
	}
	
}
