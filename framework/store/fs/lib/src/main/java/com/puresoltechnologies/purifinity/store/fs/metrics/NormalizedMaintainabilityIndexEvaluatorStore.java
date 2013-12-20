package com.puresoltechnologies.purifinity.store.fs.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint.NormalizedMaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.store.fs.evaluation.AbstractEvaluatorStore;

public class NormalizedMaintainabilityIndexEvaluatorStore extends
		AbstractEvaluatorStore {

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return NormalizedMaintainabilityIndexFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return NormalizedMaintainabilityIndexDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return NormalizedMaintainabilityIndexDirectoryResults.class;
	}
}
