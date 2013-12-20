package com.puresoltechnologies.purifinity.store.fs.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy.EntropyFileResults;
import com.puresoltechnologies.purifinity.store.fs.evaluation.AbstractEvaluatorStore;

public class EntropyEvaluatorStore extends AbstractEvaluatorStore {

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
