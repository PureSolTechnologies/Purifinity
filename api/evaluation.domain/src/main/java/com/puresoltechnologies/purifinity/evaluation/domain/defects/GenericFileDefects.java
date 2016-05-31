package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericCodeRangeDesignIssues;
import com.puresoltechnologies.versioning.Version;

public class GenericFileDefects extends AbstractDefects implements FileDefects {

    public GenericFileDefects(String evaluatorId, Version evaluatorVersion, HashId hashId,
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
    public List<GenericCodeRangeDefects> getCodeRangeDefects() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addCodeRangeDesignIssue(GenericCodeRangeDesignIssues genericCodeRangeDesignIssues) {
	// TODO Auto-generated method stub

    }

}
