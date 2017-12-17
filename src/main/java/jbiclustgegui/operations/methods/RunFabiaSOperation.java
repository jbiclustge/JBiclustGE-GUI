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
package jbiclustgegui.operations.methods;



import java.time.LocalDateTime;

import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import jbiclustge.methods.algorithms.AbstractBiclusteringAlgorithmCaller;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaSMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.biclusteringresults.FabiaSResultsBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.operations.GenericOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class RunFabiaSOperation.
 */
@Operation(name="Run FabiaS",description="Execute Fabias Algorithm",enabled = false)
public class RunFabiaSOperation extends AbstractMethodOperation{
	

	/** The method. */
	private  RFabiaSMethod method;
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.operations.methods.AbstractMethodOperation#runMethod()
	 */
	@Override
	protected BiclusterList runMethod() {
		method=new RFabiaSMethod(expressionset);
		method.setAlgorithmProperties(props);
		method.addBiclusteringPropertyChangeListener(progressview);
		method.run();
		return method.getBiclusterResultList();
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.operations.methods.AbstractMethodOperation#addResultsToClipboard(jbiclustge.results.biclusters.containers.BiclusterList)
	 */
	@Override
	protected String addResultsToClipboard(BiclusterList results) throws InvalidElementListException {
		
		IElementList<IBiclusteringMethodResult> elems=proj.getBiclusteringMethodResultElementListByClass(FabiaSResultsBox.class);
		int nelems=0;
		if(elems!=null)
			nelems=elems.size();
		
		nelems=nelems+1;
		String dataid="FabiaS_result_"+nelems;
		
		FabiaSResultsBox res=new FabiaSResultsBox(dataid, proj, results);
		res.setDate(LocalDateTime.now());
		GenericOperation.addBiclusteringResult(proj,FabiaSResultsBox.class, res, res.getMethod().getAlgorithmName()+" Results");
	    return dataid;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.operations.methods.AbstractMethodOperation#getAlgorithm()
	 */
	@Override
	protected AbstractBiclusteringAlgorithmCaller getAlgorithm() {
		return method;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.operations.methods.AbstractMethodOperation#getAlgorithmName()
	 */
	@Override
	protected String getAlgorithmName() {
		// TODO Auto-generated method stub
		return RFabiaSMethod.NAME;
	}
	

}
