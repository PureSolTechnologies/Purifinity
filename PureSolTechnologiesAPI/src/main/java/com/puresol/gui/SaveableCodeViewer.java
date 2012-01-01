package com.puresol.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

import com.puresol.filefilter.TXTFilter;

public class SaveableCodeViewer extends CodeViewer implements Saveable {

	private static final long serialVersionUID = -405181291482360302L;

	@Override
	public FileFilter[] getPossibleFileFilters() {
		return new FileFilter[] { new TXTFilter() };
	}

	@Override
	public void save(File file) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(getText());
		} finally {
			writer.close();
		}
	}
}
