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
package jbiclustgegui.gui.views.methods;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.javatuples.Triplet;

import jbiclustge.analysis.coverage.CoverageAnalyser;
import jbiclustgegui.datatypes.biclusteringresults.BiclusteringResultBox;
import jbiclustgegui.gui.components.panels.biclusters.BiclusterAnalysisMethodsPanel;
import jbiclustgegui.gui.components.panels.biclusters.BiclusterMethodSettingsInfoPanel;
import jbiclustgegui.gui.components.panels.biclusters.BiclustersInfoSearchPanel;
import jbiclustgegui.gui.components.panels.biclusters.BiclustersViewPanel;
import pt.ornrocha.stringutils.MTUStringFormat;
import pt.uminho.ceb.biosystems.mew.utilities.java.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclusteringMethodsResultsViewer.
 */
public class BiclusteringMethodsResultsViewer extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;
	
	/** The panelexecinformation. */
	private JPanel panelexecinformation;
	
	/** The results. */
	private BiclusteringResultBox results;
	
	/** The lbl method. */
	private JLabel lblMethod;
	
	/** The text fieldmethodname. */
	private JTextField textFieldmethodname;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The scroll panedisplaybicluster. */
	private JScrollPane scrollPanedisplaybicluster;
	
	/** The panel. */
	private JPanel panel;
	
	/** The panelinfobiclusters. */
	private JPanel panelinfobiclusters;
	
	/** The paneldisplaybiclusters. */
	private BiclustersViewPanel paneldisplaybiclusters;
	
	/** The lbl total biclusters. */
	private JLabel lblTotalBiclusters;
	
	/** The text fieldtotalbiclusters. */
	private JTextField textFieldtotalbiclusters;
	
	/** The lbl gene coverage. */
	private JLabel lblGeneCoverage;
	
	/** The text fieldgenecoverage. */
	private JTextField textFieldgenecoverage;
	
	/** The lbl conditions coverage. */
	private JLabel lblConditionsCoverage;
	
	/** The text fieldcondcoverage. */
	private JTextField textFieldcondcoverage;
	
	/** The lbl matrix coverage. */
	private JLabel lblMatrixCoverage;
	
	/** The text fieldmatrixcoverage. */
	private JTextField textFieldmatrixcoverage;
	
	/** The infopanel. */
	private BiclusterMethodSettingsInfoPanel infopanel;
	
	/** The lbl results saved at. */
	private JLabel lblResultsSavedAt;
	
	/** The text fieldsaveddate. */
	private JTextField textFieldsaveddate;
	
	/** The panelsearchfunctions. */
	private JPanel panelsearchfunctions;
	
	/** The panelsearchfunctionspanel. */
	private BiclustersInfoSearchPanel panelsearchfunctionspanel;
	
	/** The panelanalysis. */
	private JPanel panelanalysis;
	
	/** The panelanalysisbicluster. */
	private BiclusterAnalysisMethodsPanel panelanalysisbicluster;
	
	/**
	 * Instantiates a new biclustering methods results viewer.
	 *
	 * @param results the results
	 */
	public BiclusteringMethodsResultsViewer(BiclusteringResultBox results) {
		this.results=results;
		initGUI();
		fillcomponents();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(this.tabbedPane, gbc_tabbedPane);
		
		this.panelexecinformation = new JPanel();
		this.tabbedPane.addTab("Settings used in execution", null, this.panelexecinformation, null);
		GridBagLayout gbl_panelexecinformation = new GridBagLayout();
		gbl_panelexecinformation.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_panelexecinformation.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1};
		gbl_panelexecinformation.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_panelexecinformation.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		this.panelexecinformation.setLayout(gbl_panelexecinformation);
		
		this.lblMethod = new JLabel("Method:");
		GridBagConstraints gbc_lblMethod = new GridBagConstraints();
		gbc_lblMethod.anchor = GridBagConstraints.EAST;
		gbc_lblMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblMethod.gridx = 1;
		gbc_lblMethod.gridy = 0;
		this.panelexecinformation.add(this.lblMethod, gbc_lblMethod);
		
		this.textFieldmethodname = new JTextField();
		GridBagConstraints gbc_textFieldmethodname = new GridBagConstraints();
		gbc_textFieldmethodname.gridwidth = 2;
		gbc_textFieldmethodname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldmethodname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldmethodname.gridx = 2;
		gbc_textFieldmethodname.gridy = 0;
		this.panelexecinformation.add(this.textFieldmethodname, gbc_textFieldmethodname);
		this.textFieldmethodname.setColumns(10);
		
		this.lblResultsSavedAt = new JLabel("Results saved at:");
		GridBagConstraints gbc_lblResultsSavedAt = new GridBagConstraints();
		gbc_lblResultsSavedAt.anchor = GridBagConstraints.EAST;
		gbc_lblResultsSavedAt.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultsSavedAt.gridx = 4;
		gbc_lblResultsSavedAt.gridy = 0;
		this.panelexecinformation.add(this.lblResultsSavedAt, gbc_lblResultsSavedAt);
		
		this.textFieldsaveddate = new JTextField();
		GridBagConstraints gbc_textFieldsaveddate = new GridBagConstraints();
		gbc_textFieldsaveddate.gridwidth = 2;
		gbc_textFieldsaveddate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldsaveddate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldsaveddate.gridx = 5;
		gbc_textFieldsaveddate.gridy = 0;
		this.panelexecinformation.add(this.textFieldsaveddate, gbc_textFieldsaveddate);
		this.textFieldsaveddate.setColumns(10);
		
		//JPanel informationpanel=getMethodSummaryExecutionParametersPanel();
		infopanel=new BiclusterMethodSettingsInfoPanel();
		this.scrollPane = new JScrollPane(infopanel);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.panelexecinformation.add(this.scrollPane, gbc_scrollPane);
		
		this.panel = new JPanel();
		this.tabbedPane.addTab("Biclusters", null, this.panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0,1.0};
		this.panel.setLayout(gbl_panel);
		
		this.panelinfobiclusters = new JPanel();
		GridBagConstraints gbc_panelinfobiclusters = new GridBagConstraints();
		gbc_panelinfobiclusters.insets = new Insets(10, 0, 10, 0);
		gbc_panelinfobiclusters.fill = GridBagConstraints.BOTH;
		gbc_panelinfobiclusters.gridx = 0;
		gbc_panelinfobiclusters.gridy = 0;
		this.panel.add(this.panelinfobiclusters, gbc_panelinfobiclusters);
		GridBagLayout gbl_panelinfobiclusters = new GridBagLayout();
		gbl_panelinfobiclusters.columnWidths = new int[]{1,1,1,1,1,1,1,1};
		gbl_panelinfobiclusters.rowHeights = new int[]{1,1,1};
		gbl_panelinfobiclusters.columnWeights = new double[]{0.0,1.0,0.0,1.0,0.0,1.0,0.0,1.0};
		gbl_panelinfobiclusters.rowWeights = new double[]{1.0,1.0,1.0};
		this.panelinfobiclusters.setLayout(gbl_panelinfobiclusters);
		
		this.lblTotalBiclusters = new JLabel("Total Biclusters:");
		GridBagConstraints gbc_lblTotalBiclusters = new GridBagConstraints();
		gbc_lblTotalBiclusters.anchor = GridBagConstraints.EAST;
		gbc_lblTotalBiclusters.insets = new Insets(0, 20, 5, 5);
		gbc_lblTotalBiclusters.gridx = 0;
		gbc_lblTotalBiclusters.gridy = 1;
		this.panelinfobiclusters.add(this.lblTotalBiclusters, gbc_lblTotalBiclusters);
		
		this.textFieldtotalbiclusters = new JTextField();
		this.textFieldtotalbiclusters.setText("0");
		GridBagConstraints gbc_textFieldtotalbiclusters = new GridBagConstraints();
		gbc_textFieldtotalbiclusters.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldtotalbiclusters.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldtotalbiclusters.gridx = 1;
		gbc_textFieldtotalbiclusters.gridy = 1;
		this.panelinfobiclusters.add(this.textFieldtotalbiclusters, gbc_textFieldtotalbiclusters);
		this.textFieldtotalbiclusters.setColumns(10);
		
		this.lblGeneCoverage = new JLabel("Gene Coverage:");
		GridBagConstraints gbc_lblGeneCoverage = new GridBagConstraints();
		gbc_lblGeneCoverage.anchor = GridBagConstraints.EAST;
		gbc_lblGeneCoverage.insets = new Insets(0, 20, 5, 5);
		gbc_lblGeneCoverage.gridx = 2;
		gbc_lblGeneCoverage.gridy = 1;
		this.panelinfobiclusters.add(this.lblGeneCoverage, gbc_lblGeneCoverage);
		
		this.textFieldgenecoverage = new JTextField();
		this.textFieldgenecoverage.setText("0");
		GridBagConstraints gbc_textFieldgenecoverage = new GridBagConstraints();
		gbc_textFieldgenecoverage.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldgenecoverage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldgenecoverage.gridx = 3;
		gbc_textFieldgenecoverage.gridy = 1;
		this.panelinfobiclusters.add(this.textFieldgenecoverage, gbc_textFieldgenecoverage);
		this.textFieldgenecoverage.setColumns(10);
		
		this.lblConditionsCoverage = new JLabel("Conditions Coverage:");
		GridBagConstraints gbc_lblConditionsCoverage = new GridBagConstraints();
		gbc_lblConditionsCoverage.anchor = GridBagConstraints.EAST;
		gbc_lblConditionsCoverage.insets = new Insets(0, 20, 5, 5);
		gbc_lblConditionsCoverage.gridx = 4;
		gbc_lblConditionsCoverage.gridy = 1;
		this.panelinfobiclusters.add(this.lblConditionsCoverage, gbc_lblConditionsCoverage);
		
		this.textFieldcondcoverage = new JTextField();
		this.textFieldcondcoverage.setText("0");
		GridBagConstraints gbc_textFieldcondcoverage = new GridBagConstraints();
		gbc_textFieldcondcoverage.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldcondcoverage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldcondcoverage.gridx = 5;
		gbc_textFieldcondcoverage.gridy = 1;
		this.panelinfobiclusters.add(this.textFieldcondcoverage, gbc_textFieldcondcoverage);
		this.textFieldcondcoverage.setColumns(10);
		
		this.lblMatrixCoverage = new JLabel("Matrix Coverage:");
		GridBagConstraints gbc_lblMatrixCoverage = new GridBagConstraints();
		gbc_lblMatrixCoverage.anchor = GridBagConstraints.EAST;
		gbc_lblMatrixCoverage.insets = new Insets(0, 20, 5, 5);
		gbc_lblMatrixCoverage.gridx = 6;
		gbc_lblMatrixCoverage.gridy = 1;
		this.panelinfobiclusters.add(this.lblMatrixCoverage, gbc_lblMatrixCoverage);
		
		this.textFieldmatrixcoverage = new JTextField();
		this.textFieldmatrixcoverage.setText("0");
		GridBagConstraints gbc_textFieldmatrixcoverage = new GridBagConstraints();
		gbc_textFieldmatrixcoverage.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldmatrixcoverage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldmatrixcoverage.gridx = 7;
		gbc_textFieldmatrixcoverage.gridy = 1;
		this.panelinfobiclusters.add(this.textFieldmatrixcoverage, gbc_textFieldmatrixcoverage);
		this.textFieldmatrixcoverage.setColumns(10);
		
		//this.paneldisplaybiclusters = new BiclustersViewPanel();
		this.paneldisplaybiclusters = new BiclustersViewPanel();
		paneldisplaybiclusters.setPreferredSize(new Dimension(1000, 800));
		this.scrollPanedisplaybicluster=new JScrollPane(paneldisplaybiclusters);
		GridBagConstraints gbc_paneldisplaybiclusters = new GridBagConstraints();
		gbc_paneldisplaybiclusters.fill = GridBagConstraints.BOTH;
		gbc_paneldisplaybiclusters.gridx = 0;
		gbc_paneldisplaybiclusters.gridy = 1;
		this.panel.add(this.scrollPanedisplaybicluster, gbc_paneldisplaybiclusters);
		
		this.panelsearchfunctions = new JPanel();
		this.tabbedPane.addTab("Search", null, this.panelsearchfunctions, null);
		GridBagLayout gbl_panelsearchfunctions = new GridBagLayout();
		gbl_panelsearchfunctions.columnWidths = new int[]{1};
		gbl_panelsearchfunctions.rowHeights = new int[]{1};
		gbl_panelsearchfunctions.columnWeights = new double[]{1.0};
		gbl_panelsearchfunctions.rowWeights = new double[]{1.0};
		this.panelsearchfunctions.setLayout(gbl_panelsearchfunctions);
		
		this.panelsearchfunctionspanel = new BiclustersInfoSearchPanel();
		GridBagConstraints gbc_panelsearchfunctionspanel = new GridBagConstraints();
		gbc_panelsearchfunctionspanel.fill = GridBagConstraints.BOTH;
		gbc_panelsearchfunctionspanel.gridx = 0;
		gbc_panelsearchfunctionspanel.gridy = 0;
		this.panelsearchfunctions.add(this.panelsearchfunctionspanel, gbc_panelsearchfunctionspanel);
		
		this.panelanalysis = new JPanel();
		this.tabbedPane.addTab("Analysis", null, this.panelanalysis, null);
		GridBagLayout gbl_panelanalysis = new GridBagLayout();
		gbl_panelanalysis.columnWidths = new int[]{1};
		gbl_panelanalysis.rowHeights = new int[]{1};
		gbl_panelanalysis.columnWeights = new double[]{1.0};
		gbl_panelanalysis.rowWeights = new double[]{1.0};
		this.panelanalysis.setLayout(gbl_panelanalysis);
		
		this.panelanalysisbicluster = new BiclusterAnalysisMethodsPanel();
		GridBagConstraints gbc_panelanalysisbicluster = new GridBagConstraints();
		gbc_panelanalysisbicluster.fill = GridBagConstraints.BOTH;
		gbc_panelanalysisbicluster.gridx = 0;
		gbc_panelanalysisbicluster.gridy = 0;
		this.panelanalysis.add(this.panelanalysisbicluster, gbc_panelanalysisbicluster);
	}

	/**
	 * Fillcomponents.
	 */
	protected void fillcomponents() {
		textFieldmethodname.setText(results.getMethod().getAlgorithmName());
		textFieldmethodname.setEnabled(false);
		infopanel.addBiclusterList(results.getResults());
		paneldisplaybiclusters.addListOfBiclusters(results.getResults());
		panelsearchfunctionspanel.addListOfBiclusterResults(results.getResults());
		panelanalysisbicluster.addBiclusterListResults(results.getResults(),results.getOwnerProject() );
		textFieldtotalbiclusters.setText(String.valueOf(results.getResults().size()));
		textFieldtotalbiclusters.setEditable(false);
		Triplet<Double, Double, Double> coverageparam=CoverageAnalyser.getCoverageParameters(results.getResults());
		textFieldgenecoverage.setText(MTUStringFormat.formatDouble(coverageparam.getValue0()*100)+"%");
		textFieldgenecoverage.setEditable(false);
		textFieldcondcoverage.setText(MTUStringFormat.formatDouble(coverageparam.getValue1()*100)+"%");
		textFieldcondcoverage.setEditable(false);
		textFieldmatrixcoverage.setText(MTUStringFormat.formatDouble(coverageparam.getValue2()*100)+"%");
		textFieldmatrixcoverage.setEditable(false);
		
		if(results.getDate()!=null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			textFieldsaveddate.setText(dtf.format(results.getDate()));
		}
		else
			textFieldsaveddate.setText("Info. is not available");
		textFieldsaveddate.setEditable(false);
	}
	
	/**
	 * Gets the method summary execution parameters panel.
	 *
	 * @return the method summary execution parameters panel
	 */
	protected JPanel getMethodSummaryExecutionParametersPanel() {
		
		JPanel toRet = new JPanel();
		
		LinkedHashMap<String, String> settings=results.getResults().getMethodRunningParameters();
		
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.columnWeights = new double[] {};
		thisLayout.columnWidths = new int[] {};
		toRet.setLayout(thisLayout);
		
		int row = 0;
		for (String labelText : settings.keySet()) {
			
			String showlabelText=labelText.replace("_", " ");
			
			JLabel label = new JLabel(showlabelText+":");
			toRet.add(label, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 3), 0, 0));
			toRet.add(generateStringTextComponent(settings.get(labelText)), new GridBagConstraints(1, row, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0));
			row++;
		}
		
		return toRet;
		
	}
	

	

	/**
	 * Generate string text component.
	 *
	 * @param obj the obj
	 * @return the j component
	 */
	protected JComponent generateStringTextComponent(String obj){
		
		if(obj!=null) {
			JTextField textField = new JTextField(obj.toString());
			textField.setEditable(false);
			return textField;
		}
		else
			return new JTextField("Information not available!");
	}

	
	
	/**
	 * Gets the component from collection.
	 *
	 * @param collection the collection
	 * @return the component from collection
	 */
	protected JComponent getComponentFromCollection(Collection collection){
		ArrayList<String> list = new ArrayList<>(collection);
		return getScrollPaneFromList(list);
	}
	
	/**
	 * Gets the scroll pane from list.
	 *
	 * @param list the list
	 * @return the scroll pane from list
	 */
	protected JScrollPane getScrollPaneFromList(ArrayList<String> list){
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.append(StringUtils.concat("\n", list));
		return new JScrollPane(textArea);
	}
	
	
	
}
