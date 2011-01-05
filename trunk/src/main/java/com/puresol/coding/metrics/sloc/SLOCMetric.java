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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.math.statistics.Statistics;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.utils.Property;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetric extends AbstractEvaluator implements CodeRangeEvaluator {

	private static final long serialVersionUID = -4313208925226028154L;

	private static final Translator translator = Translator
			.getTranslator(SLOCMetric.class);

	public static final String NAME = translator.i18n("SLOC Metric");

	public static final String DESCRIPTION = translator
			.i18n("SLOC Metric calculation.");

	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(SLOCMetric.class, "enabled",
				"Switches calculation of SLOC Metric on and off.",
				Boolean.class, "true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private class LineResults {

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

	private final CodeRange codeRange;
	private final LanguageDependedSLOCMetric langDepended;

	public SLOCMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super();
		this.codeRange = codeRange;
		langDepended = language
				.getImplementation(LanguageDependedSLOCMetric.class);
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
	public void run() {
		if (getMonitor() != null) {
			getMonitor().setRange(0, 1);
			getMonitor().setDescription(NAME);
		}
		setup();
		count();
		if (getMonitor() != null) {
			getMonitor().finish();
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getPhyLOC()
	 */
	public SLOCResult getResult() {
		return sloc;
	}

	public void print() {
		System.out.println("physical lines: " + sloc.getPhyLOC());
		System.out.println("productive lines: " + sloc.getProLOC());
		System.out.println("commented lines: " + sloc.getComLOC());
		System.out.println("blank lines: " + sloc.getBlLOC());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		return SLOCQuality.get(codeRange.getType(), sloc);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
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
		return sloc.getResults();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, SourceCodeQuality> getPartQualities() {
		return null;
	}
}
