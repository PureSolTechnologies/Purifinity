package javax.swingx.rendering;

public abstract class AbstractRenderer implements Renderer {

	private double scale = 1.0;
	private int x = 0;
	private int y = 0;

	@Override
	public final void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	public final double getScale() {
		return scale;
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
