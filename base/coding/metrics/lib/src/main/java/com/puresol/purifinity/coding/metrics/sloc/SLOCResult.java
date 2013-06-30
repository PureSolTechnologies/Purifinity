package com.puresol.purifinity.coding.metrics.sloc;

import java.io.Serializable;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.utils.math.statistics.Statistics;

public class SLOCResult implements Serializable {

	private static final long serialVersionUID = 2302225864694571092L;

	private final CodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final SLOCMetric slocMetric;
	private final SourceCodeQuality quality;

	public SLOCResult(CodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			SLOCMetric slocResult, SourceCodeQuality quality) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.slocMetric = slocResult;
		this.quality = quality;
	}

	public CodeLocation getSourceCodeLocation() {
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
		SourceCodeQuality quality = SourceCodeQuality.getMinLevel(
				left.getQuality(), right.getQuality());
		return new SLOCResult(left.sourceCodeLocation, left.codeRangeType,
				left.codeRangeName, new SLOCMetric(phyLOC, proLOC, comLOC,
						blLOC, lineStatistics), quality);
	}
}
