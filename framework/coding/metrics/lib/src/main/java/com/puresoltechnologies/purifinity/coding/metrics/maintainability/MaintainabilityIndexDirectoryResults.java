package com.puresoltechnologies.purifinity.coding.metrics.maintainability;

import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL_DIRECTORY;
import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class MaintainabilityIndexDirectoryResults extends
		AbstractEvaluatorResult implements MetricDirectoryResults {

	private static final long serialVersionUID = -5901342878584699006L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;

	public MaintainabilityIndexDirectoryResults(
			SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
			String codeRangeName) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL_DIRECTORY;
	}

	@Override
	public Map<String, Value<?>> getValues() {

		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<SourceCodeLocation>(
				sourceCodeLocation, SOURCE_CODE_LOCATION));
		row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				codeRangeType, CODE_RANGE_TYPE));
		row.put(CODE_RANGE_NAME.getName(), new GeneralValue<String>(
				codeRangeName, CODE_RANGE_NAME));
		row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
				getSourceQuality(), QUALITY));
		row.put(QUALITY_LEVEL.getName(), new GeneralValue<QualityLevel>(
				getQualityLevel(), QUALITY_LEVEL));
		return row;
	}
}
