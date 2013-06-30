package com.puresol.purifinity.uhura.ust.literals;


public class NullLiteral extends NumericalLiteral {

    private static final long serialVersionUID = -5622529938750219170L;

    public NullLiteral(String originalSymbol) {
	super(originalSymbol);
    }

    @Override
    public String getName() {
	return "Null Literal";
    }

}
