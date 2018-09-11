package com.puresoltechnologies.toolshed.client.tables;

import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;

import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;

public class ProfileTreeTable extends TreeTableView<ProfileEntry> {

    public ProfileTreeTable() {
	super();
	ObservableList<TreeTableColumn<ProfileEntry, ?>> columns = getColumns();
	TreeTableColumn<ProfileEntry, String> classNameColumn = new TreeTableColumn<>("Class");
	classNameColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getValue().getClassName()));
	classNameColumn.setSortable(true);
	columns.add(classNameColumn);
	TreeTableColumn<ProfileEntry, String> methodColumn = new TreeTableColumn<>("Method");
	methodColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(
		e.getValue().getValue().getMethodName() + e.getValue().getValue().getDescriptor()));
	methodColumn.setSortable(true);
	columns.add(methodColumn);
	TreeTableColumn<ProfileEntry, Long> invokationsColumn = new TreeTableColumn<>("Invocations");
	invokationsColumn
		.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getValue().getInvocations()).asObject());
	invokationsColumn.setSortable(true);
	columns.add(invokationsColumn);
	TreeTableColumn<ProfileEntry, Long> selfTimeColumn = new TreeTableColumn<>("Self Time");
	selfTimeColumn.setCellValueFactory(e -> {
	    ProfileEntry value = e.getValue().getValue();
	    if (value.getInvocations() == 0) {
		return null;
	    }
	    return new ReadOnlyLongWrapper(value.getSelfTime() / value.getInvocations()).asObject();
	});
	selfTimeColumn.setSortable(true);
	columns.add(selfTimeColumn);
	TreeTableColumn<ProfileEntry, Long> accSelfTimeColumn = new TreeTableColumn<>("Acc. Self Time");
	accSelfTimeColumn
		.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getValue().getSelfTime()).asObject());
	accSelfTimeColumn.setSortable(true);
	columns.add(accSelfTimeColumn);
	TreeTableColumn<ProfileEntry, Long> totalTimeColumn = new TreeTableColumn<>("Total Time");
	totalTimeColumn.setCellValueFactory(e -> {
	    ProfileEntry value = e.getValue().getValue();
	    if (value.getInvocations() == 0) {
		return null;
	    }
	    return new ReadOnlyLongWrapper(value.getTotalTime() / value.getInvocations()).asObject();
	});
	totalTimeColumn.setSortable(true);
	columns.add(totalTimeColumn);
	TreeTableColumn<ProfileEntry, Long> accTotalTimeColumn = new TreeTableColumn<>("Acc. Total Time");
	accTotalTimeColumn
		.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getValue().getTotalTime()).asObject());
	accTotalTimeColumn.setSortable(true);
	columns.add(accTotalTimeColumn);

	setRowFactory(tv -> {
	    TreeTableRow<ProfileEntry> row = new TreeTableRow<>();
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
