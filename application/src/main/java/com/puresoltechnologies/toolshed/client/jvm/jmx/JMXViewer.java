package com.puresoltechnologies.toolshed.client.jvm.jmx;

import javax.management.remote.JMXConnector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.SplitPane;

public class JMXViewer extends SplitPane {

    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();

    private final MBeanTreeView treeView;
    private final MBeanFeaturePane objectView;

    public JMXViewer() {
	super();
	treeView = new MBeanTreeView();
	treeView.jmxConnectorProperty().bind(jmxConnector);
	objectView = new MBeanFeaturePane();
	objectView.jmxConnectorProperty().bind(jmxConnector);
	objectView.objectNameProperty().bind(treeView.selectedObjectNameProperty());
	getItems().addAll(treeView, objectView);
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

}
