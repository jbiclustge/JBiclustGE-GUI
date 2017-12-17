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
package jbiclustgegui.gui.components.tables.biclusters;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;

import jbiclustgegui.gui.components.panels.enrichmentanalysis.FilteredBiclusterGSEAResultContainer;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import pt.ornrocha.stringutils.MTUStringFormat;
import pt.ornrocha.swingutils.tables.models.GenericTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusterGSEAInfoTable.
 */
public class BiclusterGSEAInfoTable extends JTable{

	
	/** The tablemodel. */
	private GenericTableModel tablemodel;
	
	/** The filter header. */
	private TableFilterHeader filterHeader;
	
	/**
	 * Instantiates a new bicluster GSEA info table.
	 */
	public BiclusterGSEAInfoTable() {
		tablemodel=new GenericTableModel(new String[] {"Term ID", "Term Name","p-value","adjusted p-value"}, false);
		setModel(tablemodel);
		this.filterHeader=new TableFilterHeader(this, AutoChoices.ENABLED);
		filterHeader.setBackground(Color.lightGray);
	}
	 
	/**
	 * Adds the filtered bicluster GSEA result container.
	 *
	 * @param results the results
	 */
	public void addFilteredBiclusterGSEAResultContainer(FilteredBiclusterGSEAResultContainer results) {
		
		ArrayList<String> termids=results.getFilteredgoterms();
	
		tablemodel.resetTable();
		for (int i = 0; i < termids.size(); i++) {
			String termid=termids.get(i);
			Object[] row=new Object[] {termid,
					results.getGoterm2termName(termid),
					MTUStringFormat.formatDoubleToStringWithExponentialIfLowerThan(results.getGoterm2pvalue(termid), 0.0001, "0.000E000") ,
					results.getGoterm2adjustedpvalue(termid)==null?"":MTUStringFormat.formatDoubleToStringWithExponentialIfLowerThan(results.getGoterm2adjustedpvalue(termid), 0.0001, "0.000E000")};
			tablemodel.addRow(row);
		}
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
        
       Object val= tablemodel.getValueAt(rowIndex, colIndex);
       if(!(val instanceof String))
    	   tip=String.valueOf(val);
       else
    	   tip=(String) val;
    	
    	return tip;
	}
	
	
}
