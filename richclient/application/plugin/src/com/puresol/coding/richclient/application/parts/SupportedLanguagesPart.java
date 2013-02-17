package com.puresol.coding.richclient.application.parts;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.coding.analysis.api.ProgrammingLanguages;

public class SupportedLanguagesPart implements SelectionListener {

	private List list = null;

	@Inject
	public SupportedLanguagesPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		fd_toolBar.bottom = new FormAttachment(0, 34);
		fd_toolBar.right = new FormAttachment(0, 440);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmRefresh = new ToolItem(toolBar, SWT.NONE);
		tltmRefresh.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/arrow_refresh.png"));
		tltmRefresh.setText("Refresh");
		tltmRefresh.addSelectionListener(this);

		list = new List(composite, SWT.BORDER);
		FormData fd_list = new FormData();
		fd_list.right = new FormAttachment(100, -10);
		fd_list.left = new FormAttachment(0, 10);
		fd_list.top = new FormAttachment(toolBar, 10);
		fd_list.bottom = new FormAttachment(100, -10);
		list.setLayoutData(fd_list);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		list.removeAll();
		ProgrammingLanguages languages = ProgrammingLanguages.getInstance();
		for (AnalyzableProgrammingLanguage language : languages.getAll()) {
			list.add(language.getName());
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
