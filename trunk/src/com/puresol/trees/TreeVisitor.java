package com.puresol.trees;

public interface TreeVisitor<T extends Tree<T>> {

	public WalkingAction visit(T syntaxTree);

}
