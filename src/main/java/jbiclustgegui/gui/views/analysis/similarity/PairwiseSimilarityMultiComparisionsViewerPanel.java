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
package jbiclustgegui.gui.views.analysis.similarity;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import jbiclustge.propertiesmodules.PropertyLabels;
import jbiclustge.utils.osystem.SystemFolderTools;
import jbiclustge.utils.props.JBiGePropertiesManager;
import jbiclustgegui.datatypes.analysis.PairwiseMultipleListBiclustersResultsBox;
import jbiclustgegui.gui.components.panels.chart.heatmap.ExpressionDatasetHeatmapJFreeChartPlotPanel;
import jbiclustgegui.gui.components.panels.data.DataMatrixPanel;
import jrplot.plotpackages.dendrogram.RDendrogram;
import jrplot.rbinders.components.dataframe.DefaultDataframeContainer;
import jrplot.rbinders.components.dataframe.datatype.DoubleDataColumn;
import jrplot.rbinders.components.dataframe.datatype.rownames.StringColumnRowNames;
import pt.ornrocha.rtools.installutils.RInstallTools;

// TODO: Auto-generated Javadoc
/**
 * The Class PairwiseSimilarityMultiComparisionsViewerPanel.
 */
public class PairwiseSimilarityMultiComparisionsViewerPanel extends JPanel{
	
	/** The panel. */
	private JPanel panel;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The scroll paneviewdata. */
	private JScrollPane scrollPaneviewdata;
	
	/** The results. */
	private PairwiseMultipleListBiclustersResultsBox results;
	
	/** The expressiondatasetheatmapview. */
	private ExpressionDatasetHeatmapJFreeChartPlotPanel expressiondatasetheatmapview;
	
	/** The split pane. */
	private JSplitPane splitPane;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The panel plot. */
	private JPanel panelPlot;
	
	/** The panelsimilaritymatrixviewpanel. */
	private DataMatrixPanel panelsimilaritymatrixviewpanel;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	
	
	/**
	 * Instantiates a new pairwise similarity multi comparisions viewer panel.
	 *
	 * @param results the results
	 */
	public PairwiseSimilarityMultiComparisionsViewerPanel(PairwiseMultipleListBiclustersResultsBox results) {
		this.results=results;
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
		
		this.splitPane = new JSplitPane();
		this.splitPane.setResizeWeight(.2d);
		this.splitPane.setDividerLocation(0.2);
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		add(this.splitPane, gbc_splitPane);
		
		this.panel_1 = new JPanel();
		this.splitPane.setLeftComponent(this.panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,0.1};
		this.panel_1.setLayout(gbl_panel_1);
		
		
		
		this.panelPlot = new JPanel();
		this.splitPane.setRightComponent(this.panelPlot);
		GridBagLayout gbl_panelPlot = new GridBagLayout();
		gbl_panelPlot.columnWidths = new int[]{1};
		gbl_panelPlot.rowHeights = new int[]{1};
		gbl_panelPlot.columnWeights = new double[]{1.0};
		gbl_panelPlot.rowWeights = new double[]{1.0};
		this.panelPlot.setLayout(gbl_panelPlot);
		
		/*this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		this.panelPlot.add(this.panel_3, gbc_panel_3);*/
		
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
	}
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		viewDataMatrixTable();
		try {
			showDendrogram();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * View data matrix table.
	 */
	private void viewDataMatrixTable() {
		this.panelsimilaritymatrixviewpanel = new DataMatrixPanel();
		GridBagConstraints gbc_panelsimilaritymatrixviewpanel = new GridBagConstraints();
		gbc_panelsimilaritymatrixviewpanel.fill = GridBagConstraints.BOTH;
		gbc_panelsimilaritymatrixviewpanel.gridx = 0;
		gbc_panelsimilaritymatrixviewpanel.gridy = 0;
		this.panel_1.add(this.panelsimilaritymatrixviewpanel, gbc_panelsimilaritymatrixviewpanel);
		
		panelsimilaritymatrixviewpanel.setData(results.getOrderedNames(), results.getOrderedNames(), results.getSimilarityResults(), false);
		updateUI();
	}
	
	
	
	/**
	 * Show dendrogram.
	 *
	 * @throws Exception the exception
	 */
	private void showDendrogram() throws Exception {
		
		String R_PATH=RInstallTools.getSystemR_PATH();
		
		if(R_PATH!=null && !R_PATH.isEmpty() && !R_PATH.equals(RInstallTools.NONE_R_HOME)) {
			
			String Ruserlib=(String) JBiGePropertiesManager.getManager().getKeyValue(PropertyLabels.RUSERLIBPATH);
			
			DefaultDataframeContainer dataframe=new DefaultDataframeContainer();
			
			StringColumnRowNames rownames=new StringColumnRowNames(results.getOrderedNames());
			
			dataframe.setRowNames(rownames);
			
			double[][] simmatrix=results.getSimilarityResults();
			
			for (int i = 0; i < simmatrix[0].length; i++) {
				
				String colname=results.getOrderedNames().get(i);
				ArrayList<Double> colvalues=new ArrayList<>();
				for (int j = 0; j < simmatrix.length; j++) {
					colvalues.add(simmatrix[j][i]);
				}
				DoubleDataColumn col=new DoubleDataColumn(colname, colvalues);
				dataframe.appendDataColumnToDataframe(col);
			}
			
			RDendrogram dendmaker=new RDendrogram(dataframe, false);
			dendmaker.addWidth(800).addHeight(600);
			
			if(Ruserlib!=null)
				dendmaker.setRuserlibpath(Ruserlib);
			
			dendmaker.saveFigureFilepath(SystemFolderTools.getMainFolderTemporaryProcesses(), null);
			Thread t=new Thread(dendmaker);
			t.run();
			//dendmaker.run();
			
			
			//this.panel_3 = new JPanel();
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 0;
			gbc_panel_3.gridy = 0;
			this.panelPlot.add(dendmaker.getImageViewer().getComponent(), gbc_panel_3);
			
		}
		
	}

}
