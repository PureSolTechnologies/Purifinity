package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;

import com.puresoltechnologies.commons.HashId;
import com.puresoltechnologies.commons.trees.api.AbstractTreeImpl;
import com.puresoltechnologies.parsers.api.source.CodeLocation;

public class CodeLocationTree extends AbstractTreeImpl<CodeLocationTree>
		implements Serializable {

	private static final long serialVersionUID = 1954594925844560921L;

	private final CodeLocation location;
	private final HashId hashId;

	public CodeLocationTree(CodeLocationTree parent, String name,
			CodeLocation location, HashId hashId) {
		super(parent, name);
		this.location = location;
		this.hashId = hashId;
	}

	public CodeLocation getLocation() {
		return location;
	}

	public HashId getHashId() {
		return hashId;
	}

}
