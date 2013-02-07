package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.ProgrammingLanguage;
import com.puresol.coding.analysis.impl.evaluation.CodeRangeEvaluator;
import com.puresol.coding.analysis.impl.evaluation.Result;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;
import com.puresol.coding.analysis.impl.quality.SourceCodeQuality;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.ParserTree;

/**
 * This metric looks for cascaded code blocks and finds the maximum. The code
 * depth is a measure for complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeDepthMetric extends CodeRangeEvaluator {

    public static final String NAME = "Code Depth Metric";

    public static final String DESCRIPTION = "Analysis the nested code blocks for a maximum depth.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.UNDERSTANDABILITY);
    }

    private final AnalysisRun analysisRun;
    private final List<Result> results = new ArrayList<Result>();
    private final CodeRange codeRange;
    private final LanguageDependedCodeDepthMetric langDepended;
    private int maxDepth = 0;

    public CodeDepthMetric(AnalysisRun analysisRun,
	    ProgrammingLanguage language, CodeRange codeRange) {
	super(NAME);
	this.analysisRun = analysisRun;
	this.codeRange = codeRange;
	langDepended = language
		.getImplementation(LanguageDependedCodeDepthMetric.class);
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
    public IStatus run(IProgressMonitor monitor) {
	IStatus retVal = calculate(monitor);
	recreateResultsList();
	monitor.done();
	return retVal;
    }

    private IStatus calculate(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 1);
	maxDepth = 0;
	TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
		getCodeRange().getParserTree());
	do {
	    ParserTree node = iterator.getCurrentNode();
	    Token token = node.getToken();
	    if (token != null) {
		ParserTree parent = node;
		int depth = 0;
		do {
		    if (langDepended.cascadingNode(parent)) {
			depth++;
		    }
		    parent = parent.getParent();
		} while ((parent != null)
			&& (parent != getCodeRange().getParserTree()));
		if (depth > maxDepth) {
		    maxDepth = depth;
		}
	    }
	} while (iterator.goForward());
	return Status.OK_STATUS;
    }

    private void recreateResultsList() {
	results.clear();
	results.add(new Result(
		"Maximum code depth",
		"The maximum code depth is the maximum number of cascaded code blocks within the source.",
		maxDepth, ""));
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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<Result> getResults() {
	return results;
    }
}
