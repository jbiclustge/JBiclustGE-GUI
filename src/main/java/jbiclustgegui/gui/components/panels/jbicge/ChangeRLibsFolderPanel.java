package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;

public class ChangeRLibsFolderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnChange;
	private JTextField textField;
	private String origpath=null;

	/**
	 * Create the panel.
	 */
	public ChangeRLibsFolderPanel(String origpath) {
        this.origpath=origpath;
		initGUI();
	}
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{0.1,1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changefolder();
			}
		});
		GridBagConstraints gbc_btnChange = new GridBagConstraints();
		gbc_btnChange.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChange.insets = new Insets(0, 0, 0, 5);
		gbc_btnChange.gridx = 0;
		gbc_btnChange.gridy = 0;
		add(btnChange, gbc_btnChange);
		
		textField = new JTextField();
		textField.setText(origpath);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		setSize(new Dimension(700, 50));
		setPreferredSize(new Dimension(700, getPreferredSize().height));
	}
	
	
	private void changefolder() {
		JFileChooser fileChooser = JFileChooserWithLastDirMemory.getFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int tag=fileChooser.showSaveDialog(this);
		
		if(tag==JFileChooser.APPROVE_OPTION) {
			JFileChooserWithLastDirMemory.setLastDir(fileChooser.getSelectedFile());
			textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public String getSelectedFolder() {
		return textField.getText();
	}
	
	
	public static void main(String[] args) {
		ChangeRLibsFolderPanel pn=new ChangeRLibsFolderPanel("/home/orocha/Eclipse_Projects/jbiclustge_development/JBiclustGE-GUI/target/dist");
		JOptionPane.showMessageDialog(null, pn, "R packages will be installed at?", JOptionPane.QUESTION_MESSAGE);
        String newfolder=pn.getSelectedFolder();
        System.out.println(newfolder);
	}

}
