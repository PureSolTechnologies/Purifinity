package com.puresoltechnologies.purifinity.coding.metrics.codedepth;

import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
<<<<<<< HEAD
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;
=======
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9

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
