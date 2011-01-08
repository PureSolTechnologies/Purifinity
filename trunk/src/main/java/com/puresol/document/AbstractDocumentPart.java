package com.puresol.document;

import java.util.ArrayList;
import java.util.List;

/**
 * This implementation is an abstract one for document part. All document parts
 * should inherit this abstract class to be able to be bound to the document
 * tree.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractDocumentPart implements DocumentPart {

	private final DocumentPart parent;
	private final String name;
	private final List<DocumentPart> children = new ArrayList<DocumentPart>();

	public AbstractDocumentPart(AbstractDocumentPart parent, String name) {
		super();
		this.parent = parent;
		if (parent != null) {
			parent.addChild(this);
		}
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DocumentPart getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasChildren() {
		return children.size() > 0;
	}

	/**
	 * This method adds a new child to the childrens list. This is only done by
	 * each object itself during construction. This is used to keep the double
	 * reference (upward and downward) coherent.
	 * 
	 * @param part
	 */
	private void addChild(DocumentPart part) {
		children.add(part);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentPart> getChildren() {
		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

}
