package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.codedepth.CodeDepthMetricEvaluatorParameter.MAX_DEPTH;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class CodeDepthDirectoryResults extends AbstractMetrics implements
	DirectoryMetrics {

    private static final long serialVersionUID = 5885874850811986090L;

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private int maxDepth;

    public CodeDepthDirectoryResults(String evaluatorId,
	    Version evaluatorVersion, HashId hashId, Date time,
	    SourceCodeLocation sourceCodeLocation, CodeRangeType codeRangeType,
	    String codeRangeName) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public int getMaxDepth() {
	return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
	this.maxDepth = maxDepth;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return ALL;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	Map<String, MetricValue<?>> values = new HashMap<>();
	values.put(MAX_DEPTH.getName(), new MetricValue<Integer>(maxDepth,
		MAX_DEPTH));
	return values;
    }

}
