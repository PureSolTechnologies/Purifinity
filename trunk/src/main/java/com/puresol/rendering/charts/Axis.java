package com.puresol.rendering.charts;

import java.awt.Graphics;

import com.puresol.rendering.Arrow;

public class Axis {

	private final double min;
	private final double max;
	private final double stepWidth;

	public Axis(double min, double max, double stepWidth) {
		super();
		this.min = min;
		this.max = max;
		this.stepWidth = stepWidth;
	}

	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		new Arrow(g).draw(x1, y1, x2, y2);
		double slope = (y2 - y1) / (x2 - x1);
		int steps = (int) Math.round((max - min) / stepWidth);
		for (int step = 0; step < steps; step++) {
			g.drawString(String.valueOf(min * steps * stepWidth),
					(int) (x1 + slope * step * stepWidth), (int) (y1 + slope
							* step * stepWidth));
		}
	}
}
