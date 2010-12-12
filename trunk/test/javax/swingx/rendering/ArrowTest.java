package javax.swingx.rendering;

import java.awt.Graphics;

import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.rendering.Arrow.ArrowType;

public class ArrowTest {

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
