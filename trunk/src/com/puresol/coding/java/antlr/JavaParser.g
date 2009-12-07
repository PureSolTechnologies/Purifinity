parser grammar JavaParser;

options {
tokenVocab=JavaLexer;
backtrack=true;
}

@header {
package com.puresol.coding.java.antlr.output;

import com.puresol.coding.java.antlr.JavaParserHelper;
}

@members {
private JavaParserHelper helper = null;

public JavaParser(TokenStream stream, JavaParserHelper helper)
{
	this(stream);
	this.helper = helper;
}
}

file	:	package_def imports class_def*;

package_def	
	:	PACKAGE package_name SEMICOLON //{System.out.println($text);}
	;
	
fragment
package_name
	:	(ID DOT)* ID
	;

imports :	import_def*;

import_def
	:	IMPORT import_name SEMICOLON //{System.out.println($text);}
	;
	
fragment
import_name
	:	(ID DOT)+ (ID|STAR)
	;
	
class_def
	:	modifiers CLASS ID (EXTENDS class_name)? (IMPLEMENTS class_name (COMMA class_name)*)? block_begin class_block block_end {System.out.println("class_def " + $text);}
	;

modifiers
	:	modifier*
	;

modifier:	PUBLIC
	|	PRIVATE
	|	PROTECTED
	|	STATIC
	|	FINAL
	|	TRANSIENT
	;

block_begin
	:	OPEN_CURLY_BRACKET
	;
	
block_end
	:	CLOSE_CURLY_BRACKET
	;

class_block
	:	(class_def | constructor_def | method_def | field_def)*
	;
	
constructor_def
	:	modifiers ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end {System.out.println("constructor_def: " + $text);}
	;
	
method_def
	:	modifiers variable_type ID OPEN_BRACKET argument_def CLOSE_BRACKET block_begin code_block block_end {System.out.println("method_def: " + $text);}
	;
	
method_call
	:	method_name OPEN_BRACKET arguments CLOSE_BRACKET {System.out.println($text);}
	;

field_def
	:	modifiers variable_type variable_name (ASSIGN value)? SEMICOLON {System.out.println("field_def " + $text);}
	;


argument_def
	:	(variable_type variable_name (COMMA variable_type variable_name)*)?
	;
variable_def
	:	variable_type variable_name (ASSIGN value)? {System.out.println("variable_def " + $text);}
	;


value	:	constant		{System.out.println("constant " + $text);}
	|	class_name DOT CLASS	{System.out.println(".class " + $text);}
	|	variable_name		{System.out.println("variable_name " + $text);}
	|	method_call		{System.out.println("method_call " + $text);}
	|	new_class		{System.out.println("new_class " + $text);}
	;

constant:	INT
	|	STRING
	|	FLOAT
	|	CHAR
	|	NULL
	|	BOOLEAN
	;
	
new_class
	:	NEW class_name OPEN_BRACKET arguments CLOSE_BRACKET
	;
	
arguments
	:	(value (COMMA value)*)?
	;
	
code_block
	:	statement*
	;
	
statement
	:	variable_assignment SEMICOLON
	|	variable_def SEMICOLON
	|	method_call SEMICOLON
	|	try_catch
	;
	
variable_assignment
	:	(THIS DOT | SUPER DOT)? variable_name ASSIGN value 
	;

try_catch
	:	TRY block_begin code_block block_end (CATCH OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end)+ (FINALLY OPEN_BRACKET ID ID CLOSE_BRACKET block_begin code_block block_end)?
	;

fragment
class_name
	:	(ID DOT)* ID
	;

fragment
method_name
	:	(ID DOT)* ID
	;


fragment
variable_name
	:	(ID DOT)* ID array?
	;

fragment
variable_type
	:	class_name array?
	|	VOID
	;
	
fragment
array	:	OPEN_RECT_BRACKET value? CLOSE_RECT_BRACKET
	;
