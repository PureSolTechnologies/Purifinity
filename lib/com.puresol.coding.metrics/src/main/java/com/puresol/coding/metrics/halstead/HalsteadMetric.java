/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.parser.ParserTree;

public class HalsteadMetric extends CodeRangeEvaluator {

    private static final long serialVersionUID = -7823038852668468658L;

    public static final String NAME = "Halstead Metric";
    public static final String DESCRIPTION = "Halstead Metric calculation.";
    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
    }

    private final Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
    private final Hashtable<String, Integer> operants = new Hashtable<String, Integer>();
    private final CodeRange codeRange;
    private final LanguageDependedHalsteadMetric langDepended;

    private HalsteadResult result;

    public HalsteadMetric(ProgrammingLanguage language, CodeRange codeRange) {
	super(NAME);
	this.codeRange = codeRange;
	langDepended = language
		.getImplementation(LanguageDependedHalsteadMetric.class);
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
    public IStatus run(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 1);
	createHashtables();
	calculateValues();
	monitor.done();
	return Status.OK_STATUS;
    }

    private void createHashtables() {
	TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
		codeRange.getParserTree());
	do {
	    ParserTree node = iterator.getCurrentNode();
	    HalsteadSymbol result = langDepended.getHalsteadResult(node);
	    if (result.isCountable()) {
		if (result.isOperator()) {
		    addOperator(result.getSymbol());
		} else {
		    addOperant(result.getSymbol());
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
	int differentOperators = operators.keySet().size();
	int differentOperands = operants.keySet().size();
	int totalOperators = 0;
	for (String key : operators.keySet()) {
	    totalOperators += operators.get(key);
	}
	int totalOperands = 0;
	for (String key : operants.keySet()) {
	    totalOperands += operants.get(key);
	}
	result = new HalsteadResult(differentOperators, differentOperands,
		totalOperators, totalOperands);
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

    public double getProgramLength() {
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

    public double getVocabularySize() {
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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<Result> getResults() {
	return result.getResults();
    }
}
