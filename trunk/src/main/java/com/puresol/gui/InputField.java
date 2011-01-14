package com.puresol.gui;

import javax.swing.JComboBox;

public class InputField extends JComboBox {

	private static final long serialVersionUID = -4084870157049694903L;

	private Class<?> type = null;

	public InputField() {
		super();
	}

	public InputField(Class<?> type) {
		super();
		setType(type);
	}

	public InputField(Class<?> type, Object value) {
		super();
		setValue(value);
	}

	public void setType(Class<?> type) {
		this.type = type;
		createField();
	}

	public Class<?> getType() {
		return type;
	}

	public void setValue(Object value) {
		if (type == null) {
			setSelectedItem("");
		} else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			setSelectedItem(value);
		} else if (type.equals(String.class)) {
			setSelectedItem(value);
		}
	}

	public void setValue(Class<?> type, String value) {
		setType(type);
		setValue(value);
	}

	public Object getValue() {
		return getSelectedItem();
	}

	private void createField() {
		if (type == null) {
			setSelectedItem("");
			removeAllItems();
			return;
		}
		if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			createBooleanField();
			return;
		}
		if (type.equals(String.class)) {
			createStringField();
			return;
		}
		throw new IllegalArgumentException();
	}

	private void createBooleanField() {
		removeAllItems();
		setEditable(false);
		addItem("true");
		addItem("false");
	}

	private void createStringField() {
		removeAllItems();
		setEditable(true);
	}
}
