package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
<<<<<<< HEAD
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
=======
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevelParameter;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9

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
