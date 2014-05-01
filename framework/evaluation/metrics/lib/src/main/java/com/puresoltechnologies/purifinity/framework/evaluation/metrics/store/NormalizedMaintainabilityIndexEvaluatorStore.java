package com.puresoltechnologies.purifinity.framework.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint.NormalizedMaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint.NormalizedMaintainabilityIndexFileResults;

public class NormalizedMaintainabilityIndexEvaluatorStore extends
		AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "normalized_maintainability_index_store";
	}

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
