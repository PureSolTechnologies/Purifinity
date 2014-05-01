package com.puresoltechnologies.purifinity.framework.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyFileResults;

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
