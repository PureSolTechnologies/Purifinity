package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
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
public class CodeDepthMetric extends AbstractEvaluator implements
	CodeRangeEvaluator {

    private static final long serialVersionUID = -2151200082569811564L;

    public static final String NAME = "Code Depth Metric";

    public static final String DESCRIPTION = "Analysis the nested code blocks for a maximum depth.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.UNDERSTANDABILITY);
    }

    private final List<Result> results = new ArrayList<Result>();
    private final CodeRange codeRange;
    private final LanguageDependedCodeDepthMetric langDepended;
    private int maxDepth = 0;

    public CodeDepthMetric(ProgrammingLanguage language, CodeRange codeRange) {
	super();
	this.codeRange = codeRange;
	langDepended = language
		.getImplementation(LanguageDependedCodeDepthMetric.class);
	if (langDepended == null) {
	    throw new RuntimeException();
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRange getCodeRange() {
	return codeRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
	calculate();
	recreateResultsList();
	if (getMonitor() != null) {
	    getMonitor().finished(this);
	}
    }

    private void calculate() {
	if (getMonitor() != null) {
	    getMonitor().setRange(0, 1);
	    getMonitor().setTitle(NAME);
	}
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
    public String getName() {
	return NAME;
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
