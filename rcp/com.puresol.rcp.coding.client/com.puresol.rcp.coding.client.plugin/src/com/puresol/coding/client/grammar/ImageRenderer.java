package com.puresol.coding.client.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public class ImageRenderer implements Renderer {

    private final Image image;

    public ImageRenderer(Image image) {
	super();
	this.image = image;
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(1024, 768);
    }

    @Override
    public void render(GC graphics, int x1, int y1, int x2, int y2) {
	final int x = Math.min(x1, x2);
	final int y = Math.min(y1, y2);
	final int w = Math.abs(x2 - x1);
	final int h = Math.abs(y2 - y1);
	graphics.drawImage(image, 0, 0, image.getImageData().width,
		image.getImageData().height, x, y, w, h);

    }

}
