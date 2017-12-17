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
package jbiclustgegui.gui.components.progress;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

// TODO: Auto-generated Javadoc
/**
 * The Class CostumizableProgressBar.
 */
public class CostumizableProgressBar extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The icon. */
	protected ImageIcon icon;
	
	/** The progress bar. */
	final protected JProgressBar progressBar;
	
	/** The value. */
	protected int value;


	/**
	 * Instantiates a new costumizable progress bar.
	 *
	 * @param max the max
	 * @param image the image
	 */
	public CostumizableProgressBar(int max, ImageIcon image) {

		icon = image;	
		progressBar = new JProgressBar(0, max);
		value = 0;
		initGUI();
		
	}
	
	/**
	 * Instantiates a new costumizable progress bar.
	 *
	 * @param max the max
	 * @param imagePath the image path
	 */
	public CostumizableProgressBar(int max, String imagePath) {

		Image iconImage;
		try {
			iconImage = ImageIO.read(new File(imagePath));
			icon = new ImageIcon(iconImage);
			
			System.out.println(icon.getIconWidth());
			System.out.println(icon.getIconHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		progressBar = new JProgressBar(0, max);
		value = 0;
		initGUI();
		
	}

	/**
	 * Inits the GUI.
	 */
	protected void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10};
		gridBagLayout.rowHeights = new int[]{10, 10};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1, 1};
		this.setLayout(gridBagLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		JLabel label = new JLabel();
		if(icon!=null){
			label = new JLabel(icon);
			label.setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		}
		label.setOpaque(true);
		label.setBackground(new Color(0, 0, 0, 0));
		
		this.add(label, gbc);
		
		

		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 0, 1);
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		add(progressBar, gbc_progressBar);
		progressBar.setVisible(true);
		progressBar.setIndeterminate(false);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(true);
		this.setBackground(new Color(0, 0, 0, 0));
		
	}
	
	/**
	 * Increment.
	 */
	public void increment() {
		progressBar.setValue(value++);

	}

	/**
	 * Sets the string.
	 *
	 * @param text the new string
	 */
	public void setString(String text) {
		progressBar.setString(text);
		progressBar.setStringPainted(true);
	}

	/**
	 * Sets the value.
	 *
	 * @param i the new value
	 */
	public void setValue(int i) {
		progressBar.setValue(i);
		
	}
}