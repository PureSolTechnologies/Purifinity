package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.domain.statistics.Statistics;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
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

	public static Map<String, MetricValue<?>> toGenericMetrics(SLOCResult sloc) {
		List<MetricValue<?>> results = sloc.getSLOCMetric().getResults();
		Map<String, MetricValue<?>> values = new HashMap<>();
		for (MetricValue<?> value : results) {
			values.put(value.getParameter().getName(), value);
		}
		return values;
	}
}
