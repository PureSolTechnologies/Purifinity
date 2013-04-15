package com.puresol.coding.metrics.sloc;

import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.AVG;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.BL_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.COM_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.MAX;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.MEDIAN;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.MIN;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.PHY_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.PRO_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.QUALITY;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter.STD_DEV;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.Value;
import com.puresol.utils.math.statistics.Statistics;

public class AbstractSLOCResults implements Serializable {

	private static final long serialVersionUID = -7340562001522028390L;

	protected Map<String, Value<?>> convertToRow(SLOCResult result) {
		SLOCMetric metric = result.getSLOCMetric();
		Statistics stat = metric.getLineStatistics();
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<CodeLocation>(
				result.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
		row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
				result.getCodeRangeType(), CODE_RANGE_TYPE));
		row.put(CODE_RANGE_NAME.getName(),
				new GeneralValue<String>(result.getCodeRangeName(),
						CODE_RANGE_NAME));
		row.put(PHY_LOC.getName(), new GeneralValue<Integer>(
				metric.getPhyLOC(), PHY_LOC));
		row.put(PRO_LOC.getName(), new GeneralValue<Integer>(
				metric.getProLOC(), PRO_LOC));
		row.put(COM_LOC.getName(), new GeneralValue<Integer>(
				metric.getComLOC(), COM_LOC));
		row.put(BL_LOC.getName(), new GeneralValue<Integer>(metric.getBlLOC(),
				BL_LOC));
		row.put(MIN.getName(), new GeneralValue<Integer>((int) stat.getMin(),
				MIN));
		row.put(MAX.getName(), new GeneralValue<Integer>((int) stat.getMax(),
				MAX));
		row.put(AVG.getName(), new GeneralValue<Double>(stat.getAvg(), AVG));
		Double median = stat.getMedian();
		if (median != null) {
			row.put(MEDIAN.getName(),
					new GeneralValue<Integer>(median.intValue(), MEDIAN));
		}
		Double stdDev = stat.getStdDev();
		if (stdDev != null) {
			row.put(STD_DEV.getName(),
					new GeneralValue<Double>(stdDev, STD_DEV));
		}
		row.put(QUALITY.getName(),
				new GeneralValue<SourceCodeQuality>(result.getQuality(),
						QUALITY));
		return row;
	}

}
