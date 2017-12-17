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
package jbiclustgegui.datatypes.project.save;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;



public interface IBuildStructure <T extends Object>  {
	
	void buildAndSerialize(T obj) throws Exception;
	
	//IOException, ClassNotFoundException, UnsuportedModelTypeException;
	T deserializeAndBuild(File file, Map<String, Object> dependentObjects) throws Exception;
	
	void remove(T obj);
	
	Map<String, Object> loadContained(File path) throws IOException, ClassNotFoundException, SerializerNotRegistered;
	
	String getListName();

	void putInProject(Project p, File file, Map<String, Object> dependentObjects);

	void setWorkspace(String workspace);

}
