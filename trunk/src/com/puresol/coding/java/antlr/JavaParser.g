parser grammar JavaParser;

options {
tokenVocab=JavaLexer;
}

@header {
package com.puresol.coding.java.antlr.output;
}

file	:	package_def imports class_def*;

/* PACKAGE */
package_def	:	PACKAGE package_name SEMICOLON {System.out.println($text);};

fragment
package_name
	:	(ID DOT)* ID
	;

/* IMPORTS */
imports :	import_def*;

import_def
	:	IMPORT import_name SEMICOLON {System.out.println($text);}
	;
	
fragment
import_name
	:	(ID DOT)+ (ID|STAR)
	;
	
class_def
	:	modifiers CLASS ID (EXTENDS class_name)? (IMPLEMENTS class_name (COMMA class_name)*)? {System.out.println($text);}
	;

fragment
class_name
	:	(ID DOT)* ID
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
