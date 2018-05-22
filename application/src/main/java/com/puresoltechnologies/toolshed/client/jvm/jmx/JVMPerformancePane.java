package com.puresoltechnologies.toolshed.client.jvm.jmx;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;

import com.puresoltechnologies.javafx.charts.meter.MeterView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class JVMPerformancePane extends GridPane {

    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();
    private final MeterView<Double> cpuMeter = new MeterView<>();
    private final MeterView<Long> memoryMeter = new MeterView<>();

    public JVMPerformancePane() {
	cpuMeter.setTitle("JVM CPU Usage");
	cpuMeter.setUnit("%");
	cpuMeter.setStart(0.0);
	cpuMeter.setEnd(100.0);
	cpuMeter.setValue(75.0);
	memoryMeter.setTitle("Used Java Heap Memory");
	memoryMeter.setUnit("MB");
	memoryMeter.setStart(0l);
	memoryMeter.setEnd(1024l * 1024l);
	memoryMeter.setValue(0l);
	setConstraints(cpuMeter, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
	setConstraints(memoryMeter, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);

	getChildren().addAll(cpuMeter, memoryMeter);
    }

    public ObjectProperty<JMXConnector> jmxConnector() {
	return jmxConnector;
    }

    public JMXConnector getJMXConnector() {
	return jmxConnector.get();
    }

    public void setJMXConnector(JMXConnector jmxConnector) {
	this.jmxConnector.set(jmxConnector);
    }

    public void update() {
	try {
	    MBeanServerConnection connection = jmxConnector.get().getMBeanServerConnection();
	    double cpuLoad = (Double) connection.getAttribute(new ObjectName("java.lang:type=OperatingSystem"),
		    "SystemCpuLoad");
	    cpuMeter.setValue(cpuLoad * 100.0);
	    CompositeDataSupport memoryUsage = (CompositeDataSupport) connection
		    .getAttribute(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
	    memoryMeter.setValue((long) memoryUsage.get("used"));
	    memoryMeter.setEnd((long) memoryUsage.get("max"));
	} catch (IOException | AttributeNotFoundException | InstanceNotFoundException | MalformedObjectNameException
		| MBeanException | ReflectionException e) {
	    e.printStackTrace();
	}

    }

}
