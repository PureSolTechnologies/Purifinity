package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

public class MaintainabilityIndexEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return MaintainabilityIndexFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return MaintainabilityIndexDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return MaintainabilityIndexDirectoryResults.class;
	}
}
