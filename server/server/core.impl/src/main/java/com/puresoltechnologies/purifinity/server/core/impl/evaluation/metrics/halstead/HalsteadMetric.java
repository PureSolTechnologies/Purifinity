/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.halstead;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.trees.TreeIterator;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.HalsteadSymbol;
import com.puresoltechnologies.purifinity.evaluation.api.Result;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.common.CodeRangeEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.spi.LanguageDependedHalsteadMetric;

public class HalsteadMetric extends CodeRangeEvaluator {

	public static final String NAME = "Halstead Metric";
	public static final String DESCRIPTION = "Halstead Metric calculation.";
	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
	}

	private final AnalysisRun analysisRun;
	private final Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	private final Hashtable<String, Integer> operants = new Hashtable<String, Integer>();
	private final CodeRange codeRange;
	private final LanguageDependedHalsteadMetric langDepended;

	private HalsteadResult result;

	public HalsteadMetric(AnalysisRun analysisRun,
			ProgrammingLanguage language, CodeRange codeRange) {
		super(NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		langDepended = language
				.getImplementation(LanguageDependedHalsteadMetric.class);
	}

	@Override
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
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
	public Boolean call() {
		fireStarted("Start evaluation.", 1);
		createHashtables();
		calculateValues();
		fireDone("Finished evaluation.", true);
		return true;
	}

	private void createHashtables() {
		TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(
				codeRange.getUniversalSyntaxTree());
		do {
			UniversalSyntaxTree node = iterator.getCurrentNode();
			if (AbstractTerminal.class.isAssignableFrom(node.getClass())) {
				AbstractTerminal token = (AbstractTerminal) node;
				HalsteadSymbol result = langDepended.getHalsteadResult(token);
				if (result.isCountable()) {
					if (result.isOperator()) {
						addOperator(result.getSymbol());
					} else {
						addOperant(result.getSymbol());
					}
				}
			}
		} while (iterator.goForward());
	}

	private void addOperator(String operator) {
		if (operators.containsKey(operator)) {
			operators.put(operator, operators.get(operator) + 1);
		} else {
			operators.put(operator, 1);
		}
	}

	private void addOperant(String operant) {
		if (operants.containsKey(operant)) {
			operants.put(operant, operants.get(operant) + 1);
		} else {
			operants.put(operant, 1);
		}
	}

	private void calculateValues() {
		result = new HalsteadResult(operants, operators);
	}

	public HalsteadResult getHalsteadResults() {
		return result;
	}

	public Hashtable<String, Integer> getOperators() {
		return operators;
	}

	public Hashtable<String, Integer> getOperands() {
		return operants;
	}

	public int getDifferentOperands() {
		return result.getDifferentOperands();
	}

	public int getDifferentOperators() {
		return result.getDifferentOperators();
	}

	public double getDifficulty() {
		return result.getDifficulty();
	}

	public double getEstimatedBugs() {
		return result.getEstimatedBugs();
	}

	public double getHalsteadLength() {
		return result.getHalsteadLength();
	}

	public double getHalsteadVolume() {
		return result.getHalsteadVolume();
	}

	public double getImplementationEffort() {
		return result.getImplementationEffort();
	}

	public double getImplementationTime() {
		return result.getImplementationTime();
	}

	public int getProgramLength() {
		return result.getProgramLength();
	}

	public double getProgramLevel() {
		return result.getProgramLevel();
	}

	public int getTotalOperands() {
		return result.getTotalOperands();
	}

	public int getTotalOperators() {
		return result.getTotalOperators();
	}

	public int getVocabularySize() {
		return result.getVocabularySize();
	}

	public void print() {
		System.out.println("n1 = " + result.getDifferentOperators());
		System.out.println("n2 = " + result.getDifferentOperands());
		System.out.println("N1 = " + result.getTotalOperators());
		System.out.println("N2 = " + result.getTotalOperands());
		System.out.println("n = " + result.getVocabularySize());
		System.out.println("N = " + result.getProgramLength());
		System.out.println("HL = " + result.getHalsteadLength());
		System.out.println("HV = " + result.getHalsteadVolume());
		System.out.println("D = " + result.getDifficulty());
		System.out.println("L = " + result.getProgramLevel());
		System.out.println("E = " + result.getImplementationEffort());
		System.out.println("T = " + result.getImplementationTime());
		System.out.println("B = " + result.getEstimatedBugs());
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		if ((codeRange.getType() == CodeRangeType.FILE)
				|| (codeRange.getType() == CodeRangeType.CLASS)
				|| (codeRange.getType() == CodeRangeType.ENUMERATION)) {
			if (getHalsteadVolume() < 80) {
				return SourceCodeQuality.MEDIUM;
			}
			if (getHalsteadVolume() > 10000) {
				return SourceCodeQuality.LOW;
			}
			if (getHalsteadVolume() > 8000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRange.getType() == CodeRangeType.CONSTRUCTOR)
				|| (codeRange.getType() == CodeRangeType.METHOD)
				|| (codeRange.getType() == CodeRangeType.FUNCTION)
				|| (codeRange.getType() == CodeRangeType.INTERFACE)) {
			if (getHalsteadVolume() < 10) {
				return SourceCodeQuality.MEDIUM;
			}
			if (getHalsteadVolume() > 1250) {
				return SourceCodeQuality.LOW;
			}
			if (getHalsteadVolume() > 1000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.HIGH; // not evaluated...
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
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return result.getResults();
	}
}
