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
package jbiclustgegui.gui.io.biclusters;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.collections.CollectionUtils;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.methods.algorithms.BiclusteringMethod;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;
import jbiclustgegui.gui.components.selection.Delimiter;
import pt.ornrocha.swingutils.jfilechooser.filefilters.ExtensionFileFilter;
import smile.imputation.MissingValueImputationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportListofBiclustersGUI.
 */
public class ImportListofBiclustersGUI extends JDialog implements ActionListener,InputGUI{

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The text field. */
	private JTextField textField;
	
	/** The project selection panel. */
	private ProjectSelectionPanel projectSelectionPanel;
	
	/** The btn open bicluster list. */
	private JButton btnOpenBiclusterList;
	
	/** The rec. */
	protected ParamsReceiver rec;
	
	/** The ok button. */
	private JButton okButton;
	
	/** The loadedlist. */
	private BiclusterList loadedlist;
	
	/** The currentproject. */
	protected String currentproject=null;

	/** The open. */
	protected static String OPEN="openfile";
	
	/** The close. */
	protected static String CLOSE="close";
	
	/** The ok. */
	protected static String OK="ok";
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The combo box. */
	private JComboBox comboBox;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ImportListofBiclustersGUI dialog = new ImportListofBiclustersGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new import listof biclusters GUI.
	 */
	public ImportListofBiclustersGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
	}

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 546, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			projectSelectionPanel = new ProjectSelectionPanel();
			GridBagConstraints gbc_projectSelectionPanel = new GridBagConstraints();
			gbc_projectSelectionPanel.insets = new Insets(0, 0, 5, 0);
			gbc_projectSelectionPanel.fill = GridBagConstraints.BOTH;
			gbc_projectSelectionPanel.gridx = 0;
			gbc_projectSelectionPanel.gridy = 0;
			contentPanel.add(projectSelectionPanel, gbc_projectSelectionPanel);
			projectSelectionPanel.setProjectActionListener(this);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{0.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				btnOpenBiclusterList = new JButton("<html><center>Open<br>Bicluster<br>List</center></html>");
				btnOpenBiclusterList.setIcon(new ImageIcon(ImportListofBiclustersGUI.class.getResource("/images/i24x24/import.png")));
				GridBagConstraints gbc_btnOpenBiclusterList = new GridBagConstraints();
				gbc_btnOpenBiclusterList.insets = new Insets(0, 0, 0, 5);
				gbc_btnOpenBiclusterList.gridx = 0;
				gbc_btnOpenBiclusterList.gridy = 0;
				panel.add(btnOpenBiclusterList, gbc_btnOpenBiclusterList);
				btnOpenBiclusterList.addActionListener(this);
				btnOpenBiclusterList.setActionCommand(OPEN);
			}
			{
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 0;
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
	 {
			this.panel_1 = new JPanel();
			/*this.panel_1.setBorder(new TitledBorder(null, "Biclustering Algorithm Used ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel_1 = new GridBagConstraints();
			gbc_panel_1.insets = new Insets(0, 0, 5, 0);
			gbc_panel_1.fill = GridBagConstraints.BOTH;
			gbc_panel_1.gridx = 0;
			gbc_panel_1.gridy = 2;
			contentPanel.add(this.panel_1, gbc_panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{1};
			gbl_panel_1.rowHeights = new int[]{1};
			gbl_panel_1.columnWeights = new double[]{1.0};
			gbl_panel_1.rowWeights = new double[]{1.0};
			this.panel_1.setLayout(gbl_panel_1);
			{
				this.comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 0;
				gbc_comboBox.gridy = 0;
				this.panel_1.add(this.comboBox, gbc_comboBox);
			}*/
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
				okButton.setEnabled(false);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CLOSE);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	
	/**
	 * Open bicluster list file.
	 */
	private void openBiclusterListFile() {

		ExpressionData dataset=projectSelectionPanel.getSelectedProject().getExpressionDataset().getExpressionset();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("JBiclustGE Default Format","bicge"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("JBiclustGE json Format","json"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("biclust R package Format","txt"));
		fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Sequencial biclusters represented by 2 lines(genes and conditions)","txt"));

		int tag=fileChooser.showOpenDialog(this);

		if(tag==JFileChooser.APPROVE_OPTION) {
			ExtensionFileFilter filter=(ExtensionFileFilter) fileChooser.getFileFilter();

			if(fileChooser.getSelectedFile()!=null) {
				BiclusterList list=null;
				try {
					if(filter.getSingleDescription().equals("JBiclustGE Default Format"))
						list=BiclusterList.importBiclusterListFromJBiclustGEFormat(fileChooser.getSelectedFile().getAbsolutePath(), dataset);
					else if(filter.getSingleDescription().equals("JBiclustGE json Format"))
						list=BiclusterList.imporBiclusterListFromJBiclustGEJSONFile(fileChooser.getSelectedFile().getAbsolutePath(), dataset);
					else if(filter.getSingleDescription().equals("biclust R package Format"))
						list=BiclusterList.importBiclustersFromBiclustRPackageOutputFormat(fileChooser.getSelectedFile().getAbsolutePath(), dataset);
					else if(filter.getSingleDescription().equals("Sequencial biclusters represented by 2 lines(genes and conditions)")) {

						JComboBox<Delimiter> field1 = new JComboBox<>(Delimiter.values());
						field1.setSelectedIndex(1);
						JCheckBox field2 = new JCheckBox("Using indexes in biclusters");
						field2.setSelected(true);
						Object[] message = {
								"Delimiter of elements:", field1,
								"Using indexes, instead of names:", field2,
						};
						int option = JOptionPane.showConfirmDialog(this, message, "Choose the required values", JOptionPane.OK_CANCEL_OPTION);
						
						String value1 =Delimiter.TAB.getDelimiterString();
						boolean value2=true;
						
						if (option == JOptionPane.OK_OPTION){
							value1 = ((Delimiter)field1.getSelectedItem()).getDelimiterString();
							value2 = field2.isSelected();
							
						}
						try {
						 list=BiclusterList.importBiclustersFromTxtFile(dataset, fileChooser.getSelectedFile().getAbsolutePath(), value1, value2);
						} catch (NumberFormatException e1) {
							Workbench.getInstance().error(e1, "Incorrect file format, it was not possible to read biclusters from the selected file"+""
									+ "\n"+fileChooser.getSelectedFile().getAbsolutePath());
						}
					}

				} catch (IOException | ParseException | MissingValueImputationException e) {
					//Workbench.getInstance().error("Incorrect file format, it was not possible to read biclusters from the selected file"+""
					//		+ "\n"+fileChooser.getSelectedFile().getAbsolutePath());
					Workbench.getInstance().error(e, "Incorrect file format, it was not possible to read biclusters from the selected file"+""
							+ "\n"+fileChooser.getSelectedFile().getAbsolutePath());
				}

				
				if(list!=null) {
					
					ArrayList<String> biclistgenes=list.getAllGenesOfBiclusters();
					ArrayList<String> commongenes=(ArrayList<String>) CollectionUtils.retainAll(biclistgenes, dataset.getGeneNamesList());
					
					boolean ok=true;
					if(commongenes.size()!=biclistgenes.size()) {
						int cm=JOptionPane.showConfirmDialog(this, "Some of the genes described in the file that was loaded, are not present in the current gene expression dataset.\n"
								+ "This can lead to incorrect analysis using those imported biclusters."
								+ "\n Do you want to continue?", "Mismatch gene names", JOptionPane.YES_NO_OPTION);
						if(cm==JOptionPane.NO_OPTION)
							ok=false;
						
					}
					if(ok) {
						ArrayList<String> biclistconds=list.getAllConditionsOfBiclusters();
				
						ArrayList<String> commonconds=(ArrayList<String>) CollectionUtils.retainAll(biclistconds, dataset.getConditionsList());
						if(commonconds.size()!=biclistconds.size()) {
							int cm=JOptionPane.showConfirmDialog(this, "Some of the conditions described in the file that was loaded, are not present in the current gene expression dataset.\n"
									+ "This can lead to incorrect analysis using those imported biclusters."
									+ "\n Do you want to continue?", "Mismatch gene names", JOptionPane.YES_NO_OPTION);
							if(cm==JOptionPane.NO_OPTION)
								ok=false;
						}
					}
					
					if(ok) {
						String extractedmethod=list.getUsedmethod();
						BiclusteringMethod usedmethod=null;
						if(extractedmethod==null) {
							JComboBox<BiclusteringMethod> bicmethodfield = new JComboBox<>(BiclusteringMethod.values());
							bicmethodfield.setSelectedItem(BiclusteringMethod.UNKNOWN);
							Object[] message = {
									"Biclusters were calculated with biclustering method?:", bicmethodfield
							};
							int option = JOptionPane.showConfirmDialog(this, message, "Do you know wich method that was used?", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION){
								usedmethod=(BiclusteringMethod) bicmethodfield.getSelectedItem();
							}
							else
								usedmethod=BiclusteringMethod.UNKNOWN;
						}
						else {
							usedmethod=BiclusteringMethod.getMethodbyName(extractedmethod);
							
						}
						
						
						setMethodOption();
						comboBox.setSelectedItem(usedmethod);
						loadedlist=list;
						okButton.setEnabled(true);
						textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
						
					}
				}
                

			}


		}
	}
	
	/**
	 * Sets the method option.
	 */
	private void setMethodOption() {
		
		if(panel_1==null)
			this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "Biclustering Algorithm Used ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPanel.add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		this.panel_1.setLayout(gbl_panel_1);
		{
			this.comboBox = new JComboBox(BiclusteringMethod.values());
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 0;
			gbc_comboBox.gridy = 0;
			this.panel_1.add(this.comboBox, gbc_comboBox);
		}
		panel_1.updateUI();
	}
	
    /**
     * Fill project elements.
     *
     * @param proj the proj
     */
    private void fillProjectElements(Project proj) {
		
		String newprojname=proj.getName();
		
		if(currentproject==null || !currentproject.equals(newprojname)){
			textField.setText("");
			loadedlist=null;
			okButton.setEnabled(false);
			currentproject=newprojname;
			remove(panel_1);
			panel_1=null;
			
		}
    }
	

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(new Dimension(500,300));
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

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(CLOSE))
			finish();
		else if(cmd.equals(OPEN))
			openBiclusterListFile();
		else if(cmd.equals(ProjectSelectionPanel.PROJECT_ACTION_COMMAND )) {
			fillProjectElements(projectSelectionPanel.getSelectedProject());
		}
		else if(cmd.equals(OK) && loadedlist!=null) {
			terminate();
		}
	}
	
	/**
	 * Terminate.
	 */
	private void terminate(){
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("Project", Project.class,projectSelectionPanel.getSelectedProject(), null));
		listSpecs.add(new ParamSpec("Biclustering results",BiclusterList.class,loadedlist, null));
		listSpecs.add(new ParamSpec("Biclustering method",BiclusteringMethod.class,comboBox.getSelectedItem(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}

}
