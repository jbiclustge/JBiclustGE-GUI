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
package jbiclustgegui.operations.analysis.similarity;

import java.util.ArrayList;

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.analysis.similarity.BiclusterResultsPairwiseFunctions;
import jbiclustge.analysis.similarity.components.SimilarityIndexMethod;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.analysis.PairwiseMultipleListBiclustersResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.operations.GenericOperation;
import pt.ornrocha.printutils.MTUPrintUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SimilarityMultipleListBiclusters.
 */
@Operation(name="Analysis of similarity",description="Analyse similarity multiple lists of biclusters",enabled = false)
public class SimilarityMultipleListBiclusters {
	
	/** The proj. */
	private Project proj;
	
	/** The listresults. */
	private ArrayList<BiclusteringResultBox> listresults;
	
	/** The method. */
	private SimilarityIndexMethod method;
	
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
	 * Sets the list biclusters to compare.
	 *
	 * @param listres the new list biclusters to compare
	 */
	@Port(name="List to compare",direction=Direction.INPUT,order=2)
	public void setListBiclustersToCompare(ArrayList<BiclusteringResultBox> listres) {
		this.listresults=listres;
	}
	
	/**
	 * Sets the similarity method.
	 *
	 * @param method the new similarity method
	 */
	@Port(name="Similarity method",direction=Direction.INPUT,order=3)
	public void setSimilarityMethod(SimilarityIndexMethod method) {
		this.method=method;
		try {
			run();
		} catch (Exception e) {
			Workbench.getInstance().error(e);
		}
	}
	
	
	
	/**
	 * Run.
	 *
	 * @throws Exception the exception
	 */
	protected void run() throws Exception {
		
		if(listresults!=null && listresults.size()>0) {
			
			ArrayList<String> orderednames=new ArrayList<>();
			ArrayList<BiclusteringMethod> methods=new ArrayList<>();
			
			BiclusterList[] listtoanalyse=new BiclusterList[listresults.size()];
			for (int i = 0; i < listresults.size(); i++) {
				listtoanalyse[i]=listresults.get(i).getResults();
				orderednames.add(listresults.get(i).getName());
				methods.add(listresults.get(i).getMethod());
			}
			
			double[][] resultmatrix=BiclusterResultsPairwiseFunctions.getPairwiseBiclusterResultsSimilarityIndex(method, listtoanalyse);
			
			
			IElementList<IAnalysisResult> elems=proj.getAnalysisElementListByClass(PairwiseMultipleListBiclustersResultsBox.class);
			int nelems=0;
			if(elems!=null)
				nelems=elems.size();
			
			nelems=nelems+1;
			String dataid="Pairwise_Multiple_List_Biclusters_"+nelems;
			
			PairwiseMultipleListBiclustersResultsBox resultsbox=new PairwiseMultipleListBiclustersResultsBox(dataid, proj, resultmatrix, orderednames, method);
			resultsbox.setBiclusteringMethodsUsedInPairwiseSimilarity(methods);
			GenericOperation.addAnalysisResult(proj, PairwiseMultipleListBiclustersResultsBox.class, resultsbox, PairwiseMultipleListBiclustersResultsBox.LISTNAME);
			MTUPrintUtils.printDoubleMatrix(resultmatrix);
			
		}
		
	}

}
