package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

public class HalsteadMetricEvaluatorStore extends AbstractEvaluatorStore {

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
