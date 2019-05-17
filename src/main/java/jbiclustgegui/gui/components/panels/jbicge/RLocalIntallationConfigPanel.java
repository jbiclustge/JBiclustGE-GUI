package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FilenameUtils;

import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.osystem.CustomRInstallationManager.RenvVersion;
import jbiclustge.utils.osystem.JBiclustGESetupManager;
import jbiclustgegui.gui.components.containers.RConfigurationProperties;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVSYTEM;
import jbiclustgegui.gui.components.containers.RConfigurationProperties.RENVTYPE;
import pt.ornrocha.logutils.messagecomponents.LogMessageCenter;
import pt.ornrocha.rtools.installutils.RInstallTools;
import pt.ornrocha.swingutils.jfilechooser.JFileChooserWithLastDirMemory;

public class RLocalIntallationConfigPanel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JCheckBox chckbxSystemEnv;
	private JCheckBox chckbxStandaloneEnv;
	private JCheckBox chckbxInstalledEnv;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private RpackagesFolderConfigPanel rpackagesFolderConfigPanel;
	private JComboBox comboBoxVersions;
	private JButton btnOpenFolderRenv;
	private JTextField textFieldRenv;
	private JPanel panel_5;
	private boolean neededpackageschecked=false;
	private ArrayList<String> needlinuxdevlibraries=null;
	private boolean compilelinuxlibs=false;
	private boolean canproceedRinstallation=false;
	private JPanel panel_6;
	private JLabel lblRequirements;
	private JButton btnResolve;

	public RLocalIntallationConfigPanel() {
		initGUI();
		init();
	}
	
	
	
	private void initGUI() {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Configure a custom R environment for JBiclustGE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1,1};
		gridBagLayout.rowHeights = new int[]{1,1,1};
		gridBagLayout.columnWeights = new double[]{1.0,1.0};
		gridBagLayout.rowWeights = new double[]{1.0,1.0,1.0};
		setLayout(gridBagLayout);

		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{1};
		gbl_panel_1.rowHeights = new int[]{1,1,1};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{1.0,1.0,1.0};
		panel_1.setLayout(gbl_panel_1);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_1.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{1,1,1};
		gbl_panel_5.rowHeights = new int[]{1};
		gbl_panel_5.columnWeights = new double[]{1.0,1.0,1.0};
		gbl_panel_5.rowWeights = new double[]{1.0};
		panel_5.setLayout(gbl_panel_5);
		
		chckbxSystemEnv = new JCheckBox("No,  let me use the R environment already installed in my system");
		chckbxSystemEnv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chckbxStandaloneEnv.setSelected(false);
				chckbxInstalledEnv.setSelected(false);
				comboBoxVersions.setEnabled(false);
				btnOpenFolderRenv.setEnabled(false);
				textFieldRenv.setText("");
				rpackagesFolderConfigPanel.setRPackagesFolder("");
				textFieldRenv.setEnabled(false);
				rpackagesFolderConfigPanel.enableOptions();
				try {
					checkSystemRequirements();
				} catch (Exception e1) {
					Workbench.getInstance().error(e1);
					LogMessageCenter.getLogger().toClass(getClass()).addCriticalErrorMessage(e1);
				}
				
			}
		});
		GridBagConstraints gbc_chckbxSystemEnv = new GridBagConstraints();
		gbc_chckbxSystemEnv.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSystemEnv.anchor = GridBagConstraints.WEST;
		gbc_chckbxSystemEnv.gridx = 0;
		gbc_chckbxSystemEnv.gridy = 0;
		panel_5.add(chckbxSystemEnv, gbc_chckbxSystemEnv);
		
		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridwidth = 2;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 0;
		panel_5.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{1};
		gbl_panel_6.rowHeights = new int[]{1,1};
		gbl_panel_6.columnWeights = new double[]{1.0};
		gbl_panel_6.rowWeights = new double[]{1.0,1.0};
		panel_6.setLayout(gbl_panel_6);
		
		lblRequirements = new JLabel("All");
		GridBagConstraints gbc_lblAll = new GridBagConstraints();
		gbc_lblAll.insets = new Insets(0, 0, 5, 0);
		gbc_lblAll.gridx = 0;
		gbc_lblAll.gridy = 0;
		panel_6.add(lblRequirements, gbc_lblAll);
		
		btnResolve = new JButton("Check");
		btnResolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(btnResolve.isEnabled()) {
					try {
						
						ArrayList<String> packagesmissing = checkMissingRequirements();

						NeedDevLinuxPackagesJPanel needpackages=new NeedDevLinuxPackagesJPanel(packagesmissing, null);
						int option=JOptionPane.showConfirmDialog(Workbench.getInstance().getMainFrame(), needpackages, "Required libraries", JOptionPane.YES_NO_OPTION);

						if(option==JOptionPane.YES_OPTION) {
							needlinuxdevlibraries=new ArrayList<>(packagesmissing);
							//canproceedRinstallation=true;
						}
						else {
							needlinuxdevlibraries=null;
						}
					} catch (Exception e1) {
						LogMessageCenter.getLogger().addCriticalErrorMessage(e1);
					}

				}

			}
		});
		GridBagConstraints gbc_btnResolve = new GridBagConstraints();
		gbc_btnResolve.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnResolve.gridx = 0;
		gbc_btnResolve.gridy = 1;
		panel_6.add(btnResolve, gbc_btnResolve);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1};
		gbl_panel.rowHeights = new int[]{1,1};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0,1.0};
		panel.setLayout(gbl_panel);
		
		chckbxStandaloneEnv = new JCheckBox("Yes, install a standalone R environment");
		chckbxStandaloneEnv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chckbxSystemEnv.setSelected(false);
				chckbxInstalledEnv.setSelected(false);
				comboBoxVersions.setEnabled(true);
				btnOpenFolderRenv.setEnabled(false);
				textFieldRenv.setEnabled(false);
				textFieldRenv.setText("");
				rpackagesFolderConfigPanel.setRPackagesFolder("");
				btnResolve.setEnabled(false);
				rpackagesFolderConfigPanel.enableOptions();
				
				try {
					checkIfRequiredCompileLibrariesAreAvailable();
				} catch (Exception e1) {
					Workbench.getInstance().error(e1);
				}
				
			}
		});
		GridBagConstraints gbc_chckbxStandaloneEnv = new GridBagConstraints();
		gbc_chckbxStandaloneEnv.anchor = GridBagConstraints.WEST;
		gbc_chckbxStandaloneEnv.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxStandaloneEnv.gridx = 0;
		gbc_chckbxStandaloneEnv.gridy = 0;
		panel.add(chckbxStandaloneEnv, gbc_chckbxStandaloneEnv);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Version to install", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{1,1,1};
		gbl_panel_4.rowHeights = new int[]{1};
		gbl_panel_4.columnWeights = new double[]{1.0,1.0,1.0};
		gbl_panel_4.rowWeights = new double[]{1.0};
		panel_4.setLayout(gbl_panel_4);
		
		comboBoxVersions = new JComboBox();
		GridBagConstraints gbc_comboBoxVersions = new GridBagConstraints();
		gbc_comboBoxVersions.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxVersions.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVersions.gridx = 0;
		gbc_comboBoxVersions.gridy = 0;
		panel_4.add(comboBoxVersions, gbc_comboBoxVersions);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{1};
		gbl_panel_2.rowHeights = new int[]{1,1};
		gbl_panel_2.columnWeights = new double[]{1.0};
		gbl_panel_2.rowWeights = new double[]{1.0,1.0};
		panel_2.setLayout(gbl_panel_2);
		
		chckbxInstalledEnv = new JCheckBox("Yes, use a custom R environment previously installed");
		chckbxInstalledEnv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chckbxSystemEnv.setSelected(false);
				chckbxStandaloneEnv.setSelected(false);
				comboBoxVersions.setEnabled(false);
				btnOpenFolderRenv.setEnabled(true);
				textFieldRenv.setEnabled(true);
				btnResolve.setEnabled(false);
				rpackagesFolderConfigPanel.disableOptions();
			}
		});
		GridBagConstraints gbc_chckbxInstalledEnv = new GridBagConstraints();
		gbc_chckbxInstalledEnv.anchor = GridBagConstraints.WEST;
		gbc_chckbxInstalledEnv.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxInstalledEnv.gridx = 0;
		gbc_chckbxInstalledEnv.gridy = 0;
		panel_2.add(chckbxInstalledEnv, gbc_chckbxInstalledEnv);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Open Custom R ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{1,1,1,1};
		gbl_panel_3.rowHeights = new int[]{1};
		gbl_panel_3.columnWeights = new double[]{1.0,1.0,1.0,1.0};
		gbl_panel_3.rowWeights = new double[]{1.0};
		panel_3.setLayout(gbl_panel_3);
		
		btnOpenFolderRenv = new JButton("Open folder");
		btnOpenFolderRenv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseREnvfolder();
			}
		});
		GridBagConstraints gbc_btnOpenFolderRenv = new GridBagConstraints();
		gbc_btnOpenFolderRenv.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpenFolderRenv.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenFolderRenv.gridx = 0;
		gbc_btnOpenFolderRenv.gridy = 0;
		panel_3.add(btnOpenFolderRenv, gbc_btnOpenFolderRenv);
		
		textFieldRenv = new JTextField();
		GridBagConstraints gbc_textFieldRenv = new GridBagConstraints();
		gbc_textFieldRenv.gridwidth = 3;
		gbc_textFieldRenv.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldRenv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRenv.gridx = 1;
		gbc_textFieldRenv.gridy = 0;
		panel_3.add(textFieldRenv, gbc_textFieldRenv);
		textFieldRenv.setColumns(10);
		
		rpackagesFolderConfigPanel = new RpackagesFolderConfigPanel();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) rpackagesFolderConfigPanel.getLayout();
		gridBagLayout_1.rowWeights = new double[]{1.0};
		gridBagLayout_1.rowHeights = new int[]{1};
		gridBagLayout_1.columnWeights = new double[]{1.0};
		gridBagLayout_1.columnWidths = new int[]{1};
		GridBagConstraints gbc_rpackagesFolderConfigPanel = new GridBagConstraints();
		gbc_rpackagesFolderConfigPanel.gridwidth = 2;
		gbc_rpackagesFolderConfigPanel.insets = new Insets(0, 0, 0, 5);
		gbc_rpackagesFolderConfigPanel.fill = GridBagConstraints.BOTH;
		gbc_rpackagesFolderConfigPanel.gridx = 0;
		gbc_rpackagesFolderConfigPanel.gridy = 2;
		add(rpackagesFolderConfigPanel, gbc_rpackagesFolderConfigPanel);
	}
	
	private void init() {

		DefaultComboBoxModel<RenvVersion> commodel=new DefaultComboBoxModel<>(RenvVersion.values());
		comboBoxVersions.setModel(commodel);
		comboBoxVersions.setSelectedIndex(1);

		boolean systemRinstalled=RInstallTools.isRinstalledInOSys();

		if(systemRinstalled)
			chckbxSystemEnv.setSelected(true);
		else {
			chckbxSystemEnv.setSelected(false);
			chckbxSystemEnv.setEnabled(false);
			btnResolve.setEnabled(false);
		}

        chckbxStandaloneEnv.setSelected(false);
		chckbxInstalledEnv.setSelected(false);
		comboBoxVersions.setEnabled(false);
		btnOpenFolderRenv.setEnabled(false);
		textFieldRenv.setEnabled(false);

		if(systemRinstalled)
			try {
				checkSystemRequirements();
			} catch (Exception e) {
				Workbench.getInstance().error(e);
				LogMessageCenter.getLogger().toClass(getClass()).addCriticalErrorMessage(e);
			}
	}
	
	
	private void checkSystemRequirements() throws Exception {
		
		ArrayList<String> packagesmissing=checkMissingRequirements();
		
		if(packagesmissing.size()>0) {
			lblRequirements.setText("Needs to install required libraries");
			lblRequirements.setForeground(Color.red);
			btnResolve.setEnabled(true);
		}
		else {
			lblRequirements.setText("All requirements fulfilled");
			lblRequirements.setForeground(Color.green);
			btnResolve.setEnabled(false);
		}
		
	}
	
	
	
	private ArrayList<String> checkMissingRequirements() throws Exception {
		
		ArrayList<String> packagesmissing=new ArrayList<>();
		
		LinkedHashMap<String, Boolean> cp= JBiclustGESetupManager.checkIfCompilingToolsInstalled();
		
		for (String compt : cp.keySet()) {
			boolean state=cp.get(compt);
			if(!state)
				packagesmissing.add(compt);
		}
		
		LinkedHashMap<String, Boolean> mp= JBiclustGESetupManager.checkIfMandatoryLibrariesInstalled();
		
		for (String pack : mp.keySet()) {
			boolean state=mp.get(pack);
			if(!state)
				packagesmissing.add(JBiclustGESetupManager.getDebSystemRelatedPackage(pack));
		}
		
		if(!JBiclustGESetupManager.checkIfX11HeadersInstalled())
			packagesmissing.add("xorg-dev");
		if(!JBiclustGESetupManager.checkIfFortranInstalled())
			packagesmissing.add("gfortran");
		
		LinkedHashMap<String, Boolean> ndevpackages=JBiclustGESetupManager.checkIfRDEVLibrariesAreInstalled();
		for (String pack : ndevpackages.keySet()) {
			boolean state=ndevpackages.get(pack);
			if(!state)
				packagesmissing.add(pack);
		}
		
		return packagesmissing;
	}


	public RpackagesFolderConfigPanel getRpackagesConfigPanel() {
		return rpackagesFolderConfigPanel;
	}
	
	
	private void chooseREnvfolder() {
		JFileChooser chooser=JFileChooserWithLastDirMemory.getFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resultChooser = chooser.showOpenDialog(this);
		if(resultChooser == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().exists()) {
			JFileChooserWithLastDirMemory.setLastDir(chooser.getSelectedFile());
			String filepath=chooser.getSelectedFile().getAbsolutePath();
			
			String renv=getMostSuitableREnv(filepath);
			if(renv==null) {
				JOptionPane.showMessageDialog(this, "It was not possible to find an R environment in the folder you chose. Please choose another one", "Error ", JOptionPane.ERROR_MESSAGE);
			}
			else {
				textFieldRenv.setText(renv);
				setFolderToRpackages(renv);
			}
		}
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	
	
	private String getMostSuitableREnv(String maindir) {
		return JBiclustGESetupManager.getMostLikelyREnvDir(maindir);
	}
	

	
	private void setFolderToRpackages(String rootfolder) {
		
		String rlibrary=FilenameUtils.concat(rootfolder, "library");
		if(!new File(rlibrary).exists())
			new File(rlibrary).mkdirs();
		rpackagesFolderConfigPanel.setRPackagesFolder(rlibrary);
	}
	
	
	private void checkIfRequiredCompileLibrariesAreAvailable() throws Exception {
		
		if(!neededpackageschecked) {
			
			ArrayList<String> mandatorypackagesmissing=new ArrayList<>();
			
			LinkedHashMap<String, Boolean> cp= JBiclustGESetupManager.checkIfCompilingToolsInstalled();
			
			for (String compt : cp.keySet()) {
				boolean state=cp.get(compt);
				if(!state)
					mandatorypackagesmissing.add(compt);
			}
			
			
			LinkedHashMap<String, Boolean> mp= JBiclustGESetupManager.checkIfMandatoryLibrariesInstalled();
			
			for (String pack : mp.keySet()) {
				boolean state=mp.get(pack);
				if(!state)
					mandatorypackagesmissing.add(JBiclustGESetupManager.getDebSystemRelatedPackage(pack));
			}
			
			if(!JBiclustGESetupManager.checkIfX11HeadersInstalled())
				mandatorypackagesmissing.add("xorg-dev");
			if(!JBiclustGESetupManager.checkIfFortranInstalled())
				mandatorypackagesmissing.add("gfortran");
			

			ArrayList<String> needcompilepackages=new ArrayList<>();
			
	
			
			LinkedHashMap<String, Boolean> ndevpackages=JBiclustGESetupManager.checkIfRDEVLibrariesAreInstalled();
			for (String pack : ndevpackages.keySet()) {
				boolean state=ndevpackages.get(pack);
				if(!state)
					needcompilepackages.add(pack);
			}

			if(mandatorypackagesmissing.size()>0) {
				ArrayList<String> allpack=new ArrayList<>(mandatorypackagesmissing);
				allpack.addAll(needcompilepackages);
				
				NeedDevLinuxPackagesJPanel needpackages=new NeedDevLinuxPackagesJPanel(allpack, null);
				int option=JOptionPane.showConfirmDialog(this, needpackages, "Required libraries", JOptionPane.YES_NO_OPTION);
				
				if(option==JOptionPane.YES_OPTION) {
					needlinuxdevlibraries=new ArrayList<>(allpack);
					neededpackageschecked=true;
					canproceedRinstallation=true;
				}
				else {
					JOptionPane.showMessageDialog(this, "Sorry but the R environment cannot be installed without the following libraries:\n "+mandatorypackagesmissing, "Mandatory libraries to build R", JOptionPane.INFORMATION_MESSAGE);
					canproceedRinstallation=false;
				}
				
			}
			else {
			
				if(needcompilepackages.size()>0) {
					
					String message="<html><center>The packages listed above must be installed. <br>If \"yes\" you need sudo rights to install them.<br> if\"No\" these packages will be compiled (takes more time)</center></html>";
					NeedDevLinuxPackagesJPanel needpackages=new NeedDevLinuxPackagesJPanel(needcompilepackages, message);
					int option=JOptionPane.showConfirmDialog(this, needpackages, "Required libraries", JOptionPane.YES_NO_OPTION);
					
					if(option==JOptionPane.YES_OPTION) {
						needlinuxdevlibraries=new ArrayList<>(needcompilepackages);
						compilelinuxlibs=false;
						canproceedRinstallation=true;
					}
					else {
						needlinuxdevlibraries=null;
						compilelinuxlibs=true;
						canproceedRinstallation=true;
					}
				}
				else {
					
					String message="<html><center>(No) Proceed installation using development libs installed in the system<br>(Yes) Proceed by compiling the required libs</center></html>";
					int option=JOptionPane.showConfirmDialog(this, message, "Required libraries", JOptionPane.YES_NO_OPTION);
					
					canproceedRinstallation=true;
					if(option==JOptionPane.YES_OPTION) {
						compilelinuxlibs=true;
						needlinuxdevlibraries=null;
					}
					else
						compilelinuxlibs=false;
				}
				
				
			}
			
			
		}

	}
	
	public boolean isValidSettings() {
		
		boolean valid=true;
		
		if(chckbxInstalledEnv.isSelected() && textFieldRenv.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Please define the custom R environment folder, previously installed", "Wrong settings", JOptionPane.ERROR_MESSAGE);
			valid=false;
		}
		else if(!chckbxSystemEnv.isEnabled() && !chckbxStandaloneEnv.isSelected() && !chckbxInstalledEnv.isSelected()) {
			
			JOptionPane.showMessageDialog(this,"Please select one of the options", "Wrong settings", JOptionPane.ERROR_MESSAGE);
			valid=false;
		}
		else {
			valid=rpackagesFolderConfigPanel.isValidSettings();
		}
		
		
		return valid;
	}

	
	
	public RConfigurationProperties getRConfigurations() {
		
		RConfigurationProperties props=null;
		
		if(chckbxSystemEnv.isSelected()) {
			props=RConfigurationProperties.newInstance(RENVSYTEM.LINUX_DEB, RENVTYPE.SYSTEM)
					.setCandoRinstallation(true)
					.setLinuxDevLibrariesNames(needlinuxdevlibraries);
			
			if(rpackagesFolderConfigPanel.getChckbxLetMeChoose().isSelected())
				props.setRPackagesFolder(rpackagesFolderConfigPanel.getTextField().getText());
		}
		else if(chckbxStandaloneEnv.isSelected()) {
			props=RConfigurationProperties.newInstance(RENVSYTEM.LINUX_DEB, RENVTYPE.STANDALONE_NEW)
					.setCandoRinstallation(canproceedRinstallation)
					.setCompileLinuxLibs(compilelinuxlibs)
					.setLinuxDevLibrariesNames(needlinuxdevlibraries)
					.setVersion((RenvVersion) comboBoxVersions.getSelectedItem());
			
			
				if(rpackagesFolderConfigPanel.getChckbxLetMeChoose().isSelected())
					props.setRPackagesFolder(rpackagesFolderConfigPanel.getTextField().getText());
					
		}
		else {
			props=RConfigurationProperties.newInstance(RENVSYTEM.LINUX_DEB, RENVTYPE.STANDALONE_PREVIOUS);
			props.setRExecuteFolder(textFieldRenv.getText());
			String libsfolder=rpackagesFolderConfigPanel.getTextField().getText();
			if(!libsfolder.isEmpty())
				props.setRPackagesFolder(libsfolder);
			
		}
		

		return props;
	}
	
	

	public static void main(String[] args) {
		
		JFrame f = new JFrame("Test");
		f.getContentPane().add(new RLocalIntallationConfigPanel());
	    f.pack();
	    f.setVisible(true);
	}
	

}
