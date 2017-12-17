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
 * Created inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.datatypes.project.states;



import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;

@Datatype(structure=Structure.SIMPLE,namingMethod="getName")
public class ClosedProject extends AbstractDataType{

	protected String savePath = null;
	
	public ClosedProject(String proj, String fileName){
		super(proj);
		this.savePath = fileName;
	}

	public String getFileName() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public Project getOwnerProject() {
		return null;
	}

	@Override
	protected void removeInWorkspace(String newName) {
		
	}
	
	@Override
	protected void addInWorkspace(String oldName) {
		
	}
	
	public String toString(){
		return getName();
	}
	
}
