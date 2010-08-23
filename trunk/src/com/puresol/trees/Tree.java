package com.puresol.trees;

import java.util.List;

public interface Tree<T> {

	public T getParent();

	public List<T> getChildren();

}
