lexer grammar FortranLexer;

options {
  language = Java;
}

@header {
package com.puresol.coding.fortran.antlr.output;
}

SUBROUTINE 
	:	S U B R O U T I N E;

INTEGER
	:	I N T E G E R ;
REAL
	:	R E A L;

IF
	:	I F;
THEN
	:	T H E N;
END_IF
	:	E N D WS+ I F;

// Need 4 lookahead for logical operators (eg .NE. and .NEQV.)

DOLLAR
  :
  '$'
  ;

COMMA
  :
  ','
  ;

LPAREN
  :
  '('
  ;

RPAREN
  :
  ')'
  ;

COLON
  :
  ':'
  ;
//CONCAT     : "//" ; // define in parser. Not all // are concat ops.

ASSIGN
  :
  '='
  ;

MINUS
  :
  '-'
  ;

PLUS
  :
  '+'
  ;

DIV
  :
  '/'
  ;

POWER
  :
  {getCharPositionInLine()>0}?=>'**'
  ; // not a comment

STAR
  :
  {getCharPositionInLine()>0}?=>'*'
  ; // not a comment

LNOT
  :
  '.' N O T '.'
  ;

LAND
  :
  '.' A N D '.'
  ;

LOR
  :
  '.' O R '.'
  ;

EQV
  :
  '.' E Q V '.'
  ;

NEQV
  :
  '.' N E Q V '.'
  ;

XOR
  :
  '.' X O R '.'
  ;

EOR
  :
  '.' E O R '.'
  ;

LT
  :
  '.' L T '.'
  ;

LE
  :
  '.' L E '.'
  ;

GT
  :
  '.' G T '.'
  ;

GE
  :
  '.' G E '.'
  ;

NE
  :
  '.' N E '.'
  ;

EQ
  :
  '.' E Q '.'
  ;

TRUE
  :
  '.' T R U E '.'
  ;

FALSE
  :
  '.' F A L S E '.'
  ;

ID
  :
  (
    'a'..'z'
    | 'A'..'Z'
    | '_'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '0'..'9'
    | '_'
  )*
  ;

INT_CONST
  :
  '0'..'9'+
  ;

HEX_CONST
  :
  '0x' HEX_DIGIT+
  ;

FLOAT_CONST
  :
  ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
  | '.' ('0'..'9')+ EXPONENT?
  | ('0'..'9')+ EXPONENT
  ;

COMMENT
  :
  '//'
  ~(
    '\n'
    | '\r'
   )*
  '\r'? LINEFEED {$channel=HIDDEN;}
  | '/*'
  ( options {greedy=false;}:
    (
      LINEFEED
      | ~('\n' )
    )
  )*
  '*/' {$channel=HIDDEN;}
  |
  {getCharPositionInLine()==0}?=>
  (
    'c'
    | '*'
  )
  ~(
    '\n'
    | '\r'
   )*
  '\r'? LINEFEED {$channel=HIDDEN;}
  ;

WS
  :
  (
    ' '
    | '\t'
    | '\r'
    | LINEFEED
  )
  {$channel=HIDDEN;}
  ;

fragment
LINEFEED
  :
  '\n'
  ;

fragment
NOTNL
  :	~('\r'|'\n')
  ;
 
STRING_CONST
  :
  '"'
  (
    ESC_SEQ
    |
    ~(
      '\\'
      | '"'
     )
  )*
  '"'
  ;

CHAR_CONST
  :
  '\''
  (
    ESC_SEQ
    |
    ~(
      '\''
      | '\\'
     )
  )
  '\''
  ;

fragment
EXPONENT
  :
  (
    'e'
    | 'E'
  )
  (
    '+'
    | '-'
  )?
  ('0'..'9')+
  ;

fragment
HEX_DIGIT
  :
  (
    '0'..'9'
    | 'a'..'f'
    | 'A'..'F'
  )
  ;

fragment
ESC_SEQ
  :
  '\\'
  (
    'b'
    | 't'
    | 'n'
    | 'f'
    | 'r'
    | '\"'
    | '\''
    | '\\'
  )
  | UNICODE_ESC
  | OCTAL_ESC
  ;

fragment
OCTAL_ESC
  :
  '\\' ('0'..'3') ('0'..'7') ('0'..'7')
  | '\\' ('0'..'7') ('0'..'7')
  | '\\' ('0'..'7')
  ;

fragment
UNICODE_ESC
  :
  '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
  ;

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');
