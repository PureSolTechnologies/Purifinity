package com.puresol.coding.evaluator.gotos;

import java.io.Serializable;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;

/**
 * This class represents a single goto statement found by GotoEvaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FoundGoto implements Serializable {

	private static final long serialVersionUID = -8139337956175324114L;

	private static final Logger logger = Logger.getLogger(FoundGoto.class);
	private static final Translator translator = Translator
			.getTranslator(FoundGoto.class);

	private final CodeRange codeRange;
	private final int tokenId;
	private final String labelName;

	public FoundGoto(CodeRange codeRange, int tokenId, String labelName) {
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

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		try {
			if (format == ReportingFormat.TEXT) {
				Token gotoToken = codeRange.getTokenStream().get(tokenId);
				return translator.i18n("line") + " " + gotoToken.getStartLine()
						+ ": '" + gotoToken.getText() + " " + labelName + "'";
			} else if (format == ReportingFormat.HTML) {
				Token gotoToken = codeRange.getTokenStream().get(tokenId);
				Token labelToken;
				labelToken = codeRange.getTokenStream().findNextToken(
						tokenId + 1);
				return translator.i18n("line") + " " + gotoToken.getStartLine()
						+ ": '" + gotoToken.getText() + " "
						+ labelToken.getText() + "'";
			}
			throw new UnsupportedReportingFormatException(format);
		} catch (NoMatchingTokenException e) {
			logger.warn(e.getMessage(), e);
			return "Error in token stream.";
		}
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
		FoundGoto other = (FoundGoto) obj;
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
