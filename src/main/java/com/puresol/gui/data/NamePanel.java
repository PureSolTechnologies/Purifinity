package com.puresol.gui.data;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.puresol.data.IllegalNamePartException;
import com.puresol.data.IllegalPersonNameException;
import com.puresol.data.PersonName;
import com.puresol.data.PersonNamePart;

/**
 * This class provides a GUI for input of extended names.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NamePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 7298257976109780145L;

    private static final Translator translator = Translator
	    .getTranslator(NamePanel.class);

    private final ArrayList<NamePartPanel> nameParts = new ArrayList<NamePartPanel>();

    private final JButton reduce = new JButton(translator.i18n("reduce"));
    private final JButton extend = new JButton(translator.i18n("extend"));
    private final JPanel partsPanel = new JPanel();

    public NamePanel() {
	initValues();
	initUI();
    }

    private void initValues() {
	nameParts.add(new NamePartPanel());
    }

    private void initUI() {
	setLayout(new BorderLayout());
	add(reduce, BorderLayout.WEST);
	add(partsPanel, BorderLayout.CENTER);
	add(extend, BorderLayout.EAST);

	reduce.addActionListener(this);
	extend.addActionListener(this);
	partsPanel.setLayout(new BoxLayout(partsPanel, BoxLayout.X_AXIS));
	updateNamePartsPanel();
    }

    private void updateNamePartsPanel() {
	partsPanel.removeAll();
	for (NamePartPanel namePartPanel : nameParts
		.toArray(new NamePartPanel[0])) {
	    partsPanel.add(namePartPanel);
	}
	partsPanel.revalidate();
    }

    private void reduce() {
	nameParts.remove(nameParts.size() - 1);
	updateNamePartsPanel();
    }

    private void extend() {
	nameParts.add(new NamePartPanel());
	updateNamePartsPanel();
    }

    public PersonName getPersonName() throws IllegalPersonNameException {
	try {
	    ArrayList<PersonNamePart> personNameParts = new ArrayList<PersonNamePart>();
	    for (NamePartPanel namePart : nameParts
		    .toArray(new NamePartPanel[0])) {
		personNameParts.add(namePart.get());
	    }
	    PersonName personName = new PersonName(personNameParts);
	    return personName;
	} catch (IllegalPersonNameException e) {
	    throw new RuntimeException(e);
	} catch (IllegalNamePartException e) {
	    throw new IllegalPersonNameException(e.getMessage());
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == reduce) {
	    reduce();
	} else if (e.getSource() == extend) {
	    extend();
	}
    }
}
