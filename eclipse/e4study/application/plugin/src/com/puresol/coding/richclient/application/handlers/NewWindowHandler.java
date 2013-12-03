package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;

@SuppressWarnings("restriction")
public class NewWindowHandler {

	@Execute
	public void execute(MApplication application) {
		MTrimmedWindow newWindow = MBasicFactory.INSTANCE.createTrimmedWindow();
		newWindow.setLabel("New Window");
		newWindow.setWidth(800);
		newWindow.setHeight(600);
		application.getChildren().add(newWindow);
	}
}