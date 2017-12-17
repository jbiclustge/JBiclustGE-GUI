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

import java.io.File;
import java.io.IOException;

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;

@Operation(name="Export project",description="Export project" ,enabled = false)
public class ExportProject {
	
	private Project project;
	private File file;
	
	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Port(name="File",description="",direction=Direction.INPUT,order=2)
	public void setFile(File file) {
		this.file = file;
		saveProject();
	}
	
	private void saveProject(){
		try {
			SaveLoadManager.getInstance().exportProject(project, file.toString());
		} catch (IOException e) {
			Workbench.getInstance().error(e.getMessage());
			e.printStackTrace();
		}
	}	

}
