/***************************************************************************
 *
 *   SLOCMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.AbstractMetric;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
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
public class SLOCMetric extends AbstractMetric {

	private static final Translator translator = Translator
			.getTranslator(SLOCMetric.class);

	public static final String NAME = translator.i18n("SLOC Metric");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(SLOCMetric.class, "enabled",
				"Switches calculation of SLOC Metric on and off.",
				Boolean.class, "true"));
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
		calculate();
	}

	private void calculate() {
		countLines();
		collectLineLengths();
	}

	private void countLines() {
		phyLOC = 0;
		proLOC = 0;
		comLOC = 0;
		blLOC = 0;
		for (int index = getCodeRange().getStart(); index <= getCodeRange()
				.getStop(); index++) {
			Token token = stream.get(index);
			if (!token.getText().endsWith("\n")) {
				// it's not a end of line token and therefore to be skipped
				continue;
			}
			int lines = countLinesInToken(token);
			phyLOC += lines;
			if (isComment(token)) {
				comLOC += lines;
			}
			if (isBlank(token)) {
				blLOC += lines;
			}
			if (isProductive(token)) {
				proLOC += lines;
			}
		}
	}

	/**
	 * This method calculates how much lines are encoded in the current token
	 * and the tokens before which do not end with a new line.
	 * 
	 * @param token
	 * @return
	 */
	private int countLinesInToken(Token token) {
		int index = token.getTokenID();
		if (index == 0) {
			return 0;
		}
		index--;
		int lines = 0;
		Token previous = stream.get(index);
		while (!previous.getText().endsWith("\n")) {
			lines += previous.getText().split("\n").length - 1;
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return lines + 1;
	}

	private boolean isComment(Token token) {
		/*
		 * A comment is a trimmed non-empty string!
		 */
		int index = token.getTokenID();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getPublicity() == TokenPublicity.HIDDEN) {
				if (!previous.getText().trim().isEmpty()) {
					return true;
				}
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return false;
	}

	private boolean isBlank(Token token) {
		/*
		 * A blank lines consists only of channel 99 tokens with are trimmed
		 * empty.
		 */
		int index = token.getTokenID();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getPublicity() != TokenPublicity.HIDDEN) {
				return false;
			}
			if (!previous.getText().trim().isEmpty()) {
				return false;
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return true;
	}

	private boolean isProductive(Token token) {
		/*
		 * A productive line is a line with at least one token not in channel
		 * 99!
		 */
		int index = token.getTokenID();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getPublicity() != TokenPublicity.HIDDEN) {
				return true;
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
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
		for (int index = getCodeRange().getStart(); index <= getCodeRange()
				.getStop(); index++) {
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
	public QualityLevel getQualityLevel() {
		QualityLevel levelLineCount = getQualityLevelLineCount();
		return levelLineCount;
		// QualityLevel levelLineLength = getQualityLevelLineLength();
		// return QualityLevel.getMinLevel(levelLineCount, levelLineLength);
	}

	public QualityLevel getQualityLevelLineCount() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (getPhyLOC() > 2500) {
				return QualityLevel.LOW;
			}
			if (getPhyLOC() > 1000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
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
}
