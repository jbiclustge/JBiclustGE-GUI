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
package jbiclustgegui.gui.components.tables.data;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pt.ornrocha.swingutils.tables.models.TableModelWithColorColumnSwitchProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionDataMainTable.
 */
public class ExpressionDataMainTable extends JTable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/*public ExpressionDataMainTable(int numbergenes, ArrayList<String> conditionnames) {
		super(numbergenes, conditionnames.size());
		
		for (int i = 0; i < conditionnames.size(); i++) {
			String id=conditionnames.get(i);
			getColumnModel().getColumn(i).setHeaderValue(conditionnames.get(i));
		}
	}*/
	
	
	/**
	 * Instantiates a new expression data main table.
	 *
	 * @param numbergenes the numbergenes
	 * @param conditionnames the conditionnames
	 * @param matrixnorm the matrixnorm
	 */
	public ExpressionDataMainTable(int numbergenes, ArrayList<String> conditionnames,double [][] matrixnorm) {
		super(numbergenes, conditionnames.size());
		
		
		this.setDefaultRenderer(Object.class,new ColorGeneCellRenderer(matrixnorm));
		
		for (int i = 0; i < conditionnames.size(); i++) {
			String id=conditionnames.get(i);
			getColumnModel().getColumn(i).setHeaderValue(conditionnames.get(i));
		}
	}

	
	
	/**
	 * Adds the data.
	 *
	 * @param matrix the matrix
	 */
	public void addData(double[][] matrix) {
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				getModel().setValueAt(matrix[i][j], i, j);
			}
		}	
	}
	
	/**
	 * Append data.
	 *
	 * @param value the value
	 * @param row the row
	 * @param column the column
	 */
	public void appendData(Object value, int row, int column) {
		getModel().setValueAt(value, row, column);
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
       if(!(val instanceof String))
    	   tip=String.valueOf(val);
       else
    	   tip=(String) val;
    	
    	return "Value: "+tip;
	}
	
	
	/**
	 * The Class ColorGeneCellRenderer.
	 */
	private static class ColorGeneCellRenderer extends DefaultTableCellRenderer {

        
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The matrixnorm. */
		private double [][] matrixnorm=null;
		
		/**
		 * Instantiates a new color gene cell renderer.
		 *
		 * @param matrixnorm the matrixnorm
		 */
		public ColorGeneCellRenderer(double [][] matrixnorm) {
			this.matrixnorm=matrixnorm;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			//TableModelWithColorColumnSwitchProperties model =  (TableModelWithColorColumnSwitchProperties) table.getModel();
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        //System.out.println("Value: "+value+" class: "+value.getClass());
	        if(value instanceof Double) {
	        	double nval=matrixnorm[row][column];

	        	Color color=Color.white;
	        	if(nval<=0.5 && nval>=0)
	        		color=new Color((float) 0.0,
	        				(float) (1 - 2 * nval),
	        				(float) 0.0);
	        	else if(nval>0.5 && nval<=1.0)
	        		color=new Color(
	        				(float) (2 * nval - 1),
	        				(float) 0.0, (float) 0.0);
	        	else
	        		System.out.println("Value: "+nval);
	        
	        	if(color!=null)
	        		c.setBackground(color);
	        }
	        return c;
	        
	    }
	}
	
	

}
