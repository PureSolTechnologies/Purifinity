package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.CodeRangeDesignIssues;
import com.puresoltechnologies.versioning.Version;

public class FileDefectsImpl extends AbstractDefects implements FileDefects {

    public FileDefectsImpl(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, DesignIssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	// TODO Auto-generated constructor stub
    }

    @Override
    public DefectParameter[] getParameters() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<CodeRangeDefects> getCodeRangeDefects() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addCodeRangeDesignIssue(CodeRangeDesignIssues genericCodeRangeDesignIssues) {
	// TODO Auto-generated method stub

    }

}
