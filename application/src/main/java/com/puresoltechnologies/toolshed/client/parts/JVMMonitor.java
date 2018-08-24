package com.puresoltechnologies.toolshed.client.parts;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.utils.FXDefaultFonts;
import com.puresoltechnologies.toolshed.client.jvm.jmx.JMXViewer;
import com.puresoltechnologies.toolshed.client.jvm.jmx.JVMPerformancePane;
import com.puresoltechnologies.toolshed.client.tables.MonitorTable;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.monitor.event.MonitorStatusChangeEvent;
import sun.jvmstat.monitor.event.VmEvent;
import sun.jvmstat.monitor.event.VmListener;

public class JVMMonitor extends AbstractViewer implements VmListener {

    private HostIdentifier hostIdentifier = null;
    private int id = -1;
    private MonitoredHost monitoredHost = null;
    private MonitoredVm monitoredVm = null;
    private String name = null;
    private BorderPane borderPane;
    private TabPane tabPane;
    private MonitorTable monitorTable;
    private JVMPerformancePane performanceView;
    private JMXViewer jmxViewer;
    private ScheduledExecutorService updateScheduler = null;
    private ScheduledFuture<?> updaterFuture = null;

    private final ListProperty<Monitor> monitors = new SimpleListProperty<>(FXCollections.observableArrayList());
    private JMXConnector jmxConnector;

    public JVMMonitor() {
	super("JVM Monitor", PartOpenMode.AUTO_ONLY);
    }

    public void setJVM(HostIdentifier hostIdentifier, int id) {
	this.hostIdentifier = hostIdentifier;
	this.id = id;
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();

	try {
	    monitoredHost = MonitoredHost.getMonitoredHost(hostIdentifier);
	    monitoredHost.setInterval(1000);
	    VmIdentifier vmIdentifier = new VmIdentifier("//" + id);
	    monitoredVm = monitoredHost.getMonitoredVm(vmIdentifier);
	    name = MonitoredVmUtil.mainClass(monitoredVm, false) + " (" + id + ")";
	    monitoredVm.addVmListener(this);
	    monitoredVm.setInterval(1000);

	    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	    if (String.valueOf(id) == runtimeMXBean.getName()) {

	    } else {
		VirtualMachine virtualMachine = VirtualMachine.attach(String.valueOf(id));
		String jmxUrl = virtualMachine.startLocalManagementAgent();
		jmxConnector = JMXConnectorFactory.connect(new JMXServiceURL(jmxUrl), null);
	    }

	    setTitle("JVM Monitor - " + name);

	    Label titleLabel = new Label(name);
	    titleLabel.setFont(FXDefaultFonts.titleFont);
	    borderPane.setTop(titleLabel);
	    tabPane = new TabPane();
	    tabPane.getTabs().add(new Tab("Overview"));
	    Tab monitorsTab = new Tab("Monitors");
	    monitorTable = new MonitorTable();
	    monitorTable.itemsProperty().bind(monitors);
	    monitorsTab.setContent(monitorTable);
	    tabPane.getTabs().add(monitorsTab);
	    Tab performanceTab = new Tab("Performance");
	    performanceView = new JVMPerformancePane();
	    performanceView.setJMXConnector(jmxConnector);
	    performanceTab.setContent(performanceView);
	    tabPane.getTabs().add(performanceTab);
	    Tab jmx = new Tab("JMX");
	    jmxViewer = new JMXViewer();
	    jmxViewer.setJMXConnector(jmxConnector);
	    jmx.setContent(jmxViewer);
	    tabPane.getTabs().add(jmx);
	    borderPane.setCenter(tabPane);

	    updateScheduler = Executors.newScheduledThreadPool(1);
	    ScheduledFuture<?> updaterFuture = updateScheduler.scheduleAtFixedRate(() -> {
		performanceView.update();
	    }, 0l, 1l, TimeUnit.SECONDS);

	} catch (MonitorException | URISyntaxException | IOException | AttachNotSupportedException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void close() {
	try {
	    if (updaterFuture != null) {
		updaterFuture.cancel(false);
	    }
	    updateScheduler.shutdown();
	    jmxConnector.close();
	    monitoredVm.removeVmListener(this);
	    updateScheduler.awaitTermination(10, TimeUnit.SECONDS);
	} catch (MonitorException | IOException | InterruptedException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void monitorStatusChanged(MonitorStatusChangeEvent event) {
	monitors.addAll(event.getInserted());
	monitors.removeAll(event.getRemoved());
    }

    @Override
    public void monitorsUpdated(VmEvent event) {
	// TODO Auto-generated method stub
    }

    @Override
    public void disconnected(VmEvent event) {
	// TODO Auto-generated method stub
    }

}
