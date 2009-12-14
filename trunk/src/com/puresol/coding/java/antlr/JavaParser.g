parser grammar JavaParser;

options {
tokenVocab=JavaLexer;
backtrack=true;
//memoize=true;
}

@header {
package com.puresol.coding.java.antlr.output;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ParserHelper;
}

@members {
private ParserHelper helper = new ParserHelper(this);

public ParserHelper getParserHelper() {
	return helper;
}
}

file	:	package_def import_def* (class_def | enum_def | interface_def | annotation_def)+
	;

package_def	
	:	package_ package_name semicolon 
	;

import_def
	:	import_ import_name semicolon
	;
	
class_def
	:	annotation* class_modifier* class_ id generic? (extends_ class_name)? (implements_ class_name (comma class_name)*)? class_block 
		{helper.registerRange(CodeRangeType.CLASS, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

enum_def
	:	annotation* class_modifier* enum_ id generic? (extends_ class_name)? enum_block 
		{helper.registerRange(CodeRangeType.ENUMERATION, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

interface_def
	:	annotation* class_modifier* interface_ id generic? (extends_ class_name)? interface_block 
		{helper.registerRange(CodeRangeType.INTERFACE, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

annotation_def
	:	annotation* class_modifier* annotation_ id interface_block 
		{helper.registerRange(CodeRangeType.INTERFACE, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

static_init
	:	static_ block_begin code* block_end semicolon?
	;

constructor_def
	:	annotation* method_modifier* id open_bracket argument_def close_bracket (throws_ class_name (comma class_name)*)? code
		{helper.registerRange(CodeRangeType.CONSTRUCTOR, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;

method_def
	:	annotation* method_modifier* variable_type id open_bracket argument_def close_bracket (throws_ class_name (comma class_name)*)? code
		{helper.registerRange(CodeRangeType.METHOD, $id.text, $text, retval.start.getTokenIndex(), input.LT(-1).getTokenIndex());}
	;
	
field_def
	:	annotation* field_modifier* variable_type variable_name (assign value)? semicolon
	;

argument_def
	:	(variable_type (dot dot dot)? variable_name (comma variable_type (dot dot dot)? variable_name)*)?
	;

variable_def
	:	annotation* variable_type variable_name (assign value)? 
	;

class_modifier
	:	public_
	|	private_
	|	protected_
	|	static_
	|	abstract_
	|	final_
	;

method_modifier
	:	public_
	|	private_
	|	protected_
	|	static_
	|	final_
	|	transient_
	|	synchronized_
	|	abstract_
	;

field_modifier
	:	public_
	|	private_
	|	protected_
	|	static_
	|	transient_
	|	volatile_
	;

variable_modifier
	:	final_
	;

class_block
	:	block_begin (class_def | static_init | constructor_def | method_def | field_def)* block_end semicolon? 
	;

enum_block
	:	block_begin (class_def | constructor_def | method_def | field_def | enum_content)* block_end semicolon?
	;

enum_content
	:	id (open_bracket arguments close_bracket)? (comma id (open_bracket arguments close_bracket)?)* semicolon
	;
	
interface_block
	:	block_begin (annotation* method_modifier* variable_type id open_bracket argument_def close_bracket (throws_ class_name (comma class_name)*)? semicolon)* block_end semicolon?
	;
	
method_call
	:	method_name open_bracket arguments close_bracket (dot (id | class_) (open_bracket arguments close_bracket)?)*
	;

annotation
	:	annotation_name (open_bracket value (comma value)* close_bracket)?
	;

generic	:	LT (variable_type | question_) (comma (variable_type | question_))* GT {helper.registerOperator("<>");}
	;


value	: cast? left_unary? 
		(single_value | open_bracket value close_bracket) 
		right_unary? 
		(binary_operator value)? 
		(question_ value colon value)?
	| left_unary? 
		(single_value | open_bracket value close_bracket) 
		right_unary? 
		(binary_operator value)? 
		(question_ value colon value)?
	;

single_value
	:	method_call
	| 	variable_assignment
	|	constant // constant before variable_name due to .class(!)
	|	((this_ | super_) dot)? variable_name
	|	new_class
	|	this_
	|	super_
	;

constant:	class_const
	|	hex_long_const
	|	hex_const
	|	long_const
	|	int_const
	|	string_const
	|	float_const
	|	char_const
	|	null_const
	|	boolean_const
	|	array_const
	;
	
new_class
	:	new_ variable_type array_const? (open_bracket arguments close_bracket)? 
	;
	
arguments
	:	(value (comma value)*)?
	;

code	:	statement
	|	block_begin code* block_end semicolon?
	;
	
statement
	:	label	
	|	variable_assignment semicolon
	|	variable_def semicolon
	|	method_call semicolon
	| 	semicolon
	|	return_statement
	|	continue_
	|	break_
	|	for_loop
	|	while_loop
	|	do_loop semicolon
	|	switch_case
	|	if_else
	|	try_catch
	|	synchronized_block
	|	throw_ value semicolon
	|	left_unary? variable_name right_unary?
	;

statement_wosemicolon
	:	label
	|	variable_assignment
	|	variable_def
	|	method_call	
	|	return_statement
	|	continue_
	|	break_
	|	for_loop
	|	while_loop
	|	do_loop
	|	switch_case
	|	if_else
	|	try_catch
	|	synchronized_block
	|	throw_ value semicolon
	|	left_unary? variable_name right_unary?
	;

return_statement	
	:	return_ value? semicolon
	;
	
variable_assignment
	:	(this_ dot | super_ dot)? variable_name assign value 
	;

for_loop:	for_ OPEN_BRACKET (variable_def (comma variable_def)*)? semicolon value? semicolon (statement_wosemicolon (comma statement_wosemicolon)*)? CLOSE_BRACKET code
	|	for_ OPEN_BRACKET variable_type variable_name colon value CLOSE_BRACKET code
	;
	
while_loop
	:	while_ OPEN_BRACKET value CLOSE_BRACKET code
	;
	
do_loop	:	do_ code while_ OPEN_BRACKET value CLOSE_BRACKET
	;

switch_case
	:	switch_ OPEN_BRACKET value CLOSE_BRACKET block_begin 
		(
			(case_  (constant | variable_name) colon)+ (code | statement)* (break_ semicolon)?
		|
			default_ colon (code | statement)*
		)+
		block_end
	;

if_else	:	if_ OPEN_BRACKET value CLOSE_BRACKET code (else_ code)?
	;

try_catch
	:	try_ code (catch_ OPEN_BRACKET id id CLOSE_BRACKET code)* (finally_ code)?
	;
synchronized_block
	:	synchronized_ code
	;

label	:	id colon;

cast	:	OPEN_BRACKET variable_type CLOSE_BRACKET {helper.registerOperator("(cast)");}
	;

variable_type
	:	variable_modifier* primitive array?
	|	variable_modifier* class_name array?
	|	variable_modifier* void_
	;

id	:	ID {helper.registerOperant($text);};

binary_operator
	:	plus
	|	minus
	|	star
	|	SLASH {helper.registerOperator($text);}
	|	PERCENT {helper.registerOperator($text);}
	|	EQUAL {helper.registerOperator($text);}
	|	UNEQUAL {helper.registerOperator($text);}
	|	LT {helper.registerOperator($text);}
	|	GT {helper.registerOperator($text);}
	|	LE {helper.registerOperator($text);}
	|	GE {helper.registerOperator($text);}
	|	LOGICAL_OR {helper.registerOperator($text);}
	|	BIT_OR {helper.registerOperator($text);}
	|	LOGICAL_AND {helper.registerOperator($text);}
	|	BIT_AND {helper.registerOperator($text);}
	;

left_unary	
	:	INC {helper.registerOperator($text);}
	|	DEC {helper.registerOperator($text);}
	|	NOT {helper.registerOperator($text);}
	|	MINUS {helper.registerOperator($text);}
	|	PLUS {helper.registerOperator($text);}
	;

right_unary	
	:	INC {helper.registerOperator($text);}
	|	DEC {helper.registerOperator($text);}
	|	NOT {helper.registerOperator($text);}
	;

primitive
	:	BOOLEAN {helper.registerOperant($text);}
	|	BYTE {helper.registerOperant($text);}
	|	CHAR {helper.registerOperant($text);}
	|	SHORT {helper.registerOperant($text);}
	|	INTEGER {helper.registerOperant($text);}
	|	LONG {helper.registerOperant($text);}
	|	FLOAT {helper.registerOperant($text);}
	|	DOUBLE {helper.registerOperant($text);}
	;


////////////////////////
// Constants...
////////////////////////

int_const
	:	INT_CONST {helper.registerOperant($text);};
long_const
	:	LONG_CONST {helper.registerOperant($text);};
hex_const
	:	HEX_CONST {helper.registerOperant($text);};
hex_long_const
	:	HEX_LONG_CONST {helper.registerOperant($text);};
string_const
	:	STRING_CONST {helper.registerOperant($text);};
float_const
	:	FLOAT_CONST {helper.registerOperant($text);};
char_const
	:	CHAR_CONST {helper.registerOperant($text);};
null_const
	:	NULL {helper.registerOperant($text);};
boolean_const	
	:	BOOL_CONST {helper.registerOperant($text);};
array_const
	:	block_begin value (comma value)* block_end;
class_const
	:	(class_name | primitive | void_) dot class_;	


////////////////////////
// Names...
////////////////////////

package_name
	:	(id dot)* id {helper.registerOperant($text);}
	;

import_name
	:	(id dot)+ (id | star)
	;

class_name
	:	id (dot id)* generic?
	;

method_name
	:	(
			(super_ | this_ | new_class) dot
		)?
		variable_name 
		(
			dot (class_ | variable_name)
		)*
	|	super_
	|	this_
	;

variable_name
	:	name array?
	;
annotation_name
	:	AT ID {helper.registerOperant($text);};
name	:	id (dot id)*;

array	:	(open_rect_bracket value? close_rect_bracket)+;

////////////////////////
// Reserved keywords...
////////////////////////

package_
	:	PACKAGE {helper.registerOperator($text);};	
import_
	:	IMPORT {helper.registerOperator($text);};	
class_	:	CLASS {helper.registerOperator($text);};
enum_	:	ENUM {helper.registerOperator($text);};
interface_
	:	INTERFACE {helper.registerOperator($text);};
annotation_
	:	AT INTERFACE {helper.registerOperator($text);};
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
volatile_	
	:	VOLATILE {helper.registerOperator($text);};
synchronized_
	:	SYNCHRONIZED {helper.registerOperator($text);};
abstract_
	:	ABSTRACT {helper.registerOperator($text);};
transient_
	:	TRANSIENT {helper.registerOperator($text);};
new_	:	NEW {helper.registerOperator($text);};
try_	:	TRY {helper.registerOperator($text);};
catch_	:	CATCH {helper.registerOperator($text, 1);};
finally_:	FINALLY {helper.registerOperator($text, 1);};
throws_	:	THROWS {helper.registerOperator($text);};
throw_	:	THROW {helper.registerOperator($text);};

for_	:	FOR {helper.registerOperator("for()", 1);};
while_	:	WHILE {helper.registerOperator("while()", 1);};
do_	:	DO {helper.registerOperator("do()", 1);};
if_	:	IF {helper.registerOperator("if()", 1);};
else_	:	ELSE {helper.registerOperator("else");};

switch_	:	SWITCH {helper.registerOperator("switch()");};
case_	:	CASE {helper.registerOperator("case", 1);};
default_:	DEFAULT {helper.registerOperator("default", 1);};

return_	:	RETURN {helper.registerOperator($text);};
break_	:	BREAK id {helper.registerOperator($BREAK.text);}
	|	BREAK {helper.registerOperator($text);}
	;
continue_
	:	CONTINUE {helper.registerOperator($text);};

////////////////////////
// Symbols...
////////////////////////

semicolon
	:	SEMICOLON {helper.registerOperator($text);};
comma	:	COMMA {helper.registerOperator($text);};
colon	:	COLON {helper.registerOperator($text);};
question_
	:	QUESTION {helper.registerOperator($text);};
plus	:	PLUS {helper.registerOperator($text);};
minus	:	MINUS {helper.registerOperator($text);};
star	:	STAR {helper.registerOperator($text);};
dot	:	DOT {helper.registerOperator($text);};
assign	:	ASSIGN {helper.registerOperator($text);}
	|	INCASSIGN {helper.registerOperator($text);}
	|	DECASSIGN {helper.registerOperator($text);}
	|	BITORASSIGN {helper.registerOperator($text);}
	|	BITANDASSIGN {helper.registerOperator($text);}
	;
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
