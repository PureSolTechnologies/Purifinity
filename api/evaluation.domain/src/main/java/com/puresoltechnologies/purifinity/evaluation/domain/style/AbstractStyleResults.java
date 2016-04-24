package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractStyleResults extends AbstractEvaluatorResults implements StyleResult {

    private static final long serialVersionUID = -2248364357644192331L;

    public AbstractStyleResults(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
