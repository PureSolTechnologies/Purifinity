package com.puresol.coding.metrics.mccabe;

import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.VG;

import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.Value;

public class AbstractMcCabeMetricResults {

	protected Map<String, Value<?>> convertToRow(McCabeMetricResult result) {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<CodeLocation>(
				result.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
		row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				result.getCodeRangeType(), CODE_RANGE_TYPE));
		row.put(CODE_RANGE_NAME.getName(),
				new GeneralValue<String>(result.getCodeRangeName(),
						CODE_RANGE_NAME));
		row.put(VG.getName(),
				new GeneralValue<Integer>(result.getCyclomaticComplexity(), VG));
		row.put(QUALITY.getName(),
				new GeneralValue<SourceCodeQuality>(result.getQuality(),
						QUALITY));
		return row;
	}

}