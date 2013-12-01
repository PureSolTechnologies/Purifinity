package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_CW;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_WOC;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.SOURCE_CODE_LOCATION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.uhura.source.CodeLocation;

public class NormalizedMaintainabilityIndexFileResults extends
		AbstractEvaluatorResult implements MetricFileResults {

	private static final long serialVersionUID = 7667134885288322378L;

	private final List<NormalizedMaintainabilityIndexFileResult> results = new ArrayList<NormalizedMaintainabilityIndexFileResult>();

	public void add(NormalizedMaintainabilityIndexFileResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<NormalizedMaintainabilityIndexFileResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (NormalizedMaintainabilityIndexFileResult result : results) {
			NormalizedMaintainabilityIndexResult mi = result
					.getNormalizedMaintainabilityIndexResult();
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(NORM_MI_WOC.getName(),
					new GeneralValue<Double>(mi.getNMIwoc(), NORM_MI_WOC));
			row.put(NORM_MI_CW.getName(),
					new GeneralValue<Double>(mi.getNMIcw(), NORM_MI_CW));
			row.put(NORM_MI.getName(), new GeneralValue<Double>(mi.getNMI(),
					NORM_MI));
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
