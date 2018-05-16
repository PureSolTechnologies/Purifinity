package com.puresoltechnologies.toolshed.client.jvm;

import java.net.URISyntaxException;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.VmIdentifier;

public class RunningJVM {

    private final MonitoredHost monitoredHost;
    private final int id;
    private final MonitoredVm monitoredVm;
    private final String name;

    public RunningJVM(MonitoredHost monitoredHost, int id) throws MonitorException, URISyntaxException {
	super();
	this.monitoredHost = monitoredHost;
	this.id = id;
	this.monitoredVm = monitoredHost.getMonitoredVm(new VmIdentifier("//" + id));
	this.name = MonitoredVmUtil.mainClass(monitoredVm, false) + " (" + id + ")";
    }

    public RunningJVM(String name) {
	this.monitoredHost = null;
	this.id = -1;
	this.monitoredVm = null;
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public MonitoredHost getMonitoredHost() {
	return monitoredHost;
    }

    public MonitoredVm getMonitoredVm() {
	return monitoredVm;
    }

    public HostIdentifier getHostIdentifier() {
	return monitoredHost.getHostIdentifier();
    }

}
