package com.puresol.coding.lang.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.trees.FileTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.source.SourceFileLocation;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileSearchConfiguration;

/**
 * This is a stability test for the Java analyzer. It analyzes the source of the
 * source code analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SourceCodeAnalysisDistributionTest {

    private static final String INSTALL_DIRECTORY = "/home/ludwig/JavaSource";

    private static final File file = new File(
	    "j2se/src/share/classes/sun/nio/cs/ext/EUC_TW.java");

    private static File directory = new File(System.getProperty("user.dir"));

    private static final Grammar javaGrammar = JavaGrammar.getInstance();

    @BeforeClass
    public static void initialize() {
	directory = new File(System.getProperty("user.dir"));
	System.out.println(directory.toString());
	assertEquals("lib", directory.getName());
	directory = directory.getParentFile();
	assertEquals("java", directory.getName());
	directory = directory.getParentFile();
	assertEquals("lang", directory.getName());
	directory = directory.getParentFile();
	assertEquals("coding", directory.getName());
	directory = directory.getParentFile();
	assertEquals("base", directory.getName());
	directory = directory.getParentFile();
    }

    @Test
    public void testJavaOnSourceCodeAnalysisCode() throws IOException {
	List<String> includeFiles = new ArrayList<String>();
	includeFiles.add("*.java");
	List<String> includeDirectories = new ArrayList<String>();
	List<String> excludeFiles = new ArrayList<String>();
	excludeFiles.add("*");
	List<String> excludeDirectories = new ArrayList<String>();
	excludeDirectories.add(".*");
	excludeDirectories.add("target");
	FileSearchConfiguration configuration = new FileSearchConfiguration(
		includeDirectories, excludeDirectories, includeFiles,
		excludeFiles, true);
	FileTree fileTree = FileSearch.getFileTree(directory, configuration);
	for (FileTree fileNode : fileTree) {
	    File file = fileNode.getPathFile(true);
	    if (file.isFile()) {
		assertTrue(parseFile(file));
	    }
	}
    }

    private static boolean parseFile(File file) throws IOException {
	try {
	    System.out.println(file);
	    Java java = Java.getInstance();
	    CodeAnalyzer analyser = java.createAnalyser(new SourceFileLocation(
		    file.getParentFile(), file.getName()));
	    analyser.analyze();
	    return true;
	} catch (AnalyzerException e) {
	    e.printStackTrace();
	    return false;
	}
    }
}
