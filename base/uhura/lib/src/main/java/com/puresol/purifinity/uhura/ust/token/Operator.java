package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.grammar.token.Visibility;

public class Operator extends AbstractToken {

	private static final long serialVersionUID = 4183343777764840495L;

	public Operator(String name, String originalName, String content, int line,
			int column) {
		super(name, originalName, content, line, column, Visibility.VISIBLE);
	}

}
