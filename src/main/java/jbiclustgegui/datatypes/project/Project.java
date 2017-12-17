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

import java.io.Serializable;
import java.time.LocalDateTime;

import es.uvigo.ei.aibench.core.datatypes.annotation.Clipboard;
import es.uvigo.ei.aibench.core.datatypes.annotation.Datatype;
import es.uvigo.ei.aibench.core.datatypes.annotation.Structure;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElement;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.components.IProjectElement;
import jbiclustgegui.datatypes.project.elements.AnalysisElementList;
import jbiclustgegui.datatypes.project.elements.BiclusteringAnalysisElementList;
import jbiclustgegui.datatypes.project.elements.ProjectExtraElementList;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;


// TODO: Auto-generated Javadoc
/**
 * The Class Project.
 */
@Datatype(structure = Structure.COMPLEX, namingMethod = "getName", setNameMethod= "setName", viewable = false, removable = false)
public class Project extends AbstractDataType implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The expressiondatasetbox. */
	private GeneExpressionDatasetBox expressiondatasetbox;
	
	/** The analysis result. */
	private AnalysisElementList<IElementList<IAnalysisResult>> analysisResult;
	
	/** The resultbiclusteringmethods. */
	private BiclusteringAnalysisElementList<IElementList<IBiclusteringMethodResult>> resultbiclusteringmethods;
	
	/** The extra elements list. */
	private ProjectExtraElementList<IElementList<IProjectElement>> extraElementsList;
	
	/** The creationdate. */
	private LocalDateTime creationdate;

	/**
	 * Instantiates a new project.
	 *
	 * @param id the id
	 */
	public Project(String id) {
		super(id);
		
		this.resultbiclusteringmethods=new BiclusteringAnalysisElementList<>("Biclustering Methods Results", this);
		this.analysisResult=new AnalysisElementList<>("Analysis results", this);
		this.extraElementsList=new ProjectExtraElementList<>("Project Elements", this);	
	}
	
	
	/**
	 * Instantiates a new project.
	 *
	 * @param id the id
	 * @param expressiondatasetbox the expressiondatasetbox
	 */
	public Project(String id, GeneExpressionDatasetBox expressiondatasetbox) {
         this(id);
         this.expressiondatasetbox=expressiondatasetbox;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.datatypes.project.AbstractDataType#getOwnerProject()
	 */
	@Override
	public Project getOwnerProject() {
		return this;
	}
	
	
	
	
	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public LocalDateTime getCreationdate() {
		return creationdate;
	}


	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}


	/**
	 * Sets the gene expression dataset.
	 *
	 * @param expressiondatasetbox the new gene expression dataset
	 */
	public void setGeneExpressionDataset(GeneExpressionDatasetBox expressiondatasetbox){
		this.expressiondatasetbox= expressiondatasetbox;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gets the project folder name.
	 *
	 * @return the project folder name
	 */
	public String getProjectFolderName(){
		return this.getName();
	}
	
	/**
	 * Gets the expression dataset.
	 *
	 * @return the expression dataset
	 */
	@Clipboard(name = "Gene Expression dataset", order=1)
	public GeneExpressionDatasetBox getExpressionDataset(){
		return expressiondatasetbox;
	}
	
	/**
	 * Gets the biclustering result.
	 *
	 * @return the biclustering result
	 */
	@Clipboard(name = "Biclustering methods Results", order = 2)
	public BiclusteringAnalysisElementList<IElementList<IBiclusteringMethodResult>> getBiclusteringResult(){
		return resultbiclusteringmethods;
	}
	
	/**
	 * Gets the analysis results.
	 *
	 * @return the analysis results
	 */
	@Clipboard(name = "Analysis Results", order = 3)
	public AnalysisElementList<IElementList<IAnalysisResult>> getAnalysisResults(){
		return analysisResult;
	}
	
	/**
	 * Gets the project elements.
	 *
	 * @return the project elements
	 */
	@Clipboard(name = "Project Elements", order = 4)
	public IElementList<IElementList<IProjectElement>> getProjectElements(){
		return extraElementsList;
	}

	

	/**
	 * Gets the biclustering method result element list by class.
	 *
	 * @param klass the klass
	 * @return the biclustering method result element list by class
	 */
	public IElementList<IBiclusteringMethodResult> getBiclusteringMethodResultElementListByClass(Class<?> klass){
		
		int numberOfElements = resultbiclusteringmethods.size();
		for(int i =0; i < numberOfElements; i++){
			IElementList<IBiclusteringMethodResult> elementList = resultbiclusteringmethods.getElement(i);
			if(elementList.getElementClass().equals(klass))
				return elementList;
		}
		
		return null;
	}
	

	/**
	 * Gets the analysis element list by class.
	 *
	 * @param klass the klass
	 * @return the analysis element list by class
	 */
	public IElementList<IAnalysisResult> getAnalysisElementListByClass(Class<?> klass){
		
		int numberOfElements = analysisResult.size();
		for(int i =0; i < numberOfElements; i++){
			IElementList<IAnalysisResult> elementList = analysisResult.getElement(i);
			if(elementList.getElementClass().equals(klass))
				return elementList;
		}
		
		return null;
	}
	
	/**
	 * Gets the project element list by class.
	 *
	 * @param klass the klass
	 * @return the project element list by class
	 */
	public IElementList<IProjectElement> getProjectElementListByClass(Class<?> klass){
		int numberOfElements = extraElementsList.size();
		
		for(int i = 0; i < numberOfElements;i++){
			IElementList<IProjectElement> elementList = extraElementsList.getElement(i);
			if(elementList.getElementClass().equals(klass))
				return elementList;
		}
		return null;
	}
	
	/**
	 * Adds the biclustering result list.
	 *
	 * @param elementList the element list
	 * @throws InvalidElementListException the invalid element list exception
	 */
	public void addBiclusteringResultList(IElementList<IBiclusteringMethodResult> elementList) throws InvalidElementListException{
		if(elementList.getElementClass() != null)
			resultbiclusteringmethods.addElement(elementList);
		else 
			throw new InvalidElementListException();
	}
	
	
	/**
	 * Adds the analysis result list.
	 *
	 * @param elementList the element list
	 * @throws InvalidElementListException the invalid element list exception
	 */
	public void addAnalysisResultList(IElementList<IAnalysisResult> elementList) throws InvalidElementListException{
		if(elementList.getElementClass() != null)
			analysisResult.addElement(elementList);
		else
			throw new InvalidElementListException();
	}
	
	/**
	 * Adds the project element list.
	 *
	 * @param elementList the element list
	 * @throws InvalidElementListException the invalid element list exception
	 */
	public void addProjectElementList(IElementList<IProjectElement> elementList) throws InvalidElementListException{
		if(elementList.getElementClass() != null)
			extraElementsList.addElement(elementList);
		else
			throw new InvalidElementListException();
	}
	

	/**
	 * Removes the list from list.
	 *
	 * @param <T> the generic type
	 * @param list the list
	 * @param klass the klass
	 */
	public <T extends IElement> void removeListFromList(IElementList<T> list , Class<?> klass){
		
		if(IAnalysisResult.class.isAssignableFrom(klass)){
			analysisResult.removeElement((IElementList<IAnalysisResult>) list);
		}else if(IBiclusteringMethodResult.class.isAssignableFrom(klass)){
			resultbiclusteringmethods.removeElement((IElementList<IBiclusteringMethodResult>) list);
		}else if(IProjectElement.class.isAssignableFrom(klass)){
			extraElementsList.removeElement((IElementList<IProjectElement>) list);
			
		}	
	}
	
	
	
	
}
