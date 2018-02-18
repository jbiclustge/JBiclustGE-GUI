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
package jbiclustgegui.gui.components.panels.biclusters;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustge.results.biclusters.containers.BiclusterResult;
import jbiclustge.utils.properties.JBiGePropertiesManager;
import jbiclustge.utils.properties.JBiclustGEPropertiesInitializer;
import jbiclustgegui.gui.components.dialogs.biclusters.BiclusterHeatmapViewDialog;
import jbiclustgegui.gui.components.dialogs.biclusters.BiclusterMatrixViewDialog;
import jbiclustgegui.gui.components.tables.biclusters.BiclustersTable;
import jbiclustgegui.gui.components.tables.common.InformationTable;
import jrplot.plotpackages.interactive.heatmap.shinyheatmaply.ShinyHeatmaply;
import jrplot.plotpackages.interactive.parallelcoords.parcoords.ParcoordsWidgetMulti;
import jrplot.plotpackages.interactive.shinywrapper.shinyfeature.ShinyControlCenter;
import jrplot.rbinders.components.interfaces.IDataFrameDataLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclustersViewPanel.
 */
public class BiclustersViewPanel extends JPanel implements MouseListener,KeyListener{
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The panel. */
	private JPanel panel;
	
	/** The scroll pane 1. */
	private JScrollPane scrollPane_1;
	
	/** The scroll pane 2. */
	private JScrollPane scrollPane_2;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The biclusterstable. */
	private BiclustersTable biclusterstable;
	
	/** The genestable. */
	private InformationTable genestable;
	
	/** The conditionstable. */
	private InformationTable conditionstable;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The lbl displaing. */
	private JLabel lblDisplaing;
	
	/** The text fieldnbic. */
	private JTextField textFieldnbic;
	
	/** The lbl N genes. */
	private JLabel lblNGenes;
	
	/** The text fieldngenes. */
	private JTextField textFieldngenes;
	
	/** The lbl N conditions. */
	private JLabel lblNConditions;
	
	/** The text fieldnconds. */
	private JTextField textFieldnconds;
	
	/** The listresults. */
	private BiclusterList listresults;
	
	/** The btn export. */
	private JButton btnExport;
	
	/** The btnexportjbiclustgejson. */
	private JButton btnexportjbiclustgejson;
	
	/** The btnexportbiclustformat. */
	private JButton btnexportbiclustformat;
	
	/** The btnbicoverlapperformat. */
	private JButton btnbicoverlapperformat;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The btn bicluster matrix. */
	private JButton btnBiclusterMatrix;
	
	/** The btn bicluster heatmap. */
	private JButton btnBiclusterHeatmap;
	
	/** The panel 4. */
	private JPanel panel_4;
	
	/** The btn new buttonviewparallelcoord. */
	private JButton btnNewButtonviewparallelcoord;
	
	/** The panel 5. */
	private JPanel panel_5;
	
	/** The rdbtn genes. */
	private JRadioButton rdbtnGenes;
	
	/** The rdbtn conditions. */
	private JRadioButton rdbtnConditions;
	
	/** The shinycc. */
	private ShinyControlCenter shinycc;
    
    /** The lastselectedbicluster. */
    private int lastselectedbicluster=-1;
    
    /** The panel interactive. */
    private JPanel panel_interactive;
    
    /** The panel 6. */
    private JPanel panel_6;
    
    /** The btn view heatmaps. */
    private JButton btnViewHeatmaps;
    
    /** The btn stop viewer. */
    private JButton btnStopViewer;
	
	/**
	 * Instantiates a new biclusters view panel.
	 */
	public BiclustersViewPanel() {
		initGUI();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		biclusterstable=new BiclustersTable();
		biclusterstable.addMouseListener(this);
		biclusterstable.addKeyListener(this);
		this.scrollPane = new JScrollPane(biclusterstable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(this.scrollPane, gbc_scrollPane);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 10;
		gbc_panel.gridwidth = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{0.2,1.0,1.0,1.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.insets = new Insets(0, 20, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		this.panel.add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1,1,1,1,1,1};
		gbl_panel_2.rowHeights = new int[]{1};
		gbl_panel_2.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panel_2.rowWeights = new double[]{1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.lblDisplaing = new JLabel("Showing:");
		GridBagConstraints gbc_lblDisplaing = new GridBagConstraints();
		gbc_lblDisplaing.anchor = GridBagConstraints.EAST;
		gbc_lblDisplaing.insets = new Insets(0, 20, 0, 5);
		gbc_lblDisplaing.gridx = 0;
		gbc_lblDisplaing.gridy = 0;
		this.panel_2.add(this.lblDisplaing, gbc_lblDisplaing);
		
		this.textFieldnbic = new JTextField();
		GridBagConstraints gbc_textFieldnbic = new GridBagConstraints();
		gbc_textFieldnbic.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldnbic.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnbic.gridx = 1;
		gbc_textFieldnbic.gridy = 0;
		this.panel_2.add(this.textFieldnbic, gbc_textFieldnbic);
		this.textFieldnbic.setColumns(10);
		
		this.lblNGenes = new JLabel("Nº Genes:");
		GridBagConstraints gbc_lblNGenes = new GridBagConstraints();
		gbc_lblNGenes.anchor = GridBagConstraints.EAST;
		gbc_lblNGenes.insets = new Insets(0, 20, 0, 5);
		gbc_lblNGenes.gridx = 2;
		gbc_lblNGenes.gridy = 0;
		this.panel_2.add(this.lblNGenes, gbc_lblNGenes);
		
		this.textFieldngenes = new JTextField();
		this.textFieldngenes.setText("0");
		GridBagConstraints gbc_textFieldngenes = new GridBagConstraints();
		gbc_textFieldngenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldngenes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldngenes.gridx = 3;
		gbc_textFieldngenes.gridy = 0;
		this.panel_2.add(this.textFieldngenes, gbc_textFieldngenes);
		this.textFieldngenes.setColumns(10);
		
		this.lblNConditions = new JLabel("Nº Conditions:");
		GridBagConstraints gbc_lblNConditions = new GridBagConstraints();
		gbc_lblNConditions.anchor = GridBagConstraints.EAST;
		gbc_lblNConditions.insets = new Insets(0, 20, 0, 5);
		gbc_lblNConditions.gridx = 4;
		gbc_lblNConditions.gridy = 0;
		this.panel_2.add(this.lblNConditions, gbc_lblNConditions);
		
		this.textFieldnconds = new JTextField();
		this.textFieldnconds.setText("0");
		GridBagConstraints gbc_textFieldnconds = new GridBagConstraints();
		gbc_textFieldnconds.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnconds.gridx = 5;
		gbc_textFieldnconds.gridy = 0;
		this.panel_2.add(this.textFieldnconds, gbc_textFieldnconds);
		this.textFieldnconds.setColumns(10);
		
		genestable=new InformationTable(new String[] {"Genes"});
		this.scrollPane_1 = new JScrollPane(genestable);
		//this.scrollPane_1.setViewportBorder(new TitledBorder(null, "Genes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		this.panel.add(this.scrollPane_1, gbc_scrollPane_1);
		
		conditionstable=new InformationTable(new String[] {"Conditions"});
		this.scrollPane_2 = new JScrollPane(conditionstable);
		//this.scrollPane_2.setViewportBorder(new TitledBorder(null, "Conditions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 4;
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 2;
		gbc_scrollPane_2.gridy = 1;
		this.panel.add(this.scrollPane_2, gbc_scrollPane_2);
		
		this.panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Export list biclusters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 1;
		this.panel.add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{0.0,0.0,0.0,0.0,1.0};
		panel_1.setLayout(gbl_panel_1);
		
		btnExport = new JButton("<html><center>JBiclustGE<br>Format</center></html>");
		btnExport.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/export.png")));
		this.btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToJBiclustGEFormat();
				
			}
		});
		this.btnExport.setPreferredSize(new Dimension(150, 40));
		this.btnExport.setMinimumSize(new Dimension(180, 40));;
		this.btnExport.setBounds(100, 50, 100, 50);
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExport.insets = new Insets(0, 0, 5, 0);
		gbc_btnExport.gridx = 0;
		gbc_btnExport.gridy = 0;
		panel_1.add(btnExport, gbc_btnExport);
		btnExport.setSize(new Dimension(100, 50));
		
		btnexportjbiclustgejson = new JButton("<html><center>JBiclustGE<br>JSON</center></html>");
		btnexportjbiclustgejson.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/export.png")));
		this.btnexportjbiclustgejson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToJBiclustGEJSON();
			}
		});
		this.btnexportjbiclustgejson.setMinimumSize(new Dimension(150, 40));
		this.btnexportjbiclustgejson.setPreferredSize(new Dimension(150, 40));
		GridBagConstraints gbc_btnexportjbiclustgejson = new GridBagConstraints();
		gbc_btnexportjbiclustgejson.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnexportjbiclustgejson.insets = new Insets(0, 0, 5, 0);
		gbc_btnexportjbiclustgejson.gridx = 0;
		gbc_btnexportjbiclustgejson.gridy = 1;
		panel_1.add(btnexportjbiclustgejson, gbc_btnexportjbiclustgejson);
		
		btnexportbiclustformat = new JButton("<html><center>biclust<br>Format</center></html>");
		btnexportbiclustformat.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/export.png")));
		this.btnexportbiclustformat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToRBiclustFormat();
			}
		});
		this.btnexportbiclustformat.setMinimumSize(new Dimension(150, 40));
		this.btnexportbiclustformat.setSize(new Dimension(100, 50));
		this.btnexportbiclustformat.setPreferredSize(new Dimension(150, 40));
		GridBagConstraints gbc_btnexportbiclustformat = new GridBagConstraints();
		gbc_btnexportbiclustformat.fill = GridBagConstraints.BOTH;
		gbc_btnexportbiclustformat.insets = new Insets(0, 0, 5, 0);
		gbc_btnexportbiclustformat.gridx = 0;
		gbc_btnexportbiclustformat.gridy = 2;
		panel_1.add(btnexportbiclustformat, gbc_btnexportbiclustformat);
		
		btnbicoverlapperformat = new JButton("<html><center>BicOverlapper<br>Format</center></html>");
		btnbicoverlapperformat.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/export.png")));
		this.btnbicoverlapperformat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToBicOverlapperFormat();
			}
		});
		this.btnbicoverlapperformat.setMinimumSize(new Dimension(150, 40));
		btnbicoverlapperformat.setPreferredSize(new Dimension(150, 40));
		GridBagConstraints gbc_btnbicoverlapperformat = new GridBagConstraints();
		gbc_btnbicoverlapperformat.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnbicoverlapperformat.insets = new Insets(0, 0, 5, 0);
		gbc_btnbicoverlapperformat.gridx = 0;
		gbc_btnbicoverlapperformat.gridy = 3;
		panel_1.add(btnbicoverlapperformat, gbc_btnbicoverlapperformat);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new TitledBorder(null, "View", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 4;
		gbc_panel_3.gridy = 2;
		this.panel.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{0.0,0.0,1.0,0.0,1.0,1.0,1.0,1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.btnBiclusterMatrix = new JButton("<html><center>Bicluster<br>Matrix</center></html>");
		btnBiclusterMatrix.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/view.png")));
		this.btnBiclusterMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewCurrentBiclusterMatrix();
				
			}
		});
		this.btnBiclusterMatrix.setPreferredSize(new Dimension(150, 40));
		this.btnBiclusterMatrix.setMaximumSize(new Dimension(150, 40));
		this.btnBiclusterMatrix.setMinimumSize(new Dimension(150, 40));
		GridBagConstraints gbc_btnBiclusterMatrix = new GridBagConstraints();
		gbc_btnBiclusterMatrix.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBiclusterMatrix.gridwidth = 2;
		gbc_btnBiclusterMatrix.insets = new Insets(0, 0, 5, 0);
		gbc_btnBiclusterMatrix.gridx = 0;
		gbc_btnBiclusterMatrix.gridy = 0;
		this.panel_3.add(this.btnBiclusterMatrix, gbc_btnBiclusterMatrix);
		
		this.btnBiclusterHeatmap = new JButton("<html><center>Bicluster<br>HeatMap</center></html>");
		btnBiclusterHeatmap.setIcon(new ImageIcon(BiclustersViewPanel.class.getResource("/images/i24x24/view.png")));
		this.btnBiclusterHeatmap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewCurrentBiclusterHeatmap();
				
			}
		});
		this.btnBiclusterHeatmap.setPreferredSize(new Dimension(150, 40));
		this.btnBiclusterHeatmap.setMinimumSize(new Dimension(150, 40));
		this.btnBiclusterHeatmap.setMaximumSize(new Dimension(150, 40));
		GridBagConstraints gbc_btnBiclusterHeatmap = new GridBagConstraints();
		gbc_btnBiclusterHeatmap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBiclusterHeatmap.gridwidth = 2;
		gbc_btnBiclusterHeatmap.insets = new Insets(0, 0, 5, 0);
		gbc_btnBiclusterHeatmap.gridx = 0;
		gbc_btnBiclusterHeatmap.gridy = 1;
		this.panel_3.add(this.btnBiclusterHeatmap, gbc_btnBiclusterHeatmap);
		
		/*btnStopApp = new JButton("Stop app");
		btnStopApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if(parcord!=null)
				if(tparcord!=null) {
					System.out.println(tparcord.isAlive());
					parcord.stopAPP();
					Thread t=new Thread(new Runnable() {
						
						@Override
						public void run() {
						
							tparcord.interrupt();
							System.out.println(tparcord.isAlive()+" --> "+tparcord.isInterrupted());
						}
					});
					t.run();
					
					
				}
					//parcord.stopAPP();
			}
		});
		GridBagConstraints gbc_btnStopApp = new GridBagConstraints();
		gbc_btnStopApp.insets = new Insets(0, 0, 5, 0);
		gbc_btnStopApp.gridx = 1;
		gbc_btnStopApp.gridy = 2;
		panel_3.add(btnStopApp, gbc_btnStopApp);*/
		
		this.panel_interactive = new JPanel();
		this.panel_interactive.setBorder(new TitledBorder(null, "Browser interactive features ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_interactive = new GridBagConstraints();
		gbc_panel_interactive.gridheight = 4;
		gbc_panel_interactive.gridwidth = 2;
		gbc_panel_interactive.insets = new Insets(0, 0, 5, 5);
		gbc_panel_interactive.fill = GridBagConstraints.BOTH;
		gbc_panel_interactive.gridx = 0;
		gbc_panel_interactive.gridy = 2;
		this.panel_3.add(this.panel_interactive, gbc_panel_interactive);
		GridBagLayout gbl_panel_interactive = new GridBagLayout();
		gbl_panel_interactive.columnWidths = new int[]{1};
		gbl_panel_interactive.rowHeights = new int[]{1,1,1};
		gbl_panel_interactive.columnWeights = new double[]{1.0};
		gbl_panel_interactive.rowWeights = new double[]{1.0,1.0,1.0};
		this.panel_interactive.setLayout(gbl_panel_interactive);
		
		this.panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		this.panel_interactive.add(this.panel_4, gbc_panel_4);
		this.panel_4.setBorder(new TitledBorder(null, "Parallel Coordinates", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1};
		gbl_panel_4.rowHeights = new int[]{1,1};
		gbl_panel_4.columnWeights = new double[]{1.0};
		gbl_panel_4.rowWeights = new double[]{0.0,1.0};
		this.panel_4.setLayout(gbl_panel_4);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBorder(new TitledBorder(null, "In Columns:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		this.panel_4.add(this.panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{1,1};
		gbl_panel_5.rowHeights = new int[]{1};
		gbl_panel_5.columnWeights = new double[]{0.0,0.0};
		gbl_panel_5.rowWeights = new double[]{1.0};
		this.panel_5.setLayout(gbl_panel_5);
		
		this.rdbtnGenes = new JRadioButton("Genes");
		this.rdbtnGenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnGenes.isSelected())
					rdbtnConditions.setSelected(false);
				else {
					rdbtnConditions.setSelected(true);
				}
					
			}
		});
		rdbtnGenes.setSelected(false);
		GridBagConstraints gbc_rdbtnGenes = new GridBagConstraints();
		gbc_rdbtnGenes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnGenes.gridx = 0;
		gbc_rdbtnGenes.gridy = 0;
		this.panel_5.add(this.rdbtnGenes, gbc_rdbtnGenes);
		
		this.rdbtnConditions = new JRadioButton("Conditions");
		this.rdbtnConditions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnConditions.isSelected())
					rdbtnGenes.setSelected(false);
				else
					rdbtnGenes.setSelected(true);
			}
		});
		rdbtnConditions.setSelected(true);
		GridBagConstraints gbc_rdbtnConditions = new GridBagConstraints();
		gbc_rdbtnConditions.gridx = 1;
		gbc_rdbtnConditions.gridy = 0;
		this.panel_5.add(this.rdbtnConditions, gbc_rdbtnConditions);
		
		this.btnNewButtonviewparallelcoord = new JButton("View Parallel Coord.");
		this.btnNewButtonviewparallelcoord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewParallelCoordinates();
			}
		});
		GridBagConstraints gbc_btnNewButtonviewparallelcoord = new GridBagConstraints();
		gbc_btnNewButtonviewparallelcoord.fill = GridBagConstraints.BOTH;
		gbc_btnNewButtonviewparallelcoord.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButtonviewparallelcoord.gridx = 0;
		gbc_btnNewButtonviewparallelcoord.gridy = 1;
		this.panel_4.add(this.btnNewButtonviewparallelcoord, gbc_btnNewButtonviewparallelcoord);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBorder(new TitledBorder(null, "Biclusters in ShinyHeatmaply", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 1;
		this.panel_interactive.add(this.panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{1};
		gbl_panel_6.rowHeights = new int[]{1};
		gbl_panel_6.columnWeights = new double[]{1.0};
		gbl_panel_6.rowWeights = new double[]{1.0};
		this.panel_6.setLayout(gbl_panel_6);
		
		this.btnViewHeatmaps = new JButton("View heatmaps");
		this.btnViewHeatmaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				viewBiclusterInShinyHeatmaply();
				
			}
		});
		GridBagConstraints gbc_btnViewHeatmaps = new GridBagConstraints();
		gbc_btnViewHeatmaps.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewHeatmaps.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewHeatmaps.gridx = 0;
		gbc_btnViewHeatmaps.gridy = 0;
		this.panel_6.add(this.btnViewHeatmaps, gbc_btnViewHeatmaps);
		
		this.btnStopViewer = new JButton("Close viewer");
		GridBagConstraints gbc_btnStopViewer = new GridBagConstraints();
		gbc_btnStopViewer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStopViewer.gridx = 0;
		gbc_btnStopViewer.gridy = 2;
		this.panel_interactive.add(this.btnStopViewer, gbc_btnStopViewer);
		this.btnStopViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				stopInteractiveViewer();
			}
		});
	}
	
	
	/**
	 * Adds the list of biclusters.
	 *
	 * @param listbics the listbics
	 */
	public void addListOfBiclusters(BiclusterList listbics) {
		this.listresults=listbics;
		biclusterstable.addListofBiclusters(listbics);
		addBiclusterResult(0);

	}
	
	
	
	/**
	 * Adds the bicluster result.
	 *
	 * @param index the index
	 */
	protected void addBiclusterResult(int index) {
		
		BiclusterResult result=listresults.get(index);
		
		List<Object[]> geneids=new ArrayList<>();
		ArrayList<String> ordereredgenes=result.getGeneNamesOrderedAsExpressionDataset();
		for (int i = 0; i < ordereredgenes.size(); i++) {
			Object[] geneid=new Object[] {ordereredgenes.get(i)};
			geneids.add(geneid);
		}
		genestable.setData(geneids);
		
		List<Object[]> condids=new ArrayList<>();
		ArrayList<String> orderedconds=result.getConditionNamesOrderedAsExpressionDataset();
		for (int i = 0; i < orderedconds.size(); i++) {
			Object[] condid=new Object[] {orderedconds.get(i)};
			condids.add(condid);
		}
		conditionstable.setData(condids);
		
		
		textFieldnbic.setText("Bicluster "+String.valueOf(index+1));
		textFieldnbic.setEditable(false);
		textFieldngenes.setText(String.valueOf(result.getNumberGenes()));
		textFieldngenes.setEditable(false);
		textFieldnconds.setText(String.valueOf(result.getNumberConditions()));
		textFieldnconds.setEditable(false);
		
	}

	
	/**
	 * Export to J biclust GE format.
	 */
	protected void exportToJBiclustGEFormat() {
		String selectedfile=openFileChooser();
		if(selectedfile!=null) {
        	 String filename=FilenameUtils.getBaseName(selectedfile);
        	 String dir=FilenameUtils.getFullPath(selectedfile);
        	 try {
				listresults.writeBiclusterListToJBiclustGEOutputFormat(dir, filename);
        	 } catch (IOException e) {
				Workbench.getInstance().error(e);
        	 }
        }
	}
	
	/**
	 * Export to J biclust GEJSON.
	 */
	protected void exportToJBiclustGEJSON() {
		String selectedfile=openFileChooser();
		if(selectedfile!=null) {
        	 try {
        		 boolean appendexpressiondataset=false;
        		int flag=JOptionPane.showConfirmDialog(this, "Do you want to include gene expression dataset in JSON file?", "", JOptionPane.YES_NO_OPTION);
        		if(flag==JOptionPane.YES_OPTION)
        			appendexpressiondataset=true;
        	    listresults.writeBiclusterListToJSONFormat(selectedfile, appendexpressiondataset);
        	 } catch (IOException e) {
				Workbench.getInstance().error(e);
        	 }
        }
	}
	
	

	/**
	 * Export to R biclust format.
	 */
	protected void exportToRBiclustFormat() {
		String selectedfile=openFileChooser();
		if(selectedfile!=null) {
        	 String filename=FilenameUtils.getBaseName(selectedfile);
        	 String dir=FilenameUtils.getFullPath(selectedfile);
        	 try {
				listresults.writeBiclusterListToBiclustRPackageFormat(dir, filename, "txt");
        	 } catch (IOException e) {
				Workbench.getInstance().error(e);
        	 }
        }
	}
	
	/**
	 * Export to bic overlapper format.
	 */
	protected void exportToBicOverlapperFormat() {
		String selectedfile=openFileChooser();
		if(selectedfile!=null) {
        	 String filename=FilenameUtils.getBaseName(selectedfile);
        	 String dir=FilenameUtils.getFullPath(selectedfile);
        	 try {
				listresults.writeBiclusterListToBicOverlapperOutputFormat(dir, filename);
        	 } catch (IOException e) {
				Workbench.getInstance().error(e);
        	 }
        }
	}
	
	
	/**
	 * Open file chooser.
	 *
	 * @return the string
	 */
	protected String openFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	if(selected!=null) {
        		return selected.getAbsolutePath();
        	}
        }
        return null;
	}
	
	
	
	/**
	 * View current bicluster matrix.
	 */
	protected void viewCurrentBiclusterMatrix() {
		
		int bicindex=biclusterstable.getSelectedRow();

		if(bicindex>-1){
			BiclusterResult bic=listresults.get(bicindex);
			try {
				BiclusterMatrixViewDialog.openDialog(bic,bicindex+1, this);
			} catch (HeadlessException | InterruptedException e) {
				Workbench.getInstance().error(e);
			}
		}
		else
			Workbench.getInstance().warn("Please select one bicluster in list of biclusters");
	}
	

	/**
	 * View current bicluster heatmap.
	 */
	protected void viewCurrentBiclusterHeatmap() {
		
		int bicindex=biclusterstable.getSelectedRow();
	    
		if(bicindex>-1) {
			BiclusterResult bic=listresults.get(bicindex);
			try {
				BiclusterHeatmapViewDialog.openDialog(bic,bicindex+1, this);
			} catch (HeadlessException | InterruptedException e) {
				Workbench.getInstance().error(e);
			}
		}
		else
			Workbench.getInstance().warn("Please select one bicluster in list of biclusters");
	}
	
	
/*	protected void viewParallelCoordinates() {

		int bicindex=biclusterstable.getSelectedRow();

		if(bicindex>-1) {

			if(bicindex!=lastselectedbicluster) {
				lastselectedbicluster=bicindex;
				BiclusterResult bic=listresults.get(bicindex);

				IDataFrameDataLoader dataframe=bic.getResultsToDataFrame(rdbtnGenes.isSelected());
				
				String checkboxlabel="Show Gene Names";
				if(rdbtnGenes.isSelected())
					checkboxlabel="Show Condition Names";

				ParcoordsWidget parcoords=new ParcoordsWidget(dataframe).setShinyLabelCheckbox1(checkboxlabel);
				parcoords.setRuserlibpath((String) JBiGePropertiesManager.getManager().getKeyValue(JBiclustGEPropertiesInitializer.RUSERLIBPATH));

				if(shinycc!=null) {
					shinycc.stop();
				}
				shinycc=new ShinyControlCenter(parcoords).setPort(22553);

				Thread t=new Thread(shinycc);
				t.start();
			}

		}
		else
			Workbench.getInstance().warn("Please select one bicluster in list of biclusters");
	}*/
	
	/**
 * View parallel coordinates.
 */
protected void viewParallelCoordinates() {
		ArrayList<IDataFrameDataLoader> dataframes=new ArrayList<>();

		for (int i = 0; i < listresults.size(); i++) {
			IDataFrameDataLoader dataframe=listresults.get(i).getResultsToDataFrame(rdbtnGenes.isSelected());
			dataframe.setDataframeName("Bicluster_"+String.valueOf(i+1));
			dataframes.add(dataframe);
		}
		
		String checkboxlabel="Show Gene Names";
		if(rdbtnGenes.isSelected())
			checkboxlabel="Show Condition Names";
		
		ParcoordsWidgetMulti bicsparcoords=new ParcoordsWidgetMulti(dataframes).setShinyLabelCheckbox1(checkboxlabel);
		bicsparcoords.setRuserlibpath((String) JBiGePropertiesManager.getManager().getKeyValue(JBiclustGEPropertiesInitializer.RUSERLIBPATH));
		
		if(shinycc!=null) {
			shinycc.stop();
		}
		shinycc=new ShinyControlCenter(bicsparcoords).showDialogWarningMessages(this);
		
		Thread t=new Thread(shinycc);
		t.start();

	}
	
	
	/**
	 * View bicluster in shiny heatmaply.
	 */
	protected void viewBiclusterInShinyHeatmaply() {
		
		ArrayList<IDataFrameDataLoader> dataframes=new ArrayList<>();
		
		for (int i = 0; i < listresults.size(); i++) {
			IDataFrameDataLoader dataframe=listresults.get(i).getResultsToDataFrame(false);
			dataframe.setDataframeName("Bicluster_"+String.valueOf(i+1));
			dataframes.add(dataframe);
		}
		
		ShinyHeatmaply heatmap=new ShinyHeatmaply(dataframes);
		heatmap.setRuserlibpath((String) JBiGePropertiesManager.getManager().getKeyValue(JBiclustGEPropertiesInitializer.RUSERLIBPATH));
		
		if(shinycc!=null) {
			shinycc.stop();
		}
		shinycc=new ShinyControlCenter(heatmap).showDialogWarningMessages(this);
		
		Thread t=new Thread(shinycc);
		t.start();
	}
	
	
	/**
	 * Stop interactive viewer.
	 */
	protected void stopInteractiveViewer() {
		
		if(shinycc!=null)
			shinycc.stop();
		
	}
	
	

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row=biclusterstable.rowAtPoint(e.getPoint());
		
		addBiclusterResult(row);
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
		// TODO Auto-generated method stub
		
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
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent evt) {
		/*final int key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            final int row = biclusterstable.getSelectedRow();
            addBiclusterResult(row);
        }*/
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent evt) {
		final int key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            final int row = biclusterstable.getSelectedRow();
            addBiclusterResult(row);
        }
		
	}
}
