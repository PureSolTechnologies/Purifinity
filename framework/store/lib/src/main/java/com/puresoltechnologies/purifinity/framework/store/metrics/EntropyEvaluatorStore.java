package com.puresoltechnologies.purifinity.framework.store.metrics;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyFileResults;
import com.puresoltechnologies.purifinity.framework.store.evaluation.AbstractEvaluatorStore;

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
