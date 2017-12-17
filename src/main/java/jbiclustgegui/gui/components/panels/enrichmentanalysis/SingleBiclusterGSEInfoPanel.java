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
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import jbiclustgegui.gui.components.tables.biclusters.BiclusterGSEAInfoTable;
import jbiclustgegui.gui.components.tables.common.InformationTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class SingleBiclusterGSEInfoPanel.
 */
public class SingleBiclusterGSEInfoPanel extends JPanel implements MouseListener,KeyListener{
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The split pane. */
	private JSplitPane splitPane;
	
	/** The scroll panegeneids. */
	private JScrollPane scrollPanegeneids;
	
	/** The scroll paneprobids. */
	private JScrollPane scrollPaneprobids;
	
	/** The results. */
	private FilteredBiclusterGSEAResultContainer results;
	
	/** The tableresults. */
	private BiclusterGSEAInfoTable tableresults;
	
	/** The geneidstable. */
	private InformationTable geneidstable;
	
	/** The probidstable. */
	private InformationTable probidstable;
	
	/** The termids. */
	private ArrayList<String> termids;
	
	/** The panel. */
	private JPanel panel;
	//private JLabel lblExcludePvalues;
	//private JCheckBox checkBoxexcludzeros;
	
	/**
	 * Instantiates a new single bicluster GSE info panel.
	 */
	public SingleBiclusterGSEInfoPanel() {
		initGUI();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{1.0,0.3};
		gridBagLayout.rowWeights = new double[]{0.05,1.0};
		setLayout(gridBagLayout);
		
		tableresults=new BiclusterGSEAInfoTable();
		tableresults.addMouseListener(this);
		tableresults.addKeyListener(this);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{0.0,0.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		/*lblExcludePvalues = new JLabel("Exclude p-values = 0");
		GridBagConstraints gbc_lblExcludePvalues = new GridBagConstraints();
		gbc_lblExcludePvalues.anchor = GridBagConstraints.EAST;
		gbc_lblExcludePvalues.insets = new Insets(0, 20, 0, 5);
		gbc_lblExcludePvalues.gridx = 0;
		gbc_lblExcludePvalues.gridy = 0;
		panel.add(lblExcludePvalues, gbc_lblExcludePvalues);
		
		checkBoxexcludzeros = new JCheckBox("");
		this.checkBoxexcludzeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		GridBagConstraints gbc_checkBoxexcludzeros = new GridBagConstraints();
		gbc_checkBoxexcludzeros.insets = new Insets(0, 0, 0, 5);
		gbc_checkBoxexcludzeros.gridx = 1;
		gbc_checkBoxexcludzeros.gridy = 0;
		panel.add(checkBoxexcludzeros, gbc_checkBoxexcludzeros);*/
		
		
		this.scrollPane = new JScrollPane(tableresults);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(this.scrollPane, gbc_scrollPane);
		
		this.splitPane = new JSplitPane();
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setResizeWeight(.5d);
		this.splitPane.setDividerLocation(0.5);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		add(this.splitPane, gbc_splitPane);
		
		geneidstable=new InformationTable(new String[] {"Gene ID"});
		this.scrollPanegeneids = new JScrollPane(geneidstable);
		this.scrollPanegeneids.setToolTipText("Select a Term ID to view which genes are associated with it.");
		this.splitPane.setLeftComponent(this.scrollPanegeneids);
		
		
		probidstable=new InformationTable(new String[] {"Probset ID"});
		this.scrollPaneprobids = new JScrollPane(probidstable);
		this.scrollPaneprobids.setToolTipText("Select a Term ID to view which probsets  are associated with it.");
		this.splitPane.setRightComponent(this.scrollPaneprobids);
	}
	
	/**
	 * Addfiltered results of bicluster.
	 *
	 * @param results the results
	 * @param bicluster the bicluster
	 */
	public void addfilteredResultsOfBicluster(FilteredBiclusterGSEAResultContainer results,int bicluster) {
		this.results=results;
		this.termids=results.getFilteredgoterms();
		setBorder(new TitledBorder(null, "Enrichment Analysis Information Bicluster "+String.valueOf(bicluster), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableresults.addFilteredBiclusterGSEAResultContainer(this.results);
		fillComponents();
	}
	
	
	/**
	 * Fill components.
	 */
	protected void fillComponents() {
		String termid=null;
		if(termids!=null && termids.size()>0)
		   termid=termids.get(0);
 		fillGeneTable(termid);
 		fillProbidsTable(termid);
 		
 		//checkBoxexcludzeros.setSelected(false);
	}
	
	/**
	 * Fill gene table.
	 *
	 * @param termid the termid
	 */
	protected void fillGeneTable(String termid) {
		
		if(termid!=null) {
			ArrayList<String> genes=results.getGoterm2associatedgenes(termid);
			if(genes!=null) {
				geneidstable.clearTable();
				List<Object[]> listobj =new ArrayList<>();
			
				for (int i = 0; i < genes.size(); i++) {
					listobj.add(new Object[] {genes.get(i)});
				}
				geneidstable.setData(listobj);
			
			}
			else
				geneidstable.clearTable();
		}
		else
			geneidstable.clearTable();
		
	}
	

	/**
	 * Fill probids table.
	 *
	 * @param termid the termid
	 */
	protected void fillProbidsTable(String termid) {
		
		if(termid!=null) {
			ArrayList<String> probsids=results.getGoterm2associatprobids(termid);
			if(probsids!=null) {
				probidstable.clearTable();
				List<Object[]> listobj =new ArrayList<>();
			
				for (int i = 0; i < probsids.size(); i++) {
					listobj.add(new Object[] {probsids.get(i)});
				}
				probidstable.setData(listobj);
			
			}
			else
				probidstable.clearTable();
		}
		else
			probidstable.clearTable();
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent evt) {
		final int key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            final int row = tableresults.getSelectedRow();
            String termid=termids.get(row);
    		fillGeneTable(termid);
    		fillProbidsTable(termid);
        }
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row=tableresults.rowAtPoint(e.getPoint());
		String termid=termids.get(row);
		fillGeneTable(termid);
		fillProbidsTable(termid);
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

}
