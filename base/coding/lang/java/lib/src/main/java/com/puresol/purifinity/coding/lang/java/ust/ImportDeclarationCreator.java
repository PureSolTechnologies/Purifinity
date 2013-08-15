package com.puresol.purifinity.coding.lang.java.ust;

import java.util.List;

import com.puresol.commons.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.AbstractUSTCreator;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.AbstractUSTNode;
import com.puresol.purifinity.uhura.ust.packages.ImportPackage;

public class ImportDeclarationCreator extends AbstractUSTCreator {

	public ImportDeclarationCreator(USTCreator creator) {
		super(creator);
	}

	@Override
	public AbstractUSTNode createUST(ParserTree parserTree)
			throws TreeException {
		List<ParserTree> children = parserTree.getChildren();
		assertEquals("Only one child was expected.", 1, children.size());
		ParserTree child = children.get(0);
		AbstractUSTNode result = null;
		if (parserTree.hasChild("SingleTypeImportDeclaration")) {
			result = new ImportPackage("import", child.getChild("Name")
					.getText());
		} else if (parserTree.hasChild("TypeImportOnDemandDeclaration")) {
			result = new ImportPackage("import", child.getChild("Name")
					.getText());
		} else if (parserTree.hasChild("SingleStaticImportDeclaration")) {
			result = new ImportPackage("import", child.getChild("Name")
					.getText());
		} else if (parserTree.hasChild("StaticImportOnDemandDeclaration")) {
			result = new ImportPackage("import", child.getChild("Name")
					.getText());
		} else {
			fail("No known import was found for '" + child.getName() + "'.");
		}
		return result;
	}
}
