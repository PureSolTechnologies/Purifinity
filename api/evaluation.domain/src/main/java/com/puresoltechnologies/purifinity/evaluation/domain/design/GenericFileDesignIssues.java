package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.versioning.Version;

public class GenericFileDesignIssues extends AbstractDesignIssues implements FileDesignIssues {

    private static final long serialVersionUID = -789632724692925800L;

    private final DesignIssueParameter[] parameters;
    private final List<GenericCodeRangeMetrics> codeRangeMetrics = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public GenericFileDesignIssues(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, DesignIssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.parameters = parameters;
	this.sourceCodeLocation = sourceCodeLocation;
    }

    public DesignIssueParameter[] getParameters() {
	return parameters;
    }

    public List<GenericCodeRangeMetrics> getCodeRangeMetrics() {
	return codeRangeMetrics;
    }

    public HashId getHashId() {
	return hashId;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public List<GenericCodeRangeDesignIssues> getCodeRangeDesginIssues() {
	// TODO Auto-generated method stub
	return null;
    }
}
