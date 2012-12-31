package com.puresol.coding.analysis;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.trees.FileTree;
import com.puresol.trees.TreePrinter;
import com.puresol.utils.FileSearch;
import com.puresol.utils.CodeSearchConfiguration;

public class StoreUtilitiesTest {

    @Test
    public void testCreateHashIdFileTree() {
	FileTree fileTree = FileSearch.getFileTree(new File("src"),
		new CodeSearchConfiguration());
	assertNotNull(fileTree);
	HashIdFileTree hashIdFileTree = StoreUtilities
		.createHashIdFileTree(fileTree);
	assertNotNull(hashIdFileTree);
	TreePrinter treePrinter = new TreePrinter(System.out);
	treePrinter.println(fileTree);
	treePrinter.println(hashIdFileTree);
    }

}
