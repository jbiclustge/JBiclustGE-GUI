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
package jbiclustgegui.operations.analysis.enrichmentanalysis;

import java.util.Properties;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.core.operation.annotation.Cancel;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.core.operation.annotation.Progress;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.enrichmentanalysistools.clusterprofile.ClusterProfilerKeggPathwayEnrichmentAnalyser;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustge.enrichmentanalysistools.topgo.TopGOEnrichmentAnalyser;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.operations.GenericOperation;
import jbiclustgegui.operations.listeners.GSEAOperationRunListener;
import pt.ornrocha.timeutils.MTUTimeUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TopGOGSEAOperation.
 */
@Operation(name="Run kegg pathway enrichment analysis with ClusterProfiler",description="Execute ClusterProfiler",enabled = false)
public class ClusterProfilerKeggEnrichmentOperation {
	
	/** The expressionset. */
	private ExpressionData expressionset;
	
	/** The proj. */
	private Project proj;
	
	/** The results. */
	private BiclusterList results;
	
	/** The props. */
	private Properties props;
	
	/** The assocresultsname. */
	private String assocresultsname=null;
	
	/** The progressview. */
	private GSEAOperationRunListener progressview=new GSEAOperationRunListener();
	
	/** The stopped. */
	private boolean stopped=false;
	
	/** The analyser. */
	private ClusterProfilerKeggPathwayEnrichmentAnalyser analyser;
	
	
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
	 * Sets the biclustering results.
	 *
	 * @param results the new biclustering results
	 */
	@Port(name="Biclustering results",direction=Direction.INPUT,order=2)
	public void setBiclusteringResults(BiclusterList results) {
		this.results=results;
	}
	
	/**
	 * Sets the top GO settings.
	 *
	 * @param props the new top GO settings
	 */
	@Port(name="Settings analyser",direction=Direction.INPUT,order=3)
	public void setSettings(Properties props) {
		this.props=props;
	}
	
	/**
	 * Sets the name results datatype.
	 *
	 * @param name the new name results datatype
	 */
	@Port(name="Biclustering results name",direction=Direction.INPUT,order=4)
	public void setNameResultsDatatype(String name) {
		this.assocresultsname=name;
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
	private void run() throws Exception {

		analyser= new ClusterProfilerKeggPathwayEnrichmentAnalyser(results, props);
		analyser.addAnalyserPropertyChangeListener(progressview);
		analyser.run();

		GSEAConfigurationContainer configcont=new GSEAConfigurationContainer(props,GSEAAnalyserType.ClusterProfilerKEGGPathway);

		if(!stopped) {
			EnrichmentAnalysisResultList enrichmentresults=analyser.getEnrichmentAnalysisResults();

			IElementList<IAnalysisResult> elems=proj.getAnalysisElementListByClass(EnrichmentAnalysisResultBox.class);
			int nelems=0;
			if(elems!=null)
				nelems=elems.size();

			nelems=nelems+1;
			String dataid="GSEA_result_"+nelems+" "+MTUTimeUtils.getCurrentDateAndTime("yyyy-MM-dd_HH.mm.ss");

			EnrichmentAnalysisResultBox gsearesultbox=new EnrichmentAnalysisResultBox(dataid, proj, enrichmentresults, configcont);
			gsearesultbox.setAssociatedResultDatatype(assocresultsname);
			GenericOperation.addAnalysisResult(proj, EnrichmentAnalysisResultBox.class, gsearesultbox, EnrichmentAnalysisResultBox.LISTNAME);
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
			if(analyser!=null) {
				progressview.setCurrentTask("Stopping ClusterProfiler...");
				progressview.setCurrentSubTask("Stopping ClusterProfiler...");
				analyser.stopProcess();
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
	public GSEAOperationRunListener getStatus() {
		return progressview;
	}

}
