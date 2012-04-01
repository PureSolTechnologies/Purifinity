/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.parser.ParserTree;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends CodeRangeEvaluator {

    private static final long serialVersionUID = 4402746003873908301L;

    public static final String NAME = "McCabe Metric";

    public static final String DESCRIPTION = "McCabe Metric calculation.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }

    private int cyclomaticNumber = 1;
    private final List<Result> results = new ArrayList<Result>();
    private final LanguageDependedMcCabeMetric langDepended;
    private final CodeRange codeRange;

    public McCabeMetric(ProgrammingLanguage language, CodeRange codeRange) {
	super(NAME);
	this.codeRange = codeRange;
	langDepended = null;
	// langDepended = language
	// .getImplementation(LanguageDependedMcCabeMetric.class);
	if (langDepended == null) {
	    throw new RuntimeException();
	}
    }

    @Override
    public CodeRange getCodeRange() {
	return codeRange;
    }

    @Override
    public IStatus run(IProgressMonitor monitor) {
	IStatus retVal = calculate(monitor);
	createResultsList();
	return retVal;
    }

    private IStatus calculate(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 1);
	cyclomaticNumber = 1;
	TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
		codeRange.getParserTree());
	do {
	    cyclomaticNumber += langDepended
		    .increasesCyclomaticComplexityBy(iterator.getCurrentNode());
	} while (iterator.goForward());
	monitor.done();
	return Status.OK_STATUS;
    }

    private void createResultsList() {
	results.clear();
	results.add(new Result("v(G)", "Cyclomatic complexity",
		cyclomaticNumber, ""));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.McCabeMetric#getCyclomaticNumber()
     */
    public int getCyclomaticNumber() {
	return cyclomaticNumber;
    }

    public void print() {
	System.out.println("v(G) = " + cyclomaticNumber);
    }

    public static boolean isSuitable(CodeRange codeRange) {
	return true;
    }

    @Override
    public SourceCodeQuality getQuality() {
	return McCabeQuality.get(getCodeRange().getType(), cyclomaticNumber);
    }

    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<Result> getResults() {
	return results;
    }
}
