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
package jbiclustgegui.gui.analysis.similarity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.analysis.similarity.components.SimilarityIndexMethod;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.datatypes.components.IBiclusteringMethodResult;
import jbiclustgegui.datatypes.components.IElementList;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.datatypes.project.elements.BiclusteringAnalysisElementList;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;
import jbiclustgegui.gui.components.selection.ResultsBoxType;

// TODO: Auto-generated Javadoc
/**
 * The Class PairwiseSimilarityMultipleListBiclustersDialog.
 */
public class PairwiseSimilarityMultipleListBiclustersDialog extends JDialog implements ActionListener,InputGUI{

	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The rec. */
	private ParamsReceiver rec;
	
	/** The project selection panel. */
	private ProjectSelectionPanel projectSelectionPanel;
	
	/** The combo boxbicmethods. */
	private JComboBox comboBoxbicmethods;
	
	/** The combo boxresults. */
	private JComboBox comboBoxresults;
	
	/** The chckbx filter by biclustering. */
	private JCheckBox chckbxFilterByBiclustering;
	
	/** The ok button. */
	private JButton okButton;
	
	/** The currentproject. */
	private String currentproject=null;
	
	/** The btn add list. */
	private JButton btnAddList;
	
	/** The btn remove list. */
	private JButton btnRemoveList;
	
	/** The btn remove all. */
	private JButton btnRemoveAll;
	
	/** The listresults. */
	private JList listresults;
	
	/** The list model. */
	private DefaultListModel listModel;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The combo boxsimilaritymethods. */
	private JComboBox comboBoxsimilaritymethods;
	
	/** The currentresultsype. */
	private ResultsBoxType currentresultsype=null;
	
	/** The currentresultstypelist. */
	private ArrayList<ResultsBoxType> currentresultstypelist;
	
	/** The close. */
	protected static String CLOSE="close";
	
	/** The ok. */
	protected static String OK="ok";
	
	/** The filterbybicmethod. */
	protected static String FILTERBYBICMETHOD="filterbybiclusteringmethod";
	
	/** The choosebiclusteringmethod. */
	protected static String CHOOSEBICLUSTERINGMETHOD="choosebiclusteringmethod";
	
	/** The addlist. */
	protected static String ADDLIST="addlist";
	
	/** The removelist. */
	protected static String REMOVELIST="removelist";
	
	/** The removeall. */
	protected static String REMOVEALL="removeall";
	

	
	/**
	 * Instantiates a new pairwise similarity multiple list biclusters dialog.
	 */
	public PairwiseSimilarityMultipleListBiclustersDialog() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
		initComponents();
	}

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 707, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
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
			panel.setBorder(new TitledBorder(null, "Select Result List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1, 1, 1, 1, 0};
			gbl_panel.rowHeights = new int[]{1, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				chckbxFilterByBiclustering = new JCheckBox("Filter by biclustering method");
				chckbxFilterByBiclustering.setActionCommand("filterbybiclusteringmethod");
				GridBagConstraints gbc_chckbxFilterByBiclustering = new GridBagConstraints();
				gbc_chckbxFilterByBiclustering.insets = new Insets(0, 0, 0, 5);
				gbc_chckbxFilterByBiclustering.gridx = 0;
				gbc_chckbxFilterByBiclustering.gridy = 0;
				panel.add(chckbxFilterByBiclustering, gbc_chckbxFilterByBiclustering);
				chckbxFilterByBiclustering.setActionCommand(FILTERBYBICMETHOD);
				chckbxFilterByBiclustering.addActionListener(this);
			}
			{
				comboBoxbicmethods = new JComboBox();
				comboBoxbicmethods.setActionCommand("choosebiclusteringmethod");
				GridBagConstraints gbc_comboBoxbicmethods = new GridBagConstraints();
				gbc_comboBoxbicmethods.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxbicmethods.insets = new Insets(0, 0, 0, 5);
				gbc_comboBoxbicmethods.gridx = 1;
				gbc_comboBoxbicmethods.gridy = 0;
				panel.add(comboBoxbicmethods, gbc_comboBoxbicmethods);
			}
			{
				comboBoxresults = new JComboBox();
				GridBagConstraints gbc_comboBoxresults = new GridBagConstraints();
				gbc_comboBoxresults.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxresults.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxresults.gridwidth = 2;
				gbc_comboBoxresults.gridx = 2;
				gbc_comboBoxresults.gridy = 0;
				panel.add(comboBoxresults, gbc_comboBoxresults);
				comboBoxbicmethods.setActionCommand(CHOOSEBICLUSTERINGMETHOD);
				comboBoxbicmethods.addActionListener(this);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1,1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{0.0,1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1};
				gbl_panel_1.rowHeights = new int[]{1,1,1,1,1,1,1,1};
				gbl_panel_1.columnWeights = new double[]{1.0};
				gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
				panel_1.setLayout(gbl_panel_1);
				{
					btnAddList = new JButton("Add to list");
					GridBagConstraints gbc_btnAddList = new GridBagConstraints();
					gbc_btnAddList.fill = GridBagConstraints.BOTH;
					gbc_btnAddList.insets = new Insets(0, 0, 5, 0);
					gbc_btnAddList.gridx = 0;
					gbc_btnAddList.gridy = 1;
					panel_1.add(btnAddList, gbc_btnAddList);
					btnAddList.setActionCommand(ADDLIST);
					btnAddList.addActionListener(this);
				}
				{
					btnRemoveList = new JButton("Remove from list");
					GridBagConstraints gbc_btnRemoveList = new GridBagConstraints();
					gbc_btnRemoveList.fill = GridBagConstraints.VERTICAL;
					gbc_btnRemoveList.insets = new Insets(0, 0, 5, 0);
					gbc_btnRemoveList.gridx = 0;
					gbc_btnRemoveList.gridy = 2;
					panel_1.add(btnRemoveList, gbc_btnRemoveList);
					btnRemoveList.addActionListener(this);
					btnRemoveList.setActionCommand(REMOVELIST);
				}
				{
					btnRemoveAll = new JButton("Remove all");
					GridBagConstraints gbc_btnRemoveAll = new GridBagConstraints();
					gbc_btnRemoveAll.fill = GridBagConstraints.BOTH;
					gbc_btnRemoveAll.insets = new Insets(0, 0, 5, 0);
					gbc_btnRemoveAll.gridx = 0;
					gbc_btnRemoveAll.gridy = 3;
					panel_1.add(btnRemoveAll, gbc_btnRemoveAll);
					btnRemoveAll.setActionCommand(REMOVEALL);
					btnRemoveAll.addActionListener(this);
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 0;
				panel.add(scrollPane, gbc_scrollPane);
				{
					listresults = new JList();
					scrollPane.setViewportView(listresults);
				}
			}
		}
		{
			this.panel_2 = new JPanel();
			this.panel_2.setBorder(new TitledBorder(null, "Similarity index method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel_2 = new GridBagConstraints();
			gbc_panel_2.gridheight = 2;
			gbc_panel_2.fill = GridBagConstraints.BOTH;
			gbc_panel_2.gridx = 0;
			gbc_panel_2.gridy = 5;
			contentPanel.add(this.panel_2, gbc_panel_2);
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{1,1,1,1};
			gbl_panel_2.rowHeights = new int[]{1};
			gbl_panel_2.columnWeights = new double[]{1.0,1.0,1.0,1.0};
			gbl_panel_2.rowWeights = new double[]{1.0};
			this.panel_2.setLayout(gbl_panel_2);
			{
				this.comboBoxsimilaritymethods = new JComboBox();
				GridBagConstraints gbc_comboBoxsimilaritymethods = new GridBagConstraints();
				gbc_comboBoxsimilaritymethods.gridwidth = 2;
				gbc_comboBoxsimilaritymethods.insets = new Insets(0, 0, 0, 5);
				gbc_comboBoxsimilaritymethods.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxsimilaritymethods.gridx = 1;
				gbc_comboBoxsimilaritymethods.gridy = 0;
				this.panel_2.add(this.comboBoxsimilaritymethods, gbc_comboBoxsimilaritymethods);
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
				cancelButton.setActionCommand(CLOSE);
				cancelButton.addActionListener(this);
			}
		}
	}
	

	/**
	 * Inits the components.
	 */
	private void initComponents() {
		
		for (SimilarityIndexMethod method : SimilarityIndexMethod.values()) {
			comboBoxsimilaritymethods.addItem(method);
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
	 * Reset to default.
	 */
	private void resetToDefault() {
		chckbxFilterByBiclustering.setSelected(false);
		comboBoxbicmethods.setEnabled(false);
		comboBoxsimilaritymethods.setSelectedIndex(0);
		listModel = new DefaultListModel();
		listresults.setModel(listModel);
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
	
	/**
	 * Adds the bicluster list.
	 */
	private void addBiclusterList() {
		
		BiclusteringResultBox resultsbox=(BiclusteringResultBox) comboBoxresults.getSelectedItem();
		if(!listModel.contains(resultsbox))
			listModel.addElement(resultsbox);
	}
	
	/**
	 * Removes the bicluster list.
	 */
	private void removeBiclusterList() {
		
		if(listModel.size()>0) {
			int index=listresults.getSelectedIndex();
			listModel.remove(index);
		}
	}
	
	/**
	 * Removes the allelements.
	 */
	private void removeAllelements() {
		
		if(listModel.size()>0) {
			for (int i = 0; i < listModel.size(); i++) {
				listModel.remove(i);
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
		this.setPreferredSize(new Dimension(700,500));
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
		else if(cmd.equals(ProjectSelectionPanel.PROJECT_ACTION_COMMAND )) {
			fillProjectElements(projectSelectionPanel.getSelectedProject());
		}
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
		else if(cmd.equals(ADDLIST))
			addBiclusterList();
		else if(cmd.equals(REMOVELIST))
			removeBiclusterList();
		else if(cmd.equals(REMOVEALL))
			removeAllelements();
		else if(cmd.equals(OK)) {
			terminate();
		}
		
	}
	
	/**
	 * Terminate.
	 */
	protected void terminate(){
		
		ArrayList<BiclusteringResultBox> listtocompare=new ArrayList<>();
		
		for (int i = 0; i < listModel.getSize(); i++) {
			listtocompare.add((BiclusteringResultBox) listModel.get(i));
		}
		
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("Project", Project.class,projectSelectionPanel.getSelectedProject(), null));
		listSpecs.add(new ParamSpec("List to compare",ArrayList.class,listtocompare, null));
		listSpecs.add(new ParamSpec("Similarity method", SimilarityIndexMethod.class,comboBoxsimilaritymethods.getSelectedItem(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
		
	}

}
