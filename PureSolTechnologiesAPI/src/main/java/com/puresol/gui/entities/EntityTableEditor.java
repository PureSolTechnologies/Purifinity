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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.puresol.gui.Application;

public class EntityTableEditor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(EntityTableEditor.class);

	private final JButton showButton = new JButton(translator.i18n("Show..."));
	private final JButton addButton = new JButton(translator.i18n("Add..."));
	private final JButton changeButton = new JButton(
			translator.i18n("Change..."));
	private final JButton deleteButton = new JButton(
			translator.i18n("Delete..."));

	private List<?> entities = null;
	@SuppressWarnings("unused")
	// TODO remove!
	private Class<?> entityType = null;
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
		JPanel buttonPanel = new JPanel();

		showButton.addActionListener(this);
		addButton.addActionListener(this);
		changeButton.addActionListener(this);
		deleteButton.addActionListener(this);

		buttonPanel.add(showButton);
		buttonPanel.add(addButton);
		buttonPanel.add(changeButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel, BorderLayout.NORTH);
		add(new JScrollPane(table = new EntityTable()), BorderLayout.CENTER);
	}

	public void setEntities(Class<?> entityType, List<?> entities) {
		this.entities = entities;
		this.entityType = entityType;
		table.setEntities(entities);
	}

	public List<?> getEntities() {
		return entities;
	}

	public Object getSelectedEntity() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
