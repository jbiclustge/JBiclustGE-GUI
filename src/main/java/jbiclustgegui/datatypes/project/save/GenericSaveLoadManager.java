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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.components.IProjectElement;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.CorruptProjectFileException;
import jbiclustgegui.datatypes.project.exceptions.ProjectAllreadyExistException;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.serializefunctions.AbstractBuilder;
import jbiclustgegui.datatypes.project.serializefunctions.ISerializer;
import jbiclustgegui.datatypes.project.serializefunctions.ISerializerMemory;
import jbiclustgegui.datatypes.project.serializefunctions.ProjectRegistryManager;
import jbiclustgegui.datatypes.project.serializefunctions.SerializeStructure;
import jbiclustgegui.datatypes.project.states.ClosedProject;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;



public class GenericSaveLoadManager {
	
	static public String BASE_DATATYPE_FOLDER = "datatypes"; 
	static public String SYSTEM_SEPARATOR = System.getProperty("file.separator");
	static public String EXPRESSIONDATA = "expressiondata";
	static public String LOADEDPROJECT = "loadedproject";
	
	protected Pattern patternNameFolder = Pattern.compile("([^\\.]+)\\.([^ \\.]+)\\.(ss|fl)");

	protected String workspace;
	protected ISerializer<SerializeStructure> serializer;
	protected Map<Class<?>, IBuildStructure<?>> builderByClass;
	protected Map<String, Class<?>> builderByPrefix;
	
	

	public GenericSaveLoadManager(ISerializer<SerializeStructure> serializer){
		this();
		this.serializer = serializer;
	}
	
	public GenericSaveLoadManager() {
		builderByClass = new HashMap<Class<?>, IBuildStructure<?>>();
		builderByPrefix = new HashMap<String, Class<?>>();
	}

	
	public void registerBuilder(Class<?> dataType, AbstractBuilder<?> s) throws Exception{
		if(builderByPrefix.containsKey(s.getPrefix()))
			throw new Exception("Duplicated prefix '" + s.getPrefix() + "' Class: " +s.getClass().getSimpleName());

		s.setWorkspace(workspace);
		s.setSerializer(serializer);
		builderByClass.put(dataType, s);
		builderByPrefix.put(s.getPrefix(), dataType);
	}

	public ClosedProject getClosedProject(File f){
		String name = f.getName().replaceAll("\\.proj$", "");

		return new ClosedProject(name, f.getName());
	}

	public Project getProjectFromFolder(File f) throws IOException, ClassNotFoundException,CorruptProjectFileException, SerializerNotRegistered {
		Map<String, Object> dep = getAllDepencencies(f);
		
		Project p;
		p = loadProject(f,dep);
		
		putAllDataTypesInProject(f,dep,p);
		
		// It seems that the clear was added due to a memory leak
		// It should not be commented because the clear does not guarantee the datatypes linkage
		// On the order hand not using the clear will lead to memory leaks because the objects
		// will never be completely removed during one OptFlux session
		ProjectRegistryManager.getInstance().clear();
		
		if(getSerializer() instanceof ISerializerMemory) 
			((ISerializerMemory)getSerializer()).cleanMemory(); 
		
		return p;
	}
	
	public Set<Project> getAllProjects() throws ClassNotFoundException, IOException, CorruptProjectFileException, SerializerNotRegistered{
		File f = new File(workspace);

		Set<Project> ps = new HashSet<>();
		for(File pf : f.listFiles()){
			if(pf.isDirectory()){
				Project p = getProjectFromFolder(pf);
				ps.add(p);
			}
		}
		
		return ps;
	}

	synchronized protected void putAllDataTypesInProject(File folder,	Map<String, Object> dep, Project p) throws CorruptProjectFileException, IOException, ClassNotFoundException {
		File f = new File(folder.getAbsolutePath()+SYSTEM_SEPARATOR+BASE_DATATYPE_FOLDER);
		if(f.exists())
			putDataTypesInProject(f, dep, p);
		else{
//						_resolveOldVersionFolders(folder, dep, p);
			System.err.println("PROBLEM!");
		}
	}

	synchronized protected void putDataTypesInProject(File file, Map<String, Object> dep, Project p){
		File[] allfiles = file.listFiles();

		if(allfiles!=null){
			for(File f : allfiles){
				try {
					Class<?> dataType = getDataTypeClassByFile(f);
					IBuildStructure<?> builder = getBuilderByClass(dataType);
					builder.putInProject(p, f, dep);
				} catch (CorruptProjectFileException | SerializerNotRegistered e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void saveAllDatatypesFromProject(Project p){
		try {
			saveData(p);

			for (IElementList<IAnalysisResult> ele : p.getAnalysisResults().getElementList()) 
				for (IAnalysisResult eleAnaysis : ele.getElementList()) 
					saveData(eleAnaysis);

			for (IElementList<IBiclusteringMethodResult> ele : p.getBiclusteringResult().getElementList()) 
				for (IBiclusteringMethodResult eleSimulation : ele.getElementList()) 
					saveData(eleSimulation);

			for (IElementList<IProjectElement> ele : p.getProjectElements().getElementList()) 
				for (IProjectElement eleProject : ele.getElementList()) 
					saveData(eleProject);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized protected Class<?> getDataTypeClassByFile(File f) throws CorruptProjectFileException, SerializerNotRegistered {
		String name = f.getName();
		Matcher m = patternNameFolder.matcher(name);

		String prefix = null;
		if(m.matches())
			prefix = m.group(1);

		if(prefix == null)
			throw new CorruptProjectFileException("NAME: " + f.getName());

		if(!builderByPrefix.containsKey(prefix))
			throw new SerializerNotRegistered();

		return builderByPrefix.get(prefix);
	}

	synchronized protected IBuildStructure<?> getBuilderByClass(Class<?> dataType) {
		return builderByClass.get(dataType);
	}

	public void exportProject(Project proj, String file) throws IOException{
		FileUtils.createZipFile(workspace+SYSTEM_SEPARATOR+proj.getProjectFolderName(), file,0);
	}

	public ClosedProject closeProject(Project proj) throws IOException{	
		String file = workspace + SYSTEM_SEPARATOR+proj.getProjectFolderName()+".proj";

		FileUtils.createZipFile(workspace+SYSTEM_SEPARATOR+proj.getProjectFolderName(), file,0);

		return new ClosedProject(proj.getName(), proj.getProjectFolderName()+".proj");
	}

	public void removeCloseProjectInfo(ClosedProject proj){
		FileUtils.remove(workspace + SYSTEM_SEPARATOR + proj.getFileName());
	}

	public Project openProject(ClosedProject project) throws CorruptProjectFileException, ProjectAllreadyExistException, IOException, ClassNotFoundException,SerializerNotRegistered{
		File f = new File(workspace + SYSTEM_SEPARATOR + project.getFileName());
		Project p = importProject(f);
		FileUtils.remove(f.getAbsolutePath());
		return p;
	}

	public Project importProject(File projFile) throws CorruptProjectFileException, ProjectAllreadyExistException, IOException, ClassNotFoundException, SerializerNotRegistered {
		boolean isProj = projFile.getName().matches(".*\\.proj");
		if(!isProj)
			throw new CorruptProjectFileException(projFile.getName());


		String file = projFile.getName().replaceAll("\\.proj$", "");
		File folderProject = new File(workspace+"/"+file);

		if(folderProject.isDirectory() && folderProject.exists())
			throw new ProjectAllreadyExistException(projFile.getName());

		File f = new File(workspace);

		FileUtils.extractZipFile(projFile.getAbsolutePath(), f.getAbsolutePath());

		Project p = getProjectFromFolder(folderProject);
		//DataLinkageManager.getInstance().validateAllElements(p);
		return p;
	}

	protected Map<String, Object> getAllDepencencies(File projFolder) throws IOException, ClassNotFoundException, SerializerNotRegistered{
		IBuildStructure<Project> b = (IBuildStructure<Project>) builderByClass.get(Project.class);
		Map<String, Object> dep = b.loadContained(projFolder);
		
		File f = new File(projFolder.getAbsolutePath()+SYSTEM_SEPARATOR+BASE_DATATYPE_FOLDER);
		if(f.exists())
			getAllDep(dep, f.getAbsolutePath());

		return dep;
	}

	protected synchronized void getAllDep(Map<String, Object> dep, String path){
		File folder = new File(path);
		File[] files = folder.listFiles();

		for(File f : files){

			Map<String, Object> cont = null;

			try {
				cont = getDepsFromFile(f);
			} catch (SerializerNotRegistered | IOException e) {
				e.printStackTrace();
			}

			if(cont!=null)
				dep.putAll(cont);
		}
	}

	protected Map<String, Object> getDepsFromFile(File f) throws IOException, SerializerNotRegistered{
		Map<String, Object> cont = null;
		String name = f.getName();
		Matcher m = patternNameFolder.matcher(name);

		String prefix = null;
		if(m.matches())
			prefix = m.group(1);

		if(prefix == null)
			throw new IOException("No Prefix in file: " + f.getAbsolutePath());

		if(!builderByPrefix.containsKey(prefix))
			throw new SerializerNotRegistered();

		Class<?> dataType = builderByPrefix.get(prefix);
		IBuildStructure<?> s = builderByClass.get(dataType);

		try {
			cont = s.loadContained(f);
		} catch (Exception e) {
			// FIXME: Cannot desserialize the file
			e.printStackTrace();
		}

		return cont;
	}

	protected Project loadProject(File path, Map<String, Object> dep) throws IOException, ClassNotFoundException{
		IBuildStructure<Project> s = (IBuildStructure<Project>) builderByClass.get(Project.class);
		Project p;
		try{
			p = s.deserializeAndBuild(path, dep);
		}catch(Exception e){
			e.printStackTrace();
			throw new IOException(e.getCause());
		}
		dep.put(EXPRESSIONDATA, p.getExpressionDataset());
		dep.put(LOADEDPROJECT,p);
		return p;
	}

	public void saveData(Object obj) throws Exception{
		IBuildStructure s = builderByClass.get(obj.getClass());
		if(s != null)
			s.buildAndSerialize(obj);
	}

	public void removeData(Object obj) throws Exception{
		if(obj !=null){
			IBuildStructure s = builderByClass.get(obj.getClass());
			if(s!=null)
				s.remove(obj);
		}
	}

	public String getBASE_DATATYPE_FOLDER() {
		return BASE_DATATYPE_FOLDER;
	}

	public String getSYSTEM_SEPARATOR() {
		return SYSTEM_SEPARATOR;
	}

	public Pattern getPatternNameFolder() {
		return patternNameFolder;
	}

	public String getMODEL_BOX() {
		return EXPRESSIONDATA;
	}

	public Map<Class<?>, IBuildStructure<? extends Object>> getBuildersByClass() {
		return builderByClass;
	}

	public Map<String, Class<?>> getBuildersByPrefix() {
		return builderByPrefix;
	}

	public String getWorkspace(){
		return workspace;
	}
	
	public ISerializer<SerializeStructure> getSerializer() {
		return serializer;
	}
	
	public void setSerializer(ISerializer<SerializeStructure> serializer) {
		this.serializer = serializer;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
		for(IBuildStructure<?> b : builderByClass.values())
			b.setWorkspace(workspace);
	}
}
