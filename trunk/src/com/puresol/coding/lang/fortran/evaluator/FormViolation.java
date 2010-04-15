package com.puresol.coding.lang.fortran.evaluator;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.gotos.FoundGoto;
import com.puresol.parser.Token;
import com.puresol.reporting.ReportingFormat;

public class FormViolation {

	private static final long serialVersionUID = -8139337956175324114L;

	private static final Translator translator = Translator
			.getTranslator(FoundGoto.class);

	private final CodeRange codeRange;
	private final int tokenId;

	public FormViolation(CodeRange codeRange, int tokenId) {
		this.codeRange = codeRange;
		this.tokenId = tokenId;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public int getTokenId() {
		return tokenId;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		Token gotoToken = codeRange.getTokenStream().get(tokenId);
		if (format == ReportingFormat.TEXT) {
			return translator.i18n("line") + " " + gotoToken.getStartLine();
		} else if (format == ReportingFormat.HTML) {
			return translator.i18n("line") + " " + gotoToken.getStartLine();
		}
		throw new UnsupportedReportingFormatException(format);
	}

}
