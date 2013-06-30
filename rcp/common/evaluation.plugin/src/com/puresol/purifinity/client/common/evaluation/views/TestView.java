package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

public class TestView extends ViewPart {
	private Text text;

	public TestView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);

		ExpandItem xpndtmTest = new ExpandItem(expandBar, SWT.NONE);
		xpndtmTest.setImage(ResourceManager.getPluginImage(
				"com.puresol.purifinity.client.application.plugin",
				"icons/16x16/application.png"));
		xpndtmTest.setExpanded(true);
		xpndtmTest.setText("Settings...");

		Composite composite = new Composite(expandBar, SWT.NONE);
		xpndtmTest.setControl(composite);
		xpndtmTest.setHeight(xpndtmTest.getControl().computeSize(SWT.DEFAULT,
				SWT.DEFAULT).y);

		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(0, 0, 189, 21);

		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("New ExpandItem");

		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite_1);
		xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl()
				.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		Group group = new Group(composite_1, SWT.NONE);
		group.setBounds(0, 0, 68, 68);

		text = new Text(group, SWT.BORDER);
		text.setBounds(0, 0, 79, 21);
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
