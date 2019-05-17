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
 * Created by Orlando Rocha (ornrocha@gmail.com) inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.operations.profilecli;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.core.operation.annotation.Direction;
import es.uvigo.ei.aibench.core.operation.annotation.Operation;
import es.uvigo.ei.aibench.core.operation.annotation.Port;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustge.enrichmentanalysistools.ontologizer.components.OntologizerPropertiesContainer;
import jbiclustge.enrichmentanalysistools.topgo.components.TopGoPropertiesContainer;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.propertiesmodules.PropertiesModules;
import jbiclustge.propertiesmodules.PropertyLabels;
import jbiclustge.utils.props.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.analysis.profilecli.components.ProfileBiclusteringMethodConfiguration;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import pt.ornrocha.fileutils.MTUDirUtils;
import pt.ornrocha.propertyutils.EnhancedPropertiesWithSubGroups;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileCLIMaker.
 */
@Operation(name="Make Profile to JBiclustGE-CLI",description="Configures a profile to execute in commandline interface of JBiclustGE-CLI",enabled = false)

public class ProfileCLIMaker {
	
	
	/** The proj. */
	private Project proj;
	
	/** The methodsconfs. */
	private ArrayList<ProfileBiclusteringMethodConfiguration> methodsconfs;
	
	/** The profilename. */
	private String profilename;
	
	/** The saveatfolder. */
	private String saveatfolder;
	
	/** The gseaconfig. */
	private GSEAConfigurationContainer gseaconfig;
	
	/** The numberprocesses. */
	private int numberprocesses;
	
	/** The pvalues. */
	private ArrayList<Double> pvalues;
	
	/** The useadjustedovalues. */
	private boolean useadjustedovalues=false;
	
	private boolean usingconfigmultithread=false;
	
	/**
	 * Sets the project.
	 *
	 * @param proj the new project
	 */
	@Port(name="Project",direction=Direction.INPUT,order=1)
	public void setProject(Project proj) {
		this.proj=proj;
	}
	
	/**
	 * Sets the configurations of methods.
	 *
	 * @param confs the new configurations of methods
	 */
	@Port(name="Configurations methods",direction=Direction.INPUT,order=2)
	public void setConfigurationsOfMethods(ArrayList<ProfileBiclusteringMethodConfiguration> confs) {
		this.methodsconfs=confs;
	}
	
	/**
	 * Sets the name of profile.
	 *
	 * @param name the new name of profile
	 */
	@Port(name="Profile name",direction=Direction.INPUT,order=3)
	public void setNameOfProfile(String name) {
		this.profilename=name;
	}
	
	/**
	 * Sets the path of profile.
	 *
	 * @param path the new path of profile
	 */
	@Port(name="Profile path",direction=Direction.INPUT,order=4)
	public void setPathOfProfile(String path) {
		this.saveatfolder=path;
	}
	
	
	/**
	 * Sets the GSEA configuration.
	 *
	 * @param config the new GSEA configuration
	 */
	@Port(name="GSEA configuration",direction=Direction.INPUT,order=5)
	public void setGSEAConfiguration(GSEAConfigurationContainer config) {
		this.gseaconfig=config;
	}
	
	/**
	 * Sets the number concurrentprocesses.
	 *
	 * @param n the new number concurrentprocesses
	 */
	@Port(name="Concurrent Processes",direction=Direction.INPUT,order=6)
	public void setNumberConcurrentprocesses(int n) {
		this.numberprocesses=n;
	}
	
	/**
	 * Sets the pvalues.
	 *
	 * @param pvalues the new pvalues
	 */
	@Port(name="p-values",direction=Direction.INPUT,order=7)
	public void setPvalues(ArrayList<Double> pvalues) {
		this.pvalues=pvalues;
	}
	
	/**
	 * Sets the adjusted pvalues.
	 *
	 * @param adj the new adjusted pvalues
	 */
	@Port(name="adjusted p-values",direction=Direction.INPUT,order=8)
	public void setAdjustedPvalues(boolean adj) {
		this.useadjustedovalues=adj;
	}
	
	
	@Port(name="config multithread",direction=Direction.INPUT,order=9)
	public void setIsUsingConfigurationMultithread(boolean val) {
		this.usingconfigmultithread=val;
		try {
			run();
		} catch (IOException e) {
			Workbench.getInstance().error(e);
		}
	}
	
	
	/**
	 * Run.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void run() throws IOException{
		
		
		 String savetofolder=FilenameUtils.concat(saveatfolder, profilename);
		 MTUDirUtils.checkandsetDirectory(savetofolder);
		 
		 ExpressionData data=proj.getExpressionDataset().getExpressionset();
		 
		 data.writeExpressionDatasetToFile(FilenameUtils.concat(savetofolder, "dataset.csv"));
		
		 EnhancedPropertiesWithSubGroups props=new EnhancedPropertiesWithSubGroups();
		 
		 String dirmethods=FilenameUtils.concat(savetofolder, PropertyLabels.ALGORITHMSCONFFOLDERNAME);
		 MTUDirUtils.checkandsetDirectory(dirmethods);
		 
		 if(usingconfigmultithread)
			 props.addPropertyToGroupCategory("Biclustering Algorithms", PropertyLabels.ALGORITHMSCONFTYPE,  PropertyLabels.ALGORITHMSCONFTYPEMULTI, "Define if each configuration for an algorithm can be executed in parallel (multithreading), options: single_run or multi_run");
		 else
			 props.addPropertyToGroupCategory("Biclustering Algorithms", PropertyLabels.ALGORITHMSCONFTYPE,  PropertyLabels.ALGORITHMSCONFTYPESINGLE, "Define if each configuration for an algorithm can be executed in parallel (multithreading), options: single_run or multi_run");
		 
		 for (int i = 0; i < methodsconfs.size(); i++) {
			
			 ProfileBiclusteringMethodConfiguration methodconfig=methodsconfs.get(i);
			 
			 BiclusteringMethod method=methodconfig.getMethod();
			 AlgorithmProperties methodprops=(AlgorithmProperties) methodconfig.getMethodsettings();
			 if(method.isSupported()) { 
				 AlgorithmProperties.writeDefaultAlgorithmPropertiesToFile(methodconfig.getSufixname(), dirmethods, method, methodprops, true);
				 
				 int methodnruns=methodconfig.getRuntimes();
				 String confname=method.getAlgorithmID().toLowerCase()+"_"+methodconfig.getSufixname();
				 PropertiesModules.addBiclusteringMethodsRunningTimesOfConfiguration(props, confname, methodnruns);
			 }
		 
		 }
		 
		 props.addPropertyToGroupCategory("Concurrent Processes", PropertyLabels.SIMULTANEOUSPROCESSES, String.valueOf(numberprocesses), "Number of simultaneous processes running in parallel");
		 
		if(gseaconfig!=null) {
			
			EnhancedPropertiesWithSubGroups gseaprops=new EnhancedPropertiesWithSubGroups();
			
			GSEAAnalyserType gseatype=gseaconfig.getAnalyserType();
			String gseafilepath=null;
			
			if(gseatype.equals(GSEAAnalyserType.Ontologizer)) {
				gseafilepath=FilenameUtils.concat(savetofolder, "Ontologizer_configuration.conf");
				OntologizerPropertiesContainer.writeOntologizerProperties((OntologizerPropertiesContainer) gseaconfig.getConfig(), gseafilepath);
				gseaprops.addPropertyToGroupCategory("Enrichment Analysis",PropertyLabels.GSEAPROCESSOR, "ontologizer", "Perform enrichment analysis with: ontologizer or topgo (default=ontologizer)");
			}
			else {
				gseafilepath=FilenameUtils.concat(savetofolder, "topGO_configuration.conf");
				TopGoPropertiesContainer.writeTopGOProperties((TopGoPropertiesContainer) gseaconfig.getConfig(), gseafilepath);
			}
			
	
			gseaprops.addPropertyToGroupCategory("Enrichment Analysis", PropertyLabels.GSEAOUTPVALUES, getStringPvalues(), "p-values to use in GSEA processor, if more than one value is defined, these must be separated by semi-colon(;)");
			gseaprops.addPropertyToGroupCategory("Enrichment Analysis", PropertyLabels.GSEAUSEADJUSTEDPVALUES, useadjustedovalues?"true":"false", "If it is used adjusted p-values in GSEA analysis, this will depends if the processor supports adjusted p-values or the input configurations to GSEA processor.");
			
			props.appendProperties(gseaprops);
		}
		
		
		String propsfilepath=FilenameUtils.concat(savetofolder, PropertyLabels.PROFILEFILENAME);
		props.store(new FileWriter(propsfilepath), true);
		
		StringBuilder msg=new StringBuilder();
		msg.append("<html>");
		msg.append("The profile was saved at: "+savetofolder+"<br>");
		msg.append("You can now run it in JBiclustGE-CLI<br>");
		msg.append("Using the following cmd:<br>"); 
		msg.append("java -jar jbiclustge-cli.jar -run "+savetofolder);
		msg.append("</html>");
		
		JOptionPane.showMessageDialog(Workbench.getInstance().getMainFrame(),msg.toString(), "The profile was created successfully", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	/**
	 * Gets the string pvalues.
	 *
	 * @return the string pvalues
	 */
	private String getStringPvalues() {
		StringBuilder str=new StringBuilder();
		
		for (int i = 0; i < pvalues.size(); i++) {
			str.append(String.valueOf(pvalues.get(i)));
			if(i<(pvalues.size()-1))
				str.append(";");
		}
		return str.toString();
	}

}
