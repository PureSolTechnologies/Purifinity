package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Date;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.versioning.Version;

public class SingleIssue extends Issue {

    private static final long serialVersionUID = 6604807655869262687L;

    private final String projectId;
    private final long runId;
    private final String evaluatorId;
    private final Version evaluatorVersion;
    private final CodeRangeType codeRangeType;
    private final HashId hashId;
    private final String codeRangeName;
    private final Date time;
    private final SourceCodeLocation sourceCodeLocation;
    private final String languageName;
    private final String languageVersion;

    public SingleIssue(String projectId, long runId, String evaluatorId, Version evaluatorVersion,
	    CodeRangeType codeRangeType, HashId hashId, String codeRangeName, Date time,
	    SourceCodeLocation sourceCodeLocation, String languageName, String languageVersion, Severity severity,
	    Classification classification, int startLine, int startColumn, int lineCount, int length, Integer weight,
	    IssueParameter parameter) {
	super(severity, classification, startLine, startColumn, lineCount, length, weight, parameter);
	this.projectId = projectId;
	this.runId = runId;
	this.evaluatorId = evaluatorId;
	this.evaluatorVersion = evaluatorVersion;
	this.codeRangeType = codeRangeType;
	this.hashId = hashId;
	this.codeRangeName = codeRangeName;
	this.time = time;
	this.sourceCodeLocation = sourceCodeLocation;
	this.languageName = languageName;
	this.languageVersion = languageVersion;
    }

    public String getProjectId() {
	return projectId;
    }

    public long getRunId() {
	return runId;
    }

    public String getEvaluatorId() {
	return evaluatorId;
    }

    public Version getEvaluatorVersion() {
	return evaluatorVersion;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public HashId getHashId() {
	return hashId;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public Date getTime() {
	return time;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public String getLanguageName() {
	return languageName;
    }

    public String getLanguageVersion() {
	return languageVersion;
    }

}
