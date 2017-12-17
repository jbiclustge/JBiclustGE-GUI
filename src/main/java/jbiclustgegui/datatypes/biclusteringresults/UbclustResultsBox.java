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

import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class UbclustResultsBox.
 */
public class UbclustResultsBox extends BiclusteringResultBox{

	/**
	 * Instantiates a new ubclust results box.
	 *
	 * @param name the name
	 * @param proj the proj
	 * @param results the results
	 */
	public UbclustResultsBox(String name, Project proj, BiclusterList results) {
		super(name, proj, results, BiclusteringMethod.UBCLUST);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox#getByClass()
	 */
	@Override
	public Class<?> getByClass() {
		return UbclustResultsBox.class;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#canDelete()
	 */
	@Override
	public boolean canDelete() {
		return true;
	}



}
