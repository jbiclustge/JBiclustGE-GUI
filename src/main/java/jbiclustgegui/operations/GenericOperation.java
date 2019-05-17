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
package jbiclustgegui.operations;


import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.biclusteringresults.BBCResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BibitResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BicFinderResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BicareFlocResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.biclusteringresults.BimaxResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.BimineplusResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.COALESCEResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.CPBResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.ChengAndChurchResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.DebiResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaPResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.FabiaSResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.ISAResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.OPSMResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.PenalizedPlaidResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.PlaidResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.QubicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.SpectralResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UbclustResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UnibicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UnknownMethodResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.XmotifsResultsBox;
import jbiclustgegui.datatypes.components.IAnalysisResult;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.components.IProjectElement;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.elements.ElementList;
import jbiclustgegui.datatypes.project.exceptions.InvalidElementListException;

public class GenericOperation {

	
	public static void addBiclusteringResult(Project project,Class<?> klass,IBiclusteringMethodResult solution,String listID) throws InvalidElementListException{
		IElementList<IBiclusteringMethodResult> resultList = project.getBiclusteringMethodResultElementListByClass(klass);
		
		if(resultList != null)
			resultList.addElement(solution);
		else
			createBiclusteringSolutionList(project,solution,listID);
	}
	
	public static void addAnalysisResult(Project project,Class<?> klass,IAnalysisResult solution,String listID)throws InvalidElementListException {
		IElementList<IAnalysisResult> elementList = project.getAnalysisElementListByClass(klass);
		
		if(elementList != null)
			elementList.addElement(solution);
		else
			createAnalysisSolutionList(project,solution,listID);
	}
	
	public static void addProjectResult(Project project,Class<?> klass,IProjectElement solution,String listID)throws InvalidElementListException {
		IElementList<IProjectElement> elementList = project.getProjectElementListByClass(klass);
		
		if(elementList != null)
			elementList.addElement(solution);
		else
			createProjectSolutionList(project,solution,listID);
	}
	
	
	protected static void createBiclusteringSolutionList(Project project,IBiclusteringMethodResult solution,String listID) throws InvalidElementListException{
		IElementList<IBiclusteringMethodResult> elementList = new ElementList<IBiclusteringMethodResult>(listID, project);
		elementList.addElement(solution);
		project.addBiclusteringResultList(elementList);	
	}
	
	protected static void createProjectSolutionList(Project project,IProjectElement solution,String listID) throws InvalidElementListException {
		IElementList<IProjectElement> elementList = new ElementList<IProjectElement>(listID, project);
		elementList.addElement(solution);
		project.addProjectElementList(elementList);		
	}
	
	protected static void createAnalysisSolutionList(Project project,IAnalysisResult solution,String listID) throws InvalidElementListException {
		IElementList<IAnalysisResult> elementList = new ElementList<IAnalysisResult>(listID, project);
		elementList.addElement(solution);
		project.addAnalysisResultList(elementList);		
	}
	
	
	
	public static BiclusteringResultBox getResultsBox(Project proj, String datatypeid, BiclusterList results, BiclusteringMethod method) {
		
		switch (method) {
		case BBC:
			return new BBCResultsBox(datatypeid, proj, results);
		case BIBIT:
			return new BibitResultsBox(datatypeid, proj, results);
		case BICARE:
			return new BicareFlocResultsBox(datatypeid, proj, results);
		case BICFINDER:
			return new BicFinderResultsBox(datatypeid, proj, results);
		case BICLIC:
			return new BiclicResultsBox(datatypeid, proj, results);
		case BIMAX:
			return new BimaxResultsBox(datatypeid, proj, results);
		case BIMINEPLUS:
			return new BimineplusResultsBox(datatypeid, proj, results);
		case CC:
			return new ChengAndChurchResultsBox(datatypeid, proj, results);
		case CPB:
			return new CPBResultsBox(datatypeid, proj, results);
		case COALESCE:
			return new COALESCEResultsBox(datatypeid, proj, results);
		case DEBI:
			return new DebiResultsBox(datatypeid, proj, results);
		case FABIA:
			return new FabiaResultsBox(datatypeid, proj, results);
		case FABIAP:
			return new FabiaPResultsBox(datatypeid, proj, results);
		case FABIAS:
			return new FabiaSResultsBox(datatypeid, proj, results);
		case ISA:
			return new ISAResultsBox(datatypeid, proj, results);
		case OPSM:
			return new OPSMResultsBox(datatypeid, proj, results);
		case PENALIZEDPLAID:
			return new PenalizedPlaidResultsBox(datatypeid, proj, results);	
		case PLAID:
			return new PlaidResultsBox(datatypeid, proj, results);	
		case QUBIC:
			return new QubicResultsBox(datatypeid, proj, results);	
		case SPECTRAL:
			return new SpectralResultsBox(datatypeid, proj, results);
		case UBCLUST:
			return new UbclustResultsBox(datatypeid, proj, results);
		case UNIBIC:
			return new UnibicResultsBox(datatypeid, proj, results);
		case XMOTIFS:
			return new XmotifsResultsBox(datatypeid, proj, results);
		case UNKNOWN:
			return new UnknownMethodResultsBox(datatypeid, proj, results);
		
		default:
			return null;
		}
	}
	
	
}
