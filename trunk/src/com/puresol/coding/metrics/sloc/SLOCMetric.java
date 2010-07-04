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

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.statistics.Statistics;
import com.puresol.utils.Property;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetric extends AbstractCodeRangeEvaluator {

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

	private final TokenStream stream;
	private int phyLOC;
	private int proLOC;
	private int comLOC;
	private int blLOC;
	private ArrayList<Integer> lineLengths;
	private ArrayList<Integer> lineLengthsTrimmed;
	private ArrayList<Integer> lineLengthsProductive;
	private ArrayList<Integer> lineLengthsTrimmedProductive;
	private Statistics lineStatistics;
	private Statistics trimmedLineStatistics;
	private Statistics productiveLineStatistics;
	private Statistics trimmedProductiveLineStatistics;

	public SLOCMetric(CodeRange codeRange) {
		super(codeRange);
		this.stream = codeRange.getTokenStream();
	}

	@Override
	public void run() {
		countLines();
		collectLineLengths();
	}

	private void countLines() {
		phyLOC = 0;
		proLOC = 0;
		comLOC = 0;
		blLOC = 0;
		for (int line = getCodeRange().getStartLine(); line <= getCodeRange()
				.getStopLine(); line++) {
			TokenStream lineStream = stream.getLineStream(line);
			phyLOC += 1;
			if (isComment(lineStream)) {
				comLOC += 1;
			}
			if (isBlank(lineStream)) {
				blLOC += 1;
			}
			if (isProductive(lineStream)) {
				proLOC += 1;
			}
		}
	}

	private boolean isComment(TokenStream tokenStream) {
		for (Token token : tokenStream.getTokens()) {
			if (token.getPublicity() == TokenPublicity.HIDDEN) {
				if (token.getText().trim().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBlank(TokenStream tokenStream) {
		for (Token token : tokenStream.getTokens()) {
			if (token.getText().trim().length() > 0) {
				return false;
			}
		}
		return true;
	}

	private boolean isProductive(TokenStream tokenStream) {
		for (Token token : tokenStream.getTokens()) {
			if (token.getPublicity() != TokenPublicity.HIDDEN) {
				if (token.getText().trim().length() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	private void collectLineLengths() {
		StringBuffer completeLines = new StringBuffer();
		StringBuffer productiveLines = new StringBuffer();
		lineLengths = new ArrayList<Integer>();
		lineLengthsTrimmed = new ArrayList<Integer>();
		lineLengthsProductive = new ArrayList<Integer>();
		lineLengthsTrimmedProductive = new ArrayList<Integer>();
		for (int index = getCodeRange().getStartId(); index <= getCodeRange()
				.getStopId(); index++) {
			Token token = stream.get(index);
			completeLines.append(token.getText());
			if (token.getPublicity() != TokenPublicity.HIDDEN) {
				productiveLines.append(token.getText());
			} else if (token.getText().trim().isEmpty()) {
				productiveLines.append(token.getText());
			} else if (token.getText().contains("\n")) {
				productiveLines.append("\n");
			}
		}
		String[] lines = completeLines.toString().split("\n");
		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.isEmpty()) {
				continue;
			}
			lineLengths.add(line.length());
			lineLengthsTrimmed.add(trimmed.length());
		}

		lines = productiveLines.toString().split("\n");
		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.isEmpty()) {
				continue;
			}
			lineLengthsProductive.add(line.length());
			lineLengthsTrimmedProductive.add(trimmed.length());
		}
		lineStatistics = new Statistics(toDouble(lineLengths));
		trimmedLineStatistics = new Statistics(toDouble(lineLengthsTrimmed));
		productiveLineStatistics = new Statistics(
				toDouble(lineLengthsProductive));
		trimmedProductiveLineStatistics = new Statistics(
				toDouble(lineLengthsTrimmedProductive));
	}

	private List<Double> toDouble(List<Integer> ints) {
		ArrayList<Double> d = new ArrayList<Double>();
		for (int i : ints) {
			d.add((double) i);
		}
		return d;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getTrimmedLineStatistics()
	 */
	public Statistics getTrimmedLineStatistics() {
		return trimmedLineStatistics;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.SLOCMetric#getProductiveLineStatistics()
	 */
	public Statistics getProductiveLineStatistics() {
		return productiveLineStatistics;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.puresol.coding.analysis.SLOCMetric#
	 * getTrimmedProductiveLineStatistics()
	 */
	public Statistics getTrimmedProductiveLineStatistics() {
		return trimmedProductiveLineStatistics;
	}

	public void print() {
		System.out.println("physical lines: " + phyLOC);
		System.out.println("productive lines: " + proLOC);
		System.out.println("commented lines: " + comLOC);
		System.out.println("blank lines: " + blLOC);
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQuality() {
		QualityLevel levelLineCount = getQualityLevelLineCount();
		return levelLineCount;
		// QualityLevel levelLineLength = getQualityLevelLineLength();
		// return QualityLevel.getMinLevel(levelLineCount, levelLineLength);
	}

	public QualityLevel getQualityLevelLineCount() {
		CodeRange range = getCodeRange();
		if ((range.getCodeRangeType() == CodeRangeType.FILE)
				|| (range.getCodeRangeType() == CodeRangeType.CLASS)
				|| (range.getCodeRangeType() == CodeRangeType.ENUMERATION)) {
			if (getPhyLOC() > 2500) {
				return QualityLevel.LOW;
			}
			if (getPhyLOC() > 1000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		} else if ((range.getCodeRangeType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getCodeRangeType() == CodeRangeType.METHOD)
				|| (range.getCodeRangeType() == CodeRangeType.FUNCTION)
				|| (range.getCodeRangeType() == CodeRangeType.INTERFACE)) {
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
		if ((getTrimmedProductiveLineStatistics().getMax() > 80)
				|| (getProductiveLineStatistics().getMax() > 80)
				|| (getLineStatistics().getMax() > 80)) {
			return QualityLevel.LOW;
		}
		if ((getTrimmedProductiveLineStatistics().getAvg() > 40)
				|| (getProductiveLineStatistics().getAvg() > 50)
				|| (getLineStatistics().getAvg() > 50)) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

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
		Statistics trimmed = getTrimmedLineStatistics();
		Statistics prod = getProductiveLineStatistics();
		Statistics prodTrimmed = getTrimmedProductiveLineStatistics();
		String report = "\tnormal\ttrimmed\tproductive"
				+ "\tproductive trimmed\n";
		report += "avg\t" + Math.round(normal.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(trimmed.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(prod.getAvg() * 100.0) / 100.0 + "\t"
				+ Math.round(prodTrimmed.getAvg() * 100.0) / 100.0 + "\n";
		report += "median\t" + Math.round(normal.getMedian()) + "\t"
				+ Math.round(trimmed.getMedian()) + "\t"
				+ Math.round(prod.getMedian()) + "\t"
				+ Math.round(prodTrimmed.getMedian()) + "\n";
		report += "standard deviation\t"
				+ Math.round(normal.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(trimmed.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(prod.getStdDev() * 100.0) / 100.0 + "\t"
				+ Math.round(prodTrimmed.getStdDev() * 100.0) / 100.0 + "\n";
		report += "min\t" + Math.round(normal.getMin()) + "\t"
				+ Math.round(trimmed.getMin()) + "\t"
				+ Math.round(prod.getMin()) + "\t"
				+ Math.round(prodTrimmed.getMin()) + "\n";
		report += "max\t" + Math.round(normal.getMax()) + "\t"
				+ Math.round(trimmed.getMax()) + "\t"
				+ Math.round(prod.getMax()) + "\t"
				+ Math.round(prodTrimmed.getMax()) + "\n";
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

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
