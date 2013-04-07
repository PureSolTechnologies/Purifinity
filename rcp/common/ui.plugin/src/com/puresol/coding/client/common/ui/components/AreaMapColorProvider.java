package com.puresol.coding.client.common.ui.components;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

/**
 * This provider can be set to {@link AreaMapComponent} to color the different
 * areas in dependence to the secondary value set. This providers returns a
 * {@link Color} object for a secondary {@link Object} value provided.
 */
public interface AreaMapColorProvider {

	/**
	 * <p>
	 * This method calculates a {@link Color} value from a given {@link Object}
	 * value provided. This method is explicitly allowed to return a
	 * <code>null</code> value. In that case {@link AreaMapComponent} will not
	 * color the area.
	 * </p>
	 * <p>
	 * <b>Important:</b> The color is created for the specified value. The
	 * caller of this method needs to dispose it via {@link Color#dispose()}!
	 * </p>
	 * 
	 * @param device
	 *            is the device the color is to be created.
	 * @param value
	 *            is the secondary value set to {@link AreaMapData}.
	 * @return A {@link Color} object is returned for the color to use or null,
	 *         if not coloring is required.
	 */
	Color createColor(Device device, Object value);

}
