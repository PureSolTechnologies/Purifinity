package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeDepthLabels;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

/**
 * This metric looks for cascaded code blocks and finds the maximum. The code
 * depth is a measure for complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetric extends AbstractCodeRangeEvaluator {

    public static final String ID = CodeDepthMetric.class.getName();

    public static final String NAME = "Code Depth Metric";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "Analysis the nested code blocks for a maximum depth.";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};

    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY, QualityCharacteristic.UNDERSTANDABILITY };

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private final List<MetricValue<?>> results = new ArrayList<>();
    private int maxDepth = 0;

    public CodeDepthMetric(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NAME, analysisRun, codeRange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run() {
	boolean retVal = calculate();
	recreateResultsList();
	return retVal;
    }

    private boolean calculate() {
	maxDepth = 0;
	TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(getCodeRange().getUST());
	do {
	    UniversalSyntaxTree node = iterator.getCurrentNode();
	    if (!node.hasChildren()) {
		UniversalSyntaxTree parent = node;
		int depth = 0;
		do {
		    if (parent.hasLabel(CodeDepthLabels.CASCADING)) {
			depth++;
		    }
		    parent = parent.getParent();
		} while ((parent != null) && (parent != getCodeRange().getUST()));
		if (depth > maxDepth) {
		    maxDepth = depth;
		}
	    }
	} while (iterator.goForward());
	return true;
    }

    private void recreateResultsList() {
	results.clear();
	results.add(new MetricValue<Integer>(maxDepth, CodeDepthMetricEvaluatorParameter.MAX_DEPTH));
    }

    public int getMaxDepth() {
	return maxDepth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Severity getQuality() {
	return CodeDepthQuality.get(getCodeRange().getType(), maxDepth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	return results;
    }
}
