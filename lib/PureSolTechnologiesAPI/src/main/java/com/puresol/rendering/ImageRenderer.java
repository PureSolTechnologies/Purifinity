package com.puresol.rendering;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class ImageRenderer implements Renderer, ImageObserver {

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
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		final int x = Math.min(x1, x2);
		final int y = Math.min(y1, y2);
		final int w = Math.abs(x2 - x1);
		final int h = Math.abs(y2 - y1);
		graphics.drawImage(image, x, y, w, h, this);

	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		return false;
	}

}
