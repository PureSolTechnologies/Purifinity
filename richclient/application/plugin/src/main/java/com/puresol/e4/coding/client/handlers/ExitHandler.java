package main.java.com.puresol.e4.coding.client.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

@SuppressWarnings("restriction")
public class ExitHandler {

	@Execute
	public void execute(IWorkbench workbench) {
		workbench.close();
	}

}