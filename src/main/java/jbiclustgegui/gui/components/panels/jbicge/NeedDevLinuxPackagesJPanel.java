package jbiclustgegui.gui.components.panels.jbicge;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class NeedDevLinuxPackagesJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel lblNewLabel;
	private ArrayList<String> elems;
    private String mainmessg="<html><center>The packages listed above must be installed. <br>However, you need sudo rights to install them.<br> Do you want to proceed?</center></html>";
	/**
	 * Create the panel.
	 */
	public NeedDevLinuxPackagesJPanel(ArrayList<String> elems, String message) {
        this.elems=elems;
        if(message!=null)
        	mainmessg=message;
		initGUI();
	}
	
	
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		list = new JList<String>();
		
		if(elems!=null) {
			DefaultListModel<String> model=new DefaultListModel<String>();
			
			for (int i = 0; i < elems.size(); i++) {
				model.addElement(elems.get(i));
			}
			
			list.setModel(model);
		}
		
		scrollPane.setViewportView(list);
		
		lblNewLabel = new JLabel(mainmessg);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);
	}

}
