package com.puresol.coding.metrics.cocomo;

import java.awt.BorderLayout;

import javax.swingx.Label;
import javax.swingx.Panel;

import com.puresol.gui.osgi.BundleConfiguratorPanel;

public class CoCoMoConfiguratorPanel implements BundleConfiguratorPanel {

	private static final long serialVersionUID = 8314519169866353275L;

	private class CoCoMoPanel extends Panel {

		private static final long serialVersionUID = -3479718209248903666L;

		private CoCoMoPanel() {
			super();
			initUI();
		}

		private void initUI() {
			setLayout(new BorderLayout());
			add(new Label("CoCoMo Configurator Panel (not implemented yet)"),
					BorderLayout.CENTER);
		}
	}

	private final CoCoMoPanel panel;

	public CoCoMoConfiguratorPanel() {
		super();
		panel = new CoCoMoPanel();
	}

	@Override
	public Panel getPanel() {
		return panel;
	}

	@Override
	public void start() {
	}

	@Override
	public boolean stop() {
		return true;
	}

}
