package com.puresoltechnologies.debugging.client.tables;

import com.puresoltechnologies.debugging.client.profiles.ProfileMethodEntry;

import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProfileParetoTable extends TableView<ProfileMethodEntry> {

    public ProfileParetoTable() {
	super();
	ObservableList<TableColumn<ProfileMethodEntry, ?>> columns = getColumns();
	TableColumn<ProfileMethodEntry, String> classNameColumn = new TableColumn<>("Class");
	classNameColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getClassName()));
	classNameColumn.setSortable(true);
	columns.add(classNameColumn);
	TableColumn<ProfileMethodEntry, String> methodColumn = new TableColumn<>("Method");
	methodColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMethod()));
	methodColumn.setSortable(true);
	columns.add(methodColumn);
	TableColumn<ProfileMethodEntry, Long> invokationsColumn = new TableColumn<>("Invocations");
	invokationsColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getInvocations()).asObject());
	invokationsColumn.setSortable(true);
	columns.add(invokationsColumn);
	TableColumn<ProfileMethodEntry, Long> avgTimeColumn = new TableColumn<>("avgTime");
	avgTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getAvgTime()).asObject());
	avgTimeColumn.setSortable(true);
	columns.add(avgTimeColumn);
	TableColumn<ProfileMethodEntry, Long> totalTimeColumn = new TableColumn<>("totalTime");
	totalTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getTotalTime()).asObject());
	totalTimeColumn.setSortable(true);
	columns.add(totalTimeColumn);
    }

}
