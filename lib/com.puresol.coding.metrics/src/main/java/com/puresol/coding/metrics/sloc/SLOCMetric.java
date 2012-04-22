/***************************************************************************
 *
 *   SLOCMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.math.statistics.Statistics;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.parser.ParserTree;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetric extends CodeRangeEvaluator {

    public static final String NAME = "SLOC Metric";

    public static final String DESCRIPTION = "SLOC Metric calculation.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }

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
    private SLOCResult sloc;

    private final AnalysisRun analysisRun;
    private final CodeRange codeRange;
    private final LanguageDependedSLOCMetric langDepended;

    public SLOCMetric(AnalysisRun analysisRun, ProgrammingLanguage language,
	    CodeRange codeRange) {
	super(NAME);
	this.analysisRun = analysisRun;
	this.codeRange = codeRange;
	langDepended = language
		.getImplementation(LanguageDependedSLOCMetric.class);
	if (langDepended == null) {
	    throw new RuntimeException();
	}
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
    public IStatus run(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 1);
	setup();
	count();
	monitor.done();
	return Status.OK_STATUS;
    }

    private void setup() {
	sloc = null;
	lineResults.clear();
	for (int i = 0; i < codeRange.getParserTree().getMetaData()
		.getLineNum(); i++) {
	    lineResults.add(new LineResults());
	}
    }

    private void count() {
	TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
		codeRange.getParserTree());
	int lineOffset = codeRange.getParserTree().getMetaData().getLine();
	do {
	    ParserTree node = iterator.getCurrentNode();
	    Token token = node.getToken();
	    if (token != null) {
		SLOCType type = langDepended.getType(token);
		TokenMetaData metaData = token.getMetaData();
		int lineId = metaData.getLine() - lineOffset;
		int lineNum = metaData.getLineNum();
		for (int line = lineId; line < lineId + lineNum; line++) {
		    if (type == SLOCType.COMMENT) {
			lineResults.get(line).setComments(true);
		    } else if (type == SLOCType.PRODUCTIVE) {
			lineResults.get(line).setProductiveContent(true);
		    }
		    if (type != SLOCType.PHYSICAL) {
			lineResults.get(line).addLength(
				token.getText().length());
		    }
		}
	    }
	} while (iterator.goForward());
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
	sloc = new SLOCResult(phyLOC, proLOC, comLOC, blLOC, new Statistics(
		lineLengths));
    }

    public void print() {
	System.out.println("physical lines: " + sloc.getPhyLOC());
	System.out.println("productive lines: " + sloc.getProLOC());
	System.out.println("commented lines: " + sloc.getComLOC());
	System.out.println("blank lines: " + sloc.getBlLOC());
    }

    public SLOCResult getSLOCResult() {
	return sloc;
    }

    @Override
    public SourceCodeQuality getQuality() {
	return SLOCQuality.get(codeRange.getType(), sloc);
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
	return sloc.getResults();
    }

}
