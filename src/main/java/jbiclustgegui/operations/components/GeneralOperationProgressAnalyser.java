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
package jbiclustgegui.operations.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralOperationProgressAnalyser.
 */
public class GeneralOperationProgressAnalyser implements PropertyChangeListener{
	
	
	/** The progress. */
	public float progress ;
	
	/** The status. */
	public String status = "";
	
	/** The current. */
	protected int current=0;
	
	/** The currentstatus. */
	public static String CURRENTSTATUS="currentstatus";
	
	/** The currentprogress. */
	public static String CURRENTPROGRESS="currentprogress";
	
	/** The addpropertyname. */
	public static String ADDPROPERTYNAME="propertyname";
	
	/** The allowedpropnames. */
	private HashSet<String> allowedpropnames;
	
	
	/**
	 * Gets the progress.
	 *
	 * @return the progress
	 */
	synchronized public float getProgress() {
		return progress;
	}
	
	/**
	 * Sets the progress.
	 *
	 * @param progress the new progress
	 */
	synchronized public void setProgress(float progress) {
		this.progress= progress;
	}   
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	synchronized public String getStatus(){
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	synchronized public void setStatus(String status){
		this.status = status;
	}
	
	
	/**
	 * Adds the property name to check progress.
	 *
	 * @param propname the propname
	 */
	public void addPropertyNameToCheckProgress(String propname) {
		if(allowedpropnames==null)
			allowedpropnames=new HashSet<>();
		
		allowedpropnames.add(propname);
	}
	
	

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	
		 String propname=evt.getPropertyName();
		 Object value=evt.getNewValue();
		 
		 
		 if(value instanceof String) {
			 
			 if(propname.equals(ADDPROPERTYNAME))
				 addPropertyNameToCheckProgress((String) value);
			 
			 else if(allowedpropnames!=null && allowedpropnames.contains(propname))
				setStatus((String) value);
		 }
		 else if(value instanceof Float) {
			 if(allowedpropnames!=null && allowedpropnames.contains(propname))
				 setProgress((float) value);
		 }
			 

	}

}