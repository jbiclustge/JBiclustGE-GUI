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
package jbiclustgegui.gui.components.dialogs.biclusters;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustge.results.biclusters.containers.BiclusterResult;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.OntologizerExecutionOptionPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.TopGOExecutionOptionPanel;

import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import java.awt.GridBagConstraints;
import jbiclustgegui.gui.components.tables.data.mergedtables.DataMatrixMainTable;
import jbiclustgegui.gui.methods.BiclicMethodGUI;

import java.util.ArrayList;
import java.awt.Insets;
import jbiclustgegui.gui.components.panels.data.DataMatrixPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusterMatrixViewDialog.
 */
public class BiclusterMatrixViewDialog extends JDialog implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The cancel button. */
	private JButton cancelButton;
	
	/** The data matrix panel. */
	private DataMatrixPanel dataMatrixPanel;
	
	/** The close. */
	private static String CLOSE="close";
    
    /** The Constant CLOSE_OPTION. */
    public static final int CLOSE_OPTION = 0;
    
    /** The active. */
    private boolean active=true;
    
    /** The cmdoperation. */
    private int cmdoperation=-1;
    
    /** The text fieldbicnumber. */
    private JTextField textFieldbicnumber;
    


	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			BiclusterMatrixViewDialog dialog = new BiclusterMatrixViewDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Instantiates a new bicluster matrix view dialog.
	 */
	public BiclusterMatrixViewDialog() {
		initGUI();
	}
	
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		setBounds(100, 100, 957, 624);
		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1,1};
		gbl_contentPanel.rowHeights = new int[]{1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0,0.0};
		gbl_contentPanel.rowWeights = new double[]{0.02,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[]{1.0,1.0};
				gbl_panel_1.rowWeights = new double[]{1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					JLabel lblBicluster = new JLabel("Bicluster:");
					GridBagConstraints gbc_lblBicluster = new GridBagConstraints();
					gbc_lblBicluster.anchor = GridBagConstraints.EAST;
					gbc_lblBicluster.insets = new Insets(0, 0, 0, 5);
					gbc_lblBicluster.gridx = 0;
					gbc_lblBicluster.gridy = 0;
					panel_1.add(lblBicluster, gbc_lblBicluster);
				}
				{
					this.textFieldbicnumber = new JTextField();
					GridBagConstraints gbc_textFieldbicnumber = new GridBagConstraints();
					gbc_textFieldbicnumber.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldbicnumber.gridx = 1;
					gbc_textFieldbicnumber.gridy = 0;
					panel_1.add(this.textFieldbicnumber, gbc_textFieldbicnumber);
					this.textFieldbicnumber.setColumns(10);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
		}
		{
			dataMatrixPanel = new DataMatrixPanel();
			GridBagConstraints gbc_dataMatrixPanel = new GridBagConstraints();
			gbc_dataMatrixPanel.insets = new Insets(0, 0, 0, 5);
			gbc_dataMatrixPanel.fill = GridBagConstraints.BOTH;
			gbc_dataMatrixPanel.gridx = 0;
			gbc_dataMatrixPanel.gridy = 1;
			contentPanel.add(dataMatrixPanel, gbc_dataMatrixPanel);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}*/
			{
				cancelButton = new JButton("Close");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand(CLOSE);
			}
		}

	}
	


	/**
	 * Show open dialog.
	 *
	 * @param bicluster the bicluster
	 * @param index the index
	 * @param parent the parent
	 * @return the int
	 * @throws HeadlessException the headless exception
	 * @throws InterruptedException the interrupted exception
	 */
	public int showOpenDialog(BiclusterResult bicluster,int index, Component parent) throws HeadlessException, InterruptedException {

		dataMatrixPanel.setData(bicluster.getGeneNamesOrderedAsExpressionDataset(),
				bicluster.getConditionNamesOrderedAsExpressionDataset(), 
				bicluster.getOrderedBiclusterExpressionDatabyRowDimension(), true);
		textFieldbicnumber.setText(String.valueOf(index));
		textFieldbicnumber.setEditable(false);
		return showDialog(parent);
	} 
	

	/**
	 * Show dialog.
	 *
	 * @param parent the parent
	 * @return the int
	 * @throws InterruptedException the interrupted exception
	 */
	protected int showDialog(Component parent) throws InterruptedException {
		
		setAlwaysOnTop(true);
		setModal(true);
		Component parent1=SwingUtilities.windowForComponent(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if(parent!=null)
			this.setLocationRelativeTo(parent);
		else
			this.setLocationRelativeTo(parent1);
		
		setVisible(true);
		
	/*	while (active) {
           Thread.sleep(100);
		}*/
		
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
	

	/**
	 * Open dialog.
	 *
	 * @param bicluster the bicluster
	 * @param bicindex the bicindex
	 * @param parent the parent
	 * @throws HeadlessException the headless exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void openDialog(BiclusterResult bicluster,int bicindex, Component parent) throws HeadlessException, InterruptedException {
		BiclusterMatrixViewDialog dialog=new BiclusterMatrixViewDialog();
		dialog.showOpenDialog(bicluster,bicindex, parent);
	}

	
}
