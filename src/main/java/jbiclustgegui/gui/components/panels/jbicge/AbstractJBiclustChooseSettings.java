package jbiclustgegui.gui.components.panels.jbicge;

import java.awt.Dimension;

import javax.swing.JPanel;

import jbiclustgegui.gui.components.containers.RConfigurationProperties;

public abstract class AbstractJBiclustChooseSettings extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	
	public abstract Dimension getDimension();

	
	public abstract AlgorithmsConfigPanel getAlgorithmsConfigPanel();
	public abstract RConfigurationProperties getRConfigurations();
	public abstract boolean validSettings();
}
