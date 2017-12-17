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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import es.uvigo.ei.aibench.workbench.utilities.Utilities;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.components.panels.jbicge.ProjectSelectionPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectProjectGUI.
 */
public class SelectProjectGUI extends JDialog implements InputGUI{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The rec. */
	private ParamsReceiver rec;
    
    /** The project selection panel. */
    private ProjectSelectionPanel projectSelectionPanel;
	

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			SelectProjectGUI dialog = new SelectProjectGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new select project GUI.
	 */
	public SelectProjectGUI() {
		super(Workbench.getInstance().getMainFrame());
		initGUI();
;
	}
	
	
	/**
	 * Create the dialog.
	 */
	private void initGUI() {
		setBounds(100, 100, 484, 158);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						termination();
					}
				});

				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
		
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						finish();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				{
					JPanel panel = new JPanel();
					getContentPane().add(panel, BorderLayout.CENTER);
					GridBagLayout gbl_panel = new GridBagLayout();
					gbl_panel.columnWidths = new int[]{1};
					gbl_panel.rowHeights = new int[]{1};
					gbl_panel.columnWeights = new double[]{1.0};
					gbl_panel.rowWeights = new double[]{1.0};
					panel.setLayout(gbl_panel);
					{
						projectSelectionPanel = new ProjectSelectionPanel();
						GridBagConstraints gbc_projectSelectionPanel = new GridBagConstraints();
						gbc_projectSelectionPanel.fill = GridBagConstraints.BOTH;
						gbc_projectSelectionPanel.gridx = 0;
						gbc_projectSelectionPanel.gridy = 0;
						panel.add(projectSelectionPanel, gbc_projectSelectionPanel);
					}
				}
				
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
		this.setPreferredSize(new Dimension(300,150));
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

	
	

	/**
	 * Termination.
	 */
	private void termination(){
		
		
		rec.paramsIntroduced(new ParamSpec[] {
				new ParamSpec("Project", Project.class,
						projectSelectionPanel.getSelectedProject() , null)});
	}

}
