package com.puresol.purifinity.uhura.ust.types;

public class CharacterType extends AbstractType {

    private static final long serialVersionUID = 7805628949907717406L;

    public CharacterType(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Character Type";
    }

}
