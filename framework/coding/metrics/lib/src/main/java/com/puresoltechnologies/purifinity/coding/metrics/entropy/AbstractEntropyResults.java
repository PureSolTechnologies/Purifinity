package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.N_DIFF;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.N_TOTAL;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.R;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.RS;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.R_NORM;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S_MAX;
import static com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricEvaluatorParameter.S_NORM;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class AbstractEntropyResults extends AbstractEvaluatorResult {

	private static final long serialVersionUID = 2241293026079625803L;

	protected Map<String, Value<?>> convertToRow(EntropyResult result) {
		EntropyMetricResult entropy = result.getEntropyMetricResult();
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<CodeLocation>(
				result.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
		row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				result.getCodeRangeType(), CODE_RANGE_TYPE));
		row.put(CODE_RANGE_NAME.getName(),
				new GeneralValue<String>(result.getCodeRangeName(),
						CODE_RANGE_NAME));
		row.put(N_DIFF.getName(), new GeneralValue<Integer>(entropy.getNDiff(),
				N_DIFF));

		row.put(N_TOTAL.getName(),
				new GeneralValue<Integer>(entropy.getNTotal(), N_TOTAL));
		row.put(S.getName(), new GeneralValue<Double>(entropy.getEntropy(), S));
		row.put(S_MAX.getName(),
				new GeneralValue<Double>(entropy.getMaxEntropy(), S_MAX));
		row.put(S_NORM.getName(),
				new GeneralValue<Double>(entropy.getNormEntropy(), S_NORM));
		row.put(RS.getName(),
				new GeneralValue<Double>(entropy.getEntropyRedundancy(), RS));
		row.put(R.getName(), new GeneralValue<Double>(entropy.getRedundancy(),
				R));
		row.put(R_NORM.getName(),
				new GeneralValue<Double>(entropy.getNormalizedRedundancy(),
						R_NORM));
		SourceCodeQuality quality = result.getQuality();
		row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(quality,
				QUALITY));
		if (quality != SourceCodeQuality.UNSPECIFIED) {
			row.put(QUALITY_LEVEL.getName(), new GeneralValue<QualityLevel>(
					new QualityLevel(quality), QUALITY_LEVEL));
		}
		return row;
	}

}
