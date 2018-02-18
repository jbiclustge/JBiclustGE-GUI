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
package jbiclustgegui.gui.components.panels.methods;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import jbiclustge.methods.algorithms.wrappers.UnibicMethod;
import jbiclustge.utils.properties.AlgorithmProperties;
import jbiclustgegui.datatypes.project.Project;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class UnibicSettingsPanel.
 */
public class UnibicSettingsPanel extends AbstractMethodSettingsPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The label. */
	private JLabel label;
	
	/** The spinnerquantdiscret. */
	private JSpinner spinnerquantdiscret;
	
	/** The label 1. */
	private JLabel label_1;
	
	/** The integer text fieldnumberranks. */
	private IntegerTextField integerTextFieldnumberranks;
	
	/** The label 2. */
	private JLabel label_2;
	
	/** The integer text fieldnumberblocks. */
	private IntegerTextField integerTextFieldnumberblocks;
	
	/** The label 3. */
	private JLabel label_3;
	
	/** The spinnerfilterblocks. */
	private JSpinner spinnerfilterblocks;
	
	/** The label 4. */
	private JLabel label_4;
	
	/** The integer text fieldcolumnwidth. */
	private IntegerTextField integerTextFieldcolumnwidth;
	
	/** The check boxusetftosearch. */
	private JCheckBox checkBoxusetftosearch;
	
	/** The label 5. */
	private JLabel label_5;
	
	/** The check boxenlargebicluster. */
	private JCheckBox checkBoxenlargebicluster;
	
	/** The label 6. */
	private JLabel label_6;
	
	/** The label 7. */
	private JLabel label_7;
	
	/** The spinnerconstlevel. */
	private JSpinner spinnerconstlevel;
	
	/** The label 8. */
	private JLabel label_8;
	
	/** The check boxuseareatostop. */
	private JCheckBox checkBoxuseareatostop;
	
	/** The check boxuselowerbound. */
	private JCheckBox checkBoxuselowerbound;
	
	/** The combo boxtfsearch. */
	protected JComboBox<String> comboBoxtfsearch;

	/** The usetfsearch. */
	private static String USETFSEARCH="usetfsearch";
	
	/**
	 * Create the panel.
	 */
	public UnibicSettingsPanel() {
       super();
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initGUI()
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{0.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		label = new JLabel("Quantile discretization:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		spinnerquantdiscret = new JSpinner();
		spinnerquantdiscret.setToolTipText("Use quantile discretization for continuous data, range: (0-0.5]");
		spinnerquantdiscret.setModel(new SpinnerNumberModel(0.5, 0.01, 0.5, 0.01));
		GridBagConstraints gbc_spinnerquantdiscret = new GridBagConstraints();
		gbc_spinnerquantdiscret.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerquantdiscret.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerquantdiscret.gridx = 1;
		gbc_spinnerquantdiscret.gridy = 1;
		add(spinnerquantdiscret, gbc_spinnerquantdiscret);
		
		label_1 = new JLabel("Number ranks:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		integerTextFieldnumberranks = new IntegerTextField();
		integerTextFieldnumberranks.setText("0");
		integerTextFieldnumberranks.setToolTipText("The number of ranks as which we treat the up(down)-regulated value when discretization");
		integerTextFieldnumberranks.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberranks = new GridBagConstraints();
		gbc_integerTextFieldnumberranks.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberranks.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberranks.gridx = 1;
		gbc_integerTextFieldnumberranks.gridy = 2;
		add(integerTextFieldnumberranks, gbc_integerTextFieldnumberranks);
		
		label_2 = new JLabel("Number blocks");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		add(label_2, gbc_label_2);
		
		integerTextFieldnumberblocks = new IntegerTextField();
		integerTextFieldnumberblocks.setText("0");
		integerTextFieldnumberblocks.setToolTipText("Number of blocks to report");
		integerTextFieldnumberblocks.setColumns(10);
		GridBagConstraints gbc_integerTextFieldnumberblocks = new GridBagConstraints();
		gbc_integerTextFieldnumberblocks.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldnumberblocks.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldnumberblocks.gridx = 1;
		gbc_integerTextFieldnumberblocks.gridy = 3;
		add(integerTextFieldnumberblocks, gbc_integerTextFieldnumberblocks);
		
		label_3 = new JLabel("Filter overlap blocks:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		add(label_3, gbc_label_3);
		
		spinnerfilterblocks = new JSpinner();
		spinnerfilterblocks.setToolTipText("Filter overlapping blocks (1 do not remove any blocks), range: [0-1.0]");
		spinnerfilterblocks.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		GridBagConstraints gbc_spinnerfilterblocks = new GridBagConstraints();
		gbc_spinnerfilterblocks.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerfilterblocks.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerfilterblocks.gridx = 1;
		gbc_spinnerfilterblocks.gridy = 4;
		add(spinnerfilterblocks, gbc_spinnerfilterblocks);
		
		label_4 = new JLabel("Min. column width:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 5;
		add(label_4, gbc_label_4);
		
		integerTextFieldcolumnwidth = new IntegerTextField();
		integerTextFieldcolumnwidth.setToolTipText("Minimum column width of the block, range: > 2");
		integerTextFieldcolumnwidth.setText("4");
		integerTextFieldcolumnwidth.setColumns(10);
		GridBagConstraints gbc_integerTextFieldcolumnwidth = new GridBagConstraints();
		gbc_integerTextFieldcolumnwidth.insets = new Insets(0, 0, 5, 0);
		gbc_integerTextFieldcolumnwidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldcolumnwidth.gridx = 1;
		gbc_integerTextFieldcolumnwidth.gridy = 5;
		add(integerTextFieldcolumnwidth, gbc_integerTextFieldcolumnwidth);
		
		label_7 = new JLabel("Consistency level:");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 6;
		add(label_7, gbc_label_7);
		
		spinnerconstlevel = new JSpinner();
		spinnerconstlevel.setToolTipText("<html>Consistency level of the block, range: (0.5-1.0],<br> The minimum ratio between the number of identical valid symbols in a column and the total number of rows in the output</html>");
		spinnerconstlevel.setModel(new SpinnerNumberModel(0.85, 0.49, 1.0, 0.01));
		GridBagConstraints gbc_spinnerconstlevel = new GridBagConstraints();
		gbc_spinnerconstlevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerconstlevel.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerconstlevel.gridx = 1;
		gbc_spinnerconstlevel.gridy = 6;
		add(spinnerconstlevel, gbc_spinnerconstlevel);
		
		checkBoxusetftosearch = new JCheckBox("Transcriptional Factor to search:");
		checkBoxusetftosearch.setToolTipText("TF name to be searched, just consider the seeds containing the TF defined");
		checkBoxusetftosearch.setSelected(false);
		checkBoxusetftosearch.setActionCommand("choosetf");
		GridBagConstraints gbc_checkBoxusetftosearch = new GridBagConstraints();
		gbc_checkBoxusetftosearch.anchor = GridBagConstraints.EAST;
		gbc_checkBoxusetftosearch.insets = new Insets(0, 10, 5, 5);
		gbc_checkBoxusetftosearch.gridx = 0;
		gbc_checkBoxusetftosearch.gridy = 7;
		add(checkBoxusetftosearch, gbc_checkBoxusetftosearch);
		checkBoxusetftosearch.setActionCommand(USETFSEARCH);
		checkBoxusetftosearch.addActionListener(this);
		
		this.comboBoxtfsearch = new JComboBox();
		comboBoxtfsearch.setToolTipText("TF name to be searched");
		GridBagConstraints gbc_comboBoxtfsearch = new GridBagConstraints();
		gbc_comboBoxtfsearch.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxtfsearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxtfsearch.gridx = 1;
		gbc_comboBoxtfsearch.gridy = 7;
		add(this.comboBoxtfsearch, gbc_comboBoxtfsearch);
		
		label_5 = new JLabel("Enlarge bicluster:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 8;
		add(label_5, gbc_label_5);
		
		checkBoxenlargebicluster = new JCheckBox("");
		checkBoxenlargebicluster.setToolTipText("Enlarge current bicluster by the p-value constraint (false or true)");
		checkBoxenlargebicluster.setSelected(false);
		GridBagConstraints gbc_checkBoxenlargebicluster = new GridBagConstraints();
		gbc_checkBoxenlargebicluster.anchor = GridBagConstraints.WEST;
		gbc_checkBoxenlargebicluster.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxenlargebicluster.gridx = 1;
		gbc_checkBoxenlargebicluster.gridy = 8;
		add(checkBoxenlargebicluster, gbc_checkBoxenlargebicluster);
		
		label_6 = new JLabel("Use area to stop:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 9;
		add(label_6, gbc_label_6);
		
		checkBoxuseareatostop = new JCheckBox("");
		checkBoxuseareatostop.setToolTipText("Use area as the value of bicluster to determine when stop (false or true)");
		checkBoxuseareatostop.setSelected(false);
		GridBagConstraints gbc_checkBoxuseareatostop = new GridBagConstraints();
		gbc_checkBoxuseareatostop.anchor = GridBagConstraints.WEST;
		gbc_checkBoxuseareatostop.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxuseareatostop.gridx = 1;
		gbc_checkBoxuseareatostop.gridy = 9;
		add(checkBoxuseareatostop, gbc_checkBoxuseareatostop);
		
		label_8 = new JLabel("Use lower bound of conditions:");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.EAST;
		gbc_label_8.insets = new Insets(0, 0, 0, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 10;
		add(label_8, gbc_label_8);
		
		checkBoxuselowerbound = new JCheckBox("");
		checkBoxuselowerbound.setToolTipText("Use the lower bound of conditions number (5 percent of the gene number, false or true)");
		checkBoxuselowerbound.setSelected(false);
		GridBagConstraints gbc_checkBoxuselowerbound = new GridBagConstraints();
		gbc_checkBoxuselowerbound.anchor = GridBagConstraints.WEST;
		gbc_checkBoxuselowerbound.gridx = 1;
		gbc_checkBoxuselowerbound.gridy = 10;
		add(checkBoxuselowerbound, gbc_checkBoxuselowerbound);
	}

	


	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#resetToDefaultValues()
	 */
	@Override
	public void resetToDefaultValues() {
		
		integerTextFieldnumberranks.setText("15");
		integerTextFieldnumberblocks.setText("100");
		integerTextFieldcolumnwidth.setText("4");
		spinnerquantdiscret.setValue(0.5);
		spinnerfilterblocks.setValue(1.0);
		spinnerconstlevel.setValue(0.85);
		checkBoxenlargebicluster.setSelected(false);
		checkBoxuseareatostop.setSelected(false);
		checkBoxuselowerbound.setSelected(false);
		checkBoxusetftosearch.setSelected(false);
		comboBoxtfsearch.setEnabled(false);
		comboBoxtfsearch.removeAllItems();
		
	}
	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#needInitComponents()
	 */
	@Override
	protected boolean needInitComponents() {
		return false;
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#initComponents()
	 */
	@Override
	protected void initComponents() {
		// TODO Auto-generated method stub
		
	}
	

	
	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#setCurrentProject(jbiclustgegui.datatypes.project.Project)
	 */
	@Override
	public void setCurrentProject(Project proj) {
		comboBoxtfsearch.removeAllItems();
		ArrayList<String> genes=proj.getExpressionDataset().getExpressionset().getGeneNamesList();
		
		for (int i = 0; i < genes.size(); i++) {
			comboBoxtfsearch.addItem(genes.get(i));
		}
		comboBoxtfsearch.setSelectedIndex(0);
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getMethodSettings()
	 */
	@Override
	public Properties getMethodSettings() throws IOException {
		
		

		String[] propkeys=new String[]{
				UnibicMethod.QUANTILEDISCRETIZATION,
				UnibicMethod.RANKLEVELS,
				UnibicMethod.DISCRETEDATA,
				UnibicMethod.TFNAMETOSEARCH,
				UnibicMethod.ENLARGEBICLUSTERFLAG,
				UnibicMethod.STOPBICLUSTERBYAREAFLAG,
				UnibicMethod.LOWERBOUNDCONDITIONFLAG,
				UnibicMethod.BLOCKSTOREPORT,
				UnibicMethod.FILTEROVERLAPBLOCKS,
				UnibicMethod.MINCOLUMNWITH,
				UnibicMethod.CONSISTENCYLEVELBLOCK,
				UnibicMethod.EXPANSIONFLAG
		};
		
		String tfname="";
		if(checkBoxusetftosearch.isSelected() && comboBoxtfsearch.getModel().getSize()>0)
			tfname=(String) comboBoxtfsearch.getSelectedItem();
		
		String[] values=new String[]{String.valueOf(spinnerquantdiscret.getValue()),
				integerTextFieldnumberranks.getText(),
				"false",
				tfname,
				String.valueOf(checkBoxenlargebicluster.isSelected()),
				String.valueOf(checkBoxuseareatostop.isSelected()),
				String.valueOf(checkBoxuselowerbound.isSelected()),
				integerTextFieldnumberblocks.getText(),
				String.valueOf(spinnerfilterblocks.getValue()),
				integerTextFieldcolumnwidth.getText(),
				String.valueOf(spinnerconstlevel.getValue()),
				"false"};
		
		String[] comments=new String[] {
				"Use quantile discretization for continuous data",
				"The number of ranks as which we treat the up(down)-regulated value when discretization",
				"Discrete data (where user should send their processed data to different value classes)",
				"To-be-searched TF name, just consider the seeds containing current TF default format: B1234",
				"Enlarge current bicluster by the p-value constraint (false or true)",
				"Use area as the value of bicluster to determine when stop (false or true)",
				"Use the lower bound of conditions number (5 percent of the gene number, false or true)",
				"Number of blocks to report",
				"Filter overlapping blocks (1 do not remove any blocks)",
				"Minimum column width of the block",
				"Consistency level of the block (0.5-1.0], the minimum ratio between the number of identical valid symbols in a column and the total number of rows in the output",
				"Expansion"
		};
		
		String[] propsubkeys=new String[]{"-q","-r","-d","-T","-P","-S","-C","-o","-f","-k","-c","-s"};
		
		return AlgorithmProperties.setupProperties(propkeys, values, comments, propsubkeys);
	}

	

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#validSettings()
	 */
	@Override
	public Pair<Boolean, String> validSettings() {
		if(integerTextFieldnumberranks.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberranks.getText())<1)
			return new Pair<Boolean, String>(false, "The value for the number of ranks must be higher than 0");
		else if(integerTextFieldnumberblocks.getText().isEmpty() || Integer.parseInt(integerTextFieldnumberblocks.getText())<1)
			return new Pair<Boolean, String>(false, "Value for the number of blocks must be higher than 0");
		else if(integerTextFieldcolumnwidth.getText().isEmpty() || Integer.parseInt(integerTextFieldcolumnwidth.getText())<2)
			return new Pair<Boolean, String>(false, "Value for the minimum column width must be higher than 1");
		else
			  return new Pair<Boolean, String>(true, null);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(USETFSEARCH)) {
			if(checkBoxusetftosearch.isSelected())
				comboBoxtfsearch.setEnabled(true);
			else
				comboBoxtfsearch.setEnabled(false);
		}
		
	}

	/* (non-Javadoc)
	 * @see jbiclustgegui.gui.components.panels.methods.AbstractMethodSettingsPanel#getPreferredDimensions()
	 */
	@Override
	public Dimension getPreferredDimensions() {
		return new Dimension(450,550);
	}

	

}
