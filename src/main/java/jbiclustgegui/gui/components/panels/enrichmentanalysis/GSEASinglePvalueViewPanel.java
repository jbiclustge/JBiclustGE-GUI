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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultsContainer;
import jbiclustge.utils.properties.JBiGePropertiesManager;
import jbiclustgegui.gui.components.dialogs.gsea.GSEABiclusterListTermsFrequencyChartPanel;
import jbiclustgegui.gui.components.tables.biclusters.BiclustersTable;
import pt.ornrocha.collections.MTUMapUtils;
import pt.ornrocha.swingutils.textfield.DoubleTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEASinglePvalueViewPanel.
 */
public class GSEASinglePvalueViewPanel extends JPanel implements MouseListener,KeyListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl new label. */
	private JLabel lblNewLabel;
	
	/** The double text fieldpvalue. */
	private DoubleTextField doubleTextFieldpvalue;
	
	/** The btn change. */
	private JButton btnChange;
	
	/** The scroll panebiclusters. */
	private JScrollPane scrollPanebiclusters;
	
	/** The chckbx adjusted pvalues. */
	private JCheckBox chckbxAdjustedPvalues;
	
	/** The chckbx remove biclusters without. */
	private JCheckBox chckbxRemoveBiclustersWithout;
	
	/** The panel. */
	private JPanel panel;
	
	/** The chckbx view all biclusters. */
	private JCheckBox chckbxViewAllBiclusters;
	
	/** The results. */
	private EnrichmentAnalysisResultList results;
	
	/** The tablebiclusters. */
	private BiclustersTable tablebiclusters;
	
	/** The allfilteredpvaluegsea. */
	private LinkedHashMap<Integer, FilteredBiclusterGSEAResultContainer> allfilteredpvaluegsea;
	
	/** The onlyenrichedpvaluegsea. */
	private LinkedHashMap<Integer, FilteredBiclusterGSEAResultContainer> onlyenrichedpvaluegsea;
	
	/** The enrichedbiclustersmap. */
	private LinkedHashMap<Integer, Integer> enrichedbiclustersmap;
	
	/** The fillcomponentsstarted. */
	private boolean fillcomponentsstarted=false;
	
	/** The single bicluster GSE info panel. */
	private SingleBiclusterGSEInfoPanel singleBiclusterGSEInfoPanel;
	
	/** The lastpvalue. */
	private double lastpvalue=0.05;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	/**
	 * Instantiates a new GSEA single pvalue view panel.
	 */
	public GSEASinglePvalueViewPanel() {
		initGUI();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblNewLabel = new JLabel("p-value:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(this.lblNewLabel, gbc_lblNewLabel);
		
		this.doubleTextFieldpvalue = new DoubleTextField();
		this.doubleTextFieldpvalue.setText("0.05");
		GridBagConstraints gbc_doubleTextFieldpvalue = new GridBagConstraints();
		gbc_doubleTextFieldpvalue.gridwidth = 2;
		gbc_doubleTextFieldpvalue.insets = new Insets(0, 0, 5, 5);
		gbc_doubleTextFieldpvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleTextFieldpvalue.gridx = 1;
		gbc_doubleTextFieldpvalue.gridy = 0;
		add(this.doubleTextFieldpvalue, gbc_doubleTextFieldpvalue);
		
		this.chckbxAdjustedPvalues = new JCheckBox("adjusted p-values");
		GridBagConstraints gbc_chckbxAdjustedPvalues = new GridBagConstraints();
		gbc_chckbxAdjustedPvalues.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAdjustedPvalues.gridx = 3;
		gbc_chckbxAdjustedPvalues.gridy = 0;
		add(this.chckbxAdjustedPvalues, gbc_chckbxAdjustedPvalues);
		
		this.btnChange = new JButton("Change");
		this.btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					changePvalue();
				} catch (InstantiationException | IllegalAccessException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		GridBagConstraints gbc_btnChange = new GridBagConstraints();
		gbc_btnChange.anchor = GridBagConstraints.WEST;
		gbc_btnChange.insets = new Insets(0, 0, 5, 5);
		gbc_btnChange.gridx = 4;
		gbc_btnChange.gridy = 0;
		add(this.btnChange, gbc_btnChange);
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0,0.0};
		this.panel.setLayout(gbl_panel);
		
		this.chckbxViewAllBiclusters = new JCheckBox("View all biclusters");
		this.chckbxViewAllBiclusters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxViewAllBiclusters.isSelected())
					viewAllBiclusterCMD();
				else {
					chckbxRemoveBiclustersWithout.setSelected(true);
					viewOnlyEnrichedBiclusters();
				}
			}
		});
		GridBagConstraints gbc_chckbxViewAllBiclusters = new GridBagConstraints();
		gbc_chckbxViewAllBiclusters.anchor = GridBagConstraints.WEST;
		gbc_chckbxViewAllBiclusters.insets = new Insets(0, 30, 5, 0);
		gbc_chckbxViewAllBiclusters.gridx = 0;
		gbc_chckbxViewAllBiclusters.gridy = 0;
		this.panel.add(this.chckbxViewAllBiclusters, gbc_chckbxViewAllBiclusters);
		
		this.chckbxRemoveBiclustersWithout = new JCheckBox("Remove biclusters without enriched genes");
		this.chckbxRemoveBiclustersWithout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxRemoveBiclustersWithout.isSelected())
					viewOnlyEnrichedBiclusters();
				else {
					chckbxViewAllBiclusters.setSelected(true);
					viewAllBiclusterCMD();
					
				}
			}
		});
		GridBagConstraints gbc_chckbxRemoveBiclustersWithout = new GridBagConstraints();
		gbc_chckbxRemoveBiclustersWithout.insets = new Insets(0, 30, 0, 0);
		gbc_chckbxRemoveBiclustersWithout.anchor = GridBagConstraints.WEST;
		gbc_chckbxRemoveBiclustersWithout.gridx = 0;
		gbc_chckbxRemoveBiclustersWithout.gridy = 1;
		this.panel.add(this.chckbxRemoveBiclustersWithout, gbc_chckbxRemoveBiclustersWithout);
		
		tablebiclusters=new BiclustersTable();
		tablebiclusters.addMouseListener(this);
		tablebiclusters.addKeyListener(this);
		this.scrollPanebiclusters = new JScrollPane(tablebiclusters);
		GridBagConstraints gbc_scrollPanebiclusters = new GridBagConstraints();
		gbc_scrollPanebiclusters.gridheight = 12;
		gbc_scrollPanebiclusters.gridwidth = 2;
		gbc_scrollPanebiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPanebiclusters.fill = GridBagConstraints.BOTH;
		gbc_scrollPanebiclusters.gridx = 0;
		gbc_scrollPanebiclusters.gridy = 2;
		add(this.scrollPanebiclusters, gbc_scrollPanebiclusters);
		
		this.singleBiclusterGSEInfoPanel = new SingleBiclusterGSEInfoPanel();
		GridBagConstraints gbc_singleBiclusterGSEInfoPanel = new GridBagConstraints();
		gbc_singleBiclusterGSEInfoPanel.gridheight = 11;
		gbc_singleBiclusterGSEInfoPanel.gridwidth = 13;
		gbc_singleBiclusterGSEInfoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_singleBiclusterGSEInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_singleBiclusterGSEInfoPanel.gridx = 2;
		gbc_singleBiclusterGSEInfoPanel.gridy = 2;
		add(this.singleBiclusterGSEInfoPanel, gbc_singleBiclusterGSEInfoPanel);
		
		this.btnNewButton = new JButton("Show GO Terms Frequency");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					showGOTermsFrequencyDialog();
				} catch (InterruptedException e1) {
					Workbench.getInstance().error(e1);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 13;
		add(this.btnNewButton, gbc_btnNewButton);
	}
	
	
	/**
	 * Adds the results.
	 *
	 * @param results the results
	 * @throws NumberFormatException the number format exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public void addResults(EnrichmentAnalysisResultList results) throws NumberFormatException, InstantiationException, IllegalAccessException {
		this.results=results;
		if(!results.wasUsedMCTMethod()) {
			chckbxAdjustedPvalues.setEnabled(false);
			chckbxAdjustedPvalues.setSelected(false);
		}
		else {
			chckbxAdjustedPvalues.setSelected(true);
		}
		
		tablebiclusters.addListofBiclusters(results.getBiclusterlistAssociated());
		chckbxViewAllBiclusters.setSelected(true);
		chckbxRemoveBiclustersWithout.setSelected(false);
		if(!doubleTextFieldpvalue.getText().isEmpty())
			updateDataToView(Double.parseDouble(doubleTextFieldpvalue.getText()), chckbxAdjustedPvalues.isSelected());
		
		fillComponents();
	}
	
	
	/**
	 * Fill components.
	 */
	protected void fillComponents() {
		
		if(chckbxViewAllBiclusters.isSelected() && fillcomponentsstarted)
			tablebiclusters.showAllBiclustersIDS();
		else if(chckbxRemoveBiclustersWithout.isSelected() && fillcomponentsstarted) {
			ArrayList<Integer> filteredbics=new ArrayList<>(enrichedbiclustersmap.values());
			tablebiclusters.showOnlyBiclusters(filteredbics);
		}
		
		singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(allfilteredpvaluegsea.get(0),1);
		
		fillcomponentsstarted=true;
	}
	
	
	/**
	 * View all bicluster CMD.
	 */
	protected void viewAllBiclusterCMD() {

			tablebiclusters.showAllBiclustersIDS();
			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(allfilteredpvaluegsea.get(0),1);
			chckbxRemoveBiclustersWithout.setSelected(false);

		
	}
	
	/**
	 * View only enriched biclusters.
	 */
	protected void viewOnlyEnrichedBiclusters() {
			ArrayList<Integer> filteredbics=new ArrayList<>(enrichedbiclustersmap.values());
			tablebiclusters.showOnlyBiclusters(filteredbics);
			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(onlyenrichedpvaluegsea.get(0),enrichedbiclustersmap.get(0)+1);
			chckbxViewAllBiclusters.setSelected(false);
	}
	
	/**
	 * Change pvalue.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	protected void changePvalue() throws InstantiationException, IllegalAccessException {
		
		if(doubleTextFieldpvalue.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please set a value in the \"p-value\" field");
		}
		else {
			double val=0.05;
			Object value=JBiGePropertiesManager.getManager().getKeyValue("max_p_value");
			if(value instanceof Double) {
				val=(double) value;
			}
			else if(value instanceof String) {
				try {
					val=Double.parseDouble((String) value);
				} catch (Exception e) {
					Workbench.getInstance().error("Invalid value for p-value in the properties of JBiclustGE");
				}
				
			}
			else
				Workbench.getInstance().error("Invalid value for p-value in the properties of JBiclustGE");
			
			double uservalue=Double.parseDouble(doubleTextFieldpvalue.getText());
			if(uservalue>val)
				JOptionPane.showMessageDialog(this, "The p-value must be equal or lower than "+val);
			else {
				if(uservalue!=lastpvalue) {
					updateDataToView(uservalue, chckbxAdjustedPvalues.isSelected());
					viewAllBiclusterCMD();
					lastpvalue=uservalue;
				}
			}
		}
	}
	
	
	/**
	 * Update data to view.
	 *
	 * @param pvalue the pvalue
	 * @param useadjustedpvalues the useadjustedpvalues
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	protected void updateDataToView(double pvalue, boolean useadjustedpvalues) throws InstantiationException, IllegalAccessException {
		
		allfilteredpvaluegsea=new LinkedHashMap<>();
		onlyenrichedpvaluegsea=new LinkedHashMap<>();
		enrichedbiclustersmap=new LinkedHashMap<>();
		Map<String, String> geneid2probid=null;
		Map<String, String> mapofprobset2geneidused =results.getMapofprobset2geneidused();
		if(mapofprobset2geneidused!=null)
			geneid2probid=mapofprobset2geneidused=MTUMapUtils.invertMap(mapofprobset2geneidused);
		
		int nenriched=0;
		for (int i = 0; i < results.size(); i++) {
			EnrichmentAnalysisResultsContainer bicinfo=results.get(i);
			ArrayList<String> gotermlowerpvalue=bicinfo.getTermsWithCutoff(pvalue, useadjustedpvalues);
			if(gotermlowerpvalue!=null && gotermlowerpvalue.size()>0){
				FilteredBiclusterGSEAResultContainer filterbicgsea= new FilteredBiclusterGSEAResultContainer();
				for (int j = 0; j < gotermlowerpvalue.size(); j++) {
					String termid=gotermlowerpvalue.get(j);
					Double adjpvalue=null;
					if(bicinfo.getGOTermsadjustedpvalues()!=null && useadjustedpvalues)
						adjpvalue=bicinfo.getGOTermsadjustedpvalues().get(termid);
					
					ArrayList<String> associatedgenes=null;
					if(bicinfo.getGenesAssociatedToGoTerm(termid)!=null)
						associatedgenes=bicinfo.getGenesAssociatedToGoTerm(termid);
					
					ArrayList<String> associatedprobids=null;
					if(bicinfo.getGenesAssociatedToGoTerm(termid)!=null && geneid2probid!=null)
						associatedprobids=transformgeneidtoprobsetid(bicinfo.getGenesAssociatedToGoTerm(termid),geneid2probid);
					
					filterbicgsea.appendInformation(termid, 
							bicinfo.getMapGOTermToGoName().get(termid),
							bicinfo.getGOTermspvalues().get(termid),
							adjpvalue, 
							associatedgenes,
							associatedprobids);
				}
				allfilteredpvaluegsea.put(i, filterbicgsea);
				onlyenrichedpvaluegsea.put(nenriched, filterbicgsea);
				enrichedbiclustersmap.put(nenriched, i);
				nenriched++;
			}
			else
				allfilteredpvaluegsea.put(i, new FilteredBiclusterGSEAResultContainer());
			
		}
	}
	
	
	/**
	 * Transformgeneidtoprobsetid.
	 *
	 * @param geneid the geneid
	 * @param geneid2probsetid the geneid 2 probsetid
	 * @return the array list
	 */
	private ArrayList<String> transformgeneidtoprobsetid(ArrayList<String> geneid, Map<String,String> geneid2probsetid){
		
		if(geneid2probsetid!=null) {
			ArrayList<String> res =new ArrayList<>();
		
			for (int i = 0; i < geneid.size(); i++) {
				res.add(geneid2probsetid.get(geneid.get(i)));
			}
		
			return res;
		}
		return geneid;
		
	}
	
	
	/**
	 * Show GO terms frequency dialog.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	protected void showGOTermsFrequencyDialog() throws InterruptedException {
		GSEABiclusterListTermsFrequencyChartPanel dialog=new GSEABiclusterListTermsFrequencyChartPanel();
		dialog.showDialog(results, lastpvalue, chckbxAdjustedPvalues.isSelected(), this);
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
            final int row = tablebiclusters.getSelectedRow();
            if(chckbxRemoveBiclustersWithout.isSelected()) {
    			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(onlyenrichedpvaluegsea.get(row),enrichedbiclustersmap.get(row)+1);
    		}
    		else
    			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(allfilteredpvaluegsea.get(row),row+1);
        }
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int row=tablebiclusters.rowAtPoint(e.getPoint());
		if(chckbxRemoveBiclustersWithout.isSelected()) {
			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(onlyenrichedpvaluegsea.get(row),enrichedbiclustersmap.get(row)+1);
		}
		else
			singleBiclusterGSEInfoPanel.addfilteredResultsOfBicluster(allfilteredpvaluegsea.get(row),row+1);
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
