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
package jbiclustgegui.datatypes.analysis;

import java.util.ArrayList;

import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustge.analysis.similarity.components.SimilarityIndexMethod;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class SimilarityResultsBox.
 */
@Datatype(structure=Structure.SIMPLE,namingMethod="getName",setNameMethod="setName", removeMethod="remove")
public abstract class SimilarityResultsBox extends AbstractDataType implements IAnalysisResult{

	/** The proj. */
	protected Project proj;
	
	/** The results. */
	protected double[][]results;
	
	/** The orderednames. */
	protected ArrayList<String> orderednames;
	
	/** The method. */
	protected SimilarityIndexMethod method;
	
	/**
	 * Instantiates a new similarity results box.
	 *
	 * @param name the name
	 * @param proj the proj
	 * @param results the results
	 * @param orderednameslist the orderednameslist
	 * @param method the method
	 */
	public SimilarityResultsBox(String name, Project proj,double[][] results, ArrayList<String> orderednameslist,SimilarityIndexMethod method) {
		super(name);
		this.proj=proj;
		this.results=results;
		this.orderednames=orderednameslist;
		this.method=method;
		
	}

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#getByClass()
	 */
	@Override
	public Class<?> getByClass() {
		return SimilarityResultsBox.class;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#canDelete()
	 */
	@Override
	public boolean canDelete() {
		return true;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.AbstractDataType#getOwnerProject()
	 */
	@Override
	public Project getOwnerProject() {
		return proj;
	}


	/**
	 * Gets the ordered names.
	 *
	 * @return the ordered names
	 */
	public ArrayList<String> getOrderedNames() {
		return orderednames;
	}



	/**
	 * Gets the similarity results.
	 *
	 * @return the similarity results
	 */
	public double[][] getSimilarityResults() {
		return results;
	}


	/**
	 * Gets the similarity method.
	 *
	 * @return the similarity method
	 */
	public SimilarityIndexMethod getSimilarityMethod() {
		return method;
	}
	
	

}
