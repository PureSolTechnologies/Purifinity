/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.i18n4java.Translator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AssignmentBox extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(AssignmentBox.class);

	private Vector<String> selection = null;
	private Vector<String> available = null;
	private Vector<String> reservoir = null;
	private JList selectionList = null;
	private JList availableList = null;
	private JButton removeButton = null;
	private JButton addButton = null;

	public AssignmentBox() {
		super();
		initializeUI();
		selection = new Vector<String>();
		reservoir = new Vector<String>();
	}

	public AssignmentBox(Vector<String> selection, Vector<String> reservoir) {
		this();
		setSelection(selection);
		setReservoir(reservoir);
	}

	private void initializeUI() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 2;
		c.ipady = 2;
		c.insets = new Insets(2, 2, 2, 2);
		JScrollPane scrollPane = new JScrollPane(selectionList = new JList());
		scrollPane.setBorder(BorderFactory.createTitledBorder(translator
				.i18n("Assigned")));
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(scrollPane, c);
		add(scrollPane);

		JPanel buttonPanel = new JPanel();
		GridLayout buttonLayout = new GridLayout(2, 1);
		buttonLayout.setVgap(2);
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.add(addButton = new JButton(translator.i18n("<<< add")));
		buttonPanel.add(removeButton = new JButton(translator
				.i18n("remove >>>")));
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(buttonPanel, c);
		add(buttonPanel);

		scrollPane = new JScrollPane(availableList = new JList());
		scrollPane.setBorder(BorderFactory.createTitledBorder(translator
				.i18n("Available")));
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(scrollPane, c);
		add(scrollPane);

		addButton.addActionListener(this);
		removeButton.addActionListener(this);
	}

	public void setSelection(Vector<String> selection) {
		if (selection == null) {
			this.selection = new Vector<String>();
		} else {
			this.selection = selection;
		}
		Collections.sort(this.selection);
		selectionList.setListData(this.selection);
		updateAvailability();
	}

	public Vector<String> getSelection() {
		return selection;
	}

	public void setReservoir(Vector<String> reservoir) {
		if (reservoir == null) {
			this.reservoir = new Vector<String>();
		} else {
			this.reservoir = reservoir;
		}
		updateAvailability();
	}

	private void updateAvailability() {
		available = new Vector<String>();
		for (String string : reservoir) {
			if (!selection.contains(string)) {
				available.add(string);
			}
		}
		Collections.sort(available);
		availableList.setListData(available);
	}

	private void add() {
		int[] indices = availableList.getSelectedIndices();
		for (int index : indices) {
			selection.add(available.get(index));
		}
		setSelection(selection);
	}

	private void remove() {
		int[] indices = selectionList.getSelectedIndices();
		Vector<String> save = new Vector<String>();
		for (int index : indices) {
			save.add(selection.get(index));
		}
		for (String remove : save) {
			selection.remove(remove);
		}
		setSelection(selection);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == addButton) {
			add();
		} else if (arg0.getSource() == removeButton) {
			remove();
		}

	}
}
