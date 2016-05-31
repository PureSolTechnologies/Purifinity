package com.puresoltechnologies.purifinity.evaluation.domain.style;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.CodeRangeDesignIssues;
import com.puresoltechnologies.versioning.Version;

public class FileStyleIssuesImpl extends AbstractStyleIssues implements FileStyleIssues {

    private static final long serialVersionUID = -2265405120665928206L;

    private final Set<StyleParameter> parameters = new LinkedHashSet<>();
    private final List<CodeRangeStyleIssues> codeRangeMetrics = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public FileStyleIssuesImpl(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, DesignIssueParameter[] parameters2) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
	this.parameters.addAll(parameters);
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    @Override
    public List<CodeRangeStyleIssues> getCodeRangeStyleResults() {
	// TODO Auto-generated method stub
	return null;
    }

    public void addCodeRangeDesignIssue(CodeRangeDesignIssues genericCodeRangeDesignIssues) {
	// TODO Auto-generated method stub

    }

    @Override
    public StyleIssueParameter[] getParameters() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<CodeRangeStyleIssues> getCodeRangeStyleIssues() {
	// TODO Auto-generated method stub
	return null;
    }

}
