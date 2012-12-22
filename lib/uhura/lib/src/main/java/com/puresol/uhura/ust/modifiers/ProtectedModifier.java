package com.puresol.uhura.ust.modifiers;

public class ProtectedModifier extends Modifier {

    private static final long serialVersionUID = 729411154443449805L;

    public ProtectedModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Protected Modifier";
    }

}