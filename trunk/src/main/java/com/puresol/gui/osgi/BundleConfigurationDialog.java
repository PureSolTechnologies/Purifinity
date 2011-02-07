package com.puresol.gui.osgi;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.puresol.gui.Application;

public class BundleConfigurationDialog extends JDialog implements
		TreeSelectionListener {

	private static final long serialVersionUID = -359245614681727468L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfigurationDialog.class);

	private final String frameworkName;
	private final BundleConfiguratorTreeModel configuratorTreeView = new BundleConfiguratorTreeModel();

	public BundleConfigurationDialog(String frameworkName) {
		super(Application.getInstance(), translator
				.i18n("Plugin Configurations Dialog"), true);
		this.frameworkName = frameworkName;
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout());
		configuratorTreeView.setConfiguratorTree(BundleConfiguratorTree
				.create(frameworkName));
		configuratorTreeView.setBorder(BorderFactory
				.createTitledBorder(translator.i18n("Configurator")));
		configuratorTreeView.addTreeSelectionListener(this);

		panel.add(configuratorTreeView, BorderLayout.WEST);
		panel.add(new JPanel(), BorderLayout.CENTER);
		pack();
	}

	private void changeConfigurator(TreePath treePath) {
		System.out.println(treePath);
		BundleConfiguratorTree tree = (BundleConfiguratorTree) treePath
				.getPathComponent(treePath.getPathCount() - 1);
		System.out.println(tree);
		if (tree != null) {
			BundleConfigurator configurator = tree.getConfigurator();
			if (configurator != null) {
				BundleConfiguratorPanel a = tree.getConfigurator()
						.createPanel();
				if (a != null) {
					getContentPane().add(a.getPanel(), BorderLayout.CENTER);
				}
			}
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource() == configuratorTreeView) {
			changeConfigurator(e.getPath());
		}
	}
}
