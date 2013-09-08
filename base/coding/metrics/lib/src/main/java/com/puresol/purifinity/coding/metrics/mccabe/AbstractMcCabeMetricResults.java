package com.puresol.purifinity.coding.metrics.mccabe;

import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY;
import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.VG;

import java.util.HashMap;
import java.util.Map;

import com.puresol.commons.utils.math.GeneralValue;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorResult;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;

public abstract class AbstractMcCabeMetricResults extends
		AbstractEvaluatorResult {

	private static final long serialVersionUID = 8270749745560040672L;

	protected Map<String, Value<?>> convertToRow(McCabeMetricResult result) {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		if (result != null) {
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(VG.getName(),
					new GeneralValue<Integer>(result.getCyclomaticComplexity(),
							VG));
			SourceCodeQuality quality = result.getQuality();
			row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
					quality, QUALITY));
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY_LEVEL.getName(),
						new GeneralValue<QualityLevel>(
								new QualityLevel(quality), QUALITY_LEVEL));
			}
		}
		return row;
	}

}
