package com.puresol.purifinity.coding.metrics.maintainability;

import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL_FILE;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.commons.utils.math.GeneralValue;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class MaintainabilityIndexFileResults extends AbstractEvaluatorResult
		implements MetricFileResults {

	private static final long serialVersionUID = -5901342878584699006L;

	private final List<MaintainabilityIndexFileResult> results = new ArrayList<MaintainabilityIndexFileResult>();

	public void add(MaintainabilityIndexFileResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<MaintainabilityIndexFileResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL_FILE;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (MaintainabilityIndexFileResult result : results) {
			MaintainabilityIndexResult mi = result
					.getMaintainabilityIndexResult();
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(MI_WOC.getName(), new GeneralValue<Double>(mi.getMIwoc(),
					MI_WOC));
			row.put(MI_CW.getName(), new GeneralValue<Double>(mi.getMIcw(),
					MI_CW));
			row.put(MI.getName(), new GeneralValue<Double>(mi.getMI(), MI));
			SourceCodeQuality quality = result.getQuality();
			row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
					quality, QUALITY));
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY_LEVEL.getName(),
						new GeneralValue<QualityLevel>(
								new QualityLevel(quality), QUALITY_LEVEL));
			}
			values.add(row);
		}

		return values;
	}
}
