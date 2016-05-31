package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.CodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class CodeDepthFileResults extends AbstractMetrics implements FileMetrics {

    private static final long serialVersionUID = 5885874850811986090L;

    private final List<CodeDepthResult> results = new ArrayList<CodeDepthResult>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public CodeDepthFileResults(String evaluatorId, Version evaluatorVersion, HashId hashId,
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

    public void add(CodeDepthResult result) {
	results.add(result);
    }

    public List<CodeDepthResult> getResults() {
	return results;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return ALL;
    }

    @Override
    public List<CodeRangeMetrics> getCodeRangeMetrics() {
	List<CodeRangeMetrics> values = new ArrayList<>();

	for (CodeDepthResult result : results) {
	    Map<String, MetricValue<?>> row = new HashMap<>();
	    row.put(MAX_DEPTH.getName(), new MetricValue<Integer>(result.getMaxDepth(), MAX_DEPTH));
	    Severity quality = result.getQuality();
	    if (quality != Severity.NONE) {
		values.add(new CodeRangeMetrics(sourceCodeLocation, result.getCodeRangeType(),
			result.getCodeRangeName(), CodeDepthMetricEvaluatorParameter.ALL, row));
	    }
	}

	return values;
    }
}
