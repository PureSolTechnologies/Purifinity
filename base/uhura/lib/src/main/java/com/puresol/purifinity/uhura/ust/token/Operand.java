package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.grammar.token.Visibility;

public class Operand extends AbstractToken {

	private static final long serialVersionUID = -2543583657080248353L;

	public Operand(String name, String originalName, String content, int line,
			int column) {
		super(name, originalName, content, line, column, Visibility.VISIBLE);
	}

}
