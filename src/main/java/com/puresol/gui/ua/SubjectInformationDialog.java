package com.puresol.gui.ua;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;

import com.puresol.gui.Application;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.ImageBox;
import com.puresol.gui.PureSolDialog;
import com.puresol.ua.SubjectInformation;

public class SubjectInformationDialog extends PureSolDialog {

    private static final long serialVersionUID = 8426447241357540447L;

    private static final Translator translator = Translator
	    .getTranslator(SubjectInformationDialog.class);

    private SubjectInformation subjectInformation = null;

    public SubjectInformationDialog(SubjectInformation subjectInformation) {
	super(Application.getInstance(),
		translator.i18n("Subject Information"), false);
	this.subjectInformation = subjectInformation;
	initUI();
    }

    private void initUI() {
	BorderLayout borderLayout = new BorderLayout();
	borderLayout.setHgap(10);
	borderLayout.setVgap(10);
	add(new ImageBox(subjectInformation.getPicture().getImage()),
		BorderLayout.WEST);

	setButtonVisible(DialogButtons.OK, true);

	pack();
    }

    public SubjectInformation getSubjectInformation() {
	return subjectInformation;
    }
}
