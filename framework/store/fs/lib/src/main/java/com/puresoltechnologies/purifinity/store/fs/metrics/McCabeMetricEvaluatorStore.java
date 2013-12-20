package com.puresoltechnologies.purifinity.store.fs.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricFileResults;
import com.puresoltechnologies.purifinity.store.fs.evaluation.AbstractEvaluatorStore;

public class McCabeMetricEvaluatorStore extends AbstractEvaluatorStore {

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
