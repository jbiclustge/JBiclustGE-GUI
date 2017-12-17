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
package jbiclustgegui.operations.synthetic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import es.uvigo.ei.aibench.core.operation.annotation.ProgressProperty;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving syntheticDataOperation events.
 * The class that is interested in processing a syntheticDataOperation
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSyntheticDataOperationListener<code> method. When
 * the syntheticDataOperation event occurs, that object's appropriate
 * method is invoked.
 *
 * @see SyntheticDataOperationEvent
 */
public class SyntheticDataOperationListener implements PropertyChangeListener{

	
	
	/** The currenttask. */
	public String currenttask="";
	
	/** The currentsubtask. */
	public String currentsubtask="";
	
	
	
	/**
	 * Gets the current task.
	 *
	 * @return the current task
	 */
	@ProgressProperty(order = 1, label = "Task: ")
	synchronized public String getCurrentTask(){
		return currenttask;
	}
	
	/**
	 * Sets the current task.
	 *
	 * @param currenttask the new current task
	 */
	synchronized public void setCurrentTask(String currenttask) {
	   this.currenttask=currenttask;
   }
	
	/**
	 * Gets the current sub task.
	 *
	 * @return the current sub task
	 */
	@ProgressProperty(order = 2, label = "SubTask: ")
	synchronized public String getCurrentSubTask(){
		return currentsubtask;
	}
	
	/**
	 * Sets the current sub task.
	 *
	 * @param currenttask the new current sub task
	 */
	synchronized public void setCurrentSubTask(String currenttask) {
	   this.currentsubtask=currenttask;
   }
	
	
	
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd=evt.getPropertyName();
		
		if(cmd.equals(GenerateSynteticBiclustersDataOperation.FIREPROPERTYGSEACHANGETASKSTATUS))
			setCurrentTask((String) evt.getNewValue());
		else if(cmd.equals(GenerateSynteticBiclustersDataOperation.FIREPROPERTYGSEACHANGESUBTASKSTATUS))
			setCurrentSubTask((String) evt.getNewValue());
		
	}

}
