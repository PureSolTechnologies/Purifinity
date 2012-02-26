package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.metrics.codedepth.config.AnnotationFail;
import com.puresol.coding.metrics.codedepth.config.AnnotationWarning;
import com.puresol.coding.metrics.codedepth.config.ClassFail;
import com.puresol.coding.metrics.codedepth.config.ClassWarning;
import com.puresol.coding.metrics.codedepth.config.ConstructorFail;
import com.puresol.coding.metrics.codedepth.config.ConstructorWarning;
import com.puresol.coding.metrics.codedepth.config.EnumerationFail;
import com.puresol.coding.metrics.codedepth.config.EnumerationWarning;
import com.puresol.coding.metrics.codedepth.config.FileFail;
import com.puresol.coding.metrics.codedepth.config.FileWarning;
import com.puresol.coding.metrics.codedepth.config.FunctionFail;
import com.puresol.coding.metrics.codedepth.config.FunctionWarning;
import com.puresol.coding.metrics.codedepth.config.InterfaceFail;
import com.puresol.coding.metrics.codedepth.config.InterfaceWarning;
import com.puresol.coding.metrics.codedepth.config.ModuleFail;
import com.puresol.coding.metrics.codedepth.config.ModuleWarning;
import com.puresol.coding.metrics.codedepth.config.ProgramFail;
import com.puresol.coding.metrics.codedepth.config.ProgramWarning;
import com.puresol.coding.metrics.codedepth.config.SubroutineFail;
import com.puresol.coding.metrics.codedepth.config.SubroutineWarning;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.config.PropertyDescription;
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
    public static final List<PropertyDescription<?>> CONFIGURATION_PROPERTIES = new Vector<PropertyDescription<?>>();
    static {
	CONFIGURATION_PROPERTIES.add(new FileFail());
	CONFIGURATION_PROPERTIES.add(new FileWarning());
	CONFIGURATION_PROPERTIES.add(new ClassFail());
	CONFIGURATION_PROPERTIES.add(new ClassWarning());
	CONFIGURATION_PROPERTIES.add(new InterfaceFail());
	CONFIGURATION_PROPERTIES.add(new InterfaceWarning());
	CONFIGURATION_PROPERTIES.add(new EnumerationFail());
	CONFIGURATION_PROPERTIES.add(new EnumerationWarning());
	CONFIGURATION_PROPERTIES.add(new AnnotationFail());
	CONFIGURATION_PROPERTIES.add(new AnnotationWarning());
	CONFIGURATION_PROPERTIES.add(new ModuleFail());
	CONFIGURATION_PROPERTIES.add(new ModuleWarning());
	CONFIGURATION_PROPERTIES.add(new ConstructorFail());
	CONFIGURATION_PROPERTIES.add(new ConstructorWarning());
	CONFIGURATION_PROPERTIES.add(new ConstructorFail());
	CONFIGURATION_PROPERTIES.add(new ConstructorWarning());
	CONFIGURATION_PROPERTIES.add(new ProgramFail());
	CONFIGURATION_PROPERTIES.add(new ProgramWarning());
	CONFIGURATION_PROPERTIES.add(new SubroutineFail());
	CONFIGURATION_PROPERTIES.add(new SubroutineWarning());
	CONFIGURATION_PROPERTIES.add(new FunctionFail());
	CONFIGURATION_PROPERTIES.add(new FunctionWarning());
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
