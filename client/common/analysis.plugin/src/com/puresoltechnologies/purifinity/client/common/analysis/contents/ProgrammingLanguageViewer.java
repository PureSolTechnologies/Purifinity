package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.coding.analysis.impl.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.lang.api.ProgrammingLanguage;

/**
 * This class is a simple viewer for combo boxes which provide a selection for
 * programming languages. The only action after initialization to be done is to
 * add a listener, if needed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguageViewer extends ComboViewer {

    public ProgrammingLanguageViewer(CCombo list) {
	super(list);
	init();
    }

    public ProgrammingLanguageViewer(Combo list) {
	super(list);
	init();
    }

    public ProgrammingLanguageViewer(Composite parent) {
	super(parent);
	init();
    }

    public ProgrammingLanguageViewer(Composite parent, int style) {
	super(parent, style);
	init();
    }

    private void init() {
	setContentProvider(ArrayContentProvider.getInstance());
	setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		return ((ProgrammingLanguage) element).getName();
	    }
	});
    }

    @Override
    public void refresh() {
	super.refresh();
	ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance();
	try {
	    setInput(programmingLanguages.getAll());
	} finally {
	    IOUtils.closeQuietly(programmingLanguages);
	}
    }
}
