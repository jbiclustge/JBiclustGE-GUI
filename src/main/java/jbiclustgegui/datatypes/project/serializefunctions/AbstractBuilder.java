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
import java.util.Map;
import java.util.regex.Pattern;

import jbiclustgegui.datatypes.project.exceptions.RegistryException;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.save.IBuildStructure;

public abstract class AbstractBuilder <T extends Object> implements IBuildStructure<T>{
	
	static protected final String pathSep = System.getProperty("file.separator");
	static protected Pattern uidPatern = Pattern.compile(".*\\.(\\d+)");

	protected ISerializer<SerializeStructure> serializer = null;
	protected String workspace;
	public abstract String getPrefix();
	
	protected String getWorkspace(){
		return workspace;
	}
	
	protected String convertName(String c){
		return c.replaceAll("\\.", "-").replaceAll(" ", "_");	
	}
	
	protected String deconvertName(String d){
		return d.replaceAll("-", "\\.").replaceAll("_"," ");
	}
	
	public SerializeStructure loadStructure(String path) throws IOException, ClassNotFoundException, WriteAbortedException, SerializerNotRegistered {
		return (SerializeStructure) getSerializer().deserialize(new File(path));
	}
	
	
	public Map<String, Object> loadContained(File f) throws IOException, ClassNotFoundException, SerializerNotRegistered {
		return loadContainer(loadStructure(fileTransformation(f)));
	}
	
	
	public Map<String, Object> loadContainer(SerializeStructure se){
		Map<String, Object> cont = se.getContained();
		
		try {
			ProjectRegistryManager.getInstance().putALL(cont);
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cont;
	}
	
	protected String fileTransformation(File f){
		return f.getAbsolutePath();
	}
	
	public void setSerializer(ISerializer<SerializeStructure> serializer) {
		this.serializer = serializer;
	}
	
	public ISerializer<SerializeStructure> getSerializer() throws SerializerNotRegistered{
		if(serializer == null)
			throw new SerializerNotRegistered();
		return serializer;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

}

