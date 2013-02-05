package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

import com.puresol.trees.FileTree;
import com.puresol.trees.Tree;

@SuppressWarnings("restriction")
public class ExitHandler {

	@Execute
	public void execute(IWorkbench workbench) {
		Tree<?> tree = new FileTree(null, "Name");
		HashCodeGenerator.get(HashAlgorithm.SHA256, tree.toString());
		new TokenMetaData(new FixedCodeLocation("H"), 1, 2).clone();
		workbench.close();
	}

}