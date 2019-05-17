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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.core.operation.annotation.Progress;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.osystem.CustomRInstallationManager;
import jbiclustge.utils.osystem.JBiclustGESetupManager;
import jbiclustge.utils.osystem.SystemFolderTools;
import jbiclustge.utils.osystem.progcheck.RInstallerProgressionChecker;
import jbiclustge.utils.props.JBiGePropertiesManager;
import jbiclustgegui.gui.components.containers.RConfigurationProperties;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVSYTEM;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVTYPE;
import jbiclustgegui.gui.components.panels.jbicge.ChangeRLibsFolderPanel;
import jbiclustgegui.gui.components.panels.jbicge.RpackagesInstallationFailedPanel;
import jbiclustgegui.operations.components.GeneralOperationCurrentLastTaskStatusLogListener;
import pt.ornrocha.fileutils.MTUDirUtils;
import pt.ornrocha.fileutils.MTUFileUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.rtools.installutils.RInstallTools;
import pt.ornrocha.systemutils.OSystemUtils;
import pt.ornrocha.systemutils.linuxshell.MTUShellLinux;
import pt.ornrocha.systemutils.linuxshell.listeners.ProgressShellListener;

@Operation(description="Configure JBiclustGE",enabled = false)
public class ConfigureJBiclustGESettings {
	
	protected RConfigurationProperties rconfiguration=null;
	protected String algorithmszipfile=null;

	protected boolean forcereconfiguration=false;
	protected GeneralOperationCurrentLastTaskStatusLogListener statuslistner=new GeneralOperationCurrentLastTaskStatusLogListener();
	
	
	@Port(name="rconfigurations",direction=Direction.INPUT,order=1)
	public void setRConfigurations(RConfigurationProperties rconf) {
		this.rconfiguration=rconf;
	}
	
	@Port(name="algorithmszip",direction=Direction.INPUT,order=2)
	public void setAlgorithmsZipFile(String filepath) {
		this.algorithmszipfile=filepath;
		LogMessageCenter.getLogger().addStatusLogProgressListener(statuslistner);
		run();
	}
	

	
	private void run() {

		String R_PATH=null;
		String R_Libs_Path=null;
		boolean installR=true;
		boolean statusR=true;
		//isCustomRInstall

		if(rconfiguration.getROS().equals(RENVSYTEM.LINUX_DEB)) {


			if(rconfiguration.getTypeinstallation().equals(RENVTYPE.STANDALONE_PREVIOUS)) {
				R_PATH=rconfiguration.getRExecuteFolder();
				R_Libs_Path=rconfiguration.getRPackagesFolder();

				String rootfolder=JBiclustGESetupManager.getRLocalRootFolderFromPath(R_PATH);
				if(JBiclustGESetupManager.usesLocalCompiledLinuxLibs(rootfolder))
					JBiclustGESetupManager.addLibsExportFeaturesConfig(JBiclustGESetupManager.getRLocalRootFolderFromPath(R_PATH));

			}
			else if(rconfiguration.getTypeinstallation().equals(RENVTYPE.STANDALONE_NEW)) {


				if(rconfiguration.candoRinstallation()) {


					CustomRInstallationManager installer=new CustomRInstallationManager(rconfiguration.getVersion());

					boolean rversionexists=false;

					String currentrpath=installer.getR_Path();
					if(currentrpath!=null) {
						File check=new File(currentrpath);
						rversionexists=check.exists();
					}



					if(rversionexists) {
						int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "A custom R environment ["+rconfiguration.getVersion().getVersion()+"] its already installed, do you want to replace?", "Warning",JOptionPane.YES_NO_OPTION);
						if(option==JOptionPane.YES_OPTION) {
							String rfolderpath= FilenameUtils.concat(SystemFolderTools.getCurrentDir(), CustomRInstallationManager.RHomeName+rconfiguration.getVersion().getVersion());
							try {
								MTUDirUtils.deleteDirectory(rfolderpath);
							} catch (IOException e) {
								LogMessageCenter.getLogger().addErrorMessage(e);
							}

						}
						else {
							R_PATH=installer.getR_Path();
							R_Libs_Path=installer.getFolderToRpackages();
							String rootfolder=installer.getRootDir();
							if(JBiclustGESetupManager.usesLocalCompiledLinuxLibs(rootfolder))
								JBiclustGESetupManager.addLibsExportFeaturesConfig(rootfolder);

							installR=false;
						}
					}

					if(installR) {
						//statuslistner.setTaskStatus("Installing R environment "+rconfiguration.getVersion().getVersion()+"\n It will take a while, please wait...");

						if(!rconfiguration.isToCompileLibs()) {



							if(rconfiguration.getDevPackageNames()!=null && rconfiguration.getDevPackageNames().size()>0) {


								statuslistner.setTaskStatus("Installing the required linux libraries\n It will take a while, please wait...");

								ArrayList<String> installlibs=rconfiguration.getDevPackageNames();

								String[] cmds=new String[installlibs.size()+4];
								cmds[0]="apt-get";
								cmds[1]="install";
								cmds[2]="-f";
								cmds[3]="-y";
								for (int i = 0; i < installlibs.size(); i++) {
									cmds[i+4]=installlibs.get(i);
								}

                                LogMessageCenter.getLogger().toClass(getClass()).addTraceMessage("Linux packages: ", new ArrayList<>(Arrays.asList(cmds)));
                                ProgressShellListener list=new ProgressShellListener();
                                list.showInfoOutput(true);
								try {
									statusR = MTUShellLinux.executeCMDsAsSudoLinuxShell(Workbench.getInstance().getMainFrame(), list, cmds);
								} catch (Exception e) {
									LogMessageCenter.getLogger().addCriticalErrorMessage(e);
									Workbench.getInstance().error(e);
									statusR=false;
								}


								if(!statusR) {
									LogMessageCenter.getLogger().addCriticalErrorMessage("Output of linux shell:\n"+list.getShellstdOutput()+"\n\n");
									int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "An error occurred during the installation of the required linux libraries to compile R!\n"
											+ "Do you want to proceed?", "Warning",JOptionPane.YES_NO_OPTION);
									if(option==JOptionPane.NO_OPTION)
										System.exit(0);
									else
										installR=false;
								}

								if(installR) {
									statuslistner.setTaskStatus("Installing R environment "+rconfiguration.getVersion().getVersion()+" and it will take a while, please wait...");
									JBiclustGESetupManager.removeLibsExportFeaturesConfig();
									statusR=installer.installOnlyR().install();
									if(statusR) {
										R_PATH=installer.getR_Path();
										R_Libs_Path=installer.getFolderToRpackages();
									}
								}
							}
							else {
								statuslistner.setTaskStatus("Installing R environment "+rconfiguration.getVersion().getVersion()+" and it will take a while, please wait...");
								JBiclustGESetupManager.removeLibsExportFeaturesConfig();
								statusR=installer.installOnlyR().install();
								if(statusR) {
									R_PATH=installer.getR_Path();
									R_Libs_Path=installer.getFolderToRpackages();
								}

							}

						}

						else if(rconfiguration.isToCompileLibs()) {
							statuslistner.setTaskStatus("Installing R environment "+rconfiguration.getVersion().getVersion()+" and it will take a while, please wait...");
							statusR=installer.install();
							if(statusR) {
								R_PATH=installer.getR_Path();
								R_Libs_Path=installer.getFolderToRpackages();
								JBiclustGESetupManager.addLibsExportFeaturesConfig(installer.getRootDir());
								try {
									JBiclustGESetupManager.addLocalLinuxLibsTag(installer.getRootDir());
								} catch (IOException e) {
									LogMessageCenter.getLogger().addCriticalErrorMessage(e);
								}
							}
						}
					}

				}
			}
			else {
				
				if(rconfiguration.getDevPackageNames()!=null && rconfiguration.getDevPackageNames().size()>0) {


					statuslistner.setTaskStatus("Installing the required linux libraries\n It will take a while, please wait...");

					ArrayList<String> installlibs=rconfiguration.getDevPackageNames();

					String[] cmds=new String[installlibs.size()+4];
					cmds[0]="apt-get";
					cmds[1]="install";
					cmds[2]="-f";
					cmds[3]="-y";
					for (int i = 0; i < installlibs.size(); i++) {
						cmds[i+4]=installlibs.get(i);
					}

                    LogMessageCenter.getLogger().toClass(getClass()).addTraceMessage("Linux packages: ", new ArrayList<>(Arrays.asList(cmds)));
                    ProgressShellListener list=new ProgressShellListener();
                    list.showInfoOutput(true);
					try {
						statusR = MTUShellLinux.executeCMDsAsSudoLinuxShell(Workbench.getInstance().getMainFrame(), list, cmds);
					} catch (Exception e) {
						LogMessageCenter.getLogger().addCriticalErrorMessage(e);
						Workbench.getInstance().error(e);
						statusR=false;
					}


					if(!statusR) {
						LogMessageCenter.getLogger().addCriticalErrorMessage("Output of linux shell:\n"+list.getShellstdOutput()+"\n\n");
						JOptionPane.showMessageDialog(Workbench.getInstance().getMainFrame(), "An error it was detected during the installation of some of the linux libraries!\n Some of the R features may not work!", "Warning", JOptionPane.INFORMATION_MESSAGE);
						statusR=true;
					}
				}
				
				R_Libs_Path=rconfiguration.getRPackagesFolder();
				JBiclustGESetupManager.removeLibsExportFeaturesConfig();
			}
		}
		else if(rconfiguration.getROS().equals(RENVSYTEM.WINDOWS)){
			installR=false;
			R_PATH=null;
			statusR=rconfiguration.candoRinstallation();
		}




		try {
			if(R_PATH==null) 
				R_PATH=RInstallTools.getSystemR_PATH();
			if(R_Libs_Path==null)
				R_Libs_Path=rconfiguration.getRPackagesFolder();
		} catch (Exception e) {
			LogMessageCenter.getLogger().addCriticalErrorMessage(e);
			Workbench.getInstance().error(e);
		}

		boolean install=true;
		
		if(OSystemUtils.isWindows() && R_PATH!=null && !statusR)
			statusR=true;
		

		if(R_PATH!=null && installR && statusR==false) {
			int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "The installation of a local R environment failed.\nHowever an R environment was detected in the system\n"
					+ "Do you want to use it?\n(Yes) use it\n(No) continue without installing R packages dependencies\n"
					+ "(Cancel) quit", "",JOptionPane.YES_NO_CANCEL_OPTION);
			System.out.println("OPTION CHOOSED: "+option);
			if(option==JOptionPane.NO_OPTION) {
				R_PATH=RInstallTools.NONE_R_HOME;
			}
			else if(option==JOptionPane.CANCEL_OPTION)
				System.exit(0);
			else {
				statusR=true;

				if(R_Libs_Path==null) {
					try {
						String libsfolder=JBiclustGESetupManager.checkRLibsInstallPath(R_PATH);
						
						int option2=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "The R packages will be placed to:\n"+libsfolder+
								"\n Do you want to change folder?", "",JOptionPane.YES_NO_OPTION);
						if(option2==JOptionPane.YES_OPTION) {
							ChangeRLibsFolderPanel changefolderp=new ChangeRLibsFolderPanel(libsfolder);
							JOptionPane.showMessageDialog(Workbench.getInstance().getMainFrame(), changefolderp, "R packages will be installed at?", JOptionPane.QUESTION_MESSAGE);
                            String newfolder=changefolderp.getSelectedFolder();
                            if(newfolder!=null && !newfolder.isEmpty())
                            	R_Libs_Path=newfolder;
                            else
                            	R_Libs_Path=libsfolder;
						}
					} catch (Exception e) {
						LogMessageCenter.getLogger().addCriticalErrorMessage(e);
						Workbench.getInstance().error(e);
					}
				}

			}
		}
		else if(R_PATH==null && installR && statusR==false) {
			int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "The installation of a local R environment failed.\nDo you want to continue without install R packages dependencies?\n"
					+ "Some features will be unavailable", "",JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.NO_OPTION)
				install=false;
		}
		else if(R_PATH==null) {
				statusR=false;
		}





		if(JBiclustGESetupManager.isJbiclustGEConfigured()) {

			int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "JbiclustGE it is already configured, do you want to reconfigure it again?.\n", "Warning",JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.NO_OPTION)
				install=false;
			else
				try {

					/*String oldcustomRinstall=(String) JBiGePropertiesManager.getManager().getKeyValue(JBiclustGEPropertiesTags.RUSERPATH);
					if(oldcustomRinstall!=null && isCustomRInstall && oldcustomRinstall==R_PATH) {

					}*/

					JBiclustGESetupManager.resetPreviousConfiguration();
				} catch (IOException e) {
					LogMessageCenter.getLogger().addErrorMessage(e);
				}

		}


		if(install) {
			//try {

            boolean executablesstate=true;

            try {

            	if(algorithmszipfile!=null) {
            		String currentfolder=SystemFolderTools.getCurrentDir();
            		String zipfilename;

            		zipfilename = JBiclustGESetupManager.getRequiredZipFileName();

            		String zipfilepath=FilenameUtils.concat(currentfolder, zipfilename+".zip");
            		MTUFileUtils.copyFile(algorithmszipfile, zipfilepath);
            	}

            	JBiclustGESetupManager.createJBiclustGEStatus();


            	JBiclustGESetupManager.setupExecutableBiclusteringMethods();
            } catch (Exception e) {

            	int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), "An error occurred during installation of the executables of the necessary biclustering algorithms.\n"
            			+ "ERROR: "+e.getMessage()+"\n"
            			+ "\nDo you want to continue?\n", "Warning",JOptionPane.YES_NO_OPTION);
            	if(option==JOptionPane.NO_OPTION)
            		executablesstate=false;
            }


            String rpackageslib=null;

            if(statusR && executablesstate && !R_PATH.equals(RInstallTools.NONE_R_HOME)) {

            	if(R_PATH!=null)
            		OSystemUtils.setEnvVariable("R_HOME", R_PATH);

            	try {

            		JBiclustGESetupManager.settingscontainer.addSetting(RInstallerProgressionChecker.RINSTALLERPROGRESSCHECK, new RInstallerProgressionChecker());

            		rpackageslib=JBiclustGESetupManager.setupREnvironmentPackages(R_PATH,R_Libs_Path,true);

            		if(JBiclustGESetupManager.settingscontainer.containsSetting(RInstallerProgressionChecker.RPACKAGESFAILED)) {
            			LinkedHashMap<String, ArrayList<String>> errorpackages=(LinkedHashMap<String, ArrayList<String>>) JBiclustGESetupManager.settingscontainer.getSetting(RInstallerProgressionChecker.RPACKAGESFAILED);

            			RpackagesInstallationFailedPanel panel=new RpackagesInstallationFailedPanel(errorpackages);
            			JOptionPane.showMessageDialog(Workbench.getInstance().getMainFrame(), panel, "", JOptionPane.WARNING_MESSAGE);//(this, new RpackagesInstallationFailedPanel(errorpackages));

            			//Workbench.getInstance().error("The following packages were not installed: "+failedpackages);
            			JBiclustGESetupManager.settingscontainer.removeSetting(RInstallerProgressionChecker.RPACKAGESFAILED);
            		}
            	} catch (Exception e) {
            		LogMessageCenter.getLogger().addCriticalErrorMessage(e);
                    Workbench.getInstance().error(e);
            	}

            }


            try {
            	if(statusR || executablesstate) {
            		JBiclustGESetupManager.setupJBiclustGEProperties(rpackageslib,R_PATH);
            		JBiclustGESetupManager.addConfiguredTag();
            		JBiclustGESetupManager.addInstallationDoneTag();
            		JBiGePropertiesManager.getManager().reload();
            	}
            	else {
            		Workbench.getInstance().error("JBiclustGE could not be configured due to the lack of the necessary algorithms and R environment");
            		System.exit(0);
            	}
            } catch (Exception e) {
            	LogMessageCenter.getLogger().addCriticalErrorMessage(e);
            	Workbench.getInstance().error(e);
            }

            LogMessageCenter.getLogger().removeStatusLogProgressListener(statuslistner);

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
