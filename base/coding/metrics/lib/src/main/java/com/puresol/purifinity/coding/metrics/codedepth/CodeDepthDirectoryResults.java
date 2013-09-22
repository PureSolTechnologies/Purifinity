package com.puresol.purifinity.coding.metrics.codedepth;

import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.GeneralValue;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class CodeDepthDirectoryResults extends AbstractEvaluatorResult
		implements MetricDirectoryResults {

	private static final long serialVersionUID = 5885874850811986090L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private int maxDepth;

	public CodeDepthDirectoryResults(CodeLocation sourceCodeLocation,
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

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		Map<String, Value<?>> values = new HashMap<>();
		values.put(SOURCE_CODE_LOCATION.getName(),
				new GeneralValue<CodeLocation>(sourceCodeLocation,
						SOURCE_CODE_LOCATION));
		values.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				codeRangeType, CODE_RANGE_TYPE));
		values.put(CODE_RANGE_NAME.getName(), new GeneralValue<String>(
				codeRangeName, CODE_RANGE_NAME));
		values.put(MAX_DEPTH.getName(), new GeneralValue<Integer>(maxDepth,
				MAX_DEPTH));
		QualityLevel qualityLevel = getQualityLevel();
		values.put(QUALITY_LEVEL.getName(), new GeneralValue<QualityLevel>(
				qualityLevel, QUALITY_LEVEL));
		values.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
				qualityLevel.getQuality(), QUALITY));
		return values;
	}

}