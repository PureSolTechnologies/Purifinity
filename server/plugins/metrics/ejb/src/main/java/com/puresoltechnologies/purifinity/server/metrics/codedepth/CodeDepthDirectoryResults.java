package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_NAME;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.CODE_RANGE_TYPE;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.QUALITY_LEVEL;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class CodeDepthDirectoryResults extends AbstractMetrics implements
		DirectoryMetrics {

	private static final long serialVersionUID = 5885874850811986090L;

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private int maxDepth;

	public CodeDepthDirectoryResults(String evaluatorId, HashId hashId,
			Date time, SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
	}

	@Override
	public HashId getHashId() {
		return hashId;
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

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, MetricValue<?>> getValues() {
		Map<String, MetricValue<?>> values = new HashMap<>();
		values.put(CODE_RANGE_TYPE.getName(), new MetricValue<CodeRangeType>(
				codeRangeType, CODE_RANGE_TYPE));
		values.put(CODE_RANGE_NAME.getName(), new MetricValue<String>(
				codeRangeName, CODE_RANGE_NAME));
		values.put(MAX_DEPTH.getName(), new MetricValue<Integer>(maxDepth,
				MAX_DEPTH));
		QualityLevel qualityLevel = getQualityLevel();
		values.put(QUALITY_LEVEL.getName(), new MetricValue<QualityLevel>(
				qualityLevel, QUALITY_LEVEL));
		values.put(QUALITY.getName(), new MetricValue<SourceCodeQuality>(
				qualityLevel != null ? qualityLevel.getQuality()
						: SourceCodeQuality.UNSPECIFIED, QUALITY));
		return values;
	}

}
