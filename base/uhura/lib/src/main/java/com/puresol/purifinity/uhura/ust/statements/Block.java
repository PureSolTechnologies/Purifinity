package com.puresol.purifinity.uhura.ust.statements;

import java.util.List;

public class Block extends AbstractStatement implements Statement {

	private static final long serialVersionUID = 5873944700707434142L;

	public Block(String originalSymbol, List<Statement> statements) {
		super(originalSymbol);
		for (Statement statement : statements) {
			addChild(statement);
		}
	}

	@Override
	public String getName() {
		return "Block Statement";
	}

}
