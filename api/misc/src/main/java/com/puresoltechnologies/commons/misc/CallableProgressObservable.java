package com.puresoltechnologies.commons.misc;

import java.util.concurrent.Callable;

/**
 * This is a combined interface of {@link Callable} and
 * {@link ProgressObservable}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <Observable>
 *            is the class of the actual observable. This is needed for the
 *            {@link ProgressObserver}.
 * @param <Return>
 *            is the return value of the call method from interface
 *            {@link Callable}.
 */
public interface CallableProgressObservable<Observable, Return> extends
		Callable<Return>, ProgressObservable<Observable> {
}
