package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractStyleIssues extends AbstractEvaluatorResults implements StyleIssues {

    private static final long serialVersionUID = -2248364357644192331L;

    public AbstractStyleIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
