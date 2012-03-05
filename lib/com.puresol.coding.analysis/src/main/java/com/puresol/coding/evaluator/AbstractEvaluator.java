package com.puresol.coding.evaluator;

import java.util.Date;

/**
 * This abstract class is the base implementation for a single evaluator. The
 * evaluator keeps the information about the time stamp of the evaluation and
 * the observer which might be connected.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator extends Evaluator {

    private static final long serialVersionUID = 7451433916568644393L;

    private final Date timeStamp;

    public AbstractEvaluator(String name) {
	super(name);
	timeStamp = new Date();
    }

    @Override
    public Date getTimeStamp() {
	return timeStamp;
    }
}
