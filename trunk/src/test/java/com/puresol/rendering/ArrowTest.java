package com.puresol.rendering;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swingx.Dialog;
import javax.swingx.Panel;

import org.junit.Before;
import org.junit.Test;

import com.puresol.rendering.Arrow.ArrowType;

public class ArrowTest {

	private Graphics graphics;

	@Before
	public void prepareEnvironment() {
		BufferedImage image = new BufferedImage(1024, 768,
				BufferedImage.TYPE_INT_RGB);
		graphics = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.createGraphics(image);
		assertNotNull(graphics);
	}

	@Test
	public void testInstance() {
		assertNotNull(new Arrow(graphics));
	}

	@Test
	public void testInitialValues() {
		Arrow arrow = new Arrow(graphics);
		assertEquals(45.0, arrow.getTipAngle(), 1e-6);
		assertEquals(16, arrow.getTipLength(), 1e-6);
		assertEquals(0.5, arrow.getTipShorting(), 1e-6);
		assertEquals(ArrowType.SIMPLE, arrow.getType());
	}

	public static void main(String args[]) {
		Dialog frame = new Dialog();
		frame.add(new Panel() {
			private static final long serialVersionUID = 8456563011009861087L;

			private void drawArrowSpiral(Arrow arrow, int length) {
				for (int angle = 0; angle < 360; angle += 15) {
					arrow.draw(
							200,
							200,
							(int) (200 + length
									* Math.cos(angle / 180.0 * Math.PI)),
							(int) (200 - length
									* Math.sin(angle / 180.0 * Math.PI)));
				}
			}

			public void paint(Graphics graphics) {
				super.paint(graphics);
				Arrow arrow = new Arrow(graphics);
				drawArrowSpiral(arrow, 200);
				arrow.setTipAngle(90);
				arrow.setTipLength(10);
				arrow.setType(ArrowType.FILLED_TRIANGLE);
				drawArrowSpiral(arrow, 150);
				arrow.setTipAngle(35);
				arrow.setTipLength(32);
				arrow.setType(ArrowType.FANCY);
				drawArrowSpiral(arrow, 100);
				arrow.setTipAngle(22);
				arrow.setTipLength(15);
				arrow.setType(ArrowType.FILLED_TRIANGLE);
				drawArrowSpiral(arrow, 50);
			}
		});
		frame.run();

	}

}
