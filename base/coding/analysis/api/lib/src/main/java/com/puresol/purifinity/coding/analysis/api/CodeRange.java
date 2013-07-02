package com.puresol.purifinity.coding.analysis.api;

import java.io.Serializable;

import com.puresol.commons.utils.ObjectUtilities;
import com.puresol.purifinity.uhura.parser.ParserTree;

/**
 * This class is for keeping information about a subtree. This sub tree is
 * called code range and is a piece of code which is able to be analyzed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class CodeRange implements Serializable, Comparable<CodeRange> {

	private static final long serialVersionUID = -2262393596463952399L;

	/**
	 * This name is a simple name like a method name or class name.
	 */
	private final String simpleName;

	/**
	 * This is name is in a canonical form. The issue was overloaded methods
	 * which have the same name. This caused bugs. We have here now a name which
	 * is to be unique for the code range.
	 */
	private final String canonicalName;

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

	public CodeRange(String simpleName, String canonicalName,
			CodeRangeType type, ParserTree ast) {
		super();
		this.simpleName = simpleName;
		this.canonicalName = canonicalName;
		this.type = type;
		this.ast = ast;
		hashcode = ObjectUtilities.calculateConstantHashCode(simpleName, type,
				ast);
	}

	public String getSimpleName() {
		return simpleName;
	}

	public String getCanonicalName() {
		return canonicalName;
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
		if (simpleName == null) {
			if (other.simpleName != null)
				return false;
		} else if (!simpleName.equals(other.simpleName))
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
		return simpleName.compareTo(other.simpleName);
	}

	@Override
	public String toString() {
		if (simpleName.isEmpty()) {
			return type.getName();
		}
		return type.getName() + " " + simpleName;
	}

}
