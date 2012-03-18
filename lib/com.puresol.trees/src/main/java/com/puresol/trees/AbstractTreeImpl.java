package com.puresol.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is an abstract, immutable implementation of a Tree<T>.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type of the nodes. T needs to implement AbstractTreeImpl.
 */
public class AbstractTreeImpl<T extends AbstractTreeImpl<T>> implements
	Tree<T>, Iterable<T> {

    private final T parent;
    private final List<T> children = new ArrayList<T>();
    private final String name;

    /**
     * This is the initial value constructor.
     * 
     * @param parent
     *            is the parent where this node is to be added to.
     * @param name
     *            is the identifier of this node.
     */
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

    private void addChild(T child) {
	children.add(child);
    }

    @Override
    public T getParent() {
	return parent;
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

    @Override
    public Iterator<T> iterator() {
	@SuppressWarnings("unchecked")
	T t = (T) this;
	return new TreeIterator<T>(t);
    }
}
