package com.puresol.gui.coding;

import javax.i18n4j.Translator;
import javax.swing.border.TitledBorder;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Label;
import javax.swingx.ScrollPane;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.parser.TokenStream;

public class CodeRangeViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 3032479272552076138L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeViewer.class);

	private CodeRange codeRange;
	private Label information;
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
		information = new Label();
		information.setBorder(new TitledBorder(translator.i18n("Information")));
		code = new CodeViewer();
		code.setEditable(false);
		code.setBorder(new TitledBorder(translator.i18n("Code")));
		code.setShowLineNumbers(true);
		setNorth(information);
		setCenter(new ScrollPane(code));
	}

	public void setCodeRange(CodeRange codeRange) {
		this.codeRange = codeRange;
		refresh();
	}

	public void refresh() {
		if (codeRange != null) {
			TokenStream tokenStream = codeRange.getTokenStream();
			int startLine = tokenStream.get(codeRange.getStartId())
					.getStartLine();
			int stopLine = tokenStream.get(codeRange.getStopId()).getStopLine();
			information.setText("<html><body>"
					+ tokenStream.getFile().getName() + ":" + startLine + "-"
					+ stopLine + "<br/>"
					+ codeRange.getCodeRangeType().getIdentifier() + " "
					+ codeRange.getName() + "</body></html>");
			code.setText(codeRange.getText());
		} else {
			information.setText("");
			code.setText("");
		}
	}
}
