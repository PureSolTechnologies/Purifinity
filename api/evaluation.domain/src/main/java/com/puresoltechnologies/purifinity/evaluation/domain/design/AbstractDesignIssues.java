package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public class AbstractDesignIssues extends AbstractEvaluatorResults implements DesignIssues {

    private static final long serialVersionUID = 8154379867909602950L;

    public AbstractDesignIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
