package com.puresol.utils;

import java.io.File;

public class Directories {

    public static boolean checkAndCreateDirectory(File directory) {
	if (!directory.exists()) {
	    if (!directory.mkdir()) {
		return false;
	    }
	}
	return true;
    }

}
