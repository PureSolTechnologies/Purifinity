package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Date;

import com.puresoltechnologies.versioning.Version;

/**
 * This class is an implementation for {@link ProjectIssues}.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class ProjectIssuesImpl extends AbstractIssues implements ProjectIssues {

    private final IssueParameter[] parameters;

    public ProjectIssuesImpl(String evaluatorId, Version evaluatorVersion, Date time, IssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.parameters = parameters;
	// TODO Auto-generated constructor stub
    }

    @Override
    public IssueParameter[] getParameters() {
	return parameters;
    }
}
