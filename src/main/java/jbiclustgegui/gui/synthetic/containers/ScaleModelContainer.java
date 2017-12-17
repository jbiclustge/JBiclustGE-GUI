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

import java.util.Map;

import jbiclustge.analysis.syntheticdata.distribution.NormalDistributionDataFactory;
import jbiclustge.analysis.syntheticdata.factory.AbstractSyntheticDataFactory;
import jbiclustge.analysis.syntheticdata.factory.biclusters.BiclusterModelType;
import jbiclustge.analysis.syntheticdata.factory.biclusters.ScaleBiclusterFactory;
import jbiclustge.analysis.syntheticdata.factory.modeltype.ScaleSyntheticDataBuilder;
import jbiclustge.analysis.syntheticdata.factory.props.SyntheticDataProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class ScaleModelContainer.
 */
public class ScaleModelContainer implements IBiclusterModelDataFactory {

	
	 /** The builder. */
 	private ScaleSyntheticDataBuilder builder;
	 
	 /** The bicmeandist. */
 	public static String BICMEANDIST="BICMEANDIST";
	 
 	/** The bicsddist. */
 	public static String BICSDDIST="BICSDDIST";
	 
 	/** The scalemean. */
 	public static String SCALEMEAN="SCALEMEAN";
	 
 	/** The scalesd. */
 	public static String SCALESD="SCALESD";
	 
 	/** The columnmean. */
 	public static String COLUMNMEAN="COLUMNMEAN";
	 
 	/** The columnsd. */
 	public static String COLUMNSD="COLUMNSD";
	
	
	 /**
 	 * Instantiates a new scale model container.
 	 *
 	 * @param dataprops the dataprops
 	 * @param modelprops the modelprops
 	 */
 	public ScaleModelContainer(SyntheticDataProperties dataprops, Map<String, Object> modelprops) {
		 configureBuilder(dataprops, modelprops);
	 }
	
	
	 /**
 	 * Configure builder.
 	 *
 	 * @param dataprops the dataprops
 	 * @param modelprops the modelprops
 	 */
 	private void configureBuilder(SyntheticDataProperties dataprops, Map<String, Object> modelprops) { 
		
		 double mean=(double) modelprops.get(BICMEANDIST);
		 double sd=(double) modelprops.get(BICSDDIST);
		 
		 NormalDistributionDataFactory dist=new NormalDistributionDataFactory(mean,sd);
		 
		 
		 
		 ScaleBiclusterFactory bicfactory=new ScaleBiclusterFactory(dist,
				 							(double) modelprops.get(SCALEMEAN),
				 							(double) modelprops.get(SCALESD),
				 							(double) modelprops.get(COLUMNMEAN),
				 							(double) modelprops.get(COLUMNSD));
		 
		 
		 builder=new ScaleSyntheticDataBuilder(dataprops, bicfactory);

	 }
	
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.containers.IBiclusterModelDataFactory#getSyntheticDataBuilder()
	 */
	@Override
	public AbstractSyntheticDataFactory getSyntheticDataBuilder() {
		return builder;
	}


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.containers.IBiclusterModelDataFactory#getTypeBiclusterModel()
	 */
	@Override
	public BiclusterModelType getTypeBiclusterModel() {
		return BiclusterModelType.SCALE;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.synthetic.containers.IBiclusterModelDataFactory#getName()
	 */
	@Override
	public String getName() {
		return getTypeBiclusterModel().getName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

}
