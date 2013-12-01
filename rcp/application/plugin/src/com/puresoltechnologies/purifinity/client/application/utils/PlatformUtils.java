package com.puresoltechnologies.purifinity.client.application.utils;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * This class is a collection of some utilities which help to work with the
 * Eclipse platform.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PlatformUtils {

	/**
	 * This method returns the current workspace directory.
	 * 
	 * @return A File is returned representing the current workspace directory.
	 */
	public static File getWorkspaceDirectory() {
		Location instanceLocation = Platform.getInstanceLocation();
		URL locationURL = instanceLocation.getURL();
		if (locationURL == null) {
			throw new IllegalStateException(
					"The location URL can not be retrieved!");
		}
		return new File(locationURL.getPath());
	}

}
