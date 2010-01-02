/***************************************************************************
 *
 *   PureSolApplication.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.i18n4j.Translator;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swingx.Application;
import javax.swingx.Menu;
import javax.swingx.MenuItem;
import javax.swingx.SplashWindow;
import javax.swingx.connect.Slot;
import javax.swingx.log.LoggingDialog;

import org.apache.log4j.Logger;

/**
 * This class gives all application using it some special features which lead to
 * a consistent corporate design. The features are an automatically added help
 * menu with standard entries for X-FAB, a X-FAB logo and a splash screen during
 * program start. The splash screen was needed after introducing Hibernate due
 * to a longer startup time, because of heavy initialization.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PureSolApplication extends Application {

	private static final long serialVersionUID = 8061458459180754032L;

	private static final Logger logger = Logger
			.getLogger(PureSolApplication.class);
	private static final Translator translator = Translator
			.getTranslator(PureSolApplication.class);

	/**
	 * This variable stores the splash screen.
	 */
	private static SplashWindow splash = null;
	static {
		/**
		 * During initialization of static elements (it's one of the first
		 * things done during start time)the splash screen is opened.
		 */
		splash = new SplashWindow(PureSolApplication.class
				.getResource("/META-INF/splash.jpeg"), 640, 300);
		splash.setClosable(true);
		splash.setTimer(10);
		splash.run();
	}

	public PureSolApplication(String title) {
		super(title);
		setLookAndFeel();
	}

	private void setLookAndFeel() {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			logger.warn(e.getMessage(), e);
		} catch (InstantiationException e) {
			logger.warn(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.warn(e.getMessage(), e);
		} catch (UnsupportedLookAndFeelException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	public void initSystemTray() {
		if (!SystemTray.isSupported()) {
			logger.warn("SystemTray is not supported");
			return;
		}
		try {
			TrayIcon icon = PureSolSystemTray.getTrayIcon();
			icon.setImageAutoSize(true);
			SystemTray.getSystemTray().add(icon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
	}

	public SplashWindow getSplash() {
		return splash;
	}

	public void run() {
		if (splash != null) {
			logger.debug("Remove splash window...");
			splash.dispose();
			splash = null;
		}
		super.run();
	}

	/**
	 * This method has the same functionality like JFrame.setJMenuBar, but adds
	 * a standard help menu and a logo at the right edge of the menu
	 * automatically.
	 */
	public void setJMenuBar(JMenuBar menubar) {
		menubar.add(Box.createHorizontalGlue());
		menubar.add(getDefaultHelpMenu());
		addXFabLink(menubar);
		super.setJMenuBar(menubar);
	}

	/**
	 * Creates the default help menu for this class.
	 * 
	 * @return A help menu is returned.
	 */
	private Menu getDefaultHelpMenu() {
		Menu helpMenu = new Menu(translator.i18n("Help"));

		MenuItem webItem = new MenuItem(translator
				.i18n("PureSol-Technologies Website..."));
		webItem.connect("start", this, "openPureSolTechnologiesWebsite");

		MenuItem logItem = new MenuItem(translator.i18n("Set log level..."));
		logItem.connect("start", this, "setLogLevel");

		helpMenu.add(webItem);
		helpMenu.addSeparator();
		helpMenu.add(logItem);
		helpMenu.addSeparator();
		helpMenu.addDefaultAboutItem();
		return helpMenu;
	}

	/**
	 * This method adds the logo at the end of the menu bar just behind the help
	 * menu.
	 * 
	 * @param menubar
	 *            is the menubar to add the logo to.
	 */
	private void addXFabLink(JMenuBar menubar) {
		Image image = null;

		URL imageURL = this.getClass().getResource("/META-INF/logo.jpeg");
		if (imageURL == null) {
			return;
		}
		image = Toolkit.getDefaultToolkit().getImage(imageURL);
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);
		try {
			mt.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
		if ((image.getWidth(this) <= 0) || (image.getWidth(this) <= 0)) {
			return;
		}
		BufferedImage logoImage = new BufferedImage(192, 36,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = logoImage.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(1, 1, 190, 34);
		graphics.drawImage(image, 6, 6, 180, 24, this);
		Icon icon = new ImageIcon(logoImage);
		JLabel xFabMenu = new JLabel(icon);
		menubar.add(xFabMenu);
	}

	@Slot
	public void openPureSolTechnologiesWebsite() {
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URI("http://www.puresol-technologies.com"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showConfirmDialog(this, translator
					.i18n("IO error during opening the URI."), translator
					.i18n("IO error"), JOptionPane.OK_OPTION,
					JOptionPane.ERROR_MESSAGE);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showConfirmDialog(this, translator
					.i18n("URI is invalid."), translator.i18n("URI error"),
					JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

	@Slot
	public void setLogLevel() {
		new LoggingDialog().run();
	}
}
