package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class GenericProjectStyleIssues extends AbstractStyleIssues implements ProjectStyleIssues {

    public GenericProjectStyleIssues(String evaluatorId, Version evaluatorVersion, Date time) {
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
    public List<GenericCodeRangeStyleIssues> getCodeRangeStyleResults() {
	// TODO Auto-generated method stub
	return null;
    }

}
