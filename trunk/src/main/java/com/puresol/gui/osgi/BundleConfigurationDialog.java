package com.puresol.gui.osgi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.i18n4java.Translator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.puresol.gui.Application;
import com.puresol.osgi.BundleConfigurator;

/**
 * This class provides a dialog for plug-in configuration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BundleConfigurationDialog extends JDialog implements
		TreeSelectionListener, ActionListener {

	private static final long serialVersionUID = -359245614681727468L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfigurationDialog.class);

	private final String frameworkName;
	private final BundleConfiguratorTreeModel configuratorTreeView = new BundleConfiguratorTreeModel();
	private final BundleConfiguratorPanel configPanel = new BundleConfiguratorPanel();
	private final JButton closeButton = new JButton(translator.i18n("Close"));

	public BundleConfigurationDialog(String frameworkName) {
		super(Application.getInstance(), translator
				.i18n("Plugin Configurations Dialog"), true);
		this.frameworkName = frameworkName;
		initUI();
	}

	private void initUI() {
		JPanel contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());

		configuratorTreeView.setConfiguratorTree(BundleConfiguratorTree
				.create(frameworkName));
		configuratorTreeView.setBorder(BorderFactory
				.createTitledBorder(translator.i18n("Configurator")));
		configuratorTreeView.addTreeSelectionListener(this);

		contentPanel.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(configuratorTreeView), configPanel),
				BorderLayout.CENTER);

		closeButton.addActionListener(this);
		contentPanel.add(closeButton, BorderLayout.SOUTH);
		pack();
	}

	private void changeConfigurator(TreePath treePath) {
		final BundleConfiguratorTree tree = (BundleConfiguratorTree) treePath
				.getPathComponent(treePath.getPathCount() - 1);
		if (tree != null) {
			BundleConfigurator configurator = tree.getConfigurator();
			configPanel.setBundleConfigurator(configurator);
		}
	}

	private void close() {
		JOptionPane
				.showMessageDialog(
						Application.getInstance(),
						translator
								.i18n("Closeing does not perform checks\nfor changed values and does not save them!"),
						translator.i18n("Warning"), JOptionPane.WARNING_MESSAGE);
		dispose();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource() == configuratorTreeView) {
			changeConfigurator(e.getPath());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeButton) {
			close();
		}
	}
}
