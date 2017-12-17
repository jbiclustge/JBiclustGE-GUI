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
package jbiclustgegui.gui.enrichmentanalysis;

import jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.OntologizerExecutionOptionPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class OntologizerAnalyserGUI.
 */
public class OntologizerAnalyserGUI extends AbstractGeneEnrichmentAnalyserGUI{

	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ontologizer analyser GUI.
	 */
	public OntologizerAnalyserGUI(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.enrichmentanalysis.AbstractGeneEnrichmentAnalyserGUI#getGSEASettingsPanel()
	 */
	@Override
	protected AbstractGeneEnrichmentAnalyserSettingsPanel getGSEASettingsPanel() {
		return new OntologizerExecutionOptionPanel();
	}

}
