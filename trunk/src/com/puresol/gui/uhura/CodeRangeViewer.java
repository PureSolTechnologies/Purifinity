package com.puresol.gui.uhura;

import javax.i18n4java.Translator;
import javax.swing.border.TitledBorder;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.CodeViewer;
import javax.swingx.Label;
import javax.swingx.ScrollPane;

import com.puresol.trees.TreeIterator;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.ast.ParserTreeMetaData;
import com.puresol.uhura.lexer.Token;

public class CodeRangeViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 3032479272552076138L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeViewer.class);

	private ParserTree parserTree;
	private Label information;
	private CodeViewer code;

	public CodeRangeViewer() {
		super();
		initUI();
	}

	public CodeRangeViewer(ParserTree ast) {
		super();
		initUI();
		setParserTree(ast);
	}

	private void initUI() {
		information = new Label();
		information.setBorder(new TitledBorder(translator.i18n("Information")));
		code = new CodeViewer();
		code.setEditable(false);
		code.setBorder(new TitledBorder(translator.i18n("Code")));
		code.setShowLineNumbers(true);
		setNorth(information);
		setCenter(new ScrollPane(code));
	}

	public void setParserTree(ParserTree parserTree) {
		this.parserTree = parserTree;
		refresh();
	}

	public void refresh() {
		if (parserTree == null) {
			code.setText("No information available yet!");
		} else {
			ParserTreeMetaData metaData = parserTree.getMetaData();
			if (metaData != null) {
				information.setText(metaData.getSourceName());
			}
			final StringBuffer buffer = new StringBuffer();
			TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
					parserTree);
			do {
				Token token = iterator.getCurrentNode().getToken();
				if (token != null) {
					buffer.append(token.getText());
				}

			} while (iterator.goForward());
			code.setText(buffer.toString());
		}
	}
}
