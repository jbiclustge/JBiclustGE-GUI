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
 * Created inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.operations.project;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.datatypes.project.states.ClosedProject;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;

@Operation(name = "Delete project", description = "Delete project", enabled = false)
public class DeleteProject {

	protected AbstractDataType toDelete = null;

	@Port(name = "Project", direction = Direction.INPUT, order = 1)
	public void setProject(AbstractDataType proj) throws InvocationTargetException, InterruptedException {
		this.toDelete = proj;
		delete();
	}

	private void delete() throws InvocationTargetException, InterruptedException{
		
		if(toDelete != null){
			AbstractDataType proj = toDelete.getOwnerProject();
			Class<?> klazz = Project.class;
			
			if(proj==null && ClosedProject.class.isAssignableFrom(toDelete.getClass())){
				proj=this.toDelete;
				klazz=ClosedProject.class;
			}
	
			
			int option = JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(),
					"Do you want to delete the project \"" +proj.getName()+ "\" permanently?", "Deleting project \"" + proj.getName()+"\"" , JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if(option == JOptionPane.YES_OPTION){
				ClipboardItem removeClip = null;
				List<ClipboardItem> items =Core.getInstance().getClipboard().getItemsByClass(klazz);
				if(!items.isEmpty())
					for(ClipboardItem item: items){						
						if(item.getUserData().equals(proj)){
							removeClip = item;
							break;
						}
					}
				
				if(removeClip!=null){
					final ClipboardItem torem = removeClip;
					SwingUtilities.invokeAndWait(new Runnable() {
						
						@Override
						public void run() {							
							Core.getInstance().getClipboard().removeClipboardItem(torem);
						}
					});
				}
				
				/*if(klazz.isAssignableFrom(ClosedProject.class)){
					String file = SaveLoadManager.getInstance().getWorkspace()+ SaveLoadManager.SYSTEM_SEPARATOR+((ClosedProject)proj).getFileName();
					FileUtils.remove(file);
				}*/
				
	
					String file = SaveLoadManager.getInstance().getWorkspace()+ SaveLoadManager.SYSTEM_SEPARATOR+((Project)proj).getProjectFolderName();
					FileUtils.remove(file);
				
			}
		}
	}
}
