package jbiclustgegui.gui.components.panels.enrichmentanalysis.clusterprofiler;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import jbiclustge.datatools.databases.kegg.collectors.organism.KeggOrganismIdentifiers;
import jbiclustge.enrichmentanalysistools.clusterprofile.components.ClusterProfileKeyType;
import jbiclustge.enrichmentanalysistools.common.pvaluesAdjustMethod;
import pt.ornrocha.collections.MTUMapUtils;
import pt.ornrocha.swingutils.textfield.IntegerTextField;

public class ClusterProfilerKeggPanel extends ClusterProfilerOptionsPanel implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	private JLabel lblKeyType;
	private JLabel lblMultipleTestingCorrection;
	private JLabel lblMinimalSizeOf;
	private JLabel label;
	private JComboBox comboBoxkeytype;
	private JComboBox comboBoxMCT;
	private JPanel panel;
	private JLabel lblKeggIdentifier;
	private JTextField textFieldKeggident;
	private JPanel panel_1;
	private JComboBox comboBoxCategory;
	private JPanel panel_2;
	private JComboBox comboBoxOrganismname;
	private JPanel panel_3;
	private JComboBox comboBoxtype;
	private LinkedHashMap<String, LinkedHashMap<String, String>> prokaryotekeggnames;
	private LinkedHashMap<String, LinkedHashMap<String, String>> eukaryotekeggnames;
	
	private LinkedHashMap<String, LinkedHashMap<String, String>> currentkeggnames;
	
	private boolean changingfields=false;
	private boolean init=false;
	
	
	private static String PROKARYOTES="Prokaryotes";
	private static String EUKARYOTES="Eukaryotes";
	private static String ALL="All";
	
	private static String CHANGETYPE="changeorgtype";
	private static String CHANGECATEGORY="changecategory";
	private static String CHANGEORGNAMES="changeorganismname";
	private IntegerTextField integerTextFieldmingenesize;
	private IntegerTextField integerTextFieldmaxgenesize;
	
	public ClusterProfilerKeggPanel() {
		initGUI();
		setup();
	}
	
	
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1,1,1,1,1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Organism Kegg identifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1,1,1,1,1};
		gbl_panel.rowHeights = new int[]{1,1,1,1};
		gbl_panel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0,1.0,1.0};
		panel.setLayout(gbl_panel);
		
		lblKeggIdentifier = new JLabel("Kegg Identifier:");
		GridBagConstraints gbc_lblKeggIdentifier = new GridBagConstraints();
		gbc_lblKeggIdentifier.anchor = GridBagConstraints.EAST;
		gbc_lblKeggIdentifier.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeggIdentifier.gridx = 0;
		gbc_lblKeggIdentifier.gridy = 1;
		panel.add(lblKeggIdentifier, gbc_lblKeggIdentifier);
		
		textFieldKeggident = new JTextField();
		GridBagConstraints gbc_textFieldKeggident = new GridBagConstraints();
		gbc_textFieldKeggident.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldKeggident.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldKeggident.gridx = 1;
		gbc_textFieldKeggident.gridy = 1;
		panel.add(textFieldKeggident, gbc_textFieldKeggident);
		textFieldKeggident.setColumns(10);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		panel_3.setLayout(gbl_panel_3);
		
		comboBoxtype = new JComboBox();
		comboBoxtype.addActionListener(this);
		comboBoxtype.setActionCommand(CHANGETYPE);
		GridBagConstraints gbc_comboBoxtype = new GridBagConstraints();
		gbc_comboBoxtype.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxtype.gridx = 0;
		gbc_comboBoxtype.gridy = 0;
		panel_3.add(comboBoxtype, gbc_comboBoxtype);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Category", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0};
		panel_1.setLayout(gbl_panel_1);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.addActionListener(this);
		comboBoxCategory.setActionCommand(CHANGECATEGORY);
		AutoCompleteDecorator.decorate(comboBoxCategory);
		GridBagConstraints gbc_comboBoxCategory = new GridBagConstraints();
		gbc_comboBoxCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCategory.gridx = 0;
		gbc_comboBoxCategory.gridy = 0;
		panel_1.add(comboBoxCategory, gbc_comboBoxCategory);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Organism name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0};
		panel_2.setLayout(gbl_panel_2);
		
		comboBoxOrganismname = new JComboBox();
		comboBoxOrganismname.addActionListener(this);
		comboBoxOrganismname.setActionCommand(CHANGEORGNAMES);
		AutoCompleteDecorator.decorate(comboBoxOrganismname);
		GridBagConstraints gbc_comboBoxOrganismname = new GridBagConstraints();
		gbc_comboBoxOrganismname.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOrganismname.gridx = 0;
		gbc_comboBoxOrganismname.gridy = 0;
		panel_2.add(comboBoxOrganismname, gbc_comboBoxOrganismname);
		
		lblKeyType = new JLabel("Key type:");
		GridBagConstraints gbc_lblKeyType = new GridBagConstraints();
		gbc_lblKeyType.anchor = GridBagConstraints.EAST;
		gbc_lblKeyType.gridwidth = 2;
		gbc_lblKeyType.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeyType.gridx = 0;
		gbc_lblKeyType.gridy = 3;
		add(lblKeyType, gbc_lblKeyType);
		
		comboBoxkeytype = new JComboBox();
		GridBagConstraints gbc_comboBoxkeytype = new GridBagConstraints();
		gbc_comboBoxkeytype.gridwidth = 4;
		gbc_comboBoxkeytype.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxkeytype.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxkeytype.gridx = 2;
		gbc_comboBoxkeytype.gridy = 3;
		add(comboBoxkeytype, gbc_comboBoxkeytype);
		
		lblMultipleTestingCorrection = new JLabel("Multiple testing correction method:");
		GridBagConstraints gbc_lblMultipleTestingCorrection = new GridBagConstraints();
		gbc_lblMultipleTestingCorrection.anchor = GridBagConstraints.EAST;
		gbc_lblMultipleTestingCorrection.gridwidth = 2;
		gbc_lblMultipleTestingCorrection.insets = new Insets(0, 0, 5, 5);
		gbc_lblMultipleTestingCorrection.gridx = 0;
		gbc_lblMultipleTestingCorrection.gridy = 4;
		add(lblMultipleTestingCorrection, gbc_lblMultipleTestingCorrection);
		
		comboBoxMCT = new JComboBox();
		GridBagConstraints gbc_comboBoxMCT = new GridBagConstraints();
		gbc_comboBoxMCT.gridwidth = 4;
		gbc_comboBoxMCT.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMCT.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMCT.gridx = 2;
		gbc_comboBoxMCT.gridy = 4;
		add(comboBoxMCT, gbc_comboBoxMCT);
		
		lblMinimalSizeOf = new JLabel("Minimal size of genes annotated by Ontology:");
		GridBagConstraints gbc_lblMinimalSizeOf = new GridBagConstraints();
		gbc_lblMinimalSizeOf.anchor = GridBagConstraints.EAST;
		gbc_lblMinimalSizeOf.gridwidth = 2;
		gbc_lblMinimalSizeOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinimalSizeOf.gridx = 0;
		gbc_lblMinimalSizeOf.gridy = 5;
		add(lblMinimalSizeOf, gbc_lblMinimalSizeOf);
		
		integerTextFieldmingenesize = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldmingenesize = new GridBagConstraints();
		gbc_integerTextFieldmingenesize.gridwidth = 2;
		gbc_integerTextFieldmingenesize.insets = new Insets(0, 0, 5, 5);
		gbc_integerTextFieldmingenesize.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmingenesize.gridx = 2;
		gbc_integerTextFieldmingenesize.gridy = 5;
		add(integerTextFieldmingenesize, gbc_integerTextFieldmingenesize);
		
		label = new JLabel("Maximal size of genes annotated by Ontology:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 6;
		add(label, gbc_label);
		
		integerTextFieldmaxgenesize = new IntegerTextField();
		GridBagConstraints gbc_integerTextFieldmaxgenesize = new GridBagConstraints();
		gbc_integerTextFieldmaxgenesize.gridwidth = 2;
		gbc_integerTextFieldmaxgenesize.insets = new Insets(0, 0, 5, 5);
		gbc_integerTextFieldmaxgenesize.fill = GridBagConstraints.HORIZONTAL;
		gbc_integerTextFieldmaxgenesize.gridx = 2;
		gbc_integerTextFieldmaxgenesize.gridy = 6;
		add(integerTextFieldmaxgenesize, gbc_integerTextFieldmaxgenesize);
	}
	
	
	private void setup() {
		
		prokaryotekeggnames=(LinkedHashMap<String, LinkedHashMap<String, String>>) MTUMapUtils.sortMapByKeys(KeggOrganismIdentifiers.getInstance().getProkaryotes(),true);
		eukaryotekeggnames=(LinkedHashMap<String, LinkedHashMap<String, String>>) MTUMapUtils.sortMapByKeys(KeggOrganismIdentifiers.getInstance().getEukaryotes(),true);
		
		comboBoxtype.addItem(PROKARYOTES);
		comboBoxtype.addItem(EUKARYOTES);

		for (ClusterProfileKeyType keytype : ClusterProfileKeyType.values()) {
			comboBoxkeytype.addItem(keytype.toString());
		}
		
		for (pvaluesAdjustMethod method : pvaluesAdjustMethod.values()) {
			comboBoxMCT.addItem(method);
		}
		
		setDefaultValues();
		init=true;
	}
	
	
	private void setDefaultValues() {
		comboBoxtype.setSelectedIndex(0);
		currentkeggnames=prokaryotekeggnames;
		
		changingfields=true;
		changeType();
		setupOrganismName();
		changingfields=false;
		
		comboBoxkeytype.setSelectedIndex(0);
		comboBoxMCT.setSelectedItem(pvaluesAdjustMethod.BH);
		integerTextFieldmingenesize.setText("10");
		integerTextFieldmaxgenesize.setText("500");
	}
	
	
	private void changeType() {
		
		if(comboBoxtype.getSelectedIndex()==0)
			currentkeggnames=prokaryotekeggnames;
		else
			currentkeggnames=eukaryotekeggnames;
		
		setupCategory();
	}
	
	
	
	private void setupCategory() {
		
		comboBoxCategory.removeAllItems();
		
		if(comboBoxtype.getSelectedIndex()==0) {
		
			for (String name : prokaryotekeggnames.keySet()) {
				comboBoxCategory.addItem(name);
			}
			
			comboBoxCategory.setSelectedItem("Escherichia");
		}
		else {
			for (String name : eukaryotekeggnames.keySet()) {
				comboBoxCategory.addItem(name);
			}
			
			comboBoxCategory.setSelectedItem("Mammals");
		}
		
		comboBoxOrganismname.removeAllItems();
	}
	
	

	private void setupOrganismName() {


		String category=(String) comboBoxCategory.getSelectedItem();

		LinkedHashMap<String, String> currentcategorgnames=currentkeggnames.get(category);
		LinkedHashMap<String, String> sorted=(LinkedHashMap<String, String>) MTUMapUtils.sortMapByKeys(currentcategorgnames, true);

		comboBoxOrganismname.removeAllItems();

		for (String id : sorted.keySet()) {
			comboBoxOrganismname.addItem(id);
		}

		comboBoxOrganismname.setSelectedIndex(0);
		textFieldKeggident.setText(sorted.get((String) comboBoxOrganismname.getSelectedItem()));
	}
	
	
	
	private void changeCategory() {
		setupOrganismName();
	}
	
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(CHANGETYPE) && init && !changingfields) {
			changingfields=true;
			changeType();
			setupOrganismName();
			changingfields=false;
		}
		else if(cmd.equals(CHANGECATEGORY) && init && !changingfields) {
			changeCategory();
		}
		else if(cmd.equals(CHANGEORGNAMES) && init && !changingfields) {
			String category=(String) comboBoxCategory.getSelectedItem();
			String orgname=(String) comboBoxOrganismname.getSelectedItem();
			String keggid=currentkeggnames.get(category).get(orgname);
			textFieldKeggident.setText(keggid);
		}
		
	}
	
	
	



	@Override
	public String getKeggOrganismIdentifier() {
		return textFieldKeggident.getText();
	}



	@Override
	public String getOrgDatabase() {
		return null;
	}



	@Override
	public String getKeyType() {
		return (String) comboBoxkeytype.getSelectedItem();
	}



	@Override
	public pvaluesAdjustMethod getAdjustMethod() {
		return (pvaluesAdjustMethod) comboBoxMCT.getSelectedItem();
	}



	@Override
	public int getMinGeneSize() {
		return integerTextFieldmingenesize.getIntegerValue();
	}



	@Override
	public int getMaxGeneSize() {
		return integerTextFieldmaxgenesize.getIntegerValue();
	}

	@Override
	public void resetToDefault() {
		setDefaultValues();
	}





	@Override
	public void setKeggOrganismIdentifier(String id) {
		textFieldKeggident.setText(id);
	}



	@Override
	public void setOrgDatabase(String name) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setKeyType(String keytype) {
		comboBoxkeytype.setSelectedItem(keytype);
		
	}



	@Override
	public void setAdjustMethod(pvaluesAdjustMethod method) {
		comboBoxMCT.setSelectedItem(method);
		
	}



	@Override
	public void setMinGeneSize(int value) {
		integerTextFieldmingenesize.setText(String.valueOf(value));
		
	}



	@Override
	public void setMaxGeneSize(int value) {
		integerTextFieldmaxgenesize.setText(String.valueOf(value));
		
	}

	public static void main(String[] args) {
		try {
			JFrame dialog = new JFrame();
			dialog.getContentPane().add(new ClusterProfilerKeggPanel());
			dialog.setSize(new Dimension(700, 500));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
