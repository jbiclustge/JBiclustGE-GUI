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
package jbiclustgegui.gui.components.containers;

import java.io.Serializable;
import java.util.Properties;

import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAConfigurationContainer.
 */
public class GSEAConfigurationContainer implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The config. */
	private Properties config;
	
	/** The analysertype. */
	private GSEAAnalyserType analysertype;
	
	
	/**
	 * Instantiates a new GSEA configuration container.
	 *
	 * @param config the config
	 * @param analysertype the analysertype
	 */
	public GSEAConfigurationContainer(Properties config, GSEAAnalyserType analysertype) {
		super();
		this.config = config;
		this.analysertype = analysertype;
	}
	
	
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public Properties getConfig() {
		return config;
	}
	
	/**
	 * Gets the analyser type.
	 *
	 * @return the analyser type
	 */
	public GSEAAnalyserType getAnalyserType() {
		return analysertype;
	}
	
	/**
	 * Sets the properties.
	 *
	 * @param props the new properties
	 */
	public void setProperties(Properties props) {
		this.config=props;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return analysertype.toString();
	}
	
	
	

}
