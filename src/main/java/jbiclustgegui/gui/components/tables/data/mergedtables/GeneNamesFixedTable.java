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
package jbiclustgegui.gui.components.tables.data.mergedtables;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pt.ornrocha.swingutils.tables.models.TableModelWithColorColumnSwitchProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneNamesFixedTable.
 */
public class GeneNamesFixedTable extends JTable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The regulators. */
	private LinkedHashMap<String, Boolean> regulators;
	
	/** The target. */
	private LinkedHashMap<String, Boolean> target;
	
	/** The useregulatortargetscheme. */
	private boolean useregulatortargetscheme=false;
	
	/** The breaktag. */
	private static String BREAKTAG="<br>";
	
	/** The inittag. */
	private static String INITTAG="<html>";
	
	/** The endtag. */
	private static String ENDTAG="</html>";


	/**
	 * Instantiates a new gene names fixed table.
	 *
	 * @param geneids the geneids
	 */
	public GeneNamesFixedTable(ArrayList<String> geneids) {
		super(geneids.size(),1);
		getColumnModel().getColumn(0).setHeaderValue("Identifiers");
		setEnabled(false);
		setListOfGeneIdentifiers(geneids);
	}
	
	
	
	/**
	 * Sets the list of gene identifiers.
	 *
	 * @param geneids the new list of gene identifiers
	 */
	public void setListOfGeneIdentifiers(ArrayList<String> geneids){
		
		if(geneids!=null) {
			regulators=new LinkedHashMap<>();
			target=new LinkedHashMap<>();
			int n=0;
			for (String id : geneids) {
				getModel().setValueAt(id, n, 0);
				regulators.put(id, false);
				target.put(id, true);
				n++;
			}
		}
	}
	
	
	/**
	 * Define regulators.
	 *
	 * @param name the name
	 * @param isregulator the isregulator
	 */
	public void defineRegulators(String name, boolean isregulator) {
		if(regulators.containsKey(name)) {
			if(!useregulatortargetscheme)
				useregulatortargetscheme=true;
			regulators.put(name, isregulator);
		}
	}
	
	/**
	 * Define target.
	 *
	 * @param name the name
	 * @param istarget the istarget
	 */
	public void defineTarget(String name, boolean istarget) {
		if(target.containsKey(name))
			target.put(name,istarget);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
	 */
	@Override
	public String getToolTipText(MouseEvent e) {
		
    	String tip = null;
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        
       Object val= getModel().getValueAt(rowIndex, colIndex);
       String genename=(String) val;
       if(regulators!=null && regulators.containsKey(genename) && regulators.get(genename) && useregulatortargetscheme) {
    	   tip=INITTAG+"Identifier: "+genename+BREAKTAG+"Function: Defined as Regulator"+ENDTAG;
       }
       else if(target!=null && target.containsKey(genename)&& target.get(genename) && useregulatortargetscheme)
    	   tip=INITTAG+"Identifier: "+genename+BREAKTAG+"Function: Defined as Target"+ENDTAG;
       else
    	   tip=INITTAG+"Identifier: "+genename+ENDTAG; 
    	
    	return tip;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * The Class GeneNamesColorCellRenderer.
	 */
	public static class GeneNamesColorCellRenderer extends DefaultTableCellRenderer {


		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			TableModelWithColorColumnSwitchProperties model =  (TableModelWithColorColumnSwitchProperties) table.getModel();
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        Color color=model.getRowColorAt(row, column);
	        if(color!=null)
	        	c.setBackground(color);

	        return c;
	    }
	}
	
	
	
}
