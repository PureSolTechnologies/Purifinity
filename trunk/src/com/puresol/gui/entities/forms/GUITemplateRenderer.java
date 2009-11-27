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

import javax.i18n4j.Translator;
import javax.persistence.Entity;
import javax.swing.BoxLayout;
import javax.swingx.CheckBox;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TextField;
import javax.swingx.data.Encrypter;
import javax.swingx.data.Time;
import javax.swingx.validator.IntegerValidator;

import com.puresol.entities.forms.TemplateElement;
import com.puresol.entities.forms.TemplateInformation;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.gui.entities.EntityDialog;

public class GUITemplateRenderer extends Panel {

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
						((CheckBox) component).isSelected());
			} else if (clazz.equals(String.class) && (!element.isPassword())) {
				element.getSetter().invoke(element.getEntity(),
						((TextField) component).getText());
			} else if (clazz.equals(String.class) && (element.isPassword())) {
				element.getSetter().invoke(
						element.getEntity(),
						Encrypter.encryptPassword(((TextField) component)
								.getText()));
			} else if (clazz.equals(Integer.class)) {
				element.getSetter().invoke(element.getEntity(),
						Integer.valueOf(((TextField) component).getText()));
			} else if (clazz.equals(Date.class)) {
				element.getSetter().invoke(element.getEntity(),
						Time.string2Date(((TextField) component).getText()));
			} else if (clazz.getAnnotation(Entity.class) != null) {
				element.getSetter().invoke(element.getEntity(),
						((EntityComboBox) component).getSelectedEntity());
				element.print();
			} else if (Collection.class.isAssignableFrom(element.getType())) {
				element.getSetter()
						.invoke(
								element.getEntity(),
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
		add(new Label(translator.i18n("{0} template", translator.i18n(template
				.getName()))), BorderLayout.NORTH);
		Panel inputPanel = new Panel();
		BoxLayout boxLayout = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
		inputPanel.setLayout(boxLayout);
		add(inputPanel, BorderLayout.CENTER);
		for (int index = 0; index < template.getInputCount(); index++) {
			TemplateElement element = template.get(index);
			Panel panel = renderElement(element);
			if (!element.isHidden()) {
				inputPanel.add(panel);
			}
		}
	}

	private void renderWithLayout() {
		renderStatic();
		Label label = new Label(translator
				.i18n("Layout rendering is not implemented yet!"));
		label.setForeground(new Color(255, 0, 0));
		add(label, BorderLayout.SOUTH);
	}

	private Panel renderElement(TemplateElement element) {
		Component component = createComponent(element);
		elementHash.put(element.getIdString(), component);
		if (element.isAutomatic()) {
			component.setEnabled(false);
		}
		if (element.isHidden()) {
			component.setVisible(false);
			Panel panel = new Panel();
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
		if (element.isOptional()) {
			return Label.addTo(component, translator.i18n(element.getName())
					+ " " + translator.i18n("(opt)"), Label.TOP);
		} else {
			return Label.addTo(component, translator.i18n(element.getName()),
					Label.TOP);
		}
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
		CheckBox checkBox = new CheckBox();
		Boolean set = (Boolean) element.getGetter().invoke(element.getEntity());
		if (set != null) {
			checkBox.setSelected(set);
		}
		return checkBox;
	}

	private Component create4String(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		TextField textField = new TextField();
		textField.setText((String) element.getGetter().invoke(
				element.getEntity()));
		return textField;
	}

	private Component create4Integer(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		TextField textField = new TextField();
		Integer integer = (Integer) element.getGetter().invoke(
				element.getEntity());
		if (integer != null) {
			textField.setText(String.valueOf(integer));
		} else {
			textField.setText("0");
		}
		textField.setValidator(new IntegerValidator());
		return textField;
	}

	private Component create4Date(TemplateElement element)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		TextField textField = new TextField();
		textField.setText(Time.date2String((Date) element.getGetter().invoke(
				element.getEntity())));
		return textField;
	}
}
