package com.puresol.coding.evaluator.gotos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;

/**
 * This class represents a single goto label found by GotoEvaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FoundLabel implements Serializable {

	private static final long serialVersionUID = 4456287174201314363L;

	private static final Translator translator = Translator
			.getTranslator(FoundLabel.class);

	private final CodeRange codeRange;
	private final int tokenId;
	private final String labelName;
	private final ArrayList<FoundGoto> assignedGotos = new ArrayList<FoundGoto>();

	public FoundLabel(CodeRange codeRange, int tokenId, String labelName) {
		this.codeRange = codeRange;
		this.tokenId = tokenId;
		this.labelName = labelName;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public int getTokenId() {
		return tokenId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void addGoto(FoundGoto foundGoto) {
		assignedGotos.add(foundGoto);
	}

	public ArrayList<FoundGoto> getGotos() {
		return assignedGotos;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			Token labelToken = codeRange.getTokenStream().get(tokenId);
			String text = translator.i18n("line") + " "
					+ labelToken.getStartLine() + ": '" + labelToken.getText()
					+ "'\n";
			if (assignedGotos.size() > 0) {
				text += "targeted from:\n";
				for (FoundGoto foundGoto : assignedGotos) {
					text += "\t" + foundGoto.toString(format) + "\n";
				}
			} else {
				text += "No goto is using this label! Is it a jump from outside of this code range?\n";
			}
			return text;
		} else if (format == ReportingFormat.HTML) {
			Token labelToken = codeRange.getTokenStream().get(tokenId);
			String text = translator.i18n("line") + " "
					+ labelToken.getStartLine() + ": '" + labelToken.getText()
					+ "'<br/>\n";
			if (assignedGotos.size() > 0) {
				text += "targeted from:<br/>\n";
				text += "<ol>\n";
				for (FoundGoto foundGoto : assignedGotos) {
					text += "<li>" + foundGoto.toString(format) + "</li>\n";
				}
				text += "</ol>\n";
			} else {
				text += "No goto is using this label! Is it a jump from outside of this code range?\n";
			}
			return text;
		}
		throw new UnsupportedReportingFormatException(format);
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
		result = prime * result
				+ ((codeRange == null) ? 0 : codeRange.hashCode());
		result = prime * result
				+ ((labelName == null) ? 0 : labelName.hashCode());
		result = prime * result + tokenId;
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
		FoundLabel other = (FoundLabel) obj;
		if (codeRange == null) {
			if (other.codeRange != null)
				return false;
		} else if (!codeRange.equals(other.codeRange))
			return false;
		if (labelName == null) {
			if (other.labelName != null)
				return false;
		} else if (!labelName.equals(other.labelName))
			return false;
		if (tokenId != other.tokenId)
			return false;
		return true;
	}

}
