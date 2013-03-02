package com.puresol.coding.lang.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.AnalyzerException;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.trees.FileTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceFileLocation;
import com.puresol.utils.ConsoleUtils;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.StopWatch;

/**
 * This is not a real JUnit test, but it's used manually to check the parser
 * against the Java Source Code Distribution which is one of the biggest Java
 * source bundles available to check completeness and stability.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaSourceCodeDistributionTest {

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

	@Test
	public void testSingleFile() throws IOException {
		File file = new File(
				directory,
				"rcp/application/plugin/src/com/puresol/coding/client/application/controls/GrammarCanvas.java");
		assertTrue(parseFilePackrat(file));
	}

	@Test
	@Ignore("Takes too long...")
	public void test() throws Throwable {
		Java java = Java.getInstance();
		StopWatch watch = new StopWatch();
		watch.start();
		CodeAnalyzer analyser = java.createAnalyser(new SourceFileLocation(
				INSTALL_DIRECTORY, file.getPath()));
		analyser.analyze();
		watch.stop();
		ParserTree ast = analyser.getAnalysis().getParserTree();
		assertNotNull(ast);
		// new TreePrinter(System.out).println(ast);
		System.out.print(watch.getSeconds());
	}

	@Test
	@Ignore("Takes too long...")
	public void test2() throws Throwable {
		assertTrue(file.exists());
		PackratParser parser = new PackratParser(javaGrammar);
		CodeLocation source = new SourceFileLocation("", file.getPath());
		StopWatch watch = new StopWatch();
		watch.start();
		ParserTree ast = parser.parse(source.loadSourceCode());
		watch.stop();
		assertNotNull(ast);
		// new TreePrinter(System.out).println(ast);
		System.out.print(watch.getSeconds());
	}

	private static void parseAllFiles(List<File> files) {
		StopWatch totalWatch = new StopWatch();
		totalWatch.start();
		try {
			List<File> errors = new ArrayList<File>();
			List<String> successes = new ArrayList<String>();
			RandomAccessFile raFile = new RandomAccessFile("succeeded.txt",
					"rw");
			String line;
			while ((line = raFile.readLine()) != null) {
				successes.add(line);
			}
			int counter = 0;
			StopWatch watch = new StopWatch();
			for (File file : files) {
				assertTrue(new File(INSTALL_DIRECTORY, file.toString())
						.exists());
				if (file.getName().contains("-")) {
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
					continue;
				}
				counter++;
				System.out.print(ConsoleUtils.createPercentageBar(22,
						(double) counter / (double) files.size(), true) + "\t");
				if (successes.size() > 0) {
					System.out.print((double) successes.size()
							/ (double) (successes.size() + errors.size())
							* 100.0);
					System.out.print("%\t");
				}
				System.out
						.print(Runtime.getRuntime().freeMemory() / 1024 / 1024);
				System.out.print("MB\t");
				System.out.print(file + "\t");
				if (successes.contains(file.toString())) {
					System.out.println();
					continue;
				}
				watch.start();
				if (parseFile(new File(INSTALL_DIRECTORY, file.toString()))) {
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
				} else {
					errors.add(file);
				}
				watch.stop();
				System.out.println(watch.toString() + "s");
			}
			raFile.close();
			System.out.println("\n\nERRORS FOR FILES:\n\n");
			for (File file : errors) {
				System.out.println(file);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		totalWatch.stop();
		System.out.println("\n\nTotal time:\t" + totalWatch.toString());
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

	private static boolean parseFilePackrat(File file) throws IOException {
		try {
			System.out.println(file);
			PackratParser parser = new PackratParser(javaGrammar);
			FileInputStream inputStream = new FileInputStream(file);
			try {
				SourceCode sourceCode = SourceCode.read(
						inputStream,
						new SourceFileLocation(file.getParentFile(), file
								.getName()));
				parser.parse(sourceCode);
			} finally {
				inputStream.close();
			}
			return true;
		} catch (ParserException e) {
			e.printStackTrace();
			return false;
		}
	}

}
