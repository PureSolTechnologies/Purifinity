package com.puresoltechnologies.debugging.client.preferences;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.puresoltechnologies.javafx.preferences.Preferences;
import com.puresoltechnologies.javafx.preferences.dialogs.PreferencesPage;
import com.puresoltechnologies.javafx.utils.ResourceUtils;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TraderServerPreferencesPage implements PreferencesPage {

    private TextField hostInput;
    private SpinnerValueFactory<Integer> valueFactory;
    private Spinner<Integer> portInput;

    @Override
    public String getName() {
	return "Trader Server Connection";
    }

    @Override
    public Image getImage() {
	try {
	    return ResourceUtils.getImage(this, "/icons/FatCow_Icons16x16/server_configuration.png");
	} catch (IOException e) {
	    return null;
	}
    }

    @Override
    public List<String> getPath() {
	return Arrays.asList("Server", "Connection");
    }

    @Override
    public Pane getPane() {
	GridPane gridPane = new GridPane();

	Label hostLabel = new Label("Host");
	GridPane.setRowIndex(hostLabel, 0);
	GridPane.setColumnIndex(hostLabel, 0);
	hostInput = new TextField();
	GridPane.setRowIndex(hostInput, 0);
	GridPane.setColumnIndex(hostInput, 1);
	Label portLabel = new Label("Port");
	GridPane.setRowIndex(portLabel, 1);
	GridPane.setColumnIndex(portLabel, 0);
	valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 65535,
		TraderServerProperties.traderPortDefault);
	portInput = new Spinner<>();
	portInput.setValueFactory(valueFactory);
	GridPane.setRowIndex(portInput, 1);
	GridPane.setColumnIndex(portInput, 1);

	gridPane.getChildren().addAll(hostLabel, hostInput, portLabel, portInput);
	return gridPane;
    }

    @Override
    public void reset() {
	hostInput.setText(TraderServerProperties.traderHostDefault);
	valueFactory.setValue(TraderServerProperties.traderPortDefault);
    }

    @Override
    public void load(Preferences preferences) {
	hostInput.setText(preferences.getValue(TraderServerProperties.traderHost));
	valueFactory.setValue(preferences.getValue(TraderServerProperties.traderPort));
    }

    @Override
    public void save(Preferences preferences) {
	preferences.setValue(TraderServerProperties.traderHost, hostInput.getText());
	preferences.setValue(TraderServerProperties.traderPort, valueFactory.getValue());
    }

}
