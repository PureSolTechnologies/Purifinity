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

	private final CodeRange codeRange;
	private final List<LineResult> lineResults = new ArrayList<LineResult>();
	private int phyLOC;
	private int proLOC;
	private int comLOC;
	private int blLOC;
	private Statistics lineStatistics;

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
		phyLOC = 0;
		proLOC = 0;
		comLOC = 0;
		blLOC = 0;
		lineResults.clear();
		for (int i = 0; i < codeRange.getParserTree().getMetaData()
				.getLineNum(); i++) {
			lineResults.add(new LineResult());
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
		List<Double> lineLengths = new ArrayList<Double>();
		for (LineResult lineResult : lineResults) {
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
		lineStatistics = new Statistics(lineLengths);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getPhyLOC()
	 */
	public int getPhyLOC() {
		return phyLOC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getProLOC()
	 */
	public int getProLOC() {
		return proLOC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getComLOC()
	 */
	public int getComLOC() {
		return comLOC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getBlLOC()
	 */
	public int getBlLOC() {
		return blLOC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getLineStatistics()
	 */
	public Statistics getLineStatistics() {
		return lineStatistics;
	}

	public void print() {
		System.out.println("physical lines: " + phyLOC);
		System.out.println("productive lines: " + proLOC);
		System.out.println("commented lines: " + comLOC);
		System.out.println("blank lines: " + blLOC);
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
			if (getPhyLOC() > 2500) {
				return QualityLevel.LOW;
			}
			if (getPhyLOC() > 1000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		} else if (codeRange.getType() == CodeRangeType.SUBROUTINE) {
			if (getPhyLOC() > 40) {
				return QualityLevel.LOW;
			}
			if (getPhyLOC() > 25) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	public QualityLevel getQualityLevelLineLength() {
		if (getLineStatistics().getMax() > 80) {
			return QualityLevel.LOW;
		}
		if (getLineStatistics().getAvg() > 50) {
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
		String report = "phyLoc\t" + getPhyLOC() + "\t"
				+ translator.i18n("physical lines of code") + "\n";
		report += "proLoc\t" + getProLOC() + "\t"
				+ translator.i18n("productive lines of code") + "\n";
		report += "comLoc\t" + getComLOC() + "\t"
				+ translator.i18n("commented lines of code") + "\n";
		report += "blLoc\t" + getBlLOC() + "\t"
				+ translator.i18n("blank lines") + "\n";
		return report;
	}

	public String getHTMLLineCountReport() {
		return HTMLStandards.convertTSVToTable(getLineCountReport());
	}

	public String getLineLengthReport() {
		Statistics normal = getLineStatistics();
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
