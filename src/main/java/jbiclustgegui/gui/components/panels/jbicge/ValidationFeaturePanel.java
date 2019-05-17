package jbiclustgegui.gui.components.panels.jbicge;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.props.JBiGePropertiesManager;

public class ValidationFeaturePanel {
	
	
	public static boolean canExecuteAction(String requiredpackage) {
		
		if(!JBiGePropertiesManager.getManager().isFeatureInstalled(requiredpackage)) {
			JOptionPane.showMessageDialog(Workbench.getInstance().getMainFrame(), "This function cannot be executed because the required package ["+
		          requiredpackage+"] is not available", "Execution error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	
	

	public static boolean canExecuteActions(String...requiredpackages) {
		
		for (int i = 0; i < requiredpackages.length; i++) {
			if(!canExecuteAction(requiredpackages[i])) {
				return false;
			}
		}
		return true;
	}

}
