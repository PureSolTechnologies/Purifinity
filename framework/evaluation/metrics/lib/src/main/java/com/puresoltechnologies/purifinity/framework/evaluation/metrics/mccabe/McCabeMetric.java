/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.trees.api.TreeIterator;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.ust.AbstractProduction;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.CodeRangeEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Result;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends CodeRangeEvaluator {

	public static final String NAME = "McCabe Metric";

	public static final String DESCRIPTION = "McCabe Metric calculation.";

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
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
		TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(
				codeRange.getUniversalSyntaxTree());
		do {
			UniversalSyntaxTree node = iterator.getCurrentNode();
			if (AbstractProduction.class.isAssignableFrom(node.getClass())) {
				AbstractProduction production = (AbstractProduction) node;
				cyclomaticNumber += langDepended
						.increasesCyclomaticComplexityBy(production);
			}
		} while (iterator.goForward());
		fireDone("Evaluation finished.", true);
		return true;
	}

	private void createResultsList() {
		results.clear();
		results.add(new Result("v(G)", "Cyclomatic complexity",
				cyclomaticNumber, ""));
	}

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
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return results;
	}
}
