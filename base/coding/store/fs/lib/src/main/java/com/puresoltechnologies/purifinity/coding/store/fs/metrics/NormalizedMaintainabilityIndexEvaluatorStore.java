package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
