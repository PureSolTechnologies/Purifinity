package com.puresoltechnologies.purifinity.evaluation.domain.architecture;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssue;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;

public class GenericCodeRangeArchitectureIssues implements Serializable {

    public GenericCodeRangeArchitectureIssues(SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
	    String codeRangeName, DesignIssueParameter[] parameters, Map<String, List<DesignIssue>> values) {
	// TODO Auto-generated constructor stub
    }

    public String getCodeRangeName() {
	// TODO Auto-generated method stub
	return null;
    }

    public CodeRangeType getCodeRangeType() {
	// TODO Auto-generated method stub
	return null;
    }

    public List<ArchitectureIssue> getIssues(ArchitectureIssueParameter parameter) {
	// TODO Auto-generated method stub
	return null;
    }

}
