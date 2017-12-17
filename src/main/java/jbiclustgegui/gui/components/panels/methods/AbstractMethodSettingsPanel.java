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
package jbiclustgegui.gui.components.panels.methods;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;

import org.javatuples.Pair;

import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMethodSettingsPanel.
 */
public abstract class AbstractMethodSettingsPanel extends JPanel{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instantiates a new abstract method settings panel.
	 */
	public AbstractMethodSettingsPanel() {
		initGUI();
		if(needInitComponents())
			initComponents();

	}
	

	/**
	 * Inits the GUI.
	 */
	protected abstract void initGUI();
	
	/**
	 * Need init components.
	 *
	 * @return true, if successful
	 */
	protected abstract boolean needInitComponents();
	
	/**
	 * Inits the components.
	 */
	protected abstract void initComponents();
	
	/**
	 * Gets the method settings.
	 *
	 * @return the method settings
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract Properties getMethodSettings() throws IOException;
	
	/**
	 * Reset to default values.
	 */
	public abstract void resetToDefaultValues();
	
	/**
	 * Valid settings.
	 *
	 * @return the pair
	 */
	public abstract Pair<Boolean, String> validSettings();
	
	/**
	 * Sets the current project.
	 *
	 * @param proj the new current project
	 */
	public abstract void setCurrentProject(Project proj);
	
	/**
	 * Gets the preferred dimensions.
	 *
	 * @return the preferred dimensions
	 */
	public abstract Dimension getPreferredDimensions();

}
