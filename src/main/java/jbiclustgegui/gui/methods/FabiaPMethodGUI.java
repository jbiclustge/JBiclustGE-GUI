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
package jbiclustgegui.gui.methods;

import java.awt.Dimension;

import jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.FabiaPSettingsPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class FabiaPMethodGUI.
 */
public class FabiaPMethodGUI extends AbstractMethodGUI{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.methods.AbstractMethodGUI#getMethodSettingsPanel()
	 */
	@Override
	protected AbstractMethodSettingsPanel getMethodSettingsPanel() {
		return new FabiaPSettingsPanel();
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.methods.AbstractMethodGUI#getDialogDimensions()
	 */
	@Override
	protected Dimension getDialogDimensions() {
		return new Dimension(700,800);
	}

}
