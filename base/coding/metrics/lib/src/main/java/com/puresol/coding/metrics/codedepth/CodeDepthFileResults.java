package com.puresol.coding.metrics.codedepth;

import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class CodeDepthFileResults implements MetricFileResults {

	private static final long serialVersionUID = 5885874850811986090L;

	private final List<CodeDepthResult> results = new ArrayList<CodeDepthResult>();

	public void add(CodeDepthResult result) {
		results.add(result);
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
			row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
					result.getQuality(), QUALITY));
			values.add(row);
		}

		return values;
	}
}
