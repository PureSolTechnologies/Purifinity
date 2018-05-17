package com.puresoltechnologies.toolshed.client.jvm.jmx;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MBeanOperationsPane extends BorderPane {

    private final ObjectProperty<ObjectName> objectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();

    private final TabPane resultTabs = new TabPane();

    private final TableView<MBeanOperationInfo> operationsTableView = new TableView<>();
    private final TableView<MBeanParameterInfo> parametersTableView = new TableView<>();

    public MBeanOperationsPane() {
	TableColumn<MBeanOperationInfo, String> operationColumn = new TableColumn<MBeanOperationInfo, String>(
		"Operation");
	operationColumn.setCellValueFactory(
		info -> new ReadOnlyStringWrapper(info.getValue().getName() + ": " + info.getValue().getReturnType()));
	operationsTableView.getColumns().add(operationColumn);

	parametersTableView.getColumns().add(new TableColumn<MBeanParameterInfo, String>("Name"));
	parametersTableView.getColumns().add(new TableColumn<MBeanParameterInfo, String>("Value"));
	parametersTableView.getColumns().add(new TableColumn<MBeanParameterInfo, String>("Description"));

	BorderPane operationsPane = new BorderPane();
	SplitPane horizontalSplitPane = new SplitPane();
	horizontalSplitPane.setOrientation(Orientation.HORIZONTAL);
	horizontalSplitPane.getItems().addAll(operationsTableView, parametersTableView);
	operationsPane.setCenter(horizontalSplitPane);
	operationsPane.setBottom(new HBox(new Button("Execute..."), new Label("Operation Name")));

	SplitPane verticalSplitPane = new SplitPane();
	verticalSplitPane.setOrientation(Orientation.VERTICAL);
	verticalSplitPane.getItems().addAll(operationsPane, resultTabs);
	setCenter(verticalSplitPane);

	jmxConnector.addListener((jmxConnector, oldValue, newValue) -> {
	    refresh();
	});
	objectName.addListener((objectName, oldValue, newValue) -> {
	    refresh();
	});
    }

    private void refresh() {
	try {
	    JMXConnector connector = jmxConnector.getValue();
	    ObjectName object = objectName.getValue();
	    if ((connector != null) && (object != null)) {
		MBeanServerConnection connection = connector.getMBeanServerConnection();
		MBeanOperationInfo[] operations = connection.getMBeanInfo(object).getOperations();
		operationsTableView.setItems(FXCollections.observableArrayList(operations));
	    } else {
		operationsTableView.getItems().clear();
	    }
	} catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException e) {
	    e.printStackTrace();
	}
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
