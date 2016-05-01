package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.Date;

import com.puresoltechnologies.versioning.Version;

public class GenericFileDesignIssues extends AbstractDesignIssues implements FileDesignIssues {

    public GenericFileDesignIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
    }

}
