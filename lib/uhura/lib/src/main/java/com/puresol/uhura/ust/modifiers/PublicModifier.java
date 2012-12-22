package com.puresol.uhura.ust.modifiers;


public class PublicModifier extends Modifier {

    private static final long serialVersionUID = -5800466279299023817L;

    public PublicModifier(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Public Modifier";
    }

}