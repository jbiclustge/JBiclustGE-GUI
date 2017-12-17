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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.List;

import org.platonos.pluginengine.Plugin;

import es.uvigo.ei.aibench.Launcher;

public class PluginObjectInputStream extends ObjectInputStream{
	
	protected String uidPlugin;
	
	public PluginObjectInputStream(InputStream in) throws IOException, SecurityException {
		super(in);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,ClassNotFoundException{
		List<?> pluginsList = Launcher.pluginEngine.getPlugins();
		

		
		Class<?> c = null;
		for(Object o : pluginsList){
			Plugin p = (Plugin) o;
			uidPlugin = p.getUID();
				try{
					// if the class is an array, the platonos plugin class loader doesn't handle it well, so trick it!

					int dimension = desc.getName().lastIndexOf('[');
					if (dimension >=0 && desc.getName().charAt(dimension+1)=='L'){
						//it's an array, but not a primitive array

						String noArrayName = desc.getName().substring(dimension+2, desc.getName().length()-1);


						c = p.getPluginClassLoader().loadClass(noArrayName);
						//Create the Class object
						int[] dimensions = new int[dimension+1];
						c = java.lang.reflect.Array.newInstance(c, dimensions).getClass();

					}else{						
						c = p.getPluginClassLoader().loadClass(desc.getName());						
					}

					break;
				}catch(ClassNotFoundException e){
					//e.printStackTrace();

				}
				
			}

		
		if (c == null) throw new ClassNotFoundException(desc.getName());
		
		return c;
	}
	
	public String getUidPlugin(){
		return uidPlugin;
	}
}
