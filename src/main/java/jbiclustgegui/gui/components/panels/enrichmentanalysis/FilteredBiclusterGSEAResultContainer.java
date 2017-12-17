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
package jbiclustgegui.gui.components.panels.enrichmentanalysis;

import java.util.ArrayList;
import java.util.LinkedHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class FilteredBiclusterGSEAResultContainer.
 */
public class FilteredBiclusterGSEAResultContainer {
	
	
	/** The filteredgoterms. */
	private ArrayList<String> filteredgoterms=null;
	
	/** The goterm 2 termname. */
	private LinkedHashMap<String, String> goterm2termname=null;
	
	/** The goterm 2 pvalue. */
	private LinkedHashMap<String, Double> goterm2pvalue=null;
	
	/** The goterm 2 adjustedpvalue. */
	private LinkedHashMap<String, Double> goterm2adjustedpvalue=null;
	
	/** The goterm 2 associatedgenes. */
	private LinkedHashMap<String, ArrayList<String>> goterm2associatedgenes=null;
	
	/** The goterm 2 associatprobids. */
	private LinkedHashMap<String, ArrayList<String>> goterm2associatprobids=null;
	
	
	
	/**
	 * Instantiates a new filtered bicluster GSEA result container.
	 */
	public FilteredBiclusterGSEAResultContainer() {
		filteredgoterms=new ArrayList<>();
		goterm2termname=new LinkedHashMap<>();
		goterm2pvalue=new LinkedHashMap<>();
		goterm2adjustedpvalue=new LinkedHashMap<>();
		goterm2associatedgenes=new LinkedHashMap<>();
		goterm2associatprobids=new LinkedHashMap<>();
	}
	
	
	/**
	 * Append information.
	 *
	 * @param gotermid the gotermid
	 * @param gotermname the gotermname
	 * @param pvalue the pvalue
	 * @param adjpvalue the adjpvalue
	 * @param assocgenes the assocgenes
	 * @param assocprobids the assocprobids
	 */
	public void appendInformation(String gotermid, String gotermname, double pvalue, Double adjpvalue, ArrayList<String> assocgenes, ArrayList<String> assocprobids) {
		
		filteredgoterms.add(gotermid);
		goterm2termname.put(gotermid, gotermname);
		goterm2pvalue.put(gotermid, pvalue);
		goterm2adjustedpvalue.put(gotermid, adjpvalue);
		goterm2associatedgenes.put(gotermid, assocgenes);
		goterm2associatprobids.put(gotermid, assocprobids);
	}


	/**
	 * Gets the filteredgoterms.
	 *
	 * @return the filteredgoterms
	 */
	public ArrayList<String> getFilteredgoterms() {
		return filteredgoterms;
	}

	
	/**
	 * Gets the goterm 2 term name.
	 *
	 * @param gotermid the gotermid
	 * @return the goterm 2 term name
	 */
	public String getGoterm2termName(String gotermid) {
		if(goterm2termname.containsKey(gotermid))
			return goterm2termname.get(gotermid);
		return "";
	}
	

	/**
	 * Gets the goterm 2 termname.
	 *
	 * @return the goterm 2 termname
	 */
	public LinkedHashMap<String, String> getGoterm2termname() {
		return goterm2termname;
	}


	/**
	 * Gets the goterm 2 pvalue.
	 *
	 * @return the goterm 2 pvalue
	 */
	public LinkedHashMap<String, Double> getGoterm2pvalue() {
		return goterm2pvalue;
	}
	
	/**
	 * Gets the goterm 2 pvalue.
	 *
	 * @param gotermid the gotermid
	 * @return the goterm 2 pvalue
	 */
	public Double getGoterm2pvalue(String gotermid) {
		if(goterm2pvalue.containsKey(gotermid))
			return goterm2pvalue.get(gotermid);
		return null;
	}


	/**
	 * Gets the goterm 2 adjustedpvalue.
	 *
	 * @return the goterm 2 adjustedpvalue
	 */
	public LinkedHashMap<String, Double> getGoterm2adjustedpvalue() {
		return goterm2adjustedpvalue;
	}
	
	/**
	 * Gets the goterm 2 adjustedpvalue.
	 *
	 * @param gotermid the gotermid
	 * @return the goterm 2 adjustedpvalue
	 */
	public Double getGoterm2adjustedpvalue(String gotermid) {
		if(goterm2adjustedpvalue.containsKey(gotermid))
			return goterm2adjustedpvalue.get(gotermid);
		return null;
	}


	/**
	 * Gets the goterm 2 associatedgenes.
	 *
	 * @return the goterm 2 associatedgenes
	 */
	public LinkedHashMap<String, ArrayList<String>> getGoterm2associatedgenes() {
		return goterm2associatedgenes;
	}
	
	/**
	 * Gets the goterm 2 associatedgenes.
	 *
	 * @param gotermid the gotermid
	 * @return the goterm 2 associatedgenes
	 */
	public ArrayList<String> getGoterm2associatedgenes(String gotermid) {
		if(goterm2associatedgenes.containsKey(gotermid))
			return goterm2associatedgenes.get(gotermid);
		return null;
	}


	/**
	 * Gets the goterm 2 associatprobids.
	 *
	 * @return the goterm 2 associatprobids
	 */
	public LinkedHashMap<String, ArrayList<String>> getGoterm2associatprobids() {
		return goterm2associatprobids;
	}
	
	/**
	 * Gets the goterm 2 associatprobids.
	 *
	 * @param gotermid the gotermid
	 * @return the goterm 2 associatprobids
	 */
	public ArrayList<String> getGoterm2associatprobids(String gotermid) {
		if(goterm2associatprobids.containsKey(gotermid))
			return goterm2associatprobids.get(gotermid);
		return null;
	}
	
	
   

}
