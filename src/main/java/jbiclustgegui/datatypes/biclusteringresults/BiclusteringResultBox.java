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
package jbiclustgegui.datatypes.biclusteringresults;

import java.time.LocalDateTime;

import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusteringResultBox.
 */
@Datatype(structure=Structure.SIMPLE,namingMethod="getName",setNameMethod="setName", removeMethod="remove")
public abstract class BiclusteringResultBox extends AbstractDataType implements IBiclusteringMethodResult{
	
	/** The proj. */
	protected Project proj;
	
	/** The results. */
	protected BiclusterList results;
	
	/** The method. */
	protected BiclusteringMethod method;
	
	/** The date. */
	protected LocalDateTime date;
	
	/**
	 * Instantiates a new biclustering result box.
	 *
	 * @param name the name
	 * @param proj the proj
	 * @param results the results
	 * @param method the method
	 */
	public BiclusteringResultBox(String name, Project proj,BiclusterList results, BiclusteringMethod method) {
		super(name);
		this.proj=proj;
		this.results=results;
		this.method=method;

	}

	//public abstract Class<?> getChildClass();
	

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public BiclusterList getResults() {
		return results;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IBiclusteringMethodResult#getMethod()
	 */
	public BiclusteringMethod getMethod() {
		return method;
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#getByClass()
	 */
	@Override
	public Class<?> getByClass() {
		return BiclusteringResultBox.class;
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.AbstractDataType#getOwnerProject()
	 */
	@Override
	public Project getOwnerProject() {
		return proj;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	

}
