package com.puresoltechnologies.purifinity.client.common.ui;

import org.eclipse.swt.graphics.RGB;

/**
 * This class contains standard colors in form of RGB objects to be used in RCP
 * applications.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SWTColor {

	public static final RGB BLACK = new RGB(0, 0, 0);
	public static final RGB WHITE = new RGB(255, 255, 255);
	public static final RGB RED = new RGB(255, 0, 0);
	public static final RGB GREEN = new RGB(0, 255, 0);
	public static final RGB BLUE = new RGB(0, 0, 255);

	public static final RGB DARK_RED = new RGB(192, 0, 0);

	public static final RGB PALE_RED = new RGB(255, 210, 210);
	public static final RGB PALE_GREEN = new RGB(210, 255, 210);
	public static final RGB PALE_YELLOW = new RGB(255, 255, 210);
	public static final RGB PALE_BLUE = new RGB(210, 210, 255);

	/**
	 * Private constructor to avoid instantiation.
	 */
	private SWTColor() {
	}

}
