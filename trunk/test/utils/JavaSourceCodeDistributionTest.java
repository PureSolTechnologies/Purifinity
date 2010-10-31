package utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.utils.FileSearch;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.java.Java;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.ConsoleUtils;

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

	@Test
	public void test() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			File file = new File("test/com/puresol/coding/lang/java/samples",
					"AbstractGradient.java");
			assertTrue(file.exists());
			Java java = Java.getInstance();
			Analyzer analyser = java.createAnalyser(file);
			analyser.parse();
		} catch (AnalyzerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	public static void main(String args[]) {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY),
					"*.java");
			List<String> successes = new ArrayList<String>();
			RandomAccessFile raFile = new RandomAccessFile("succeeded.txt",
					"rw");
			String line;
			while ((line = raFile.readLine()) != null) {
				successes.add(line);
			}
			int counter = 0;
			Java java = Java.getInstance();
			List<File> errors = new ArrayList<File>();
			for (File file : files) {
				if (file.getName().contains("-")) {
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
					continue;
				}
				counter++;
				System.out.print(ConsoleUtils.createPercentageBar(22,
						(double) counter / (double) files.size(), true) + "\t");
				System.out.print(successes.size()
						/ (successes.size() + errors.size()) * 100.0);
				System.out.print("%\t");
				System.out
						.print(Runtime.getRuntime().freeMemory() / 1024 / 1024);
				System.out.print("MB\t");
				System.out.println(file);
				if (successes.contains(file.toString())) {
					continue;
				}
				try {
					Analyzer analyser = java.createAnalyser(new File(
							INSTALL_DIRECTORY, file.toString()));
					analyser.parse();
					raFile.writeBytes(file.toString() + "\n");
					successes.add(file.toString());
				} catch (AnalyzerException e) {
					e.printStackTrace();
					errors.add(file);
				}
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
	}

}
