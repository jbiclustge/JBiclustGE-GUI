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
 * The Class BiclusterHeatmapJFreeChartPlotPanel.
 */
public class BiclusterHeatmapJFreeChartPlotPanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The panel. */
	private JPanel panel;
	
	/** The scroll paneplotpanel. */
	private JScrollPane scrollPaneplotpanel;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The label. */
	private JLabel label;
	
	/** The text fieldbicnumber. */
	private JTextField textFieldbicnumber;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/** The bicluster. */
	private BiclusterResult bicluster;
	
	
	/**
	 * Instantiates a new bicluster heatmap J free chart plot panel.
	 */
	public BiclusterHeatmapJFreeChartPlotPanel() {
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
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1, 1, 1, 0};
		gbl_panel_1.rowHeights = new int[]{1, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		this.panel_1.add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1, 1, 0};
		gbl_panel_2.rowHeights = new int[]{1, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_2.setLayout(gbl_panel_2);
		
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
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
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
		gbc_scrollPaneplotpanel.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPaneplotpanel.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneplotpanel.gridx = 0;
		gbc_scrollPaneplotpanel.gridy = 1;
		add(this.scrollPaneplotpanel, gbc_scrollPaneplotpanel);
	}

	
	/**
	 * Load bicluster heat map.
	 *
	 * @param bicluster the bicluster
	 * @param bicindex the bicindex
	 */
	public void loadBiclusterHeatMap(BiclusterResult bicluster, Integer bicindex) {
		this.bicluster=bicluster;
		if(bicindex!=null) {
			this.label = new JLabel("Bicluster:");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			this.panel_2.add(this.label, gbc_label);
			textFieldbicnumber.setText(String.valueOf(bicindex));
			textFieldbicnumber.setEditable(false);
			this.textFieldbicnumber.setColumns(10);
			GridBagConstraints gbc_textFieldbicnumber = new GridBagConstraints();
			gbc_textFieldbicnumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldbicnumber.gridx = 1;
			gbc_textFieldbicnumber.gridy = 0;
			this.panel_2.add(this.textFieldbicnumber, gbc_textFieldbicnumber);
		}
		
		loadHeatmapPanel();
	}
	
	
	/**
	 * Load heatmap panel.
	 */
	public void loadHeatmapPanel() {
		
		int labelfontsize=20;
	
		SymbolAxis xAxis=new SymbolAxis("Conditions", bicluster.getOrderedConditionNamesArray());
        xAxis.setVisible(true);
		xAxis.setAxisLineVisible(false);
		xAxis.setTickMarksVisible(false);
		xAxis.setTickLabelInsets(new RectangleInsets(0.0,0.0,0.0,0.0));
		xAxis.setTickLabelPaint(Color.BLACK);
		xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(12.0f));
		if(bicluster.getNumberConditions()<10)
			xAxis.setVerticalTickLabels(false);
		else
			xAxis.setVerticalTickLabels(true);
		xAxis.setLowerMargin(10.0);
		xAxis.setUpperMargin(0.0);
		xAxis.setLabelFont(new Font("Dialog", Font.BOLD, 20));
		
		SymbolAxis yAxis = new SymbolAxis("Genes", bicluster.getOrderedGeneNamesArray());
		yAxis.setVisible(true);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setTickLabelInsets(new RectangleInsets(0.0,0.0,0.0,0.0));
		yAxis.setTickLabelPaint(Color.BLACK);
		yAxis.setTickLabelFont(yAxis.getTickLabelFont().deriveFont(8.0f));
		yAxis.setVerticalTickLabels(false);
		yAxis.setLowerMargin(10.0);
		yAxis.setUpperMargin(0.0);
		yAxis.setInverted(true);
		
		if(bicluster.getNumberGenes()<10)
	        labelfontsize=10;
		yAxis.setLabelFont(new Font("Dialog", Font.BOLD, labelfontsize));
		
		
		XYZDataset xyzset = new XYZArrayDataset(getWeightedMatrix(bicluster));
        XYBlockRenderer r = new XYBlockRenderer();
		
        Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(bicluster.getOrderedBiclusterExpressionDatabyColunmDimension());
        HeatmapGreenRedColorPaintScale scale=new HeatmapGreenRedColorPaintScale();
        r.setPaintScale(scale);
        r.setBaseToolTipGenerator(new HeatmapToolTipGenerator(xyzset,bicluster.getOrderedBiclusterExpressionDatabyRowDimension(),bicluster.getGeneNamesOrderedAsExpressionDataset(),bicluster.getConditionNamesOrderedAsExpressionDataset()));
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
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);
		
		int width = 30*bicluster.getNumberConditions();
        int height = 10*bicluster.getNumberGenes();
		
        ChartPanel chartPanel = new ChartPanel(chart,width,height,16,16,width*10,height*10,true,true,true,true,true,true);
        NumberAxis scaleAxis = new NumberAxis("");
        scaleAxis.setUpperBound(minmaxdata.getValue1());
        scaleAxis.setLowerBound(minmaxdata.getValue0());
        scaleAxis.setAxisLinePaint(Color.white);
        scaleAxis.setTickMarkPaint(Color.white);
        scaleAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 10));
        PaintScaleLegend legend = new PaintScaleLegend(new HeatmapGreenRedColorPaintScale(minmaxdata.getValue0(),minmaxdata.getValue1()),scaleAxis);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        legend.setPadding(new RectangleInsets(5, 5, 5, 5));
        legend.setStripWidth(20);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setBackgroundPaint(Color.WHITE);
        chart.addSubtitle(legend);
        chart.setBackgroundPaint(Color.white);
		scrollPaneplotpanel.setViewportView(chartPanel);
		
	}


	/**
	 * Gets the weighted matrix.
	 *
	 * @param bicluster the bicluster
	 * @return the weighted matrix
	 */
	protected double[][] getWeightedMatrix(BiclusterResult bicluster){
		
		double [][] weightmatrix=new double[bicluster.getConditionNames().size()][bicluster.getGeneNames().size()];
		
		double[][] bicdata=bicluster.getOrderedBiclusterExpressionDatabyColunmDimension();
		Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(bicdata);
		
		
		for (int i = 0; i < bicluster.getConditionNames().size(); i++) {
			
			double[] row=bicdata[i];
			for (int j = 0; j < row.length; j++) {
				double weightval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				weightmatrix[i][j]=weightval;
			}
		}

		return weightmatrix;
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MissingValueImputationException the missing value imputation exception
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, MissingValueImputationException, ParseException {
		JFrame f=new JFrame();
		f.setSize(1000,600);
		BiclusterHeatmapJFreeChartPlotPanel p=new BiclusterHeatmapJFreeChartPlotPanel();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//ExpressionData expdata=ExpressionData.importFromTXTFormat("/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt").load();
		BiclusterList list=BiclusterList.importBiclusterListFromJBiclustGEFormat("/home/orocha/Documentos/aaaaa/development/bimaxreslist.bicge", 
				"/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt");
		p.loadBiclusterHeatMap(list.get(1), 9);

	}
	
}
