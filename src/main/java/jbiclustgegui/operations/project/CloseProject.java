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

import javax.swing.SwingUtilities;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.datatypes.project.states.ClosedProject;

@Operation(name="Close project", description="Close project" ,enabled = false)
public class CloseProject {
	
	
	protected AbstractDataType toClose = null;
	
	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProject(AbstractDataType proj){
		this.toClose = proj;
	}
	
	
	@Port(direction=Direction.OUTPUT, order=2)
	public ClosedProject close() throws Exception{

		if(toClose!=null){
			Project p = toClose.getOwnerProject();
			ClosedProject closeP = null;
			
			if(p!=null)
			try {
				closeP = SaveLoadManager.getInstance().closeProject(p);
				for(final ClipboardItem item: Core.getInstance().getClipboard().getItemsByClass(Project.class)){
					if(item.getUserData().equals(p))
						SwingUtilities.invokeAndWait(new Runnable() {
							
							@Override
							public void run() {							
								Core.getInstance().getClipboard().removeClipboardItem(item);
							}
						});
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return closeP;
		}
		return null;
		
	}

}
