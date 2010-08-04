package com.puresol.coding.lang.fortran.evaluator;

import javax.i18n4java.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;

public class FoundImplicit {

	private static final long serialVersionUID = -8139337956175324114L;

	private static final Translator translator = Translator
			.getTranslator(FoundGoto.class);

	private final CodeRange codeRange;
	private final int tokenId;
	private final String text;
	private final boolean implicitNone;

	public FoundImplicit(CodeRange codeRange, int tokenId, String text,
			boolean implicitNone) {
		this.codeRange = codeRange;
		this.tokenId = tokenId;
		this.text = text;
		this.implicitNone = implicitNone;
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

	public boolean isImplicitNone() {
		return implicitNone;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedFormatException {
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
		throw new UnsupportedFormatException(format);
	}

}
