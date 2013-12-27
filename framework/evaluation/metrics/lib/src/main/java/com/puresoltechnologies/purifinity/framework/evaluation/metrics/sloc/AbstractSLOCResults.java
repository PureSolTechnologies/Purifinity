package com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.AVG;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.BL_LOC;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.COM_LOC;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.MAX;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.MEDIAN;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.MIN;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.PHY_LOC;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.PRO_LOC;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.SOURCE_CODE_LOCATION;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluatorParameter.STD_DEV;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluatorResult;

public abstract class AbstractSLOCResults extends AbstractEvaluatorResult {

	private static final long serialVersionUID = -7340562001522028390L;

	protected Map<String, Value<?>> convertToRow(SLOCResult result) {
		Map<String, Value<?>> row = new HashMap<String, Value<?>>();
		if (result == null) {
			return row;
		}
		SLOCMetric metric = result.getSLOCMetric();
		Statistics stat = metric.getLineStatistics();
		row.put(SOURCE_CODE_LOCATION.getName(), new GeneralValue<SourceCodeLocation>(
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