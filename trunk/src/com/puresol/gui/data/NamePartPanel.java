package com.puresol.gui.data;

import javax.swing.BoxLayout;
import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.TextField;

import org.apache.log4j.Logger;

import com.puresol.data.IllegalNamePartException;
import com.puresol.data.PersonNamePart;
import com.puresol.data.PersonNamePartType;

public class NamePartPanel extends Panel {

    private static final long serialVersionUID = -6012865138878226773L;

    private static final Logger logger =
	    Logger.getLogger(NamePartPanel.class);

    private TextField text;
    private EnumComboBox type;

    public NamePartPanel() {
	super();
	initUI();
    }

    public NamePartPanel(PersonNamePart namePart) {
	super();
	initUI();
	setDefault(namePart);
    }

    private void initUI() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	add(text = new TextField());
	add(type = EnumComboBox.forEnum(PersonNamePartType.class));
    }

    private void setDefault(PersonNamePart namePart) {
	text.setText(namePart.getName());
	type.setSelectedItem(namePart.getType());
    }

    public PersonNamePart get() {
	try {
	    return new PersonNamePart(text.getText(),
		    (PersonNamePartType) type.getSelectedItem());
	} catch (IllegalNamePartException e) {
	    logger.warn(e.getMessage(), e);
	}
	return null;
    }

    public static void main(String[] args) {
	Dialog dialog = new Dialog("Test", true);
	dialog.add(new NamePartPanel());
	dialog.pack();
	dialog.run();
    }
}
