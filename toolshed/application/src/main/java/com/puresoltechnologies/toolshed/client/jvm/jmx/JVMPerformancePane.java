package com.puresoltechnologies.toolshed.client.jvm.jmx;

import java.io.IOException;
import java.time.Instant;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;

import com.puresoltechnologies.javafx.charts.ChartView;
import com.puresoltechnologies.javafx.charts.axes.AxisType;
import com.puresoltechnologies.javafx.charts.axes.NumberAxis;
import com.puresoltechnologies.javafx.charts.axes.TimeSeriesAxis;
import com.puresoltechnologies.javafx.charts.meter.MeterView;
import com.puresoltechnologies.javafx.charts.plots.timeseries.TimeSeriesPlot;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class JVMPerformancePane extends GridPane {

    private static class CPULoadMeasurement {
	private final Instant time;
	private final double load;

	public CPULoadMeasurement(Instant time, double load) {
	    super();
	    this.time = time;
	    this.load = load;
	}

	public Instant getTime() {
	    return time;
	}

	public double getLoad() {
	    return load;
	}

    }

    private static class MemoryUsageMeasurement {
	private final Instant time;
	private final long usedMemory;
	private final long maxMemory;

	public MemoryUsageMeasurement(Instant time, long usedMemory, long maxMemory) {
	    super();
	    this.time = time;
	    this.usedMemory = usedMemory;
	    this.maxMemory = maxMemory;
	}

	public Instant getTime() {
	    return time;
	}

	public long getUsedMemory() {
	    return usedMemory;
	}

	public long getMaxMemory() {
	    return maxMemory;
	}

    }

    private final ObjectProperty<JMXConnector> jmxConnector = new SimpleObjectProperty<>();
    private final MeterView<Double> cpuMeter = new MeterView<>();
    private final MeterView<Long> memoryMeter = new MeterView<>();
    private final ChartView cpuChartView = new ChartView();
    private final ChartView memoryChartView = new ChartView();

    private final TimeSeriesPlot<Double, CPULoadMeasurement> cpuLoadPlot;
    private final TimeSeriesPlot<Long, MemoryUsageMeasurement> usedMemoryPlot;
    private final TimeSeriesPlot<Long, MemoryUsageMeasurement> maxMemoryPlot;
    private final ObservableList<CPULoadMeasurement> cpuLoad = FXCollections.observableArrayList();
    private final ObservableList<MemoryUsageMeasurement> memoryUsage = FXCollections.observableArrayList();

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
	setConstraints(cpuChartView, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
	setConstraints(memoryChartView, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);

	TimeSeriesAxis cpuXAxis = new TimeSeriesAxis("Time", AxisType.X);
	NumberAxis<Double> cpuYAxis = new NumberAxis<>("CPU", "%", AxisType.Y, Double.class);
	cpuLoadPlot = new TimeSeriesPlot<Double, CPULoadMeasurement>("JVM CPU Usage", //
		cpuXAxis, cpuYAxis, //
		cpuLoad, //
		date -> date.getTime(), date -> date.getLoad());
	cpuChartView.addPlot(cpuLoadPlot);
	TimeSeriesAxis memoryXAxis = new TimeSeriesAxis("Time", AxisType.X);
	NumberAxis<Long> memoryYAxis = new NumberAxis<>("Memory", "MB", AxisType.Y, Long.class);
	usedMemoryPlot = new TimeSeriesPlot<Long, MemoryUsageMeasurement>("Used Java Heap Memory", //
		memoryXAxis, memoryYAxis, //
		memoryUsage, //
		date -> date.getTime(), date -> date.getUsedMemory());
	maxMemoryPlot = new TimeSeriesPlot<Long, MemoryUsageMeasurement>("Used Java Heap Memory", //
		memoryXAxis, memoryYAxis, //
		memoryUsage, //
		date -> date.getTime(), date -> date.getMaxMemory());
	memoryChartView.addPlot(usedMemoryPlot);
	memoryChartView.addPlot(maxMemoryPlot);

	getChildren().addAll(cpuMeter, memoryMeter, cpuChartView, memoryChartView);
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
	    Instant now = Instant.now();
	    double cpuLoad = (Double) connection.getAttribute(new ObjectName("java.lang:type=OperatingSystem"),
		    "SystemCpuLoad");
	    cpuMeter.setValue(cpuLoad * 100.0);
	    CompositeDataSupport memoryUsage = (CompositeDataSupport) connection
		    .getAttribute(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
	    long usedMemory = (long) memoryUsage.get("used");
	    long maxMemory = (long) memoryUsage.get("max");
	    memoryMeter.setValue(usedMemory);
	    memoryMeter.setEnd(maxMemory);

	    this.cpuLoad.add(new CPULoadMeasurement(now, cpuLoad));
	    this.memoryUsage.add(new MemoryUsageMeasurement(now, usedMemory, maxMemory));
	    cpuLoadPlot.setData(this.cpuLoad);
	    usedMemoryPlot.setData(this.memoryUsage);
	    maxMemoryPlot.setData(this.memoryUsage);
	} catch (IOException | AttributeNotFoundException | InstanceNotFoundException | MalformedObjectNameException
		| MBeanException | ReflectionException e) {
	    e.printStackTrace();
	}

    }

}
