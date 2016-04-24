package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class MaintainabilityIndexDirectoryResults extends AbstractMetrics implements DirectoryMetrics {

    private static final long serialVersionUID = -5901342878584699006L;

    private final HashId hashId;

    public MaintainabilityIndexDirectoryResults(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    Date time) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return new HashMap<>();
    }

}
