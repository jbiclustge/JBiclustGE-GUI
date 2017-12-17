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
package jbiclustgegui.datatypes.project.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.ListElements;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.components.IElement;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;

@Datatype(structure=Structure.LIST,namingMethod="getName",setNameMethod="setName", viewable=false , removeMethod="remove")
public class ElementList <T extends IElement> extends AbstractDataType implements Serializable, IElementList<T> {
	
	private static final long serialVersionUID = 1L;
	protected List<T> elementList;
	protected Class<?> klass = null;
	protected Project p = null;
	
	public ElementList(IElementList<T> elementList, Project p){
		this(elementList.getName(), p);
		klass = elementList.getElementClass();
		this.elementList = elementList.getElementList();
	}
	
	public ElementList(String id, Project p){
		super(id);
		elementList = new ArrayList<T>();
		this.p = p;
	}
	
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#getElementList()
	 */
	@ListElements(modifiable=true)
	public List<T> getElementList(){
		return elementList;
	}
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#addElement(T)
	 */
	public void addElement(T element){
		
		if(element !=null){
			if(existElement(element.getName(),false)){
				((AbstractDataType)element).setNameWithoutTest(getAlernativeName(element.getName()));
			}
			
			elementList.add(element);
			klass = element.getByClass();
			setChanged();
			notifyObservers();
		}
	}
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#removeElement(T)
	 */
	public void removeElement(T element){
		elementList.remove(element);
		setChanged();
		notifyObservers();
	}
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#getElement(int)
	 */
	public T getElement(int i){
		return elementList.get(i);
	}
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#contains(T)
	 */
	public boolean contains(T element){
		if(elementList.contains(element)) 
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see optflux.core.datatypes.generic.IElementList#getByClass(java.lang.Class)
	 */
	public T getByClass(Class<?> klass){
		if(elementList.isEmpty())
			return null;
		for(T t : elementList){
			if(t.getClass().isAssignableFrom(klass))
				return t;
		}
		return null;
	}
	
	public boolean canDelete(){
		boolean ret = true;
		for(T t : elementList){
			ret = ret && t.canDelete();
		}
		return ret;
	}
	
	public boolean remove(){
		int dialogResult = JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(),
				"Are you sure you want to remove all '"+ getName() +"'?", "Remove " + getName(),
				JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE);
		
		if(dialogResult==JOptionPane.YES_OPTION){
		synchronized (this) {
			return remove(true);
		}}
		return false;
	}
	
	public boolean remove(boolean showWarnig){
		Set<IElement> toDelete = new HashSet<>();
		boolean removeAll = true;
		
		for(T t : elementList){
			if(ElementList.class.isAssignableFrom(t.getClass())){
				ElementList list = (ElementList)t;
				
				boolean remove = list.remove(false);
				int after = list.size();
				removeAll = removeAll && after==0;
				
				if(remove) toDelete.add(t);
			}else if(t.canDelete()){
				toDelete.add(t);
			}else
				removeAll=false;
		}
		
		elementList.removeAll(toDelete);
		setChanged();
		notifyObservers();
		
		if(showWarnig && !removeAll){
			Workbench.getInstance().warn("Some items can not be removed.");
		}
		return elementList.size()==0;
	}
	
	public Class<?> getElementClass(){
		return klass;
	}

	@Override
	public int size() {
		return elementList.size();
	}

	@Override
	public Class<?> getByClass() {
		return klass;
	}

	private String getAlernativeName(String name){
		TreeSet<Integer> conflitnumbers = new TreeSet<>();
		name = name.trim();
		Pattern p = Pattern.compile(name+"\\s*\\((\\d+)\\)");
		for (int i = 0; i < elementList.size(); i++) {
			Matcher m =p.matcher(elementList.get(i).getName());
			if(m.matches()){
				String t = m.group(1);
				Integer c = Integer.parseInt(t);
				conflitnumbers.add(c);
			}
		}
		int toUse = 1;
		for(Integer v : conflitnumbers){
			if(v==toUse) toUse++;
			else break;
		}
		
		String newName = name+"("+ toUse+")";
		return newName;
	}
	
	@Override
	public boolean existElement(String name, boolean showWarnigIfExists) {
		for (int i = 0; i < elementList.size(); i++) {
			if(elementList.get(i).getName().equals(name)){
				if(showWarnigIfExists)
					Workbench.getInstance().warn("The item could not be renamed.\nThe name '"+name+"' is already used. Please use a different name.");
				return true;
			}
		}
		return false;
	}

	public String toString(){
		return elementList.toString();
	}
	
	public static void main(String[] args) {
		String name ="e. e";
		Pattern p = Pattern.compile(name+"\\s*\\((\\d+)\\)");
		Matcher m =p.matcher("e. e(23)");
		if(m.matches()){
			String t = m.group(1);
			Integer c = Integer.parseInt(t);
			System.out.println(c);
		}
		
	}

	@Override
	public Project getOwnerProject() {
		return p;
	}
}

