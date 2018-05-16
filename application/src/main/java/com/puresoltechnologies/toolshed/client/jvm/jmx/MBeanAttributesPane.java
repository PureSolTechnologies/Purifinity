package com.puresoltechnologies.toolshed.client.jvm.jmx;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MBeanAttributesPane extends TableView<MBeanAttributeInfo> {

    private final ObjectProperty<ObjectName> objectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();

    public MBeanAttributesPane() {
	TableColumn<MBeanAttributeInfo, String> nameColumn = new TableColumn<>("Name");
	nameColumn.setCellValueFactory(value -> new ReadOnlyStringWrapper(value.getValue().getName()));
	getColumns().add(nameColumn);
	TableColumn<MBeanAttributeInfo, String> valueColumn = new TableColumn<>("Value");
	valueColumn.setCellValueFactory(value -> {
	    try {
		JMXConnector connector = jmxConnector.getValue();
		ObjectName object = objectName.getValue();
		if ((connector != null) && (object != null)) {
		    MBeanServerConnection connection = connector.getMBeanServerConnection();
		    Object v = connection.getAttribute(object, value.getValue().getName());
		    return new ReadOnlyStringWrapper(v.toString());
		} else {
		    return new ReadOnlyStringWrapper();
		}
	    } catch (InstanceNotFoundException | ReflectionException | IOException | AttributeNotFoundException
		    | MBeanException e) {
		e.printStackTrace();
		return new ReadOnlyStringWrapper();
	    }
	});
	getColumns().add(valueColumn);
	TableColumn<MBeanAttributeInfo, String> updateIntervalColumn = new TableColumn<>("Update Interval");
	updateIntervalColumn.setCellValueFactory(value -> new ReadOnlyStringWrapper("DEFAULT"));
	getColumns().add(updateIntervalColumn);

	objectName.addListener((objectName, oldValue, newValue) -> {
	    try {
		JMXConnector connector = jmxConnector.getValue();
		ObjectName object = objectName.getValue();
		if ((connector != null) && (object != null)) {
		    MBeanServerConnection connection = connector.getMBeanServerConnection();
		    MBeanAttributeInfo[] attributes = connection.getMBeanInfo(object).getAttributes();
		    setItems(FXCollections.observableArrayList(attributes));
		} else {
		    getItems().clear();
		}
	    } catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException e) {
		e.printStackTrace();
	    }
	});
    }

    public final ObjectProperty<JMXConnector> jmxConnectorProperty() {
	return jmxConnector;
    }

    public final void setJMXConnector(JMXConnector jmxConnector) {
	this.jmxConnector.setValue(jmxConnector);
    }

    public final JMXConnector getJMXConnector() {
	return jmxConnector.get();
    }

    public final ObjectProperty<ObjectName> objectNameProperty() {
	return objectName;
    }

    public final void setObjectName(ObjectName objectName) {
	this.objectName.setValue(objectName);
    }

    public final ObjectName getObjectName() {
	return objectName.get();
    }

}
