package com.puresol.coding.client.common.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (plugin != null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was already started!");
		}
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		if (plugin == null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was never started!");
		}
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		if (plugin == null) {
			throw new RuntimeException("A " + Activator.class.getName()
					+ " plugin was never started!");
		}
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		add(reg, ClientImages.DATABASE_REFRESH_16x16);

		add(reg, ClientImages.FOLDER_16x16);
		add(reg, ClientImages.FOLDERS_16x16);
		add(reg, ClientImages.FOLDERS_EXPLORER_16x16);
		add(reg, ClientImages.FOLDER_ADD_16x16);
		add(reg, ClientImages.FOLDER_EDIT_16x16);
		add(reg, ClientImages.FOLDER_DELETE_16x16);
		add(reg, ClientImages.DATABASE_FOLDER_16x16);
		add(reg, ClientImages.DOCUMENT_EMPTY_16x16);

		add(reg, ClientImages.ANALYSIS_16x16);
		add(reg, ClientImages.ANALYZES_VIEW_16x16);
		add(reg, ClientImages.ANALYSIS_ADD_16x16);
		add(reg, ClientImages.ANALYSIS_EDIT_16x16);
		add(reg, ClientImages.ANALYSIS_DELETE_16x16);

		add(reg, ClientImages.ANALYSIS_RUN_16x16);
		add(reg, ClientImages.ANALYSIS_RUNS_VIEW_16x16);
		add(reg, ClientImages.ANALYSIS_RUN_ADD_16x16);
		add(reg, ClientImages.ANALYSIS_RUN_EDIT_16x16);
		add(reg, ClientImages.ANALYSIS_RUN_DELETE_16x16);
	}

	private void add(ImageRegistry reg, String keyAndPath) {
		reg.put(keyAndPath, ImageDescriptor.createFromURL(Activator.class
				.getResource(keyAndPath)));
	}

}
