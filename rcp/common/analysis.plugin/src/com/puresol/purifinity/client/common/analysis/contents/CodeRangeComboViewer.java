package com.puresol.purifinity.client.common.analysis.contents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Combo;

import com.puresol.purifinity.coding.analysis.api.CodeRange;

public class CodeRangeComboViewer extends ComboViewer implements
		IStructuredContentProvider {

	private final List<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public CodeRangeComboViewer(Combo list) {
		super(list);
		setContentProvider(this);
		setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				CodeRange codeRange = (CodeRange) element;
				return codeRange.getType() + " - " + codeRange.getSimpleName();
			}
		});
	}

	@Override
	public void dispose() {
		// intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		codeRanges.clear();
		if (newInput != null) {
			if (Collection.class.isAssignableFrom(newInput.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<CodeRange> codeRanges = (Collection<CodeRange>) newInput;
				this.codeRanges.addAll(codeRanges);
			}
		}
		refresh();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return codeRanges.toArray();
	}
}
