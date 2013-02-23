package com.puresol.coding.evaluation.api;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * This is a utilities class which provides functionality to access the metrics
 * services more easily.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class Evaluators {

    private static Evaluators instance = null;

    public static Evaluators getInstance() {
	if (instance == null) {
	    loadInstance();
	}
	return instance;
    }

    private static synchronized void loadInstance() {
	ServiceLoader<Evaluators> loader = ServiceLoader.load(Evaluators.class);
	Iterator<Evaluators> iterator = loader.iterator();
	if (!iterator.hasNext()) {
	    throw new IllegalStateException("No implementation for '"
		    + Evaluators.class.getName() + "' found.");
	}
	instance = iterator.next();
	if (iterator.hasNext()) {
	    throw new IllegalStateException("Too many implementations for '"
		    + Evaluators.class.getName() + "' found.");
	}
    }

    /**
     * This method looks into the bundle context and returns all available
     * programming languages.
     * 
     * @return
     */
    public abstract List<EvaluatorFactory> getAll();

}
