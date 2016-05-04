package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class GenericCodeRangeDesignIssues implements Serializable {

    private static final long serialVersionUID = -598831823857817995L;
    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final DesignIssueParameter[] parameters;
    private final Map<String, List<DesignIssue>> values = new HashMap<>();

    public GenericCodeRangeDesignIssues(SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
	    String codeRangeName, DesignIssueParameter[] parameters, Map<String, List<DesignIssue>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters = parameters;
	this.values.putAll(values);
    }

    public GenericCodeRangeDesignIssues(SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
	    String codeRangeName, DesignIssueParameter[] parameters, Collection<List<DesignIssue>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters = parameters;
	for (List<DesignIssue> issues : values) {
	    for (DesignIssue issue : issues) {
		List<DesignIssue> savedIssues = this.values.get(issue.getParameter().getName());
		if (savedIssues == null) {
		    savedIssues = new ArrayList<>();
		    this.values.put(issue.getParameter().getName(), savedIssues);
		}
		savedIssues.add(issue);
	    }
	}
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public DesignIssueParameter[] getParameters() {
	return parameters;
    }

    public Map<String, List<DesignIssue>> getAllIssues() {
	return values;
    }

    public List<DesignIssue> getIssues(DesignIssueParameter parameter) {
	return values.get(parameter.getName());
    }
}
