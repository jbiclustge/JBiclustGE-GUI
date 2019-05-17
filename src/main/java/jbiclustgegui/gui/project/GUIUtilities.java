package jbiclustgegui.gui.project;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.inputgui.ParamsWindow;
import jbiclustgegui.datatypes.project.AbstractDataType;

public class GUIUtilities {
	
	
	public static AbstractDataType getSelectedItem(){
		JTree tree = Workbench.getInstance().getTreeManager().getAIBenchClipboardTree();
		TreePath selPath = tree.getSelectionPath();
		
		AbstractDataType ret = null;
		if (selPath != null) {
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
			if (node.getUserObject() instanceof ClipboardItem){
				ClipboardItem item = (ClipboardItem)node.getUserObject();
				ParamsWindow.preferredClipboardItem = item;
				Object o = item.getUserData();
				
				
				if(o != null && AbstractDataType.class.isAssignableFrom(o.getClass()))
						ret = (AbstractDataType) o; //O ITEM SELECCIONADO...!
			}
		}
		return ret;
	}

}
