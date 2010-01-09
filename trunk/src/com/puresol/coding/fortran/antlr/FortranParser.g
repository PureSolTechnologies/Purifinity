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
  program_file
  |
  (
    ~WS
    | WS
  )*
  ;

program_file
  :
  program id
  (
    ~WS
    | WS
  )
  end
  ;

implicit_none
  :
  implicit none
  ;

logical_constants
  :
  true_
  | false_
  ;

// /////////////////////////////////////////
// KEYWORDS
// /////////////////////////////////////////

program
  :
  PROGRAM
  ;

subroutine
  :
  SUBROUTINE
  ;

function
  :
  FUNCTION
  ;

integer
  :
  INTEGER
  ;

real
  :
  REAL
  ;

double_precision
  :
  DOUBLE_PRECISION
  ;

complex
  :
  COMPLEX
  ;

character
  :
  CHARACTER
  ;

logical
  :
  LOGICAL
  ;

implicit
  :
  IMPLICIT
  ;

none
  :
  NONE
  ;

data
  :
  DATA
  ;

allocate
  :
  ALLOCATE
  ;

parameter
  :
  PARAMETER
  ;

if_
  :
  IF
  ;

then_
  :
  THEN
  ;

else_
  :
  ELSE
  ;

do_
  :
  DO
  ;

while_
  :
  WHILE
  ;

enddo
  :
  ENDDO
  ;

end
  :
  END
  ;

call_
  :
  CALL
  ;

goto_
  :
  GOTO
  ;

return_
  :
  RETURN
  ;

continue_
  :
  CONTINUE
  ;

external
  :
  EXTERNAL
  ;

intrincis
  :
  INTRINSIC
  ;

dollar
  :
  DOLLAR
  ;

comma
  :
  COMMA
  ;

lparen
  :
  LPAREN
  ;

rparen
  :
  RPAREN
  ;

colon
  :
  COLON
  ;

assign
  :
  ASSIGN
  ;

minus
  :
  MINUS
  ;

plus
  :
  PLUS
  ;

div
  :
  DIV
  ;

power
  :
  POWER
  ;

star
  :
  STAR
  ;

lnot
  :
  LNOT
  ;

land
  :
  LAND
  ;

lor
  :
  LOR
  ;

eqv
  :
  EQV
  ;

neqv
  :
  NEQV
  ;

xor
  :
  XOR
  ;

eor
  :
  EOR
  ;

lt
  :
  LT
  ;

le
  :
  LE
  ;

gt
  :
  GT
  ;

ge
  :
  GE
  ;

ne
  :
  NE
  ;

eq
  :
  EQ
  ;

true_
  :
  TRUE
  ;

false_
  :
  FALSE
  ;

id
  :
  ID
  ;

int_const
  :
  INT_CONST
  ;

hex_const
  :
  HEX_CONST
  ;

float_const
  :
  FLOAT_CONST
  ;
