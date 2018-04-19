package com.puresoltechnologies.debugging.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.debugging.client.perspectives.ProfilerPerspective;
import com.puresoltechnologies.javafx.extensions.menu.AboutMenuItem;
import com.puresoltechnologies.javafx.perspectives.PerspectiveContainer;
import com.puresoltechnologies.javafx.perspectives.PerspectiveService;
import com.puresoltechnologies.javafx.perspectives.menu.PerspectiveMenu;
import com.puresoltechnologies.javafx.perspectives.menu.ShowPartMenuItem;
import com.puresoltechnologies.javafx.preferences.Preferences;
import com.puresoltechnologies.javafx.preferences.menu.PreferencesMenuItem;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.javafx.utils.FXThreads;
import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.javafx.workspaces.Workspace;
import com.puresoltechnologies.javafx.workspaces.menu.ExitApplicationMenuItem;
import com.puresoltechnologies.javafx.workspaces.menu.RestartApplicationMenuItem;
import com.puresoltechnologies.javafx.workspaces.menu.SwitchWorkspaceMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DebuggingClient extends Application {

    private static final Logger logger = LoggerFactory.getLogger(DebuggingClient.class);

    private PerspectiveContainer perspectiveContainer;

    @Override
    public void init() throws Exception {
	super.init();
	FXThreads.initialize();
	Preferences.initialize();
	PerspectiveService.initialize();
	ReactiveFX.initialize();
    }

    @Override
    public void start(Stage stage) throws Exception {
	try {
	    stage.setTitle("Debugging Client");
	    stage.setResizable(true);
	    stage.centerOnScreen();

	    Image chartUpColorSmall = ResourceUtils.getImage(this, "/icons/FatCow_Icons16x16/bug_fixing.png");
	    Image chartUpColorBig = ResourceUtils.getImage(this, "/icons/FatCow_Icons32x32/bug_fixing.png");
	    stage.getIcons().addAll(chartUpColorSmall, chartUpColorBig);

	    perspectiveContainer = PerspectiveService.getContainer();
	    BorderPane root = new BorderPane();
	    addMenu(stage, root);
	    root.setCenter(perspectiveContainer);

	    perspectiveContainer.addPerspective(new ProfilerPerspective());

	    Scene scene = new Scene(root, 640, 480);
	    stage.setScene(scene);
	    stage.show();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    private void addMenu(Stage stage, BorderPane root) {
	// File Menu
	SwitchWorkspaceMenu switchWorkspaceMenu = new SwitchWorkspaceMenu(stage);
	RestartApplicationMenuItem restartApplicationMenuItem = new RestartApplicationMenuItem(stage);
	ExitApplicationMenuItem exitApplicationMenuItem = new ExitApplicationMenuItem(stage);
	Menu fileMenu = new Menu("File");
	fileMenu.getItems().addAll(switchWorkspaceMenu, restartApplicationMenuItem, exitApplicationMenuItem);
	// Window Menu
	ShowPartMenuItem showViewItem = new ShowPartMenuItem();
	PreferencesMenuItem preferencesItem = new PreferencesMenuItem();
	Menu windowMenu = new Menu("Window");
	windowMenu.getItems().addAll(showViewItem, new PerspectiveMenu(), new SeparatorMenuItem(), preferencesItem);
	// Help Menu
	AboutMenuItem aboutItem = new AboutMenuItem();
	Menu helpMenu = new Menu("Help");
	helpMenu.getItems().addAll(aboutItem);
	// Menu Bar
	MenuBar menuBar = new MenuBar();
	menuBar.getMenus().addAll(fileMenu, windowMenu, helpMenu);
	root.setTop(menuBar);
    }

    @Override
    public void stop() {
	ReactiveFX.shutdown();
	PerspectiveService.shutdown();
	Preferences.shutdown();
	try {
	    FXThreads.shutdown();
	} catch (InterruptedException e) {
	    logger.warn("FXThreads were not cleanly shutdown.", e);
	}
    }

    public static void main(String[] args) throws InterruptedException {
	Workspace.launchApplicationInWorkspace(DebuggingClient.class, args);
    }

}
