/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
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
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package jbiclustgegui.operations.project;

import java.io.IOException;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.CorruptProjectFileException;
import jbiclustgegui.datatypes.project.exceptions.ProjectAllreadyExistException;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.datatypes.project.states.ClosedProject;

@Operation(name="Open project", description="Open project" ,enabled = false)
public class OpenProject {
	
	ClosedProject closed = null;

	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProj(ClosedProject closed){
		this.closed = closed;
	}
	
	@Port(direction=Direction.OUTPUT,order=2)
	public Project getProject() throws IOException{
		Project ret = null;

		if(closed!=null)	
			try {
				ret = SaveLoadManager.getInstance().openProject(closed);
				for(ClipboardItem item: Core.getInstance().getClipboard().getItemsByClass(ClosedProject.class)){
					if(item.getUserData().equals(closed))
						Core.getInstance().getClipboard().removeClipboardItem(item);
				}
				
				
			} catch (CorruptProjectFileException e) {
				Workbench.getInstance().error("The project are corrupted in workspace folder...");
				e.printStackTrace();
			} catch (ProjectAllreadyExistException e) {
				Workbench.getInstance().error("Already exists a opened project with same name...");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SerializerNotRegistered e) {
				e.printStackTrace();
			}
		
		return ret;
	}
	
}
