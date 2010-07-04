package com.puresol.coding.evaluator;

import java.util.Date;

import javax.swingx.progress.ProgressObserver;

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
