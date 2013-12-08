package com.puresoltechnologies.purifinity.coding.lang.java7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.commons.utils.FileSearch;
import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.commons.utils.FileTree;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.coding.lang.java7.Java;
import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;

/**
 * This is a stability test for the Java analyzer. It analyzes the source of the
 * source code analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SourceCodeAnalysisDistributionIT {

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
		assertEquals("java7", directory.getName());
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
