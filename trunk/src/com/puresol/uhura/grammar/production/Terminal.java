package com.puresol.uhura.grammar.production;

public class Terminal extends AbstractConstruction {

	private static final long serialVersionUID = 9050440704073872898L;

	public Terminal(String name) {
		super(name, "", ConstructionType.TERMINAL);
	}

	public Terminal(String name, String text) {
		super(name, text, ConstructionType.TERMINAL);
	}

}
