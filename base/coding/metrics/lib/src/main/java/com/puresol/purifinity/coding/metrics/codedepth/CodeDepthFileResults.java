package com.puresol.purifinity.coding.metrics.codedepth;

import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

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

public class CodeDepthFileResults extends AbstractEvaluatorResult implements
		MetricFileResults {

	private static final long serialVersionUID = 5885874850811986090L;

	private final List<CodeDepthResult> results = new ArrayList<CodeDepthResult>();

	public void add(CodeDepthResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (CodeDepthResult result : results) {
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(MAX_DEPTH.getName(),
					new GeneralValue<Integer>(result.getMaxDepth(), MAX_DEPTH));
			SourceCodeQuality quality = result.getQuality();
			row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
					quality, QUALITY));
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY_LEVEL.getName(),
						new GeneralValue<QualityLevel>(
								new QualityLevel(quality), QUALITY_LEVEL));
				values.add(row);
			}
		}

		return values;
	}
}
