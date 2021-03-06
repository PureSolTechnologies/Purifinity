package com.puresoltechnologies.toolshed.client.tables;

import java.util.concurrent.TimeUnit;

import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;

import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class ProfileParetoTable extends TableView<ProfileEntry> {

    public ProfileParetoTable() {
	super();
	ObservableList<TableColumn<ProfileEntry, ?>> columns = getColumns();
	TableColumn<ProfileEntry, String> classNameColumn = new TableColumn<>("Class");
	classNameColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getClassName()));
	classNameColumn.setSortable(true);
	columns.add(classNameColumn);
	TableColumn<ProfileEntry, String> methodColumn = new TableColumn<>("Method");
	methodColumn.setCellValueFactory(
		e -> new ReadOnlyStringWrapper(e.getValue().getMethodName() + e.getValue().getDescriptor()));
	methodColumn.setSortable(true);
	columns.add(methodColumn);
	TableColumn<ProfileEntry, Long> invokationsColumn = new TableColumn<>("Invocations");
	invokationsColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getInvocations()).asObject());
	invokationsColumn.setSortable(true);
	columns.add(invokationsColumn);
	TableColumn<ProfileEntry, Long> selfTimeColumn = new TableColumn<>("Self Time (us)");
	selfTimeColumn.setCellValueFactory(e -> {
	    ProfileEntry value = e.getValue();
	    if (value.getInvocations() == 0) {
		return null;
	    }
	    long averageTime = value.getSelfTime() / value.getInvocations();
	    return new ReadOnlyLongWrapper(TimeUnit.NANOSECONDS.toMicros(averageTime)).asObject();
	});
	selfTimeColumn.setSortable(true);
	columns.add(selfTimeColumn);
	TableColumn<ProfileEntry, Long> accSelfTimeColumn = new TableColumn<>("Acc. Self Time (us)");
	accSelfTimeColumn.setCellValueFactory(
		e -> new ReadOnlyLongWrapper(TimeUnit.NANOSECONDS.toMicros(e.getValue().getSelfTime())).asObject());
	accSelfTimeColumn.setSortable(true);
	columns.add(accSelfTimeColumn);
	TableColumn<ProfileEntry, Long> totalTimeColumn = new TableColumn<>("Total Time (us)");
	totalTimeColumn.setCellValueFactory(e -> {
	    ProfileEntry value = e.getValue();
	    if (value.getInvocations() == 0) {
		return null;
	    }
	    long averageTime = value.getTotalTime() / value.getInvocations();
	    return new ReadOnlyLongWrapper(TimeUnit.NANOSECONDS.toMicros(averageTime)).asObject();
	});
	totalTimeColumn.setSortable(true);
	columns.add(totalTimeColumn);
	TableColumn<ProfileEntry, Long> accTotalTimeColumn = new TableColumn<>("Acc. Total Time (us)");
	accTotalTimeColumn.setCellValueFactory(
		e -> new ReadOnlyLongWrapper(TimeUnit.NANOSECONDS.toMicros(e.getValue().getTotalTime())).asObject());
	accTotalTimeColumn.setSortable(true);
	columns.add(accTotalTimeColumn);

	setRowFactory(tv -> {
	    TableRow<ProfileEntry> row = new TableRow<>();
	    row.setOnMouseClicked(event -> {
		if (event.getClickCount() == 2 && (!row.isEmpty())) {
		    ProfileEntry profileEntry = row.getItem();
		    ReactiveFX.getStore().publish(Topics.PROFILE_ENTRY_SELECTED, profileEntry);
		}
	    });
	    return row;
	});
    }

}
