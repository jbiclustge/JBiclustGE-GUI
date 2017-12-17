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

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;

// TODO: Auto-generated Javadoc
/**
 * The Class DataMatrixMainTable.
 */
public class DataMatrixMainTable extends JTable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The genes. */
	protected ArrayList<String> genes=null;
	
	/** The conditions. */
	protected ArrayList<String> conditions=null;
	
	/** The column tool tips. */
	private String[] columnToolTips=null;

	
	/**
	 * Instantiates a new data matrix main table.
	 *
	 * @param genes the genes
	 * @param conditions the conditions
	 */
	public DataMatrixMainTable(ArrayList<String> genes, ArrayList<String> conditions) {
		super();
		
		setModel(new DefaultTableModel(genes.size(), conditions.size()));
		columnToolTips=conditions.toArray(new String[conditions.size()]);
		
		for (int i = 0; i < conditions.size(); i++) {
			getColumnModel().getColumn(i).setHeaderValue(conditions.get(i));
		}
		this.genes=genes;
		this.conditions=conditions;
	}
	
	
	/**
	 * Instantiates a new data matrix main table.
	 *
	 * @param genes the genes
	 * @param conditions the conditions
	 * @param weightmatrix the weightmatrix
	 */
	public DataMatrixMainTable(ArrayList<String> genes, ArrayList<String> conditions,double [][] weightmatrix) {
		this(genes,conditions);
		this.setDefaultRenderer(Object.class,new ColorMatrixCellRenderer(weightmatrix));
	}
	
	/**
	 * Instantiates a new data matrix main table.
	 *
	 * @param genes the genes
	 * @param conditions the conditions
	 * @param weightmatrix the weightmatrix
	 * @param mincutoff the mincutoff
	 * @param meancutoff the meancutoff
	 * @param maxcutoff the maxcutoff
	 */
	public DataMatrixMainTable(ArrayList<String> genes, ArrayList<String> conditions,double [][] weightmatrix,double mincutoff,double meancutoff,double maxcutoff) {
		this(genes,conditions);
		this.setDefaultRenderer(Object.class,new ColorMatrixCellRenderer(weightmatrix,mincutoff,meancutoff,maxcutoff));
	}

	/* (non-Javadoc)
	 * @see javax.swing.JTable#createDefaultTableHeader()
	 */
	@Override
	protected JTableHeader createDefaultTableHeader() {
		
		return new JTableHeader(columnModel) {
			
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int index = columnModel.getColumnIndexAtX(p.x);
                int realIndex = columnModel.getColumn(index).getModelIndex();
                if(columnToolTips!=null)
                	tip=columnToolTips[realIndex];
                return tip;
            }
        };
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

       if(val instanceof Double)
    	   tip=String.format("%.4f", val);
       else if(val instanceof Integer)
    	   tip=String.format("%d", val);
       else if(! (val instanceof String))
    	   tip=String.valueOf(val);
       else
    	   tip=(String) val;
    	
    	return "("+genes.get(rowIndex)+","+conditions.get(colIndex)+") = "+tip;
	}
	
	
	/**
	 * The Class ColorMatrixCellRenderer.
	 */
	private static class ColorMatrixCellRenderer extends DefaultTableCellRenderer {

        
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The weightmatrix. */
		private double [][] weightmatrix=null;
		
		/** The mincutoff. */
		private double mincutoff=0.0;
		
		/** The meancutoff. */
		private double meancutoff=0.5;
		
		/** The maxcutoff. */
		private double maxcutoff=1.0;
		
		/**
		 * Instantiates a new color matrix cell renderer.
		 *
		 * @param weightmatrix the weightmatrix
		 */
		public ColorMatrixCellRenderer(double [][] weightmatrix) {
			this.weightmatrix=weightmatrix;
		}
		
		/**
		 * Instantiates a new color matrix cell renderer.
		 *
		 * @param weightmatrix the weightmatrix
		 * @param mincutoff the mincutoff
		 * @param meancutoff the meancutoff
		 * @param maxcutoff the maxcutoff
		 */
		public ColorMatrixCellRenderer(double [][] weightmatrix, double mincutoff,double meancutoff,double maxcutoff) {
			this(weightmatrix);
			this.mincutoff=mincutoff;
			this.meancutoff=meancutoff;
			this.maxcutoff=maxcutoff;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        //System.out.println("Value: "+value+" class: "+value.getClass());
	        if(weightmatrix!=null) {
	        	if(value instanceof Double) {
	        		double nval=weightmatrix[row][column];

	        		Color color=Color.white;
	        		if(nval<=meancutoff && nval>=mincutoff)
	        			color=new Color((float) 0.0,
	        					(float) (1 - 2 * nval),
	        					(float) 0.0);
	        		else if(nval>meancutoff && nval<=maxcutoff)
	        			color=new Color(
	        					(float) (2 * nval - 1),
	        					(float) 0.0, (float) 0.0);
	        		else
	        			LogMessageCenter.getLogger().toClass(getClass()).addWarnMessage("Value in index["+row+","+column+"]: "+String.valueOf(value)+" is out of range");
	        
	        			if(color!=null)
	        				c.setBackground(color);
	        	}
	        }
	        return c;
	        
	    }
	}
	
	

}
