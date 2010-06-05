package com.puresol.coding.lang.java;

import java.io.File;
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
									"deploy/src/common/share/classes/com/sun/deploy/config/Config.java"));
			analyser.parse();
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
		} catch (AnalyserException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		List<File> files = FileSearch.find(new File(INSTALL_DIRECTORY),
				"/**/*.java");
		int counter = 0;
		Java java = Java.getInstance();
		List<File> errors = new ArrayList<File>();
		for (File file : files) {
			counter++;
			System.out.print(ConsoleUtils.createPercentageBar(22,
					(double) counter / (double) files.size(), true)
					+ "\t");
			System.out.println(file);
			try {
				Analyser analyser = java.createAnalyser(new File(
						INSTALL_DIRECTORY), file);
				analyser.parse();
			} catch (ClassInstantiationException e) {
				e.printStackTrace();
				return;
			} catch (AnalyserException e) {
				e.printStackTrace();
				errors.add(file);
			}
		}
		System.out.println("\n\nERRORS FOR FILES:\n\n");
		for (File file : errors) {
			System.out.println(file);
		}
	}

}
