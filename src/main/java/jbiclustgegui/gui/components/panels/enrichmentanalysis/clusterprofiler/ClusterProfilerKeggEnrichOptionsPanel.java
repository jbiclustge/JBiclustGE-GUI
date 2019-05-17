package jbiclustgegui.gui.components.panels.enrichmentanalysis.clusterprofiler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.clusterprofile.components.ClusterProfileKeyType;
import jbiclustge.enrichmentanalysistools.clusterprofile.components.props.ClusterProfileEnrichKeggPropsContainer;
import jbiclustge.enrichmentanalysistools.common.pvaluesAdjustMethod;
import jbiclustge.propertiesmodules.PropertyLabels;
import pt.ornrocha.propertyutils.PropertiesUtilities;

public class ClusterProfilerKeggEnrichOptionsPanel extends ClusterProfilerExecutionOptionPanel{
	
	private static final long serialVersionUID = 1L;

	
	
	public ClusterProfilerKeggEnrichOptionsPanel() {
		super();
	}

	
	
	@Override
	protected String getBorderTitle() {
		return "ClusterProfiler Kegg pathway";
	}

	@Override
	protected ClusterProfilerOptionsPanel getOptionsPanel() {
		return new ClusterProfilerKeggPanel();
	}

	
	@Override
	public Dimension getPreferredDimensionsToPanel() {
		return new Dimension(1000, 600);
	}
	

	@Override
	public void resetToDefaultValues() {
		super.resetToDefaultValues();
		options_panel.resetToDefault();
		
	}



	@Override
	public Pair<Boolean, String> validSettings() {
		if(options_panel.getKeggOrganismIdentifier().isEmpty())
			return new Pair<Boolean, String>(false, "Please fill the kegg identifier concerning your organism");
		else if(options_panel.getMinGeneSize()==-1) {
			return new Pair<Boolean, String>(false, "Please set a value for the Minimal size of genes annotated by Ontology");
		}
		else if(options_panel.getMaxGeneSize()==-1) {
			return new Pair<Boolean, String>(false, "Please set a value for the Maximal size of genes annotated by Ontology");
		}
		else if(chckbxNewCheckBoxCustomFile.isSelected() && textFieldcustomfile.getText().isEmpty()) {
			return new Pair<Boolean, String>(false, "Please set the path to your Custom file with mapping identifiers");
		}
		
		else if(chckbxNewCheckBoxBITR.isSelected() && ((String)textFieldorgdb.getSelectedItem()).isEmpty()) {
			return new Pair<Boolean, String>(false, "Please set a package name to the ORG annotation database");
		}
		else if(chckbxNewCheckBoxBITR.isSelected() && ((String)comboBoxfromid.getSelectedItem()).isEmpty()) {
			return new Pair<Boolean, String>(false, "Please set a value in \"Convert from ids\" field");
		}
		else if(chckbxNewCheckBoxBITR.isSelected() && ((String) comboBoxtoid.getSelectedItem()).isEmpty()) {
			return new Pair<Boolean, String>(false, "Please set a value in \"Convert to ids\" field");
		}
		
		return new Pair<Boolean, String>(true, null);
	}

	@Override
	public Properties getExecutionSettings() {
		
		ClusterProfileEnrichKeggPropsContainer props=new ClusterProfileEnrichKeggPropsContainer();
		
		props.setKeggOrganismID(options_panel.getKeggOrganismIdentifier());
		props.setKeyType(options_panel.getKeyType());
		props.setminGSSize(options_panel.getMinGeneSize());
		props.setmaxGSSize(options_panel.getMaxGeneSize());
		props.setPvalueAdjustMethod(options_panel.getAdjustMethod());
		
		if(chckbxNewCheckBoxBITR.isSelected()) {
			props.setBitrConfiguration((String)textFieldorgdb.getSelectedItem(), (String)comboBoxfromid.getSelectedItem(),(String) comboBoxtoid.getSelectedItem());
		}
		else if(chckbxNewCheckBoxCustomFile.isSelected()) {
			props.setProbidsToAnnotaionIdsFileMap(textFieldcustomfile.getText());
		}
		
		
		return props;
	}


	@Override
	public void loadProperties(Properties props) {
		
		if(props!=null) {
			
			options_panel.setKeggOrganismIdentifier(PropertiesUtilities.getStringPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.KEGGORGANISMID, null, getClass()));
			options_panel.setKeyType(PropertiesUtilities.getStringPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.KEYTYPE, ClusterProfileKeyType.kegg.getName(),getClass()));
			options_panel.setAdjustMethod(pvaluesAdjustMethod.getMTCMethodFromString(PropertiesUtilities.getStringPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.ADJUSTMETHOD, pvaluesAdjustMethod.NONE.toString(), getClass())));
			options_panel.setMinGeneSize(PropertiesUtilities.getIntegerPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.MINGSSIZE, 10, getClass()));
			options_panel.setMaxGeneSize(PropertiesUtilities.getIntegerPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.MAXGSSIZE, 500, getClass()));
			
			if(PropertiesUtilities.isValidProperty(props, PropertyLabels.MAPPROBEID2GENEID)) {
				
				chckbxNewCheckBoxCustomFile.setSelected(true);
				
				String filepath=PropertiesUtilities.getStringPropertyValue(props, PropertyLabels.MAPPROBEID2GENEID, null, getClass());
				textFieldcustomfile.setText(filepath);
				chooseCustomFile();
				
			}
			else if(PropertiesUtilities.isValidProperty(props, ClusterProfileEnrichKeggPropsContainer.USEBITRCONVERTER)) {
				
				boolean use=PropertiesUtilities.getBooleanPropertyValue(props, ClusterProfileEnrichKeggPropsContainer.USEBITRCONVERTER,false, getClass());
				
				if(use && PropertiesUtilities.isValidProperty(props, ClusterProfileEnrichKeggPropsContainer.BITRORGANISMDB)
					   && PropertiesUtilities.isValidProperty(props, ClusterProfileEnrichKeggPropsContainer.CONVERTFROMKEY)
					   && PropertiesUtilities.isValidProperty(props, ClusterProfileEnrichKeggPropsContainer.CONVERTTOKEY)) {
					try {
						textFieldorgdb.addAndCheck(PropertiesUtilities.getStringPropertyValue(props,  ClusterProfileEnrichKeggPropsContainer.BITRORGANISMDB,null,getClass()));
						
						String fromid=PropertiesUtilities.getStringPropertyValue(props,ClusterProfileEnrichKeggPropsContainer.CONVERTFROMKEY, null, getClass());
						String toid=PropertiesUtilities.getStringPropertyValue(props,ClusterProfileEnrichKeggPropsContainer.CONVERTTOKEY, null, getClass());
						
						if(((DefaultComboBoxModel)comboBoxfromid.getModel()).getIndexOf(fromid)==-1) {
							comboBoxfromid.addItem(fromid);
						}
						
						comboBoxfromid.setSelectedItem(fromid);
						
						
						if(((DefaultComboBoxModel)comboBoxtoid.getModel()).getIndexOf(fromid)==-1) {
							comboBoxtoid.addItem(fromid);
						}
							
						comboBoxtoid.setSelectedItem(toid);	
						
						chckbxNewCheckBoxBITR.setSelected(true);
						chooseBitr();
							
							
					} catch (IOException e) {
						Workbench.getInstance().error(e);
					}
				}
			}

		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		String cmd=e.getActionCommand();

		/*if(cmd.equals(RESET))
			resetToDefaultValues();*/

	}

	public static void main(String[] args) {
		try {
			JFrame dialog = new JFrame();
			dialog.getContentPane().add(new ClusterProfilerKeggEnrichOptionsPanel());
			dialog.setSize(new Dimension(1000, 600));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	



	
}
