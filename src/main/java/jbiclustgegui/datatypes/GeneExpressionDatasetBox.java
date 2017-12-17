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
package jbiclustgegui.datatypes;

import java.io.Serializable;

import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;

@Datatype(structure = Structure.SIMPLE, namingMethod = "getName", setNameMethod = "setName")
public class GeneExpressionDatasetBox extends AbstractDataType implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String NAME="Gene Expression Dataset";
	private ExpressionData expressionset;
	
	protected String name=NAME;
	protected Project ownerProject;
	protected String notes=null;
	protected String usedfilepath;
	
	
	public GeneExpressionDatasetBox(Project proj, ExpressionData dataset) {
		super(NAME);
		this.ownerProject=proj;
		this.expressionset=dataset;

	}
	
	public GeneExpressionDatasetBox(ExpressionData dataset) {
		super(NAME);
		this.expressionset=dataset;

	}


	@Override
	public Project getOwnerProject() {
		return ownerProject;
	}

	

	public void setOwnerProject(Project ownerProject) {
		this.ownerProject = ownerProject;
	}

	public ExpressionData getExpressionset() {
		return expressionset;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getExpressionDatasetfilepath() {
		return usedfilepath;
	}

	public void setExpressionDatasetfilepath(String usedfilepath) {
		this.usedfilepath = usedfilepath;
	}
	

	
	
	
	
}
