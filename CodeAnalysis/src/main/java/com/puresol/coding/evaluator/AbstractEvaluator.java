package com.puresol.coding.evaluator;

import java.util.Date;

import com.puresol.gui.progress.ProgressObserver;

/**
 * This abstract class is the base implementation for a single evaluator. The
 * evaluator keeps the information about the time stamp of the evaluation and
 * the observer which might be connected.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator implements Evaluator {

	private static final long serialVersionUID = 7451433916568644393L;

	transient private ProgressObserver observer = null;
	private final Date timeStamp;

	public AbstractEvaluator() {
		timeStamp = new Date();
	}

	@Override
	public final void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	public final ProgressObserver getMonitor() {
		return observer;
	}

	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}
}
