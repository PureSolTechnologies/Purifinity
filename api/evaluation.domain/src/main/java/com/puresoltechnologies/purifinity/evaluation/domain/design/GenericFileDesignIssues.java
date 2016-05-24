package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class GenericFileDesignIssues extends AbstractDesignIssues implements FileDesignIssues {

    private static final long serialVersionUID = -789632724692925800L;

    private final DesignIssueParameter[] parameters;
    private final List<GenericCodeRangeDesignIssues> codeRangeDesignIssues = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public GenericFileDesignIssues(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, DesignIssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.parameters = parameters;
	this.sourceCodeLocation = sourceCodeLocation;
    }

    @JsonCreator
    public GenericFileDesignIssues(@JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion, @JsonProperty("hashId") HashId hashId,
	    @JsonProperty("sourceCodeLocation") SourceCodeLocation sourceCodeLocation, @JsonProperty("time") Date time,
	    @JsonProperty("parameters") DesignIssueParameter[] parameters,
	    @JsonProperty("codeRangeDesignIssues") List<GenericCodeRangeDesignIssues> codeRangeDesignIssues) {
	this(evaluatorId, evaluatorVersion, hashId, sourceCodeLocation, time, parameters);
	this.codeRangeDesignIssues.addAll(codeRangeDesignIssues);
    }

    @Override
    public DesignIssueParameter[] getParameters() {
	return parameters;
    }

    public void addCodeRangeDesignIssue(GenericCodeRangeDesignIssues issues) {
	codeRangeDesignIssues.add(issues);
    }

    @Override
    public List<GenericCodeRangeDesignIssues> getCodeRangeDesignIssues() {
	return codeRangeDesignIssues;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }
}
