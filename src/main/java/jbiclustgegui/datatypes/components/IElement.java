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
 * Created by inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.datatypes.components;

import jbiclustgegui.datatypes.project.Project;

// TODO: Auto-generated Javadoc
/**
 * The Interface IElement.
 */
public interface IElement{
	
	/**
	 * Gets the by class.
	 *
	 * @return the by class
	 */
	Class<?> getByClass();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Can delete.
	 *
	 * @return true, if successful
	 */
	boolean canDelete();
	
	/**
	 * Gets the owner project.
	 *
	 * @return the owner project
	 */
	Project getOwnerProject();
}