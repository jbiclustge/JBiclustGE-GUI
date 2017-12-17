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
package jbiclustgegui.gui.synthetic.containers;

import jbiclustge.analysis.syntheticdata.factory.AbstractSyntheticDataFactory;
import jbiclustge.analysis.syntheticdata.factory.biclusters.AbstractBiclusterFactory;
import jbiclustge.analysis.syntheticdata.factory.biclusters.BiclusterModelType;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating IBiclusterModelData objects.
 */
public interface IBiclusterModelDataFactory {
	
	
	/**
	 * Gets the synthetic data builder.
	 *
	 * @return the synthetic data builder
	 */
	public AbstractSyntheticDataFactory getSyntheticDataBuilder();
	
	/**
	 * Gets the type bicluster model.
	 *
	 * @return the type bicluster model
	 */
	public BiclusterModelType getTypeBiclusterModel();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	

}
