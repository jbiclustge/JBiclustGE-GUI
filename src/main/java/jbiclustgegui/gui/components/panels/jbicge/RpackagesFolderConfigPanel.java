package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;

public class RpackagesFolderConfigPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JCheckBox chckbxAutomatic;
	private JCheckBox chckbxLetMeChoose;
	private JButton btnInstallAt;
	
	/** The autochoose. */
	private static String AUTOCHOOSE="autochoose";
	
	/** The userchoose. */
	private static String USERCHOOSE="userchoose";
	
	/** The choosefolder. */
	private static String CHOOSEFOLDER="choosefolder";
	
	
	public RpackagesFolderConfigPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Set folder where R packages will be installed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1, 0};
		gbl_panel.rowHeights = new int[]{1, 1, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1, 1, 0};
		gbl_panel_1.rowHeights = new int[]{1, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		chckbxAutomatic = new JCheckBox("Automatic");
		chckbxAutomatic.setSelected(true);
		chckbxAutomatic.setActionCommand(AUTOCHOOSE);
		chckbxAutomatic.addActionListener(this);
		GridBagConstraints gbc_chckbxAutomatic = new GridBagConstraints();
		gbc_chckbxAutomatic.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxAutomatic.gridx = 0;
		gbc_chckbxAutomatic.gridy = 0;
		panel_1.add(chckbxAutomatic, gbc_chckbxAutomatic);
		
		chckbxLetMeChoose = new JCheckBox("Let me choose");
		chckbxLetMeChoose.setSelected(false);
		chckbxLetMeChoose.setActionCommand(USERCHOOSE);
		chckbxLetMeChoose.addActionListener(this);
		GridBagConstraints gbc_chckbxLetMeChoose = new GridBagConstraints();
		gbc_chckbxLetMeChoose.gridx = 1;
		gbc_chckbxLetMeChoose.gridy = 0;
		panel_1.add(chckbxLetMeChoose, gbc_chckbxLetMeChoose);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1, 1, 0};
		gbl_panel_2.rowHeights = new int[]{1, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.2, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);
		
		btnInstallAt = new JButton("Install at...");
		btnInstallAt.setEnabled(false);
		btnInstallAt.setActionCommand(CHOOSEFOLDER);
		btnInstallAt.addActionListener(this);
		GridBagConstraints gbc_btnInstallAt = new GridBagConstraints();
		gbc_btnInstallAt.gridx = 1;
		gbc_btnInstallAt.gridy = 0;
		panel_2.add(btnInstallAt, gbc_btnInstallAt);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(AUTOCHOOSE)){
			autochooseselect();
		}
		else if(cmd.equals(USERCHOOSE)) {
			userselect();
		}
		else if(cmd.equals(CHOOSEFOLDER)) {
			choosefolder();
		}
	}

	private void choosefolder() {
		JFileChooser chooser=JFileChooserWithLastDirMemory.getFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resultChooser = chooser.showSaveDialog(this);
		if(resultChooser == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
			JFileChooserWithLastDirMemory.setLastDir(chooser.getSelectedFile());
			String filepath=chooser.getSelectedFile().getAbsolutePath();
			textField.setText(filepath);
		}
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	
	
	private void autochooseselect() {
		if(chckbxLetMeChoose.isSelected()) {
			chckbxLetMeChoose.setSelected(false);
			textField.setText("");
			textField.setEnabled(false);
			btnInstallAt.setEnabled(false);
		}

	}
	
	
	private void userselect() {
		if(chckbxAutomatic.isSelected()) {
			chckbxAutomatic.setSelected(false);
			textField.setEnabled(true);
			btnInstallAt.setEnabled(true);
		}
	}


	public JCheckBox getChckbxLetMeChoose() {
		return chckbxLetMeChoose;
	}


	public JTextField getTextField() {
		return textField;
	}
	
	public void setRPackagesFolder(String folderpath) {
		textField.setText(folderpath);
	}
	
	public boolean isValidSettings() {
		
		if(chckbxLetMeChoose.isSelected() && textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Please define where R packages will be installed", "Wrong settings", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void disableOptions() {
		chckbxLetMeChoose.setSelected(false);
		chckbxAutomatic.setSelected(false);
		chckbxLetMeChoose.setEnabled(false);
		chckbxAutomatic.setEnabled(false);
		textField.setText("");
		textField.setEnabled(false);
		btnInstallAt.setEnabled(false);
	}
	
	public void enableOptions() {
		chckbxLetMeChoose.setEnabled(true);
		chckbxAutomatic.setEnabled(true);
		chckbxAutomatic.setSelected(true);
		chckbxLetMeChoose.setSelected(false);
		textField.setText("");
		textField.setEnabled(false);
		btnInstallAt.setEnabled(false);
	}

}
