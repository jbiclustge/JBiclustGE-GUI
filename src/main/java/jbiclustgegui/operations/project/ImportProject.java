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

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;

/**
 * <p> Loads a project from marshaled binary file. </p> 
 * <p> It is able to dynamically search and load class files from the classpath.</p>
 * 
 * @author pmaia
 */
@Operation(name="Load Project",description="Load project",enabled = false)
public class ImportProject {

	private File file;	
	//	public ReadStatusHandler status = new ReadStatusHandler();

	@Port(name = "File", direction = Direction.INPUT, order =1)
	public void setFile(File file){
		this.file = file;
		//		status.maxBytes = file.length();
	}

	@Port(name = "Project",direction = Direction.OUTPUT,order = 2)
	public Project loadProject(){
		Project p = null;
		
		try {
			p = SaveLoadManager.getInstance().importProject(file);
		} catch (Exception e) {
			Workbench.getInstance().error(e.getMessage());
			e.printStackTrace();
		} 

		return p;
	}

}
