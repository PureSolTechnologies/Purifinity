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

import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.parts.RunningJVMs;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MBeanOperationsPane extends BorderPane {

    private final ObjectProperty<ObjectName> objectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();

    private final TabPane resultTabs = new TabPane();
    private final Label operationNameLabel = new Label("Operation Name");

    private final TableView<MBeanOperationInfo> operationsTableView = new TableView<>();
    private final TableView<MBeanParameterInfo> parametersTableView = new TableView<>();

    public MBeanOperationsPane() {
	TableColumn<MBeanOperationInfo, String> operationColumn = new TableColumn<MBeanOperationInfo, String>(
		"Operation");
	operationColumn.setCellValueFactory(
		info -> new ReadOnlyStringWrapper(info.getValue().getName() + ": " + info.getValue().getReturnType()));
	operationsTableView.getColumns().add(operationColumn);
	operationColumn.prefWidthProperty().bind(operationsTableView.widthProperty());
	operationsTableView.getSelectionModel().selectedIndexProperty()
		.addListener((observable, oldValue, newValue) -> {
		    MBeanOperationInfo item = operationsTableView.getSelectionModel().getSelectedItem();
		    if (item != null) {
			parametersTableView.setItems(FXCollections.observableArrayList(item.getSignature()));
			operationNameLabel.setText(item.getName());
		    } else {
			clear();
		    }
		});

	TableColumn<MBeanParameterInfo, String> nameColumn = new TableColumn<MBeanParameterInfo, String>("Name");
	nameColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));
	parametersTableView.getColumns().add(nameColumn);
	TableColumn<MBeanParameterInfo, String> valueColumn = new TableColumn<MBeanParameterInfo, String>("Value");
	valueColumn.setCellFactory(info -> {
	    TableCell<MBeanParameterInfo, String> cell = new TableCell<MBeanParameterInfo, String>() {
		@Override
		protected void updateItem(String item, boolean empty) {
		    super.updateItem(item, empty);
		    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    if (empty) {
			setText(null);
			setGraphic(null);
		    } else {
			TextField value = new TextField();
			setGraphic(value);
		    }
		}
	    };
	    return cell;
	});
	parametersTableView.getColumns().add(valueColumn);
	TableColumn<MBeanParameterInfo, String> descriptionColumn = new TableColumn<MBeanParameterInfo, String>(
		"Description");
	descriptionColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getDescription()));
	parametersTableView.getColumns().add(descriptionColumn);

	nameColumn.prefWidthProperty().bind(parametersTableView.widthProperty().divide(3.0));
	valueColumn.prefWidthProperty().bind(parametersTableView.widthProperty().divide(3.0));
	descriptionColumn.prefWidthProperty().bind(parametersTableView.widthProperty().divide(3.0));

	BorderPane operationsPane = new BorderPane();
	SplitPane horizontalSplitPane = new SplitPane();
	horizontalSplitPane.setOrientation(Orientation.HORIZONTAL);
	horizontalSplitPane.getItems().addAll(operationsTableView, parametersTableView);
	operationsPane.setCenter(horizontalSplitPane);

	try {
	    ImageView lightningImage = ResourceUtils.getImageView(RunningJVMs.class,
		    "/icons/FatCow_Icons16x16/lightning.png");

	    Button executeButton = new Button("Execute...", lightningImage);
	    executeButton.setContentDisplay(ContentDisplay.LEFT);
	    operationNameLabel.setAlignment(Pos.CENTER_LEFT);
	    operationsPane.setBottom(new HBox(executeButton, operationNameLabel));
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
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
	    clear();
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

    private void clear() {
	parametersTableView.setItems(FXCollections.observableArrayList());
	operationNameLabel.setText("");
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
