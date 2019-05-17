package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JOptionPane;

import jbiclustgegui.gui.components.containers.RConfigurationProperties;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVSYTEM;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVTYPE;
import pt.ornrocha.rtools.installutils.RInstallTools;

public class WindowsJbiclustChooseSettings extends AbstractJBiclustChooseSettings{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RpackagesFolderConfigPanel rpackagesFolderConfigPanel;
    private AlgorithmsConfigPanel algorithmsConfigPanel;
    
    private boolean rinstalled=false;

	public WindowsJbiclustChooseSettings() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1};
		gridBagLayout.rowHeights = new int[]{1,1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0};
		setLayout(gridBagLayout);
		
		rpackagesFolderConfigPanel = new RpackagesFolderConfigPanel();
		GridBagConstraints gbc_rpackagesFolderConfigPanel = new GridBagConstraints();
		gbc_rpackagesFolderConfigPanel.insets = new Insets(0, 0, 5, 0);
		gbc_rpackagesFolderConfigPanel.fill = GridBagConstraints.BOTH;
		gbc_rpackagesFolderConfigPanel.gridx = 0;
		gbc_rpackagesFolderConfigPanel.gridy = 0;
		add(rpackagesFolderConfigPanel, gbc_rpackagesFolderConfigPanel);
		
		algorithmsConfigPanel = new AlgorithmsConfigPanel();
		GridBagConstraints gbc_algorithmsConfigPanel = new GridBagConstraints();
		gbc_algorithmsConfigPanel.fill = GridBagConstraints.BOTH;
		gbc_algorithmsConfigPanel.gridx = 0;
		gbc_algorithmsConfigPanel.gridy = 1;
		add(algorithmsConfigPanel, gbc_algorithmsConfigPanel);
		
		checkREnv();
	}
	
	private void checkREnv() {

		boolean systemRinstalled=RInstallTools.isRinstalledInOSys();
        rinstalled=systemRinstalled;
		
        if(!systemRinstalled) {
			rpackagesFolderConfigPanel.disableOptions();
	
			int option=JOptionPane.showConfirmDialog(this,new WindowsRNotInstalledWarnPanel(), "Warning",JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.NO_OPTION)
				System.exit(0);
			else {
				// check R again because user can install R and Rtools in the meantime
				systemRinstalled=RInstallTools.isRinstalledInOSys();
				rinstalled=systemRinstalled;
				if(systemRinstalled)
					rpackagesFolderConfigPanel.enableOptions();
			}
		}
		
		
	}


	@Override
	public Dimension getDimension() {
		return new Dimension(700, 250);
	}


	@Override
	public AlgorithmsConfigPanel getAlgorithmsConfigPanel() {
		return algorithmsConfigPanel;
	}


	@Override
	public RConfigurationProperties getRConfigurations() {
		
		String rlibs=null;
		if(rinstalled && rpackagesFolderConfigPanel.getChckbxLetMeChoose().isSelected()) {
			String userlibspath=rpackagesFolderConfigPanel.getTextField().getText();
			if(userlibspath!=null && !userlibspath.isEmpty())
				rlibs=userlibspath;
		}

		return RConfigurationProperties.newInstance(RENVSYTEM.WINDOWS, RENVTYPE.SYSTEM).setRPackagesFolder(rlibs).setCandoRinstallation(rinstalled);
	}


	@Override
	public boolean validSettings() {
		boolean valid=true;
		valid=algorithmsConfigPanel.isValidSettings();
		if(valid)
			valid=rpackagesFolderConfigPanel.isValidSettings();
		
		return valid;
	}

}
