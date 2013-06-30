package com.puresol.purifinity.uhura.ust.modifiers;

public class VolatileModifier extends Modifier {

    private static final long serialVersionUID = -4713670533029137280L;

    public VolatileModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Volatile Modifier";
    }

}
