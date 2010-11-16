package com.puresol.uhura.lexer;

import java.util.ArrayList;

public class TokenStream extends ArrayList<Token> {

	private static final long serialVersionUID = 4992743487086731635L;

	private final String name;

	public TokenStream(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
