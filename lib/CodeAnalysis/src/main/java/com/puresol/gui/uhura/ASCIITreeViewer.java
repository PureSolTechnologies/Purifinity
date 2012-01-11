package com.puresol.gui.uhura;

import java.io.PrintStream;

import com.puresol.gui.SaveableTextArea;
import com.puresol.io.StringOutputStream;
import com.puresol.trees.TreePrinter;
import com.puresol.uhura.parser.ParserTree;

public class ASCIITreeViewer extends SaveableTextArea {

	private static final long serialVersionUID = 6542830047386533983L;

	private ParserTree grammarAST = null;

	public ASCIITreeViewer() {
		super();
	}

	public ASCIITreeViewer(ParserTree grammarAST) {
		super();
		setGrammarAST(grammarAST);
	}

	public void setGrammarAST(ParserTree grammarAST) {
		this.grammarAST = grammarAST;
		refresh();
	}

	private void refresh() {
		if (grammarAST == null) {
			setText("");
		} else {
			StringOutputStream outputStream = new StringOutputStream();
			TreePrinter treePrinter = new TreePrinter(new PrintStream(
					outputStream));
			treePrinter.println(grammarAST);
			setText(outputStream.toString());
		}
	}

}