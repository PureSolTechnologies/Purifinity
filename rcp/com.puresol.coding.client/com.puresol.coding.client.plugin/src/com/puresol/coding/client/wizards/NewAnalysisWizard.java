package com.puresol.coding.client.wizards;

import javax.i18n4java.Translator;

import org.eclipse.jface.wizard.Wizard;

public class NewAnalysisWizard extends Wizard {

	private static final Translator translator = Translator
			.getTranslator(NewAnalysisWizard.class);

	public NewAnalysisWizard() {
		super();
		setWindowTitle(translator.i18n("New Analysis"));
		addPage(new NewAnalysisGeneralSettingsPage());
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
