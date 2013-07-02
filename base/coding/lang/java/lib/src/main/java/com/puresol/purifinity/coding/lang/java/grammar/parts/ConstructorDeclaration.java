package com.puresol.purifinity.coding.lang.java.grammar.parts;

import java.util.List;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.uhura.parser.ParserTree;

public class ConstructorDeclaration {

	public static boolean is(ParserTree part) {
		return "ConstructorDeclaration".equals(part.getName());
	}

	private final ParserTree part;

	public ConstructorDeclaration(ParserTree part) {
		super();
		this.part = part;
	}

	public String getName() throws TreeException {
		ParserTree constructorDeclarator = part
				.getChild("ConstructorDeclarator");
		ParserTree name = constructorDeclarator.getChild("Name");
		StringBuffer nameBuffer = new StringBuffer();
		for (ParserTree namePart : name.getChildren()) {
			nameBuffer.append(namePart.getText());
		}
		return nameBuffer.toString();
	}

	/**
	 * This method returns a canonical identifier for method which are
	 * overloaded.
	 * 
	 * @return
	 * @throws TreeException
	 */
	public String getCanonicalName() throws TreeException {
		ParserTree constructorDeclarator = part
				.getChild("ConstructorDeclarator");
		ParserTree name = constructorDeclarator.getChild("Name");
		StringBuilder parameterTypes = new StringBuilder();
		if (constructorDeclarator.hasChild("FormalParameterList")) {
			ParserTree formalParameterList = constructorDeclarator
					.getChild("FormalParameterList");
			List<ParserTree> formalParameters = formalParameterList
					.getSubTrees("FormalParameter");
			for (ParserTree formalParameter : formalParameters) {
				ParserTree type = formalParameter.getChild("Type");
				if (parameterTypes.length() > 0) {
					parameterTypes.append(",");
				}
				parameterTypes.append(type.getText().trim());
			}
			if (formalParameterList.hasChild("LastFormalParameter")) {
				ParserTree lastFormalParameter = formalParameterList
						.getChild("LastFormalParameter");
				if (parameterTypes.length() > 0) {
					parameterTypes.append(",");
				}
				ParserTree type = lastFormalParameter.getChild("Type");
				parameterTypes.append(type.getText().trim());
				if (lastFormalParameter.hasChild("DOT")) {
					parameterTypes.append("...");
				}
			}
		}
		return name.getText() + "(" + parameterTypes + ")";
	}

	public CodeRange getCodeRange() throws TreeException {
		return new CodeRange(getName(), getCanonicalName(),
				CodeRangeType.CONSTRUCTOR, part);
	}
}
