package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;

@Stateless
@Remote(ProgrammingLanguageAnalyzer.class)
public class Fortran extends AbstractProgrammingLanguageAnalyzer {

	public static final String NAME = "Fortran";
	public static final Version VERSION = new Version(2008, 0, 0);

	public static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
			".f95", ".for" };

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();
	private static Fortran instance = null;

	private SourceForm sourceForm = SourceForm.FREE_FORM;

	public Fortran() {
		super(NAME, VERSION);
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (CodeAnalyzer) ois.readObject();
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			/*
			 * XXX This needs to be null to go on with the language try out...
			 * :-(
			 */
			return null;
		}
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
		return new FortranAnalyzer(sourceCodeLocation);
	}

	public void setSourceForm(SourceForm sourceForm) {
		this.sourceForm = sourceForm;
	}

	public SourceForm getSourceForm() {
		return sourceForm;
	}

	@Override
	public LanguageGrammar getGrammar() {
		return FortranGrammar.getInstance();
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		ServiceLoader<T> service = ServiceLoader.load(clazz);
		Iterator<T> iterator = service.iterator();
		T result = iterator.next();
		if (iterator.hasNext()) {
			throw new RuntimeException(
					"There is more than one implementation available for '"
							+ clazz.getName() + "'!");
		}
		return result;
	}
}
