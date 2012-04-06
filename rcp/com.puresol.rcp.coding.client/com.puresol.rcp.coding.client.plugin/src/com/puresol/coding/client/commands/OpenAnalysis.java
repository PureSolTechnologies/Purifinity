package com.puresol.coding.client.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ILog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.Activator;

public class OpenAnalysis extends AbstractHandler implements IHandler {

    private static final ILog logger = Activator.getDefault().getLog();

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

    @Override
    public void dispose() {
	// not needed...
    }

    @Override
    public String execute(ExecutionEvent event) throws ExecutionException {
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();
	AnalysisStore analysisStore = AnalysisStoreFactory.getInstance();

	ListDialog listDialog = new ListDialog(shell);
	listDialog.setTitle("Open Analysis");
	listDialog.setMessage("Please, select the analysis to be opened.");
	listDialog.setContentProvider(ArrayContentProvider.getInstance());
	listDialog.setLabelProvider(new LabelProvider());
	listDialog.setInput(analysisStore.getAllAnalysisInformation());
	if (listDialog.open() == Dialog.OK) {
	    Object[] infos = listDialog.getResult();
	    AnalysisInformation analysisInformation = (AnalysisInformation) infos[0];
	    Analysis projectAnalyzer = analysisStore
		    .loadAnalysis(analysisInformation.getUUID());
	    projectAnalyzer.schedule();
	}
	return "";
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isHandled() {
	return true;
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

}
