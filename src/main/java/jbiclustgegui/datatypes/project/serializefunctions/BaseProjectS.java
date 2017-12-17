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
package jbiclustgegui.datatypes.project.serializefunctions;

import java.io.File;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;
import smile.imputation.MissingValueImputationException;

public class BaseProjectS extends AbstractBuilder<Project>{

	
	protected static final String NAME = "name";
	protected static final String DATE = "date";
	protected static final String EXPRESSIONMATRIX="expressionmatrix";
	protected static final String EXPRESSIONGENES="expressiongenes";
	protected static final String EXPRESSIONCONDITIONS="expressionconditions";
	protected static final String EXPRESSIONNOTES="expressiondatasetnotes";
	protected static final String EXPRESSIONFILEPATHUSED="expressiondatasetfilepath";


	public BaseProjectS() {
		this(null);
	}
	
	public BaseProjectS(ISerializer<SerializeStructure> serializer) {
		setSerializer(serializer);
	}
	
	/**
	 * @throws IOException 
	 * @throws SerializerNotRegistered 
	 * 
	 */
	public void buildAndSerialize(Project proj) throws IOException, SerializerNotRegistered {
		
		String projFolder = proj.getProjectFolderName();


		
		SerializeStructure ss = SerializeStructure.createEmptyStructure();
	
		ss.putContainedField(NAME, proj.getName());
		ss.putContainedField(DATE, proj.getCreationdate());
		ss.putContainedField(EXPRESSIONMATRIX, proj.getExpressionDataset().getExpressionset().getexpressionDataMatrix());
		ss.putContainedField(EXPRESSIONGENES, proj.getExpressionDataset().getExpressionset().getGeneNamesList());
		ss.putContainedField(EXPRESSIONCONDITIONS, proj.getExpressionDataset().getExpressionset().getConditionsList());
		ss.putContainedField(EXPRESSIONNOTES, proj.getExpressionDataset().getNotes());
		ss.putContainedField(EXPRESSIONFILEPATHUSED, proj.getExpressionDataset().getExpressionDatasetfilepath());
		
		FileUtils.createFoldersFromPath(getWorkspace()+SaveLoadManager.getInstance().getSYSTEM_SEPARATOR()+projFolder+SaveLoadManager.getInstance().getSYSTEM_SEPARATOR()+SaveLoadManager.getInstance().getBASE_DATATYPE_FOLDER());

		String path = getWorkspace()+"/"+projFolder+"/"+getPrefix()+"."+convertName(proj.getName())+".ss";
		(new File(path)).getParentFile().mkdirs();
		
		getSerializer().serialize(ss, new File(path));
		
	}
	
	/**
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws UnsuportedModelTypeException 
	 * @throws SerializerNotRegistered 
	 * 
	 */
	public Project deserializeAndBuild(File path, Map<String, Object> dependentObjects) throws IOException, ClassNotFoundException,SerializerNotRegistered {
		
		String name = convertName(path.getName());
		SerializeStructure ss = loadStructure(path.getAbsolutePath()+"/"+getPrefix()+"."+name+".ss");
		
		
		double[][] matrix=(double[][]) dependentObjects.get(ss.getUID(EXPRESSIONMATRIX));
		ArrayList<String> genenames=(ArrayList<String>) dependentObjects.get(ss.getUID(EXPRESSIONGENES));
		ArrayList<String> conditionnames=(ArrayList<String>) dependentObjects.get(ss.getUID(EXPRESSIONCONDITIONS));
		
		String notes=(String) dependentObjects.get(ss.getUID(EXPRESSIONNOTES));
		String origfilepath=(String) dependentObjects.get(ss.getUID(EXPRESSIONFILEPATHUSED));
		LocalDateTime creationdate=(LocalDateTime) dependentObjects.get(ss.getUID(DATE));
		
		ExpressionData dataset=null;
		GeneExpressionDatasetBox datasetbox=null;
		try {
			dataset = new ExpressionData(genenames, conditionnames, matrix).load();
		} catch (MissingValueImputationException e) {
			e.printStackTrace();
		}
		
		datasetbox=new GeneExpressionDatasetBox(dataset);
		datasetbox.setNotes(notes);
		datasetbox.setExpressionDatasetfilepath(origfilepath);
		
		String id = (String) dependentObjects.get(ss.getUID(NAME));
		Project proj = new Project(id, datasetbox);
		proj.setCreationdate(creationdate);
		datasetbox.setOwnerProject(proj);
		
		return proj;
	}
	
	public void remove(Project proj){
		FileUtils.remove(getWorkspace()+System.getProperty("file.separator")+ proj.getProjectFolderName());
	}
	
	public String getPrefix(){
		return "proj";
	}
	
	@Override
	protected String fileTransformation(File f){
		String nameFolder = convertName(f.getName());
		return f.getAbsolutePath()+"/"+getPrefix()+"."+nameFolder+".ss";
	}

	@Override
	public String getListName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void putInProject(Project p, File file,
			Map<String, Object> dependentObjects) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeNameProject(Project p, String newName) throws WriteAbortedException, ClassNotFoundException, IOException, SerializerNotRegistered{
		
		String projFolder = p.getProjectFolderName();
		String pathSSFile = getWorkspace()+"/"+projFolder+"/"+getPrefix()+"."+convertName(p.getName())+".ss";
		SerializeStructure ss = loadStructure(pathSSFile);
		
		String newProjectFolder = newName;
		String nameUUID = ss.getUID(NAME);
		ss.setContainedField(nameUUID, newName);
		FileUtils.createFoldersFromPath(getWorkspace() + SaveLoadManager.SYSTEM_SEPARATOR + newProjectFolder 
													   + SaveLoadManager.SYSTEM_SEPARATOR + SaveLoadManager.BASE_DATATYPE_FOLDER);
		
		FileUtils.copy(getWorkspace()+"/"+projFolder, getWorkspace() + SaveLoadManager.SYSTEM_SEPARATOR + newProjectFolder);
		
		
		FileUtils.remove(getWorkspace()+"/"+newProjectFolder+"/"+getPrefix()+"."+convertName(p.getName())+".ss");
		getSerializer().serialize(ss, new File(getWorkspace()+"/"+newProjectFolder+"/"+getPrefix()+"."+convertName(newName)+".ss"));
		
		FileUtils.remove(getWorkspace() + SaveLoadManager.SYSTEM_SEPARATOR + projFolder);
	}

}
