package com.puresol.purifinity.client.common.analysis.views;

import java.io.IOException;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.contents.ProgrammingLanguageViewer;
import com.puresol.purifinity.client.common.analysis.controls.GrammarCanvas;
import com.puresol.purifinity.client.common.analysis.grammar.RenderException;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.uhura.grammar.GrammarException;

public class GrammarView extends ViewPart implements ISelectionChangedListener {

	private Combo languageCombo;
	private ProgrammingLanguageViewer languageViewer;
	private ProgrammingLanguage selectedProgrammingLanguage;
	private GrammarCanvas grammarCanvas;

	public GrammarView() {
		super();
	}

	@Override
	public void setFocus() {
		languageCombo.setFocus();
	}

	@Override
	public void createPartControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label lblLanguage = new Label(composite, SWT.NONE);
		FormData fd_lblLanguage = new FormData();
		fd_lblLanguage.top = new FormAttachment(0, 10);
		lblLanguage.setLayoutData(fd_lblLanguage);
		lblLanguage.setAlignment(SWT.RIGHT);
		lblLanguage.setText("Language:");

		languageCombo = new Combo(composite, SWT.READ_ONLY);
		fd_lblLanguage.right = new FormAttachment(languageCombo, -6);
		FormData fd_languageCombo = new FormData();
		fd_languageCombo.right = new FormAttachment(100, -10);
		fd_languageCombo.left = new FormAttachment(0, 75);
		fd_languageCombo.top = new FormAttachment(0, 10);
		languageCombo.setLayoutData(fd_languageCombo);

		languageViewer = new ProgrammingLanguageViewer(languageCombo);

		ScrolledComposite scrolledComposite = new ScrolledComposite(composite,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.top = new FormAttachment(languageCombo, 6);
		fd_scrolledComposite.bottom = new FormAttachment(100, -10);
		fd_scrolledComposite.left = new FormAttachment(0, 10);
		fd_scrolledComposite.right = new FormAttachment(100, -10);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setAlwaysShowScrollBars(true);

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
