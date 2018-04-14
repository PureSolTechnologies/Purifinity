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
	TableColumn<ProfileMethodEntry, Long> invokationsColumn = new TableColumn<>("Invokations");
	invokationsColumn.setSortable(true);
	invokationsColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getInvocations()).asObject());
	columns.add(invokationsColumn);
	TableColumn<ProfileMethodEntry, Long> avgTimeColumn = new TableColumn<>("avgTime");
	avgTimeColumn.setSortable(true);
	avgTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getAvgTime()).asObject());
	columns.add(avgTimeColumn);
	TableColumn<ProfileMethodEntry, Long> minTimeColumn = new TableColumn<>("minTime");
	minTimeColumn.setSortable(true);
	minTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getMinTime()).asObject());
	columns.add(minTimeColumn);
	TableColumn<ProfileMethodEntry, Long> maxTimeColumn = new TableColumn<>("maxTime");
	maxTimeColumn.setSortable(true);
	maxTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getMaxTime()).asObject());
	columns.add(maxTimeColumn);
	TableColumn<ProfileMethodEntry, Long> sigmaTimeColumn = new TableColumn<>("sigmaTime");
	sigmaTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getSigmaTime()).asObject());
	columns.add(sigmaTimeColumn);
	TableColumn<ProfileMethodEntry, Long> totalTimeColumn = new TableColumn<>("totalTime");
	totalTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getTotalTime()).asObject());
	totalTimeColumn.setSortable(true);
	columns.add(totalTimeColumn);
    }

}
