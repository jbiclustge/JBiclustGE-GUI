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
package jbiclustgegui.operations.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.uvigo.ei.aibench.core.operation.annotation.ProgressProperty;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.methods.algorithms.AbstractBiclusteringAlgorithmCaller;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving biclusteringOperationRun events.
 * The class that is interested in processing a biclusteringOperationRun
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBiclusteringOperationRunListener<code> method. When
 * the biclusteringOperationRun event occurs, that object's appropriate
 * method is invoked.
 *
 * @see BiclusteringOperationRunEvent
 */
public class BiclusteringOperationRunListener implements PropertyChangeListener{

	
	
	/** The algorithmname. */
	public String algorithmname ="";
	
	/** The started. */
	public String started="";
	
	/** The currenttask. */
	public String currenttask="";
	
	/**
	 * Instantiates a new biclustering operation run listener.
	 */
	public BiclusteringOperationRunListener() {}
	
	/**
	 * Instantiates a new biclustering operation run listener.
	 *
	 * @param algorithm the algorithm
	 */
	public BiclusteringOperationRunListener(String algorithm) {
		this.algorithmname=algorithm;
		LocalDateTime date=LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		started=date.format(formatter);
	}
	
	
	
	
	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	@ProgressProperty(order = 1, label = "Started at: ")
	synchronized public String getStart(){
		return started;
	}
	
	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	synchronized public void setStart(String start){
		this.started=start;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@ProgressProperty(order = 2, label = "Running: ")
	synchronized public String getName(){
		return algorithmname;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param algorithmname the new name
	 */
	synchronized public void setName(String algorithmname) {
		   this.algorithmname=algorithmname;
	   }
	
	
	/**
	 * Gets the current task.
	 *
	 * @return the current task
	 */
	@ProgressProperty(order = 3, label = "Task: ")
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
	
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd=evt.getPropertyName();
		
		if(cmd.equals(AbstractBiclusteringAlgorithmCaller.FIREPROPERTYALGORITHMCHANGENAME)) {
			setName((String) evt.getNewValue());
			LocalDateTime date=LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			started=date.format(formatter);
		}
		
		if(cmd.equals(AbstractBiclusteringAlgorithmCaller.FIREBICLUSTERINGPROPERTYCHANGETASKSTATUS))
			setCurrentTask((String) evt.getNewValue());
		else if(cmd.equals(AbstractBiclusteringAlgorithmCaller.FIREPROPERTYCRITICALERROR))
			Workbench.getInstance().error((String) evt.getNewValue());
	}
	
	

}
