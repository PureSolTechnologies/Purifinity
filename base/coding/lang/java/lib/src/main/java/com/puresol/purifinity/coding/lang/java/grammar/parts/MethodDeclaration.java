package com.puresol.purifinity.coding.lang.java.grammar.parts;

import java.util.List;

import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.trees.Tree;
import com.puresol.purifinity.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;

public class MethodDeclaration {

    public static boolean is(ParserTree part) {
	return "MethodDeclaration".equals(part.getName());
    }

    private final ParserTree part;

    public MethodDeclaration(ParserTree part) {
	super();
	this.part = part;
    }

    /**
     * This method simply returns the name of the method. In cases of overloaded
     * method this is not unique!
     * 
     * @return A {@link String} is returned containing the name of the method.
     * @throws TreeException
     *             is thrown if the {@link Tree} was not in shape as expected.
     */
    public String getIdentifier() throws TreeException {
	ParserTree methodHeader = part.getChild("MethodHeader");
	ParserTree methodDeclarator = methodHeader.getChild("MethodDeclarator");
	ParserTree identifier = methodDeclarator.getChild("Identifier");
	return identifier.getText();
    }

    /**
     * This method returns a canonical identifier for method which are
     * overloaded.
     * 
     * @return
     * @throws TreeException
     */
    public String getCanonicalIdentifier() throws TreeException {
	ParserTree methodHeader = part.getChild("MethodHeader");
	ParserTree methodDeclarator = methodHeader.getChild("MethodDeclarator");
	ParserTree identifier = methodDeclarator.getChild("Identifier");
	StringBuilder parameterTypes = new StringBuilder();
	if (methodDeclarator.hasChild("FormalParameterList")) {
	    ParserTree formalParameterList = methodDeclarator
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
	return identifier.getText() + "(" + parameterTypes + ")";
    }

    public CodeRange getCodeRange() throws TreeException {
	return new CodeRange(getIdentifier(), getCanonicalIdentifier(),
		CodeRangeType.METHOD, part);
    }
}
