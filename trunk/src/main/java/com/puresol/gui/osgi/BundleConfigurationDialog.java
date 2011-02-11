package com.puresol.gui.osgi;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.puresol.gui.Application;

/**
 * This class provides a dialog for plug-in configuration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BundleConfigurationDialog extends JDialog implements
		TreeSelectionListener {

	private static final long serialVersionUID = -359245614681727468L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfigurationDialog.class);

	private static class EmptyPanel extends AbstractBundleConfiguratorPanel {

		private static final long serialVersionUID = -4884833535616002340L;

		@Override
		public void start() {
		}

		@Override
		public boolean stop() {
			return false;
		}
	}

	private final String frameworkName;
	private final BundleConfiguratorTreeModel configuratorTreeView = new BundleConfiguratorTreeModel();
	private final JPanel contentPanel = new JPanel();
	private AbstractBundleConfiguratorPanel currentPanel = null;

	public BundleConfigurationDialog(String frameworkName) {
		super(Application.getInstance(), translator
				.i18n("Plugin Configurations Dialog"), true);
		this.frameworkName = frameworkName;
		initUI();
	}

	private void initUI() {
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		configuratorTreeView.setConfiguratorTree(BundleConfiguratorTree
				.create(frameworkName));
		configuratorTreeView.setBorder(BorderFactory
				.createTitledBorder(translator.i18n("Configurator")));
		configuratorTreeView.addTreeSelectionListener(this);
		contentPanel.add(new JScrollPane(configuratorTreeView),
				BorderLayout.WEST);
		currentPanel = new EmptyPanel();
		contentPanel.add(currentPanel, BorderLayout.CENTER);
		pack();
	}

	private void changeConfigurator(TreePath treePath) {
		final BundleConfiguratorTree tree = (BundleConfiguratorTree) treePath
				.getPathComponent(treePath.getPathCount() - 1);
		if (tree != null) {
			contentPanel.remove(currentPanel);
			BundleConfigurator configurator = tree.getConfigurator();
			if (configurator != null) {
				currentPanel = configurator.createPanel();
			} else {
				currentPanel = new EmptyPanel();
			}
			currentPanel.start();
			contentPanel.add(currentPanel, BorderLayout.CENTER);
			contentPanel.updateUI();
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource() == configuratorTreeView) {
			changeConfigurator(e.getPath());
		}
	}
}
