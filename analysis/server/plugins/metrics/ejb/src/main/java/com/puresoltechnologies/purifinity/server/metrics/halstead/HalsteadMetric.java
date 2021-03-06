/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadLabels;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

public class HalsteadMetric extends AbstractCodeRangeEvaluator {

    public static final String ID = HalsteadMetric.class.getName();

    public static final String NAME = "Halstead Metric";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "Halstead Metric calculation.";
    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};
    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY };

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private final Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
    private final Hashtable<String, Integer> operants = new Hashtable<String, Integer>();

    private HalsteadResult result;

    public HalsteadMetric(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NAME, analysisRun, codeRange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run() {
	createHashtables();
	calculateValues();
	return true;
    }

    private void createHashtables() {
	TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(getCodeRange().getUST());
	do {
	    UniversalSyntaxTree node = iterator.getCurrentNode();
	    if (AbstractTerminal.class.isAssignableFrom(node.getClass())) {
		AbstractTerminal token = (AbstractTerminal) node;
		if (token.hasLabel(HalsteadLabels.RELEVANT)) {
		    if (token.hasLabel(HalsteadLabels.OPERATOR)) {
			addOperator((String) token.getProperties().get(HalsteadLabels.SYMBOL));
		    } else {
			addOperant((String) token.getProperties().get(HalsteadLabels.SYMBOL));
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
    public Severity getQuality() {
	CodeRange codeRange = getCodeRange();
	if ((codeRange.getType() == CodeRangeType.FILE) || (codeRange.getType() == CodeRangeType.CLASS)
		|| (codeRange.getType() == CodeRangeType.ENUMERATION)) {
	    if (getHalsteadVolume() < 80) {
		return Severity.MINOR;
	    }
	    if (getHalsteadVolume() > 10000) {
		return Severity.CRITICAL;
	    }
	    if (getHalsteadVolume() > 8000) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	} else if ((codeRange.getType() == CodeRangeType.CONSTRUCTOR) || (codeRange.getType() == CodeRangeType.METHOD)
		|| (codeRange.getType() == CodeRangeType.FUNCTION)
		|| (codeRange.getType() == CodeRangeType.INTERFACE)) {
	    if (getHalsteadVolume() < 10) {
		return Severity.MINOR;
	    }
	    if (getHalsteadVolume() > 1250) {
		return Severity.CRITICAL;
	    }
	    if (getHalsteadVolume() > 1000) {
		return Severity.MAJOR;
	    }
	    return Severity.NONE;
	}
	return Severity.NONE; // not evaluated...
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
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	return result.getResults();
    }
}
