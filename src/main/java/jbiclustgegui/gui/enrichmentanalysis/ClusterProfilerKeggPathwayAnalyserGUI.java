package jbiclustgegui.gui.enrichmentanalysis;

import jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.clusterprofiler.ClusterProfilerKeggEnrichOptionsPanel;

public class ClusterProfilerKeggPathwayAnalyserGUI extends AbstractGeneEnrichmentAnalyserGUI{

	
	private static final long serialVersionUID = 1L;

	public ClusterProfilerKeggPathwayAnalyserGUI() {
		super();
	}
	
	
	@Override
	protected AbstractGeneEnrichmentAnalyserSettingsPanel getGSEASettingsPanel() {
		return new ClusterProfilerKeggEnrichOptionsPanel();
	}

}
