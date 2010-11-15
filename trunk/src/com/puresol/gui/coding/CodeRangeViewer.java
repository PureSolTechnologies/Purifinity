package com.puresol.gui.coding;

import javax.i18n4java.Translator;
import javax.swing.border.TitledBorder;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Label;
import javax.swingx.ScrollPane;

import com.puresol.uhura.ast.AST;

public class CodeRangeViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 3032479272552076138L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeViewer.class);

	private AST ast;
	private Label information;
	private CodeViewer code;

	public CodeRangeViewer() {
		super();
		initUI();
	}

	public CodeRangeViewer(AST ast) {
		super();
		initUI();
		setAST(ast);
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

	public void setAST(AST codeRange) {
		this.ast = codeRange;
		refresh();
	}

	public void refresh() {
		if (ast != null) {
			code.setText("No meta information available yet!");
		} else {
			information.setText("");
			code.setText("");
		}
	}
}
