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
package jbiclustgegui.gui.components;

import java.beans.PropertyChangeEvent;
import java.io.File;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;

import jbiclustge.utils.properties.JBiGePropertiesManager;
import pt.ornrocha.logutils.MTULogLevel;
import pt.ornrocha.logutils.MTULogUtils;
import pt.ornrocha.logutils.messagecomponents.ILogProgressListener;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;

// TODO: Auto-generated Javadoc
/**
 * The Class JBiclustLogCenter.
 */
public class JBiclustLogCenter implements ILogProgressListener{

	/** The currentlevel. */
	private MTULogLevel currentlevel=MTULogLevel.INFO;
	

	/** The currentclass. */
	private Class<?> currentclass;
	
	/** The usestacktrace. */
	private boolean usestacktrace=false;
	
	/** The Constant LOGGER. */
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(JBiclustLogCenter.class.getName());
	
	/**
	 * Instantiates a new j biclust log center.
	 */
	public JBiclustLogCenter () {
        currentlevel=getDefinedLogLevel();
	}
	
	
	/**
	 * Gets the defined log level.
	 *
	 * @return the defined log level
	 */
	private MTULogLevel getDefinedLogLevel(){
		MTULogLevel level =MTULogLevel.INFO;
		File f =new File(JBiGePropertiesManager.propsfile);
		if(f.exists()) {
			PropertiesConfiguration config=null;
			try {
				config = new Configurations().properties(f);
			} catch (ConfigurationException e) {

			}
			if(config!=null) {
				Object value=config.getProperty("loglevel");
				if(value!=null) {
					level=MTULogLevel.getLevelFromStringName(String.valueOf(value).toLowerCase());	
				}
			}
		}
		return level;
	}
	

	/* (non-Javadoc)
	 * @see pt.ornrocha.logutils.messagecomponents.ILogProgressListener#getmaxLogLevel()
	 */
	@Override
	public MTULogLevel getmaxLogLevel() {
		return currentlevel;
	}

	/* (non-Javadoc)
	 * @see pt.ornrocha.logutils.messagecomponents.ILogProgressListener#getDefaultLevel()
	 */
	@Override
	public MTULogLevel getDefaultLevel() {
		return currentlevel;
	}

	/* (non-Javadoc)
	 * @see pt.ornrocha.logutils.messagecomponents.ILogProgressListener#setCurrentClass(java.lang.Class)
	 */
	@Override
	public void setCurrentClass(Class<?> currentclass) {
		this.currentclass=currentclass;
		
	}

	/* (non-Javadoc)
	 * @see pt.ornrocha.logutils.messagecomponents.ILogProgressListener#enableStackTrace(boolean)
	 */
	@Override
	public void enableStackTrace(boolean enable) {
		usestacktrace=enable;
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String cmd =evt.getPropertyName();
		if(cmd!=null) {
			if(cmd.equals(LogMessageCenter.INFO_MESSSAGE)){
				if(currentclass!=null) {
					MTULogUtils.addInfoMsgToClass(currentclass, String.valueOf(evt.getNewValue()));
					Logger.getLogger(currentclass).info(String.valueOf(evt.getNewValue()));
				}
				else {
					MTULogUtils.addInfoMsg(String.valueOf(evt.getNewValue()));	
					Logger.getRootLogger().info(String.valueOf(evt.getNewValue()));
				}
			}
			else if(cmd.equals(LogMessageCenter.DEBUG_MESSAGE)){
				if(currentclass!=null) {
					MTULogUtils.addDebugMsgToClass(currentclass, String.valueOf(evt.getNewValue()));
					Logger.getLogger(currentclass).info(String.valueOf(evt.getNewValue()));
				}
				else {
					MTULogUtils.addDebugMsg(String.valueOf(evt.getNewValue()));
					Logger.getRootLogger().info(String.valueOf(evt.getNewValue()));
				}

			}
			else if(cmd.equals(LogMessageCenter.TRACE_MESSAGE)){
				if(currentclass!=null) {
					MTULogUtils.addTraceMsgToClass(currentclass, String.valueOf(evt.getNewValue()));
					Logger.getLogger(currentclass).info(String.valueOf(evt.getNewValue()));
				}
				else {
					MTULogUtils.addTraceMsg(String.valueOf(evt.getNewValue()));
					Logger.getRootLogger().info(String.valueOf(evt.getNewValue()));
				}

			}
			else if(cmd.equals(LogMessageCenter.ERROR_MESSAGE) || cmd.equals(LogMessageCenter.CRITICAL_ERROR_MESSAGE)) {
				if(currentclass!=null) {
					MTULogUtils.addErrorMsgToClass(currentclass, String.valueOf(evt.getNewValue()));
					Logger.getLogger(currentclass).error(String.valueOf(evt.getNewValue()));
				}
				else {
					MTULogUtils.addErrorMsg(String.valueOf(evt.getNewValue()));
					Logger.getRootLogger().error(String.valueOf(evt.getNewValue()));
				}
			}
			else if(cmd.equals(LogMessageCenter.THROWABLE_MESSAGE)){
				Throwable e =(Throwable) evt.getNewValue();
				if(usestacktrace)
					e.printStackTrace();
				else{
					if(currentclass!=null) {
						MTULogUtils.addErrorMsgToClass(currentclass, e.getMessage());
						Logger.getLogger(currentclass).error(String.valueOf(evt.getNewValue()));
					}
					else {
						MTULogUtils.addErrorMsg(e.getMessage());
						Logger.getRootLogger().error(String.valueOf(evt.getNewValue()));
					}
				}
			}

		}

	}

}
