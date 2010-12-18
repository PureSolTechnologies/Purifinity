/***************************************************************************
 *
 *   EntityTableEditor.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.entities;

import java.awt.BorderLayout;
import java.util.List;

import javax.i18n4java.Translator;
import javax.swing.JOptionPane;
import javax.swingx.Application;
import javax.swingx.Button;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

public class EntityTableEditor extends Panel {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(EntityTableEditor.class);

	private List<?> entities = null;
	private Class<?> entityType = null;
	private Button showButton = null;
	private Button addButton = null;
	private Button changeButton = null;
	private Button deleteButton = null;
	private EntityTable table = null;

	public EntityTableEditor() {
		super();
		initDesktop();
	}

	public EntityTableEditor(Class<?> entityType, List<?> entities) {
		super();
		initDesktop();
		setEntities(entityType, entities);
	}

	private void initDesktop() {
		setLayout(new BorderLayout());
		Panel buttonPanel = new Panel();
		buttonPanel.add(showButton = new Button(translator.i18n("Show...")));
		buttonPanel.add(addButton = new Button(translator.i18n("Add...")));
		buttonPanel
				.add(changeButton = new Button(translator.i18n("Change...")));
		buttonPanel
				.add(deleteButton = new Button(translator.i18n("Delete...")));
		add(buttonPanel, BorderLayout.NORTH);
		add(new ScrollPane(table = new EntityTable()), BorderLayout.CENTER);

		showButton.connect("start", this, "show");
		addButton.connect("start", this, "add");
		changeButton.connect("start", this, "change");
		deleteButton.connect("start", this, "delete");
	}

	public void setEntities(Class<?> entityType, List<?> entities) {
		this.entities = entities;
		this.entityType = entityType;
		table.setEntities(entities);
	}

	public List<?> getEntities() {
		return entities;
	}

	private Object getSelectedEntity() {
		int index = table.getSelectedRow();
		if (index < 0) {
			JOptionPane.showConfirmDialog(Application.getInstance(),
					translator.i18n("Please select an entity first."),
					"No Selection", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		return entities.get(index);
	}

	@Slot
	public void show() {
		Object entity = getSelectedEntity();
		if (entity != null) {
			show(entity);
		}
	}

	@Slot
	public void add() {
		add(entityType);
	}

	@Slot
	public void change() {
		Object entity = getSelectedEntity();
		if (entity != null) {
			change(entity);
		}
	}

	@Slot
	public void delete() {
		Object entity = getSelectedEntity();
		if (entity != null) {
			delete(entity);
		}
	}

	@Signal
	public void show(Object entity) {
		connectionManager.emitSignal("show", entity);
	}

	@Signal
	public void add(Class<?> type) {
		connectionManager.emitSignal("add", type);
	}

	@Signal
	public void change(Object entity) {
		connectionManager.emitSignal("change", entity);
	}

	@Signal
	public void delete(Object entity) {
		connectionManager.emitSignal("delete", entity);
	}
}
