package com.puresol.uhura.ust.types;


public class StringType extends Type {

    private static final long serialVersionUID = 7805628949907717406L;

    public StringType(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "String Type";
    }

}
