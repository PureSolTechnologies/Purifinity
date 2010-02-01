package com.puresol.coding.lang.fortran.source.literals;

import com.puresol.coding.tokentypes.Operant;

public class StringLiteral extends Operant {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	String octalSequence = "\\\\[0-7]{1,3}";
	String hex = "[a-fA-F0-9]";
	String unicodeSequence = "\\\\u" + hex + "{4}";
	String defaultSequences = "\\\\(b|t|n|f|r|\\\\|\"|')";
	String escapeSequences =
		"(" + defaultSequences + "|" + octalSequence + "|"
			+ unicodeSequence + ")";
	setPatternString("('(" + escapeSequences + "|[^'])*'|\"("
		+ escapeSequences + "|[^\"])*\"");
    }
}
