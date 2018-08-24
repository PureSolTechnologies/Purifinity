package com.puresoltechnologies.toolshed.client.tables;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sun.jvmstat.monitor.Monitor;

public class MonitorTable extends TableView<Monitor> {

    public MonitorTable() {
	TableColumn<Monitor, String> nameColumn = new TableColumn<>("Name");
	nameColumn.setCellValueFactory(monitor -> new ReadOnlyStringWrapper(monitor.getValue().getName()));
	getColumns().add(nameColumn);

	TableColumn<Monitor, String> baseNameColumn = new TableColumn<>("Base Name");
	baseNameColumn.setCellValueFactory(monitor -> new ReadOnlyStringWrapper(monitor.getValue().getBaseName()));
	getColumns().add(baseNameColumn);

	TableColumn<Monitor, String> variabilityColumn = new TableColumn<>("Variability");
	variabilityColumn.setCellValueFactory(
		monitor -> new ReadOnlyStringWrapper(monitor.getValue().getVariability().toString()));
	getColumns().add(variabilityColumn);

	TableColumn<Monitor, String> valueColumn = new TableColumn<>("Value");
	valueColumn.setCellValueFactory(monitor -> new ReadOnlyStringWrapper(monitor.getValue().getValue().toString()));
	getColumns().add(valueColumn);

	TableColumn<Monitor, String> unitsColumn = new TableColumn<>("Units");
	unitsColumn.setCellValueFactory(monitor -> new ReadOnlyStringWrapper(monitor.getValue().getUnits().toString()));
	getColumns().add(unitsColumn);
    }

}
