package com.puresol.purifinity.client.common.analysis.controls;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;

public class CodeRangeTypeComboViewer extends ComboViewer {

	public CodeRangeTypeComboViewer(CCombo list) {
		super(list);
		init();
	}

	public CodeRangeTypeComboViewer(Combo list) {
		super(list);
		init();
	}

	public CodeRangeTypeComboViewer(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public CodeRangeTypeComboViewer(Composite parent) {
		super(parent);
		init();
	}

	private void init() {
		setContentProvider(ArrayContentProvider.getInstance());
		setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((CodeRangeType) element).getName();
			}
		});
	}

	@Override
	public void refresh() {
		super.refresh();
		setInput(CodeRangeType.values());
	}

}
