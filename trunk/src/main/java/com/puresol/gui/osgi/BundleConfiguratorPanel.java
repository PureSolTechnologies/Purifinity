package com.puresol.gui.osgi;

import javax.swingx.Panel;

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

	public Panel getPanel();

}
