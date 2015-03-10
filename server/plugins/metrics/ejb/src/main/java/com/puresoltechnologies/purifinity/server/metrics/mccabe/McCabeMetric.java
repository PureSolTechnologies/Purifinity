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

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.McCabeLabels;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.CodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends CodeRangeEvaluator {

    public static final String ID = McCabeMetric.class.getName();

    public static final String NAME = "McCabe Metric";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "McCabe Metric calculation.";

    public static final Set<ConfigurationParameter<?>> PARAMETERS = new HashSet<>();

    public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private int cyclomaticNumber = 1;
    private final List<MetricValue<?>> results = new ArrayList<>();
    private final AnalysisRun analysisRun;
    private final CodeRange codeRange;
    private final ProgrammingLanguage language;

    public McCabeMetric(AnalysisRun analysisRun, ProgrammingLanguage language,
	    CodeRange codeRange) {
	super(NAME);
	this.analysisRun = analysisRun;
	this.codeRange = codeRange;
	this.language = language;
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
    public boolean run() {
	boolean retVal = calculate();
	createResultsList();
	return retVal;
    }

    private boolean calculate() {
	cyclomaticNumber = 1;
	TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(
		codeRange.getUST());
	do {
	    UniversalSyntaxTree node = iterator.getCurrentNode();
	    if (AbstractProduction.class.isAssignableFrom(node.getClass())) {
		AbstractProduction production = (AbstractProduction) node;
		cyclomaticNumber += (Integer) production.getProperties().get(
			McCabeLabels.VG);
	    }
	} while (iterator.goForward());
	return true;
    }

    private void createResultsList() {
	results.clear();
	results.add(new MetricValue<Integer>(cyclomaticNumber,
		McCabeMetricEvaluatorParameter.VG));
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
    public List<MetricValue<?>> getResults() {
	return results;
    }
}
