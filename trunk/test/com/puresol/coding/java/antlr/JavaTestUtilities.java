package com.puresol.coding.java.antlr;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.FileSearch;

import junit.framework.TestCase;

public class JavaTestUtilities extends TestCase {

	public static ArrayList<File> getFiles() {
		return FileSearch.find("src/**/*.java");
	}

}
