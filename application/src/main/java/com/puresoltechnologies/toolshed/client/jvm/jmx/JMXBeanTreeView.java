package com.puresoltechnologies.toolshed.client.jvm.jmx;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.management.Descriptor;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class JMXBeanTreeView extends TreeView<JMXBeanNode> {

    private final ObjectProperty<ObjectName> selectedObjectName = new SimpleObjectProperty<>();
    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();
    private final TreeItem<JMXBeanNode> root = new TreeItem<>();

    public JMXBeanTreeView() {
	jmxConnector.addListener((observable, oldValue, newValue) -> refreshTree());
	setRoot(root);
	setShowRoot(false);

	setCellFactory(item -> {
	    return new TreeCell<JMXBeanNode>() {
		@Override
		protected void updateItem(JMXBeanNode item, boolean empty) {
		    super.updateItem(item, empty);
		    if (empty) {
			setText("");
		    } else {
			setText(item.getName());
		    }
		}
	    };
	});
	setOnMouseClicked(mouseEvent -> {
	    TreeItem<JMXBeanNode> item = getSelectionModel().getSelectedItem();
	    if (item != null) {
		JMXBeanNode jmxBean = item.getValue();
		selectedObjectName.set(jmxBean.getObjectName());
	    } else {
		selectedObjectName.set(null);
	    }
	});
    }

    public final ObjectProperty<ObjectName> selectedObjectNameProperty() {
	return selectedObjectName;
    }

    public final void setSelectedObjectName(ObjectName selectedObject) {
	this.selectedObjectName.setValue(selectedObject);
    }

    public final ObjectName getSelectedObjectName() {
	return selectedObjectName.get();
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

    private void refreshTree() {
	root.setExpanded(true);
	root.getChildren().clear();
	try {
	    MBeanServerConnection connection = jmxConnector.get().getMBeanServerConnection();
	    ObservableList<TreeItem<JMXBeanNode>> domainNodes = root.getChildren();
	    for (String domain : connection.getDomains()) {
		TreeItem<JMXBeanNode> domainNode = findChildNode(root, domain);
		if (domainNode == null) {
		    domainNode = new TreeItem<>(new JMXBeanNode(domain, null));
		    domainNodes.add(domainNode);
		}
	    }
	    Collections.sort(domainNodes, (l, r) -> l.getValue().getName().compareTo(r.getValue().getName()));

	    Set<ObjectInstance> mBeans = connection.queryMBeans(null, null);
	    for (ObjectInstance mBean : mBeans) {
		ObjectName objectName = mBean.getObjectName();
		String domain = objectName.getDomain();
		TreeItem<JMXBeanNode> domainNode = findChildNode(root, domain);

		MBeanInfo mBeanInfo = connection.getMBeanInfo(objectName);
		Descriptor descriptor = mBeanInfo.getDescriptor();
		String type = objectName.getKeyProperty("type");
		String name = objectName.getKeyProperty("name");
		if (name == null) {
		    TreeItem<JMXBeanNode> treeItem = new TreeItem<>(new JMXBeanNode(type, objectName));
		    domainNode.getChildren().add(treeItem);
		} else {
		    TreeItem<JMXBeanNode> nameNode = findChildNode(domainNode, type);
		    if (nameNode == null) {
			nameNode = new TreeItem<>(new JMXBeanNode(type, null));
			domainNode.getChildren().add(nameNode);
		    }
		    TreeItem<JMXBeanNode> treeItem = new TreeItem<>(new JMXBeanNode(name, objectName));
		    nameNode.getChildren().add(treeItem);
		}
	    }
	} catch (IOException | InstanceNotFoundException | IntrospectionException | ReflectionException e) {
	    e.printStackTrace();
	}
    }

    private TreeItem<JMXBeanNode> findChildNode(TreeItem<JMXBeanNode> node, String domain) {
	for (TreeItem<JMXBeanNode> child : node.getChildren()) {
	    if (child.getValue().getName().equals(domain)) {
		return child;
	    }
	}
	return null;
    }

}
