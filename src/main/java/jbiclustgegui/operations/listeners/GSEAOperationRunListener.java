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

import es.uvigo.ei.aibench.core.operation.annotation.ProgressProperty;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalyserProcessor;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving GSEAOperationRun events.
 * The class that is interested in processing a GSEAOperationRun
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addGSEAOperationRunListener<code> method. When
 * the GSEAOperationRun event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GSEAOperationRunEvent
 */
public class GSEAOperationRunListener extends BiclusteringOperationRunListener{

	/** The progress. */
	private float progress=0;
	
	/** The currentsubtask. */
	public String currentsubtask="";
	
	/**
	 * Gets the progress.
	 *
	 * @return the progress
	 */
	@ProgressProperty(order = 4, label = "Progress GSEA: ")
	synchronized public float getProgress() {
		return progress;
	}
	
	/**
	 * Sets the progress.
	 *
	 * @param progress the new progress
	 */
	synchronized public void setProgress(float progress) {
		this.progress=progress;
	}
	
	/**
	 * Gets the current sub task.
	 *
	 * @return the current sub task
	 */
	@ProgressProperty(order = 5, label = "SubTask: ")
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
	 * @see jbiclustgegui.operations.listeners.BiclusteringOperationRunListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd=evt.getPropertyName();
		
		if(cmd.equals(EnrichmentAnalyserProcessor.FIREPROPERTYGSEAANALYSERCHANGENAME))
			setName((String) evt.getNewValue());
		else if(cmd.equals(EnrichmentAnalyserProcessor.FIREPROPERTYGSEACHANGETASKSTATUS))
			setCurrentTask((String) evt.getNewValue());
		else if(cmd.equals(EnrichmentAnalyserProcessor.FIREPROPERTYGSEACHANGEPROGRESS))
			setProgress((float) evt.getNewValue());
		else if(cmd.equals(EnrichmentAnalyserProcessor.FIREPROPERTYGSEACHANGESUBTASKSTATUS))
			setCurrentSubTask((String) evt.getNewValue());
	}

}
