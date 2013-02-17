package com.puresol.coding.client.evaluation;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.puresol.coding.evaluation.api.SourceCodeQuality;

/**
 * This is a collection of utilities which are used to calculate colors for
 * output.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ColorUtils {

	/**
	 * This method calculates the color for a cetain software quality.
	 * 
	 * @param display
	 * @param quality
	 * @return
	 */
	public static Color getColor(Display display, SourceCodeQuality quality) {
		RGB rgb;
		if (quality == SourceCodeQuality.HIGH) {
			rgb = new RGB(96, 255, 96);
		} else if (quality == SourceCodeQuality.MEDIUM) {
			rgb = new RGB(255, 255, 96);
		} else if (quality == SourceCodeQuality.LOW) {
			rgb = new RGB(255, 96, 96);
		} else {
			rgb = new RGB(255, 255, 255);
		}
		return new Color(display, rgb);
	}

}
