/***************************************************************************
 *
 *   EntityAssignmentBox.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.entities.forms;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.i18n4java.Translator;
import javax.persistence.EntityManager;
import javax.swingx.AssignmentBox;
import javax.swingx.Button;
import javax.swingx.Panel;
import javax.swingx.connect.Slot;

import com.puresol.appserv.EntityManagerExtension;
import com.puresol.appserv.EntityManagerFactories;
import com.puresol.entities.forms.Template;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.gui.entities.EntityDialog;

public class EntityAssignmentBox extends Panel {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(EntityAssignmentBox.class);

	private AssignmentBox assignmentBox = null;
	private Class<?> type = null;
	private Collection<?> entities = null;
	private Collection<?> entityReservoir = null;
	private Button addButton = null;

	static public EntityAssignmentBox from(Class<?>[] type,
			Collection<?> entities) {
		if (type.length != 1) {
			throw new IllegalArgumentException(
					"Class array of type has to have a length of 1!");
		}
		return new EntityAssignmentBox(type[0], entities);
	}

	public EntityAssignmentBox(Class<?> type, Collection<?> entities) {
		super();
		this.type = type;
		this.entities = entities;
		initializeUI();
		initializeValues();
	}

	private void initializeUI() {
		setLayout(new BorderLayout());
		add(assignmentBox = new AssignmentBox(), BorderLayout.CENTER);
		try {
			Template template = (Template) type.getAnnotation(Template.class);
			if (template != null) {
				add(addButton = new Button(translator.i18n("Add new {0}",
						template.name())), BorderLayout.SOUTH);
			} else {
				add(addButton = new Button(translator.i18n("Add new...")),
						BorderLayout.SOUTH);
			}
			addButton.connect("start", this, "add");
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (SecurityException e) {
			throw new StrangeSituationException(e);
		}
	}

	private void initializeValues() {
		initializeReservoir();
		initializeSelection();
	}

	private void initializeReservoir() {
		EntityManager entityManager = EntityManagerFactories.create(type)
				.createEntityManager();
		EntityManagerExtension extension = new EntityManagerExtension(
				entityManager);
		entityReservoir = extension.getAll(type);
		Vector<String> reservoir = new Vector<String>();
		if (entityReservoir != null) {
			for (Object o : entityReservoir) {
				reservoir.add(o.toString());
			}
		}
		assignmentBox.setReservoir(reservoir);
		extension = null;
		entityManager.close();
	}

	private void initializeSelection() {
		Vector<String> selection = new Vector<String>();
		if (entities != null) {
			for (Object o : entities) {
				selection.add(o.toString());
			}
		}
		assignmentBox.setSelection(selection);
	}

	public Collection<?> getSelectedEntities() {
		Vector<String> selection = assignmentBox.getSelection();
		ArrayList<Object> entities = new ArrayList<Object>();
		for (String string : selection) {
			for (Object o : entityReservoir) {
				if (o.toString().equals(string)) {
					entities.add(o);
				}
			}
		}
		return entities;
	}

	@Slot
	public void add() {
		EntityDialog dialog = EntityDialog.create(type);
		if (dialog.run()) {
			initializeReservoir();
		}
	}
}
