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
 * Created inside BIOSYSTEMS Group (https://www.ceb.uminho.pt/BIOSYSTEMS)
 */
package jbiclustgegui.gui.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustge.datatools.expressiondata.dataset.ExpressionData;
import jbiclustge.datatools.expressiondata.dataset.MissingValueImputationMethod;
import jbiclustge.datatools.expressiondata.dataset.MissingValuesInDataException;
import smile.imputation.MissingValueImputation;
import smile.imputation.MissingValueImputationException;

// TODO: Auto-generated Javadoc
/**
 * The Class NewProjectGUI.
 */
public class NewProjectGUI extends JDialog implements ActionListener, InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The text fieldprojectname. */
	private JTextField textFieldprojectname;
	
	/** The text fielddatasetfilepath. */
	private JTextField textFielddatasetfilepath;
	
	/** The combo boxmissingmethods. */
	private JComboBox comboBoxmissingmethods;
	
	/** The spinnermissingmethodparam. */
	private JSpinner spinnermissingmethodparam;
	
	/** The labelmethodlabel. */
	private JLabel labelmethodlabel;
	
	/** The panel 2. */
	private JPanel panel_2;
	
	/** The labelmethoddescription. */
	private JLabel labelmethoddescription;
	
	/** The dataset. */
	private ExpressionData dataset;
	
	/** The currentgeneexpressionfilepath. */
	private String currentgeneexpressionfilepath=null;
	
	/** The rec. */
	private ParamsReceiver rec;
	
	/** The text areanotes. */
	private JTextArea textAreanotes;
	
	/** The btn new buttonopendataset. */
	private JButton btnNewButtonopendataset;
	
	/** The needsimputationmethod. */
	private boolean needsimputationmethod=false;
	
	 /** The choosemethod. */
 	private static String CHOOSEMETHOD="choosemethod";
	 
 	/** The cancel. */
 	private static String CANCEL="close";
	 
 	/** The ok. */
 	private static String OK="ok";
	 
 	/** The loaddataset. */
 	private static String LOADDATASET="loadsdataset";
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			NewProjectGUI dialog = new NewProjectGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new new project GUI.
	 */
	public NewProjectGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
		initComponents();
	}
	
	
	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 671, 501);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1,1,1,1,1,1,1,1};
		gbl_contentPanel.rowHeights = new int[]{1,1,1,1,1,1,1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Project Name:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			this.textFieldprojectname = new JTextField();
			GridBagConstraints gbc_textFieldprojectname = new GridBagConstraints();
			gbc_textFieldprojectname.gridwidth = 5;
			gbc_textFieldprojectname.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldprojectname.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldprojectname.gridx = 2;
			gbc_textFieldprojectname.gridy = 0;
			contentPanel.add(this.textFieldprojectname, gbc_textFieldprojectname);
			this.textFieldprojectname.setColumns(10);
		}
		{
			btnNewButtonopendataset = new JButton("<html><center>Open Gene Expression<br> Dataset File:</center></html>");
			btnNewButtonopendataset.setPreferredSize(new Dimension(120, 40));
			btnNewButtonopendataset.setMinimumSize(new Dimension(140, 40));
			btnNewButtonopendataset.setActionCommand(LOADDATASET);
			btnNewButtonopendataset.addActionListener(this);
			GridBagConstraints gbc_btnNewButtonopendataset = new GridBagConstraints();
			gbc_btnNewButtonopendataset.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButtonopendataset.gridwidth = 2;
			gbc_btnNewButtonopendataset.insets = new Insets(0, 40, 5, 5);
			gbc_btnNewButtonopendataset.gridx = 0;
			gbc_btnNewButtonopendataset.gridy = 1;
			contentPanel.add(btnNewButtonopendataset, gbc_btnNewButtonopendataset);
			
		}
		{
			this.textFielddatasetfilepath = new JTextField();
			GridBagConstraints gbc_textFielddatasetfilepath = new GridBagConstraints();
			gbc_textFielddatasetfilepath.gridwidth = 6;
			gbc_textFielddatasetfilepath.insets = new Insets(0, 0, 5, 0);
			gbc_textFielddatasetfilepath.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFielddatasetfilepath.gridx = 2;
			gbc_textFielddatasetfilepath.gridy = 1;
			contentPanel.add(this.textFielddatasetfilepath, gbc_textFielddatasetfilepath);
			this.textFielddatasetfilepath.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Missing value imputation method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 8;
			gbc_panel.gridheight = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1, 0};
			gbl_panel.rowHeights = new int[]{1, 1, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Select method", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 0;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1, 0};
				gbl_panel_1.rowHeights = new int[]{1, 0};
				gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					comboBoxmissingmethods = new JComboBox();
					comboBoxmissingmethods.setActionCommand(CHOOSEMETHOD);
					comboBoxmissingmethods.addActionListener(this);
					GridBagConstraints gbc_comboBoxmissingmethods = new GridBagConstraints();
					gbc_comboBoxmissingmethods.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBoxmissingmethods.gridx = 0;
					gbc_comboBoxmissingmethods.gridy = 0;
					panel_1.add(comboBoxmissingmethods, gbc_comboBoxmissingmethods);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 1;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{1, 0};
				gbl_panel_1.rowHeights = new int[]{1, 1, 0};
				gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					GridBagConstraints gbc_panel_2 = new GridBagConstraints();
					gbc_panel_2.fill = GridBagConstraints.BOTH;
					gbc_panel_2.insets = new Insets(0, 0, 5, 0);
					gbc_panel_2.gridx = 0;
					gbc_panel_2.gridy = 0;
					panel_1.add(panel_2, gbc_panel_2);
					GridBagLayout gbl_panel_2 = new GridBagLayout();
					gbl_panel_2.columnWidths = new int[]{0, 0, 0};
					gbl_panel_2.rowHeights = new int[]{0, 0};
					gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
					gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
					panel_2.setLayout(gbl_panel_2);
					{
						labelmethoddescription = new JLabel("Missing value imputation by k-nearest neighbors");
						GridBagConstraints gbc_label = new GridBagConstraints();
						gbc_label.fill = GridBagConstraints.HORIZONTAL;
						gbc_label.gridx = 1;
						gbc_label.gridy = 0;
						panel_2.add(labelmethoddescription, gbc_label);
					}
				}
				{
					panel_2 = new JPanel();
					GridBagConstraints gbc_panel_2 = new GridBagConstraints();
					gbc_panel_2.fill = GridBagConstraints.BOTH;
					gbc_panel_2.gridx = 0;
					gbc_panel_2.gridy = 1;
					panel_1.add(panel_2, gbc_panel_2);
					GridBagLayout gbl_panel_2 = new GridBagLayout();
					gbl_panel_2.columnWidths = new int[]{1, 1, 1, 1, 1, 1, 0};
					gbl_panel_2.rowHeights = new int[]{1, 0};
					gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
					gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
					panel_2.setLayout(gbl_panel_2);
				
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Notes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridheight = 3;
			gbc_panel.gridwidth = 8;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 5;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1};
			gbl_panel.rowHeights = new int[]{1};
			gbl_panel.columnWeights = new double[]{1.0};
			gbl_panel.rowWeights = new double[]{1.0};
			panel.setLayout(gbl_panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel.add(scrollPane, gbc_scrollPane);
				{
					textAreanotes = new JTextArea();
					scrollPane.setViewportView(textAreanotes);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand(CANCEL);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	
	/**
	 * Inits the components.
	 */
	private void initComponents() {
		
		DefaultComboBoxModel combomodel=new DefaultComboBoxModel<>(MissingValueImputationMethod.values());
		comboBoxmissingmethods.setModel(combomodel);
		comboBoxmissingmethods.setSelectedIndex(2);
		addParameterPanel("Number of neighbors",1);
		comboBoxmissingmethods.setEnabled(false);
		spinnermissingmethodparam.setEnabled(false);
		
	}
	
	

	/**
	 * Adds the parameter panel.
	 *
	 * @param label the label
	 * @param minparam the minparam
	 */
	private void addParameterPanel(String label,int minparam) {
		
		labelmethodlabel = new JLabel(label);
		GridBagConstraints gbc_labelmethodlabel = new GridBagConstraints();
		gbc_labelmethodlabel.anchor = GridBagConstraints.EAST;
		gbc_labelmethodlabel.gridwidth = 2;
		gbc_labelmethodlabel.insets = new Insets(0, 0, 0, 10);
		gbc_labelmethodlabel.gridx = 0;
		gbc_labelmethodlabel.gridy = 0;
		panel_2.add(labelmethodlabel, gbc_labelmethodlabel);
		
		spinnermissingmethodparam = new JSpinner();
		spinnermissingmethodparam.setModel(new SpinnerNumberModel(4, minparam, 50, 1));
		GridBagConstraints gbc_spinnermissingmethodparam = new GridBagConstraints();
		gbc_spinnermissingmethodparam.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnermissingmethodparam.insets = new Insets(0, 0, 0, 5);
		gbc_spinnermissingmethodparam.gridx = 2;
		gbc_spinnermissingmethodparam.gridy = 0;
		panel_2.add(spinnermissingmethodparam, gbc_spinnermissingmethodparam);
		if(!needsimputationmethod)
			spinnermissingmethodparam.setEnabled(false);
		
	}
	

	/**
	 * Load expression dataset.
	 */
	private void loadExpressionDataset() {
		
		needsimputationmethod=false;
		currentgeneexpressionfilepath=null;
		
		disableMissingValueImputationComponents();
		
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        
        if(returnValue==JFileChooser.APPROVE_OPTION) {
        	File selected=fileChooser.getSelectedFile();
        	try {
				dataset=ExpressionData.loadDataset(selected.getAbsolutePath(), null);
				currentgeneexpressionfilepath=selected.getAbsolutePath();
			} catch (Exception e) {
				if(e instanceof MissingValuesInDataException) {
					
					JOptionPane.showMessageDialog(this, "The selected expression dataset have missing values. \n Please select a missing value imputation method to load this dataset.", "Missing values in gene expression dataset.", JOptionPane.INFORMATION_MESSAGE);
					enableMissingValueImputationComponents();
					needsimputationmethod=true;
					currentgeneexpressionfilepath=selected.getAbsolutePath();
					dataset=null;
					
				}
			}
        	textFielddatasetfilepath.setText(selected.getAbsolutePath());
        	
        }
 
	}
	
	/**
	 * Enable missing value imputation components.
	 */
	private void enableMissingValueImputationComponents() {
		comboBoxmissingmethods.setEnabled(true);
		spinnermissingmethodparam.setEnabled(true);
	}
	
	/**
	 * Disable missing value imputation components.
	 */
	private void disableMissingValueImputationComponents() {
		comboBoxmissingmethods.setEnabled(false);
		spinnermissingmethodparam.setEnabled(false);
	}
	
	
	/**
	 * Change method.
	 */
	private void changeMethod() {
		MissingValueImputationMethod method=(MissingValueImputationMethod) comboBoxmissingmethods.getSelectedItem();
		labelmethoddescription.setText(method.getDescription());
		
		if(method.equals(MissingValueImputationMethod.AverageImputation) || method.equals(MissingValueImputationMethod.ZeroValueImputation))
			panel_2.removeAll();
		else {
			String label=null;
			int min=1;
			if(method.equals(MissingValueImputationMethod.KMeansImputation)){
			    label="Number of clusters";
			    min=2;
			}
			else if(method.equals(MissingValueImputationMethod.KNNImputation))
				label="Number of neighbors";
			else if(method.equals(MissingValueImputationMethod.LLSImputation))
				label="Number of similar rows";
			else if(method.equals(MissingValueImputationMethod.SVDImputation))
					label="Number of eigenvectors";
			
			panel_2.removeAll();
			addParameterPanel(label,min);
		}
	}
	
	
	/**
	 * Gets the imputation method.
	 *
	 * @return the imputation method
	 */
	private MissingValueImputation getImputationMethod() {
		MissingValueImputationMethod method=(MissingValueImputationMethod) comboBoxmissingmethods.getSelectedItem();
		if(method.equals(MissingValueImputationMethod.AverageImputation) || method.equals(MissingValueImputationMethod.ZeroValueImputation))
			return method.getInstance();
		else 
			return method.getInstanceWithParameter((int) spinnermissingmethodparam.getValue());

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
		
		if(cmd.equals(LOADDATASET))
			loadExpressionDataset();
		else if(cmd.equals(CHOOSEMETHOD) && comboBoxmissingmethods.isEnabled()) {
			changeMethod();
		}
		else if(cmd.equals(CANCEL))
			finish();
		else if(cmd.equals(OK)) {
			boolean ok=true;
			if(textFieldprojectname.getText()==null || textFieldprojectname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please give the project a name", "Missing Parameter", JOptionPane.WARNING_MESSAGE);
				ok=false;
			}
			else if(needsimputationmethod) {
				
				if(currentgeneexpressionfilepath==null)
					ok=false;
				else {
					try {
						dataset=ExpressionData.loadDataset(currentgeneexpressionfilepath, getImputationMethod());
					} catch (IOException | ParseException | MissingValueImputationException e1) {
						ok=false;
						Workbench.getInstance().error(e1);
					}
				}
				
			}
			else if(dataset==null) {
				JOptionPane.showMessageDialog(this, "Please set the gene expression dataset file for loading", "Missing Parameter", JOptionPane.WARNING_MESSAGE);
				ok=false;	
			}
				
		    if(ok)
		    	termination();
		}
	}
	

	/**
	 * Termination.
	 */
	public void termination(){
		
		rec.paramsIntroduced(new ParamSpec[] {
				new ParamSpec("name", String.class,textFieldprojectname.getText(), null),
				new ParamSpec("dataset", ExpressionData.class,dataset, null),
				new ParamSpec("notes", String.class,textAreanotes.getText(), null),
				new ParamSpec("expressionfilepath", String.class,currentgeneexpressionfilepath, null)});
	}

}
