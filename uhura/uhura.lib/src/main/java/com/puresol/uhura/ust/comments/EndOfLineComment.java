package com.puresol.uhura.ust.comments;

import javax.i18n4java.Translator;

public class EndOfLineComment extends Comment {

	private static final long serialVersionUID = 8664830280231683442L;

	private static final Translator translator = Translator
			.getTranslator(EndOfLineComment.class);

	private final String commentText;

	public EndOfLineComment(String originalSymbol, String commentText) {
		super(originalSymbol);
		this.commentText = commentText;
	}

	@Override
	public String getName() {
		return translator.i18n("End-Of-Line Comment: {0}", commentText);
	}

}
