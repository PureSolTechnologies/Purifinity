package com.puresoltechnologies.purifinity.framework.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricFileResults;

public class HalsteadMetricEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "halstead_metric_store";
	}

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return HalsteadMetricFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return HalsteadMetricDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return HalsteadMetricDirectoryResults.class;
	}
}
