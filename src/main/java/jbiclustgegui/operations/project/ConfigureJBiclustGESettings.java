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

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.core.operation.annotation.Progress;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.osystem.JBiclustGESetupManager;
import jbiclustge.utils.osystem.SystemFolderTools;
import jbiclustgegui.operations.components.GeneralOperationCurrentLastTaskStatusLogListener;
import pt.ornrocha.fileutils.MTUFileUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.rtools.installutils.RInstallTools;

@Operation(description="Configure JBiclustGE",enabled = false)
public class ConfigureJBiclustGESettings {
	
	protected String RWorkspaceLib=null;
	protected String algorithmszipfile=null;
	protected boolean forcereconfiguration=false;
	protected GeneralOperationCurrentLastTaskStatusLogListener statuslistner=new GeneralOperationCurrentLastTaskStatusLogListener();
	
	
	@Port(name="rworkspacelib",direction=Direction.INPUT,order=1)
	public void setRWorkspaceDir(String filepath) {
		this.RWorkspaceLib=filepath;
	}
	
	@Port(name="algorithmszip",direction=Direction.INPUT,order=2)
	public void setAlgorithmsZipFile(String filepath) {
		this.algorithmszipfile=filepath;
		LogMessageCenter.getLogger().addStatusLogProgressListener(statuslistner);
		run();
	}
	

	
	private void run() {
		
		
		String R_HOME=null;
		try {
			R_HOME=RInstallTools.getSystemR_HOME();	
		} catch (Exception e) {
			Workbench.getInstance().error(e);
		}
		boolean install=true;
		
		if(R_HOME==null) {
			int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "Not was detected a R environment in this computer.\nDo you want to continue without install R packages dependencies?\n"
					+ "Get R from: https://www.r-project.org/, and install it", "Warning",JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.NO_OPTION)
            	install=false;
			
		}
		
		if(JBiclustGESetupManager.isJbiclustGEConfigured()) {
			
			int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "JbiclustGE it is already configured, do you want to reconfigure it again?.\n", "Warning",JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.NO_OPTION)
            	install=false;
			else
				try {
					JBiclustGESetupManager.resetPreviousConfiguration();
				} catch (IOException e) {
                     LogMessageCenter.getLogger().addErrorMessage(e);
				}
					
					
		}
		
		
		if(install) {
			try {
				
				
				
				
				if(algorithmszipfile!=null) {
					String currentfolder=SystemFolderTools.getCurrentDir();
					String zipfilename=JBiclustGESetupManager.getRequiredZipFileName();
					String zipfilepath=FilenameUtils.concat(currentfolder, zipfilename+".zip");
					MTUFileUtils.copyFile(algorithmszipfile, zipfilepath);
				}
				
				
				
				JBiclustGESetupManager.createJBiclustGEStatus();
			
				try {
					JBiclustGESetupManager.setupExecutableBiclusteringMethods();
				} catch (Exception e) {
					
					int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "An error occurred during installation of the executables of the necessary biclustering algorithms.\n"
							+ "ERROR: "+e.getMessage()+"\n"
							+ "\nDo you want to continue?\n", "Warning",JOptionPane.YES_NO_OPTION);
		            if(option==JOptionPane.NO_OPTION)
		            	install=false;
				}
				if(install) {
					String rpackageslib=JBiclustGESetupManager.setupREnvBiclusteringMethods(RWorkspaceLib,true);
					JBiclustGESetupManager.setupJBiclustGEProperties(rpackageslib);
					JBiclustGESetupManager.addConfiguredTag();
					JBiclustGESetupManager.addInstallationDoneTag();
				}
				else {
					System.exit(0);
				}
				
				//JBiclustGESetupManager.setupJBiclustGEMethodsEnvironment(RWorkspaceLib);
				
				LogMessageCenter.getLogger().removeStatusLogProgressListener(statuslistner);
			} catch (Exception e1) {
				LogMessageCenter.getLogger().addCriticalErrorMessage(e1);
			}
		}
		else
			System.exit(0);
		
	}
	
	@Progress
	public GeneralOperationCurrentLastTaskStatusLogListener status() {
		return statuslistner;
	}
	
	
	@SuppressWarnings("rawtypes")
	static public void executeOperation(){
		for ( OperationDefinition def : Core.getInstance().getOperations()){
			if (def.getID().equals("operation.configure.jbiclustge")){
                  Workbench.getInstance().executeOperation(def);
            }
		}
	}
	

}
