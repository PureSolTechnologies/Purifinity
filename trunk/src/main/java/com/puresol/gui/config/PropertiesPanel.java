package com.puresol.gui.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.config.properties.ConfigurationManager;
import com.puresol.config.properties.ConfigurationLayer;
import com.puresol.config.properties.PropertyDescription;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -5497942963691954365L;

	private ConfigurationLayer configurationType = ConfigurationLayer.SYSTEM;
	private String context = "";
	private final List<PropertyDescription<?>> propertyDescriptions = new Vector<PropertyDescription<?>>();
	private final Map<PropertyDescription<?>, PropertyInput> propertyInputs = new HashMap<PropertyDescription<?>, PropertyInput>();

	public PropertiesPanel() {
		super();
		initUI();
	}

	public PropertiesPanel(ConfigurationLayer configurationType, String context,
			List<PropertyDescription<?>> propertyDescriptions) {
		super();
		this.configurationType = configurationType;
		this.context = context;
		this.propertyDescriptions.addAll(propertyDescriptions);
		initUI();
	}

	private void initUI() {
		removeAll();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		propertyInputs.clear();
		ConfigurationManager manager = ConfigurationManager
				.getInstance(configurationType);
		Properties properties = manager.getContextProperties(context);
		if (properties == null) {
			properties = new Properties();
		}
		for (PropertyDescription<?> description : propertyDescriptions) {
			PropertyInput input = new PropertyInput(description);
			propertyInputs.put(description, input);
			add(input);
			Object value = properties
					.getProperty(description.getPropertyName());
			if (value != null) {
				input.setValue(value);
			}
		}
		updateUI();
	}

	public void setPropertyDescriptions(ConfigurationLayer configurationType,
			String context, List<PropertyDescription<?>> propertyDescriptions) {
		this.configurationType = configurationType;
		this.context = context;
		this.propertyDescriptions.clear();
		this.propertyDescriptions.addAll(propertyDescriptions);
		initUI();
	}

	public void clearPropertyDescriptions() {
		context = "";
		propertyDescriptions.clear();
		initUI();
	}

	public void reset() {
		for (PropertyDescription<?> description : propertyDescriptions) {
			PropertyInput input = propertyInputs.get(description);
			input.setValue(description.getDefaultValue());
		}
	}

	public void apply() {
		ConfigurationManager manager = ConfigurationManager
				.getInstance(configurationType);
		Properties properties = manager.getContextProperties(context);
		if (properties == null) {
			properties = new Properties();
			manager.addProperties(context, properties);
		}
		for (PropertyDescription<?> description : propertyInputs.keySet()) {
			PropertyInput input = propertyInputs.get(description);
			properties.setProperty(description.getPropertyName(), input
					.getValue().toString());
		}
	}
}
