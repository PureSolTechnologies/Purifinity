package com.puresoltechnologies.purifinity.framework.evaluation.metrics.store;

import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.AbstractEvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoFileResults;

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