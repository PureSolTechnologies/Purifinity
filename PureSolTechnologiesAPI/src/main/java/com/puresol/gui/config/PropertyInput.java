package com.puresol.gui.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.puresol.config.PropertyDescription;
import com.puresol.data.Identifiable;

public class PropertyInput extends JPanel {

	private static final long serialVersionUID = 7574440491895744152L;

	private final PropertyDescription<?> description;
	private JComponent component;

	public PropertyInput(PropertyDescription<?> description) {
		super();
		this.description = description;
		initUI();
	}

	private void initUI() {
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		setLayout(layout);
		TitledBorder border = BorderFactory.createTitledBorder(description
				.getDisplayName());
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getFontName(), font.getStyle(),
				(int) (font.getSize() * 1.2)));
		setBorder(border);
		add(new JLabel(description.getPropertyName()), BorderLayout.WEST);
		add(new JLabel(description.getDescription()), BorderLayout.SOUTH);
		initComponent();
	}

	private void initComponent() {
		if (description.getType().equals(String.class)) {
			initTextField();
		} else if (description.getType().equals(Integer.class)) {
			initTextField();
			final JTextField textField = (JTextField) component;
			textField.setInputVerifier(new InputVerifier() {
				@Override
				public boolean verify(JComponent e) {
					try {
						Integer.valueOf(textField.getText());
						textField.setForeground(Color.BLACK);
						return true;
					} catch (Throwable t) {
						textField.setForeground(Color.RED);
						return false;
					}
				}
			});
		} else if (description.getType().isEnum()
				&& Identifiable.class.isAssignableFrom(description.getType())) {
			initComboBox();
		}
	}

	private void initTextField() {
		final JTextField textField = new JTextField();
		textField.setToolTipText(description.getDescription());
		textField.setText(String.valueOf(description.getDefaultValue()));
		component = textField;
		add(textField);
	}

	private void initComboBox() {
		final JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText(description.getDescription());
		for (int id = 0; id < description.getType().getEnumConstants().length; id++) {
			comboBox.addItem(description.getType().getEnumConstants()[id]);
		}
		component = comboBox;
		add(comboBox);
	}

	public Object getValue() {
		if (JComboBox.class.isAssignableFrom(component.getClass())) {
			return ((JComboBox) component).getSelectedItem();
		} else if (JTextField.class.isAssignableFrom(component.getClass())) {
			return ((JTextField) component).getText();
		}
		return null;
	}

	public void setValue(Object value) {
		if (JComboBox.class.equals(component.getClass())) {
			if (String.class.equals(value.getClass())) {
				for (Object o : description.getType().getEnumConstants()) {
					if (o.toString().equals(value)) {
						((JComboBox) component).setSelectedItem(o);
					}
				}
			} else {
				((JComboBox) component).setSelectedItem(value);
			}
		} else if (JTextField.class.isAssignableFrom(component.getClass())) {
			((JTextField) component).setText(String.valueOf(value));
		}
	}
}
