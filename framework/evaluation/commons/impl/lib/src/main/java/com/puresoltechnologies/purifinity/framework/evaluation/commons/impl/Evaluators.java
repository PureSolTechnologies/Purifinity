package com.puresoltechnologies.purifinity.framework.evaluation.commons.impl;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	 * This method returns all available evaluators in arbitrary order.
	 * 
	 * @return A {@link List} of {@link EvaluatorFactory} is returned for all
	 *         available evaluators.
	 */
	public abstract List<EvaluatorFactory> getAll();

	/**
	 * This method returns all available evaluators in sorted order. The sorting
	 * is determined by the dependencies of the evaluators to each other.
	 * 
	 * @return An ordered {@link List} of {@link EvaluatorFactory} is returned
	 *         for all available evaluators.
	 */
	public List<EvaluatorFactory> getAllSortedByDependency() {
		List<EvaluatorFactory> unsorted = getAll();
		List<EvaluatorFactory> sorted = new ArrayList<EvaluatorFactory>();
		for (EvaluatorFactory factory : unsorted) {
			sorted.add(factory);
		}
		Collections.sort(sorted, new Comparator<EvaluatorFactory>() {

			@Override
			public int compare(EvaluatorFactory o1, EvaluatorFactory o2) {
				if (o1.getClass().equals(o2.getClass())) {
					return 0;
				}
				boolean o1DependsOnO2 = o1.getDependencies().contains(
						o2.getClass());
				boolean o2DependsOnO1 = o2.getDependencies().contains(
						o1.getClass());
				if (o1DependsOnO2 && o2DependsOnO1) {
					throw new RuntimeException(
							"Two different EvaluationFactory objects cannot depend on each other.");
				}
				if (o1DependsOnO2) {
					return -1;
				}
				if (o2DependsOnO1) {
					return 1;
				}
				return 0;
			}
		});
		return sorted;
	}

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
