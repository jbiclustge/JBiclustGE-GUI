package jbiclustgegui.gui.components.panels.enrichmentanalysis.clusterprofiler;

import javax.swing.JPanel;

import jbiclustge.enrichmentanalysistools.common.pvaluesAdjustMethod;

public abstract class ClusterProfilerOptionsPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract String getKeggOrganismIdentifier();
	public abstract void setKeggOrganismIdentifier(String id);
	public abstract String getOrgDatabase();
	public abstract void setOrgDatabase(String name);
	public abstract String getKeyType();
	public abstract void setKeyType(String keytype);
	public abstract pvaluesAdjustMethod getAdjustMethod();
	public abstract void setAdjustMethod(pvaluesAdjustMethod method);
	public abstract int getMinGeneSize();
	public abstract void setMinGeneSize(int value);
	public abstract int getMaxGeneSize();
	public abstract void setMaxGeneSize(int value);
	public abstract void resetToDefault();
}
