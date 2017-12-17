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
package jbiclustgegui.operations.project;



import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.datatypes.project.Project;

@Operation(name="Load Expression dataset",description="Load a gene expression dataset from file",enabled = false)
public class NewProjectOperation {
	
	
	private String name;
	private ExpressionData dataset;
	private String notes=null;
	private String datasetfilepath=null;
	
	@Port(name="name",direction=Direction.INPUT,order=1)
	public void setName(String name) {
		this.name = name;
	}
	
	@Port(name="dataset",direction=Direction.INPUT,order=2)
	public void setExpressionDataset(ExpressionData dataset) {
		this.dataset=dataset;
	}
	
	@Port(name="notes",direction=Direction.INPUT,order=3)
	public void setNotes(String notes) {
		this.notes=notes;
	}
	
	@Port(name="expressionfilepath",direction=Direction.INPUT,order=4)
	public void setExpressionDatasetFilepath(String filepath) {
		this.datasetfilepath=filepath;
	}
	
	
	
	@Port(direction=Direction.OUTPUT,order=5)
	public Project getProj() throws Exception{
		
		GeneExpressionDatasetBox expressionbox=new GeneExpressionDatasetBox(dataset);
		expressionbox.setNotes(notes);
		expressionbox.setExpressionDatasetfilepath(datasetfilepath);
		Project proj=new Project(name,expressionbox);
		expressionbox.setOwnerProject(proj);
		return proj;
		
	}
	
	
}
