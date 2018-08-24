package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.io.Serializable;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class MaintainabilityIndexFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final MaintainabilityIndexResult maintainabilityIndexResult;

    public MaintainabilityIndexFileResult(
	    SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
	    String codeRangeName,
	    MaintainabilityIndexResult maintainabilityIndexResult) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.maintainabilityIndexResult = maintainabilityIndexResult;
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

    public MaintainabilityIndexResult getMaintainabilityIndexResult() {
	return maintainabilityIndexResult;
    }
}
