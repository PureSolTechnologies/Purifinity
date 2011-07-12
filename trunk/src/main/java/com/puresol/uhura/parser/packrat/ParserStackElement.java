package com.puresol.uhura.parser.packrat;

import com.puresol.uhura.parser.ParserTree;

class ParserStackElement {

	private final ParserTree tree;
	private final ParserProgress progress;

	public ParserStackElement(ParserTree tree, ParserProgress progress) {
		super();
		this.tree = tree;
		this.progress = progress;
	}

	public ParserProgress getProgress() {
		return progress;
	}

	public ParserTree getParserTree() {
		return tree;
	}

}
