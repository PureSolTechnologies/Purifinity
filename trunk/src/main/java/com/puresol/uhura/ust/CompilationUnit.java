package com.puresol.uhura.ust;

import java.io.File;

import javax.i18n4java.Translator;

public class CompilationUnit extends AbstractUniversalSyntaxTree {

	private static final Translator translator = Translator
			.getTranslator(CompilationUnit.class);

	/**
	 * This file keeps the file where the compilation unit was found.
	 */
	private final File file;
	private final String language;
	private final String version;

	public CompilationUnit(File file, String language, String version) {
		super(null, "");
		this.file = file;
		this.language = language;
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return translator.i18n("Compilation Unit");
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
