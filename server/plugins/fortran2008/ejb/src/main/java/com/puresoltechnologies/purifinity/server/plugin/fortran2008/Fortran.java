package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(ProgrammingLanguageAnalyzer.class)
public class Fortran extends AbstractProgrammingLanguageAnalyzer {

	public static final String ID = Fortran.class.getName();
	public static final String NAME = "Fortran";
	public static final String VERSION = "2008";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();

	public static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
			".f95", ".for" };

	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"Fortran Source File Suffixes for Fixed Form",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a comma separated list of file name suffixes which are to be use to identify pre Fortran 90 fixed form sources.",
						String.class, "suffixes.form.fixed", "/", "f,f77"));
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"Fortran Source File Suffixes for Free Form",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a comma separated list of file name suffixes which are to be use to identify Fortran 90 free form sources.",
						String.class, "suffixes.form.free", "/", "f90"));
	}

	private SourceForm sourceForm = SourceForm.FREE_FORM;

	public Fortran() {
		super(NAME, VERSION);
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
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

}
