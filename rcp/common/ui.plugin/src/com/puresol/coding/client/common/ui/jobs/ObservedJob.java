package com.puresol.coding.client.common.ui.jobs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.client.common.ui.Activator;
import com.puresol.utils.progress.CallableProgressObservable;
import com.puresol.utils.progress.ProgressObserver;

public class ObservedJob<Observable, Return> extends Job implements
	ProgressObserver<Observable> {

    private static final ILog logger = Activator.getDefault().getLog();

    private final CallableProgressObservable<Observable, Return> observable;
    private IProgressMonitor monitor = null;

    public ObservedJob(String name,
	    CallableProgressObservable<Observable, Return> observable) {
	super(name);
	this.observable = observable;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	try {
	    this.monitor = monitor;

	    observable.addObservable(this);

	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    executor.submit(observable);
	    executor.shutdown();
	    executor.awaitTermination(1, TimeUnit.HOURS);
	    return Status.OK_STATUS;
	} catch (OperationCanceledException e) {
	    logger.log(new Status(Status.INFO, observable.getClass().getName(),
		    "Job was cancelled!", e));
	    return Status.CANCEL_STATUS;
	} catch (InterruptedException e) {
	    logger.log(new Status(Status.ERROR,
		    observable.getClass().getName(), "Job was interrupted!", e));
	    return Status.CANCEL_STATUS;
	}
    }

    @Override
    public void started(Observable observable, String message, long total) {
	monitor.beginTask(getName(), (int) total);
	monitor.subTask(message);
    }

    @Override
    public void done(Observable observable, String message, boolean successful) {
	monitor.subTask(message);
	monitor.setCanceled(successful);
	monitor.done();
    }

    @Override
    public void updateWork(Observable observable, String message, long finished) {
	monitor.subTask(message);
	monitor.worked((int) finished);
    }
}