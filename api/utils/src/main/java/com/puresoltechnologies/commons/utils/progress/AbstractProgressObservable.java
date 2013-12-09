package com.puresoltechnologies.commons.utils.progress;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements an abstract {@link ProgressObservable}. It can be used
 * by subclassing. The adding and removing of {@link ProgressObserver}s is
 * implemented as well as firing progress updates.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <Observable>
 */
public class AbstractProgressObservable<Observable> implements
	ProgressObservable<Observable> {

    private final List<WeakReference<ProgressObserver<Observable>>> observers = new ArrayList<WeakReference<ProgressObserver<Observable>>>();

    private boolean started = false;
    private long totalAmount;
    @SuppressWarnings("unchecked")
    private final Observable observable = (Observable) this;

    @Override
    public final void addObserver(ProgressObserver<Observable> observer) {
	if (started) {
	    observer.started(observable, "Work is already started.",
		    totalAmount);
	}
	observers
		.add(new WeakReference<ProgressObserver<Observable>>(observer));
    }

    @Override
    public final void removeObservable(ProgressObserver<Observable> observer) {
	Iterator<WeakReference<ProgressObserver<Observable>>> iterator = observers
		.iterator();
	while (iterator.hasNext()) {
	    WeakReference<ProgressObserver<Observable>> weakReference = iterator
		    .next();
	    ProgressObserver<Observable> listener = weakReference.get();
	    if ((listener == null) || (listener == observer)) {
		observers.remove(weakReference);
	    }
	}
    }

    protected void fireStarted(String message, int totalAmount) {
	started = true;
	this.totalAmount = totalAmount;
	Iterator<WeakReference<ProgressObserver<Observable>>> iterator = observers
		.iterator();
	while (iterator.hasNext()) {
	    WeakReference<ProgressObserver<Observable>> weakReference = iterator
		    .next();
	    ProgressObserver<Observable> observer = weakReference.get();
	    if (observer == null) {
		observers.remove(weakReference);
	    } else {
		observer.started(observable, message, totalAmount);
	    }
	}
    }

    protected void fireDone(String message, boolean successful) {
	started = false;
	Iterator<WeakReference<ProgressObserver<Observable>>> iterator = observers
		.iterator();
	while (iterator.hasNext()) {
	    WeakReference<ProgressObserver<Observable>> weakReference = iterator
		    .next();
	    ProgressObserver<Observable> observer = weakReference.get();
	    if (observer == null) {
		observers.remove(weakReference);
	    } else {
		observer.done(observable, message, successful);
	    }
	}
    }

    /**
     * This method is used to fire the
     * {@link ProgressObserver#updateProgress(Object, String, long, long)} for
     * all observers.
     * 
     * @param observable
     *            is the observable which fires the event.
     * @param message
     *            is the message to be broadcasted.
     * @param total
     *            is the total amount of work to be done.
     * @param finished
     *            is the finished amount since the last call of this method.
     */
    protected final void fireUpdateWork(String message, long finished) {
	Iterator<WeakReference<ProgressObserver<Observable>>> iterator = observers
		.iterator();
	while (iterator.hasNext()) {
	    WeakReference<ProgressObserver<Observable>> weakReference = iterator
		    .next();
	    ProgressObserver<Observable> observer = weakReference.get();
	    if (observer == null) {
		observers.remove(weakReference);
	    } else {
		observer.updateWork(observable, message, finished);
	    }
	}
    }
}
