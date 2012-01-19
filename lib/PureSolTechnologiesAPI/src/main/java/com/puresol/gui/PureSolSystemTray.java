/***************************************************************************
 *
 *   PureSolSystemTray.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PureSolSystemTray {

    private static final Logger logger = LoggerFactory
	    .getLogger(PureSolSystemTray.class);

    private static final SystemTray systemTray = SystemTray.getSystemTray();
    private static TrayIcon trayIcon = null;

    public static TrayIcon getTrayIcon() {
	URL imageURL = PureSolSystemTray.class
		.getResource("/META-INF/logo.jpeg");
	Image image = null;
	if (imageURL == null) {
	    logger.error("Resource not found: " + imageURL);
	    return null;
	} else {
	    image = new ImageIcon(imageURL, "Logo").getImage();
	}
	trayIcon = new TrayIcon(image);
	return trayIcon;
    }

    public static SystemTray getSystemTray() {
	return systemTray;
    }
}
