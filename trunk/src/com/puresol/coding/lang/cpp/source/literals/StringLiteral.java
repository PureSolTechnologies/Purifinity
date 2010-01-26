package com.puresol.coding.lang.cpp.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class StringLiteral extends Literal {

	@Override
	protected void initialize() {
		super.initialize();
		String octalSequence = "\\\\[0-7]{1,3}";
		String hex = "[a-fA-F0-9]";
		String unicodeSequence = "\\\\u" + hex + "{4}";
		String defaultSequences = "\\\\(b|t|n|f|r|\\\\|\"|')";
		String escapeSequences = "(" + defaultSequences + "|" + octalSequence
				+ "|" + unicodeSequence + ")";
		setPatternString("\"(" + escapeSequences + "|[^\"])*\"");
	}
}
