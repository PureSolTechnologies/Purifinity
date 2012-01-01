package com.puresol.coding.metrics.codedepth.config;

import javax.i18n4java.Translator;

import com.puresol.config.PropertyDescription;

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
public class ModuleFail implements PropertyDescription<Integer> {

	private static final Translator translator = Translator
			.getTranslator(ModuleFail.class);

	@Override
	public String getPropertyName() {
		return "codedepth.modules.fail";
	}

	@Override
	public String getDisplayName() {
		return translator.i18n("CodeDepth for Modules for Low Quality");
	}

	@Override
	public String getDescription() {
		return translator.i18n("Specify here the codepth for modules to fail.");
	}

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public Integer getDefaultValue() {
		return 8;
	}

}
