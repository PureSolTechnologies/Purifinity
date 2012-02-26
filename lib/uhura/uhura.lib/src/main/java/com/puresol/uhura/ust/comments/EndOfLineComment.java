package com.puresol.uhura.ust.comments;

public class EndOfLineComment extends Comment {

    private static final long serialVersionUID = 8664830280231683442L;

    private final String commentText;

    public EndOfLineComment(String originalSymbol, String commentText) {
	super(originalSymbol);
	this.commentText = commentText;
    }

    @Override
    public String getName() {
	return "End-Of-Line Comment: " + commentText;
    }

}
