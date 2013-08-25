package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.grammar.token.Visibility;

public class LineBreak extends AbstractToken {

	private static final long serialVersionUID = 5937521937552494525L;

	public LineBreak(String name, String originalName, String content,
			int line, int column) {
		super(name, originalName, content, line, column, Visibility.IGNORED);
	}

}
