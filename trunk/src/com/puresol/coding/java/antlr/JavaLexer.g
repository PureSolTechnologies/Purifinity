lexer grammar JavaLexer;
options {
	language=Java;
}
@header {
package com.puresol.coding.java.antlr.output;

import com.puresol.coding.java.antlr.JavaLexerHelper;
}
@members {
private JavaLexerHelper helper = null;

public JavaLexer(CharStream stream, JavaLexerHelper helper)
{
	this(stream);
	this.helper = helper;
}
}

PACKAGE	:		'package' {helper.addOperator($text);};
IMPORT	:		'import' {helper.addOperator($text);};

CLASS	:	'class' {helper.addOperator($text);};
EXTENDS	:	'extends' {helper.addOperator($text);};
IMPLEMENTS	:	'implements' {helper.addOperator($text);};

VOID	:	'void' {helper.addOperator($text);};
NEW	:	'new' {helper.addOperator($text);};
RETURN	:	'return' {helper.addOperator($text);};

FOR	:	'for' {helper.addOperator($text);};
DO	:	'do' {helper.addOperator($text);};
WHILE	:	'while' {helper.addOperator($text);};
IF	:	'if' {helper.addOperator($text);};
ELSE	:	'else' {helper.addOperator($text);};
SWITCH	:	'switch' {helper.addOperator($text);};
CASE	:	'case' {helper.addOperator($text);};

PUBLIC	:	'public' {helper.addOperator($text);};
PRIVATE	:	'private' {helper.addOperator($text);};
PROTECTED
	:	'protected' {helper.addOperator($text);};
FINAL	:	'final' {helper.addOperator($text);};
STATIC	:	'static' {helper.addOperator($text);};
TRANSIENT
	:	'transient' {helper.addOperator($text);};

OPEN_CURLY_BRACKET
	:	'{' {helper.addBlockBegin();helper.addOperator($text);}
	;
	
CLOSE_CURLY_BRACKET
	:	'}' {helper.addBlockEnd();helper.addOperator($text);}
	;
	
PLUS	:	'+' {helper.addOperator($text);};
MINUS	:	'-' {helper.addOperator($text);};
SLASH	:	'/' {helper.addOperator($text);};
STAR	:	'*' {helper.addOperator($text);};

EQUAL	:	'==' {helper.addOperator($text);};
UNEQUAL	:	'!=' {helper.addOperator($text);};
ASSIGN	:	'=' {helper.addOperator($text);};

INC	:	'++' {helper.addOperator($text);};
DEC	:	'--' {helper.addOperator($text);};

LOGICAL_OR
	:	'||' {helper.addOperator($text);};
BIT_OR	:	'|' {helper.addOperator($text);};

LOGICAL_AND
	:	'&&' {helper.addOperator($text);};
BIT_AND	:	'&' {helper.addOperator($text);};

NOT	:	'!' {helper.addOperator($text);};

DOT	:	'.' {helper.addOperator($text);};
COMMA	:	',' {helper.addOperator($text);};
LT	:	'<' {helper.addOperator($text);};
GT	:	'>' {helper.addOperator($text);};

OPEN_RECT_BRACKET
	:	'[' {helper.addOperator($text);}
	;
	
CLOSE_RECT_BRACKET
	:	']' {helper.addOperator($text);}
	;
	
OPEN_BRACKET
	:	'(' {helper.addOperator($text);}
	;
	
CLOSE_BRACKER
	:	')' {helper.addOperator($text);}
	;

COLON	:	':' {helper.addOperator($text);};
SEMICOLON
	:	';' {helper.addOperator($text);};

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* {helper.addOperand($text);}
    ;

INT :	'0'..'9'+ {helper.addOperand($text);}
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT? {helper.addOperand($text);}
    |   '.' ('0'..'9')+ EXPONENT? {helper.addOperand($text);}
    |   ('0'..'9')+ EXPONENT {helper.addOperand($text);}
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? LINEFEED {$channel=HIDDEN; helper.addOperand($text);}
    |   '/*' ( options {greedy=false;} : (LINEFEED|~('\n')) )* '*/' {$channel=HIDDEN; helper.addOperand($text);}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | LINEFEED
        ) {$channel=HIDDEN;}
    ;
    
fragment
LINEFEED:	'\n' {helper.incSlocCount();};

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"' {helper.addOperand($text);}
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\'' {helper.addOperand($text);}
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
