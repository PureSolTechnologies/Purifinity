package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

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
