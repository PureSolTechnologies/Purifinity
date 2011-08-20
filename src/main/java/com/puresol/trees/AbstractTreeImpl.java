package com.puresol.trees;

import java.util.ArrayList;
import java.util.List;

import com.puresol.trees.Tree;

public class AbstractTreeImpl<T extends AbstractTreeImpl<T>> implements Tree<T> {

	private final T parent;
	private final List<T> children = new ArrayList<T>();
	private final String name;

	public AbstractTreeImpl(T parent, String name) {
		super();
		this.parent = parent;
		if (parent != null) {
			@SuppressWarnings("unchecked")
			T t = (T) this;
			parent.addChild(t);
		}
		this.name = name;
	}

	@Override
	public T getParent() {
		return parent;
	}

	private void addChild(T child) {
		children.add(child);
	}

	@Override
	public boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	public List<T> getChildren() {
		return children;
	}

	@Override
	public String getName() {
		return name;
	}
}
