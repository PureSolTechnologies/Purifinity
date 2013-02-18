package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.puresol.coding.richclient.application.wizards.NewAnalysisWizard;

@SuppressWarnings("restriction")
public class NewAnalysisHandler {

	@Execute
	public void execute(Shell shell) {
		WizardDialog wizardDialog = new WizardDialog(shell,
				new NewAnalysisWizard());
		wizardDialog.open();
	}

}