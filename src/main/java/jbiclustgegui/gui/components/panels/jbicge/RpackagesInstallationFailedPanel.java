package jbiclustgegui.gui.components.panels.jbicge;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.border.TitledBorder;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.gui.components.tables.common.RpackagesTable;
import pt.ornrocha.ioutils.writers.MTUWriterUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;
import pt.ornrocha.swingutils.jfilechooser.filefilters.ExtensionFileFilter;
import pt.ornrocha.swingutils.tables.models.GenericTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RpackagesInstallationFailedPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JButton btnSave;
	private RpackagesTable packagestable=null;
	private JLabel lblTheRPackages;

	/**
	 * Create the panel.
	 */
	public RpackagesInstallationFailedPanel(LinkedHashMap<String, ArrayList<String>> packagesmap) {
		packagestable=new RpackagesTable();
		packagestable.addPackagesMap(packagesmap);
		initGUI();
	}
	
	
	private void initGUI() {
		
		setBorder(new TitledBorder(null, "R packages that were not installed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,0.0};
		setLayout(gridBagLayout);
		
		panel_1 = new JPanel();
		panel_1.setSize(600, 200);
		panel_1.setPreferredSize(new Dimension(600, 200));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1,1,1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,1.0,0.2};
		panel_1.setLayout(gbl_panel_1);
		
		scrollPane = new JScrollPane(packagestable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 4;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0};
		panel_2.setLayout(gbl_panel_2);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(packagestable.getRowCount()>0)
					saveTable();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 0;
		panel_2.add(btnSave, gbc_btnSave);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		lblTheRPackages = new JLabel("<html><center>The R packages presented above were not installed<br> due to errors during compiling process.<br>So, the features that depends of these packages will not be available.<br> (see jbiclustge_status log file to check the cause) </center><html>");
		GridBagConstraints gbc_lblTheRPackages = new GridBagConstraints();
		gbc_lblTheRPackages.gridx = 0;
		gbc_lblTheRPackages.gridy = 0;
		panel.add(lblTheRPackages, gbc_lblTheRPackages);
		

	}
	
	
	private void saveTable() {
		JFileChooser fileChooser = JFileChooserWithLastDirMemory.getFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Comma-separated values","csv"));
		int tag=fileChooser.showSaveDialog(this);
		
		if(tag==JFileChooser.APPROVE_OPTION) {
			ExtensionFileFilter filter=(ExtensionFileFilter) fileChooser.getFileFilter();
			JFileChooserWithLastDirMemory.setLastDir(fileChooser.getSelectedFile());
			try {
				if(filter.getExtension().equals("csv"))
					saveToCSV(fileChooser.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
					LogMessageCenter.getLogger().addCriticalErrorMessage("Error in saving file", e);
					Workbench.getInstance().error("Error in saving file");
				}
		}
	}
	

	private void saveToCSV(String filename) throws IOException {
		
		StringBuilder str=new StringBuilder();
		
		String[] header=((GenericTableModel)packagestable.getModel()).getColumnNames();
		
		for (int i = 0; i < header.length; i++) {
			str.append(header[i]);
			if(i<(header.length-1))
				str.append("\t");
		}
		str.append("\n");
		
		for (int i = 0; i < packagestable.getRowCount(); i++) {
			Object[] row=((GenericTableModel)packagestable.getModel()).getRowAt(i);
			
			for (int j = 0; j < row.length; j++) {
				str.append(String.valueOf(row[j]));	
				if(j<(row.length-1))
					str.append("\t");
			}
			str.append("\n");
			
		}
		
		String filepath=filename+".csv";
		MTUWriterUtils.writeDataTofile(filepath, str.toString());
		
	}
	
	public static void main(String[] args) {
		
		LinkedHashMap<String, ArrayList<String>> errorpackages= new LinkedHashMap<>();
		ArrayList<String> pack=new ArrayList<>(Arrays.asList("dffasfa","sfadffsd"));
		errorpackages.put("abs", pack);
		
		RpackagesInstallationFailedPanel panel=new RpackagesInstallationFailedPanel(errorpackages);
		JOptionPane.showMessageDialog(null, panel, "", JOptionPane.WARNING_MESSAGE);
		
	}

}
