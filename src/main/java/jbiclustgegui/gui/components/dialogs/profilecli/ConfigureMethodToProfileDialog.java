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
package jbiclustgegui.gui.components.dialogs.profilecli;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.javatuples.Pair;

import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.analysis.profilecli.components.ProfileBiclusteringMethod;
import jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigureMethodToProfileDialog.
 */
public class ConfigureMethodToProfileDialog extends JDialog implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
    
    /** The methodpanel. */
    private AbstractMethodSettingsPanel methodpanel;
    
    /** The mainpanel. */
    private JPanel mainpanel;
    
    /** The spinner. */
    private JSpinner spinner;
    
    /** The cmdoperation. */
    private int cmdoperation=-1;
    
    /** The Constant APPROVE_OPTION. */
    public static final int APPROVE_OPTION = 0;
    
    /** The Constant CANCEL_OPTION. */
    public static final int CANCEL_OPTION = 1;
    
    /** The close. */
    private static String CLOSE="close";
    
    /** The ok. */
    private static String OK="ok";
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ConfigureMethodToProfileDialog dialog = new ConfigureMethodToProfileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Instantiates a new configure method to profile dialog.
	 */
	public ConfigureMethodToProfileDialog() {
		initGUI();
	}
	
	/**
	 * Create the dialog.
	 */
	private void initGUI(){
		setBounds(100, 100, 628, 542);
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
			mainpanel = new JPanel();
			GridBagConstraints gbc_mainpanel = new GridBagConstraints();
			gbc_mainpanel.fill = GridBagConstraints.BOTH;
			gbc_mainpanel.gridx = 0;
			gbc_mainpanel.gridy = 0;
			contentPanel.add(mainpanel, gbc_mainpanel);
			GridBagLayout gbl_mainpanel = new GridBagLayout();
			gbl_mainpanel.columnWidths = new int[]{1};
			gbl_mainpanel.rowHeights = new int[]{1,1};
			gbl_mainpanel.columnWeights = new double[]{1.0};
			gbl_mainpanel.rowWeights = new double[]{1.0,0.05};
			mainpanel.setLayout(gbl_mainpanel);
		/*	{
				JPanel panelmethods = new JPanel();
				GridBagConstraints gbc_panelmethods = new GridBagConstraints();
				gbc_panelmethods.insets = new Insets(0, 0, 5, 0);
				gbc_panelmethods.fill = GridBagConstraints.BOTH;
				gbc_panelmethods.gridx = 0;
				gbc_panelmethods.gridy = 0;
				mainpanel.add(panelmethods, gbc_panelmethods);
			}*/
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 1;
				mainpanel.add(panel, gbc_panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{1,1,1,1};
				gbl_panel.rowHeights = new int[]{1};
				gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0};
				gbl_panel.rowWeights = new double[]{1.0};
				panel.setLayout(gbl_panel);
				{
					JLabel lblNumberExecutions = new JLabel("Number executions:");
					GridBagConstraints gbc_lblNumberExecutions = new GridBagConstraints();
					gbc_lblNumberExecutions.anchor = GridBagConstraints.EAST;
					gbc_lblNumberExecutions.insets = new Insets(0, 0, 0, 5);
					gbc_lblNumberExecutions.gridx = 0;
					gbc_lblNumberExecutions.gridy = 0;
					panel.add(lblNumberExecutions, gbc_lblNumberExecutions);
				}
				{
					spinner = new JSpinner();
					spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
					GridBagConstraints gbc_spinner = new GridBagConstraints();
					gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinner.insets = new Insets(0, 0, 0, 5);
					gbc_spinner.gridx = 1;
					gbc_spinner.gridy = 0;
					panel.add(spinner, gbc_spinner);
				}
			}
		}
		/*{
			JPanel methodspanel = new JPanel();
			GridBagConstraints gbc_methodspanel = new GridBagConstraints();
			gbc_methodspanel.fill = GridBagConstraints.BOTH;
			gbc_methodspanel.gridx = 0;
			gbc_methodspanel.gridy = 0;
			contentPanel.add(methodspanel, gbc_methodspanel);
		}*/
		{
			JPanel comandspanel = new JPanel();
			comandspanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(comandspanel, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				comandspanel.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				comandspanel.add(cancelButton);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand(CLOSE);
			}
		}
	}
	
	
	
	/**
	 * Show open dialog.
	 *
	 * @param method the method
	 * @param proj the proj
	 * @param parent the parent
	 * @return the int
	 * @throws InterruptedException the interrupted exception
	 */
	public int showOpenDialog(ProfileBiclusteringMethod method,Project proj, Component parent) throws InterruptedException {
		
		//remove(methodpanel);
		methodpanel = method.getSettingsPanelOfMethod();
		methodpanel.resetToDefaultValues();
		methodpanel.setCurrentProject(proj);
		GridBagConstraints gbc_panelmethods = new GridBagConstraints();
		gbc_panelmethods.insets = new Insets(0, 0, 5, 0);
		gbc_panelmethods.fill = GridBagConstraints.BOTH;
		gbc_panelmethods.gridx = 0;
		gbc_panelmethods.gridy = 0;
		mainpanel.add(methodpanel, gbc_panelmethods);
		setSize(method.getSettingsPanelOfMethod().getPreferredDimensions());
		setTitle(method.getBiclusteringMethod().getAlgorithmName()+" Configuration");
		
		return showDialog(parent);
	}
	
	/**
	 * Gets the method configuration.
	 *
	 * @return the method configuration
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Properties getMethodConfiguration() throws IOException {
		return methodpanel.getMethodSettings();
	}
	
	/**
	 * Gets the number times to execute.
	 *
	 * @return the number times to execute
	 */
	public int getNumberTimesToExecute() {
		return (int) spinner.getValue();
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
			dispose();

		}
		else if(cmd.equals(OK)) {
			Pair<Boolean, String> isvalid=methodpanel.validSettings();
			if(isvalid!=null && !isvalid.getValue0())
				launchWarning(isvalid.getValue1());
			else {
				cmdoperation=APPROVE_OPTION;
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
