package com.puresol.coding.client;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.ProgrammingLanguages;
import com.puresol.rendering.RenderException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.ui.rendering.GrammarRenderer;

public class GrammarViewer extends ViewPart implements
	ISelectionChangedListener {

    private Combo languageCombo;
    private ComboViewer languageViewer;
    private ProgrammingLanguage selectedProgrammingLanguage;
    private Composite grammarViewer;

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

	List<ProgrammingLanguage> all = ProgrammingLanguages.getAll();
	languageViewer = new ComboViewer(languageCombo);

	ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
		SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	scrolledComposite.setAlwaysShowScrollBars(true);
	scrolledComposite.setLayoutData(BorderLayout.CENTER);

	grammarViewer = new Composite(scrolledComposite, SWT.NONE);
	grammarViewer.setLayout(null);

	grammarViewer.addPaintListener(new PaintListener() {

	    @Override
	    public void paintControl(PaintEvent event) {
		try {
		    if (selectedProgrammingLanguage == null) {
			return;
		    }
		    Grammar grammar = selectedProgrammingLanguage.getGrammar();
		    if (grammar == null) {
			return;
		    }
		    GrammarReader grammarReader = new GrammarReader(grammar
			    .getGrammarDefinition());
		    ParserTree ast = grammarReader.getAST();
		    GrammarRenderer grammarRenderer = new GrammarRenderer(ast);
		    Dimension preferredSize = grammarRenderer
			    .getPreferredSize();
		    Composite viewer = (Composite) event.getSource();

		    GC context = event.gc;
		    context.setForeground(new Color(viewer.getDisplay(),
			    new RGB(255, 155, 0)));
		    context.drawOval(0, 0, preferredSize.width,
			    preferredSize.height);
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
	});
	scrolledComposite.setContent(grammarViewer);
	scrolledComposite.setMinSize(grammarViewer.computeSize(SWT.DEFAULT,
		SWT.DEFAULT));
	languageViewer.setContentProvider(ArrayContentProvider.getInstance());
	languageViewer.setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		return ((ProgrammingLanguage) element).getName();
	    }
	});
	languageViewer.setInput(all);
	languageViewer.addSelectionChangedListener(this);

    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
	if (event.getSource() == languageViewer) {
	    ProgrammingLanguage selected = (ProgrammingLanguage) ((IStructuredSelection) languageViewer
		    .getSelection()).getFirstElement();
	    if (selected != selectedProgrammingLanguage) {
		selectedProgrammingLanguage = selected;
		grammarViewer.setSize(new Point(2000, 2000));
		System.out.println(grammarViewer.getSize().toString());
		grammarViewer.redraw();
	    }
	}
    }
}
