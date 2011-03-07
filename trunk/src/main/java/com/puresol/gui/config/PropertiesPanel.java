package com.puresol.gui.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.config.ConfigurationSource;
import com.puresol.config.PropertyDescription;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -5497942963691954365L;

	private ConfigurationSource configurationSource = null;
	private final List<PropertyDescription<?>> propertyDescriptions = new Vector<PropertyDescription<?>>();
	private final Map<PropertyDescription<?>, PropertyInput> propertyInputs = new HashMap<PropertyDescription<?>, PropertyInput>();

	public PropertiesPanel() {
		super();
		initUI();
	}

	public PropertiesPanel(ConfigurationSource configurationSource,
			List<PropertyDescription<?>> propertyDescriptions) {
		super();
		this.configurationSource = configurationSource;
		this.propertyDescriptions.addAll(propertyDescriptions);
		initUI();
	}

	private void initUI() {
		removeAll();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		propertyInputs.clear();
		for (PropertyDescription<?> description : propertyDescriptions) {
			PropertyInput input = new PropertyInput(description);
			propertyInputs.put(description, input);
			add(input);
			if (configurationSource != null) {
				Object value = configurationSource.getProperty(description
						.getPropertyName());
				if (value != null) {
					input.setValue(value);
				}
			}
		}
		updateUI();
	}

	public void setPropertyDescriptions(
			ConfigurationSource configurationSource,
			List<PropertyDescription<?>> propertyDescriptions) {
		this.configurationSource = configurationSource;
		this.propertyDescriptions.clear();
		this.propertyDescriptions.addAll(propertyDescriptions);
		initUI();
	}

	public void clearPropertyDescriptions() {
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
		for (PropertyDescription<?> description : propertyInputs.keySet()) {
			PropertyInput input = propertyInputs.get(description);
			configurationSource.setProperty(description.getPropertyName(),
					input.getValue().toString());
		}
	}
}
