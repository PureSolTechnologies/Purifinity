parser grammar FortranParser;

options {
  tokenVocab = FortranLexer;
  backtrack  = true;
}

@header {
package com.puresol.coding.fortran.antlr.output;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ParserHelper;
import java.io.File;
}

@members {
private ParserHelper helper = null;

public FortranParser(File file, TokenStream input) {
	this(input, new RecognizerSharedState());
	helper = new ParserHelper(file, this);
}

public ParserHelper getParserHelper() {
	return helper;
}
}

file
  :
  (
    ~WS
    | WS
  )*
  ;
