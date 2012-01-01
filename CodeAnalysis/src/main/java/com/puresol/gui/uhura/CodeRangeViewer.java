package com.puresol.gui.uhura;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.puresol.coding.CodeRange;
import com.puresol.gui.CodeViewer;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.ParserTreeMetaData;

public class CodeRangeViewer extends JPanel {

	private static final long serialVersionUID = 3032479272552076138L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeViewer.class);

	private CodeRange codeRange;
	private JLabel information = new JLabel();
	private CodeViewer code;

	public CodeRangeViewer() {
		super();
		initUI();
	}

	public CodeRangeViewer(CodeRange codeRange) {
		super();
		initUI();
		setCodeRange(codeRange);
	}

	private void initUI() {
		setLayout(new BorderLayout());
		information.setBorder(new TitledBorder(translator.i18n("Information")));
		code = new CodeViewer();
		code.setEditable(false);
		code.setBorder(new TitledBorder(translator.i18n("Code")));
		code.setShowLineNumbers(true);
		add(information, BorderLayout.NORTH);
		add(new JScrollPane(code), BorderLayout.CENTER);
	}

	public void setCodeRange(CodeRange codeRange) {
		this.codeRange = codeRange;
		refresh();
	}

	public void refresh() {
		if (codeRange == null) {
			code.setText("No information available yet!");
		} else {
			ParserTree parserTree = codeRange.getParserTree();
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
