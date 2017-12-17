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
package jbiclustgegui.datatypes.components;

import java.util.List;



import es.uvigo.ei.aibench.core.datatypes.annotation.ListElements;

// TODO: Auto-generated Javadoc
/**
 * The Interface IElementList.
 *
 * @param <T> the generic type
 */
public interface IElementList <T extends IElement> extends IElement{

	/**
	 * Sets the name.
	 *
	 * @param id the new name
	 */
	public abstract void setName(String id);

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.components.IElement#getName()
	 */
	public abstract String getName();

	/**
	 * Gets the element list.
	 *
	 * @return the element list
	 */
	@ListElements(modifiable = true)
	public abstract List<T> getElementList();

	/**
	 * Adds the element.
	 *
	 * @param element the element
	 */
	public abstract void addElement(T element);

	/**
	 * Removes the element.
	 *
	 * @param element the element
	 */
	public abstract void removeElement(T element);

	/**
	 * Gets the element.
	 *
	 * @param i the i
	 * @return the element
	 */
	public abstract T getElement(int i);

	/**
	 * Contains.
	 *
	 * @param element the element
	 * @return true, if successful
	 */
	public abstract boolean contains(T element);

	/**
	 * Gets the by class.
	 *
	 * @param klass the klass
	 * @return the by class
	 */
	public abstract T getByClass(Class<?> klass);
	
	/**
	 * Gets the element class.
	 *
	 * @return the element class
	 */
	public Class<?> getElementClass();

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public abstract int size();
	
	/**
	 * Exist element.
	 *
	 * @param name the name
	 * @param showWarnigIfExists the show warnig if exists
	 * @return true, if successful
	 */
	public boolean existElement(String name, boolean showWarnigIfExists);

	/**
	 * Removes the.
	 *
	 * @return true, if successful
	 */
	public boolean remove();

}
