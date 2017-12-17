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
 * Created inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.gui.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.datatools.expressiondata.dataset.MissingValuesInDataException;
import jbiclustgegui.gui.components.dialogs.MissingValueImputationSelector;
import jbiclustgegui.gui.components.panels.data.GeneExpressionDataPanel;
import smile.imputation.MissingValueImputation;
import smile.imputation.MissingValueImputationException;

// TODO: Auto-generated Javadoc
/**
 * The Class InitializeNewProjectGUI.
 */
public class InitializeNewProjectGUI extends JDialog implements ActionListener, InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	
	/** The dataset. */
	private ExpressionData dataset;
	
	/** The gene expression data panel. */
	private GeneExpressionDataPanel geneExpressionDataPanel;
	
	/** The text field. */
	private JTextField textField;
	
	/** The rec. */
	private ParamsReceiver rec;
	
	/** The loaddataset. */
	private static String LOADDATASET="loadsdataset";
	
	/** The ok. */
	private static String OK="ok";
	
	/** The cancel. */
	private static String CANCEL="cancel";
	

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			InitializeNewProjectGUI dialog = new InitializeNewProjectGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Instantiates a new initialize new project GUI.
	 */
	public InitializeNewProjectGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
	}
	
	
	

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 1302, 639);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 2;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1,1,1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				JButton btnNewButton = new JButton("<html><center>Load expression<br>dataset</center></html> ");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.gridy = 0;
				panel.add(btnNewButton, gbc_btnNewButton);
				btnNewButton.addActionListener(this);
				btnNewButton.setActionCommand(LOADDATASET);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 5;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[]{1.0,1.0,1.0};
				gbl_panel_1.rowWeights = new double[]{1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					JLabel lblNewLabel = new JLabel("<html><center>Please give a name<br> to your project:</center></html>");
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
					gbc_lblNewLabel.gridx = 0;
					gbc_lblNewLabel.gridy = 0;
					panel_1.add(lblNewLabel, gbc_lblNewLabel);
				}
				{
					this.textField = new JTextField();
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.gridwidth = 2;
					gbc_textField.insets = new Insets(0, 0, 0, 5);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 1;
					gbc_textField.gridy = 0;
					panel_1.add(this.textField, gbc_textField);
					this.textField.setColumns(10);
				}
			}
		}
		{
			geneExpressionDataPanel = new GeneExpressionDataPanel();
			GridBagConstraints gbc_geneExpressionDataPanel = new GridBagConstraints();
			gbc_geneExpressionDataPanel.gridwidth = 2;
			gbc_geneExpressionDataPanel.fill = GridBagConstraints.BOTH;
			gbc_geneExpressionDataPanel.gridx = 0;
			gbc_geneExpressionDataPanel.gridy = 1;
			contentPanel.add(geneExpressionDataPanel, gbc_geneExpressionDataPanel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CANCEL);
				cancelButton.addActionListener(this);
			}
		}
	}

	
	
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd=e.getActionCommand();
		
		if(cmd.equals(LOADDATASET))
			loadExpressionDataset();
		else if(cmd.equals(CANCEL))
			finish();
		else if(cmd.equals(OK)) {
			if(textField.getText()!=null && !textField.getText().isEmpty()) {
				termination();
			}
			else
				JOptionPane.showMessageDialog(this, "Please give the project a name", "Missing Parameter", JOptionPane.WARNING_MESSAGE);
				
		}
		
	}
	
	
	/**
	 * Load expression dataset.
	 */
	private void loadExpressionDataset() {
		
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	
        	try {
				dataset=ExpressionData.loadDataset(selected.getAbsolutePath(), null);
			} catch (Exception e) {
				if(e instanceof MissingValuesInDataException) {
					MissingValueImputation impmethod=launchMissingImputationMethodSelector();
					if(impmethod!=null)
						try {
							dataset=ExpressionData.loadDataset(selected.getAbsolutePath(), impmethod);
						} catch (IOException | ParseException | MissingValueImputationException e1) {
							Workbench.getInstance().error(e1);
						}
				}
			}	
        }
        if(dataset==null)
        	JOptionPane.showMessageDialog(this, "Gene Expression Dataset cannot be loaded.", "Error in Gene Expression dataset", JOptionPane.WARNING_MESSAGE);
        else {
        	geneExpressionDataPanel.loadExpressionDatasetToTable(dataset);
        }
	}
	
	

	/**
	 * Launch missing imputation method selector.
	 *
	 * @return the missing value imputation
	 */
	private MissingValueImputation launchMissingImputationMethodSelector() {
		
		MissingValueImputationSelector selector=new MissingValueImputationSelector();
		try {
			int cmd=selector.showOpenDialog(this);
			
			if(cmd==MissingValueImputationSelector.APPROVE_OPTION) {
				return selector.getImputationMethod();
			}
			
		} catch (HeadlessException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}


	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(new Dimension(1000,600));
	    pack();
	    Utilities.centerOnOwner(this);
	    setVisible(true);
		
	}


	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#onValidationError(java.lang.Throwable)
	 */
	@Override
	public void onValidationError(Throwable t) {
		Workbench.getInstance().error(t);
		
	}


	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#finish()
	 */
	@Override
	public void finish() {
		setVisible(false);
		dispose();
		
	}
	
	/**
	 * Termination.
	 */
	public void termination(){
		
		
		rec.paramsIntroduced(new ParamSpec[] {
				new ParamSpec("name", String.class,
						textField.getText() , null),
				new ParamSpec("dataset", ExpressionData.class,
						dataset, null)
						});
	}

}
