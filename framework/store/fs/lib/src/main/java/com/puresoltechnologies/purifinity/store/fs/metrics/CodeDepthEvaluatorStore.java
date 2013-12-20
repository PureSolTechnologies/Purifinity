package com.puresoltechnologies.purifinity.store.fs.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthFileResults;
import com.puresoltechnologies.purifinity.store.fs.evaluation.AbstractEvaluatorStore;

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
