package com.puresoltechnologies.purifinity.framework.store.db.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.store.db.evaluation.AbstractEvaluatorStore;

public class SLOCEvaluatorStore extends AbstractEvaluatorStore {

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
