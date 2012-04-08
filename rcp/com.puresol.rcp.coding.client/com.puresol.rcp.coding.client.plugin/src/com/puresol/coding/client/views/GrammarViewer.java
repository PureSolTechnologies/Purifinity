package com.puresol.coding.client.views;

import java.io.IOException;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.client.controls.GrammarCanvas;
import com.puresol.coding.client.controls.ProgrammingLanguageViewer;
import com.puresol.coding.client.grammar.RenderException;
import com.puresol.uhura.grammar.GrammarException;

public class GrammarViewer extends ViewPart implements
	ISelectionChangedListener {

    private Combo languageCombo;
    private ProgrammingLanguageViewer languageViewer;
    private ProgrammingLanguage selectedProgrammingLanguage;
    private GrammarCanvas grammarCanvas;

    public GrammarViewer() {
	super();
    }

    @Override
    public void setFocus() {
	languageCombo.setFocus();
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new BorderLayout(0, 0));

	Composite composite = new Composite(parent, SWT.NONE);
	composite.setLayoutData(BorderLayout.NORTH);
	composite.setLayout(new FillLayout(SWT.HORIZONTAL));

	Label lblLanguage = new Label(composite, SWT.NONE);
	lblLanguage.setAlignment(SWT.RIGHT);
	lblLanguage.setText("Language:");

	languageCombo = new Combo(composite, SWT.READ_ONLY);

	languageViewer = new ProgrammingLanguageViewer(languageCombo);

	ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
		SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	scrolledComposite.setAlwaysShowScrollBars(true);
	scrolledComposite.setLayoutData(BorderLayout.CENTER);

	grammarCanvas = new GrammarCanvas(scrolledComposite, SWT.NONE);

	scrolledComposite.setContent(grammarCanvas);
	scrolledComposite.setMinSize(grammarCanvas.computeSize(SWT.DEFAULT,
		SWT.DEFAULT));
	languageViewer.addSelectionChangedListener(this);

    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
	try {
	    if (event.getSource() == languageViewer) {
		ProgrammingLanguage selected = (ProgrammingLanguage) ((IStructuredSelection) languageViewer
			.getSelection()).getFirstElement();
		if (selected != selectedProgrammingLanguage) {
		    grammarCanvas.setProgrammingLanguage(selected);
		}
	    }
	} catch (GrammarException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (RenderException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
