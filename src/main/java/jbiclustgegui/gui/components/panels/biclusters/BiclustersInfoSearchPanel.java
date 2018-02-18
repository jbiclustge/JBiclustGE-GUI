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
package jbiclustgegui.gui.components.panels.biclusters;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pt.ornrocha.excelutils.ExcelVersion;
import pt.ornrocha.excelutils.MTUExcelWriterUtils;
import pt.ornrocha.swingutils.textfield.CopyPasteJTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.results.biclusters.containers.BiclusterList;
import jbiclustge.results.biclusters.containers.BiclusterResult;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;
import org.javatuples.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

// TODO: Auto-generated Javadoc
/**
 * The Class BiclustersInfoSearchPanel.
 */
public class BiclustersInfoSearchPanel extends JPanel{
	
	/** The lbl search key. */
	private JLabel lblSearchKey;
	
	/** The copy paste J text field. */
	private CopyPasteJTextField copyPasteJTextField;
	
	/** The btn gene. */
	private JButton btnGene;
	
	/** The btn condition. */
	private JButton btnCondition;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The list. */
	private JList list;
	
	/** The results. */
	private BiclusterList results;
	
	/** The lbl number occurences. */
	private JLabel lblNumberOccurences;
	
	/** The text fieldnumberoccurences. */
	private JTextField textFieldnumberoccurences;
	
	/** The btn new buttongenefreq. */
	private JButton btnNewButtongenefreq;
	
	/** The btn new buttoncodfreq. */
	private JButton btnNewButtoncodfreq;
	
	/** The btn export. */
	private JButton btnExport;
	
	/** The geneinfo. */
	private boolean geneinfo=true;
	
	/** The currentviewislist. */
	private boolean currentviewislist=true;
	
	/** The currentplotpanel. */
	private ChartPanel currentplotpanel;
	
	/** The freqresults. */
	private ArrayList<ComponentFrequencyListComponent> freqresults;
	
	/** The btn view plot. */
	private JButton btnViewPlot;

	/**
	 * Instantiates a new biclusters info search panel.
	 */
	public BiclustersInfoSearchPanel() {
		initGUI();
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,0.0,0.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		this.lblSearchKey = new JLabel("Search key:");
		GridBagConstraints gbc_lblSearchKey = new GridBagConstraints();
		gbc_lblSearchKey.anchor = GridBagConstraints.EAST;
		gbc_lblSearchKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchKey.gridx = 0;
		gbc_lblSearchKey.gridy = 0;
		add(this.lblSearchKey, gbc_lblSearchKey);
		
		this.copyPasteJTextField = new CopyPasteJTextField();
		GridBagConstraints gbc_copyPasteJTextField = new GridBagConstraints();
		gbc_copyPasteJTextField.gridwidth = 4;
		gbc_copyPasteJTextField.insets = new Insets(0, 0, 5, 5);
		gbc_copyPasteJTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_copyPasteJTextField.gridx = 1;
		gbc_copyPasteJTextField.gridy = 0;
		add(this.copyPasteJTextField, gbc_copyPasteJTextField);
		
		this.btnGene = new JButton("Gene");
		this.btnGene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!copyPasteJTextField.getText().isEmpty())
					findProvidedKey(true);
				else
					Workbench.getInstance().warn("Please set the gene name that you want to search");
			}
		});
		this.btnGene.setIcon(new ImageIcon(BiclustersInfoSearchPanel.class.getResource("/images/i24x24/searchkey.png")));
		GridBagConstraints gbc_btnGene = new GridBagConstraints();
		gbc_btnGene.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGene.insets = new Insets(0, 0, 5, 5);
		gbc_btnGene.gridx = 5;
		gbc_btnGene.gridy = 0;
		add(this.btnGene, gbc_btnGene);
		
		this.btnCondition = new JButton("Condition");
		this.btnCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!copyPasteJTextField.getText().isEmpty())
					findProvidedKey(false);
				else
					Workbench.getInstance().warn("Please set the conditions name that you want to search");
				
			}
		});
		this.btnCondition.setIcon(new ImageIcon(BiclustersInfoSearchPanel.class.getResource("/images/i24x24/searchkey.png")));
		GridBagConstraints gbc_btnCondition = new GridBagConstraints();
		gbc_btnCondition.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCondition.insets = new Insets(0, 0, 5, 5);
		gbc_btnCondition.gridx = 6;
		gbc_btnCondition.gridy = 0;
		add(this.btnCondition, gbc_btnCondition);
		
		this.btnNewButtongenefreq = new JButton("Gene Frequencies");
		this.btnNewButtongenefreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(results.size()>0)
					findFrequencies(true);
			}
		});
		this.btnNewButtongenefreq.setIcon(new ImageIcon(BiclustersInfoSearchPanel.class.getResource("/images/i24x24/searchkey.png")));
		GridBagConstraints gbc_btnNewButtongenefreq = new GridBagConstraints();
		gbc_btnNewButtongenefreq.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtongenefreq.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButtongenefreq.gridwidth = 3;
		gbc_btnNewButtongenefreq.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtongenefreq.gridx = 1;
		gbc_btnNewButtongenefreq.gridy = 1;
		add(this.btnNewButtongenefreq, gbc_btnNewButtongenefreq);
		
		this.btnNewButtoncodfreq = new JButton("Condition Frequencies");
		this.btnNewButtoncodfreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(results.size()>0)
					findFrequencies(false);
				
			}
		});
		this.btnNewButtoncodfreq.setIcon(new ImageIcon(BiclustersInfoSearchPanel.class.getResource("/images/i24x24/searchkey.png")));
		GridBagConstraints gbc_btnNewButtoncodfreq = new GridBagConstraints();
		gbc_btnNewButtoncodfreq.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButtoncodfreq.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButtoncodfreq.gridwidth = 3;
		gbc_btnNewButtoncodfreq.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButtoncodfreq.gridx = 4;
		gbc_btnNewButtoncodfreq.gridy = 1;
		add(this.btnNewButtoncodfreq, gbc_btnNewButtoncodfreq);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(this.scrollPane, gbc_scrollPane);
		
		this.list = new JList();
		this.scrollPane.setViewportView(this.list);
		
		this.lblNumberOccurences = new JLabel("Number Occurences");
		GridBagConstraints gbc_lblNumberOccurences = new GridBagConstraints();
		gbc_lblNumberOccurences.anchor = GridBagConstraints.SOUTH;
		gbc_lblNumberOccurences.insets = new Insets(0, 10, 5, 20);
		gbc_lblNumberOccurences.gridx = 11;
		gbc_lblNumberOccurences.gridy = 2;
		add(this.lblNumberOccurences, gbc_lblNumberOccurences);
		
		this.textFieldnumberoccurences = new JTextField();
		GridBagConstraints gbc_textFieldnumberoccurences = new GridBagConstraints();
		gbc_textFieldnumberoccurences.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnumberoccurences.anchor = GridBagConstraints.NORTH;
		gbc_textFieldnumberoccurences.insets = new Insets(0, 10, 5, 20);
		gbc_textFieldnumberoccurences.gridx = 11;
		gbc_textFieldnumberoccurences.gridy = 3;
		add(this.textFieldnumberoccurences, gbc_textFieldnumberoccurences);
		this.textFieldnumberoccurences.setColumns(10);
		
		this.btnExport = new JButton("Export");
		this.btnExport.setIcon(new ImageIcon(BiclustersInfoSearchPanel.class.getResource("/images/i24x24/export.png")));
		this.btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					exportFrequencyResults();
				} catch (IOException e1) {
					Workbench.getInstance().error(e1);
				}
				
			}
		});
		
		btnViewPlot = new JButton("View Plot");
		btnViewPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(freqresults!=null)
				changeDisplayResults();
				
			}
		});
		/*GridBagConstraints gbc_btnViewPlot = new GridBagConstraints();
		gbc_btnViewPlot.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewPlot.gridx = 11;
		gbc_btnViewPlot.gridy = 5;
		add(btnViewPlot, gbc_btnViewPlot);*/
		
		/*GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExport.insets = new Insets(0, 0, 5, 0);
		gbc_btnExport.gridx = 11;
		gbc_btnExport.gridy = 4;
		add(this.btnExport, gbc_btnExport);*/
	}
	
	/**
	 * Adds the list of bicluster results.
	 *
	 * @param results the results
	 */
	public void addListOfBiclusterResults(BiclusterList results) {
		this.results=results;
	}
	
	
	
	/**
	 * Find provided key.
	 *
	 * @param isgene the isgene
	 */
	private void findProvidedKey(boolean isgene) {
		
		String inputkey=copyPasteJTextField.getText();
		if(!currentviewislist) {
			this.scrollPane.remove(currentplotpanel);
			this.scrollPane.setViewportView(this.list);
			resetButtonDisplay();
			currentviewislist=true;
			
		}
		
		ArrayList<BiclusterInfoListComponent> searchresults=new ArrayList<>();
		
		for (int i = 0; i < results.size(); i++) {
			BiclusterResult bicres=results.get(i);
			
			if(isgene) {
				ArrayList<String> genenames=bicres.getGeneNames();
				if(genenames.contains(inputkey))
					searchresults.add(new BiclusterInfoListComponent(i, "Bicluster", bicres));
			}
			else {
				ArrayList<String> condnames=bicres.getConditionNames();
				if(condnames.contains(inputkey))
					searchresults.add(new BiclusterInfoListComponent(i, "Bicluster", bicres));
			}
		}
		
		final DefaultListModel model = new DefaultListModel();
		if(searchresults.size()>0) {
			for (int i = 0; i < searchresults.size(); i++) {
			  model.addElement(searchresults.get(i));
			}
			textFieldnumberoccurences.setText(String.valueOf(searchresults.size()));
		}
		else {
			model.addElement("No occurrences were found");
			textFieldnumberoccurences.setText("0");	
		}
		
		list.removeAll();
		list.setModel(model);
		freqresults=null;
		currentviewislist=true;
		remove(btnExport);
		remove(btnViewPlot);
		updateUI();
	}
	
	
	/**
	 * Find frequencies.
	 *
	 * @param isgenes the isgenes
	 */
	private void findFrequencies(boolean isgenes) {
		
		if(!currentviewislist) {
			this.scrollPane.remove(currentplotpanel);
			this.scrollPane.setViewportView(this.list);
			resetButtonDisplay();
			currentviewislist=true;
		}
		
		ArrayList<String> elems=null;
		if(isgenes) {
			elems=results.getAllGenesOfBiclusters();
			geneinfo=true;
		}
		else {
			elems=results.getAllConditionsOfBiclusters();
		    geneinfo=false;	
		}
		
		
		Collections.sort(elems);
		
		freqresults=new ArrayList<>();
		
		for (int i = 0; i < elems.size(); i++) {
			String id=elems.get(i);
			ArrayList<Integer> associatedbics=new ArrayList<>();
			int occ=0;
			for (int j = 0; j < results.size(); j++) {
				BiclusterResult bicres=results.get(j);
				
				if(isgenes) {
				   if(bicres.getGeneNames().contains(id)) {
					   occ++;
					   associatedbics.add(j);
				   }
			    }
				else {
					if(bicres.getConditionNames().contains(id)) {
						occ++;
						associatedbics.add(j);
					}
				}
			}
			
			freqresults.add(new ComponentFrequencyListComponent(id, occ, results.size(), associatedbics));
			
		}
		
		final DefaultListModel model = new DefaultListModel();
		
		if(freqresults.size()>0) {
			
			Collections.sort(freqresults,Collections.reverseOrder());
			
			for (int i = 0; i < freqresults.size(); i++) {
			  model.addElement(freqresults.get(i));
			}
			textFieldnumberoccurences.setText(String.valueOf(freqresults.size()));
		}
		else {
			model.addElement("No occurrences were found");
			textFieldnumberoccurences.setText("");	
		}
		
		list.removeAll();
		list.setModel(model);
		showButtons();
		
	}
	
	/**
	 * Show buttons.
	 */
	private void showButtons() {
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExport.insets = new Insets(0, 0, 5, 0);
		gbc_btnExport.gridx = 11;
		gbc_btnExport.gridy = 4;
		add(this.btnExport, gbc_btnExport);
		
		GridBagConstraints gbc_btnViewPlot = new GridBagConstraints();
		gbc_btnViewPlot.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewPlot.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewPlot.gridx = 11;
		gbc_btnViewPlot.gridy = 5;
		add(btnViewPlot, gbc_btnViewPlot);
		updateUI();
	}
	
	/**
	 * Change display results.
	 */
	private void changeDisplayResults() {
		
		if(currentviewislist) {
			//currentplotpanel=new ChartPanel(createChart());
			int height=10*freqresults.size();
			currentplotpanel=new ChartPanel(createChart(), 
					ChartPanel.DEFAULT_WIDTH, height, 
					ChartPanel.DEFAULT_MINIMUM_DRAW_WIDTH,
					ChartPanel.DEFAULT_MINIMUM_DRAW_HEIGHT, 
					ChartPanel.DEFAULT_MAXIMUM_DRAW_WIDTH,
					height*10, true, true, true, true, false, true);
			this.scrollPane.setViewportView(currentplotpanel);
			btnViewPlot.setText("View list results");
			currentviewislist=false;
		}
		else {
			this.scrollPane.setViewportView(this.list);
			btnViewPlot.setText("View Plot");
			currentviewislist=true;
		}
	}
	
	/**
	 * Reset button display.
	 */
	private void resetButtonDisplay() {
		if(!currentviewislist) {
			btnViewPlot.setText("View Plot");
		}
	}
	
	/**
	 * Export frequency results.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void exportFrequencyResults() throws IOException {
		
		if(freqresults!=null) {
			JFileChooser fileChooser = new JFileChooser();
			
			int tag=fileChooser.showSaveDialog(this);
			if(tag==JFileChooser.APPROVE_OPTION) {
				saveToExcel(fileChooser.getSelectedFile().getAbsolutePath());
			}
		
		}
		
	}
	
	/**
	 * Save to excel.
	 *
	 * @param filepath the filepath
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void saveToExcel(String filepath) throws IOException {
		
		
		ArrayList<ArrayList<Object>> intputdata=new ArrayList<>();
		
		ArrayList<Object> header=new ArrayList<>();
		if(geneinfo)
			header.add("Gene identifier");
		else
			header.add("Condition identifier");
		header.add("Frequency");
		header.add("Associated Biclusters");
		intputdata.add(header);
		
		for (int i = 0; i < freqresults.size(); i++) {
			ArrayList<Object> row=new ArrayList<>();
			ComponentFrequencyListComponent elem=freqresults.get(i);
			
			row.add(elem.getId());
			row.add(elem.getFrequency()*100);
			row.add(elem.getAssociatedBiclustersString(false));
			intputdata.add(row);
		}
		
		String sheetname=null;
		if(geneinfo)
			sheetname="Gene Frequencies";
		else
			sheetname="Condition Frequencies";
		File f=new File(filepath);
		if(f.exists()) {
			int tag=JOptionPane.showConfirmDialog(this, "A file with same name already exists do you want to append the results to this file?", "Warning", JOptionPane.YES_NO_OPTION);
			if(tag==JOptionPane.YES_OPTION) {
				MTUExcelWriterUtils.updateDataOfExcelFile(filepath, intputdata, sheetname);
			}
			else {
				filepath=FilenameUtils.concat(FilenameUtils.getFullPath(filepath), FilenameUtils.getBaseName(filepath));
				MTUExcelWriterUtils.WriteDataToNewExcelFile(filepath, ExcelVersion.XLSX, intputdata, sheetname);
			}
		}
		else
		 MTUExcelWriterUtils.WriteDataToNewExcelFile(filepath, ExcelVersion.XLSX, intputdata,sheetname);
	}
	
	
	

	/**
	 * Creates the chart.
	 *
	 * @return the j free chart
	 */
	protected JFreeChart createChart() {
		
		ArrayList<String> yaxis=new ArrayList<>();
		ArrayList<Double> xaxis=new ArrayList<>();
		
		for (ComponentFrequencyListComponent fr : freqresults) {
			yaxis.add(fr.getId());
			xaxis.add(fr.getFrequency()*100);
			
		}

		
		DefaultCategoryDataset data=(DefaultCategoryDataset) getCategoryDataset(xaxis, yaxis);
	    String yaxisname=null;
	    if(geneinfo)
	    	yaxisname="Gene Names";
	    else
	    	yaxisname="Conditions Names";
		
		 JFreeChart jFreeChart = ChartFactory.createBarChart("",yaxisname,"Frequency (%)", data, PlotOrientation.HORIZONTAL, false, false, false);
		 
		 CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot();
		 Font font3 = new Font("Dialog", Font.PLAIN, 8); 
		 categoryPlot.getDomainAxis().setTickLabelFont(font3);
		 
		 categoryPlot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
	     categoryPlot.setRangePannable(true);
	     categoryPlot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
	     
	     CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
	     categoryAxis.setCategoryMargin(0.02);
	     categoryAxis.setUpperMargin(0);
	     categoryAxis.setLowerMargin(0);
	    
	   /*  NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
	        domain.setRange(0.00, 1.00);
	        domain.setTickUnit(new NumberTickUnit(0.1));
	        domain.setVerticalTickLabels(true);*/
		 
	      NumberAxis numberAxis = (NumberAxis)categoryPlot.getRangeAxis();
	      numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	      numberAxis.setUpperMargin(0.1);
	      numberAxis.setRange(0.0, 100);
	    
		// categoryPlot.getRangeAxis().setLabelFont(font3);
		 
		 BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
		 barRenderer.setItemLabelAnchorOffset(9.0);
	     barRenderer.setBaseItemLabelsVisible(true);
	     barRenderer.setDrawBarOutline(true);
	     GradientPaint gradientPaint = new GradientPaint(0.0f, 0.0f, Color.BLUE, 0.0f, 0.0f, new Color(0, 0, 64));
	     barRenderer.setSeriesPaint(0, (Paint)gradientPaint);
	     barRenderer.setBaseToolTipGenerator((CategoryToolTipGenerator)new StandardCategoryToolTipGenerator("{1} = {2}", (NumberFormat)new DecimalFormat("0")));
	     
		 
		 return jFreeChart;
		
	}
	
	

	
	
	/**
	 * Gets the category dataset.
	 *
	 * @param xaxis the xaxis
	 * @param yaxis the yaxis
	 * @return the category dataset
	 */
	protected CategoryDataset getCategoryDataset(ArrayList<Double> xaxis,ArrayList<String> yaxis) {
		
		DefaultCategoryDataset data=new DefaultCategoryDataset();
		for (int i = 0; i < xaxis.size(); i++) {
			double value=xaxis.get(i);
			String name=yaxis.get(i);
			data.addValue(value, (Comparable)((Object)""), name);
		}
		
		return data;
	}
	
	
	/**
	 * The Class BiclusterInfoListComponent.
	 */
	private class BiclusterInfoListComponent{
		
		/** The index. */
		private int index;
		
		/** The tag. */
		private String tag;
		
		/** The associatedresult. */
		private BiclusterResult associatedresult;
		
		/**
		 * Instantiates a new bicluster info list component.
		 *
		 * @param index the index
		 * @param tag the tag
		 * @param associatedresult the associatedresult
		 */
		public BiclusterInfoListComponent(int index, String tag, BiclusterResult associatedresult) {
			this.index=index;
			this.tag=tag;
			this.associatedresult=associatedresult;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return tag+" "+(index+1);
		}

		/**
		 * Gets the index.
		 *
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Gets the associatedresult.
		 *
		 * @return the associatedresult
		 */
		public BiclusterResult getAssociatedresult() {
			return associatedresult;
		}
	}
	
	
	/**
	 * The Class ComponentFrequencyListComponent.
	 */
	private class ComponentFrequencyListComponent implements Comparable<ComponentFrequencyListComponent>{
		
		/** The id. */
		String id;
		
		/** The numberoccurences. */
		int numberoccurences=0;
		
		/** The totalressize. */
		int totalressize=0;
		
		/** The associatedbiclusters. */
		ArrayList<Integer> associatedbiclusters;
		
		/**
		 * Instantiates a new component frequency list component.
		 *
		 * @param id the id
		 * @param numberoccurences the numberoccurences
		 * @param totalbicresults the totalbicresults
		 * @param associatedbiclusters the associatedbiclusters
		 */
		public ComponentFrequencyListComponent(String id, int numberoccurences, int totalbicresults,ArrayList<Integer> associatedbiclusters ) {
			this.id=id;
			this.numberoccurences=numberoccurences;
			this.totalressize=totalbicresults;
			this.associatedbiclusters=associatedbiclusters;
			
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return id+"        Freq. = "+String.format("%.1f", getFrequency()*100)+"%        Biclusters: "+getAssociatedBiclustersString(true);
		}
		
		
		/**
		 * Gets the frequency.
		 *
		 * @return the frequency
		 */
		public double getFrequency() {
			return (double)numberoccurences/totalressize;
		}
		
		
		
		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public String getId() {
			return id;
		}


		/**
		 * Gets the associated biclusters string.
		 *
		 * @param addnewline the addnewline
		 * @return the associated biclusters string
		 */
		public String getAssociatedBiclustersString(boolean addnewline) {
			StringBuilder str=new StringBuilder();
			for (int i = 0; i < associatedbiclusters.size(); i++) {
				str.append(associatedbiclusters.get(i)+1);
				if(i<(associatedbiclusters.size()-1))
					str.append(";");
			}
			if(addnewline)
				str.append("\n");
			return str.toString();
		}


		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(ComponentFrequencyListComponent o) {
			if(this.getFrequency()==o.getFrequency())
				return 0;
			else
				return this.getFrequency()>o.getFrequency() ? 1 : -1;
		}
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setSize(400,200);
		BiclustersInfoSearchPanel p=new BiclustersInfoSearchPanel();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		

	}

}
