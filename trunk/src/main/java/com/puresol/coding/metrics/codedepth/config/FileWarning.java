package com.puresol.coding.metrics.codedepth.config;

import javax.i18n4java.Translator;

import com.puresol.config.properties.PropertyDescription;

/**
 * This class is a configurator PropertyDescription for CodeDepth. The following
 * code range types have configurations for LOW, MEDIUM and HIGH quality:
 * 
 * <pre>
 * FILE
 * CLASS
 * INTERFACE
 * ENUMERATION
 * ANNOTATION
 * MODULE 
 * CONSTRUCTOR
 * METHOD
 * PROGRAM
 * SUBROUTINE
 * FUNCTION
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileWarning implements PropertyDescription<Integer> {

	private static final Translator translator = Translator
			.getTranslator(FileWarning.class);

	@Override
	public String getPropertyName() {
		return "codedepth.files.warning";
	}

	@Override
	public String getDisplayName() {
		return translator.i18n("CodeDepth for Files for Medium Quality");
	}

	@Override
	public String getDescription() {
		return translator.i18n("Specify here the codepth for files to raise warning.");
	}

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public Integer getDefaultValue() {
		return 6;
	}

}
