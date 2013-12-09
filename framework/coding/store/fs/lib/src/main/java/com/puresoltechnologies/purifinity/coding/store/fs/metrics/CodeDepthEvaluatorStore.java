package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

public class CodeDepthEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return CodeDepthFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return CodeDepthDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return CodeDepthDirectoryResults.class;
	}
}
