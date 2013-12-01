package com.puresol.coding.richclient.application.controls;

import java.awt.Dimension;
import java.io.IOException;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.coding.lang.api.LanguageGrammar;
import com.puresoltechnologies.coding.lang.api.ProgrammingLanguage;
import com.puresoltechnologies.coding.richclient.application.grammar.GrammarRenderer;
import com.puresoltechnologies.coding.richclient.application.grammar.RenderException;
import com.puresoltechnologies.uhura.grammar.GrammarException;
import com.puresoltechnologies.uhura.grammar.GrammarReader;
import com.puresoltechnologies.uhura.parser.ParserTree;

public class GrammarCanvas extends Canvas implements PaintListener {

	private final GrammarRenderer renderer = new GrammarRenderer();
	private ProgrammingLanguage language = null;
	private Dimension preferredSize = new Dimension();

	public GrammarCanvas(Composite parent, int style) {
		super(parent, style);
		FillLayout layout = new FillLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
		setLayout(layout);
		RGB white = new RGB(255, 255, 255);
		Color color = new Color(getDisplay(), white);
		try {
			setBackground(color);
			addPaintListener(this);
		} finally {
			color.dispose();
		}
	}

	@Override
	public void paintControl(PaintEvent event) {
		if (language == null) {
			return;
		}
		synchronized (renderer) {
			GC context = event.gc;
			// Image image = new Image(context.getDevice(), preferredSize.width,
			// preferredSize.height);
			// try {
			// GC gc = new GC(image);
			// try {
			RGB white = new RGB(255, 255, 255);
			Color whiteColor = new Color(getDisplay(), white);
			try {
				context.setBackground(whiteColor);
			} finally {
				whiteColor.dispose();
			}
			Rectangle clientArea = getClientArea();
			renderer.render(context, clientArea.x, clientArea.y, clientArea.x
					+ clientArea.width - 1, clientArea.y + clientArea.height
					- 1);
			// } finally {
			// gc.dispose();
			// }
			// context.drawImage(image, 0, 0);
			// } finally {
			// image.dispose();
			// }
		}
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage)
			throws GrammarException, IOException, RenderException {
		language = programmingLanguage;
		if (language != null) {
			LanguageGrammar grammar = language.getGrammar();
			if (grammar == null) {
				return;
			}
			GrammarReader grammarReader = new GrammarReader(
					grammar.getGrammarDefinition());
			try {
				ParserTree ast = grammarReader.getAST();
				synchronized (renderer) {
					renderer.setGrammar(ast);
					preferredSize = renderer.getPreferredSize();
					// preferredSize.height *= 0.7;
					// preferredSize.width *= 0.7;
					setSize(preferredSize.width, preferredSize.height);
					getParent().redraw();
				}
			} finally {
				grammarReader.close();
			}
		}
	}

}
