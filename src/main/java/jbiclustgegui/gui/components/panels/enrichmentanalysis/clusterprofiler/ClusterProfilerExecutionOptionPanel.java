package jbiclustgegui.gui.components.panels.enrichmentanalysis.clusterprofiler;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.enrichmentanalysistools.clusterprofile.components.props.ClusterProfilerCommonPropertiesContainer;
import jbiclustge.enrichmentanalysistools.common.OrgDatabaseItem;
import jbiclustge.utils.osystem.SystemFolderTools;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.OntologizerExecutionOptionPanel;
import pt.ornrocha.propertyutils.PropertiesUtilities;
import pt.ornrocha.swingutils.combobox.ComboBoxMemoFile;
import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;
import pt.ornrocha.swingutils.textfield.CopyPasteJTextField;

public abstract class ClusterProfilerExecutionOptionPanel extends AbstractGeneEnrichmentAnalyserSettingsPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton btnNewButtonreset;
	protected ClusterProfilerOptionsPanel options_panel;
	protected  ComboBoxMemoFile textFieldorgdb;
	protected JTextField textFieldcustomfile;
	protected JCheckBox chckbxNewCheckBoxBITR;
	protected JCheckBox chckbxNewCheckBoxCustomFile;
	protected JComboBox comboBoxfromid;
	protected JComboBox comboBoxtoid;
	private JButton btnNewButtonopenfile;
	private JButton buttonimport;
	private JButton buttonexport;
	
	protected static String RESET="reset";
	protected static String CHOOSEBITR="choosebitr";
	protected static String CHOOSECUSTOMFILE="choosecustomfile";
	protected static String OPENCUSTOMFILE="opencustomfile";
	protected static String IMPORTSETS="importsettings";
	protected static String EXPORTSETS="exportsettings";
	
	public ClusterProfilerExecutionOptionPanel() {
		setBorder(new TitledBorder(null, getBorderTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//initGUI();
	}

	
	protected abstract String getBorderTitle();
	protected abstract ClusterProfilerOptionsPanel getOptionsPanel();

	
	
	
	@Override
	protected void initGUI() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0, 1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		options_panel = getOptionsPanel();
		GridBagConstraints gbc_options_panel = new GridBagConstraints();
		gbc_options_panel.gridheight = 8;
		gbc_options_panel.gridwidth = 10;
		gbc_options_panel.insets = new Insets(0, 0, 5, 5);
		gbc_options_panel.fill = GridBagConstraints.BOTH;
		gbc_options_panel.gridx = 0;
		gbc_options_panel.gridy = 0;
		add(options_panel, gbc_options_panel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Convert identifiers of genes described in expression dataset  to identifiers described in annotation ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.gridwidth = 10;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel.columnWeights = new double[]{0.5,1.0};
		gbl_panel.rowWeights = new double[]{0.0,1.0,1.0,1.0,1.0};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		panel_3.setLayout(gbl_panel_3);
		
		chckbxNewCheckBoxBITR = new JCheckBox("Biological Id TRanslator of ClusterProfiler");
		chckbxNewCheckBoxBITR.addActionListener(this);
		chckbxNewCheckBoxBITR.setActionCommand(CHOOSEBITR);
		GridBagConstraints gbc_chckbxNewCheckBoxBITR = new GridBagConstraints();
		gbc_chckbxNewCheckBoxBITR.insets = new Insets(0, 20, 0, 0);
		gbc_chckbxNewCheckBoxBITR.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBoxBITR.gridx = 0;
		gbc_chckbxNewCheckBoxBITR.gridy = 0;
		panel_3.add(chckbxNewCheckBoxBITR, gbc_chckbxNewCheckBoxBITR);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1};
		gbl_panel_4.rowHeights = new int[]{1};
		gbl_panel_4.columnWeights = new double[]{1.0};
		gbl_panel_4.rowWeights = new double[]{0.0};
		panel_4.setLayout(gbl_panel_4);
		
		chckbxNewCheckBoxCustomFile = new JCheckBox("Custom file with mapping identifiers ");
		chckbxNewCheckBoxCustomFile.addActionListener(this);
		chckbxNewCheckBoxCustomFile.setActionCommand(CHOOSECUSTOMFILE);
		GridBagConstraints gbc_chckbxNewCheckBoxCustomFile = new GridBagConstraints();
		gbc_chckbxNewCheckBoxCustomFile.insets = new Insets(0, 20, 0, 0);
		gbc_chckbxNewCheckBoxCustomFile.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBoxCustomFile.gridx = 0;
		gbc_chckbxNewCheckBoxCustomFile.gridy = 0;
		panel_4.add(chckbxNewCheckBoxCustomFile, gbc_chckbxNewCheckBoxCustomFile);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1,1};
		gbl_panel_1.rowHeights = new int[]{1,1,1};
		gbl_panel_1.columnWeights = new double[]{0.8,1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblOrgAnnotationDatabase = new JLabel("ORG annotation database:");
		GridBagConstraints gbc_lblOrgAnnotationDatabase = new GridBagConstraints();
		gbc_lblOrgAnnotationDatabase.anchor = GridBagConstraints.EAST;
		gbc_lblOrgAnnotationDatabase.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrgAnnotationDatabase.gridx = 0;
		gbc_lblOrgAnnotationDatabase.gridy = 0;
		panel_1.add(lblOrgAnnotationDatabase, gbc_lblOrgAnnotationDatabase);
		
		try {
			textFieldorgdb = new  ComboBoxMemoFile(SystemFolderTools.getOrgDatabaseTmpFilepath());
		} catch (IOException e) {
			Workbench.getInstance().error(e);
		}
		GridBagConstraints gbc_textFieldorgdb = new GridBagConstraints();
		gbc_textFieldorgdb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldorgdb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorgdb.gridx = 1;
		gbc_textFieldorgdb.gridy = 0;
		panel_1.add(textFieldorgdb, gbc_textFieldorgdb);
		//textFieldorgdb.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Convert from ids:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		comboBoxfromid = new JComboBox();
		GridBagConstraints gbc_comboBoxfromid = new GridBagConstraints();
		gbc_comboBoxfromid.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxfromid.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxfromid.gridx = 1;
		gbc_comboBoxfromid.gridy = 1;
		panel_1.add(comboBoxfromid, gbc_comboBoxfromid);
		
		JLabel lblNewLabel = new JLabel("Convert to ids:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxtoid = new JComboBox();
		GridBagConstraints gbc_comboBoxtoid = new GridBagConstraints();
		gbc_comboBoxtoid.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxtoid.gridx = 1;
		gbc_comboBoxtoid.gridy = 2;
		panel_1.add(comboBoxtoid, gbc_comboBoxtoid);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 4;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1,1};
		gbl_panel_2.rowHeights = new int[]{1,1,1};
		gbl_panel_2.columnWeights = new double[]{1.0,1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0,1.0};
		panel_2.setLayout(gbl_panel_2);
		
		btnNewButtonopenfile = new JButton("Choose file");
		btnNewButtonopenfile.addActionListener(this);
		btnNewButtonopenfile.setActionCommand(OPENCUSTOMFILE);
		GridBagConstraints gbc_btnNewButtonopenfile = new GridBagConstraints();
		gbc_btnNewButtonopenfile.gridwidth = 2;
		gbc_btnNewButtonopenfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtonopenfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtonopenfile.gridx = 0;
		gbc_btnNewButtonopenfile.gridy = 0;
		panel_2.add(btnNewButtonopenfile, gbc_btnNewButtonopenfile);
		
		textFieldcustomfile = new JTextField();
		GridBagConstraints gbc_textFieldcustomfile = new GridBagConstraints();
		gbc_textFieldcustomfile.gridwidth = 2;
		gbc_textFieldcustomfile.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldcustomfile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldcustomfile.gridx = 0;
		gbc_textFieldcustomfile.gridy = 1;
		panel_2.add(textFieldcustomfile, gbc_textFieldcustomfile);
		textFieldcustomfile.setColumns(10);
		
		btnNewButtonreset = new JButton("Reset to default values");
		btnNewButtonreset.addActionListener(this);
		btnNewButtonreset.setActionCommand(RESET);
		btnNewButtonreset.setIcon(new ImageIcon(OntologizerExecutionOptionPanel.class.getResource("/images/i24x24/delete.png")));
		GridBagConstraints gbc_btnNewButtonreset = new GridBagConstraints();
		gbc_btnNewButtonreset.fill = GridBagConstraints.BOTH;
		gbc_btnNewButtonreset.gridwidth = 4;
		gbc_btnNewButtonreset.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButtonreset.gridx = 0;
		gbc_btnNewButtonreset.gridy = 13;
		add(btnNewButtonreset, gbc_btnNewButtonreset);
		
		buttonimport = new JButton("Import Settings");
		buttonimport.addActionListener(this);
		buttonimport.setActionCommand(IMPORTSETS);
		buttonimport.setIcon(new ImageIcon(ClusterProfilerExecutionOptionPanel.class.getResource("/images/i24x24/import.png")));
		GridBagConstraints gbc_buttonimport = new GridBagConstraints();
		gbc_buttonimport.fill = GridBagConstraints.BOTH;
		gbc_buttonimport.gridwidth = 3;
		gbc_buttonimport.insets = new Insets(0, 0, 0, 5);
		gbc_buttonimport.gridx = 4;
		gbc_buttonimport.gridy = 13;
		add(buttonimport, gbc_buttonimport);
		
		buttonexport = new JButton("Export Settings");
		buttonexport.addActionListener(this);
		buttonexport.setActionCommand(EXPORTSETS);
		buttonexport.setIcon(new ImageIcon(ClusterProfilerExecutionOptionPanel.class.getResource("/images/i24x24/export.png")));
		GridBagConstraints gbc_buttonexport = new GridBagConstraints();
		gbc_buttonexport.fill = GridBagConstraints.BOTH;
		gbc_buttonexport.gridwidth = 3;
		gbc_buttonexport.insets = new Insets(0, 0, 0, 5);
		gbc_buttonexport.gridx = 7;
		gbc_buttonexport.gridy = 13;
		add(buttonexport, gbc_buttonexport);
		
	}

	@Override
	protected boolean needInitComponents() {
		return true;
	}

	@Override
	protected void initComponents() {
		
		for (OrgDatabaseItem item : OrgDatabaseItem.values()) {
			comboBoxfromid.addItem(item.toString());
		}
		
		for (OrgDatabaseItem item : OrgDatabaseItem.values()) {
			comboBoxtoid.addItem(item.toString());
		}
		
		comboBoxfromid.setSelectedItem(OrgDatabaseItem.PROBEID.toString());
		comboBoxtoid.setSelectedItem(OrgDatabaseItem.ENTREZID.toString());
		comboBoxfromid.setEditable(true);
		comboBoxtoid.setEditable(true);
	}



	@Override
	protected boolean resetToDefaultInBeginning() {
		return true;
	}
	
	
	@Override
	public void resetToDefaultValues() {
		chckbxNewCheckBoxBITR.setSelected(false);
		chckbxNewCheckBoxCustomFile.setSelected(false);
		textFieldorgdb.setEnabled(false);
		comboBoxfromid.setEnabled(false);
		comboBoxtoid.setEnabled(false);
		btnNewButtonopenfile.setEnabled(false);
		textFieldcustomfile.setText("");
		textFieldcustomfile.setEnabled(false);
		
	}
	
	
	protected void chooseBitr() {
		
		if(chckbxNewCheckBoxBITR.isSelected()) {
			textFieldorgdb.setEnabled(true);
			comboBoxfromid.setEnabled(true);
			comboBoxtoid.setEnabled(true);
			chckbxNewCheckBoxCustomFile.setSelected(false);
			btnNewButtonopenfile.setEnabled(false);
			textFieldcustomfile.setText("");
			textFieldcustomfile.setEnabled(false);
		}
		else {
			textFieldorgdb.setEnabled(false);
			comboBoxfromid.setEnabled(false);
			comboBoxtoid.setEnabled(false);
			
		}
	}
	
	protected void chooseCustomFile() {
		
		if(chckbxNewCheckBoxCustomFile.isSelected()) {
			chckbxNewCheckBoxBITR.setSelected(false);
			textFieldorgdb.setEnabled(false);
			comboBoxfromid.setEnabled(false);
			comboBoxtoid.setEnabled(false);
			btnNewButtonopenfile.setEnabled(true);
			textFieldcustomfile.setText("");
			textFieldcustomfile.setEnabled(true);
		}
		else {
			btnNewButtonopenfile.setEnabled(false);
			textFieldcustomfile.setText("");
			textFieldcustomfile.setEnabled(false);
		}
	}
	
	
	private void openCustomFile() {
		
		JFileChooser chooser=JFileChooserWithLastDirMemory.getFileChooser();
		 int returnValue = chooser.showOpenDialog(this);
	        if(returnValue==JFileChooser.APPROVE_OPTION) {
	        	File selected=chooser.getSelectedFile();
	        	JFileChooserWithLastDirMemory.setLastDir(selected);
	        	if(selected!=null)
	        		textFieldcustomfile.setText(selected.getAbsolutePath());
	        }
		
	}
	
	
	private void importSettings() throws FileNotFoundException, IOException {
		
		JFileChooser chooser=JFileChooserWithLastDirMemory.getFileChooser();
		 int returnValue = chooser.showOpenDialog(this);
	        if(returnValue==JFileChooser.APPROVE_OPTION) {
	        	File selected=chooser.getSelectedFile();
	        	JFileChooserWithLastDirMemory.setLastDir(selected);
	        	if(selected!=null) {
	        		Properties props=PropertiesUtilities.loadFileProperties(selected.getAbsolutePath());
	        		loadProperties(props);
	        	}
	        }
		
	}
	
	private void exportSettings() {
		
	}
	
	
	protected void addCommonProperties(ClusterProfilerCommonPropertiesContainer props) {
		
		if(props!=null) {
			if(chckbxNewCheckBoxBITR.isSelected()) {
				String orgdb=(String) textFieldorgdb.getSelectedItem();
				String fromid=(String) comboBoxfromid.getSelectedItem();
				String toid=(String) comboBoxtoid.getSelectedItem();
				props.setBitrConfiguration(orgdb, fromid, toid);
			}
			else  if(chckbxNewCheckBoxCustomFile.isSelected()) {
				props.setProbidsToAnnotaionIdsFileMap(textFieldcustomfile.getText());
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(RESET))
			resetToDefaultValues();
		else if(cmd.equals(CHOOSEBITR))
			chooseBitr();
		else if(cmd.equals(CHOOSECUSTOMFILE))
			chooseCustomFile();
		else if(cmd.equals(OPENCUSTOMFILE))
			openCustomFile();
		else if(cmd.equals(IMPORTSETS)) {
			try {
				importSettings();
			} catch (IOException e1) {
				Workbench.getInstance().error(e1);
			}
		}
		else if(cmd.equals(EXPORTSETS))
			exportSettings();
		
	}

	
	

}
