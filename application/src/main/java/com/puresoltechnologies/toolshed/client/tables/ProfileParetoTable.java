package com.puresoltechnologies.toolshed.client.tables;

import com.puresoltechnologies.javafx.perspectives.PerspectiveService;
import com.puresoltechnologies.toolshed.client.parts.MethodProfileViewer;
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
	methodColumn.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getMethod()));
	methodColumn.setSortable(true);
	columns.add(methodColumn);
	TableColumn<ProfileEntry, Long> invokationsColumn = new TableColumn<>("Invocations");
	invokationsColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getInvocations()).asObject());
	invokationsColumn.setSortable(true);
	columns.add(invokationsColumn);
	TableColumn<ProfileEntry, Long> totalTimeColumn = new TableColumn<>("totalTime");
	totalTimeColumn.setCellValueFactory(e -> new ReadOnlyLongWrapper(e.getValue().getTotalTime()).asObject());
	totalTimeColumn.setSortable(true);
	columns.add(totalTimeColumn);

	setRowFactory(tv -> {
	    TableRow<ProfileEntry> row = new TableRow<>();
	    row.setOnMouseClicked(event -> {
		if (event.getClickCount() == 2 && (!row.isEmpty())) {
		    ProfileEntry profileEntry = row.getItem();
		    MethodProfileViewer methodProfileViewer = new MethodProfileViewer();
		    PerspectiveService.openPart(methodProfileViewer);
		    methodProfileViewer.setMethod(profileEntry.getClassName(), profileEntry.getMethod());
		}
	    });
	    return row;
	});

    }

}
