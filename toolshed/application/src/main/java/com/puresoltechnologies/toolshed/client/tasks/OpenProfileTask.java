package com.puresoltechnologies.toolshed.client.tasks;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.profiles.Profile;

import javafx.concurrent.Task;
import javafx.scene.image.Image;

public class OpenProfileTask extends Task<Profile> {

    private static final Logger logger = LoggerFactory.getLogger(OpenProfileTask.class);

    private final File rawProfileFile;
    private final Image openFolder;

    public OpenProfileTask(File rawProfileFile) {
	super();
	try {
	    this.rawProfileFile = rawProfileFile;
	    updateTitle("Opening Profile");
	    updateMessage("Opening '" + rawProfileFile + "'...");
	    openFolder = ResourceUtils.getImage(this, "/icons/FatCow_Icons32x32/open_folder.png");
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    protected Profile call() {
	try {
	    Profile profile = new Profile(rawProfileFile);
	    profile.setProgressObserver((workDone, max) -> {
		this.updateProgress(workDone, max);
	    });
	    profile.read();
	    return profile;
	} catch (Exception e) {
	    logger.error("Could not open profile.", e);
	    return null;
	}
    }

    public Image getImage() {
	return openFolder;
    }

}
