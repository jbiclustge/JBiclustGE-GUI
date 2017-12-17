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
package jbiclustgegui.gui.components.panels.data;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.gui.components.tables.data.ExpressionDataMainTable;
import jbiclustgegui.gui.components.tables.data.mergedtables.DataMatrixMainTable;
import jbiclustgegui.gui.components.tables.data.mergedtables.GeneNamesFixedTable;
import pt.ornrocha.arrays.MTUArrayUtils;
import pt.ornrocha.arrays.MTUMatrixUtils;
import pt.ornrocha.excelutils.ExcelVersion;
import pt.ornrocha.excelutils.MTUExcelWriterUtils;
import pt.ornrocha.ioutils.writers.MTUWriterUtils;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.printutils.MTUPrintUtils;
import pt.ornrocha.swingutils.jfilechooser.filefilters.ExtensionFileFilter;
import pt.ornrocha.swingutils.tables.FixedColumnTableLinker;
import smile.imputation.MissingValueImputationException;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class DataMatrixPanel.
 */
public class DataMatrixPanel extends JPanel implements PropertyChangeListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The maintable. */
	private DataMatrixMainTable maintable;
	
	/** The fixedtable. */
	private GeneNamesFixedTable fixedtable;
	
	/** The panel. */
	private JPanel panel;
	
	/** The progress bar. */
	private JProgressBar progressBar;
	
	/** The panel 1. */
	private JPanel panel_1;
	
	/** The titleprogressbar. */
	private TitledBorder titleprogressbar;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The panel 3. */
	private JPanel panel_3;
	
	/** The gradient panel. */
	private LinearGradientPanel gradientPanel;
	
	/** The lbl low expression. */
	private JLabel lblLowExpression;
	
	/** The lbl high expression. */
	private JLabel lblHighExpression;
	
	/** The genes. */
	private ArrayList<String>genes;
	
	/** The conditions. */
	private ArrayList<String> conditions;
	
	/** The datamatrix. */
	private double[][] datamatrix;
	
	/** The panel 4. */
	private JPanel panel_4;
	
	/** The btn new button. */
	private JButton btnNewButton;
	
	
	/**
	 * Instantiates a new data matrix panel.
	 */
	public DataMatrixPanel() {
		initGUI();
	}
	
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.1,2.0,0.1};
		setLayout(gridBagLayout);
		
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(this.scrollPane, gbc_scrollPane);
		
		
		this.panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		this.panel.setLayout(gbl_panel);
		
		this.panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		this.panel.add(this.panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1,1,1};
		gbl_panel_4.rowHeights = new int[]{1};
		gbl_panel_4.columnWeights = new double[]{0.3,0.0,0.3};
		gbl_panel_4.rowWeights = new double[]{1.0};
		this.panel_4.setLayout(gbl_panel_4);
		
		this.btnNewButton = new JButton("Save Matrix");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(datamatrix!=null && genes!=null && conditions!=null)
					saveMatrix();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		this.panel_4.add(this.btnNewButton, gbc_btnNewButton);
		
		
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 0;
		this.panel.add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		this.panel_1.setLayout(gbl_panel_1);
		

	}
	
	
	
	/**
	 * Sets the data.
	 *
	 * @param genes the genes
	 * @param conditions the conditions
	 * @param datamatrix the datamatrix
	 * @param findweights the findweights
	 */
	public void setData(ArrayList<String>genes, ArrayList<String> conditions, double[][] datamatrix, boolean findweights) {
		
		this.genes=genes;
		this.conditions=conditions;
		this.datamatrix=datamatrix;
		
		if(findweights) {
			maintable=new DataMatrixMainTable(genes, conditions, getWeightedMatrix());
			addColorBarToPanel();
		}
		else {
			maintable=new DataMatrixMainTable(genes, conditions);
		}
		
		
		maintable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		maintable.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		fixedtable=new GeneNamesFixedTable(genes);

		dataloaderworker worker=new dataloaderworker(maintable,datamatrix);
		worker.addPropertyChangeListener(this);
		worker.execute();
		
		maintable.setEnabled(false);
		
		scrollPane.setViewportView(maintable);
		FixedColumnTableLinker.link(maintable, fixedtable, scrollPane);	
	}
	
	
	
	
	/**
	 * Gets the weighted matrix.
	 *
	 * @return the weighted matrix
	 */
	protected double[][] getWeightedMatrix(){
		
		double [][] weightmatrix=new double[genes.size()][conditions.size()];
		Pair<Double, Double> minmaxdata=MTUMatrixUtils.getMinMaxValuesOfMatrix(datamatrix);
		
		for (int i = 0; i < genes.size(); i++) {
			
			double[] row=datamatrix[i];
			for (int j = 0; j < row.length; j++) {
				double weightval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				weightmatrix[i][j]=weightval;
			}
		}

		return weightmatrix;
	}
	
	/**
	 * Show progress bar.
	 */
	public void showProgressBar() {
		titleprogressbar=new TitledBorder(null, "waiting...", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		this.panel_1.setBorder(titleprogressbar);
		this.progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 0;
		this.panel_1.add(this.progressBar, gbc_progressBar);
		this.progressBar.setStringPainted(true);
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
	}
	
	/**
	 * Adds the color bar to panel.
	 */
	public void addColorBarToPanel() {
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		this.panel.add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1,1,1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0,1.0};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		this.panel_2.add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1,1,1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{0.2,1.0,0.2};
		gbl_panel_3.rowWeights = new double[]{1.0};
		this.panel_3.setLayout(gbl_panel_3);
		
		this.lblLowExpression = new JLabel("Low Expression");
		GridBagConstraints gbc_lblLowExpression = new GridBagConstraints();
		gbc_lblLowExpression.anchor = GridBagConstraints.EAST;
		gbc_lblLowExpression.insets = new Insets(0, 0, 0, 5);
		gbc_lblLowExpression.gridx = 0;
		gbc_lblLowExpression.gridy = 0;
		this.panel_3.add(this.lblLowExpression, gbc_lblLowExpression);
		
		this.gradientPanel = new LinearGradientPanel();
		GridBagConstraints gbc_gradientPanel = new GridBagConstraints();
		gbc_gradientPanel.insets = new Insets(0, 0, 0, 5);
		gbc_gradientPanel.fill = GridBagConstraints.BOTH;
		gbc_gradientPanel.gridx = 1;
		gbc_gradientPanel.gridy = 0;
		this.panel_3.add(this.gradientPanel, gbc_gradientPanel);
		
		this.lblHighExpression = new JLabel("High Expression");
		GridBagConstraints gbc_lblHighExpression = new GridBagConstraints();
		gbc_lblHighExpression.anchor = GridBagConstraints.WEST;
		gbc_lblHighExpression.gridx = 2;
		gbc_lblHighExpression.gridy = 0;
		this.panel_3.add(this.lblHighExpression, gbc_lblHighExpression);
	}
	

	/**
	 * The Class dataloaderworker.
	 */
	private class dataloaderworker extends SwingWorker<Boolean, Integer>{

		/** The maintable. */
		DataMatrixMainTable maintable;
		
		/** The data. */
		double[][] data;
		
		/**
		 * Instantiates a new dataloaderworker.
		 *
		 * @param maintable the maintable
		 * @param data the data
		 */
		public dataloaderworker(DataMatrixMainTable maintable, double[][] data) {
			this.maintable=maintable;
            this.data=data;
		}
		
		
		/**
		 * Sets the title tag value.
		 *
		 * @param newValue the new title tag value
		 */
		protected void setTitleTagValue(String newValue) {
			getPropertyChangeSupport().firePropertyChange("titlebordertag",null, newValue);
		}

		
		/* (non-Javadoc)
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		@Override
		protected Boolean doInBackground() throws Exception {
			
			
			int max=data.length*data[0].length;
			int cur=0;
			int currperc=0;

			setTitleTagValue("Loading data to table...");
			for (int i = 0; i < data.length; i++) {
				
				for (int j = 0; j < data[0].length; j++) {
					maintable.appendData(data[i][j], i, j);
					cur++;
					currperc=(int)(cur*100/max);
					setProgress(currperc);
				}
			}
			setTitleTagValue("Dataset was loaded");
			return true;
		}
		
	}

	
	/**
	 * Gets the fixed names table.
	 *
	 * @return the fixed names table
	 */
	public GeneNamesFixedTable getFixedNamesTable() {
		return fixedtable;
	}
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MissingValueImputationException the missing value imputation exception
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, MissingValueImputationException, ParseException {
		JFrame f=new JFrame();
		f.setSize(1000,600);
		DataMatrixPanel p=new DataMatrixPanel();
		p.showProgressBar();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		ExpressionData expdata=ExpressionData.importFromTXTFormat("/home/orocha/MEOCloud/TRABALHO/PROM/PROM_develop/model1/datasetexpressioniNJ661.csv").load();
		p.setData(expdata.getGeneNamesList(), expdata.getConditionsList(), expdata.getexpressionDataMatrix(), false);

	}



	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
	
		if(e.getPropertyName().equals("progress")) {
			if(progressBar!=null) {
				progressBar.setValue((int) e.getNewValue());
			}
		}
		else if(e.getPropertyName().equals("titlebordertag")) {
			if(titleprogressbar!=null) {
				titleprogressbar.setTitle((String) e.getNewValue());
				updateUI();
			}
		}
		
	}
	
	/**
	 * Save matrix.
	 */
	public void saveMatrix() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Comma-separated values","csv"));
			fileChooser.addChoosableFileFilter(new ExtensionFileFilter("Excel", "xlsx"));
			int tag=fileChooser.showSaveDialog(this);
			
			if(tag==JFileChooser.APPROVE_OPTION) {
				ExtensionFileFilter filter=(ExtensionFileFilter) fileChooser.getFileFilter();
				try {
					if(filter.getExtension().equals("csv"))
						saveToCSV(fileChooser.getSelectedFile().getAbsolutePath());
					else
						saveToExcel(fileChooser.getSelectedFile().getAbsolutePath());
				} catch (IOException e) {
						LogMessageCenter.getLogger().addCriticalErrorMessage("Error in saving file", e);
						Workbench.getInstance().error("Error in saving file");
					}
			}
	}
	
	/**
	 * Save to CSV.
	 *
	 * @param filename the filename
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void saveToCSV(String filename) throws IOException {
	
		StringBuilder str=new StringBuilder();
		
		str.append("identifiers\t");
		for (int i = 0; i < conditions.size(); i++) {
			str.append(conditions.get(i));
			if(i<(conditions.size()-1))
				str.append("\t");
		}
		str.append("\n");
		
		for (int i = 0; i < datamatrix.length; i++) {
			
			double[] row=datamatrix[i];
			str.append(genes.get(i)+"\t");
			for (int j = 0; j < row.length; j++) {
				str.append(datamatrix[i][j]);
				if(j<(conditions.size()-1))
					str.append("\t");
			}
			str.append("\n");
			
		}
		
		String filepath=filename+".csv";
		MTUWriterUtils.writeDataTofile(filepath, str.toString());
		
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
		header.add("Identifiers");
		for (int i = 0; i < conditions.size(); i++) {
			header.add(conditions.get(i));
		}
		intputdata.add(header);
		
		for (int i = 0; i < datamatrix.length; i++) {
			ArrayList<Object> row=new ArrayList<>();
			row.add(genes.get(i));
			double[] rowm=datamatrix[i];
			for (int j = 0; j < rowm.length; j++) {
				row.add(datamatrix[i][j]);
			}
			intputdata.add(row);
		}
		
		//String filepathh=ExcelVersion.XLSX.getFileNameWithExtension(filepath);
		MTUExcelWriterUtils.WriteDataToNewExcelFile(filepath, ExcelVersion.XLSX, intputdata, "Bicluster matrix");
	}
	

}
