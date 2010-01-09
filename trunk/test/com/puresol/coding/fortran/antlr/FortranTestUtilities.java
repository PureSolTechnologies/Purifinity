package com.puresol.coding.fortran.antlr;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.FileSearch;

public class FortranTestUtilities {

	public static ArrayList<File> getFiles() {
		// return FileSearch
		// .find("test/com/puresol/coding/fortran/samples/**/*.f*");
		return FileSearch
				.find("/usr/src/compile/ATLAS/src/blas/f77reference/**/*.f*");
	}

}
