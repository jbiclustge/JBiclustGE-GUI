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
package jbiclustgegui.gui.components.panels.enrichmentanalysis;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.math3.util.Precision;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.ChartTheme;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustge.utils.props.JBiGePropertiesManager;
import jbiclustgegui.gui.components.panels.chart.XChartPanelJBiclustGE;
import pt.ornrocha.stringutils.MTUStringFormat;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAInfoPercentageEnrichedBiclusters.
 */
public class GSEAInfoPercentageEnrichedBiclusters extends JPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The lbl pvalue. */
	private JLabel lblPvalue;
	
	/** The btn add. */
	private JButton btnAdd;
	
	/** The double text field. */
	private DoubleTextField doubleTextField;
	
	/** The btn remove selected. */
	private JButton btnRemoveSelected;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The panel chart. */
	private XChartPanelJBiclustGE panel_chart;
	
	/** The listmodel. */
	private DefaultListModel listmodel;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The list. */
	private JList list;
	
	/** The results. */
	private EnrichmentAnalysisResultList results;
	
	/** The chckbx use adjusted pvalues. */
	private JCheckBox chckbxUseAdjustedPvalues;
	
	/** The allowedpvalue. */
	private Double allowedpvalue=null;
	
	/** The btn new button 2. */
	private JButton btnNewButton_2;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The currentvalues. */
	private LinkedHashMap<Double, Number> currentvalues=null;
	
	/** The chart. */
	private CategoryChart chart;
	
	/** The btn save. */
	private JButton btnSave;
	
	/** The btn invert xaxis. */
	private JButton btnInvertXaxis;
	
	/** The isinverted. */
	private boolean isinverted=false;

	/**
	 * Instantiates a new GSEA info percentage enriched biclusters.
	 */
	public GSEAInfoPercentageEnrichedBiclusters() {
		initGUI();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,0.0};
		gridBagLayout.rowWeights = new double[]{0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 10;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{0.0,0.0,0.0};
		this.panel.setLayout(gbl_panel);
		
		this.lblPvalue = new JLabel("p-value:");
		GridBagConstraints gbc_lblPvalue = new GridBagConstraints();
		gbc_lblPvalue.anchor = GridBagConstraints.EAST;
		gbc_lblPvalue.insets = new Insets(0, 0, 5, 5);
		gbc_lblPvalue.gridx = 0;
		gbc_lblPvalue.gridy = 0;
		this.panel.add(this.lblPvalue, gbc_lblPvalue);
		
		this.doubleTextField = new DoubleTextField();
		this.doubleTextField.setText("0.05");
		GridBagConstraints gbc_doubleTextField = new GridBagConstraints();
		gbc_doubleTextField.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextField.gridx = 1;
		gbc_doubleTextField.gridy = 0;
		this.panel.add(this.doubleTextField, gbc_doubleTextField);
		
		
		
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		this.panel.add(this.scrollPane, gbc_scrollPane);
		
		listmodel = new DefaultListModel();
		this.list = new JList(listmodel);
		this.scrollPane.setViewportView(this.list);
		
		this.chckbxUseAdjustedPvalues = new JCheckBox("use adjusted p-values");
		GridBagConstraints gbc_chckbxUseAdjustedPvalues = new GridBagConstraints();
		gbc_chckbxUseAdjustedPvalues.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUseAdjustedPvalues.gridx = 4;
		gbc_chckbxUseAdjustedPvalues.gridy = 0;
		this.panel.add(this.chckbxUseAdjustedPvalues, gbc_chckbxUseAdjustedPvalues);
		
		this.btnNewButton = new JButton("Create plot");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listmodel.getSize()>0)
				  panel_chart.addChart(getChart());
				
			}
		});
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		this.panel.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1,1,1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0,1.0,1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.btnAdd = new JButton("Add >>");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.insets = new Insets(0, 40, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		this.panel_3.add(this.btnAdd, gbc_btnAdd);
		
		this.btnNewButton_2 = new JButton("<html><center>Add default<br>p-values</center></html>");
		this.btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Double> defaultlist=new ArrayList<>();
				defaultlist.add(0.05);
				defaultlist.add(0.01);
				defaultlist.add(0.001);
				defaultlist.add(0.0001);
				defaultlist.add(0.00001);
				defaultlist.add(0.000001);
			
				for (int i = 0; i < defaultlist.size(); i++) {
					if(defaultlist.get(i)<=getAllowedPvalue() && !listmodel.contains(defaultlist.get(i)) )
						listmodel.addElement(defaultlist.get(i));
				}
				
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.insets = new Insets(0, 40, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		this.panel_3.add(this.btnNewButton_2, gbc_btnNewButton_2);
		this.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!doubleTextField.getText().isEmpty()) {
					double value=Double.parseDouble(doubleTextField.getText());
					if(!listmodel.contains(value))
						listmodel.addElement(value);
				}
				
				
				
			}
		});
	
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 1;
		this.panel.add(this.btnNewButton, gbc_btnNewButton);
		
		this.btnRemoveSelected = new JButton("Remove selected");
		this.btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getModel().getSize()>0) {
					int row=list.getSelectedIndex();
				    listmodel.removeElementAt(row);
				
				}
				
				
			}
		});
		GridBagConstraints gbc_btnRemoveSelected = new GridBagConstraints();
		gbc_btnRemoveSelected.gridwidth = 2;
		gbc_btnRemoveSelected.anchor = GridBagConstraints.NORTH;
		gbc_btnRemoveSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveSelected.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveSelected.gridx = 2;
		gbc_btnRemoveSelected.gridy = 2;
		this.panel.add(this.btnRemoveSelected, gbc_btnRemoveSelected);
	
		
		this.panel_chart = new XChartPanelJBiclustGE();
		GridBagConstraints gbc_panel_chart = new GridBagConstraints();
		gbc_panel_chart.gridheight = 10;
		gbc_panel_chart.gridwidth = 9;
		gbc_panel_chart.insets = new Insets(0, 0, 5, 5);
		gbc_panel_chart.fill = GridBagConstraints.BOTH;
		gbc_panel_chart.gridx = 0;
		gbc_panel_chart.gridy = 2;
		add(this.panel_chart, gbc_panel_chart);
		
		
		
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 10;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 9;
		gbc_panel_1.gridy = 2;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{0.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.btnSave = new JButton("Save");
		this.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chart!=null)
					panel_chart.showSaveAsDialog();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 0;
		this.panel_1.add(this.btnSave, gbc_btnSave);
		
		this.btnInvertXaxis = new JButton("Invert xAxis");
		this.btnInvertXaxis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invertOrderXaxis();
				
			}
		});
		GridBagConstraints gbc_btnInvertXaxis = new GridBagConstraints();
		gbc_btnInvertXaxis.insets = new Insets(0, 0, 5, 0);
		gbc_btnInvertXaxis.gridx = 0;
		gbc_btnInvertXaxis.gridy = 1;
		this.panel_1.add(this.btnInvertXaxis, gbc_btnInvertXaxis);
	}
	
	
	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(EnrichmentAnalysisResultList results) {
		this.results=results;
		if(results.wasUsedMCTMethod())
			chckbxUseAdjustedPvalues.setSelected(true);
		else {
			chckbxUseAdjustedPvalues.setEnabled(false);
		}
	}
	
	
	
	/**
	 * Gets the chart.
	 *
	 * @return the chart
	 */
	public Chart getChart() {
		
		
		LinkedHashMap<String, Number> percenrichedbics=getPercentageEnrichedBiclusters();
		chart =new CategoryChartBuilder().width(800).height(600).xAxisTitle("p-value").yAxisTitle("Percentage of enriched biclusters (%)").theme(ChartTheme.GGPlot2).build();
		
		
		ArrayList<String> xaxis=new ArrayList<>();
		ArrayList<Number> yaxis=new ArrayList<>();
		
		for (String pv : percenrichedbics.keySet()) {
			xaxis.add(pv);
			yaxis.add(percenrichedbics.get(pv));
		}
		
		chart.addSeries("% enrichment", xaxis, yaxis);
	    chart.getStyler().setLegendVisible(false);
	    chart.getStyler().setYAxisMin(0.0);
	    chart.getStyler().setYAxisMax(100.0);
	    chart.getStyler().setAvailableSpaceFill(0.5);
	    chart.getStyler().setSeriesColors(new Color[] {Color.BLUE});
	    
		return chart;
	}
	
	/**
	 * Invert order xaxis.
	 */
	protected void invertOrderXaxis() {
		
		if(chart!=null) {
			ArrayList<Double> values=new ArrayList<>(currentvalues.keySet());
		
			if(!isinverted) {
				Collections.sort(values,Collections.reverseOrder());
				isinverted=true;
			}
			else {
				Collections.sort(values);
				isinverted=false;
			}
		
			ArrayList<String> xaxis=new ArrayList<>();
			ArrayList<Number> yaxis=new ArrayList<>();
		
		
		
			for (int i = 0; i < values.size(); i++) {
				xaxis.add(MTUStringFormat.formatDoubleToStringWithExponentialIfLowerThan(values.get(i), 0.0001, "0.0E0"));
				yaxis.add(currentvalues.get(values.get(i)));
			}
		
			chart.updateCategorySeries("% enrichment", xaxis, yaxis, null);
			panel_chart.updateUI();
		}
	}
	
	
	
	/**
	 * Gets the percentage enriched biclusters.
	 *
	 * @return the percentage enriched biclusters
	 */
	protected LinkedHashMap<String, Number> getPercentageEnrichedBiclusters(){
		LinkedHashMap<String, Number> res=new LinkedHashMap<>();
		currentvalues=new LinkedHashMap<>();
		
		if(listmodel.getSize()>0) {
			
			ArrayList<Double> values=new ArrayList<>();
			for (int i = 0; i < listmodel.getSize(); i++) {
				values.add((Double) listmodel.get(i));
			}
			
			Collections.sort(values);
			
			
			for (int i = 0; i < values.size(); i++) {
			      double pvalue=values.get(i);
			      
			      if(pvalue<=getAllowedPvalue()) {
			    	  
			    	  EnrichmentAnalysisResultList l=results.filterAndProcessResults(pvalue, chckbxUseAdjustedPvalues.isSelected());
			    	  double percEnrBicl=Precision.round(l.getPercentageEnrichedBiclusters(),2);
			    	  
			    	  res.put(MTUStringFormat.formatDoubleToStringWithExponentialIfLowerThan(pvalue, 0.0001, "0.0E0"),percEnrBicl);
			    	  currentvalues.put(pvalue, percEnrBicl);
			      }
			}
		
		}
		
		
		return res;
	}
	
	/**
	 * Gets the allowed pvalue.
	 *
	 * @return the allowed pvalue
	 */
	public Double getAllowedPvalue() {
		
		if(allowedpvalue!=null)
			return allowedpvalue;
		else {
			double val=0.05;
		
			Object value=JBiGePropertiesManager.getManager().getKeyValue("max_p_value");
			if(value instanceof Double) {
				val=(double) value;
				allowedpvalue=val;
				return allowedpvalue;
			}
			else if(value instanceof String) {
				try {
					val=Double.parseDouble((String) value);
					allowedpvalue=val;
					return allowedpvalue;
				} catch (Exception e) {
					Workbench.getInstance().error("Invalid value for p-value in the properties of JBiclustGE");
				}
			
			}
			else
				Workbench.getInstance().error("Invalid value for p-value in the properties of JBiclustGE");
			}
		return null;
	}

}
