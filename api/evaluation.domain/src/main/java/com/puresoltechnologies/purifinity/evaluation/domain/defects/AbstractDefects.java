package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.versioning.Version;

public class AbstractDefects extends AbstractEvaluatorResults implements Defects {

    private static final long serialVersionUID = 2852866090780167835L;

    public AbstractDefects(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
