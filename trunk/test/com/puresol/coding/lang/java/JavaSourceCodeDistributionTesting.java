package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.FileSearch;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.AnalyserException;
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
public class JavaSourceCodeDistributionTesting {

	private static final String INSTALL_DIRECTORY = "/home/ludwig/JavaSource";

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			Analyser analyser = Java
					.getInstance()
					.createAnalyser(
							new File(INSTALL_DIRECTORY),
							new File(
									"j2se/make/tools/swing-nimbus/classes/org/jdesktop/swingx/designer/BlendingMode.java"));
			analyser.parse();
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
		} catch (AnalyserException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY),
					"/**/*.java");
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
				counter++;
				System.out.print(ConsoleUtils.createPercentageBar(22,
						(double) counter / (double) files.size(), true)
						+ "\t");
				System.out.println(file);
				if (successes.contains(file.toString())) {
					continue;
				}
				try {
					Analyser analyser = java.createAnalyser(new File(
							INSTALL_DIRECTORY), file);
					analyser.parse();
					raFile.writeBytes(file.toString() + "\n");
				} catch (ClassInstantiationException e) {
					e.printStackTrace();
					raFile.close();
					return;
				} catch (AnalyserException e) {
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
