package com.puresol.gui.data;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.puresol.data.IllegalNamePartException;
import com.puresol.data.PersonNamePart;
import com.puresol.data.PersonNamePartType;

/**
 * This class provides a part of NamePanel to specify a name part with the
 * specification of the type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NamePartPanel extends JPanel {

    private static final long serialVersionUID = -6012865138878226773L;

    private JTextField text;
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
	add(text = new JTextField());
	add(type = EnumComboBox.forEnum(PersonNamePartType.class));
    }

    private void setDefault(PersonNamePart namePart) {
	text.setText(namePart.getName());
	type.setSelectedItem(namePart.getType());
    }

    public PersonNamePart get() throws IllegalNamePartException {
	return new PersonNamePart(text.getText(),
		(PersonNamePartType) type.getSelectedItem());
    }
}
