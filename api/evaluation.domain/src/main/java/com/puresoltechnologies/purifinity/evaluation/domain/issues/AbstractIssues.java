package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

/**
 * This class is designed to contain issues after an evaluator run.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public abstract class AbstractIssues extends AbstractEvaluatorResults implements Issues {

    private static final long serialVersionUID = 8154379867909602950L;

    private final IssueParameter[] parameters;

    public AbstractIssues(String evaluatorId, Version evaluatorVersion, Date time, IssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.parameters = parameters;
    }

    @Override
    public final IssueParameter[] getParameters() {
	return parameters;
    }

}
