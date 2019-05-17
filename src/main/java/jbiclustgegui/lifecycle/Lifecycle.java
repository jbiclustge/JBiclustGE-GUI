package jbiclustgegui.lifecycle;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ToolTipManager;

import org.apache.commons.io.FilenameUtils;
import org.platonos.pluginengine.PluginLifecycle;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.core.clipboard.ClipboardListener;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.propertiesmodules.PropertyLabels;
import jbiclustge.utils.osystem.JBiclustGESetupManager;
import jbiclustge.utils.osystem.SystemFolderTools;
import jbiclustge.utils.props.JBiGePropertiesManager;
import jbiclustgegui.datatypes.analysis.PairwiseMultipleListBiclustersResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BBCResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BibitResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BicFinderResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BicareFlocResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.biclusteringresults.BimaxResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BimineplusResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.COALESCEResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.CPBResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.ChengAndChurchResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.DebiResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaPResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaSResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.ISAResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.OPSMResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.PenalizedPlaidResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.PlaidResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.QubicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.SpectralResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UbclustResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UnibicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.XmotifsResultsBox;
import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.gui.components.JBiclustLogCenter;
import jbiclustgegui.operations.project.ChangeWorkspace;
import jbiclustgegui.operations.project.ConfigureJBiclustGESettings;
import jbiclustgegui.operations.serializers.analysis.SimilarityMultipleListBiclustersResultsSerializer;
import jbiclustgegui.operations.serializers.gsearesults.GSEAResultSerializer;
import jbiclustgegui.operations.serializers.methodresults.BiclusteringMethodResultSerializer;
import pt.ornrocha.fileutils.MTUFileUtils;
import pt.ornrocha.logutils.MTULogLevel;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.systemutils.OSystemUtils;


/**
 * Orlando Rocha
 *
 */
public class Lifecycle extends PluginLifecycle {
	public void start(){



		System.out.println("Start Plugin");

	    LogMessageCenter.getLogger().enableStackTrace();
	   //LogMessageCenter.getLogger().setLogLevel(MTULogLevel.TRACE);

	    MTUFileUtils.deleteFile(FilenameUtils.concat(SystemFolderTools.getLogFolder(), "jbiclustge_status.log"));
		LogMessageCenter.getLogger().setLogProgressListener(new JBiclustLogCenter(SystemFolderTools.getLogFolder(),"jbiclustge_status"));

		if(!JBiclustGESetupManager.isJbiclustGEConfigured()) {
			
			try {
				ConfigureJBiclustGESettings.executeOperation();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			

		
			while (!JBiclustGESetupManager.isinstallationdone()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {

				}
			}
		}
		
		if(OSystemUtils.isLinux()) {
			String rcustom=(String) JBiGePropertiesManager.getManager().getKeyValue(PropertyLabels.RUSERPATH);
			if(rcustom!=null)
				OSystemUtils.setEnvVariable("R_HOME", rcustom);
		}


			JBiclustGESetupManager.removeInstallationDoneTag();
			LogMessageCenter.getLogger().setLogLevel(MTULogLevel.TRACE);

			Core.getInstance().enableOperation("operation.configure.jbiclustge");
			Core.getInstance().enableOperation("operations.newproject");
			SaveLoadManager.init();

			JBiGePropertiesManager.getManager().addProperty("rserve_type_session", "multiple");
			
	
		    ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

			Workbench.getInstance().getMainFrame().addWindowListener(new WindowListener(){

				public void windowActivated(WindowEvent e) {

				}

				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				public void windowOpened(WindowEvent e) {
					//				removeOperations();

				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub

				}

			});


			ChangeWorkspace.executeOperation();

			//BiclusteringMethodResultSerializer biclusteringresultsserializer=new BiclusteringMethodResultSerializer();
			GSEAResultSerializer gseaserializer=new GSEAResultSerializer();

			try {
				SaveLoadManager.getInstance().registerBuilder(BBCResultsBox.class,new BiclusteringMethodResultSerializer("bbc"));
				SaveLoadManager.getInstance().registerBuilder(BibitResultsBox.class,new BiclusteringMethodResultSerializer("bibit"));
				SaveLoadManager.getInstance().registerBuilder(BicareFlocResultsBox.class,new BiclusteringMethodResultSerializer("bicare"));
				SaveLoadManager.getInstance().registerBuilder(BicFinderResultsBox.class,new BiclusteringMethodResultSerializer("bicfinder"));
				SaveLoadManager.getInstance().registerBuilder(BiclicResultsBox.class,new BiclusteringMethodResultSerializer("biclic"));
				SaveLoadManager.getInstance().registerBuilder(BimaxResultsBox.class,new BiclusteringMethodResultSerializer("bimax"));
				SaveLoadManager.getInstance().registerBuilder(BimineplusResultsBox.class,new BiclusteringMethodResultSerializer("bimineplus"));
				SaveLoadManager.getInstance().registerBuilder(ChengAndChurchResultsBox.class,new BiclusteringMethodResultSerializer("cc"));
				SaveLoadManager.getInstance().registerBuilder(COALESCEResultsBox.class,new BiclusteringMethodResultSerializer("coalesce"));
				SaveLoadManager.getInstance().registerBuilder(CPBResultsBox.class,new BiclusteringMethodResultSerializer("cpb"));
				SaveLoadManager.getInstance().registerBuilder(DebiResultsBox.class,new BiclusteringMethodResultSerializer("debi"));
				SaveLoadManager.getInstance().registerBuilder(FabiaPResultsBox.class,new BiclusteringMethodResultSerializer("fabiap"));
				SaveLoadManager.getInstance().registerBuilder(FabiaResultsBox.class,new BiclusteringMethodResultSerializer("fabia"));
				SaveLoadManager.getInstance().registerBuilder(FabiaSResultsBox.class,new BiclusteringMethodResultSerializer("fabias"));
				SaveLoadManager.getInstance().registerBuilder(ISAResultsBox.class,new BiclusteringMethodResultSerializer("isa"));
				SaveLoadManager.getInstance().registerBuilder(OPSMResultsBox.class,new BiclusteringMethodResultSerializer("opsm"));
				SaveLoadManager.getInstance().registerBuilder(PenalizedPlaidResultsBox.class,new BiclusteringMethodResultSerializer("penalizedplaid"));
				SaveLoadManager.getInstance().registerBuilder(PlaidResultsBox.class,new BiclusteringMethodResultSerializer("plaid"));
				SaveLoadManager.getInstance().registerBuilder(QubicResultsBox.class,new BiclusteringMethodResultSerializer("qubic"));
				SaveLoadManager.getInstance().registerBuilder(SpectralResultsBox.class,new BiclusteringMethodResultSerializer("spectral"));
				SaveLoadManager.getInstance().registerBuilder(UbclustResultsBox.class,new BiclusteringMethodResultSerializer("ubclust"));
				SaveLoadManager.getInstance().registerBuilder(UnibicResultsBox.class,new BiclusteringMethodResultSerializer("unibic"));
				SaveLoadManager.getInstance().registerBuilder(XmotifsResultsBox.class,new BiclusteringMethodResultSerializer("xmotifs"));

				SaveLoadManager.getInstance().registerBuilder(EnrichmentAnalysisResultBox.class,gseaserializer);
				SaveLoadManager.getInstance().registerBuilder(PairwiseMultipleListBiclustersResultsBox.class,new SimilarityMultipleListBiclustersResultsSerializer());

			} catch (Exception e1) {
				e1.printStackTrace();
			}


			Core.getInstance().getClipboard().addClipboardListener(new ClipboardListener(){
				public void elementAdded(ClipboardItem item){
					
					Core.getInstance().enableOperation("operation.synthetic.generate");

					if(Core.getInstance().getClipboard().getItemsByClass(Project.class).size()>0){
						Core.getInstance().enableOperation("operations.project.delete");
						Core.getInstance().enableOperation("operation.import.biclusterlist");

						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","jbimax"))
							Core.getInstance().enableOperation("operation.method.bimax");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("unibic"))
							Core.getInstance().enableOperation("operation.method.unibic");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("BBC"))
							Core.getInstance().enableOperation("operation.method.bbc");
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","BicARE"))
							Core.getInstance().enableOperation("operation.method.floc");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("BicFinder"))
							Core.getInstance().enableOperation("operation.method.bicfinder");
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","BICLIC"))
							Core.getInstance().enableOperation("operation.method.biclic");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("BiMine+"))	
							Core.getInstance().enableOperation("operation.method.bimineplus");
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","biclust")) {
							Core.getInstance().enableOperation("operation.method.cc");
							Core.getInstance().enableOperation("operation.method.plaid");
							Core.getInstance().enableOperation("operation.method.spectral");
							Core.getInstance().enableOperation("operation.method.xmotifs");
						}
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("cpb"))	
							Core.getInstance().enableOperation("operation.method.cpb");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("debi"))
							Core.getInstance().enableOperation("operation.method.debi");
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","fabia")) {
							Core.getInstance().enableOperation("operation.method.fabia");
							Core.getInstance().enableOperation("operation.method.fabiap");
							Core.getInstance().enableOperation("operation.method.fabias");
						}
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","eisa"))
							Core.getInstance().enableOperation("operation.method.isa");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("qubic"))	
							Core.getInstance().enableOperation("operation.method.qubic");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("ubc"))	
							Core.getInstance().enableOperation("operation.method.ubclust");
						if(JBiGePropertiesManager.getManager().isFeatureInstalled("COALESCE"))
							if(!OSystemUtils.isWindows())
								Core.getInstance().enableOperation("operation.method.coalesce");
						
						
						Core.getInstance().enableOperation("operation.method.bibit");
						Core.getInstance().enableOperation("operation.method.opsm");
						Core.getInstance().enableOperation("operation.method.penalizedplaid");
						Core.getInstance().enableOperation("operation.profile.jbiclustgecli");

					}
					if(Core.getInstance().getClipboard().getItemsByClass(BiclusteringResultBox.class).size()>0){
						Core.getInstance().enableOperation("operation.gsea.ontologizer");
						
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","topGO"))
							Core.getInstance().enableOperation("operation.gsea.topgo");
						if(JBiGePropertiesManager.getManager().areFeaturesInstalled("Rserve","clusterProfiler"))
							Core.getInstance().enableOperation("operation.kegg.clusterprofiler");
					}
					if(Core.getInstance().getClipboard().getItemsByClass(BiclusteringResultBox.class).size()>1){
						Core.getInstance().enableOperation("operation.analysis.similarity.multiple");
					}



					//if(PMUtils.useWorkspace()){
					try {
						if(item!=null && SaveLoadManager.getInstance().canSaveObject(item.getUserData())){
							//System.out.println(">>>>>>>>> SERIALIZING = "+item.getUserData().getClass().getCanonicalName());
							SaveLoadManager.getInstance().saveData(item.getUserData());
						}
					} catch (Exception e) {
						Workbench.getInstance().error(e);
						//waitingGUI.close();
						//AIBenchExceptionManager.getInstance().handleException(e);
					}
					//}

				}

				public void elementRemoved(ClipboardItem item) {


				}



			});



	}
	
/*	public static void setEnv(String key, String value) {
	    try {
	        Map<String, String> env = System.getenv();
	        Class<?> cl = env.getClass();
	        Field field = cl.getDeclaredField("m");
	        field.setAccessible(true);
	        Map<String, String> writableEnv = (Map<String, String>) field.get(env);
	        writableEnv.put(key, value);
	    } catch (Exception e) {
	        throw new IllegalStateException("Failed to set environment variable", e);
	    }
	}*/
}
