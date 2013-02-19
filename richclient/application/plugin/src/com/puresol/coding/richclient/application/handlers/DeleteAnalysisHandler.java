package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.puresol.coding.analysis.api.AnalysisInformation;

@SuppressWarnings("restriction")
public class DeleteAnalysisHandler {

	@Execute
	public void execute(Shell shell, ESelectionService selectionService) {
		Object selection = selectionService.getSelection();
		if (selection instanceof AnalysisInformation) {
			AnalysisInformation information = (AnalysisInformation) selection;
			MessageDialog dialog = new MessageDialog(shell,
					"Delete Analysis Project", null,
					"Do you really want to delete analysis project '"
							+ information.getName() + "' from "
							+ information.getCreationTime() + "?",
					MessageDialog.QUESTION, new String[] { "Yes", "No" }, 0);
			dialog.open();
		}
	}
}