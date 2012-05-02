package com.puresol.coding.metrics.maintainability;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class MaintainabilityIndexFileResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final String file;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final MaintainabilityIndexResult maintainabilityIndexResult;
    private final SourceCodeQuality quality;

    public MaintainabilityIndexFileResult(String file, CodeRangeType codeRangeType,
	    String codeRangeName,
	    MaintainabilityIndexResult maintainabilityIndexResult,
	    SourceCodeQuality quality) {
	super();
	this.file = file;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.maintainabilityIndexResult = maintainabilityIndexResult;
	this.quality = quality;
    }

    public String getFile() {
	return file;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public MaintainabilityIndexResult getHalsteadResult() {
	return maintainabilityIndexResult;
    }

    public SourceCodeQuality getQuality() {
	return quality;
    }

}
