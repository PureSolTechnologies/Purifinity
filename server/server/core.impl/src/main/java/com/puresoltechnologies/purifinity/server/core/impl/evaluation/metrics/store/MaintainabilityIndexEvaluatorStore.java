package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.store.AbstractEvaluatorStore;

public class MaintainabilityIndexEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "maintainability_index_store";
	}

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
