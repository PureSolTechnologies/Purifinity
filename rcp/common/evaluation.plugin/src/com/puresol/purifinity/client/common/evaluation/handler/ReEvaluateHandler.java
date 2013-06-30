package com.puresol.purifinity.client.common.evaluation.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.puresol.purifinity.client.common.analysis.views.AnalysisRunSelection;
import com.puresol.purifinity.client.common.analysis.views.AnalysisRunsView;
import com.puresol.purifinity.client.common.evaluation.jobs.EvaluationJob;

public class ReEvaluateHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
	IWorkbenchWindow activeWorkbench = PlatformUI.getWorkbench()
		.getActiveWorkbenchWindow();
	IWorkbenchPage activePage = activeWorkbench.getActivePage();
	AnalysisRunsView analysisRunView = (AnalysisRunsView) activePage
		.findView(AnalysisRunsView.class.getName());
	AnalysisRunSelection selection = (AnalysisRunSelection) analysisRunView
		.getSelection();
	if (selection != null) {
	    EvaluationJob job = new EvaluationJob(selection.getAnalysisRun(),
		    true);
	    job.schedule();
	} else {
	    MessageDialog dialog = new MessageDialog(
		    activeWorkbench.getShell(),
		    "Re-evalute",
		    null,
		    "An analysis run needs to be selected in Analysis Runs view.",
		    MessageDialog.WARNING,
		    new String[] { IDialogConstants.OK_LABEL }, 0);
	    dialog.open();
	}
	return null;
    }
}
