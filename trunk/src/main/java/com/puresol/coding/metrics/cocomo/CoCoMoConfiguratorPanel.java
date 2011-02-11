package com.puresol.coding.metrics.cocomo;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.puresol.gui.osgi.AbstractBundleConfiguratorPanel;

public class CoCoMoConfiguratorPanel extends AbstractBundleConfiguratorPanel {

	private static final long serialVersionUID = 8314519169866353275L;

	public CoCoMoConfiguratorPanel() {
		super();
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(new JLabel("CoCoMo Configurator Panel (not implemented yet)"),
				BorderLayout.CENTER);
	}

	@Override
	public void start() {
	}

	@Override
	public boolean stop() {
		return true;
	}

}
