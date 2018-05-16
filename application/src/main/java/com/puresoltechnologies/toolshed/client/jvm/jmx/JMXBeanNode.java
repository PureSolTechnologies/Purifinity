package com.puresoltechnologies.toolshed.client.jvm.jmx;

import javax.management.ObjectName;

public class JMXBeanNode {

    private final String name;
    private final ObjectName objectName;

    public JMXBeanNode(String name, ObjectName objectName) {
	this.name = name;
	this.objectName = objectName;
    }

    public String getName() {
	return name;
    }

    public ObjectName getObjectName() {
	return objectName;
    }

}
