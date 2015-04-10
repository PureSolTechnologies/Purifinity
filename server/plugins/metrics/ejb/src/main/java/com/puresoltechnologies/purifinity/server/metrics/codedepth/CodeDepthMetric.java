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
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.CodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

/**
 * This metric looks for cascaded code blocks and finds the maximum. The code
 * depth is a measure for complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetric extends CodeRangeEvaluator {

	public static final String ID = CodeDepthMetric.class.getName();

	public static final String NAME = "Code Depth Metric";

	public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

	public static final String DESCRIPTION = "Analysis the nested code blocks for a maximum depth.";

	public static final Set<ConfigurationParameter<?>> PARAMETERS = new HashSet<>();

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.UNDERSTANDABILITY);
	}

	public static final Set<String> DEPENDENCIES = new HashSet<>();

	private final AnalysisRun analysisRun;
	private final List<MetricValue<?>> results = new ArrayList<>();
	private final CodeRange codeRange;
	private final ProgrammingLanguage language;
	private int maxDepth = 0;

	public CodeDepthMetric(AnalysisRun analysisRun,
			ProgrammingLanguage language, CodeRange codeRange) {
		super(NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		this.language = language;
	}

	@Override
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	@Override
	public CodeRange getCodeRange() {
		return codeRange;
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
		TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(
				getCodeRange().getUST());
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
				} while ((parent != null)
						&& (parent != getCodeRange().getUST()));
				if (depth > maxDepth) {
					maxDepth = depth;
				}
			}
		} while (iterator.goForward());
		return true;
	}

	private void recreateResultsList() {
		results.clear();
		results.add(new MetricValue<Integer>(maxDepth,
				CodeDepthMetricEvaluatorParameter.MAX_DEPTH));
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
	public SourceCodeQuality getQuality() {
		return CodeDepthQuality.get(codeRange.getType(), maxDepth);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<MetricValue<?>> getResults() {
		return results;
	}
}
