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

    public AbstractIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
