package com.puresol.purifinity.uhura.ust;

import java.io.File;

public class CompilationUnit extends AbstractUniversalSyntaxTree {

	private static final long serialVersionUID = -5790049234290910253L;

	/**
	 * This file keeps the file where the compilation unit was found.
	 */
	private final File file;
	private final String language;
	private final String version;

	public CompilationUnit(File file, String language, String version) {
		super("");
		this.file = file;
		this.language = language;
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Compilation Unit";
	}

	public File getFile() {
		return file;
	}

	public String getLanguage() {
		return language;
	}

	public String getVersion() {
		return version;
	}
}
