package com.puresoltechnologies.purifinity.evaluation.domain.architecture;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.versioning.Version;

public class FileArchitectureIssuesImpl extends AbstractArchitectureIssues implements FileArchitectureIssues {

    public FileArchitectureIssuesImpl(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, DesignIssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
    }

    @Override
    public ArchitectureIssueParameter[] getParameters() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<CodeRangeArchitectureIssues> getCodeRangeDesignIssues() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addCodeRangeDesignIssue(CodeRangeArchitectureIssues genericCodeRangeArchitectureIssues) {
	// TODO Auto-generated method stub

    }

}
