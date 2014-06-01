package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.store.AbstractEvaluatorStore;

public class SLOCEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "sloc_metric_store";
	}

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return SLOCFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return SLOCDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return SLOCDirectoryResults.class;
	}
}