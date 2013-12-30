package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.Tree;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;

public class AnalysisFileTree implements Tree<AnalysisFileTree>, Serializable {

	private static final long serialVersionUID = 294965469645813244L;

	private final List<AnalysisFileTree> children = new ArrayList<AnalysisFileTree>();

	private final AnalysisFileTree parent;
	private final String name;
	private final HashId hashId;
	private final boolean file;
	private final List<AnalyzedCode> analyzedCodes;

	public AnalysisFileTree(AnalysisFileTree parent, String name,
			HashId hashId, boolean file, List<AnalyzedCode> analyzedCodes) {
		super();
		this.parent = parent;
		this.name = name;
		this.hashId = hashId;
		this.file = file;
		this.analyzedCodes = analyzedCodes;
		if (parent != null) {
			parent.children.add(this);
		}
	}

	@Override
	public final AnalysisFileTree getParent() {
		return parent;
	}

	@Override
	public final boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public final List<AnalysisFileTree> getChildren() {
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

	public final List<AnalyzedCode> getAnalyses() {
		return analyzedCodes;
	}

	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder(getName());
		if (file) {
			builder.append(" (file) / ");
		} else {
			builder.append(" (dir)  / ");
		}
		if (hashId != null) {
			builder.append(hashId.toString());
		} else {
			builder.append("<no hash>");
		}
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
		AnalysisFileTree current = this;
		File file;
		file = new File(current.getName());
		current = current.getParent();
		while (current != null) {
			AnalysisFileTree parent = current.getParent();
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
			TreeVisitor<AnalysisFileTree> {

		private AnalysisFileTree found = null;
		private final File file;

		public FindFileTreeVisitor(File file) {
			this.file = file;
		}

		@Override
		public WalkingAction visit(AnalysisFileTree tree) {
			if (tree.getPathFile(false).equals(file)) {
				found = tree;
				return WalkingAction.ABORT;
			}
			return WalkingAction.PROCEED;
		}

		public AnalysisFileTree getFound() {
			return found;
		}
	}

	public AnalysisFileTree findFile(final File file2) {
		FindFileTreeVisitor visitor = new FindFileTreeVisitor(file2);
		TreeWalker<AnalysisFileTree> walker = new TreeWalker<AnalysisFileTree>(
				this);
		walker.walk(visitor);
		return visitor.getFound();
	}

	public AnalysisFileTree getChild(String name) {
		for (AnalysisFileTree child : children) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

}
