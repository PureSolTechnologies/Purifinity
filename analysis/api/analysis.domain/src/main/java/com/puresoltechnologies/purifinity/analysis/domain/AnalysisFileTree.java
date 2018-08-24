package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.trees.TreeLink;
import com.puresoltechnologies.trees.TreeNode;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

public class AnalysisFileTree implements TreeNode<AnalysisFileTree>, Serializable {

    private static final long serialVersionUID = 294965469645813244L;

    private final List<AnalysisFileTree> children = new ArrayList<AnalysisFileTree>();
    private final List<AnalysisInformation> analyzedCodes = new ArrayList<>();

    private AnalysisFileTree parent;
    private final String name;
    private final HashId hashId;
    private final boolean file;
    private final long size;
    private final long sizeRecursive;
    private final SourceCodeLocation sourceCodeLocation;

    public AnalysisFileTree(AnalysisFileTree parent, //
	    String name, //
	    HashId hashId, //
	    boolean file, //
	    long size, //
	    long sizeRecursive, //
	    SourceCodeLocation sourceCodeLocation, //
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

    @JsonCreator
    public AnalysisFileTree(@JsonProperty("name") String name, //
	    @JsonProperty("hashId") HashId hashId, //
	    @JsonProperty("file") boolean file, //
	    @JsonProperty("size") long size, //
	    @JsonProperty("sizeRecursive") long sizeRecursive, //
	    @JsonProperty("sourceCodeLocation") SourceCodeLocation sourceCodeLocation, //
	    @JsonProperty("analyzedCodes") List<AnalysisInformation> analyzedCodes,
	    @JsonProperty("children") List<AnalysisFileTree> children) {
	super();
	this.parent = null;
	this.name = name;
	this.hashId = hashId;
	this.file = file;
	this.size = size;
	this.sizeRecursive = sizeRecursive;
	this.sourceCodeLocation = sourceCodeLocation;
	if (analyzedCodes != null) {
	    this.analyzedCodes.addAll(analyzedCodes);
	}
	this.children.addAll(children);
	for (AnalysisFileTree child : this.children) {
	    child.parent = this;
	}
    }

    public List<AnalysisInformation> getAnalyzedCodes() {
	return analyzedCodes;
    }

    @Override
    @JsonIgnore
    public AnalysisFileTree getParent() {
	return parent;
    }

    @Override
    public boolean hasChildren() {
	return !children.isEmpty();
    }

    @Override
    public List<AnalysisFileTree> getChildren() {
	return children;
    }

    @Override
    @JsonIgnore
    public Set<TreeLink<AnalysisFileTree>> getEdges() {
	Set<TreeLink<AnalysisFileTree>> edges = new HashSet<>();
	edges.add(new TreeLink<AnalysisFileTree>(parent, this));
	for (AnalysisFileTree child : children) {
	    edges.add(new TreeLink<>(this, child));
	}
	return edges;
    }

    @Override
    public String getName() {
	return name;
    }

    public HashId getHashId() {
	return hashId;
    }

    public boolean isFile() {
	return file;
    }

    public long getSize() {
	return size;
    }

    public long getSizeRecursive() {
	return sizeRecursive;
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public List<AnalysisInformation> getAnalyses() {
	return analyzedCodes;
    }

    @Override
    public String toString() {
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
     * @return A {@link File} is returned containing the path of the file within
     *         the tree.
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

    private static class FindFileTreeVisitor implements TreeVisitor<AnalysisFileTree> {

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
	TreeWalker<AnalysisFileTree> walker = new TreeWalker<AnalysisFileTree>(this);
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((analyzedCodes == null) ? 0 : analyzedCodes.hashCode());
	result = prime * result + ((children == null) ? 0 : children.hashCode());
	result = prime * result + (file ? 1231 : 1237);
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((parent == null) ? 0 : parent.hashCode());
	result = prime * result + (int) (size ^ (size >>> 32));
	result = prime * result + (int) (sizeRecursive ^ (sizeRecursive >>> 32));
	result = prime * result + ((sourceCodeLocation == null) ? 0 : sourceCodeLocation.hashCode());
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
	AnalysisFileTree other = (AnalysisFileTree) obj;
	if (analyzedCodes == null) {
	    if (other.analyzedCodes != null)
		return false;
	} else if (!analyzedCodes.equals(other.analyzedCodes))
	    return false;
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
	if (size != other.size)
	    return false;
	if (sizeRecursive != other.sizeRecursive)
	    return false;
	if (sourceCodeLocation == null) {
	    if (other.sourceCodeLocation != null)
		return false;
	} else if (!sourceCodeLocation.equals(other.sourceCodeLocation))
	    return false;
	return true;
    }

}
