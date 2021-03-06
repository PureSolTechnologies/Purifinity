package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.trees.TreeNodeImpl;

public class CodeLocationTree extends TreeNodeImpl<CodeLocationTree> implements
		Serializable {

	private static final long serialVersionUID = 1954594925844560921L;

	private final SourceCodeLocation location;
	private final HashId hashId;

	public CodeLocationTree(CodeLocationTree parent, String name,
			SourceCodeLocation location, HashId hashId) {
		super(parent, name);
		this.location = location;
		this.hashId = hashId;
	}

	public SourceCodeLocation getLocation() {
		return location;
	}

	public HashId getHashId() {
		return hashId;
	}

}
