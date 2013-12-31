package com.puresoltechnologies.purifinity.client.common.evaluation.utils;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;

public interface EvaluationsTarget {

	public void showEvaluation(AnalysisFileTree path)
			throws EvaluationStoreException;

}
