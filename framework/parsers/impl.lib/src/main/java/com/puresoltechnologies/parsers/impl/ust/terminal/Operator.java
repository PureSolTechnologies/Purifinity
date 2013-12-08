package com.puresoltechnologies.parsers.impl.ust.terminal;


public class Operator extends AbstractTerminal {

	private static final long serialVersionUID = 4183343777764840495L;

	public Operator(String name, String content, int line, int column) {
		super(name, content, line, column, true);
	}

}
