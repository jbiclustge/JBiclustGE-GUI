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
package jbiclustgegui.gui.components.dialogs;

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

// TODO: Auto-generated Javadoc
/**
 * The Class GSEAConfigurationDialog.
 */
public class GSEAConfigurationDialog extends JDialog implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The typeanalyser. */
	private GSEAAnalyserType typeanalyser;
	
	/** The panelsettings. */
	private AbstractGeneEnrichmentAnalyserSettingsPanel panelsettings;
	
	/** The ok button. */
	private JButton okButton;
	
	/** The cancel button. */
	private JButton cancelButton;
	
	/** The close. */
	private static String CLOSE="close";
    
    /** The ok. */
    private static String OK="ok";
	
	/** The Constant APPROVE_OPTION. */
	public static final int APPROVE_OPTION = 0;
    
    /** The Constant CANCEL_OPTION. */
    public static final int CANCEL_OPTION = 1;
    
    /** The active. */
    private boolean active=true;
    
    /** The cmdoperation. */
    private int cmdoperation=-1;
    
    /**
     * The Enum MODEOPERATION.
     */
    public enum MODEOPERATION{
    	
    }

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			GSEAConfigurationDialog dialog = new GSEAConfigurationDialog(GSEAAnalyserType.Ontologizer);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Instantiates a new GSEA configuration dialog.
	 *
	 * @param typeanalyser the typeanalyser
	 */
	public GSEAConfigurationDialog(GSEAAnalyserType typeanalyser) {
		this.typeanalyser=typeanalyser;
		initGUI();
	}
	
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		setBounds(100, 100, 704, 509);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new TitledBorder(null, "Setup for "+typeanalyser.toString()+" GSEA Analyser", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			panelsettings = getSettingsPanel();
			GridBagConstraints gbc_panelsettings = new GridBagConstraints();
			gbc_panelsettings.fill = GridBagConstraints.BOTH;
			gbc_panelsettings.gridx = 0;
			gbc_panelsettings.gridy = 0;
			contentPanel.add(panelsettings, gbc_panelsettings);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand(CLOSE);
			}
		}
		this.setSize(panelsettings.getPreferredDimensionsToPanel());
	}
	
	/**
	 * Gets the settings panel.
	 *
	 * @return the settings panel
	 */
	private AbstractGeneEnrichmentAnalyserSettingsPanel getSettingsPanel() {
		if(typeanalyser.equals(GSEAAnalyserType.Ontologizer))
			return new OntologizerExecutionOptionPanel();
		else
			return new TopGOExecutionOptionPanel();
	}
	
	/**
	 * Gets the GSEA settings.
	 *
	 * @return the GSEA settings
	 */
	public Properties getGSEASettings() {
		return panelsettings.getExecutionSettings();
	}
	
	
	
	/**
	 * Show open dialog.
	 *
	 * @param parent the parent
	 * @return the int
	 * @throws HeadlessException the headless exception
	 * @throws InterruptedException the interrupted exception
	 */
	public int showOpenDialog(Component parent) throws HeadlessException, InterruptedException {
		return showDialog(parent);
	} 
	
	/**
	 * Show open dialog.
	 *
	 * @param gseaprops the gseaprops
	 * @param parent the parent
	 * @return the int
	 * @throws HeadlessException the headless exception
	 * @throws InterruptedException the interrupted exception
	 */
	public int showOpenDialog(Properties gseaprops, Component parent) throws HeadlessException, InterruptedException {
		panelsettings.loadProperties(gseaprops);
		okButton.setText("Apply changes");
		cancelButton.setText("Close");
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
		
		while (active) {
           Thread.sleep(100);
		}
		
		return cmdoperation;
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(CLOSE)) {
			cmdoperation=CANCEL_OPTION;
			active=false;
			dispose();
			
		}
		else if(cmd.equals(OK)) {
			Pair<Boolean, String> isvalid=panelsettings.validSettings();
			if(isvalid!=null && !isvalid.getValue0())
				launchWarning(isvalid.getValue1());
			else {
				cmdoperation=APPROVE_OPTION;
				active=false;
				dispose();
			}
		}
		
	}
	
	/**
	 * Launch warning.
	 *
	 * @param msg the msg
	 */
	private void launchWarning(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Incorrect Parameters", JOptionPane.WARNING_MESSAGE);
	}

	
}
