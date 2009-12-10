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
INTERFACE
	:	'interface';
ENUM	:	'enum';
EXTENDS	:	'extends';
IMPLEMENTS	:	'implements';

VOID	:	'void';
NEW	:	'new';
RETURN	:	'return';
BREAK	:	'break';
CONTINUE:	'continue';
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
THROW	:	'throw';
THROWS	:	'throws';

PUBLIC	:	'public';
PRIVATE	:	'private';
PROTECTED
	:	'protected';
FINAL	:	'final';
STATIC	:	'static';
TRANSIENT
	:	'transient';

BOOLEAN	:	'boolean';
BYTE	:	'byte';
CHAR	:	'char';
SHORT	:	'short';
INTEGER	:	'int';
LONG	:	'long';
FLOAT	:	'float';
DOUBLE	:	'double';

OPEN_CURLY_BRACKET
	:	'{'
	;
	
CLOSE_CURLY_BRACKET
	:	'}'
	;
	
LE	:	'<=';
GE	:	'>=';
EQUAL	:	'==';
UNEQUAL	:	'!=';
ASSIGN	:	'=';
INCASSIGN	
	:	'+=';
DECASSIGN
	:	'-=';

INC	:	'++';
DEC	:	'--';

PLUS	:	'+';
MINUS	:	'-';
SLASH	:	'/';
STAR	:	'*';

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

BOOL_CONST	
	:	'true'
	|	'false'
	;

ID	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
	;

INT_CONST
	:	'0'..'9'+
    	;

LONG_CONST
	:	'0'..'9'+ 'L'
    	;

FLOAT_CONST
	:   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
	|   '.' ('0'..'9')+ EXPONENT?
	|   ('0'..'9')+ EXPONENT
	;

COMMENT
	:   '//' ~('\n'|'\r')* '\r'? LINEFEED {$channel=HIDDEN;}
	|   '/*' ( options {greedy=false;} : (LINEFEED|~('\n')) )* '*/' {$channel=HIDDEN;}
	;

WS	:   ( ' '
	| '\t'
	| '\r'
	| LINEFEED
	) {$channel=HIDDEN;}
	;
    
fragment
LINEFEED:	'\n';

STRING_CONST
	:  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
	;

CHAR_CONST
	:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
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
