package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTreeMetaData;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;

public class UniversalSyntaxTreeLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof String) {
			return element.toString();
		}
		UniversalSyntaxTree nodeElement = (UniversalSyntaxTree) element;
		String text = nodeElement.getName();
		if (AbstractTerminal.class.isAssignableFrom(nodeElement.getClass())) {
			AbstractTerminal token = (AbstractTerminal) nodeElement;
			text += ": '" + token.getContent() + "'";
		}
		UniversalSyntaxTreeMetaData metaData = nodeElement.getMetaData();
		text += " (line=" + metaData.getLine() + "; #line="
				+ metaData.getLineNum() + "; column=" + metaData.getColumn()
				+ "; length=" + metaData.getLength() + ")";
		return text;
	}

}
