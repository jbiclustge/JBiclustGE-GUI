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

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.core.operation.annotation.Cancel;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.core.operation.annotation.Progress;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalyserProcessor;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustge.enrichmentanalysistools.ontologizer.OntologizerEnrichmentAnalyser;
import jbiclustge.enrichmentanalysistools.topgo.TopGOEnrichmentAnalyser;
import jbiclustge.methods.algorithms.AbstractBiclusteringAlgorithmCaller;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.operations.GenericOperation;
import jbiclustgegui.operations.listeners.BiclusteringAndGSEAOperationRunListener;
import pt.ornrocha.timeutils.MTUTimeUtils;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMethodOperation.
 */
@Operation(name="Run Biclustering Method",description="Execute Algorithm",enabled = false)
public abstract class AbstractMethodOperation {



	/** The expressionset. */
	protected ExpressionData expressionset;
	
	/** The proj. */
	protected Project proj;
	
	/** The props. */
	protected Properties props;
	
	/** The gseaconfigs. */
	protected ArrayList<GSEAConfigurationContainer> gseaconfigs;
	
	/** The stopped. */
	protected boolean stopped=false;
	
	/** The gseaanalyser. */
	protected EnrichmentAnalyserProcessor gseaanalyser=null;
	
	/** The progressview. */
	//protected MethodProgressViewer progressview=new MethodProgressViewer();
	protected BiclusteringAndGSEAOperationRunListener progressview=new BiclusteringAndGSEAOperationRunListener();

	/**
	 * Run method.
	 *
	 * @return the bicluster list
	 */
	protected abstract BiclusterList runMethod();
	
	/**
	 * Adds the results to clipboard.
	 *
	 * @param results the results
	 * @return the string
	 * @throws InvalidElementListException the invalid element list exception
	 */
	protected abstract String addResultsToClipboard(BiclusterList results) throws InvalidElementListException;

	/**
	 * Sets the project.
	 *
	 * @param proj the new project
	 */
	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProject(Project proj) {
		this.proj=proj;
		this.expressionset = proj.getExpressionDataset().getExpressionset();
	}



	/**
	 * Sets the settings.
	 *
	 * @param props the new settings
	 */
	@Port(name="Method settings",direction=Direction.INPUT,order=2)
	public void setSettings(Properties props) {
		this.props=props;
	}

	/**
	 * Sets the GSEA configurations.
	 *
	 * @param configs the new GSEA configurations
	 */
	@Port(name="GSEA configs",direction=Direction.INPUT,order=3)
	public void setGSEAConfigurations(ArrayList<GSEAConfigurationContainer> configs) {
		this.gseaconfigs=configs;
		try {
			run();
		} catch (InvalidElementListException e) {
			Workbench.getInstance().error(e);
		}
	}

	/**
	 * Gets the algorithm.
	 *
	 * @return the algorithm
	 */
	protected abstract AbstractBiclusteringAlgorithmCaller getAlgorithm();
	
	/**
	 * Gets the algorithm name.
	 *
	 * @return the algorithm name
	 */
	protected abstract String getAlgorithmName();

	/**
	 * Run.
	 *
	 * @throws InvalidElementListException the invalid element list exception
	 */
	public void run() throws InvalidElementListException {
		//progressview.setName(getAlgorithmName());

		BiclusterList results=runMethod();
		//ArrayList<Pair<EnrichmentAnalysisResultList, GSEAConfigurationContainer>> GSEAToClipboard=null;

		if(!stopped) {

			

			if(results!=null && results.size()>0) {
				String resultsid=addResultsToClipboard(results); 

				if(gseaconfigs!=null) {

					for (int i = 0; i < gseaconfigs.size() && !stopped; i++) {
						GSEAConfigurationContainer config=gseaconfigs.get(i);
						GSEAAnalyserType type=config.getAnalyserType();
						Properties props=config.getConfig();

						if(type.equals(GSEAAnalyserType.Ontologizer)) {
							gseaanalyser= new OntologizerEnrichmentAnalyser(results, props);
							gseaanalyser.addAnalyserPropertyChangeListener(progressview);
							try {
								gseaanalyser.run();
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}
						else {
							gseaanalyser= new TopGOEnrichmentAnalyser(results, props);
							gseaanalyser.addAnalyserPropertyChangeListener(progressview);
							try {
								gseaanalyser.run();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if(!stopped) {
							EnrichmentAnalysisResultList enrichmentresults=gseaanalyser.getEnrichmentAnalysisResults();
							IElementList<IAnalysisResult> elems=proj.getAnalysisElementListByClass(EnrichmentAnalysisResultBox.class);
							int nelems=0;
							if(elems!=null)
								nelems=elems.size();

							nelems=nelems+1;
							String dataid="GSEA_result_"+nelems+" "+MTUTimeUtils.getCurrentDateAndTime("yyyy-MM-dd_HH.mm.ss");
							EnrichmentAnalysisResultBox gsearesultbox=new EnrichmentAnalysisResultBox(dataid, proj,  enrichmentresults, config);
							gsearesultbox.setAssociatedResultDatatype(resultsid);
							GenericOperation.addAnalysisResult(proj, EnrichmentAnalysisResultBox.class, gsearesultbox, EnrichmentAnalysisResultBox.LISTNAME);
						}

					}
				}

			
			}
			else
				Workbench.getInstance().warn(getAlgorithm().getAlgorithmName()+" was unable to find biclusters, please change the parameters of this method and try again");

		}
	}

	/**
	 * Cancel.
	 */
	@Cancel
	public void cancel(){

		Object[] options = {"Yes","No"};

		int result = JOptionPane.showOptionDialog(Workbench.getInstance().getMainFrame(), "Cancel Execution?", "Cancel Operation", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options, options[1]);

		if(result==JOptionPane.YES_OPTION) {
			stopped=true;
			AbstractBiclusteringAlgorithmCaller method=getAlgorithm();
			method.stopProcess();
			if(gseaanalyser!=null) {
				progressview.setCurrentTask("Stopping GSEA analyser...");
				gseaanalyser.stopProcess();
			}
		}
		else if(result==JOptionPane.NO_OPTION)
			return;
	}



	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Progress()
	public BiclusteringAndGSEAOperationRunListener getStatus() {
		return progressview;
	}



}
