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
import javax.management.RuntimeMBeanException;
import javax.management.remote.JMXConnector;

import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.parts.RunningJVMs;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MBeanAttributesPane extends BorderPane {

    private final ObjectProperty<ObjectName> objectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();
    private final TableView<MBeanAttributeInfo> tableView = new TableView<>();

    public MBeanAttributesPane() {
	try {
	    TableColumn<MBeanAttributeInfo, String> nameColumn = new TableColumn<>("Name");
	    nameColumn.setCellValueFactory(value -> new ReadOnlyStringWrapper(value.getValue().getName()));
	    tableView.getColumns().add(nameColumn);
	    TableColumn<MBeanAttributeInfo, String> valueColumn = new TableColumn<>("Value");
	    valueColumn.setCellValueFactory(value -> {
		try {
		    ObjectName object = objectName.getValue();
		    JMXConnector connector = jmxConnector.getValue();
		    if ((connector != null) && (object != null)) {
			MBeanServerConnection connection = connector.getMBeanServerConnection();
			MBeanAttributeInfo attributeInfo = value.getValue();
			if (!attributeInfo.isReadable()) {
			    return new ReadOnlyStringWrapper();
			}
			Object v = connection.getAttribute(object, attributeInfo.getName());
			if (v != null) {
			    return new ReadOnlyStringWrapper(v.toString());
			} else {
			    return new ReadOnlyStringWrapper("<null>");
			}
		    } else {
			return new ReadOnlyStringWrapper();
		    }
		} catch (InstanceNotFoundException | ReflectionException | IOException | AttributeNotFoundException
			| MBeanException e) {
		    e.printStackTrace();
		    return new ReadOnlyStringWrapper();
		} catch (RuntimeMBeanException e) {
		    return new ReadOnlyStringWrapper();
		}
	    });
	    valueColumn.setCellFactory(column -> {
		TableCell<MBeanAttributeInfo, String> cell = new TableCell<MBeanAttributeInfo, String>() {
		    @Override
		    protected void updateItem(String value, boolean empty) {
			super.updateItem(value, empty);
			if (!empty) {
			    setText(value);
			    int index = getIndex();
			    try {
				ObservableList<MBeanAttributeInfo> items = getTableView().getItems();
				MBeanAttributeInfo mBeanAttributeInfo = items.get(index);
				if (mBeanAttributeInfo.isWritable()) {
				    setGraphic(new Button(value));
				    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				}
			    } catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			    }
			}
		    }
		};
		return cell;
	    });
	    tableView.getColumns().add(valueColumn);

	    jmxConnector.addListener((jmxConnector, oldValue, newValue) -> {
		refresh();
	    });
	    objectName.addListener((objectName, oldValue, newValue) -> {
		refresh();
	    });
	    ImageView refreshIcon = ResourceUtils.getImageView(RunningJVMs.class,
		    "/icons/FatCow_Icons16x16/arrow_refresh.png");
	    Button refreshButton = new Button("Refresh...", refreshIcon);
	    refreshButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	    refreshButton.setOnAction(event -> {
		refresh();
		event.consume();
	    });
	    ToolBar toolBar = new ToolBar();
	    toolBar.getItems().add(refreshButton);
	    setTop(toolBar);
	    setCenter(tableView);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    private void refresh() {
	try {
	    JMXConnector connector = jmxConnector.getValue();
	    ObjectName object = objectName.getValue();
	    if ((connector != null) && (object != null)) {
		MBeanServerConnection connection = connector.getMBeanServerConnection();
		MBeanAttributeInfo[] attributes = connection.getMBeanInfo(object).getAttributes();
		tableView.setItems(FXCollections.observableArrayList(attributes));
	    } else {
		tableView.getItems().clear();
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
