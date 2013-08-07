package com.puresol.purifinity.uhura.ust.comments;


public class BlockComment extends AbstractComment {

    private static final long serialVersionUID = -6042748959476468119L;

    private final String commentText;

    public BlockComment(String originalSymbol, String commentText) {
	super(originalSymbol);
	this.commentText = commentText;
    }

    @Override
    public String getName() {
	return "Block Comment: " + commentText;
    }

}
