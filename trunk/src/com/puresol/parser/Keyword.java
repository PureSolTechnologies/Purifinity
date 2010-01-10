package com.puresol.parser;

import java.util.regex.Pattern;

public interface Keyword {

	public Pattern getStartPattern();

	public Pattern getPattern();

	public boolean included(String string);

	public boolean atStart(String string);
}
