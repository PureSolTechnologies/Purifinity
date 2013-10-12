package com.puresol.purifinity.coding.store.fs.metrics;

import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresol.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;

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
