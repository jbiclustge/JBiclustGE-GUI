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
package jbiclustgegui.gui.analysis.profilecli.components;

import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BBCSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BibitSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BicFinderSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BicareFlocSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BiclicSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BimaxSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.BiminplusSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.CPBSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.ChengAndChurchSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.CoalesceSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.DebiSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.FabiaPSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.FabiaSSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.FabiaSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.ISASettingsPanel;
import jbiclustgegui.gui.components.panels.methods.OPSMSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.PenalizedPlaidSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.PlaidSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.QubicSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.SpectralSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.UbclustSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.UnibicSettingsPanel;
import jbiclustgegui.gui.components.panels.methods.XmotifsSettingsPanel;

public enum ProfileBiclusteringMethod {
	
	
	BIMAX{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BIMAX;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BimaxSettingsPanel();
		}
	
		
	},
	
	/** The bibit. */
	BIBIT{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BIBIT;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BibitSettingsPanel();
		}
	
	
	},
	
	/** The biclic. */
	BICLIC{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BICLIC;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BiclicSettingsPanel();
		}
		
	},
	
	/** The opsm. */
	OPSM{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.OPSM;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new OPSMSettingsPanel();
		}
	
	},
	
	/** The cc. */
	CC{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.CC;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new ChengAndChurchSettingsPanel();
		}
	
	},
	
	/** The plaid. */
	PLAID{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.PLAID;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new PlaidSettingsPanel();
		}
	
	},
	
	/** The penalizedplaid. */
	PENALIZEDPLAID{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.PENALIZEDPLAID;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new PenalizedPlaidSettingsPanel();
		}
		
	
	},
	
	/** The quest. */
	QUEST{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.QUEST;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BimaxSettingsPanel();
		}
	
	},
	
	/** The questord. */
	QUESTORD{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.QUESTORD;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BimaxSettingsPanel();
		}
		
	
	},
	
	/** The questmet. */
	QUESTMET{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.QUESTMET;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BimaxSettingsPanel();
		}
		
		
	},
	
	/** The spectral. */
	SPECTRAL{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.SPECTRAL;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new SpectralSettingsPanel();
		}
		
		
	},
	
	/** The xmotifs. */
	XMOTIFS{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.XMOTIFS;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new XmotifsSettingsPanel();
		}
		
		
	},
	
	/** The fabia. */
	FABIA{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.FABIA;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new FabiaSettingsPanel();
		}
		
	
	},
	
	/** The fabiap. */
	FABIAP{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.FABIAP;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new FabiaPSettingsPanel();
		}
		
	
	},
	
	/** The fabias. */
	FABIAS{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.FABIAS;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new FabiaSSettingsPanel();
		}
		
	
	},
	
	/** The isa. */
	ISA{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.ISA;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new ISASettingsPanel();
		}
		
	
	},
	
	/** The bicfinder. */
	BICFINDER{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BICFINDER;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BicFinderSettingsPanel();
		}
		
	
	},
	
	/** The bimineplus. */
	BIMINEPLUS{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BIMINEPLUS;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BiminplusSettingsPanel();
		}
		
	
	},
	
	/** The bicare. */
	BICARE{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BICARE;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BicareFlocSettingsPanel();
		}
	
	},
	
	/** The debi. */
	DEBI{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.DEBI;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new DebiSettingsPanel();
		}
		

	},
	
	/** The coalesce. */
	COALESCE{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.COALESCE;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new CoalesceSettingsPanel();
		}
		
	
	},
	
	/** The cpb. */
	CPB{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.CPB;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new CPBSettingsPanel();
		}
		

	},
	
	/** The qubic. */
	QUBIC{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.QUBIC;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new QubicSettingsPanel();
		}
		
	
	},
	
	/** The unibic. */
	UNIBIC{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.UNIBIC;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new UnibicSettingsPanel();
		}

	},
	
	/** The bbc. */
	BBC{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.BBC;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new BBCSettingsPanel();
		}
		

	},
	
	/** The ubclust. */
	UBCLUST{
		@Override
		public BiclusteringMethod getBiclusteringMethod() {
			return BiclusteringMethod.UBCLUST;
		}
		
		@Override
		public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
			return new UbclustSettingsPanel();
		}
		

	};
	
	public BiclusteringMethod getBiclusteringMethod() {
		return getBiclusteringMethod();
	}
	
	public AbstractMethodSettingsPanel getSettingsPanelOfMethod() {
		return getSettingsPanelOfMethod();
	}
	
	@Override
	public String toString() {
		return getBiclusteringMethod().getAlgorithmName();
	}
	
	
	

}
