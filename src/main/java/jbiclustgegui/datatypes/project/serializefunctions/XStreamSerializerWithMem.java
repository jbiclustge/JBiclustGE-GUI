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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.platonos.pluginengine.Plugin;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import es.uvigo.ei.aibench.Launcher;

public class XStreamSerializerWithMem <T extends Object> implements ISerializerMemory<T>{

	
	static Pattern problem = Pattern.compile(".*Cannot find class ([^ ]+).*");
	private Map<String, T> memory;
	
	public XStreamSerializerWithMem() {
		memory = new HashMap<String, T>();
	}
	
	public void serialize(T obj, File file) throws IOException {
		try {
			memory.remove(file.getAbsolutePath());
			FileOutputStream outputStream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
			XStream xStream = new XStream();
			xStream.marshal(obj, new CompactWriter(writer));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public T deserialize(File f) throws IOException, ClassNotFoundException {
		
		T info = memory.get(f.getAbsolutePath());
		if (info!= null) return info;
		
        XStream xStream = new XStream();
        xStream.setClassLoader(SerializeStructure.class.getClassLoader());
        
        Object obj = null;
    	try{
        	obj = xStream.fromXML(new FileInputStream(f));
        }catch(CannotResolveClassException | ConversionException e){
        	e.printStackTrace();
        	obj = resolveClass(xStream, f, e);
		}
        
    	memory.put(f.getAbsolutePath(), (T) obj);
        return (T) obj;
	}
	
	private Object resolveClass(XStream xstreamInstance, File f, Exception e) throws FileNotFoundException{
		xstreamInstance = new XStream();

		String message = e.getMessage().trim().split("\n")[0];
		String[] data = message.split(":");

		String klassStr = data[data.length-1];
		klassStr = klassStr.replaceAll("Cannot find class ", "");
		Class klass = getClassFromPlugins(klassStr.trim());

		xstreamInstance.setClassLoader(klass.getClassLoader());
		try {
			return xstreamInstance.fromXML(new FileInputStream(f));
		} catch (Exception e2) {
			return resolveClass(xstreamInstance, f, e2);
		}
		
	}
	
	private Class<?> getClassFromPlugins(String className){
		Class<?> c = null;
		List<?> pluginsList = Launcher.pluginEngine.getPlugins();
		for (Object object : pluginsList) {
			Plugin p = (Plugin) object;
				
			try {
				c = p.getPluginClassLoader().loadClass(className);
				return c;
			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
			}		
		}
		
		return c;
	}

	public void cleanMemory() {
		this.memory.clear();
	}

}