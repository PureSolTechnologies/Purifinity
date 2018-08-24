package com.puresoltechnologies.toolshed.client.controls.interval;

import java.time.temporal.ChronoUnit;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class PeriodPicker extends HBox {

    private final IntegerProperty amount = new SimpleIntegerProperty(1);
    private final ObjectProperty<ChronoUnit> unit = new SimpleObjectProperty<>(ChronoUnit.HOURS);

    private final Spinner<Integer> amountSpinner = new Spinner<>(1, 366, 1);
    private final ComboBox<ChronoUnit> unitComboBox = new ComboBox<>();

    public PeriodPicker() {
	setSpacing(5.0);
	setAlignment(Pos.CENTER_RIGHT);
	amountSpinner.setEditable(true);
	Callback<ListView<ChronoUnit>, ListCell<ChronoUnit>> callback = listView -> {
	    return new ListCell<ChronoUnit>() {
		@Override
		protected void updateItem(ChronoUnit item, boolean empty) {
		    super.updateItem(item, empty);
		    if (empty) {
			setText(null);
		    } else {
			setText(item.name());
		    }
		}
	    };
	};
	unitComboBox.setButtonCell(callback.call(null));
	unitComboBox.setCellFactory(callback);
	unitComboBox.getItems().addAll(ChronoUnit.MINUTES, ChronoUnit.HOURS, ChronoUnit.DAYS, ChronoUnit.MONTHS,
		ChronoUnit.YEARS);
	unitComboBox.setValue(ChronoUnit.HOURS);
	amountSpinner.valueProperty().addListener((o, oldValue, newValue) -> {
	    amount.set(newValue);
	});
	unitComboBox.valueProperty().addListener((o, oldValue, newValue) -> {
	    unit.set(newValue);
	});
	getChildren().addAll(amountSpinner, unitComboBox);
    }

    public int getAmount() {
	return amount.get();
    }

    public void setAmount(int amount) {
	this.amount.set(amount);
    }

    public IntegerProperty amountProperty() {
	return amount;
    }

    public ChronoUnit getUnit() {
	return unit.get();
    }

    public void setUnit(ChronoUnit unit) {
	this.unit.set(unit);
    }

    public ObjectProperty<ChronoUnit> unitProperty() {
	return unit;
    }
}
