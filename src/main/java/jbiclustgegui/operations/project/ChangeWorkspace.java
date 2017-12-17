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

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.Launcher;
import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;


@Operation(description="Change Workspace",enabled = false)
public class ChangeWorkspace {
	protected File file;
	
//	final public WorkspaceStatusHandler status = new WorkspaceStatusHandler();
	
	protected String error = "";
	@Port(name="ws",direction=Direction.INPUT,order=1)
	public void setFile(File file) {
		
		
		this.file = file;
		
		
		
		boolean b = SaveLoadManager.getInstance().testWorkspaceFolderIsLocked(file);
		
		if(b){
			error = "The selected workspace is already in use by another OptFlux instance.";
			exceptionExecution(error);
		}else{
			try {
				SaveLoadManager.getInstance().setWorkspaceFolder(file);
			} catch (IOException e) {
				exceptionExecution(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
	}
	
	public File getDefaultWS(){
		return new File(SaveLoadManager.getInstance().getWorkspace());
	}
	
	
	static public void exceptionExecution(final String error){
		new Thread(){
            public void run(){
            	int opt = JOptionPane.showConfirmDialog(
				Workbench.getInstance().getMainFrame().getContentPane(), 
				error+"\nChoose another workspace.",
				"Workspace selection error", JOptionPane.OK_CANCEL_OPTION);
		
			if(opt==JOptionPane.OK_OPTION)
				executeOperation();
			else{
				String ws = SaveLoadManager.getInstance().getWorkspace();
				if(ws == null || ws.equals("")){
					Launcher.getPluginEngine().shutdown();
	                System.exit(0);
				}
			}
           }
		}.start();
	}
	
	@SuppressWarnings("rawtypes")
	static public void executeOperation(){
		for ( OperationDefinition def : Core.getInstance().getOperations()){
			if (def.getID().equals("saveloadquit.operations.changeworkspace")){
                  Workbench.getInstance().executeOperation(def);
            }
		}
	}



}


