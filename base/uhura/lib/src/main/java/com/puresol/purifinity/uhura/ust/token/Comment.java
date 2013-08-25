package com.puresol.purifinity.uhura.ust.token;

import com.puresol.purifinity.uhura.grammar.token.Visibility;

public class Comment extends AbstractToken {

	private static final long serialVersionUID = -5436207235168500593L;

	public Comment(String originalName, String content, int line, int column) {
		super("Comment", originalName, content, line, column,
				Visibility.IGNORED);
	}

}
