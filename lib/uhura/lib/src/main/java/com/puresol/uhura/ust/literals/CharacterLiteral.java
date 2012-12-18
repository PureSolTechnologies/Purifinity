package com.puresol.uhura.ust.literals;

public class CharacterLiteral extends NumericalLiteral {

    private static final long serialVersionUID = -5622529938750219170L;

    private final char value;

    public CharacterLiteral(String originalSymbol, char value) {
	super(originalSymbol);
	this.value = value;
    }

    public char getValue() {
	return value;
    }

    @Override
    public String getName() {
	return "Character Literal: " + value;
    }
}
