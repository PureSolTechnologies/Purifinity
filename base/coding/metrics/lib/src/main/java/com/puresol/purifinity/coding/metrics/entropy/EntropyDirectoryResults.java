package com.puresol.purifinity.coding.metrics.entropy;

import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresol.commons.math.GeneralValue;
import com.puresol.commons.math.Parameter;
import com.puresol.commons.math.Value;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class EntropyDirectoryResults extends AbstractEntropyResults implements
		MetricDirectoryResults {

	private static final long serialVersionUID = 4585034044953318000L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private EntropyMetricResult entropyResult;

	public EntropyDirectoryResults(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
	}

	public CodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public CodeRangeType getCodeRangeType() {
		return codeRangeType;
	}

	public String getCodeRangeName() {
		return codeRangeName;
	}

	public EntropyMetricResult getEntropyResult() {
		return entropyResult;
	}

	public void setEntropyResult(EntropyMetricResult entropyResult) {
		this.entropyResult = entropyResult;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		Map<String, Value<?>> values = convertToRow(new EntropyResult(
				sourceCodeLocation, codeRangeType, codeRangeName,
				entropyResult, getQualityLevel().getQuality()));
		values.put(QualityLevelParameter.NAME, new GeneralValue<QualityLevel>(
				getQualityLevel(), QualityLevelParameter.getInstance()));
		return values;
	}
}
