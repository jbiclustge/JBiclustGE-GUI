package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import jbiclustgegui.gui.components.containers.RConfigurationProperties;

public class LinuxJbiclustChooseSettings extends AbstractJBiclustChooseSettings{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RLocalIntallationConfigPanel localIntallationConfigPanel;
	private AlgorithmsConfigPanel algorithmsConfigPanel;

	public LinuxJbiclustChooseSettings() {
		initGUI();
	}
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0,1.0};
		setLayout(gridBagLayout);
		
		localIntallationConfigPanel = new RLocalIntallationConfigPanel();
		GridBagConstraints gbc_localIntallationConfigPanel = new GridBagConstraints();
		gbc_localIntallationConfigPanel.gridheight = 3;
		gbc_localIntallationConfigPanel.insets = new Insets(0, 0, 5, 0);
		gbc_localIntallationConfigPanel.fill = GridBagConstraints.BOTH;
		gbc_localIntallationConfigPanel.gridx = 0;
		gbc_localIntallationConfigPanel.gridy = 0;
		add(localIntallationConfigPanel, gbc_localIntallationConfigPanel);
		
		algorithmsConfigPanel = new AlgorithmsConfigPanel();
		GridBagConstraints gbc_algorithmsConfigPanel = new GridBagConstraints();
		gbc_algorithmsConfigPanel.insets = new Insets(0, 0, 5, 0);
		gbc_algorithmsConfigPanel.fill = GridBagConstraints.BOTH;
		gbc_algorithmsConfigPanel.gridx = 0;
		gbc_algorithmsConfigPanel.gridy = 3;
		add(algorithmsConfigPanel, gbc_algorithmsConfigPanel);
	}

	
	@Override
	public Dimension getDimension() {
		return new Dimension(780, 470);
	}


	@Override
	public AlgorithmsConfigPanel getAlgorithmsConfigPanel() {
		return algorithmsConfigPanel;
	}
	
	@Override
	public RConfigurationProperties getRConfigurations() {
		return localIntallationConfigPanel.getRConfigurations();
	}
	@Override
	public boolean validSettings() {

		boolean valid=true;
		valid=algorithmsConfigPanel.isValidSettings();
		if(valid)
			valid=localIntallationConfigPanel.isValidSettings();
		
		return valid;
	}

	
	
	
	

}
