/***************************************************************************
 *
 *   SLOCMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.statistics.Statistics;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractSLOCMetric extends AbstractMetric {

	private static final Translator translator = Translator
			.getTranslator(AbstractSLOCMetric.class);

	private TokenStream stream = null;
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

	public AbstractSLOCMetric(CodeRange codeRange) {
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

	public int getPhyLOC() {
		return phyLOC;
	}

	public int getProLOC() {
		return proLOC;
	}

	public int getComLOC() {
		return comLOC;
	}

	public int getBlLOC() {
		return blLOC;
	}

	public Statistics getLineStatistics() {
		return lineStatistics;
	}

	public Statistics getTrimmedLineStatistics() {
		return trimmedLineStatistics;
	}

	public Statistics getProductiveLineStatistics() {
		return productiveLineStatistics;
	}

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
}
