package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import com.puresoltechnologies.purifinity.evaluation.domain.EvaluationResults;

/**
 * This interface is used to transport issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Issues extends EvaluationResults {

    /**
     * This method returns the parameters which can be retrieved.
     * 
     * @return An array of {@link IssueParameter} is returned to describe the
     *         parameters available.
     */
    public IssueParameter[] getParameters();

}
