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
package jbiclustgegui.datatypes.enrichmentanalysisresults;

import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class EnrichmentAnalysisResultBox.
 */
@Datatype(structure=Structure.SIMPLE,namingMethod="getName",setNameMethod="setName", removeMethod="remove")
public class EnrichmentAnalysisResultBox extends AbstractDataType implements IAnalysisResult{
	
	
	
	/** The proj. */
	protected Project proj;
	
	/** The results. */
	protected EnrichmentAnalysisResultList results;
	
	/** The settingsused. */
	protected GSEAConfigurationContainer settingsused;
	
	/** The associatedresultdatatype. */
	protected String associatedresultdatatype=null;
	
	/** The listname. */
	public static String LISTNAME="Gene Enrichment Analysis Results";
	
	/**
	 * Instantiates a new enrichment analysis result box.
	 *
	 * @param name the name
	 * @param proj the proj
	 * @param results the results
	 * @param settingsused the settingsused
	 */
	public EnrichmentAnalysisResultBox(String name, Project proj,EnrichmentAnalysisResultList results, GSEAConfigurationContainer settingsused) {
		super(name);
		this.proj=proj;
		this.results=results;
		this.settingsused=settingsused;

	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public EnrichmentAnalysisResultList getResults() {
		return results;
	}

	/**
	 * Gets the settings used.
	 *
	 * @return the settings used
	 */
	public GSEAConfigurationContainer getSettingsUsed() {
		return settingsused;
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#getByClass()
	 */
	@Override
	public Class<?> getByClass() {
		return EnrichmentAnalysisResultBox.class;
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
	 * Gets the associated result datatype.
	 *
	 * @return the associated result datatype
	 */
	public String getAssociatedResultDatatype() {
		return associatedresultdatatype;
	}

	/**
	 * Sets the associated result datatype.
	 *
	 * @param associatedresultdatatype the new associated result datatype
	 */
	public void setAssociatedResultDatatype(String associatedresultdatatype) {
		this.associatedresultdatatype = associatedresultdatatype;
	}
	
	
	


}
