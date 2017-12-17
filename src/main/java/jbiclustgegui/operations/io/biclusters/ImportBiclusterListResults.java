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
package jbiclustgegui.operations.io.biclusters;

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.operations.GenericOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportBiclusterListResults.
 */
@Operation(name="Import List of Biclusters",description="Import a list of bicluster results into project ",enabled = false)
public class ImportBiclusterListResults {

	/** The proj. */
	protected Project proj;
	
	/** The results. */
	protected BiclusterList results;
	
	/** The bicmethod. */
	protected BiclusteringMethod bicmethod;
	
	/**
	 * Sets the project.
	 *
	 * @param proj the new project
	 */
	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProject(Project proj) {
		this.proj=proj;
	}
	
	/**
	 * Sets the bicluster list.
	 *
	 * @param results the new bicluster list
	 */
	@Port(name="Biclustering results",direction=Direction.INPUT,order=2)
	public void setBiclusterList(BiclusterList results) {
		this.results=results;
	}
	
	/**
	 * Sets the biclustering method.
	 *
	 * @param method the new biclustering method
	 */
	@Port(name="Biclustering method",direction=Direction.INPUT,order=3)
	public void setBiclusteringMethod(BiclusteringMethod method) {
		this.bicmethod=method;
		try {
			run();
		} catch (InvalidElementListException e) {
			Workbench.getInstance().error(e);
		}
	}
	
	/**
	 * Run.
	 *
	 * @throws InvalidElementListException the invalid element list exception
	 */
	private void run() throws InvalidElementListException {
		
		BiclusteringResultBox bicboxtype=GenericOperation.getResultsBox(proj, "tmp", results, bicmethod);
		IElementList<IBiclusteringMethodResult> elems=proj.getBiclusteringMethodResultElementListByClass(bicboxtype.getByClass());
		int nelems=0;
		if(elems!=null)
			nelems=elems.size();
		
		nelems=nelems+1;
		
		String dataid=bicmethod.getAlgorithmName()+"_imported_results_"+nelems;
		bicboxtype.setName(dataid, false);
		GenericOperation.addBiclusteringResult(proj, bicboxtype.getByClass(), bicboxtype, bicmethod.getAlgorithmName()+" Results");
		
		
	}
	
}
