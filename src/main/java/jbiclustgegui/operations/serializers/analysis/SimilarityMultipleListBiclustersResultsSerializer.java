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
package jbiclustgegui.operations.serializers.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jbiclustge.analysis.similarity.components.SimilarityIndexMethod;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustgegui.datatypes.analysis.PairwiseMultipleListBiclustersResultsBox;
import jbiclustgegui.datatypes.components.IAnalysisResult;
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
 * The Class SimilarityMultipleListBiclustersResultsSerializer.
 */
public class SimilarityMultipleListBiclustersResultsSerializer  extends AbstractBuilder<PairwiseMultipleListBiclustersResultsBox> {

	/** The Constant SUFIX. */
	private static final String SUFIX = "PSIMILMULTIBICS";
	
	/** The Constant NAME. */
	public static final String NAME = "NAME";
	
	/** The Constant PSRESULTS. */
	public static final String PSRESULTS = "PSRESULTS";
	
	/** The Constant ORDNAMES. */
	public static final String ORDNAMES = "ORDNAMES";
	
	/** The Constant SIMMETHOD. */
	public static final String SIMMETHOD="SIMMETHOD";
	
	/** The Constant BICMETHODS. */
	public static final String BICMETHODS="BICMETHODs";
	
	/**
	 * Instantiates a new similarity multiple list biclusters results serializer.
	 */
	public SimilarityMultipleListBiclustersResultsSerializer() {
		this(null);
	}
	
	/**
	 * Instantiates a new similarity multiple list biclusters results serializer.
	 *
	 * @param serializer the serializer
	 */
	public SimilarityMultipleListBiclustersResultsSerializer(ISerializer<SerializeStructure> serializer) {
		setSerializer(serializer);
	}
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#buildAndSerialize(java.lang.Object)
	 */
	@Override
	public void buildAndSerialize(PairwiseMultipleListBiclustersResultsBox obj) throws Exception {
		
		String projectId =  obj.getOwnerProject().getProjectFolderName();
		
		String file = SaveLoadManager.SYSTEM_SEPARATOR + projectId 
					+ SaveLoadManager.SYSTEM_SEPARATOR + SaveLoadManager.BASE_DATATYPE_FOLDER 
					+ SaveLoadManager.SYSTEM_SEPARATOR + getPrefix()+"." + convertName(obj.getName())+".ss";
		

		SerializeStructure ss = SerializeStructure.createEmptyStructure();
		
		ss.putContainedField(PSRESULTS,obj.getSimilarityResults());
		ss.putContainedField(ORDNAMES,obj.getOrderedNames());
		ss.putContainedField(SIMMETHOD,obj.getSimilarityMethod());
		ss.putContainedField(BICMETHODS,obj.getBiclusteringMethodsUsedInPairwiseSimilarity());
		ss.putContainedField(NAME, obj.getName());
		getSerializer().serialize(ss, new File(getWorkspace() + file));
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#deserializeAndBuild(java.io.File, java.util.Map)
	 */
	@Override
	public PairwiseMultipleListBiclustersResultsBox deserializeAndBuild(File file, Map<String, Object> referencedObjects)
			throws Exception {
		
		SerializeStructure ss = loadStructure(file.getAbsolutePath());
		
		Project proj=(Project) referencedObjects.get(SaveLoadManager.LOADEDPROJECT);
		
		double[][]results=(double[][]) referencedObjects.get(ss.getUID(PSRESULTS));
		ArrayList<String> orderednames=(ArrayList<String>) referencedObjects.get(ss.getUID(ORDNAMES));
		SimilarityIndexMethod method=(SimilarityIndexMethod) referencedObjects.get(ss.getUID(SIMMETHOD));
		ArrayList<BiclusteringMethod> compbiclusteringmethods=(ArrayList<BiclusteringMethod>) referencedObjects.get(ss.getUID(BICMETHODS));
		String id = (String) referencedObjects.get(ss.getUID(NAME));
		
		PairwiseMultipleListBiclustersResultsBox box=new PairwiseMultipleListBiclustersResultsBox(id, proj, results, orderednames, method);
		box.setBiclusteringMethodsUsedInPairwiseSimilarity(compbiclusteringmethods);
		
		return box;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.save.IBuildStructure#remove(java.lang.Object)
	 */
	@Override
	public void remove(PairwiseMultipleListBiclustersResultsBox obj) {
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
				GenericOperation.addAnalysisResult(p,PairwiseMultipleListBiclustersResultsBox.class, res, PairwiseMultipleListBiclustersResultsBox.LISTNAME);
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
