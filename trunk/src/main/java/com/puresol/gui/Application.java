/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui;

import static com.puresol.config.APIInformation.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.puresol.config.ConfigFile;

public class Application extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Application.class);
	private static final Translator translator = Translator
			.getTranslator(Application.class);
	private static List<Thread> threads = new ArrayList<Thread>();

	/**
	 * This variable keeps the reference to translator.
	 */
	static private Application instance = null;

	static public Application getInstance() {
		return instance;
	}

	public static void showNotImplementedMessage() {
		JOptionPane.showMessageDialog(getInstance(),
				translator.i18n("This function is not implemented yet!"),
				translator.i18n("No Implementation"),
				JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_OPTION);
	}

	public static void showStandardErrorMessage(String string, Throwable e) {
		StringBuffer stackTrace = new StringBuffer();
		for (StackTraceElement element : e.getStackTrace()) {
			stackTrace.append(element.toString() + "\n");
		}
		JOptionPane.showConfirmDialog(getInstance(), string
				+ "\n\nMessage:\n\"" + e.getMessage() + "\"\n\nTrace:\n\""
				+ stackTrace.toString() + "\"", "An error occured",
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * This method generates the default copyright message of this API in HTML
	 * code.
	 * 
	 * @return A String containing the copyright information is returned in HTML
	 *         code.
	 */
	public static String getCopyrightMessage() {
		return createAboutMessage("config/about", "COPYRIGHT");
	}

	/**
	 * This method generates the default vendor information of this API in HTML
	 * code.
	 * 
	 * @return A String containing the vendor information is returned in HTML
	 *         code.
	 */
	public static String getVendorInformation() {
		return createAboutMessage("config/about", "VENDOR");
	}

	/**
	 * This method generates the default contact information of this API in HTML
	 * code.
	 * 
	 * @return A String containing the contact information is returned in HTML
	 *         code.
	 */
	public static String getContactInformation() {
		return createAboutMessage("config/about", "CONTACT");
	}

	private static String createAboutMessage(String file, String section) {
		Application application = Application.getInstance();
		String message = ConfigFile.readSection(file, section);
		if (application != null) {
			message = message.replaceAll(
					"%APPLICATION%",
					application.getApplicationTitle() + " "
							+ application.getApplicationVersion());
		} else {
			message = message.replaceAll("%APPLICATION%", "Noname Application");
		}
		message = message.replaceAll("%VERSION%", getPackageVersion());
		message = message.replaceAll("%YEARS%", getPackageYears());
		message = message.replaceAll("%OWNER%", getPackageOwner());
		message = message.replaceAll("%AUTHOR%", getPackageAuthor());
		message = message.replaceAll("%COPYRIGHT%", getPackageCopyright());
		message = message.replaceAll("%BUGREPORT%", getPackageBugReport());
		return message;
	}

	private final String title;
	private final String version;
	private String subtitle;

	public Application(String title, String version, String subtitle) {
		super(title + " " + version + " [" + subtitle + "]");
		this.title = title;
		this.version = version;
		this.subtitle = subtitle;
		setInstance();
		setClosingBehavior();
	}

	public Application(String title, String version) {
		super(title + " " + version);
		this.title = title;
		this.version = version;
		this.subtitle = "";
		setInstance();
		setClosingBehavior();
	}

	private synchronized void setInstance() {
		if (instance != null) {
			throw new RuntimeException("Application was started double!");
		}
		instance = this;
		logger.info("Application instance with name '" + getTitle()
				+ "' was registered.");
	}

	private void setClosingBehavior() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
	}

	public String getApplicationTitle() {
		return title;
	}

	public String getApplicationVersion() {
		return version;
	}

	public void setSubtitle(String subTitle) {
		this.subtitle = subTitle;
		if (subTitle.isEmpty()) {
			super.setTitle(title + " " + version);
		} else {
			super.setTitle(title + " " + version + " [" + subTitle + "]");
		}
		System.out.println(super.getTitle());
	}

	public String getSubtitle() {
		return subtitle;
	}

	@Override
	public void run() {
		pack();
		logger.info("Starting application...");
		setVisible(true);
	}

	public void quit() {
		logger.info("Quitting application...");
		stopAllThreads();
		dispose();
	}

	private void stopAllThreads() {
		for (Thread thread : threads) {
			thread.interrupt();
		}
		threads.clear();
	}

	public Thread getThread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		addThread(thread);
		return thread;
	}

	public void addThread(Thread thread) {
		threads.add(thread);
	}

	public void removeThreadToQuit(Thread thread) {
		threads.remove(thread);
	}

}
