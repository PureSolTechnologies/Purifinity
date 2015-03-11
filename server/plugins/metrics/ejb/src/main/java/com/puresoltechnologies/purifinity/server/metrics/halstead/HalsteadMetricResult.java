package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class HalsteadMetricResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final HalsteadResult halsteadResult;

    public HalsteadMetricResult(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    HalsteadResult halsteadResult) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.halsteadResult = halsteadResult;
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

    public HalsteadResult getHalsteadResult() {
	return halsteadResult;
    }

    public static HalsteadMetricResult combine(HalsteadMetricResult left,
	    HalsteadMetricResult right) {

	HalsteadResult leftHalsteadResult = left.getHalsteadResult();
	HalsteadResult rightHalsteadResult = right.getHalsteadResult();

	Map<String, Integer> operands = new HashMap<>();
	Map<String, Integer> operators = new HashMap<>();
	operands.putAll(leftHalsteadResult.getOperands());
	operators.putAll(leftHalsteadResult.getOperators());

	for (Entry<String, Integer> entry : rightHalsteadResult.getOperands()
		.entrySet()) {
	    String key = entry.getKey();
	    if (operands.containsKey(key)) {
		operands.put(key, operands.get(key) + entry.getValue());
	    } else {
		operands.put(entry.getKey(), entry.getValue());
	    }
	}

	for (Entry<String, Integer> entry : rightHalsteadResult.getOperators()
		.entrySet()) {
	    String key = entry.getKey();
	    if (operators.containsKey(key)) {
		operators.put(key, operators.get(key) + entry.getValue());
	    } else {
		operators.put(entry.getKey(), entry.getValue());
	    }
	}

	return new HalsteadMetricResult(left.sourceCodeLocation,
		left.codeRangeType, left.codeRangeName, new HalsteadResult(
			operands, operators));
    }
}