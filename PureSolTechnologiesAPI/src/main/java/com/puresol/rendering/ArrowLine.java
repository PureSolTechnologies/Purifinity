package com.puresol.rendering;

import java.awt.Graphics;

public class ArrowLine {

	private final Graphics graphics;

	private Arrow startArrow = null;
	private Arrow endArrow = null;

	public ArrowLine(Graphics graphics) {
		super();
		this.graphics = graphics;
	}

	public Arrow getStartArrow() {
		return startArrow;
	}

	public void setStartArrow(Arrow startArrow) {
		this.startArrow = startArrow;
	}

	public Arrow getEndArrow() {
		return endArrow;
	}

	public void setEndArrow(Arrow endArrow) {
		this.endArrow = endArrow;
	}

	public void draw(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2);
		if (startArrow != null) {
			startArrow.draw(x2, y2, x1, y1);
		}
		if (endArrow != null) {
			startArrow.draw(x1, y1, x2, y2);
		}
	}
}
