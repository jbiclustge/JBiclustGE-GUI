package jbiclustgelauncher;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class RunJBiclustGE {
	
	
	private static String libfolder = "lib";
	private static String aibenchLauncher = "es.uvigo.ei.aibench.Launcher";
	private static String pluginsBin = "plugins_bin";
	private static String pluginsInstall = "plugins_install";
	public static final String RESTARTTAG=".restart";
	private static String log = "jbiclustge.log";
	private static String logError = "jbiclustge.error.log";
	
	public static final int PTAG=2;
	
	
	
	
	private enum OSType {
        WINDOWS,LINUX, MACOS
     };
	
	
	
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
 	
 	 public static String getSystemSeparator(){
 		    OSType os= getOperationSystem();
 	    	if(os.equals(OSType.LINUX) || os.equals(OSType.MACOS))
 	    		return "/";
 	    	else if (os.equals(OSType.WINDOWS))
 	    	    return "\\";
 	    	
 	    	return null;
 	    }
	
 	 
 	public static String getCurrentDir(){
		 String currentpath = new File(".").getAbsolutePath();
		 Path p = Paths.get(currentpath);
		 return p.getParent().toString();
	 }
	 
	 
	 public static String getParentDir(){
		 String current=getCurrentDir();
		 Path p = Paths.get(current);
		 return p.getParent().toString();
	 }
	 
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
	
	
	
	public static String getMACOSLIBDIR(){
		
		return getParentDir()+getSystemSeparator()+"Resources/lib";
	}
	
     public static String getMACOSPLUGINSBINDIR(){
		
		return getParentDir()+getSystemSeparator()+"Resources/plugins_bin";
	}
     
     public static String getMACOSCONFDIR(){ 
       return getParentDir()+getSystemSeparator()+"Resources/conf";
     }
	
	
	public static String getMACOSClasspath(){
		StringBuilder str = new StringBuilder();
		
	
		str.append(convertJarstoClasspath(getMACOSLIBDIR(),true));
	    str.append(":");
		str.append(convertJarstoClasspath(getMACOSPLUGINSBINDIR(),true));		
		return str.toString();
	}
	
	 
		public static ArrayList<String> getMACOSJavaArgsOS(){
			ArrayList<String> javaArgs = new ArrayList<String>();
			
			javaArgs.add("-Dapple.laf.useScreenMenuBar=true");
			javaArgs.add("-Xdock:name=JBiclustGE");
			javaArgs.add("-Xdock:icon=conf/JBiclustGE.icns");
			javaArgs.add("-Xdock:icon="+getMACOSCONFDIR()+getSystemSeparator()+"JBiclustGE.icns");
			
			return javaArgs;
		}
		
		
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
		
		
		 
		 private static boolean existRestartTag(){
			 boolean exist=false;
			 
			 String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
			 File f = new File(tagpath);
			 if(f.exists())
				 exist=true;
			 
			 return exist;
		 }
		 
		 
		 private static void removeRestartTag(){
			 if(existRestartTag()){
				 String tagpath = getCurrentDir()+getSystemSeparator()+RESTARTTAG;
				 File f = new File(tagpath);
				 f.delete();
			 }
				 
		 }
		
		 private static int checkRestartTag(){
		    boolean restart = existRestartTag();
		    if(restart)
		    	return 0;
		    else
		    	return 2;
		}
		
	
	
		 public static void startJBiclustGE(){


			 int t = PTAG;
			 int p = PTAG;

		
			 do{

				 removeRestartTag();

				 ProcessBuilder pb = new ProcessBuilder(getLaunchCommands());

				 pb.directory(new File(getCurrentDir()));
				
				 pb.redirectOutput(new File(log));
				 pb.redirectError(new File(logError));
					
				 try {

					 Process proc = pb.start();
					 t=proc.waitFor();

				 } catch (IOException | InterruptedException e) {
					 e.printStackTrace();
				 }

				 p=checkRestartTag();

			 } while(t==p);

			File logfile=new File(log);
			if(logfile.exists())
				logfile.delete();
	
			 System.exit(t);	    
		 }
   

 
		public static void main(String args[]){
			RunJBiclustGE.startJBiclustGE();
		}
	
	
	
	

}
