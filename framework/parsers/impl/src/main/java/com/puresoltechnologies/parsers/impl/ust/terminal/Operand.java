package com.puresoltechnologies.parsers.impl.ust.terminal;


public class Operand extends AbstractTerminal {

	private static final long serialVersionUID = -2543583657080248353L;

	public Operand(String name, String content, int line, int column) {
		super(name, content, line, column, true);
	}

}