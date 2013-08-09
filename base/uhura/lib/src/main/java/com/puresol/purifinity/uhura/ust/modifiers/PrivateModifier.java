package com.puresol.purifinity.uhura.ust.modifiers;


public class PrivateModifier extends AbstractModifier {

    private static final long serialVersionUID = -4713670533029137280L;

    public PrivateModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Private Modifier";
    }

}
