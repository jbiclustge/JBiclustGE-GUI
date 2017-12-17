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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import jbiclustge.datatools.expressiondata.dataset.MissingValueImputationMethod;
import smile.imputation.MissingValueImputation;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class MissingValueImputationSelector.
 */
public class MissingValueImputationSelector extends JDialog implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
    
    /** The panel 1. */
    private JPanel panel_1;
    
    /** The lbl new label 1. */
    private JLabel lblNewLabel_1;
    
    /** The lbl new label. */
    private JLabel lblNewLabel;
    
    /** The combo box. */
    private JComboBox comboBox;
    
    /** The spinner. */
    private JSpinner spinner;
    
    /** The ok button. */
    private JButton okButton;
    
    
    
    /** The choosemethod. */
    private static String CHOOSEMETHOD="choosemethod";
    
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
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			MissingValueImputationSelector dialog = new MissingValueImputationSelector();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new missing value imputation selector.
	 */
	public MissingValueImputationSelector() {
		initGUI();
		addParameterPanel("Number of neighbors",1);
	}

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 567, 237);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new TitledBorder(null, "Missing value imputation method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Select method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				comboBox = new JComboBox();
				DefaultComboBoxModel combomodel=new DefaultComboBoxModel<>(MissingValueImputationMethod.values());
				comboBox.setModel(combomodel);
				comboBox.setSelectedIndex(2);
				comboBox.setActionCommand(CHOOSEMETHOD);
				comboBox.addActionListener(this);
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 0;
				gbc_comboBox.gridy = 0;
				panel.add(comboBox, gbc_comboBox);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1};
			gbl_panel.rowHeights = new int[]{1,1};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					lblNewLabel = new JLabel(((MissingValueImputationMethod)comboBox.getSelectedItem()).getDescription());
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblNewLabel.gridx = 1;
					gbc_lblNewLabel.gridy = 0;
					panel_1.add(lblNewLabel, gbc_lblNewLabel);
				}
			}
			{
				panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1,1,1,1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
				gbl_panel_1.rowWeights = new double[]{1.0};
				panel_1.setLayout(gbl_panel_1);
				/*{
					JLabel lblNewLabel_1 = new JLabel("New label");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.gridwidth = 2;
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
					gbc_lblNewLabel_1.gridx = 0;
					gbc_lblNewLabel_1.gridy = 0;
					panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
				}
				{
					JSpinner spinner = new JSpinner();
					GridBagConstraints gbc_spinner = new GridBagConstraints();
					gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinner.insets = new Insets(0, 0, 0, 5);
					gbc_spinner.gridx = 2;
					gbc_spinner.gridy = 0;
					panel_1.add(spinner, gbc_spinner);
				}*/
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand(CLOSE);
			}
		}
	}
	
	
	/**
	 * Adds the parameter panel.
	 *
	 * @param label the label
	 * @param minparam the minparam
	 */
	private void addParameterPanel(String label,int minparam) {
		
		lblNewLabel_1 = new JLabel(label);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(4, minparam, 50, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 0;
		panel_1.add(spinner, gbc_spinner);
	}
	
	
	
	/**
	 * Change method.
	 */
	private void changeMethod() {
		MissingValueImputationMethod method=(MissingValueImputationMethod) comboBox.getSelectedItem();
		lblNewLabel.setText(method.getDescription());
		
		if(method.equals(MissingValueImputationMethod.AverageImputation) || method.equals(MissingValueImputationMethod.ZeroValueImputation))
			panel_1.removeAll();
		else {
			String label=null;
			int min=1;
			if(method.equals(MissingValueImputationMethod.KMeansImputation)){
			    label="Number of clusters";
			    min=2;
			}
			else if(method.equals(MissingValueImputationMethod.KNNImputation))
				label="Number of neighbors";
			else if(method.equals(MissingValueImputationMethod.LLSImputation))
				label="Number of similar rows";
			else if(method.equals(MissingValueImputationMethod.SVDImputation))
					label="Number of eigenvectors";
			
			panel_1.removeAll();
			addParameterPanel(label,min);
		}
	}
	
	/**
	 * Adds the listener to ok button.
	 *
	 * @param l the l
	 */
	public void addListenerToOkButton(ActionListener l) {
		okButton.addActionListener(l);
		okButton.setActionCommand(OK);
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
	
	
	/**
	 * Gets the imputation method.
	 *
	 * @return the imputation method
	 */
	public MissingValueImputation getImputationMethod() {
		MissingValueImputationMethod method=(MissingValueImputationMethod) comboBox.getSelectedItem();
		if(method.equals(MissingValueImputationMethod.AverageImputation) || method.equals(MissingValueImputationMethod.ZeroValueImputation))
			return method.getInstance();
		else 
			return method.getInstanceWithParameter((int) spinner.getValue());

	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(CHOOSEMETHOD)) {
			changeMethod();
		}
		else if(cmd.equals(CLOSE)) {
			cmdoperation=CANCEL_OPTION;
			active=false;
			dispose();
			
		}
		else if(cmd.equals(OK)) {
			cmdoperation=APPROVE_OPTION;
			active=false;
			dispose();
		}
			

	}
	
	
	
	

}
