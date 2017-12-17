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
package jbiclustgegui.gui.views.data;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FilenameUtils;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.gui.components.panels.chart.heatmap.ExpressionDatasetHeatmapJFreeChartPlotPanel;
import jbiclustgegui.gui.components.panels.data.DataMatrixPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionDatasetViewerPanel.
 */
public class ExpressionDatasetViewerPanel extends JPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The scroll paneviewdata. */
	private JScrollPane scrollPaneviewdata;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The panelnotes. */
	private JPanel panelnotes;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The text areanotes. */
	private JTextArea textAreanotes;
	
	/** The lbl dataset name. */
	private JLabel lblDatasetName;
	
	/** The text fielddatasetname. */
	private JTextField textFielddatasetname;
	
	/** The lbl loaded from. */
	private JLabel lblLoadedFrom;
	
	/** The text fieldloadedfrom. */
	private JTextField textFieldloadedfrom;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The lbl number of genes. */
	private JLabel lblNumberOfGenes;
	
	/** The lbl number of conditions. */
	private JLabel lblNumberOfConditions;
	
	/** The text fieldnumbergenes. */
	private JTextField textFieldnumbergenes;
	
	/** The text fieldnumberconditions. */
	private JTextField textFieldnumberconditions;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The btn new button 1. */
	private JButton btnNewButton_1;
	
	/** The data. */
	private GeneExpressionDatasetBox data;
	
	/** The expressiondatasetmatrixview. */
	private DataMatrixPanel expressiondatasetmatrixview;
	
	/** The expressiondatasetheatmapview. */
	private ExpressionDatasetHeatmapJFreeChartPlotPanel expressiondatasetheatmapview;
	
	
	
	/**
	 * Instantiates a new expression dataset viewer panel.
	 *
	 * @param data the data
	 */
	public ExpressionDatasetViewerPanel(GeneExpressionDatasetBox data) {
		this.data=data;
		initGUI();
		initComponents();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.1,1.0,0.05};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{0.5,0.5,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		this.panel.setLayout(gbl_panel);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		this.panel.add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{0.0,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.lblDatasetName = new JLabel("Dataset name:");
		GridBagConstraints gbc_lblDatasetName = new GridBagConstraints();
		gbc_lblDatasetName.anchor = GridBagConstraints.EAST;
		gbc_lblDatasetName.insets = new Insets(0, 40, 5, 5);
		gbc_lblDatasetName.gridx = 0;
		gbc_lblDatasetName.gridy = 0;
		this.panel_1.add(this.lblDatasetName, gbc_lblDatasetName);
		
		this.textFielddatasetname = new JTextField();
		GridBagConstraints gbc_textFielddatasetname = new GridBagConstraints();
		gbc_textFielddatasetname.insets = new Insets(0, 0, 5, 0);
		gbc_textFielddatasetname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFielddatasetname.gridx = 1;
		gbc_textFielddatasetname.gridy = 0;
		this.panel_1.add(this.textFielddatasetname, gbc_textFielddatasetname);
		this.textFielddatasetname.setColumns(10);
		
		this.lblLoadedFrom = new JLabel("Loaded from:");
		GridBagConstraints gbc_lblLoadedFrom = new GridBagConstraints();
		gbc_lblLoadedFrom.anchor = GridBagConstraints.EAST;
		gbc_lblLoadedFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoadedFrom.gridx = 0;
		gbc_lblLoadedFrom.gridy = 1;
		this.panel_1.add(this.lblLoadedFrom, gbc_lblLoadedFrom);
		
		this.textFieldloadedfrom = new JTextField();
		this.textFieldloadedfrom.setToolTipText(textFieldloadedfrom.getText());
		GridBagConstraints gbc_textFieldloadedfrom = new GridBagConstraints();
		gbc_textFieldloadedfrom.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldloadedfrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldloadedfrom.gridx = 1;
		gbc_textFieldloadedfrom.gridy = 1;
		this.panel_1.add(this.textFieldloadedfrom, gbc_textFieldloadedfrom);
		this.textFieldloadedfrom.setColumns(10);
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		this.panel_1.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1,1};
		gbl_panel_3.rowHeights = new int[]{1,1};
		gbl_panel_3.columnWeights = new double[]{1.0,1.0};
		gbl_panel_3.rowWeights = new double[]{1.0,1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.lblNumberOfGenes = new JLabel("Number of Genes");
		GridBagConstraints gbc_lblNumberOfGenes = new GridBagConstraints();
		gbc_lblNumberOfGenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfGenes.gridx = 0;
		gbc_lblNumberOfGenes.gridy = 0;
		this.panel_3.add(this.lblNumberOfGenes, gbc_lblNumberOfGenes);
		
		this.lblNumberOfConditions = new JLabel("Number of Conditions");
		GridBagConstraints gbc_lblNumberOfConditions = new GridBagConstraints();
		gbc_lblNumberOfConditions.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumberOfConditions.gridx = 1;
		gbc_lblNumberOfConditions.gridy = 0;
		this.panel_3.add(this.lblNumberOfConditions, gbc_lblNumberOfConditions);
		
		this.textFieldnumbergenes = new JTextField();
		GridBagConstraints gbc_textFieldnumbergenes = new GridBagConstraints();
		gbc_textFieldnumbergenes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldnumbergenes.gridx = 0;
		gbc_textFieldnumbergenes.gridy = 1;
		this.panel_3.add(this.textFieldnumbergenes, gbc_textFieldnumbergenes);
		this.textFieldnumbergenes.setColumns(10);
		
		this.textFieldnumberconditions = new JTextField();
		GridBagConstraints gbc_textFieldnumberconditions = new GridBagConstraints();
		gbc_textFieldnumberconditions.gridx = 1;
		gbc_textFieldnumberconditions.gridy = 1;
		this.panel_3.add(this.textFieldnumberconditions, gbc_textFieldnumberconditions);
		this.textFieldnumberconditions.setColumns(10);
		
		this.panelnotes = new JPanel();
		this.panelnotes.setBorder(new TitledBorder(null, "Notes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelnotes = new GridBagConstraints();
		gbc_panelnotes.gridwidth = 2;
		gbc_panelnotes.insets = new Insets(0, 0, 0, 5);
		gbc_panelnotes.fill = GridBagConstraints.BOTH;
		gbc_panelnotes.gridx = 2;
		gbc_panelnotes.gridy = 0;
		this.panel.add(this.panelnotes, gbc_panelnotes);
		GridBagLayout gbl_panelnotes = new GridBagLayout();
		gbl_panelnotes.columnWidths = new int[]{1};
		gbl_panelnotes.rowHeights = new int[]{1};
		gbl_panelnotes.columnWeights = new double[]{1.0};
		gbl_panelnotes.rowWeights = new double[]{1.0};
		this.panelnotes.setLayout(gbl_panelnotes);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		this.panelnotes.add(this.scrollPane, gbc_scrollPane);
		
		this.textAreanotes = new JTextArea();
		this.scrollPane.setViewportView(this.textAreanotes);
		
		/*this.scrollPaneviewdata = new JScrollPane();
		GridBagConstraints gbc_scrollPaneviewdata = new GridBagConstraints();
		gbc_scrollPaneviewdata.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneviewdata.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneviewdata.gridx = 0;
		gbc_scrollPaneviewdata.gridy = 1;
		add(this.scrollPaneviewdata, gbc_scrollPaneviewdata);*/
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1,1,1,1};
		gbl_panel_2.rowHeights = new int[]{1};
		gbl_panel_2.columnWeights = new double[]{1.0,1.0,1.0,1.0};
		gbl_panel_2.rowWeights = new double[]{1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.btnNewButton = new JButton("Show Dataset Matrix");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewDataMatrixTable();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		this.panel_2.add(this.btnNewButton, gbc_btnNewButton);
		
		this.btnNewButton_1 = new JButton("Show Dataset Heatmap");
		this.btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewHeatmapFigure();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		this.panel_2.add(this.btnNewButton_1, gbc_btnNewButton_1);
	}
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		
		ExpressionData dataset=data.getExpressionset();
		textFieldnumbergenes.setText(String.valueOf(dataset.numberGenes()));
		textFieldnumberconditions.setText(String.valueOf(dataset.numberConditions()));
		textFieldnumbergenes.setEditable(false);
		textFieldnumberconditions.setEditable(false);
		
		if(data.getExpressionDatasetfilepath()!=null) {
			textFielddatasetname.setText(FilenameUtils.getBaseName(data.getExpressionDatasetfilepath()));
			textFieldloadedfrom.setText(data.getExpressionDatasetfilepath());
		}
		else {
			textFielddatasetname.setText("Info. is not available");
			textFieldloadedfrom.setText("Info. is not available");
		}
		
		if(data.getNotes()!=null && data.getNotes().length()>0) {
			textAreanotes.setText(data.getNotes());
		}
		
		viewDataMatrixTable();
		
	}
	
	
	/**
	 * View data matrix table.
	 */
	private void viewDataMatrixTable() {
		ExpressionData dataset=data.getExpressionset();
		if(expressiondatasetmatrixview==null) {
			expressiondatasetmatrixview=new DataMatrixPanel();
			GridBagConstraints gbc_viewdata = new GridBagConstraints();
			gbc_viewdata.insets = new Insets(0, 0, 5, 0);
			gbc_viewdata.fill = GridBagConstraints.BOTH;
			gbc_viewdata.gridx = 0;
			gbc_viewdata.gridy = 1;
			add(expressiondatasetmatrixview, gbc_viewdata);
			
			expressiondatasetmatrixview.setData(dataset.getGeneNamesList(), dataset.getConditionsList(), dataset.getexpressionDataMatrix(), true);
			if(dataset.numberConditions()>1500 || dataset.numberGenes()>1500)
				expressiondatasetmatrixview.showProgressBar();
			
			updateUI();
		}
		else {
			this.remove(expressiondatasetheatmapview);
			GridBagConstraints gbc_viewdata = new GridBagConstraints();
			gbc_viewdata.insets = new Insets(0, 0, 5, 0);
			gbc_viewdata.fill = GridBagConstraints.BOTH;
			gbc_viewdata.gridx = 0;
			gbc_viewdata.gridy = 1;
			add(expressiondatasetmatrixview, gbc_viewdata);
			updateUI();
		}
			
		
	}
	
	/**
	 * View heatmap figure.
	 */
	private void viewHeatmapFigure() {
		
		if(expressiondatasetheatmapview==null) {
			this.remove(expressiondatasetmatrixview);
			expressiondatasetheatmapview=new ExpressionDatasetHeatmapJFreeChartPlotPanel();
			GridBagConstraints gbc_viewdata = new GridBagConstraints();
			gbc_viewdata.insets = new Insets(0, 0, 5, 0);
			gbc_viewdata.fill = GridBagConstraints.BOTH;
			gbc_viewdata.gridx = 0;
			gbc_viewdata.gridy = 1;
			add(expressiondatasetheatmapview, gbc_viewdata);
			
			expressiondatasetheatmapview.loadExpressionHeatMap(data.getExpressionset());
			updateUI();
		}
		else {
			this.remove(expressiondatasetmatrixview);
			GridBagConstraints gbc_viewdata = new GridBagConstraints();
			gbc_viewdata.insets = new Insets(0, 0, 5, 0);
			gbc_viewdata.fill = GridBagConstraints.BOTH;
			gbc_viewdata.gridx = 0;
			gbc_viewdata.gridy = 1;
			add(expressiondatasetheatmapview, gbc_viewdata);
			updateUI();
		}
	}

}
