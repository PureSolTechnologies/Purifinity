package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class DirectoryStyleIssuesImpl extends AbstractStyleIssues implements DirectoryStyleIssues {

    public DirectoryStyleIssuesImpl(String evaluatorId, Version evaluatorVersion, Date time) {
	super(evaluatorId, evaluatorVersion, time);
	// TODO Auto-generated constructor stub
    }

    @Override
    public HashId getHashId() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<CodeRangeStyleIssues> getCodeRangeStyleResults() {
	// TODO Auto-generated method stub
	return null;
    }

}
