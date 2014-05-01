package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class CodeDepthDirectoryResults extends AbstractEvaluatorResult
		implements MetricDirectoryResults {

	private static final long serialVersionUID = 5885874850811986090L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private int maxDepth;

	public CodeDepthDirectoryResults(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
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
				new GeneralValue<SourceCodeLocation>(sourceCodeLocation,
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
				qualityLevel != null ? qualityLevel.getQuality()
						: SourceCodeQuality.UNSPECIFIED, QUALITY));
		return values;
	}

}
