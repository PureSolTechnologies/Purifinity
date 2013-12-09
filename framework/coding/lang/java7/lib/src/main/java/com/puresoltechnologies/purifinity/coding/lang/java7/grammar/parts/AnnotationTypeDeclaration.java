package com.puresoltechnologies.purifinity.coding.lang.java7.grammar.parts;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;

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
