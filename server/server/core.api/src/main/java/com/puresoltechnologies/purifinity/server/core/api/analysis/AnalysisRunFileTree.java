package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.trees.TreeLink;
import com.puresoltechnologies.trees.TreeNode;

public class AnalysisRunFileTree implements TreeNode<AnalysisRunFileTree> {

	private HashId hashId = null;

	private final List<AnalysisRunFileTree> children = new ArrayList<>();

	private AnalysisRunFileTree parent;
	private String name;
	private boolean file;
	private SourceCodeLocation sourceCodeLocation;

	public AnalysisRunFileTree() {
	}

	public AnalysisRunFileTree(AnalysisRunFileTree parent, String name,
			boolean file, SourceCodeLocation sourceCodeLocation) {
		this.parent = parent;
		this.name = name;
		this.file = file;
		this.sourceCodeLocation = sourceCodeLocation;
		if (parent != null) {
			parent.addChild(this);
		}
	}

	public AnalysisRunFileTree(AnalysisRunFileTree parent, String name,
			boolean file, SourceCodeLocation sourceCodeLocation, HashId hashId) {
		this(parent, name, file, sourceCodeLocation);
		this.hashId = hashId;
	}

	public AnalysisRunFileTree(AnalysisRunFileTree parent, String name,
			boolean file, SourceCodeLocation sourceCodeLocation, HashId hashId,
			List<AnalysisRunFileTree> children) {
		this(parent, name, file, sourceCodeLocation, hashId);
		this.children.addAll(children);
	}

	@Override
	@JsonBackReference
	public AnalysisRunFileTree getParent() {
		return parent;
	}

	@JsonBackReference
	public void setParent(AnalysisRunFileTree parent) {
		this.parent = parent;
	}

	@Override
	public boolean hasChildren() {
		return children.size() > 0;
	}

	private void addChild(AnalysisRunFileTree child) {
		children.add(child);
	}

	public void setChildren(List<AnalysisRunFileTree> children) {
		this.children.clear();
		this.children.addAll(children);
	}

	@Override
	@JsonManagedReference
	public List<AnalysisRunFileTree> getChildren() {
		return children;
	}

	@Override
	@JsonIgnore
	public Set<TreeLink<AnalysisRunFileTree>> getEdges() {
		Set<TreeLink<AnalysisRunFileTree>> edges = new HashSet<>();
		edges.add(new TreeLink<AnalysisRunFileTree>(parent, this));
		for (AnalysisRunFileTree child : children) {
			edges.add(new TreeLink<>(this, child));
		}
		return edges;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public boolean isFile() {
		return file;
	}

	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public void setSourceCodeLocation(SourceCodeLocation sourceCodeLocation) {
		this.sourceCodeLocation = sourceCodeLocation;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public HashId getHashId() {
		return hashId;
	}

	public void setHashId(HashId hashId) {
		this.hashId = hashId;
	}

	public AnalysisRunFileTree findChild(String name) {
		for (AnalysisRunFileTree child : children) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + (file ? 1231 : 1237);
		result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisRunFileTree other = (AnalysisRunFileTree) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (file != other.file)
			return false;
		if (hashId == null) {
			if (other.hashId != null)
				return false;
		} else if (!hashId.equals(other.hashId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(name);
		buffer.append(" (");
		if (file) {
			buffer.append("File");
		} else {
			buffer.append("Directory");
		}
		buffer.append(") / ");
		buffer.append(hashId.toString());
		return buffer.toString();
	}
}
