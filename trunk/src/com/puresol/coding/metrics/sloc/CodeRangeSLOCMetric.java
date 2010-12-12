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

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.statistics.Statistics;
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
public class CodeRangeSLOCMetric extends AbstractEvaluator implements
		CodeRangeEvaluator {

	private static final long serialVersionUID = -4313208925226028154L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeSLOCMetric.class);

	public static final String NAME = translator.i18n("SLOC Metric");

	public static final String DESCRIPTION = translator
			.i18n("SLOC Metric calculation.");

	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(CodeRangeSLOCMetric.class,
				"enabled", "Switches calculation of SLOC Metric on and off.",
				Boolean.class, "true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private final List<LineResults> lineResults = new ArrayList<LineResults>();
	private SLOCResult sloc;

	private final CodeRange codeRange;
	private final LanguageDependedSLOCMetric langDepended;

	public CodeRangeSLOCMetric(ProgrammingLanguage language, CodeRange codeRange) {
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
				SourceLineType type = langDepended.getType(token);
				TokenMetaData metaData = token.getMetaData();
				int lineId = metaData.getLine() - lineOffset;
				int lineNum = metaData.getLineNum();
				for (int line = lineId; line < lineId + lineNum; line++) {
					if (type == SourceLineType.COMMENT) {
						lineResults.get(line).setComments(true);
					} else if (type == SourceLineType.PRODUCTIVE) {
						lineResults.get(line).setProductiveContent(true);
					}
					if (type != SourceLineType.PHYSICAL) {
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
		sloc = new SLOCResult(phyLOC, phyLOC, phyLOC, phyLOC, new Statistics(
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
	public QualityLevel getQuality() {
		QualityLevel levelLineCount = getQualityLevelLineCount();
		return levelLineCount;
		// QualityLevel levelLineLength = getQualityLevelLineLength();
		// return QualityLevel.getMinLevel(levelLineCount, levelLineLength);
	}

	public QualityLevel getQualityLevelLineCount() {
		if ((codeRange.getType() == CodeRangeType.FILE)
				|| (codeRange.getType() == CodeRangeType.CLASS)) {
			if (sloc.getPhyLOC() > 2500) {
				return QualityLevel.LOW;
			}
			if (sloc.getPhyLOC() > 1000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		} else if (codeRange.getType() == CodeRangeType.SUBROUTINE) {
			if (sloc.getPhyLOC() > 40) {
				return QualityLevel.LOW;
			}
			if (sloc.getPhyLOC() > 25) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	public QualityLevel getQualityLevelLineLength() {
		if (sloc.getLineStatistics().getMax() > 80) {
			return QualityLevel.LOW;
		}
		if (sloc.getLineStatistics().getAvg() > 50) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
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
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		} else {
			throw new UnsupportedFormatException(format);
		}
	}

	public String getLineCountReport() {
		String report = "phyLoc\t" + sloc.getPhyLOC() + "\t"
				+ translator.i18n("physical lines of code") + "\n";
		report += "proLoc\t" + sloc.getProLOC() + "\t"
				+ translator.i18n("productive lines of code") + "\n";
		report += "comLoc\t" + sloc.getComLOC() + "\t"
				+ translator.i18n("commented lines of code") + "\n";
		report += "blLoc\t" + sloc.getBlLOC() + "\t"
				+ translator.i18n("blank lines") + "\n";
		return report;
	}

	public String getHTMLLineCountReport() {
		return HTMLStandards.convertTSVToTable(getLineCountReport());
	}

	public String getLineLengthReport() {
		Statistics normal = sloc.getLineStatistics();
		String report = "\tnormal\n";
		report += "avg\t" + Math.round(normal.getAvg() * 100.0) / 100.0 + "\n";
		report += "median\t" + Math.round(normal.getMedian()) + "\n";
		report += "standard deviation\t"
				+ Math.round(normal.getStdDev() * 100.0) / 100.0 + "\n";
		report += "min\t" + Math.round(normal.getMin()) + "\n";
		report += "max\t" + Math.round(normal.getMax()) + "\n";
		return report;
	}

	public String getHTMLLineLengthReport() {
		return HTMLStandards.convertTSVToTable(getLineLengthReport());
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("SLOC Metrics") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += "<b>Line Counts</b>";
		report += getHTMLLineCountReport();
		report += "<b>Line Lengths</b>";
		report += getHTMLLineLengthReport();
		return report;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
