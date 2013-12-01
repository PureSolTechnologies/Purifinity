package com.puresoltechnologies.purifinity.coding.evaluation.api;

import java.util.Iterator;
import java.util.ServiceLoader;


/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class EvaluatorStoreFactory {

	private static EvaluatorStoreFactory instance;

	public static EvaluatorStoreFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<EvaluatorStoreFactory> serviceLoader = ServiceLoader
					.load(EvaluatorStoreFactory.class);
			Iterator<EvaluatorStoreFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ EvaluatorStoreFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ EvaluatorStoreFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract EvaluatorStore createInstance(
			Class<? extends Evaluator> clazz);

}
