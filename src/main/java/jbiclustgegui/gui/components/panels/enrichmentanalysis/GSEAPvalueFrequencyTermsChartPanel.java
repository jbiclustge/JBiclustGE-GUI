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

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.ToolTipType;

import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustgegui.gui.components.panels.chart.XChartPanelJBiclustGE;

import java.awt.Insets;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAPvalueFrequencyTermsChartPanel.
 */
public class GSEAPvalueFrequencyTermsChartPanel extends JPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The chart panel. */
	private ChartPanel chartPanel;
	
	/** The results. */
	private EnrichmentAnalysisResultList results;
	
	/** The termidfrequency. */
	private LinkedHashMap<String, Double> termidfrequency;
	
	/** The pvalue. */
	private double pvalue;
	//private CategoryChart chart;
	
	/**
	 * Instantiates a new GSEA pvalue frequency terms chart panel.
	 */
	public GSEAPvalueFrequencyTermsChartPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0,0.2};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(this.scrollPane, gbc_scrollPane);
		
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel.setLayout(gbl_panel);
	}

	
	/**
	 * Adds the results.
	 *
	 * @param results the results
	 * @param pvalue the pvalue
	 * @param useadjustedpvalues the useadjustedpvalues
	 */
	public void addResults(EnrichmentAnalysisResultList results,double pvalue,boolean useadjustedpvalues) {
		this.results=results.filterAndProcessResults(pvalue, useadjustedpvalues);
		termidfrequency=results.getTermidsFrequency();
		this.pvalue=pvalue;
		
		this.chartPanel = initChart();
		chartPanel.setMouseWheelEnabled(true);
		this.scrollPane.setViewportView(chartPanel);
		GridBagLayout gbl_panel_chart = new GridBagLayout();
		gbl_panel_chart.columnWidths = new int[]{1};
		gbl_panel_chart.rowHeights = new int[]{1};
		gbl_panel_chart.columnWeights = new double[]{1.0};
		gbl_panel_chart.rowWeights = new double[]{1.0};
		this.chartPanel.setLayout(gbl_panel_chart);

	}
	
	
	/**
	 * Inits the chart.
	 *
	 * @return the chart panel
	 */
	protected ChartPanel initChart() {

		ArrayList<String> yaxis=new ArrayList<>();
		ArrayList<Double> xaxis=new ArrayList<>();


		for (String name : termidfrequency.keySet()) {
			yaxis.add(results.gettermid2termname().get(name));
			xaxis.add(termidfrequency.get(name)*100);
		}

		DefaultCategoryDataset data=(DefaultCategoryDataset) getCategoryDataset(xaxis, yaxis);

		JFreeChart jFreeChart = ChartFactory.createBarChart("","GO Term Name","Go Term Frequencies (%) using p-value "+String.valueOf(pvalue), data, PlotOrientation.HORIZONTAL, false, false, false);

		CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot();
		Font font3 = new Font("Dialog", Font.PLAIN, 6); 
		categoryPlot.getDomainAxis().setTickLabelFont(font3);

		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		categoryAxis.setCategoryMargin(0.02);
		categoryAxis.setUpperMargin(0);
		categoryAxis.setLowerMargin(0);

		NumberAxis numberAxis = (NumberAxis)categoryPlot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberAxis.setUpperMargin(0.1);
		numberAxis.setRange(0.0, 100);

		BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
		barRenderer.setDrawBarOutline(false);
		GradientPaint gradientPaint = new GradientPaint(0.0f, 0.0f, Color.BLUE, 0.0f, 0.0f, new Color(0, 0, 64));
		barRenderer.setSeriesPaint(0, (Paint)gradientPaint);
		barRenderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator("{1} = {2}", (NumberFormat)new DecimalFormat("0")));

		if(yaxis.size()>50) {
			int height=yaxis.size()*10;
			int whidth=height/5;

			return new ChartPanel(jFreeChart,
					whidth, 
					height, 
					ChartPanel.DEFAULT_MINIMUM_DRAW_WIDTH,
					ChartPanel.DEFAULT_MINIMUM_DRAW_HEIGHT,
					whidth+200,
					height+200,
					ChartPanel.DEFAULT_BUFFER_USED,
					true, true, true, true, true);
		}
		else
			return new ChartPanel(jFreeChart);

	}
	
	

	
	
	/**
	 * Gets the category dataset.
	 *
	 * @param xaxis the xaxis
	 * @param yaxis the yaxis
	 * @return the category dataset
	 */
	protected CategoryDataset getCategoryDataset(ArrayList<Double> xaxis,ArrayList<String> yaxis) {
		
		DefaultCategoryDataset data=new DefaultCategoryDataset();
		for (int i = 0; i < xaxis.size(); i++) {
			double value=xaxis.get(i);
			String name=yaxis.get(i);
			data.addValue(value, (Comparable)((Object)""), name);
		}
		
		return data;
	}
	
	
	
	/**
	 * Update chart data.
	 *
	 * @param xaxis the xaxis
	 * @param yaxis the yaxis
	 */
	protected void updateChartData(ArrayList<Double> xaxis,ArrayList<String> yaxis) {
		
		DefaultCategoryDataset data=new DefaultCategoryDataset();
		for (int i = 0; i < xaxis.size(); i++) {
			double value=xaxis.get(i);
			String name=yaxis.get(i);
			data.addValue(value, (Comparable)((Object)""), name);
		}
		
		CategoryPlot plot=(CategoryPlot) chartPanel.getChart().getPlot();
		plot.setDataset(data);
		
	}
	
	
	
}
