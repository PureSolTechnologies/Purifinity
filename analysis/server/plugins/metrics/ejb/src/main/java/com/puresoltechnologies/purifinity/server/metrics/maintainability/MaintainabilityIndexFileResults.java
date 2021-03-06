package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.CodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class MaintainabilityIndexFileResults extends AbstractMetrics implements FileMetrics {

    private static final long serialVersionUID = -5901342878584699006L;

    private final List<MaintainabilityIndexFileResult> results = new ArrayList<MaintainabilityIndexFileResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public MaintainabilityIndexFileResults(String evaluatorId, Version evaluatorVersion, HashId hashId,
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

    public void add(MaintainabilityIndexFileResult result) {
	results.add(result);
    }

    public List<MaintainabilityIndexFileResult> getResults() {
	return results;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public List<CodeRangeMetrics> getCodeRangeMetrics() {
	List<CodeRangeMetrics> values = new ArrayList<>();

	for (MaintainabilityIndexFileResult result : results) {
	    MaintainabilityIndexResult mi = result.getMaintainabilityIndexResult();
	    Map<String, MetricValue<?>> row = new HashMap<>();
	    row.put(MI_WOC.getName(), new MetricValue<Double>(mi.getMIwoc(), MI_WOC));
	    row.put(MI_CW.getName(), new MetricValue<Double>(mi.getMIcw(), MI_CW));
	    row.put(MI.getName(), new MetricValue<Double>(mi.getMI(), MI));
	    values.add(new CodeRangeMetrics(result.getSourceCodeLocation(), result.getCodeRangeType(),
		    result.getCodeRangeName(), MaintainabilityIndexEvaluatorParameter.ALL, row));
	}
	return values;
    }
}
