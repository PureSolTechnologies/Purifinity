package com.puresol.coding.config;

import java.io.File;

import javax.i18n4java.Translator;

import com.puresol.config.PropertyDescription;

public class WorkspaceDirectory implements PropertyDescription<File> {

    private static final Translator translator = Translator
	    .getTranslator(WorkspaceDirectory.class);

    @Override
    public String getPropertyName() {
	return "codeanalysis.workspace";
    }

    @Override
    public String getDisplayName() {
	return translator.i18n("CodeAnalysis Workspace");
    }

    @Override
    public String getDescription() {
	return translator
		.i18n("Specifies the directory where all analysis files and evaluation results for different projects are to be stored.");
    }

    @Override
    public Class<File> getType() {
	return File.class;
    }

    @Override
    public File getDefaultValue() {
	return new File(System.getProperty("user.home") + "/CodeAnalysis");
    }

}
