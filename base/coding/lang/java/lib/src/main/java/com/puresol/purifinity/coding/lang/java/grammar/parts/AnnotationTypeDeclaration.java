package com.puresol.purifinity.coding.lang.java.grammar.parts;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class AnnotationTypeDeclaration {

	public static boolean is(UniversalSyntaxTree part) {
		return "AnnotationTypeDeclaration".equals(part.getName());
	}

	private final UniversalSyntaxTree part;

	public AnnotationTypeDeclaration(UniversalSyntaxTree part) {
		super();
		this.part = part;
	}

	public String getIdentifier() throws TreeException {
		UniversalSyntaxTree identifier = part.getChild("Identifier");
		if (identifier == null) {
			throw new TreeException("No Identifier child found!");
		}
		return identifier.getContent();
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getIdentifier(), getIdentifier(),
				CodeRangeType.ANNOTATION, part);
	}
}
