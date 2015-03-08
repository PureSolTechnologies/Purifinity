package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class SLOCResult implements Serializable {

    private static final long serialVersionUID = 2302225864694571092L;

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final SLOCMetric slocMetric;

    public SLOCResult(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    SLOCMetric slocResult) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.slocMetric = slocResult;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public SLOCMetric getSLOCMetric() {
	return slocMetric;
    }

    /**
     * This method combines two results into one. The information for code
     * location, code range type and code range name are taken out of the left
     * argument.
     * 
     * @param left
     * @param right
     * @return
     */
    public static SLOCResult combine(SLOCResult left, SLOCResult right) {
	SLOCMetric leftMetric = left.getSLOCMetric();
	SLOCMetric rightMetric = right.getSLOCMetric();
	int phyLOC = leftMetric.getPhyLOC() + rightMetric.getPhyLOC();
	int proLOC = leftMetric.getProLOC() + rightMetric.getProLOC();
	int comLOC = leftMetric.getComLOC() + rightMetric.getComLOC();
	int blLOC = leftMetric.getBlLOC() + rightMetric.getBlLOC();

	Statistics lineStatistics = Statistics
		.combine(leftMetric.getLineStatistics(),
			rightMetric.getLineStatistics());
	return new SLOCResult(left.sourceCodeLocation, left.codeRangeType,
		left.codeRangeName, new SLOCMetric(phyLOC, proLOC, comLOC,
			blLOC, lineStatistics));
    }

    public static SLOCResult valueOf(GenericCodeRangeMetrics result) {
	MetricValue<Integer> phyLOC = result
		.getValue(SLOCEvaluatorParameter.PHY_LOC);
	MetricValue<Integer> proLOC = result
		.getValue(SLOCEvaluatorParameter.PRO_LOC);
	MetricValue<Integer> comLOC = result
		.getValue(SLOCEvaluatorParameter.COM_LOC);
	MetricValue<Integer> blLOC = result
		.getValue(SLOCEvaluatorParameter.BL_LOC);

	MetricValue<Integer> min = result.getValue(SLOCEvaluatorParameter.MIN);
	MetricValue<Integer> max = result.getValue(SLOCEvaluatorParameter.MAX);
	MetricValue<Double> median = result
		.getValue(SLOCEvaluatorParameter.MEDIAN);
	Value<Double> avg = result.getValue(SLOCEvaluatorParameter.AVG);
	Value<Double> stdDev = result.getValue(SLOCEvaluatorParameter.STD_DEV);
	Statistics lineStatistics = new Statistics(phyLOC.getValue(),
		min.getValue(), max.getValue(), avg.getValue(),
		median.getValue(), stdDev.getValue());

	SLOCMetric metric = new SLOCMetric(phyLOC.getValue(),
		proLOC.getValue(), comLOC.getValue(), blLOC.getValue(),
		lineStatistics);

	return new SLOCResult(result.getSourceCodeLocation(),
		result.getCodeRangeType(), result.getCodeRangeName(), metric);
    }

    public static SLOCResult valueOf(GenericDirectoryMetrics directoryResult) {
	Map<String, MetricValue<?>> result = directoryResult.getValues();
	@SuppressWarnings("unchecked")
	MetricValue<Integer> phyLOC = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.PHY_LOC.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Integer> proLOC = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.PRO_LOC.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Integer> comLOC = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.COM_LOC.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Integer> blLOC = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.BL_LOC.getName());

	@SuppressWarnings("unchecked")
	MetricValue<Integer> min = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.MIN.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Integer> max = (MetricValue<Integer>) result
		.get(SLOCEvaluatorParameter.MAX.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Double> median = (MetricValue<Double>) result
		.get(SLOCEvaluatorParameter.MEDIAN.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Double> avg = (MetricValue<Double>) result
		.get(SLOCEvaluatorParameter.AVG.getName());
	@SuppressWarnings("unchecked")
	MetricValue<Double> stdDev = (MetricValue<Double>) result
		.get(SLOCEvaluatorParameter.STD_DEV.getName());
	Statistics lineStatistics = new Statistics(phyLOC.getValue(),
		min.getValue(), max.getValue(), avg.getValue(),
		median.getValue(), stdDev.getValue());

	SLOCMetric metric = new SLOCMetric(phyLOC.getValue(),
		proLOC.getValue(), comLOC.getValue(), blLOC.getValue(),
		lineStatistics);
	// XXX The 'null' and "" in the next line are strange...
	return new SLOCResult(null, CodeRangeType.DIRECTORY, "", metric);
    }

    public static Map<String, MetricValue<?>> toGenericMetrics(SLOCResult sloc) {
	List<MetricValue<?>> results = sloc.getSLOCMetric().getResults();
	Map<String, MetricValue<?>> values = new HashMap<>();
	for (MetricValue<?> value : results) {
	    values.put(value.getParameter().getName(), value);
	}
	return values;
    }
}
