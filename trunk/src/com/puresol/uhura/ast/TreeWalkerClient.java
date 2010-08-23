package com.puresol.uhura.ast;

public interface TreeWalkerClient {

	public WalkingAction trigger(SyntaxTree syntaxTree);

}
