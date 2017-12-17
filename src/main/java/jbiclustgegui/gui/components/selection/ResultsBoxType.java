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
package jbiclustgegui.gui.components.selection;

import jbiclustge.methods.algorithms.java.bibit.BibitMethod;
import jbiclustge.methods.algorithms.java.bicat.opsm.OPSMMethod;
import jbiclustge.methods.algorithms.java.penalizedplaid.PenalizedPlaidMethod;
import jbiclustge.methods.algorithms.r.bicarepackage.RBicAREMethod;
import jbiclustge.methods.algorithms.r.biclic.RBiclicMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RBCCCMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RPlaidMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RQuestMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RQuestmetMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RQuestordMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RSpectralMethod;
import jbiclustge.methods.algorithms.r.biclustpackage.RXMOTIFSMethod;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaMethod;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaPMethod;
import jbiclustge.methods.algorithms.r.fabiapackage.RFabiaSMethod;
import jbiclustge.methods.algorithms.r.isapackage.RIsaMethod;
import jbiclustge.methods.algorithms.wrappers.BBCMethod;
import jbiclustge.methods.algorithms.wrappers.BiMinePlusMethod;
import jbiclustge.methods.algorithms.wrappers.BicFinderMethod;
import jbiclustge.methods.algorithms.wrappers.BimaxMethod;
import jbiclustge.methods.algorithms.wrappers.COALESCEMethod;
import jbiclustge.methods.algorithms.wrappers.CPBMethod;
import jbiclustge.methods.algorithms.wrappers.QuBicMethod;
import jbiclustge.methods.algorithms.wrappers.UBClustMethod;
import jbiclustge.methods.algorithms.wrappers.UnibicMethod;
import jbiclustge.methods.algorithms.wrappers.debi.DebiMethod;
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
import jbiclustgegui.datatypes.biclusteringresults.QuestMResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.QuestORDResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.QuestResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.SpectralResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UbclustResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.UnibicResultsBox;
import jbiclustgegui.datatypes.biclusteringresults.XmotifsResultsBox;

// TODO: Auto-generated Javadoc
/**
 * The Enum ResultsBoxType.
 */
public enum ResultsBoxType {
	
	/** The all. */
	ALL{
		@Override
		public Class<?> getResultsBoxClass() {
			return BiclusteringResultBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return "All";
		}
	},
	
	/** The bbc. */
	BBC{
		@Override
		public Class<?> getResultsBoxClass() {
			return BBCResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return BBCMethod.NAME;
		}
	},
	
	/** The bibit. */
	BIBIT{
		@Override
		public Class<?> getResultsBoxClass() {
			return BibitResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return BibitMethod.NAME;
		}
	},
	
	/** The bicare. */
	BICARE{
		@Override
		public Class<?> getResultsBoxClass() {
			return BicareFlocResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RBicAREMethod.NAME;
		}
	},
	
	/** The bicfinder. */
	BICFINDER{
		@Override
		public Class<?> getResultsBoxClass() {
			return BicFinderResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return BicFinderMethod.NAME;
		}
	},
	
	/** The biclic. */
	BICLIC{
		@Override
		public Class<?> getResultsBoxClass() {
			return BiclicResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RBiclicMethod.NAME;
		}
	},
	
	/** The bimax. */
	BIMAX{
		@Override
		public Class<?> getResultsBoxClass() {
			return BimaxResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return BimaxMethod.NAME;
		}
	},
	
	/** The bimineplus. */
	BIMINEPLUS{
		@Override
		public Class<?> getResultsBoxClass() {
			return BimineplusResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return BiMinePlusMethod.NAME;
		}
	},
	
	/** The chengchurch. */
	CHENGCHURCH{
		@Override
		public Class<?> getResultsBoxClass() {
			return ChengAndChurchResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RBCCCMethod.NAME;
		}
	},
	
	/** The coalesce. */
	COALESCE{
		@Override
		public Class<?> getResultsBoxClass() {
			return COALESCEResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return COALESCEMethod.NAME;
		}
	},
	
	/** The cpb. */
	CPB{
		@Override
		public Class<?> getResultsBoxClass() {
			return CPBResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return CPBMethod.NAME;
		}
	},
	
	/** The debi. */
	DEBI{
		@Override
		public Class<?> getResultsBoxClass() {
			return DebiResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return DebiMethod.NAME;
		}
	},
	
	/** The fabia. */
	FABIA{
		@Override
		public Class<?> getResultsBoxClass() {
			return FabiaResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RFabiaMethod.NAME;
		}
	},
	
	/** The fabiap. */
	FABIAP{
		@Override
		public Class<?> getResultsBoxClass() {
			return FabiaPResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RFabiaPMethod.NAME;
		}
	},
	
	/** The fabias. */
	FABIAS{
		@Override
		public Class<?> getResultsBoxClass() {
			return FabiaSResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RFabiaSMethod.NAME;
		}
	},
	
	/** The isa. */
	ISA{
		@Override
		public Class<?> getResultsBoxClass() {
			return ISAResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RIsaMethod.NAME;
		}
	},
	
	/** The opsm. */
	OPSM{
		@Override
		public Class<?> getResultsBoxClass() {
			return OPSMResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return OPSMMethod.NAME;
		}
	},
	
	/** The penalizedplaid. */
	PENALIZEDPLAID{
		@Override
		public Class<?> getResultsBoxClass() {
			return PenalizedPlaidResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return PenalizedPlaidMethod.NAME;
		}
	},
	
	/** The plaid. */
	PLAID{
		@Override
		public Class<?> getResultsBoxClass() {
			return PlaidResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RPlaidMethod.NAME;
		}
	},
	
	/** The qubic. */
	QUBIC{
		@Override
		public Class<?> getResultsBoxClass() {
			return QubicResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return QuBicMethod.NAME;
		}
	},
	
	/** The quest. */
	QUEST{
		@Override
		public Class<?> getResultsBoxClass() {
			return QuestResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RQuestMethod.NAME;
		}
	},
	
	/** The questmet. */
	QUESTMET{
		@Override
		public Class<?> getResultsBoxClass() {
			return QuestMResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RQuestmetMethod.NAME;
		}
	},
	
	/** The questord. */
	QUESTORD{
		@Override
		public Class<?> getResultsBoxClass() {
			return QuestORDResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RQuestordMethod.NAME;
		}
	},
	
	/** The spectral. */
	SPECTRAL{
		@Override
		public Class<?> getResultsBoxClass() {
			return SpectralResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RSpectralMethod.NAME;
		}
	},
	
	/** The ubclust. */
	UBCLUST{
		@Override
		public Class<?> getResultsBoxClass() {
			return UbclustResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return UBClustMethod.NAME;
		}
	},
	
	/** The unibic. */
	UNIBIC{
		@Override
		public Class<?> getResultsBoxClass() {
			return UnibicResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return UnibicMethod.NAME;
		}
	},
	
	/** The xmotifs. */
	XMOTIFS{
		@Override
		public Class<?> getResultsBoxClass() {
			return XmotifsResultsBox.class;
		}
		
		@Override
		public String getBiclusteringMethodName() {
			return RXMOTIFSMethod.NAME;
		}
	};
	
	
	
	/**
	 * Gets the results box class.
	 *
	 * @return the results box class
	 */
	public Class<?> getResultsBoxClass(){
		return getResultsBoxClass();
	}
	
	/**
	 * Gets the biclustering method name.
	 *
	 * @return the biclustering method name
	 */
	public String getBiclusteringMethodName() {
		return getBiclusteringMethodName();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getBiclusteringMethodName();
	}

}
