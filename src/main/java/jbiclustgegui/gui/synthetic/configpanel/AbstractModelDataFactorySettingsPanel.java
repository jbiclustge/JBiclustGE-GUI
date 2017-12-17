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
package jbiclustgegui.gui.synthetic.configpanel;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractModelDataFactorySettingsPanel.
 */
public abstract class AbstractModelDataFactorySettingsPanel extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The numberbics. */
	protected Integer numberbics;
	
	/**
	 * Instantiates a new abstract model data factory settings panel.
	 */
	public AbstractModelDataFactorySettingsPanel() {
		initGUI();
		initComponents();
	}
	
	/**
	 * Instantiates a new abstract model data factory settings panel.
	 *
	 * @param numberbiclusters the numberbiclusters
	 */
	public AbstractModelDataFactorySettingsPanel(Integer numberbiclusters) {
		this.numberbics=numberbiclusters;
		initGUI();
		initComponents();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	protected abstract void initGUI();
	
	/**
	 * Inits the components.
	 */
	protected abstract void initComponents();
	
	/**
	 * Gets the mandatory options.
	 *
	 * @return the mandatory options
	 */
	public abstract Map<String, Object> getMandatoryOptions();
	
	/**
	 * Gets the dimensions.
	 *
	 * @return the dimensions
	 */
	public abstract Dimension getDimensions();

}
