package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.grammar.token.Visibility;

public class WhiteSpace extends AbstractToken {

	private static final long serialVersionUID = -4930916064232367782L;

	public WhiteSpace(String name, String originalName, String content,
			int line, int column) {
		super(name, originalName, content, line, column, Visibility.IGNORED);
	}

}
