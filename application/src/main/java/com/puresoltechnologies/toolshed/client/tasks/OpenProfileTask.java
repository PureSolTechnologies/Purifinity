package com.puresoltechnologies.toolshed.client.tasks;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.profiles.Profile;

import javafx.concurrent.Task;
import javafx.scene.image.Image;

public class OpenProfileTask extends Task<Profile> {

    private final File rawProfileFile;
    private final Image openFolder;

    public OpenProfileTask(File rawProfileFile) {
	try {
	    this.rawProfileFile = rawProfileFile;
	    openFolder = ResourceUtils.getImage(this, "/icons/FatCow_Icons16x16/open_folder.png");
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    protected Profile call() throws Exception {
	Profile profile = new Profile(rawProfileFile);
	profile.setProgressObserver((workDone, max) -> {
	    this.updateProgress(workDone, max);
	});
	profile.read();
	return profile;
    }

    public Image getImage() {
	return openFolder;
    }

}
