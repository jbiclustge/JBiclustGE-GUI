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
package launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class RunJBiclustGE.
 */
public final class RunJBiclustGE {
	
	
	/** The libfolder. */
	private static String libfolder = "lib";
	
	/** The aibench launcher. */
	private static String aibenchLauncher = "es.uvigo.ei.aibench.Launcher";
	
	/** The plugins bin. */
	private static String pluginsBin = "plugins_bin";
	
	/** The plugins install. */
	private static String pluginsInstall = "plugins_install";
	
	/** The Constant RESTARTTAG. */
	public static final String RESTARTTAG=".restart";
	
	/** The log. */
	private static String log = "jbiclustge.log";
	
	/** The log error. */
	private static String logError = "jbiclustge.error.log";
	
	/** The Constant PTAG. */
	public static final int PTAG=2;
	
	
	
	
	/**
	 * The Enum OSType.
	 */
	private enum OSType {
        
        /** The windows. */
        WINDOWS,
/** The linux. */
LINUX, 
 /** The macos. */
 MACOS
     };
	
	
	
     /**
      * Gets the operation system.
      *
      * @return the operation system
      */
     private static OSType getOperationSystem()  {
     	String os = System.getProperty("os.name").toLowerCase();

     	if(os.indexOf("nux")>=0 || os.indexOf("nix") >= 0){
     		return OSType.LINUX;
     	}
     	else if(os.indexOf("win") >= 0){
     	    return OSType.WINDOWS;
     		
     	}
     	else if(os.indexOf("mac") >= 0){
     			return OSType.MACOS;
     	}

     	return null;
     }
 	
 	 /**
 	  * Gets the system separator.
 	  *
 	  * @return the system separator
 	  */
 	 public static String getSystemSeparator(){
 		    OSType os= getOperationSystem();
 	    	if(os.equals(OSType.LINUX) || os.equals(OSType.MACOS))
 	    		return "/";
 	    	else if (os.equals(OSType.WINDOWS))
 	    	    return "\\";
 	    	
 	    	return null;
 	    }
	
 	 
 	/**
	  * Gets the current dir.
	  *
	  * @return the current dir
	  */
	 public static String getCurrentDir(){
		 String currentpath = new File(".").getAbsolutePath();
		 Path p = Paths.get(currentpath);
		 return p.getParent().toString();
	 }
	 
	 
	 /**
 	 * Gets the parent dir.
 	 *
 	 * @return the parent dir
 	 */
 	public static String getParentDir(){
		 String current=getCurrentDir();
		 Path p = Paths.get(current);
		 return p.getParent().toString();
	 }
	 
 /**
  * Gets the filenames in folder.
  *
  * @param folder the folder
  * @param ext the ext
  * @param ismac the ismac
  * @return the filenames in folder
  */
 public static ArrayList<String> getFilenamesInFolder(String folder, String ext, boolean ismac){
		 
		 File file =null;
		 
		 if(ismac)
			 file = new File(folder); 
		 else
		     file = new File(getCurrentDir()+getSystemSeparator()+folder); 
		 ArrayList<String> res= new ArrayList<>();
		 File[] files = file.listFiles();  
		 for (File f:files){
			 String name=f.getName();
			 if(name.endsWith("."+ext))
			 res.add(name);
		 }
		 
		 return res;
		 
		 
	 }
	 
	 
	 
	/**
	 * Convert jarsto classpath.
	 *
	 * @param folder the folder
	 * @param ismac the ismac
	 * @return the string
	 */
	public static String convertJarstoClasspath(String folder, boolean ismac){

		 ArrayList<String> jars = getFilenamesInFolder(folder,"jar",ismac);
		 
		 StringBuilder str= new StringBuilder();
		 
		 if(jars!=null){
		 
		 for (int i = 0; i < jars.size(); i++) {
			 String path=null;
			 if(getOperationSystem().equals(OSType.MACOS))
				 path=folder+getSystemSeparator()+jars.get(i);
			 else
				 path=getCurrentDir()+getSystemSeparator()+folder+getSystemSeparator()+jars.get(i);
			str.append(path);
			if(i<jars.size()-1){
				if(getOperationSystem().equals(OSType.WINDOWS))
					str.append(";");
				else
			       str.append(":");
			  
			 }
		   }
		 }
		 return str.toString(); 
	 }
	
	
	/**
	 * Convert jarsto MACOS classpath.
	 *
	 * @param folder the folder
	 * @return the string
	 */
	public static String convertJarstoMACOSClasspath(String folder){
		 
		 ArrayList<String> jars = getFilenamesInFolder(folder,"jar",true);
		 
		 StringBuilder str= new StringBuilder();
		 
		 for (int i = 0; i < jars.size(); i++) {
			String path=folder+getSystemSeparator()+jars.get(i);
			str.append(path);
			if(i<jars.size()-1){
			       str.append(":");
			  
			}
		 }
		 return str.toString(); 
	 }
	
	
	
	
	/**
	 * Gets the classpath.
	 *
	 * @return the classpath
	 */
	public static String getClasspath(){
		StringBuilder str = new StringBuilder();
		
		if(getOperationSystem().equals(OSType.WINDOWS))
			str.append("\"");
		
		str.append(convertJarstoClasspath(libfolder,false));
		
		if(getOperationSystem().equals(OSType.WINDOWS))
			str.append(";");
		else
	       str.append(":");
		
		str.append(convertJarstoClasspath(pluginsBin,false));
		
		if(getOperationSystem().equals(OSType.WINDOWS))
			str.append("\"");
		
		return str.toString();
	}
	
	
	
	/**
	 * Gets the macoslibdir.
	 *
	 * @return the macoslibdir
	 */
	public static String getMACOSLIBDIR(){
		
		return getParentDir()+getSystemSeparator()+"Resources/lib";
	}
	
     /**
      * Gets the macospluginsbindir.
      *
      * @return the macospluginsbindir
      */
     public static String getMACOSPLUGINSBINDIR(){
		
		return getParentDir()+getSystemSeparator()+"Resources/plugins_bin";
	}
     
     /**
      * Gets the macosconfdir.
      *
      * @return the macosconfdir
      */
     public static String getMACOSCONFDIR(){ 
       return getParentDir()+getSystemSeparator()+"Resources/conf";
     }
	
	
	/**
	 * Gets the MACOS classpath.
	 *
	 * @return the MACOS classpath
	 */
	public static String getMACOSClasspath(){
		StringBuilder str = new StringBuilder();
		
	
		str.append(convertJarstoClasspath(getMACOSLIBDIR(),true));
	    str.append(":");
		str.append(convertJarstoClasspath(getMACOSPLUGINSBINDIR(),true));		
		return str.toString();
	}
	
	 
		/**
		 * Gets the MACOS java args OS.
		 *
		 * @return the MACOS java args OS
		 */
		public static ArrayList<String> getMACOSJavaArgsOS(){
			ArrayList<String> javaArgs = new ArrayList<String>();
			
			javaArgs.add("-Dapple.laf.useScreenMenuBar=true");
			javaArgs.add("-Xdock:name=JBiclustGE");
			javaArgs.add("-Xdock:icon=conf/JBiclustGE.icns");
			javaArgs.add("-Xdock:icon="+getMACOSCONFDIR()+getSystemSeparator()+"JBiclustGE.icns");
			
			return javaArgs;
		}
		
		
		/**
		 * Gets the launch commands.
		 *
		 * @return the launch commands
		 */
		private static ArrayList<String> getLaunchCommands(){
			
			ArrayList<String> cmd = new ArrayList<>();
			
			cmd.add("java");
			cmd.add("-Duser.language=en");
			cmd.add("-Xmx2048m");
			cmd.add("-Dfile.encoding=utf-8");
			if(getOperationSystem().equals(OSType.MACOS))
				cmd.addAll(getMACOSJavaArgsOS());
			cmd.add("-cp");
			if(getOperationSystem().equals(OSType.MACOS))
				cmd.add(getMACOSClasspath());
			else
		        cmd.add(getClasspath());
			cmd.add(aibenchLauncher);
			cmd.add(pluginsBin);
			return cmd;
		}
		
		
		 
		 /**
 		 * Exist restart tag.
 		 *
 		 * @return true, if successful
 		 */
 		private static boolean existRestartTag(){
			 boolean exist=false;
			 
			 String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
			 File f = new File(tagpath);
			 if(f.exists())
				 exist=true;
			 
			 return exist;
		 }
		 
		 
		 /**
 		 * Removes the restart tag.
 		 */
 		private static void removeRestartTag(){
			 if(existRestartTag()){
				 String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
				 File f = new File(tagpath);
				 f.delete();
			 }
				 
		 }
		
		 /**
 		 * Check restart tag.
 		 *
 		 * @return the int
 		 */
 		private static int checkRestartTag(){
		    boolean restart = existRestartTag();
		    if(restart)
		    	return 0;
		    else
		    	return 2;
		}
		
	
	
   /**
    * Start J biclust GE.
    */
   public static void startJBiclustGE(){
		
		int t = PTAG;
		int p = PTAG;
		
	
		do{
		
		  removeRestartTag();
	
		  ProcessBuilder pb = new ProcessBuilder(getLaunchCommands());

          pb.directory(new File(getCurrentDir()));
          //pb.redirectOutput(new File(log));
		  //pb.redirectError(new File(logError));
          try {
        	  
              Process proc = pb.start();
              t=proc.waitFor();
         
          } catch (IOException | InterruptedException e) {
              e.printStackTrace();
          }
          
          p=checkRestartTag();
          
		} while(t==p);
		
		/*File logfile=new File(log);
		File logerrorfile=new File(logError);
		
		if(logfile.exists())
			logfile.delete();
		if(logerrorfile.exists())
			logerrorfile.delete();*/
  	    
  	       System.exit(t);	    
	}
   

 
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String args[]){
			RunJBiclustGE.startJBiclustGE();
			
		}
	
	
	
	

}
