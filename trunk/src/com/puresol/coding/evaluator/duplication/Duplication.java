package com.puresol.coding.evaluator.duplication;

import java.io.Serializable;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

/**
 * This class keeps the information of a single duplication found by a scanner.
 * Both code ranges for the duplication are named left and right for the
 * recommended presentation style on the screen.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Duplication implements Comparable<Duplication>, Serializable {

	private static final long serialVersionUID = -3213681933345753869L;

	private static final Translator translator = Translator
			.getTranslator(Duplication.class);

	private final CodeRange left;
	private final CodeRange right;
	private final CodeRange leftDuplicationRange;
	private final CodeRange rightDuplicationRange;
	private double correlation;
	private double matchingNumber;

	public Duplication(CodeRange left, CodeRange right,
			CodeRange leftDuplicationRange, CodeRange rightDuplicationRange) {
		this.left = left;
		this.right = right;
		this.leftDuplicationRange = leftDuplicationRange;
		this.rightDuplicationRange = rightDuplicationRange;
		init();
	}

	private void init() {
		double avgCodeRangeSize = (double) ((left.getStopId()
				- left.getStartId() + right.getStopId() - right.getStartId())) / 2.0;
		double avgDuplicationRangeSize = (double) ((leftDuplicationRange
				.getStopId()
				- leftDuplicationRange.getStartId()
				+ rightDuplicationRange.getStopId() - rightDuplicationRange
				.getStartId())) / 2.0;
		correlation = avgDuplicationRangeSize / avgCodeRangeSize;
		matchingNumber = correlation * avgCodeRangeSize;
	}

	public CodeRange getLeft() {
		return left;
	}

	public CodeRange getRight() {
		return right;
	}

	public CodeRange getLeftDuplicationRange() {
		return leftDuplicationRange;
	}

	public CodeRange getRightDuplicationRange() {
		return rightDuplicationRange;
	}

	public double getCorrelation() {
		return correlation;
	}

	public double getMatchingNumner() {
		return matchingNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(correlation);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime
				* result
				+ ((leftDuplicationRange == null) ? 0 : leftDuplicationRange
						.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime
				* result
				+ ((rightDuplicationRange == null) ? 0 : rightDuplicationRange
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duplication other = (Duplication) obj;
		if (Double.doubleToLongBits(correlation) != Double
				.doubleToLongBits(other.correlation))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (leftDuplicationRange == null) {
			if (other.leftDuplicationRange != null)
				return false;
		} else if (!leftDuplicationRange.equals(other.leftDuplicationRange))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (rightDuplicationRange == null) {
			if (other.rightDuplicationRange != null)
				return false;
		} else if (!rightDuplicationRange.equals(other.rightDuplicationRange))
			return false;
		return true;
	}

	@Override
	public int compareTo(Duplication other) {
		if (this.matchingNumber < other.matchingNumber) {
			return -1;
		}
		if (this.matchingNumber > other.matchingNumber) {
			return +1;
		}
		return 0;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.TEXT) {
			return toTextString();
		} else if (format == ReportingFormat.HTML) {
			return toHTMLString();
		}
		throw new UnsupportedFormatException(format);
	}

	private String toTextString() throws UnsupportedFormatException {
		CodeRange left = getLeft();
		CodeRange right = getRight();
		String output = translator.i18n("left:") + "\n";
		output += left.getTitleString(ReportingFormat.TEXT);
		output += "\n";
		output += translator.i18n("right:") + "\n";
		output += right.getTitleString(ReportingFormat.TEXT);
		return output;
	}

	private String toHTMLString() throws UnsupportedFormatException {
		CodeRange left = getLeft();
		CodeRange right = getRight();
		String output = "<table border=\"1\" padding=\"10pt\"><tr><th>left</th><th>right</th></tr><tr>\n";
		output += "<td>" + getHTMLString(left, leftDuplicationRange) + "</td>";
		output += "<td>" + getHTMLString(right, rightDuplicationRange)
				+ "</td>";
		output += "</tr></table>";
		return output;
	}

	private String getHTMLString(CodeRange range, CodeRange duplicationRange)
			throws UnsupportedFormatException {
		String output = range.getTitleString(ReportingFormat.HTML);
		output += "<tt>\n";
		boolean marked = false;
		for (Token token : range.getTokens()) {
			if (!marked) {
				if ((token.getTokenID() >= duplicationRange.getStartId())
						&& (token.getTokenID() <= duplicationRange.getStopId())) {
					marked = true;
					output += "<font class=\"highlighted\">\n";
				}
			} else {
				if ((token.getTokenID() < duplicationRange.getStartId())
						|| (token.getTokenID() > duplicationRange.getStopId())) {
					marked = false;
					output += "</font>\n";
				}
			}
			output += token.getText().replaceAll("\\n", "<br/>\n")
					.replaceAll(" ", "&nbsp;")
					.replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		if (marked) {
			output += "</font>\n";
		}
		output += "<tt>\n";
		return output;
	}
}
