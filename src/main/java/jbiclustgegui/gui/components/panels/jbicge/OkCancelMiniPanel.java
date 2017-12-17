/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
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
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class OkCancelMiniPanel.
 */
public class OkCancelMiniPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

/** The Constant OK_BUTTON_ACTION_COMMAND. */
//	public static final String INFO_BUTTON_ACTION_COMMAND = "infoButtonActionCommand";
	public static final String OK_BUTTON_ACTION_COMMAND = "okButtonActionCommand";
	
	/** The Constant CANCEL_BUTTON_ACTION_COMMAND. */
	public static final String CANCEL_BUTTON_ACTION_COMMAND = "cancelButtonActionCommand";

/** The ok button. */
//	protected JButton infoButton;
	protected JButton okButton;
	
	/** The cancel button. */
	protected JButton cancelButton;
	
	/**
	 * Instantiates a new ok cancel mini panel.
	 */
	public OkCancelMiniPanel(){
		initGUI();
	}


	/**
	 * Inits the GUI.
	 */
	protected void initGUI(){
		setLayout(new FlowLayout());
		
		////////////////////////////////////////////////////////////////////////
//		infoButton = new JButton();
////		infoButton.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
//		infoButton.setIcon(new ImageIcon(getClass().getResource("/images/icons/s16x16/infoIcon.png")));
//		infoButton.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
//		infoButton.setBorder(null);
//		add(infoButton);
//		//Always invisible on init  
//		infoButton.setVisible(false);
//		infoButton.setContentAreaFilled(false);
//		infoButton.setEnabled(false);
//		infoButton.setActionCommand(INFO_BUTTON_ACTION_COMMAND);
		
		///////////////////////////////////////////////////////////////////////////		
		okButton = new JButton("Ok");
		okButton.setActionCommand(OK_BUTTON_ACTION_COMMAND);
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand(CANCEL_BUTTON_ACTION_COMMAND);
		add(okButton);
		add(cancelButton);
		
	}
	
	/**
	 * Adds the buttons action listener.
	 *
	 * @param actionListener the action listener
	 */
	public void addButtonsActionListener(ActionListener actionListener){
		okButton.addActionListener(actionListener);
		cancelButton.addActionListener(actionListener);
//		infoButton.addActionListener(actionListener);
	}
	
	/**
	 * Adds the ok button action listener.
	 *
	 * @param actionListener the action listener
	 */
	public void addOkButtonActionListener(ActionListener actionListener){
		okButton.addActionListener(actionListener);
	}
	
	/**
	 * Adds the cancel button action listener.
	 *
	 * @param actionListener the action listener
	 */
	public void addCancelButtonActionListener(ActionListener actionListener){
		cancelButton.addActionListener(actionListener);
	}
	
//	public void addInfoButtonActionListener(ActionListener actionListener){
//		infoButton.addActionListener(actionListener);
//	}
	
	/**
 * Sets the enabled ok button.
 *
 * @param b the new enabled ok button
 */
public void setEnabledOkButton(Boolean b){
		okButton.setEnabled(b);
	}
	
//	public void setEnabledInfoButton(Boolean b){
//		infoButton.setEnabled(b);
//	}
	
//	public JButton getInfoButton() {
//		return infoButton;
//	}
	
//	public void setInfoButton(JButton infoButton) {
//		this.infoButton = infoButton;
//	}


	/**
 * Gets the ok button.
 *
 * @return the ok button
 */
public JButton getOkButton() {
		return okButton;
	}


	/**
	 * Sets the ok button.
	 *
	 * @param okButton the new ok button
	 */
	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}


	/**
	 * Gets the cancel button.
	 *
	 * @return the cancel button
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}


	/**
	 * Sets the cancel button.
	 *
	 * @param cancelButton the new cancel button
	 */
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
}
