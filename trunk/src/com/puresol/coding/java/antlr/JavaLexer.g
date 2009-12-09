lexer grammar JavaLexer;
options {
	language=Java;
}
@header {
package com.puresol.coding.java.antlr.output;
}

PACKAGE	:		'package';
IMPORT	:		'import';

CLASS	:	'class';
EXTENDS	:	'extends';
IMPLEMENTS	:	'implements';

VOID	:	'void';
NEW	:	'new';
RETURN	:	'return';
NULL	:	'null';
THIS	:	'this';
SUPER	:	'super';

FOR	:	'for';
DO	:	'do';
WHILE	:	'while';
IF	:	'if';
ELSE	:	'else';
SWITCH	:	'switch';
CASE	:	'case';

TRY	:	'try' ;
CATCH	:	'catch' ;
FINALLY	:	'finally' ;

PUBLIC	:	'public';
PRIVATE	:	'private';
PROTECTED
	:	'protected';
FINAL	:	'final';
STATIC	:	'static';
TRANSIENT
	:	'transient';

OPEN_CURLY_BRACKET
	:	'{'
	;
	
CLOSE_CURLY_BRACKET
	:	'}'
	;
	
PLUS	:	'+';
MINUS	:	'-';
SLASH	:	'/';
STAR	:	'*';

EQUAL	:	'==';
UNEQUAL	:	'!=';
ASSIGN	:	'=';

INC	:	'++';
DEC	:	'--';

LOGICAL_OR
	:	'||';
BIT_OR	:	'|';

LOGICAL_AND
	:	'&&';
BIT_AND	:	'&';

NOT	:	'!';

DOT	:	'.';
COMMA	:	',';
LT	:	'<';
GT	:	'>';

OPEN_RECT_BRACKET
	:	'['
	;
	
CLOSE_RECT_BRACKET
	:	']'
	;
	
OPEN_BRACKET
	:	'('
	;
	
CLOSE_BRACKET
	:	')'
	;

COLON	:	':';
SEMICOLON
	:	';';
AT	:	'@';
TILDE	:	'~';

BOOLEAN	:	'true'
	|	'false'
	;

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? LINEFEED {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : (LINEFEED|~('\n')) )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | LINEFEED
        ) {$channel=HIDDEN;}
    ;
    
fragment
LINEFEED:	'\n';

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
