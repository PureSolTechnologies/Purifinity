package com.puresoltechnologies.toolshed.client.parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.streaming.csv.CSVHeader;
import com.puresoltechnologies.streaming.csv.CSVWriter;
import com.puresoltechnologies.streaming.csv.mapper.CSVMapper;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.tables.ProfileParetoTable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProfileParetoTableViewer extends AbstractViewer implements Consumer<Profile> {

    private Disposable storeDisposable;
    private Button tableExportButton;
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
	table = new ProfileParetoTable();

	ToolBar toolBar = new ToolBar();
	try {
	    ImageView tableExportImage = ResourceUtils.getImageView(this, "/icons/FatCow_Icons16x16/table_export.png");
	    tableExportButton = new Button("", tableExportImage);
	} catch (IOException e) {
	    tableExportButton = new Button("Export Table");
	}
	tableExportButton.setTooltip(new Tooltip("Export loaded profile to table."));
	tableExportButton.setOnAction(event -> {
	    exportTable();
	    event.consume();
	});
	toolBar.getItems().addAll(tableExportButton);

	BorderPane filterPane = new BorderPane();
	HBox filterBox = new HBox();
	Label filterLabel = new Label("Filter:");
	filterTextField = new TextField();
	filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
	    showValue();
	});
	filterBox.getChildren().addAll(filterLabel, filterTextField);
	showUnusedMethodsCheckBox = new CheckBox("Show unused methods");
	showUnusedMethodsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> showValue());
	filterPane.setTop(filterBox);
	filterPane.setCenter(showUnusedMethodsCheckBox);

	borderPane.setTop(toolBar);
	borderPane.setCenter(table);
	borderPane.setBottom(filterPane);
	storeDisposable = ReactiveFX.getStore().subscribe(Topics.NEW_PROFILE, this);
    }

    private void exportTable() {
	if (profile == null) {
	    return;
	}
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Choose output file");
	fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV file", "*.csv"));
	File exportFile = fileChooser.showSaveDialog(null);
	if (exportFile != null) {
	    saveCsv(exportFile);
	}
    }

    private void saveCsv(File rawProfileFile) {
	String filterText = filterTextField.getText();
	boolean emptyFilter = filterText.isEmpty();
	Stream<ProfileEntry> entries = profile.getEntries().stream();
	if (!showUnusedMethodsCheckBox.isSelected()) {
	    entries = entries.filter(entry -> entry.getInvocations() > 0);
	}
	if (!filterText.isEmpty()) {
	    entries = entries.filter(
		    entry -> entry.getClassName().contains(filterText) || entry.getMethodName().contains(filterText));

	}
	CSVMapper csvMapper = new CSVMapper();
	try (FileOutputStream fileOutputStream = new FileOutputStream(rawProfileFile)) {
	    CSVWriter csvWriter = new CSVWriter(fileOutputStream);
	    AtomicBoolean success = new AtomicBoolean(true);
	    csvWriter.writeHeader(new CSVHeader(Arrays.asList("Class", "Method", "Invokations", "Self Time",
		    "Cummulated Self Time", "Total Time", "Cummulated Total Time")));
	    entries.forEach(entry -> {
		try {
		    long invocations = entry.getInvocations();
		    csvWriter.write(entry.getClassName());
		    csvWriter.writeSeparator();
		    csvWriter.write(entry.getMethodName() + entry.getDescriptor());
		    csvWriter.writeSeparator();
		    csvWriter.write(invocations);
		    csvWriter.writeSeparator();
		    csvWriter.write((double) entry.getSelfTime() / invocations);
		    csvWriter.writeSeparator();
		    csvWriter.write(entry.getSelfTime());
		    csvWriter.writeSeparator();
		    csvWriter.write((double) entry.getTotalTime() / invocations);
		    csvWriter.writeSeparator();
		    csvWriter.write(entry.getTotalTime());
		    csvWriter.writeEndOfLine();
		} catch (IOException e) {
		    success.set(false);
		}
	    });
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
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
	if (profile == null) {
	    return;
	}
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
