package com.puresol.coding.lang.fortran.evaluator;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.gotos.FoundGoto;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;

public class FoundImplicit {

	private static final long serialVersionUID = -8139337956175324114L;

	private static final Translator translator = Translator
			.getTranslator(FoundGoto.class);

	private final CodeRange codeRange;
	private final int tokenId;
	private final String text;

	public FoundImplicit(CodeRange codeRange, int tokenId, String text) {
		this.codeRange = codeRange;
		this.tokenId = tokenId;
		this.text = text;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public int getTokenId() {
		return tokenId;
	}

	public String getText() {
		return text;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			Token gotoToken = codeRange.getTokenStream().get(tokenId);
			return translator.i18n("line") + " " + gotoToken.getStartLine()
					+ ": '" + text + "'";
		} else if (format == ReportingFormat.HTML) {
			Token gotoToken = codeRange.getTokenStream().get(tokenId);
			Token labelToken = codeRange.getTokenStream().get(tokenId + 1);
			return translator.i18n("line") + " " + gotoToken.getStartLine()
					+ ": '" + gotoToken.getText() + " " + labelToken.getText()
					+ "'";
		}
		throw new UnsupportedReportingFormatException(format);
	}

}
