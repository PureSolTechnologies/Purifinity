package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

/**
 * This class contains issues for a single code range.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class CodeRangeIssues implements Serializable {

    private static final long serialVersionUID = -598831823857817995L;
    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final IssueParameter[] parameters;
    private final Map<String, List<Issue>> values = new HashMap<>();

    public CodeRangeIssues(SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType, String codeRangeName,
	    IssueParameter[] parameters, Map<String, List<Issue>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters = parameters;
	this.values.putAll(values);
    }

    public CodeRangeIssues(SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType, String codeRangeName,
	    IssueParameter[] parameters, Collection<List<Issue>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters = parameters;
	for (List<Issue> issues : values) {
	    for (Issue issue : issues) {
		List<Issue> savedIssues = this.values.get(issue.getParameter().getName());
		if (savedIssues == null) {
		    savedIssues = new ArrayList<>();
		    this.values.put(issue.getParameter().getName(), savedIssues);
		}
		savedIssues.add(issue);
	    }
	}
    }

    public final SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public final CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public final String getCodeRangeName() {
	return codeRangeName;
    }

    public final IssueParameter[] getParameters() {
	return parameters;
    }

    public final Map<String, List<Issue>> getAllIssues() {
	return values;
    }

    public final List<Issue> getIssues(IssueParameter parameter) {
	return values.get(parameter.getName());
    }
}
