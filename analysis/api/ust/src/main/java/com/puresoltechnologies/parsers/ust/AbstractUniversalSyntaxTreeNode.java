package com.puresoltechnologies.parsers.ust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.trees.TreeException;
import com.puresoltechnologies.trees.TreeLink;

public abstract class AbstractUniversalSyntaxTreeNode implements
		UniversalSyntaxTree {

	private static final long serialVersionUID = -3031571790486296091L;

	private final Set<String> labels = new HashSet<>();
	private final Map<String, Object> properties = new HashMap<>();
	private final List<UniversalSyntaxTree> children = new ArrayList<>();

	private UniversalSyntaxTree parent = null;
	private final String name;
	private final UniversalSyntaxTreeMetaData metaData;
	private final String content;

	@JsonCreator
	public AbstractUniversalSyntaxTreeNode(@JsonProperty("name") String name,
			@JsonProperty("content") String content,
			@JsonProperty("metaData") UniversalSyntaxTreeMetaData metaData,
			@JsonProperty("children") List<UniversalSyntaxTree> children) {
		super();
		this.name = name;
		this.metaData = metaData;
		this.content = content;
		for (UniversalSyntaxTree child : children) {
			this.children.add(child);
			((AbstractUniversalSyntaxTreeNode) child).setParent(this);
		}
	}

	public AbstractUniversalSyntaxTreeNode(String name, String content,
			UniversalSyntaxTreeMetaData metaData) {
		super();
		this.name = name;
		this.metaData = metaData;
		this.content = content;
	}

	@Override
	@JsonBackReference
	public final UniversalSyntaxTree getParent() {
		return parent;
	}

	protected final void setParent(UniversalSyntaxTree parent) {
		this.parent = parent;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final UniversalSyntaxTreeMetaData getMetaData() {
		return metaData;
	}

	@Override
	public final String getContent() {
		return content;
	}

	@Override
	@JsonManagedReference
	public final List<UniversalSyntaxTree> getChildren() {
		return children;
	}

	@Override
	public final boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	@JsonIgnore
	public Set<TreeLink<UniversalSyntaxTree>> getEdges() {
		Set<TreeLink<UniversalSyntaxTree>> edges = new HashSet<>();
		edges.add(new TreeLink<UniversalSyntaxTree>(parent, this));
		for (UniversalSyntaxTree child : children) {
			edges.add(new TreeLink<>(this, child));
		}
		return edges;
	}

	@Override
	public final UniversalSyntaxTree getChild(String name) throws TreeException {
		UniversalSyntaxTree result = null;
		for (UniversalSyntaxTree child : children) {
			if (child.getName().equals(name)) {
				if (result != null) {
					throw new TreeException(
							"There were multiple children found for '" + name
									+ "' in '" + getName() + "'.");
				}
				result = child;
			}
		}
		return result;
	}

	@Override
	public final boolean hasChild(String name) {
		for (UniversalSyntaxTree child : children) {
			if (child.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final List<UniversalSyntaxTree> getChildren(String name) {
		List<UniversalSyntaxTree> foundChildren = new ArrayList<>();
		for (UniversalSyntaxTree child : children) {
			if (child.getName().equals(name)) {
				foundChildren.add(child);
			}
		}
		return foundChildren;
	}

	@Override
	public final boolean addLabel(String label) {
		return labels.add(label);
	}

	@Override
	public final boolean removeLabel(String label) {
		return labels.remove(label);
	}

	@Override
	public final boolean hasLabel(String label) {
		return labels.contains(label);
	}

	@Override
	public final Set<String> getLabels() {
		return labels;
	}

	@Override
	public Object setProperty(String key, Object value) {
		return properties.put(key, value);
	}

	@Override
	public Object removeProperty(String key) {
		return properties.remove(key);
	}

	@Override
	public boolean hasProperty(String key) {
		return properties.containsKey(key);
	}

	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}
}
