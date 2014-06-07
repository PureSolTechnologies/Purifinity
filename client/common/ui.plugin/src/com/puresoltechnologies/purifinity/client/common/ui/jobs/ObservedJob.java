package com.puresoltechnologies.purifinity.client.common.ui.jobs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresoltechnologies.purifinity.client.common.ui.Activator;

public class ObservedJob<Return> extends Job {

	private static final ILog logger = Activator.getDefault().getLog();

	private final Callable<Return> callable;
	private Future<Return> future;

	public ObservedJob(String name, Callable<Return> callable) {
		super(name);
		this.callable = callable;
	}

	public Return getRunResult() throws InterruptedException {
		try {
			if (future.isDone()) {
				if (future.isCancelled()) {
					throw new InterruptedException("Job was interrupted.");
				}
				return future.get();
			}
			return null;
		} catch (ExecutionException e) {
			throw new RuntimeException("Job was aborted with an exception.", e);
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			future = executor.submit(callable);
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.HOURS);
			return Status.OK_STATUS;
		} catch (OperationCanceledException e) {
			logger.log(new Status(Status.INFO, callable.getClass().getName(),
					"Job was cancelled!", e));
			return Status.CANCEL_STATUS;
		} catch (InterruptedException e) {
			logger.log(new Status(Status.ERROR, callable.getClass().getName(),
					"Job was interrupted!", e));
			return Status.CANCEL_STATUS;
		}
	}

	@Override
	protected void canceling() {
		super.canceling();
		if (future != null) {
			future.cancel(true);
		}
	}

}