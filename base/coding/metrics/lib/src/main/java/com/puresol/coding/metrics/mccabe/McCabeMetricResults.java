package com.puresol.coding.metrics.mccabe;

import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.VG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class McCabeMetricResults implements MetricResults {

	private final List<McCabeMetricResult> results = new ArrayList<McCabeMetricResult>();

	private static final long serialVersionUID = -5992363758018121695L;

	public List<McCabeMetricResult> getResults() {
		return results;
	}

	public void add(McCabeMetricResult result) {
		results.add(result);
	}

	@Override
	public List<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (McCabeMetricResult result : results) {
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(),
							SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(),
					new GeneralValue<CodeRangeType>(result.getCodeRangeType(),
							CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(), new GeneralValue<String>(
					result.getCodeRangeName(), CODE_RANGE_NAME));
			row.put(VG.getName(),
					new GeneralValue<Integer>(result.getCyclomaticComplexity(),
							VG));
			row.put(QUALITY.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							QUALITY));
			values.add(row);
		}

		return values;
	}

	public static McCabeMetricResult combine(McCabeMetricResult left,
			McCabeMetricResult right) {
		int vG = left.getCyclomaticComplexity()
				+ right.getCyclomaticComplexity();
		SourceCodeQuality quality = SourceCodeQuality.getMinLevel(
				left.getQuality(), right.getQuality());
		return new McCabeMetricResult(left.getSourceCodeLocation(),
				left.getCodeRangeType(), left.getCodeRangeName(), vG, quality);
	}
}
