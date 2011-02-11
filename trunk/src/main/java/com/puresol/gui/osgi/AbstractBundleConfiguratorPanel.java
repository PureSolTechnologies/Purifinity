package com.puresol.gui.osgi;

import javax.swing.JPanel;

/**
 * This interface is implements for panels which are used within the bundle
 * configuration dialog.
 * 
 * @author ludwig
 * 
 */
public abstract class AbstractBundleConfiguratorPanel extends JPanel {

	private static final long serialVersionUID = 1764610714089798469L;

	public abstract void start();

	public abstract boolean stop();

}
