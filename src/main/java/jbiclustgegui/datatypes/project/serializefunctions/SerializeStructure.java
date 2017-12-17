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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.util.HashMap;
import java.util.Map;

import jbiclustgegui.datatypes.project.save.PluginObjectInputStream;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;

public class SerializeStructure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contains uuid and the serialized Object
	 */
	protected Map<String, Object> containerObjects;
	protected Map<String, String> nameFielduuid;
	

	
	protected SerializeStructure(){
		containerObjects = new HashMap<String, Object>();
		nameFielduuid = new HashMap<String, String>();
	}

	public void putContainedField(String fieldId, Object obj){
		String uuid = getUniqId(obj);
		containerObjects.put(uuid, obj);
		nameFielduuid.put(fieldId, uuid);
	}
	
	public void putLinkedField(String fieldId, Object obj){
		String uuid = getUniqId(obj);
		nameFielduuid.put(fieldId, uuid);
	}
	
	public Map<String, Object> getContained(){
		return containerObjects;
	}
	
	public Map<String, String> getFieldUID(){
		return nameFielduuid;
	}
	
	public String getUID(String fieldId){
		return nameFielduuid.get(fieldId);
	}
	
	protected String getUniqId(Object b){

		String d= ProjectRegistryManager.getInstance().getUUID(b);
		return d;
	}
	
	public void setContainedField(String uuid, Object o){
		if(containerObjects.containsKey(uuid)){
			containerObjects.put(uuid, o);
		}
	}
	
	static public SerializeStructure createEmptyStructure(){
		return new SerializeStructure();
	}
	
	static public SerializeStructure loadStructure(String path) throws IOException, ClassNotFoundException, WriteAbortedException {
		
		Object obj = null;
		try{
			obj = FileUtils.loadSerializableObject(path);
		}catch(ClassNotFoundException e){
			obj = readTryResolveBinaryFile(path);
		}
		
		return (SerializeStructure) obj;
	}
	
	static public void saveSerializedStructure(String path, SerializeStructure se) throws IOException{
		(new File(path)).getParentFile().mkdirs();
		FileUtils.saveSerializableObject(se, path);
	}
	
	
	static private Object readTryResolveBinaryFile(String filePath) throws IOException, ClassNotFoundException{
		Object ret = null;
		FileInputStream fis = new FileInputStream(filePath);
		PluginObjectInputStream ois = new PluginObjectInputStream(fis);
		ret = ois.readObject();
		
//		resolvedPlugins.add(ois.getUidPlugin());
		ois.close();
		fis.close();
		return ret;
	}
}
