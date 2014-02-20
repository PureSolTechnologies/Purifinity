package com.puresoltechnologies.purifinity.client.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.puresoltechnologies.purifinity.client.application.dialogs.PickWorkspaceDialog;

/**
 * This class controls all aspects of the application's execution
 */
public class Purifinity implements IApplication {

	private static final String LOGBACK_CONFIGURATION_FILE_PROPERTY_NAME = "logback.configurationFile";

	@Override
	public Object start(IApplicationContext context) throws Exception {
		if (!changeWorkspace()) {
			return IApplication.EXIT_OK;
		}
		initializeLogbackConfiguration();
		Display display = PlatformUI.createDisplay();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	/**
	 * This method checks for the presence of a set property
	 * {@value #LOGBACK_CONFIGURATION_FILE_PROPERTY_NAME} and creates a new
	 * configuration file if it is not present.
	 */
	private void initializeLogbackConfiguration() {
		String configFileProperty = System
				.getProperty(LOGBACK_CONFIGURATION_FILE_PROPERTY_NAME);
		if (configFileProperty == null) {
			System.err.println("Warning: Property '"
					+ LOGBACK_CONFIGURATION_FILE_PROPERTY_NAME
					+ "' is not set.");
			return;
		}
		File configFile = new File(configFileProperty);
		if (configFile.exists()) {
			return;
		}
		try {
			if (!configFile.createNewFile()) {
				System.err.println("Logging configuration file '" + configFile
						+ "' cannot be created.");
				return;
			}
			try (InputStream inputStream = getClass().getResourceAsStream(
					"/logback.xml")) {
				try (FileOutputStream fileWriter = new FileOutputStream(
						configFile)) {
					byte[] buffer = new byte[256];
					int len;
					while ((len = inputStream.read(buffer)) >= 0) {
						fileWriter.write(buffer, 0, len);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Logging configuration file '" + configFile
					+ "' cannot be created.");
			e.printStackTrace(System.err);
		}
	}

	private boolean changeWorkspace() throws IOException, MalformedURLException {
		boolean needNewLocation = false;
		String purifinityUITestProperty = System
				.getProperty("purifinity.ui.test");
		if ((purifinityUITestProperty != null)
				&& (purifinityUITestProperty.equals("true"))) {
			return false;
		}
		Location location = Platform.getInstanceLocation();
		String savedLocation = PickWorkspaceDialog.getWorkspaceLocation();
		if (savedLocation == null) {
			needNewLocation = true;
		} else {
			File directory = new File(savedLocation);
			if ((!directory.exists()) || (!directory.isDirectory())) {
				needNewLocation = true;
			}
		}
		if (needNewLocation) {
			PickWorkspaceDialog dialog = new PickWorkspaceDialog(false);
			if (dialog.open() == PickWorkspaceDialog.CANCEL) {
				return false;
			}
			savedLocation = dialog.getLocation();
		}
		location.set(new URL("file", null, savedLocation), true);
		return true;
	}

	@Override
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
