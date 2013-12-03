package com.puresol.coding.richclient.application.handlers;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.handlers.ShowPerspectiveHandler;

@SuppressWarnings("restriction")
public class OpenPerspectiveHandler {

	@Execute
	public void execute(ECommandService commandService,
			EHandlerService handlerService) throws ExecutionException {
		ShowPerspectiveHandler handler = new ShowPerspectiveHandler();
		handlerService.activateHandler(
				"org.eclipse.ui.perspectives.showPerspective", handler);
		Command command = commandService
				.getCommand("org.eclipse.ui.perspectives.showPerspective");
		ParameterizedCommand createdCommand;
		if (command == null) {
			createdCommand = commandService.createCommand(
					"org.eclipse.ui.perspectives.showPerspective", null);
		} else {
			createdCommand = new ParameterizedCommand(command, null);
		}
		handlerService.executeHandler(createdCommand, null);
	}

}