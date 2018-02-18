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
package jbiclustgegui.gui.enrichmentanalysis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.elements.BiclusteringAnalysisElementList;
import jbiclustgegui.gui.components.panels.enrichmentanalysis.AbstractGeneEnrichmentAnalyserSettingsPanel;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;
import jbiclustgegui.gui.components.selection.ResultsBoxType;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractGeneEnrichmentAnalyserGUI.
 */
public abstract class AbstractGeneEnrichmentAnalyserGUI extends JDialog implements ActionListener,InputGUI{

	/** The content panel. */
	protected final JPanel contentPanel = new JPanel();
	
	/** The rec. */
	protected ParamsReceiver rec;
	
	/** The chckbx filter by biclustering. */
	protected JCheckBox chckbxFilterByBiclustering;
	
	/** The combo boxresults. */
	protected JComboBox comboBoxresults;
	
	/** The combo boxbicmethods. */
	protected JComboBox comboBoxbicmethods;
	
	/** The project selection panel. */
	protected ProjectSelectionPanel projectSelectionPanel;
	
	/** The panel settings. */
	protected AbstractGeneEnrichmentAnalyserSettingsPanel panel_settings;
	
	/** The ok button. */
	protected JButton okButton;
	
	/** The currentproject. */
	protected String currentproject=null;
	
	/** The currentresultsype. */
	protected ResultsBoxType currentresultsype=null;
	
	/** The currentresultstypelist. */
	protected ArrayList<ResultsBoxType> currentresultstypelist;
	
	
	/** The close. */
	protected static String CLOSE="close";
	
	/** The ok. */
	protected static String OK="ok";
	
	/** The filterbybicmethod. */
	protected static String FILTERBYBICMETHOD="filterbybiclusteringmethod";
	
	/** The choosebiclusteringmethod. */
	protected static String CHOOSEBICLUSTERINGMETHOD="choosebiclusteringmethod";
	 
	
	
	/**
	 * Instantiates a new abstract gene enrichment analyser GUI.
	 */
	public AbstractGeneEnrichmentAnalyserGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
		initComponents();
	}
	
    /**
     * Gets the GSEA settings panel.
     *
     * @return the GSEA settings panel
     */
    protected abstract AbstractGeneEnrichmentAnalyserSettingsPanel getGSEASettingsPanel();
	
	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 1318, 804);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			projectSelectionPanel = new ProjectSelectionPanel();
			GridBagConstraints gbc_projectSelectionPanel = new GridBagConstraints();
			gbc_projectSelectionPanel.gridwidth = 10;
			gbc_projectSelectionPanel.insets = new Insets(0, 0, 5, 0);
			gbc_projectSelectionPanel.fill = GridBagConstraints.BOTH;
			gbc_projectSelectionPanel.gridx = 0;
			gbc_projectSelectionPanel.gridy = 0;
			contentPanel.add(projectSelectionPanel, gbc_projectSelectionPanel);
			projectSelectionPanel.setProjectActionListener(this);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Select Result List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 10;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1,1,1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{0.0,1.0,1.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				chckbxFilterByBiclustering = new JCheckBox("Filter by biclustering method");
				GridBagConstraints gbc_chckbxFilterByBiclustering = new GridBagConstraints();
				gbc_chckbxFilterByBiclustering.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxFilterByBiclustering.gridx = 0;
				gbc_chckbxFilterByBiclustering.gridy = 0;
				panel.add(chckbxFilterByBiclustering, gbc_chckbxFilterByBiclustering);
				chckbxFilterByBiclustering.setActionCommand(FILTERBYBICMETHOD);
				chckbxFilterByBiclustering.addActionListener(this);
			}
			comboBoxbicmethods = new JComboBox();
			GridBagConstraints gbc_comboBoxbicmethods = new GridBagConstraints();
			gbc_comboBoxbicmethods.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxbicmethods.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxbicmethods.gridx = 1;
			gbc_comboBoxbicmethods.gridy = 0;
			panel.add(comboBoxbicmethods, gbc_comboBoxbicmethods);
			comboBoxbicmethods.setActionCommand(CHOOSEBICLUSTERINGMETHOD);
			comboBoxbicmethods.addActionListener(this);
			{
				comboBoxresults = new JComboBox();
				GridBagConstraints gbc_comboBoxresults = new GridBagConstraints();
				gbc_comboBoxresults.gridwidth = 2;
				gbc_comboBoxresults.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxresults.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxresults.gridx = 2;
				gbc_comboBoxresults.gridy = 0;
				panel.add(comboBoxresults, gbc_comboBoxresults);
			}
			{
				{
					this.panel_settings = getGSEASettingsPanel();
					GridBagConstraints gbc_panel_settings = new GridBagConstraints();
					gbc_panel_settings.gridheight = 13;
					gbc_panel_settings.gridwidth = 10;
					gbc_panel_settings.insets = new Insets(0, 0, 5, 5);
					gbc_panel_settings.fill = GridBagConstraints.BOTH;
					gbc_panel_settings.gridx = 0;
					gbc_panel_settings.gridy = 2;
					contentPanel.add(this.panel_settings, gbc_panel_settings);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
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
	 * Inits the components.
	 */
	private void initComponents() {
		
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
			
			BiclusteringAnalysisElementList<IElementList<IBiclusteringMethodResult>> allelems=proj.getBiclusteringResult();
			if(allelems.size()==0) {
				okButton.setEnabled(false);
				Workbench.getInstance().warn("The selected project do not contains any results concerning biclustering algorithms.\nSelect other project or run a biclustering algorithm first.");
			}
			else {
				resetToDefault();
				fillComboboxResults(proj, null);
				checkCurrentResultTypes(proj);
				okButton.setEnabled(true);
			}
			
		}
	}
	
	
	/**
	 * Check current result types.
	 *
	 * @param proj the proj
	 */
	private void checkCurrentResultTypes(Project proj) {
		
		currentresultstypelist=new ArrayList<>();

		
		BiclusteringAnalysisElementList<IElementList<IBiclusteringMethodResult>> allelems=proj.getBiclusteringResult();
		if(allelems!=null) {
			for (int i = 0; i < allelems.size(); i++) {
				IElementList<IBiclusteringMethodResult> resultsboxsubelems=allelems.getElement(i);

				IBiclusteringMethodResult elem=resultsboxsubelems.getElement(0);
				Class<?> elemclass=elem.getClass();
				for (ResultsBoxType resultsBoxType : ResultsBoxType.values()) {
						Class<?> resultclass=resultsBoxType.getResultsBoxClass();
						if(resultclass.equals(elemclass)) {
							if(!currentresultstypelist.contains(resultsBoxType))
								currentresultstypelist.add(resultsBoxType);
							else
								break;
						}
					}
			}
			
			comboBoxbicmethods.removeAllItems();
			for (int i = 0; i < currentresultstypelist.size(); i++) {
				comboBoxbicmethods.addItem(currentresultstypelist.get(i));
			}
	  }
	}
	
	
	/**
	 * Reset to default.
	 */
	private void resetToDefault() {
		chckbxFilterByBiclustering.setSelected(false);
		comboBoxbicmethods.setEnabled(false);
	}
	
	
	/**
	 * Fill combobox results.
	 *
	 * @param proj the proj
	 * @param typeresults the typeresults
	 */
	private void fillComboboxResults(Project proj, ResultsBoxType typeresults) {
		
	
		if(typeresults==null || typeresults.equals(ResultsBoxType.ALL)) {
			
			BiclusteringAnalysisElementList<IElementList<IBiclusteringMethodResult>> allelems=proj.getBiclusteringResult();
			comboBoxresults.removeAllItems();
			if(allelems!=null) {
				for (int i = 0; i < allelems.size(); i++) {
					IElementList<IBiclusteringMethodResult> resultsboxall=allelems.getElement(i);
					for (int j = 0; j <resultsboxall.size(); j++) {
						IBiclusteringMethodResult elem=resultsboxall.getElement(j);
						comboBoxresults.addItem(elem);
					}
				}
			}
		}
		else {
			Class<?> clazz=typeresults.getResultsBoxClass();			
			IElementList<IBiclusteringMethodResult> resultsbox=proj.getBiclusteringMethodResultElementListByClass(clazz);
			comboBoxresults.removeAllItems();
			if(resultsbox!=null) {
				for (int i = 0; i < resultsbox.size(); i++) {
					IBiclusteringMethodResult elem=resultsbox.getElement(i);
					comboBoxresults.addItem(elem);
				}
			}
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
		this.setPreferredSize(panel_settings.getPreferredDimensionsToPanel());
		//this.setPreferredSize(new Dimension(1000,700));
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
	 * Update results combo box.
	 */
	private void updateResultsComboBox() {
		
		if(comboBoxbicmethods.isEnabled()) {
			ResultsBoxType restype=(ResultsBoxType) comboBoxbicmethods.getSelectedItem();
			if(currentresultsype==null || !restype.equals(currentresultsype)) {
				fillComboboxResults(projectSelectionPanel.getSelectedProject(), restype);
				currentresultsype=restype;
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(CLOSE))
			finish();
		else if(cmd.equals(FILTERBYBICMETHOD)) {
			if(chckbxFilterByBiclustering.isSelected() && comboBoxbicmethods.getModel().getSize()>0) {
				comboBoxbicmethods.setEnabled(true);
				updateResultsComboBox();
			}
			else if(!chckbxFilterByBiclustering.isSelected() && comboBoxbicmethods.getModel().getSize()>0) {
				comboBoxbicmethods.setEnabled(false);
				fillComboboxResults(projectSelectionPanel.getSelectedProject(),ResultsBoxType.ALL);
				currentresultsype=null;
			}
		}
		else if(cmd.equals(CHOOSEBICLUSTERINGMETHOD)) {
			updateResultsComboBox();
		}	
		else if(cmd.equals(ProjectSelectionPanel.PROJECT_ACTION_COMMAND )) {
			fillProjectElements(projectSelectionPanel.getSelectedProject());
		}
		else if(cmd.equals(OK)) {
			Pair<Boolean, String> isvalid=panel_settings.validSettings();
			if(isvalid!=null && !isvalid.getValue0())
				launchWarning(isvalid.getValue1());
			else
				terminate();
		}
	}
	
	/**
	 * Launch warning.
	 *
	 * @param msg the msg
	 */
	private void launchWarning(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Incorrect Parameters", JOptionPane.WARNING_MESSAGE);
	}
	
	
	/**
	 * Terminate.
	 */
	protected void terminate(){

		BiclusteringResultBox resultsbox=(BiclusteringResultBox) comboBoxresults.getSelectedItem();
		
		BiclusterList results=resultsbox.getResults();
		
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("Project", Project.class,projectSelectionPanel.getSelectedProject(), null));
		listSpecs.add(new ParamSpec("Biclustering results",BiclusterList.class,results, null));
		listSpecs.add(new ParamSpec("Settings analyser", Properties.class,panel_settings.getExecutionSettings(), null));
		listSpecs.add(new ParamSpec("Biclustering results name", String.class,resultsbox.getName(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}
	
	

}
