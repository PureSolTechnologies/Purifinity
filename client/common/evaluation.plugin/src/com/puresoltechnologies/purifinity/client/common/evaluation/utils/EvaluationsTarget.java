package com.puresoltechnologies.purifinity.client.common.evaluation.utils;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;

/**
 * This interface is used to mark a class as evaluations part.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluationsTarget {

	/**
	 * This method is used to force a part to show a new evaluation for another
	 * part of the {@link AnalysisFileTree}.
	 * 
	 * @param node
	 *            is the new {@link AnalysisFileTree} root node to be shown in
	 *            the UI.
	 * @throws EvaluationStoreException
	 *             is thrown in case of a store issue.
	 */
	public void showEvaluation(AnalysisFileTree node)
			throws EvaluationStoreException;

}
