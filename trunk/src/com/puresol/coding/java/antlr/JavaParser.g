parser grammar JavaParser;

options {
tokenVocab=JavaLexer;
}

@header {
package com.puresol.coding.java.antlr.output;
}

file	:	PACKAGE (ID DOT)* ID;
