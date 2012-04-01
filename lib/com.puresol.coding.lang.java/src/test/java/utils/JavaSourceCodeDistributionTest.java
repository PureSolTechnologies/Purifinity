package utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.Analyzer;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.utils.ConsoleUtils;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileUtilities;
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

    private static final File file = new File(INSTALL_DIRECTORY,
	    "j2se/src/share/classes/sun/nio/cs/ext/EUC_TW.java");

    @Test
    @Ignore("Takes too long...")
    public void test() throws Throwable {
	assertTrue(file.exists());
	Java java = Java.getInstance();
	StopWatch watch = new StopWatch();
	watch.start();
	Analyzer analyser = java.createAnalyser(file);
	analyser.parse();
	watch.stop();
	ParserTree ast = analyser.getParserTree();
	assertNotNull(ast);
	// new TreePrinter(System.out).println(ast);
	System.out.print(watch.getSeconds());
    }

    @Test
    @Ignore("Takes too long...")
    public void test2() throws Throwable {
	assertTrue(file.exists());
	Grammar grammar = JavaGrammar.getInstance();
	PackratParser parser = new PackratParser(grammar);
	String text = FileUtilities.readFileToString(file);
	StopWatch watch = new StopWatch();
	watch.start();
	ParserTree ast = parser.parse(text, file.getName());
	watch.stop();
	assertNotNull(ast);
	// new TreePrinter(System.out).println(ast);
	System.out.print(watch.getSeconds());
    }

    private static Grammar javaGrammar;

    private static void parseAllFiles(List<File> files) {
	StopWatch totalWatch = new StopWatch();
	totalWatch.start();
	try {
	    javaGrammar = JavaGrammar.getInstance();
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

    private static boolean parseFile(File file) {
	try {
	    // Java java = Java.getInstance();
	    // Analyzer analyser = java.createAnalyser(file);
	    // analyser.parse();
	    // analyser = null;
	    PackratParser parser = new PackratParser(javaGrammar);
	    String text = FileUtilities.readFileToString(file);
	    parser.parse(text, file.getName());
	    return true;
	    // } catch (AnalyzerException e) {
	    // e.printStackTrace();
	    // return false;
	} catch (ParserException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public static void main(String args[]) {
	List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY),
		"*.java");
	parseAllFiles(files);
    }

}
