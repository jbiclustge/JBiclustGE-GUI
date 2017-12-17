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
package jbiclustgegui.gui.views.analysis.gsea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.GSEAInfoPercentageEnrichedBiclusters;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.GSEASettingsInfoPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.GSEASinglePvalueViewPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneEnrichmentAnalysisViewer.
 */
public class GeneEnrichmentAnalysisViewer extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;
	
	/** The panel. */
	private JPanel panel;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The infosettings. */
	private GSEASettingsInfoPanel infosettings;
	
	/** The results. */
	private EnrichmentAnalysisResultBox results;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The panelshow results. */
	private GSEASinglePvalueViewPanel panelshowResults;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The panelpercenrbics. */
	private GSEAInfoPercentageEnrichedBiclusters panelpercenrbics;
	
	/**
	 * Instantiates a new gene enrichment analysis viewer.
	 *
	 * @param results the results
	 */
	public GeneEnrichmentAnalysisViewer(EnrichmentAnalysisResultBox results) {
		this.results=results;
		initGUI();
		initComponents();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(this.tabbedPane, gbc_tabbedPane);
		
		this.panel = new JPanel();
		this.tabbedPane.addTab("Settings used in analysis", null, this.panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		this.panel.setLayout(gbl_panel);
		
		this.infosettings = new GSEASettingsInfoPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		this.panel.add(this.infosettings, gbc_panel_2);
		
		this.panel_1 = new JPanel();
		this.tabbedPane.addTab("View Results", null, this.panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{0.05,1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_21 = new GridBagConstraints();
		gbc_panel_21.insets = new Insets(0, 0, 5, 0);
		gbc_panel_21.fill = GridBagConstraints.BOTH;
		gbc_panel_21.gridx = 0;
		gbc_panel_21.gridy = 0;
		this.panel_1.add(this.panel_2, gbc_panel_21);
		
		this.panelshowResults = new GSEASinglePvalueViewPanel();
		GridBagConstraints gbc_panelshowResults = new GridBagConstraints();
		gbc_panelshowResults.fill = GridBagConstraints.BOTH;
		gbc_panelshowResults.gridx = 0;
		gbc_panelshowResults.gridy = 1;
		this.panel_1.add(this.panelshowResults, gbc_panelshowResults);
		
		this.panel_3 = new JPanel();
		this.tabbedPane.addTab("Percentage Enriched Biclusters", null, this.panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.panelpercenrbics = new GSEAInfoPercentageEnrichedBiclusters();
		GridBagConstraints gbc_panelpercenrbics = new GridBagConstraints();
		gbc_panelpercenrbics.fill = GridBagConstraints.BOTH;
		gbc_panelpercenrbics.gridx = 0;
		gbc_panelpercenrbics.gridy = 0;
		this.panel_3.add(this.panelpercenrbics, gbc_panelpercenrbics);
	}

	/**
	 * Inits the components.
	 */
	protected void initComponents() {
		infosettings.addEnrichmentResultsBox(results);
		try {
			panelshowResults.addResults(results.getResults());
		} catch (NumberFormatException | InstantiationException | IllegalAccessException e) {
			Workbench.getInstance().error(e);
		}
		
		EnrichmentAnalysisResultList l=results.getResults().filterAndProcessResults(0.05, false);
		LinkedHashMap<String, Double>termidfrequency=l.getGotermFrequency();
		
	
		//Entry<String, Double> minvalue=Collections.min(termidfrequency.entrySet(), Map.Entry.comparingByValue());
		//Entry<String, Double> maxvalue=Collections.max(termidfrequency.entrySet(), Map.Entry.comparingByValue());
		
		//System.out.println(minvalue);
		//System.out.println(maxvalue);
		
		panelpercenrbics.setResults(results.getResults());
	}

}
