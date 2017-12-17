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
package jbiclustgegui.gui.analysis.profilecli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.containers.GSEAConfigurationContainer;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;
import jbiclustgegui.gui.components.panels.profilescli.ProfileSettingsMaker;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileMakerToJBiclustGECLI.
 */
public class ProfileMakerToJBiclustGECLI extends JDialog implements ActionListener,InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The project selection panel. */
	private ProjectSelectionPanel projectSelectionPanel;
	
	/** The panelmethods. */
	private ProfileSettingsMaker panelmethods;
	
	/** The rec. */
	private ParamsReceiver rec;
	
	/** The currentproject. */
	private String currentproject=null;
	
	
	
	/** The close. */
	private static String CLOSE="close";
	
	/** The ok. */
	private static String OK="ok";

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			ProfileMakerToJBiclustGECLI dialog = new ProfileMakerToJBiclustGECLI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Instantiates a new profile maker to J biclust GECLI.
	 */
	public ProfileMakerToJBiclustGECLI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
		setProject(projectSelectionPanel.getSelectedProject());
	}
	
	

	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 1013, 633);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1,1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{0.1,1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			projectSelectionPanel = new ProjectSelectionPanel();
			projectSelectionPanel.setProjectActionListener(this);
			//JPanel projectSelectionPanel=new JPanel();
			GridBagConstraints gbc_projectSelectionPanel = new GridBagConstraints();
			gbc_projectSelectionPanel.insets = new Insets(0, 0, 5, 0);
			gbc_projectSelectionPanel.fill = GridBagConstraints.BOTH;
			gbc_projectSelectionPanel.gridx = 0;
			gbc_projectSelectionPanel.gridy = 0;
			contentPanel.add(projectSelectionPanel, gbc_projectSelectionPanel);
		}
		{
			panelmethods = new ProfileSettingsMaker();
			GridBagConstraints gbc_panelmethods = new GridBagConstraints();
			gbc_panelmethods.fill = GridBagConstraints.BOTH;
			gbc_panelmethods.gridx = 0;
			gbc_panelmethods.gridy = 1;
			contentPanel.add(panelmethods, gbc_panelmethods);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(new Dimension(1000, 700));
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
	
	
	/**
	 * Sets the project.
	 *
	 * @param proj the new project
	 */
	private void setProject(Project proj) {
		
		String newprojname=proj.getName();
		if(currentproject==null || !currentproject.equals(newprojname)){
			panelmethods.setCurrentProject(proj);
			panelmethods.resetToDefault();
			currentproject=newprojname;
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
		else if(cmd.equals(ProjectSelectionPanel.PROJECT_ACTION_COMMAND)) {
			setProject(projectSelectionPanel.getSelectedProject());
		}
		else if(cmd.equals(OK)) {
			boolean isvalid=true;
			if(panelmethods.getNameProfile()==null || panelmethods.getNameProfile().isEmpty()) {
				launchWarning("Please set a name to profile");
				isvalid=false;
			}
			else if(panelmethods.getSaveToFolder()==null || panelmethods.getSaveToFolder().isEmpty()) {
				launchWarning("Please define where you want to save this profile");
				isvalid=false;
			}
			else if(panelmethods.numberMethodsConfigured()==0) {
				launchWarning("Please define at least one configuration for a biclustering method");
				isvalid=false;
			}
			else if(panelmethods.getPValues().size()==0 && panelmethods.isToPerformGSEA()) {
				launchWarning("Please define at least one p-value to perform GSEA");
				isvalid=false;
			}
			if(isvalid)
				try {
					terminate();
				} catch (IllegalArgumentException | IOException e1) {
					Workbench.getInstance().error(e1);
				}
				
		}
		
	}
	
	

	/**
	 * Terminate.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void terminate() throws IllegalArgumentException, IOException{

		
		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("Project", Project.class,projectSelectionPanel.getSelectedProject(), null));
		listSpecs.add(new ParamSpec("Configurations methods",ArrayList.class,panelmethods.getMethodConfigurations(), null));
		listSpecs.add(new ParamSpec("Profile name",String.class,panelmethods.getNameProfile(), null));
		listSpecs.add(new ParamSpec("Profile path",String.class,panelmethods.getSaveToFolder(), null));
		listSpecs.add(new ParamSpec("GSEA configuration",GSEAConfigurationContainer.class,panelmethods.isToPerformGSEA()?panelmethods.getGSEAConfiguration():null, null));
		listSpecs.add(new ParamSpec("Concurrent Processes",Integer.class,panelmethods.getConcurrentProcesses(), null));
		listSpecs.add(new ParamSpec("p-values",ArrayList.class,panelmethods.getPValues(), null));
		listSpecs.add(new ParamSpec("adjusted p-values",Boolean.class,panelmethods.useAdjustedPvalues(), null));

		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}

	/**
	 * Launch warning.
	 *
	 * @param msg the msg
	 */
	private void launchWarning(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Incorrect Parameters", JOptionPane.WARNING_MESSAGE);
	}

}
