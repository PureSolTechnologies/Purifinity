/***************************************************************************
 *
 *   SLOCMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.statistics.Statistics;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTreeMetaData;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.trees.TreeIterator;
import com.puresoltechnologies.versioning.Version;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetricCalculator extends AbstractCodeRangeEvaluator {

    public static final String ID = SLOCEvaluator.class.getName();

    public static final String NAME = "SLOC Metric";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "SLOC Metric calculation.";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};

    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY, QualityCharacteristic.TESTABILITY };

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private class LineResults implements Serializable {

	private static final long serialVersionUID = -7222483379600215412L;

	private int length = 0;
	private boolean comments = false;
	private boolean productiveContent = false;

	public int getLength() {
	    return length;
	}

	public void addLength(int length) {
	    this.length += length;
	}

	public boolean hasComments() {
	    return comments;
	}

	public void setComments(boolean comments) {
	    this.comments = comments;
	}

	public boolean hasProductiveContent() {
	    return productiveContent;
	}

	public void setProductiveContent(boolean productiveContent) {
	    this.productiveContent = productiveContent;
	}

	public boolean isBlank() {
	    return (!hasProductiveContent()) && (!hasComments());
	}
    }

    private final List<LineResults> lineResults = new ArrayList<LineResults>();
    private SLOCMetric sloc;

    public SLOCMetricCalculator(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NAME, analysisRun, codeRange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run() {
	setup();
	count();
	return true;
    }

    private void setup() {
	sloc = null;
	lineResults.clear();
	for (int i = 0; i < getCodeRange().getUST().getMetaData().getLineNum(); i++) {
	    lineResults.add(new LineResults());
	}
    }

    private void count() {
	gatherData();
	calculateFinalResult();
    }

    private void gatherData() {
	CodeRange codeRange = getCodeRange();
	TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(codeRange.getUST());
	int lineOffset = codeRange.getUST().getMetaData().getLine();
	do {
	    UniversalSyntaxTree node = iterator.getCurrentNode();
	    if (AbstractTerminal.class.isAssignableFrom(node.getClass())) {
		AbstractTerminal token = (AbstractTerminal) node;
		UniversalSyntaxTreeMetaData metaData = token.getMetaData();
		int lineId = metaData.getLine() - lineOffset;
		int lineNum = metaData.getLineNum();
		for (int line = lineId; line < lineId + lineNum; line++) {
		    if (token.hasLabel(SLOCType.COMMENT.getLabel())) {
			// Additional check due to end-of-line comments contain
			// an additional line break
			if ((!token.getContent().endsWith("\n")) || (line < lineId + lineNum - 1)) {
			    lineResults.get(line).setComments(true);
			}
		    } else if (token.hasLabel(SLOCType.PRODUCTIVE.getLabel())) {
			lineResults.get(line).setProductiveContent(true);
		    }
		}
		String[] tokenParts = token.getContent().split("\n");
		for (int i = 0; i < tokenParts.length; i++) {
		    String tokenPart = tokenParts[i];
		    lineResults.get(lineId + i).addLength(tokenPart.length());
		}
	    }
	} while (iterator.goForward());
    }

    private void calculateFinalResult() {
	int blLOC = 0;
	int comLOC = 0;
	int proLOC = 0;
	int phyLOC = 0;

	List<Double> lineLengths = new ArrayList<Double>();
	for (LineResults lineResult : lineResults) {
	    if (lineResult.isBlank()) {
		blLOC++;
	    } else {
		if (lineResult.hasComments()) {
		    comLOC++;
		}
		if (lineResult.hasProductiveContent()) {
		    proLOC++;
		}
	    }
	    phyLOC++;
	    lineLengths.add((double) lineResult.getLength());
	}
	sloc = new SLOCMetric(phyLOC, proLOC, comLOC, blLOC, new Statistics(lineLengths));
    }

    public void print() {
	System.out.println("physical lines: " + sloc.getPhyLOC());
	System.out.println("productive lines: " + sloc.getProLOC());
	System.out.println("commented lines: " + sloc.getComLOC());
	System.out.println("blank lines: " + sloc.getBlLOC());
    }

    public SLOCMetric getSLOCResult() {
	return sloc;
    }

    @Override
    public Severity getQuality() {
	return SLOCQuality.get(getCodeRange().getType(), sloc);
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
	return sloc.getResults();
    }

}
