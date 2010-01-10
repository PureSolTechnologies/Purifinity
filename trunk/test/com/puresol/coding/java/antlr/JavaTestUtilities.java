package com.puresol.coding.java.antlr;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.FileSearch;

public class JavaTestUtilities {

	public static ArrayList<File> getFiles() {
		ArrayList<File> files = FileSearch.find("src/**/*.java");
		// ArrayList<File> files = FileSearch
		// .find("test/com/puresol/coding/java/samples/**/*.java");
		ArrayList<File> remove = new ArrayList<File>();
		for (File file : files) {
			if (file.getPath().contains("/antlr/output/")) {
				/*
				 * ANTLR classes are not good in sense of quality and therefore
				 * might get errors until refactored!
				 */
				remove.add(file);
			}
		}
		for (File file : remove) {
			files.remove(file);
		}
		return files;
	}
}
