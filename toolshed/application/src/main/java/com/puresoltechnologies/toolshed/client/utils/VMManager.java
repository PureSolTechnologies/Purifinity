package com.puresoltechnologies.toolshed.client.utils;

import java.net.URISyntaxException;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.event.HostEvent;
import sun.jvmstat.monitor.event.HostListener;
import sun.jvmstat.monitor.event.VmStatusChangeEvent;

public class VMManager implements HostListener {

    public static class VMDefinition {

    }

    private static final VMManager instance = new VMManager();

    private static final Subject<VmStatusChangeEvent> subject = PublishSubject.create();

    public static VMManager getInstance() {
	return instance;
    }

    public static Subject<VmStatusChangeEvent> getSubject() {
	return subject;
    }

    private MonitoredHost monitoredHost;

    private VMManager() {
	try {
	    monitoredHost = MonitoredHost.getMonitoredHost(new HostIdentifier("localhost"));
	    monitoredHost.addHostListener(this);
	} catch (MonitorException | URISyntaxException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void vmStatusChanged(VmStatusChangeEvent event) {
	subject.onNext(event);
    }

    @Override
    public void disconnected(HostEvent event) {
	refresh();
    }

    private void refresh() {
	// TODO
    }

}
