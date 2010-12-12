package javax.swingx.rendering;

import java.awt.Dimension;

/**
 * This interface is used for all classes which provide a rendering service.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Renderer {

	/**
	 * This method sets the scale for the renderer. The image is scaled by this
	 * factor.
	 * 
	 * @param scale
	 */
	public void setScale(double scale);

	public double getScale();

	/**
	 * This method returns the preferred drawing size. This size is calculated
	 * taking the scale into account.
	 * 
	 * @return
	 */
	public Dimension getPreferredSize();

	/**
	 * This method renders the image at the given x,y position with the size w,h
	 * given. The rendering is performed within this rectangle without taking
	 * into account, that getPreferredSize might give other dimensions.
	 * 
	 * Taking care for enough space to render the image in is the responsibility
	 * of the calling object.
	 * 
	 * @param graphics
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void render();

	/**
	 * This method sets the position of the object to be rendered. This must not
	 * be done every time the object is re-rendered. So complex renderings can
	 * be done in advance and get just redrawn.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void setPosition(int x, int y);

}
