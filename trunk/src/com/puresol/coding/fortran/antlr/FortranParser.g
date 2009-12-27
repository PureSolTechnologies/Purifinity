parser grammar JavaParser;

options {
  tokenVocab = JavaLexer;
  backtrack  = true;
//memoize=true;
}

@header {
package com.puresol.coding.java.antlr.output;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ParserHelper;
}

@members {
private ParserHelper helper = new ParserHelper(this);

public ParserHelper getParserHelper() {
	return helper;
}
}
