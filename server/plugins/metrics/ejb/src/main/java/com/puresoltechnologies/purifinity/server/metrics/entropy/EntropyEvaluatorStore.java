package com.puresoltechnologies.purifinity.server.metrics.entropy;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorStore;

public class EntropyEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "entropy_metric_store";
	}

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return EntropyFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return EntropyDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return EntropyDirectoryResults.class;
	}
}
