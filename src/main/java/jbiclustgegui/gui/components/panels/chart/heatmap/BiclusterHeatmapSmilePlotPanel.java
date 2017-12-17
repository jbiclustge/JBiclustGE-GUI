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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.javatuples.Pair;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustge.results.biclusters.containers.BiclusterResult;
import jbiclustgegui.gui.components.panels.data.DataMatrixPanel;
import pt.ornrocha.arrays.MTUMatrixUtils;
import smile.imputation.MissingValueImputationException;
import smile.plot.Heatmap;
import smile.plot.PlotCanvas;
import smile.plot.PlotPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusterHeatmapSmilePlotPanel.
 */
public class BiclusterHeatmapSmilePlotPanel extends JPanel{
	
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
	
	
	/**
	 * Instantiates a new bicluster heatmap smile plot panel.
	 */
	public BiclusterHeatmapSmilePlotPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{1.0,0.01};
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
		
		this.label = new JLabel("Bicluster:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		this.panel_2.add(this.label, gbc_label);
		
		this.textFieldbicnumber = new JTextField();
		this.textFieldbicnumber.setColumns(10);
		GridBagConstraints gbc_textFieldbicnumber = new GridBagConstraints();
		gbc_textFieldbicnumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldbicnumber.gridx = 1;
		gbc_textFieldbicnumber.gridy = 0;
		this.panel_2.add(this.textFieldbicnumber, gbc_textFieldbicnumber);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(this.panel, gbc_panel);
		
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
	public void loadBiclusterHeatMap(BiclusterResult bicluster, int bicindex) {
		
		textFieldbicnumber.setText(String.valueOf(bicindex));
		textFieldbicnumber.setEditable(false);
		
		
		
		PlotCanvas plot=Heatmap.plot(bicluster.getGeneNamesArray(), bicluster.getConditionNamesArray(), getWeightedMatrix(bicluster), new Color[] {Color.GREEN,Color.BLACK,Color.RED});
		PlotPanel plotsmilepanel=new PlotPanel(plot);
		//PlotPanel plotsmilepanel=new PlotCanvas(lowerBound, upperBound, extendBound)
		scrollPaneplotpanel.setViewportView(plotsmilepanel);
	//	PlotPanel smilepanel
		
	}
	
	/**
	 * Gets the weighted matrix.
	 *
	 * @param bicluster the bicluster
	 * @return the weighted matrix
	 */
	protected double[][] getWeightedMatrix(BiclusterResult bicluster){
		
		double [][] weightmatrix=new double[bicluster.getGeneNames().size()][bicluster.getConditionNames().size()];
		
		double[][] bicdata=bicluster.getBiclusterExpressionDatabyRowDimension();
		Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(bicdata);
		
		
		for (int i = 0; i < bicluster.getGeneNames().size(); i++) {
			
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
		BiclusterHeatmapSmilePlotPanel p=new BiclusterHeatmapSmilePlotPanel();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//ExpressionData expdata=ExpressionData.importFromTXTFormat("/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt").load();
		BiclusterList list=BiclusterList.importBiclusterListFromJBiclustGEFormat("/home/orocha/Documentos/aaaaa/development/bimaxreslist.bicge", 
				"/home/orocha/Documentos/aaaaa/development/yeast_GOEnrichment_Gasch2000_2944x173.txt");
		p.loadBiclusterHeatMap(list.get(0), 1);

	}
	
}
