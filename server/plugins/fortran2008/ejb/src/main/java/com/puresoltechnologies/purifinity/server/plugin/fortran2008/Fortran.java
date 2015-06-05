package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
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

	private static final String[] FIXED_FORM_FILE_PATTERNS = { ".*\\.f",
			".*\\.for" };
	private static final String[] FREE_FORM_FILE_PATTERNS = { ".*\\.f90",
			".*\\.f95", ".*\\.f03", ".*\\.f08" };

	private static final String FIXED_FORM_FILE_PATTERNS_PROPERTY = "suffixes.form.fixed";
	private static final String FREE_FORM_FILE_PATTERNS_PROPERTY = "suffixes.form.free";

	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"Fortran Source File Patterns for Fixed Form",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of file patterns in regular expression format which are to be use to identify pre Fortran 90 fixed form sources. Each pattern is placed on its own line.",
						String.class, FIXED_FORM_FILE_PATTERNS_PROPERTY, "/",
						patternsToString(FIXED_FORM_FILE_PATTERNS)));
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"Fortran Source File Patterns for Free Form",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of file patterns in regular expression format which are to be use to identify Fortran 90 free form sources. Each pattern is placed on its own line.",
						String.class, FREE_FORM_FILE_PATTERNS_PROPERTY, "/",
						patternsToString(FREE_FORM_FILE_PATTERNS)));
	}

	private SourceForm sourceForm = SourceForm.FREE_FORM;

	private String[] freeFormFilePatterns = FREE_FORM_FILE_PATTERNS;
	private String[] fixedFormFilePatterns = FIXED_FORM_FILE_PATTERNS;

	public Fortran() {
		super(NAME, VERSION);
	}

	@Override
	protected String[] getValidFilePatterns() {
		List<String> patternsList = Arrays.asList(fixedFormFilePatterns);
		patternsList.addAll(Arrays.asList(freeFormFilePatterns));
		return patternsList.toArray(new String[patternsList.size()]);
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
	}

	@Override
	public FortranAnalyzer restoreAnalyzer(File file) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (FortranAnalyzer) ois.readObject();
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
	public FortranAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
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
	public void setConfigurationParameter(ConfigurationParameter<?> parameter,
			Object value) {
		if (FIXED_FORM_FILE_PATTERNS_PROPERTY
				.equals(parameter.getPropertyKey())) {
			fixedFormFilePatterns = stringToPatterns((String) value);
		} else if (FREE_FORM_FILE_PATTERNS_PROPERTY.equals(parameter
				.getPropertyKey())) {
			freeFormFilePatterns = stringToPatterns((String) value);
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}

	@Override
	public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
		if (FIXED_FORM_FILE_PATTERNS_PROPERTY
				.equals(parameter.getPropertyKey())) {
			return patternsToString(fixedFormFilePatterns);
		} else if (FREE_FORM_FILE_PATTERNS_PROPERTY.equals(parameter
				.getPropertyKey())) {
			return patternsToString(freeFormFilePatterns);
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}
}
