package com.puresoltechnologies.purifinity.client.application.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This handler was created due to an initialization bug in Eclipse! The
 * original handler {@link org.eclipse.ui.internal.handlers.IntroHandler} is
 * initialized to early and does not get
 * <code>introDescriptor = workbench.getIntroDescriptor();</code> initialized.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@SuppressWarnings("restriction")
public class MyIntroHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		PlatformUI.getWorkbench().getIntroManager().showIntro(window, false);
		return null;
	}
}
