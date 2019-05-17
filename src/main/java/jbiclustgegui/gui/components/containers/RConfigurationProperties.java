package jbiclustgegui.gui.components.containers;

import java.util.ArrayList;

import jbiclustge.utils.osystem.CustomRInstallationManager.RenvVersion;

public class RConfigurationProperties {
	
	
	public enum RENVTYPE{
		
		SYSTEM,
		STANDALONE_NEW,
		STANDALONE_PREVIOUS;
	}
	
	

	public enum RENVSYTEM{
		
		WINDOWS,
		LINUX_DEB;
	}
	
	
	
	private RENVTYPE typeRinstall=RENVTYPE.SYSTEM;
	private RENVSYTEM os=RENVSYTEM.LINUX_DEB;
	
	private ArrayList<String> needDevPackages =null;
	private boolean canmakeRinstall=false;
	private boolean compileproc=false;
	private RenvVersion version=RenvVersion.R_3_4_3;
	
	
	private String RExecFolder=null;
	private String RLibsFolder=null;
	
	public RConfigurationProperties(RENVSYTEM osys, RENVTYPE typerenv) {
		this.os=osys;
		this.typeRinstall=typerenv;
	}
	
	
	public RConfigurationProperties setOperationSystem(RENVSYTEM sys) {
		this.os=sys;
		return this;
	}
	
	
	

	
	public String getRExecuteFolder() {
		return RExecFolder;
	}


	public RConfigurationProperties setRExecuteFolder(String rEnvFolder) {
		RExecFolder = rEnvFolder;
		return this;
	}


	public String getRPackagesFolder() {
		return RLibsFolder;
	}


	public RConfigurationProperties setRPackagesFolder(String rEnvLibsFolder) {
		RLibsFolder = rEnvLibsFolder;
		return this;
	}
	
	


	public ArrayList<String> getDevPackageNames() {
		return needDevPackages;
	}


	public RConfigurationProperties setLinuxDevLibrariesNames(ArrayList<String> needDevPackages) {
		this.needDevPackages = needDevPackages;
		return this;
	}


	public boolean candoRinstallation() {
		return canmakeRinstall;
	}


	public RConfigurationProperties setCandoRinstallation(boolean canmakeRinstall) {
		this.canmakeRinstall = canmakeRinstall;
		return this;
	}


	public boolean isToCompileLibs() {
		return compileproc;
	}


	public RConfigurationProperties setCompileLinuxLibs(boolean compileproc) {
		this.compileproc = compileproc;
		return this;
	}
	
	


	public RenvVersion getVersion() {
		return version;
	}


	public RConfigurationProperties setVersion(RenvVersion version) {
		this.version = version;
		return this;
	}
	
	


	public RENVTYPE getTypeinstallation() {
		return typeRinstall;
	}


	public RENVSYTEM getROS() {
		return os;
	}


	public static RConfigurationProperties newInstance(RENVSYTEM osys, RENVTYPE typerenv) {
		return new RConfigurationProperties(osys, typerenv);
	}
	
}
