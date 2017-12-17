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
package jbiclustgegui.operations.serializers.methodresults;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.datatypes.project.serializefunctions.AbstractBuilder;
import jbiclustgegui.datatypes.project.serializefunctions.ISerializer;
import jbiclustgegui.datatypes.project.serializefunctions.SerializeStructure;
import jbiclustgegui.operations.GenericOperation;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusteringMethodResultSerializer.
 */
public class BiclusteringMethodResultSerializer extends AbstractBuilder<BiclusteringResultBox>{

	/** The Constant SUFIX. */
	private static final String SUFIX = "BicMethodRES";
	
	/** The Constant NAME. */
	public static final String NAME = "NAME";
	
	/** The Constant EXPRESSIONDATA. */
	public static final String EXPRESSIONDATA = "EXPRESSIONDATA";
	
	/** The Constant BICLUSTERRESULTS. */
	public static final String BICLUSTERRESULTS = "BICLUSTERRESULTS";
	
	/** The Constant BICLUSTERINGMETHOD. */
	public static final String BICLUSTERINGMETHOD = "BICLUSTERINGMETHOD";
	
	/** The Constant SAVEDATE. */
	public static final String SAVEDATE="SAVEDATE";

	/** The prefix. */
	private String prefix=null;
	
	
	/*public BiclusteringMethodResultSerializer() {
		this(null);
	}*/
	
	/**
	 * Instantiates a new biclustering method result serializer.
	 *
	 * @param prefix the prefix
	 */
	public BiclusteringMethodResultSerializer(String prefix) {
		this.prefix=prefix;
		
	}
	
	
	/**
	 * Instantiates a new biclustering method result serializer.
	 *
	 * @param serializer the serializer
	 */
	public BiclusteringMethodResultSerializer(ISerializer<SerializeStructure> serializer) {
		setSerializer(serializer);
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#buildAndSerialize(java.lang.Object)
	 */
	@Override
	public void buildAndSerialize(BiclusteringResultBox obj) throws Exception {
		
		String projectId =  obj.getOwnerProject().getProjectFolderName();
		
		String file = SaveLoadManager.SYSTEM_SEPARATOR + projectId 
					+ SaveLoadManager.SYSTEM_SEPARATOR + SaveLoadManager.BASE_DATATYPE_FOLDER 
					+ SaveLoadManager.SYSTEM_SEPARATOR + getPrefix()+"." + convertName(obj.getName())+".ss";
		

		SerializeStructure ss = SerializeStructure.createEmptyStructure();
		
		
		BiclusterList results=obj.getResults();
		
		// delete expression data dependency to avoid serialization problems

		ss.putContainedField(BICLUSTERRESULTS,results.getCloneWithoutExpressionDataAssociation());
		ss.putContainedField(BICLUSTERINGMETHOD, obj.getMethod());
		ss.putContainedField(SAVEDATE, obj.getDate());
		ss.putContainedField(NAME, obj.getName());
		
		getSerializer().serialize(ss, new File(getWorkspace() + file));
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#deserializeAndBuild(java.io.File, java.util.Map)
	 */
	@Override
	public BiclusteringResultBox deserializeAndBuild(File file, Map<String, Object> referencedObjects) throws Exception {
	
		SerializeStructure ss = loadStructure(file.getAbsolutePath());
		
		GeneExpressionDatasetBox dataBox=(GeneExpressionDatasetBox) referencedObjects.get(SaveLoadManager.EXPRESSIONDATA);

		BiclusterList results=(BiclusterList) referencedObjects.get(ss.getUID(BICLUSTERRESULTS));
		// load expression dataset in results
		results.addExpressionDatasetDependencies(dataBox.getExpressionset());
		BiclusteringMethod method=(BiclusteringMethod) referencedObjects.get(ss.getUID(BICLUSTERINGMETHOD));
		String id = (String) referencedObjects.get(ss.getUID(NAME));
		LocalDateTime savedat=(LocalDateTime) referencedObjects.get(ss.getUID(SAVEDATE));
		
		BiclusteringResultBox resultbox=GenericOperation.getResultsBox(dataBox.getOwnerProject(), id, results, method);
        resultbox.setDate(savedat);
		return resultbox;

	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#remove(java.lang.Object)
	 */
	@Override
	public void remove(BiclusteringResultBox obj) {
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
		IBiclusteringMethodResult res = null;
		
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
				GenericOperation.addBiclusteringResult(p, res.getByClass(), res, res.getMethod().getAlgorithmName()+" Results");
		} catch (InvalidElementListException e) {
			e.printStackTrace();
		}
	}
	


	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.serializefunctions.AbstractBuilder#getPrefix()
	 */
	@Override
	public String getPrefix() {
		return prefix;
	}

}