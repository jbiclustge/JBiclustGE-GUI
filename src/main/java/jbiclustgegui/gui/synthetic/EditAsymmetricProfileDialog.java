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
package jbiclustgegui.gui.synthetic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.javatuples.Pair;

import jbiclustgegui.gui.synthetic.tables.TableOverlapBetweenBiclusters;

// TODO: Auto-generated Javadoc
/**
 * The Class EditAsymmetricProfileDialog.
 */
public class EditAsymmetricProfileDialog extends JDialog implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
    
    /** The tableoverlap. */
    private TableOverlapBetweenBiclusters tableoverlap;
    
    
    
    /** The close. */
    private static String CLOSE="close";
    
    /** The ok. */
    private static String OK="ok";
	
	/** The Constant APPROVE_OPTION. */
	public static final int APPROVE_OPTION = 0;
    
    /** The Constant CANCEL_OPTION. */
    public static final int CANCEL_OPTION = 1;
    
    /** The cmdoperation. */
    private int cmdoperation=-1;
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			EditAsymmetricProfileDialog dialog = new EditAsymmetricProfileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditAsymmetricProfileDialog() {
		initGUI();
		
	}
	
	
	
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI(){
		setBounds(100, 100, 381, 255);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			tableoverlap=new TableOverlapBetweenBiclusters();
			JScrollPane scrollPane = new JScrollPane(tableoverlap);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		/*{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
		}*/
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
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand(CLOSE);
			}
		}
	}

	/**
	 * Show open dialog.
	 *
	 * @param overlapprofile the overlapprofile
	 * @param parent the parent
	 * @return the int
	 */
	public int showOpenDialog(ArrayList<Pair<Integer, Integer>> overlapprofile, Component parent){
		tableoverlap.setOverlapBetweenBiclusters(overlapprofile);

		setTitle("Edit overlap profile");
		
		return showDialog(parent);
	} 

	
	/**
	 * Show dialog.
	 *
	 * @param parent the parent
	 * @return the int
	 */
	protected int showDialog(Component parent){
		
		setAlwaysOnTop(true);
		setModal(true);
		Component parent1=SwingUtilities.windowForComponent(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		if(parent!=null)
			this.setLocationRelativeTo(parent);
		else
			this.setLocationRelativeTo(parent1);
		
		setVisible(true);

		return cmdoperation;
	}
	
	
	/**
	 * Gets the overlap between biclusters.
	 *
	 * @return the overlap between biclusters
	 */
	public ArrayList<Pair<Integer, Integer>> getOverlapBetweenBiclusters(){
		return tableoverlap.getOverlapBetweenBiclusters();
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals(CLOSE)) {
			cmdoperation=CANCEL_OPTION;
			dispose();
			
		}
		else if(cmd.equals(OK)) {
				cmdoperation=APPROVE_OPTION;
				dispose();
		}
		
	}

}
