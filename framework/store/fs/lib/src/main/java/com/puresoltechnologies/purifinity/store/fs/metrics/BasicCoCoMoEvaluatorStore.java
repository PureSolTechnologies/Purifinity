package com.puresoltechnologies.purifinity.store.fs.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.store.fs.evaluation.AbstractEvaluatorStore;

public class BasicCoCoMoEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return BasicCoCoMoFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return BasicCoCoMoDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return BasicCoCoMoDirectoryResults.class;
	}
}
