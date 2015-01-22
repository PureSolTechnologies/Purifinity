package com.puresoltechnologies.purifinity.server.metrics.entropy;

import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class EntropyFileResults extends AbstractEntropyResults implements
		FileMetrics {

	private static final long serialVersionUID = 4585034044953318000L;

	private final List<EntropyResult> results = new ArrayList<EntropyResult>();

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;

	public EntropyFileResults(HashId hashId,
			SourceCodeLocation sourceCodeLocation, Date time) {
		super(time);
		this.hashId = hashId;
		this.sourceCodeLocation = sourceCodeLocation;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	@Override
	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public void add(EntropyResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<GenericCodeRangeMetrics> getValues() {
		List<GenericCodeRangeMetrics> values = new ArrayList<>();
		for (EntropyResult result : results) {
			Map<String, MetricValue<?>> row = convertToRow(result);
			values.add(new GenericCodeRangeMetrics(result
					.getSourceCodeLocation(), result.getCodeRangeType(), result
					.getCodeRangeName(), EntropyMetricEvaluatorParameter.ALL,
					row));
		}
		return values;
	}

}
