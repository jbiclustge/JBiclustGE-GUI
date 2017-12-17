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

import jbiclustge.analysis.similarity.components.SimilarityIndexMethod;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class PairwiseMultipleListBiclustersResultsBox.
 */
public class PairwiseMultipleListBiclustersResultsBox extends SimilarityResultsBox{

	/** The listname. */
	public static String LISTNAME="Pairwise Similarity Multiple List Biclusters";
	
	/** The compbiclusteringmethods. */
	private ArrayList<BiclusteringMethod> compbiclusteringmethods;
	
	/**
	 * Instantiates a new pairwise multiple list biclusters results box.
	 *
	 * @param name the name
	 * @param proj the proj
	 * @param results the results
	 * @param orderednameslist the orderednameslist
	 * @param method the method
	 */
	public PairwiseMultipleListBiclustersResultsBox(String name, Project proj, double[][] results,
			ArrayList<String> orderednameslist, SimilarityIndexMethod method) {
		super(name, proj, results, orderednameslist, method);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.analysis.SimilarityResultsBox#getByClass()
	 */
	@Override
	public Class<?> getByClass() {
		return PairwiseMultipleListBiclustersResultsBox.class;
	}

	/**
	 * Gets the biclustering methods used in pairwise similarity.
	 *
	 * @return the biclustering methods used in pairwise similarity
	 */
	public ArrayList<BiclusteringMethod> getBiclusteringMethodsUsedInPairwiseSimilarity() {
		return compbiclusteringmethods;
	}

	/**
	 * Sets the biclustering methods used in pairwise similarity.
	 *
	 * @param compbiclusteringmethods the new biclustering methods used in pairwise similarity
	 */
	public void setBiclusteringMethodsUsedInPairwiseSimilarity(ArrayList<BiclusteringMethod> compbiclusteringmethods) {
		this.compbiclusteringmethods = compbiclusteringmethods;
	}
	
	

}
