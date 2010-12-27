package com.puresol.gui.osgi;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swing.tree.TreePath;
import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.connect.Slot;

public class BundleConfigurationDialog extends Dialog {

	private static final long serialVersionUID = -359245614681727468L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfigurationDialog.class);

	private final String frameworkName;
	private final BundleConfiguratorTreeViewer configuratorTreeView = new BundleConfiguratorTreeViewer();

	public BundleConfigurationDialog(String frameworkName) {
		super(translator.i18n("Plugin Configurations"), true);
		this.frameworkName = frameworkName;
		initUI();
	}

	private void initUI() {
		Panel panel = new Panel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout());
		configuratorTreeView.setConfiguratorTree(BundleConfiguratorTree
				.create(frameworkName));
		configuratorTreeView.connect("valueChanged", this,
				"changeConfigurator", TreePath.class);
		panel.add(configuratorTreeView, BorderLayout.WEST);
		panel.add(new Panel(), BorderLayout.CENTER);
		pack();
	}

	@Slot
	void changeConfigurator(TreePath treePath) {
		System.out.println(treePath);
		BundleConfiguratorTree tree = (BundleConfiguratorTree) treePath
				.getPathComponent(treePath.getPathCount() - 1);
		BundleConfiguratorPanel a = tree.getConfigurator().createPanel();
		if (a != null) {
			Panel panel = a.getPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
		}
	}
}
