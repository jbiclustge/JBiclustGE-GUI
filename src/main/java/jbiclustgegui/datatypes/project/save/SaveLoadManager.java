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

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import org.apache.commons.io.FileDeleteStrategy;

import es.uvigo.ei.aibench.Launcher;
import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.exceptions.SerializerNotRegistered;
import jbiclustgegui.datatypes.project.serializefunctions.BaseProjectS;
import jbiclustgegui.datatypes.project.serializefunctions.ISerializer;
import jbiclustgegui.datatypes.project.serializefunctions.ProjectRegistryManager;
import jbiclustgegui.datatypes.project.serializefunctions.SerializeStructure;
import jbiclustgegui.datatypes.project.serializefunctions.XStreamSerializerWithMem;
import jbiclustgegui.datatypes.project.states.ClosedProject;
import jbiclustgegui.gui.components.progress.CostumizableProgressBar;
import pt.uminho.ceb.biosystems.mew.utilities.io.RegExpFileFilter;

public class SaveLoadManager extends GenericSaveLoadManager{
	
	protected static SaveLoadManager _instance;
	
	private String lockFile=".islocked";
	
	private FileLock lock;
	
	private boolean changingWorkspace = false;
	private boolean hashutdownHookRegistered = false;
	
	synchronized static public SaveLoadManager getInstance(){
		if(_instance == null){
			XStreamSerializerWithMem<SerializeStructure> serializer = new XStreamSerializerWithMem<SerializeStructure>();
			_instance = new SaveLoadManager(serializer);
		}
		
		return _instance;
	}
	
	public static SaveLoadManager init() {
		return SaveLoadManager.getInstance();
	}
	
	private SaveLoadManager(ISerializer<SerializeStructure> serializer){
		super(serializer);
		BaseProjectS proj = new BaseProjectS();
		try {
			super.registerBuilder(Project.class,proj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean lock() throws IOException{
		boolean b = islock();
		
		if(!b){
			File file = new File(getWorkspace()+SYSTEM_SEPARATOR+lockFile);
			file.createNewFile();
			
			registeShutdownHook();
		}
		return b;
	}
	
	 private void registeShutdownHook(){
		if(!hashutdownHookRegistered){
			Runtime.getRuntime().addShutdownHook(new Thread(new RemoveLock()));
			hashutdownHookRegistered = true;
		}
	}
	
	private  boolean islock(){
		return new File(getWorkspace()+SYSTEM_SEPARATOR+lockFile).exists();
	}
	
	public boolean unlock() {
		File file = new File(getWorkspace()+SYSTEM_SEPARATOR+lockFile);
		
		boolean unlocked=false;
		if(file.exists())
			unlocked=file.delete();
		else
			unlocked=true;
		return unlocked;
	}
	
	public boolean isChangingWorkspace(){
		return changingWorkspace; 
	}
	
	synchronized protected void removeAllProjectsFromClipboard(){
		changingWorkspace = true;
		List<ClipboardItem> projects= new ArrayList<ClipboardItem>(Core.getInstance().getClipboard().getItemsByClass(Project.class));
		projects.addAll(Core.getInstance().getClipboard().getItemsByClass(ClosedProject.class));
		
		for(ClipboardItem c:projects){
			Core.getInstance().getClipboard().removeClipboardItem(c);
		}
		changingWorkspace = false;
	}
	
	public void putAllProjectsInClipbord(){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		boolean translucent = gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT);
		
		ProjectRegistryManager.getInstance().clear();
		File n = new File(getWorkspace());
		FileFilter directoryFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		
		final File[] files = n.listFiles(directoryFilter);
		final File[] closeF = n.listFiles(new RegExpFileFilter(Pattern.compile(".*\\.proj")));
		
		int number = (files!=null)?files.length:0;
		number+=(closeF!=null)?closeF.length:0;
	/*	if(files != null)
			for(File f : files){
				changingWorkspace = true;
				//progressBar.increment();
				//d.setAlwaysOnTop(true);
				Project p;
				try {
					p = getProjectFromFolder(f);
					Core.getInstance().getClipboard().putItem(p, p.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(closeF!=null)
			for(File f : closeF){
				//progressBar.increment();
				ClosedProject p = getClosedProject(f);
				Core.getInstance().getClipboard().putItem(p, p.getName());
			}*/
		final CostumizableProgressBar progressBar =  new CostumizableProgressBar(number, new ImageIcon(SaveLoadManager.class.getResource("/images/iconjbicgetp.png"))); 
		JDialog d = new JDialog();
		
		d.setUndecorated(true);		
		d.add(progressBar);
		d.pack();
		
		if(translucent)
			d.setBackground(new Color(255, 255, 255, 0));
		else
			d.setBackground(new Color(255, 255, 255, 255));
		d.setLocationRelativeTo(Workbench.getInstance().getMainFrame().getContentPane());
		
		if(translucent)
			progressBar.setBackground(new Color(255, 255, 255, 0));
		else
			progressBar.setBackground(new Color(255, 255, 255, 255));
		
		progressBar.setBorder(null);
		
        d.setVisible(true);
        progressBar.increment();
        progressBar.setString("Loading workspace...");
        d.setAlwaysOnTop(true);
        
		if(files != null)
		for(File f : files){
			changingWorkspace = true;
			progressBar.increment();
			d.setAlwaysOnTop(true);
			Project p;
			try {
				p = getProjectFromFolder(f);
				Core.getInstance().getClipboard().putItem(p, p.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(closeF!=null)
		for(File f : closeF){
			progressBar.increment();
			ClosedProject p = getClosedProject(f);
			Core.getInstance().getClipboard().putItem(p, p.getName());
		}

		d.dispose();
		
		changingWorkspace = false;
	}
	
	
	
	public static void appendToFile(String data, File f){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f.getAbsolutePath(), true)))) {
		    out.println(data);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	


	 public boolean testWorkspaceFolderIsLocked(File f){
		boolean ret=false;
		
		if(f.exists() && f.isDirectory())
			ret = new File(f.getAbsolutePath()+SYSTEM_SEPARATOR+lockFile).exists();
		return ret;
	}

	 public void setWorkspaceFolder(File f) throws IOException{
		if(!f.exists())
			f.mkdirs();
		
		if(!f.isDirectory())
			throw new IOException("The Workspace must be a directory!");
		else{
			unlock();
			setWorkspace(f.getAbsolutePath());
			lock();
			removeAllProjectsFromClipboard();
			putAllProjectsInClipbord();
		}
	}
	
/*	synchronized private void _resolveDepsOldVersionFolders(Map<String, Object> dep,
			File projFolder) {
		super.getAllDep(dep, projFolder.getAbsoluteFile()+SYSTEM_SEPARATOR+"extrainfo");
		getAllDep(dep, projFolder.getAbsoluteFile()+SYSTEM_SEPARATOR+"simulation");
		getAllDep(dep, projFolder.getAbsoluteFile()+SYSTEM_SEPARATOR+"optimization");
	}*/

/*	 private void testWorkspace() throws OpenedWorkspaceExcption{
		boolean b = FileUtils.existsPath(getWorkspace()+"/.optflux.ws");
		
		if(b)
			throw new OpenedWorkspaceExcption(getWorkspace());
		
	}*/
	
	public boolean canSaveObject(Object obj){
		if(obj == null)
			return false;
		return builderByClass.containsKey(obj.getClass()) && !isChangingWorkspace();
	}

	public String getLockFile() {
		return lockFile;
	}

	public FileLock getLock() {
		return lock;
	}

	public boolean isChangingWotkspace() {
		return changingWorkspace;
	}

	public boolean isHashutdownHookRegistered() {
		return hashutdownHookRegistered;
	}

	public boolean  changeNameProject(Project project, String newName) {
		BaseProjectS s = (BaseProjectS) builderByClass.get(Project.class);
		boolean ret = true;
		try {
			s.changeNameProject(project, newName);
		} catch (ClassNotFoundException | IOException | SerializerNotRegistered e) {
			ret = false;
			e.printStackTrace();
		}
		
		return ret;
	}

}



class RemoveLock implements Runnable{

	@Override
	public void run() {
		
		System.out.println(">>>>Shutdowning!!!");
		
			boolean unlocked=SaveLoadManager.getInstance().unlock();	
			if(!unlocked)
				try {
					FileDeleteStrategy.FORCE.delete(new File(SaveLoadManager.getInstance().getWorkspace()+File.separator+".islocked"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			Launcher.getPluginEngine().shutdown();
	}
}
