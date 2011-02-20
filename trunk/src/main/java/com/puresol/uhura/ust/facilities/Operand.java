package com.puresol.uhura.ust.facilities;

import java.util.ArrayList;
import java.util.List;

import com.puresol.uhura.ust.comments.Comment;

/**
 * This class represents an implementation of an operand. This is needed to
 * calculate Halstead metrics and other tasks where operators and operands need
 * to be distinguished.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class Operand extends CompilerRelevantElement {

	private static final long serialVersionUID = 1637318416068127842L;

	private final List<Comment> comments = new ArrayList<Comment>();

	public Operand(String originalSymbol) {
		super(originalSymbol);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public List<Comment> getComments() {
		return comments;
	}
}
