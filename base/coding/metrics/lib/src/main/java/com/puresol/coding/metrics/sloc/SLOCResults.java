package com.puresol.coding.metrics.sloc;

import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.ALL;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.AVG;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.BL_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.CODE_RANGE_NAME;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.CODE_RANGE_TYPE;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.COM_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.MAX;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.MEDIAN;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.MIN;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.PHY_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.PRO_LOC;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.QUALITY;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.SOURCE_CODE_LOCATION;
import static com.puresol.coding.metrics.sloc.SLOCEvaluatorParameters.STD_DEV;

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
import com.puresol.utils.math.statistics.Statistics;

public class SLOCResults implements MetricResults {

	private static final long serialVersionUID = -3995835459935284595L;

	private final List<SLOCResult> results = new ArrayList<SLOCResult>();

	public void add(SLOCResult result) {
		results.add(result);
	}

	public List<SLOCResult> getResults() {
		return results;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (SLOCResult result : results) {
			SLOCMetric metric = result.getSLOCMetric();
			Statistics stat = metric.getLineStatistics();
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(SOURCE_CODE_LOCATION.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(), SOURCE_CODE_LOCATION));
			row.put(CODE_RANGE_TYPE.getName(), new GeneralValue<CodeRangeType>(
					result.getCodeRangeType(), CODE_RANGE_TYPE));
			row.put(CODE_RANGE_NAME.getName(),
					new GeneralValue<String>(result.getCodeRangeName(),
							CODE_RANGE_NAME));
			row.put(PHY_LOC.getName(),
					new GeneralValue<Integer>(metric.getPhyLOC(), PHY_LOC));
			row.put(PRO_LOC.getName(),
					new GeneralValue<Integer>(metric.getProLOC(), PRO_LOC));
			row.put(COM_LOC.getName(),
					new GeneralValue<Integer>(metric.getComLOC(), COM_LOC));
			row.put(BL_LOC.getName(),
					new GeneralValue<Integer>(metric.getBlLOC(), BL_LOC));
			row.put(MIN.getName(),
					new GeneralValue<Integer>((int) stat.getMin(), MIN));
			row.put(MAX.getName(),
					new GeneralValue<Integer>((int) stat.getMax(), MAX));
			row.put(AVG.getName(), new GeneralValue<Double>(stat.getAvg(), AVG));
			Double median = stat.getMedian();
			if (median != null) {
				row.put(MEDIAN.getName(),
						new GeneralValue<Integer>(median.intValue(), MEDIAN));
			}
			Double stdDev = stat.getStdDev();
			if (stdDev != null) {
				row.put(STD_DEV.getName(), new GeneralValue<Double>(stdDev,
						STD_DEV));
			}
			row.put(QUALITY.getName(), new GeneralValue<SourceCodeQuality>(
					result.getQuality(), QUALITY));
			values.add(row);
		}

		return values;
	}
}
