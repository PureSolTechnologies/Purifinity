package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
