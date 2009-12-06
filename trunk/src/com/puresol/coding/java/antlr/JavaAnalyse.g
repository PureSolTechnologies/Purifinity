grammar JavaAnalyse;
options {
	language=Java;
}
@header {
package com.puresol.coding.antlr.output;

import com.puresol.coding.antlr.ANTLRJavaHelper;
}
@lexer::header {
package com.puresol.coding.antlr.output;

import com.puresol.coding.antlr.ANTLRJavaHelper;
}
@members {
private ANTLRJavaHelper helper = null;

public JavaAnalyseParser(TokenStream stream, ANTLRJavaHelper helper)
{
	this(stream);
	this.helper = helper;
}

}
@lexer::members {
private ANTLRJavaHelper helper = null;

public JavaAnalyseLexer(CharStream stream, ANTLRJavaHelper helper)
{
	this(stream);
	this.helper = helper;
}
}

file	:	package_decl import_decl* (class_decl (constructor_decl | method_decl)*)* ;

package_decl	:	'package' package_name SEMICOLON {helper.setPackageName($package_name.text);};
import_decl	:	'import'  import_name SEMICOLON {helper.addImport($import_name.text);};
class_decl	:	MODIFIERS 'class' class_name extended? implemented? {helper.addClass($class_name.text, $MODIFIERS.text, $extended.text, $implemented.text);};
constructor_decl:	MODIFIERS ID '(' ~(')')* ')' {helper.addMethod($ID.text, $MODIFIERS.text);};
method_decl	:	MODIFIERS ID ID '(' ~(')')* ')' {helper.addMethod($ID.text, $MODIFIERS.text);};

package_name	:	ID (DOT ID)*;
import_name	:	(ID DOT)* (ID|STAR);
class_name	:	(ID DOT)* ID;

extended:	'extends' ID;
implemented:	'implemented' ID (COMMA ID)*;

/* 
==========================================================================
The next rules belong to the lexer and identify the different operants, 
groups into tokens and removes irrelevant content like comments and 
whitespaces.
==========================================================================
*/

MODIFIERS 
	:	(MODIFIER WS)*;

fragment
MODIFIER
	:	VISIBILITY
	|	'final'
	|	'static'
	|	'transient'
	;

fragment
VISIBILITY
	:	'public'
	|	'private'
	|	'protected'
	;

BLOCK_BEGIN
	:	'{' {helper.addBlockBegin();}
	;
	
BLOCK_END
	:	'}' {helper.addBlockEnd();}
	;
	
PLUS	:	'+';
MINUS	:	'-';
SLASH	:	'/';
STAR	:	'*';

EQUAL	:	'==';
UNEQUAL	:	'!=';
ASSIGN	:	'=';

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
	
CLOSE_BRACKER
	:	')'
	;

SEMICOLON
	:	';';

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
LINEFEED:	'\n' {helper.incSlocCount();};

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
