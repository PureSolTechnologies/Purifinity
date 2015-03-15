package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class McCabeMetricDirectoryResults extends AbstractMcCabeMetricResults
	implements DirectoryMetrics {

    private static final long serialVersionUID = -5992363758018121695L;

    private final HashId hashId;
    private final McCabeMetricResult result;

    public McCabeMetricDirectoryResults(String evaluatorId,
	    Version evaluatorVersion, HashId hashId, Date time,
	    McCabeMetricResult result) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.result = result;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    public McCabeMetricResult getResult() {
	return result;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return ALL;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return convertToRow(result);
    }

    public static McCabeMetricResult combine(McCabeMetricResult left,
	    McCabeMetricResult results2) {
	int vG = left.getCyclomaticComplexity()
		+ results2.getCyclomaticComplexity();
	return new McCabeMetricResult(left.getSourceCodeLocation(),
		left.getCodeRangeType(), left.getCodeRangeName(), vG);
    }

}
