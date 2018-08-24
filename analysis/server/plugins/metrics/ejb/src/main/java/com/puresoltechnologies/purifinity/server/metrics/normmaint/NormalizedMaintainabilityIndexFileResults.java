package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_CW;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_WOC;

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

public class NormalizedMaintainabilityIndexFileResults extends AbstractMetrics implements FileMetrics {

    private static final long serialVersionUID = 7667134885288322378L;

    private final List<NormalizedMaintainabilityIndexFileResult> results = new ArrayList<NormalizedMaintainabilityIndexFileResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public NormalizedMaintainabilityIndexFileResults(String evaluatorId, Version evaluatorVersion, HashId hashId,
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

    public void add(NormalizedMaintainabilityIndexFileResult result) {
	results.add(result);
    }

    public List<NormalizedMaintainabilityIndexFileResult> getResults() {
	return results;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public List<CodeRangeMetrics> getCodeRangeMetrics() {
	List<CodeRangeMetrics> values = new ArrayList<>();
	for (NormalizedMaintainabilityIndexFileResult result : results) {
	    NormalizedMaintainabilityIndexResult mi = result.getNormalizedMaintainabilityIndexResult();
	    Map<String, MetricValue<?>> row = new HashMap<>();
	    row.put(NORM_MI_WOC.getName(), new MetricValue<Double>(mi.getNMIwoc(), NORM_MI_WOC));
	    row.put(NORM_MI_CW.getName(), new MetricValue<Double>(mi.getNMIcw(), NORM_MI_CW));
	    row.put(NORM_MI.getName(), new MetricValue<Double>(mi.getNMI(), NORM_MI));
	    values.add(new CodeRangeMetrics(result.getSourceCodeLocation(), result.getCodeRangeType(),
		    result.getCodeRangeName(), NormalizedMaintainabilityIndexEvaluatorParameter.ALL, row));
	}
	return values;
    }
}
