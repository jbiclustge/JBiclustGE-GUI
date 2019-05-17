package jbiclustgegui.gui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uvigo.ei.aibench.core.ParamSpec;
import es.uvigo.ei.aibench.core.operation.OperationDefinition;
import es.uvigo.ei.aibench.workbench.InputGUI;
import es.uvigo.ei.aibench.workbench.ParamsReceiver;
import es.uvigo.ei.aibench.workbench.Workbench;
import jbiclustge.utils.osystem.JBiclustGESetupManager;
import jbiclustgegui.gui.components.containers.RConfigurationProperties;
import jbiclustgegui.gui.components.panels.jbicge.AbstractJBiclustChooseSettings;
import jbiclustgegui.gui.components.panels.jbicge.LinuxJbiclustChooseSettings;
import jbiclustgegui.gui.components.panels.jbicge.WindowsJbiclustChooseSettings;
import pt.ornrocha.systemutils.OSystemUtils;

public class NewConfigureJBiclustGESettingsGUI extends JDialog implements ActionListener,InputGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ParamsReceiver rec;
    private AbstractJBiclustChooseSettings panelchoose;
    private boolean shutdown=false;
    

    private static String CANCEL="cancel";
	
	/** The ok. */
	private static String OK="ok";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewConfigureJBiclustGESettingsGUI dialog = new NewConfigureJBiclustGESettingsGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewConfigureJBiclustGESettingsGUI() {
		setBounds(100, 100, 719, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1};
		gbl_contentPanel.rowHeights = new int[]{1};
		gbl_contentPanel.columnWeights = new double[]{1.0,};
		gbl_contentPanel.rowWeights = new double[]{1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			if(OSystemUtils.isWindows())
				panelchoose=new WindowsJbiclustChooseSettings();
			else
				panelchoose=new LinuxJbiclustChooseSettings();
			
			//setSize(panelchoose.getDimension());
			GridBagConstraints gbc_panelchoose = new GridBagConstraints();
			gbc_panelchoose.fill = GridBagConstraints.BOTH;
			gbc_panelchoose.gridx = 0;
			gbc_panelchoose.gridy = 0;
			contentPanel.add(panelchoose, gbc_panelchoose);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand(CANCEL);
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void init(ParamsReceiver receiver, OperationDefinition<?> operation) {
		rec = receiver;
		setTitle(operation.getName());
		setModal(true);
		this.setPreferredSize(panelchoose.getDimension());
	    pack();
	    //Utilities.centerOnOwner(this);
	    setLocationRelativeTo(Workbench.getInstance().getMainFrame());
		setVisible(true);
		
	}

	@Override
	public void onValidationError(Throwable t) {
		Workbench.getInstance().error(t);
		
	}

	@Override
	public void finish() {
		setVisible(false);
		dispose();
		if(!JBiclustGESetupManager.isConfigured() && shutdown)
			System.exit(0);
	}
	
	private void terminate() {
		
		RConfigurationProperties rconfigs=panelchoose.getRConfigurations();
		String zipfile=null;
		if(!panelchoose.getAlgorithmsConfigPanel().getTextField().getText().isEmpty())
			zipfile=panelchoose.getAlgorithmsConfigPanel().getTextField().getText();
	

		ArrayList<ParamSpec> listSpecs=new ArrayList<>();
		listSpecs.add(new ParamSpec("rconfigurations", RConfigurationProperties.class,rconfigs, null));
		listSpecs.add(new ParamSpec("algorithmszip", String.class,zipfile, null));
		
		ParamSpec[] arraySpecs=new ParamSpec[listSpecs.size()];
		for (int i = 0; i < listSpecs.size(); i++) {
			arraySpecs[i]=listSpecs.get(i);
		}
		rec.paramsIntroduced(arraySpecs);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		
		if(cmd.equals(CANCEL)) {
			shutdown=true;
			finish();
		}
		else if(cmd.equals(OK)) {
			if(panelchoose.validSettings())
				terminate();
		}
		
	}
}
