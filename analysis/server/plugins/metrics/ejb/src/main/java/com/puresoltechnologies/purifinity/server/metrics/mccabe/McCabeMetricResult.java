package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class McCabeMetricResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final int cyclomaticComplexity;

    public McCabeMetricResult(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    int cyclomaticComplexity) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.cyclomaticComplexity = cyclomaticComplexity;
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

    public int getCyclomaticComplexity() {
	return cyclomaticComplexity;
    }

    public Map<String, MetricValue<?>> getValues() {
	Map<String, MetricValue<?>> values = new HashMap<>();
	values.put(McCabeMetricEvaluatorParameter.VG.getName(),
		new MetricValue<Integer>(cyclomaticComplexity,
			McCabeMetricEvaluatorParameter.VG));
	return values;
    }
}
