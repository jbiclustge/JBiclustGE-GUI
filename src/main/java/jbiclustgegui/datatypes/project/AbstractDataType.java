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
package jbiclustgegui.datatypes.project;

import java.util.Observable;

import javax.swing.JOptionPane;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElement;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.components.IProjectElement;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDataType.
 */
public abstract class AbstractDataType extends Observable{
	
	/** The name. */
	protected String name;
	
	/**
	 * Gets the owner project.
	 *
	 * @return the owner project
	 */
	//private Set<IDataTypeContainerLinkage> datatypes;
	public abstract Project getOwnerProject();
	
	/**
	 * Instantiates a new abstract data type.
	 *
	 * @param name the name
	 */
	public AbstractDataType(String name){
		this.name = name;
	}
	
	/**
	 * Sets the name without test.
	 *
	 * @param name the new name without test
	 */
	public void setNameWithoutTest(String name){
		this.name = name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		setName(name, true);
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name
	 * @param notify the notify
	 * @return true, if successful
	 */
	public boolean setName(String name, boolean notify){
		
		boolean toRet =false;
//		name=name.trim();
		if(name.length() == 0){
			Workbench.getInstance().warn("The name cannot be empty.");
			setChanged();
		}else if(name.matches(".*[\\/\\:\\*\\?\\\"\\<\\>\\|\\\\].*"))
		{
			String invalidChars = "\\ / : * ? \" < > |";
			Workbench.getInstance().warn("The name can't contain any of the following characters:\n "+ invalidChars);
			setChanged();
		}
		else if(!name.equals(this.name)){
			if(!getIElementList().existElement(name, true))
			{
				String oldName = this.name;
				removeInWorkspace(name);
				this.name = name;
				addInWorkspace(oldName);
				toRet = true;
			}				
			setChanged();
		}
		if(notify) 
			notifyObservers();
		
		return toRet;
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Removes the in workspace.
	 *
	 * @param oldName the old name
	 */
	protected void removeInWorkspace(String oldName){
		//if(PMUtils.useWorkspace()){
			try {
				SaveLoadManager.getInstance().removeData(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
	
	/**
	 * Adds the in workspace.
	 *
	 * @param oldName the old name
	 */
	protected void addInWorkspace(String oldName){
		//if(PMUtils.useWorkspace()){
			try {
				SaveLoadManager.getInstance().saveData(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}
	
	/**
	 * Gets the i element list.
	 *
	 * @return the i element list
	 */
	protected IElementList<?> getIElementList(){
		Project p = this.getOwnerProject();
		IElementList<?> list = null;
		if (this instanceof IProjectElement) {
			list = p.getProjectElementListByClass(((IProjectElement)this).getByClass());
		}else if(this instanceof IAnalysisResult){
			list = p.getAnalysisElementListByClass(((IAnalysisResult)this).getByClass());
		}else if(this instanceof IBiclusteringMethodResult){
			list = p.getBiclusteringMethodResultElementListByClass(((IBiclusteringMethodResult)this).getByClass());
		}
		return list;
	}
	
	
	
	/**
	 * Removes the.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean remove() throws Exception{
		boolean toRemove = false;

		int dialogResult = JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(),
				"Are you sure you want to remove '"+getName()+"'?", "Delete Item",
				JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE);

		if(dialogResult==JOptionPane.YES_OPTION){
			synchronized (this) {
				removeFromProject(getOwnerProject(), this);
				SaveLoadManager.getInstance().removeData(this);
			}

			toRemove = true;
		}

		return toRemove;
	}

	
/*	synchronized protected Set<IDataTypeContainerLinkage> getContainerListData(){
		if(datatypes == null)
			datatypes = new HashSet<IDataTypeContainerLinkage>();
		return datatypes;
	}*/
	
/*	public void registAssociatedDataType(IDataTypeContainerLinkage data) {
		getContainerListData().add(data);
		
	}

	public void unregistAssociatedDataType(IDataTypeContainerLinkage data) {
		getContainerListData().remove(data);
	}*/
	
	/*public boolean canDelete(){
		return getContainerListData() == null || getContainerListData().size()==0;
	}*/
	
	/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
	public String toString(){
		return getName();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		String t ="test\\new";
		if(t.matches(".*[\\/\\:\\*\\?\\\"\\<\\>\\|\\\\].*"))
		{
			String invalidChars = "\\ / : * ? \" < > | ";
			System.out.println("The name can't contain any of the following characters:\n "+ invalidChars);
		}
		else
			System.out.println("No match");
		
	}
	

	/**
	 * Removes the from project.
	 *
	 * @param <T> the generic type
	 * @param p the p
	 * @param dataType the data type
	 */
	public synchronized static <T extends IElement> void removeFromProject(Project p, Object dataType){
		
		IElementList<T> list = getElementListByClass(p, dataType);
		if(list != null)
		synchronized (list) {
			list.removeElement((T) dataType);
			
			Class<?> klass = ((IElement) dataType).getByClass();
			if(list.size() == 0)
				p.removeListFromList(list, klass);
		}
	}
	
	/**
	 * Gets the element list by class.
	 *
	 * @param <T> the generic type
	 * @param p the p
	 * @param dataType the data type
	 * @return the element list by class
	 */
	public synchronized static <T extends IElement> IElementList<T> getElementListByClass(Project p, Object dataType){
		IElementList<T> list = null;
		
		if(IAnalysisResult.class.isAssignableFrom(dataType.getClass())){
			list = (IElementList<T>) p.getAnalysisElementListByClass(((IElement)dataType).getByClass());
		}else if(IBiclusteringMethodResult.class.isAssignableFrom(dataType.getClass())){
			list = (IElementList<T>) p.getBiclusteringMethodResultElementListByClass(((IElement)dataType).getByClass());
		}else if(IProjectElement.class.isAssignableFrom(dataType.getClass())){
			list = (IElementList<T>) p.getProjectElementListByClass(((IElement)dataType).getByClass());
		}	
		return list;
	}

}
