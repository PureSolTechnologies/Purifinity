package com.puresoltechnologies.purifinity.client.storage.db;

import java.io.IOException;

import org.apache.cassandra.server.CassandraServer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;

import com.puresoltechnologies.purifinity.client.common.ui.parts.DatabaseTarget;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;

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
		startCassandra();
	}

	private void startCassandra() throws InterruptedException {
		setDBUIEnabled(false);
		Job job = new Job("Database startup") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Cassandra", 2);
					monitor.subTask("Start process");
					CassandraServer.start();
					monitor.worked(1);
					monitor.subTask("Wait for availability");
					CassandraServer.waitForStartup(15000);
					monitor.worked(1);
					monitor.done();
					return Status.OK_STATUS;
				} catch (IOException e) {
					monitor.done();
					return new Status(Status.ERROR, getBundle()
							.getSymbolicName(),
							"Could not start Cassandra database.", e);
				}
			}
		};
		job.schedule();
		job.join();
		IStatus result = job.getResult();
		if (result == Status.OK_STATUS) {
			setDBUIEnabled(true);
		} else {
			throw new RuntimeException(result.getMessage(),
					result.getException());
		}
	}

	private void setDBUIEnabled(final boolean enabled) {
		new UIJob("") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				for (IWorkbenchWindow workbenchWindow : PlatformUI
						.getWorkbench().getWorkbenchWindows()) {
					for (IWorkbenchPage workbenchPage : workbenchWindow
							.getPages()) {
						for (IViewReference viewReference : workbenchPage
								.getViewReferences()) {
							IWorkbenchPart part = viewReference.getPart(true);
							if (DatabaseTarget.class
									.isAssignableFrom(part.getClass())) {
								DatabaseTarget databaseUserInterface = (DatabaseTarget) part;
								databaseUserInterface
										.setDatabaseAvailable(enabled);
							}
						}
					}
				}
				return Status.OK_STATUS;
			}
		}.schedule();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		TitanConnection.disconnect();
		CassandraConnection.disconnect();
		CassandraServer.stop();
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

}
