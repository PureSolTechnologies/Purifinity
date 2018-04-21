package com.puresoltechnologies.toolshed.client;

import java.io.File;

import com.puresoltechnologies.javafx.reactive.Topic;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;

/**
 * This enum contains all topics used for
 */
public class Topics {

    public static final Topic<File> PROFILE_OPENED = new Topic<>("profile.opened", File.class);
    public static final Topic<Profile> NEW_PROFILE = new Topic<>("new.profile", Profile.class);
    public static final Topic<ProfileEntry> PROFILE_ENTRY_SELECTED = new Topic<>("profile.entry.selected",
	    ProfileEntry.class);

}
