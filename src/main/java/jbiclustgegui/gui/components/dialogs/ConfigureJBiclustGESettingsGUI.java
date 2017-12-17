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
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigureJBiclustGESettingsGUI.
 */
public class ConfigureJBiclustGESettingsGUI extends JDialog implements ActionListener,InputGUI{

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The rec. */
	private ParamsReceiver rec;
	
	/** The text field. */
	private JTextField textField;
	
	/** The chckbx automatic. */
	private JCheckBox chckbxAutomatic;
	
	/** The chckbx let me choose. */
	private JCheckBox chckbxLetMeChoose;
	
	/** The btn install at. */
	private JButton btnInstallAt;
	
	/** The text field 1. */
	private JTextField textField_1;
	
	/** The btn choose zip. */
	private JButton btnChooseZip;
	
	/** The chckbx let me choose 1. */
	private JCheckBox chckbxLetMeChoose_1;
	
	/** The chckbx download from jbiclustge. */
	private JCheckBox chckbxDownloadFromJbiclustge;
	
	/** The cancel. */
	private static String CANCEL="cancel";
	
	/** The ok. */
	private static String OK="ok";
	
	/** The autochoose. */
	private static String AUTOCHOOSE="autochoose";
	
	/** The userchoose. */
	private static String USERCHOOSE="userchoose";
	
	/** The choosefolder. */
	private static String CHOOSEFOLDER="choosefolder";
	
	/** The downloadzip. */
	private static String DOWNLOADZIP="downloadzip";
	
	/** The selectzip. */
	private static String SELECTZIP="selectzip";
	
	/** The choosezip. */
	private static String CHOOSEZIP="choosezip";

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ConfigureJBiclustGESettingsGUI dialog = new ConfigureJBiclustGESettingsGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new configure J biclust GE settings GUI.
	 */
	public ConfigureJBiclustGESettingsGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
	}
	
	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 779, 345);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Set folder where R packages will be installed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1};
			gbl_panel.rowHeights = new int[]{1,1};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[]{1.0,1.0};
				gbl_panel_1.rowWeights = new double[]{1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					chckbxAutomatic = new JCheckBox("Automatic");
					GridBagConstraints gbc_chckbxAutomatic = new GridBagConstraints();
					gbc_chckbxAutomatic.insets = new Insets(0, 0, 0, 5);
					gbc_chckbxAutomatic.gridx = 0;
					gbc_chckbxAutomatic.gridy = 0;
					panel_1.add(chckbxAutomatic, gbc_chckbxAutomatic);
					chckbxAutomatic.setSelected(true);
					chckbxAutomatic.setActionCommand(AUTOCHOOSE);
					chckbxAutomatic.addActionListener(this);
				}
				{
					chckbxLetMeChoose = new JCheckBox("Let me choose");
					GridBagConstraints gbc_chckbxLetMeChoose = new GridBagConstraints();
					gbc_chckbxLetMeChoose.gridx = 1;
					gbc_chckbxLetMeChoose.gridy = 0;
					panel_1.add(chckbxLetMeChoose, gbc_chckbxLetMeChoose);
					chckbxLetMeChoose.setSelected(false);
					chckbxLetMeChoose.setActionCommand(USERCHOOSE);
					chckbxLetMeChoose.addActionListener(this);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[]{1.0,0.2};
				gbl_panel_1.rowWeights = new double[]{1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					this.textField = new JTextField();
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.insets = new Insets(0, 0, 0, 5);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 0;
					gbc_textField.gridy = 0;
					panel_1.add(this.textField, gbc_textField);
					this.textField.setColumns(10);
					textField.setEnabled(false);
				}
				{
					btnInstallAt = new JButton("Install at...");
					GridBagConstraints gbc_btnInstallAt = new GridBagConstraints();
					gbc_btnInstallAt.gridx = 1;
					gbc_btnInstallAt.gridy = 0;
					panel_1.add(btnInstallAt, gbc_btnInstallAt);
					btnInstallAt.setEnabled(false);
					btnInstallAt.setActionCommand(CHOOSEFOLDER);
					{
						JPanel panel_2 = new JPanel();
						panel_2.setBorder(new TitledBorder(null, "Zip file containing Biclustering Algorithms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						GridBagConstraints gbc_panel_2 = new GridBagConstraints();
						gbc_panel_2.gridheight = 2;
						gbc_panel_2.fill = GridBagConstraints.BOTH;
						gbc_panel_2.gridx = 0;
						gbc_panel_2.gridy = 2;
						contentPanel.add(panel_2, gbc_panel_2);
						GridBagLayout gbl_panel_2 = new GridBagLayout();
						gbl_panel_2.columnWidths = new int[]{1};
						gbl_panel_2.rowHeights = new int[]{1,1};
						gbl_panel_2.columnWeights = new double[]{1.0};
						gbl_panel_2.rowWeights = new double[]{1.0,1.0};
						panel_2.setLayout(gbl_panel_2);
						{
							JPanel panel_3 = new JPanel();
							GridBagConstraints gbc_panel_3 = new GridBagConstraints();
							gbc_panel_3.insets = new Insets(0, 0, 5, 0);
							gbc_panel_3.fill = GridBagConstraints.BOTH;
							gbc_panel_3.gridx = 0;
							gbc_panel_3.gridy = 0;
							panel_2.add(panel_3, gbc_panel_3);
							GridBagLayout gbl_panel_3 = new GridBagLayout();
							gbl_panel_3.columnWidths = new int[]{1,1};
							gbl_panel_3.rowHeights = new int[]{1};
							gbl_panel_3.columnWeights = new double[]{1.0,1.0};
							gbl_panel_3.rowWeights = new double[]{1.0};
							panel_3.setLayout(gbl_panel_3);
							{
								chckbxDownloadFromJbiclustge = new JCheckBox("Download From JBiclustGE Website");
								GridBagConstraints gbc_chckbxDownloadFromJbiclustge = new GridBagConstraints();
								gbc_chckbxDownloadFromJbiclustge.insets = new Insets(0, 0, 0, 5);
								gbc_chckbxDownloadFromJbiclustge.gridx = 0;
								gbc_chckbxDownloadFromJbiclustge.gridy = 0;
								panel_3.add(chckbxDownloadFromJbiclustge, gbc_chckbxDownloadFromJbiclustge);
								chckbxDownloadFromJbiclustge.setSelected(true);
								chckbxDownloadFromJbiclustge.setActionCommand(DOWNLOADZIP);
								chckbxDownloadFromJbiclustge.addActionListener(this);
							}
							{
								chckbxLetMeChoose_1 = new JCheckBox("Let me choose zip file");
								GridBagConstraints gbc_chckbxLetMeChoose_1 = new GridBagConstraints();
								gbc_chckbxLetMeChoose_1.gridx = 1;
								gbc_chckbxLetMeChoose_1.gridy = 0;
								panel_3.add(chckbxLetMeChoose_1, gbc_chckbxLetMeChoose_1);
								chckbxLetMeChoose_1.setSelected(false);
								chckbxLetMeChoose_1.addActionListener(this);
								chckbxLetMeChoose_1.setActionCommand(SELECTZIP);
							}
						}
						{
							JPanel panel_3 = new JPanel();
							GridBagConstraints gbc_panel_3 = new GridBagConstraints();
							gbc_panel_3.fill = GridBagConstraints.BOTH;
							gbc_panel_3.gridx = 0;
							gbc_panel_3.gridy = 1;
							panel_2.add(panel_3, gbc_panel_3);
							GridBagLayout gbl_panel_3 = new GridBagLayout();
							gbl_panel_3.columnWidths = new int[]{1,1};
							gbl_panel_3.rowHeights = new int[]{1};
							gbl_panel_3.columnWeights = new double[]{1.0,0.2};
							gbl_panel_3.rowWeights = new double[]{1.0};
							panel_3.setLayout(gbl_panel_3);
							{
								this.textField_1 = new JTextField();
								GridBagConstraints gbc_textField_1 = new GridBagConstraints();
								gbc_textField_1.insets = new Insets(0, 0, 0, 5);
								gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
								gbc_textField_1.gridx = 0;
								gbc_textField_1.gridy = 0;
								panel_3.add(this.textField_1, gbc_textField_1);
								this.textField_1.setColumns(10);
								this.textField_1.setEnabled(false);
							}
							{
								btnChooseZip = new JButton("Choose zip");
								GridBagConstraints gbc_btnChooseZip = new GridBagConstraints();
								gbc_btnChooseZip.gridx = 1;
								gbc_btnChooseZip.gridy = 0;
								panel_3.add(btnChooseZip, gbc_btnChooseZip);
								btnChooseZip.setEnabled(false);
								btnChooseZip.setActionCommand(CHOOSEZIP);
								btnChooseZip.addActionListener(this);
							}
						}
					}
					btnInstallAt.addActionListener(this);
				}
			}
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
		
		if(cmd.equals(CANCEL))
			finish();
		else if(cmd.equals(OK)) {
			if(chckbxLetMeChoose.isSelected() && textField.getText().isEmpty())
				JOptionPane.showMessageDialog(this, "Please select folder to install R packages", "Warning",JOptionPane.WARNING_MESSAGE);
			else
				terminate();
		}
		else if(cmd.equals(AUTOCHOOSE)){
			autochooseselect();
		}
		else if(cmd.equals(USERCHOOSE)) {
			userselect();
		}
		else if(cmd.equals(CHOOSEFOLDER)) {
			choosefolder();
		}
		else if(cmd.equals(DOWNLOADZIP)) {
			if(chckbxLetMeChoose_1.isSelected()) {
				chckbxLetMeChoose_1.setSelected(false);
				textField_1.setText("");
				textField_1.setEnabled(false);
				btnChooseZip.setEnabled(false);
			}
		}
		else if(cmd.equals(SELECTZIP)) {
			if(chckbxDownloadFromJbiclustge.isSelected()) {
				chckbxDownloadFromJbiclustge.setSelected(false);
				textField_1.setEnabled(true);
				btnChooseZip.setEnabled(true);
			}
		}
		else if(cmd.equals(CHOOSEZIP) && chckbxLetMeChoose_1.isSelected()) {
			choosezipfile();
		}
		
	}
	
	/**
	 * Choosefolder.
	 */
	private void choosefolder() {
		JFileChooser chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resultChooser = chooser.showSaveDialog(this);
		if(resultChooser == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
			String filepath=chooser.getSelectedFile().getAbsolutePath();
			textField.setText(filepath);
		}
	}
	
	/**
	 * Choosezipfile.
	 */
	private void choosezipfile() {
		JFileChooser chooser=new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Compressed files (.zip)", "zip");
		chooser.addChoosableFileFilter(filter);
		int resultChooser = chooser.showSaveDialog(this);
		if(resultChooser == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
			String filepath=chooser.getSelectedFile().getAbsolutePath();
			textField_1.setText(filepath);
		}
	}
	
	/**
	 * Autochooseselect.
	 */
	private void autochooseselect() {
		if(chckbxLetMeChoose.isSelected()) {
			chckbxLetMeChoose.setSelected(false);
			textField.setText("");
			textField.setEnabled(false);
			btnInstallAt.setEnabled(false);
		}

	}
	
	/**
	 * Userselect.
	 */
	private void userselect() {
		if(chckbxAutomatic.isSelected()) {
			chckbxAutomatic.setSelected(false);
			textField.setEnabled(true);
			btnInstallAt.setEnabled(true);
		}
	}
	
	/**
	 * Terminate.
	 */
	private void terminate() {
		String saveto=null;
		if(!textField.getText().isEmpty())
			saveto=textField.getText();
		
		String zipfile=null;
		if(!textField_1.getText().isEmpty())
			zipfile=textField_1.getText();
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("rworkspacelib", String.class,saveto, null));
		listSpecs.add(new ParamSpec("algorithmszip", String.class,zipfile, null));
		
		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(new Dimension(500,200));
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

}
