package com.puresol.coding;

import java.io.Serializable;

import com.puresol.uhura.parser.ParserTree;

/**
 * This class is for keeping information about a subtree. This sub tree is
 * called code range and is a piece of code which is able to be analyzed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeRange implements Serializable, Comparable<CodeRange> {

	private static final long serialVersionUID = -2262393596463952399L;

	/**
	 * This is a human readable name of the code range.
	 */
	private final String name;

	/**
	 * This is the type of the code range. It might be a whole file, a class
	 * definition or a subroutine.
	 */
	private final CodeRangeType type;

	/**
	 * This is the actual code which is part of this code range.
	 */
	private final ParserTree ast;

	private final int hashcode;

	public CodeRange(String name, CodeRangeType type, ParserTree ast) {
		super();
		this.name = name;
		this.type = type;
		this.ast = ast;

		final int prime = 31;
		int result = 1;
		result = prime * result + ((ast == null) ? 0 : ast.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		hashcode = result;
	}

	public String getName() {
		return name;
	}

	public CodeRangeType getType() {
		return type;
	}

	public ParserTree getParserTree() {
		return ast;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodeRange other = (CodeRange) obj;
		if (hashcode != other.hashcode) {
			return false;
		}
		if (ast == null) {
			if (other.ast != null)
				return false;
		} else if (!ast.equals(other.ast))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(CodeRange other) {
		int result = type.compareTo(other.type);
		if (result != 0) {
			return result;
		}
		return name.compareTo(other.name);
	}

	@Override
	public String toString() {
		if (name.isEmpty()) {
			return type.getName();
		}
		return type.getName() + " " + name;
	}

}
