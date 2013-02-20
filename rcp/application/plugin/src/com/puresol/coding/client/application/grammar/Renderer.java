package com.puresol.coding.client.application.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.GC;

/**
 * This interface is used for all classes which provide a rendering service.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Renderer {

    /**
     * This method returns the preferred drawing size. This size is calculated
     * taking no scale into account. It's a 100% drawing size assumption.
     * 
     * @return
     */
    public Dimension getPreferredSize();

    /**
     * This method renders the image at the given x1,y1 position until x2.y2
     * position. The rendering is performed within this rectangle without taking
     * into account, that getPreferredSize might give other dimensions. How the
     * renderer reacts to different size ratios and different sizes is up to
     * him.
     * 
     * @param graphics
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void render(GC graphics, int x1, int y1, int x2, int y2);
}
