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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import jbiclustgegui.datatypes.project.exceptions.RegistryException;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.MapUtils;



public class ProjectRegistryManager {
	
	private static ProjectRegistryManager instance;
	
	protected Map<String, Object> uuidObject;
	protected TreeMap<Object, String> objectUUid;
	
	
	public static ProjectRegistryManager getInstance(){
		if(instance == null)
			instance = new ProjectRegistryManager();
		return instance;
	}
	
	public ProjectRegistryManager() {
		uuidObject = new HashMap<String, Object>();
		objectUUid = new TreeMap<Object, String>(new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1== null && o2==null)
					return 0;
				else if(o1==null)
					return 1;
				else if (o2==null)
					return -1;
				
				int ret = o1.getClass().hashCode() - o2.getClass().hashCode();
				if(ret == 0)
					ret = o1.hashCode() -o2.hashCode();
//				System.out.println(o1 + "\t" + o2 +"\t"+o1.getClass()+"\t"+o2.getClass()+ "\t" + ret);
				return ret;
				
			}
		});
	}
	
	
	public void registObject(Object obj){
		String uuid = UUID.randomUUID().toString();
		uuidObject.put(uuid,obj);
		objectUUid.put(obj, uuid);
	}
	
	public String getUUID(Object obj){
		if(!objectUUid.containsKey(obj))
			registObject(obj);
		
		String n = objectUUid.get(obj);
		return n;
	}
	
	public Object getObject(String uuid){
		return uuidObject.get(uuid);
	}
	
	public void put(String id, Object obj){
		uuidObject.put(id, obj);
		objectUUid.put(obj, id);
	}
	
	public void clear(){
		uuidObject.clear();
		objectUUid.clear();
	}

	public void putALL(Map<String, Object> cont) throws RegistryException {
		
		for(String id : cont.keySet()){
			Object obj = cont.get(id);
			if(uuidObject.containsKey(id) || objectUUid.containsKey(obj)){
//				throw new RegistryException();
			}else{
				uuidObject.put(id, obj);
				objectUUid.put(obj, id);
			}
		}
		
	}

	public String toStringRegistry(){
		return MapUtils.prettyToString(uuidObject);
	}
	

	private Map<?, ?> getObjectUUid() {
		return objectUUid;
	}

}