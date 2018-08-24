package com.puresoltechnologies.purifinity.server.metrics.halstead;

import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.Date;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class HalsteadMetricDirectoryResults extends AbstractHalsteadResults implements DirectoryMetrics {

    private static final long serialVersionUID = -5970030495863471269L;

    private final HashId hashId;
    private final HalsteadMetricResult result;

    public HalsteadMetricDirectoryResults(String evaluatorId, Version evaluatorVersion, HashId hashId, Date time,
	    HalsteadMetricResult result) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	if (result == null) {
	    throw new IllegalArgumentException("Result must not be null!");
	}
	this.result = result;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    public HalsteadMetricResult getResult() {
	return result;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return convertToRow(result);
    }
}
