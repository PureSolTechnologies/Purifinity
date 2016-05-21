package com.puresoltechnologies.purifinity.evaluation.domain.architecture;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public class AbstractArchitectureIssues extends AbstractEvaluatorResults implements ArchitectureIssues {

    private static final long serialVersionUID = 2189759842769011902L;

    public AbstractArchitectureIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
