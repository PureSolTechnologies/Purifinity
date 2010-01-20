package com.puresol.gui.coding.analysis;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.Panel;

import com.puresol.coding.Duplication;
import com.puresol.gui.coding.CodeRangeViewer;

public class DuplicationViewer extends Panel {

	private static final long serialVersionUID = -3537248596117222356L;

	private static final Translator translator = Translator
			.getTranslator(DuplicationViewer.class);

	private CodeRangeViewer leftCodeRange;
	private CodeRangeViewer rightCodeRange;

	private Duplication duplication;

	public DuplicationViewer() {
		super();
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(leftCodeRange = new CodeRangeViewer());
		leftCodeRange.setBorder(new TitledBorder(translator
				.i18n("Left Code Range")));
		add(rightCodeRange = new CodeRangeViewer());
		rightCodeRange.setBorder(new TitledBorder(translator
				.i18n("Right Code Range")));
	}

	public void setDuplication(Duplication duplication) {
		this.duplication = duplication;
		refresh();
	}

	public void refresh() {
		if (duplication != null) {
			leftCodeRange.setCodeRange(duplication.getLeft());
			rightCodeRange.setCodeRange(duplication.getRight());
		} else {
			leftCodeRange.setCodeRange(null);
			rightCodeRange.setCodeRange(null);
		}
	}
}
