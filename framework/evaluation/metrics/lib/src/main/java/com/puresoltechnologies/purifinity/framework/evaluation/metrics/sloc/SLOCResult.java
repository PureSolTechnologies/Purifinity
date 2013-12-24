package com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc;

import java.io.Serializable;

import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class SLOCResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final SLOCMetric slocMetric;
	private final SourceCodeQuality quality;

	public SLOCResult(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			SLOCMetric slocResult, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.slocMetric = slocResult;
		this.quality = quality;
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

	public SourceCodeQuality getQuality() {
		return quality;
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
		SourceCodeQuality quality = SourceCodeQuality.getMinimum(
				left.getQuality(), right.getQuality());
		return new SLOCResult(left.sourceCodeLocation, left.codeRangeType,
				left.codeRangeName, new SLOCMetric(phyLOC, proLOC, comLOC,
						blLOC, lineStatistics), quality);
	}
}
