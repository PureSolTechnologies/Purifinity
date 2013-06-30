package com.puresol.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.parser.ParserTree;

public class ParserTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof String) {
	    return element.toString();
	}
	ParserTree nodeElement = (ParserTree) element;
	String text = nodeElement.getName();
	Token token = nodeElement.getToken();
	if (token != null) {
	    text += ": '" + token.getText() + "'";
	}
	return text;
    }

}
