package com.puresol.uhura.ust.comments;

import javax.i18n4java.Translator;

public class BlockComment extends Comment {

	private static final long serialVersionUID = -6042748959476468119L;

	private static final Translator translator = Translator
			.getTranslator(BlockComment.class);

	private final String commentText;

	public BlockComment(String originalSymbol, String commentText) {
		super(originalSymbol);
		this.commentText = commentText;
	}

	@Override
	public String getName() {
		return translator.i18n("Block Comment: {0}", commentText);
	}

}
