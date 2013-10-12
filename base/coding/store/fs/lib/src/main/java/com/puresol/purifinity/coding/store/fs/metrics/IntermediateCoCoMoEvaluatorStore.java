package com.puresol.purifinity.coding.store.fs.metrics;

import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
