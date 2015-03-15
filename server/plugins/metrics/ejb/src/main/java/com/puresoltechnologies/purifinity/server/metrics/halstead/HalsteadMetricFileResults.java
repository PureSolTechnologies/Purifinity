package com.puresoltechnologies.purifinity.server.metrics.halstead;

import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class HalsteadMetricFileResults extends AbstractHalsteadResults
	implements FileMetrics {

    private static final long serialVersionUID = -5970030495863471269L;

    private final List<HalsteadMetricResult> results = new ArrayList<HalsteadMetricResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public HalsteadMetricFileResults(String evaluatorId,
	    Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time) {
	super(evaluatorId, evaluatorVersion, time);
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

    public void add(HalsteadMetricResult result) {
	results.add(result);
    }

    public List<HalsteadMetricResult> getResults() {
	return results;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return ALL;
    }

    @Override
    public List<GenericCodeRangeMetrics> getValues() {
	List<GenericCodeRangeMetrics> values = new ArrayList<>();
	for (HalsteadMetricResult result : results) {
	    Map<String, MetricValue<?>> row = convertToRow(result);
	    values.add(new GenericCodeRangeMetrics(result
		    .getSourceCodeLocation(), result.getCodeRangeType(), result
		    .getCodeRangeName(), HalsteadMetricEvaluatorParameter.ALL,
		    row));
	}
	return values;
    }
}
