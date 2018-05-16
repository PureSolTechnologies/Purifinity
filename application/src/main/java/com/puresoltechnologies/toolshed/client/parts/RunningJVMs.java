package com.puresoltechnologies.toolshed.client.parts;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Set;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.PerspectiveService;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.toolshed.client.jvm.RunningJVM;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.VmIdentifier;
import sun.jvmstat.monitor.event.HostEvent;
import sun.jvmstat.monitor.event.HostListener;
import sun.jvmstat.monitor.event.VmStatusChangeEvent;

public class RunningJVMs extends AbstractViewer implements HostListener {

    private BorderPane borderPane;
    TreeItem<RunningJVM> localJVMsTreeItem = new TreeItem<RunningJVM>(new RunningJVM("Local JVMS"));
    TreeItem<RunningJVM> remoteJVMsTreeItem = new TreeItem<RunningJVM>(new RunningJVM("Remote JVMS"));
    private TreeView<RunningJVM> vms;
    private MonitoredHost monitoredHost;

    public RunningJVMs() {
	super("Running Java Virtual Machines", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
	ToolBar toolBar = new ToolBar();
	toolBar.getItems().addAll(new Button("Add remote JVM..."), new Button("Refresh local JVM list"));
	TreeItem<RunningJVM> rootItem = new TreeItem<>();
	rootItem.setExpanded(true);
	rootItem.getChildren().add(localJVMsTreeItem);
	rootItem.getChildren().add(remoteJVMsTreeItem);
	vms = new TreeView<>(rootItem);
	vms.setCellFactory(item -> {
	    return new TreeCell<RunningJVM>() {
		@Override
		protected void updateItem(RunningJVM item, boolean empty) {
		    super.updateItem(item, empty);
		    if (empty) {
			setText("");
		    } else {
			setText(item.getName());
		    }
		}
	    };
	});
	vms.setOnMouseClicked(mouseEvent -> {
	    if (mouseEvent.getClickCount() == 2) {
		TreeItem<RunningJVM> item = vms.getSelectionModel().getSelectedItem();
		RunningJVM runningJVM = item.getValue();
		JVMMonitor jvmMonitor = new JVMMonitor();
		jvmMonitor.setJVM(runningJVM.getHostIdentifier(), runningJVM.getId());
		PerspectiveService.openPart(jvmMonitor);
	    }
	});
	vms.setShowRoot(false);
	borderPane.setTop(toolBar);
	borderPane.setCenter(vms);

	try {
	    monitoredHost = MonitoredHost.getMonitoredHost(new HostIdentifier("localhost"));
	    monitoredHost.addHostListener(this);
	} catch (MonitorException | URISyntaxException e) {
	    e.printStackTrace();
	}
    }

    private void refresh() {
	try {
	    localJVMsTreeItem.getChildren().clear();
	    Set<Integer> activeVms = monitoredHost.activeVms();
	    for (int id : activeVms) {
		MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(new VmIdentifier("//" + id));
		localJVMsTreeItem.getChildren().add(new TreeItem<>(new RunningJVM(monitoredHost, id)));

	    }
	} catch (MonitorException | URISyntaxException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void close() {
	try {
	    monitoredHost.removeHostListener(this);
	} catch (MonitorException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void vmStatusChanged(VmStatusChangeEvent event) {
	refresh();
    }

    @Override
    public void disconnected(HostEvent event) {
	refresh();
    }

}
