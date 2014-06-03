package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorStore;

public class McCabeMetricEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "mccabe_metric_store";
	}

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return McCabeMetricFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return McCabeMetricDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return McCabeMetricDirectoryResults.class;
	}
}
