package com.puresoltechnologies.purifinity.framework.store.db.metrics;

import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoFileResults;
import com.puresoltechnologies.purifinity.framework.store.db.evaluation.AbstractEvaluatorStore;

public class IntermediateCoCoMoEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	protected Class<? extends MetricFileResults> getFileResultClass() {
		return IntermediateCoCoMoFileResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getDirectoryResultClass() {
		return IntermediateCoCoMoDirectoryResults.class;
	}

	@Override
	protected Class<? extends MetricDirectoryResults> getProjectResultClass() {
		return IntermediateCoCoMoDirectoryResults.class;
	}
}
