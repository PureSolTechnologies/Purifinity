package com.puresoltechnologies.toolshed.client.controls.daterange;

import java.io.IOException;
import java.time.LocalDate;

import com.puresoltechnologies.javafx.utils.ResourceUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DateRangePicker extends HBox {

    private static final Image rightArrow;
    static {
	try {
	    rightArrow = ResourceUtils.getImage(DateRangePicker.class, "/icons/FatCow_Icons16x16/arrow_right.png");
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    private final ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(LocalDate.now());
    private final ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>(LocalDate.now());

    private final Label defaultDateRangeTitleLabel = new Label("manual");
    private final MenuButton defaultDateRangesMenuButton = new MenuButton("Quick Access");
    private final DatePicker startDatePicker = new DatePicker();
    private final DatePicker endDatePicker = new DatePicker();

    public DateRangePicker() {
	super();
	setSpacing(5.0);
	setAlignment(Pos.CENTER_RIGHT);
	startDatePicker.valueProperty().addListener((o, oldValue, newValue) -> {
	    startDate.set(newValue);
	    defaultDateRangeTitleLabel.setText("manual");
	});
	endDatePicker.valueProperty().addListener((o, oldValue, newValue) -> {
	    endDate.set(newValue);
	    defaultDateRangeTitleLabel.setText("manual");
	});
	startDatePicker.setValue(LocalDate.now());
	endDatePicker.setValue(LocalDate.now());
	getChildren().addAll(defaultDateRangesMenuButton, defaultDateRangeTitleLabel, startDatePicker,
		new Label("", new ImageView(rightArrow)), endDatePicker);
    }

    public DateRangePicker(LocalDate startDate, LocalDate endDate) {
	this();
	startDatePicker.setValue(startDate);
	endDatePicker.setValue(endDate);
    }

    public LocalDate getStartDate() {
	return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
	this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> endStartProperty() {
	return startDate;
    }

    public LocalDate getEndDate() {
	return endDate.get();
    }

    public void setEndDate(LocalDate endDate) {
	this.endDate.set(endDate);
    }

    public ObjectProperty<LocalDate> endDateProperty() {
	return endDate;
    }

    public void addDefaultDateRange(DefaultDateRange defaultDateRange) {
	MenuItem menuItem = new MenuItem(defaultDateRange.getTitle());
	menuItem.setOnAction(event -> {
	    startDatePicker.setValue(defaultDateRange.getStartDate());
	    endDatePicker.setValue(defaultDateRange.getEndDate());
	    defaultDateRangeTitleLabel.setText(defaultDateRange.getTitle());
	    event.consume();
	});
	defaultDateRangesMenuButton.getItems().add(menuItem);
    }
}
