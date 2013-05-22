package com.puresol.coding.client.application.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

/**
 * @see http://www.eclipsezone.com/eclipse/forums/t31374.html
 * @see http://www.eclipse.org/forums/index.php/m/638711/
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrintHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// not needed
	}

	@Override
	public void dispose() {
		// not needed
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
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
		// not needed
	}

}
