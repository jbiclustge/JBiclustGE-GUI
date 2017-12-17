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
package jbiclustgegui.gui.components.dialogs.gsea;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import jbiclustge.enrichmentanalysistools.common.EnrichmentAnalysisResultList;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.GSEAPvalueFrequencyTermsChartPanel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;

// TODO: Auto-generated Javadoc
/**
 * The Class GSEABiclusterListTermsFrequencyChartPanel.
 */
public class GSEABiclusterListTermsFrequencyChartPanel extends JDialog implements ActionListener{

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
    
    /** The panelfreqtermspanel. */
    private GSEAPvalueFrequencyTermsChartPanel panelfreqtermspanel;
    
    /** The active. */
    private boolean active=true;
    
    /** The cmdoperation. */
    private int cmdoperation=-1;
    
    /** The close. */
    private static String CLOSE="close";
    
    /** The Constant CLOSE_OPTION. */
    public static final int CLOSE_OPTION = 0;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			GSEABiclusterListTermsFrequencyChartPanel dialog = new GSEABiclusterListTermsFrequencyChartPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new GSEA bicluster list terms frequency chart panel.
	 */
	public GSEABiclusterListTermsFrequencyChartPanel() {
		initGUI();
	}
	

	/**
	 * Create the dialog.
	 */
	public void initGUI() {
		setBounds(100, 100, 1120, 690);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			panelfreqtermspanel = new GSEAPvalueFrequencyTermsChartPanel();
			GridBagLayout gridBagLayout = (GridBagLayout) this.panelfreqtermspanel.getLayout();
			gridBagLayout.rowWeights = new double[]{1.0};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0};
			GridBagConstraints gbc_panelfreqtermspanel = new GridBagConstraints();
			gbc_panelfreqtermspanel.fill = GridBagConstraints.BOTH;
			gbc_panelfreqtermspanel.gridx = 0;
			gbc_panelfreqtermspanel.gridy = 0;
			contentPanel.add(panelfreqtermspanel, gbc_panelfreqtermspanel);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}*/
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.setActionCommand(CLOSE);
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CLOSE);
				cancelButton.addActionListener(this);
			}
		}
	}
	

	/**
	 * Show dialog.
	 *
	 * @param results the results
	 * @param pvalue the pvalue
	 * @param useadjustedpvalues the useadjustedpvalues
	 * @param parent the parent
	 * @return the int
	 * @throws InterruptedException the interrupted exception
	 */
	public int showDialog(EnrichmentAnalysisResultList results,double pvalue,boolean useadjustedpvalues,Component parent) throws InterruptedException {
		
		panelfreqtermspanel.addResults(results, pvalue, useadjustedpvalues);
		setAlwaysOnTop(true);
		setModal(true);
		Component parent1=SwingUtilities.windowForComponent(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if(parent!=null)
			this.setLocationRelativeTo(parent);
		else
			this.setLocationRelativeTo(parent1);
		
		setVisible(true);
		
		return cmdoperation;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(CLOSE)) {
			cmdoperation=CLOSE_OPTION;
			active=false;
			dispose();
			
		}
		
	}

}
