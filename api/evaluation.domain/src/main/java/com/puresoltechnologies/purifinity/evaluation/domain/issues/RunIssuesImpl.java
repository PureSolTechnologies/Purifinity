package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Date;

import com.puresoltechnologies.versioning.Version;

public class RunIssuesImpl extends AbstractIssues implements RunIssues {

    public RunIssuesImpl(String evaluatorId, Version evaluatorVersion, Date time, IssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time, parameters);
	// TODO Auto-generated constructor stub
    }

}
