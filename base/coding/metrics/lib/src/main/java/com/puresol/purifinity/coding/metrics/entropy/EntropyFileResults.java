package com.puresol.purifinity.coding.metrics.entropy;

import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.N_DIFF;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.N_TOTAL;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.QUALITY;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.R;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.RS;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.R_NORM;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S_MAX;
import static com.puresol.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S_NORM;

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

public class EntropyFileResults extends AbstractEvaluatorResult implements
		MetricFileResults {

	private static final long serialVersionUID = 4585034044953318000L;

	private final List<EntropyResult> results = new ArrayList<EntropyResult>();

	public void add(EntropyResult result) {
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

		for (EntropyResult result : results) {
			EntropyMetricResult entropy = result.getEntropyMetricResult();
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(N_DIFF.getName(),
					new GeneralValue<Integer>(entropy.getNDiff(), N_DIFF));

			row.put(N_TOTAL.getName(),
					new GeneralValue<Integer>(entropy.getNTotal(), N_TOTAL));
			row.put(S.getName(), new GeneralValue<Double>(entropy.getEntropy(),
					S));
			row.put(S_MAX.getName(),
					new GeneralValue<Double>(entropy.getMaxEntropy(), S_MAX));
			row.put(S_NORM.getName(),
					new GeneralValue<Double>(entropy.getNormEntropy(), S_NORM));
			row.put(RS.getName(),
					new GeneralValue<Double>(entropy.getEntropyRedundancy(), RS));
			row.put(R.getName(),
					new GeneralValue<Double>(entropy.getRedundancy(), R));
			row.put(R_NORM.getName(),
					new GeneralValue<Double>(entropy.getNormalizedRedundancy(),
							R_NORM));
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
