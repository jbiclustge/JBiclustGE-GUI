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
package jbiclustgegui.gui.components.tables.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import es.uvigo.ei.aibench.workbench.Workbench;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import pt.ornrocha.excelutils.ExcelVersion;
import pt.ornrocha.excelutils.MTUExcelWriterUtils;
import pt.ornrocha.ioutils.writers.MTUWriterUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.swingutils.jfilechooser.filefilters.ExtensionFileFilter;
import pt.ornrocha.swingutils.tables.models.TableModelWithColorColumnSwitchProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class InformationTable.
 */
public class InformationTable extends JTable implements MouseListener, ActionListener{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The filter header. */
	protected TableFilterHeader filterHeader;
	
	/** The tablemodel. */
	protected TableModelWithColorColumnSwitchProperties tablemodel;
	
	/** The pop menu. */
	protected JPopupMenu popMenu = new JPopupMenu();
	
	/** The export. */
	protected JMenuItem export;
	
	/** The savetable. */
	public static String SAVETABLE="savetable";
	
	/**
	 * Instantiates a new information table.
	 *
	 * @param columnames the columnames
	 */
	public InformationTable(String[] columnames) {
		tablemodel=new TableModelWithColorColumnSwitchProperties(columnames, false);
		setModel(tablemodel);
		setPopMenu();
		addMouseListener(this);
		this.setDefaultRenderer(String.class, tablemodel.getNewColorCellRendererInstance());
		this.setDefaultRenderer(Double.class, tablemodel.getNewColorCellRendererInstance());
		this.setDefaultRenderer(Integer.class, tablemodel.getNewColorCellRendererInstance());
		this.filterHeader=new TableFilterHeader(this, AutoChoices.ENABLED);
		filterHeader.setBackground(Color.lightGray);
		
	}
	
	/**
	 * Sets the pop menu.
	 */
	protected void setPopMenu(){
		export=new JMenuItem("Export table");
		export.setActionCommand(SAVETABLE);
		export.addActionListener(this);
		popMenu.add(export);
		popMenu.setInvoker(this);
		setComponentPopupMenu(popMenu);
	}
	
	/**
	 * Pop menu.
	 *
	 * @param event the event
	 */
	protected void popMenu(MouseEvent event) {
	    popMenu.show(event.getComponent(), event.getX(), event.getY());
	}
	
	/**
	 * Sets the color scheme.
	 *
	 * @param stringcolums the stringcolums
	 * @param booldecisioncolumn the booldecisioncolumn
	 * @param coloriftrue the coloriftrue
	 * @param coloriffalse the coloriffalse
	 */
	/*
	 * Decision Column must be a column of boolean values
	 */
	public void setColorScheme(int[] stringcolums, int booldecisioncolumn, Color coloriftrue, Color coloriffalse) {
		
		for (int i = 0; i < stringcolums.length; i++) {
			tablemodel.addColumnColorSwitch(stringcolums[i], booldecisioncolumn, coloriftrue, coloriffalse);
		}
		
	}
	
	/**
	 * Sets the data.
	 *
	 * @param listobj the new data
	 */
	public void setData(List<Object[]> listobj) {
		clearTable();
		for (int i = 0; i < listobj.size(); i++) {
			tablemodel.addRow(listobj.get(i));
		}
	}
	
	/**
	 * Adds the row.
	 *
	 * @param row the row
	 */
	public void addRow(Object[] row) {
		tablemodel.addRow(row);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		tablemodel.setValueAt(value, rowIndex, columnIndex);
	}
	
	
	/**
	 * Clear table.
	 */
	public void clearTable(){
		tablemodel.resetTable();
	}
	
	/**
	 * Sets the editable column positions.
	 *
	 * @param indexes the new editable column positions
	 */
	public void setEditableColumnPositions(int[]indexes) {
		tablemodel.setEditableColumnPositions(indexes);
	}
	
	/**
	 * Append data.
	 *
	 * @param row the row
	 */
	public void appendData(Object[] row) {
		tablemodel.addRow(row);
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
	
	/* (non-Javadoc)
	 * @see javax.swing.JTable#getModel()
	 */
	@Override
	 public TableModel getModel() {
	        return tablemodel;
	    }



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	/*	if (e.isPopupTrigger()) {
			popMenu(e);
		}*/
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
			popMenu(e);
		}

	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
       String cmd=e.getActionCommand();
       
       if(cmd.equals(SAVETABLE))
		  saveTable();
	}
	
	/**
	 * Save table.
	 */
	protected void saveTable() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Comma-separated values","csv"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Excel", "xlsx"));
		int tag=fileChooser.showSaveDialog(this);
		
		if(tag==JFileChooser.APPROVE_OPTION) {
			ExtensionFileFilter filter=(ExtensionFileFilter) fileChooser.getFileFilter();
			try {
				if(filter.getExtension().equals("csv"))
					saveToCSV(fileChooser.getSelectedFile().getAbsolutePath());
				else
					saveToExcel(fileChooser.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
					LogMessageCenter.getLogger().addCriticalErrorMessage("Error in saving file", e);
					Workbench.getInstance().error("Error in saving file");
				}
		}
	}
	
	/**
	 * Save to CSV.
	 *
	 * @param filename the filename
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void saveToCSV(String filename) throws IOException {
		
		StringBuilder str=new StringBuilder();
		
		String[] header=tablemodel.getColumnNames();
		
		for (int i = 0; i < header.length; i++) {
			str.append(header[i]);
			if(i<(header.length-1))
				str.append("\t");
		}
		str.append("\n");
		
		for (int i = 0; i < getRowCount(); i++) {
			Object[] row=tablemodel.getRowAt(i);
			
			for (int j = 0; j < row.length; j++) {
				str.append(String.valueOf(row[j]));	
				if(j<(row.length-1))
					str.append("\t");
			}
			str.append("\n");
			
		}
		
		String filepath=filename+".csv";
		MTUWriterUtils.writeDataTofile(filepath, str.toString());
		
	}
	
	
	/**
	 * Save to excel.
	 *
	 * @param filepath the filepath
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void saveToExcel(String filepath) throws IOException {
		
		ArrayList<ArrayList<Object>> intputdata=new ArrayList<>();
		
		ArrayList<Object> headerexcel=new ArrayList<>();
		String[] header=tablemodel.getColumnNames();

		for (int i = 0; i < header.length; i++) {
			headerexcel.add(header[i]);
		}
		intputdata.add(headerexcel);
		
		for (int i = 0; i < getRowCount(); i++) {
			ArrayList<Object> rowexcel=new ArrayList<>();
			Object[] row=tablemodel.getRowAt(i);
			for (int j = 0; j < row.length; j++) {
				rowexcel.add(row[j]);
			}
			intputdata.add(rowexcel);
		}
		
		//String filepathh=ExcelVersion.XLSX.getFileNameWithExtension(filepath);
		MTUExcelWriterUtils.WriteDataToNewExcelFile(filepath, ExcelVersion.XLSX, intputdata, "Exported Data");
	}

}
