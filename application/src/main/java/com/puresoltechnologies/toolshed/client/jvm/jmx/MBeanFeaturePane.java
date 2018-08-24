package com.puresoltechnologies.toolshed.client.jvm.jmx;

import javax.management.ObjectName;
import javax.management.remote.JMXConnector;

import com.puresoltechnologies.toolshed.client.utils.Fonts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MBeanFeaturePane extends BorderPane {

    private final ObjectProperty<ObjectName> objectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();
    private final MBeanAttributesPane attributes = new MBeanAttributesPane();
    private final MBeanOperationsPane operations = new MBeanOperationsPane();
    private final MBeanNotificationsPane notifications = new MBeanNotificationsPane();
    private final MBeanMetadataPane metadata = new MBeanMetadataPane();
    private final TabPane tabPane = new TabPane();
    private final Label nameLabel = new Label();

    public MBeanFeaturePane() {
	super();
	Tab attributesTab = new Tab("Attributes");
	attributesTab.setContent(attributes);
	Tab operationsTab = new Tab("Operations");
	operationsTab.setContent(operations);
	Tab notificationTab = new Tab("Notifications");
	notificationTab.setContent(notifications);
	Tab metadataTab = new Tab("Metadata");
	metadataTab.setContent(metadata);
	tabPane.getTabs().addAll(attributesTab, operationsTab, notificationTab, metadataTab);

	attributes.jmxConnectorProperty().bind(jmxConnector);
	attributes.objectNameProperty().bind(objectName);
	operations.jmxConnectorProperty().bind(jmxConnector);
	operations.objectNameProperty().bind(objectName);
	notifications.jmxConnectorProperty().bind(jmxConnector);
	notifications.objectNameProperty().bind(objectName);
	metadata.jmxConnectorProperty().bind(jmxConnector);
	metadata.objectNameProperty().bind(objectName);

	nameLabel.setFont(Fonts.titleFont);
	setTop(nameLabel);
	setCenter(tabPane);

	objectName.addListener((objectName, oldValue, newValue) -> {
	    if (newValue != null) {
		nameLabel.setText(newValue.getCanonicalName());
	    } else {
		nameLabel.setText(null);
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
