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
import java.util.HashSet;

import es.uvigo.ei.aibench.core.operation.annotation.ProgressProperty;
import pt.ornrocha.logutils.messagecomponents.IStatusProgressListener;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving generalOperationCurrentLastTaskStatusLog events.
 * The class that is interested in processing a generalOperationCurrentLastTaskStatusLog
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addGeneralOperationCurrentLastTaskStatusLogListener<code> method. When
 * the generalOperationCurrentLastTaskStatusLog event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GeneralOperationCurrentLastTaskStatusLogEvent
 */
public class GeneralOperationCurrentLastTaskStatusLogListener implements IStatusProgressListener{

	
	/** The taskstatus. */
	public String taskstatus = "";
	
	/** The subtaskstatus. */
	public String subtaskstatus = "";
	
	/** The addpropertyname. */
	public static String ADDPROPERTYNAME="propertyname";
	
	/** The allowedpropnames. */
	private HashSet<String> allowedpropnames;
	
	
	/**
	 * Instantiates a new general operation current last task status log listener.
	 */
	public GeneralOperationCurrentLastTaskStatusLogListener() {}

	
	/**
	 * Gets the task status.
	 *
	 * @return the task status
	 */
	/*synchronized public float getProgress() {
		return progress;
	}
	
	synchronized public void setProgress(float progress) {
		this.progress= progress;
	}*/   
	@ProgressProperty(order = 1, label = "Current task: ")
	synchronized public String getTaskStatus(){
		return taskstatus;
	}
	
	/**
	 * Sets the task status.
	 *
	 * @param status the new task status
	 */
	synchronized public void setTaskStatus(String status){
		this.taskstatus = status;
	}
	
	/**
	 * Gets the sub task status.
	 *
	 * @return the sub task status
	 */
	@ProgressProperty(order = 2, label = "Last task: ")
	synchronized public String getSubTaskStatus(){
		return subtaskstatus;
	}
	
	/**
	 * Sets the sub task status.
	 *
	 * @param status the new sub task status
	 */
	synchronized public void setSubTaskStatus(String status){
		this.subtaskstatus = status;
	}
	
	
	/**
	 * Adds the property name to check progress.
	 *
	 * @param propname the propname
	 */
	public synchronized void addPropertyNameToCheckProgress(String propname) {
		if(allowedpropnames==null)
			allowedpropnames=new HashSet<>();
		
		allowedpropnames.add(propname);
	}
	
	

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public synchronized void propertyChange(PropertyChangeEvent evt) {
	
		 String propname=evt.getPropertyName();
		 Object value=evt.getNewValue();
		 
		 
		 if(value instanceof String) {
			 
			 if(propname.equals(ADDPROPERTYNAME))
				 addPropertyNameToCheckProgress((String) value);
			 
			 else if(propname.equals(LogMessageCenter.SUBTASK_STATUS_MESSSAGE))
				 setSubTaskStatus((String) value);
			 else if(propname.equals(LogMessageCenter.TASK_STATUS_MESSSAGE))
				 setTaskStatus((String) value); 
			 else if(allowedpropnames!=null && allowedpropnames.contains(propname) && value instanceof String)
				 setTaskStatus((String) value);
		 }
		/* else if(value instanceof Float) {
			 if(allowedpropnames!=null && allowedpropnames.contains(propname))
				 setProgress((float) value);
		 }*/
			 

	}
	

}
