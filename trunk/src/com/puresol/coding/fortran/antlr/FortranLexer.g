lexer grammar FortranLexer;

options {
  language = Java;
}

@header {
package com.puresol.coding.fortran.antlr.output;
}

PROGRAM
  :
  P R O G R A M
  ;

SUBROUTINE
  :
  S U B R O U T I N E
  ;

FUNCTION
  :
  F U N C T I O N
  ;

INTEGER
  :
  I N T E G E R
  ;

REAL
  :
  R E A L
  ;

DOUBLE_COMPLEX
	:	
	DOUBLE WS+ COMPLEX
	;

DOUBLE_PRECISION
  :
  DOUBLE WS+ P R E C I S I O N
  ;

DOUBLE	:	
	D O U B L E
	;

COMPLEX
  :
  C O M P L E X
  ;

CHARACTER
  :
  C H A R A C T E R
  ;

LOGICAL
  :
  L O G I C A L
  ;

IMPLICIT
  :
  I M P L I C I T
  ;

NONE
  :
  N O N E
  ;

DATA
  :
  D A T A
  ;

ALLOCATE
  :
  A L L O C A T E
  ;

PARAMETER
  :
  P A R A M E T E R
  ;

IF
  :
  I F
  ;

THEN
  :
  T H E N
  ;

ELSE
  :
  E L S E
  ;

DO
  :
  D O
  ;

WHILE
  :
  W H I L E
  ;

ENDDO
  :
  E N D D O
  ;

END
  :
  E N D
  ;

CALL
  :
  C A L L
  ;

GOTO
  :
  G O WS+ T O
  ;

RETURN
  :
  R E T U R N
  ;

CONTINUE
  :
  C O N T I N U E
  ;

EXTERNAL
  :
  E X T E R N A L
  ;

INTRINSIC
  :
  I N T R I N S I C
  ;

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
  {getCharPositionInLine()>0}?=> '**'
  ; // not a comment

STAR
  :
  {getCharPositionInLine()>0}?=> '*'
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
  | {getCharPositionInLine()==0}?=>
  (
    'c'
    | 'C'
    | '*'
  )
  ~(
    '\n'
    | '\r'
   )*
  '\r'? LINEFEED {$channel=HIDDEN;}
  | '!'
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
  :
  ~(
    '\r'
    | '\n'
   )
  ;

STRING_CONST
  :
  '\''
  (
    '\'' '\''
    | ESC_SEQ
    |
    ~(
      '\\'
      | '\''
     )
  )*
  '\''
  ;

fragment
EXPONENT
  :
  (
    'e'
    | 'E'
    | 'd'
    | 'D'
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

fragment
A
  :
  (
    'a'
    | 'A'
  )
  ;

fragment
B
  :
  (
    'b'
    | 'B'
  )
  ;

fragment
C
  :
  (
    'c'
    | 'C'
  )
  ;

fragment
D
  :
  (
    'd'
    | 'D'
  )
  ;

fragment
E
  :
  (
    'e'
    | 'E'
  )
  ;

fragment
F
  :
  (
    'f'
    | 'F'
  )
  ;

fragment
G
  :
  (
    'g'
    | 'G'
  )
  ;

fragment
H
  :
  (
    'h'
    | 'H'
  )
  ;

fragment
I
  :
  (
    'i'
    | 'I'
  )
  ;

fragment
J
  :
  (
    'j'
    | 'J'
  )
  ;

fragment
K
  :
  (
    'k'
    | 'K'
  )
  ;

fragment
L
  :
  (
    'l'
    | 'L'
  )
  ;

fragment
M
  :
  (
    'm'
    | 'M'
  )
  ;

fragment
N
  :
  (
    'n'
    | 'N'
  )
  ;

fragment
O
  :
  (
    'o'
    | 'O'
  )
  ;

fragment
P
  :
  (
    'p'
    | 'P'
  )
  ;

fragment
Q
  :
  (
    'q'
    | 'Q'
  )
  ;

fragment
R
  :
  (
    'r'
    | 'R'
  )
  ;

fragment
S
  :
  (
    's'
    | 'S'
  )
  ;

fragment
T
  :
  (
    't'
    | 'T'
  )
  ;

fragment
U
  :
  (
    'u'
    | 'U'
  )
  ;

fragment
V
  :
  (
    'v'
    | 'V'
  )
  ;

fragment
W
  :
  (
    'w'
    | 'W'
  )
  ;

fragment
X
  :
  (
    'x'
    | 'X'
  )
  ;

fragment
Y
  :
  (
    'y'
    | 'Y'
  )
  ;

fragment
Z
  :
  (
    'z'
    | 'Z'
  )
  ;
