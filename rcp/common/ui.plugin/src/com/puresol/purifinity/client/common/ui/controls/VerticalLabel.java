package com.puresol.purifinity.client.common.ui.controls;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class VerticalLabel extends Canvas implements PaintListener {

	private String text;

	public VerticalLabel(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Transform transform = new Transform(getDisplay());
		transform.rotate(270);
		gc.setTransform(transform);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int width = fontMetrics.getAverageCharWidth() * text.length();
		int height = fontMetrics.getHeight();
		Point size = getSize();
		gc.drawText(text, -size.y / 2 - width / 2, height);
	}
}
