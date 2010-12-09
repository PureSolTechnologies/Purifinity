package com.puresol.gui.osgi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.i18n4java.Translator;
import javax.swing.JFileChooser;
import javax.swingx.Application;
import javax.swingx.Button;
import javax.swingx.Dialog;
import javax.swingx.FreeList;
import javax.swingx.Panel;
import javax.swingx.connect.Slot;
import javax.swingx.filefilter.JARFilter;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

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

	private final BundleContext bundleContext;
	private FreeList bundles = null;
	private Button installBundle = null;
	private Button uninstallBundle = null;
	private Button updateBundle = null;
	private Button startBundle = null;
	private Button stopBundle = null;
	private Button updateList = null;

	public BundleManager(BundleContext context) {
		super(translator.i18n("Plugin Manager"), true);
		this.bundleContext = context;
		initUI();
		update();
		pack();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		Panel actionPanel = new Panel();
		GridLayout layout = new GridLayout(6, 1);
		actionPanel.setLayout(layout);
		actionPanel.add(updateList = new Button(translator.i18n("Update")));
		actionPanel.add(installBundle = new Button(translator
				.i18n("Install new plugin...")));
		actionPanel.add(uninstallBundle = new Button(translator
				.i18n("Uninstall plugin...")));
		actionPanel.add(updateBundle = new Button(translator.i18n("Update")));
		actionPanel.add(startBundle = new Button(translator.i18n("Start")));
		actionPanel.add(stopBundle = new Button(translator.i18n("Stop")));

		updateList.connect("start", this, "update");
		installBundle.connect("start", this, "installBundle");
		uninstallBundle.connect("start", this, "uninstallBundle");
		updateBundle.connect("start", this, "updateBundle");
		startBundle.connect("start", this, "startBundle");
		stopBundle.connect("start", this, "stopBundle");

		add(actionPanel, BorderLayout.EAST);
		add(bundles = new FreeList(), BorderLayout.CENTER);
		add(getDefaultOKButton(), BorderLayout.SOUTH);
	}

	@Slot
	public void update() {
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

	@Slot
	public void installBundle() {
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

	@Slot
	public void uninstallBundle() {
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

	@Slot
	public void updateBundle() {
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

	@Slot
	public void startBundle() {
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

	@Slot
	public void stopBundle() {
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
}
