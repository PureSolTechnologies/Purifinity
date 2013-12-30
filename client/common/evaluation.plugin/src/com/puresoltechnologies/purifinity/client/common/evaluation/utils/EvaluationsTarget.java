package com.puresoltechnologies.purifinity.client.common.evaluation.utils;

import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;

public interface EvaluationsTarget {

	public void showEvaluation(HashIdFileTree path)
			throws EvaluationStoreException;

}
