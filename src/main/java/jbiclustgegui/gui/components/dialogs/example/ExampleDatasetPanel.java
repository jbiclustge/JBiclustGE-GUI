package jbiclustgegui.gui.components.dialogs.example;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FilenameUtils;
import org.javatuples.Pair;
import org.zeroturnaround.zip.ZipUtil;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.osystem.SystemFolderTools;
import pt.ornrocha.collections.MTUMapUtils;
import pt.ornrocha.fileutils.MTUDirUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.propertyutils.PropertiesUtilities;
import pt.ornrocha.webutils.connectionutils.downloaders.HTTPFileDownloader;

public class ExampleDatasetPanel extends JDialog implements ListSelectionListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldorganism;
	private JList listdatasets;
	private DefaultListModel listmodel;
	private LinkedHashMap<String, Properties> mapdatasetproperties;
	private LinkedHashMap<String, String> mapnametopath;
	private JTextPane textPaneauthors;
	private JTextPane textPanetitlemanuscript;
	private JTextPane textPaneurlmanuscript;
	private JTextPane textPaneurldataset;
	private JTextField textFieldfileformat;
	private String currentdatasetfile=null;
	private boolean havemissingvalues=false;
	
	public static final int APPROVE_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int cmdoperation=-1;
	private JTextField textFieldmissingvalues;
	
	private static String JBICLUSTEXAMPLESDOWNLOADURL="https://jbiclustge.github.io/files/example_datasets.zip";

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ExampleDatasetPanel() throws FileNotFoundException, IOException {
		iniGUI();
		loadAvailableExamplesToGUI();
	}
	
	
	private void iniGUI() {
		setBounds(100, 100, 639, 601);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{0.5,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				listmodel = new DefaultListModel();
				listdatasets = new JList(listmodel);
				scrollPane.setViewportView(listdatasets);
				listdatasets.addListSelectionListener(this);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 2;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1};
			gbl_panel.rowHeights = new int[]{1,1,1,1,1,1,1,1};
			gbl_panel.columnWeights = new double[]{1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
			panel.setLayout(gbl_panel);
			{
				JLabel lblOrganism = new JLabel("Organism:");
				GridBagConstraints gbc_lblOrganism = new GridBagConstraints();
				gbc_lblOrganism.insets = new Insets(0, 0, 5, 5);
				gbc_lblOrganism.anchor = GridBagConstraints.EAST;
				gbc_lblOrganism.gridx = 0;
				gbc_lblOrganism.gridy = 0;
				panel.add(lblOrganism, gbc_lblOrganism);
			}
			{
				textFieldorganism = new JTextField();
				GridBagConstraints gbc_textFieldorganism = new GridBagConstraints();
				gbc_textFieldorganism.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldorganism.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldorganism.gridx = 1;
				gbc_textFieldorganism.gridy = 0;
				panel.add(textFieldorganism, gbc_textFieldorganism);
				textFieldorganism.setColumns(10);
			}
			{
				JLabel lblAuthors = new JLabel("Authors:");
				GridBagConstraints gbc_lblAuthors = new GridBagConstraints();
				gbc_lblAuthors.anchor = GridBagConstraints.EAST;
				gbc_lblAuthors.insets = new Insets(0, 0, 5, 5);
				gbc_lblAuthors.gridx = 0;
				gbc_lblAuthors.gridy = 1;
				panel.add(lblAuthors, gbc_lblAuthors);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 1;
				panel.add(scrollPane, gbc_scrollPane);
				{
					textPaneauthors = new JTextPane();
					scrollPane.setViewportView(textPaneauthors);
				}
			}
			{
				JLabel lblManuscript = new JLabel("Title of Manuscript:");
				GridBagConstraints gbc_lblManuscript = new GridBagConstraints();
				gbc_lblManuscript.anchor = GridBagConstraints.EAST;
				gbc_lblManuscript.insets = new Insets(0, 0, 5, 5);
				gbc_lblManuscript.gridx = 0;
				gbc_lblManuscript.gridy = 2;
				panel.add(lblManuscript, gbc_lblManuscript);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 2;
				panel.add(scrollPane, gbc_scrollPane);
				{
					textPanetitlemanuscript = new JTextPane();
					scrollPane.setViewportView(textPanetitlemanuscript);
				}
			}
			{
				JLabel lblUrlOfManuscript = new JLabel("Url of Manuscript:");
				GridBagConstraints gbc_lblUrlOfManuscript = new GridBagConstraints();
				gbc_lblUrlOfManuscript.anchor = GridBagConstraints.EAST;
				gbc_lblUrlOfManuscript.insets = new Insets(0, 0, 5, 5);
				gbc_lblUrlOfManuscript.gridx = 0;
				gbc_lblUrlOfManuscript.gridy = 3;
				panel.add(lblUrlOfManuscript, gbc_lblUrlOfManuscript);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 3;
				panel.add(scrollPane, gbc_scrollPane);
				{
					textPaneurlmanuscript = new JTextPane();
					scrollPane.setViewportView(textPaneurlmanuscript);
				}
			}
			{
				JLabel lblUrlOfOriginal = new JLabel("<html><center>Url of the<br>original dataset:</center></html>");
				GridBagConstraints gbc_lblUrlOfOriginal = new GridBagConstraints();
				gbc_lblUrlOfOriginal.fill = GridBagConstraints.VERTICAL;
				gbc_lblUrlOfOriginal.anchor = GridBagConstraints.EAST;
				gbc_lblUrlOfOriginal.insets = new Insets(0, 0, 5, 5);
				gbc_lblUrlOfOriginal.gridx = 0;
				gbc_lblUrlOfOriginal.gridy = 4;
				panel.add(lblUrlOfOriginal, gbc_lblUrlOfOriginal);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 4;
				panel.add(scrollPane, gbc_scrollPane);
				{
					textPaneurldataset = new JTextPane();
					scrollPane.setViewportView(textPaneurldataset);
				}
			}
			{
				JLabel lblFileFormat = new JLabel("File format:");
				GridBagConstraints gbc_lblFileFormat = new GridBagConstraints();
				gbc_lblFileFormat.anchor = GridBagConstraints.EAST;
				gbc_lblFileFormat.insets = new Insets(0, 0, 5, 5);
				gbc_lblFileFormat.gridx = 0;
				gbc_lblFileFormat.gridy = 5;
				panel.add(lblFileFormat, gbc_lblFileFormat);
			}
			{
				textFieldfileformat = new JTextField();
				GridBagConstraints gbc_textFieldfileformat = new GridBagConstraints();
				gbc_textFieldfileformat.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldfileformat.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldfileformat.gridx = 1;
				gbc_textFieldfileformat.gridy = 5;
				panel.add(textFieldfileformat, gbc_textFieldfileformat);
				textFieldfileformat.setColumns(10);
			}
			{
				JLabel lblContainsMissingValues = new JLabel("Contains missing values:");
				GridBagConstraints gbc_lblContainsMissingValues = new GridBagConstraints();
				gbc_lblContainsMissingValues.anchor = GridBagConstraints.EAST;
				gbc_lblContainsMissingValues.insets = new Insets(0, 0, 5, 5);
				gbc_lblContainsMissingValues.gridx = 0;
				gbc_lblContainsMissingValues.gridy = 6;
				panel.add(lblContainsMissingValues, gbc_lblContainsMissingValues);
			}
			{
				textFieldmissingvalues = new JTextField();
				GridBagConstraints gbc_textFieldmissingvalues = new GridBagConstraints();
				gbc_textFieldmissingvalues.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldmissingvalues.insets = new Insets(0, 0, 5, 0);
				gbc_textFieldmissingvalues.gridx = 1;
				gbc_textFieldmissingvalues.gridy = 6;
				panel.add(textFieldmissingvalues, gbc_textFieldmissingvalues);
				textFieldmissingvalues.setColumns(10);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 7;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1,1};
				gbl_panel_1.rowHeights = new int[]{1};
				gbl_panel_1.columnWeights = new double[] {1.0,1.0};
				gbl_panel_1.rowWeights = new double[]{0.0};
				panel_1.setLayout(gbl_panel_1);
				{
					JButton btnOpenLocation = new JButton("Open location");
					btnOpenLocation.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							String examplesdir=FilenameUtils.concat(SystemFolderTools.getCurrentDir(), "example_datasets");
							if(new File(examplesdir).exists()) {
								File f=new File(examplesdir);
								Desktop desktop = Desktop.getDesktop();
								if(Desktop.isDesktopSupported())
									try {
										desktop.open(f);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
							}
						}
					});
					GridBagConstraints gbc_btnOpenLocation = new GridBagConstraints();
					gbc_btnOpenLocation.insets = new Insets(0, 0, 0, 5);
					gbc_btnOpenLocation.gridx = 0;
					gbc_btnOpenLocation.gridy = 0;
					panel_1.add(btnOpenLocation, gbc_btnOpenLocation);
				}
				{
					JButton btnOpenWithDefault = new JButton("Open with default text editor");
					btnOpenWithDefault.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(currentdatasetfile!=null) {
								File f=new File(currentdatasetfile);
								Desktop desktop = Desktop.getDesktop();
								if(Desktop.isDesktopSupported())
									try {
										desktop.open(f);
										//desktop.edit(f);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
							}
						}
					});
					GridBagConstraints gbc_btnOpenWithDefault = new GridBagConstraints();
					gbc_btnOpenWithDefault.gridx = 1;
					gbc_btnOpenWithDefault.gridy = 0;
					panel_1.add(btnOpenWithDefault, gbc_btnOpenWithDefault);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Load");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	private void loadAvailableExamplesToGUI() throws FileNotFoundException, IOException {
		checkAvailableExamples();
		fillListExamples();
	}
	
	
	private void fillListExamples() {
		
		String first=null;
		for (String name : mapnametopath.keySet()) {
			if(first==null)
				first=name;
			listmodel.addElement(name);
		}
		
		loadInfoForDatasetName(first);

	}
	
	
	
	
	
	private void checkAvailableExamples() throws FileNotFoundException, IOException {
		
		String examplesdir=FilenameUtils.concat(SystemFolderTools.getCurrentDir(), "example_datasets");
		if(!new File(examplesdir).exists()) {
			
			boolean validdownload=true;
			try {
				downloadAndConfigureExamples();
			} catch (Exception e) {
				validdownload=false;
				Workbench.getInstance().error("The download of the example datasets could not be performed, please check your internet connection!");
			}
			
			if(validdownload)
				readAvailableExamples(examplesdir);	
		}
		else {
			readAvailableExamples(examplesdir);		
		}
	}
	
	private void downloadAndConfigureExamples() throws MalformedURLException {
		
		HTTPFileDownloader downloader=new HTTPFileDownloader(JBICLUSTEXAMPLESDOWNLOADURL, SystemFolderTools.getCurrentDir());
		downloader.run();
		
		String currentexamplesfile=FilenameUtils.concat( SystemFolderTools.getCurrentDir(), "example_datasets.zip");
		File examplesfile=new File(currentexamplesfile);
		if(examplesfile.exists()) {
			ZipUtil.unpack(examplesfile, new File(SystemFolderTools.getCurrentDir()));
			LogMessageCenter.getLogger().addInfoMessage("Example datasets were installed successfully in: "+FilenameUtils.concat( SystemFolderTools.getCurrentDir(), "example_datasets"));
		}
	}
	
	
	private void readAvailableExamples(String dirpath) throws FileNotFoundException, IOException {
		
		ArrayList<String> infofiles=MTUDirUtils.getFilesWithExtensionInsideDirectory(dirpath,"info");
		ArrayList<String> files=MTUDirUtils.getFilePathsInsideDirectory(dirpath);		
		mapInfoWithFile(files, infofiles);
	}
	
	
	private void mapInfoWithFile(ArrayList<String> files, ArrayList<String> infofiles) throws FileNotFoundException, IOException {
		
		mapdatasetproperties=new LinkedHashMap<>();
		LinkedHashMap<String, String> tmpmapnametopath=new LinkedHashMap<>();
		
		
		
		for (int i = 0; i < files.size(); i++) {
			
			String filename=FilenameUtils.getBaseName(files.get(i));
			String extension=FilenameUtils.getExtension(files.get(i));
			if(!extension.toLowerCase().equals("info")) {
				Properties props=getInfoFileProperties(filename, infofiles);
				mapdatasetproperties.put(files.get(i), props);
				tmpmapnametopath.put(filename, files.get(i));
			}
		}
		
		mapnametopath=(LinkedHashMap<String, String>) MTUMapUtils.sortMapByKeys(tmpmapnametopath, true);
	}
	
	private Properties getInfoFileProperties(String namefile, ArrayList<String> infofiles) throws FileNotFoundException, IOException {
		
		for (int i = 0; i < infofiles.size(); i++) {
			String filename=FilenameUtils.getBaseName(infofiles.get(i));
			if(filename.toLowerCase().equals(namefile.toLowerCase()))
				return PropertiesUtilities.loadFileProperties(infofiles.get(i));
		}
		return null;
	}

	
	public static void main(String[] args) {
		try {
			ExampleDatasetPanel dialog = new ExampleDatasetPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {

		boolean adjust = e.getValueIsAdjusting();
		
		if(!adjust) {
			String selected=(String) listdatasets.getSelectedValue();
			loadInfoForDatasetName(selected);
		}
		
	}
	
	
	private void loadInfoForDatasetName(String name) {
		
		String path=mapnametopath.get(name);
		Properties infoprops= mapdatasetproperties.get(path);
		fillInfoFields(infoprops);
		currentdatasetfile=path;
	}
	
	private void fillInfoFields(Properties props) {
		if(props!=null) {
			
			String organism=props.getProperty("organism");
			if(organism!=null) {
				textFieldorganism.setText(organism);
				textFieldorganism.setEditable(false);
			}
			
			String authors=props.getProperty("authors");
			if(authors!=null) {
				textPaneauthors.setText(authors);
				textPaneauthors.setEditable(false);
			}
			
			String titlemanuscript=props.getProperty("title_manuscript");
			if(titlemanuscript!=null) {
				textPanetitlemanuscript.setText(titlemanuscript);
				textPanetitlemanuscript.setEditable(false);
			}
			
			String manuscript_url=props.getProperty("manuscript_url");
			if(manuscript_url!=null) {
				textPaneurlmanuscript.setText(manuscript_url);
				textPaneurlmanuscript.setEditable(false);
			}
			
			String data_url=props.getProperty("data_url");
			if(data_url!=null) {
				textPaneurldataset.setText(data_url);
				textPaneurldataset.setEditable(false);
			}
			
			String file_format=props.getProperty("file_format");
			if(file_format!=null) {
				textFieldfileformat.setText(file_format);
				textFieldfileformat.setEditable(false);
			}
			
			String missingvalues=props.getProperty("missing_values");
			if(missingvalues!=null) {
				textFieldmissingvalues.setText(missingvalues);
				textFieldmissingvalues.setEditable(false);
				if(missingvalues.toLowerCase().equals("yes")) {
					havemissingvalues=true;
				}
				else
					havemissingvalues=false;
			}
				
		}
		
	}
	
	public int showOpenDialog(Component parent) throws InterruptedException {
		return showDialog(parent);
	} 

	
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
	
	public String getSelectedDataset() {
		return currentdatasetfile;
	}
	
	public boolean haveMissingValues() {
		return havemissingvalues;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("Cancel")) {
			cmdoperation=CANCEL_OPTION;
			dispose();
			
		}
		else if(cmd.equals("OK")) {
				cmdoperation=APPROVE_OPTION;
				dispose();
			}
		}


}
