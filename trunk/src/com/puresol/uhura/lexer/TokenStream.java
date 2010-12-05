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

	public String getCodeSample(int position) {
		StringBuffer buffer = new StringBuffer();
		int startPosition = Math.max(0, position - 10);
		if (startPosition >= size()) {
			startPosition = 0;
		}
		int stopPosition = Math.min(size() - 1, startPosition + 20);
		if (startPosition > 0) {
			buffer.append("[...]");
		}
		for (int i = startPosition; i <= stopPosition; i++) {
			if (i == position) {
				buffer.append(" >>> ");
			}
			buffer.append(get(i).getText());
			if (i == position) {
				buffer.append(" <<< ");
			}
		}
		if (stopPosition < size() - 1) {
			buffer.append("[...]");
		}
		return buffer.toString();
	}

}
