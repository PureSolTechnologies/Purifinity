package com.puresol.gui.osgi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.puresol.filefilter.JARFilter;
import com.puresol.gui.Application;
import com.puresol.gui.Dialog;
import com.puresol.gui.FreeList;

/**
 * This class provides a simple BundleManager GUI for OSGi bundles.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BundleManager extends Dialog {

	private static final long serialVersionUID = -1851664339619930401L;

	private static final Translator translator = Translator
			.getTranslator(BundleManager.class);

	private final JButton installBundle = new JButton(
			translator.i18n("Install new plugin..."));
	private final JButton uninstallBundle = new JButton(
			translator.i18n("Uninstall plugin..."));
	private final JButton updateBundle = new JButton(translator.i18n("Update"));
	private final JButton startBundle = new JButton(translator.i18n("Start"));
	private final JButton stopBundle = new JButton(translator.i18n("Stop"));
	private final JButton updateList = new JButton(translator.i18n("Update"));

	private final BundleContext bundleContext;
	private FreeList bundles = null;

	public BundleManager(BundleContext context) {
		super(translator.i18n("Plugin Manager"), true);
		this.bundleContext = context;
		initUI();
		update();
		pack();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		JPanel actionPanel = new JPanel();
		GridLayout layout = new GridLayout(6, 1);
		actionPanel.setLayout(layout);
		actionPanel.add(updateList);
		actionPanel.add(installBundle);
		actionPanel.add(uninstallBundle);
		actionPanel.add(updateBundle);
		actionPanel.add(startBundle);
		actionPanel.add(stopBundle);

		updateList.addActionListener(this);
		installBundle.addActionListener(this);
		uninstallBundle.addActionListener(this);
		updateBundle.addActionListener(this);
		startBundle.addActionListener(this);
		stopBundle.addActionListener(this);

		add(actionPanel, BorderLayout.EAST);
		add(bundles = new FreeList(), BorderLayout.CENTER);
		add(getDefaultOKButton(), BorderLayout.SOUTH);
	}

	private void update() {
		bundles.removeAll();
		Map<Object, Object> listData = new HashMap<Object, Object>();
		for (Bundle bundle : bundleContext.getBundles()) {
			if (bundle.getBundleId() != 0) {
				// add new bundle as long it is not the OSGi base bundle with id
				// zero (Felix or Equinox itself)
				listData.put(getText(bundle), bundle);
			}
		}
		bundles.setListData(listData);
	}

	private String getText(Bundle bundle) {
		return OSGiUtils.getTextForState(bundle.getState()) + ": "
				+ bundle.getSymbolicName() + "(" + bundle.getBundleId() + ")";
	}

	private void installBundle() {
		try {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileDialog.setFileFilter(new JARFilter());
			if (fileDialog.showOpenDialog(Application.getInstance()) == JFileChooser.APPROVE_OPTION) {
				bundleContext.installBundle("file:"
						+ fileDialog.getSelectedFile().toString());
				update();
			}
		} catch (BundleException e) {
			Application.showStandardErrorMessage(
					"Selected bundle could not be uninstalled.", e);
		}
	}

	private void uninstallBundle() {
		try {
			Bundle bundle = (Bundle) bundles.getSelectedValue();
			if (bundle != null) {
				bundle.uninstall();
				update();
			}
		} catch (BundleException e) {
			Application.showStandardErrorMessage(
					"Selected bundle could not be uninstalled.", e);
		}
	}

	private void updateBundle() {
		try {
			Bundle bundle = (Bundle) bundles.getSelectedValue();
			if (bundle != null) {
				bundle.update();
				update();
			}
		} catch (BundleException e) {
			Application.showStandardErrorMessage(
					"Selected bundle could not be updated.", e);
		}
	}

	private void startBundle() {
		try {
			Bundle bundle = (Bundle) bundles.getSelectedValue();
			if (bundle != null) {
				bundle.start();
				update();
			}
		} catch (BundleException e) {
			Application.showStandardErrorMessage(
					"Selected bundle could not be started.", e);
		}
	}

	private void stopBundle() {
		try {
			Bundle bundle = (Bundle) bundles.getSelectedValue();
			if (bundle != null) {
				bundle.stop();
				update();
			}
		} catch (BundleException e) {
			Application.showStandardErrorMessage(
					"Selected bundle could not be stopped.", e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateList) {
			update();
		} else if (e.getSource() == installBundle) {
			installBundle();
		} else if (e.getSource() == uninstallBundle) {
			uninstallBundle();
		} else if (e.getSource() == updateBundle) {
			updateBundle();
		} else if (e.getSource() == startBundle) {
			startBundle();
		} else if (e.getSource() == stopBundle) {
			stopBundle();
		} else {
			super.actionPerformed(e);
		}
	}

}
