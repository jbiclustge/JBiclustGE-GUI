package jbiclustgegui.gui.components.panels.jbicge;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AlgorithmsConfigPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JCheckBox checkBox_download;
	private JCheckBox checkBox_letchoose;
	private JButton button_choose;
	
	
	/** The downloadzip. */
	private static String DOWNLOADZIP="downloadzip";
	
	/** The selectzip. */
	private static String SELECTZIP="selectzip";
	
	/** The choosezip. */
	private static String CHOOSEZIP="choosezip";
	
	public AlgorithmsConfigPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Zip file containing Biclustering Algorithms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		checkBox_download = new JCheckBox("Download From JBiclustGE Website");
		checkBox_download.setSelected(true);
		checkBox_download.setActionCommand(DOWNLOADZIP);
		checkBox_download.addActionListener(this);
		GridBagConstraints gbc_checkBox_download = new GridBagConstraints();
		gbc_checkBox_download.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_download.gridx = 0;
		gbc_checkBox_download.gridy = 0;
		panel_1.add(checkBox_download, gbc_checkBox_download);
		
		checkBox_letchoose = new JCheckBox("Let me choose zip file");
		checkBox_letchoose.setSelected(false);
		checkBox_letchoose.setActionCommand(SELECTZIP);
		checkBox_letchoose.addActionListener(this);
		GridBagConstraints gbc_checkBox_letchoose = new GridBagConstraints();
		gbc_checkBox_letchoose.gridx = 1;
		gbc_checkBox_letchoose.gridy = 0;
		panel_1.add(checkBox_letchoose, gbc_checkBox_letchoose);
		
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
		
		button_choose = new JButton("Choose zip");
		button_choose.setEnabled(false);
		button_choose.setActionCommand(CHOOSEZIP);
		button_choose.addActionListener(this);
		GridBagConstraints gbc_button_choose = new GridBagConstraints();
		gbc_button_choose.gridx = 1;
		gbc_button_choose.gridy = 0;
		panel_2.add(button_choose, gbc_button_choose);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(DOWNLOADZIP)) {
			if(checkBox_download.isSelected()) {
				checkBox_letchoose.setSelected(false);
				textField.setText("");
				textField.setEnabled(false);
				button_choose.setEnabled(false);
			}
		}
		else if(cmd.equals(SELECTZIP)) {
			if(checkBox_letchoose.isSelected()) {
				checkBox_download.setSelected(false);
				textField.setEnabled(true);
				button_choose.setEnabled(true);
			}
		}
		else if(cmd.equals(CHOOSEZIP) && checkBox_letchoose.isSelected()) {
			choosezipfile();
		}
		
		
	}
	
	/**
	 * Choosezipfile.
	 */
	private void choosezipfile() {
		JFileChooser chooser=JFileChooserWithLastDirMemory.getFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Compressed files (.zip)", "zip");
		chooser.addChoosableFileFilter(filter);
		int resultChooser = chooser.showSaveDialog(this);
		if(resultChooser == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
			JFileChooserWithLastDirMemory.setLastDir(chooser.getSelectedFile());
			String filepath=chooser.getSelectedFile().getAbsolutePath();
			textField.setText(filepath);
		}
		chooser.removeChoosableFileFilter(filter);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JCheckBox getCheckBox_letchoose() {
		return checkBox_letchoose;
	}
	
	public boolean isValidSettings() {
		
		boolean valid=true;
		if(checkBox_letchoose.isSelected() && textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Please define the Zip file that contains the Biclustering Algorithm executables.", "Wrong settings", JOptionPane.ERROR_MESSAGE);
			valid=false;
		}
		
		return valid;
	}

	
}
