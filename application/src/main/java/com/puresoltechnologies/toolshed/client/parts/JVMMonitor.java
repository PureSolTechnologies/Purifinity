package com.puresoltechnologies.toolshed.client.parts;

import java.net.URISyntaxException;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.utils.FXDefaultFonts;
import com.puresoltechnologies.toolshed.client.tables.MonitorTable;

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
    private final ListProperty<Monitor> monitors = new SimpleListProperty<>(FXCollections.observableArrayList());

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
	    monitoredVm = monitoredHost.getMonitoredVm(new VmIdentifier("//" + id));
	    name = MonitoredVmUtil.mainClass(monitoredVm, false) + " (" + id + ")";
	    monitoredVm.addVmListener(this);

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
	    tabPane.getTabs().add(new Tab("Performance"));
	    tabPane.getTabs().add(new Tab("JMX"));
	    borderPane.setCenter(tabPane);
	} catch (MonitorException | URISyntaxException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void close() {
	try {
	    monitoredVm.removeVmListener(this);
	} catch (MonitorException e) {
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
