package com.puresoltechnologies.purifinity.framework.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthFileResults;

public class CodeDepthEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "code_depth_metric_store";
	}

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