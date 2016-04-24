package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

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

public class McCabeMetricFileResults extends AbstractMcCabeMetricResults
	implements FileMetrics {

    private static final long serialVersionUID = -5992363758018121695L;

    private final List<McCabeMetricResult> results = new ArrayList<McCabeMetricResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public McCabeMetricFileResults(String evaluatorId,
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

    public List<McCabeMetricResult> getResults() {
	return results;
    }

    public void add(McCabeMetricResult result) {
	results.add(result);
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public List<GenericCodeRangeMetrics> getCodeRangeMetrics() {
	List<GenericCodeRangeMetrics> values = new ArrayList<>();
	for (McCabeMetricResult result : results) {
	    Map<String, MetricValue<?>> row = convertToRow(result);
	    values.add(new GenericCodeRangeMetrics(result
		    .getSourceCodeLocation(), result.getCodeRangeType(), result
		    .getCodeRangeName(), McCabeMetricEvaluatorParameter.ALL,
		    row));
	}
	return values;
    }

}
