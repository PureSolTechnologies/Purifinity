package com.puresoltechnologies.purifinity.server.metrics.entropy;

import static com.puresoltechnologies.purifinity.server.metrics.entropy.EntropyMetricEvaluatorParameter.ALL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.CodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class EntropyFileResults extends AbstractEntropyResults implements FileMetrics {

    private static final long serialVersionUID = 4585034044953318000L;

    private final List<EntropyResult> results = new ArrayList<EntropyResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public EntropyFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, Date time) {
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
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public List<CodeRangeMetrics> getCodeRangeMetrics() {
	List<CodeRangeMetrics> values = new ArrayList<>();
	for (EntropyResult result : results) {
	    Map<String, MetricValue<?>> row = convertToRow(result);
	    values.add(new CodeRangeMetrics(result.getSourceCodeLocation(), result.getCodeRangeType(),
		    result.getCodeRangeName(), EntropyMetricEvaluatorParameter.ALL, row));
	}
	return values;
    }

}
