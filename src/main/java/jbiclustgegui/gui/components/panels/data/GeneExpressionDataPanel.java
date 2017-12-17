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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustgegui.gui.components.tables.data.ExpressionDataMainTable;
import jbiclustgegui.gui.components.tables.data.mergedtables.GeneNamesFixedTable;
import pt.ornrocha.printutils.MTUPrintUtils;
import pt.ornrocha.swingutils.tables.FixedColumnTableLinker;
import smile.imputation.MissingValueImputationException;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneExpressionDataPanel.
 */
public class GeneExpressionDataPanel extends JPanel implements PropertyChangeListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The maintable. */
	private ExpressionDataMainTable maintable;
	
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
	
	
	/**
	 * Instantiates a new gene expression data panel.
	 */
	public GeneExpressionDataPanel() {
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
		//"Please wait, loading data..."
		titleprogressbar=new TitledBorder(null, "waiting...", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		
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
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(titleprogressbar);
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
	 * Load expression dataset to table.
	 *
	 * @param dataset the dataset
	 */
	public void loadExpressionDatasetToTable(ExpressionData dataset) {
		
		
		double [][] normmatrix=new double[dataset.numberGenes()][dataset.numberConditions()];
		Pair<Double, Double> minmaxdata=dataset.getMinMaxValuesInDataset();
		
		for (int i = 0; i < dataset.numberConditions(); i++) {
			
			double[] row=dataset.getGeneRow(i);
			for (int j = 0; j < row.length; j++) {
				double normval=(row[j]-minmaxdata.getValue0())/(minmaxdata.getValue1()-minmaxdata.getValue0());
				normmatrix[j][i]=normval;
			}
		}
		
	   //MTUPrintUtils.printDoubleMatrix(normmatrix);
		maintable=new ExpressionDataMainTable(dataset.numberGenes(), dataset.getConditionsList(), normmatrix);
		maintable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		maintable.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		fixedtable=new GeneNamesFixedTable(dataset.getGeneNamesList());
		
		//titleprogressbar.setTitle("Loading data...");
		
		dataloaderworker worker=new dataloaderworker(maintable,dataset);
		worker.addPropertyChangeListener(this);
		worker.execute();
		
		maintable.setEnabled(false);
		
		scrollPane.setViewportView(maintable);
		FixedColumnTableLinker.link(maintable, fixedtable, scrollPane);	
	}
	

	/**
	 * The Class dataloaderworker.
	 */
	private class dataloaderworker extends SwingWorker<Boolean, Integer>{

		/** The maintable. */
		ExpressionDataMainTable maintable;
		
		/** The data. */
		//TitledBorder titleprogressbar;
		ExpressionData data;
		
		/**
		 * Instantiates a new dataloaderworker.
		 *
		 * @param maintable the maintable
		 * @param data the data
		 */
		public dataloaderworker(ExpressionDataMainTable maintable, ExpressionData data) {
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
			
			double[][] expressionmatrix=data.getexpressionDataMatrix();
			
			int max=expressionmatrix.length*expressionmatrix[0].length;
			int cur=0;
			int currperc=0;
			//titleprogressbar.setTitle("Loading data...");
			setTitleTagValue("Loading data to table...");
			for (int i = 0; i < expressionmatrix.length; i++) {
				
				for (int j = 0; j < expressionmatrix[0].length; j++) {
					maintable.appendData(expressionmatrix[i][j], i, j);
					cur++;
					currperc=(int)(cur*100/max);
					setProgress(currperc);
				}
			}
			setTitleTagValue("Dataset was loaded");
			//titleprogressbar.setTitle("Dataset loaded");
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
		GeneExpressionDataPanel p=new GeneExpressionDataPanel();
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		ExpressionData expdata=ExpressionData.importFromTXTFormat("/home/orocha/MEOCloud/TRABALHO/PROM/PROM_develop/model1/datasetexpressioniNJ661.csv").load();
		p.loadExpressionDatasetToTable(expdata);

	}



	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
	
		if(e.getPropertyName().equals("progress")) {
			progressBar.setValue((int) e.getNewValue());
		}
		else if(e.getPropertyName().equals("titlebordertag")) {
			titleprogressbar.setTitle((String) e.getNewValue());
			updateUI();
		}
		
	}
	
	
	

}
