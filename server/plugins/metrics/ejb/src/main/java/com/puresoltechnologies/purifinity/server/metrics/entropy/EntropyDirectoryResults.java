package com.puresoltechnologies.purifinity.server.metrics.entropy;

import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class EntropyDirectoryResults extends AbstractEntropyResults implements
		DirectoryMetrics {

	private static final long serialVersionUID = 4585034044953318000L;

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private EntropyMetricResult entropyResult;

	public EntropyDirectoryResults(HashId hashId, Date time,
			SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
			String codeRangeName) {
		super(time);
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

	public EntropyMetricResult getEntropyResult() {
		return entropyResult;
	}

	public void setEntropyResult(EntropyMetricResult entropyResult) {
		this.entropyResult = entropyResult;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, MetricValue<?>> getValues() {
		Map<String, MetricValue<?>> values = convertToRow(new EntropyResult(
				sourceCodeLocation, codeRangeType, codeRangeName,
				entropyResult, getQualityLevel().getQuality()));
		values.put(QualityLevelParameter.NAME, new MetricValue<QualityLevel>(
				getQualityLevel(), QualityLevelParameter.getInstance()));
		return values;
	}
}
