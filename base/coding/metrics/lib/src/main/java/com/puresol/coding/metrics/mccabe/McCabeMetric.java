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

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.coding.evaluation.impl.CodeRangeEvaluator;
import com.puresol.coding.evaluation.impl.Result;
import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.parser.ParserTree;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends CodeRangeEvaluator {

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
	private final AnalysisRun analysisRun;
	private final CodeRange codeRange;

	public McCabeMetric(AnalysisRun analysisRun, ProgrammingLanguage language,
			CodeRange codeRange) {
		super(NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		langDepended = language
				.getImplementation(LanguageDependedMcCabeMetric.class);
	}

	@Override
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	@Override
	public CodeRange getCodeRange() {
		return codeRange;
	}

	@Override
	public Boolean call() {
		boolean retVal = calculate();
		createResultsList();
		return retVal;
	}

	private boolean calculate() {
		fireStarted("Evaluation started.", 1);
		cyclomaticNumber = 1;
		TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
				codeRange.getParserTree());
		do {
			cyclomaticNumber += langDepended
					.increasesCyclomaticComplexityBy(iterator.getCurrentNode());
		} while (iterator.goForward());
		fireDone("Evaluation finished.", true);
		return true;
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