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
package jbiclustgegui.gui.components.panels.chart.heatmap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.javatuples.Pair;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustge.results.biclusters.containers.BiclusterResult;
import jbiclustgegui.gui.components.jfreechart.HeatmapGreenRedColorPaintScale;
import jbiclustgegui.gui.components.jfreechart.HeatmapToolTipGenerator;
import jbiclustgegui.gui.components.jfreechart.XYZArrayDataset;
import jbiclustgegui.gui.components.panels.data.DataMatrixPanel;
import pt.ornrocha.arrays.MTUMatrixUtils;
import smile.imputation.MissingValueImputationException;
import smile.plot.Heatmap;
import smile.plot.PlotCanvas;
import smile.plot.PlotPanel;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionDatasetHeatmapJFreeChartPlotPanel.
 */
public class ExpressionDatasetHeatmapJFreeChartPlotPanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The panel. */
	private JPanel panel;
	
	/** The scroll paneplotpanel. */
	private JScrollPane scrollPaneplotpanel;
	
	/** The label. */
	private JLabel label;
	
	/** The text fieldbicnumber. */
	private JTextField textFieldbicnumber;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The dataset. */
	private ExpressionData dataset;
	
	//private boolean fixsize=true;
	
	
	/**
	 * Instantiates a new expression dataset heatmap J free chart plot panel.
	 */
	public ExpressionDatasetHeatmapJFreeChartPlotPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{1.0,0.05};
		gridBagLayout.rowWeights = new double[]{0.01,1.0};
		setLayout(gridBagLayout);
		
		/*this.label = new JLabel("Bicluster:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		this.panel_2.add(this.label, gbc_label);*/
		
		this.textFieldbicnumber = new JTextField();
		/*this.textFieldbicnumber.setColumns(10);
		GridBagConstraints gbc_textFieldbicnumber = new GridBagConstraints();
		gbc_textFieldbicnumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldbicnumber.gridx = 1;
		gbc_textFieldbicnumber.gridy = 0;
		this.panel_2.add(this.textFieldbicnumber, gbc_textFieldbicnumber);*/
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		/*this.btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		this.panel.add(this.btnNewButton, gbc_btnNewButton);*/
		
		this.scrollPaneplotpanel = new JScrollPane();
		
		GridBagConstraints gbc_scrollPaneplotpanel = new GridBagConstraints();
		gbc_scrollPaneplotpanel.gridheight = 2;
		gbc_scrollPaneplotpanel.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPaneplotpanel.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneplotpanel.gridx = 0;
		gbc_scrollPaneplotpanel.gridy = 0;
		add(this.scrollPaneplotpanel, gbc_scrollPaneplotpanel);
	}

	
	/**
	 * Load expression heat map.
	 *
	 * @param dataset the dataset
	 */
	public void loadExpressionHeatMap(ExpressionData dataset) {
		this.dataset=dataset;
		loadHeatmapPanel();
	}
	
	
	/**
	 * Load heatmap panel.
	 */
	public void loadHeatmapPanel() {
	
		SymbolAxis xAxis=new SymbolAxis("Conditions", dataset.getConditions());
        xAxis.setVisible(true);
		xAxis.setAxisLineVisible(false);
		xAxis.setTickMarksVisible(false);
		xAxis.setTickLabelInsets(new RectangleInsets(0.0,0.0,0.0,0.0));
		xAxis.setTickLabelPaint(Color.BLACK);
		//xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(12.0f));
		
		if(dataset.numberConditions()<10) {
			xAxis.setVerticalTickLabels(false);
			xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(12.0f));
		}
		else {
			xAxis.setVerticalTickLabels(true);
			if(dataset.numberConditions()>=50 && dataset.numberConditions()<200)
				xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(10.0f));
			else if(dataset.numberConditions()>=200)
				xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(5.0f));
		}
		
		 
		
		xAxis.setLowerMargin(10.0);
		xAxis.setUpperMargin(0.0);
		xAxis.setLabelFont(new Font("Dialog", Font.BOLD, 20));
		
		SymbolAxis yAxis = new SymbolAxis("Genes", dataset.getGeneids());
		yAxis.setVisible(true);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setTickLabelInsets(new RectangleInsets(0.0,0.0,0.0,0.0));
		yAxis.setTickLabelPaint(Color.BLACK);
		if(dataset.numberGenes()<200)
			yAxis.setTickLabelFont(yAxis.getTickLabelFont().deriveFont(8.0f));
		else
			yAxis.setTickLabelFont(yAxis.getTickLabelFont().deriveFont(5.0f));
		yAxis.setVerticalTickLabels(false);
		yAxis.setTickLabelFont(yAxis.getTickLabelFont().deriveFont(10.0f));
		yAxis.setLowerMargin(10.0);
		yAxis.setUpperMargin(0.0);

		
		yAxis.setInverted(true);
		yAxis.setLabelFont(new Font("Dialog", Font.BOLD, 20));
		
		
		double[][] data=MTUMatrixUtils.transposeMatrix(dataset.getexpressionDataMatrix());
		 Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(data);
		XYZDataset xyzset = new XYZArrayDataset(getWeightedMatrix(data,minmaxdata));
        XYBlockRenderer r = new XYBlockRenderer();
		
       
        HeatmapGreenRedColorPaintScale scale=new HeatmapGreenRedColorPaintScale();
        r.setPaintScale(scale);
        r.setBaseToolTipGenerator(new HeatmapToolTipGenerator(xyzset,dataset.getexpressionDataMatrix(),dataset.getGeneNamesList(),dataset.getConditionsList()));
/*        r.setBlockHeight(1.0f);
        r.setBlockWidth(1.0f);*/
		
        XYPlot plot = new XYPlot(xyzset, xAxis, yAxis, r);
		plot.setDomainAxis(xAxis);
		plot.setDomainAxisLocation(AxisLocation.TOP_OR_LEFT);
		plot.setDomainGridlinesVisible(false);
		plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
		plot.setRangeGridlinesVisible(false);
		plot.setOutlineVisible(false);
		plot.setInsets(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
		plot.setAxisOffset(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
		plot.setOrientation(PlotOrientation.VERTICAL);

		
		final JFreeChart chart = new JFreeChart(null, plot);
		chart.removeLegend();
		chart.setBorderVisible(true);
		chart.setPadding(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		chart.setAntiAlias(false);
		chart.setTextAntiAlias(true);
		
		//int width = 15*dataset.numberConditions();
       // int height = 10*dataset.numberGenes();
		
		int width=800;
		int height=1200;
		
        ChartPanel chartPanel = new ChartPanel(chart,width,height,16,16,width*10,height*10,true,true,true,true,true,true);
        NumberAxis scaleAxis = new NumberAxis("");
        scaleAxis.setUpperBound(minmaxdata.getValue1());
        scaleAxis.setLowerBound(minmaxdata.getValue0());
        scaleAxis.setAxisLinePaint(Color.white);
        scaleAxis.setTickMarkPaint(Color.white);
        scaleAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 8));
        PaintScaleLegend legend = new PaintScaleLegend(new HeatmapGreenRedColorPaintScale(minmaxdata.getValue0(),minmaxdata.getValue1()),scaleAxis);
        legend.setAxisLocation(AxisLocation.TOP_OR_LEFT);
        legend.setPadding(new RectangleInsets(5, 5, 5, 5));
        legend.setStripWidth(20);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setBackgroundPaint(Color.WHITE);
        chart.addSubtitle(legend);
        chart.setBackgroundPaint(Color.white);
		scrollPaneplotpanel.setViewportView(chartPanel);
		
		if(dataset.numberGenes()>200 || dataset.numberConditions()>200)
			chartPanel.setMouseWheelEnabled(true);
		
	}


	/**
	 * Gets the weighted matrix.
	 *
	 * @param data the data
	 * @param minmaxdata the minmaxdata
	 * @return the weighted matrix
	 */
	protected double[][] getWeightedMatrix(double[][] data, Pair<Double, Double> minmaxdata){
		
		double [][] weightmatrix=new double[dataset.getConditionsList().size()][dataset.getGeneNamesList().size()];
		
		//double[][] data=MTUMatrixUtils.transposeMatrix(dataset.getexpressionDataMatrix());
		//Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(data);
		
		
		for (int i = 0; i < dataset.getConditionsList().size(); i++) {
			
			double[] row=data[i];
			for (int j = 0; j < row.length; j++) {
				double weightval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				weightmatrix[i][j]=weightval;
			}
		}

		return weightmatrix;
	}
	
	
	
/*	public static void main(String[] args) throws FileNotFoundException, IOException, MissingValueImputationException, ParseException {
		JFrame f=new JFrame();
		f.setSize(1000,600);
		ExpressionDatasetHeatmapJFreeChartPlotPanel p=new ExpressionDatasetHeatmapJFreeChartPlotPanel();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//ExpressionData expdata=ExpressionData.importFromTXTFormat("/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt").load();
		BiclusterList list=BiclusterList.importBiclusterListFromJBiclustGEFormat("/home/orocha/Documentos/aaaaa/development/bimaxreslist.bicge", 
				"/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt");
		p.loadBiclusterHeatMap(list.get(1), 9);

	}*/
	
}
