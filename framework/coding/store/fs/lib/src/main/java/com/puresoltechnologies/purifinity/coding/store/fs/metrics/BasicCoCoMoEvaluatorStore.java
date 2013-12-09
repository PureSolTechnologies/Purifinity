package com.puresoltechnologies.purifinity.coding.store.fs.metrics;

import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.coding.store.fs.evaluation.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

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
