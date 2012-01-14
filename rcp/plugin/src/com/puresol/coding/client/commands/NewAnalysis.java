package com.puresol.coding.client.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class NewAnalysis extends AbstractHandler implements IHandler {

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
	FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
	fileDialog.open();
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
