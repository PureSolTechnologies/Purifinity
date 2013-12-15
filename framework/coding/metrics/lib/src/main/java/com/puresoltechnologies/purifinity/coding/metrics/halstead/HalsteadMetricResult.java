package com.puresoltechnologies.purifinity.coding.metrics.halstead;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class HalsteadMetricResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final HalsteadResult halsteadResult;
	private final SourceCodeQuality quality;

	public HalsteadMetricResult(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			HalsteadResult halsteadResult, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.halsteadResult = halsteadResult;
		this.quality = quality;
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

	public SourceCodeQuality getQuality() {
		return quality;
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

		SourceCodeQuality quality = SourceCodeQuality.UNSPECIFIED;

		return new HalsteadMetricResult(left.sourceCodeLocation,
				left.codeRangeType, left.codeRangeName, new HalsteadResult(
						operands, operators), quality);
	}
}