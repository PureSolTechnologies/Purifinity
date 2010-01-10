package com.puresol.parser.java;

import java.util.regex.Pattern;

import com.puresol.parser.Keyword;

public enum JavaKeywords implements Keyword {

	CLASS("class"), ENUM("enum"), FLOAT_CONST("(+|-)?(\\d*)\\.\\d+((e|E)\\d+)?"), INT_CONST(
			"\\d+"), BOOLEAN_CONST("(true|false)");

	private Pattern pattern;
	private Pattern startPattern;

	private JavaKeywords(String pattern) {
		this.pattern = Pattern.compile(pattern);
		this.startPattern = Pattern.compile("^" + pattern);
	}

	@Override
	public boolean atStart(String string) {
		return startPattern.matcher(string).matches();
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public Pattern getStartPattern() {
		return startPattern;
	}

	@Override
	public boolean included(String string) {
		return pattern.matcher(string).matches();
	}
}
