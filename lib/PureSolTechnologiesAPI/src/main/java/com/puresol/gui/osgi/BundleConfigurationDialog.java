package com.puresol.gui.osgi;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.puresol.gui.Application;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.PureSolDialog;
import com.puresol.osgi.BundleConfigurator;

/**
 * This class provides a dialog for plug-in configuration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BundleConfigurationDialog extends PureSolDialog implements
	TreeSelectionListener {

    private static final long serialVersionUID = -359245614681727468L;

    private final String frameworkName;
    private final BundleConfiguratorTreeModel configuratorTreeView = new BundleConfiguratorTreeModel();
    private final BundleConfiguratorPanel configPanel = new BundleConfiguratorPanel();

    public BundleConfigurationDialog(String frameworkName) {
	super(Application.getInstance(), "Plugin Configurations Dialog", true);
	this.frameworkName = frameworkName;
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	initUI();
	initLocation();
    }

    private void initUI() {
	Container contentPane = getContentPane();

	configuratorTreeView.setConfiguratorTree(BundleConfiguratorTree
		.create(frameworkName));
	configuratorTreeView.setBorder(BorderFactory
		.createTitledBorder("Configurator"));
	configuratorTreeView.addTreeSelectionListener(this);

	contentPane.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		new JScrollPane(configuratorTreeView), configPanel),
		BorderLayout.CENTER);

	setButtonVisible(DialogButtons.CLOSE, true);
    }

    private void changeConfigurator(TreePath treePath) {
	final BundleConfiguratorTree tree = (BundleConfiguratorTree) treePath
		.getPathComponent(treePath.getPathCount() - 1);
	if (tree != null) {
	    BundleConfigurator configurator = tree.getConfigurator();
	    configPanel.setBundleConfigurator(configurator);
	}
    }

    @Override
    public void close() {
	JOptionPane
		.showMessageDialog(
			Application.getInstance(),
			"Closeing does not perform checks\nfor changed values and does not save them!",
			"Warning", JOptionPane.WARNING_MESSAGE);
	super.close();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
	if (e.getSource() == configuratorTreeView) {
	    changeConfigurator(e.getPath());
	}
    }
}
