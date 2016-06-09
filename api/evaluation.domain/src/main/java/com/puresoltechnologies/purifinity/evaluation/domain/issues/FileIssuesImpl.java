package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

/**
 * This class is an implementation of {@link FileIssues}.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class FileIssuesImpl extends AbstractIssues implements FileIssues {

    private static final long serialVersionUID = -789632724692925800L;

    private final List<CodeRangeIssues> codeRangeDesignIssues = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public FileIssuesImpl(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time, IssueParameter[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
    }

    @JsonCreator
    public FileIssuesImpl(@JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion, @JsonProperty("hashId") HashId hashId,
	    @JsonProperty("sourceCodeLocation") SourceCodeLocation sourceCodeLocation, @JsonProperty("time") Date time,
	    @JsonProperty("parameters") IssueParameter[] parameters,
	    @JsonProperty("codeRangeDesignIssues") List<CodeRangeIssues> codeRangeDesignIssues) {
	this(evaluatorId, evaluatorVersion, hashId, sourceCodeLocation, time, parameters);
	this.codeRangeDesignIssues.addAll(codeRangeDesignIssues);
    }

    public void addCodeRangeDesignIssue(CodeRangeIssues issues) {
	codeRangeDesignIssues.add(issues);
    }

    @Override
    public List<CodeRangeIssues> getCodeRangeIssues() {
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

    @Override
    @JsonIgnore
    public IssueParameter[] getParameters() {
	Set<IssueParameter> parameters = new HashSet<>();
	for (CodeRangeIssues codeRangeIssue : codeRangeDesignIssues) {
	    parameters.addAll(Arrays.asList(codeRangeIssue.getParameters()));
	}
	return parameters.toArray(new IssueParameter[parameters.size()]);
    }
}
