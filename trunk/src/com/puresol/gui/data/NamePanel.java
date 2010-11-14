package com.puresol.gui.data;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swingx.Button;
import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.connect.Slot;

import com.puresol.data.IllegalNamePartException;
import com.puresol.data.IllegalPersonNameException;
import com.puresol.data.PersonName;
import com.puresol.data.PersonNamePart;
import com.puresol.exceptions.StrangeSituationException;

/**
 * This class provides a GUI for input of extended names.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NamePanel extends Panel {

	private static final long serialVersionUID = 7298257976109780145L;

	private static final Translator translator = Translator
			.getTranslator(NamePanel.class);

	private ArrayList<NamePartPanel> nameParts = new ArrayList<NamePartPanel>();

	private Button reduce = null;
	private Button extend = null;
	private Panel partsPanel = null;

	public NamePanel() {
		initValues();
		initUI();
	}

	private void initValues() {
		nameParts.add(new NamePartPanel());
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(reduce = new Button(translator.i18n("reduce")), BorderLayout.WEST);
		add(partsPanel = new Panel(), BorderLayout.CENTER);
		add(extend = new Button(translator.i18n("extend")), BorderLayout.EAST);

		reduce.connect("start", this, "reduce");
		extend.connect("start", this, "extend");
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

	@Slot
	public void reduce() {
		nameParts.remove(nameParts.size() - 1);
		updateNamePartsPanel();
	}

	@Slot
	public void extend() {
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
			throw new StrangeSituationException(e);
		} catch (IllegalNamePartException e) {
			throw new IllegalPersonNameException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Dialog dialog = new Dialog("Test", true);
		dialog.setLayout(new BorderLayout());
		NamePanel name;
		dialog.add(name = new NamePanel(), BorderLayout.CENTER);
		dialog.pack();
		dialog.run();
		try {
			System.out.println(name.getPersonName().toString());
		} catch (IllegalPersonNameException e) {
			e.printStackTrace();
		}

	}
}
