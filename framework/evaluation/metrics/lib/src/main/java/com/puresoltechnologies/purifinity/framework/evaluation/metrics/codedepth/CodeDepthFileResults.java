package com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluatorResult;

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

	public List<CodeDepthResult> getResults() {
		return results;
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
					new GeneralValue<SourceCodeLocation>(result
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
