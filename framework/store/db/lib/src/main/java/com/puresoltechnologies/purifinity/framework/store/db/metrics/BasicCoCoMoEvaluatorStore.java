package com.puresoltechnologies.purifinity.framework.store.db.metrics;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.framework.store.db.evaluation.AbstractEvaluatorStore;

public class BasicCoCoMoEvaluatorStore extends AbstractEvaluatorStore {

	@Override
	public String getStoreName() {
		return "basic_cocomo_store";
	}

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
