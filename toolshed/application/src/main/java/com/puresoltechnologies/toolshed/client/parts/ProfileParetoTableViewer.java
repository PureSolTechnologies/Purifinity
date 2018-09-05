package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.tables.ProfileParetoTable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ProfileParetoTableViewer extends AbstractViewer implements Consumer<Profile> {

    private Disposable storeDisposable;
    private TextField filterTextField;
    private CheckBox showUnusedMethodsCheckBox;
    private BorderPane borderPane;
    private ProfileParetoTable table;
    private Profile profile = null;

    public ProfileParetoTableViewer() {
	super("Profile Pareto", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
	filterTextField = new TextField();
	filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
	    showValue();
	});
	table = new ProfileParetoTable();
	showUnusedMethodsCheckBox = new CheckBox("Show unused methods");
	showUnusedMethodsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> showValue());
	borderPane.setTop(filterTextField);
	borderPane.setCenter(table);
	borderPane.setBottom(showUnusedMethodsCheckBox);
	storeDisposable = ReactiveFX.getStore().subscribe(Topics.NEW_PROFILE, this);
    }

    @Override
    public void close() {
	storeDisposable.dispose();
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void accept(Profile profile) throws Exception {
	this.profile = profile;
	showValue();
    }

    private void showValue() {
	String filterText = filterTextField.getText();
	boolean emptyFilter = filterText.isEmpty();
	if (showUnusedMethodsCheckBox.isSelected() && emptyFilter) {
	    table.setItems(FXCollections.observableList(profile.getEntries()));
	} else {
	    ObservableList<ProfileEntry> entries = FXCollections.observableArrayList();
	    profile.getEntries().stream() //
		    .filter(entry -> entry.getInvocations() > 0) //
		    .filter(entry -> emptyFilter || entry.getClassName().contains(filterText)
			    || entry.getMethodName().contains(filterText))
		    .forEach(entry -> entries.add(entry));
	    table.setItems(entries);
	}
    }

}
