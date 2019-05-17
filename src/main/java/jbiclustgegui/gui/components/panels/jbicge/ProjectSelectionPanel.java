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
package jbiclustgegui.gui.components.panels.jbicge;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.uvigo.ei.aibench.core.Core;
import es.uvigo.ei.aibench.core.clipboard.ClipboardItem;
import jbiclustgegui.datatypes.project.AbstractDataType;
import jbiclustgegui.datatypes.project.Project;
import jbiclustgegui.gui.project.GUIUtilities;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.GridBagConstraints;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectSelectionPanel.
 */
public class ProjectSelectionPanel extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The project combo box. */
	private JComboBox<Project> projectComboBox;
	private ArrayList<String> currentprojs=null;
	
	/** The Constant PROJECT_ACTION_COMMAND. */
	public static final String PROJECT_ACTION_COMMAND = "projectActionCommand";
	
	/**
	 * Instantiates a new project selection panel.
	 */
	public ProjectSelectionPanel() {
		initGUI();
		setProjectComboBoxDataItems();
	}
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		setBorder(new TitledBorder(null, "Select Project", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.projectComboBox = new JComboBox<Project>();
		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox.gridx = 0;
		gbc_projectComboBox.gridy = 0;
		add(this.projectComboBox, gbc_projectComboBox);
		projectComboBox.setActionCommand(PROJECT_ACTION_COMMAND);
	}

	/**
	 * Sets the project combo box data items.
	 */
	protected void setProjectComboBoxDataItems() {
		
		currentprojs=new ArrayList<>();
		List<ClipboardItem> citems = Core.getInstance().getClipboard().getItemsByClass(Project.class);
		if (citems == null){
			citems = new ArrayList<ClipboardItem>();
		}
		for (ClipboardItem item: citems){
			Project project = (Project) item.getUserData();
			projectComboBox.addItem(project);
			currentprojs.add(project.getName());
		}
		if(projectComboBox.getItemCount()!=0)
			trytoAssignProject();
	}
	
	protected void trytoAssignProject() {
		AbstractDataType currentselectedintree=GUIUtilities.getSelectedItem();
		if(currentselectedintree instanceof Project) {
			String pname=currentselectedintree.getName();
			if(pname!=null) {
				int indx=getProjectIndex(pname);
			    projectComboBox.setSelectedIndex(indx);
			}
		}
		else {
			
			if(currentselectedintree!=null && !(currentselectedintree instanceof Project)) {
				Project p=currentselectedintree.getOwnerProject();
				if(p!=null) {
					int indx=getProjectIndex(p.getName());
					projectComboBox.setSelectedIndex(indx);
				}
				else
					projectComboBox.setSelectedIndex(0);
			}
			else
				projectComboBox.setSelectedIndex(0);
		}
	}
	

	protected int getProjectIndex(String name) {
		
		for (int i = 0; i < currentprojs.size(); i++) {
			if(currentprojs.get(i).equals(name))
				return i;
		}
		return 0;
	}
	
	/**
	 * Gets the selected project.
	 *
	 * @return the selected project
	 */
	public Project getSelectedProject(){
		Object object = projectComboBox.getSelectedItem();
		Project project =null;
		if(object!=null)
			project = (Project)object;
		return project;
	}
	
	
	/**
	 * Sets the project action listener.
	 *
	 * @param l the new project action listener
	 */
	public void setProjectActionListener(ActionListener l) {
		projectComboBox.addActionListener(l);
	}

}
