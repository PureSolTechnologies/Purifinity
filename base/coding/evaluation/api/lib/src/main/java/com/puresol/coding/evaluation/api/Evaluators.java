package com.puresol.coding.evaluation.api;

import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * This is a utilities class which provides functionality to access the metrics
 * services more easily.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class Evaluators implements Closeable {

    public static Evaluators createInstance() {
	ServiceLoader<Evaluators> loader = ServiceLoader.load(Evaluators.class);
	Iterator<Evaluators> iterator = loader.iterator();
	if (!iterator.hasNext()) {
	    throw new IllegalStateException("No implementation for '"
		    + Evaluators.class.getName() + "' found.");
	}
	Evaluators instance = iterator.next();
	if (iterator.hasNext()) {
	    throw new IllegalStateException("Too many implementations for '"
		    + Evaluators.class.getName() + "' found.");
	}
	return instance;
    }

    /**
     * This method returns all available evaluators.
     * 
     * @return A {@link List} of {@link EvaluatorFactory} is returned for all
     *         available evaluators.
     */
    public abstract List<EvaluatorFactory> getAll();

    /**
     * This method returns all available metrics.
     * 
     * @return A {@link List} of {@link EvaluatorFactory} is returned for all
     *         available metrics.
     */
    public abstract List<EvaluatorFactory> getAllMetrics();

    /**
     * This method returns all available evaluators which are not metrics.
     * 
     * @return A {@link List} of {@link EvaluatorFactory} is returned for all
     *         available evaluators which are not metrics.
     */
    public abstract List<EvaluatorFactory> getAllNonMetrics();

}
