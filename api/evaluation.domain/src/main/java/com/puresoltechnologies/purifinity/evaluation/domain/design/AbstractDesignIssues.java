package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.Date;

import com.puresoltechnologies.versioning.Version;

public class AbstractDesignIssues implements DesignIssues {

    private static final long serialVersionUID = 7453265096698289697L;

    private final String evaluatorId;
    private final Version evaluatorVersion;
    private final Date time;

    public AbstractDesignIssues(String evaluatorId, Version evaluatorVersion, Date time) {
	this.evaluatorId = evaluatorId;
	this.evaluatorVersion = evaluatorVersion;
	this.time = time;
    }

    @Override
    public String getEvaluatorId() {
	return evaluatorId;
    }

    @Override
    public Version getEvaluatorVersion() {
	return evaluatorVersion;
    }

    @Override
    public Date getTime() {
	return time;
    }

}
