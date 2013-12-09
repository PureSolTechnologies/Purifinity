/***************************************************************************
 *
 *   SLOCMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.coding.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.commons.trees.api.TreeIterator;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTreeMetaData;
import com.puresoltechnologies.parsers.impl.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.CodeRangeEvaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.Result;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.lang.api.ProgrammingLanguage;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetricCalculator extends CodeRangeEvaluator {

	public static final String NAME = "SLOC Metric";

	public static final String DESCRIPTION = "SLOC Metric calculation.";

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
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
	private SLOCMetric sloc;

	private final AnalysisRun analysisRun;
	private final CodeRange codeRange;
	private final LanguageDependedSLOCMetric langDepended;

	public SLOCMetricCalculator(AnalysisRun analysisRun,
			ProgrammingLanguage language, CodeRange codeRange) {
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
	public Boolean call() {
		fireStarted("Evaluation started.", 1);
		setup();
		count();
		fireDone("Finished evaluation.", true);
		return true;
	}

	private void setup() {
		sloc = null;
		lineResults.clear();
		for (int i = 0; i < codeRange.getUniversalSyntaxTree().getMetaData()
				.getLineNum(); i++) {
			lineResults.add(new LineResults());
		}
	}

	private void count() {
		gatherData();
		calculateFinalResult();
	}

	private void gatherData() {
		TreeIterator<UniversalSyntaxTree> iterator = new TreeIterator<UniversalSyntaxTree>(
				codeRange.getUniversalSyntaxTree());
		int lineOffset = codeRange.getUniversalSyntaxTree().getMetaData()
				.getLine();
		do {
			UniversalSyntaxTree node = iterator.getCurrentNode();
			if (AbstractTerminal.class.isAssignableFrom(node.getClass())) {
				AbstractTerminal token = (AbstractTerminal) node;
				SLOCType type = langDepended.getType(token);
				UniversalSyntaxTreeMetaData metaData = token.getMetaData();
				int lineId = metaData.getLine() - lineOffset;
				int lineNum = metaData.getLineNum();
				for (int line = lineId; line < lineId + lineNum; line++) {
					if (type == SLOCType.COMMENT) {
						// Additional check due to end-of-line comments contain
						// an additional line break
						if ((!token.getContent().endsWith("\n"))
								|| (line < lineId + lineNum - 1)) {
							lineResults.get(line).setComments(true);
						}
					} else if (type == SLOCType.PRODUCTIVE) {
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
		sloc = new SLOCMetric(phyLOC, proLOC, comLOC, blLOC, new Statistics(
				lineLengths));
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
	public SourceCodeQuality getQuality() {
		return SLOCQuality.get(codeRange.getType(), sloc);
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
		return sloc.getResults();
	}

}
