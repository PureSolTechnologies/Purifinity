package com.puresoltechnologies.purifinity.server.ui.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean
public class SystemInformationMBean {

	public List<SystemProperty> getSystemProperties() {
		Properties systemProperties = System.getProperties();
		List<SystemProperty> properties = new ArrayList<>();
		for (Entry<Object, Object> entry : systemProperties.entrySet()) {
			properties.add(new SystemProperty(entry.getKey().toString(), entry
					.getValue().toString()));
		}
		Collections.sort(properties, new Comparator<SystemProperty>() {

			@Override
			public int compare(SystemProperty o1, SystemProperty o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return properties;
	}

	public List<EnvironmentVariable> getEnvironmentVariables() {
		List<EnvironmentVariable> variables = new ArrayList<>();
		for (Entry<String, String> entry : System.getenv().entrySet()) {
			variables.add(new EnvironmentVariable(entry.getKey(), entry
					.getValue()));
		}
		Collections.sort(variables, new Comparator<EnvironmentVariable>() {
			@Override
			public int compare(EnvironmentVariable o1, EnvironmentVariable o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return variables;
	}

}
