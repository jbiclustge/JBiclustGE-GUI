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
package jbiclustgegui.gui.views.analysis.gsea;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.javatuples.Pair;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.datatypes.GeneExpressionDatasetBox;
import jbiclustgegui.gui.components.panels.data.HeatMap;
import smile.plot.Heatmap;
import smile.plot.PlotPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionViewer.
 */
public class ExpressionViewer extends JPanel{
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The dataset. */
	private ExpressionData dataset;
	
	/**
	 * Instantiates a new expression viewer.
	 *
	 * @param data the data
	 */
	public ExpressionViewer(GeneExpressionDatasetBox data) {
		this.dataset=data.getExpressionset();
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		
		PlotPanel p=getchartPanel();
		//HeatMap p2=getheatmap2();
		//this.scrollPane = new JScrollPane(p2);
		
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
	   // add(this.scrollPane, gbc_scrollPane);
	   add(p, gbc_scrollPane);

	}
	
	
	
	/**
	 * Gets the chart panel.
	 *
	 * @return the chart panel
	 */
	private PlotPanel getchartPanel() {
		
		double [][] normmatrix=new double[dataset.numberGenes()][dataset.numberConditions()];
		Pair<Double, Double> minmaxdata=dataset.getMinMaxValuesInDataset();
		
		for (int i = 0; i < dataset.numberConditions(); i++) {
			
			double[] row=dataset.getGeneRow(i);
			for (int j = 0; j < row.length; j++) {
				double normval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				normmatrix[j][i]=normval;
			}
		}
		
		return new PlotPanel(Heatmap.plot(dataset.getGeneids(), dataset.getConditions(),normmatrix));
	}
	
	/**
	 * Gets the heatmap 2.
	 *
	 * @return the heatmap 2
	 */
	private HeatMap getheatmap2() {
		
		double [][] normmatrix=new double[dataset.numberGenes()][dataset.numberConditions()];
		Pair<Double, Double> minmaxdata=dataset.getMinMaxValuesInDataset();
		
		for (int i = 0; i < dataset.numberConditions(); i++) {
			
			double[] row=dataset.getGeneRow(i);
			for (int j = 0; j < row.length; j++) {
				double normval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				normmatrix[j][i]=normval;
			}
		}
		
		return new HeatMap(normmatrix, false, new Color[] {Color.green,Color.RED,Color.BLUE,Color.LIGHT_GRAY,Color.cyan});
	}
	

}
