package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operant;

public class StringLiteral extends Operant {

	@Override
	public String getPatternString() {
		String octalSequence = "\\\\[0-7]{1,3}";
		String hex = "[a-fA-F0-9]";
		String unicodeSequence = "\\\\u" + hex + "{4}";
		String defaultSequences = "\\\\(b|t|n|f|r|\\\\|\"|')";
		String escapeSequences = "(" + defaultSequences + "|" + octalSequence
				+ "|" + unicodeSequence + ")";
		return "\"(" + escapeSequences + "|[^\"])*\"";
	}
}
