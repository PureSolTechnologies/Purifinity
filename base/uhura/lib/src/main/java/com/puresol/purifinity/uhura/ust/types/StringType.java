package com.puresol.purifinity.uhura.ust.types;


public class StringType extends AbstractType {

    private static final long serialVersionUID = 7805628949907717406L;

    public StringType(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "String Type";
    }

}
