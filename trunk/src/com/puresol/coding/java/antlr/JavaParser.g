parser grammar JavaParser;

options {
tokenVocab=JavaLexer;
backtrack=true;
memoize=true;
}

@header {
package com.puresol.coding.java.antlr.output;

import com.puresol.coding.ParserHelper;
}

@members {
private ParserHelper helper = new ParserHelper(this);

public ParserHelper getParserHelper() {
	return helper;
}
}

file	:	package_def import_def* class_def*;

package_def	
	:	package_ package_name semicolon 
	;

import_def
	:	import_ import_name semicolon
	;
	
class_def
	:	annotation* modifier* class_ id (extends_ class_name)? (implements_ class_name (comma class_name)*)? class_block 
		{helper.registerRange("class", $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

constructor_def
	:	annotation* modifier* id open_bracket argument_def close_bracket code
		{helper.registerRange("constructor", $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

method_def
	:	annotation* modifier* variable_type id open_bracket argument_def close_bracket code
		{helper.registerRange("method", $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;
	
field_def
	:	annotation* modifier* variable_type variable_name (assign value)? semicolon
	;

argument_def
	:	(variable_def (comma variable_def)*)?
	;

variable_def
	:	annotation* variable_type variable_name (assign value)? 
	;

modifier:	public_
	|	private_
	|	protected_
	|	static_
	|	final_
	|	transient_
	;


class_block
	:	block_begin (class_def | constructor_def | method_def | field_def)* block_end
	;
	
method_call
	:	method_name open_bracket arguments close_bracket (dot id open_bracket arguments close_bracket)*
	;

annotation
	:	annotation_name (open_bracket value (comma value)* close_bracket)?
	;

value	:
	(	open_bracket
	|	constant
	|	class_name dot class_
	|	variable_name
	|	method_call
	|	new_class
	) 	(operator value close_bracket?)*
	;

constant:	int_const
	|	string_const
	|	float_const
	|	char_const
	|	null_const
	|	boolean_const
	;
	
new_class
	:	new_ class_name open_bracket arguments close_bracket
	;
	
arguments
	:	(value (comma value)*)?
	;

code	:	statement
	|	block_begin statement* block_end
	;
	
statement
	:	variable_assignment semicolon
	|	variable_def semicolon
	|	method_call semicolon
	|	try_catch
	;
	
variable_assignment
	:	(this_ dot | super_ dot)? variable_name assign value 
	;

try_catch
	:	try_ code (catch_ OPEN_BRACKET id id CLOSE_BRACKET code)+ (finally_ OPEN_BRACKET id id CLOSE_BRACKET code)?
	;


variable_type
	:	class_name array?
	|	void_
	;

id	:	ID {helper.registerOperant($text);};

operator:	PLUS {helper.registerOperator($text);}
	|	MINUS {helper.registerOperator($text);}
	|	STAR {helper.registerOperator($text);}
	|	SLASH {helper.registerOperator($text);}
	;

////////////////////////
// Constants...
////////////////////////

int_const
	:	INT {helper.registerOperant($text);};
string_const
	:	STRING {helper.registerOperant($text);};
float_const
	:	FLOAT {helper.registerOperant($text);};
char_const
	:	CHAR {helper.registerOperant($text);};
null_const
	:	NULL {helper.registerOperant($text);};
boolean_const	
	:	BOOLEAN {helper.registerOperant($text);};

////////////////////////
// Names...
////////////////////////

package_name
	:	(ID DOT)* ID {helper.registerOperant($text);}
	;

import_name
	:	(ID DOT)+ (ID|STAR) {helper.registerOperant($text);}
	;

class_name
	:	(ID DOT)* ID {helper.registerOperant($text);}
	;

method_name
	:	(ID DOT)* ID {helper.registerOperant($text);}
	;

variable_name
	:	name array?
	;
annotation_name
	:	AT name {helper.registerOperant($text);};
name	:	(ID DOT)* ID {helper.registerOperant($text);};

array	:	open_rect_bracket value? close_rect_bracket;

////////////////////////
// Reserved keywords...
////////////////////////

package_
	:	PACKAGE {helper.registerOperator($text);};	
import_
	:	IMPORT {helper.registerOperator($text);};	
class_	:	CLASS {helper.registerOperator($text);};
extends_:	EXTENDS {helper.registerOperator($text);};
implements_
	:	IMPLEMENTS {helper.registerOperator($text);};
this_	:	THIS {helper.registerOperant($text);};
super_	:	SUPER {helper.registerOperant($text);};
void_	:	VOID {helper.registerOperant($text);};
public_	:	PUBLIC {helper.registerOperator($text);};
private_:	PRIVATE {helper.registerOperator($text);};
protected_
	:	PROTECTED {helper.registerOperator($text);};
static_	:	STATIC {helper.registerOperator($text);};
final_	:	FINAL {helper.registerOperator($text);};
transient_
	:	TRANSIENT {helper.registerOperator($text);};
new_	:	NEW {helper.registerOperator($text);};
try_	:	TRY {helper.registerOperator($text);};
catch_	:	CATCH {helper.registerOperator($text, 1);};
finally_:	FINALLY {helper.registerOperator($text, 1);};

////////////////////////
// Symbols...
////////////////////////

semicolon
	:	SEMICOLON {helper.registerOperator($text);};
comma	:	COMMA {helper.registerOperator($text);};
dot	:	DOT {helper.registerOperator($text);};
assign	:	ASSIGN {helper.registerOperator($text);};
block_begin
	:	OPEN_CURLY_BRACKET {helper.registerOperator("{}");};
block_end
	:	CLOSE_CURLY_BRACKET {helper.registerOperator("");};
open_bracket
	:	OPEN_BRACKET {helper.registerOperator("()");};
close_bracket
	:	CLOSE_BRACKET  {helper.registerOperator("");};
open_rect_bracket
	:	OPEN_RECT_BRACKET {helper.registerOperator("[]");};
close_rect_bracket
	:	CLOSE_RECT_BRACKET {helper.registerOperator("");};
