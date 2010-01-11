package com.puresol.coding.java.tokens;

import com.puresol.parser.AbstractTokenDefinition;

public class StringLiteral extends AbstractTokenDefinition {

    @Override
    public String getPatternString() {
	String octalSequence = "\\\\[0-7]{1,3}";
	String hex = "[a-fA-F0-9]";
	String unicodeSequence = "\\\\u" + hex + "{4}";
	String defaultSequences = "\\\\(b|t|n|f|r|\\\\|\"|')";
	String escapeSequences =
		"(" + defaultSequences + "|" + octalSequence + "|"
			+ unicodeSequence + ")";
	return "\"(" + escapeSequences + "|[^\"])*\"";
    }
}
