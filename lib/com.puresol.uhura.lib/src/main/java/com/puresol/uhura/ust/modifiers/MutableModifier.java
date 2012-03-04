package com.puresol.uhura.ust.modifiers;


public class MutableModifier extends Modifier {

    private static final long serialVersionUID = -4713670533029137280L;

    public MutableModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Mutable Modifier";
    }

}
