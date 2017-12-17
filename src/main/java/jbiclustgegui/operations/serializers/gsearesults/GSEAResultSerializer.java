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
package jbiclustgegui.operations.serializers.gsearesults;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.enrichmentanalysisresults.EnrichmentAnalysisResultBox;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.datatypes.project.serializefunctions.AbstractBuilder;
import jbiclustgegui.datatypes.project.serializefunctions.ISerializer;
import jbiclustgegui.datatypes.project.serializefunctions.SerializeStructure;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.operations.GenericOperation;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAResultSerializer.
 */
public class GSEAResultSerializer extends AbstractBuilder<EnrichmentAnalysisResultBox>{

	/** The Constant SUFIX. */
	private static final String SUFIX = "GSEARES";
	
	/** The Constant NAME. */
	public static final String NAME = "NAME";
	
	/** The Constant GSEARESULTS. */
	public static final String GSEARESULTS = "GSEARESULTS";
	
	/** The Constant GSEACONFIG. */
	public static final String GSEACONFIG = "GSEACONFIG";
	
	/** The Constant ASSOCRESULTS. */
	public static final String ASSOCRESULTS="associatedresults";

	
	
	/**
	 * Instantiates a new GSEA result serializer.
	 */
	public GSEAResultSerializer() {
		this(null);
	}
	
	
	/**
	 * Instantiates a new GSEA result serializer.
	 *
	 * @param serializer the serializer
	 */
	public GSEAResultSerializer(ISerializer<SerializeStructure> serializer) {
		setSerializer(serializer);
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#buildAndSerialize(java.lang.Object)
	 */
	@Override
	public void buildAndSerialize(EnrichmentAnalysisResultBox obj) throws Exception {
		
		String projectId =  obj.getOwnerProject().getProjectFolderName();
		
		String file = SaveLoadManager.SYSTEM_SEPARATOR + projectId 
					+ SaveLoadManager.SYSTEM_SEPARATOR + SaveLoadManager.BASE_DATATYPE_FOLDER 
					+ SaveLoadManager.SYSTEM_SEPARATOR + getPrefix()+"." + convertName(obj.getName())+".ss";
		

		SerializeStructure ss = SerializeStructure.createEmptyStructure();
		
		EnrichmentAnalysisResultList gsearesult=obj.getResults();


		ss.putContainedField(GSEARESULTS,gsearesult.getCloneWithoutExpressionDataAssociation());
		ss.putContainedField(GSEACONFIG, obj.getSettingsUsed());
		ss.putContainedField(ASSOCRESULTS, obj.getAssociatedResultDatatype());
		ss.putContainedField(NAME, obj.getName());
		
		getSerializer().serialize(ss, new File(getWorkspace() + file));
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#deserializeAndBuild(java.io.File, java.util.Map)
	 */
	@Override
	public EnrichmentAnalysisResultBox deserializeAndBuild(File file, Map<String, Object> referencedObjects) throws Exception {
	
		SerializeStructure ss = loadStructure(file.getAbsolutePath());
		
		GeneExpressionDatasetBox dataBox=(GeneExpressionDatasetBox) referencedObjects.get(SaveLoadManager.EXPRESSIONDATA);

		EnrichmentAnalysisResultList gsearesults=(EnrichmentAnalysisResultList) referencedObjects.get(ss.getUID(GSEARESULTS));
		BiclusterList bicresults=gsearesults.getBiclusterlistAssociated();
		bicresults.addExpressionDatasetDependencies(dataBox.getExpressionset());
	
		GSEAConfigurationContainer settingsused=(GSEAConfigurationContainer) referencedObjects.get(ss.getUID(GSEACONFIG));
		String associatedresults=(String) referencedObjects.get(ss.getUID(ASSOCRESULTS));
		String id = (String) referencedObjects.get(ss.getUID(NAME));
		
		EnrichmentAnalysisResultBox resultsbox=new EnrichmentAnalysisResultBox(id, dataBox.getOwnerProject(), gsearesults, settingsused);
        resultsbox.setAssociatedResultDatatype(associatedresults);
		return resultsbox;

	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#remove(java.lang.Object)
	 */
	@Override
	public void remove(EnrichmentAnalysisResultBox obj) {
		String projectId = obj.getOwnerProject().getProjectFolderName();
		String folder = getWorkspace() + SaveLoadManager.SYSTEM_SEPARATOR + projectId 
									   + SaveLoadManager.SYSTEM_SEPARATOR + SaveLoadManager.BASE_DATATYPE_FOLDER 
									   + SaveLoadManager.SYSTEM_SEPARATOR + getPrefix()+"." + convertName(obj.getName())+".ss";
		FileUtils.remove(folder);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#getListName()
	 */
	@Override
	public String getListName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#putInProject(jbiclustgegui.datatypes.project.Project, java.io.File, java.util.Map)
	 */
	@Override
	public void putInProject(Project p, File file, Map<String, Object> dependentObjects) {
		IAnalysisResult res = null;
		
		try {
			res = deserializeAndBuild(file, dependentObjects);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SerializerNotRegistered e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(res!=null)
				GenericOperation.addAnalysisResult(p,EnrichmentAnalysisResultBox.class, res, EnrichmentAnalysisResultBox.LISTNAME);
		} catch (InvalidElementListException e) {
			e.printStackTrace();
		}
	}
	


	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.serializefunctions.AbstractBuilder#getPrefix()
	 */
	@Override
	public String getPrefix() {
		return SUFIX;
	}

}