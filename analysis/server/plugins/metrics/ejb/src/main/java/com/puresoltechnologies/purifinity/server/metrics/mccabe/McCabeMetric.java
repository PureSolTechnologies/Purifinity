/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.McCabeLabels;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends AbstractCodeRangeEvaluator {

    public static final String ID = McCabeMetric.class.getName();

    public static final String NAME = "McCabe Metric";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "McCabe Metric calculation.";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};

    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY, QualityCharacteristic.TESTABILITY };

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private int cyclomaticNumber = 1;
    private final List<MetricValue<?>> results = new ArrayList<>();

    public McCabeMetric(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NAME, analysisRun, codeRange);
    }

    @Override
    public boolean run() {
	boolean retVal = calculate();
	createResultsList();
	return retVal;
    }

    private boolean calculate() {
	cyclomaticNumber = 1;
	TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(getCodeRange().getUST());
	do {
	    UniversalSyntaxTree node = iterator.getCurrentNode();
	    if (AbstractProduction.class.isAssignableFrom(node.getClass())) {
		AbstractProduction production = (AbstractProduction) node;
		cyclomaticNumber += (Integer) production.getProperties().get(McCabeLabels.VG);
	    }
	} while (iterator.goForward());
	return true;
    }

    private void createResultsList() {
	results.clear();
	results.add(new MetricValue<Integer>(cyclomaticNumber, McCabeMetricEvaluatorParameter.VG));
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
    public Severity getQuality() {
	return McCabeQuality.get(getCodeRange().getType(), cyclomaticNumber);
    }

    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	return results;
    }
}
