/***************************************************************************
 *
 *   GUITemplateRenderer.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.entities.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.i18n4java.Translator;
import javax.persistence.Entity;
import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.puresol.entities.forms.TemplateElement;
import com.puresol.entities.forms.TemplateInformation;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.gui.data.Encrypter;
import com.puresol.gui.data.Time;
import com.puresol.gui.entities.EntityDialog;

public class GUITemplateRenderer extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(GUITemplateRenderer.class);

	private String layoutResource = "";
	private TemplateInformation template = null;
	private Hashtable<String, Component> elementHash = null;
	private int type;

	static public GUITemplateRenderer render(Object entity, int type) {
		return new GUITemplateRenderer(entity, type);
	}

	static public GUITemplateRenderer render(Object entity,
			String layoutResource, int type) {
		return new GUITemplateRenderer(entity, type, layoutResource);
	}

	private GUITemplateRenderer(Object entity, int type) {
		elementHash = new Hashtable<String, Component>();
		setEntity(entity);
		this.type = type;
		render();
	}

	private GUITemplateRenderer(Object entity, int type, String layoutResource) {
		elementHash = new Hashtable<String, Component>();
		setEntity(entity);
		this.type = type;
		setLayoutResource(layoutResource);
		render();
	}

	private void setEntity(Object entity) {
		template = TemplateInformation.from(entity);
	}

	public Object getOldEntity() {
		return template.getEntity();
	}

	public Object getUpdatedEntity() {
		Object o = template.getEntity();
		Enumeration<String> keys = elementHash.keys();
		while (keys.hasMoreElements()) {
			updateEntityForKey(keys.nextElement());
		}
		return o;
	}

	private void updateEntityForKey(String key) {
		try {
			Component component = elementHash.get(key);
			TemplateElement element = template.get(key);
			Class<?> clazz = element.getType();
			if (clazz.equals(Boolean.class)) {
				element.getSetter().invoke(element.getEntity(),
						((JCheckBox) component).isSelected());
			} else if (clazz.equals(String.class) && (!element.isPassword())) {
				element.getSetter().invoke(element.getEntity(),
						((JTextField) component).getText());
			} else if (clazz.equals(String.class) && (element.isPassword())) {
				element.getSetter().invoke(
						element.getEntity(),
						Encrypter.encryptPassword(((JTextField) component)
								.getText()));
			} else if (clazz.equals(Integer.class)) {
				element.getSetter().invoke(element.getEntity(),
						Integer.valueOf(((JTextField) component).getText()));
			} else if (clazz.equals(Date.class)) {
				element.getSetter().invoke(element.getEntity(),
						Time.string2Date(((JTextField) component).getText()));
			} else if (clazz.getAnnotation(Entity.class) != null) {
				element.getSetter().invoke(element.getEntity(),
						((EntityComboBox) component).getSelectedEntity());
				element.print();
			} else if (Collection.class.isAssignableFrom(element.getType())) {
				element.getSetter()
						.invoke(element.getEntity(),
								((EntityAssignmentBox) component)
										.getSelectedEntities());
			} else {
				throw new StrangeSituationException("Element type "
						+ clazz.getName() + " is not supported!");
			}
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		}
	}

	private void setLayoutResource(String layoutResource) {
		this.layoutResource = layoutResource;
	}

	private String getLayoutResource() {
		return layoutResource;
	}

	private void render() {
		InputStream input = null;
		if (!getLayoutResource().isEmpty()) {
			input = getClass().getClassLoader().getResourceAsStream(
					getLayoutResource());
		}
		if (input == null) {
			renderStatic();
		} else {
			renderWithLayout();
		}
	}

	private void renderStatic() {
		setLayout(new BorderLayout());
		add(new JLabel(translator.i18n("{0} template",
				translator.i18n(template.getName()))), BorderLayout.NORTH);
		JPanel inputPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
		inputPanel.setLayout(boxLayout);
		add(inputPanel, BorderLayout.CENTER);
		for (int index = 0; index < template.getInputCount(); index++) {
			TemplateElement element = template.get(index);
			JPanel panel = renderElement(element);
			if (!element.isHidden()) {
				inputPanel.add(panel);
			}
		}
	}

	private void renderWithLayout() {
		renderStatic();
		JLabel label = new JLabel(
				translator.i18n("Layout rendering is not implemented yet!"));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.SOUTH);
	}

	private JPanel renderElement(TemplateElement element) {
		Component component = createComponent(element);
		elementHash.put(element.getIdString(), component);
		if (element.isAutomatic()) {
			component.setEnabled(false);
		}
		if (element.isHidden()) {
			component.setVisible(false);
			JPanel panel = new JPanel();
			panel.add(component);
			return panel;
		}
		if (type == EntityDialog.CHANGE) {
			if (element.isID()) {
				component.setEnabled(false);
			}
		} else if (type == EntityDialog.SHOW) {
			component.setEnabled(false);
		}
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if (element.isOptional()) {
			panel.add(new JLabel(translator.i18n(element.getName()) + " "
					+ translator.i18n("(opt)")));
		} else {
			panel.add(new JLabel(translator.i18n(element.getName())));
		}
		panel.add(component);
		return panel;
	}

	private Component createComponent(TemplateElement element) {
		try {
			Class<?> clazz = element.getType();
			if (clazz.equals(Boolean.class)) {
				return create4Boolean(element);
			} else if (clazz.equals(String.class)) {
				return create4String(element);
			} else if (clazz.equals(Integer.class)) {
				return create4Integer(element);
			} else if (clazz.equals(Date.class)) {
				return create4Date(element);
			} else if (Collection.class.isAssignableFrom(clazz)) {
				return EntityAssignmentBox.from(element.getGenerics(),
						(Collection<?>) element.getValue());
			} else if (clazz.getAnnotation(Entity.class) != null) {
				if (element.getValue() != null) {
					return EntityComboBox.from(element.getValue());
				} else {
					return EntityComboBox.from(clazz);
				}
			} else {
				throw new StrangeSituationException("Element type "
						+ clazz.getName() + " is not supported!");
			}
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		} catch (InvocationTargetException e) {
			throw new StrangeSituationException(e);
		}
	}

	private Component create4Boolean(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		JCheckBox checkBox = new JCheckBox();
		Boolean set = (Boolean) element.getGetter().invoke(element.getEntity());
		if (set != null) {
			checkBox.setSelected(set);
		}
		return checkBox;
	}

	private Component create4String(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		JTextField textField = new JTextField();
		textField.setText((String) element.getGetter().invoke(
				element.getEntity()));
		return textField;
	}

	private Component create4Integer(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		JTextField textField = new JTextField();
		Integer integer = (Integer) element.getGetter().invoke(
				element.getEntity());
		if (integer != null) {
			textField.setText(String.valueOf(integer));
		} else {
			textField.setText("0");
		}
		textField.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent c) {
				try {
					Integer.valueOf(((JTextField) c).getText());
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		});
		return textField;
	}

	private Component create4Date(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		JTextField textField = new JTextField();
		textField.setText(Time.date2String((Date) element.getGetter().invoke(
				element.getEntity())));
		return textField;
	}
}
