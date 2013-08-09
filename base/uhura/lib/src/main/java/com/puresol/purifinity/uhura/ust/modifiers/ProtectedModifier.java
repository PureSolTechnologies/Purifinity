package com.puresol.purifinity.uhura.ust.modifiers;

public class ProtectedModifier extends AbstractModifier {

    private static final long serialVersionUID = 729411154443449805L;

    public ProtectedModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Protected Modifier";
    }

}
