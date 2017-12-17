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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.uvigo.ei.aibench.Launcher;
import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustgegui.datatypes.project.save.SaveLoadManager;
import jbiclustgegui.gui.components.panels.jbicge.OkCancelMiniPanel;
import pt.uminho.ceb.biosystems.mew.utilities.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ChooseWorkspaceGUI.
 */
public class ChooseWorkspaceGUI extends JDialog implements ActionListener,InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant oldWsFile. */
	private static final String oldWsFile=".workspaces";
	
	/** The Constant CHOOSEBUTTONACTION. */
	private static final String CHOOSEBUTTONACTION = "CHOOSE_ACTION";
	
	/** The ok concel panel. */
	protected OkCancelMiniPanel okConcelPanel;
	
	/** The ws combo box. */
	protected JComboBox wsComboBox;
	
	/** The ws button. */
	protected JButton wsButton;
	
	/** The chooser. */
	final JFileChooser chooser = new JFileChooser();
	
	/** The rec. */
	protected ParamsReceiver rec;
	
	/**
	 * Instantiates a new choose workspace GUI.
	 */
	public ChooseWorkspaceGUI(){

		super(Workbench.getInstance().getMainFrame(), true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		

		initGUI();
		this.setLocationRelativeTo(Workbench.getInstance().getMainFrame().getContentPane());
		this.setFocusableWindowState(true);
	}
	
	/**
	 * Read old directories.
	 *
	 * @return the default combo box model
	 */
	private DefaultComboBoxModel readOldDirectories() {
		
		DefaultComboBoxModel dcb = new DefaultComboBoxModel();
		try {
			List<String> files = FileUtils.readLines(oldWsFile);
			for(String s : files)
				dcb.addElement(s);
		} catch (Exception e) {
			dcb.addElement(System.getProperty("user.home")+"/jbiclustGE_workspace/");
		} 
		return dcb;
	}

	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel workspacePanel = new JPanel();
		workspacePanel.setBorder(new TitledBorder(null, "Workspace Folder", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
		GridBagConstraints gbc_workspacePanel = new GridBagConstraints();
		gbc_workspacePanel.fill = GridBagConstraints.BOTH;
		gbc_workspacePanel.insets = new Insets(0, 0, 5, 0);
		gbc_workspacePanel.gridx = 0;
		gbc_workspacePanel.gridy = 0;
		getContentPane().add(workspacePanel, gbc_workspacePanel);
		
		GridBagLayout gbl_workspacePanel = new GridBagLayout();
		gbl_workspacePanel.columnWidths = new int[]{0, 0};
		gbl_workspacePanel.rowHeights = new int[]{0, 0};
		gbl_workspacePanel.columnWeights = new double[]{1.0, 0.0};
		gbl_workspacePanel.rowWeights = new double[]{0.0};
		workspacePanel.setLayout(gbl_workspacePanel);
		
		wsComboBox = new JComboBox();
		GridBagConstraints gbc_wsComboBox = new GridBagConstraints();
		gbc_wsComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_wsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_wsComboBox.gridx = 0;
		gbc_wsComboBox.gridy = 0;
		workspacePanel.add(wsComboBox, gbc_wsComboBox);
		
		wsButton = new JButton("Browse...");
		wsButton.setActionCommand(CHOOSEBUTTONACTION);
		wsButton.addActionListener(this);
		
		GridBagConstraints gbc_wsButton = new GridBagConstraints();
		gbc_wsButton.gridx = 1;
		gbc_wsButton.gridy = 0;
		workspacePanel.add(wsButton, gbc_wsButton);
		ComboBoxModel m = readOldDirectories();
		
		System.out.println(m);
	    wsComboBox.setModel(m);
		wsComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getID() == ItemEvent.SELECTED){
					chooser.setCurrentDirectory(new java.io.File(e.getItem().toString()));
				}
				
			}
		});
		
		okConcelPanel = new OkCancelMiniPanel();
		GridBagConstraints gbc_okConcelPanel = new GridBagConstraints();
		gbc_okConcelPanel.fill = GridBagConstraints.BOTH;
		gbc_okConcelPanel.gridx = 0;
		gbc_okConcelPanel.gridy = 1;
		getContentPane().add(okConcelPanel, gbc_okConcelPanel);

		
		okConcelPanel.addButtonsActionListener(this);
		okConcelPanel.getCancelButton().setVisible(true);
	    chooser.setDialogTitle("Choose a workspace directory...");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	    pack();

	}
	
	/**
	 * Save infoin file.
	 */
	private void saveInfoinFile(){
		
		List<String> files = new ArrayList<String>();		
		try {
			files = FileUtils.readLines(oldWsFile);
		} catch (IOException e) {
			
		}
		
		String selected = wsComboBox.getSelectedItem().toString();
		String info = selected+"\n";		
		for(String e : files){
			if(!e.equals(selected))
				info+=e+"\n";
		}
		
		try {
			FileUtils.saveStringInFile(oldWsFile, info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Termination.
	 */
	public void termination(){
		
		saveInfoinFile();
		File f = new File(wsComboBox.getSelectedItem().toString());
		rec.paramsIntroduced(new ParamSpec[] {
							new ParamSpec("ws", File.class,
									f, null)});		
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
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#init(es.uvigo.ei.aibench.workbench.ParamsReceiver, es.uvigo.ei.aibench.core.operation.OperationDefinition)
	 */
	@Override
	public void init(ParamsReceiver arg0, OperationDefinition<?> arg1) {
		rec = arg0;

		setVisible(true);
		
	    
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(OkCancelMiniPanel.OK_BUTTON_ACTION_COMMAND)){

			termination();
			
		}else if(e.getActionCommand().equals(OkCancelMiniPanel.CANCEL_BUTTON_ACTION_COMMAND)){
			String ws = SaveLoadManager.getInstance().getWorkspace();
			if(ws==null || ws.equals("")){
				Launcher.getPluginEngine().shutdown();
				System.exit(0);
			}else
				finish();
				
 		}else if(e.getActionCommand().equals(CHOOSEBUTTONACTION)){
		    if (chooser.showDialog(this, "Choose") == JFileChooser.APPROVE_OPTION) { 
		      wsComboBox.insertItemAt(chooser.getSelectedFile().getAbsolutePath(),0);
		      wsComboBox.setSelectedIndex(0);
		    } 
		}
	}

	/* (non-Javadoc)
	 * @see es.uvigo.ei.aibench.workbench.InputGUI#onValidationError(java.lang.Throwable)
	 */
	@Override
	public void onValidationError(Throwable t) {
		t.printStackTrace();
		Workbench.getInstance().error(t.getMessage());
		
	}
	
 
		 
		 

	
}
