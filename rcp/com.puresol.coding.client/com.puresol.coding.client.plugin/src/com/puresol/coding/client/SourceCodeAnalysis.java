package com.puresol.coding.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.client.dialogs.PickWorkspaceDialog;

/**
 * This class controls all aspects of the application's execution
 */
public class SourceCodeAnalysis implements IApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(SourceCodeAnalysis.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {
		logger.info("Starting up SourceCodeAnalysis client...");
		if (!changeWorkspace()) {
			return IApplication.EXIT_OK;
		}
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

	private boolean changeWorkspace() throws IOException, MalformedURLException {
		Location location = Platform.getInstanceLocation();
		String savedLocation = PickWorkspaceDialog.getWorkspaceLocation();
		if (savedLocation == null) {
			PickWorkspaceDialog dialog = new PickWorkspaceDialog(false);
			if (dialog.open() == PickWorkspaceDialog.CANCEL) {
				return false;
			}
			savedLocation = dialog.getLocation();
		}
		location.set(new URL("file", null, savedLocation), true);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
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
