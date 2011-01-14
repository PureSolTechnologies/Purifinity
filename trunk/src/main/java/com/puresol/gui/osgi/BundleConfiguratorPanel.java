package com.puresol.gui.osgi;

import javax.swing.JPanel;

/**
 * This interface is implements for panels which are used within the bundle
 * configuration dialog.
 * 
 * @author ludwig
 * 
 */
public interface BundleConfiguratorPanel {

	public void start();

	public boolean stop();

	public JPanel getPanel();

}
