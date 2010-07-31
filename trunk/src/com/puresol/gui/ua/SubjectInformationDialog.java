package com.puresol.gui.ua;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swingx.Dialog;
import javax.swingx.ImageBox;

import com.puresol.ua.SubjectInformation;

public class SubjectInformationDialog extends Dialog {

	private static final long serialVersionUID = 8426447241357540447L;

	private static final Translator translator = Translator
			.getTranslator(SubjectInformationDialog.class);

	private SubjectInformation subjectInformation = null;

	public SubjectInformationDialog(SubjectInformation subjectInformation) {
		super(translator.i18n("Subject Information"), false);
		this.subjectInformation = subjectInformation;
		initUI();
	}

	private void initUI() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		setLayout(borderLayout);
		add(new ImageBox(subjectInformation.getPicture().getImage()),
				BorderLayout.WEST);
		add(getDefaultOKButton(), BorderLayout.SOUTH);
		pack();
	}

	public SubjectInformation getSubjectInformation() {
		return subjectInformation;
	}
}
