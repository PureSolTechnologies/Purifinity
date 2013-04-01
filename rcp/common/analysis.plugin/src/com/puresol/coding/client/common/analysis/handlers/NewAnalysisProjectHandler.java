package com.puresol.coding.client.common.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.puresol.coding.client.common.analysis.wizards.NewAnalysisWizard;

public class NewAnalysisProjectHandler extends AbstractHandler {

    private final ListenerList listeners = new ListenerList();

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
	listeners.add(handlerListener);
    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();
	NewAnalysisWizard wizard = new NewAnalysisWizard();
	WizardDialog wizardDialog = new WizardDialog(shell, wizard);
	wizardDialog.open();
	return null;
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
	listeners.remove(handlerListener);
    }

}
