package com.puresol.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.i18n4j.Translator;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swingx.Application;
import javax.swingx.Menu;
import javax.swingx.SplashWindow;
import javax.swingx.connect.Slot;

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
		// splash = new SplashWindow(PureSolApplication.class
		// .getResource("/META-INF/splash.jpeg"), 480, 225);
		splash = new SplashWindow(PureSolApplication.class
				.getResource("/META-INF/splash.jpeg"), 640, 300);
		splash.setClosable(true);
		splash.setTimer(10);
		splash.run();
	}

	public PureSolApplication(String title) {
		super(title);
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
	public void openXFabSite() {
		openURLInDefaultBrowser("http://www.xfab.com");
	}

	@Slot
	public void openXFabDDSite() {
		openURLInDefaultBrowser("http://intranet.drs.xfab.de");
	}

	@Slot
	public void openSharepointSite() {
		openURLInDefaultBrowser("http://sp.drs.xfab.de");
	}

	@Slot
	public void openProcessDocumentation() {
		openURLInDefaultBrowser("http://pcmcenter/operating/scripts/desktop.php?lang=en&menu=operating");
	}

	private void openURLInDefaultBrowser(String urlString) {
		try {
			openURLInDefaultBrowser(new URL(urlString));
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void openURLInDefaultBrowser(URL url) {
		try {
			Desktop desktop = Desktop.getDesktop();
			if (!desktop.isSupported(Desktop.Action.BROWSE)) {
				logger
						.error("Desktop doesn't support the browse action (fatal)");
				return;
			}
			desktop.browse(url.toURI());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
