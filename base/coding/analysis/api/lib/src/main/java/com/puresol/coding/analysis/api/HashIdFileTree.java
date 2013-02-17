package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.trees.Tree;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;

public final class HashIdFileTree implements Tree<HashIdFileTree>, Serializable {

	private static final long serialVersionUID = -3846025775548502693L;

	private final List<HashIdFileTree> children = new ArrayList<HashIdFileTree>();

	private final HashIdFileTree parent;
	private final String name;
	private final HashId hashId;
	private final boolean file;

	public HashIdFileTree(HashIdFileTree parent, String name, HashId hashId,
			boolean file) {
		super();
		this.parent = parent;
		this.name = name;
		this.hashId = hashId;
		this.file = file;
		if (parent != null) {
			parent.children.add(this);
		}
	}

	@Override
	public final HashIdFileTree getParent() {
		return parent;
	}

	@Override
	public final boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public final List<HashIdFileTree> getChildren() {
		return children;
	}

	@Override
	public final String getName() {
		return name;
	}

	public final HashId getHashId() {
		return hashId;
	}

	public final boolean isFile() {
		return file;
	}

	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder(getName());
		if (file) {
			builder.append(" (file) / ");
		} else {
			builder.append(" (dir)  / ");
		}
		builder.append(hashId.toString());
		return builder.toString();
	}

	/**
	 * This method returns the Path of the node as File.
	 * 
	 * @param includeRootElement
	 *            specifies whether the root element of the tree needs to be
	 *            added to the returned path, too, or not. This would make the
	 *            return value an absolute path. True adds the root element.
	 *            False, does not.
	 * @return
	 */
	public File getPathFile(boolean includeRootElement) {
		if (getParent() == null) {
			return new File("");
		}
		HashIdFileTree current = this;
		File file;
		file = new File(current.getName());
		current = current.getParent();
		while (current != null) {
			HashIdFileTree parent = current.getParent();
			if ((includeRootElement) || (parent != null)) {
				if (!current.getName().isEmpty()) {
					file = new File(current.getName(), file.getPath());
				}
			}
			current = parent;
		}
		return file;
	}

	private static class FindFileTreeVisitor implements
			TreeVisitor<HashIdFileTree> {

		private HashIdFileTree found = null;
		private final File file;

		public FindFileTreeVisitor(File file) {
			this.file = file;
		}

		@Override
		public WalkingAction visit(HashIdFileTree tree) {
			if (tree.getPathFile(false).equals(file)) {
				found = tree;
				return WalkingAction.ABORT;
			}
			return WalkingAction.PROCEED;
		}

		public HashIdFileTree getFound() {
			return found;
		}
	}

	public HashIdFileTree findFile(final File file2) {
		FindFileTreeVisitor visitor = new FindFileTreeVisitor(file2);
		TreeWalker<HashIdFileTree> walker = new TreeWalker<HashIdFileTree>(this);
		walker.walk(visitor);
		return visitor.getFound();
	}
}