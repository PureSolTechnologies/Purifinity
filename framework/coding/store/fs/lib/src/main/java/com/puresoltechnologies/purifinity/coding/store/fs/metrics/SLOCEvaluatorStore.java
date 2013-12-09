package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

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
