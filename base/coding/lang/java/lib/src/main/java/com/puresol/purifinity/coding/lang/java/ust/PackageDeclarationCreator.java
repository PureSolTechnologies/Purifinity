package com.puresol.purifinity.coding.lang.java.ust;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.USTNode;
import com.puresol.purifinity.uhura.ust.packages.PackageDeclaration;

public class PackageDeclarationCreator extends AbstractUSTCreator {

	public PackageDeclarationCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public USTNode createUST(ParserTree parserTree)
			throws TreeException {
		ParserTree nameNode = parserTree.getChild("Name");
		String packageNode = nameNode.getText();
		return new PackageDeclaration("package", packageNode);
	}

}
