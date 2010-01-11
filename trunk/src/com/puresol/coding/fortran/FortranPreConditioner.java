package com.puresol.coding.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.puresol.parser.DefaultPreConditioner;

public class FortranPreConditioner extends DefaultPreConditioner {

	public FortranPreConditioner(File file) throws FileNotFoundException {
		super(file);
	}

	public FortranPreConditioner(String name, InputStream stream) {
		super(name, stream);
	}
}
