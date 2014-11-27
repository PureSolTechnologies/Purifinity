package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.commons.trees.Tree;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class AnalysisFileTree implements Tree<AnalysisFileTree>, Serializable {

	private static final long serialVersionUID = 294965469645813244L;

	private final List<AnalysisFileTree> children = new ArrayList<AnalysisFileTree>();
	private final List<AnalysisInformation> analyzedCodes = new ArrayList<>();

	private AnalysisFileTree parent;
	private String name;
	private HashId hashId;
	private boolean file;
	private long size;
	private long sizeRecursive;
	private SourceCodeLocation sourceCodeLocation;

	public AnalysisFileTree() {
	}

	public AnalysisFileTree(AnalysisFileTree parent, String name,
			HashId hashId, boolean file, long size, long sizeRecursive,
			SourceCodeLocation sourceCodeLocation,
			List<AnalysisInformation> analyzedCodes) {
		super();
		this.parent = parent;
		this.name = name;
		this.hashId = hashId;
		this.file = file;
		this.size = size;
		this.sizeRecursive = sizeRecursive;
		this.sourceCodeLocation = sourceCodeLocation;
		if (analyzedCodes != null) {
			this.analyzedCodes.addAll(analyzedCodes);
		}
		if (parent != null) {
			parent.children.add(this);
		}
	}

	public List<AnalysisInformation> getAnalyzedCodes() {
		return analyzedCodes;
	}

	public void setAnalyzedCodes(List<AnalysisInformation> analyzedCodes) {
		this.analyzedCodes.clear();
		this.analyzedCodes.addAll(analyzedCodes);
	}

	public void setChildren(List<AnalysisFileTree> children) {
		this.children.clear();
		this.children.addAll(children);
	}

	public void setParent(AnalysisFileTree parent) {
		this.parent = parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHashId(HashId hashId) {
		this.hashId = hashId;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public void setSourceCodeLocation(SourceCodeLocation sourceCodeLocation) {
		this.sourceCodeLocation = sourceCodeLocation;
	}

	@Override
	@JsonBackReference
	public final AnalysisFileTree getParent() {
		return parent;
	}

	@Override
	public final boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	@JsonManagedReference
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSizeRecursive() {
		return sizeRecursive;
	}

	public void setSizeRecursive(long sizeRecursive) {
		this.sizeRecursive = sizeRecursive;
	}

	public final SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public final List<AnalysisInformation> getAnalyses() {
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
	@JsonIgnore
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

	@JsonIgnore
	public AnalysisFileTree getChild(String name) {
		for (AnalysisFileTree child : children) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

}
