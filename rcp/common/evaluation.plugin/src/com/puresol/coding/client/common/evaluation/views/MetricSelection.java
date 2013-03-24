package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class MetricSelection implements ISelection {

	private final EvaluatorFactory metric;

	public MetricSelection(EvaluatorFactory metric) {
		super();
		this.metric = metric;
	}

	public EvaluatorFactory getMetric() {
		return metric;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
