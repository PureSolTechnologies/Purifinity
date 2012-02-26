package utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.fortran.Fortran;
import com.puresol.trees.TreePrinter;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.ConsoleUtils;
import com.puresol.utils.FileSearch;
import com.puresol.utils.StopWatch;

/**
 * This is not a real JUnit test, but it's used manually to check the parser
 * against the Java Source Code Distribution which is one of the biggest Java
 * source bundles available to check completeness and stability.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranSourceCodeDistributionTest {

    private static final String INSTALL_DIRECTORY = "/home/ludwig/workspace/Dyn3D";

    private final static Logger logger = LoggerFactory
	    .getLogger(FortranSourceCodeDistributionTest.class);

    @Test
    public void test() {
	try {
	    File file = new File(INSTALL_DIRECTORY, "src/fort/ndicrs.f");
	    assumeTrue(file.exists());
	    Fortran fortran = Fortran.getInstance();
	    StopWatch watch = new StopWatch();
	    watch.start();
	    Analyzer analyser = fortran.createAnalyser(file);
	    analyser.parse();
	    watch.stop();
	    ParserTree ast = analyser.getParserTree();
	    assertNotNull(ast);
	    System.out.print(watch.getSeconds());
	    if (logger.isTraceEnabled()) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		new TreePrinter(new PrintStream(outStream)).println(ast);
		logger.trace(outStream.toString());
	    }
	} catch (AnalyzerException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (IOException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
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

    private static boolean parseFile(File file) {
	try {
	    Fortran fortran = Fortran.getInstance();
	    Analyzer analyser = fortran.createAnalyser(file);
	    analyser.parse();
	    analyser = null;
	    return true;
	} catch (AnalyzerException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public static void main(String args[]) {
	List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY), "*.f");
	parseAllFiles(files);
    }

}
