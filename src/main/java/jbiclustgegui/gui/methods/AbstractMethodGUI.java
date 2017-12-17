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
package jbiclustgegui.gui.methods;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.enrichmentanalysistools.common.GSEAAnalyserType;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.gui.components.dialogs.GSEAConfigurationDialog;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;
import jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel;
import jbiclustgegui.gui.components.tables.gsea.GSEAConfigurationsTable;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractMethodGUI.
 */
public abstract class AbstractMethodGUI extends JDialog implements ActionListener,InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The project selection panel. */
	protected ProjectSelectionPanel projectSelectionPanel;
	
	/** The panelbicmethodsettings. */
	protected AbstractMethodSettingsPanel panelbicmethodsettings;
	
	/** The rec. */
	protected ParamsReceiver rec;
	
	/** The chckbx perform gsea. */
	protected JCheckBox chckbxPerformGsea;
	
	/** The combo boxgsea. */
	protected JComboBox comboBoxgsea;
	
	/** The btn add gsea configuration. */
	protected JButton btnAddGseaConfiguration;
	
	/** The btn new buttonviewconfig. */
	protected JButton btnNewButtonviewconfig;
	
	/** The btn new buttonremoveconfig. */
	protected JButton btnNewButtonremoveconfig;
	
	/** The configstable. */
	protected GSEAConfigurationsTable configstable;
	
	/** The currentproject. */
	protected String currentproject=null;
	
	
	/** The close. */
	protected static String CLOSE="close";
	
	/** The ok. */
	protected static String OK="ok";
	
	/** The performgsea. */
	protected static String PERFORMGSEA="performgsea";
	
	/** The addgsea. */
	protected static String ADDGSEA="addgsea";
	
	/** The viewgsea. */
	protected static String VIEWGSEA="viewgsea";
	
	/** The removegsea. */
	protected static String REMOVEGSEA="removegsea";
	
	/** The scroll pane 1. */
	private JScrollPane scrollPane_1;
	
	/**
	 * Instantiates a new abstract method GUI.
	 */
	public AbstractMethodGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
		initComponents();
	}
	
	

	/**
	 * Create the dialog.
	 */
	protected void initGUI() {
		setBounds(100, 100, 561, 616);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{0.2,0.3,1.0};
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
			panel.setBorder(new TitledBorder(null, "Perform Gene Enrichment Analysis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1,1};
			gbl_panel.rowHeights = new int[]{1,1,1,1};
			gbl_panel.columnWeights = new double[]{0.0,1.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,0.0};
			panel.setLayout(gbl_panel);
			{
				chckbxPerformGsea = new JCheckBox("Perform GSEA");
				GridBagConstraints gbc_chckbxPerformGsea = new GridBagConstraints();
				gbc_chckbxPerformGsea.insets = new Insets(0, 10, 5, 5);
				gbc_chckbxPerformGsea.gridx = 0;
				gbc_chckbxPerformGsea.gridy = 0;
				panel.add(chckbxPerformGsea, gbc_chckbxPerformGsea);
				chckbxPerformGsea.addActionListener(this);
				chckbxPerformGsea.setActionCommand(PERFORMGSEA);
			}
			{
				this.configstable=new GSEAConfigurationsTable();
				JScrollPane scrollPane = new JScrollPane(configstable);
				scrollPane.setViewportBorder(new TitledBorder(null, "GSEA Configurations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridheight = 3;
				gbc_scrollPane.gridwidth = 4;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 0;
				panel.add(scrollPane, gbc_scrollPane);
			}
			{
				comboBoxgsea = new JComboBox();
				GridBagConstraints gbc_comboBoxgsea = new GridBagConstraints();
				gbc_comboBoxgsea.anchor = GridBagConstraints.NORTH;
				gbc_comboBoxgsea.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxgsea.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxgsea.gridx = 0;
				gbc_comboBoxgsea.gridy = 1;
				panel.add(comboBoxgsea, gbc_comboBoxgsea);
			}
			{
				btnAddGseaConfiguration = new JButton("Add GSEA Configuration");
				GridBagConstraints gbc_btnAddGseaConfiguration = new GridBagConstraints();
				gbc_btnAddGseaConfiguration.anchor = GridBagConstraints.NORTH;
				gbc_btnAddGseaConfiguration.insets = new Insets(0, 0, 5, 5);
				gbc_btnAddGseaConfiguration.gridx = 0;
				gbc_btnAddGseaConfiguration.gridy = 2;
				panel.add(btnAddGseaConfiguration, gbc_btnAddGseaConfiguration);
				btnAddGseaConfiguration.addActionListener(this);
				btnAddGseaConfiguration.setActionCommand(ADDGSEA);
			}
			{
				btnNewButtonviewconfig = new JButton("View config.");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton.gridwidth = 2;
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 3;
				panel.add(btnNewButtonviewconfig, gbc_btnNewButton);
				btnNewButtonviewconfig.setActionCommand(VIEWGSEA);
				btnNewButtonviewconfig.addActionListener(this);
			}
			{
				btnNewButtonremoveconfig = new JButton("Remove config.");
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton_1.gridwidth = 2;
				gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton_1.gridx = 3;
				gbc_btnNewButton_1.gridy = 3;
				panel.add(btnNewButtonremoveconfig, gbc_btnNewButton_1);
				btnNewButtonremoveconfig.setActionCommand(REMOVEGSEA);
				btnNewButtonremoveconfig.addActionListener(this);
			}
		}
		{
			panelbicmethodsettings = getMethodSettingsPanel();
			/*GridBagConstraints gbc_panelbicmethodsettings = new GridBagConstraints();
			gbc_panelbicmethodsettings.fill = GridBagConstraints.BOTH;
			gbc_panelbicmethodsettings.gridx = 0;
			gbc_panelbicmethodsettings.gridy = 2;
			contentPanel.add(panelbicmethodsettings, gbc_panelbicmethodsettings);*/
		}
		{
			scrollPane_1 = new JScrollPane(panelbicmethodsettings);
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 0;
			gbc_scrollPane_1.gridy = 2;
			contentPanel.add(scrollPane_1, gbc_scrollPane_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CLOSE);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	/**
	 * Gets the method settings panel.
	 *
	 * @return the method settings panel
	 */
	protected abstract AbstractMethodSettingsPanel getMethodSettingsPanel();
	
	/**
	 * Gets the dialog dimensions.
	 *
	 * @return the dialog dimensions
	 */
	protected abstract Dimension getDialogDimensions();
	
	/**
	 * Inits the components.
	 */
	protected void initComponents() {
		for (GSEAAnalyserType type : GSEAAnalyserType.values()) {
			comboBoxgsea.addItem(type);
		}
		
		fillProjectElements(projectSelectionPanel.getSelectedProject());
		
	}
	
    /**
     * Fill project elements.
     *
     * @param proj the proj
     */
    private void fillProjectElements(Project proj) {
		
		String newprojname=proj.getName();
		
		if(currentproject==null || !currentproject.equals(newprojname)){
			resetToDefaults();
			panelbicmethodsettings.setCurrentProject(projectSelectionPanel.getSelectedProject());
			currentproject=newprojname;
		}
	}
	
	/**
	 * Reset to defaults.
	 */
	public void resetToDefaults() {
		chckbxPerformGsea.setSelected(false);
		ActivateGSEACMD();
		panelbicmethodsettings.resetToDefaultValues();
		
	}
	
	
	
	/**
	 * Activate GSEACMD.
	 */
	protected void ActivateGSEACMD() {
		if(chckbxPerformGsea.isSelected()) {
			comboBoxgsea.setEnabled(true);
			btnAddGseaConfiguration.setEnabled(true);
			btnNewButtonviewconfig.setEnabled(true);
			btnNewButtonremoveconfig.setEnabled(true);
			
		}
		else {
			comboBoxgsea.setEnabled(false);
			btnAddGseaConfiguration.setEnabled(false);
			btnNewButtonviewconfig.setEnabled(false);
			btnNewButtonremoveconfig.setEnabled(false);
			configstable.resetToDefault();
		}
	}
	
	/**
	 * ADDGSEA configuration.
	 */
	protected void ADDGSEAConfiguration() {
		GSEAConfigurationDialog dialog=new GSEAConfigurationDialog((GSEAAnalyserType) comboBoxgsea.getSelectedItem());
		
		try {
			int flag=dialog.showOpenDialog(this);
			if(flag==GSEAConfigurationDialog.APPROVE_OPTION) {
				GSEAConfigurationContainer config=new GSEAConfigurationContainer(dialog.getGSEASettings(), (GSEAAnalyserType) comboBoxgsea.getSelectedItem());
				configstable.addConfiguration(config);
			}
			
			
		} catch (HeadlessException | InterruptedException e) {
			Workbench.getInstance().error(e);
		}
		
	}
	
	/**
	 * Removes the GSEA configuration.
	 */
	protected void removeGSEAConfiguration() {
		if(configstable.getRowCount()>0) {
			int pos=configstable.getSelectedRow();
			configstable.removeConfiguration(pos);
		}
	}
	
	/**
	 * View GSEA configuration.
	 */
	protected void viewGSEAConfiguration() {
		
		GSEAConfigurationContainer config=configstable.getSelectedConfiguration();
		if(config!=null) {
			GSEAConfigurationDialog dialog=new GSEAConfigurationDialog(config.getAnalyserType());

			try {
				int flag = dialog.showOpenDialog(config.getConfig(),this);
				if(flag==GSEAConfigurationDialog.APPROVE_OPTION) {
					config.setProperties(dialog.getGSEASettings());	
				}	
			} catch (HeadlessException | InterruptedException e) {
				e.printStackTrace();
			}	
		}
		else
			Workbench.getInstance().warn("Please select one of the configurations");
	}
	

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(getDialogDimensions());
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
		else if(cmd.equals(PERFORMGSEA)) {
			ActivateGSEACMD();
		}
		else if(cmd.equals(ADDGSEA) && chckbxPerformGsea.isSelected())
			ADDGSEAConfiguration();
		else if(cmd.equals(VIEWGSEA) && configstable.getRowCount()>0)
			viewGSEAConfiguration();
		else if(cmd.equals(REMOVEGSEA)) {
			removeGSEAConfiguration();
		}
		else if(cmd.equals(ProjectSelectionPanel.PROJECT_ACTION_COMMAND)) {
			fillProjectElements(projectSelectionPanel.getSelectedProject());
		}
		else if(cmd.equals(OK)) {
			Pair<Boolean, String> isvalid=panelbicmethodsettings.validSettings();
			if(isvalid!=null && !isvalid.getValue0())
				launchWarning(isvalid.getValue1());
			else
				try {
					terminate();
				} catch (IllegalArgumentException | IOException e1) {
					Workbench.getInstance().error(e1);
				}
		}
		
	}
	
	/**
	 * Launch warning.
	 *
	 * @param msg the msg
	 */
	protected void launchWarning(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Incorrect Parameters", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Terminate.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void terminate() throws IllegalArgumentException, IOException{

		
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("Project", Project.class,projectSelectionPanel.getSelectedProject(), null));
		listSpecs.add(new ParamSpec("Method settings",Properties.class,panelbicmethodsettings.getMethodSettings(), null));
		listSpecs.add(new ParamSpec("GSEA configs",ArrayList.class,configstable.getGSEAConfigurations(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}

}
